/*
 * This software is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain.
 * 
 * This software is experimental. NIST assumes no responsibility whatsoever 
 * for its use by other parties, and makes no guarantees, expressed or 
 * implied, about its quality, reliability, or any other characteristic. 
 * We would appreciate acknowledgement if the software is used. 
 * This software can be redistributed and/or modified freely provided 
 * that any derivative works bear some notice that they are derived from it, 
 * and any modified versions bear some notice that they have been modified.
 * 
 *  See http://www.copyright.gov/title17/92chap1.html#105
 * 
 */
package com.github.wshackle.crcl4java.motoman;

import com.github.wshackle.crcl4java.motoman.MotoPlusConnection.MotoPlusConnectionException;
import com.github.wshackle.crcl4java.motoman.motctrl.CoordTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.JointTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_COORD_TYPE;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_INTP_TYPE;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_SPEED;
import com.github.wshackle.crcl4java.motoman.motctrl.MotCtrlReturnEnum;
import com.github.wshackle.crcl4java.motoman.sys1.MP_ALARM_CODE_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_CART_POS_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_MODE_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_PULSE_POS_RSP_DATA;
import crcl.base.ActuateJointType;
import crcl.base.ActuateJointsType;
import crcl.base.AngleUnitEnumType;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStateEnumType;
import static crcl.base.CommandStateEnumType.CRCL_DONE;
import static crcl.base.CommandStateEnumType.CRCL_ERROR;
import static crcl.base.CommandStateEnumType.CRCL_READY;
import static crcl.base.CommandStateEnumType.CRCL_WORKING;
import crcl.base.CommandStatusType;
import crcl.base.ConfigureJointReportsType;
import crcl.base.DwellType;
import crcl.base.EndCanonType;
import crcl.base.GetStatusType;
import crcl.base.InitCanonType;
import crcl.base.JointDetailsType;
import crcl.base.JointSpeedAccelType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.LengthUnitEnumType;
import crcl.base.MessageType;
import crcl.base.MoveToType;
import crcl.base.PoseStatusType;
import crcl.base.RotSpeedAbsoluteType;
import crcl.base.RotSpeedRelativeType;
import crcl.base.RotSpeedType;
import crcl.base.SetAngleUnitsType;
import crcl.base.SetEndEffectorType;
import crcl.base.SetLengthUnitsType;
import crcl.base.SetRotSpeedType;
import crcl.base.SetTransSpeedType;
import crcl.base.StopMotionType;
import crcl.base.TransSpeedAbsoluteType;
import crcl.base.TransSpeedRelativeType;
import crcl.base.TransSpeedType;
import crcl.utils.CRCLException;
import crcl.utils.CRCLPosemath;
import static crcl.utils.CRCLPosemath.pose;
import crcl.utils.CRCLServerSocket;
import crcl.utils.CRCLServerSocketEvent;
import crcl.utils.CRCLServerSocketEventListener;
import crcl.utils.CRCLSocket;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rcs.posemath.PmCartesian;
import rcs.posemath.PmException;
import rcs.posemath.PmRpy;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Consumer;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import rcs.posemath.PmEulerZyx;
import rcs.posemath.PmRotationMatrix;
import rcs.posemath.PmRotationVector;
import rcs.posemath.Posemath;

/**
 *
 * @author shackle
 */
public class MotomanCrclServer implements AutoCloseable, CRCLServerSocketEventListener, Runnable {

    private final CRCLServerSocket svrSocket;
    private final MotoPlusConnection mpc;

    public MotomanCrclServer(CRCLServerSocket svrSocket, MotoPlusConnection mpConnection) {
        this.svrSocket = svrSocket;
        this.mpc = mpConnection;
        this.svrSocket.addListener(this);
    }

    public boolean mpcConnected() {
        return mpc.isConnected();
    }

    @Override
    public void close() throws Exception {
        svrSocket.close();
        mpc.close();
    }

    private final CRCLStatusType crclStatus = new CRCLStatusType();

