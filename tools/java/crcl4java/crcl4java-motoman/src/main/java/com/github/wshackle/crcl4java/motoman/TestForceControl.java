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

import com.github.wshackle.crcl4java.motoman.force.FCS_COORD_TYPE;
import com.github.wshackle.crcl4java.motoman.force.FcsReturnCode;
import com.github.wshackle.crcl4java.motoman.force.MP_FCS_ROB_ID;
import com.github.wshackle.crcl4java.motoman.force.MpFcsBaseReturn;
import com.github.wshackle.crcl4java.motoman.force.MpFcsGetForceDataReturn;
import com.github.wshackle.crcl4java.motoman.force.MpFcsStartMeasuringReturn;
import com.github.wshackle.crcl4java.motoman.kinematics.MP_KINEMA_TYPE;
import com.github.wshackle.crcl4java.motoman.kinematics.MpKinAngleReturn;
import com.github.wshackle.crcl4java.motoman.kinematics.MpKinPulseReturn;
import com.github.wshackle.crcl4java.motoman.sys1.MP_CART_POS_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_PULSE_POS_RSP_DATA;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Enumeration;
import java.util.List;

/**
 *
 * @author shackle
 */
public class TestForceControl {

    private static String host = MotoPlusConnection.getDefaultHost();

    public static void main(String[] args) throws Exception {
        System.out.println("host = " + host);
        try (MotoPlusConnection mpc = MotoPlusConnection.connectionFromSocket(new Socket(host, 12222))) {

            MP_PULSE_POS_RSP_DATA currentPulseData = mpc.getPulsePos(0);
            System.out.println("currentPulseData = " + currentPulseData);

            MP_CART_POS_RSP_DATA currentCartPos = mpc.getCartPos(0);
            System.out.println("currentCartPos = " + currentCartPos);

            final MpFcsStartMeasuringReturn startMeasRet
                    = mpc.mpFcsStartMeasuring(MP_FCS_ROB_ID.MP_FCS_R1ID, 100);
            System.out.println("startMeasRet = " + startMeasRet);
            final MpFcsGetForceDataReturn getForceDataRet
                    = mpc.mpFcsGetForceData(MP_FCS_ROB_ID.MP_FCS_R1ID, FCS_COORD_TYPE.FCS_ROBO_TYPE, 0);
            System.out.println("getForceDataRet = " + getForceDataRet);
            int m[] = new int[]{1, 1, 1, 1, 1, 1};
            int d[] = new int[]{1, 1, 1, 1, 1, 1};
            int k[] = new int[]{1, 1, 1, 1, 1, 1};
            try {
                mpc.mpSetServoPower(true);
                final MpFcsBaseReturn mpFcsStartImpRet = mpc.mpFcsStartImp(
                        MP_FCS_ROB_ID.MP_FCS_R1ID, m, d, k, 0, 0, 63, 0);
                System.out.println("mpFcsStartImpRet = " + mpFcsStartImpRet);
                int fref[] = new int[]{0, 0, 1, 0, 0, 0};
                final MpFcsBaseReturn mpFcsSetReferenceForceRet = mpc.mpFcsSetReferenceForce(MP_FCS_ROB_ID.MP_FCS_R1ID, fref);
                System.out.println("mpFcsSetReferenceForceRet = " + mpFcsSetReferenceForceRet);
                if (mpFcsStartImpRet.returnCode == FcsReturnCode.E_FCS_SUCCESS
                        && mpFcsSetReferenceForceRet.returnCode == FcsReturnCode.E_FCS_SUCCESS) {
                    for (int i = 0; i < 100; i++) {
                        Thread.sleep(100);
                        final MpFcsGetForceDataReturn getForceDataRet2
                                = mpc.mpFcsGetForceData(MP_FCS_ROB_ID.MP_FCS_R1ID, FCS_COORD_TYPE.FCS_ROBO_TYPE, 0);
                        System.out.println("getForceDataRet2 = " + getForceDataRet2);
                        MP_PULSE_POS_RSP_DATA currentPulseData2 = mpc.getPulsePos(0);
                        System.out.println("currentPulseData2 = " + currentPulseData2);

                        MP_CART_POS_RSP_DATA currentCartPos2 = mpc.getCartPos(0);
                        System.out.println("currentCartPos2 = " + currentCartPos2);
                        System.out.println("i = " + i);
                        System.out.println("");
                        System.out.println("");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                final MpFcsBaseReturn mpFcsEndImpRet = mpc.mpFcsEndImp(MP_FCS_ROB_ID.MP_FCS_R1ID);
                System.out.println("mpFcsEndImpRet = " + mpFcsEndImpRet);
                mpc.mpSetServoPower(false);

            }
        }
    }
}
