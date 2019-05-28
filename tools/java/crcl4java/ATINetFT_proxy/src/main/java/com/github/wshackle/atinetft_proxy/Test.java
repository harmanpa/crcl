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
package com.github.wshackle.atinetft_proxy;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author shackle
 */
public class Test {

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        ConfigurationReader configurationReader = new ConfigurationReader("192.168.1.1");
        System.out.println("configurationReader = " + configurationReader);
        NetFTSensorProxy sensor = new NetFTSensorProxy(InetAddress.getByName("192.168.1.1"));
        try (DatagramSocket socket = sensor.initLowSpeedData()) {
            double fzsum = 0;
            double fzmax = Double.MIN_VALUE;
            double fzmin = Double.MAX_VALUE;
            for (int i = 0; i < 50; i++) {
                long t1 = System.currentTimeMillis();
                NetFTRDTPacketProxy packet = sensor.readLowSpeedData(socket);
                long t2 = System.currentTimeMillis();
                long diff = t2 - t1;
                double fz =  packet.getFz()/configurationReader.getCountsPerForce();
                fzsum += fz;
                System.out.println("fz = " + fz);
                if (fz > fzmax) {
                    fzmax = fz;
                }
                if (fz < fzmin) {
                    fzmin = fz;
                }
                Thread.sleep(50);
            }
            double fzoffset = fzsum/20;
            System.out.println("fzoffset = " + fzoffset);
            System.out.println("fzmin = " + fzmin);
            double fzminrel = fzmin - fzoffset;
            System.out.println("fzminrel = " + fzminrel);
            System.out.println("fzmax = " + fzmax);
            double fzmaxrel = fzmax - fzoffset;
            System.out.println("fzmaxrel = " + fzminrel);
            for (int i = 0; i < 100000; i++) {
                long t1 = System.currentTimeMillis();
                NetFTRDTPacketProxy packet = sensor.readLowSpeedData(socket);
                long t2 = System.currentTimeMillis();
                long diff = t2 - t1;
                double fz =  packet.getFz()/configurationReader.getCountsPerForce();
                double fzrel = fz - fzoffset;
                if(fzrel > fzmaxrel) {
                    System.out.println(" > fzmaxrel : fzrel = " + fzrel);
                } else if(fzrel <fzminrel) {
                     System.out.println(" < fzminrel : fzrel = " + fzrel);
                }
                Thread.sleep(50);
            }
        }
    }

}
