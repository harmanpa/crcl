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
import com.github.wshackle.crcl4java.motoman.MotoPlusConnection.Returner;
import com.github.wshackle.crcl4java.motoman.MotoPlusConnection.Starter;
import com.github.wshackle.crcl4java.motoman.motctrl.CoordTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.JointTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_COORD_TYPE;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_INTP_TYPE;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_SPEED;
import com.github.wshackle.crcl4java.motoman.motctrl.MotCtrlReturnEnum;
import com.github.wshackle.crcl4java.motoman.sys1.MP_ALARM_CODE_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_ALARM_STATUS_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_CART_POS_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_MODE_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_PULSE_POS_RSP_DATA;
import crcl.base.ActuateJointType;
import crcl.base.ActuateJointsType;
import crcl.base.AngleUnitEnumType;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLStatusType;
import crcl.base.CloseToolChangerType;
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
import crcl.base.MessageType;
import crcl.base.MoveToType;
import crcl.base.OpenToolChangerType;
import crcl.base.PoseStatusType;
import crcl.base.RotSpeedAbsoluteType;
import crcl.base.RotSpeedRelativeType;
import crcl.base.RotSpeedType;
import crcl.base.SetEndEffectorType;
import crcl.base.SetRotSpeedType;
import crcl.base.SetTransSpeedType;
import crcl.base.StopMotionType;
import crcl.base.TransSpeedAbsoluteType;
import crcl.base.TransSpeedRelativeType;
import crcl.base.TransSpeedType;
import crcl.utils.CRCLPosemath;
import static crcl.utils.CRCLPosemath.pose;

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
import crcl.utils.XFuture;
import crcl.utils.server.CRCLServerClientState;
import crcl.utils.server.CRCLServerSocket;
import crcl.utils.server.CRCLServerSocketEvent;
import crcl.utils.server.CRCLServerSocketEventListener;
import crcl.utils.server.CRCLServerSocketStateGenerator;
import crcl.utils.server.UnitsTypeSet;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import rcs.posemath.PmEulerZyx;
import rcs.posemath.PmRotationMatrix;
import rcs.posemath.PmRotationVector;
import rcs.posemath.Posemath;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class MotomanCRCLServer implements AutoCloseable {

    public static class MotomanClientState extends CRCLServerClientState {

        public MotomanClientState(CRCLSocket cs) {
            super(cs);
        }
        int i;
    }

    public static final CRCLServerSocketStateGenerator<MotomanClientState> MOTOMAN_STATE_GENERATOR
            = MotomanClientState::new;

    private final CRCLServerSocketEventListener<MotomanClientState> crclSocketEventListener
            = this::handleCrclServerSocketEvent;

    private final CRCLServerSocket<MotomanClientState> crclServerSocket;
    private final MotoPlusConnection mpc;
    private final MotoPlusConnection triggerStopMpc;

    public MotomanCRCLServer(CRCLServerSocket<MotomanClientState> svrSocket, MotoPlusConnection mpConnection) throws IOException {
        this.crclServerSocket = svrSocket;
        this.mpc = mpConnection;
        triggerStopMpc = new MotoPlusConnection(new Socket(mpc.getHost(), mpc.getPort()));
        this.crclServerSocket.addListener(crclSocketEventListener);
        this.crclServerSocket.setThreadNamePrefix("MotomanCrclServer");
    }

    public MotoPlusConnection getMpc() {
        return mpc;
    }

    public boolean mpcConnected() {
        return mpc.isConnected();
    }

    private void runUpdateStatus() {
        try {
            getCrclStatusFuture();
        } catch (Exception ex) {
            logException(ex);
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        }
    }

    public Future<?> start() {
        crclServerSocket.setServerSideStatus(crclStatus);
        crclServerSocket.setUpdateStatusSupplier(this::getCrclStatusFuture);
        crclServerSocket.setAutomaticallySendServerSideStatus(true);
        crclServerSocket.setAutomaticallyConvertUnits(true);
        crclServerSocket.setServerUnits(new UnitsTypeSet());
        return crclServerSocket.start();
    }

    @Override
    public void close() throws Exception {
        crclServerSocket.close();
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

    private final int lastJointPos[] = new int[MP_PULSE_POS_RSP_DATA.MAX_PULSE_AXES];

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

    private final AtomicInteger recheckCoordTargetFailConsecutive = new AtomicInteger();

    private void recheckCoordTarget(MP_CART_POS_RSP_DATA pos) throws IOException, MotoPlusConnectionException, PmException {
        boolean resendNeeded = false;
        int diffx = pos.lx() - lastMoveToCoordTarget.getDst().x;
        if (Math.abs(diffx) > 100) {
            System.err.println("MotomanCRCLServer.recheckCoordTarget: diffx = " + diffx + ",pos=" + pos + ",lastMoveToCoordTarget=" + lastMoveToCoordTarget);
            resendNeeded = true;
        }
        int diffy = pos.ly() - lastMoveToCoordTarget.getDst().y;
        if (Math.abs(diffy) > 100) {
            System.out.println("MotomanCRCLServer.recheckCoordTarget: diffy = " + diffy + ",pos=" + pos + ",lastMoveToCoordTarget=" + lastMoveToCoordTarget);
            resendNeeded = true;
        }
        int diffz = pos.lz() - lastMoveToCoordTarget.getDst().z;
        if (Math.abs(diffz) > 100) {
            System.out.println("MotomanCRCLServer.recheckCoordTarget: diffz = " + diffz + ",pos=" + pos + ",lastMoveToCoordTarget=" + lastMoveToCoordTarget);
            resendNeeded = true;
        }
        if (resendNeeded) {
            int failCount = recheckCoordTargetFailConsecutive.incrementAndGet();
            if (failCount > 10) {
                getCommandStatus().setCommandState(CRCL_ERROR);
                getCommandStatus().setStateDescription("MotomanCRCLServer.recheckCoordTarget: diffx=" + diffx + ",diffy=" + diffy + ",diffz=" + diffz + ",failCount=" + failCount + ",pos=" + pos + ",lastMoveToCoordTarget=" + lastMoveToCoordTarget);
            } else {
                getCommandStatus().setCommandState(CRCL_WORKING);
                moveTo((MoveToType) lastCommand);
            }
        } else {
            getCommandStatus().setCommandState(CRCL_DONE);
            recheckCoordTargetFailConsecutive.set(0);
        }
    }

    private void recheckJoints(MP_PULSE_POS_RSP_DATA curPulseData, int recvId) throws IOException, MotoPlusConnectionException {
        System.out.println("recvId = " + recvId);
        System.out.println("actuateJointsLastJointTarget.getId() = " + actuateJointsLastJointTarget.getId());
        System.out.printf("%10s\t%10s\t%10s\t%10s\t%10s\t%10s\n",
                "Axis", "Current", "Destination", "Start", "DiffDest", "DiffStart");
        boolean resendNeeded = false;
        for (int i = 0; i < MP_PULSE_POS_RSP_DATA.MAX_PULSE_AXES; i++) {
            int current = curPulseData.lPos[i];
            int dst = actuateJointsLastJointTarget.getDst()[i];
            int start = actuateJointsStartPulseData.lPos[i];
            int diffdest = current - dst;
            int diffstart = current - start;
            System.out.printf("%10d\t%10d\t%10d\t%10d\t%10d\t%10d\n",
                    i, current, dst, start, diffdest, diffstart);
            if (Math.abs(diffdest) > 100) {
                resendNeeded = true;
            }
        }
        if (resendNeeded) {
            getCommandStatus().setCommandState(CRCL_WORKING);
            actuateJoints((ActuateJointsType) lastCommand);
        } else {
            getCommandStatus().setCommandState(CRCL_DONE);
        }
    }

    private AtomicInteger updatesSinceCommand = new AtomicInteger();

    private volatile MpcStatus lastMpcStatus = null;

    public XFuture<CRCLStatusType> getCrclStatusFuture() {
        long time = System.currentTimeMillis();
        long status_time_diff = time - last_status_update_time;
        long cmd_time_diff = time - last_command_time;
        if (status_time_diff > 50) {
            updatesSinceCommand.incrementAndGet();
            try {
                final CommandStatusType commandStatusLocal = getCommandStatus();
                if (null == mpc || !mpc.isConnected()) {
                    setStateDescription(commandStatusLocal, CRCL_ERROR, "Not connected to Robot.");
                    commandStatusLocal.setStatusID(commandStatusLocal.getStatusID() + 1);
                    return XFuture.completedFuture(crclStatus);
                }
                CRCLStatusType crclLocalStatus = this.crclStatus;
                final CommandStateEnumType localOrigCommandState = getCommandState();
                MotoPlusConnection mpcLocal = this.mpc;
                final boolean lastErrorWasWrongMode = mpcLocal.isLastErrorWasWrongMode();
                int lastSentId = mpcLocal.getLastSentTargetId().get();

                MpcStatus mpcStatus = mpcLocal.readMpcStatus(localOrigCommandState, lastSentId,(int) crclLocalStatus.getCommandStatus().getStatusID());

                this.lastMpcStatus = mpcStatus;
                if (localOrigCommandState != CRCL_ERROR) {
                    MP_ALARM_STATUS_DATA alarmStatusData1 = mpcStatus.getAlarmStatusData();
                    if (alarmStatusData1.sIsAlarm != 0) {
                        MP_ALARM_CODE_DATA alarmCodeData1 = mpcStatus.getAlarmCodeData();
                        setStateDescription(commandStatusLocal, CRCL_ERROR, "alarmCodeData = " + alarmCodeData1);
                    }

                }
                final MP_PULSE_POS_RSP_DATA pulseData = mpcStatus.getPulseData();
                final MP_CART_POS_RSP_DATA pos = mpcStatus.getPos();
                if (null != pulseData) {
                    System.arraycopy(pulseData.lPos, 0, lastJointPos, 0, lastJointPos.length);
                    if (localOrigCommandState == CRCL_WORKING) {
                        if (lastSentId != mpcLocal.getLastRecvdTargetId()) {
//                        System.out.println("lastSentTargetId = " + lastSentTargetId);
//                        System.out.println("mpcLocal.getLastRecvdTargetId () = " + mpcLocal.getLastRecvdTargetId ());
                            MotCtrlReturnEnum motTargetReceiveRet = mpcStatus.getMotTargetReceiveRet();
                            int recvId = mpcStatus.getRecvId();
                            if (motTargetReceiveRet == MotCtrlReturnEnum.SUCCESS) {
                                if (recvId != 0) {
                                    if (debug) {
                                        System.out.println("recvId = " + recvId);
                                    }
                                }
                                if (lastSentId == mpcLocal.getLastRecvdTargetId()) {
                                    if (lastCommand instanceof ActuateJointsType) {
                                        recheckJoints(pulseData, recvId);
                                    } else {
                                        recheckCoordTarget(pos);
                                    }
                                }
                            } else {
                                System.out.println("recvId = " + recvId);
                                System.err.println("cmd_time_diff=" + cmd_time_diff);
                                System.err.println("updatesSinceCommand = " + updatesSinceCommand.get());
                                System.err.println("lastCommand=" + lastCommand);
                            }
                        }
                        if (dwelling && System.currentTimeMillis() > dwellEnd) {
                            commandStatusLocal.setCommandState(CRCL_DONE);
                            dwelling = false;
                        }
                    }
                    if (null == crclLocalStatus.getJointStatuses()) {
                        crclLocalStatus.setJointStatuses(new JointStatusesType());
                    }
                    List<JointStatusType> jsl = crclLocalStatus.getJointStatuses().getJointStatus();
                    for (int i = 0; i < MP_PULSE_POS_RSP_DATA.MAX_PULSE_AXES; i++) {
                        int diff = pulseData.lPos[i] - lastJointPos[i];
                        if (i >= jsl.size()) {
                            JointStatusType js = new JointStatusType();
                            js.setJointNumber(i + 1);
                            js.setJointPosition((double) pulseData.lPos[i]);
                            if (last_status_update_time > 0) {
                                double vj = ((double) diff) / status_time_diff;
                                js.setJointVelocity(vj);
//                            System.out.println("i = " + i + "vj = " + vj);
                            }
                            jsl.add(js);
                        } else {
                            JointStatusType js = jsl.get(i);
                            js.setJointNumber(i + 1);
                            js.setJointPosition((double) pulseData.lPos[i]);

                            if (last_status_update_time > 0) {
                                double vj = ((double) diff) / status_time_diff;
                                js.setJointVelocity(vj);
//                            System.out.println("i = " + i + ", vj = " + vj);
                            }
                            jsl.set(i, js);
                        }
                        lastJointPos[i] = pulseData.lPos[i];
                    }
                }

                if (null != mpcStatus.getModeData()) {
                    boolean wrongMode = (mpcStatus.getModeData().sRemote == 0);
                    if (wrongMode) {
                        setStateDescription(commandStatusLocal, CRCL_ERROR, "Pendant switch must be set to REMOTE. : current mode = " + mpcStatus.getModeData().toString());
                    } else if (lastErrorWasWrongMode) {
                        commandStatusLocal.setStateDescription("");
                    }
                }
                if (null != pos) {
                    PmCartesian cart = new PmCartesian(pos.x(), pos.y(), pos.z());
                    PmEulerZyx zyx = new PmEulerZyx(Math.toRadians(pos.rz()), Math.toRadians(pos.ry()), Math.toRadians(pos.rx()));
                    PmRotationMatrix mat = Posemath.toMat(zyx);
                    crclLocalStatus.getPoseStatus().setPose(CRCLPosemath.toPoseType(cart, mat, crclLocalStatus.getPoseStatus().getPose()));
                }
                last_status_update_time = System.currentTimeMillis();
            } catch (IOException | PmException | MotoPlusConnection.MotoPlusConnectionException ex) {
                logException(ex);
                try {
                    mpc.close();
                } catch (Exception ex1) {
                    logException(ex1);
                }
                setStateDescription(getCommandStatus(), CRCL_ERROR, ex.getMessage());
            }
        }
        incCommandStatusId();
        return XFuture.completedFuture(crclStatus);
    }

    private void incCommandStatusId() {
        final CommandStatusType localCommandStatus = getCommandStatus();
        localCommandStatus.setStatusID(localCommandStatus.getStatusID() + 1);
    }

    private CommandStateEnumType lastState = CRCL_WORKING;
    private String lastDescription = "";

    private void setStateDescription(CommandStatusType localCommandStatus, CommandStateEnumType state, String description) {
        localCommandStatus.setCommandState(state);
        localCommandStatus.setStateDescription(description);
        if (lastState != state || !Objects.equals(lastDescription, description)) {
            log(state + ":" + description);
            lastState = state;
            lastDescription = description;
        }
    }

    private void setStateDescription(CommandStateEnumType state, String description) {
        setStateDescription(getCommandStatus(), state, description);
    }

    private void logException(final java.lang.Exception ex) {
        Logger.getLogger(MotomanCRCLServer.class.getName()).log(Level.SEVERE, "MotomanCRCLServer.logException", ex);
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

    private void triggeredStopMotion() throws IOException {
        MotCtrlReturnEnum stopRet = triggerStopMpc.mpMotStop(0);
        if (debug) {
            System.out.println("stopRet = " + stopRet);
        }
        MotCtrlReturnEnum clearRet = triggerStopMpc.mpMotTargetClear(0xf, 0);
        if (debug) {
            System.out.println("clearRet = " + clearRet);
        }
    }

    private double maxVelocity = 100.0;

//    private LengthUnitEnumType currentLengthUnits = LengthUnitEnumType.MILLIMETER;
//    private AngleUnitEnumType currentAngleUnits = AngleUnitEnumType.DEGREE;
    private final double MM_TO_INCH = 0.0393701;

    private void setTransSpeed(SetTransSpeedType sts, MotomanClientState clientState) throws IOException {
        MP_SPEED spd = new MP_SPEED();
        TransSpeedType ts = sts.getTransSpeed();
        if (ts instanceof TransSpeedAbsoluteType) {
            TransSpeedAbsoluteType tsa = (TransSpeedAbsoluteType) ts;
//            spd.v = (int) clientState.filterSettings.convertLengthToServer(tsa.getSetting() * 10.0);
            spd.v = (int) (tsa.getSetting() * 10.0);
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

    private void setRotSpeed(SetRotSpeedType srs, MotomanClientState clientState) throws IOException {
        MP_SPEED spd = new MP_SPEED();
        RotSpeedType rs = srs.getRotSpeed();
        if (rs instanceof RotSpeedAbsoluteType) {
            RotSpeedAbsoluteType rsa = (RotSpeedAbsoluteType) rs;
            final AngleUnitEnumType angleUnit = clientState.filterSettings.getClientUserSet().getAngleUnit();
            switch (angleUnit) {
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

    private void setEndEffector(SetEndEffectorType see) throws Exception {
        if (see.getSetting() > 0.5) {
            mpc.openGripper();
        } else {
            mpc.closeGripper();
        }
    }

    private void openToolChanger(OpenToolChangerType see) throws Exception {
        mpc.openToolChanger();
    }

    private void closeToolChanger(CloseToolChangerType see) throws Exception {
        mpc.closeToolChanger();
    }

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

    private volatile CoordTarget lastMoveToCoordTarget = null;

    private void moveTo(MoveToType cmd) throws IOException, MotoPlusConnection.MotoPlusConnectionException, PmException {
        boolean isStraight = cmd.isMoveStraight();
        final int newTargetId = mpc.getLastSentTargetId().incrementAndGet();
        CoordTarget tgt = new CoordTarget(isStraight, newTargetId);
        mpc.mpSetServoPower(true);
        boolean power = mpc.mpGetServoPower();
        if (debug) {
            try {
                System.out.println("moveTo(" + CRCLSocket.getUtilSocket().commandToSimpleString(cmd) + ")");
            } catch (Exception ex) {
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
        tgt.setId(newTargetId);
        if (isStraight) {
            tgt.setIntp(MP_INTP_TYPE.MP_MOVL_TYPE);
        } else {
            tgt.setIntp(MP_INTP_TYPE.MP_MOVL_TYPE);
        }
        tgt.getDst().x = (int) (cmd.getEndPosition().getPoint().getX() * 1000.0);
        tgt.getDst().y = (int) (cmd.getEndPosition().getPoint().getY() * 1000.0);
        tgt.getDst().z = (int) (cmd.getEndPosition().getPoint().getZ() * 1000.0);
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

        tgt.getAux().x = (int) (cmd.getEndPosition().getPoint().getX() * 1000.0);
        tgt.getAux().y = (int) (cmd.getEndPosition().getPoint().getY() * 1000.0);
        tgt.getAux().z = (int) (cmd.getEndPosition().getPoint().getZ() * 1000.0);
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
            System.out.println("lastSentTargetId = " + mpc.getLastSentTargetId().get());
        }
        lastMoveToCoordTarget = tgt;
    }

    private volatile MP_PULSE_POS_RSP_DATA actuateJointsStartPulseData = null;
    private volatile JointTarget actuateJointsLastJointTarget = null;

    private void actuateJoints(ActuateJointsType ajs) throws IOException, MotoPlusConnection.MotoPlusConnectionException {
        boolean debug = true;

        final int newTargetId = mpc.getLastSentTargetId().incrementAndGet();
        JointTarget tgt = new JointTarget(newTargetId);
        mpc.mpSetServoPower(true);
        boolean power = mpc.mpGetServoPower();
        if (debug) {
            try {
                System.out.println("actuateJoints(" + CRCLSocket.getUtilSocket().commandToSimpleString(ajs) + ")");
            } catch (Exception ex) {
                logException(ex);
            }
            System.out.println("power = " + power);
        }
        tgt.setId(newTargetId);
        tgt.setIntp(MP_INTP_TYPE.MP_MOVJ_TYPE);

        if (debug) {
            System.out.println("Calling mpMotSetCoord(1, MP_COORD_TYPE.MP_ROBOT_TYPE, 0)");
        }
        MotCtrlReturnEnum motSetCoordRet = mpc.mpMotSetCoord(0, MP_COORD_TYPE.MP_PULSE_TYPE, 0);
        if (debug) {
            System.out.println("motSetCoordRet = " + motSetCoordRet);
        }
        MP_PULSE_POS_RSP_DATA pulseData = mpc.getPulsePos(0);
        actuateJointsStartPulseData = pulseData;
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
        actuateJointsLastJointTarget = tgt;
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
            System.out.println("lastSentTargetId = " + mpc.getLastSentTargetId().get());
        }
    }

    private long dwellEnd = -1;
    private boolean dwelling = false;
    private long initCanonTime = 0;

    private void initCanon() {
        try {
            initialized = false;
            if (!mpc.isConnected()) {
                initCanonTime = 0;
                mpc.reconnect();
                if (!mpc.isConnected()) {
                    setStateDescription(CRCL_ERROR, "Can not connect to robot.");
                }
            }
            final long currentTimeMillis = System.currentTimeMillis();
            final long timeDiff = currentTimeMillis - initCanonTime;

            if (timeDiff < 10000) {
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
                initCanonTime = System.currentTimeMillis();
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

    private volatile CRCLCommandType lastCommand = null;
    private volatile long last_command_time = -1;

    public void handleCrclServerSocketEvent(CRCLServerSocketEvent<MotomanClientState> event) {
        try {
            switch (event.getEventType()) {
                case CRCL_COMMAND_RECIEVED:
                    CRCLCommandInstanceType cmdInstance = event.getInstance();
                    if (null != cmdInstance) {
                        CRCLCommandType cmd = cmdInstance.getCRCLCommand();
                        handleNewCommandFromServerSocket(cmd, event);
                    }
                    break;

                case GUARD_LIMIT_REACHED:
                    triggeredStopMotion();
                    if (getCommandStatus().getCommandState() == CRCL_WORKING) {
                        setStateDescription(CRCL_DONE, "");
                    }
                    crclServerSocket.comleteGuardTrigger();
                    break;
            }

        } catch (Exception ex) {
            setStateDescription(CRCL_ERROR, ex.getMessage());
            logException(ex);
        }
    }

    private void handleNewCommandFromServerSocket(CRCLCommandType cmd, CRCLServerSocketEvent<MotomanClientState> event) throws Exception {
        if (cmd instanceof GetStatusType) {
            getCrclStatusFuture().thenAccept((CRCLStatusType suppliedStatus) -> {
                try {
                    event.getSource().writeStatus(suppliedStatus);
                } catch (Exception ex) {
                    logException(ex);
                    if (ex instanceof RuntimeException) {
                        throw (RuntimeException) ex;
                    } else {
                        throw new RuntimeException(ex);
                    }
                }
            });
//            event.getSource().writeStatus(getCrclStatusFuture());
        } else {
            lastCommand = cmd;
            updatesSinceCommand.set(0);
            last_command_time = System.currentTimeMillis();
            getCommandStatus().setCommandID(cmd.getCommandID());
            if (getCommandState() != CRCL_ERROR) {
                getCommandStatus().setCommandState(CRCL_WORKING);
            }
            if (cmd instanceof InitCanonType) {
                initCanon();

            } else if (!initialized) {
                if (getCommandState() != CRCL_ERROR
                        || getCommandStatus().getStateDescription().length() < 1) {
                    setStateDescription(CRCL_ERROR, "Command received when not initialized. cmd=" + cmd);
                } else {
                    logErr("Command received when not initialized. cmd=" + cmd);
                }
            } else if (cmd instanceof StopMotionType) {
                stopMotion();
                setStateDescription(CRCL_DONE, "");
            } else if (cmd instanceof ActuateJointsType) {
                actuateJoints((ActuateJointsType) cmd);
                setStateDescription(CRCL_WORKING, "");
            } else if (cmd instanceof SetTransSpeedType) {
                setTransSpeed((SetTransSpeedType) cmd, event.getState());
                setStateDescription(CRCL_DONE, "");
            } else if (cmd instanceof SetRotSpeedType) {
                setRotSpeed((SetRotSpeedType) cmd, event.getState());
                setStateDescription(CRCL_DONE, "");
            } else if (cmd instanceof MoveToType) {
                setStateDescription(CRCL_WORKING, "");
                moveTo((MoveToType) cmd);
                setStateDescription(CRCL_WORKING, "");
            } else if (cmd instanceof DwellType) {
                dwellEnd = System.currentTimeMillis() + (long) (((DwellType) cmd).getDwellTime() * 1000.0);
                dwelling = true;
                setStateDescription(CRCL_WORKING, "");
            } else if (cmd instanceof ConfigureJointReportsType) {
                setStateDescription(CRCL_DONE, "");
//            } else if (cmd instanceof SetLengthUnitsType) {
//                setLengthUnits((SetLengthUnitsType) cmd);
//                setStateDescription(CRCL_DONE, "");
//            } else if (cmd instanceof SetAngleUnitsType) {
////                        setAngleUnits((SetAngleUnitsType) cmd);
//                setStateDescription(CRCL_DONE, "");
            } else if (cmd instanceof SetEndEffectorType) {
                setEndEffector((SetEndEffectorType) cmd);
                setStateDescription(CRCL_DONE, "");
            } else if (cmd instanceof OpenToolChangerType) {
                openToolChanger((OpenToolChangerType) cmd);
                setStateDescription(CRCL_DONE, "");
            } else if (cmd instanceof CloseToolChangerType) {
                closeToolChanger((CloseToolChangerType) cmd);
                setStateDescription(CRCL_DONE, "");
            } else if (cmd instanceof EndCanonType) {
                setStateDescription(CRCL_DONE, "");
            } else if (cmd instanceof MessageType) {
                setStateDescription(CRCL_DONE, ((MessageType) cmd).getMessage());
            } else {
                setStateDescription(CRCL_ERROR, cmd.getClass().getName() + " not implemented");
                try {
                    System.err.println("Unrecognized cmd = " + CRCLSocket.getUtilSocket().commandToSimpleString(cmd));
                } catch (Exception ex) {
                    logException(ex);
                }
            }
            last_command_time = System.currentTimeMillis();
        }
    }

    public CommandStateEnumType getCommandState() {
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
        try (MotomanCRCLServer motomanCrclServer = new MotomanCRCLServer(
                new CRCLServerSocket<>(crclPort, MOTOMAN_STATE_GENERATOR),
                new MotoPlusConnection(new Socket(motomanHost, motomanPort)))) {
            motomanCrclServer.crclServerSocket.runServer();
        }
    }

}
