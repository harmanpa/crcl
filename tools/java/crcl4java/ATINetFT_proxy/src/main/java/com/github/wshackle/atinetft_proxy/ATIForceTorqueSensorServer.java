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

import crcl.base.ForceTorqueSensorStatusType;
import crcl.base.ParameterSettingType;
import crcl.utils.server.SensorServerInterface;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *@author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class ATIForceTorqueSensorServer implements SensorServerInterface {

    private final NetFTSensorProxy netFtSensor;
    private final ConfigurationReader configurationReader;
    private final DatagramSocket lowSpeedSocket;
    private final String sensorId;
    private final List<ParameterSettingType> sensorParameterSetting;

    public ATIForceTorqueSensorServer(String sensorId, List<ParameterSettingType> sensorParameterSetting, NetFTSensorProxy netFtSensor, ConfigurationReader configurationReader) throws IOException {
        this.sensorId = sensorId;
        this.sensorParameterSetting = sensorParameterSetting;
        this.netFtSensor = netFtSensor;
        this.configurationReader = configurationReader;
        this.lowSpeedSocket = netFtSensor.initLowSpeedData();
    }

    public ATIForceTorqueSensorServer(String sensorId, List<ParameterSettingType> sensorParameterSetting, String host) throws UnknownHostException, IOException, IOException, IOException {
        this(sensorId, sensorParameterSetting, new NetFTSensorProxy(InetAddress.getByName(host)), new ConfigurationReader(host));
    }

    private static String findParam(List<ParameterSettingType> sensorParameterSetting, String name) {
        for (ParameterSettingType param : sensorParameterSetting) {
            if (param.getParameterName().equals(name)) {
                return param.getParameterValue();
            }
        }
        return null;
    }

    public ATIForceTorqueSensorServer(String sensorId, List<ParameterSettingType> sensorParameterSetting) throws UnknownHostException, IOException {
        this(sensorId, sensorParameterSetting, findParam(sensorParameterSetting, "host"));
    }

    @Override
    public synchronized  ForceTorqueSensorStatusType getCurrentSensorStatus() {
        try {
            NetFTRDTPacketProxy packet = netFtSensor.readLowSpeedData(lowSpeedSocket);
            ForceTorqueSensorStatusType sensorStatus = new ForceTorqueSensorStatusType();
            double countsPerForce = configurationReader.getCountsPerForce();
            sensorStatus.setFx(packet.getFx() / countsPerForce);
            sensorStatus.setFy(packet.getFy() / countsPerForce);
            sensorStatus.setFz(packet.getFz() / countsPerForce);
            double countsPerTorque = configurationReader.getCountsPerTorque();
            sensorStatus.setTx(packet.getTx() / countsPerTorque);
            sensorStatus.setTy(packet.getTy() / countsPerTorque);
            sensorStatus.setTz(packet.getTz() / countsPerTorque);
            sensorStatus.setSensorID(sensorId);
            sensorStatus.getSensorParameterSetting().addAll(sensorParameterSetting);
            return sensorStatus;
        } catch (Exception ex) {
            Logger.getLogger(ATIForceTorqueSensorServer.class.getName()).log(Level.SEVERE, "", ex);
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void close() throws Exception {
        lowSpeedSocket.close();
    }

}
