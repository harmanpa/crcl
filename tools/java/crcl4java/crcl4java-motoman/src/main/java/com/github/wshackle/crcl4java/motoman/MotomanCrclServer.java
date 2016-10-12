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

import com.github.wshackle.crcl4java.motoman.motctrl.JointTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_INTP_TYPE;
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
import crcl.base.GetStatusType;
import crcl.base.InitCanonType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.PoseStatusType;
import crcl.base.StopMotionType;
import crcl.utils.CRCLException;
import crcl.utils.CRCLPosemath;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.pose;
import static crcl.utils.CRCLPosemath.vector;
import crcl.utils.CRCLServerSocket;
import crcl.utils.CRCLServerSocketEvent;
import crcl.utils.CRCLServerSocketEventListener;
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
    private final MotoPlusConnection mpConnection;

    public MotomanCrclServer(CRCLServerSocket svrSocket, MotoPlusConnection mpConnection) {
        this.svrSocket = svrSocket;
        this.mpConnection = mpConnection;
        this.svrSocket.addListener(this);
    }

    @Override
    public void close() throws Exception {
        svrSocket.close();
        mpConnection.close();
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

    private long last_status_update_time;

    private double lengthScale = 1.0;

    public CRCLStatusType getCrclStatus() {

        if (System.currentTimeMillis() - last_status_update_time > 50) {
            try {
                MP_CART_POS_RSP_DATA pos = mpConnection.getCartPos(0);
                PmCartesian cart = new PmCartesian(pos.x() / lengthScale, pos.y() / lengthScale, pos.z() / lengthScale);
                PmRpy rpy = new PmRpy(Math.toRadians(pos.rz()), Math.toRadians(pos.ry()), Math.toRadians(pos.rx()));
                crclStatus.getPoseStatus().setPose(CRCLPosemath.toPoseType(cart, rcs.posemath.Posemath.toRot(rpy), crclStatus.getPoseStatus().getPose()));
//                crclStatus.getPoseStatus().getPose().setPoint(point(cartData[0].x(), cartData[0].y(), cartData[0].z()));
                MP_PULSE_POS_RSP_DATA pulseData = mpConnection.getPulsePos(0);
                if (null == crclStatus.getJointStatuses()) {
                    crclStatus.setJointStatuses(new JointStatusesType());
                }
                List<JointStatusType> jsl = crclStatus.getJointStatuses().getJointStatus();
                for (int i = 0; i < MP_PULSE_POS_RSP_DATA.MAX_PULSE_AXES; i++) {
                    if (i >= jsl.size()) {
                        JointStatusType js = new JointStatusType();
                        js.setJointNumber(BigInteger.valueOf(i + 1));
                        js.setJointPosition(BigDecimal.valueOf(pulseData.lPos[i]));
                        jsl.add(js);
                    } else {
                        JointStatusType js = jsl.get(i);
                        js.setJointNumber(BigInteger.valueOf(i + 1));
                        js.setJointPosition(BigDecimal.valueOf(pulseData.lPos[i]));
                        jsl.set(i, js);
                    }
                }
                if(crclStatus.getCommandStatus().getCommandState() == CRCL_WORKING) {
                    if(lastSentTargetId != lastRecvdTargetId) {
                        System.out.println("lastSentTargetId = " + lastSentTargetId);
                        System.out.println("lastRecvdTargetId = " + lastRecvdTargetId);
                        int recvId[] = new int[1];
                        mpConnection.mpMotTargetReceive(0, lastSentTargetId, recvId, 0, MotoPlusConnection.NO_WAIT);
                        System.out.println("recvId = " + Arrays.toString(recvId));
                        if(recvId[0] != 0) {
                            lastRecvdTargetId = recvId[0];
                        }
                        if(lastSentTargetId == lastRecvdTargetId) {
                            crclStatus.getCommandStatus().setCommandState(CRCL_DONE);
                        }
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
        mpConnection.mpMotStop(0);
    }

    private int lastSentTargetId = 1;
    private int lastRecvdTargetId = 1;
    
    private void actuateJoints(ActuateJointsType ajs) throws IOException, MotoPlusConnection.MotoPlusConnectionException {
        JointTarget tgt  = new JointTarget();
        mpConnection.mpSetServoPower(true);
        boolean power = mpConnection.mpGetServoPower();
        System.out.println("power = " + power);
        lastSentTargetId++;
        tgt.setId(lastSentTargetId);
        tgt.setIntp(MP_INTP_TYPE.MP_MOVJ_TYPE);
        MP_PULSE_POS_RSP_DATA pulseData = mpConnection.getPulsePos(0);
        System.out.println("pulseData = " + pulseData);
        for (int i = 0; i < MP_PULSE_POS_RSP_DATA.MAX_PULSE_AXES; i++) {
            tgt.getDst()[i] = pulseData.lPos[i];
            tgt.getAux()[i] = pulseData.lPos[i];
        }
        for(ActuateJointType aj : ajs.getActuateJoint()) {
            tgt.getDst()[aj.getJointNumber().intValue()-1] = aj.getJointPosition().intValue();
            tgt.getAux()[aj.getJointNumber().intValue()-1] = aj.getJointPosition().intValue();
        }
        System.out.println("tgt = " + tgt);
        MotCtrlReturnEnum targetJointSendRet =
                mpConnection.mpMotTargetJointSend(1, tgt, MotoPlusConnection.NO_WAIT);
        System.out.println("targetJointSendRet = " + targetJointSendRet);
        MotCtrlReturnEnum motStartRet =
                mpConnection.mpMotStart(0);
        System.out.println("motStartRet = " + motStartRet);
        System.out.println("lastSentTargetId = " + lastSentTargetId);
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
                        actuateJoints((ActuateJointsType)cmd);
                        crclStatus.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_WORKING);
                        crclStatus.getCommandStatus().setStateDescription("");
                    }
                    System.out.println("cmd = " + cmd);
                }
            }
        } catch (CRCLException | IOException | MotoPlusConnection.MotoPlusConnectionException ex) {
            Logger.getLogger(MotomanCrclServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static final String DEFAULT_MOTOMAN_HOST = "10.0.0.2";

//    public static final String DEFAULT_MOTOMAN_HOST = "localhost";
    public static final int DEFAULT_MOTOMAN_PORT = 11000;

    public static void main(String[] args) throws Exception {
        try (MotomanCrclServer motomanCrclServer = new MotomanCrclServer(
                new CRCLServerSocket(),
                new MotoPlusConnection(new Socket(DEFAULT_MOTOMAN_HOST, DEFAULT_MOTOMAN_PORT)))) {
            motomanCrclServer.run();
        }
    }

    @Override
    public void run() {
        svrSocket.run();
    }
}
