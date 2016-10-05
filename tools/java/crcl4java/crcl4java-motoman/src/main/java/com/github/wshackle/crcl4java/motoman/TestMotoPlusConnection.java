package com.github.wshackle.crcl4java.motoman;

import com.github.wshackle.crcl4java.motoman.motctrl.CoordTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.JointTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_INTP_TYPE;
import com.github.wshackle.crcl4java.motoman.motctrl.MotCtrlReturnEnum;
import com.github.wshackle.crcl4java.motoman.sys1.MP_VAR_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_VAR_INFO;
import com.github.wshackle.crcl4java.motoman.sys1.VarType;

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
            MP_VAR_INFO sData[] = new MP_VAR_INFO[1];
            sData[0] = new MP_VAR_INFO();
            sData[0].usType = VarType.MP_RESTYPE_VAR_I;
            sData[0].usIndex = 1;
            long []rData = new long[1];
            System.out.println("Calling mpGetVarData(,,1)");
            boolean getVarRet = mpc.mpGetVarData(sData, rData, 1);
            System.out.println("getVarRet = " + getVarRet);
            System.out.println("rData[0] = " + rData[0]);
            MP_VAR_DATA varData[] = new MP_VAR_DATA[1];
            varData[0] = new MP_VAR_DATA();
            varData[0].usType = VarType.MP_RESTYPE_VAR_I;
            varData[0].usIndex = 1;
            varData[0].ulValue = 77;
            System.out.println("Calling mpPutVarData(,,1)");
            boolean putVarRet = mpc.mpPutVarData(varData, 1);
            System.out.println("putVarRet = " + putVarRet);
        }
    }
}
