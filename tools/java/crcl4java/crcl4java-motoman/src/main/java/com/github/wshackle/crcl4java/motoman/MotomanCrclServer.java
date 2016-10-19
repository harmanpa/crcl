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

import com.github.wshackle.crcl4java.motoman.motctrl.CoordTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.JointTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_COORD_TYPE;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_INTP_TYPE;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_SPEED;
import com.github.wshackle.crcl4java.motoman.motctrl.MotCtrlReturnEnum;
import com.github.wshackle.crcl4java.motoman.sys1.MP_CART_POS_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_PULSE_POS_RSP_DATA;
import crcl.base.ActuateJointType;
import crcl.base.ActuateJointsType;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStateEnumType;
import static crcl.base.CommandStateEnumType.CRCL_DONE;
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
import crcl.base.MoveToType;
import crcl.base.PoseStatusType;
import crcl.base.SetLengthUnitsType;
import crcl.base.SetTransSpeedType;
import crcl.base.StopMotionType;
import crcl.base.TransSpeedAbsoluteType;
import crcl.base.TransSpeedRelativeType;
import crcl.base.TransSpeedType;
import crcl.utils.CRCLException;
import crcl.utils.CRCLPosemath;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.pose;
import static crcl.utils.CRCLPosemath.vector;
import crcl.utils.CRCLServerSocket;
import crcl.utils.CRCLServerSocketEvent;
import crcl.utils.CRCLServerSocketEventListener;
import crcl.utils.CRCLSocket;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rcs.posemath.PmCartesian;
import rcs.posemath.PmException;
import rcs.posemath.PmRpy;

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

    @Override
    public void close() throws Exception {
        svrSocket.close();
        mpc.close();
    }

    private final CRCLStatusType crclStatus = new CRCLStatusType();

    {
        crclStatus.setCommandStatus(new CommandStatusType());
        crclStatus.getCommandStatus().setCommandID(BigInteger.ONE);
        crclStatus.getCommandStatus().setStatusID(BigInteger.ONE);
        crclStatus.setPoseStatus(new PoseStatusType());
        crclStatus.getPoseStatus().setPose(pose(point(0, 0, 0), vector(1, 0, 0), vector(0, 0, 1)));
        crclStatus.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_READY);
    }

    private long last_status_update_time = -1;

    private double lengthScale = 1.0;
    private final int lastJointPos[] = new int[MP_PULSE_POS_RSP_DATA.MAX_PULSE_AXES];

    public CRCLStatusType getCrclStatus() {
        long time = System.currentTimeMillis();
        long time_diff = time - last_status_update_time;
//        System.out.println("time = " + time);
//        System.out.println("time_diff = " + time_diff);
        if (time_diff > 50) {
            try {
                MP_CART_POS_RSP_DATA pos = mpc.getCartPos(0);
                PmCartesian cart = new PmCartesian(pos.x() / lengthScale, pos.y() / lengthScale, pos.z() / lengthScale);
                PmRpy rpy = new PmRpy(Math.toRadians(pos.rz()), Math.toRadians(pos.ry()), Math.toRadians(pos.rx()));
                crclStatus.getPoseStatus().setPose(CRCLPosemath.toPoseType(cart, rcs.posemath.Posemath.toRot(rpy), crclStatus.getPoseStatus().getPose()));
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
                        js.setJointNumber(BigInteger.valueOf(i + 1));
                        js.setJointPosition(BigDecimal.valueOf(pulseData.lPos[i]));
                        if (last_status_update_time > 0) {
                            double vj = ((double) diff) / time_diff;
                            js.setJointVelocity(BigDecimal.valueOf(vj));
//                            System.out.println("i = " + i + "vj = " + vj);
                        }
                        jsl.add(js);
                    } else {
                        JointStatusType js = jsl.get(i);
                        js.setJointNumber(BigInteger.valueOf(i + 1));
                        js.setJointPosition(BigDecimal.valueOf(pulseData.lPos[i]));
                        jsl.set(i, js);
                        if (last_status_update_time > 0) {
                            double vj = ((double) diff) / time_diff;
                            js.setJointVelocity(BigDecimal.valueOf(vj));
//                            System.out.println("i = " + i + ", vj = " + vj);
                        }
                    }
                    lastJointPos[i] = pulseData.lPos[i];
                }
                System.arraycopy(pulseData.lPos, 0, lastJointPos, 0, lastJointPos.length);

                if (crclStatus.getCommandStatus().getCommandState() == CRCL_WORKING) {
                    if (lastSentTargetId != lastRecvdTargetId) {
//                        System.out.println("lastSentTargetId = " + lastSentTargetId);
//                        System.out.println("lastRecvdTargetId = " + lastRecvdTargetId);
                        int recvId[] = new int[1];
                        mpc.mpMotTargetReceive(0, lastSentTargetId, recvId, 0, MotoPlusConnection.NO_WAIT);
                        if (recvId[0] != 0) {
                            lastRecvdTargetId = recvId[0];
                            System.out.println("recvId = " + Arrays.toString(recvId));
                        }
                        if (lastSentTargetId == lastRecvdTargetId) {
                            crclStatus.getCommandStatus().setCommandState(CRCL_DONE);
                        }
                    }
                    if(dwelling && System.currentTimeMillis() > dwellEnd) {
                        crclStatus.getCommandStatus().setCommandState(CRCL_DONE);
                        dwelling=false;
                    }
                }
                last_status_update_time = System.currentTimeMillis();
            } catch (IOException | PmException | MotoPlusConnection.MotoPlusConnectionException ex) {
                Logger.getLogger(MotomanCrclServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        crclStatus.getCommandStatus().setStatusID(crclStatus.getCommandStatus().getStatusID().add(BigInteger.ONE));
        return crclStatus;
    }

    private boolean initialized = false;

    private void stopMotion() throws IOException {
        MotCtrlReturnEnum stopRet = mpc.mpMotStop(0);
        System.out.println("stopRet = " + stopRet);
        MotCtrlReturnEnum clearRet = mpc.mpMotTargetClear(0xf, 0);
        System.out.println("clearRet = " + clearRet);
    }

    private int lastSentTargetId = 1;
    private int lastRecvdTargetId = 1;
    private double maxVelocity = 100.0;

    private void setTransSpeed(SetTransSpeedType sts) throws IOException {
        MP_SPEED spd = new MP_SPEED();
        TransSpeedType ts = sts.getTransSpeed();
        if (ts instanceof TransSpeedAbsoluteType) {
            TransSpeedAbsoluteType tsa = (TransSpeedAbsoluteType) ts;
            spd.v = (int) (tsa.getSetting().doubleValue() * 10.0);
            mpc.mpMotSetSpeed(1, spd);
        } else if (ts instanceof TransSpeedRelativeType) {
            TransSpeedRelativeType tsr = (TransSpeedRelativeType) ts;
            spd.v = (int) (tsr.getFraction().doubleValue() * maxVelocity);
            mpc.mpMotSetSpeed(1, spd);
        }
    }
    
    private void setLengthUnits(SetLengthUnitsType slu) {
        switch(slu.getUnitName()) {
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

    private void moveTo(MoveToType cmd) throws IOException, MotoPlusConnection.MotoPlusConnectionException, PmException {
        CoordTarget tgt = new CoordTarget();
        mpc.mpSetServoPower(true);
        boolean power = mpc.mpGetServoPower();
        System.out.println("power = " + power);
        System.out.println("Calling mpMotSetCoord(1, MP_COORD_TYPE.MP_ROBOT_TYPE, 0)");
        MotCtrlReturnEnum motSetCoordRet = mpc.mpMotSetCoord(0, MP_COORD_TYPE.MP_ROBOT_TYPE, 0);
        System.out.println("motSetCoordRet = " + motSetCoordRet);

        lastSentTargetId++;
        tgt.setId(lastSentTargetId);
        tgt.setIntp(MP_INTP_TYPE.MP_MOVL_TYPE);
        tgt.getDst().x = (int) (cmd.getEndPosition().getPoint().getX().doubleValue() * 1000.0 * lengthScale);
        tgt.getDst().y = (int) (cmd.getEndPosition().getPoint().getY().doubleValue() * 1000.0 * lengthScale);
        tgt.getDst().z = (int) (cmd.getEndPosition().getPoint().getZ().doubleValue() * 1000.0 * lengthScale);
        PmRpy rpy = CRCLPosemath.toPmRpy(cmd.getEndPosition());

        MP_CART_POS_RSP_DATA pos = mpc.getCartPos(0);

        tgt.getAux().x = (int) (cmd.getEndPosition().getPoint().getX().doubleValue() * 1000.0 * lengthScale);
        tgt.getAux().y = (int) (cmd.getEndPosition().getPoint().getY().doubleValue() * 1000.0 * lengthScale);
        tgt.getAux().z = (int) (cmd.getEndPosition().getPoint().getZ().doubleValue() * 1000.0 * lengthScale);
//        PmRpy rpy = CRCLPosemath.toPmRpy(cmd.getEndPosition());
//        MP_CART_POS_RSP_DATA pos = mpc.getCartPos(0);

        tgt.getDst().rx = (int) (Math.toDegrees(rpy.y) * 10000.0);
        tgt.getDst().ry = (int) (Math.toDegrees(rpy.p) * 10000.0);
        tgt.getDst().rz = (int) (Math.toDegrees(rpy.r) * 10000.0);
        tgt.getAux().rx = (int) (Math.toDegrees(rpy.y) * 10000.0);
        tgt.getAux().ry = (int) (Math.toDegrees(rpy.p) * 10000.0);
        tgt.getAux().rz = (int) (Math.toDegrees(rpy.r) * 10000.0);

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
        System.out.println("tgt = " + tgt);
        System.out.println("pos = " + pos);
        MotCtrlReturnEnum targetCoordSendRet
                = mpc.mpMotTargetCoordSend(1, tgt, MotoPlusConnection.NO_WAIT);
        System.out.println("targetCoordSendRet = " + targetCoordSendRet);

        MotCtrlReturnEnum motStartRet
                = mpc.mpMotStart(0);
        System.out.println("motStartRet = " + motStartRet);
        System.out.println("lastSentTargetId = " + lastSentTargetId);
    }

    private void actuateJoints(ActuateJointsType ajs) throws IOException, MotoPlusConnection.MotoPlusConnectionException {
        JointTarget tgt = new JointTarget();
        mpc.mpSetServoPower(true);
        boolean power = mpc.mpGetServoPower();
        System.out.println("power = " + power);
        lastSentTargetId++;
        tgt.setId(lastSentTargetId);
        tgt.setIntp(MP_INTP_TYPE.MP_MOVJ_TYPE);

        System.out.println("Calling mpMotSetCoord(1, MP_COORD_TYPE.MP_ROBOT_TYPE, 0)");
        MotCtrlReturnEnum motSetCoordRet = mpc.mpMotSetCoord(0, MP_COORD_TYPE.MP_PULSE_TYPE, 0);
        System.out.println("motSetCoordRet = " + motSetCoordRet);

        MP_PULSE_POS_RSP_DATA pulseData = mpc.getPulsePos(0);
        System.out.println("pulseData = " + pulseData);
        for (int i = 0; i < MP_PULSE_POS_RSP_DATA.MAX_PULSE_AXES; i++) {
            tgt.getDst()[i] = pulseData.lPos[i];
            tgt.getAux()[i] = pulseData.lPos[i];
        }
        for (ActuateJointType aj : ajs.getActuateJoint()) {
            tgt.getDst()[aj.getJointNumber().intValue() - 1] = aj.getJointPosition().intValue();
            tgt.getAux()[aj.getJointNumber().intValue() - 1] = aj.getJointPosition().intValue();
            JointDetailsType jd = aj.getJointDetails();
            if (jd instanceof JointSpeedAccelType) {
                JointSpeedAccelType jas = (JointSpeedAccelType) jd;
                MP_SPEED spd = new MP_SPEED();
                spd.vj = (int) (jas.getJointSpeed().doubleValue() * 100.0);
                mpc.mpMotSetSpeed(1, spd);
            }
        }
        System.out.println("tgt = " + tgt);
        MotCtrlReturnEnum targetJointSendRet
                = mpc.mpMotTargetJointSend(1, tgt, MotoPlusConnection.NO_WAIT);
        System.out.println("targetJointSendRet = " + targetJointSendRet);

        MotCtrlReturnEnum motStartRet
                = mpc.mpMotStart(0);
        System.out.println("motStartRet = " + motStartRet);
        System.out.println("lastSentTargetId = " + lastSentTargetId);
    }

    private long dwellEnd = -1;
    private boolean dwelling = false;
    
    @Override
    public void accept(CRCLServerSocketEvent event) {
        try {
            CRCLCommandInstanceType cmdInstance = event.getInstance();
            if (null != cmdInstance) {
                CRCLCommandType cmd = cmdInstance.getCRCLCommand();
                if (cmd instanceof GetStatusType) {
                    event.getSource().writeStatus(getCrclStatus());
                } else {
                    crclStatus.getCommandStatus().setCommandID(cmd.getCommandID());
                    crclStatus.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_WORKING);
                    if (cmd instanceof InitCanonType) {
                        initialized = true;
                        crclStatus.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_DONE);
                    } else if (!initialized) {
                        crclStatus.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_ERROR);
                        crclStatus.getCommandStatus().setStateDescription("Command recieved when not initialized.");
                        System.err.println("Command recieved when not initialized.");
                    } else if (cmd instanceof StopMotionType) {
                        stopMotion();
                        crclStatus.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_DONE);
                        crclStatus.getCommandStatus().setStateDescription("");
                    } else if (cmd instanceof ActuateJointsType) {
                        actuateJoints((ActuateJointsType) cmd);
                        crclStatus.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_WORKING);
                        crclStatus.getCommandStatus().setStateDescription("");
                    } else if (cmd instanceof SetTransSpeedType) {
                        setTransSpeed((SetTransSpeedType) cmd);
                        crclStatus.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_DONE);
                        crclStatus.getCommandStatus().setStateDescription("");
                    } else if (cmd instanceof MoveToType) {
                        moveTo((MoveToType) cmd);
                        crclStatus.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_WORKING);
                        crclStatus.getCommandStatus().setStateDescription("");
                    }  else if (cmd instanceof DwellType) {
                        dwellEnd = System.currentTimeMillis() + (long) (((DwellType)cmd).getDwellTime().doubleValue()*1000.0);
                        dwelling=true;
                        crclStatus.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_WORKING);
                        crclStatus.getCommandStatus().setStateDescription("");
                    }  else if (cmd instanceof ConfigureJointReportsType) {
                        crclStatus.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_DONE);
                        crclStatus.getCommandStatus().setStateDescription("");
                    }  else if (cmd instanceof SetLengthUnitsType) {
                        setLengthUnits((SetLengthUnitsType)cmd);
                        crclStatus.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_DONE);
                        crclStatus.getCommandStatus().setStateDescription("");
                    }  else if (cmd instanceof EndCanonType) {
                        crclStatus.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_DONE);
                        crclStatus.getCommandStatus().setStateDescription("");
                    }  else  {
                        crclStatus.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_ERROR);
                        crclStatus.getCommandStatus().setStateDescription(cmd.getClass().getName()+ " not implemented");
                        System.out.println("Unrecognized cmd = " + cmd);
                    }
                }
            }
        } catch (CRCLException | IOException | MotoPlusConnection.MotoPlusConnectionException | PmException ex) {
            Logger.getLogger(MotomanCrclServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static final String DEFAULT_MOTOMAN_HOST = "10.0.0.2";
//    public static final String DEFAULT_MOTOMAN_HOST = "localhost";
    public static final int DEFAULT_MOTOMAN_PORT = 11000;

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
                    motomanPort = Integer.valueOf(args[i + 1]);
                    break;
                case "--crclport":
                    crclPort = Integer.valueOf(args[i + 1]);
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
