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
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class MotoPlusConnection implements AutoCloseable {

    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;

    public void connect(String host, int port) throws IOException {
        if (null != socket) {
            socket.close();
        }
        socket = new Socket(host, port);
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void close() throws Exception {
        if (null != dos) {
            try {
                dos.close();

            } catch (IOException iOException) {
            }
            dos = null;
        }
        if (null != dis) {
            try {
                dis.close();

            } catch (IOException iOException) {
            }
            dis = null;
        }
        if (null != socket) {
            try {
                socket.close();

            } catch (IOException iOException) {
            }
            socket = null;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        close();
    }

    public void startMpMotStart(int options) throws IOException {
        final int inputSize = 16;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteMotFunctionType.MOT_START.getId()); // type of function remote server will call
        bb.putInt(12, options);
        dos.write(bb.array());
    }

    public MotCtrlReturnEnum mpMotStart(int options) throws IOException {
        startMpMotStart(options);
        return getMpMotStandardReturn();
    }

    public void startMpMotStop(int options) throws IOException {
        final int inputSize = 16;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteMotFunctionType.MOT_STOP.getId()); // type of function remote server will call
        bb.putInt(12, options);
        dos.write(bb.array());
    }

    public MotCtrlReturnEnum mpMotStop(int options) throws IOException {
        startMpMotStop(options);
        return getMpMotStandardReturn();
    }

    public void startMpMotTargetClear(long grp, int options) throws IOException {
        final int inputSize = 24;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteMotFunctionType.MOT_TARGET_CLEAR.getId()); // type of function remote server will call
        bb.putLong(12, grp);
        bb.putInt(20, options);
        dos.write(bb.array());
    }

    public MotCtrlReturnEnum mpMotTargetClear(long grp, int options) throws IOException {
        startMpMotTargetClear(grp, options);
        return getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotTargetJointSend(long grp, JointTarget target, int timeout) throws IOException {
        startMpMotTargetJointSend(grp, target, timeout);
        return getMpMotStandardReturn();
    }

    public void startMpMotTargetJointSend(long grp, JointTarget target, int timeout) throws IOException {
        final int inputSize = 160;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteMotFunctionType.MOT_JOINT_TARGET_SEND.getId()); // type of function remote server will call
        bb.putLong(12, grp);
        bb.putInt(20, target.getId());
        bb.putInt(24, target.getIntp().getId());
        for (int i = 0; i < 8 /* MP_GRP_AXES_NUM */; i++) {
            bb.putLong(28 + (i * 8), target.getDst()[i]);
        }
        for (int i = 0; i < 8 /* MP_GRP_AXES_NUM */; i++) {
            bb.putLong(92 + (i * 8), target.getAux()[i]);
        }
        bb.putInt(156, timeout);
        dos.write(bb.array());
    }

    public MotCtrlReturnEnum getMpMotStandardReturn() throws IOException {
        byte inbuf[] = new byte[4];
        dis.readFully(inbuf);
        ByteBuffer bb = ByteBuffer.wrap(inbuf);
        int sz = bb.getInt(0);
        inbuf = new byte[sz];
        dis.readFully(inbuf);
        bb = ByteBuffer.wrap(inbuf);
        int intRet = bb.getInt(0);
        return MotCtrlReturnEnum.fromId(intRet);
    }

    public MotCtrlReturnEnum mpMotTargetCoordSend(long grp, CoordTarget target, int timeout) throws IOException {
        startMpMotTargetCoordSend(grp, target, timeout);
        return getMpMotStandardReturn();
    }

    public void startMpMotTargetCoordSend(long grp, CoordTarget target, int timeout) throws IOException {
        final int inputSize = 160;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteMotFunctionType.MOT_COORD_TARGET_SEND.getId()); // type of function remote server will call
        bb.putLong(12, grp);
        bb.putInt(20, target.getId());
        bb.putInt(24, target.getIntp().getId());
        bb.putLong(28, target.getDst().x);
        bb.putLong(36, target.getDst().y);
        bb.putLong(44, target.getDst().z);
        bb.putLong(52, target.getDst().rx);
        bb.putLong(60, target.getDst().ry);
        bb.putLong(68, target.getDst().rz);
        bb.putLong(76, target.getDst().ex1);
        bb.putLong(84, target.getDst().ex2);
        bb.putLong(92, target.getAux().x);
        bb.putLong(100, target.getAux().y);
        bb.putLong(108, target.getAux().z);
        bb.putLong(116, target.getAux().rx);
        bb.putLong(124, target.getAux().ry);
        bb.putLong(132, target.getAux().rz);
        bb.putLong(140, target.getAux().ex1);
        bb.putLong(148, target.getAux().ex2);
        bb.putInt(156, timeout);
        dos.write(bb.array());
    }

    public MotCtrlReturnEnum mpMotTargetReceive(int grpNo, int id, int[] recvId, int timeout, int options) throws IOException {
        startMpMotTargetReceive(grpNo, id, recvId, timeout, options);
        return getMpMotTargetReceiveReturn(recvId);
    }

    public MotCtrlReturnEnum getMpMotTargetReceiveReturn(int[] recvId) throws IOException {
        byte inbuf[] = new byte[4];
        dis.readFully(inbuf);
        ByteBuffer bb = ByteBuffer.wrap(inbuf);
        int sz = bb.getInt(0);
        inbuf = new byte[sz];
        dis.readFully(inbuf);
        bb = ByteBuffer.wrap(inbuf);
        int intRet = bb.getInt(0);
        if (sz >= 8 && null != recvId && recvId.length > 0) {
            recvId[0] = bb.getInt(4);
        }
        return MotCtrlReturnEnum.fromId(intRet);
    }

    public void startMpMotTargetReceive(int grpNo, int id, int[] recvId, int timeout, int options) throws IOException {
        final int inputSize = 28;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteMotFunctionType.MOT_TARGET_RECEIVE.getId()); // type of function remote server will call
        bb.putInt(12, grpNo);
        bb.putInt(16, id);
        bb.putInt(20, timeout);
        bb.putInt(24, options);
        dos.write(bb.array());
    }

    public MotCtrlReturnEnum mpMotSetCoord(int grpNo, MP_COORD_TYPE type, int aux) throws IOException {
        startMpMotSetCoord(grpNo, type, aux);
        return getMpMotStandardReturn();
    }

    public void startMpMotSetCoord(int grpNo, MP_COORD_TYPE type, int aux) throws IOException {
        final int inputSize = 24;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteMotFunctionType.MOT_SET_COORD.getId()); // type of function remote server will call
        bb.putInt(12, grpNo);
        bb.putInt(16, type.getId());
        bb.putInt(20, aux);
        dos.write(bb.array());
    }

    public MotCtrlReturnEnum mpMotSetTool(int grpNo, int toolNo) throws IOException {
        startMpMotSetTool(grpNo, toolNo);
        return getMpMotStandardReturn();
    }

    public void startMpMotSetTool(int grpNo, int toolNo) throws IOException {
        final int inputSize = 20;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteMotFunctionType.MOT_SET_TOOL.getId()); // type of function remote server will call
        bb.putInt(12, grpNo);
        bb.putInt(16, toolNo);
        dos.write(bb.array());
    }

    public MotCtrlReturnEnum mpMotSetSpeed(int grpNo, MP_SPEED spd) throws IOException {
        startMpMotSetSpeed(grpNo, spd);
        return getMpMotStandardReturn();
    }

    public void startMpMotSetSpeed(int grpNo, MP_SPEED spd) throws IOException {
        final int inputSize = 40;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteMotFunctionType.MOT_SET_SPEED.getId()); // type of function remote server will call
        bb.putInt(12, grpNo);
        bb.putLong(16, spd.vj);
        bb.putLong(24, spd.v);
        bb.putLong(32, spd.vr);
        dos.write(bb.array());
    }

    public MotCtrlReturnEnum mpMotSetOrigin(int grpNo, int options) throws IOException {
        startMpMotSetOrigin(grpNo, options);
        return getMpMotStandardReturn();
    }

    public void startMpMotSetOrigin(int grpNo, int options) throws IOException {
        final int inputSize = 20;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteMotFunctionType.MOT_SET_ORIGIN.getId()); // type of function remote server will call
        bb.putInt(12, grpNo);
        bb.putInt(16, options);
        dos.write(bb.array());
    }

    public MotCtrlReturnEnum mpMotSetTask(int grpNo, int taskNo) throws IOException {
        startMpMotSetTask(grpNo, taskNo);
        return getMpMotStandardReturn();
    }

    public void startMpMotSetTask(int grpNo, int taskNo) throws IOException {
        final int inputSize = 20;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteMotFunctionType.MOT_SET_TASK.getId()); // type of function remote server will call
        bb.putInt(12, grpNo);
        bb.putInt(16, taskNo);
        dos.write(bb.array());
    }

    public MotCtrlReturnEnum mpMotSetSync(int grpNo, int aux, int options) throws IOException {
        startMpMotSetSync(grpNo, aux, options);
        return getMpMotStandardReturn();
    }

    public void startMpMotSetSync(int grpNo, int aux, int options) throws IOException {
        final int inputSize = 24;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteMotFunctionType.MOT_SET_TASK.getId()); // type of function remote server will call
        bb.putInt(12, grpNo);
        bb.putInt(16, aux);
        bb.putInt(20, options);
        dos.write(bb.array());
    }

    public MotCtrlReturnEnum mpMotResetSync(int grpNo) throws IOException {
        startMpMotSetSync(grpNo);
        return getMpMotStandardReturn();
    }

    public void startMpMotSetSync(int grpNo) throws IOException {
        final int inputSize = 16;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteMotFunctionType.MOT_SET_TASK.getId()); // type of function remote server will call
        bb.putInt(12, grpNo);
        dos.write(bb.array());
    }

    public static void main(String[] args) throws Exception {
        try (MotoPlusConnection mpc = new MotoPlusConnection()) {
            mpc.connect("localhost", 11000);

            System.out.println("Calling mpMotStart(1)");
            MotCtrlReturnEnum motStartRet = mpc.mpMotStart(1);
            System.out.println("motStartRet = " + motStartRet);
            Thread.sleep(200);

            System.out.println("Calling mpMotStop(2)");
            MotCtrlReturnEnum motStopRet = mpc.mpMotStop(2);
            System.out.println("motStopRet = " + motStopRet);
            Thread.sleep(200);

            System.out.println("Calling mpMotTargetClear(3,4)");
            MotCtrlReturnEnum motClearRet = mpc.mpMotTargetClear(3,4);
            System.out.println("motClearRet = " + motClearRet);
            Thread.sleep(200);
            
            JointTarget jointTarget = new JointTarget();
            jointTarget.setId(5);
            jointTarget.setIntp(MP_INTP_TYPE.MP_MOVJ_TYPE);
            for (int i = 0; i < jointTarget.getDst().length; i++) {
                jointTarget.getDst()[i] = 6+i;
            }
            
            for (int i = 0; i < jointTarget.getDst().length; i++) {
                jointTarget.getAux()[i] = 14+i;
            }
            System.out.println("jointTarget = " + jointTarget);
            System.out.println("Calling mpMotTargetJointSend(33,(...),35)");
            MotCtrlReturnEnum motTargetJointRet = mpc.mpMotTargetJointSend(33, jointTarget, 35);
            System.out.println("motTargetJointRet = " + motTargetJointRet);
            Thread.sleep(200);
            
            CoordTarget coordTarget = new CoordTarget();
            coordTarget.setId(36);
            coordTarget.setIntp(MP_INTP_TYPE.MP_MOVL_TYPE);
            coordTarget.getDst().x = 40;
            coordTarget.getDst().y = 41;
            coordTarget.getDst().z = 42;
            coordTarget.getDst().rx = 43;
            coordTarget.getDst().ry = 44;
            coordTarget.getDst().rz = 45;
            coordTarget.getDst().ex1 = 46;
            coordTarget.getDst().ex2 = 47;
            
            coordTarget.getAux().x = 50;
            coordTarget.getAux().y = 51;
            coordTarget.getAux().z = 52;
            coordTarget.getAux().rx = 53;
            coordTarget.getAux().ry = 54;
            coordTarget.getAux().rz = 55;
            coordTarget.getAux().ex1 = 56;
            coordTarget.getAux().ex2 = 57;
            System.out.println("coordTarget = " + coordTarget);
            System.out.println("Calling mpMotTargetJointSend(63,(...),65)");
            MotCtrlReturnEnum motTargetCoordRet = mpc.mpMotTargetCoordSend(63, coordTarget, 65);
            System.out.println("motTargetCoordRet = " + motTargetCoordRet);
            Thread.sleep(200);
            
        }
    }
}