    {
        crclStatus.setCommandStatus(new CommandStatusType());
        getCommandStatus().setCommandID(1);
        getCommandStatus().setStatusID(1);
        crclStatus.setPoseStatus(new PoseStatusType());
        crclStatus.getPoseStatus().setPose(pose(point(0, 0, 0), vector(1, 0, 0), vector(0, 0, 1)));
        getCommandStatus().setCommandState(CRCL_READY);
    }

    private long last_status_update_time = -1;

    private double lengthScale = 1.0;
    private final int lastJointPos[] = new int[MP_PULSE_POS_RSP_DATA.MAX_PULSE_AXES];
    private boolean lastErrorWasWrongMode = false;

    private final ConcurrentLinkedDeque<Consumer<String>> logListeners = new ConcurrentLinkedDeque<>();

    public void addLogListener(Consumer<String> consumer) {
        logListeners.add(consumer);
    }

    public void removeLogListener(Consumer<String> consumer) {
        logListeners.remove(consumer);
    }

    private void log(String s) {
        for (Consumer<String> consumer : logListeners) {
            consumer.accept(s);
        }
    }

    private void logErr(String s) {
        System.err.println(s);
        log(s);
    }
    
    public CRCLStatusType getCrclStatus() {
        long time = System.currentTimeMillis();
        long time_diff = time - last_status_update_time;
//        System.out.println("time = " + time);
//        System.out.println("time_diff = " + time_diff);
        if (time_diff > 50) {
            try {
                if (null == mpc || !mpc.isConnected()) {
                    setStateDescription(CRCL_ERROR, "Not connected to Robot.");
                    getCommandStatus().setStatusID(getCommandStatus().getStatusID() + 1);
                    return crclStatus;
                }
                MP_CART_POS_RSP_DATA pos = mpc.getCartPos(0);
                PmCartesian cart = new PmCartesian(pos.x() / lengthScale, pos.y() / lengthScale, pos.z() / lengthScale);
                PmEulerZyx zyx = new PmEulerZyx(Math.toRadians(pos.rz()), Math.toRadians(pos.ry()), Math.toRadians(pos.rx()));
//                double rx = pos.rx();
//                double ry = pos.ry();
//                double rz = pos.rz();
//                double rotMag = Math.toRadians(Math.sqrt(rx * rx + ry * ry + rz * rz));
                PmRotationMatrix mat = Posemath.toMat(zyx);//new PmRotationVector(rotMag, rx / rotMag, ry / rotMag, rz / rotMag);
                crclStatus.getPoseStatus().setPose(CRCLPosemath.toPoseType(cart, mat, crclStatus.getPoseStatus().getPose()));
//                crclStatus.getPoseStatus().getPose().setPoint(point(cartData[0].x(), cartData[0].y(), cartData[0].z()));
                MP_PULSE_POS_RSP_DATA pulseData = mpc.getPulsePos(0);
                if (null == crclStatus.getJointStatuses()) {
                    crclStatus.setJointStatuses(new JointStatusesType());
                }
                List<JointStatusType> jsl = crclStatus.getJointStatuses().getJointStatus();
                for (int i = 0; i < MP_PULSE_POS_RSP_DATA.MAX_PULSE_AXES; i++) {
                    int diff = pulseData.lPos[i] - lastJointPos[i];
                    if (i >= jsl.size()) {
                        JointStatusType js = new JointStatusType();
                        js.setJointNumber(i + 1);
                        js.setJointPosition((double) pulseData.lPos[i]);
                        if (last_status_update_time > 0) {
                            double vj = ((double) diff) / time_diff;
                            js.setJointVelocity(vj);
//                            System.out.println("i = " + i + "vj = " + vj);
                        }
                        jsl.add(js);
                    } else {
                        JointStatusType js = jsl.get(i);
                        js.setJointNumber(i + 1);
                        js.setJointPosition((double) pulseData.lPos[i]);

                        if (last_status_update_time > 0) {
                            double vj = ((double) diff) / time_diff;
                            js.setJointVelocity(vj);
//                            System.out.println("i = " + i + ", vj = " + vj);
                        }
                        jsl.set(i, js);
                    }
                    lastJointPos[i] = pulseData.lPos[i];
                }
                System.arraycopy(pulseData.lPos, 0, lastJointPos, 0, lastJointPos.length);

                if (getCommandState() == CRCL_WORKING) {
                    if (lastSentTargetId != lastRecvdTargetId) {
//                        System.out.println("lastSentTargetId = " + lastSentTargetId);
//                        System.out.println("lastRecvdTargetId = " + lastRecvdTargetId);
                        int recvId[] = new int[1];
                        mpc.mpMotTargetReceive(0, lastSentTargetId, recvId, 0, MotoPlusConnection.NO_WAIT);
                        if (recvId[0] != 0) {
                            lastRecvdTargetId = recvId[0];
                            if (debug) {
                                System.out.println("recvId = " + Arrays.toString(recvId));
                            }
                        }
                        if (lastSentTargetId == lastRecvdTargetId) {
                            getCommandStatus().setCommandState(CRCL_DONE);
                        }
                    }
                    if (dwelling && System.currentTimeMillis() > dwellEnd) {
                        getCommandStatus().setCommandState(CRCL_DONE);
                        dwelling = false;
                    }
                }
                if (getCommandState() != CRCL_ERROR || lastErrorWasWrongMode) {
                    MP_MODE_DATA modeData = mpc.mpGetMode();
                    boolean wrongMode = (modeData.sRemote == 0);
                    if (wrongMode) {
                        setStateDescription(CRCL_ERROR, "Pendant switch must be set to REMOTE. : current mode = " + modeData.toString());
                        lastErrorWasWrongMode = true;
                    } else if (lastErrorWasWrongMode) {
                        getCommandStatus().setStateDescription("");
                    }
                }
                if (getCommandState() != CRCL_ERROR) {
                    lastErrorWasWrongMode = false;
                }
                if (getCommandState() != CRCL_ERROR) {
                    if (mpc.mpGetAlarmStatus().sIsAlarm != 0) {
                        MP_ALARM_CODE_DATA alarmData = mpc.mpGetAlarmCode();
                        setStateDescription(CRCL_ERROR, "alarmData = " + alarmData);
                    }
                    lastErrorWasWrongMode = false;
                }

                last_status_update_time = System.currentTimeMillis();
            } catch (IOException | PmException | MotoPlusConnection.MotoPlusConnectionException ex) {
                logException(ex);
                try {
                    mpc.close();
                } catch (Exception ex1) {
                    logException(ex1);
                }
                setStateDescription(CRCL_ERROR, ex.getMessage());
            }
        }
        getCommandStatus().setStatusID(getCommandStatus().getStatusID() + 1);
        return crclStatus;
    }

