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

import com.github.wshackle.crcl4java.motoman.motctrl.RemoteMotFunctionType;
import com.github.wshackle.crcl4java.motoman.motctrl.CoordTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.JointTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_COORD_TYPE;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_SPEED;
import com.github.wshackle.crcl4java.motoman.motctrl.MotCtrlReturnEnum;
import com.github.wshackle.crcl4java.motoman.sys1.MP_CART_POS_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_DEG_POS_RSP_DATA_EX;
import com.github.wshackle.crcl4java.motoman.sys1.MP_FB_PULSE_POS_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_PULSE_POS_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_VAR_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_VAR_INFO;
import com.github.wshackle.crcl4java.motoman.sys1.RemoteSys1FunctionType;
import com.github.wshackle.crcl4java.motoman.sys1.UnitType;
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
    public static final int NO_WAIT = 0;
    public static final int WAIT_FOREVER = -1;

    public MotoPlusConnection(Socket socket) throws IOException {
        this.socket = socket;
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
    }

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

    public void startMpMotTargetClear(int grp, int options) throws IOException {
        final int inputSize = 20;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteMotFunctionType.MOT_TARGET_CLEAR.getId()); // type of function remote server will call
        bb.putInt(12, grp);
        bb.putInt(16, options);
        dos.write(bb.array());
    }

    public MotCtrlReturnEnum mpMotTargetClear(int grp, int options) throws IOException {
        startMpMotTargetClear(grp, options);
        return getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotTargetJointSend(int grp, JointTarget target, int timeout) throws IOException {
        startMpMotTargetJointSend(grp, target, timeout);
        return getMpMotStandardReturn();
    }

    public void startMpMotTargetJointSend(int grp, JointTarget target, int timeout) throws IOException {
        final int inputSize = 92;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteMotFunctionType.MOT_JOINT_TARGET_SEND.getId()); // type of function remote server will call
        bb.putInt(12, grp);
        bb.putInt(16, target.getId());
        bb.putInt(20, target.getIntp().getId());
        for (int i = 0; i < 8 /* MP_GRP_AXES_NUM */; i++) {
            bb.putInt(24 + (i * 4), target.getDst()[i]);
        }
        for (int i = 0; i < 8 /* MP_GRP_AXES_NUM */; i++) {
            bb.putInt(56 + (i * 4), target.getAux()[i]);
        }
        bb.putInt(88, timeout);
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

    public MotCtrlReturnEnum mpMotTargetCoordSend(int grp, CoordTarget target, int timeout) throws IOException {
        startMpMotTargetCoordSend(grp, target, timeout);
        return getMpMotStandardReturn();
    }

    public void startMpMotTargetCoordSend(int grp, CoordTarget target, int timeout) throws IOException {
        final int inputSize = 92;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteMotFunctionType.MOT_COORD_TARGET_SEND.getId()); // type of function remote server will call
        bb.putInt(12, grp);
        bb.putInt(16, target.getId());
        bb.putInt(20, target.getIntp().getId());
        bb.putInt(24, target.getDst().x);
        bb.putInt(28, target.getDst().y);
        bb.putInt(32, target.getDst().z);
        bb.putInt(36, target.getDst().rx);
        bb.putInt(40, target.getDst().ry);
        bb.putInt(44, target.getDst().rz);
        bb.putInt(48, target.getDst().ex1);
        bb.putInt(52, target.getDst().ex2);
        bb.putInt(56, target.getAux().x);
        bb.putInt(60, target.getAux().y);
        bb.putInt(64, target.getAux().z);
        bb.putInt(68, target.getAux().rx);
        bb.putInt(72, target.getAux().ry);
        bb.putInt(76, target.getAux().rz);
        bb.putInt(80, target.getAux().ex1);
        bb.putInt(84, target.getAux().ex2);
        bb.putInt(88, timeout);
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
        final int inputSize = 36;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteMotFunctionType.MOT_SET_SPEED.getId()); // type of function remote server will call
        bb.putInt(12, grpNo);
        bb.putInt(16, spd.vj);
        bb.putInt(24, spd.v);
        bb.putInt(32, spd.vr);
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

    public boolean getSysOkReturn() throws IOException {
        byte inbuf[] = new byte[4];
        dis.readFully(inbuf);
        ByteBuffer bb = ByteBuffer.wrap(inbuf);
        int sz = bb.getInt(0);
        inbuf = new byte[sz];
        dis.readFully(inbuf);
        bb = ByteBuffer.wrap(inbuf);
        int intRet = bb.getInt(0);
        return intRet == 0;
    }

    public boolean mpPutVarData(MP_VAR_DATA[] sData, int num) throws IOException {
        startMpPutVarData(sData, num);
        return getSysOkReturn();
    }

    public void startMpPutVarData(MP_VAR_DATA[] sData, int num) throws IOException {
        final int inputSize = 16 + (8 * num);
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteSys1FunctionType.SYS1_PUT_VAR_DATA.getId()); // type of function remote server will call
        bb.putInt(12, num);
        for (int i = 0; i < num; i++) {
            bb.putShort(16 + (i * 8), sData[i].usType.getId());
            bb.putShort(18 + (i * 8), sData[i].usIndex);
            bb.putInt(20 + (i * 8), sData[i].ulValue);
        }
        dos.write(bb.array());
    }

    public boolean mpGetVarData(MP_VAR_INFO[] sData, long[] rData, int num) throws IOException {
        startMpGetVarData(sData, rData, num);
        return getSysDataReturn(rData);
    }

    public boolean getSysDataReturn(long rData[]) throws IOException {
        byte inbuf[] = new byte[4];
        dis.readFully(inbuf);
        ByteBuffer bb = ByteBuffer.wrap(inbuf);
        int sz = bb.getInt(0);
        inbuf = new byte[sz];
        dis.readFully(inbuf);
        bb = ByteBuffer.wrap(inbuf);
        int intRet = bb.getInt(0);
        for (int i = 0; i < rData.length && i < (sz - 4) / 8; i++) {
            rData[i] = bb.getLong(4 + (i * 8));
        }
        return intRet == 0;
    }

    public void startMpGetVarData(MP_VAR_INFO[] sData, long[] rData, int num) throws IOException {
        final int inputSize = (int) (16 + (4 * num));
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_VAR_DATA.getId()); // type of function remote server will call
        bb.putInt(12, num);
        for (int i = 0; i < num; i++) {
            bb.putShort(16 + (4 * i), sData[i].usType.getId());
            bb.putShort(18 + (4 * i), sData[i].usIndex);
        }
        dos.write(bb.array());
    }

    public MP_CART_POS_RSP_DATA getCartPos(int grp) throws MotoPlusConnectionException, IOException {
        MP_CART_POS_RSP_DATA cartData[] = new MP_CART_POS_RSP_DATA[1];
        cartData[0] = new MP_CART_POS_RSP_DATA();
        MP_CART_POS_RSP_DATA pos = cartData[0];
        if (!mpGetCartPos(0, cartData)) {
            throw new MotoPlusConnectionException("mpGetCartPos returned false");
        }
        return cartData[0];
    }

    public boolean mpGetCartPos(int ctrlGroup, MP_CART_POS_RSP_DATA[] data) throws IOException {
        startMpGetCartPos(ctrlGroup, data);
        return getCartPosReturn(data);
    }

    public boolean getCartPosReturn(MP_CART_POS_RSP_DATA[] data) throws IOException {
        byte inbuf[] = new byte[4];
        dis.readFully(inbuf);
        ByteBuffer bb = ByteBuffer.wrap(inbuf);
        int sz = bb.getInt(0);
        inbuf = new byte[sz];
        dis.readFully(inbuf);
        bb = ByteBuffer.wrap(inbuf);
        int intRet = bb.getInt(0);
        for (int i = 0; i < MP_CART_POS_RSP_DATA.MAX_CART_AXES; i++) {
            data[0].lPos[i] = bb.getInt(4 + (i * 4));
        }
        data[0].sConfig = bb.getShort(52);
        return intRet == 0;
    }

    public void startMpGetCartPos(int ctrlGroup, MP_CART_POS_RSP_DATA[] data) throws IOException {
        final int inputSize = 16;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_CURRENT_CART_POS.getId()); // type of function remote server will call
        bb.putInt(12, ctrlGroup);
        dos.write(bb.array());
    }

    public MP_PULSE_POS_RSP_DATA getPulsePos(int grp) throws MotoPlusConnectionException, IOException {
        MP_PULSE_POS_RSP_DATA pulseData[] = new MP_PULSE_POS_RSP_DATA[1];
        pulseData[0] = new MP_PULSE_POS_RSP_DATA();
        if (!mpGetPulsePos(grp, pulseData)) {
            throw new MotoPlusConnectionException("mpGetPulsePos returned false");
        }
        return pulseData[0];
    }

    public boolean mpGetPulsePos(int ctrlGroup, MP_PULSE_POS_RSP_DATA[] data) throws IOException {
        startMpGetPulsePos(ctrlGroup, data);
        return getPulsePosReturn(data);
    }

    public boolean getPulsePosReturn(MP_PULSE_POS_RSP_DATA[] data) throws IOException {
        byte inbuf[] = new byte[4];
        dis.readFully(inbuf);
        ByteBuffer bb = ByteBuffer.wrap(inbuf);
        int sz = bb.getInt(0);
        inbuf = new byte[sz];
        dis.readFully(inbuf);
        bb = ByteBuffer.wrap(inbuf);
        int intRet = bb.getInt(0);
        for (int i = 0; i < MP_PULSE_POS_RSP_DATA.MAX_PULSE_AXES; i++) {
            data[0].lPos[i] = bb.getInt(4 + (i * 4));
        }
        return intRet == 0;
    }

    public void startMpGetPulsePos(int ctrlGroup, MP_PULSE_POS_RSP_DATA[] data) throws IOException {
        final int inputSize = 16;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_CURRENT_PULSE_POS.getId()); // type of function remote server will call
        bb.putInt(12, ctrlGroup);
        dos.write(bb.array());
    }

    public boolean mpGetFBPulsePos(int ctrlGroup, MP_FB_PULSE_POS_RSP_DATA[] data) throws IOException {
        startMpGetFBPulsePos(ctrlGroup, data);
        return getFBPulsePosReturn(data);
    }

    public boolean getFBPulsePosReturn(MP_FB_PULSE_POS_RSP_DATA[] data) throws IOException {
        byte inbuf[] = new byte[4];
        dis.readFully(inbuf);
        ByteBuffer bb = ByteBuffer.wrap(inbuf);
        int sz = bb.getInt(0);
        inbuf = new byte[sz];
        dis.readFully(inbuf);
        bb = ByteBuffer.wrap(inbuf);
        int intRet = bb.getInt(0);
        for (int i = 0; i < MP_PULSE_POS_RSP_DATA.MAX_PULSE_AXES; i++) {
            data[0].lPos[i] = bb.getInt(4 + (i * 4));
        }
        return intRet == 0;
    }

    public void startMpGetFBPulsePos(int ctrlGroup, MP_FB_PULSE_POS_RSP_DATA[] data) throws IOException {
        final int inputSize = 16;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_CURRENT_FEEDBACK_PULSE_POS.getId()); // type of function remote server will call
        bb.putInt(12, ctrlGroup);
        dos.write(bb.array());
    }

    public boolean mpGetDegPosEx(int ctrlGroup, MP_DEG_POS_RSP_DATA_EX[] data) throws IOException {
        startMpGetDegPosEx(ctrlGroup, data);
        return getDegPosExPosReturn(data);
    }

    public boolean getDegPosExPosReturn(MP_DEG_POS_RSP_DATA_EX[] data) throws IOException {
        byte inbuf[] = new byte[4];
        dis.readFully(inbuf);
        ByteBuffer bb = ByteBuffer.wrap(inbuf);
        int sz = bb.getInt(0);
        inbuf = new byte[sz];
        dis.readFully(inbuf);
        bb = ByteBuffer.wrap(inbuf);
        int intRet = bb.getInt(0);
        for (int i = 0; i < MP_DEG_POS_RSP_DATA_EX.MAX_PULSE_AXES; i++) {
            data[0].degPos[i] = bb.getInt(4 + (i * 4));
        }
        for (int i = 0; i < MP_DEG_POS_RSP_DATA_EX.MAX_PULSE_AXES; i++) {
            int unitInt = bb.getInt(68 + (i * 4));
            data[0].degUnit[i] = UnitType.fromId(unitInt);
        }
        return intRet == 0;
    }

    public void startMpGetDegPosEx(int ctrlGroup, MP_DEG_POS_RSP_DATA_EX[] data) throws IOException {
        final int inputSize = 16;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_DEG_POS_EX.getId()); // type of function remote server will call
        bb.putInt(12, ctrlGroup);
        dos.write(bb.array());
    }

    public static class MotoPlusConnectionException extends Exception {

        public MotoPlusConnectionException(String message) {
            super(message);
        }

    }

    public boolean mpGetServoPower() throws IOException, MotoPlusConnectionException {
        startMpGetServoPower();
        return getServoPowerReturn();
    }

    public boolean getServoPowerReturn() throws IOException, MotoPlusConnectionException {
        byte inbuf[] = new byte[4];
        dis.readFully(inbuf);
        ByteBuffer bb = ByteBuffer.wrap(inbuf);
        int sz = bb.getInt(0);
        inbuf = new byte[sz];
        dis.readFully(inbuf);
        bb = ByteBuffer.wrap(inbuf);
        int intRet = bb.getInt(0);
        short shortRet = bb.getShort(4);
        if (intRet != 0) {
            throw new MotoPlusConnectionException("mpGetServoPower returned " + intRet);
        }
        if (shortRet < 0 || shortRet > 1) {
            throw new MotoPlusConnectionException("mpGetServoPower had invalid value for sServoPower =  " + shortRet);
        }
        return shortRet == 1;
    }

    public void startMpGetServoPower() throws IOException {
        final int inputSize = 12;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_SERVO_POWER.getId()); // type of function remote server will call
        dos.write(bb.array());
    }

    public boolean mpSetServoPower(boolean on) throws IOException, MotoPlusConnectionException {
        startMpSetServoPower(on);
        return getSetServoPowerReturn();
    }

    public boolean getSetServoPowerReturn() throws IOException, MotoPlusConnectionException {
        byte inbuf[] = new byte[4];
        dis.readFully(inbuf);
        ByteBuffer bb = ByteBuffer.wrap(inbuf);
        int sz = bb.getInt(0);
        inbuf = new byte[sz];
        dis.readFully(inbuf);
        bb = ByteBuffer.wrap(inbuf);
        int intRet = bb.getInt(0);
        short shortRet = bb.getShort(4);
        if (intRet != 0) {
            throw new MotoPlusConnectionException("mpSetServoPower returned " + intRet);
        }
        return shortRet == 1;
    }

    public void startMpSetServoPower(boolean on) throws IOException {
        final int inputSize = 14;
        ByteBuffer bb = ByteBuffer.allocate(inputSize);
        bb.putInt(0, inputSize - 4); // bytes to read
        bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
        bb.putInt(8, RemoteSys1FunctionType.SYS1_SET_SERVO_POWER.getId()); // type of function remote server will call
        bb.putShort(12, (short) (on ? 1 : 0));
        dos.write(bb.array());
    }
}
