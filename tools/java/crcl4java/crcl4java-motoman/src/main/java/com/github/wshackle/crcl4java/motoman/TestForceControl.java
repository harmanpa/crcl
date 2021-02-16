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
import com.github.wshackle.crcl4java.motoman.motctrl.COORD_POS;
import com.github.wshackle.crcl4java.motoman.motctrl.CoordTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_COORD_TYPE;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_INTP_TYPE;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_SPEED;
import com.github.wshackle.crcl4java.motoman.motctrl.MotCtrlReturnEnum;
import com.github.wshackle.crcl4java.motoman.sys1.MP_ALARM_CODE_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_ALARM_STATUS_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_CART_POS_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_PULSE_POS_RSP_DATA;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

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
                System.out.println("Calling mpc.mpSetServoPower(true)");
                mpc.mpSetServoPower(true);
                final MP_ALARM_STATUS_DATA mpGetAlarmStatusRet0 = mpc.mpGetAlarmStatus();
                System.out.println("mpGetAlarmStatusRet0 = " + mpGetAlarmStatusRet0);
                final MP_ALARM_CODE_DATA mpGetAlarmCodeRet0 = mpc.mpGetAlarmCode();
                System.out.println("mpGetAlarmCodeRet0 = " + mpGetAlarmCodeRet0);

                System.out.println("Calling mpc.mpFcsStartImp(\n"
                        + "                        MP_FCS_ROB_ID.MP_FCS_R1ID, // MP_FCS_ROB_ID  rob_id\n"
                        + "                        " + Arrays.toString(m) + ", // int m[]\n"
                        + "                        " + Arrays.toString(d) + ", // int d[]\n"
                        + "                        " + Arrays.toString(k) + ", // int k[]\n"
                        + "                        0, // int coord_type\n"
                        + "                        0, // int uf_no\n"
                        + "                        1,  // int cart_axes\n"
                        + "                        0 // int option_ctrl\n"
                        + "                ); \n"
                        + "                ");
                final MpFcsBaseReturn mpFcsStartImpRet
                        = mpc.mpFcsStartImp(
                                MP_FCS_ROB_ID.MP_FCS_R1ID, // MP_FCS_ROB_ID  rob_id
                                m, // int m[]
                                d, // int d[]
                                k, // int k[]
                                0, // int coord_type
                                0, // int uf_no
                                1, // int cart_axes
                                0 // int option_ctrl
                        );
                System.out.println("mpFcsStartImpRet = " + mpFcsStartImpRet);
                Thread.sleep(1000);
                System.out.println("");
                final MP_ALARM_STATUS_DATA mpGetAlarmStatusRet1 = mpc.mpGetAlarmStatus();
                System.out.println("mpGetAlarmStatusRet1 = " + mpGetAlarmStatusRet1);
                final MP_ALARM_CODE_DATA mpGetAlarmCodeRet1 = mpc.mpGetAlarmCode();
                System.out.println("mpGetAlarmCodeRet1 = " + mpGetAlarmCodeRet1);
                int fref[] = new int[]{0, 0, 0, 0, 0, 0};
                Thread.sleep(1000);
                System.out.println("Calling mpc.mpFcsSetReferenceForce(\n"
                        + "                                MP_FCS_ROB_ID.MP_FCS_R1ID, // MP_FCS_ROB_ID rob_id\n"
                        + "                                " + Arrays.toString(fref) + " // int fref_data[]\n"
                        + "                        );\n"
                        + "                ");
                final MpFcsBaseReturn mpFcsSetReferenceForceRet
                        = mpc.mpFcsSetReferenceForce(
                                MP_FCS_ROB_ID.MP_FCS_R1ID, // MP_FCS_ROB_ID rob_id
                                fref // int fref_data[]
                        );
                System.out.println("mpFcsSetReferenceForceRet = " + mpFcsSetReferenceForceRet);
                Thread.sleep(1000);
                System.out.println("");
                final MP_ALARM_STATUS_DATA mpGetAlarmStatusRet2 = mpc.mpGetAlarmStatus();
                System.out.println("mpGetAlarmStatusRet2 = " + mpGetAlarmStatusRet2);
                final MP_ALARM_CODE_DATA mpGetAlarmCodeRet2 = mpc.mpGetAlarmCode();
                System.out.println("mpGetAlarmCodeRet2 = " + mpGetAlarmCodeRet2);
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

        System.out.println("Calling mpGetCartPos(0,...)");
        MP_CART_POS_RSP_DATA cartResData = mpc.getCartPos(0);
        System.out.println("cartResData = " + cartResData);

        final MpFcsStartMeasuringReturn startMeasRet
                = mpc.mpFcsStartMeasuring(MP_FCS_ROB_ID.MP_FCS_R1ID, 100);
        System.out.println("startMeasRet = " + startMeasRet);
        final MpFcsGetForceDataReturn getForceDataRet
                = mpc.mpFcsGetForceData(MP_FCS_ROB_ID.MP_FCS_R1ID, FCS_COORD_TYPE.FCS_ROBO_TYPE, 0);
        System.out.println("getForceDataRet = " + getForceDataRet);

        final int MAX_WAIT = mpc.mpGetMaxWait();
        try {
            mpc.mpSetServoPower(true);
            int m[] = new int[]{1, 1, 1, 1, 1, 1};
            int d[] = new int[]{1, 1, 1, 1, 1, 1};
            int k[] = new int[]{1, 1, 1, 1, 1, 1};
            final MpFcsBaseReturn mpFcsStartImpRet = mpc.mpFcsStartImp(
                    MP_FCS_ROB_ID.MP_FCS_R1ID, m, d, k, 0, 0, 63, 0);
            System.out.println("mpFcsStartImpRet = " + mpFcsStartImpRet);
            int fref[] = new int[]{0, 0, 1, 0, 0, 0};
            final MpFcsBaseReturn mpFcsSetReferenceForceRet
                    = mpc.mpFcsSetReferenceForce(MP_FCS_ROB_ID.MP_FCS_R1ID, fref);
            System.out.println("mpFcsSetReferenceForceRet = " + mpFcsSetReferenceForceRet);
            if (mpFcsStartImpRet.returnCode == FcsReturnCode.E_FCS_SUCCESS
                    && mpFcsSetReferenceForceRet.returnCode == FcsReturnCode.E_FCS_SUCCESS) {
                CoordTarget coordTarget = new CoordTarget();
                coordTarget.setId(16);
                coordTarget.setIntp(MP_INTP_TYPE.MP_MOVL_TYPE);
                COORD_POS cp = coordTarget.getDst();
                cp.x = (int) cartResData.lx();
                cp.y = (int) cartResData.ly();
                cp.z = (int) cartResData.lz() - Z_MOVE_MICROS;
                cp.rx = (int) cartResData.lrx();
                cp.ry = (int) cartResData.lry();
                cp.rz = (int) cartResData.lrz();
                cp = coordTarget.getAux();
                cp.x = (int) cartResData.lx();
                cp.y = (int) cartResData.ly();
                cp.z = (int) cartResData.lz() - Z_MOVE_MICROS;
                cp.rx = (int) cartResData.lrx();
                cp.ry = (int) cartResData.lry();
                cp.rz = (int) cartResData.lrz();

                System.out.println("coordTarget = " + coordTarget);
                System.out.println("Calling mpMotTargetCoordSend(0,(...),0)\n");
                MotCtrlReturnEnum motTargetCoordRet = mpc.mpMotTargetCoordSend(1, coordTarget, 0);
                System.out.println("motTargetCoordRet = " + motTargetCoordRet);
                System.out.println("Calling mpMotStart(0)");
                final MotCtrlReturnEnum mpMotStartRet = mpc.mpMotStart(0);
                System.out.println("mpMotStartRet = " + mpMotStartRet);
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
                    int[] recvId = new int[1];
                    System.out.println("Calling mpMotTargetReceive(0," + coordTarget.getId() + ",...," + MAX_WAIT + ",0)");
                    final MotCtrlReturnEnum mpMotTargetReceiveRet
                            = mpc.mpMotTargetReceive(0, coordTarget.getId(), recvId, mpc.mpGetMaxWait(), 0);
                    System.out.println("mpMotTargetReceiveRet = " + mpMotTargetReceiveRet);
                    System.out.println("recvId = " + Arrays.toString(recvId));
                    System.out.println("");
                    System.out.println("");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("Calling mpMotStop(0)");
            final MotCtrlReturnEnum mpMotStopRet = mpc.mpMotStop(0);
            System.out.println("mpMotStopRet = " + mpMotStopRet);
            final MpFcsBaseReturn mpFcsEndImpRet = mpc.mpFcsEndImp(MP_FCS_ROB_ID.MP_FCS_R1ID);
            System.out.println("mpFcsEndImpRet = " + mpFcsEndImpRet);
            mpc.mpSetServoPower(false);
        }

        Thread.sleep(5000);

        CoordTarget coordTarget2 = new CoordTarget();
        coordTarget2.setId(17);
        coordTarget2.setIntp(MP_INTP_TYPE.MP_MOVL_TYPE);
        COORD_POS cp2 = coordTarget2.getDst();
        cp2 = coordTarget2.getDst();
        cp2.x = (int) cartResData.lx();
        cp2.y = (int) cartResData.ly();
        cp2.z = (int) cartResData.lz();
        cp2.rx = (int) cartResData.lrx();
        cp2.ry = (int) cartResData.lry();
        cp2.rz = (int) cartResData.lrz();
        cp2 = coordTarget2.getAux();
        cp2.x = (int) cartResData.lx();
        cp2.y = (int) cartResData.ly();
        cp2.z = (int) cartResData.lz();
        cp2.rx = (int) cartResData.lrx();
        cp2.ry = (int) cartResData.lry();
        cp2.rz = (int) cartResData.lrz();
        coordTarget2.setId(17);

        System.out.println("coordTarget = " + coordTarget2);
        System.out.println("Calling mpMotTargetCoordSend(0,(...),0)\n");
        final MotCtrlReturnEnum motTargetCoordRet2 = mpc.mpMotTargetCoordSend(1, coordTarget2, 0);
        System.out.println("motTargetCoordRet2 = " + motTargetCoordRet2);
        Thread.sleep(200);

        System.out.println("Calling mpMotStart(0)");
        final MotCtrlReturnEnum mpMotStartRet2 = mpc.mpMotStart(0);
        System.out.println("mpMotStartRet2 = " + mpMotStartRet2);
        Thread.sleep(200);

        int[] recvId2 = new int[1];
        System.out.println("Calling mpMotTargetReceive(0," + coordTarget2.getId() + ",...," + MAX_WAIT + ",0)");
        final MotCtrlReturnEnum mpMotTargetReceiveRet2
                = mpc.mpMotTargetReceive(0, coordTarget2.getId(), recvId2, mpc.mpGetMaxWait(), 0);
        System.out.println("mpMotTargetReceiveRet2 = " + mpMotTargetReceiveRet2);
        System.out.println("recvId2 = " + Arrays.toString(recvId2));
//

        System.out.println("Calling mpMotStop(0)");
        final MotCtrlReturnEnum mpMotStopRet2 = mpc.mpMotStop(0);
        System.out.println("mpMotStopRet2 = " + mpMotStopRet2);
        Thread.sleep(200);
        System.out.println("Calling mpGetCartPos(0,...)");
        MP_CART_POS_RSP_DATA cartResData2 = mpc.getCartPos(0);
        System.out.println("cartResData2 = " + cartResData2);
        System.out.println("Calling mpSetServoPower(false)");
        mpc.mpSetServoPower(false);
        System.out.println("Calling mpGetServoPower()");
        on = mpc.mpGetServoPower();
        System.out.println("on = " + on);
    }
    private static final int Z_MOVE_MICROS = 200000;

}