    private CommandStateEnumType lastState = CRCL_WORKING;
    private String lastDescription = "";

    private void setStateDescription(CommandStateEnumType state, String description) {
        getCommandStatus().setCommandState(state);
        getCommandStatus().setStateDescription(description);
        if (lastState != state || !lastDescription.equals(description)) {
            log(state + ":" + description);
            lastState = state;
            lastDescription = description;
        }
    }

    private void logException(final java.lang.Exception ex) {
        Logger.getLogger(MotomanCrclServer.class.getName()).log(Level.SEVERE, null, ex);
        log(ex.toString());
    }

    private volatile boolean initialized = false;

    private void stopMotion() throws IOException {
        MotCtrlReturnEnum stopRet = mpc.mpMotStop(0);
        if (debug) {
            System.out.println("stopRet = " + stopRet);
        }
        MotCtrlReturnEnum clearRet = mpc.mpMotTargetClear(0xf, 0);
        if (debug) {
            System.out.println("clearRet = " + clearRet);
        }
    }

    private int lastSentTargetId = 1;
    private int lastRecvdTargetId = 1;
    private double maxVelocity = 100.0;

    private LengthUnitEnumType currentLengthUnits = LengthUnitEnumType.MILLIMETER;
    private AngleUnitEnumType currentAngleUnits = AngleUnitEnumType.DEGREE;

    private final double MM_TO_INCH = 0.0393701;

