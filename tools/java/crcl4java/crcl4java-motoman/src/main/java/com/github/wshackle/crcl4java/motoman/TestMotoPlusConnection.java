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

import com.github.wshackle.crcl4java.motoman.motctrl.COORD_POS;
import com.github.wshackle.crcl4java.motoman.motctrl.CoordTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.JointTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_COORD_TYPE;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_INTP_TYPE;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_SPEED;
import com.github.wshackle.crcl4java.motoman.motctrl.MotCtrlReturnEnum;
import com.github.wshackle.crcl4java.motoman.sys1.MP_CART_POS_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_PULSE_POS_RSP_DATA;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class TestMotoPlusConnection {

    private static String host = "10.0.0.2";
//    private static String host = "localhost";

    public static void main(String[] args) throws Exception {
        System.out.println("host = " + host);
        try (MotoPlusConnection mpc = new MotoPlusConnection(new Socket(host, 11000))) {

//            testMoveZ(mpc);
            testMoveJointS(mpc);
            if (true) {
                return;
            }
//            mpc.downloadJobData("MOVELGEAR", new File(System.getProperty("user.home"), "MOVELGEAR.JBR"));
//            if (true) {
//                return;
//            }
//            int jbiCount = mpc.getMpFileCount(MpExtensionType.MP_EXT_ID_JBI);
//            System.out.println("jbiCount = " + jbiCount);
//            for (int i = 0; i < jbiCount; i++) {
//                String name = mpc.getMpFileName(MpExtensionType.MP_EXT_ID_JBI, i);
//                System.out.println("name = " + name);
//            }
//            int jbrCount = mpc.getMpFileCount(MpExtensionType.MP_EXT_ID_JBR);
//            System.out.println("jbrCount = " + jbrCount);
//            for (int i = 0; i < jbrCount; i++) {
//                String name = mpc.getMpFileName(MpExtensionType.MP_EXT_ID_JBR, i);
//                System.out.println("name = " + name);
//            }
//
//            String fname = "MPRAM1:0/MOVELGEAR.JBR";
////            String contents = mpc.readFullFileByNameToString(fname);
////            System.out.println("contents = " + contents);
////            if (true) {
////                return;
////            }
//            int fd0 = -1;
//            int closeRet = -1;
//
//            System.out.println("Calling  mpc.mpCreateFile(\"" + fname + "\",MpFileFlagsEnum.O_RDWR); ");
//
//            fd0 = mpc.mpCreateFile(fname, MpFileFlagsEnum.O_RDWR);
//            System.out.println("fd0 = " + fd0);
//
//            int fdReadRet = mpc.mpFdReadFile(fd0, "MOVELGEAR.JBR");
//            System.out.println("fdReadRet = " + fdReadRet);
//
//            closeRet = mpc.mpCloseFile(fd0);
//            System.out.println("closeRet = " + closeRet);
//            String fileStr = mpc.readFullFileByNameToString(fname);
//            System.out.println("fileStr = " + fileStr);
////            fd0 = mpc.mpOpenFile(fname, MpFileFlagsEnum.O_RDWR, 0666);
////            System.out.println("fd0 = " + fd0);
////
////            byte buf[] = new byte[MotoPlusConnection.MAX_READ_LEN];
////            int r = buf.length;
////            while (r == buf.length) {
////                r = mpc.mpReadFile(fd0, buf);
////                System.out.println("r = " + r);
////                if(r > 0 && r <= buf.length) {
////                    System.out.println("Start buf:\n\n" + new String(buf,0,r, Charset.forName("US-ASCII"))+"\nend buf:\n");
////                }
////            }
//
////            closeRet = mpc.mpCloseFile(fd0);
////            System.out.println("closeRet = " + closeRet);
//            if (true) {
//                return;
//            }
//            int fdWriteRet = mpc.mpFdWriteFile(3, "anotherfile");
//            System.out.println("fdWriteRet = " + fdWriteRet);
//
//            MP_GET_JOBLIST_RSP_DATA jlistData = new MP_GET_JOBLIST_RSP_DATA();
//            int ret = mpc.mpFdGetJobList(3, jlistData);
//            System.out.println("jlistData = " + jlistData);
//            if (true) {
//                return;
//            }
//
//            System.out.println("Calling  mpc.mpCreateFile(\"test.txt\",MpFileFlagsEnum.O_RDWR); ");
//            fd0 = mpc.mpCreateFile("C:\\Users\\shackle\\Documents\\test.txt", MpFileFlagsEnum.O_RDWR);
//            System.out.println("fd0 = " + fd0);
//            int writeRet = mpc.mpWriteFile(fd0, "test text 2".getBytes());
//            System.out.println("writeRet = " + writeRet);
//            closeRet = mpc.mpCloseFile(fd0);
//            System.out.println("closeRet = " + closeRet);
//
//            System.out.println("Calling  mpc.mpOpenFile(\"C:\\Users\\shackle\\Documents\\test.txt\",MpFileFlagsEnum.O_RDWR); ");
//            int fd1 = mpc.mpOpenFile("C:\\Users\\shackle\\Documents\\test.txt", MpFileFlagsEnum.O_RDWR, 0);
//            System.out.println("fd1 = " + fd1);
//            byte buf[] = new byte[40];
//            int readRet = mpc.mpReadFile(fd1, buf);
//            System.out.println("readRet = " + readRet);
//            System.out.println("buf = " + new String(buf, Charset.forName("US-ASCII")));
//            closeRet = mpc.mpCloseFile(fd1);
//            System.out.println("closeRet = " + closeRet);
//            if (true) {
//                return;
//            }
//
//            jbiCount = mpc.getMpFileCount(MpExtensionType.MP_EXT_ID_JBI);
//            System.out.println("jbiCount = " + jbiCount);
//            for (int i = 0; i < jbiCount; i++) {
//                String name = mpc.getMpFileName(MpExtensionType.MP_EXT_ID_JBI, i);
//                System.out.println("name = " + name);
//            }
//            jbrCount = mpc.getMpFileCount(MpExtensionType.MP_EXT_ID_JBR);
//            System.out.println("jbrCount = " + jbrCount);
//            for (int i = 0; i < jbrCount; i++) {
//                String name = mpc.getMpFileName(MpExtensionType.MP_EXT_ID_JBR, i);
//                System.out.println("name = " + name);
//            }
//
//            MP_MODE_DATA modeData = mpc.mpGetMode();
//            System.out.println("modeData = " + modeData);
//
//            MP_CYCLE_DATA cycleData = mpc.mpGetCycle();
//            System.out.println("cycleData = " + cycleData);
//
//            MP_ALARM_STATUS_DATA alarmStatusData = mpc.mpGetAlarmStatus();
//            System.out.println("alarmStatusData = " + alarmStatusData);
//
//            MP_ALARM_CODE_DATA alarmCodeData = mpc.mpGetAlarmCode();
//            System.out.println("alarmCodeData = " + alarmCodeData);
//
//            MP_IO_INFO ioInfo[] = new MP_IO_INFO[8];
//            for (int i = 0; i < ioInfo.length; i++) {
//                ioInfo[i] = new MP_IO_INFO();
//                ioInfo[i].ulAddr = 10 + i;
//            }
//            MP_IO_DATA ioData[] = new MP_IO_DATA[2];
//            ioData[0] = new MP_IO_DATA();
//
//            // Close
//            ioData[0].ulAddr = 10010;
//            ioData[0].ulValue = 0;
//            ioData[1] = new MP_IO_DATA();
//            ioData[1].ulAddr = 10011;
//            ioData[1].ulValue = 1;
////            
////            // Open
////            ioData[0] = new MP_IO_DATA();
////            ioData[0].ulAddr = 10010;
////            ioData[0].ulValue = 1;
////            ioData[1] = new MP_IO_DATA();
////            ioData[1].ulAddr = 10011;
////            ioData[1].ulValue = 0;
//            boolean mpWriteIoRet = mpc.mpWriteIO(ioData, 2);
//            System.out.println("mpWriteIoRet = " + mpWriteIoRet);
//            boolean t = true;
//            while (t) {
//                short iorData[] = new short[ioInfo.length];
//                boolean readIORet = mpc.mpReadIO(ioInfo, iorData, ioInfo.length);
//                System.out.println("readIORet = " + readIORet);
////            System.out.println("iorData = " + Arrays.toString(iorData));
//                for (int i = 0; i < iorData.length / 2; i++) {
//                    short tmp = iorData[i];
//                    iorData[i] = iorData[iorData.length - i - 1];
//                    iorData[iorData.length - i - 1] = tmp;
//                }
//                System.out.println("reversed iorData = " + Arrays.toString(iorData));
//                System.out.printf("iorData[0] = %x\n", iorData[0]);
//                Thread.sleep(100);
//            }
//            System.out.println("Calling mpGetServoPower()");
//            boolean on = mpc.mpGetServoPower();
//            System.out.println("on = " + on);
//            if (true) {
//                return;
//            }
//            System.out.println("Calling mpMotStop(0)");
//            MotCtrlReturnEnum motStopRet = mpc.mpMotStop(0);
//            System.out.println("motStopRet = " + motStopRet);
//
//            System.out.println("Calling mpMotTargetClear(1,0)");
//            MotCtrlReturnEnum motTargetClearRet = mpc.mpMotTargetClear(1, 0);
//            System.out.println("motTargetClearRet = " + motTargetClearRet);
//
//            System.out.println("Calling mpSetServoPower(true)");
//            mpc.mpSetServoPower(true);
//            System.out.println("Calling mpGetServoPower()");
//            on = mpc.mpGetServoPower();
//            System.out.println("on = " + on);
//
//            System.out.println("Calling mpMotSetCoord(1, MP_COORD_TYPE.MP_PULSE_TYPE, 0)");
//            MotCtrlReturnEnum motSetCoordRet = mpc.mpMotSetCoord(0, MP_COORD_TYPE.MP_PULSE_TYPE, 0);
//            System.out.println("motSetCoordRet = " + motSetCoordRet);
//            MP_SPEED spd = new MP_SPEED();
//            spd.vj = (int) 300;
//            System.out.println("Calling mpMotSetSpeed(1,...)");
//            mpc.mpMotSetSpeed(1, spd);
////            JointTarget jointTarget = new JointTarget();
////            jointTarget.setId(15);
////            jointTarget.setIntp(MP_INTP_TYPE.MP_MOVJ_TYPE);
////            int jp[] = new int[]{-3348, 9564, -74224, 3640, -112923, 3209, -5, 0};
////            System.arraycopy(jp, 0, jointTarget.getDst(), 0, jointTarget.getDst().length);
////            System.arraycopy(jp, 0, jointTarget.getAux(), 0, jointTarget.getAux().length);
////            System.out.println("jointTarget = " + jointTarget);
////            System.out.println("Calling mpMotTargetJointSend(0,(...),0)\n");
////            MotCtrlReturnEnum motTargetJointRet = mpc.mpMotTargetJointSend(1, jointTarget, 0);
////            System.out.println("motTargetJointRet = " + motTargetJointRet);
////            Thread.sleep(200);
////
////            System.out.println("Calling mpMotStart(0)");
////            motTargetClearRet = mpc.mpMotStart(0);
////            System.out.println("motStartRet = " + motTargetClearRet);
//            Thread.sleep(200);
//            int recvId[] = new int[1];
////            System.out.println("Calling mpMotTargetReceive(0,5,...,WAIT_FOREVER,0)");
////            mpc.mpMotTargetReceive(0, 15, recvId, WAIT_FOREVER, 0);
////            System.out.println("recvId = " + Arrays.toString(recvId));
//
//            System.out.println("Calling mpMotStop(0)");
//            motTargetClearRet = mpc.mpMotStop(0);
//            System.out.println("motStartRet = " + motTargetClearRet);
////            
////            CoordTarget coordTarget = new CoordTarget();
////            coordTarget.setId(36);
////            coordTarget.setIntp(MP_INTP_TYPE.MP_MOVL_TYPE);
////            coordTarget.getDst().x = 40;
////            coordTarget.getDst().y = 41;
////            coordTarget.getDst().z = 42;
////            coordTarget.getDst().rx = 43;
////            coordTarget.getDst().ry = 44;
////            coordTarget.getDst().rz = 45;
////            coordTarget.getDst().ex1 = 46;
////            coordTarget.getDst().ex2 = 47;
////            
////            coordTarget.getAux().x = 50;
////            coordTarget.getAux().y = 51;
////            coordTarget.getAux().z = 52;
////            coordTarget.getAux().rx = 53;
////            coordTarget.getAux().ry = 54;
////            coordTarget.getAux().rz = 55;
////            coordTarget.getAux().ex1 = 56;
////            coordTarget.getAux().ex2 = 57;
////            System.out.println("coordTarget = " + coordTarget);
////            System.out.println("Calling mpMotTargetJointSend(63,(...),65)");
////            MotCtrlReturnEnum motTargetCoordRet = mpc.mpMotTargetCoordSend(63, coordTarget, 65);
////            System.out.println("motTargetCoordRet = " + motTargetCoordRet);
////            Thread.sleep(200);
////            MP_VAR_INFO sData[] = new MP_VAR_INFO[1];
////            sData[0] = new MP_VAR_INFO();
////            sData[0].usType = VarType.MP_RESTYPE_VAR_I;
////            sData[0].usIndex = 1;
////            long []rData = new long[1];
////            System.out.println("Calling mpGetVarData(,,1)");
////            boolean getVarRet = mpc.mpGetVarData(sData, rData, 1);
////            System.out.println("getVarRet = " + getVarRet);
////            System.out.println("rData[0] = " + rData[0]);
////            varData[0] = new MP_VAR_DATA();
////            varData[0].usType = VarType.MP_RESTYPE_VAR_I;
////            varData[0].usIndex = 1;
////            varData[0].ulValue = 77;
////            System.out.println("Calling mpPutVarData(,,1)");
////            boolean putVarRet = mpc.mpPutVarData(varData, 1);
////            System.out.println("putVarRet = " + putVarRet);
//
//            MP_FB_PULSE_POS_RSP_DATA fbPulseData[] = new MP_FB_PULSE_POS_RSP_DATA[1];
//            fbPulseData[0] = new MP_FB_PULSE_POS_RSP_DATA();
//            System.out.println("Calling mpGetFBPulsePos(0,...)");
//            boolean getFBPulsePosRet = mpc.mpGetFBPulsePos(0, fbPulseData);
//            System.out.println("getFBPulsePosRet = " + getFBPulsePosRet);
//            System.out.println("fbPulseData[0] = " + fbPulseData[0]);
//
//            MP_CART_POS_RSP_DATA cartData[] = new MP_CART_POS_RSP_DATA[1];
//            cartData[0] = new MP_CART_POS_RSP_DATA();
//            System.out.println("Calling mpGetCartPos(0,...)");
//            boolean getCartPosRet = mpc.mpGetCartPos(0, cartData);
//            System.out.println("getCartPosRet = " + getCartPosRet);
//            System.out.println("cartData[0] = " + cartData[0]);
//
//            MP_PULSE_POS_RSP_DATA pulseData[] = new MP_PULSE_POS_RSP_DATA[1];
//            pulseData[0] = new MP_PULSE_POS_RSP_DATA();
//            System.out.println("Calling mpGetPulsePos(0,...)");
//            boolean getPulsePosRet = mpc.mpGetPulsePos(0, pulseData);
//            System.out.println("getPulsePosRet = " + getPulsePosRet);
//            System.out.println("pulseData[0] = " + pulseData[0]);
//
//            MP_DEG_POS_RSP_DATA_EX degData[] = new MP_DEG_POS_RSP_DATA_EX[1];
//            degData[0] = new MP_DEG_POS_RSP_DATA_EX();
//            System.out.println("Calling mpGetDegPosEx(0,...)");
//            boolean geDegPosExRet = mpc.mpGetDegPosEx(0, degData);
//            System.out.println("geDegPosExRet = " + geDegPosExRet);
//            System.out.println("degData[0] = " + degData[0]);
//
//            System.out.println("Calling mpMotSetCoord(1, MP_COORD_TYPE.MP_ROBOT_TYPE, 0)");
//            motSetCoordRet = mpc.mpMotSetCoord(0, MP_COORD_TYPE.MP_ROBOT_TYPE, 0);
//            System.out.println("motSetCoordRet = " + motSetCoordRet);
//
//            CoordTarget coordTarget = new CoordTarget();
//            coordTarget.setId(16);
//            coordTarget.setIntp(MP_INTP_TYPE.MP_MOVL_TYPE);
//            COORD_POS cp = coordTarget.getDst();
//            cp.x = 492295;
//            cp.y = -52139;
//            cp.z = 106148;
//            cp.rx = 1768859;
//            cp.ry = 3795;
//            cp.rz = 17925;
//            cp.ex1 = 0;
//            cp.ex2 = 0;
//            cp = coordTarget.getAux();
//            cp.x = 492295;
//            cp.y = -52139;
//            cp.z = 106148;
//            cp.rx = 1768859;
//            cp.ry = 3795;
//            cp.rz = 17925;
//            cp.ex1 = 0;
//            cp.ex2 = 0;
//
//            System.out.println("coordTarget = " + coordTarget);
//            System.out.println("Calling mpMotTargetCoordSend(0,(...),0)\n");
//            MotCtrlReturnEnum motTargetCoordRet = mpc.mpMotTargetCoordSend(1, coordTarget, 0);
//            System.out.println("motTargetCoordRet = " + motTargetCoordRet);
//            Thread.sleep(200);
//
//            System.out.println("Calling mpMotStart(0)");
//            motTargetClearRet = mpc.mpMotStart(0);
//            System.out.println("motStartRet = " + motTargetClearRet);
//            Thread.sleep(200);
//
//            recvId = new int[1];
//            System.out.println("Calling mpMotTargetReceive(0,6,...,WAIT_FOREVER,0)");
//            mpc.mpMotTargetReceive(0, 16, recvId, WAIT_FOREVER, 0);
//            System.out.println("recvId = " + Arrays.toString(recvId));
////            
//
//            System.out.println("Calling mpMotStop(0)");
//            motTargetClearRet = mpc.mpMotStop(0);
//            System.out.println("motStartRet = " + motTargetClearRet);
//            Thread.sleep(200);
//            System.out.println("Calling mpSetServoPower(false)");
//            mpc.mpSetServoPower(false);
//            System.out.println("Calling mpGetServoPower()");
//            on = mpc.mpGetServoPower();
//            System.out.println("on = " + on);
        }
    }

    private static void testMoveZ(final MotoPlusConnection mpc) throws InterruptedException, IOException, MotoPlusConnection.MotoPlusConnectionException {
        System.out.println("Calling mpMotStop(0)");
        MotCtrlReturnEnum motStopRet = mpc.mpMotStop(0);
        System.out.println("motStopRet = " + motStopRet);

        System.out.println("Calling mpMotTargetClear(1,0)");
        MotCtrlReturnEnum motTargetClearRet = mpc.mpMotTargetClear(1, 0);
        System.out.println("motTargetClearRet = " + motTargetClearRet);

        System.out.println("Calling mpSetServoPower(true)");
        mpc.mpSetServoPower(true);
        System.out.println("Calling mpGetServoPower()");
        boolean on = mpc.mpGetServoPower();
        System.out.println("on = " + on);

        System.out.println("Calling mpMotSetCoord(1, MP_COORD_TYPE.MP_ROBOT_TYPE, 0)");
        MotCtrlReturnEnum motSetCoordRet = mpc.mpMotSetCoord(0, MP_COORD_TYPE.MP_ROBOT_TYPE, 0);
        System.out.println("motSetCoordRet = " + motSetCoordRet);
        MP_SPEED spd = new MP_SPEED();
        spd.v = 200;
        System.out.println("Calling mpMotSetSpeed(0," + spd + ")");
        mpc.mpMotSetSpeed(0, spd);
        MP_CART_POS_RSP_DATA cartResData[] = new MP_CART_POS_RSP_DATA[1];
        cartResData[0] = new MP_CART_POS_RSP_DATA();
        System.out.println("Calling mpGetCartPos(0,...)");
        mpc.mpGetCartPos(0, cartResData);
        System.out.println("cartResData = " + Arrays.toString(cartResData));
        CoordTarget coordTarget = new CoordTarget();
        coordTarget.setId(16);
        coordTarget.setIntp(MP_INTP_TYPE.MP_MOVL_TYPE);
        COORD_POS cp = coordTarget.getDst();
        cp.x = (int) cartResData[0].lx();
        cp.y = (int) cartResData[0].ly();
        cp.z = (int) cartResData[0].lz() + 200000;
        cp.rx = (int) cartResData[0].lrx();
        cp.ry = (int) cartResData[0].lry();
        cp.rz = (int) cartResData[0].lrz();
        cp = coordTarget.getAux();
        cp.x = (int) cartResData[0].lx();
        cp.y = (int) cartResData[0].ly();
        cp.z = (int) cartResData[0].lz() + 200000;
        cp.rx = (int) cartResData[0].lrx();
        cp.ry = (int) cartResData[0].lry();
        cp.rz = (int) cartResData[0].lrz();

        System.out.println("coordTarget = " + coordTarget);
        System.out.println("Calling mpMotTargetCoordSend(0,(...),0)\n");
        MotCtrlReturnEnum motTargetCoordRet = mpc.mpMotTargetCoordSend(1, coordTarget, 0);
        System.out.println("motTargetCoordRet = " + motTargetCoordRet);
        Thread.sleep(2000);

        System.out.println("Calling mpMotStart(0)");
        motTargetClearRet = mpc.mpMotStart(0);
        System.out.println("motStartRet = " + motTargetClearRet);
        Thread.sleep(2000);

        int[] recvId = new int[1];
        final int MAX_WAIT = mpc.mpGetMaxWait();
        System.out.println("MAX_WAIT = " + MAX_WAIT);
        System.out.println("Calling mpMotTargetReceive(0,"+coordTarget.getId()+",...,"+MAX_WAIT+",0)");
        mpc.mpMotTargetReceive(0, coordTarget.getId(), recvId, mpc.mpGetMaxWait(), 0);
        System.out.println("recvId = " + Arrays.toString(recvId));
        Thread.sleep(2000);

        System.out.println("Calling mpMotStop(0)");
        motTargetClearRet = mpc.mpMotStop(0);

        cp = coordTarget.getDst();
        cp.x = (int) cartResData[0].lx();
        cp.y = (int) cartResData[0].ly();
        cp.z = (int) cartResData[0].lz();
        cp.rx = (int) cartResData[0].lrx();
        cp.ry = (int) cartResData[0].lry();
        cp.rz = (int) cartResData[0].lrz();
        cp = coordTarget.getAux();
        cp.x = (int) cartResData[0].lx();
        cp.y = (int) cartResData[0].ly();
        cp.z = (int) cartResData[0].lz();
        cp.rx = (int) cartResData[0].lrx();
        cp.ry = (int) cartResData[0].lry();
        cp.rz = (int) cartResData[0].lrz();
        coordTarget.setId(17);

        System.out.println("coordTarget = " + coordTarget);
        System.out.println("Calling mpMotTargetCoordSend(0,(...),0)\n");
        motTargetCoordRet = mpc.mpMotTargetCoordSend(1, coordTarget, 0);
        System.out.println("motTargetCoordRet = " + motTargetCoordRet);
        Thread.sleep(200);

        System.out.println("Calling mpMotStart(0)");
        motTargetClearRet = mpc.mpMotStart(0);
        System.out.println("motStartRet = " + motTargetClearRet);
        Thread.sleep(200);

        recvId = new int[1];
        System.out.println("Calling mpMotTargetReceive(0,"+coordTarget.getId()+",...,"+MAX_WAIT+",0)");
        mpc.mpMotTargetReceive(0, coordTarget.getId(), recvId, MAX_WAIT, 0);
        System.out.println("recvId = " + Arrays.toString(recvId));
//

        System.out.println("Calling mpMotStop(0)");
        motTargetClearRet = mpc.mpMotStop(0);

        System.out.println("motStartRet = " + motTargetClearRet);
        Thread.sleep(200);
        System.out.println("Calling mpSetServoPower(false)");
        mpc.mpSetServoPower(false);
        System.out.println("Calling mpGetServoPower()");
        on = mpc.mpGetServoPower();
        System.out.println("on = " + on);
    }
    
    private static void testMoveJointS(final MotoPlusConnection mpc) throws InterruptedException, IOException, MotoPlusConnection.MotoPlusConnectionException {
        System.out.println("Calling mpMotStop(0)");
        MotCtrlReturnEnum motStopRet = mpc.mpMotStop(0);
        System.out.println("motStopRet = " + motStopRet);

        System.out.println("Calling mpMotTargetClear(1,0)");
        MotCtrlReturnEnum motTargetClearRet = mpc.mpMotTargetClear(1, 0);
        System.out.println("motTargetClearRet = " + motTargetClearRet);

        System.out.println("Calling mpSetServoPower(true)");
        mpc.mpSetServoPower(true);
        System.out.println("Calling mpGetServoPower()");
        boolean on = mpc.mpGetServoPower();
        System.out.println("on = " + on);

        System.out.println("Calling mpMotSetCoord(1, MP_COORD_TYPE.MP_PULSE_TYPE, 0)");
        MotCtrlReturnEnum motSetCoordRet = mpc.mpMotSetCoord(0, MP_COORD_TYPE.MP_PULSE_TYPE, 0);
        System.out.println("motSetCoordRet = " + motSetCoordRet);
        MP_SPEED spd = new MP_SPEED();
        spd.v = 1;
        spd.vj = 1000;
        spd.vr = 1;
        int grp = 0;
        int dist = 20000;
        
        MP_PULSE_POS_RSP_DATA pulseData[] = new MP_PULSE_POS_RSP_DATA[1];
        pulseData[0] = new MP_PULSE_POS_RSP_DATA();
        System.out.println("Calling mpGetCartPos(0,...)");
        mpc.mpGetPulsePos(0, pulseData);
        System.out.println("pulseData = " + Arrays.toString(pulseData));
        JointTarget jointTarget = new JointTarget();
        jointTarget.setId(26);
        jointTarget.setIntp(MP_INTP_TYPE.MP_MOVJ_TYPE);
        int dst[] = jointTarget.getDst();
        System.arraycopy(pulseData[0].lPos, 0, dst, 0, dst.length);
        int aux[] = jointTarget.getDst();
        System.arraycopy(pulseData[0].lPos, 0, aux, 0, aux.length);
        dst[0] += dist;
        aux[0] += dist;

        System.out.println("jointTarget = " + jointTarget);
        System.out.println("Calling mpMotSetSpeed("+grp+"," + spd + ")");
        mpc.mpMotSetSpeed(grp, spd);
        System.out.println("Calling mpMotTargetJointSend(0,(...),0)\n");
        MotCtrlReturnEnum motTargeJointRet = mpc.mpMotTargetJointSend(1, jointTarget, 0);
        System.out.println("motTargeJointRet = " + motTargeJointRet);
        Thread.sleep(2000);

        System.out.println("Calling mpMotStart(0)");
        motTargetClearRet = mpc.mpMotStart(0);
        System.out.println("motStartRet = " + motTargetClearRet);
        Thread.sleep(2000);

        int[] recvId = new int[1];
        final int MAX_WAIT = mpc.mpGetMaxWait();
        System.out.println("MAX_WAIT = " + MAX_WAIT);
        System.out.println("Calling mpMotTargetReceive(0,"+jointTarget.getId()+",...,MAX_WAIT,0)");
        mpc.mpMotTargetReceive(0, jointTarget.getId(), recvId, MAX_WAIT+1000, 0);
        System.out.println("recvId = " + Arrays.toString(recvId));
        Thread.sleep(2000);

        System.out.println("Calling mpMotStop(0)");
        motTargetClearRet = mpc.mpMotStop(0);

        dst = jointTarget.getDst();
        aux = jointTarget.getAux();
        System.arraycopy(pulseData[0].lPos, 0, dst, 0, dst.length);
        System.arraycopy(pulseData[0].lPos, 0, aux, 0, aux.length);
        jointTarget.setId(27);

        System.out.println("Calling mpMotSetSpeed("+grp+"," + spd + ")");
        mpc.mpMotSetSpeed(grp, spd);
        System.out.println("jointTarget = " + jointTarget);
        System.out.println("Calling mpMotTargetCoordSend(0,(...),0)\n");
        motTargeJointRet = mpc.mpMotTargetJointSend(1, jointTarget, 0);
        System.out.println("motTargeJointRet = " + motTargeJointRet);
        Thread.sleep(200);

        System.out.println("Calling mpMotStart(0)");
        motTargetClearRet = mpc.mpMotStart(0);
        System.out.println("motStartRet = " + motTargetClearRet);
        Thread.sleep(2000);

        recvId = new int[1];
        System.out.println("Calling mpMotTargetReceive(0,"+jointTarget.getId()+",...,WAIT_FOREVER,0)");
        mpc.mpMotTargetReceive(0, jointTarget.getId(), recvId, MAX_WAIT, 0);
        System.out.println("recvId = " + Arrays.toString(recvId));
//

        System.out.println("Calling mpMotStop(0)");
        motTargetClearRet = mpc.mpMotStop(0);

        System.out.println("motStartRet = " + motTargetClearRet);
        Thread.sleep(200);
        System.out.println("Calling mpSetServoPower(false)");
        mpc.mpSetServoPower(false);
        System.out.println("Calling mpGetServoPower()");
        on = mpc.mpGetServoPower();
        System.out.println("on = " + on);
    }
}
