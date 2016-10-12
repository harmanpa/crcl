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

import com.github.wshackle.crcl4java.motoman.sys1.MP_CART_POS_RSP_DATA;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStateEnumType;
import crcl.base.CommandStatusType;
import crcl.base.GetStatusType;
import crcl.base.PoseStatusType;
import crcl.utils.CRCLException;
import crcl.utils.CRCLPosemath;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.pose;
import static crcl.utils.CRCLPosemath.vector;
import crcl.utils.CRCLServerSocket;
import crcl.utils.CRCLServerSocketEvent;
import crcl.utils.CRCLServerSocketEventListener;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
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
                MP_CART_POS_RSP_DATA cartData[] = new MP_CART_POS_RSP_DATA[1];
                cartData[0] = new MP_CART_POS_RSP_DATA();
                MP_CART_POS_RSP_DATA pos = cartData[0];
                mpConnection.mpGetCartPos(0, cartData);
                PmCartesian cart = new PmCartesian(pos.x() / lengthScale, pos.y() / lengthScale, pos.z() / lengthScale);
                PmRpy rpy = new PmRpy(Math.toRadians(pos.rz()), Math.toRadians(pos.ry()), Math.toRadians(pos.rx()));
                crclStatus.getPoseStatus().setPose(CRCLPosemath.toPoseType(cart, rcs.posemath.Posemath.toRot(rpy), crclStatus.getPoseStatus().getPose()));
//                crclStatus.getPoseStatus().getPose().setPoint(point(cartData[0].x(), cartData[0].y(), cartData[0].z()));
                last_status_update_time = System.currentTimeMillis();
            } catch (IOException | PmException ex) {
                Logger.getLogger(MotomanCrclServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        crclStatus.getCommandStatus().setStatusID(crclStatus.getCommandStatus().getStatusID().add(BigInteger.ONE));
        return crclStatus;
    }

    @Override
    public void accept(CRCLServerSocketEvent event) {
        try {
            CRCLCommandInstanceType cmdInstance = event.getInstance();
            if (null != cmdInstance) {
                CRCLCommandType cmd = cmdInstance.getCRCLCommand();
                if (cmd instanceof GetStatusType) {
                    event.getSource().writeStatus(getCrclStatus());
                }
            }
        } catch (CRCLException ex) {
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
