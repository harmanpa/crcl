/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import crcl.base.LengthUnitEnumType;
import crcl.base.MessageType;
import crcl.base.MoveThroughToType;
import crcl.base.MoveToType;
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
import crcl.utils.CRCLException;
import crcl.utils.CRCLPosemath;
import crcl.utils.CRCLSocket;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.SocketException;
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
import static crcl.utils.CRCLPosemath.point;
import java.util.concurrent.Future;

/**
 *
 * @author shackle
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
    public void setRemoteRobotHost(String remoteRobotHost) {
        if (this.remoteRobotHost != remoteRobotHost) {
            this.remoteRobotHost = remoteRobotHost;
            if (null != displayInterface) {
                displayInterface.getjTextFieldHostName().setText(remoteRobotHost);
            }
            this.disconnectRemoteRobot();
            this.connectRemoteRobot();
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

    public void startDisplayInterface() {
        java.awt.EventQueue.invokeLater(() -> {
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
        });
    }

    public void start(boolean preferRobotNeighborhood, String neighborhoodname, String remoteRobotHost, int localPort) {
        try {
            this.preferRobotNeighborhood = preferRobotNeighborhood;
            this.neighborhoodname = neighborhoodname;
            this.remoteRobotHost = remoteRobotHost;
            this.localPort = localPort;

            connectRemoteRobot();
            startCrclServer();
        } catch (Exception exception) {
            exception.printStackTrace();
            showError(exception.toString());
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

    private LengthUnitEnumType lengthUnit = LengthUnitEnumType.MILLIMETER;
    private double lengthScale = 1.0;

    /**
     * Get the value of lengthUnit
     *
     * @return the value of lengthUnit
     */
    public LengthUnitEnumType getLengthUnit() {
        return lengthUnit;
    }

    /**
     * Set the value of lengthUnit
     *
     * @param lengthUnit new value of lengthUnit
     */
    public void setLengthUnit(LengthUnitEnumType lengthUnit) {
        this.lengthUnit = lengthUnit;
        switch (lengthUnit) {
            case METER:
                lengthScale = 1000.0;
                break;

            case MILLIMETER:
                lengthScale = 1.0;
                break;

            case INCH:
                lengthScale = 25.4;
                break;
        }
    }

    long statusUpdateTime = 0;
    private final CRCLStatusType status = new CRCLStatusType();
    volatile long moveDoneTime = 0;
    volatile boolean lastCheckAtPosition = false;
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

    public synchronized CRCLStatusType readCachedStatusFromRobot() throws PmException {

        if (System.currentTimeMillis() - lastUpdateStatusTime > 30) {
            CRCLStatusType status = readStatusFromRobot();
            if (null == jointStatuses || jointStatuses.getJointStatus().size() < 1 || !reportJointStatus) {
                status.setJointStatuses(null);
            } else {
                status.setJointStatuses(jointStatuses);
            }
            lastUpdateStatusTime = System.currentTimeMillis();
            return status;
        } else {
            return getStatus();
        }
    }

    boolean lastRobotIsConnected = true;

    public CRCLStatusType readStatusFromRobot() {
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
        if (null == robotService) {
            robotService = Executors.newSingleThreadExecutor(daemonThreadFactory);
        }
        robotService.submit(this::readStatusFromRobotInternal);
        return status;
    }

    public boolean isConnected() {
        return (null != robot && robotIsConnected);
    }

    public void setConnected(boolean connected) {
        if (connected != isConnected()) {
            if (connected) {
                connectRemoteRobot();
            } else {
                disconnectRemoteRobot();
            }
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
    public static BigInteger lastDoneMoveCommandID = null;

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

    private synchronized void readStatusFromRobotInternal() {
        try {
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
            synchronized (status) {
                if (status.getCommandStatus() == null) {
                    status.setCommandStatus(new CommandStatusType());
                    setCommandState(CommandStateEnumType.CRCL_WORKING);
                }
                if (null == status.getCommandStatus().getCommandID()) {
                    status.getCommandStatus().setCommandID(BigInteger.ONE);
                }
                if (null == status.getCommandStatus().getCommandState()) {
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
                        parallelGripperStatus.setSeparation(BigDecimal.valueOf(gripperSeperation));
                    }
                }
                status.getCommandStatus().setStatusID(BigInteger.ONE.add(status.getCommandStatus().getStatusID()));
                if (status.getCommandStatus().getCommandState() == CommandStateEnumType.CRCL_WORKING) {
                    if (prevCmd != null) {
                        if (prevCmd instanceof MoveToType) {
                            try {
                                MoveToType mtPrev = (MoveToType) prevCmd;
                                double dist = distTransFrom(mtPrev.getEndPosition());
                                double rotDist = distRotFrom(mtPrev.getEndPosition());
                                if ((dist < distanceTolerance
                                        && rotDist < distanceRotTolerance
                                        && System.currentTimeMillis() > expectedEndMoveToTime
                                        && posReg98.isAtCurPosition()) && (System.currentTimeMillis() - moveTime > 20)
                                        && !lastMotionProgramRunning()) {
                                    if (!lastCheckAtPosition) {
                                        moveDoneTime = System.currentTimeMillis();
                                    } else if ((System.currentTimeMillis() - moveDoneTime) > 20) {
                                        try {
                                            lastDoneMovePose = CRCLPosemath.copy(mtPrev.getEndPosition());
                                            lastDoneMoveCommandID = mtPrev.getCommandID();
                                            System.out.println("mtPrev.getCommandID() = " + mtPrev.getCommandID());
                                            System.out.println("mtPrev.getEndPosition().getPoint().getZ() = " + mtPrev.getEndPosition().getPoint().getZ());
                                            System.out.println("rotDist = " + rotDist);
                                            System.out.println("dist = " + dist);
                                            System.out.println("Done move = " + CRCLSocket.getUtilSocket().commandToString(prevCmd, false) + " status =" + CRCLSocket.getUtilSocket().statusToString(status, false));
                                            System.out.println("Move took" + (System.currentTimeMillis() - startMoveTime));
                                        } catch (CRCLException ex) {
                                            Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        setCommandState(CommandStateEnumType.CRCL_DONE);
                                        prevCmd = null;
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
                        } else if (prevCmd instanceof MoveThroughToType) {
                            MoveThroughToType mtt = (MoveThroughToType) prevCmd;
                            if (currentWaypointNumber >= mtt.getNumPositions().intValue()
                                    || currentWaypointNumber >= mtt.getWaypoint().size()) {
                                try {
                                    PoseType pose = mtt.getWaypoint().get(mtt.getWaypoint().size() - 1);
                                    double dist = distTransFrom(pose);
                                    double rotDist = distRotFrom(pose);
                                    if (dist < distanceTolerance
                                            && rotDist < distanceRotTolerance
                                            && groupPos.isAtCurPosition() && (System.currentTimeMillis() - moveTime > 10)) {
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
//                            System.out.println("(prevCmd instanceof InitCanonType) diff = " + diff);
//                            System.out.println("status.getCommandStatus().getCommandState() = " + status.getCommandStatus().getCommandState());
                            if (diff >= 0 && status.getCommandStatus().getCommandState() == CommandStateEnumType.CRCL_WORKING) {
//                                if(diff > 5) {
//                                    showError("dwell took:" + diff + " additional milliseconds over the expected "+((long)(((DwellType)prevCmd).getDwellTime().doubleValue()*1000.0)));
//                                }
                                lastServoReady = true;
//                                System.out.println("robotResetCount = " + robotResetCount);
                                boolean secondInitSafetyStatError = checkSafetyStatError();
//                                System.out.println("secondInitSafetyStatError = " + secondInitSafetyStatError);
                                if (secondInitSafetyStatError) {
                                    setCommandState(CommandStateEnumType.CRCL_ERROR);
                                } else if (robotResetCount < 3) {
                                    boolean secondInitCheckServoReady = checkServoReady();
                                    System.out.println("secondInitCheckServoReady = " + secondInitCheckServoReady);
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
                            if (posReg97.isAtCurPosition()) {
                                ActuateJointsType actJoints = (ActuateJointsType) prevCmd;
                                double maxDiff = 0;
                                for (ActuateJointType aj : actJoints.getActuateJoint()) {
                                    int num = aj.getJointNumber().intValue();
                                    assert (jointStatuses != null);
                                    for (JointStatusType jst : jointStatuses.getJointStatus()) {
                                        if (num == jst.getJointNumber().intValue()) {
                                            double diff = Math.abs(jst.getJointPosition().doubleValue() - aj.getJointPosition().doubleValue());
                                            if (diff > maxDiff) {
                                                maxDiff = diff;
                                            }
                                            break;
                                        }
                                    }
                                }
                                if (maxDiff < 0.1 && lastMaxJointDiff < 0.1) {
                                    setCommandState(CommandStateEnumType.CRCL_DONE);
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
                if (status.getCommandStatus().getCommandState() != CommandStateEnumType.CRCL_WORKING) {
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
                    status.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_ERROR);
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
                    js.setJointNumber(BigInteger.valueOf(i));
                    double cur_joint_pos = joint_pos.item(i);
                    double last_joint_pos = lastJointPosArray[i];
                    long last_joint_pos_time = lastJointPosTimeArray[i];
                    long cur_time = System.currentTimeMillis();
                    double joint_vel = 1000.0 * (cur_joint_pos - last_joint_pos) / (cur_time - last_joint_pos_time + 1);
                    lastJointPosArray[i] = cur_joint_pos;
                    lastJointPosTimeArray[i] = cur_time;
                    BigDecimal jointPosition = BigDecimal.valueOf(cur_joint_pos);
                    js.setJointPosition(jointPosition);
                    try {
                        if (null != cjrMap && cjrMap.size() > 0) {
                            js.setJointPosition(null);
                            js.setJointVelocity(null);
                            js.setJointTorqueOrForce(null);
                            ConfigureJointReportType cjrt = this.cjrMap.get(js.getJointNumber().intValue());
                            if (null != cjrt) {
                                if (cjrt.getJointNumber().compareTo(js.getJointNumber()) == 0) {
                                    if (cjrt.isReportPosition()) {
                                        js.setJointPosition(jointPosition);
                                    }
                                    if (cjrt.isReportVelocity()) {
                                        js.setJointVelocity(BigDecimal.valueOf(joint_vel));
                                    }
                                    if (cjrt.isReportTorqueOrForce()) {
                                        js.setJointTorqueOrForce(BigDecimal.ZERO);
                                    }
                                }
                            }
                            if (this.status.getCommandStatus().getCommandState() == CommandStateEnumType.CRCL_WORKING
                                    && prevCmd instanceof ConfigureJointReportsType) {
                                this.setCommandState(CommandStateEnumType.CRCL_DONE);
                            }
                            if (this.status.getCommandStatus().getCommandState() == CommandStateEnumType.CRCL_WORKING
                                    && prevCmd instanceof ConfigureStatusReportType) {
                                this.setCommandState(CommandStateEnumType.CRCL_DONE);
                            }
                        }
                    } catch (Throwable ex) {
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                    }
                    jointStatuses.getJointStatus().add(js);
                }
                if (null == prevCmd || !(prevCmd instanceof InitCanonType)
                        || status.getCommandStatus().getCommandState() != CommandStateEnumType.CRCL_WORKING) {
                    checkServoReady();
                }
            }
        } catch (PmException ex) {
            showError(ex.toString());
            Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private ExecutorService es;
    private ServerSocket ss;
    private Set<CRCLSocket> clients = new HashSet<>();

    public static void stop() {
        if (null != main) {
            main.stopInternal();
        }
        main = null;
        System.out.println("Thread.activeCount() = " + Thread.activeCount());
        for (StackTraceElement ste[] : Thread.getAllStackTraces().values()) {
            System.out.println("ste = " + Arrays.toString(ste));
        }
        Thread ta[] = new Thread[10 + Thread.activeCount()];
        Thread.enumerate(ta);
        for (Thread t : ta) {
            if (null != t && !t.equals(Thread.currentThread())) {
                System.out.println("t = " + t);
                System.out.println("t.isAlive() = " + t.isAlive());
                System.out.println("t.isDaemon() = " + t.isDaemon());
                System.out.println("t.isInteruppted() = " + t.isInterrupted());
                System.out.println("t.getStackTrace() = " + Arrays.toString(t.getStackTrace()));
                t.interrupt();
            }
        }
        System.exit(0);
    }

    public void stopCrclServer() {
        if (null != crclServerFuture) {
            crclServerFuture.cancel(true);
//            try {
//                crclServerFuture.get(100, TimeUnit.MILLISECONDS);
//            } catch (InterruptedException | ExecutionException | TimeoutException ex) {
//                Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
        if (null != clients) {
            for (CRCLSocket cs : clients) {
                try {
                    cs.close();
                } catch (IOException ex) {
                    Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            clients.clear();
        }
        if (null != ss) {
            try {
                ss.close();
            } catch (IOException ex) {
                Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            ss = null;
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
        if (null != es) {
            es.shutdownNow();
            try {
                es.awaitTermination(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException ex) {
                Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            es = null;
        }
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

        boolean initSafetyStatError = checkSafetyStatError();
//        System.out.println("initSafetyStatError = " + initSafetyStatError);
        if (initSafetyStatError) {
            setCommandState(CommandStateEnumType.CRCL_ERROR);
        } else {
            boolean initCheckServoReady = checkServoReady();
//            System.out.println("initCheckServoReady = " + initCheckServoReady);
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

    private void handleSetEndEffector(SetEndEffectorType seeCmd) {
        setCommandState(CommandStateEnumType.CRCL_DONE);
        if (seeCmd.getSetting().compareTo(BigDecimal.valueOf(0.5)) > 0) {
            open_gripper_prog.run(FREStepTypeConstants.frStepNone, 1, FREExecuteConstants.frExecuteFwd);
            setGripperSeperation(1.0);
        } else {
            close_gripper_prog.run(FREStepTypeConstants.frStepNone, 1, FREExecuteConstants.frExecuteFwd);
            setGripperSeperation(0.0);
        }
        settingsStatus.setEndEffectorSetting(seeCmd.getSetting());
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
                status.getCommandStatus().setCommandID(BigInteger.ONE);
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
                status.getCommandStatus().setCommandID(BigInteger.ONE);
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
            transSpeed = tsRel.getFraction().doubleValue() * 200.0;
            int val = ((TransSpeedRelativeType) ts).getFraction().multiply(BigDecimal.valueOf(maxRelativeSpeed)).intValue();
            overrideVar.value(Integer.valueOf(val));
            if (null != displayInterface) {
                displayInterface.getjSliderOverride().setValue(val);
            }
            setCommandState(CommandStateEnumType.CRCL_DONE);
            settingsStatus.setTransSpeedRelative(tsRel);
        } else if (ts instanceof TransSpeedAbsoluteType) {
            TransSpeedAbsoluteType tsAbs = (TransSpeedAbsoluteType) ts;
            transSpeed = tsAbs.getSetting().doubleValue() * lengthScale;
            regNumeric98.regFloat((float) transSpeed);
//            reg98Var.update();
            setCommandState(CommandStateEnumType.CRCL_DONE);
            settingsStatus.setTransSpeedAbsolute(tsAbs);
        }
    }

    private void handleSetRotSpeed(SetRotSpeedType stsCmd) {
        RotSpeedType rs = stsCmd.getRotSpeed();
        if (rs instanceof RotSpeedRelativeType) {
            RotSpeedRelativeType rsRel = (RotSpeedRelativeType) rs;
            int val = rsRel.getFraction().multiply(BigDecimal.valueOf(maxRelativeSpeed)).intValue();
            overrideVar.value(val);
            if (null != displayInterface) {
                displayInterface.getjSliderOverride().setValue(val);
            }
            setCommandState(CommandStateEnumType.CRCL_DONE);
            settingsStatus.setRotSpeedRelative(rsRel);
        } else if (rs instanceof RotSpeedAbsoluteType) {
            RotSpeedAbsoluteType rsAbs = (RotSpeedAbsoluteType) rs;
            rotSpeed = rsAbs.getSetting().doubleValue() * lengthScale;
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

    private void handleMoveTo(MoveToType moveCmd) throws PmException {
//        try {
//            System.out.println("Starting move = " + CRCLSocket.getUtilSocket().commandToString(moveCmd, false) + ", status=" + CRCLSocket.getUtilSocket().statusToString(status, false));
//        } catch (CRCLException ex) {
//            Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
//        }

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
        moveCmdEndPt.setX(BigDecimal.valueOf(endCart.x / lengthScale));
        moveCmdEndPt.setY(BigDecimal.valueOf(endCart.y / lengthScale));
        moveCmdEndPt.setZ(BigDecimal.valueOf(endCart.z / lengthScale));
        double cartMoveTime = cartDiff / transSpeed;
        double rotMoveTime = rotDiff / rotSpeed;
        if (rotMoveTime > cartMoveTime) {
            double timeNeeded = Math.max(rotMoveTime, cartMoveTime);
            int time_needed_ms = (int) (1000.0 * timeNeeded);
            regNumeric96.regLong(time_needed_ms);
            reg96Var.update();
            runMotionTpProgram(move_w_time_prog);
            expectedEndMoveToTime = System.currentTimeMillis() + time_needed_ms;
        } else {
            runMotionTpProgram(move_linear_prog);
        }
        startMoveTime = System.currentTimeMillis();
    }

    public Optional<List<Object[]>> getTaskList(boolean showAborted) {
        ITasks tasks = robot.tasks();
        final List<Object[]> taskList = new ArrayList<>();
        if (null != tasks) {
            for (Com4jObject c4jo : tasks) {
                ITask tsk = null;
                String tskProgName = null;
                FREProgramTypeConstants pType = null;
                FRETaskStatusConstants tskStatus = null;
                try {
                    tsk = c4jo.queryInterface(ITask.class);
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

                    try {
                        tskStatus = tsk.status();
                    } catch (Exception e) {
                    }
                } catch (ComException e) {
                    e.printStackTrace();
                    showError(e.toString());
                    continue;
                }
                if (!showAborted && tskStatus == FRETaskStatusConstants.frStatusAborted) {
                    continue;
                }
                if (null == tskProgName && null == pType && null == tskStatus) {
                    continue;
                }
                taskList.add(new Object[]{
                    tskProgName == null ? "" : tskProgName,
                    pType == null ? "" : pType.toString(),
                    tskStatus == null ? "" : tskStatus.toString()});
            }
            return Optional.of(taskList);
        }
        return Optional.empty();
    }

    long lastRunMotionTpTime = 0;
    ITPProgram lastRunMotionProgram = null;

    public synchronized void runMotionTpProgram(final ITPProgram program) {
        boolean program_started = false;
        int count = 0;
        long start = System.currentTimeMillis();
        CommandStateEnumType state = origState;
        while (lastMotionProgramRunning()) {
//            System.err.println("waiting for lastMotionProgramRunning");
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
                    taskList.forEach((Object[] objects) -> System.out.println(Arrays.toString(objects)));
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
                for (currentWaypointNumber = 0; currentWaypointNumber < moveCmd.getNumPositions().intValue() && currentWaypointNumber < moveCmd.getWaypoint().size(); currentWaypointNumber++) {
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
                            || !groupPos.isAtCurPosition()) {
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

    private void handleSetLengthUnits(SetLengthUnitsType slu) {
        this.setLengthUnit(slu.getUnitName());
        setCommandState(CommandStateEnumType.CRCL_DONE);
        settingsStatus.setLengthUnitName(slu.getUnitName());
    }

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
        dwellEndTime = System.currentTimeMillis() + ((long) (dwellCmd.getDwellTime().doubleValue() * 1000.0 + 1.0));
//        System.out.println("dwellEndTime = " + dwellEndTime);
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
            cjr.setJointNumber(BigInteger.valueOf(i));
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
            this.cjrMap.put(cjr.getJointNumber().intValue(),
                    cjr);
        }
        setCommandState(CommandStateEnumType.CRCL_WORKING);
    }

    private void runTPProgram(ITPProgram prog) {
        try {
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
                    if (tsk != null && tskProgName != null && tskProgName.equals(prog.name())) {
                        FRETaskStatusConstants tskStatus = tsk.status();
//                        System.out.println("tskStatus = " + tskStatus);
                        if (tskStatus == FRETaskStatusConstants.frStatusRun) {
                            System.out.println("aborting task with curProgram().name() = " + tskProgName);
                            tsk.abort(true, true);
                            long t0 = System.currentTimeMillis();
                            int cycles = 0;
                            while (!Thread.currentThread().isInterrupted()
                                    && (tskStatus == FRETaskStatusConstants.frStatusRun
                                    || tskStatus == FRETaskStatusConstants.frStatusAborting)) {
                                Thread.sleep(10);
                                tskStatus = tsk.status();
                                System.out.println("tskStatus = " + tskStatus);
                                cycles++;
                                System.out.println("cycles = " + cycles);
                            }
                            long t1 = System.currentTimeMillis();
                            boolean interrupted = Thread.currentThread().isInterrupted();
                            System.out.println("interrupted = " + interrupted);
                            if (interrupted) {
                                System.exit(1);
                            }
                            System.out.println("Abort took " + (t1 - t0) + " ms and " + cycles + " cycles.");
                            System.out.println("tskStatus = " + tskStatus);
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
                    didit = true;
                } catch (Exception e) {
                    tries++;
                    Thread.sleep(10);
                }
            }
            System.out.println("(System.currentTimeMillis()-runStartTime) = " + (System.currentTimeMillis() - runStartTime));
            System.out.println("tries = " + tries);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("e.getMessage() = " + e.getMessage());
            prog.run(FREStepTypeConstants.frStepNone, 1, FREExecuteConstants.frExecuteFwd);
        }
    }

    private void handleActuateJoints(ActuateJointsType ajCmd) throws PmException, InterruptedException {
        posReg98Updated = false;
        setCommandState(CommandStateEnumType.CRCL_WORKING);
        updatePosReg97();
        posReg97.refresh();
        final IJoint posReg97Joint = posReg97.formats(FRETypeCodeConstants.frJoint).queryInterface(IJoint.class);
        long max_time = 0;
        for (ActuateJointType aj : ajCmd.getActuateJoint()) {
            double val = aj.getJointPosition().doubleValue();
            short number = aj.getJointNumber().shortValue();
            if (val > this.upperJointLimits[number - 1]) {
                val = this.upperJointLimits[number - 1];
            }
            if (val < this.lowerJointLimits[number - 1]) {
                val = this.lowerJointLimits[number - 1];
            }
            float curVal = (float) posReg97Joint.item(number);
            double absDiff = (double) Math.abs(val - curVal);
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
        }
        if (max_time < 50) {
            max_time = 50;
        }
        regNumeric97.regLong((int) max_time);
        posReg97.update();
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

    private CRCLCommandType prevCmd = null;

    final CRCLSocket utilCrclSocket;

    public FanucCRCLMain() throws CRCLException {
        utilCrclSocket = new CRCLSocket();
        setDefaultJointReports();
        poseStatus.setPose(CRCLPosemath.identityPose());
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
                    if (null == commandStatus.getCommandID()) {
                        commandStatus.setCommandID(BigInteger.ONE);
                    }
                    if (null == commandStatus.getStatusID()) {
                        commandStatus.setStatusID(BigInteger.ONE);
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

    private void updatePerformance() {
        if (handleCommandCount > 0 && updateStatusCount > 0) {
            displayInterface.updatePerformanceString("Performance: Commands: " + handleCommandCount + " maxTime=" + maxHandleCommandTime + " (ms), avgTime=" + (totalHandleCommandTime / handleCommandCount) + "(ms)"
                    + " Status: " + updateStatusCount + " maxTime=" + maxUpdateStatusTime + "(ms), avgTime=" + (totalUpdateStatusTime / updateStatusCount) + "(ms)");
        }
    }

    private void handleClient(CRCLSocket cs) {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    CRCLCommandInstanceType cmdInstance = cs.readCommand(validate);
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
                } catch (SocketException | EOFException se) {
                    // probably just closing the connection.
                    break;
                } catch (ComException comEx) {
                    showComException(comEx);
                    disconnectRemoteRobot();
                    connectRemoteRobot();
                }
            }
        } catch (SocketException | EOFException se) {
            // probably just closing the connection.
        } catch (Exception ex) {
            Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
            showError(ex.getMessage());
        } finally {
            try {
                System.out.println("Closing connection with " + cs.getInetAddress() + ":" + cs.getPort());
                clients.remove(cs);
                cs.close();
                System.out.println("clients = " + clients);
            } catch (IOException ex) {
                Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    Future<?> crclServerFuture = null;

    public void startCrclServer() throws IOException {
        stopCrclServer();
        es = Executors.newWorkStealingPool();
        ss = new ServerSocket(localPort);
        crclServerFuture = es.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    CRCLSocket cs = new CRCLSocket(ss.accept());
                    clients.add(cs);
                    es.submit(() -> {
                        handleClient(cs);
                    });
                } catch (IOException ex) {
                    Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                }
            }
        });
    }

    private boolean robotIsConnected = false;

    public synchronized void handleCommand(CRCLCommandInstanceType cmdInstance) {
        CRCLCommandType cmd = cmdInstance.getCRCLCommand();
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
                    cst.setStatusID(BigInteger.ONE);
                    cst.setProgramFile(cmdInstance.getProgramFile());
                    cst.setProgramIndex(cmdInstance.getProgramIndex());
                    cst.setProgramLength(cmdInstance.getProgramLength());
                }
            }

            lastCheckAtPosition = false;
            if (null == robot || !robotIsConnected || null == groupPos) {
                showError(utilCrclSocket.commandToSimpleString(cmd, 18, 70) + " recieved when robot not connected or not initialized.");
                return;
            }
            if (status.getCommandStatus().getCommandState() != CommandStateEnumType.CRCL_ERROR) {
                status.getCommandStatus().setStateDescription("");
            }
            if (cmd instanceof InitCanonType) {
                handleInitCanon((InitCanonType) cmd);
            } else if (cmd instanceof StopMotionType) {
                handleStopMotion((StopMotionType) cmd);
            } else if (cmd instanceof EndCanonType) {
                handleEndCanon((EndCanonType) cmd);
            } else if (cmd instanceof MoveToType) {
                handleMoveTo((MoveToType) cmd);
            } else if (cmd instanceof MoveThroughToType) {
                handleMoveThroughTo((MoveThroughToType) cmd);
            } else if (cmd instanceof SetEndEffectorType) {
                handleSetEndEffector((SetEndEffectorType) cmd);
            } else if (cmd instanceof SetAngleUnitsType) {
                handleSetAngleUnits((SetAngleUnitsType) cmd);
            } else if (cmd instanceof SetForceUnitsType) {
                handleSetForceUnits((SetForceUnitsType) cmd);
            } else if (cmd instanceof SetEndEffectorType) {
                handleSetEndEffector((SetEndEffectorType) cmd);
            } else if (cmd instanceof SetTransSpeedType) {
                handleSetTransSpeed((SetTransSpeedType) cmd);
            } else if (cmd instanceof SetRotSpeedType) {
                handleSetRotSpeed((SetRotSpeedType) cmd);
            } else if (cmd instanceof ActuateJointsType) {
                handleActuateJoints((ActuateJointsType) cmd);
            } else if (cmd instanceof SetLengthUnitsType) {
                handleSetLengthUnits((SetLengthUnitsType) cmd);
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
        prevCmd = cmd;
    }

    private IRobot2 robot;
    private IIndGroupPosition groupPos;
    private ITPProgram close_gripper_prog;
    private ITPProgram open_gripper_prog;
    private ITPProgram move_linear_prog;
    private ITPProgram move_w_time_prog;
    private ITPProgram move_joint_prog;
    private IVar overrideVar = null;
    private IVar morSafetyStatVar = null;
    private IVar moveGroup1RobMoveVar = null;
    private IVar moveGroup1ServoReadyVar = null;

    private long isMovingLastCheckTime = 0;
    private boolean lastIsMoving = false;

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

    public void setNeighborhoodname(String neighborhoodname) {
        if (this.neighborhoodname != neighborhoodname) {
            this.neighborhoodname = neighborhoodname;
            if (null != displayInterface) {
                displayInterface.getjTextFieldRobotNeighborhoodPath().setText(remoteRobotHost);
            }
            this.disconnectRemoteRobot();
            this.connectRemoteRobot();
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
    public void setPreferRobotNeighborhood(boolean preferRobotNeighborhood) {
        if (this.preferRobotNeighborhood != preferRobotNeighborhood) {
            this.preferRobotNeighborhood = preferRobotNeighborhood;
            if (null != displayInterface) {
                displayInterface.setPreferRobotNeighborhood(preferRobotNeighborhood);
            }
            this.disconnectRemoteRobot();
            this.connectRemoteRobot();
        }
    }

    float lowerJointLimits[] = new float[6];
    float upperJointLimits[] = new float[6];

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
        try (PrintWriter pw = new PrintWriter(CART_LIMITS_FILE)) {
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
            lowerJointLimits[i] = Math.max(lowerJointLimits[i], min[i]);
            upperJointLimits[i] = Math.min(upperJointLimits[i], max[i]);
            JointLimitType jointLimit = new JointLimitType();
            jointLimit.setJointNumber(BigInteger.valueOf(i + 1));
            jointLimit.setJointMaxPosition(BigDecimal.valueOf(upperJointLimits[i]));
            jointLimit.setJointMinPosition(BigDecimal.valueOf(lowerJointLimits[i]));
            settingsStatus.getJointLimits().add(jointLimit);
        }
    }

    public void saveJointLimits(float[] min, float[] max) {
        try (PrintWriter pw = new PrintWriter(JOINT_LIMITS_FILE)) {
            for (int i = 0; i < max.length && i < min.length; i++) {
                pw.println("min[" + i + "]=" + min[i]);
                pw.println("max[" + i + "]=" + max[i]);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static final File CART_LIMITS_FILE = new File(System.getProperty("user.home"),
            ".fanucCRLCCartLimits.txt");
    public static final File JOINT_LIMITS_FILE = new File(System.getProperty("user.home"),
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
        try (BufferedReader br = new BufferedReader(new FileReader(CART_LIMITS_FILE))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                findString(line, "min.x=", t -> min.x = Double.valueOf(t));
                findString(line, "max.x=", t -> max.x = Double.valueOf(t));
                findString(line, "min.y=", t -> min.y = Double.valueOf(t));
                findString(line, "max.y=", t -> max.y = Double.valueOf(t));
                findString(line, "min.z=", t -> min.z = Double.valueOf(t));
                findString(line, "max.z=", t -> max.z = Double.valueOf(t));
            }
        } catch (IOException ex) {
            Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
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
        try (BufferedReader br = new BufferedReader(new FileReader(JOINT_LIMITS_FILE))) {
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
            return t;
        }
    };

    public void connectRemoteRobot() {
        if (null == robotService) {
            robotService = Executors.newSingleThreadExecutor(daemonThreadFactory);
        }
        robotService.submit(this::connectRemoteRobotInternal);
    }

    private synchronized void connectRemoteRobotInternal() {
        try {
            this.lastIsMoving = false;
            this.lastRobotIsConnected = true;
            this.last_safety_stat = 0;
            this.lastServoReady = true;
            if (preferRobotNeighborhood) {
                if (null == neighborhood) {
                    System.out.println("Calling createFRCRobotNeighborhood ...");
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
                System.out.println("Calling createFRCRobot ...");
                robot = com.github.wshackle.fanuc.robotserver.ClassFactory.createFRCRobot();
                System.out.println("createFRCRobot returned " + robot);
                setPreferRobotNeighborhood(false);
            }

            robotIsConnected = robot.isConnected();

            if (!robotIsConnected) {
                System.out.println("Connecting to " + remoteRobotHost + " ...");
                int tries = 0;
                robot.connectEx(remoteRobotHost, true, 1, 1);
                while (!robot.isConnected() && !Thread.currentThread().isInterrupted() && tries < 10) {
                    tries++;
                    System.out.println("Connecting to " + remoteRobotHost + " ... : tries = " + tries + "/10");
                    Thread.sleep(200);
                }
                robotIsConnected = robot.isConnected();
                System.out.println("robotIsConnected = " + robotIsConnected);
            }

            if (!robotIsConnected) {
                showError("Failed to connect to robot: " + remoteRobotHost);
                return;
            }

            IIndPosition iip = robot.createIndependentPosition(FREGroupBitMaskConstants.frGroup1BitMask);
            iip.record();
            groupPos = iip.group((short) 1);

            System.out.println("Getting list of programs ...");
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
                            System.out.println("Found open_gripper program.");
                            open_gripper_prog = program;

                        }
                        if (null != program && program.name().equalsIgnoreCase("MOVE_W_TIME")) {
                            System.out.println("Found MOVE_W_TIME program.");
                            move_w_time_prog = program;
                        }
                        if (null != program && program.name().equalsIgnoreCase("MOVE_LINEAR")) {
                            System.out.println("Found MOVE_LINEAR program.");
                            move_linear_prog = program;
                        }
                        if (null != program && program.name().equalsIgnoreCase("MOVE_JOINT")) {
                            System.out.println("Found MOVE_JOINT program.");
                            move_joint_prog = program;
                        }
                        if (null != program && program.name().equalsIgnoreCase("GRIPPER_CLOSE")) {
                            System.out.println("Found close_gripper program.");
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
            System.out.println("Calling robot.sysVariables() ...");
            IVars sysvars = robot.sysVariables();
            overrideVar = sysvars.item("$MCR.$GENOVERRIDE", null).queryInterface(IVar.class);
            if (null != overrideVar) {
                overrideVar.refresh();
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
            System.out.println("Connect to Remote Fanuc Robot complete.");
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
        return val + " : "
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
                + " 0 ";
    }

    private static FanucCRCLMain main = null;

    public static FanucCRCLMain getMain() {
        return main;
    }

    public static void main(String[] args) throws IOException, CRCLException {
        main = new FanucCRCLMain();
        String neighborhoodname = args.length > 0 ? args[0] : "AgilityLabLRMate200iD";
        String host = args.length > 1 ? args[1] : "129.6.78.111";
        int port = args.length > 2 ? Integer.valueOf(args[2]) : CRCLSocket.DEFAULT_PORT;
        boolean prefRNN = (args.length > 3) ? Boolean.valueOf(args[3]) : false;
        main.startDisplayInterface();
        main.start(prefRNN, neighborhoodname, host, port);
//        System.out.println("Press enter \"stop\" to quit");
//        Scanner in = new Scanner(System.in);
//        while (!in.nextLine().equals("stop")) {
//            System.out.println("Enter \"stop\" to quit");
//        }
//        main.stop();
//        main = null;
    }
}
