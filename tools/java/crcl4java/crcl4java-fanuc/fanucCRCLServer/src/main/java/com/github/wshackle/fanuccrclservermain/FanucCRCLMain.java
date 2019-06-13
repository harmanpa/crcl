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

import static com.github.wshackle.fanuc.robotneighborhood.ClassFactory.createFRCRobotNeighborhood;
import com.github.wshackle.fanuc.robotneighborhood.IRNRobot;
import com.github.wshackle.fanuc.robotneighborhood.IRNRobots;
import com.github.wshackle.fanuc.robotneighborhood.IRobotNeighborhood;
import com.github.wshackle.fanuc.robotserver.FRECurPositionConstants;
import com.github.wshackle.fanuc.robotserver.FREExecuteConstants;
import com.github.wshackle.fanuc.robotserver.FREGroupBitMaskConstants;
import com.github.wshackle.fanuc.robotserver.FREProgramTypeConstants;
import com.github.wshackle.fanuc.robotserver.FREStepTypeConstants;
import com.github.wshackle.fanuc.robotserver.FRETaskStatusConstants;
import com.github.wshackle.fanuc.robotserver.FRETypeCodeConstants;
import com.github.wshackle.fanuc.robotserver.IAlarm;
import com.github.wshackle.fanuc.robotserver.IAlarms;
import com.github.wshackle.fanuc.robotserver.IConfig;
import com.github.wshackle.fanuc.robotserver.ICurGroupPosition;
import com.github.wshackle.fanuc.robotserver.ICurPosition;
import com.github.wshackle.fanuc.robotserver.IIndGroupPosition;
import com.github.wshackle.fanuc.robotserver.IIndPosition;
import com.github.wshackle.fanuc.robotserver.IJoint;
import com.github.wshackle.fanuc.robotserver.IProgram;
import com.github.wshackle.fanuc.robotserver.IPrograms;
import com.github.wshackle.fanuc.robotserver.IRegNumeric;
import com.github.wshackle.fanuc.robotserver.IRobot2;
import com.github.wshackle.fanuc.robotserver.ISysGroupPosition;
import com.github.wshackle.fanuc.robotserver.ISysPosition;
import com.github.wshackle.fanuc.robotserver.ITPProgram;
import com.github.wshackle.fanuc.robotserver.ITask;
import com.github.wshackle.fanuc.robotserver.ITasks;
import com.github.wshackle.fanuc.robotserver.IVar;
import com.github.wshackle.fanuc.robotserver.IVars;
import com.github.wshackle.fanuc.robotserver.IXyzWpr;
import com4j.Com4jObject;
import com4j.ComException;
import crcl.base.ActuateJointType;
import crcl.base.ActuateJointsType;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLStatusType;
import crcl.base.CloseToolChangerType;
import crcl.base.CommandStateEnumType;
import crcl.base.CommandStatusType;
import crcl.base.ConfigureJointReportType;
import crcl.base.ConfigureJointReportsType;
import crcl.base.ConfigureStatusReportType;
import crcl.base.DwellType;
import crcl.base.EndCanonType;
import crcl.base.GetStatusType;
import crcl.base.GripperStatusType;
import crcl.base.InitCanonType;
import crcl.base.JointDetailsType;
import crcl.base.JointLimitType;
import crcl.base.JointSpeedAccelType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.MessageType;
import crcl.base.MoveThroughToType;
import crcl.base.MoveToType;
import crcl.base.OpenToolChangerType;
import crcl.base.ParallelGripperStatusType;
import crcl.base.PointType;
import crcl.base.PoseStatusType;
import crcl.base.PoseToleranceType;
import crcl.base.PoseType;
import crcl.base.RotSpeedAbsoluteType;
import crcl.base.RotSpeedRelativeType;
import crcl.base.RotSpeedType;
import crcl.base.SetAngleUnitsType;
import crcl.base.SetEndEffectorType;
import crcl.base.SetEndPoseToleranceType;
import crcl.base.SetForceUnitsType;
import crcl.base.SetLengthUnitsType;
import crcl.base.SetRotSpeedType;
import crcl.base.SetTransSpeedType;
import crcl.base.SettingsStatusType;
import crcl.base.StopMotionType;
import crcl.base.TransSpeedAbsoluteType;
import crcl.base.TransSpeedRelativeType;
import crcl.base.TransSpeedType;
import crcl.ui.XFutureVoid;
import crcl.utils.CRCLException;
import crcl.utils.CRCLPosemath;
import crcl.utils.CRCLSocket;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSlider;
import rcs.posemath.PmCartesian;
import rcs.posemath.PmException;
import rcs.posemath.PmRotationVector;
import rcs.posemath.PmRpy;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import static crcl.utils.CRCLPosemath.point;
import crcl.utils.Utils;
import crcl.utils.server.CRCLServerClientState;
import crcl.utils.server.CRCLServerSocket;
import crcl.utils.server.CRCLServerSocketEvent;
import crcl.utils.server.CRCLServerSocketEventListener;
import crcl.utils.server.CRCLServerSocketStateGenerator;
import crcl.utils.server.UnitsTypeSet;
import java.util.Iterator;
import java.util.concurrent.Future;
import javax.swing.SwingUtilities;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class FanucCRCLMain {

    private String remoteRobotHost;

    /**
     * Get the value of remoteRobotHost
     *
     * @return the value of remoteRobotHost
     */
    public String getRemoteRobotHost() {
        return remoteRobotHost;
    }

    /**
     * Set the value of remoteRobotHost
     *
     * @param remoteRobotHost new value of remoteRobotHost
     */
    public XFutureVoid setRemoteRobotHost(String remoteRobotHost) {
        if (this.remoteRobotHost != remoteRobotHost) {
            this.remoteRobotHost = remoteRobotHost;
            if (null != displayInterface) {
                displayInterface.getjTextFieldHostName().setText(remoteRobotHost);
            }
            this.disconnectRemoteRobot();
            return this.connectRemoteRobot();
        } else {
            return XFutureVoid.completedFutureWithName("setRemoteRobotHost.nochange");
        }
    }

    private int localPort;

    /**
     * Get the value of localPort
     *
     * @return the value of localPort
     */
    public int getLocalPort() {
        return localPort;
    }

    /**
     * Set the value of localPort
     *
     * @param localPort new value of localPort
     */
    public void setLocalPort(int localPort) {
        this.localPort = localPort;
    }

    public IVar getOverideVar() {
        return this.overrideVar;
    }

    public IVar getMorSafetyStatVar() {
        return this.morSafetyStatVar;
    }

    public IVar getMoveGroup1RobMove() {
        return moveGroup1RobMoveVar;
    }

    public IVar getMoveGroup1ServoReadyVar() {
        return moveGroup1ServoReadyVar;
    }

    public IRobot2 getRobot() {
        return robot;
    }

    private float xMax;
    private float xMin;
    private float yMax;
    private float yMin;
    private float zMax;
    private float zMin;
    private float border1 = 10;

    private void limitAndUpdatePos(ISysGroupPosition pos) {

        IXyzWpr posXyzWpr = pos.formats(FRETypeCodeConstants.frXyzWpr).queryInterface(IXyzWpr.class);
        if (null == posXyzWpr) {
            throw new IllegalArgumentException("Can't get xyzwpr for pos");
        }
        boolean changed = false;
        PmCartesian cart = new PmCartesian(posXyzWpr.x(), posXyzWpr.y(), posXyzWpr.z());
        float xMinEffective = xMin + border1;
        float xMaxEffective = xMax - border1;
        float yMinEffective = yMin + border1;
        float yMaxEffective = yMax - border1;
        float zMinEffective = zMin + border1;
        float zMaxEffective = zMax - border1;

        if (cart.x > xMaxEffective) {
            posXyzWpr.x(xMaxEffective);
            showWarning("X move of " + cart.x + " limited to max = " + xMaxEffective);
            changed = true;
        } else if (cart.x < xMinEffective) {
            posXyzWpr.x(xMinEffective);
            showWarning("X move of " + cart.x + " limited to min= " + xMinEffective);
            changed = true;
        }
        if (cart.y > yMaxEffective) {
            posXyzWpr.y(yMaxEffective);
            showWarning("Y move of " + cart.y + " limited to max = " + yMaxEffective);
            changed = true;
        } else if (cart.y < yMinEffective) {
            posXyzWpr.y(yMinEffective);
            showWarning("Y move of " + cart.y + " limited to min = " + yMinEffective);
            changed = true;
        }
        if (cart.z > zMaxEffective) {
            posXyzWpr.z(zMaxEffective);
            showWarning("Z move of " + cart.z + " limited to max = " + zMaxEffective);
            changed = true;
        } else if (cart.z < zMinEffective) {
            posXyzWpr.z(zMinEffective);
            showWarning("Z move of " + cart.z + " limited to min = " + zMinEffective);
            changed = true;
        }

        if (changed) {
            PmCartesian newcart = new PmCartesian(posXyzWpr.x(), posXyzWpr.y(), posXyzWpr.z());
        }
        pos.update();
    }

    public XFutureVoid startDisplayInterface() {
        XFutureVoid ret = new XFutureVoid("startDisplayInterface");
        SwingUtilities.invokeLater(() -> {
            if (null == displayInterface) {
                displayInterface = new FanucCRCLServerJFrame();
            }
            JSlider sliderOv = displayInterface.getjSliderOverride();
            sliderOv.addChangeListener(e -> {
                IVar var = FanucCRCLMain.this.getOverideVar();
                if (null != var) {
                    var.value(sliderOv.getValue());
                } else {
                    showError("Can NOT change override since robot is not initialized.");
                }
            });
            JSlider sliderMaxOv = displayInterface.getjSliderMaxOverride();
            sliderMaxOv.setValue(100);
            sliderMaxOv.addChangeListener(e -> {
                IVar var = FanucCRCLMain.this.getOverideVar();
                if (null != var) {
                    if (Integer.valueOf(var.value().toString()) > sliderMaxOv.getValue()) {
                        var.value(sliderMaxOv.getValue());
                    }
                }
                if (sliderOv.getValue() > sliderMaxOv.getValue()) {
                    sliderOv.setValue(sliderMaxOv.getValue());
                }
                sliderOv.setMaximum(sliderMaxOv.getValue());
                maxRelativeSpeed = sliderMaxOv.getValue();
            });
            displayInterface.setMain(this);
            displayInterface.setPrograms(tpPrograms);
            displayInterface.getjMenuItemReconnectRobot().addActionListener(e -> {
                disconnectRemoteRobot();
                connectRemoteRobot();
            });
            displayInterface.getjMenuItemResetAlarms().addActionListener(e -> {
                IRobot2 robot = FanucCRCLMain.this.getRobot();
                if (null != robot) {
                    robot.alarms().reset();
                    robot.tasks().abortAll(true);
                    this.lastRobotIsConnected = true;
                    this.lastServoReady = true;
                    this.last_safety_stat = 0;
                } else {
                    showError("Can NOT reset alarms since robot is not initialized.");
                }
            });
            displayInterface.getjRadioButtonUseDirectIP().setSelected(!preferRobotNeighborhood);
            displayInterface.getjRadioButtonUseRobotNeighborhood().setSelected(preferRobotNeighborhood);
            displayInterface.getjTextFieldHostName().setText(remoteRobotHost);
            displayInterface.getjTextFieldRobotNeighborhoodPath().setText(neighborhoodname);
            displayInterface.getjRadioButtonUseDirectIP().addActionListener(e -> {
                FanucCRCLMain.this.setPreferRobotNeighborhood(displayInterface.getjRadioButtonUseRobotNeighborhood().isSelected());
            });
            displayInterface.getjRadioButtonUseRobotNeighborhood().addActionListener(e -> {
                FanucCRCLMain.this.setPreferRobotNeighborhood(displayInterface.getjRadioButtonUseRobotNeighborhood().isSelected());
            });
            displayInterface.getjTextFieldHostName().addActionListener(e -> {
                FanucCRCLMain.this.setRemoteRobotHost(displayInterface.getjTextFieldHostName().getText());
            });
            displayInterface.getjTextFieldRobotNeighborhoodPath().addActionListener(e -> {
                FanucCRCLMain.this.setNeighborhoodname(displayInterface.getjTextFieldRobotNeighborhoodPath().getText());
            });
            displayInterface.getjTextFieldLimitSafetyBumper().setText("" + border1);
            displayInterface.getjTextFieldLimitSafetyBumper().addActionListener(e -> {
                FanucCRCLMain.this.border1 = Float.valueOf(displayInterface.getjTextFieldLimitSafetyBumper().getText());
            });
            displayInterface.setVisible(true);
            ret.complete();
        });
        return ret;
    }

    public XFutureVoid start(boolean preferRobotNeighborhood, String neighborhoodname, String remoteRobotHost, int localPort) {
        try {
            this.preferRobotNeighborhood = preferRobotNeighborhood;
            this.neighborhoodname = neighborhoodname;
            this.remoteRobotHost = remoteRobotHost;
            this.localPort = localPort;
            crclServerSocket.setPort(localPort);
            return connectRemoteRobot()
                    .thenRun(this::wrappedStartCrclServer);
        } catch (Exception exception) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                    "start(" + preferRobotNeighborhood + "," + neighborhoodname + "," + remoteRobotHost + "," + localPort + ") : " + exception.getMessage(),
                    exception);
            showError(exception.toString());
            throw new RuntimeException(exception);
        }
    }

    private boolean validate = false;

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
        this.validate = validate;
    }

//    private LengthUnitEnumType lengthUnit = LengthUnitEnumType.MILLIMETER;
    private double lengthScale = 1.0;

