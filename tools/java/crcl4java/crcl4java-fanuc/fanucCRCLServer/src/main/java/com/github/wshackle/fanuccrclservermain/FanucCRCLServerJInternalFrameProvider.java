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
package com.github.wshackle.fanuccrclservermain;

import static com.github.wshackle.fanuccrclservermain.FanucCRCLMain.DEFAULT_AGILITY_FANUC_NEIGHBORHOOD_NAME;
import static com.github.wshackle.fanuccrclservermain.FanucCRCLMain.DEFAULT_AGILITY_LAB_REMOTE_ROBOT_HOST;
import crcl.utils.CRCLSocket;
import crcl.utils.server.ServerJInternalFrameProviderInterface;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *@author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class FanucCRCLServerJInternalFrameProvider implements ServerJInternalFrameProviderInterface {

    private volatile FanucCRCLMain fanucCRCLMain;
    private volatile FanucCRCLServerJInternalFrame fanucCRCLServerJInternalFrame;

    @Override
    public void start(Object... args) {
        try {
            FanucCRCLMain newFanucCrclMain = new FanucCRCLMain();
            FanucCRCLServerJInternalFrame newFanucCRCLServerJInternalFrame = 
                    getJInternalFrame();
            newFanucCrclMain.setDisplayInterface(newFanucCRCLServerJInternalFrame);
            boolean preferRobotNeighborhood = false;
            String neighborhoodname = DEFAULT_AGILITY_FANUC_NEIGHBORHOOD_NAME;
            String remoteRobotHost = DEFAULT_AGILITY_LAB_REMOTE_ROBOT_HOST;
            int port = CRCLSocket.DEFAULT_PORT;
            if (null != args) {
                if (args.length > 0) {
                    Object arg0 = args[0];
                    if (arg0 instanceof Boolean) {
                        preferRobotNeighborhood = (Boolean) arg0;
                    } else {
                        throw new IllegalArgumentException("args[0] should be Boolean preferRobotNeighborhood");
                    }
                }
                if (args.length > 1) {
                    Object arg1 = args[1];
                    if (arg1 instanceof String) {
                        neighborhoodname = (String) arg1;
                    } else {
                        throw new IllegalArgumentException("args[1] should be String neighborhoodname");
                    }
                }
                if (args.length > 2) {
                    Object arg2 = args[2];
                    if (arg2 instanceof String) {
                        remoteRobotHost = (String) arg2;
                    } else {
                        throw new IllegalArgumentException("args[2] should be String remoteRobotHost");
                    }
                }
                if (args.length > 3) {
                    Object arg3 = args[3];
                    if (arg3 instanceof Integer) {
                        port = (Integer) arg3;
                    } else {
                        throw new IllegalArgumentException("args[2] should be Integer port");
                    }
                }
            }
            newFanucCrclMain.startDisplayInterface();
            newFanucCrclMain.start(preferRobotNeighborhood, neighborhoodname, remoteRobotHost, port); 
            fanucCRCLMain = newFanucCrclMain;
            fanucCRCLServerJInternalFrame = newFanucCRCLServerJInternalFrame;
            //            return runOnDispatchThread(() -> newFanuCRCLServerJInternalFrame(newFanucCrclMain))
                    //                    .thenComposeToVoid(() -> newFanucCrclMain.startDisplayInterface())
                    //                    .thenComposeToVoid(() -> newFanucCrclMain.start(fanucPreferRNN, fanucNeighborhoodName, fanucRobotHost, fanucCrclPort))
                    //                    .thenRun(() -> {
                    //                        this.fanuc = newFanucCrclMain;
                    //                    });
        } catch (Exception ex) {
            Logger.getLogger(FanucCRCLServerJInternalFrameProvider.class.getName()).log(Level.SEVERE, "", ex);
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public synchronized  FanucCRCLServerJInternalFrame getJInternalFrame() {
       if(null == fanucCRCLServerJInternalFrame) {
           FanucCRCLServerJInternalFrame newFanucCRCLServerJInternalFrame = new FanucCRCLServerJInternalFrame();
           this.fanucCRCLServerJInternalFrame = newFanucCRCLServerJInternalFrame;
           return newFanucCRCLServerJInternalFrame;
       }
       return fanucCRCLServerJInternalFrame;
    }
    

    private String name = "FanucCRCLServer";
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public File getPropertiesFile() {
        return fanucCRCLServerJInternalFrame.getPropertiesFile();
    }

    @Override
    public void setPropertiesFile(File propertiesFile) {
        fanucCRCLMain.setPropertiesFile(propertiesFile);
        fanucCRCLServerJInternalFrame.setPropertiesFile(propertiesFile);
    }

    @Override
    public void saveProperties() {
        fanucCRCLServerJInternalFrame.saveProperties();
    }

    @Override
    public void loadProperties() {
         fanucCRCLServerJInternalFrame.loadProperties();
    }

    @Override
    public void setCrclPort(int crclPort) {
        fanucCRCLMain.setLocalPort(crclPort);
    }

    @Override
    public int getCrclPort() {
        return fanucCRCLMain.getLocalPort();
    }

    @Override
    public String getRemoteRobotHost() {
        return fanucCRCLMain.getRemoteRobotHost();
    }

    @Override
    public void setRemotRobotHost(String remoteRobotHost) {
        fanucCRCLMain.setRemoteRobotHost(remoteRobotHost);
    }

}