    private void setTransSpeed(SetTransSpeedType sts) throws IOException {
        MP_SPEED spd = new MP_SPEED();
        TransSpeedType ts = sts.getTransSpeed();
        if (ts instanceof TransSpeedAbsoluteType) {
            TransSpeedAbsoluteType tsa = (TransSpeedAbsoluteType) ts;
            switch (currentLengthUnits) {
                case MILLIMETER:
                    spd.v = (int) (tsa.getSetting() * 10.0);
                    break;

                case INCH:
                    spd.v = (int) (tsa.getSetting() * 10.0 / MM_TO_INCH);
                    break;

                case METER:
                    spd.v = (int) (tsa.getSetting() * 10_000.0);
                    break;
            }
            if (debug) {
                System.out.println("spd = " + spd);
            }
            mpc.mpMotSetSpeed(0, spd);
        } else if (ts instanceof TransSpeedRelativeType) {
//            TransSpeedRelativeType tsr = (TransSpeedRelativeType) ts;
//            spd.v = (int) (tsr.getFraction() * maxVelocity);
//            System.out.println("spd = " + spd);
//            mpc.mpMotSetSpeed(0, spd);
            System.err.println("Setting relative speed not supported.");
        }
    }

    private void setRotSpeed(SetRotSpeedType srs) throws IOException {
        MP_SPEED spd = new MP_SPEED();
        RotSpeedType rs = srs.getRotSpeed();
        if (rs instanceof RotSpeedAbsoluteType) {
            RotSpeedAbsoluteType rsa = (RotSpeedAbsoluteType) rs;
            switch (currentAngleUnits) {
                case DEGREE:
                    spd.vr = (int) (rsa.getSetting() * 10.0);
                    break;

                case RADIAN:
                    spd.vr = (int) (Math.toDegrees(rsa.getSetting()) * 10.0);
                    break;
            }
            if (debug) {
                System.out.println("spd = " + spd);
            }
            mpc.mpMotSetSpeed(0, spd);
        } else if (rs instanceof RotSpeedRelativeType) {
//            RotSpeedRelativeType rsr = (RotSpeedRelativeType) rs;
//            spd.vr = (int) (rsr.getFraction() * maxVelocity);
//            mpc.mpMotSetSpeed(1, spd);
            System.err.println("Setting relative speed not supported.");
        }
    }

    private void setEndEffector(SetEndEffectorType see) throws IOException {
        if (see.getSetting() > 0.5) {
            mpc.openGripper();
        } else {
            mpc.closeGripper();
        }
    }

    AngleUnitEnumType currentAngleUnit = AngleUnitEnumType.DEGREE;

    private void setAngleUnits(SetAngleUnitsType sau) {
        currentAngleUnit = sau.getUnitName();
    }

    private void setLengthUnits(SetLengthUnitsType slu) {
        switch (slu.getUnitName()) {
            case MILLIMETER:
                lengthScale = 1.0;
                break;

            case METER:
                lengthScale = 1000.0;
                break;

            case INCH:
                lengthScale = 25.4;
                break;
        }
    }

//    private static class RxRyRz  {
//        public double rx,ry,rz;
//    }
//
//    public static ToRxRyRz(VectorType xAxis, VectorType zAxis) {
//        RxRyRz rxryrz = new RxRyRz();
//        rxryrz.rx = Math.toDegrees(Math.atan2(zAxis.getJ(), zAxis.getK()));
//        rxryrz.ry = Math.toDegrees(Math.atan2(zAxis.getJ(), zAxis.getI()));
//        rxryrz.rz = Math.toDegrees(Math.atan2(xAxis.getJ(), xAxis.getI()));
//        
//    }
    private boolean debug = false;

