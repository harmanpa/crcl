package com.github.wshackle.crcl4java.motoman;

import static com.github.wshackle.crcl4java.motoman.MotoPlusConnection.WAIT_FOREVER;
import com.github.wshackle.crcl4java.motoman.motctrl.JointTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_INTP_TYPE;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_SPEED;
import com.github.wshackle.crcl4java.motoman.motctrl.MotCtrlReturnEnum;
import com.github.wshackle.crcl4java.motoman.sys1.MP_CART_POS_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_DEG_POS_RSP_DATA_EX;
import com.github.wshackle.crcl4java.motoman.sys1.MP_FB_PULSE_POS_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_PULSE_POS_RSP_DATA;
import java.net.Socket;
import java.util.Arrays;

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
/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class TestMotoPlusConnection {

    private static String host = "10.0.0.2";
//    private static String host = "localhost";
    
    public static void main(String[] args) throws Exception {
        try (MotoPlusConnection mpc = new MotoPlusConnection(new Socket(host, 11000))) {
//            mpc.connect("10.0.0.2", 11000);
//            mpc.connect("localhost", 11000);

//
//            System.out.println("Calling mpMotStop(2)");
//            MotCtrlReturnEnum motStopRet = mpc.mpMotStop(2);
//            System.out.println("motStopRet = " + motStopRet);
//            Thread.sleep(200);
//
//            System.out.println("Calling mpMotTargetClear(3,4)");
//            MotCtrlReturnEnum motClearRet = mpc.mpMotTargetClear(3,4);
//            System.out.println("motClearRet = " + motClearRet);
//            Thread.sleep(200);
//            
            System.out.println("Calling mpGetServoPower()");
            boolean on = mpc.mpGetServoPower();
            System.out.println("on = " + on);
            System.out.println("Calling mpSetServoPower(true)");
            mpc.mpSetServoPower(true);
            System.out.println("Calling mpGetServoPower()");
            on = mpc.mpGetServoPower();
            System.out.println("on = " + on);

            MP_SPEED spd = new MP_SPEED();
            spd.vj = (int) 300;
            System.out.println("Calling mpMotSetSpeed(1,...)");
            mpc.mpMotSetSpeed(1, spd);
            JointTarget jointTarget = new JointTarget();
            jointTarget.setId(5);
            jointTarget.setIntp(MP_INTP_TYPE.MP_MOVJ_TYPE);
            int jp[] = new int[]{-3348, 9564, -74224, 3640, -112923, 3209, -5, 0};
            System.arraycopy(jp, 0, jointTarget.getDst(), 0, jointTarget.getDst().length);
            System.arraycopy(jp, 0, jointTarget.getAux(), 0, jointTarget.getAux().length);
            System.out.println("jointTarget = " + jointTarget);
            System.out.println("Calling mpMotTargetJointSend(0,(...),0)\n");
            MotCtrlReturnEnum motTargetJointRet = mpc.mpMotTargetJointSend(1, jointTarget, 0);
            System.out.println("motTargetJointRet = " + motTargetJointRet);
            Thread.sleep(200);

            System.out.println("Calling mpMotStart(0)");
            MotCtrlReturnEnum motStartRet = mpc.mpMotStart(0);
            System.out.println("motStartRet = " + motStartRet);
            Thread.sleep(200);
            int recvId[] = new int[1];
            System.out.println("Calling mpMotTargetReceive(0,5,...,WAIT_FOREVER,0)");
            mpc.mpMotTargetReceive(0, 5, recvId, WAIT_FOREVER, 0);
            System.out.println("recvId = " + Arrays.toString(recvId));
//            
//            CoordTarget coordTarget = new CoordTarget();
//            coordTarget.setId(36);
//            coordTarget.setIntp(MP_INTP_TYPE.MP_MOVL_TYPE);
//            coordTarget.getDst().x = 40;
//            coordTarget.getDst().y = 41;
//            coordTarget.getDst().z = 42;
//            coordTarget.getDst().rx = 43;
//            coordTarget.getDst().ry = 44;
//            coordTarget.getDst().rz = 45;
//            coordTarget.getDst().ex1 = 46;
//            coordTarget.getDst().ex2 = 47;
//            
//            coordTarget.getAux().x = 50;
//            coordTarget.getAux().y = 51;
//            coordTarget.getAux().z = 52;
//            coordTarget.getAux().rx = 53;
//            coordTarget.getAux().ry = 54;
//            coordTarget.getAux().rz = 55;
//            coordTarget.getAux().ex1 = 56;
//            coordTarget.getAux().ex2 = 57;
//            System.out.println("coordTarget = " + coordTarget);
//            System.out.println("Calling mpMotTargetJointSend(63,(...),65)");
//            MotCtrlReturnEnum motTargetCoordRet = mpc.mpMotTargetCoordSend(63, coordTarget, 65);
//            System.out.println("motTargetCoordRet = " + motTargetCoordRet);
//            Thread.sleep(200);
//            MP_VAR_INFO sData[] = new MP_VAR_INFO[1];
//            sData[0] = new MP_VAR_INFO();
//            sData[0].usType = VarType.MP_RESTYPE_VAR_I;
//            sData[0].usIndex = 1;
//            long []rData = new long[1];
//            System.out.println("Calling mpGetVarData(,,1)");
//            boolean getVarRet = mpc.mpGetVarData(sData, rData, 1);
//            System.out.println("getVarRet = " + getVarRet);
//            System.out.println("rData[0] = " + rData[0]);
//            MP_VAR_DATA varData[] = new MP_VAR_DATA[1];
//            varData[0] = new MP_VAR_DATA();
//            varData[0].usType = VarType.MP_RESTYPE_VAR_I;
//            varData[0].usIndex = 1;
//            varData[0].ulValue = 77;
//            System.out.println("Calling mpPutVarData(,,1)");
//            boolean putVarRet = mpc.mpPutVarData(varData, 1);
//            System.out.println("putVarRet = " + putVarRet);

            MP_FB_PULSE_POS_RSP_DATA fbPulseData[] = new MP_FB_PULSE_POS_RSP_DATA[1];
            fbPulseData[0] = new MP_FB_PULSE_POS_RSP_DATA();
            System.out.println("Calling mpGetFBPulsePos(0,...)");
            boolean getFBPulsePosRet = mpc.mpGetFBPulsePos(0, fbPulseData);
            System.out.println("getFBPulsePosRet = " + getFBPulsePosRet);
            System.out.println("fbPulseData[0] = " + fbPulseData[0]);

            MP_CART_POS_RSP_DATA data[] = new MP_CART_POS_RSP_DATA[1];
            data[0] = new MP_CART_POS_RSP_DATA();
            System.out.println("Calling mpGetCartPos(0,...)");
            boolean getCartPosRet = mpc.mpGetCartPos(0, data);
            System.out.println("getCartPosRet = " + getCartPosRet);
            System.out.println("data[0] = " + data[0]);

            MP_PULSE_POS_RSP_DATA pulseData[] = new MP_PULSE_POS_RSP_DATA[1];
            pulseData[0] = new MP_PULSE_POS_RSP_DATA();
            System.out.println("Calling mpGetPulsePos(0,...)");
            boolean getPulsePosRet = mpc.mpGetPulsePos(0, pulseData);
            System.out.println("getPulsePosRet = " + getPulsePosRet);
            System.out.println("pulseData[0] = " + pulseData[0]);

            MP_DEG_POS_RSP_DATA_EX degData[] = new MP_DEG_POS_RSP_DATA_EX[1];
            degData[0] = new MP_DEG_POS_RSP_DATA_EX();
            System.out.println("Calling mpGetDegPosEx(0,...)");
            boolean geDegPosExRet = mpc.mpGetDegPosEx(0, degData);
            System.out.println("geDegPosExRet = " + geDegPosExRet);
            System.out.println("degData[0] = " + degData[0]);

            System.out.println("Calling mpSetServoPower(false)");
            mpc.mpSetServoPower(false);
            System.out.println("Calling mpGetServoPower()");
            on = mpc.mpGetServoPower();
            System.out.println("on = " + on);
        }
    }
}
