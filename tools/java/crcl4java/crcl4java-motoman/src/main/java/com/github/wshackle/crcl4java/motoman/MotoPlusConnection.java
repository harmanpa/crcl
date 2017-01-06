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

import com.github.wshackle.crcl4java.motoman.exfile.MP_GET_JOBLIST_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.exfile.MpExFileRamIdEnum;
import com.github.wshackle.crcl4java.motoman.exfile.MpExtensionType;
import com.github.wshackle.crcl4java.motoman.exfile.RemoteExFileFunctionType;
import com.github.wshackle.crcl4java.motoman.file.MpFileFlagsEnum;
import com.github.wshackle.crcl4java.motoman.file.RemoteFileFunctionType;
import com.github.wshackle.crcl4java.motoman.motctrl.RemoteMotFunctionType;
import com.github.wshackle.crcl4java.motoman.motctrl.CoordTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.JointTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_COORD_TYPE;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_SPEED;
import com.github.wshackle.crcl4java.motoman.motctrl.MotCtrlReturnEnum;
import com.github.wshackle.crcl4java.motoman.sys1.CycleEnum;
import com.github.wshackle.crcl4java.motoman.sys1.MP_ALARM_CODE_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_ALARM_STATUS_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_CART_POS_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_CYCLE_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_DEG_POS_RSP_DATA_EX;
import com.github.wshackle.crcl4java.motoman.sys1.MP_FB_PULSE_POS_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_IO_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_IO_INFO;
import com.github.wshackle.crcl4java.motoman.sys1.MP_MODE_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_PULSE_POS_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_VAR_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_VAR_INFO;
import com.github.wshackle.crcl4java.motoman.sys1.ModeEnum;
import com.github.wshackle.crcl4java.motoman.sys1.RemoteSys1FunctionType;
import com.github.wshackle.crcl4java.motoman.sys1.UnitType;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class MotoPlusConnection implements AutoCloseable {

    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    public static final int NO_WAIT = 0;
    private static final int WAIT_FOREVER = -1; // This is not allowed to avoid hanging server.
    
    private volatile int maxWait = -99;
    
    public synchronized  int mpGetMaxWait() throws IOException, MotoPlusConnectionException {
        if(maxWait == -99) {
            maxWait = 5000/mpGetRtc();
        }
        return maxWait;
    }
    
    public MotoPlusConnection(Socket socket) throws IOException {
        this.socket = socket;
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
    }

    public boolean isConnected() {
        return null != socket && socket.isConnected();
    }
    
    private String host;
    private int port;
    
    public void connect(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        if (null != socket) {
            socket.close();
        }
        System.out.println("Connecting to " + host + ", port=" + port + " . . .");
        socket = new Socket(host, port);
        System.out.println("Connection successful.");
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
    }
    public  void reconnect() throws IOException {
        if(!isConnected() && null != host && port > 0) {
            connect(host,port);
        }
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

    public final class Starter {

        private Starter() {
            // only one needed per MotoPlusConnection instance
        }

        public void startMpLoadFile(int fd, String name, String name2) throws IOException {
            final int inputSize = name.length() + name2.length() + 22;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.EX_FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteExFileFunctionType.EX_FILE_CTRL_LOAD_FILE.getId()); // type of function remote server will call
            bb.putInt(12, fd);
            int offset = 21 + name.length();
            bb.putInt(16, offset);
            bb.position(20);
            bb.put(name.getBytes());
            bb.position(offset);
            bb.put(name2.getBytes());
            dos.write(bb.array());
        }

        public void startMpSaveFile(int fd, String name, String name2) throws IOException {
            final int inputSize = name.length() + name2.length() + 22;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.EX_FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteExFileFunctionType.EX_FILE_CTRL_SAVE_FILE.getId()); // type of function remote server will call
            bb.putInt(12, fd);
            int offset = 21 + name.length();
            bb.putInt(16, offset);
            bb.position(20);
            bb.put(name.getBytes());
            bb.position(offset);
            bb.put(name2.getBytes());
            dos.write(bb.array());
        }

        public void startMpFdReadFile(int fd, String name) throws IOException {
            final int inputSize = name.length() + 17;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.EX_FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteExFileFunctionType.EX_FILE_CTRL_FD_READ_FILE.getId()); // type of function remote server will call
            bb.putInt(12, fd);
            bb.position(16);
            bb.put(name.getBytes());
            dos.write(bb.array());
        }

        public void startMpFdWriteFile(int fd, String name) throws IOException {
            final int inputSize = name.length() + 17;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.EX_FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteExFileFunctionType.EX_FILE_CTRL_FD_WRITE_FILE.getId()); // type of function remote server will call
            bb.putInt(12, fd);
            bb.position(16);
            bb.put(name.getBytes());
            dos.write(bb.array());
        }

        public void startMpFdGetJobList(int fd, MP_GET_JOBLIST_RSP_DATA jlistData) throws IOException {
            final int inputSize = 16;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.EX_FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteExFileFunctionType.EX_FILE_CTRL_FD_GET_JOB_LIST.getId()); // type of function remote server will call
            bb.putInt(12, fd);
            dos.write(bb.array());
        }

        public void startMpOpenFile(String name, int flags, int mode) throws IOException {
            final int inputSize = name.length() + 21;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteFileFunctionType.FILE_CTRL_OPEN.getId()); // type of function remote server will call
            bb.putInt(12, flags);
            bb.putInt(16, mode);
            bb.position(20);
            bb.put(name.getBytes());
            dos.write(bb.array());
        }

        public void startMpCreateFile(String name, int flags) throws IOException {
            final int inputSize = name.length() + 17;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteFileFunctionType.FILE_CTRL_CREATE.getId()); // type of function remote server will call
            bb.putInt(12, flags);
            bb.position(16);
            bb.put(name.getBytes());
            dos.write(bb.array());
        }

        public void startMpCloseFile(int fd) throws IOException {
            final int inputSize = 16;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteFileFunctionType.FILE_CTRL_CLOSE.getId()); // type of function remote server will call
            bb.putInt(12, fd);
            dos.write(bb.array());
        }

        public void startMpReadFile(int fd, int maxBytes) throws IOException {
            final int inputSize = 20;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteFileFunctionType.FILE_CTRL_READ.getId()); // type of function remote server will call
            bb.putInt(12, fd);
            bb.putInt(16, maxBytes);
            dos.write(bb.array());
        }

        public void startMpWriteFile(int fd, byte bytesToWrite[], int offset, int maxBytes) throws IOException {
            if (maxBytes > bytesToWrite.length - offset) {
                maxBytes = bytesToWrite.length - offset;
            }
            final int inputSize = 20 + bytesToWrite.length;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteFileFunctionType.FILE_CTRL_WRITE.getId()); // type of function remote server will call
            bb.putInt(12, fd);
            bb.putInt(16, maxBytes);
            bb.position(20);
            bb.put(bytesToWrite, offset, maxBytes);
            dos.write(bb.array());
        }

        public void startMpGetFileCount(MpExtensionType ext) throws IOException {
            final int inputSize = 14;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.EX_FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteExFileFunctionType.EX_FILE_CTRL_GET_FILE_COUNT.getId()); // type of function remote server will call
            bb.putShort(12, ext.getId());
            dos.write(bb.array());
        }

        public void startMpGetFileName(MpExtensionType ext, int index) throws IOException {
            final int inputSize = 18;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.EX_FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteExFileFunctionType.EX_FILE_CTRL_GET_FILE_NAME.getId()); // type of function remote server will call
            bb.putShort(12, ext.getId());
            bb.putInt(14, index);
            dos.write(bb.array());
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

        public void startMpMotStop(int options) throws IOException {
            final int inputSize = 16;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteMotFunctionType.MOT_STOP.getId()); // type of function remote server will call
            bb.putInt(12, options);
            dos.write(bb.array());
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

        public void startMpMotTargetJointSend(int grp, JointTarget target, int timeout) throws IOException {
            final int inputSize = 92;
            checkTimeout(timeout);
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

        public void startMpMotTargetCoordSend(int grp, CoordTarget target, int timeout) throws IOException {
            final int inputSize = 92;
            checkTimeout(timeout);
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

        public void startMpMotTargetReceive(int grpNo, int id, int[] recvId, int timeout, int options) throws IOException {
            checkTimeout(timeout);
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

        public void startMpMotSetSync(int grpNo) throws IOException {
            final int inputSize = 16;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteMotFunctionType.MOT_SET_TASK.getId()); // type of function remote server will call
            bb.putInt(12, grpNo);
            dos.write(bb.array());
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

        public void startMpWriteIO(MP_IO_DATA[] sData, int num) throws IOException {
            final int inputSize = 16 + (8 * num);
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_WRITEIO.getId()); // type of function remote server will call
            bb.putInt(12, num);
            for (int i = 0; i < num; i++) {
                bb.putInt(16 + (i * 8), sData[i].ulAddr);
                bb.putInt(20 + (i * 8), sData[i].ulValue);
            }
            dos.write(bb.array());
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

        public void startMpReadIO(MP_IO_INFO[] sData, short[] iorData, int num) throws IOException {
            final int inputSize = (int) (16 + (4 * num));
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_READIO.getId()); // type of function remote server will call
            bb.putInt(12, num);
            for (int i = 0; i < num; i++) {
                bb.putInt(16 + (4 * i), sData[i].ulAddr);
            }
            dos.write(bb.array());
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

        public void startMpGetPulsePos(int ctrlGroup, MP_PULSE_POS_RSP_DATA[] data) throws IOException {
            final int inputSize = 16;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_CURRENT_PULSE_POS.getId()); // type of function remote server will call
            bb.putInt(12, ctrlGroup);
            dos.write(bb.array());
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

        public void startMpGetDegPosEx(int ctrlGroup, MP_DEG_POS_RSP_DATA_EX[] data) throws IOException {
            final int inputSize = 16;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_DEG_POS_EX.getId()); // type of function remote server will call
            bb.putInt(12, ctrlGroup);
            dos.write(bb.array());
        }

        public void startMpGetServoPower() throws IOException {
            final int inputSize = 12;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_SERVO_POWER.getId()); // type of function remote server will call
            dos.write(bb.array());
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

        public void startMpGetMode() throws IOException {
            final int inputSize = 12;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_MODE.getId()); // type of function remote server will call
            dos.write(bb.array());
        }

        public void startMpGetCycle() throws IOException {
            final int inputSize = 12;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_CYCLE.getId()); // type of function remote server will call
            dos.write(bb.array());
        }

        public void startMpGetAlarmStatus() throws IOException {
            final int inputSize = 12;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_ALARM_STATUS.getId()); // type of function remote server will call
            dos.write(bb.array());
        }

        public void startMpGetAlarmCode() throws IOException {
            final int inputSize = 12;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_ALARM_CODE.getId()); // type of function remote server will call
            dos.write(bb.array());
        }

        public void startMpGetRtc() throws IOException {
            final int inputSize = 12;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_RTC.getId()); // type of function remote server will call
            dos.write(bb.array());
        }
    }

    public final class Returner {

        private Returner() {
            // only one needed per MotoPlusConnection instance
        }

        public int getMpFdReadFileReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            dis.readFully(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            dis.readFully(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            return intRet;
        }

        public int getMpFdWriteFileReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            dis.readFully(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            dis.readFully(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            return intRet;
        }

        public int getMpSaveFileReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            dis.readFully(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            dis.readFully(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            return intRet;
        }

        public int getMpLoadFileReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            dis.readFully(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            dis.readFully(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            return intRet;
        }

        public int getMpOpenFileReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            dis.readFully(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            dis.readFully(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet <= 0) {
                throw new MotoPlusConnectionException("mpOpen returned " + intRet);
            }
            return intRet;
        }

        public int getMpFdGetJobListReturn(MP_GET_JOBLIST_RSP_DATA jListData) throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            dis.readFully(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            dis.readFully(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet != 0) {
                throw new MotoPlusConnectionException("mpFdGetJobList returned " + intRet);
            }
            if (size < 10) {
                throw new MotoPlusConnectionException("mpFdGetJobList size = " + size);
            }
            jListData.err_no = bb.getShort(4);
            jListData.uIsEndFlag = bb.getShort(6);
            jListData.uListDataNum = bb.getShort(8);
            return intRet;
        }

        public int getMpCreateFileReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            dis.readFully(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            dis.readFully(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet <= 0) {
                throw new MotoPlusConnectionException("mpCreate returned " + intRet);
            }
            return intRet;
        }

        public int getMpCloseFileReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            dis.readFully(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            dis.readFully(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet != 0) {
                throw new MotoPlusConnectionException("mpClose returned " + intRet);
            }
            return intRet;
        }

        public int getMpReadFileReturn(byte buf[], int offset, int len) throws IOException, MotoPlusConnectionException {
            if (null == buf || buf.length < offset + len) {
                throw new IllegalArgumentException("Buf must not be null and have length of atleast offset = " + offset + " len = " + len);
            }
            byte inbuf[] = new byte[4];
            dis.readFully(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            dis.readFully(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet < 0) {
                throw new MotoPlusConnectionException("mpRead returned " + intRet);
            }
            if (size <= 3) {
                throw new MotoPlusConnectionException("mpRead size = " + size);
            }
            bb.position(4);
            bb.get(buf, offset, Math.min(intRet, Math.min(len, size - 4)));
            return intRet;
        }

        public int getMpWriteFileReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            dis.readFully(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            dis.readFully(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            return intRet;
        }

        public int getMpFileCountReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            dis.readFully(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            dis.readFully(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet != 0) {
                throw new MotoPlusConnectionException("mpRefreshFileList returned " + intRet);
            }
            if (size < 8) {
                throw new MotoPlusConnectionException("mpGetFileCount size = " + size + ": 8 bytes needed");
            }
            return bb.getInt(4);
        }

        public String getMpFileNameReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            dis.readFully(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            dis.readFully(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet != 0) {
                throw new MotoPlusConnectionException("mpRefreshFileList returned " + intRet);
            }
            if (size < 10) {
                throw new MotoPlusConnectionException("mpGetFileCount size = " + size + ": 10 bytes needed");
            }
            intRet = bb.getInt(4);
            if (intRet != 0) {
                throw new MotoPlusConnectionException("mpGetFileName returned " + intRet);
            }
            return new String(inbuf, 8, size - 9, Charset.forName("US-ASCII"));
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

        public int getIntReturn() throws IOException {
            byte inbuf[] = new byte[4];
            dis.readFully(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            dis.readFully(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            return intRet;
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
            for (int i = 0; i < rData.length && i < (sz - 4) / 4; i++) {
                rData[i] = bb.getInt(4 + (i * 4));
            }
            return intRet == 0;
        }

        public boolean getSysReadIOReturn(short iorData[]) throws IOException {
            byte inbuf[] = new byte[4];
            dis.readFully(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            dis.readFully(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            for (int i = 0; i < iorData.length && i < (sz - 4) / 2; i++) {
                iorData[i] = bb.getShort(4 + (i * 2));
            }
            return intRet == 0;
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

        public MP_MODE_DATA getModeReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            dis.readFully(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            dis.readFully(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet != 0) {
                throw new MotoPlusConnectionException("mpGetMode returned " + intRet);
            }
            short sMode = bb.getShort(4);
            MP_MODE_DATA data = new MP_MODE_DATA();
            data.mode = ModeEnum.fromId(sMode);
            data.sRemote = bb.getShort(6);
            return data;
        }

        public MP_CYCLE_DATA getCycleReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            dis.readFully(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            dis.readFully(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet != 0) {
                throw new MotoPlusConnectionException("mpGetCycle returned " + intRet);
            }
            MP_CYCLE_DATA data = new MP_CYCLE_DATA();
            data.cycle = CycleEnum.fromId(bb.getShort(4));
            return data;
        }

        public MP_ALARM_STATUS_DATA getAlarmStatusReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            dis.readFully(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            dis.readFully(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet != 0) {
                throw new MotoPlusConnectionException("mpGetAlarmStatus returned " + intRet);
            }
            MP_ALARM_STATUS_DATA data = new MP_ALARM_STATUS_DATA();
            data.sIsAlarm = bb.getShort(4);
            return data;
        }

        public MP_ALARM_CODE_DATA getAlarmCodeReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            dis.readFully(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            dis.readFully(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet != 0) {
                throw new MotoPlusConnectionException("mpGetAlarmCode returned " + intRet);
            }
            MP_ALARM_CODE_DATA data = new MP_ALARM_CODE_DATA();
            data.usErrorNo = bb.getShort(4);
            data.usErrorData = bb.getShort(6);
            data.usAlarmNum = bb.getShort(8);
            for (int i = 0; i < data.usAlarmNum && i < 4; i++) {
                data.AlarmData.usAlarmNo[i] = bb.getShort(10 + i * 4);
                data.AlarmData.usAlarmData[i] = bb.getShort(12 + i * 4);
            }
            return data;
        }

    };

    private final Starter starter = new Starter();

    public Starter getStarter() {
        return starter;
    }

    private final Returner returner = new Returner();

    public Returner getReturner() {
        return returner;
    }

    public MotCtrlReturnEnum mpMotStart(int options) throws IOException {
        starter.startMpMotStart(options);
        return returner.getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotStop(int options) throws IOException {
        starter.startMpMotStop(options);
        return returner.getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotTargetClear(int grp, int options) throws IOException {
        starter.startMpMotTargetClear(grp, options);
        return returner.getMpMotStandardReturn();
    }

    private void checkTimeout(int timeout) {
        if (timeout != NO_WAIT) {
            if (timeout < 0 || timeout > 5000) {
                throw new IllegalArgumentException("timeout must be finite and less than 5000 ms: timeout = " + timeout);
            }
        }
    }

    public MotCtrlReturnEnum mpMotTargetJointSend(int grp, JointTarget target, int timeout) throws IOException {
        checkTimeout(timeout);
        starter.startMpMotTargetJointSend(grp, target, timeout);
        return returner.getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotTargetCoordSend(int grp, CoordTarget target, int timeout) throws IOException {
        checkTimeout(timeout);
        starter.startMpMotTargetCoordSend(grp, target, timeout);
        return returner.getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotTargetReceive(int grpNo, int id, int[] recvId, int timeout, int options) throws IOException {
        checkTimeout(timeout);
        starter.startMpMotTargetReceive(grpNo, id, recvId, timeout, options);
        return returner.getMpMotTargetReceiveReturn(recvId);
    }

    public MotCtrlReturnEnum mpMotSetCoord(int grpNo, MP_COORD_TYPE type, int aux) throws IOException {
        starter.startMpMotSetCoord(grpNo, type, aux);
        return returner.getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotSetTool(int grpNo, int toolNo) throws IOException {
        starter.startMpMotSetTool(grpNo, toolNo);
        return returner.getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotSetSpeed(int grpNo, MP_SPEED spd) throws IOException {
        starter.startMpMotSetSpeed(grpNo, spd);
        return returner.getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotSetOrigin(int grpNo, int options) throws IOException {
        starter.startMpMotSetOrigin(grpNo, options);
        return returner.getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotSetTask(int grpNo, int taskNo) throws IOException {
        starter.startMpMotSetTask(grpNo, taskNo);
        return returner.getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotSetSync(int grpNo, int aux, int options) throws IOException {
        starter.startMpMotSetSync(grpNo, aux, options);
        return returner.getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotResetSync(int grpNo) throws IOException {
        starter.startMpMotSetSync(grpNo);
        return returner.getMpMotStandardReturn();
    }

    public boolean mpPutVarData(MP_VAR_DATA[] sData, int num) throws IOException {
        starter.startMpPutVarData(sData, num);
        return returner.getSysOkReturn();
    }

    public boolean mpWriteIO(MP_IO_DATA[] sData, int num) throws IOException {
        starter.startMpWriteIO(sData, num);
        return returner.getSysOkReturn();
    }

    public boolean mpGetVarData(MP_VAR_INFO[] sData, long[] rData, int num) throws IOException {
        starter.startMpGetVarData(sData, rData, num);
        return returner.getSysDataReturn(rData);
    }

    public boolean mpReadIO(MP_IO_INFO[] sData, short[] iorData, int num) throws IOException {
        starter.startMpReadIO(sData, iorData, num);
        return returner.getSysReadIOReturn(iorData);
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
        starter.startMpGetCartPos(ctrlGroup, data);
        return returner.getCartPosReturn(data);
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
        starter.startMpGetPulsePos(ctrlGroup, data);
        return returner.getPulsePosReturn(data);
    }

    public boolean mpGetFBPulsePos(int ctrlGroup, MP_FB_PULSE_POS_RSP_DATA[] data) throws IOException {
        starter.startMpGetFBPulsePos(ctrlGroup, data);
        return returner.getFBPulsePosReturn(data);
    }

    public boolean mpGetDegPosEx(int ctrlGroup, MP_DEG_POS_RSP_DATA_EX[] data) throws IOException {
        starter.startMpGetDegPosEx(ctrlGroup, data);
        return returner.getDegPosExPosReturn(data);
    }

    public static final class MotoPlusConnectionException extends Exception {

        public MotoPlusConnectionException(String message) {
            super(message);
        }

    }

    public boolean mpGetServoPower() throws IOException, MotoPlusConnectionException {
        starter.startMpGetServoPower();
        return returner.getServoPowerReturn();
    }

    public boolean mpSetServoPower(boolean on) throws IOException, MotoPlusConnectionException {
        starter.startMpSetServoPower(on);
        return returner.getSetServoPowerReturn();
    }

    public MP_MODE_DATA mpGetMode() throws IOException, MotoPlusConnectionException {
        starter.startMpGetMode();
        return returner.getModeReturn();
    }

    public MP_CYCLE_DATA mpGetCycle() throws IOException, MotoPlusConnectionException {
        starter.startMpGetCycle();
        return returner.getCycleReturn();
    }

    public MP_ALARM_STATUS_DATA mpGetAlarmStatus() throws IOException, MotoPlusConnectionException {
        starter.startMpGetAlarmStatus();
        return returner.getAlarmStatusReturn();
    }

    public MP_ALARM_CODE_DATA mpGetAlarmCode() throws IOException, MotoPlusConnectionException {
        starter.startMpGetAlarmCode();
        return returner.getAlarmCodeReturn();
    }

    public int mpGetRtc() throws IOException, MotoPlusConnectionException {
        starter.startMpGetRtc();
        return returner.getIntReturn();
    }

    public int getMpFileCount(MpExtensionType ext) throws IOException, MotoPlusConnectionException {
        starter.startMpGetFileCount(ext);
        return returner.getMpFileCountReturn();
    }

    public String getMpFileName(MpExtensionType ext, int index) throws IOException, MotoPlusConnectionException {
        starter.startMpGetFileName(ext, index);
        return returner.getMpFileNameReturn();
    }

    public int mpOpenFile(String filename, MpFileFlagsEnum flags, int mode) throws IOException, MotoPlusConnectionException {
        starter.startMpOpenFile(filename, flags.getId(), mode);
        return returner.getMpOpenFileReturn();
    }

    public int mpCreateFile(String filename, MpFileFlagsEnum flags) throws IOException, MotoPlusConnectionException {
        starter.startMpCreateFile(filename, flags.getId());
        return returner.getMpCreateFileReturn();
    }

    public int mpCloseFile(int fd) throws IOException, MotoPlusConnectionException {
        starter.startMpCloseFile(fd);
        return returner.getMpCloseFileReturn();
    }

    public int mpReadFileEx(int fd, byte buf[], int offset, int len) throws IOException, MotoPlusConnectionException {
        if (null == buf || len <= 0 || offset < 0 || buf.length < offset + len) {
            throw new IllegalArgumentException("Buf must not be null and have length of atleast offset = " + offset + " + len = " + len);
        }
        if (len > MAX_READ_LEN) {
            throw new IllegalArgumentException("len = " + len + "   must not be greater than MAX_READ_LEN = " + MAX_READ_LEN);
        }
        starter.startMpReadFile(fd, (len - offset));
        return returner.getMpReadFileReturn(buf, offset, len);
    }

    public int mpReadFile(int fd, byte buf[]) throws IOException, MotoPlusConnectionException {
        return mpReadFileEx(fd, buf, 0, buf.length);
    }

    private static class ReadBlock {

        public ReadBlock(int r, byte[] buf) {
            this.r = r;
            this.buf = buf;
        }

        final int r;
        final byte buf[];

        @Override
        public String toString() {
            return "ReadBlock{" + "r=" + r + ", buf=" + new String(buf) + '}';
        }
    };

    public byte[] readFullFile(int fd) throws IOException, MotoPlusConnectionException {
        int r = MAX_READ_LEN;
        int sum = 0;
        ArrayList<ReadBlock> l = new ArrayList<>();
        while (r == MAX_READ_LEN) {
            byte buf[] = new byte[MAX_READ_LEN];
            r = mpReadFile(fd, buf);
            if (r > 0 && r <= buf.length) {
                l.add(new ReadBlock(r, buf));
                sum += r;
            }
        }
        byte ret[] = new byte[sum];
        int s = 0;
        for (ReadBlock rb : l) {
            System.arraycopy(rb.buf, 0, ret, s, rb.r);
            s += rb.r;
        }
        return ret;
    }

    public boolean openGripper() throws IOException {
        MP_IO_DATA ioData[] = new MP_IO_DATA[3];
        ioData[0] = new MP_IO_DATA();
        ioData[0] = new MP_IO_DATA();
        ioData[0].ulAddr = 10010;
        ioData[0].ulValue = 1;
        ioData[1] = new MP_IO_DATA();
        ioData[1].ulAddr = 10011;
        ioData[1].ulValue = 0;
        ioData[2] = new MP_IO_DATA();
        ioData[2].ulAddr = 10012;
        ioData[2].ulValue = 1;
        return mpWriteIO(ioData, 3);
    }

    public boolean closeGripper() throws IOException {
        MP_IO_DATA ioData[] = new MP_IO_DATA[3];
        ioData[0] = new MP_IO_DATA();
        ioData[0] = new MP_IO_DATA();
        ioData[0].ulAddr = 10010;
        ioData[0].ulValue = 0;
        ioData[1] = new MP_IO_DATA();
        ioData[1].ulAddr = 10011;
        ioData[1].ulValue = 1;
        ioData[2] = new MP_IO_DATA();
        ioData[2].ulAddr = 10012;
        ioData[2].ulValue = 1;
        return mpWriteIO(ioData, 3);
    }

    public byte[] readFullFileByName(String fname) throws IOException, MotoPlusConnectionException {
        int fd = mpOpenFile(fname, MpFileFlagsEnum.O_RDWR, 0666);
        if (fd < 0) {
            throw new MotoPlusConnectionException("mpOpenFile(" + fname + ") returned " + fd);
        }
        byte ret[] = readFullFile(fd);
        mpCloseFile(fd);
        return ret;
    }

    public String readFullFileByNameToString(String fname) throws IOException, MotoPlusConnectionException {
        return new String(readFullFileByName(fname), Charset.forName("US-ASCII"));
    }

    public void downloadJobData(String jobName, File localFile) throws IOException, MotoPlusConnectionException {
        if (jobName.endsWith(".JBR") || jobName.endsWith(".JBI")) {
            jobName = jobName.substring(0, jobName.length() - 4);
        }
        String remoteFileName = "MPRAM1:0/" + jobName + ".JBR";
        int fd0 = -1;
        int closeRet = -1;

        System.out.println("Calling  mpc.mpCreateFile(\"" + remoteFileName + "\",MpFileFlagsEnum.O_RDWR); ");

        fd0 = mpCreateFile(remoteFileName, MpFileFlagsEnum.O_RDWR);
        System.out.println("fd0 = " + fd0);

        int fdReadRet = mpFdReadFile(fd0, jobName + ".JBR");
        System.out.println("fdReadRet = " + fdReadRet);

        closeRet = mpCloseFile(fd0);
        System.out.println("closeRet = " + closeRet);
        downloadFile(remoteFileName, localFile);
    }

    public void downloadFile(String remoteFileName, File localFile) throws IOException, MotoPlusConnectionException {
        try (FileOutputStream fos = new FileOutputStream(localFile)) {
            int fd = mpOpenFile(remoteFileName, MpFileFlagsEnum.O_RDWR, 0666);
            if (fd < 0) {
                throw new MotoPlusConnectionException("mpOpenFile(" + remoteFileName + ") returned " + fd);
            }
            int r = MAX_READ_LEN;
            int sum = 0;
//            ArrayList<ReadBlock> l = new ArrayList<>();
            while (r == MAX_READ_LEN) {
                byte buf[] = new byte[MAX_READ_LEN];
                r = mpReadFile(fd, buf);

                if (r > 0 && r <= buf.length) {
                    fos.write(buf);
//                    l.add(new ReadBlock(r, buf));
//                    sum += r;
                }
            }
//            byte ret[] = new byte[sum];
//            int s = 0;
//            for (ReadBlock rb : l) {
//                System.arraycopy(rb.buf, 0, ret, s, rb.r);
//                s += rb.r;
//            }
            mpCloseFile(fd);
        }
    }
    public static final int MAX_WRITE_LEN = 1024 - 10;
    public static final int MAX_READ_LEN = 1024 - 10;

    public int mpWriteFileEx(int fd, byte buf[], int offset, int len) throws IOException, MotoPlusConnectionException {
        if (null == buf || len <= 0 || offset < 0 || buf.length < offset + len) {
            throw new IllegalArgumentException("Buf must not be null and have length of atleast offset = " + offset + " + len = " + len);
        }
        if (len > MAX_WRITE_LEN) {
            throw new IllegalArgumentException("len = " + len + "  must not be greater  than MAX_WRITE_LEN = " + MAX_WRITE_LEN);
        }
        starter.startMpWriteFile(fd, buf, offset, len);
        return returner.getMpWriteFileReturn();
    }

    public int mpWriteFile(int fd, byte buf[]) throws IOException, MotoPlusConnectionException {
        return mpWriteFileEx(fd, buf, 0, buf.length);
    }

    public int mpFdGetJobList(int fd, MP_GET_JOBLIST_RSP_DATA jlistData) throws IOException, MotoPlusConnectionException {
        starter.startMpFdGetJobList(fd, jlistData);
        return returner.getMpFdGetJobListReturn(jlistData);
    }

    public int mpFdReadFile(int fd, String name) throws IOException, MotoPlusConnectionException {
        starter.startMpFdReadFile(fd, name);
        return returner.getMpFdReadFileReturn();
    }

    public int mpFdWriteFile(int fd, String name) throws IOException, MotoPlusConnectionException {
        starter.startMpFdWriteFile(fd, name);
        return returner.getMpFdWriteFileReturn();
    }

    public int mpLoadFile(MpExFileRamIdEnum id, String name, String name2) throws IOException, MotoPlusConnectionException {
        starter.startMpLoadFile(id.getId(), name, name2);
        return returner.getMpLoadFileReturn();
    }

    public int mpSaveFile(MpExFileRamIdEnum id, String name, String name2) throws IOException, MotoPlusConnectionException {
        starter.startMpSaveFile(id.getId(), name, name2);
        return returner.getMpSaveFileReturn();
    }

}