    /**
     * Get the value of debug
     *
     * @return the value of debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * Set the value of debug
     *
     * @param debug new value of debug
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    private void moveTo(MoveToType cmd) throws IOException, MotoPlusConnection.MotoPlusConnectionException, PmException {
        CoordTarget tgt = new CoordTarget();
        mpc.mpSetServoPower(true);
        boolean power = mpc.mpGetServoPower();
        if (debug) {
            try {
                System.out.println("moveTo(" + CRCLSocket.getUtilSocket().commandToSimpleString(cmd) + ")");
            } catch (ParserConfigurationException | SAXException ex) {
                logException(ex);
            }
            System.out.println("power = " + power);
        }
        if (power == false) {
            throw new MotoPlusConnection.MotoPlusConnectionException("Failed to set servo power on");
        }
        if (debug) {
            System.out.println("Calling mpMotSetCoord(1, MP_COORD_TYPE.MP_ROBOT_TYPE, 0)");
        }
        MotCtrlReturnEnum motSetCoordRet = mpc.mpMotSetCoord(0, MP_COORD_TYPE.MP_ROBOT_TYPE, 0);
        if (debug) {
            System.out.println("motSetCoordRet = " + motSetCoordRet);
        }

        lastSentTargetId++;
        tgt.setId(lastSentTargetId);
        if (cmd.isMoveStraight()) {
            tgt.setIntp(MP_INTP_TYPE.MP_MOVL_TYPE);
        } else {
            tgt.setIntp(MP_INTP_TYPE.MP_MOVL_TYPE);
        }
        tgt.getDst().x = (int) (cmd.getEndPosition().getPoint().getX() * 1000.0 * lengthScale);
        tgt.getDst().y = (int) (cmd.getEndPosition().getPoint().getY() * 1000.0 * lengthScale);
        tgt.getDst().z = (int) (cmd.getEndPosition().getPoint().getZ() * 1000.0 * lengthScale);
        PmRotationMatrix rotMat = CRCLPosemath.toPmRotationMatrix(cmd.getEndPosition());
        if (debug) {
            System.out.println("rotMat = " + rotMat);
        }
//        PmRpy rpy = new PmRpy();
//        int e = Posemath.pmMatRpyConvert(rotMat, rpy);
//        System.out.println("rpy = " + Math.toDegrees(rpy.r) + ", " + Math.toDegrees(rpy.p) + "," + Math.toDegrees(rpy.y));
        PmRotationVector rv = Posemath.toRot(rotMat);
//        double rotMatDeg = Math.toDegrees(rv.s);
        PmRpy rpy = Posemath.toRpy(rotMat);

//        PmEulerZyx zyx = new PmEulerZyx();
//        e = Posemath.pmRpyZyxConvert(rpy, zyx);
//        System.out.println("e = " + e);
//        System.out.println("zyx = " + Math.toDegrees(zyx.x) + ", " + Math.toDegrees(zyx.y) + "," + Math.toDegrees(zyx.z));
//        PmEulerZyz zyz = new PmEulerZyz();
//        e = Posemath.pmRpyZyzConvert(rpy, zyz);
//        System.out.println("e = " + e);
//        System.out.println("zyzp = " + Math.toDegrees(zyz.z) + ", " + Math.toDegrees(zyz.y) + "," + Math.toDegrees(zyz.zp));
        MP_CART_POS_RSP_DATA pos = mpc.getCartPos(0);

        tgt.getAux().x = (int) (cmd.getEndPosition().getPoint().getX() * 1000.0 * lengthScale);
        tgt.getAux().y = (int) (cmd.getEndPosition().getPoint().getY() * 1000.0 * lengthScale);
        tgt.getAux().z = (int) (cmd.getEndPosition().getPoint().getZ() * 1000.0 * lengthScale);
//        PmRpy rpy = CRCLPosemath.toPmRpy(cmd.getEndPosition());
//        MP_CART_POS_RSP_DATA pos = mpc.getCartPos(0);

        double rx = Math.atan2(cmd.getEndPosition().getZAxis().getK(),
                Math.hypot(cmd.getEndPosition().getZAxis().getI(), cmd.getEndPosition().getZAxis().getJ()));
        PmEulerZyx zyx = new PmEulerZyx();
        Posemath.pmMatZyxConvert(rotMat, zyx);
        double degreesRx = Math.toDegrees(zyx.x);
        double degreesRy = Math.toDegrees(zyx.y);
        double degreesRz = Math.toDegrees(zyx.z);

        if (debug) {
            System.out.println("zyx = " + zyx);
        }
        tgt.getDst().rx = (int) (degreesRx * 10000.0);
        tgt.getDst().ry = (int) (degreesRy * 10000.0);
        tgt.getDst().rz = (int) (degreesRz * 10000.0);
        tgt.getAux().rx = (int) tgt.getDst().rx;
        tgt.getAux().ry = (int) tgt.getDst().ry;
        tgt.getAux().rz = (int) tgt.getDst().rz;

//        System.out.println("tgt = " + tgt);
//        System.out.println("Convert to zyx");
//        PmEulerZyx zyx = new PmEulerZyx();
//        Posemath.pmRpyZyxConvert(rpy, zyx);
//        tgt.getDst().rx = (int) (Math.toDegrees(zyx.x) * 10000.0);
//        tgt.getDst().ry = (int) (Math.toDegrees(zyx.y) * 10000.0);
//        tgt.getDst().rz = (int) (Math.toDegrees(zyx.z) * 10000.0);
//        tgt.getAux().rx = (int) (Math.toDegrees(zyx.x) * 10000.0);
//        tgt.getAux().ry = (int) (Math.toDegrees(zyx.y) * 10000.0);
//        tgt.getAux().rz = (int) (Math.toDegrees(zyx.z) * 10000.0);
//        System.out.println("tgt = " + tgt);
//        tgt.getDst().rx = pos.lPos[3];
//        tgt.getDst().ry = pos.lPos[4];
//        tgt.getDst().rz = pos.lPos[5];
//        tgt.getAux().rx = pos.lPos[3];
//        tgt.getAux().ry = pos.lPos[4];
//        tgt.getAux().rz = pos.lPos[5];
        if (Math.abs(tgt.getDst().rx) > 1799990
                && Math.abs(pos.lrx()) > 1799990
                && pos.lrx() * tgt.getDst().rx < 0) {
            tgt.getDst().rx = (int) pos.lrx();
            tgt.getAux().rx = (int) pos.lrx();
        }
        if (Math.abs(tgt.getDst().ry) > 1799990
                && Math.abs(pos.lry()) > 1799990
                && pos.lry() * tgt.getDst().ry < 0) {
            tgt.getDst().ry = (int) pos.lry();
            tgt.getAux().ry = (int) pos.lry();
        }
        if (Math.abs(tgt.getDst().rz) > 1799990
                && Math.abs(pos.lrz()) > 1799990
                && pos.lrz() * tgt.getDst().rz < 0) {
            tgt.getDst().rz = (int) pos.lrz();
            tgt.getAux().rz = (int) pos.lrz();
        }
        if (debug) {
            System.out.println("tgt = " + tgt);
            System.out.println("pos = " + pos);
        }
        MotCtrlReturnEnum targetCoordSendRet
                = mpc.mpMotTargetCoordSend(1, tgt, MotoPlusConnection.NO_WAIT);
        if (debug) {
            System.out.println("targetCoordSendRet = " + targetCoordSendRet);
        }
        MotCtrlReturnEnum motStartRet
                = mpc.mpMotStart(0);
        if (debug) {
            System.out.println("motStartRet = " + motStartRet);
            System.out.println("lastSentTargetId = " + lastSentTargetId);
        }
    }

    private void actuateJoints(ActuateJointsType ajs) throws IOException, MotoPlusConnection.MotoPlusConnectionException {
        JointTarget tgt = new JointTarget();
        mpc.mpSetServoPower(true);
        boolean power = mpc.mpGetServoPower();
        if (debug) {
            try {
                System.out.println("actuateJoints(" + CRCLSocket.getUtilSocket().commandToSimpleString(ajs) + ")");
            } catch (ParserConfigurationException | SAXException ex) {
                logException(ex);
            }
            System.out.println("power = " + power);
        }
        lastSentTargetId++;
        tgt.setId(lastSentTargetId);
        tgt.setIntp(MP_INTP_TYPE.MP_MOVJ_TYPE);

        if (debug) {
            System.out.println("Calling mpMotSetCoord(1, MP_COORD_TYPE.MP_ROBOT_TYPE, 0)");
        }
        MotCtrlReturnEnum motSetCoordRet = mpc.mpMotSetCoord(0, MP_COORD_TYPE.MP_PULSE_TYPE, 0);
        if (debug) {
            System.out.println("motSetCoordRet = " + motSetCoordRet);
        }
        MP_PULSE_POS_RSP_DATA pulseData = mpc.getPulsePos(0);
        if (debug) {
            System.out.println("pulseData = " + pulseData);
        }
        for (int i = 0; i < MP_PULSE_POS_RSP_DATA.MAX_PULSE_AXES; i++) {
            tgt.getDst()[i] = pulseData.lPos[i];
            tgt.getAux()[i] = pulseData.lPos[i];
        }
        for (ActuateJointType aj : ajs.getActuateJoint()) {
            tgt.getDst()[aj.getJointNumber() - 1] = (int) aj.getJointPosition();
            tgt.getAux()[aj.getJointNumber() - 1] = (int) aj.getJointPosition();
            JointDetailsType jd = aj.getJointDetails();
            if (jd instanceof JointSpeedAccelType) {
                JointSpeedAccelType jas = (JointSpeedAccelType) jd;
                MP_SPEED spd = new MP_SPEED();
                spd.vj = (int) (jas.getJointSpeed() * 100.0);
                mpc.mpMotSetSpeed(0, spd);
            }
        }
        if (debug) {
            System.out.println("tgt = " + tgt);
        }
        MotCtrlReturnEnum targetJointSendRet
                = mpc.mpMotTargetJointSend(1, tgt, MotoPlusConnection.NO_WAIT);
        if (debug) {
            System.out.println("targetJointSendRet = " + targetJointSendRet);
        }

        MotCtrlReturnEnum motStartRet
                = mpc.mpMotStart(0);
        if (debug) {
            System.out.println("motStartRet = " + motStartRet);
            System.out.println("lastSentTargetId = " + lastSentTargetId);
        }
    }

    private long dwellEnd = -1;
    private boolean dwelling = false;

    private void initCanon() {
        try {
            initialized = false;
            if (!mpc.isConnected()) {
                mpc.reconnect();
            }
            if (!mpc.isConnected()) {
                setStateDescription(CRCL_ERROR, "Can not connect to robot.");
            }
            stopMotion();
            if (!mpc.mpGetServoPower()) {
                mpc.mpSetServoPower(true);
                if (!mpc.mpGetServoPower()) {
                    MP_MODE_DATA modeData = mpc.mpGetMode();
                    if (modeData.sRemote == 0) {
                        setStateDescription(CRCL_ERROR, "Pendant switch must be set to REMOTE. : current mode = " + modeData.toString());
                    } else {
                        setStateDescription(CRCL_ERROR, "Can not enable servo power.");
                    }
                    mpc.mpSetServoPower(false);
                    return;
                }
            }
            mpc.mpSetServoPower(false);
            initialized = true;
            setStateDescription(CRCL_DONE, "");
        } catch (IOException | MotoPlusConnectionException ex) {
            if (!mpc.isConnected()) {
                setStateDescription(CRCL_ERROR, ex.toString());
            }
            logException(ex);
        }
    }

    @Override
    public void accept(CRCLServerSocketEvent event) {
        try {
            CRCLCommandInstanceType cmdInstance = event.getInstance();
            if (null != cmdInstance) {
                CRCLCommandType cmd = cmdInstance.getCRCLCommand();
                if (cmd instanceof GetStatusType) {
                    event.getSource().writeStatus(getCrclStatus());
                } else {
                    getCommandStatus().setCommandID(cmd.getCommandID());
                    if(getCommandState() != CRCL_ERROR) {
                        getCommandStatus().setCommandState(CRCL_WORKING);
                    }
                    if (cmd instanceof InitCanonType) {
                        initCanon();

                    } else if (!initialized) {
                        if (getCommandState() != CRCL_ERROR
                                || getCommandStatus().getStateDescription().length() < 1) {
                            setStateDescription(CRCL_ERROR, "Command received when not initialized. cmd="+cmd);
                        } else {
                            logErr("Command received when not initialized. cmd="+cmd);
                        }
                    } else if (cmd instanceof StopMotionType) {
                        stopMotion();
                        setStateDescription(CRCL_DONE, "");
                    } else if (cmd instanceof ActuateJointsType) {
                        actuateJoints((ActuateJointsType) cmd);
                        setStateDescription(CRCL_WORKING, "");
                    } else if (cmd instanceof SetTransSpeedType) {
                        setTransSpeed((SetTransSpeedType) cmd);
                        setStateDescription(CRCL_DONE, "");
                    } else if (cmd instanceof SetRotSpeedType) {
                        setRotSpeed((SetRotSpeedType) cmd);
                        setStateDescription(CRCL_DONE, "");
                    } else if (cmd instanceof MoveToType) {
                        moveTo((MoveToType) cmd);
                        setStateDescription(CRCL_WORKING, "");
                    } else if (cmd instanceof DwellType) {
                        dwellEnd = System.currentTimeMillis() + (long) (((DwellType) cmd).getDwellTime() * 1000.0);
                        dwelling = true;
                        setStateDescription(CRCL_WORKING, "");
                    } else if (cmd instanceof ConfigureJointReportsType) {
                        setStateDescription(CRCL_DONE, "");
                    } else if (cmd instanceof SetLengthUnitsType) {
                        setLengthUnits((SetLengthUnitsType) cmd);
                        setStateDescription(CRCL_DONE, "");
                    } else if (cmd instanceof SetAngleUnitsType) {
                        setAngleUnits((SetAngleUnitsType) cmd);
                        setStateDescription(CRCL_DONE, "");
                    } else if (cmd instanceof SetEndEffectorType) {
                        setEndEffector((SetEndEffectorType) cmd);
                        setStateDescription(CRCL_DONE, "");
                    } else if (cmd instanceof EndCanonType) {
                        setStateDescription(CRCL_DONE, "");
                    } else if (cmd instanceof MessageType) {
                        setStateDescription(CRCL_DONE, ((MessageType) cmd).getMessage());
                    } else {
                        setStateDescription(CRCL_ERROR, cmd.getClass().getName() + " not implemented");
                        try {
                            System.err.println("Unrecognized cmd = " + CRCLSocket.getUtilSocket().commandToSimpleString(cmd));
                        } catch (ParserConfigurationException | SAXException ex) {
                            logException(ex);
                        }
                    }
                }
            }
        } catch (CRCLException | IOException | MotoPlusConnection.MotoPlusConnectionException | PmException ex) {
            setStateDescription(CRCL_ERROR, ex.getMessage());
            logException(ex);
        }
    }

    private CommandStateEnumType getCommandState() {
        return getCommandStatus().getCommandState();
    }

    private CommandStatusType getCommandStatus() {
        return crclStatus.getCommandStatus();
    }

    public static final String DEFAULT_MOTOMAN_HOST = "192.168.1.33"; //10.0.0.2";
//    public static final String DEFAULT_MOTOMAN_HOST = "localhost";
    public static final int DEFAULT_MOTOMAN_PORT = 12222;

    public static void main(String[] args) throws Exception {
        String motomanHost = DEFAULT_MOTOMAN_HOST;
        int motomanPort = DEFAULT_MOTOMAN_PORT;
        int crclPort = CRCLSocket.DEFAULT_PORT;
        for (int i = 0; i < args.length - 1; i++) {
            switch (args[i]) {
                case "--motomanhost":
                    motomanHost = args[i + 1];
                    break;

                case "--motomanport":
                    motomanPort = Integer.parseInt(args[i + 1]);
                    break;
                case "--crclport":
                    crclPort = Integer.parseInt(args[i + 1]);
                    break;
            }
        }
        System.out.println("Starting MotomanCrclServer on port " + crclPort + " after connecting to Motoman robot " + motomanHost + " on port " + motomanPort);
        try (MotomanCrclServer motomanCrclServer = new MotomanCrclServer(
                new CRCLServerSocket(crclPort),
                new MotoPlusConnection(new Socket(motomanHost, motomanPort)))) {
            motomanCrclServer.run();
        }
    }

    @Override
    public void run() {
        svrSocket.run();
    }
}
