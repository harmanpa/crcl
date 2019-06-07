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
import crcl.base.CRCLCommandType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStateEnumType;
import crcl.base.CommandStatusType;
import crcl.base.ConfigureJointReportType;
import crcl.base.ConfigureJointReportsType;
import crcl.base.ConfigureStatusReportType;
import crcl.base.CountSensorStatusType;
import crcl.base.DisableSensorStatusType;
import crcl.base.EnableSensorStatusType;
import crcl.base.ForceTorqueSensorStatusType;
import crcl.base.GetStatusType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.MoveThroughToType;
import crcl.base.MoveToType;
import crcl.base.OnOffSensorStatusType;
import crcl.base.PointType;
import crcl.base.PoseStatusType;
import crcl.base.PoseType;
import crcl.base.RotAccelAbsoluteType;
import crcl.base.RotAccelType;
import crcl.base.RotSpeedAbsoluteType;
import crcl.base.RotSpeedType;
import crcl.base.ScalarSensorStatusType;
import crcl.base.SensorStatusType;
import crcl.base.SensorStatusesType;
import crcl.base.SetAngleUnitsType;
import crcl.base.SetForceUnitsType;
import crcl.base.SetLengthUnitsType;
import crcl.base.SetRotAccelType;
import crcl.base.SetRotSpeedType;
import crcl.base.SetTorqueUnitsType;
import crcl.base.SetTransAccelType;
import crcl.base.SetTransSpeedType;
import crcl.base.SettingsStatusType;
import crcl.base.TransAccelAbsoluteType;
import crcl.base.TransAccelType;
import crcl.base.TransSpeedAbsoluteType;
import crcl.base.TransSpeedType;
import crcl.utils.CRCLException;
import crcl.utils.CRCLPosemath;
import crcl.utils.CRCLSocket;
import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.BindException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.validation.Schema;

/*
 * 
 * NOTE: Comments beginning with {@literal @} or {@literal >>>} are used by Checker Framework Comments
 * beginning with {@literal @} must have no spaces in the comment or Checker will ignore
 * it.
 *
 * See http://types.cs.washington.edu/checker-framework for null pointer
 * checks. This file can be compiled without the Checker Framework, but using
 * the framework allows potential NullPointerExceptions to be found.
 */

 /*>>>
import org.checkerframework.checker.nullness.qual.*;
 */
/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov> }
 */
public class CRCLServerSocket<STATE_TYPE extends CRCLServerClientState> implements AutoCloseable {

    private final List<CRCLServerClientInfo> clients = new ArrayList<>();

    private final CRCLServerSocketStateGenerator<STATE_TYPE> stateGenerator;

    private final Class<STATE_TYPE> stateClass;

    private volatile CRCLStatusType serverSideStatus;

    private final Map<String, SensorServerInterface> sensorServers = new HashMap<>();

    public void addSensorServer(String sensorId, SensorServerInterface sensorServer) {
        sensorServers.put(sensorId, sensorServer);
    }

    public void removeSensorServer(String sensorId) {
        sensorServers.remove(sensorId);
    }

    private final List<SensorServerFinderInterface> sensorFinders = new ArrayList<>();

    public void addSensorFinder(SensorServerFinderInterface sensorFinder) {
        sensorFinders.add(sensorFinder);
    }

    public void removeSensorFinder(SensorServerFinderInterface sensorFinder) {
        sensorFinders.remove(sensorFinder);
    }

    public void clearSensorFinders() {
        sensorFinders.clear();
    }

    public CRCLStatusType getServerSideStatus() {
        return serverSideStatus;
    }

    private boolean automaticallyHandleSensorServers = true;

    public final void refreshSensorFinders() {

        clearSensorFinders();
//        try {
//            ClassLoader cl = Thread.currentThread().getContextClassLoader();
//            System.out.println("cl = " + cl);
//            Class clzz = cl.loadClass("com.github.wshackle.atinetft_proxy.ATIForceTorqueSensorFinder");
//            System.out.println("clzz = " + clzz);
//            ProtectionDomain protDom = clzz.getProtectionDomain();
//            System.out.println("protDom = " + protDom);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(CRCLServerSocket.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            Class clzz = Class.forName("com.github.wshackle.atinetft_proxy.ATIForceTorqueSensorFinder");
//            System.out.println("clzz = " + clzz);
//            ProtectionDomain protDom = clzz.getProtectionDomain();
//            System.out.println("protDom = " + protDom);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(CRCLServerSocket.class.getName()).log(Level.SEVERE, null, ex);
//        }
        

        ServiceLoader<SensorServerFinderInterface> loader
                = ServiceLoader
                        .load(SensorServerFinderInterface.class);

        Iterator<SensorServerFinderInterface> it = loader.iterator();
//        System.out.println("it = " + it);
        while(it.hasNext()) {
            SensorServerFinderInterface finder = it.next();
//            System.out.println("finder = " + finder);
            addSensorFinder(finder);
        }
//        System.out.println("this.sensorFinders = " + this.sensorFinders);

    }

    /**
     * Get the value of automaticallyHandleSensorServers
     *
     * @return the value of automaticallyHandleSensorServers
     */
    public boolean isAutomaticallyHandleSensorServers() {
        return automaticallyHandleSensorServers;
    }

    /**
     * Set the value of automaticallyHandleSensorServers
     *
     * @param automaticallyHandleSensorServers new value of
     * automaticallyHandleSensorServers
     */
    public void setAutomaticallyHandleSensorServers(boolean automaticallyHandleSensorServers) {
        this.automaticallyHandleSensorServers = automaticallyHandleSensorServers;
    }

    public void setServerSideStatus(CRCLStatusType serverSideStatus) {
        this.serverSideStatus = serverSideStatus;
        CommandStatusType cst = serverSideStatus.getCommandStatus();
        if (null == cst) {
            cst = new CommandStatusType();
            cst.setCommandState(CommandStateEnumType.CRCL_WORKING);
            cst.setCommandID(1);
            cst.setStatusID(1);
            serverSideStatus.setCommandStatus(cst);
        }
    }

    private boolean automaticallyConvertUnits = true;

    /**
     * Get the value of automaticallyConvertUnits
     *
     * @return the value of automaticallyConvertUnits
     */
    public boolean isAutomaticallyConvertUnits() {
        return automaticallyConvertUnits;
    }

    /**
     * Set the value of automaticallyConvertUnits
     *
     * @param automaticallyConvertUnits new value of automaticallyConvertUnits
     */
    public void setAutomaticallyConvertUnits(boolean automaticallyConvertUnits) {
        this.automaticallyConvertUnits = automaticallyConvertUnits;
    }

    private boolean automaticallySendServerSideStatus = false;

    /**
     * Get the value of automaticallySendServerSideStatus
     *
     * @return the value of automaticallySendServerSideStatus
     */
    public boolean isAutomaticallySendServerSideStatus() {
        return automaticallySendServerSideStatus;
    }