//    /**
//     * Get the value of lengthUnit
//     *
//     * @return the value of lengthUnit
//     */
//    public LengthUnitEnumType getLengthUnit() {
//        return lengthUnit;
//    }
//
//    /**
//     * Set the value of lengthUnit
//     *
//     * @param lengthUnit new value of lengthUnit
//     */
//    public void setLengthUnit(LengthUnitEnumType lengthUnit) {
//        this.lengthUnit = lengthUnit;
//        switch (lengthUnit) {
//            case METER:
//                lengthScale = 1000.0;
//                break;
//
//            case MILLIMETER:
//                lengthScale = 1.0;
//                break;
//
//            case INCH:
//                lengthScale = 25.4;
//                break;
//        }
//    }
    long statusUpdateTime = 0;
    private final CRCLStatusType status = new CRCLStatusType();
    volatile long moveDoneTime = 0;
    volatile boolean lastCheckAtPosition = false;
    volatile int moveChecksDone = 0;
    private final double lastJointPosArray[] = new double[10];
    private final long lastJointPosTimeArray[] = new long[10];

    public CRCLStatusType getStatus() {
        return status;
    }
    double lastMaxJointDiff = Double.MAX_VALUE;

    private final PoseStatusType poseStatus = new PoseStatusType();

    /**
     * Get the value of poseStatus
     *
     * @return the value of poseStatus
     */
    public PoseStatusType getPoseStatus() {
        return poseStatus;
    }

    private final SettingsStatusType settingsStatus = new SettingsStatusType();

    /**
     * Get the value of settingsStatus
     *
     * @return the value of settingsStatus
     */
    public SettingsStatusType getSettingsStatus() {
        return settingsStatus;
    }

    private JointStatusesType jointStatuses = new JointStatusesType();

    /**
     * Get the value of jointStatuses
     *
     * @return the value of jointStatuses
     */
    public JointStatusesType getJointStatuses() {
        return jointStatuses;
    }

    /**
     * Set the value of jointStatuses
     *
     * @param jointStatuses new value of jointStatuses
     */
    public void setJointStatuses(JointStatusesType jointStatuses) {
        this.jointStatuses = jointStatuses;
    }

    private boolean reportPoseStatus = true;

    /**
     * Get the value of reportPoseStatus
     *
     * @return the value of reportPoseStatus
     */
    public boolean isReportPoseStatus() {
        return reportPoseStatus;
    }

    /**
     * Set the value of reportPoseStatus
     *
     * @param reportPoseStatus new value of reportPoseStatus
     */
    public void setReportPoseStatus(boolean reportPoseStatus) {
        this.reportPoseStatus = reportPoseStatus;
        status.setPoseStatus(reportPoseStatus ? poseStatus : null);
    }

    private boolean reportSettingsStatus = true;

    /**
     * Get the value of reportSettingsStatus
     *
     * @return the value of reportSettingsStatus
     */
    public boolean isReportSettingsStatus() {
        return reportSettingsStatus;
    }

    /**
     * Set the value of reportSettingsStatus
     *
     * @param reportSettingsStatus new value of reportSettingsStatus
     */
    public void setReportSettingsStatus(boolean reportSettingsStatus) {
        this.reportSettingsStatus = reportSettingsStatus;
        status.setSettingsStatus(reportSettingsStatus ? settingsStatus : null);
    }

    private boolean reportJointStatus = true;

    /**
     * Get the value of reportJointStatus
     *
     * @return the value of reportJointStatus
     */
    public boolean isReportJointStatus() {
        return reportJointStatus;
    }

    /**
     * Set the value of reportJointStatus
     *
     * @param reportJointStatus new value of reportJointStatus
     */
    public void setReportJointStatus(boolean reportJointStatus) {
        this.reportJointStatus = reportJointStatus;
        status.setJointStatuses(reportJointStatus ? jointStatuses : null);
    }

    private volatile boolean firstUpdate = true;

    public synchronized CRCLStatusType readCachedStatusFromRobot() throws PmException {

        CRCLStatusType status;
        if (System.currentTimeMillis() - lastUpdateStatusTime > 30) {
            status = readStatusFromRobot(firstUpdate);
            firstUpdate = false;
            lastUpdateStatusTime = System.currentTimeMillis();
            return status;
        } else {
            status = getStatus();
        }
        if (null == jointStatuses) {
            throw new RuntimeException("null == jointStatuses");
        }
        if (null == jointStatuses || jointStatuses.getJointStatus().size() < 1 || !reportJointStatus) {
            status.setJointStatuses(null);
            throw new RuntimeException("jointStatuses.().size()=" + jointStatuses.getJointStatus().size() + ", reportJointStatus=" + reportJointStatus);

        } else {
            status.setJointStatuses(jointStatuses);
        }
        return status;
    }

    boolean lastRobotIsConnected = true;
    private volatile Future lastReadStatusFromRobotFuture = null;
    
    public CRCLStatusType readStatusFromRobot(boolean inline) {
        if (null == robot) {
            setCommandState(CommandStateEnumType.CRCL_ERROR);
            if (lastRobotIsConnected) {
                showError("Robot is NOT connected.");
            }
            lastRobotIsConnected = false;
            return status;
        }
        if (!robotIsConnected) {
            setCommandState(CommandStateEnumType.CRCL_ERROR);
            if (lastRobotIsConnected) {
                showError("Robot is NOT connected.");
            }
            lastRobotIsConnected = false;
            return status;
        }

        if (!inline) {
            if (null == robotService) {
                robotService = Executors.newSingleThreadExecutor(daemonThreadFactory);
            }
            Future readStatusFuture = robotService.submit(this::readStatusFromRobotInternal);
            this.lastReadStatusFromRobotFuture = readStatusFuture;
        } else {
            readStatusFromRobotInternal();
        }
        return status;
    }

    public boolean isConnected() {
        return (null != robot && robotIsConnected);
    }

    public XFutureVoid setConnected(boolean connected) {
        if (connected != isConnected()) {
            if (connected) {
                return connectRemoteRobot();
            } else {
                disconnectRemoteRobot();
                return XFutureVoid.completedFutureWithName("setConnected.disconnected");
            }
        } else {
            return XFutureVoid.completedFutureWithName("setConnected.nochange");
        }
    }

    private boolean lastMotionProgramRunning() {
        if (null == lastRunMotionProgram) {
            return false;
        }
        return getTaskList(false)
                .map(taskList -> taskList.stream().anyMatch((Object[] objects) -> objects.length > 0 && objects[0].toString().equals(lastRunMotionProgram.name())))
                .orElse(false);
    }

    public static PoseType lastDoneMovePose = null;
    public static long lastDoneMoveCommandID = -582;

    private boolean holdingObject;

    /**
     * Get the value of holdingObject
     *
     * @return the value of holdingObject
     */
    public boolean isHoldingObject() {
        return holdingObject;
    }

    /**
     * Set the value of holdingObject
     *
     * @param holdingObject new value of holdingObject
     */
    public void setHoldingObject(boolean holdingObject) {
        this.holdingObject = holdingObject;
    }

    private boolean holdingObjectKnown;

    /**
     * Get the value of holdingObjectKnown
     *
     * @return the value of holdingObjectKnown
     */
    public boolean isHoldingObjectKnown() {
        return holdingObjectKnown;
    }

    /**
     * Set the value of holdingObjectKnown
     *
     * @param holdingObjectKnown new value of holdingObjectKnown
     */
    public void setHoldingObjectKnown(boolean holdingObjectKnown) {
        this.holdingObjectKnown = holdingObjectKnown;
    }

    public PoseType getPose() {
        return poseStatus.getPose();
    }

    public void setPose(PoseType newPose) {
        poseStatus.setPose(newPose);
    }

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmss.SSS");

    public static String getDateTimeString() {
        Date date = new Date();
        return dateFormat.format(date);
    }

    private File moveLogFile = null;
    private PrintStream moveLogFilePrintStream = null;
    private final AtomicInteger readStatusCount = new AtomicInteger();
    private List<Long> updateTimes = new ArrayList<>();
    private final AtomicInteger readStatusFromRobotInternalStartCount = new AtomicInteger();
    private final AtomicInteger readStatusFromRobotInternalEndCount = new AtomicInteger();

    private synchronized void readStatusFromRobotInternal() {
        try {
            readStatusFromRobotInternalStartCount.incrementAndGet();
            if (null == robot) {
                setCommandState(CommandStateEnumType.CRCL_ERROR);
                if (lastRobotIsConnected) {
                    showError("Robot is NOT connected.");
                }
                lastRobotIsConnected = false;
                return;
            }
            if (!robotIsConnected) {
                setCommandState(CommandStateEnumType.CRCL_ERROR);
                if (lastRobotIsConnected) {
                    showError("Robot is NOT connected.");
                }
                lastRobotIsConnected = false;
                return;
            }
            lastRobotIsConnected = true;
            long start = System.currentTimeMillis();
            synchronized (status) {
                readStatusCount.incrementAndGet();
                CommandStatusType commandStatus = status.getCommandStatus();
                if (commandStatus == null) {
                    commandStatus = new CommandStatusType();
                    status.setCommandStatus(commandStatus);
                    setCommandState(CommandStateEnumType.CRCL_WORKING);
                }
                if (commandStatus.getCommandID() < 1) {
                    commandStatus.setCommandID(1);
                }
                if (null == commandStatus.getCommandState()) {
                    setCommandState(CommandStateEnumType.CRCL_WORKING);
                }
                if (holdingObjectKnown) {
                    if (null == status.getGripperStatus()) {
                        ParallelGripperStatusType parallelGripperStatus = new ParallelGripperStatusType();
                        parallelGripperStatus.setGripperName("SCHUNK_MPG40");
                        status.setGripperStatus(parallelGripperStatus);
                    }
                    GripperStatusType gripperStatus = status.getGripperStatus();
                    if (null != gripperStatus) {
                        gripperStatus.setHoldingObject(holdingObject);
                    }
                }
                if (null != status.getGripperStatus()) {
                    if (status.getGripperStatus() instanceof ParallelGripperStatusType) {
                        ParallelGripperStatusType parallelGripperStatus = (ParallelGripperStatusType) status.getGripperStatus();
                        parallelGripperStatus.setSeparation(gripperSeperation);
                    }
                }
                commandStatus.setStatusID(commandStatus.getStatusID() + 1);
                commandStatus.setOverridePercent(overrideValue);
                if (commandStatus.getCommandState() != CommandStateEnumType.CRCL_WORKING) {
                    lastCheckAtPosition = false;
                }
                if (null == robot) {
                    setCommandState(CommandStateEnumType.CRCL_ERROR);
                    showError("fanucCRCLServer not connected to robot");
                    return;
                }
                ICurPosition icp = robot.curPosition();
                if (null == icp) {
                    showError("robot.curPosition() returned null");
                    commandStatus.setCommandState(CommandStateEnumType.CRCL_ERROR);
                    return;
                }
                ICurGroupPosition icgp = icp.group((short) 1, FRECurPositionConstants.frWorldDisplayType);
                Com4jObject com4jobj_pos = icgp.formats(FRETypeCodeConstants.frXyzWpr);
                IXyzWpr pos = com4jobj_pos.queryInterface(IXyzWpr.class);
                PmCartesian cart = new PmCartesian(pos.x() / lengthScale, pos.y() / lengthScale, pos.z() / lengthScale);
                PmRpy rpy = new PmRpy(Math.toRadians(pos.w()), Math.toRadians(pos.p()), Math.toRadians(pos.r()));
                setPose(CRCLPosemath.toPoseType(cart, rcs.posemath.Posemath.toRot(rpy), getPose()));
                Com4jObject com4jobj_joint_pos = icgp.formats(FRETypeCodeConstants.frJoint);
                IJoint joint_pos = com4jobj_joint_pos.queryInterface(IJoint.class);
                assert (jointStatuses != null);
                jointStatuses.getJointStatus().clear();
                for (short i = 1; i <= joint_pos.count(); i++) {
                    JointStatusType js = new JointStatusType();
                    js.setJointNumber(i);
                    double cur_joint_pos = joint_pos.item(i);
                    double last_joint_pos = lastJointPosArray[i];
                    long last_joint_pos_time = lastJointPosTimeArray[i];
                    long cur_time = System.currentTimeMillis();
                    double joint_vel = 1000.0 * (cur_joint_pos - last_joint_pos) / (cur_time - last_joint_pos_time + 1);
                    lastJointPosArray[i] = cur_joint_pos;
                    lastJointPosTimeArray[i] = cur_time;
                    js.setJointPosition(cur_joint_pos);
                    try {
                        if (null != cjrMap && cjrMap.size() > 0) {
                            js.setJointPosition(null);
                            js.setJointVelocity(null);
                            js.setJointTorqueOrForce(null);
                            ConfigureJointReportType cjrt = this.cjrMap.get(js.getJointNumber());
                            if (null != cjrt) {
                                if (cjrt.getJointNumber() == js.getJointNumber()) {
                                    if (cjrt.isReportPosition()) {
                                        js.setJointPosition(cur_joint_pos);
                                    }
                                    if (cjrt.isReportVelocity()) {
                                        js.setJointVelocity(joint_vel);
                                    }
                                    if (cjrt.isReportTorqueOrForce()) {
                                        js.setJointTorqueOrForce(0.0);
                                    }
                                }
                            }
                            if (commandStatus.getCommandState() == CommandStateEnumType.CRCL_WORKING
                                    && prevCmd instanceof ConfigureJointReportsType) {
                                this.setCommandState(CommandStateEnumType.CRCL_DONE);
                            }
                            if (commandStatus.getCommandState() == CommandStateEnumType.CRCL_WORKING
                                    && prevCmd instanceof ConfigureStatusReportType) {
                                this.setCommandState(CommandStateEnumType.CRCL_DONE);
                            }
                        }
                    } catch (Throwable ex) {
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "i=" + i + ",js=" + js + " : " + ex.getMessage(), ex);
                    }
                    jointStatuses.getJointStatus().add(js);
                    checkDonePrevCmd();
                }
                if (null == prevCmd || !(prevCmd instanceof InitCanonType)
                        || commandStatus.getCommandState() != CommandStateEnumType.CRCL_WORKING) {
                    checkServoReady();
                }
                updateTimes.add(System.currentTimeMillis() - start);
            }
        } catch (PmException ex) {
            showError(ex.toString());
            Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new RuntimeException(ex);
        } finally {
            readStatusFromRobotInternalEndCount.incrementAndGet();
        }
    }

    private volatile double distToGoal = 0.0;

    public double getDistToGoal() {
        return distToGoal;
    }

    private final List<ITask> tasksList = new ArrayList<>();

    private final Map<String, ITask> namesToTaskMap = new HashMap<>();
    private final Set<String> prognamesNeeded = new HashSet<>();
    private volatile Iterator<Com4jObject> updateTasksListIterator = null;

    private static final Logger LOGGER = Logger.getLogger(FanucCRCLMain.class.getName());

    private static boolean debug = Boolean.getBoolean("FanucCRCLMain.debug");

    private static void logDebug(String string) {
        if (debug) {
            LOGGER.log(Level.INFO, string);
        }
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean newDebugVal) {
        debug = newDebugVal;
    }

    private void updateTasksList() {
        try {
            logDebug("prognamesNeeded = " + prognamesNeeded);
            logDebug("namesToTaskMap.keySet() = " + namesToTaskMap.keySet());
            if (null == updateTasksListIterator) {
                ITasks tasks = robot.tasks();
                if (null != tasks) {
                    updateTasksListIterator = tasks.iterator();
                }
            }
            if (updateTasksListIterator != null) {
                if (updateTasksListIterator.hasNext()) {
                    Com4jObject c4jo = updateTasksListIterator.next();
                    ITask tsk = null;
                    String tskProgName = null;
                    tsk = c4jo.queryInterface(ITask.class);
                    try {
                        IProgram tskProg = tsk.curProgram();
                        if (null != tskProg) {
                            tskProgName = tskProg.name();
                        }
                    } catch (Exception e) {
                    }
                    String upperTskProgName = tskProgName.toUpperCase();
                    if (namesToTaskMap.keySet().contains(upperTskProgName)) {
                        return;
                    }
                    if (!prognamesNeeded.contains(upperTskProgName)) {
                        return;
                    }
                    tasksList.add(tsk);
                    namesToTaskMap.put(upperTskProgName, tsk);
                    logDebug("namesToTaskMap.keySet() = " + namesToTaskMap.keySet());
                } else {
                    updateTasksListIterator = null;
                }
            }
        } catch (ComException e) {
            e.printStackTrace();
            showError(e.toString());
        }
    }

    public boolean checkCurrentTasks() {
        if (tasksList.size() < prognamesNeeded.size()) {
            updateTasksList();
        }
        for (ITask tsk : tasksList) {
            FRETaskStatusConstants tskStatus = tsk.status();
            if (tskStatus == FRETaskStatusConstants.frStatusRun || tskStatus == FRETaskStatusConstants.frStatusRunAccept) {
                return false;
            }
        }
        return true;
    }

    private void checkDonePrevCmd() {
        if (status.getCommandStatus().getCommandState() == CommandStateEnumType.CRCL_WORKING) {
            if (prevCmd != null) {
                if (prevCmd instanceof MoveToType) {
                    try {
                        MoveToType mtPrev = (MoveToType) prevCmd;
                        double dist = distTransFrom(mtPrev.getEndPosition());
                        distToGoal = dist;
                        double rotDist = distRotFrom(mtPrev.getEndPosition());
                        long curTime = System.currentTimeMillis();
                        if (checkMoveDone(dist, rotDist, curTime)) {
                            if (!lastCheckAtPosition) {
                                moveDoneTime = System.currentTimeMillis();
                            } else if ((System.currentTimeMillis() - moveDoneTime) > 20) {
                                try {
                                    lastDoneMovePose = CRCLPosemath.copy(mtPrev.getEndPosition());
                                    lastDoneMoveCommandID = mtPrev.getCommandID();
//                                            logDebug("mtPrev.getCommandID() = " + mtPrev.getCommandID());
//                                            logDebug("mtPrev.getEndPosition().getPoint().getZ() = " + mtPrev.getEndPosition().getPoint().getZ());
//                                            logDebug("rotDist = " + rotDist);
//                                            logDebug("dist = " + dist);
                                    double distTransFromStart = distTransFrom(moveToStartPosition);
//                                            logDebug("distFromStart = " + distTransFromStart);
                                    double distRotFromStart = distRotFrom(moveToStartPosition);
//                                            logDebug("distRotFromStart = " + distRotFromStart);
//                                            logDebug("Done move = " + CRCLSocket.getUtilSocket().commandToString(prevCmd, false) + " status =" + CRCLSocket.getUtilSocket().statusToString(status, false));
                                    long moveTime = (System.currentTimeMillis() - startMoveTime);
//                                            logDebug("Move took " + moveTime + " ms.");
//                                            logDebug("moveChecksDone = " + moveChecksDone);
//                                                moveLogFilePrintStream.println("current_time_ms,current_time_string,id,start_x,start_y,start_z,end_x,end_y,end_z,distTran,distRot,moveTime,moveCheckCount");
                                    if (keepMoveToLog) {
                                        openMoveToLogFile();
                                    }
                                    if (null != moveLogFilePrintStream) {
                                        String stringToLog
                                                = curTime + ","
                                                + getDateTimeString() + ","
                                                + (curTime - expectedEndMoveToTime) + ","
                                                + lastDoneMoveCommandID + ","
                                                + moveToStartPosition.getPoint().getX() + ","
                                                + moveToStartPosition.getPoint().getY() + ","
                                                + moveToStartPosition.getPoint().getZ() + ","
                                                + mtPrev.getEndPosition().getPoint().getX() + ","
                                                + mtPrev.getEndPosition().getPoint().getY() + ","
                                                + mtPrev.getEndPosition().getPoint().getZ() + ","
                                                + distTransFromStart + ","
                                                + distRotFromStart + ","
                                                + moveTime + ","
                                                + moveChecksDone + ","
                                                + transSpeed + ","
                                                + rotSpeed + ","
                                                + (distTransFromStart / (1e-3 * moveTime)) + ","
                                                + (distRotFromStart / (1e-3 * moveTime)) + ","
                                                + timeToWaitForLastMotionProgram + ","
                                                + timeToStartMotionProgram + ","
                                                + lastMotionProgramRunningCount + ","
                                                + "\"" + distances + "\","
                                                + "\"" + moveReasons + "\","
                                                + "\"" + updateTimes + "\",";
                                        moveLogFilePrintStream.println(stringToLog);
                                        moveLogFilePrintStream.flush();
                                    }
                                    moveChecksDone = 0;
//start_y,start_z,end_x,end_y,end_z,distTran,distRot,moveTime,moveCheckCount");

                                } catch (Exception ex) {
                                    Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                                }
                                setCommandState(CommandStateEnumType.CRCL_DONE);
                                setPrevCmd(null);
                            }
                            lastCheckAtPosition = true;
                        } else {
                            setCommandState(CommandStateEnumType.CRCL_WORKING);
                            lastCheckAtPosition = false;
                            moveChecksDone++;
                        }
                    } catch (PmException ex) {
                        showError(ex.toString());
                        Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (prevCmd instanceof MoveThroughToType) {
                    MoveThroughToType mtt = (MoveThroughToType) prevCmd;
                    if (currentWaypointNumber >= mtt.getNumPositions()
                            || currentWaypointNumber >= mtt.getWaypoint().size()) {
                        try {
                            PoseType pose = mtt.getWaypoint().get(mtt.getWaypoint().size() - 1);
                            double dist = distTransFrom(pose);
                            double rotDist = distRotFrom(pose);
                            if (dist < distanceTolerance
                                    && rotDist < distanceRotTolerance
                                    && groupPos.isAtCurPosition()
                                    && (System.currentTimeMillis() - moveTime > 10)
                                    && checkCurrentTasks()) {
                                if (!lastCheckAtPosition) {
                                    moveDoneTime = System.currentTimeMillis();
                                } else if ((System.currentTimeMillis() - moveDoneTime) > 10) {
                                    setCommandState(CommandStateEnumType.CRCL_DONE);
                                }
                                lastCheckAtPosition = true;
                            } else {
                                setCommandState(CommandStateEnumType.CRCL_WORKING);
                                lastCheckAtPosition = false;
                            }
                        } catch (PmException ex) {
                            showError(ex.toString());
                            Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else if (prevCmd instanceof DwellType) {
                    long diff = System.currentTimeMillis() - dwellEndTime;
                    if (diff >= 0 && status.getCommandStatus().getCommandState() == CommandStateEnumType.CRCL_WORKING) {
//                                if(diff > 5) {
//                                    showError("dwell took:" + diff + " additional milliseconds over the expected "+((long)(((DwellType)prevCmd).getDwellTime().doubleValue()*1000.0)));
//                                }
                        setCommandState(CommandStateEnumType.CRCL_DONE);
                    }
                } else if (prevCmd instanceof InitCanonType) {
                    long diff = System.currentTimeMillis() - dwellEndTime;
//                            logDebug("(prevCmd instanceof InitCanonType) diff = " + diff);
//                            logDebug("status.getCommandStatus().getCommandState() = " + status.getCommandStatus().getCommandState());
                    if (diff >= 0 && status.getCommandStatus().getCommandState() == CommandStateEnumType.CRCL_WORKING) {
//                                if(diff > 5) {
//                                    showError("dwell took:" + diff + " additional milliseconds over the expected "+((long)(((DwellType)prevCmd).getDwellTime().doubleValue()*1000.0)));
//                                }
                        lastServoReady = true;
//                                logDebug("robotResetCount = " + robotResetCount);
                        boolean secondInitSafetyStatError = checkSafetyStatError();
//                                logDebug("secondInitSafetyStatError = " + secondInitSafetyStatError);
                        if (secondInitSafetyStatError) {
                            setCommandState(CommandStateEnumType.CRCL_ERROR);
                        } else if (robotResetCount < 3) {
                            boolean secondInitCheckServoReady = checkServoReady();
                            logDebug("secondInitCheckServoReady = " + secondInitCheckServoReady);
                            if (!secondInitCheckServoReady) {
                                robot.alarms().reset();
                                robot.tasks().abortAll(true);
                                dwellEndTime = System.currentTimeMillis() + 2000;
                                robotResetCount++;
                                setCommandState(CommandStateEnumType.CRCL_WORKING);
                            } else {
                                setCommandState(CommandStateEnumType.CRCL_DONE);
                            }
                        } else {
                            setCommandState(CommandStateEnumType.CRCL_DONE);
                        }
                    }
                } else if (prevCmd instanceof ActuateJointsType) {
                    posReg97.update();
                    if (posReg97.isAtCurPosition() && checkCurrentTasks()) {
                        ActuateJointsType actJoints = (ActuateJointsType) prevCmd;
                        double maxDiff = 0;
                        for (ActuateJointType aj : actJoints.getActuateJoint()) {
                            int num = aj.getJointNumber();
                            assert (jointStatuses != null);
                            for (JointStatusType jst : jointStatuses.getJointStatus()) {
                                if (num == jst.getJointNumber()) {
                                    double diff = Math.abs(jst.getJointPosition() - aj.getJointPosition());
                                    if (diff > maxDiff) {
                                        maxDiff = diff;
                                    }
                                    break;
                                }
                            }
                        }
                        if (maxDiff < 0.1 && lastMaxJointDiff < 0.1) {
                            setCommandState(CommandStateEnumType.CRCL_DONE);
                            logDebug("actuateJointMaxTime = " + actuateJointMaxTime);
                            long time_running = System.currentTimeMillis() - actuateJointStartTime;
                            logDebug("time_running = " + time_running);
                        }
                        lastMaxJointDiff = maxDiff;

                    }

                }
            } else {
                lastCheckAtPosition = false;
            }
        } else {
            lastCheckAtPosition = false;
        }
    }

    public static enum MoveStatus {
        DIST_OVER_TOLERANCE,
        ROTDIST_OVER_TOLERANCE,
        EXPECTED_END_MOVE_TIME,
        POSREG98_AT_CUR_POSITION,
        CURTIME_NEAR_MOVETIME,
        LASTMOTIONPROGGRAMRUNNING,
        MOVE_DONE,
        MOVE_STATUS_NOT_SET,
        CHECKED_TASK_STILL_RUNNING,
    }

    private volatile MoveStatus moveStatus = MoveStatus.MOVE_STATUS_NOT_SET;

    public MoveStatus getMoveStatus() {
        return moveStatus;
    }

    List<MoveStatus> moveReasons = new ArrayList<>();
    List<Double> distances = new ArrayList<>();

    private void addMoveReason(MoveStatus reason) {
        moveStatus = reason;
        if (keepMoveToLog) {
            if (moveReasons.isEmpty() || moveReasons.get(moveReasons.size() - 1) != reason) {
                moveReasons.add(reason);
            }
        }
    }

    private boolean checkForLastMotionProgramRunning = false;

    private boolean checkMoveDone(double dist, double rotDist, long curTime) {
        if ((curTime - expectedEndMoveToTime) > 2000) {
            warnMoveTime(dist, rotDist, curTime);
        }
        if (dist >= distanceTolerance) {
            addMoveReason(MoveStatus.DIST_OVER_TOLERANCE);
            distances.add(dist);
            return false;
        }
        if (rotDist >= distanceRotTolerance) {
            addMoveReason(MoveStatus.ROTDIST_OVER_TOLERANCE);
            return false;
        }

        if (!posReg98.isAtCurPosition()) {
            addMoveReason(MoveStatus.POSREG98_AT_CUR_POSITION);
            return false;
        }
        if (!checkCurrentTasks()) {
            addMoveReason(MoveStatus.CHECKED_TASK_STILL_RUNNING);
            return false;
        }
        if (curTime < expectedEndMoveToTime && (curTime - moveTime) < 2000) {
            addMoveReason(MoveStatus.EXPECTED_END_MOVE_TIME);
            return false;
        }
        if ((curTime - moveTime) < 20) {
            addMoveReason(MoveStatus.CURTIME_NEAR_MOVETIME);
            return false;
        }
        if (checkForLastMotionProgramRunning) {
            if (lastMotionProgramRunning()) {
                addMoveReason(MoveStatus.LASTMOTIONPROGGRAMRUNNING);
                return false;
            }
        }
        moveStatus = MoveStatus.MOVE_DONE;
        return true;
    }

    private long lastWarnMoveTime = 0;

    private void warnMoveTime(double dist, double rotDist, long curTime) {
        if (curTime - lastWarnMoveTime > 2000) {
            System.err.println("FanucCRCL: move taking much longer than expected : (curTime - expectedEndMoveToTime) =" + (curTime - expectedEndMoveToTime) + ", moveStatus=" + moveStatus + ",dist=" + dist + ",rotDist=" + rotDist);
            lastWarnMoveTime = curTime;
        }
    }

    public CRCLCommandType getPrevCmd() {
        return prevCmd;
    }

    private boolean lastActivAlarms = false;

    private boolean checkServoReady() {
        boolean readyNow = false;
        boolean safetyStatError = checkSafetyStatError();
        if (safetyStatError) {
            lastServoReady = true;
        } else if (null != moveGroup1ServoReadyVar) {
            moveGroup1ServoReadyVar.refresh();
            Object val = moveGroup1ServoReadyVar.value();
            if (val instanceof Boolean) {
                boolean servoReady = (boolean) val;
                if (!servoReady) {
                    if (lastServoReady) {
                        showError("SERVO_NOT_READY (Need to reset fault?)");
                    } else if (status.getCommandStatus().getCommandState() == CommandStateEnumType.CRCL_WORKING) {
                        setStatusErrorDescription("SERVO_NOT_READY (Need to reset fault?)");
                    }
                }
                lastServoReady = servoReady;
                readyNow = servoReady;
            }
        } else {
            lastServoReady = true;
        }
        return readyNow;
    }

    private volatile boolean lastSafetyStatError = false;
    private volatile long lastCheckSafetyStatTime = 0;

    private boolean checkSafetyStatError() {
        boolean safetyStatError = false;
        if (System.currentTimeMillis() - lastCheckSafetyStatTime < 100) {
            return lastSafetyStatError;
        }
        if (null != morSafetyStatVar) {
            morSafetyStatVar.refresh();
            int safety_stat = (int) morSafetyStatVar.value();
            lastCheckSafetyStatTime = System.currentTimeMillis();
            safetyStatError = isMoreSafetyStatError(safety_stat);
            if (safetyStatError) {
                if (safety_stat != last_safety_stat) {
                    showError(morSafetyStatToString(safety_stat));
                } else if (status.getCommandStatus().getCommandState() == CommandStateEnumType.CRCL_WORKING) {
                    setStatusErrorDescription(morSafetyStatToString(safety_stat));
                }
            }
            last_safety_stat = safety_stat;
        }
        lastSafetyStatError = safetyStatError;
        return safetyStatError;
    }

    public ExecutorService getRobotService() {
        if (null == robotService) {
            robotService = Executors.newSingleThreadExecutor(daemonThreadFactory);
        }
        return robotService;
    }

    int last_safety_stat = 0;
    boolean lastServoReady = true;

    public static class FanucClientState extends CRCLServerClientState {

        public FanucClientState(CRCLSocket cs) {
            super(cs);
        }
        int i;
    }

    public static final CRCLServerSocketStateGenerator<FanucClientState> FANUC_STATE_GENERATOR
            = FanucClientState::new;

    private final CRCLServerSocketEventListener<FanucClientState> crclSocketEventListener
            = this::handleCrclServerSocketEvent;

    private void handleCrclServerSocketEvent(CRCLServerSocketEvent<FanucClientState> evt) {
        try {
            switch (evt.getEventType()) {
                case CRCL_COMMAND_RECIEVED:
                    handleClientCommand(evt.getInstance(), evt.getSource());
                    break;

                case EXCEPTION_OCCURRED:
                    break;

                case NEW_CRCL_CLIENT:
                    break;

                case SERVER_CLOSED:
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, "evt=" + evt, ex);
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        }
    }
    private final CRCLServerSocket<FanucClientState> crclServerSocket;

//    private ExecutorService es;
//    private ServerSocket ss;
//    private Set<CRCLSocket> clients = new HashSet<>();
    public static void stop() {
        if (null != main) {
            main.stopInternal();
        }
        main = null;
        logDebug("Thread.activeCount() = " + Thread.activeCount());
        for (StackTraceElement ste[] : Thread.getAllStackTraces().values()) {
            logDebug("ste = " + Arrays.toString(ste));
        }
        Thread ta[] = new Thread[10 + Thread.activeCount()];
        Thread.enumerate(ta);
        for (Thread t : ta) {
            if (null != t && !t.equals(Thread.currentThread())) {
                logDebug("t = " + t);
                logDebug("t.isAlive() = " + t.isAlive());
                logDebug("t.isDaemon() = " + t.isDaemon());
                logDebug("t.isInteruppted() = " + t.isInterrupted());
                logDebug("t.getStackTrace() = " + Arrays.toString(t.getStackTrace()));
                t.interrupt();
            }
        }
        System.exit(0);
    }

    public synchronized void stopCrclServer() {
//        if (null != crclServerFuture) {
//            crclServerFuture.cancel(true);
////            try {
////                crclServerFuture.get(100, TimeUnit.MILLISECONDS);
////            } catch (InterruptedException | ExecutionException | TimeoutException ex) {
////                Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
////            }
//        }
//        if (null != clients) {
//            for (CRCLSocket cs : clients) {
//                try {
//                    cs.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            clients.clear();
//        }
//        if (null != ss) {
//            try {
//                ss.close();
//            } catch (IOException ex) {
//                Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            ss = null;
//        }
        if (null != crclServerSocket) {
            crclServerSocket.close();
        }
    }

    private void stopInternal() {
        if (null != moveThread) {
            moveThread.interrupt();
            try {
                moveThread.join(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            moveThread = null;
        }
        stopCrclServer();
//        if (null != es) {
//            es.shutdownNow();
//            try {
//                es.awaitTermination(500, TimeUnit.MILLISECONDS);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            es = null;
//        }
        disconnectRemoteRobot();
        if (null != neighborhood) {
            neighborhood.dispose();
            neighborhood = null;
        }
        if (null != displayInterface) {
            displayInterface.setVisible(false);
            displayInterface.dispose();
            displayInterface = null;
        }
    }

    private ExecutorService robotService = null;

    public void disconnectRemoteRobot() {
        try {
            tasksIterator = null;
            getTaskListOutput = null;
            updateTasksListIterator = null;
            tasksList.clear();
            namesToTaskMap.clear();
            robotIsConnected = false;
            displayInterface.setConnected(false);
            if (null != robotService) {
                robotService.submit(this::disconnectRemoteRobotInternal).get(500, TimeUnit.MILLISECONDS);
                robotService.shutdownNow();
                robotService.awaitTermination(500, TimeUnit.MILLISECONDS);
                robotService = null;
            } else {
                disconnectRemoteRobotInternal();
            }
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            if (null != robotService) {
                robotService.shutdownNow();
                try {
                    robotService.awaitTermination(500, TimeUnit.MILLISECONDS);
                } catch (InterruptedException ex1) {
                    Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex1);
                }
                robotService = null;
            }
            Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            robotIsConnected = false;
            displayInterface.setConnected(false);
            robot = null;
        }
    }

    private synchronized void disconnectRemoteRobotInternal() {

        tasksIterator = null;
        getTaskListOutput = null;
        updateTasksListIterator = null;
        tasksList.clear();
        namesToTaskMap.clear();
        try {
//            if (null != robot_ec) {
//                robot_ec.close();
//                robot_ec = null;
//            }
            if (null != robot) {
                robot.dispose();
                robot = null;
            }
            robotIsConnected = false;
            displayInterface.setConnected(false);
        } catch (Exception e) {
            Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private volatile int robotResetCount = 0;

    private void handleInitCanon(InitCanonType initCmd) {
        lastServoReady = true;

        recheckOverride();
        boolean initSafetyStatError = checkSafetyStatError();
//        logDebug("initSafetyStatError = " + initSafetyStatError);
        if (initSafetyStatError) {
            setCommandState(CommandStateEnumType.CRCL_ERROR);
        } else {
            boolean initCheckServoReady = checkServoReady();
//            logDebug("initCheckServoReady = " + initCheckServoReady);
            if (!initCheckServoReady) {
                setCommandState(CommandStateEnumType.CRCL_WORKING);
                dwellEndTime = System.currentTimeMillis() + 1000;
            } else {
                setCommandState(CommandStateEnumType.CRCL_DONE);
            }
        }
        robot.alarms().reset();
        robot.tasks().abortAll(true);
        handleCommandCount = 0;
        updateStatusCount = 0;
        maxHandleCommandTime = 0;
        totalHandleCommandTime = 0;
        maxUpdateStatusTime = 0;
        totalUpdateStatusTime = 0;
        lastServoReady = true;

        robotResetCount = 0;
//        checkAlarms();
    }

    private void recheckOverride() {
        if (null != overrideVar) {
            overrideVar.refresh();
            Object overrideValueObject = overrideVar.value();

            if (overrideValueObject instanceof Integer) {
                int val = (Integer) overrideValueObject;
                if (val != overrideValue) {
                    System.out.println("overrideValueObject = " + overrideValueObject);
                    setOverrideValue(val);
                }
            } else {
                System.out.println("overrideValueObject = " + overrideValueObject);
                if (null != overrideValueObject) {
                    System.out.println("overrideValueObject.getClass() = " + overrideValueObject.getClass());
                }
            }
        }
    }

    private void handleStopMotion(StopMotionType stopCmd) {
        if (null != moveThread) {
            moveThread.interrupt();
            try {
                moveThread.join(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            moveThread = null;
        }
        robot.tasks().abortAll(true);
        setCommandState(CommandStateEnumType.CRCL_DONE);
    }

    private void handleEndCanon(EndCanonType initCmd) {
        setCommandState(CommandStateEnumType.CRCL_DONE);
    }

    private double gripperSeperation = 1.0;

    /**
     * Get the value of gripperSeperation
     *
     * @return the value of gripperSeperation
     */
    public double getGripperSeperation() {
        return gripperSeperation;
    }

    /**
     * Set the value of gripperSeperation
     *
     * @param gripperSeperation new value of gripperSeperation
     */
    public void setGripperSeperation(double gripperSeperation) {
        this.gripperSeperation = gripperSeperation;
    }

    private void handleCloseToolChanger(CloseToolChangerType closeToolCmd) {
        this.runTPProgram(tool_close_prog);
        setCommandState(CommandStateEnumType.CRCL_DONE);
    }

    private void handleOpenToolChanger(OpenToolChangerType openToolCmd) {
        this.runTPProgram(tool_open_prog);
        setCommandState(CommandStateEnumType.CRCL_DONE);
    }

    private void handleSetEndEffector(SetEndEffectorType seeCmd) {
        if (seeCmd.getSetting() > 0.5) {
            open_gripper_prog.run(FREStepTypeConstants.frStepNone, 1, FREExecuteConstants.frExecuteFwd);
            setGripperSeperation(1.0);
        } else {
            close_gripper_prog.run(FREStepTypeConstants.frStepNone, 1, FREExecuteConstants.frExecuteFwd);
            setGripperSeperation(0.0);
        }
        settingsStatus.setEndEffectorSetting(seeCmd.getSetting());
        setCommandState(CommandStateEnumType.CRCL_DONE);
    }

    private void handleSetAngleUnits(SetAngleUnitsType sauCmd) {
        settingsStatus.setAngleUnitName(sauCmd.getUnitName());
        setCommandState(CommandStateEnumType.CRCL_DONE);
    }

    private void handleSetForceUnits(SetForceUnitsType sfuCmd) {
        settingsStatus.setForceUnitName(sfuCmd.getUnitName());
        setCommandState(CommandStateEnumType.CRCL_DONE);
    }

    private String lastErrorString = null;

    public void showError(String error) {
        setStatusErrorDescription(error);
        logInfoString(error);
    }

    public void setStatusErrorDescription(String error) {
        if (null != status) {
            if (null == status.getCommandStatus()) {
                status.setCommandStatus(new CommandStatusType());
                status.getCommandStatus().setCommandID(1);
            }

            setCommandState(CommandStateEnumType.CRCL_ERROR);
            status.getCommandStatus().setStateDescription(error);
        }
    }

    private void logInfoString(String error) {
        if (null != error && !error.equals(lastErrorString)) {
            System.err.println(error);
            if (null != displayInterface) {
                displayInterface.getjTextAreaErrors().append(error + "\n");
            }
            lastErrorString = error;
        }
    }

    public void showWarning(String warningString) {
        if (null != status) {
            if (null == status.getCommandStatus()) {
                status.setCommandStatus(new CommandStatusType());
                status.getCommandStatus().setCommandID(1);
            }

//            status.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_ERROR);
            status.getCommandStatus().setStateDescription(warningString);
        }
        logInfoString(warningString);
    }

    private void showInfo(String info) {
        if (null != displayInterface) {
            displayInterface.getjTextAreaErrors().append(info + "\n");
        }
    }

    double maxRelativeSpeed = 100.0;

    private void handleSetTransSpeed(SetTransSpeedType stsCmd) {
        TransSpeedType ts = stsCmd.getTransSpeed();
        if (ts instanceof TransSpeedRelativeType) {
            TransSpeedRelativeType tsRel = (TransSpeedRelativeType) ts;
            transSpeed = tsRel.getFraction() * 200.0;
//            int val = ((TransSpeedRelativeType) ts).getFraction().multiply(BigDecimal.valueOf(maxRelativeSpeed)).intValue();
            int val = (int) (tsRel.getFraction() * maxRelativeSpeed);
            overrideVar.value(val);
            setOverrideValue(val);
            if (null != displayInterface) {
                displayInterface.getjSliderOverride().setValue(val);
            }
            setCommandState(CommandStateEnumType.CRCL_DONE);
            settingsStatus.setTransSpeedRelative(tsRel);
        } else if (ts instanceof TransSpeedAbsoluteType) {
            TransSpeedAbsoluteType tsAbs = (TransSpeedAbsoluteType) ts;
            transSpeed = tsAbs.getSetting() * lengthScale;
            regNumeric98.regFloat((float) transSpeed);
            showInfo("R[98] = transSpeed = " + transSpeed);
//            reg98Var.update();
            setCommandState(CommandStateEnumType.CRCL_DONE);
            settingsStatus.setTransSpeedAbsolute(tsAbs);
        }
    }

    private void handleSetRotSpeed(SetRotSpeedType stsCmd) {
        RotSpeedType rs = stsCmd.getRotSpeed();
        if (rs instanceof RotSpeedRelativeType) {
            RotSpeedRelativeType rsRel = (RotSpeedRelativeType) rs;
            int val = (int) (rsRel.getFraction() * maxRelativeSpeed);
            overrideVar.value(val);
            setOverrideValue(val);
            if (null != displayInterface) {
                displayInterface.getjSliderOverride().setValue(val);
            }
            setCommandState(CommandStateEnumType.CRCL_DONE);
            settingsStatus.setRotSpeedRelative(rsRel);
        } else if (rs instanceof RotSpeedAbsoluteType) {
            RotSpeedAbsoluteType rsAbs = (RotSpeedAbsoluteType) rs;
            rotSpeed = rsAbs.getSetting() * lengthScale;
            setCommandState(CommandStateEnumType.CRCL_DONE);
            settingsStatus.setRotSpeedAbsolute(rsAbs);
        }
    }

    private Set<String> getProgramNames() {
        IPrograms progs = robot.programs();
        Set<String> prognames = new TreeSet<String>();
        for (Com4jObject c4jo_prog : progs) {
            IProgram prog = c4jo_prog.queryInterface(IProgram.class);
            prognames.add(prog.name());
        }
        return prognames;
    }

    Thread moveThread = null;
    volatile private int moveCount = 0;
    volatile long moveTime = 0;
    private Set<String> origProgNames;
    private double transSpeed = 200; // 200 mm/s
    private double rotSpeed = 90; // 90 deg/s

    private boolean posReg98Updated = false;

    private void updatePosReg98() {
        if (!posReg98Updated && null != posReg98) {
            posReg98.record();
            posReg98.refresh();
            ICurGroupPosition curPos = robot.curPosition().group((short) 1, FRECurPositionConstants.frWorldDisplayType);
            IXyzWpr curXyzWpr = curPos.formats(FRETypeCodeConstants.frXyzWpr).queryInterface(IXyzWpr.class);
            IConfig curConf = curXyzWpr.config();
            IXyzWpr posReg98XyzWpr = posReg98.formats(FRETypeCodeConstants.frXyzWpr).queryInterface(IXyzWpr.class);
            IConfig posReg98XyzWprConf = posReg98XyzWpr.config();
            posReg98XyzWprConf.flip(curConf.flip());
            posReg98XyzWprConf.front(curConf.front());
            posReg98XyzWprConf.left(curConf.left());
            posReg98XyzWprConf.up(curConf.up());
            posReg98XyzWprConf.turnNum((short) 1, curConf.turnNum((short) 1));
            posReg98.update();
            posReg98Updated = true;
        }
    }

    private boolean posReg97Updated = false;

    private void updatePosReg97() {
        if (true) {
            posReg97.refresh();
            ICurPosition icp = robot.curPosition();
            ICurGroupPosition icgp = icp.group((short) 1, FRECurPositionConstants.frJointDisplayType);
            Com4jObject com4jobj_joint_pos = icgp.formats(FRETypeCodeConstants.frJoint);
            IJoint joint_pos = com4jobj_joint_pos.queryInterface(IJoint.class);
            final IJoint posReg97Joint = posReg97.formats(FRETypeCodeConstants.frJoint).queryInterface(IJoint.class);
            for (short i = 1; i <= joint_pos.count(); i++) {
                double cur_joint_pos = joint_pos.item(i);
                posReg97Joint.item(i, cur_joint_pos);
            }
            posReg97.update();
            posReg97Updated = true;
        }
    }

    long expectedEndMoveToTime = -1;
    long startMoveTime = -1;

    private volatile PoseType moveToStartPosition = null;

    private void handleMoveTo(MoveToType moveCmd) throws PmException {

        if (overrideValue < 50) {
            recheckOverride();
        }
        moveReasons = new ArrayList<>();
        distances = new ArrayList<>();
        moveChecksDone = 0;
        moveToStartPosition = CRCLPosemath.copy(status.getPoseStatus().getPose());
        posReg97Updated = false;
        setCommandState(CommandStateEnumType.CRCL_WORKING);
        PointType moveCmdEndPt = moveCmd.getEndPosition().getPoint();
        PmCartesian cart = CRCLPosemath.toPmCartesian(moveCmdEndPt);
        PmCartesian endCart = new PmCartesian(cart.x * lengthScale, cart.y * lengthScale, cart.z * lengthScale);
        PmRpy rpy = CRCLPosemath.toPmRpy(moveCmd.getEndPosition());
        updatePosReg98();
        posReg98.refresh();
        IXyzWpr posReg98XyzWpr = posReg98.formats(FRETypeCodeConstants.frXyzWpr).queryInterface(IXyzWpr.class);
        posReg98XyzWpr.setAll(endCart.x, endCart.y, endCart.z, Math.toDegrees(rpy.r), Math.toDegrees(rpy.p), Math.toDegrees(rpy.y));
        limitAndUpdatePos(posReg98);
        posReg98XyzWpr = posReg98.formats(FRETypeCodeConstants.frXyzWpr).queryInterface(IXyzWpr.class);
        endCart.x = posReg98XyzWpr.x();
        endCart.y = posReg98XyzWpr.y();
        endCart.z = posReg98XyzWpr.z();
        double cartDiff = distTransFrom(moveCmd.getEndPosition());
        double rotDiff = distRotFrom(moveCmd.getEndPosition());
        moveCmdEndPt.setX(endCart.x / lengthScale);
        moveCmdEndPt.setY(endCart.y / lengthScale);
        moveCmdEndPt.setZ(endCart.z / lengthScale);
        double cartMoveTime = cartDiff / transSpeed;
        double rotMoveTime = rotDiff / rotSpeed;
        if (rotMoveTime > cartMoveTime) {
            double timeNeeded = Math.max(rotMoveTime, cartMoveTime);
            int time_needed_ms = (int) (1000.0 * timeNeeded);
            showInfo("MoveTo : cartDiff = " + cartDiff + ",rotDiff = " + rotDiff + ", transSpeed=" + transSpeed + ", time_needed_ms = " + time_needed_ms);
            if (time_needed_ms < 10) {
                time_needed_ms = 10;
            }
            regNumeric96.regLong(time_needed_ms);
            reg96Var.update();
            runMotionTpProgram(move_w_time_prog);
            expectedEndMoveToTime = System.currentTimeMillis() + time_needed_ms * (100 / overrideValue);
        } else {
            showInfo("MoveTo : cartDiff = " + cartDiff + ",rotDiff = " + rotDiff);
            expectedEndMoveToTime = System.currentTimeMillis() + ((long) (1000.0 * cartMoveTime) * (100 / overrideValue));
            runMotionTpProgram(move_linear_prog);
        }
        startMoveTime = System.currentTimeMillis();
    }

    private volatile Iterator<Com4jObject> tasksIterator = null;
    private volatile List<Object[]> getTaskListOutput = null;

    public Optional<List<Object[]>> getTaskList(boolean showAborted) {
        if (null == tasksIterator || null == getTaskListOutput) {
            ITasks tasks = robot.tasks();
            getTaskListOutput = new ArrayList<>();
            if (null != tasks) {
                tasksIterator = tasks.iterator();
                getTaskListOutput = new ArrayList<>();
            }
            return Optional.empty();
        } else {
            if (!tasksIterator.hasNext()) {
                tasksIterator = null;
                Optional<List<Object[]>> ret = Optional.of(getTaskListOutput);
                getTaskListOutput = null;
                return ret;
            }
            Com4jObject c4jo = tasksIterator.next();
            if (null == c4jo) {
                tasksIterator = null;
                Optional<List<Object[]>> ret = Optional.of(getTaskListOutput);
                getTaskListOutput = null;
                return ret;
            } else {
                ITask tsk = null;
                String tskProgName = null;
                FREProgramTypeConstants pType = null;
                FRETaskStatusConstants tskStatus = null;
                try {
                    tsk = c4jo.queryInterface(ITask.class);
                    try {
                        tskStatus = tsk.status();
                    } catch (Exception e) {
                    }
                    if (!showAborted && tskStatus == FRETaskStatusConstants.frStatusAborted) {
                        return Optional.empty();
                    }
                    try {
                        pType = tsk.programType();
                    } catch (Exception e) {
                    }
                    try {
                        IProgram tskProg = tsk.curProgram();
                        if (null != tskProg) {
                            tskProgName = tskProg.name();
                        }
                    } catch (Exception e) {
                    }
                } catch (ComException e) {
                    e.printStackTrace();
                    showError(e.toString());
                    return Optional.empty();
                }

                if (null == tskProgName && null == pType && null == tskStatus) {
                    return Optional.empty();
                }
                getTaskListOutput.add(new Object[]{
                    tskProgName == null ? "" : tskProgName,
                    pType == null ? "" : pType.toString(),
                    tskStatus == null ? "" : tskStatus.toString()});
                return Optional.empty();
            }
        }
    }

    long lastRunMotionTpTime = 0;
    ITPProgram lastRunMotionProgram = null;

    private volatile long timeToWaitForLastMotionProgram = 0;
    private volatile long timeToStartMotionProgram = 0;
    private volatile int lastMotionProgramRunningCount = 0;

    public synchronized void runMotionTpProgram(final ITPProgram program) {
        boolean program_started = false;
        int count = 0;
        long start = System.currentTimeMillis();
        CommandStateEnumType state = origState;
        int motionProgramRunningCount = 0;
        if (start - moveDoneTime < 100) {
            while (lastMotionProgramRunning()) {
//            System.err.println("waiting for lastMotionProgramRunning");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
                }
                motionProgramRunningCount++;
            }
        }
        lastMotionProgramRunningCount = motionProgramRunningCount;
        timeToWaitForLastMotionProgram = System.currentTimeMillis() - start;
        while (!program_started) {
            try {
                count++;
                program.run(FREStepTypeConstants.frStepNone, 1, FREExecuteConstants.frExecuteFwd);
                program_started = true;
            } catch (Exception e) {
                long curtime = System.currentTimeMillis();
                System.err.println("count=" + count);
                System.err.println("state=" + state);
                System.err.println("time since move =" + (curtime - moveTime));
                System.err.println("time since move done=" + (curtime - moveDoneTime));
                System.err.println("time since last command=" + (curtime - lastRunMotionTpTime));
                System.err.println("time since start=" + (curtime - start));
                Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, e);
                getTaskList(false).ifPresent(taskList -> {
                    taskList.forEach((Object[] objects) -> logDebug(Arrays.toString(objects)));
                });
                if (count > 3) {
                    showError(e.toString());
                    return;
                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        lastRunMotionProgram = program;
        lastRunMotionTpTime = System.currentTimeMillis();
        timeToStartMotionProgram = lastRunMotionTpTime - start;
        String progName = program.name();
        if (null != progName && progName.length() > 1) {
            prognamesNeeded.add(progName);
            logDebug("progName = " + progName);
            logDebug("prognamesNeeded = " + prognamesNeeded);
        }
    }
    public static final long MOVE_INTERVAL_MILLIS = 100;
    private int currentWaypointNumber = 0;

    public double distTransFrom(PoseType pose) {
        PmCartesian cart = CRCLPosemath.toPmCartesian(CRCLPosemath.getPoint(status));
        return cart.distFrom(CRCLPosemath.toPmCartesian(pose.getPoint()));
    }

    public double distRotFrom(PoseType pose) throws PmException {
        PmRotationVector rotvCurrent = CRCLPosemath.toPmRotationVector(getPose());
        PmRotationVector rotvArg = CRCLPosemath.toPmRotationVector(pose);
        PmRotationVector rotvDiff = rotvArg.multiply(rotvCurrent.inv());
        return Math.toDegrees(rotvDiff.s);
    }

    private void moveToGroupPos() throws InterruptedException {
        boolean didit = false;
        int tries = 0;
        long t0 = System.currentTimeMillis();
        do {
            tries++;
            try {
                groupPos.moveto();
                didit = true;
            } catch (ComException comEx) {
                showComException(comEx);
                Thread.sleep(20);
            }
        } while (!didit && !Thread.currentThread().isInterrupted() && (System.currentTimeMillis() - t0) < 500);
        long t1 = System.currentTimeMillis();
        long diff = (t1 - t0);
        if (diff > 500 && !didit) {
            showError("Timed out trying to send moveto command. tried " + tries + " times over " + diff + " ms");
        }
    }

    private void handleMoveThroughTo(MoveThroughToType moveCmd) throws PmException {
        posReg97Updated = false;
        setCommandState(CommandStateEnumType.CRCL_WORKING);
        if (moveThread != null) {
            try {
                moveThread.join(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            moveThread.interrupt();
            try {
                moveThread.join(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            moveThread = null;
        }

        moveCount++;
        moveThread = new Thread(() -> {
            try {
                for (currentWaypointNumber = 0; currentWaypointNumber < moveCmd.getNumPositions() && currentWaypointNumber < moveCmd.getWaypoint().size(); currentWaypointNumber++) {
                    PoseType pose = moveCmd.getWaypoint().get(currentWaypointNumber);
                    PmCartesian cart = CRCLPosemath.toPmCartesian(pose.getPoint());
                    PmRpy rpy = CRCLPosemath.toPmRpy(pose);
                    ICurPosition icp = robot.curPosition();
                    ICurGroupPosition icgp = icp.group((short) 1, FRECurPositionConstants.frWorldDisplayType);
                    Com4jObject com4jobj_pos = icgp.formats(FRETypeCodeConstants.frXyzWpr);
                    IXyzWpr pos = com4jobj_pos.queryInterface(IXyzWpr.class);
                    Com4jObject com4jobj_sys_pos = groupPos.formats(FRETypeCodeConstants.frXyzWpr);
                    IXyzWpr sys_pos = com4jobj_sys_pos.queryInterface(IXyzWpr.class);
                    long t0 = System.currentTimeMillis();
                    long t1 = System.currentTimeMillis();
                    sys_pos.setAll(cart.x * lengthScale, cart.y * lengthScale, cart.z * lengthScale,
                            Math.toDegrees(rpy.r), Math.toDegrees(rpy.p), Math.toDegrees(rpy.y));
                    groupPos.update();
                    moveToGroupPos();
                    if (Thread.currentThread().isInterrupted()) {
                        return;
                    }
                    Thread.sleep(40);
                    double dist = distTransFrom(pose);
                    double distRot = distRotFrom(pose);
                    while (dist > distanceTolerance
                            || distRot > distanceRotTolerance
                            || !groupPos.isAtCurPosition()
                            || !checkCurrentTasks()) {
                        if (Thread.currentThread().isInterrupted()) {
                            return;
                        }
                        Thread.sleep(20);
                        dist = distTransFrom(pose);
                    }
                    long t5 = System.currentTimeMillis();
                    moveTime = t5;
                    Thread.sleep(20);
                }
            } catch (InterruptedException ex) {
            } catch (ComException | PmException e) {
                e.printStackTrace();
                System.err.println("Time since moveTime = " + (System.currentTimeMillis() - moveTime));
                System.err.println("Time since moveDoneTime = " + (System.currentTimeMillis() - moveDoneTime));
                showError(e.toString());
            }
        }, "moveThread" + moveCount);
        moveThread.start();
    }
    public double distanceTolerance = 1.0; // millimeter
    public double distanceRotTolerance = 0.25; // degrees

//    private void handleSetLengthUnits(SetLengthUnitsType slu) {
//        this.setLengthUnit(slu.getUnitName());
//        setCommandState(CommandStateEnumType.CRCL_DONE);
//        settingsStatus.setLengthUnitName(slu.getUnitName());
//    }
    private void handleSetEndPoseTolerance(SetEndPoseToleranceType sepCmd) {
        PoseToleranceType poseTol = sepCmd.getTolerance();
        distanceTolerance = Math.min(poseTol.getXPointTolerance().doubleValue(),
                Math.min(poseTol.getXPointTolerance().doubleValue(),
                        poseTol.getZPointTolerance().doubleValue()));
        setCommandState(CommandStateEnumType.CRCL_DONE);
        settingsStatus.setPoseTolerance(sepCmd.getTolerance());
    }

    long dwellEndTime = 0;

    private void handleDwell(DwellType dwellCmd) {
        dwellEndTime = System.currentTimeMillis() + ((long) (dwellCmd.getDwellTime() * 1000.0 + 1.0));
//        logDebug("dwellEndTime = " + dwellEndTime);
        setCommandState(CommandStateEnumType.CRCL_WORKING);
    }

    private void setCommandState(CommandStateEnumType newState) {
        if (null == status.getCommandStatus()) {
            status.setCommandStatus(new CommandStatusType());
        }
        if (checkSafetyStatError()) {
            newState = CommandStateEnumType.CRCL_ERROR;
        }
        CommandStatusType cmdStatus = status.getCommandStatus();
        if (null != cmdStatus) {
            cmdStatus.setCommandState(newState);
//            if (newState != CommandStateEnumType.CRCL_ERROR && null != prevCmd) {
//                cmdStatus.setStateDescription(prevCmd.getClass().getName());
//            }
        }
    }
    private ConfigureJointReportsType cjrs;
    private Map<Integer, ConfigureJointReportType> cjrMap = null;

    private void setDefaultJointReports() {
        if (null == this.cjrMap) {
            this.cjrMap = new HashMap<>();
        }

        for (int i = 1; i <= 6; i++) {
            ConfigureJointReportType cjr = new ConfigureJointReportType();
            cjr.setReportPosition(true);
            cjr.setReportVelocity(true);
            cjr.setJointNumber(i);
            this.cjrMap.put(i,
                    cjr);
        }
        setReportJointStatus(true);
        setReportPoseStatus(true);
        setReportSettingsStatus(true);
    }

    private void handleConfigureStatusReport(ConfigureStatusReportType cmd) {
        setReportJointStatus(cmd.isReportJointStatuses());
        setReportPoseStatus(cmd.isReportPoseStatus());
        setReportSettingsStatus(cmd.isReportSettingsStatus());
        setCommandState(CommandStateEnumType.CRCL_WORKING);
    }

    private void handleMessage(MessageType cmd) {
        logInfoString(cmd.getMessage());
        setCommandState(CommandStateEnumType.CRCL_DONE);
    }

    private void handleConfigureJointReports(ConfigureJointReportsType cmd) {
        cjrs = (ConfigureJointReportsType) cmd;
        if (cjrs.isResetAll() || null == this.cjrMap) {
            this.cjrMap = new HashMap<>();
        }
        for (ConfigureJointReportType cjr : cjrs.getConfigureJointReport()) {
            this.cjrMap.put(cjr.getJointNumber(),
                    cjr);
        }
        setCommandState(CommandStateEnumType.CRCL_WORKING);
    }

    private void runTPProgram(ITPProgram prog) {
        try {
            String progName = prog.name();
            for (Com4jObject c4jo : robot.tasks()) {
                ITask tsk = null;
                String tskProgName = null;
                try {
                    tsk = c4jo.queryInterface(ITask.class);
                    FREProgramTypeConstants pType = tsk.programType();
                    if (pType != FREProgramTypeConstants.frTPProgramType) {
                        continue;
                    }
                    IProgram tskProg = tsk.curProgram();
                    if (null == tskProg) {
                        continue;
                    }
                    tskProgName = tskProg.name();
                    if (null == tskProgName) {
                        continue;
                    }
                } catch (ComException e) {
                    continue;
                }
                try {
                    if (tsk != null && tskProgName != null && tskProgName.equals(progName)) {
                        FRETaskStatusConstants tskStatus = tsk.status();
//                        logDebug("tskStatus = " + tskStatus);
                        if (tskStatus == FRETaskStatusConstants.frStatusRun) {
                            logDebug("aborting task with curProgram().name() = " + tskProgName);
                            tsk.abort(true, true);
                            long t0 = System.currentTimeMillis();
                            int cycles = 0;
                            while (!Thread.currentThread().isInterrupted()
                                    && (tskStatus == FRETaskStatusConstants.frStatusRun
                                    || tskStatus == FRETaskStatusConstants.frStatusAborting)) {
                                Thread.sleep(10);
                                tskStatus = tsk.status();
                                logDebug("tskStatus = " + tskStatus);
                                cycles++;
                                logDebug("cycles = " + cycles);
                            }
                            long t1 = System.currentTimeMillis();
                            boolean interrupted = Thread.currentThread().isInterrupted();
                            logDebug("interrupted = " + interrupted);
                            if (interrupted) {
                                System.exit(1);
                            }
                            logDebug("Abort took " + (t1 - t0) + " ms and " + cycles + " cycles.");
                            logDebug("tskStatus = " + tskStatus);
                        }
                    }
                } catch (InterruptedException ie) {
                    return;
                } catch (ComException comEx) {
                    showComException(comEx);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            boolean didit = false;
            int tries = 0;
            long runStartTime = System.currentTimeMillis();
            while (!didit && !Thread.currentThread().isInterrupted()) {
                try {
                    prog.run(FREStepTypeConstants.frStepNone, 1, FREExecuteConstants.frExecuteFwd);
                    if (null != progName && progName.length() > 1) {
                        prognamesNeeded.add(progName);
                        logDebug("progName = " + progName);
                        logDebug("prognamesNeeded = " + prognamesNeeded);
                    }
                    didit = true;
                } catch (Exception e) {
                    tries++;
                    Thread.sleep(10);
                }
            }
            logDebug("(System.currentTimeMillis()-runStartTime) = " + (System.currentTimeMillis() - runStartTime));
            logDebug("tries = " + tries);
        } catch (Exception e) {
            e.printStackTrace();
            logDebug("e.getMessage() = " + e.getMessage());
            prog.run(FREStepTypeConstants.frStepNone, 1, FREExecuteConstants.frExecuteFwd);
        }
    }

    private volatile long actuateJointMaxTime = -1;
    private volatile long actuateJointStartTime = -1;

    private void handleActuateJoints(ActuateJointsType ajCmd) throws PmException, InterruptedException {
        posReg98Updated = false;
        setCommandState(CommandStateEnumType.CRCL_WORKING);
        updatePosReg97();
        posReg97.refresh();
        final IJoint posReg97Joint = posReg97.formats(FRETypeCodeConstants.frJoint).queryInterface(IJoint.class);
        long max_time = 0;
        double diffs[] = new double[ajCmd.getActuateJoint().size()];
        int diffindex = 0;
        for (ActuateJointType aj : ajCmd.getActuateJoint()) {
            double val = aj.getJointPosition();
            final double origval = val;
            short number = (short) aj.getJointNumber();
            if (number < 1) {
                System.err.println("bad joint number : " + number);
                return;
            }
            if (number > this.upperJointLimits.length) {
                System.err.println("bad joint number > this.upperJointLimits.length : " + number + " > " + this.upperJointLimits);
                return;
            }
            if (number > this.lowerJointLimits.length) {
                System.err.println("bad joint number > this.lowerJointLimits.length : " + number + " > " + this.lowerJointLimits);
                return;
            }
            final double uplimit = this.upperJointLimits[number - 1];
            if (val > uplimit) {
                val = uplimit;
            }
            final double lowlimit = this.lowerJointLimits[number - 1];
            if (val < lowlimit) {
                val = lowlimit;
            }
            float curVal = (float) posReg97Joint.item(number);
            double absDiff = (double) Math.abs(val - curVal);
            if (diffindex < diffs.length) {
                diffs[diffindex] = absDiff;
                diffindex++;
            }
            double speed = DEFAULT_JOINT_SPEED;

            JointDetailsType jd = aj.getJointDetails();
            if (jd instanceof JointSpeedAccelType) {
                JointSpeedAccelType jsa = (JointSpeedAccelType) jd;
                if (jsa.getJointSpeed() != null) {
                    speed = jsa.getJointSpeed().doubleValue();
                    if (speed < Double.MIN_NORMAL) {
                        speed = Double.MIN_NORMAL;
                    }
                }
            }
            long time = (long) (1000 * absDiff / speed);
            if (time > max_time) {
                max_time = time;
            }
            posReg97Joint.item(number, val);
            posReg97.update();
            posReg97.refresh();
            double chkval = posReg97Joint.item(number);
            logDebug("Actuate joints: number=" + number + " \tval=\t" + val + " \torigval=" + origval + "\tup_limit=" + uplimit + "\tlow_limit=" + lowlimit);
            if (Math.abs(chkval - val) > 1e-4) {
                System.err.println("chkval = " + chkval);
            }
        }
        if (max_time < 10) {
            max_time = 10;
        }
        regNumeric97.regLong((int) max_time);
        posReg97.update();
        actuateJointMaxTime = max_time;
        logDebug("actuateJointMaxTime = " + actuateJointMaxTime);
        logDebug("diffs = " + Arrays.toString(diffs));
        actuateJointStartTime = System.currentTimeMillis();
        this.runTPProgram(move_joint_prog);

        if (moveThread != null) {
            moveThread.interrupt();
            try {
                moveThread.join(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            moveThread = null;
        }
    }

    public static final float DEFAULT_JOINT_SPEED = 10.0f;

    private Set<String> lastAlarms;

    private Set<String> getAlarms() {
        IAlarms alarms = robot.alarms();
        Map<java.util.Date, String> alarmMap = new java.util.TreeMap<>();
        java.util.Date maxResetDate = null;
        if (null != alarms) {
            for (Com4jObject alarm_obj : alarms) {
                IAlarm alarm = alarm_obj.queryInterface(IAlarm.class);
                if (null != alarm) {
                    if (alarm.errorClass() == 1) {
                        if (maxResetDate == null || alarm.timeStamp().after(maxResetDate)) {
                            maxResetDate = alarm.timeStamp();
                        }
                    }
                    alarmMap.put(alarm.timeStamp(), alarm.errorMessage());
                }
            }
        }
        Set<String> alarmSet = new TreeSet<>();
        for (Com4jObject alc4jo : alarms) {
            IAlarm alarm = alc4jo.queryInterface(IAlarm.class);
            if (null != alarm) {
                if (alarm.errorMotion() != 0 && alarm.timeStamp().after(maxResetDate)) {
                    alarmSet.add(alarm.errorMessage());
                }
            }
        }
        return alarmSet;
    }

    public FanucCRCLServerDisplayInterface getDisplayInterface() {
        return displayInterface;
    }

    public void setDisplayInterface(FanucCRCLServerDisplayInterface displayInterface) {
        this.displayInterface = displayInterface;
    }

    private volatile CRCLCommandType prevCmd = null;

    private void setPrevCmd(CRCLCommandType cmd) {
        this.prevCmd = cmd;
    }

    final CRCLSocket utilCrclSocket;

    public FanucCRCLMain() throws CRCLException, IOException {
        utilCrclSocket = new CRCLSocket();
        setDefaultJointReports();
        poseStatus.setPose(CRCLPosemath.identityPose());
        crclServerSocket = new CRCLServerSocket<>(FANUC_STATE_GENERATOR);
        crclServerSocket.addListener(crclSocketEventListener);
    }

    private boolean keepMoveToLog = false;

    /**
     * Get the value of keepMoveToLog
     *
     * @return the value of keepMoveToLog
     */
    public boolean isKeepMoveToLog() {
        return keepMoveToLog;
    }

    /**
     * Set the value of keepMoveToLog
     *
     * @param keepMoveToLog new value of keepMoveToLog
     */
    public void setKeepMoveToLog(boolean keepMoveToLog) {
        this.keepMoveToLog = keepMoveToLog;
        if (!keepMoveToLog) {
            closeMoveToLogFile();
        }
    }

    private void openMoveToLogFile() {
        try {
            File directory = null;
//            if (null != propertiesFile) {
//                directory = propertiesFile.getParentFile();
//            }
            if (null == moveLogFile || null == moveLogFilePrintStream) {
                if (null == directory) {
                    moveLogFile = File.createTempFile("fanucCrclMoveLog_" + getDateTimeString() + "_", ".csv");
                } else {
                    moveLogFile = File.createTempFile("fanucCrclMoveLog_" + getDateTimeString() + "_", ".csv", directory);
                }
                moveLogFilePrintStream = new PrintStream(new FileOutputStream(moveLogFile));
                moveLogFilePrintStream.println("current_time_ms,current_time_string,expectedEndMoveTimeDiff,id,start_x,start_y,start_z,end_x,end_y,end_z,distTran,distRot,moveTime,moveCheckCount,cmdTransSpeed,cmdRotSpeed,realTransSpeed,realRotSpeed,timeToWaitForLastMotionProgram,timeToStartMotionProgram,lastMotionProgramRunningCount,distances,reasons,updateTimes");
            }
        } catch (IOException ex) {
            Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeMoveToLogFile() {
        try {
            PrintStream ps = moveLogFilePrintStream;
            moveLogFile = null;
            moveLogFilePrintStream = null;
            if (null != ps) {
                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long lastUpdateStatusTime = -1;

    private synchronized void updateStatus(CRCLSocket cs) {
        try {
            CRCLStatusType status = readCachedStatusFromRobot();
            synchronized (status) {
                if (status.getCommandStatus() == null) {
                    status.setCommandStatus(new CommandStatusType());
                }
                CommandStatusType commandStatus = status.getCommandStatus();
                if (null != commandStatus) {
                    if (commandStatus.getCommandID() < 1) {
                        commandStatus.setCommandID(1);
                    }
                    if (commandStatus.getStatusID() < 1) {
                        commandStatus.setStatusID(1);
                    }
                    if (null == status.getCommandStatus().getCommandState()) {
                        setCommandState(CommandStateEnumType.CRCL_WORKING);
                    }
                }
                cs.writeStatus(status, validate);
            }
        } catch (Throwable t) {
            showError(t.toString());
        }
    }

    private CommandStateEnumType origState = CommandStateEnumType.CRCL_DONE;

    private long totalHandleCommandTime = 0;
    private long maxHandleCommandTime = 0;
    private long totalUpdateStatusTime = 0;
    private long maxUpdateStatusTime = 0;
    private long handleCommandCount = 0;
    private long updateStatusCount = 0;
    private volatile long cmdStartTime = -1;

    private void updatePerformance() {
        if (handleCommandCount > 0 && updateStatusCount > 0) {
            String extra = "";
            if (prevCmd instanceof ActuateJointsType) {
//                logDebug("actuateJointMaxTime = " + actuateJointMaxTime);
                long time_running = System.currentTimeMillis() - actuateJointStartTime;
//                logDebug("time_running = " + time_running);
                extra = "actuateJointMaxTime = " + actuateJointMaxTime + ",time_running = " + time_running;
            }
            displayInterface.updatePerformanceString("Performance: Commands: " + handleCommandCount + " maxTime=" + maxHandleCommandTime + " (ms), avgTime=" + (totalHandleCommandTime / handleCommandCount) + "(ms)"
                    + " Status: " + updateStatusCount + " maxTime=" + maxUpdateStatusTime + "(ms), avgTime=" + (totalUpdateStatusTime / updateStatusCount) + "(ms), TimeSinceCommandStart=" + (System.currentTimeMillis() - cmdStartTime) + extra);
        }
    }

//    private void handleClient(CRCLSocket cs) {
//        try {
//            while (!Thread.currentThread().isInterrupted()) {
//                try {
//                    CRCLCommandInstanceType cmdInstance = cs.readCommand(validate);
//                    handleClientCommand(cmdInstance, cs);
//                } catch (SocketException | EOFException se) {
//                    // probably just closing the connection.
//                    break;
//                } catch (ComException comEx) {
//                    showComException(comEx);
//                    disconnectRemoteRobot();
//                    connectRemoteRobot();
//                }
//            }
//        } catch (SocketException | EOFException se) {
//            // probably just closing the connection.
//        } catch (Exception ex) {
//            Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
//            showError(ex.getMessage());
//        } finally {
//            try {
//                logDebug("Closing connection with " + cs.getInetAddress() + ":" + cs.getPort());
//                clients.remove(cs);
//                cs.close();
//                logDebug("clients = " + clients);
//            } catch (IOException ex) {
//                Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
    private void handleClientCommand(CRCLCommandInstanceType cmdInstance, CRCLSocket cs) throws SAXException, InterruptedException, ExecutionException, JAXBException, IOException, ParserConfigurationException, CRCLException {
        long readRetTime = System.currentTimeMillis();
        final CRCLCommandType cmd = cmdInstance.getCRCLCommand();
        if (cmd instanceof GetStatusType) {
            updateStatus(cs);
            long updateStatusEndTime = System.currentTimeMillis();
            long updateStatusTimeDiff = updateStatusEndTime - readRetTime;
            totalUpdateStatusTime += updateStatusTimeDiff;
            if (updateStatusTimeDiff > maxUpdateStatusTime) {
                maxUpdateStatusTime = updateStatusTimeDiff;
            }
            updateStatusCount++;
        } else {
            try {
                if (null != this.displayInterface && this.displayInterface.getjCheckBoxLogAllCommands().isSelected()) {
                    logInfoString(utilCrclSocket.commandToSimpleString(cmd, 18, 70) + " recieved.");
                    logInfoString(cs.getLastCommandString());
                }
                CompletableFuture.runAsync(() -> handleCommand(cmdInstance), robotService).get();
                long handleCommandEndTime = System.currentTimeMillis();
                long handleCommandTimeDiff = handleCommandEndTime - readRetTime;
                totalHandleCommandTime += handleCommandTimeDiff;
                if (handleCommandTimeDiff > maxHandleCommandTime) {
                    maxHandleCommandTime = handleCommandTimeDiff;
                }
                handleCommandCount++;
            } catch (ComException comEx) {
                showError(comEx.getMessage() + " : cmd=" + utilCrclSocket.commandToSimpleString(cmd, 18, 70));
                Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, "cmd=" + utilCrclSocket.commandToPrettyString(cmd), comEx);
                disconnectRemoteRobot();
                connectRemoteRobot();
            }
        }
        updatePerformance();
    }

    private final AtomicInteger startCrclServerCount = new AtomicInteger();

    private void wrappedStartCrclServer() {
        int startStartCrclServerCount = startCrclServerCount.incrementAndGet();
        try {
            startCrclServer();
        } catch (IOException ex) {
            Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("localPort = " + localPort);
            System.err.println("startStartCrclServerCount = " + startStartCrclServerCount);
            System.err.println("startCrclServerCount.get() = " + startCrclServerCount.get());
            throw new RuntimeException(ex);
        }
    }

    private void runUpdateCachedStatus() {
        try {
            readCachedStatusFromRobot();
        } catch (Exception ex) {
            Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, "", ex);
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        }
    }

//    Future<?> crclServerFuture = null;
    public synchronized void startCrclServer() throws IOException {
//        stopCrclServer();
//        es = Executors.newCachedThreadPool(daemonThreadFactory);
//        ss = new ServerSocket(localPort);
//        crclServerFuture = es.submit(() -> {
//            while (!Thread.currentThread().isInterrupted()) {
//                try {
//                    Thread.currentThread().setName("FanucCRCL.acceptClients.ss=" + ss);
//                    CRCLSocket cs = new CRCLSocket(ss.accept());
//                    clients.add(cs);
//                    es.submit(() -> {
//                        Thread.currentThread().setName("FanucCRCL.handleClient.cs=" + cs);
//                        handleClient(cs);
//                    });
//                } catch (IOException ex) {
//                    Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
//                    return;
//                }
//            }
//        });
        crclServerSocket.setPort(localPort);
        crclServerSocket.setThreadNamePrefix("FanucCRCLServer");
        crclServerSocket.setServerSideStatus(status);
        crclServerSocket.setUpdateStatusRunnable(this::runUpdateCachedStatus);
        crclServerSocket.setAutomaticallySendServerSideStatus(true);
        crclServerSocket.setAutomaticallyConvertUnits(true);
        crclServerSocket.setServerUnits(new UnitsTypeSet());
        crclServerSocket.start();
    }

    private boolean robotIsConnected = false;

    public synchronized void handleCommand(CRCLCommandInstanceType cmdInstance) {
        CRCLCommandType cmd = cmdInstance.getCRCLCommand();
        cmdStartTime = System.currentTimeMillis();
        try {
            synchronized (status) {
                if (null == status.getCommandStatus()) {
                    status.setCommandStatus(new CommandStatusType());
                    status.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_ERROR);
                }
                CommandStatusType cst = status.getCommandStatus();
                if (null != cst) {
                    origState = cst.getCommandState();
                    if (cst.getCommandState() == CommandStateEnumType.CRCL_DONE) {
                        setCommandState(CommandStateEnumType.CRCL_WORKING);
                    }
                    cst.setCommandID(cmd.getCommandID());
                    cst.setStatusID(1);
                    cst.setProgramFile(cmdInstance.getProgramFile());
                    cst.setProgramIndex(cmdInstance.getProgramIndex());
                    cst.setProgramLength(cmdInstance.getProgramLength());
                }
            }

            lastCheckAtPosition = false;
            if (cmd instanceof StopMotionType) {
                handleStopMotion((StopMotionType) cmd);
                setPrevCmd(cmd);
                return;
            }
            if (null == robot || !robotIsConnected || null == groupPos) {
                showError(utilCrclSocket.commandToSimpleString(cmd, 18, 70) + " recieved when robot not connected or not initialized.");
                return;
            }
            if (status.getCommandStatus().getCommandState() != CommandStateEnumType.CRCL_ERROR) {
                status.getCommandStatus().setStateDescription("");
            }
            updateTimes = new ArrayList<>();
            if (cmd instanceof InitCanonType) {
                handleInitCanon((InitCanonType) cmd);
            } else if (cmd instanceof EndCanonType) {
                handleEndCanon((EndCanonType) cmd);
            } else if (cmd instanceof MoveToType) {
                handleMoveTo((MoveToType) cmd);
            } else if (cmd instanceof MoveThroughToType) {
                handleMoveThroughTo((MoveThroughToType) cmd);
            } else if (cmd instanceof SetEndEffectorType) {
                handleSetEndEffector((SetEndEffectorType) cmd);
            } else if (cmd instanceof OpenToolChangerType) {
                handleOpenToolChanger((OpenToolChangerType) cmd);
            } else if (cmd instanceof CloseToolChangerType) {
                handleCloseToolChanger((CloseToolChangerType) cmd);
            } else if (cmd instanceof SetAngleUnitsType) {
                handleSetAngleUnits((SetAngleUnitsType) cmd);
            } else if (cmd instanceof SetForceUnitsType) {
                handleSetForceUnits((SetForceUnitsType) cmd);
            } else if (cmd instanceof SetTransSpeedType) {
                handleSetTransSpeed((SetTransSpeedType) cmd);
            } else if (cmd instanceof SetRotSpeedType) {
                handleSetRotSpeed((SetRotSpeedType) cmd);
            } else if (cmd instanceof ActuateJointsType) {
                handleActuateJoints((ActuateJointsType) cmd);
            } else if (cmd instanceof SetLengthUnitsType) {
//                handleSetLengthUnits((SetLengthUnitsType) cmd);
            } else if (cmd instanceof SetEndPoseToleranceType) {
                handleSetEndPoseTolerance((SetEndPoseToleranceType) cmd);
            } else if (cmd instanceof DwellType) {
                handleDwell((DwellType) cmd);
            } else if (cmd instanceof ConfigureJointReportsType) {
                handleConfigureJointReports((ConfigureJointReportsType) cmd);
            } else if (cmd instanceof ConfigureStatusReportType) {
                handleConfigureStatusReport((ConfigureStatusReportType) cmd);
            } else if (cmd instanceof MessageType) {
                handleMessage((MessageType) cmd);
            } else {
                showError("Unimplemented  command :" + cmd.getClass().getSimpleName());
            }
        } catch (Exception ex) {
            showError(ex.getMessage());
            Logger.getLogger(FanucCRCLMain.class.getCanonicalName()).log(Level.SEVERE, "handle command", ex);
        }
        setPrevCmd(cmd);
    }

    private IRobot2 robot;
    private IIndGroupPosition groupPos;
    private ITPProgram close_gripper_prog;
    private ITPProgram open_gripper_prog;
    private ITPProgram move_linear_prog;
    private ITPProgram move_w_time_prog;
    private ITPProgram move_joint_prog;
    private ITPProgram tool_open_prog;
    private ITPProgram tool_close_prog;
    private IVar overrideVar = null;
    private IVar morSafetyStatVar = null;
    private IVar moveGroup1RobMoveVar = null;
    private IVar moveGroup1ServoReadyVar = null;

    private long isMovingLastCheckTime = 0;
    private boolean lastIsMoving = false;

    private File propertiesFile;

    /**
     * Get the value of propertiesFile
     *
     * @return the value of propertiesFile
     */
    public File getPropertiesFile() {
        return propertiesFile;
    }

    /**
     * Set the value of propertiesFile
     *
     * @param propertiesFile new value of propertiesFile
     */
    public void setPropertiesFile(File propertiesFile) {
        this.propertiesFile = propertiesFile;
        setJointLimitsFile(new File(propertiesFile.getParentFile(), "fanucCRLCJointLimits.txt"));
        setCartLimitsFile(new File(propertiesFile.getParentFile(), "fanucCRLCCartLimits.txt"));
    }

    public void loadProperties() {
        if (null != this.propertiesFile && propertiesFile.exists()) {
            Properties props = new Properties();
            try (FileReader reader = new FileReader(propertiesFile)) {
                props.load(reader);
                String keepMoveToLogString = (String) props.get("keepMoveToLog");
                if (null != keepMoveToLogString) {
                    keepMoveToLog = Boolean.parseBoolean(keepMoveToLogString);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        readAndApplyUserCartLimits();
        readAndApplyUserJointLimits();
    }

    public File getMoveLogFile() {
        return moveLogFile;
    }

    public void saveProperties() {
        if (null != this.propertiesFile) {
            Properties props = new Properties();
            props.put("keepMoveToLog", Boolean.valueOf(keepMoveToLog));
            try (FileWriter fw = new FileWriter(propertiesFile)) {
                props.store(fw, "");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        saveCartLimits(new PmCartesian(xMin, yMin, zMin), new PmCartesian(xMax, yMax, zMax));
        saveJointLimits(this.lowerJointLimits, this.upperJointLimits);
    }

    public boolean isMoving() {
        if (System.currentTimeMillis() - isMovingLastCheckTime < 20) {
            return lastIsMoving;
        }
        if (null != moveGroup1RobMoveVar) {
            moveGroup1RobMoveVar.refresh();
            Object val = moveGroup1RobMoveVar.value();
            if (val instanceof Boolean) {
                lastIsMoving = (Boolean) val;
            }
            isMovingLastCheckTime = System.currentTimeMillis();
            return lastIsMoving;
        }
        return false;
    }

    private IVar reg96Var = null;
    private IVar reg97Var = null;
    private IVar reg98Var = null;

    private IRegNumeric regNumeric96 = null;
    private IRegNumeric regNumeric97 = null;
    private IRegNumeric regNumeric98 = null;
    private ISysGroupPosition posReg98 = null;
    private ISysGroupPosition posReg97 = null;
    private FanucCRCLServerDisplayInterface displayInterface = null;

    private IRobotNeighborhood neighborhood = null;
    private String neighborhoodname = "AgilityLabLRMate200iD";

    public String getNeighborhoodname() {
        return neighborhoodname;
    }

    public XFutureVoid setNeighborhoodname(String neighborhoodname) {
        if (this.neighborhoodname != neighborhoodname) {
            this.neighborhoodname = neighborhoodname;
            if (null != displayInterface) {
                displayInterface.getjTextFieldRobotNeighborhoodPath().setText(remoteRobotHost);
            }
            this.disconnectRemoteRobot();
            return this.connectRemoteRobot();
        } else {
            return XFutureVoid.completedFutureWithName("setNeighborhoodname.nochange");
        }
    }

    List<ITPProgram> tpPrograms = new ArrayList<>();

    private boolean preferRobotNeighborhood = false;

    /**
     * Get the value of preferRobotNeighborhood
     *
     * @return the value of preferRobotNeighborhood
     */
    public boolean isPreferRobotNeighborhood() {
        return preferRobotNeighborhood;
    }

    /**
     * Set the value of preferRobotNeighborhood
     *
     * @param preferRobotNeighborhood new value of preferRobotNeighborhood
     */
    public XFutureVoid setPreferRobotNeighborhood(boolean preferRobotNeighborhood) {
        if (this.preferRobotNeighborhood != preferRobotNeighborhood) {
            this.preferRobotNeighborhood = preferRobotNeighborhood;
            if (null != displayInterface) {
                displayInterface.setPreferRobotNeighborhood(preferRobotNeighborhood);
            }
            this.disconnectRemoteRobot();
            return this.connectRemoteRobot();
        } else {
            return XFutureVoid.completedFutureWithName("setPreferRobotNeighborhood.nochange");
        }
    }

    float lowerJointLimits[] = new float[]{-10000.f, -10000.f, -10000.f, -10000.f, -10000.f, -10000.f};
    float upperJointLimits[] = new float[]{10000.f, 10000.f, 10000.f, 10000.f, 10000.f, 10000.f};

    public void applyAdditionalCartLimits(PmCartesian min, PmCartesian max) {
        xMax = (float) Math.min(xMax, max.x);
        xMin = (float) Math.max(xMin, min.x);
        yMax = (float) Math.min(yMax, max.y);
        yMin = (float) Math.max(yMin, min.y);
        zMax = (float) Math.min(zMax, max.z);
        zMin = (float) Math.max(zMin, min.z);
        settingsStatus.setMaxCartesianLimit(point(xMax, yMax, zMax));
        settingsStatus.setMinCartesianLimit(point(xMin, yMin, zMin));
    }

    public void saveCartLimits(PmCartesian min, PmCartesian max) {
        try (PrintWriter pw = new PrintWriter(cartLimitsFile)) {
            pw.println("min.x=" + min.x);
            pw.println("min.y=" + min.y);
            pw.println("min.z=" + min.z);
            pw.println("max.x=" + max.x);
            pw.println("max.y=" + max.y);
            pw.println("max.z=" + max.z);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void applyAdditionalJointLimits(float[] min, float[] max) {
        settingsStatus.getJointLimits().clear();
        for (int i = 0; i < min.length && i < max.length && i < lowerJointLimits.length; i++) {
            lowerJointLimits[i] = min[i];
            upperJointLimits[i] = max[i];
            JointLimitType jointLimit = new JointLimitType();
            jointLimit.setJointNumber(i + 1);
            jointLimit.setJointMaxPosition(Double.valueOf(upperJointLimits[i]));
            jointLimit.setJointMinPosition(Double.valueOf(lowerJointLimits[i]));
            settingsStatus.getJointLimits().add(jointLimit);
        }
    }

    public void saveJointLimits(float[] min, float[] max) {
        try (PrintWriter pw = new PrintWriter(jointLimitsFile)) {
            for (int i = 0; i < max.length && i < min.length; i++) {
                pw.println("min[" + i + "]=" + min[i]);
                pw.println("max[" + i + "]=" + max[i]);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private File jointLimitsFile = JOINT_LIMITS_FILE;

    /**
     * Get the value of jointLimitsFile
     *
     * @return the value of jointLimitsFile
     */
    public File getJointLimitsFile() {
        return jointLimitsFile;
    }

    /**
     * Set the value of jointLimitsFile
     *
     * @param jointLimitsFile new value of jointLimitsFile
     */
    public void setJointLimitsFile(File jointLimitsFile) {
        this.jointLimitsFile = jointLimitsFile;
    }

    private File cartLimitsFile = CART_LIMITS_FILE;

    /**
     * Get the value of cartLimitsFile
     *
     * @return the value of cartLimitsFile
     */
    public File getCartLimitsFile() {
        return cartLimitsFile;
    }

    /**
     * Set the value of cartLimitsFile
     *
     * @param cartLimitsFile new value of cartLimitsFile
     */
    public void setCartLimitsFile(File cartLimitsFile) {
        this.cartLimitsFile = cartLimitsFile;
    }

    private static final File CART_LIMITS_FILE = new File(Utils.getCrclUserHomeDir(),
            ".fanucCRLCCartLimits.txt");
    private static final File JOINT_LIMITS_FILE = new File(Utils.getCrclUserHomeDir(),
            ".fanucCRLCJointLimits.txt");

    private void findString(String input, String token, Consumer<String> tailConsumer) {
        int index = input.indexOf(token);
        if (index >= 0) {
            String tail = input.substring(index + token.length());
            tailConsumer.accept(tail);
        }
    }

    private void findIndexedString(String input, String token, BiConsumer<Integer, String> tailConsumer) {
        int i0 = input.indexOf('[');
        int i1 = input.indexOf(']');
        if (i0 > 0 && i1 > i0) {
            String indexString = input.substring(i0 + 1, i1);
            int indexVal = Integer.valueOf(indexString);
            String newInput = input.substring(0, i0 + 1) + input.substring(i1);
            int index = newInput.indexOf(token);
            if (index >= 0) {
                String tail = newInput.substring(index + token.length());
                tailConsumer.accept(indexVal, tail);
            }
        }
    }

    public void readAndApplyUserCartLimits() {
        PmCartesian min = new PmCartesian(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        PmCartesian max = new PmCartesian(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        if (null != cartLimitsFile && cartLimitsFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(cartLimitsFile))) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    findString(line, "min.x=", t -> min.x = Double.parseDouble(t));
                    findString(line, "max.x=", t -> max.x = Double.parseDouble(t));
                    findString(line, "min.y=", t -> min.y = Double.parseDouble(t));
                    findString(line, "max.y=", t -> max.y = Double.parseDouble(t));
                    findString(line, "min.z=", t -> min.z = Double.parseDouble(t));
                    findString(line, "max.z=", t -> max.z = Double.parseDouble(t));
                }
            } catch (IOException ex) {
                Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        applyAdditionalCartLimits(min, max);
    }

    public void readAndApplyUserJointLimits() {
        float min[] = new float[6];
        float max[] = new float[6];
        for (int i = 0; i < max.length; i++) {
            max[i] = Float.POSITIVE_INFINITY;
            min[i] = Float.NEGATIVE_INFINITY;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(jointLimitsFile))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                findIndexedString(line, "min[]=", (i, t) -> min[i] = Float.valueOf(t));
                findIndexedString(line, "max[]=", (i, t) -> max[i] = Float.valueOf(t));
            }
        } catch (IOException ex) {
            Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        applyAdditionalJointLimits(min, max);
    }

    public static ThreadFactory daemonThreadFactory
            = new ThreadFactory() {
        public Thread newThread(Runnable r) {
            Thread t = Executors.defaultThreadFactory().newThread(r);
            t.setDaemon(true);
            String oldname = t.getName();
            if (!oldname.startsWith("Fanuc")) {
                t.setName("FanucCRCL." + oldname);
            }
            return t;
        }
    };

    public XFutureVoid connectRemoteRobot() {
        if (null == robotService) {
            robotService = Executors.newSingleThreadExecutor(daemonThreadFactory);
        }
        return XFutureVoid.runAsync("connectRemoteRobot", this::connectRemoteRobotInternal, robotService);
    }

    private static final List<String> programNamesToCheckList = Arrays.asList(
            "GRIPPER_OPEN",
            "MOVE_W_TIME",
            "MOVE_JOINT",
            "TOOL_OPEN",
            "TOOL_CLOSE",
            "GRIPPER_CLOSE");

    private static final HashSet<String> programNamesToCheckSet = new HashSet<>(programNamesToCheckList);

    private volatile int overrideValue = 100;

    private void setOverrideValue(int overrideValue) {
        this.overrideValue = Math.max(1, Math.min(100, overrideValue));
    }

    private synchronized void connectRemoteRobotInternal() {
        try {
            this.lastIsMoving = false;
            this.lastRobotIsConnected = true;
            this.last_safety_stat = 0;
            this.lastServoReady = true;
            if (preferRobotNeighborhood) {
                if (null == neighborhood) {
                    logDebug("Calling createFRCRobotNeighborhood ...");
                    neighborhood = createFRCRobotNeighborhood();
                }
                IRNRobots robots = neighborhood.robots();
                for (Com4jObject c4jo : robots) {
                    IRNRobot irnrobot = c4jo.queryInterface(IRNRobot.class);
                    if (null != irnrobot) {
                        if (irnrobot.name().equals(neighborhoodname)) {
                            Com4jObject robot_object = irnrobot.robotServer();
                            robot = robot_object.queryInterface(IRobot2.class);
                        }
                    }
                }
                robots.dispose();
            }
            if (null == robot) {
                logDebug("Calling createFRCRobot ...");
                robot = com.github.wshackle.fanuc.robotserver.ClassFactory.createFRCRobot();
                logDebug("createFRCRobot returned " + robot);
                setPreferRobotNeighborhood(false);
            }

            robotIsConnected = robot.isConnected();

            if (!robotIsConnected) {
                logDebug("Connecting to " + remoteRobotHost + " ...");
                int tries = 0;
                robot.connectEx(remoteRobotHost, true, 1, 1);
                while (!robot.isConnected() && !Thread.currentThread().isInterrupted() && tries < 10) {
                    tries++;
                    logDebug("Connecting to " + remoteRobotHost + " ... : tries = " + tries + "/10");
                    Thread.sleep(200);
                }
                robotIsConnected = robot.isConnected();
                logDebug("robotIsConnected = " + robotIsConnected);
            }

            if (!robotIsConnected) {
                showError("Failed to connect to robot: " + remoteRobotHost);
                return;
            }

            IIndPosition iip = robot.createIndependentPosition(FREGroupBitMaskConstants.frGroup1BitMask);
            iip.record();
            groupPos = iip.group((short) 1);

            logDebug("Getting list of programs ...");
            IPrograms programs = robot.programs();
            if (null != programs) {
                synchronized (tpPrograms) {
                    if (null != displayInterface) {
                        displayInterface.setPrograms(null);
                    }
                    tpPrograms.clear();
                    for (Com4jObject com4jo_program : programs) {
                        ITPProgram program = com4jo_program.queryInterface(ITPProgram.class);
                        if (null != program) {
                            tpPrograms.add(program);
                        }
                        if (null != program && program.name().equalsIgnoreCase("GRIPPER_OPEN")) {
                            logDebug("Found open_gripper program.");
                            open_gripper_prog = program;

                        }
                        if (null != program && program.name().equalsIgnoreCase("MOVE_W_TIME")) {
                            logDebug("Found MOVE_W_TIME program.");
                            move_w_time_prog = program;
                        }
                        if (null != program && program.name().equalsIgnoreCase("MOVE_LINEAR")) {
                            logDebug("Found MOVE_LINEAR program.");
                            move_linear_prog = program;
                        }
                        if (null != program && program.name().equalsIgnoreCase("MOVE_JOINT")) {
                            logDebug("Found MOVE_JOINT program.");
                            move_joint_prog = program;
                        }
                        if (null != program && program.name().equalsIgnoreCase("TOOL_OPEN")) {
                            logDebug("Found MOVE_JOINT program.");
                            tool_open_prog = program;
                        }
                        if (null != program && program.name().equalsIgnoreCase("TOOL_CLOSE")) {
                            logDebug("Found MOVE_JOINT program.");
                            tool_close_prog = program;
                        }
                        if (null != program && program.name().equalsIgnoreCase("GRIPPER_CLOSE")) {
                            logDebug("Found close_gripper program.");
                            close_gripper_prog = program;
                        }
                    }
                }
            }

            reg96Var = robot.regNumerics().item(96, null).queryInterface(IVar.class);
            reg96Var.noUpdate(true);
            regNumeric96 = ((Com4jObject) reg96Var.value()).queryInterface(IRegNumeric.class);
            regNumeric96.regLong(10_000);
            reg97Var = robot.regNumerics().item(97, null).queryInterface(IVar.class);
            regNumeric97 = ((Com4jObject) reg97Var.value()).queryInterface(IRegNumeric.class);
            regNumeric97.regLong(50);
            reg98Var = robot.regNumerics().item(98, null).queryInterface(IVar.class);
            regNumeric98 = ((Com4jObject) reg98Var.value()).queryInterface(IRegNumeric.class);
            regNumeric98.regFloat(DEFAULT_CART_SPEED);

            posReg98 = robot.regPositions().item(98, null).queryInterface(ISysPosition.class).group((short) 1);
            posReg98.record();
            posReg97 = robot.regPositions().item(97, null).queryInterface(ISysPosition.class).group((short) 1);
            posReg97.record();
            logDebug("Calling robot.sysVariables() ...");
            IVars sysvars = robot.sysVariables();
            overrideVar = sysvars.item("$MCR.$GENOVERRIDE", null).queryInterface(IVar.class);
            if (null != overrideVar) {
                overrideVar.refresh();
                Object overrideValueObject = overrideVar.value();
                System.out.println("overrideValueObject = " + overrideValueObject);
//                if(null != overrideValueObject) {
//                    System.out.println("overrideValueObject.getClass() = " + overrideValueObject.getClass());
//                }
                if (overrideValueObject instanceof Integer) {
                    setOverrideValue((Integer) overrideValueObject);
                }
            }
            morSafetyStatVar = sysvars.item("$MOR.$safety_stat", null).queryInterface(IVar.class);
            if (null != overrideVar) {
                morSafetyStatVar.refresh();
            }
            moveGroup1RobMoveVar = sysvars.item("$MOR_GRP[1].$ROB_MOVE", null).queryInterface(IVar.class);
            if (null != moveGroup1RobMoveVar) {
                moveGroup1RobMoveVar.refresh();
            }
            moveGroup1ServoReadyVar = sysvars.item("$MOR_GRP[1].$SERVO_READY", null).queryInterface(IVar.class);
            if (null != moveGroup1RobMoveVar) {
                moveGroup1RobMoveVar.refresh();
            }
            xMax = yMax = zMax = 10000.0f;
            xMin = yMin = zMin = -10000.0f;

//            readCartLimitsFromRobot();
            readAndApplyUserCartLimits();
            for (int i = 0; i < 6; i++) {
                IVar jointUpperLimVar = sysvars.item("$MRR_GRP[1].$UPPERLIMSDF[" + (i + 1) + "]", null).queryInterface(IVar.class);
                this.upperJointLimits[i] = (Float) jointUpperLimVar.value();
            }
            readAndApplyUserJointLimits();
            updateJFrame();
            logDebug("Connect to Remote Fanuc Robot complete.");
        } catch (ComException comEx) {
            showComException(comEx);
        } catch (Exception e) {
            e.printStackTrace();
            showError(e.toString());
        }
    }

    public void readCartLimitsFromRobot() {
        IVars sysvars = robot.sysVariables();
        IVar xLimitVar1 = sysvars.item("$DCSS_CPC[1].$X[1]", null).queryInterface(IVar.class);
        if (null != xLimitVar1) {
            xMax = xMin = (Float) xLimitVar1.value();
        }
        IVar xLimitVar2 = sysvars.item("$DCSS_CPC[1].$X[2]", null).queryInterface(IVar.class);
        if (null != xLimitVar2) {
            float v = (Float) xLimitVar2.value();
            if (xMax < v) {
                xMax = v;
            }
            if (xMin > v) {
                xMin = v;
            }
        }
        IVar xLimitVar3 = sysvars.item("$DCSS_CPC[1].$X[3]", null).queryInterface(IVar.class);
        if (null != xLimitVar3) {
            float v = (Float) xLimitVar3.value();
            if (xMax < v) {
                xMax = v;
            }
            if (xMin > v) {
                xMin = v;
            }
        }
        IVar xLimitVar4 = sysvars.item("$DCSS_CPC[1].$X[4]", null).queryInterface(IVar.class);
        if (null != xLimitVar4) {
            float v = (Float) xLimitVar4.value();
            if (xMax < v) {
                xMax = v;
            }
            if (xMin > v) {
                xMin = v;
            }
        }

        IVar yLimitVar1 = sysvars.item("$DCSS_CPC[1].$Y[1]", null).queryInterface(IVar.class);
        if (null != yLimitVar1) {
            yMax = yMin = (Float) yLimitVar1.value();
        }
        IVar yLimitVar2 = sysvars.item("$DCSS_CPC[1].$Y[2]", null).queryInterface(IVar.class);
        if (null != yLimitVar2) {
            float v = (Float) yLimitVar2.value();
            if (yMax < v) {
                yMax = v;
            }
            if (yMin > v) {
                yMin = v;
            }
        }
        IVar yLimitVar3 = sysvars.item("$DCSS_CPC[1].$Y[3]", null).queryInterface(IVar.class);
        if (null != yLimitVar3) {
            float v = (Float) yLimitVar3.value();
            if (yMax < v) {
                yMax = v;
            }
            if (yMin > v) {
                yMin = v;
            }
        }
        IVar yLimitVar4 = sysvars.item("$DCSS_CPC[1].$Y[4]", null).queryInterface(IVar.class);
        if (null != yLimitVar4) {
            float v = (Float) yLimitVar4.value();
            if (yMax < v) {
                yMax = v;
            }
            if (yMin > v) {
                yMin = v;
            }
        }

        IVar zLimitVar1 = sysvars.item("$DCSS_CPC[1].$Z1", null).queryInterface(IVar.class);
        if (null != zLimitVar1) {
            zMax = zMin = (Float) zLimitVar1.value();
        }
        IVar zLimitVar2 = sysvars.item("$DCSS_CPC[1].$Z2", null).queryInterface(IVar.class);
        if (null != zLimitVar2) {
            float v = (Float) zLimitVar2.value();
            if (zMax < v) {
                zMax = v;
            }
            if (zMin > v) {
                zMin = v;
            }
        }

        for (int i = 0; i < 6; i++) {
            IVar jointLowerLimVar = sysvars.item("$MRR_GRP[1].$LOWERLIMSDF[" + (i + 1) + "]", null).queryInterface(IVar.class);
            this.lowerJointLimits[i] = (Float) jointLowerLimVar.value();
        }
        settingsStatus.setMaxCartesianLimit(point(xMax, yMax, zMax));
        settingsStatus.setMinCartesianLimit(point(xMin, yMin, zMin));
    }
    public static final float DEFAULT_CART_SPEED = 100.0f;

    private String lastComExString = null;
    private long last_com_ex_time = 0;

    public void showComException(ComException comEx) {
        String newMsg = comEx.getMessage();
        if (!newMsg.equals(lastComExString) || (System.currentTimeMillis() - last_com_ex_time) > 5000) {
            showError(newMsg);
            Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, comEx);
            lastComExString = newMsg;
            last_com_ex_time = System.currentTimeMillis();
        }
    }

    public void updateJFrame() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (null != displayInterface) {
                    displayInterface.setMain(FanucCRCLMain.this);
                    displayInterface.setConnected(robotIsConnected);
                    displayInterface.setPrograms(tpPrograms);
                    displayInterface.updateCartesianLimits(xMax, xMin, yMax, yMin, zMax, zMin);
                    displayInterface.updateJointLimits(lowerJointLimits, upperJointLimits);
                    displayInterface.setOverrideVar(getOverideVar());
                    displayInterface.setMorSafetyStatVar(getMorSafetyStatVar());
                    displayInterface.setMoveGroup1ServoReadyVar(getMoveGroup1ServoReadyVar());
                }
            }
        });
    }

    // Taken from https://github.com/ros-industrial/fanuc/blob/indigo-devel/fanuc_driver/karel/libind_rs.kl
//// CONST
//	                     -- '$MOR.$safety_stat', R-J3iC Software Reference Manual, 
//	                     --   MARACSSRF03061E Rev A
//	MFS_EMGOP    =    1  -- E-Stop SOP
//	MFS_EMGTP    =    2  -- E-Stop TP
//	MFS_DEADMAN  =    4  -- TP Deadman
//	MFS_FENCE    =    8  -- Fence Open
//	MFS_ROT      =   16  -- Over Travel
//	MFS_HBK      =   32  -- Hand Broken
//	MFS_EMGEX    =   64  -- External E-Stop
//	MFS_PPABN    =  128  -- ?
//	MFS_BLTBREAK =  256  -- Belt Broken
//	MFS_ENABLE   =  512  -- TP Enable
//	MFS_FALM     = 1024  -- Alarm?
    public static final int MOR_SAFETY_STAT_ESTOP_SOP_FLAG = 1;
    public static final int MOR_SAFETY_STAT_ESTOP_TP_FLAG = 2;
    public static final int MOR_SAFETY_STAT_TP_DEADMAN_FLAG = 4;
    public static final int MOR_SAFETY_STAT_FENCE_OPEN_FLAG = 8;
    public static final int MOR_SAFETY_STAT_OVER_TRAVEL_FLAG = 16;
//    public static final int MOR_SAFETY_STAT_HAND_BROKEN_FLAG = 32; not sure what this is but seems to be always set
    public static final int MOR_SAFETY_STAT_EXTERNAL_ESTOP_FLAG = 64;
    public static final int MOR_SAFETY_STAT_BELT_BROKEN_FLAG = 256;
    public static final int MOR_SAFETY_STAT_TP_ENABLE_FLAG = 512;
    public static final int MOR_SAFETY_STAT_ALARM_FLAG = 1024;

    public static boolean isMoreSafetyStatError(int val) {
        return (val
                & (MOR_SAFETY_STAT_ESTOP_SOP_FLAG
                | MOR_SAFETY_STAT_ESTOP_TP_FLAG
                | MOR_SAFETY_STAT_TP_DEADMAN_FLAG
                | MOR_SAFETY_STAT_FENCE_OPEN_FLAG
                | MOR_SAFETY_STAT_OVER_TRAVEL_FLAG
                | MOR_SAFETY_STAT_EXTERNAL_ESTOP_FLAG
                | MOR_SAFETY_STAT_TP_ENABLE_FLAG
                | MOR_SAFETY_STAT_ALARM_FLAG)) != 0;
    }

    public static String morSafetyStatToString(int val) {
        String ret = val + " : "
                + ((val & MOR_SAFETY_STAT_ESTOP_SOP_FLAG) == MOR_SAFETY_STAT_ESTOP_SOP_FLAG ? " Main E-Stop | " : "")
                + ((val & MOR_SAFETY_STAT_ESTOP_TP_FLAG) == MOR_SAFETY_STAT_ESTOP_TP_FLAG ? " Teach-Pendant E-Stop | " : "")
                + ((val & MOR_SAFETY_STAT_TP_DEADMAN_FLAG) == MOR_SAFETY_STAT_TP_DEADMAN_FLAG ? " Teach-Pendant Deadman ( Need to set switch to Auto and Turn off Pendant) | " : "")
                + ((val & MOR_SAFETY_STAT_FENCE_OPEN_FLAG) == MOR_SAFETY_STAT_FENCE_OPEN_FLAG ? " Fence Open | " : "")
                + ((val & MOR_SAFETY_STAT_OVER_TRAVEL_FLAG) == MOR_SAFETY_STAT_OVER_TRAVEL_FLAG ? " Over Travel | " : "")
                //                + ((val & MOR_SAFETY_STAT_HAND_BROKEN_FLAG) == MOR_SAFETY_STAT_HAND_BROKEN_FLAG ? " Hand Broken | " : "")
                + ((val & MOR_SAFETY_STAT_EXTERNAL_ESTOP_FLAG) == MOR_SAFETY_STAT_EXTERNAL_ESTOP_FLAG ? " External E-Stop | " : "")
                + ((val & MOR_SAFETY_STAT_BELT_BROKEN_FLAG) == MOR_SAFETY_STAT_BELT_BROKEN_FLAG ? " Belt Broken | " : "")
                + ((val & MOR_SAFETY_STAT_TP_ENABLE_FLAG) == MOR_SAFETY_STAT_TP_ENABLE_FLAG ? " TP Enable | " : "")
                + ((val & MOR_SAFETY_STAT_ALARM_FLAG) == MOR_SAFETY_STAT_ALARM_FLAG ? " Alarm | " : "")
                + "  ";
        ret = ret.trim();
        if (ret.endsWith("|")) {
            return ret.substring(0, ret.length() - 1).trim();
        }
        return ret;
    }

    private static FanucCRCLMain main = null;

    public static FanucCRCLMain getMain() {
        return main;
    }

    public static void main(String[] args) throws IOException, CRCLException {
        main = new FanucCRCLMain();
        String neighborhoodname = args.length > 0 ? args[0] : "AgilityLabLRMate200iD";
        String host = args.length > 1 ? args[1] : "192.168.1.34"; //"129.6.78.111";
        int port = args.length > 2 ? Integer.valueOf(args[2]) : CRCLSocket.DEFAULT_PORT;
        boolean prefRNN = (args.length > 3) ? Boolean.valueOf(args[3]) : false;
        main.startDisplayInterface();
        main.start(prefRNN, neighborhoodname, host, port);
//        logDebug("Press enter \"stop\" to quit");
//        Scanner in = new Scanner(System.in);
//        while (!in.nextLine().equals("stop")) {
//            logDebug("Enter \"stop\" to quit");
//        }
//        main.stop();
//        main = null;
    }
}
