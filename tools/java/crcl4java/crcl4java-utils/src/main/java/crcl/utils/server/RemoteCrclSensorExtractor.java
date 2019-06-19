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
package crcl.utils.server;

import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLStatusType;
import crcl.base.GetStatusType;
import crcl.base.ParameterSettingType;
import crcl.base.SensorStatusType;
import crcl.base.SensorStatusesType;
import crcl.utils.CRCLException;
import crcl.utils.CRCLSocket;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class RemoteCrclSensorExtractor implements SensorServerInterface {

    final int remotePort;
    final String remoteHost;
    final String remoteSensorId;
    final String outSensorId;
    final String inSensorId;
    private final List<ParameterSettingType> parameterList;
    private final CRCLSocket crclSocket;

    private static String findParam(List<ParameterSettingType> sensorParameterSetting, String name, String defaultValue) {
        for (ParameterSettingType param : sensorParameterSetting) {
            if (param.getParameterName().equals(name)) {
                return param.getParameterValue();
            }
        }
        return defaultValue;
    }

    public RemoteCrclSensorExtractor(String inSensorId, List<ParameterSettingType> parameterList) throws CRCLException, IOException {

        this.inSensorId = inSensorId;
        this.parameterList = parameterList;
        this.remoteHost = findParam(parameterList, "host","localhost");
        this.remotePort = Integer.parseInt(findParam(parameterList, "port","8888"));
        this.remoteSensorId = findParam(parameterList, "remoteSensorId",inSensorId);
        this.outSensorId = findParam(parameterList, "outSensorId",inSensorId);
        crclSocket = new CRCLSocket(remoteHost, remotePort);
        getStatusCommandInstance = new CRCLCommandInstanceType();
        GetStatusType getStatusCmd = new GetStatusType();
        getStatusCommandInstance.setCRCLCommand(getStatusCmd);
    }

    private final CRCLCommandInstanceType getStatusCommandInstance;

    @Override
    public SensorStatusType getCurrentSensorStatus() {
        try {
            crclSocket.writeCommand(getStatusCommandInstance);
            CRCLStatusType status = crclSocket.readStatus();
            if(status == null) {
                return null;
            }
            SensorStatusesType sensors = status.getSensorStatuses();
            SensorStatusType firstSensorStat = null;
            if (null != sensors) {
                for (SensorStatusType sensorStat : sensors.getCountSensorStatus()) {
                    if(null == firstSensorStat && null != sensorStat ) {
                        firstSensorStat=sensorStat;
                    }
                    if (sensorStat.getSensorID().equals(remoteSensorId)) {
                        sensorStat.setSensorID(outSensorId);
                        return sensorStat;
                    }
                }
                for (SensorStatusType sensorStat : sensors.getCountSensorStatus()) {
                    if(null == firstSensorStat && null != sensorStat ) {
                        firstSensorStat=sensorStat;
                    }
                    if (sensorStat.getSensorID().equals(remoteSensorId)) {
                        sensorStat.setSensorID(outSensorId);
                        return sensorStat;
                    }
                }
                for (SensorStatusType sensorStat : sensors.getOnOffSensorStatus()) {
                    if(null == firstSensorStat && null != sensorStat ) {
                        firstSensorStat=sensorStat;
                    }
                    if (sensorStat.getSensorID().equals(remoteSensorId)) {
                        sensorStat.setSensorID(outSensorId);
                        return sensorStat;
                    }
                }
                for (SensorStatusType sensorStat : sensors.getScalarSensorStatus()) {
                    if(null == firstSensorStat && null != sensorStat ) {
                        firstSensorStat=sensorStat;
                    }
                    if (sensorStat.getSensorID().equals(remoteSensorId)) {
                        sensorStat.setSensorID(outSensorId);
                        return sensorStat;
                    }
                }
                for (SensorStatusType sensorStat : sensors.getForceTorqueSensorStatus()) {
                    if(null == firstSensorStat && null != sensorStat ) {
                        firstSensorStat=sensorStat;
                    }
                    if (sensorStat.getSensorID().equals(remoteSensorId)) {
                        sensorStat.setSensorID(outSensorId);
                        return sensorStat;
                    }
                }
                return firstSensorStat;
            }
            return null;
        } catch (Exception ex) {
            Logger.getLogger(RemoteCrclSensorExtractor.class.getName()).log(Level.SEVERE, null, ex);
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void close() throws Exception {
        crclSocket.close();
    }

}