    /**
     * Set the value of automaticallySendServerSideStatus
     *
     * @param automaticallySendServerSideStatus new value of
     * automaticallySendServerSideStatus
     */
    public void setAutomaticallySendServerSideStatus(boolean automaticallySendServerSideStatus) {
        if (automaticallySendServerSideStatus && null == serverSideStatus) {
            throw new IllegalStateException("serverSideStatus == null");
        }
        this.automaticallySendServerSideStatus = automaticallySendServerSideStatus;
    }

    private class CRCLServerClientInfo implements AutoCloseable {

        private final CRCLSocket socket;

        private final STATE_TYPE state;

        /*@Nullable*/
        private final Future<?> future;

        public CRCLSocket getSocket() {
            return socket;
        }

        /*@Nullable*/
        public Future<?> getFuture() {
            return future;
        }

        CRCLServerClientInfo(CRCLSocket socket, /*@Nullable*/ Future<?> future, STATE_TYPE state) {
            this.socket = socket;
            this.future = future;
            this.state = state;
        }

        @Override
        public void close() {
            try {
                if (null != socket) {
                    this.socket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(CRCLServerSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (null != future) {
                this.future.cancel(true);
            }
        }
    }

    /**
     * Get the value of clients
     *
     * @return the value of clients
     */
    public List<CRCLServerClientInfo> getClients() {
        return Collections.unmodifiableList(clients);
    }

    private int port = CRCLSocket.DEFAULT_PORT;

    /**
     * Get the value of port
     *
     * @return the value of port
     */
    public int getPort() {
        return port;
    }

    public boolean isClosed() {
        if (null != serverSocket) {
            return serverSocket.isClosed();
        }
        if (null != serverSocketChannel) {
            return !serverSocketChannel.isOpen();
        }
        return true;
    }

    /**
     * Set the value of port
     *
     * @param port new value of port
     * @throws java.io.IOException if current socket fails to close
     */
    public void setPort(int port) throws IOException {
        if (isRunning()) {
            throw new IllegalStateException("Can not set field when server is running.");
        }
        this.port = port;
        if (serverSocket != null) {
            serverSocket.close();
            serverSocket = null;
        }
        if (serverSocketChannel != null) {
            serverSocketChannel.close();
            serverSocketChannel = null;
        }
    }

    /*@Nullable*/
    private ExecutorService callbackService = null;

    /**
     * Get the value of callbackService
     *
     * @return the value of callbackService
     */
    /*@Nullable*/
    public ExecutorService getCallbackService() {
        return callbackService;
    }

    /**
     * Set the value of callbackService
     *
     * @param callbackService new value of callbackService
     */
    public void setCallbackService(ExecutorService callbackService) {
        if (isRunning()) {
            throw new IllegalStateException("Can not set field when server is running.");
        }
        this.callbackService = callbackService;
    }

    /*@Nullable*/
    private ExecutorService executorService;

    /**
     * Get the value of executorService
     *
     * @return the value of executorService
     */
    /*@Nullable*/
    public ExecutorService getExecutorService() {
        return executorService;
    }

    /**
     * Set the value of executorService
     *
     * @param executorService new value of executorService
     */
    public void setExecutorService(ExecutorService executorService) {
        if (isRunning()) {
            throw new IllegalStateException("Can not set field when server is running.");
        }
        this.executorService = executorService;
    }

    /*@Nullable*/ private volatile ServerSocketChannel serverSocketChannel;

    private boolean closing = false;

    @Override
    public void close() {
        started = false;
        closing = true;
        if (queueEvents) {
            try {
                queue.offer(CRCLServerSocketEvent.serverClosed(stateClass), 1, TimeUnit.SECONDS);
            } catch (Exception ex) {
                Logger.getLogger(CRCLServerSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (null != serverSocketChannel) {
            try {
                serverSocketChannel.close();
            } catch (IOException ignored) {
            }
            serverSocketChannel = null;
        }
        if (null != serverSocket) {
            try {
                serverSocket.close();
            } catch (IOException ignored) {
            }
            serverSocket = null;
        }
        if (null != selector) {
            try {
                selector.close();
            } catch (IOException ignored) {
            }
            selector = null;
        }
        for (int i = 0; i < clients.size(); i++) {
            try {
                CRCLServerClientInfo client = clients.get(i);
                client.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        clients.clear();
        if (null != executorService) {
            executorService.shutdownNow();
            try {
                executorService.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                Logger.getLogger(CRCLServerSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (null != callbackService) {
            callbackService.shutdownNow();
            try {
                callbackService.awaitTermination(1, TimeUnit.SECONDS);
            } catch (Exception ex) {
                Logger.getLogger(CRCLServerSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        listeners.clear();
        queue.clear();
    }

//    @Override
//    protected void finalize() throws Throwable {
//        close();
//        super.finalize();
//    }
    private boolean validate;

    /**
     * Get the value of validate
     *
     * @return the value of validate
     */
    public boolean isValidate() {
        return validate;
    }

    /**
     * Set the value of validate
     *
     * @param validate new value of validate
     */
    public void setValidate(boolean validate) {
        if (isRunning()) {
            throw new IllegalStateException("Can not set field when server is running.");
        }
        this.validate = validate;
    }

    /*@Nullable*/ private ServerSocket serverSocket;

    final List<CRCLServerSocketEventListener<STATE_TYPE>> listeners = new ArrayList<>();

    public synchronized void addListener(CRCLServerSocketEventListener<STATE_TYPE> l) {
        if (isRunning()) {
            throw new IllegalStateException("Can not add listener when server is already running.");
        }
        listeners.add(l);
    }

    public synchronized void removeListener(CRCLServerSocketEventListener<STATE_TYPE> l) {
        if (isRunning()) {
            throw new IllegalStateException("Can not remove listener when server is already running.");
        }
        listeners.remove(l);
    }

    private boolean queueEvents = false;

    /**
     * Get the value of queueEvents
     *
     * @return the value of queueEvents
     */
    public boolean isQueueEvents() {
        return queueEvents;
    }

    /**
     * Set the value of queueEvents
     *
     * @param queueEvents new value of queueEvents
     */
    public void setQueueEvents(boolean queueEvents) {
        if (isRunning()) {
            throw new IllegalStateException("Can not set field when server is running.");
        }
        this.queueEvents = queueEvents;
    }

    private BlockingQueue<CRCLServerSocketEvent<STATE_TYPE>> queue = new LinkedBlockingQueue<>();

    private void handleEvent(final CRCLServerSocketEvent<STATE_TYPE> event) throws Exception {
        if (closing) {
            return;
        }
        if (handleAutomaticEvents(event)) {
            return;
        }
        completeHandleEvent(event);
    }

    private boolean handleAutomaticEvents(final CRCLServerSocketEvent<STATE_TYPE> event) throws IllegalStateException, CRCLException, InterruptedException {
        final CRCLCommandInstanceType instanceIn = event.getInstance();
        if (automaticallySendServerSideStatus
                && event.getEventType() == CRCLServerSocketEventType.CRCL_COMMAND_RECIEVED
                && serverSideStatus != null
                && instanceIn != null) {
            STATE_TYPE state = event.getState();
            final CRCLCommandType cmd = instanceIn.getCRCLCommand();
            if (cmd instanceof GetStatusType) {
                CRCLSocket source = event.getSource();
                if (updateStatusRunnable != null) {
                    updateStatusRunnable.run();
                    updateStatusRunCount++;
                }
                for (SensorServerInterface sensorServer : sensorServers.values()) {
                    SensorStatusType sensorStat = sensorServer.getCurrentSensorStatus();
                    if (null != sensorStat) {
                        if (null == serverSideStatus.getSensorStatuses()) {
                            serverSideStatus.setSensorStatuses(new SensorStatusesType());
                        }
                        SensorStatusesType sensorStatuses = serverSideStatus.getSensorStatuses();
                        final List<OnOffSensorStatusType> onOffSensorStatusList = sensorStatuses.getOnOffSensorStatus();
                        if (null != onOffSensorStatusList) {
                            for (int i = 0; i < onOffSensorStatusList.size(); i++) {
                                OnOffSensorStatusType onOffSensorStat = onOffSensorStatusList.get(i);
                                if (onOffSensorStat.getSensorID().equals(sensorStat.getSensorID())) {
                                    onOffSensorStatusList.remove(i);
                                    break;
                                }
                            }
                        }
                        final List<CountSensorStatusType> countSensorStatusList = sensorStatuses.getCountSensorStatus();
                        if (null != countSensorStatusList) {
                            for (int i = 0; i < countSensorStatusList.size(); i++) {
                                CountSensorStatusType countSensorStat = countSensorStatusList.get(i);
                                if (countSensorStat.getSensorID().equals(sensorStat.getSensorID())) {
                                    countSensorStatusList.remove(i);
                                    break;
                                }
                            }
                        }
                        final List<ScalarSensorStatusType> scalarSensorStatusList = sensorStatuses.getScalarSensorStatus();
                        if (null != scalarSensorStatusList) {
                            for (int i = 0; i < scalarSensorStatusList.size(); i++) {
                                ScalarSensorStatusType scalarSensorStat = scalarSensorStatusList.get(i);
                                if (scalarSensorStat.getSensorID().equals(sensorStat.getSensorID())) {
                                    scalarSensorStatusList.remove(i);
                                    break;
                                }
                            }
                        }
                        final List<ForceTorqueSensorStatusType> forceTorqueSensorStatusList = sensorStatuses.getForceTorqueSensorStatus();
                        if (null != forceTorqueSensorStatusList) {
                            for (int i = 0; i < forceTorqueSensorStatusList.size(); i++) {
                                ForceTorqueSensorStatusType forceTorqueSensorStat = forceTorqueSensorStatusList.get(i);
                                if (forceTorqueSensorStat.getSensorID().equals(sensorStat.getSensorID())) {
                                    forceTorqueSensorStatusList.remove(i);
                                    break;
                                }
                            }
                        }
                        if (sensorStat instanceof OnOffSensorStatusType) {
                            OnOffSensorStatusType onOffSensorStat = (OnOffSensorStatusType) sensorStat;
                            onOffSensorStatusList.add(onOffSensorStat);
                        }
                        if (sensorStat instanceof CountSensorStatusType) {
                            CountSensorStatusType countSensorStat = (CountSensorStatusType) sensorStat;
                            countSensorStatusList.add(countSensorStat);
                        }
                        if (sensorStat instanceof ScalarSensorStatusType) {
                            ScalarSensorStatusType scalarSensorStat = (ScalarSensorStatusType) sensorStat;
                            scalarSensorStatusList.add(scalarSensorStat);
                        }
                        if (sensorStat instanceof ForceTorqueSensorStatusType) {
                            ForceTorqueSensorStatusType forceTorqueSensorStat = (ForceTorqueSensorStatusType) sensorStat;
                            forceTorqueSensorStatusList.add(forceTorqueSensorStat);
                        }
                    }
                }
                CRCLStatusType statusToSend = state.filterSettings.filterStatus(serverSideStatus);
                statusToSend.getCommandStatus().setCommandID(state.cmdId);
                PointType origPoint = CRCLPosemath.getPoint(serverSideStatus);
                PointType pointToSend = CRCLPosemath.getPoint(statusToSend);
                if (null == origPoint) {
                    throw new IllegalStateException("origPoint = " + origPoint);
                } else if (null == pointToSend) {
                    throw new IllegalStateException("pointToSend = " + pointToSend);
                }
                source.writeStatus(statusToSend);
                return true;
            } else {
                state.cmdId = cmd.getCommandID();
                if (serverSideStatus.getCommandStatus() == null) {
                    serverSideStatus.setCommandStatus(new CommandStatusType());
                }
                final CommandStatusType commandStatus = serverSideStatus.getCommandStatus();
                commandStatus.setCommandID(cmd.getCommandID());
                String cmdStatusName = cmd.getClass().getSimpleName();
                if (cmd.getName() != null && cmd.getName().length() > 0 && !cmd.getName().startsWith(cmdStatusName)) {
                    cmdStatusName += cmd.getName();
                }
                if (cmdStatusName.length() > 120) {
                    cmdStatusName = cmdStatusName.substring(0, 120);
                }
                commandStatus.setName(cmdStatusName);
                if (null != instanceIn.getProgramFile()) {
                    commandStatus.setProgramFile(instanceIn.getProgramFile());
                }
                if (null != instanceIn.getProgramIndex()) {
                    commandStatus.setProgramIndex(instanceIn.getProgramIndex());
                }
                if (null != instanceIn.getProgramLength()) {
                    commandStatus.setProgramLength(instanceIn.getProgramLength());
                }
                commandStatus.setCommandState(CommandStateEnumType.CRCL_WORKING);
                if (cmd instanceof ConfigureStatusReportType) {
                    state.filterSettings.setConfigureStatusReport((ConfigureStatusReportType) cmd);
                    final ConfigureStatusReportType configStatusCmd = (ConfigureStatusReportType) cmd;
                    if (configStatusCmd.isReportJointStatuses() && null == serverSideStatus.getJointStatuses()) {
                        serverSideStatus.setJointStatuses(new JointStatusesType());
                    }
                    if (configStatusCmd.isReportPoseStatus() && null == serverSideStatus.getPoseStatus()) {
                        serverSideStatus.setPoseStatus(new PoseStatusType());
                    }
                    if (configStatusCmd.isReportSensorsStatus() && null == serverSideStatus.getSensorStatuses()) {
                        serverSideStatus.setSensorStatuses(new SensorStatusesType());
                    }
                    if (configStatusCmd.isReportSettingsStatus() && null == serverSideStatus.getSettingsStatus()) {
                        serverSideStatus.setSettingsStatus(new SettingsStatusType());
                    }
                    commandStatus.setCommandState(CommandStateEnumType.CRCL_DONE);
                    return true;
                } else if (cmd instanceof ConfigureJointReportsType) {
                    ConfigureJointReportsType cjrt = (ConfigureJointReportsType) cmd;
                    if (cjrt.isResetAll()) {
                        state.filterSettings.clearConfigJointsReportMap();
                    }
                    state.filterSettings.configureJointReports(cjrt.getConfigureJointReport());
                    commandStatus.setCommandState(CommandStateEnumType.CRCL_DONE);
                    return true;
                }
                if (automaticallyConvertUnits) {
                    if (cmd instanceof SetLengthUnitsType) {
                        SetLengthUnitsType setLengthUnitsCmd = (SetLengthUnitsType) cmd;
                        state.filterSettings.getClientUserSet().setLengthUnit(setLengthUnitsCmd.getUnitName());
                        commandStatus.setCommandState(CommandStateEnumType.CRCL_DONE);
                        return true;
                    } else if (cmd instanceof SetAngleUnitsType) {
                        SetAngleUnitsType setAngleUnitsCmd = (SetAngleUnitsType) cmd;
                        state.filterSettings.getClientUserSet().setAngleUnit(setAngleUnitsCmd.getUnitName());
                        commandStatus.setCommandState(CommandStateEnumType.CRCL_DONE);
                        return true;
                    } else if (cmd instanceof SetForceUnitsType) {
                        SetForceUnitsType setForceUnitsCmd = (SetForceUnitsType) cmd;
                        state.filterSettings.getClientUserSet().setForceUnit(setForceUnitsCmd.getUnitName());
                        return true;
                    } else if (cmd instanceof SetTorqueUnitsType) {
                        SetTorqueUnitsType setTorqueUnitsCmd = (SetTorqueUnitsType) cmd;
                        state.filterSettings.getClientUserSet().setTorqueUnit(setTorqueUnitsCmd.getUnitName());
                        commandStatus.setCommandState(CommandStateEnumType.CRCL_DONE);
                        return true;
                    } else if (cmd instanceof MoveToType) {
                        MoveToType moveToCmdIn = (MoveToType) cmd;
                        MoveToType moveToCmdOut = new MoveToType();
                        moveToCmdOut.setCommandID(moveToCmdIn.getCommandID());
                        moveToCmdOut.setName(moveToCmdIn.getName());
                        moveToCmdOut.setMoveStraight(moveToCmdIn.isMoveStraight());
                        moveToCmdOut.setEndPosition(CRCLPosemath.copy(moveToCmdIn.getEndPosition()));
                        PointType outPoint = moveToCmdOut.getEndPosition().getPoint();
                        PointType inPoint = moveToCmdIn.getEndPosition().getPoint();
                        outPoint.setX(state.filterSettings.convertLengthToServer(inPoint.getX()));
                        outPoint.setY(state.filterSettings.convertLengthToServer(inPoint.getY()));
                        outPoint.setZ(state.filterSettings.convertLengthToServer(inPoint.getZ()));
                        CRCLCommandInstanceType newCommandInstance = createNewCommandInstance(moveToCmdOut, instanceIn);
                        completeHandleEvent(CRCLServerSocketEvent.commandRecieved(state, newCommandInstance));
                        return true;
                    } else if (cmd instanceof MoveThroughToType) {
                        MoveThroughToType moveThroughToCmdIn = (MoveThroughToType) cmd;
                        MoveThroughToType moveThroughToCmdOut = new MoveThroughToType();
                        moveThroughToCmdOut.setCommandID(moveThroughToCmdIn.getCommandID());
                        moveThroughToCmdOut.setName(moveThroughToCmdIn.getName());
                        moveThroughToCmdOut.setMoveStraight(moveThroughToCmdIn.isMoveStraight());
                        final List<PoseType> waypointInList = moveThroughToCmdIn.getWaypoint();
                        final List<PoseType> waypointOutList = moveThroughToCmdOut.getWaypoint();
                        for (int i = 0; i < moveThroughToCmdIn.getNumPositions() && i < waypointInList.size(); i++) {
                            PoseType wayPointInI = waypointInList.get(i);
                            PoseType wayPointOutI = waypointOutList.get(i);
                            PointType outPoint = wayPointOutI.getPoint();
                            PointType inPoint = wayPointInI.getPoint();
                            outPoint.setX(state.filterSettings.convertLengthToServer(inPoint.getX()));
                            outPoint.setY(state.filterSettings.convertLengthToServer(inPoint.getY()));
                            outPoint.setZ(state.filterSettings.convertLengthToServer(inPoint.getZ()));
                        }
                        CRCLCommandInstanceType newCommandInstance = createNewCommandInstance(moveThroughToCmdOut, instanceIn);
                        completeHandleEvent(CRCLServerSocketEvent.commandRecieved(state, newCommandInstance));
                        return true;
                    } else if (cmd instanceof SetTransSpeedType) {
                        SetTransSpeedType setTransSpeedCmdIn = (SetTransSpeedType) cmd;
                        SetTransSpeedType setTransSpeedCmdOut = new SetTransSpeedType();
                        final TransSpeedType transSpeedIn = setTransSpeedCmdIn.getTransSpeed();
                        if (transSpeedIn instanceof TransSpeedAbsoluteType) {
                            TransSpeedAbsoluteType transSpeedAbsIn = (TransSpeedAbsoluteType) transSpeedIn;
                            TransSpeedAbsoluteType transSpeedAbsOut = new TransSpeedAbsoluteType();
                            setTransSpeedCmdOut.setCommandID(setTransSpeedCmdIn.getCommandID());
                            setTransSpeedCmdOut.setName(setTransSpeedCmdIn.getName());
                            transSpeedAbsOut.setSetting(state.filterSettings.convertLengthToServer(transSpeedAbsIn.getSetting()));
                            setTransSpeedCmdOut.setTransSpeed(transSpeedAbsOut);
                            CRCLCommandInstanceType newCommandInstance = createNewCommandInstance(setTransSpeedCmdOut, instanceIn);
                            completeHandleEvent(CRCLServerSocketEvent.commandRecieved(state, newCommandInstance));
                            return true;
                        } else {
                            return false;
                        }

                    } else if (cmd instanceof SetRotSpeedType) {
                        SetRotSpeedType setRotSpeedCmdIn = (SetRotSpeedType) cmd;
                        SetRotSpeedType setRotSpeedCmdOut = new SetRotSpeedType();
                        final RotSpeedType rotSpeedIn = setRotSpeedCmdIn.getRotSpeed();
                        if (rotSpeedIn instanceof RotSpeedAbsoluteType) {
                            RotSpeedAbsoluteType rotSpeedAbsIn = (RotSpeedAbsoluteType) rotSpeedIn;
                            RotSpeedAbsoluteType rotSpeedAbsOut = new RotSpeedAbsoluteType();
                            setRotSpeedCmdOut.setCommandID(setRotSpeedCmdIn.getCommandID());
                            setRotSpeedCmdOut.setName(setRotSpeedCmdIn.getName());
                            rotSpeedAbsOut.setSetting(state.filterSettings.convertAngleToServer(rotSpeedAbsIn.getSetting()));
                            setRotSpeedCmdOut.setRotSpeed(rotSpeedAbsOut);
                            CRCLCommandInstanceType newCommandInstance = createNewCommandInstance(setRotSpeedCmdOut, instanceIn);
                            completeHandleEvent(CRCLServerSocketEvent.commandRecieved(state, newCommandInstance));
                            return true;
                        } else {
                            return false;
                        }

                    } else if (cmd instanceof SetTransAccelType) {
                        SetTransAccelType setTransAccelCmdIn = (SetTransAccelType) cmd;
                        SetTransAccelType setTransAccelCmdOut = new SetTransAccelType();
                        final TransAccelType transAccelIn = setTransAccelCmdIn.getTransAccel();
                        if (transAccelIn instanceof TransAccelAbsoluteType) {
                            TransAccelAbsoluteType transAccelAbsIn = (TransAccelAbsoluteType) transAccelIn;
                            TransAccelAbsoluteType transAccelAbsOut = new TransAccelAbsoluteType();
                            setTransAccelCmdOut.setCommandID(setTransAccelCmdIn.getCommandID());
                            setTransAccelCmdOut.setName(setTransAccelCmdIn.getName());
                            transAccelAbsOut.setSetting(state.filterSettings.convertLengthToServer(transAccelAbsIn.getSetting()));
                            setTransAccelCmdOut.setTransAccel(transAccelAbsOut);
                            CRCLCommandInstanceType newCommandInstance = createNewCommandInstance(setTransAccelCmdOut, instanceIn);
                            completeHandleEvent(CRCLServerSocketEvent.commandRecieved(state, newCommandInstance));
                            return true;
                        } else {
                            return false;
                        }

                    } else if (cmd instanceof SetRotAccelType) {
                        SetRotAccelType setRotAccelCmdIn = (SetRotAccelType) cmd;
                        SetRotAccelType setRotAccelCmdOut = new SetRotAccelType();
                        final RotAccelType rotAccelIn = setRotAccelCmdIn.getRotAccel();
                        if (rotAccelIn instanceof RotAccelAbsoluteType) {
                            RotAccelAbsoluteType rotAccelAbsIn = (RotAccelAbsoluteType) rotAccelIn;
                            RotAccelAbsoluteType rotAccelAbsOut = new RotAccelAbsoluteType();
                            setRotAccelCmdOut.setCommandID(setRotAccelCmdIn.getCommandID());
                            setRotAccelCmdOut.setName(setRotAccelCmdIn.getName());
                            rotAccelAbsOut.setSetting(state.filterSettings.convertAngleToServer(rotAccelAbsIn.getSetting()));
                            setRotAccelCmdOut.setRotAccel(rotAccelAbsOut);
                            CRCLCommandInstanceType newCommandInstance = createNewCommandInstance(setRotAccelCmdOut, instanceIn);
                            completeHandleEvent(CRCLServerSocketEvent.commandRecieved(state, newCommandInstance));
                            return true;
                        } else {
                            return false;
                        }

                    }
                }
                if (automaticallyHandleSensorServers) {
                    if (cmd instanceof EnableSensorStatusType) {
                        EnableSensorStatusType enableSensorCmd = (EnableSensorStatusType) cmd;
                        for (SensorServerFinderInterface finder : sensorFinders) {
                            final String sensorID = enableSensorCmd.getSensorID();
                            SensorServerInterface sensorSvr = finder.findSensorServer(sensorID, enableSensorCmd.getSensorOption());
                            if (null != sensorSvr) {
                                SensorServerInterface oldSensorServr = this.sensorServers.get(sensorID);
                                if (null != oldSensorServr) {
                                    try {
                                        oldSensorServr.close();
                                    } catch (Exception ex) {
                                        Logger.getLogger(CRCLServerSocket.class.getName()).log(Level.SEVERE, null, ex);
                                        if (ex instanceof RuntimeException) {
                                            throw (RuntimeException) ex;
                                        } else {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                }
                                this.sensorServers.put(sensorID, sensorSvr);
                                break;
                            }
                        }
                        return true;
                    } else if (cmd instanceof DisableSensorStatusType) {
                        DisableSensorStatusType disableSensorCmd = (DisableSensorStatusType) cmd;
                        SensorServerInterface sensorSvr = sensorServers.remove(disableSensorCmd.getSensorID());
                        if (null != sensorSvr) {
                            try {
                                sensorSvr.close();
                            } catch (Exception ex) {
                                Logger.getLogger(CRCLServerSocket.class.getName()).log(Level.SEVERE, "", ex);
                                if (ex instanceof RuntimeException) {
                                    throw (RuntimeException) ex;
                                } else {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private CRCLCommandInstanceType createNewCommandInstance(CRCLCommandType newCRCLCommand, final CRCLCommandInstanceType instanceIn) {
        CRCLCommandInstanceType newCommandInstance = new CRCLCommandInstanceType();
        newCommandInstance.setCRCLCommand(newCRCLCommand);
        newCommandInstance.setName(instanceIn.getName());
        newCommandInstance.setProgramFile(instanceIn.getProgramFile());
        newCommandInstance.setProgramIndex(instanceIn.getProgramIndex());
        newCommandInstance.setProgramLength(instanceIn.getProgramLength());
        return newCommandInstance;
    }

    private void completeHandleEvent(final CRCLServerSocketEvent<STATE_TYPE> event) throws InterruptedException {
        for (int i = 0; i < listeners.size(); i++) {
            if (closing) {
                return;
            }
            final CRCLServerSocketEventListener<STATE_TYPE> l = listeners.get(i);
            if (null == callbackService) {
                l.accept(event);
            } else {
                callbackService.submit(new Runnable() {
                    @Override
                    public void run() {
                        if (closing) {
                            return;
                        }
                        l.accept(event);
                    }
                });
            }
        }
        if (queueEvents && !closing) {
            queue.put(event);
        }
        if (event.getException() instanceof SocketException
                || event.getException() instanceof EOFException) {
            try {
                CRCLSocket source = event.getSource();
                if (null != source) {
                    source.close();

                }
            } catch (IOException ex) {
                Logger.getLogger(CRCLServerSocket.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < clients.size(); i++) {
                CRCLServerClientInfo c = clients.get(i);
                if (Objects.equals(c.getSocket(), event.getSource())) {
                    clients.remove(c);
                }
            }
            throw new InterruptedException("Closing socket due to " + event.getException());
        }
        return;
    }

    public CRCLServerSocketEvent waitForEvent() throws InterruptedException {
        if (!started && !isRunning()) {
            throw new IllegalStateException("CRCLServerSocket must be running/started before call to waitForEvent.");
        }
        if (!queueEvents) {
            throw new IllegalStateException("queueEvents should be set before call to waitForEvent.");

        }
        return queue.take();
    }

    public List<CRCLServerSocketEvent<STATE_TYPE>> checkForEvents() {
        List<CRCLServerSocketEvent<STATE_TYPE>> l = new ArrayList<>();
        queue.drainTo(l);
        return l;
    }

    /*@Nullable*/
    private Selector selector;

    /**
     * Get the value of selector
     *
     * @return the value of selector
     */
    /*@Nullable*/
    public Selector getSelector() {
        return selector;
    }

    /**
     * Set the value of selector
     *
     * @param selector new value of selector
     */
    public void setSelector(Selector selector) {
        if (isRunning()) {
            throw new IllegalStateException("Can not set field when server is running.");
        }
        this.selector = selector;
    }

    /*@Nullable*/
    private SocketAddress localAddress;

    /*@Nullable*/
    private InetAddress bindAddress;

    /**
     * Get the value of bindAddress
     *
     * @return the value of bindAddress
     */
    /*@Nullable*/
    public InetAddress getBindAddress() {
        return bindAddress;
    }

    /**
     * Set the value of bindAddress
     *
     * @param bindAddress new value of bindAddress
     */
    public void setBindAddress(InetAddress bindAddress) {
        if (isRunning()) {
            throw new IllegalStateException("Can not set field when server is running.");
        }
        this.bindAddress = bindAddress;
    }

    private static int runcount = 0;

    private boolean multithreaded;

    /**
     * Get the value of multithreaded
     *
     * @return the value of multithreaded
     */
    public boolean isMultithreaded() {
        return multithreaded;
    }

    /**
     * Set the value of multithreaded
     *
     * @param multithreaded new value of multithreaded
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public void setMultithreaded(boolean multithreaded) throws IOException, InterruptedException {
        if (isRunning()) {
            throw new IllegalStateException("Can not set field when server is running.");
        }
        this.multithreaded = multithreaded;
        if (multithreaded) {
            if (null != serverSocketChannel) {
                serverSocketChannel.close();
                serverSocketChannel = null;
            }
            if (null != selector) {
                selector.close();
                selector = null;
            }
        } else {
            if (null != serverSocket) {
                serverSocket.close();
                serverSocket = null;
            }
            if (null != executorService) {
                executorService.shutdownNow();
                executorService.awaitTermination(1, TimeUnit.SECONDS);
                executorService = null;
            }
        }
    }

    private volatile boolean running = false;

    public boolean isRunning() {
        return running;
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            runServer();
        }
    };

    public Runnable getRunnable() {
        return runnable;
    }

    public final void runServer() {

        if (isRunning()) {
            throw new IllegalStateException("Can not start again when server is already running.");
        }
        running = true;
        try {
            if (this.closing) {
                return;
            }
            runcount++;
            for (CRCLServerClientInfo crclSocket : clients) {
                crclSocket.close();
            }
            clients.clear();
            if (null == localAddress) {
                localAddress = new InetSocketAddress(port);
            }
            if (multithreaded) {
                runMultiThreaded();
            } else {
                runSingleThreaded();
            }
        } catch (Throwable ex) {
            running = false;

            if (!closing) {
                Logger.getLogger(CRCLServerSocket.class
                        .getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        } finally {
            running = false;
        }
    }

    private UnitsTypeSet serverUnits = null;

    /**
     * Get the value of serverUnits
     *
     * @return the value of serverUnits
     */
    public UnitsTypeSet getServerUnits() {
        return serverUnits;
    }

    /**
     * Set the value of serverUnits
     *
     * @param serverUnits new value of serverUnits
     */
    public void setServerUnits(UnitsTypeSet serverUnits) {
        this.serverUnits = serverUnits;
    }

    private UnitsScaleSet serverToClientScales = null;

    /**
     * Get the value of serverToClientScales
     *
     * @return the value of serverToClientScales
     */
    public UnitsScaleSet getServerToClientScales() {
        return serverToClientScales;
    }

    /**
     * Set the value of serverToClientScales
     *
     * @param serverToClientScales new value of serverToClientScales
     */
    public void setServerToClientScales(UnitsScaleSet serverToClientScales) {
        this.serverToClientScales = serverToClientScales;
    }

    // @MonotonicNonNull
    private volatile Schema cmdSchema = null;

    // @MonotonicNonNull
    private volatile Schema statSchema = null;

    public Schema getCmdSchema() {
        return cmdSchema;
    }

    public void setCmdSchema(Schema cmdSchema) {
        this.cmdSchema = cmdSchema;
    }

    public Schema getStatSchema() {
        return statSchema;
    }

    public void setStatSchema(Schema statSchema) {
        this.statSchema = statSchema;
    }

    private final IdentityHashMap<CRCLSocket, STATE_TYPE> socketToStateMap
            = new IdentityHashMap<>();

    private boolean sendAllJointPositionsByDefault = true;

    /**
     * Get the value of sendAllJointPositionsByDefault
     *
     * @return the value of sendAllJointPositionsByDefault
     */
    public boolean isSendAllJointPositionsByDefault() {
        return sendAllJointPositionsByDefault;
    }

    /**
     * Set the value of sendAllJointPositionsByDefault
     *
     * @param sendAllJointPositionsByDefault new value of
     * sendAllJointPositionsByDefault
     */
    public void setSendAllJointPositionsByDefault(boolean sendAllJointPositionsByDefault) {
        this.sendAllJointPositionsByDefault = sendAllJointPositionsByDefault;
    }

    private void runSingleThreaded() throws Exception {
        ServerSocketChannel channelForRun = this.serverSocketChannel;
        if (null == channelForRun) {
            SocketAddress localAddressToBind = this.localAddress;
            if (null == localAddressToBind) {
                throw new IllegalStateException("null == localAddressToBind");
            }
            try {
                channelForRun = (ServerSocketChannel) ServerSocketChannel.open()
                        .bind(localAddressToBind)
                        .setOption(StandardSocketOptions.SO_REUSEADDR, true)
                        .configureBlocking(false);
                this.serverSocketChannel = channelForRun;
            } catch (BindException bindException) {
                if (localAddressToBind instanceof InetSocketAddress) {
                    InetSocketAddress loclaInetSocketAddress = (InetSocketAddress) localAddressToBind;
                    System.err.println("localAdrress = " + loclaInetSocketAddress.getHostString() + ":" + loclaInetSocketAddress.getPort());
                }
                throw new IOException(bindException);
            }
        }
        Selector selectorForRun = this.selector;
        if (null == selectorForRun) {
            selectorForRun = Selector.open();
            this.selector = selectorForRun;
        }
        if (null == channelForRun) {
            throw new IllegalStateException("serverSocketChannel==null");
        }

        if (null == selectorForRun) {
            throw new IllegalStateException("selectorForRun==null");
        }
        channelForRun.register(selectorForRun, SelectionKey.OP_ACCEPT);
        while (!closing && !Thread.currentThread().isInterrupted()) {
            selectorForRun.select();
            SelectionKey keys[] = getAndClearKeys(selectorForRun);
            for (int i = 0; i < keys.length; i++) {
                SelectionKey key = keys[i];

                if (key.channel() == channelForRun) {
                    SocketChannel clientSocketChannel
                            = channelForRun.accept();
                    if (null == clientSocketChannel) {
                        System.out.println("key = " + key);
                    } else {
                        clientSocketChannel
                                = (SocketChannel) clientSocketChannel.configureBlocking(false);
                        CRCLSocket crclSocket = new CRCLSocket(clientSocketChannel);
                        if (null != statSchema) {
                            crclSocket.setStatSchema(statSchema);
                        }
                        if (null != cmdSchema) {
                            crclSocket.setCmdSchema(cmdSchema);
                        }

                        SelectionKey newKey
                                = clientSocketChannel.register(selectorForRun, SelectionKey.OP_READ, crclSocket);
                        STATE_TYPE state = generateNewClientState(crclSocket);
                        if (null == state) {
                            throw new IllegalStateException("stateGenerator.generate(crclSocket) returned null");
                        }
                        clients.add(new CRCLServerClientInfo(crclSocket, null, state));
                        socketToStateMap.put(crclSocket, state);
                        handleEvent(CRCLServerSocketEvent.newClient(state));
                    }
                } else {
                    CRCLSocket crclSocket = (CRCLSocket) key.attachment();
                    STATE_TYPE state = socketToStateMap.get(crclSocket);
                    if (null == state) {
                        throw new IllegalStateException("ssocketToStateMap.get(crclSocket) returned null");
                    }
                    List<CRCLCommandInstanceType> cmdInstances;
                    try {
                        SocketChannel s = ((SocketChannel) key.channel());
                        try {
                            ByteBuffer bb = ByteBuffer.allocate(4096);
                            int readbytes = s.read(bb);
                            if (readbytes > 0) {
                                String string = new String(bb.array(), 0, readbytes);
                                cmdInstances = crclSocket.parseMultiCommandString(string, validate);
                                for (CRCLCommandInstanceType instance : cmdInstances) {
                                    handleEvent(CRCLServerSocketEvent.commandRecieved(state, instance));
                                }
                            }
                        } catch (Exception ex) {
                            if (!closing && (!(ex instanceof ClosedChannelException) && !(ex.getCause() instanceof ClosedChannelException))) {
                                System.err.println("port =" + port + ",closing = " + closing);
                                Logger
                                        .getLogger(CRCLServerSocket.class
                                                .getName()).log(Level.SEVERE, "", ex);
                            }
                            try {
                                s.close();
                                selectorForRun.selectedKeys().remove(key);
                            } catch (Exception exception) {
                            }
                            handleEvent(CRCLServerSocketEvent.exceptionOccured(state, ex));
                            try {
                                crclSocket.close();
                            } catch (Exception exception) {
                            }
                        }
                    } catch (Exception ex2) {
                        if (!closing && (!(ex2 instanceof ClosedChannelException) && !(ex2.getCause() instanceof ClosedChannelException))) {
                            System.err.println("port =" + port + ",closing = " + closing);
                            Logger
                                    .getLogger(CRCLServerSocket.class
                                            .getName()).log(Level.SEVERE, "", ex2);
                        }
                        handleEvent(CRCLServerSocketEvent.exceptionOccured(state, ex2));
                    }
                }
            }
        }
    }

    private void setupNewClientState(STATE_TYPE state) {
        if (updateStatusRunnable != null && updateStatusRunCount == 0) {
            updateStatusRunnable.run();
            updateStatusRunCount++;
        }
        if (automaticallyConvertUnits && null != serverToClientScales) {
            state.filterSettings.setServerToClientScaleSet(serverToClientScales);
        }
        if (automaticallyConvertUnits && null != serverUnits) {
            state.filterSettings.setServerUserSet(serverUnits);
        }
        if (sendAllJointPositionsByDefault && null != serverSideStatus && null != serverSideStatus.getJointStatuses()) {
            final List<JointStatusType> jointStatusList = serverSideStatus.getJointStatuses().getJointStatus();
            List<ConfigureJointReportType> cjrList = new ArrayList<>();
            for (int j = 0; j < jointStatusList.size(); j++) {
                JointStatusType jst = jointStatusList.get(j);
                ConfigureJointReportType cjr = new ConfigureJointReportType();
                cjr.setJointNumber(jst.getJointNumber());
                cjr.setReportPosition(true);
                cjrList.add(cjr);
            }
            state.filterSettings.configureJointReports(cjrList);
        }
    }

    @SuppressWarnings("nullness")
    private SelectionKey[] getAndClearKeys(Selector selectorForRun) {
        SelectionKey keys[] = new SelectionKey[0];
        synchronized (selectorForRun) {
            Set<SelectionKey> keySet = selectorForRun.selectedKeys();
            synchronized (keySet) {
                keys = keySet.toArray(keys);
                keySet.removeAll(Arrays.asList(keys));
            }
        }
        return keys;
    }

    private int backlog = 0;

    /**
     * Get the value of backlog
     *
     * @return the value of backlog
     */
    public int getBacklog() {
        return backlog;
    }

    /**
     * Set the value of backlog
     *
     * @param backlog new value of backlog
     * @throws java.io.IOException
     */
    public void setBacklog(int backlog) throws IOException {
        if (isRunning()) {
            throw new IllegalStateException("Can not start again when server is already running.");
        }
        this.backlog = backlog;
        if (serverSocket != null) {
            serverSocket.close();
            serverSocket = null;
        }
        if (serverSocketChannel != null) {
            serverSocketChannel.close();
            serverSocketChannel = null;
        }
    }

    private Socket acceptFromServerSocket() throws SocketException, IOException {
        ServerSocket acceptServerSocket = this.serverSocket;
        if (null == acceptServerSocket) {
            throw new IllegalStateException("serverSocket == null");
        }
        // This is an anoying hack around the problem that you can't rely on
        // serverSocket.accept() being interrupted.
        // This only generally needs to happen when shuting down the server
        int orig_timeout = acceptServerSocket.getSoTimeout();
        acceptServerSocket.setSoTimeout(500);
        Socket ret = null;
        boolean interrupted = false;
        try {
            interrupted = interrupted || Thread.currentThread().isInterrupted();
            while (ret == null && !closing && !interrupted) {
                try {
                    ret = acceptServerSocket.accept();
                } catch (SocketTimeoutException ignored) {
                }
            }
        } finally {
            acceptServerSocket.setSoTimeout(orig_timeout);
        }
        if (null == ret) {
            throw new IllegalStateException("acceptFromServerSocket returned null,closing=" + closing + ",interrupted=" + interrupted);
        }
        return ret;
    }

    private void runMultiThreaded() throws Exception {

        if (null != serverSocketChannel) {
            serverSocketChannel.close();
            serverSocketChannel = null;
        }
        if (null != selector) {
            selector.close();
            selector = null;
        }

        ExecutorService runMultiExecutorService = initExecutorService();
        if (null == runMultiExecutorService) {
            throw new IllegalStateException("null == runMultiExcututorService");
        }
        if (null == serverSocket) {
            if (null != bindAddress) {
                serverSocket = new ServerSocket(port, backlog, bindAddress);
            } else {
                serverSocket = new ServerSocket(port);
            }
        }
        serverSocket.setReuseAddress(true);
        while (!closing && !Thread.currentThread().isInterrupted()) {

            Socket socket = acceptFromServerSocket();
            if (null == socket) {
                return;
            }
            final CRCLSocket crclSocket = new CRCLSocket(socket, cmdSchema, statSchema, null);
            STATE_TYPE state = generateNewClientState(crclSocket);
            Future<?> future = runMultiExecutorService.submit(createHandleClientThreadRunnable(state));
            clients.add(new CRCLServerClientInfo(crclSocket, future, state));
            handleEvent(CRCLServerSocketEvent.newClient(state));
        }
    }

    private STATE_TYPE generateNewClientState(final CRCLSocket crclSocket) {
        STATE_TYPE state = stateGenerator.generate(crclSocket);
        setupNewClientState(state);
        return state;
    }

    private Runnable createHandleClientThreadRunnable(final STATE_TYPE state) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    CRCLSocket crclSocket = state.getCs();
                    while (!closing
                            && crclSocket.isConnected()
                            && !Thread.currentThread().isInterrupted()) {
                        try {
                            CRCLCommandInstanceType instance = crclSocket.readCommand(validate);
                            handleEvent(CRCLServerSocketEvent.commandRecieved(state, instance));
                        } catch (Exception ex) {
                            handleEvent(CRCLServerSocketEvent.exceptionOccured(state, ex));

                        }
                    }
                } catch (Exception ex1) {
                    Logger.getLogger(CRCLServerSocket.class
                            .getName()).log(Level.SEVERE, null, ex1);
                }
            }
        };
    }
    private String threadNamePrefix = "CRCLServerSocket";

    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }

    public void setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }

    private volatile int updateStatusRunCount = 0;
    private Runnable updateStatusRunnable;

    /**
     * Get the value of updateStatusRunnable
     *
     * @return the value of updateStatusRunnable
     */
    public Runnable getUpdateStatusRunnable() {
        return updateStatusRunnable;
    }

    /**
     * Set the value of updateStatusRunnable
     *
     * @param updateStatusRunnable new value of updateStatusRunnable
     */
    public void setUpdateStatusRunnable(Runnable updateStatusRunnable) {
        this.updateStatusRunnable = updateStatusRunnable;
    }

    private ExecutorService initExecutorService() {
        ExecutorService es = this.executorService;
        if (null != es) {
            return es;
        }
        ExecutorService newExecutorService = Executors.newCachedThreadPool(new ThreadFactory() {

            int num = 0;

            @Override
            public Thread newThread(Runnable r) {
                num++;
                Thread t = new Thread(r, threadNamePrefix + "_" + port + "_" + runcount + "_" + num);
//                        t.setDaemon(true);
                return t;
            }
        });
        this.executorService = newExecutorService;
        return newExecutorService;
    }

    public CRCLServerSocket(CRCLServerSocketStateGenerator<STATE_TYPE> stateGenerator) throws IOException {
        this(CRCLSocket.DEFAULT_PORT, stateGenerator);
    }

    public CRCLServerSocket(int port, CRCLServerSocketStateGenerator<STATE_TYPE> stateGenerator) throws IOException {
        this(port, 0, stateGenerator);
    }

    public CRCLServerSocket(int port, int backlog, CRCLServerSocketStateGenerator<STATE_TYPE> stateGenerator) throws IOException {
        this(port, backlog, (new InetSocketAddress(port)).getAddress(), stateGenerator);
    }

    public CRCLServerSocket(int port, int backlog, InetAddress addr, CRCLServerSocketStateGenerator<STATE_TYPE> stateGenerator) throws IOException {
        this(port, backlog, addr, Boolean.getBoolean("CRCLServerSocket.multithreaded"), stateGenerator);
    }

    private static <STATE_TYPE extends CRCLServerClientState> Class<STATE_TYPE> classFromGenerator(CRCLServerSocketStateGenerator<STATE_TYPE> stateGenerator) {
        try {
            Class<?> stateGeneratorClass = stateGenerator.getClass();
            Method genMethod = stateGeneratorClass.getMethod("generate", CRCLSocket.class
            );
            @SuppressWarnings("unchecked")
            Class<STATE_TYPE> stateClassFromReturnType = (Class<STATE_TYPE>) ((Object) genMethod.getReturnType());
            return stateClassFromReturnType;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public CRCLServerSocket(int port, int backlog, InetAddress addr, boolean multithreaded, CRCLServerSocketStateGenerator<STATE_TYPE> stateGenerator) throws IOException {
        this(port, backlog, addr, multithreaded, stateGenerator, classFromGenerator(stateGenerator));
    }

    public CRCLServerSocket(int port, int backlog, InetAddress addr, boolean multithreaded, CRCLServerSocketStateGenerator<STATE_TYPE> stateGenerator, Class<STATE_TYPE> stateClass) {
        this.port = port;
        this.backlog = backlog;
        this.bindAddress = addr;
        this.localAddress = new InetSocketAddress(bindAddress, port);
        this.multithreaded = multithreaded;
        this.stateGenerator = stateGenerator;
        this.stateClass = stateClass;
        refreshSensorFinders();
    }

    private static final CRCLServerSocketStateGenerator<CRCLServerClientState> DEFAULT_STATE_GENERATOR
            = new CRCLServerSocketStateGenerator<CRCLServerClientState>() {
        @Override
        public CRCLServerClientState generate(CRCLSocket crclSocket) {
            return new CRCLServerClientState(crclSocket);
        }
    };

    static public CRCLServerSocket<CRCLServerClientState> newDefaultServer() throws IOException {
        return new CRCLServerSocket<>(DEFAULT_STATE_GENERATOR);
    }

    static public CRCLServerSocket<CRCLServerClientState> newDefaultServer(int port) throws IOException {
        return new CRCLServerSocket<>(port, DEFAULT_STATE_GENERATOR);
    }

    static public CRCLServerSocket<CRCLServerClientState> newDefaultServer(int port, int backlog) throws IOException {
        return new CRCLServerSocket<>(port, backlog, DEFAULT_STATE_GENERATOR);
    }

    static public CRCLServerSocket<CRCLServerClientState> newDefaultServer(int port, int backlog, InetAddress bindAddr) throws IOException {
        return new CRCLServerSocket<>(port, backlog, bindAddr, DEFAULT_STATE_GENERATOR);
    }

    static public CRCLServerSocket<CRCLServerClientState> newDefaultServer(int port, int backlog, InetAddress bindAddr, boolean multithreaded) throws IOException {
        return new CRCLServerSocket<>(port, backlog, bindAddr, multithreaded, DEFAULT_STATE_GENERATOR);
    }

    private boolean started = false;

    public Future<?> start() {
        if (isRunning()) {
            throw new IllegalStateException("Can not start again when server is already running.");
        }
        ExecutorService serviceFoStart = initExecutorService();
        started = true;
        return serviceFoStart.submit(this.runnable);
    }
}
