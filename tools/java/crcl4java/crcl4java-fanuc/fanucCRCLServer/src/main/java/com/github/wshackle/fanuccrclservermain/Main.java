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
import com.github.wshackle.fanuc.robotserver.IVar;
import com.github.wshackle.fanuc.robotserver.IVars;
import com.github.wshackle.fanuc.robotserver.IXyzWpr;
import com.siemens.ct.exi.exceptions.EXIException;
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
import crcl.base.DwellType;
import crcl.base.EndCanonType;
import crcl.base.GetStatusType;
import crcl.base.InitCanonType;
import crcl.base.JointDetailsType;
import crcl.base.JointSpeedAccelType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.LengthUnitEnumType;
import crcl.base.MoveThroughToType;
import crcl.base.MoveToType;
import crcl.base.PointType;
import crcl.base.PoseToleranceType;
import crcl.base.PoseType;
import crcl.base.RotSpeedAbsoluteType;
import crcl.base.RotSpeedRelativeType;
import crcl.base.RotSpeedType;
import crcl.base.SetEndEffectorType;
import crcl.base.SetEndPoseToleranceType;
import crcl.base.SetLengthUnitsType;
import crcl.base.SetRotSpeedType;
import crcl.base.SetTransSpeedType;
import crcl.base.StopMotionType;
import crcl.base.TransSpeedAbsoluteType;
import crcl.base.TransSpeedRelativeType;
import crcl.base.TransSpeedType;
import crcl.utils.CRCLPosemath;
import crcl.utils.CRCLSocket;
import java.io.IOException;
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
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSlider;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;
import rcs.posemath.PmCartesian;
import rcs.posemath.PmException;
import rcs.posemath.PmRotationVector;
import rcs.posemath.PmRpy;
import rcs.posemath.Posemath;

/**
 *
 * @author shackle
 */
public class Main {

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
            if (null != jframe) {
                jframe.getjTextFieldHostName().setText(remoteRobotHost);
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

    public IRobot2 getRobot() {
        return robot;
    }

    private float xMax;
    private float xMin;
    private float yMax;
    private float yMin;
    private float zMax;
    private float zMin;
    private float border1 = 120;

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
            changed = true;
        } else if (cart.x < xMinEffective) {
            posXyzWpr.x(xMinEffective);
            changed = true;
        }
        if (cart.y > yMaxEffective) {
            posXyzWpr.y(yMaxEffective);
            changed = true;
        } else if (cart.y < yMinEffective) {
            posXyzWpr.y(yMinEffective);
            changed = true;
        }
        if (cart.z > zMaxEffective) {
            posXyzWpr.x(zMaxEffective);
            changed = true;
        } else if (cart.z < zMinEffective) {
            posXyzWpr.z(zMinEffective);
            changed = true;
        }

        if (changed) {
            PmCartesian newcart = new PmCartesian(posXyzWpr.x(), posXyzWpr.y(), posXyzWpr.z());
            System.out.println("newcart = " + newcart);
            System.out.println("cart = " + cart);
        }
        pos.update();
    }

    public void startJFrame() {
        java.awt.EventQueue.invokeLater(() -> {
            jframe = new FanucCRCLServerJFrame();
            JSlider sliderOv = jframe.getjSliderOverride();
            sliderOv.addChangeListener(e -> {
                IVar var = Main.this.getOverideVar();
                if (null != var) {
                    var.value(sliderOv.getValue());
                } else {
                    showError("Can NOT change override since robot is not initialized.");
                }
            });
            JSlider sliderMaxOv = jframe.getjSliderMaxOverride();
            sliderMaxOv.setValue(100);
            sliderMaxOv.addChangeListener(e -> {
                IVar var = Main.this.getOverideVar();
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
            jframe.setRobot(robot);
            jframe.setPrograms(tpPrograms);
            jframe.getjMenuItemReconnectRobot().addActionListener(e -> {
                disconnectRemoteRobot();
                connectRemoteRobot();
            });
            jframe.getjMenuItemResetAlarms().addActionListener(e -> {
                IRobot2 robot = Main.this.getRobot();
                if (null != robot) {
                    robot.alarms().reset();
                    robot.tasks().abortAll(true);
                } else {
                    showError("Can NOT reset alarms since robot is not initialized.");
                }
            });
            jframe.getjRadioButtonUseDirectIP().setSelected(!preferRobotNeighborhood);
            jframe.getjRadioButtonUseRobotNeighborhood().setSelected(preferRobotNeighborhood);
            jframe.getjTextFieldHostName().setText(remoteRobotHost);
            jframe.getjTextFieldRobotNeighborhoodPath().setText(neighborhoodname);
            jframe.getjRadioButtonUseDirectIP().addActionListener(e -> {
                Main.this.setPreferRobotNeighborhood(jframe.getjRadioButtonUseRobotNeighborhood().isSelected());
            });
            jframe.getjRadioButtonUseRobotNeighborhood().addActionListener(e -> {
                Main.this.setPreferRobotNeighborhood(jframe.getjRadioButtonUseRobotNeighborhood().isSelected());
            });
            jframe.getjTextFieldHostName().addActionListener(e -> {
                Main.this.setRemoteRobotHost(jframe.getjTextFieldHostName().getText());
            });
            jframe.getjTextFieldRobotNeighborhoodPath().addActionListener(e -> {
                Main.this.setNeighborhoodname(jframe.getjTextFieldRobotNeighborhoodPath().getText());
            });
            jframe.getjTextFieldLimitSafetyBumper().setText("" + border1);
            jframe.getjTextFieldLimitSafetyBumper().addActionListener(e -> {
                Main.this.border1 = Float.valueOf(jframe.getjTextFieldLimitSafetyBumper().getText());
            });
            jframe.setVisible(true);
        });
    }

    public void start(boolean preferRobotNeighborhood, String neighborhoodname, String remoteRobotHost, int localPort) {
        try {
            this.preferRobotNeighborhood = preferRobotNeighborhood;
            this.neighborhoodname = neighborhoodname;
            this.remoteRobotHost = remoteRobotHost;
            this.localPort = localPort;
            startJFrame();
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
    private CRCLStatusType status = new CRCLStatusType();
    volatile long moveDoneTime = 0;
    volatile boolean lastCheckAtPosition = false;
    private final double lastJointPosArray[] = new double[10];
    private final long lastJointPosTimeArray[] = new long[10];

    public CRCLStatusType getStatus() {
        return status;
    }
    double lastMaxJointDiff = Double.MAX_VALUE;

    public CRCLStatusType readStatusFromRobot() throws PmException {
        if (status.getCommandStatus() == null) {
            status.setCommandStatus(new CommandStatusType());
            setCommandState(CommandStateEnumType.CRCL_WORKING);
        }
        if (null == status.getCommandStatus().getCommandID()) {
            status.getCommandStatus().setCommandID(BigInteger.ONE);
        }
        if (null == status.getCommandStatus().getStatusID()) {
            status.getCommandStatus().setStatusID(BigInteger.ONE);
        }
        status.getCommandStatus().setStatusID(BigInteger.ONE.add(status.getCommandStatus().getStatusID()));
        CRCLPosemath.initPose(status);
        if (status.getCommandStatus().getCommandState() == CommandStateEnumType.CRCL_WORKING) {
            if (prevCmd != null) {
                if (prevCmd instanceof MoveToType) {
                    MoveToType mtPrev = (MoveToType) prevCmd;
                    double dist = distTransFrom(mtPrev.getEndPosition());
                    double rotDist = distRotFrom(mtPrev.getEndPosition());
                    if ((dist < distanceTolerance
                            && rotDist < distanceRotTolerance
                            && posReg98.isAtCurPosition()) && (System.currentTimeMillis() - moveTime > 20)) {
                        if (!lastCheckAtPosition) {
                            moveDoneTime = System.currentTimeMillis();
                        } else if ((System.currentTimeMillis() - moveDoneTime) > 20) {
                            setCommandState(CommandStateEnumType.CRCL_DONE);
                        }
                        lastCheckAtPosition = true;
                    } else {
                        setCommandState(CommandStateEnumType.CRCL_WORKING);
                        lastCheckAtPosition = false;
                    }
                } else if (prevCmd instanceof MoveThroughToType) {
                    MoveThroughToType mtt = (MoveThroughToType) prevCmd;
                    if (currentWaypointNumber >= mtt.getNumPositions().intValue()
                            || currentWaypointNumber >= mtt.getWaypoint().size()) {
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
                    }
                } else if (prevCmd instanceof DwellType) {
                    long diff = System.currentTimeMillis() - dwellEndTime;
                    if (diff > 0) {
                        setCommandState(CommandStateEnumType.CRCL_DONE);
                    }
                } else if (prevCmd instanceof ActuateJointsType) {
                    posReg97.update();
                    if (posReg97.isAtCurPosition()) {
                        ActuateJointsType actJoints = (ActuateJointsType) prevCmd;
                        double maxDiff = 0;
                        for (ActuateJointType aj : actJoints.getActuateJoint()) {
                            int num = aj.getJointNumber().intValue();
                            for (JointStatusType jst : status.getJointStatuses().getJointStatus()) {
                                if (num == jst.getJointNumber().intValue()) {
                                    double diff = Math.abs(jst.getJointPosition().doubleValue() - aj.getJointPosition().doubleValue());
                                    if (diff > maxDiff) {
                                        maxDiff = diff;
                                    }
                                    break;
                                }
                            }
                        }
                        System.out.println("maxDiff = " + maxDiff);
                        if (maxDiff < 0.1 && lastMaxJointDiff < 0.1) {
                            setCommandState(CommandStateEnumType.CRCL_DONE);
                        }
                        lastMaxJointDiff = maxDiff;
                    }

                }
            }
        } else {
            lastCheckAtPosition = false;
        }

        ICurPosition icp = robot.curPosition();
        if(null == icp) {
            showError("robot.curPosition() returned null");
            status.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_ERROR);
            return status;
        }
        ICurGroupPosition icgp = icp.group((short) 1, FRECurPositionConstants.frWorldDisplayType);
        Com4jObject com4jobj_pos = icgp.formats(FRETypeCodeConstants.frXyzWpr);
        IXyzWpr pos = com4jobj_pos.queryInterface(IXyzWpr.class);
        PmCartesian cart = new PmCartesian(pos.x() / lengthScale, pos.y() / lengthScale, pos.z() / lengthScale);
        PmRpy rpy = new PmRpy(Math.toRadians(pos.w()), Math.toRadians(pos.p()), Math.toRadians(pos.r()));
        CRCLPosemath.setPose(status, CRCLPosemath.toPoseType(cart, rcs.posemath.Posemath.toRot(rpy), CRCLPosemath.getPose(status)));
        Com4jObject com4jobj_joint_pos = icgp.formats(FRETypeCodeConstants.frJoint);
        IJoint joint_pos = com4jobj_joint_pos.queryInterface(IJoint.class);
        if (null == status.getJointStatuses()) {
            status.setJointStatuses(new JointStatusesType());
        }
        status.getJointStatuses().getJointStatus().clear();
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
                }
            } catch (Throwable ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
            status.getJointStatuses().getJointStatus().add(js);
        }
        return status;
    }

    private ExecutorService es;
    private ServerSocket ss;
    private Set<CRCLSocket> clients = new HashSet<>();

    public void stop() {

        if (null != moveThread) {
            moveThread.interrupt();
            try {
                moveThread.join(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            moveThread = null;
        }
        for (CRCLSocket cs : clients) {
            try {
                cs.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        clients.clear();
        if (null != ss) {
            try {
                ss.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            ss = null;
        }
        if (null != es) {
            es.shutdownNow();
            es = null;
        }
        disconnectRemoteRobot();
        if (null != neighborhood) {
            neighborhood.dispose();
            neighborhood = null;
        }
    }

    public void disconnectRemoteRobot() {
        try {
//            if (null != robot_ec) {
//                robot_ec.close();
//                robot_ec = null;
//            }
            if (null != this.robotMonitorThread) {
                robotMonitorThread.interrupt();
                robotMonitorThread.join(100);
                robotMonitorThread = null;
            }
            if (null != robot) {
                robot.dispose();
                robot = null;
            }
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void handleInitCanon(InitCanonType initCmd) {
        robot.alarms().reset();
        robot.tasks().abortAll(true);
        setCommandState(CommandStateEnumType.CRCL_DONE);
//        checkAlarms();
    }

    private void handleStopMotion(StopMotionType stopCmd) {
        if (null != moveThread) {
            moveThread.interrupt();
            try {
                moveThread.join(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            moveThread = null;
        }
        robot.tasks().abortAll(true);
        setCommandState(CommandStateEnumType.CRCL_DONE);
    }

    private void handleEndCanon(EndCanonType initCmd) {
        setCommandState(CommandStateEnumType.CRCL_DONE);
    }

    private void handleSetEndEffector(SetEndEffectorType seeCmd) {
        setCommandState(CommandStateEnumType.CRCL_DONE);
        if (seeCmd.getSetting().compareTo(BigDecimal.valueOf(0.5)) > 0) {
            open_gripper_prog.run(FREStepTypeConstants.frStepNone, 1, FREExecuteConstants.frExecuteFwd);
        } else {
            close_gripper_prog.run(FREStepTypeConstants.frStepNone, 1, FREExecuteConstants.frExecuteFwd);
        }
    }

    private void showError(String error) {
        System.err.println(error);
        if (null != status) {
            if (null == status.getCommandStatus()) {
                status.setCommandStatus(new CommandStatusType());
                status.getCommandStatus().setCommandID(BigInteger.ONE);
            }
            status.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_ERROR);
        }
        if (null != jframe) {
            jframe.getjTextAreaErrors().append(error + "\n");
        }
        if (null != robot) {

        }
    }

    private void showInfo(String info) {
        // System.out.println(info);
        if (null != jframe) {
            jframe.getjTextAreaErrors().append(info + "\n");
        }
    }

    double maxRelativeSpeed = 100.0;

    private void handleSetTransSpeed(SetTransSpeedType stsCmd) {
        TransSpeedType ts = stsCmd.getTransSpeed();
        if (ts instanceof TransSpeedRelativeType) {
            transSpeed = ((TransSpeedRelativeType) ts).getFraction().doubleValue() * 200.0;
            int val = ((TransSpeedRelativeType) ts).getFraction().multiply(BigDecimal.valueOf(maxRelativeSpeed)).intValue();
            overrideVar.value(Integer.valueOf(val));
            if (null != jframe) {
                jframe.getjSliderOverride().setValue(val);
            }
            setCommandState(CommandStateEnumType.CRCL_DONE);
        } else if (ts instanceof TransSpeedAbsoluteType) {
            TransSpeedAbsoluteType tsAbs = (TransSpeedAbsoluteType) ts;
            transSpeed = tsAbs.getSetting().doubleValue() * lengthScale;
            regNumeric98.regFloat((float) transSpeed);
//            reg98Var.update();
            setCommandState(CommandStateEnumType.CRCL_DONE);
        }
    }

    private void handleSetRotSpeed(SetRotSpeedType stsCmd) {
        RotSpeedType ts = stsCmd.getRotSpeed();
        if (ts instanceof RotSpeedRelativeType) {
            int val = ((RotSpeedRelativeType) ts).getFraction().multiply(BigDecimal.valueOf(maxRelativeSpeed)).intValue();
            overrideVar.value(Integer.valueOf(val));
            if (null != jframe) {
                jframe.getjSliderOverride().setValue(val);
            }
            setCommandState(CommandStateEnumType.CRCL_DONE);
        } else if (ts instanceof RotSpeedAbsoluteType) {
            RotSpeedAbsoluteType tsAbs = (RotSpeedAbsoluteType) ts;
            rotSpeed = tsAbs.getSetting().doubleValue() * lengthScale;
            setCommandState(CommandStateEnumType.CRCL_DONE);
        }
    }

    private Set<String> getProgramNames() {
        IPrograms progs = robot.programs();
        Set<String> prognames = new TreeSet<String>();
        for (Com4jObject c4jo_prog : progs) {
            IProgram prog = c4jo_prog.queryInterface(IProgram.class);
            prognames.add(prog.name());
        }
        //// System.out.println("prognames = " + prognames);
        return prognames;
    }

    Thread moveThread = null;
    volatile private int moveCount = 0;
    volatile long moveTime = 0;
    private Set<String> origProgNames;
    private double transSpeed = 200; // 200 mm/s
    private double rotSpeed = 10; // 10 deg/s

    private boolean posReg98Updated = false;

    private void updatePosReg98() {
        if (!posReg98Updated && null != posReg98) {
            posReg98.record();
            posReg98.refresh();
            ICurGroupPosition curPos = robot.curPosition().group((short) 1, FRECurPositionConstants.frWorldDisplayType);
            IXyzWpr curXyzWpr = curPos.formats(FRETypeCodeConstants.frXyzWpr).queryInterface(IXyzWpr.class);
            IConfig curConf = curXyzWpr.config();
            System.out.println("curConf = " + curConf);
            System.out.println("curConf.text() = " + curConf.text());
            IXyzWpr posReg98XyzWpr = posReg98.formats(FRETypeCodeConstants.frXyzWpr).queryInterface(IXyzWpr.class);
            IConfig posReg98XyzWprConf = posReg98XyzWpr.config();
            System.out.println("posReg98XyzWprConf = " + posReg98XyzWprConf);
            System.out.println("posReg98XyzWprConf.text() = " + posReg98XyzWprConf.text());
            posReg98XyzWprConf.flip(curConf.flip());
            posReg98XyzWprConf.front(curConf.front());
            posReg98XyzWprConf.left(curConf.left());
            posReg98XyzWprConf.up(curConf.up());
            posReg98XyzWprConf.turnNum((short) 1, curConf.turnNum((short) 1));
            System.out.println("posReg98XyzWprConf = " + posReg98XyzWprConf);
            System.out.println("posReg98XyzWprConf.text() = " + posReg98XyzWprConf.text());
            posReg98.update();
            posReg98Updated = true;
        }
    }

    private boolean posReg97Updated = false;

    private void updatePosReg97() {
        if (true) {
            posReg97.refresh();
            ICurGroupPosition curPos = robot.curPosition().group((short) 1, FRECurPositionConstants.frWorldDisplayType);
            IXyzWpr curXyzWpr = curPos.formats(FRETypeCodeConstants.frXyzWpr).queryInterface(IXyzWpr.class);
            IConfig curConf = curXyzWpr.config();
            System.out.println("curConf = " + curConf);
            System.out.println("curConf.text() = " + curConf.text());
            IXyzWpr posReg97XyzWpr = posReg97.formats(FRETypeCodeConstants.frXyzWpr).queryInterface(IXyzWpr.class);
            IConfig posReg97XyzWprConf = posReg97XyzWpr.config();
            System.out.println("posReg97XyzWprConf = " + posReg97XyzWprConf);
            System.out.println("posReg97XyzWprConf.text() = " + posReg97XyzWprConf.text());
            posReg97XyzWprConf.flip(curConf.flip());
            posReg97XyzWprConf.front(curConf.front());
            posReg97XyzWprConf.left(curConf.left());
            posReg97XyzWprConf.up(curConf.up());
            posReg97XyzWprConf.turnNum((short) 1, curConf.turnNum((short) 1));
            System.out.println("posReg97XyzWprConf = " + posReg97XyzWprConf);
            System.out.println("posReg97XyzWprConf.text() = " + posReg97XyzWprConf.text());
            posReg97.update();
            posReg97.record();
            posReg97XyzWpr = posReg97.formats(FRETypeCodeConstants.frXyzWpr).queryInterface(IXyzWpr.class);
            posReg97XyzWprConf = posReg97XyzWpr.config();
            System.out.println("posReg97XyzWprConf = " + posReg97XyzWprConf);
            System.out.println("posReg97XyzWprConf.text() = " + posReg97XyzWprConf.text());
            posReg97XyzWprConf.flip(curConf.flip());
            posReg97XyzWprConf.front(curConf.front());
            posReg97XyzWprConf.left(curConf.left());
            posReg97XyzWprConf.up(curConf.up());
            posReg97XyzWprConf.turnNum((short) 1, curConf.turnNum((short) 1));
            System.out.println("posReg97XyzWprConf = " + posReg97XyzWprConf);
            System.out.println("posReg97XyzWprConf.text() = " + posReg97XyzWprConf.text());
            ICurPosition icp = robot.curPosition();
            ICurGroupPosition icgp = icp.group((short) 1, FRECurPositionConstants.frWorldDisplayType);
            Com4jObject com4jobj_pos = icgp.formats(FRETypeCodeConstants.frXyzWpr);
            Com4jObject com4jobj_joint_pos = icgp.formats(FRETypeCodeConstants.frJoint);
            IJoint joint_pos = com4jobj_joint_pos.queryInterface(IJoint.class);
            final IJoint posReg97Joint = posReg97.formats(FRETypeCodeConstants.frJoint).queryInterface(IJoint.class);
            for (short i = 1; i <= joint_pos.count(); i++) {
                System.out.println("i = " + i);
                double cur_joint_pos = joint_pos.item(i);
                System.out.println("cur_joint_pos = " + cur_joint_pos);
                double reg97_joint_pos = posReg97Joint.item(i);
                System.out.println("reg97_joint_pos = " + reg97_joint_pos);
                posReg97Joint.item(i, cur_joint_pos);
            }
            posReg97.update();
            posReg97Updated = true;
        }
    }

    private void handleMoveTo(MoveToType moveCmd) throws PmException {
        // System.out.println("groupPos.isAtCurPosition() = " + groupPos.isAtCurPosition());
//        groupPos.refresh();
        posReg97Updated = false;
        setCommandState(CommandStateEnumType.CRCL_WORKING);
        PointType moveCmdEndPt = moveCmd.getEndPosition().getPoint();
        PmCartesian cart = CRCLPosemath.pointToPmCartesian(moveCmdEndPt);
        PmCartesian endCart = new PmCartesian(cart.x * lengthScale, cart.y * lengthScale, cart.z * lengthScale);
        PmRpy rpy = CRCLPosemath.toPmRpy(moveCmd.getEndPosition());
        updatePosReg98();
        posReg98.refresh();
        IXyzWpr posReg98XyzWpr = posReg98.formats(FRETypeCodeConstants.frXyzWpr).queryInterface(IXyzWpr.class);
//        System.out.println("posReg98XyzWpr = " + posReg98XyzWpr);
        posReg98XyzWpr.setAll(endCart.x, endCart.y, endCart.z, Math.toDegrees(rpy.r), Math.toDegrees(rpy.p), Math.toDegrees(rpy.y));
        limitAndUpdatePos(posReg98);
        posReg98XyzWpr = posReg98.formats(FRETypeCodeConstants.frXyzWpr).queryInterface(IXyzWpr.class);
        endCart.x = posReg98XyzWpr.x();
        endCart.y = posReg98XyzWpr.y();
        endCart.z = posReg98XyzWpr.z();
        double cartDiff = distTransFrom(moveCmd.getEndPosition());
        System.out.println("cartDiff = " + cartDiff);
        double rotDiff = distRotFrom(moveCmd.getEndPosition());
        System.out.println("rotDiff = " + rotDiff);
        moveCmdEndPt.setX(BigDecimal.valueOf(endCart.x / lengthScale));
        moveCmdEndPt.setY(BigDecimal.valueOf(endCart.y / lengthScale));
        moveCmdEndPt.setZ(BigDecimal.valueOf(endCart.z / lengthScale));
        double cartMoveTime = cartDiff / transSpeed;
        System.out.println("cartMoveTime = " + cartMoveTime);
        double rotMoveTime = rotDiff / rotSpeed;
        System.out.println("rotMoveTime = " + rotMoveTime);
        if (rotMoveTime > cartMoveTime) {
            double timeNeeded = Math.max(rotMoveTime, cartMoveTime);
            int time_needed_ms = (int) (1000.0 * timeNeeded);
            System.out.println("time_needed_ms = " + time_needed_ms);
            regNumeric96.regLong(time_needed_ms);
            reg96Var.update();
//        System.out.println("move_linear_prog = " + move_linear_prog);
            move_w_time_prog.run(FREStepTypeConstants.frStepNone, 1, FREExecuteConstants.frExecuteFwd);
        } else {
            move_linear_prog.run(FREStepTypeConstants.frStepNone, 1, FREExecuteConstants.frExecuteFwd);
        }
//        if (moveThread != null) {
//            moveThread.interrupt();
//            try {
//                moveThread.join(200);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            moveThread = null;
//        }
//        final ICurPosition icp = robot.curPosition();
////        // System.out.println("icp = " + icp);
//        if (null == icp) {
//            showError("Couldn't get Robot Current position.");
//            return;
//        }
//
//        final ICurGroupPosition icgp = icp.group((short) 1, FRECurPositionConstants.frWorldDisplayType);
//        if (null == icgp) {
//            showError("Couldn't get Robot Current position.");
//            return;
//        }
//        icgp.record();
////        // System.out.println("icgp = " + icgp);
//        Com4jObject com4jobj_pos = icgp.formats(FRETypeCodeConstants.frXyzWpr);
//        if (null == com4jobj_pos) {
//            showError("Couldn't get Robot Current position.");
//            return;
//        }
//        IXyzWpr pos = com4jobj_pos.queryInterface(IXyzWpr.class);
//        if (null == pos) {
//            showError("Couldn't get Robot Current position.");
//            return;
//        }
//        // System.out.println("pos = " + pos);
//        // System.out.println("x: " + pos.x() + "   y:" + pos.y() + "    z:" + pos.z());
//        // System.out.println("w: " + pos.w() + "   y:" + pos.p() + "    r:" + pos.r());
//        // System.out.println("isgp = " + groupPos);
//        Com4jObject com4jobj_sys_pos = groupPos.formats(FRETypeCodeConstants.frXyzWpr);
//        final IXyzWpr sys_pos = com4jobj_sys_pos.queryInterface(IXyzWpr.class);
//        // System.out.println("sys_pos = " + sys_pos);
//        // System.out.println("x: " + sys_pos.x() + "   y:" + sys_pos.y() + "    z:" + sys_pos.z());
//        // System.out.println("w: " + sys_pos.w() + "   y:" + sys_pos.p() + "    r:" + sys_pos.r());
//
////        Com4jObject com4jobj_joint_sys_pos = isgp.formats(FRETypeCodeConstants.frJoint);
////        IJoint sys_joint_pos = com4jobj_joint_sys_pos.queryInterface(IJoint.class);
////        // System.out.println("sys_joint_pos = " + sys_joint_pos);
////        for (short i = 1; i <= sys_joint_pos.count(); i++) {
////            try {
////                // System.out.println("sys_joint_pos.item(" + i + ") = " + sys_joint_pos.item(i));
////            } catch (Exception e) {
////                System.err.println("i= " + i);
////                e.printStackTrace();
////            }
////        }
//        PmCartesian cart = CRCLPosemath.pointToPmCartesian(moveCmd.getEndPosition().getPoint());
//        PmRpy rpy = CRCLPosemath.toPmRpy(moveCmd.getEndPosition());
//        // System.out.println("Refreshing groupPos");
//        long t0 = System.currentTimeMillis();
////        groupPos.refresh();
//        long t1 = System.currentTimeMillis();
//        // System.out.println("refresh groupPose took = " + (t1 - t0) + "millis");
//
//        PmCartesian startCart = new PmCartesian(pos.x(), pos.y(), pos.z());
//        PmCartesian endCart = new PmCartesian(cart.x * lengthScale, cart.y * lengthScale, cart.z * lengthScale);
//        double dist = startCart.distFrom(endCart);
//        PmCartesian diff = endCart.subtract(startCart);
//        final double SEGMENT_DIST = transSpeed * MOVE_INTERVAL_MILLIS / 1000.0;
//
//        final int num_segs = (int) (dist / SEGMENT_DIST);
//
////        sys_pos.setAll(cart.x * lengthScale, cart.y * lengthScale, cart.z * lengthScale, Math.toDegrees(rpy.r), Math.toDegrees(rpy.p), Math.toDegrees(rpy.y));
//        // System.out.println("updating groupPos");
//        long t2 = System.currentTimeMillis();
//        groupPos.update();
//        long t3 = System.currentTimeMillis();
//        // System.out.println("updating groupPos took " + (t3 - t2) + " ms");
//        // System.out.println("sys_pos = " + sys_pos);
//        // System.out.println("x: " + sys_pos.x() + "   y:" + sys_pos.y() + "    z:" + sys_pos.z());
//        // System.out.println("w: " + sys_pos.w() + "   y:" + sys_pos.p() + "    r:" + sys_pos.r());
//
//        // System.out.println("cart = " + cart);
//        // System.out.println("rpy = " + rpy);
////        groupPos.update();
////        groupPos.moveto();
//        moveCount++;
//        moveThread = new Thread(() -> {
//            try {
//                // System.out.println("Starting move " + moveCount);
//                icgp.record();
//                PmCartesian lastCart = new PmCartesian(pos.x(), pos.y(), pos.z());
//                long t4 = System.currentTimeMillis();
//                long last_time = t4;
//                for (int i = 0; i < num_segs; i++) {
//                    System.out.println("i = " + i);
//                    icgp.record();
//                    double percent = ((double) (i + 1)) / num_segs;
//                    System.out.println("percent = " + percent);
//                    PmCartesian interPoint = startCart.add(diff.multiply(percent));
//                    System.out.println("interPoint = " + interPoint);
//                    sys_pos.setAll(interPoint.x, interPoint.y, interPoint.z, Math.toDegrees(rpy.r), Math.toDegrees(rpy.p), Math.toDegrees(rpy.y));
//                    moveToGroupPos();
//                    PmCartesian curCart = new PmCartesian(pos.x(), pos.y(), pos.z());
//                    while (curCart.distFrom(interPoint) > 10) {
//                        Thread.sleep(20);
//                        icgp.record();
//                        curCart = new PmCartesian(pos.x(), pos.y(), pos.z());
//                    }
//                    long time_left = (long) (MOVE_INTERVAL_MILLIS - (System.currentTimeMillis() - last_time));
//                    if (time_left > 0) {
//                        Thread.sleep(time_left);
//                    }
//                    double distseg = curCart.distFrom(lastCart);
//                    System.out.println("distseg = " + distseg);
//                    long curtime = System.currentTimeMillis();
//                    long difftime = curtime - last_time;
//                    System.out.println("difftime = " + difftime);
//                    if (difftime > 0) {
//                        double speed = distseg / difftime;
//                        System.out.println("speed = " + speed);
//                    }
//                    lastCart = curCart;
//                    last_time = curtime;
//                }
//                sys_pos.setAll(endCart.x, endCart.y, endCart.z, Math.toDegrees(rpy.r), Math.toDegrees(rpy.p), Math.toDegrees(rpy.y));
//                moveToGroupPos();
//                long t5 = System.currentTimeMillis();
//                moveTime = t5;
//                // System.out.println("Move  took = " + (t4 - t5) + " ms");
////                Set<String> curPrognames = this.getProgramNames();
////                curPrognames.removeAll(origProgNames);
//                // System.out.println("curPrognames = " + curPrognames);
//            } catch (ComException e) {
//                e.printStackTrace();
//                System.err.println("Time since moveTime = " + (System.currentTimeMillis() - moveTime));
//                System.err.println("Time since moveDoneTime = " + (System.currentTimeMillis() - moveDoneTime));
////                Set<String> curPrognames = this.getProgramNames();
////                curPrognames.removeAll(origProgNames);
//                // System.out.println("curPrognames = " + curPrognames);
//                showError(e.toString());
//            } catch (InterruptedException ex) {
////                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }, "moveThread" + moveCount);
//        moveThread.start();
    }
    public static final long MOVE_INTERVAL_MILLIS = 100;
    private int currentWaypointNumber = 0;

    public double distTransFrom(PoseType pose) {
        ICurPosition icp = robot.curPosition();

//        // System.out.println("icp = " + icp);
        ICurGroupPosition icgp = icp.group((short) 1, FRECurPositionConstants.frWorldDisplayType);
        icgp.refresh();
//        // System.out.println("icgp = " + icgp);
        Com4jObject com4jobj_pos = icgp.formats(FRETypeCodeConstants.frXyzWpr);
        IXyzWpr pos = com4jobj_pos.queryInterface(IXyzWpr.class);
//        // System.out.println("pos = " + pos);
//
//        // System.out.println("x: " + pos.x() + "   y:" + pos.y() + "    z:" + pos.z());
//        // System.out.println("w: " + pos.w() + "   y:" + pos.p() + "    r:" + pos.r());
        PmCartesian cart = new PmCartesian(pos.x() / lengthScale, pos.y() / lengthScale, pos.z() / lengthScale);
        PmRpy rpy = new PmRpy(Math.toRadians(pos.w()), Math.toRadians(pos.p()), Math.toRadians(pos.r()));
        return cart.distFrom(CRCLPosemath.pointToPmCartesian(pose.getPoint()));
    }

    public double distRotFrom(PoseType pose) throws PmException {
        ICurPosition icp = robot.curPosition();

//        // System.out.println("icp = " + icp);
        ICurGroupPosition icgp = icp.group((short) 1, FRECurPositionConstants.frWorldDisplayType);
        icgp.refresh();
//        // System.out.println("icgp = " + icgp);
        Com4jObject com4jobj_pos = icgp.formats(FRETypeCodeConstants.frXyzWpr);
        IXyzWpr pos = com4jobj_pos.queryInterface(IXyzWpr.class);
//        // System.out.println("pos = " + pos);
//
//        // System.out.println("x: " + pos.x() + "   y:" + pos.y() + "    z:" + pos.z());
//        // System.out.println("w: " + pos.w() + "   y:" + pos.p() + "    r:" + pos.r());
        PmRpy rpy = new PmRpy(Math.toRadians(pos.w()), Math.toRadians(pos.p()), Math.toRadians(pos.r()));
        PmRotationVector rotvCurrent = Posemath.toRot(rpy);
        PmRotationVector rotvArg = CRCLPosemath.toPmRotationVector(pose);
        PmRotationVector rotvDiff = rotvArg.multiply(rotvCurrent.inv());
        System.out.println("rotvDiff.s = " + rotvDiff.s);
        return Math.toDegrees(rotvDiff.s);
    }

    private void moveToGroupPos() throws InterruptedException {
        boolean didit = false;
        int tries = 0;
        long t0 = System.currentTimeMillis();
//        System.out.println("starting moveToGroupPos()");
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
//        // System.out.println("moveToGroupPos took " + (t1 - t0) + "ms and " + tries + " tries.");
    }

    private void handleMoveThroughTo(MoveThroughToType moveCmd) throws PmException {
        // System.out.println("groupPos.isAtCurPosition() = " + groupPos.isAtCurPosition());
//        groupPos.refresh();
        posReg97Updated = false;
        setCommandState(CommandStateEnumType.CRCL_WORKING);
        if (moveThread != null) {
            try {
                moveThread.join(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            moveThread.interrupt();
            try {
                moveThread.join(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            moveThread = null;
        }

//        Com4jObject com4jobj_joint_sys_pos = isgp.formats(FRETypeCodeConstants.frJoint);
//        IJoint sys_joint_pos = com4jobj_joint_sys_pos.queryInterface(IJoint.class);
//        // System.out.println("sys_joint_pos = " + sys_joint_pos);
//        for (short i = 1; i <= sys_joint_pos.count(); i++) {
//            try {
//                // System.out.println("sys_joint_pos.item(" + i + ") = " + sys_joint_pos.item(i));
//            } catch (Exception e) {
//                System.err.println("i= " + i);
//                e.printStackTrace();
//            }
//        }
//        groupPos.update();
//        moveToGroupPos();
        moveCount++;
        moveThread = new Thread(() -> {
            try {
                for (currentWaypointNumber = 0; currentWaypointNumber < moveCmd.getNumPositions().intValue() && currentWaypointNumber < moveCmd.getWaypoint().size(); currentWaypointNumber++) {
                    PoseType pose = moveCmd.getWaypoint().get(currentWaypointNumber);
                    PmCartesian cart = CRCLPosemath.pointToPmCartesian(pose.getPoint());
                    PmRpy rpy = CRCLPosemath.toPmRpy(pose);
                    ICurPosition icp = robot.curPosition();
//        // System.out.println("icp = " + icp);
                    ICurGroupPosition icgp = icp.group((short) 1, FRECurPositionConstants.frWorldDisplayType);
//        // System.out.println("icgp = " + icgp);
                    Com4jObject com4jobj_pos = icgp.formats(FRETypeCodeConstants.frXyzWpr);
                    IXyzWpr pos = com4jobj_pos.queryInterface(IXyzWpr.class);
                    // System.out.println("pos = " + pos);

                    // System.out.println("x: " + pos.x() + "   y:" + pos.y() + "    z:" + pos.z());
                    // System.out.println("w: " + pos.w() + "   y:" + pos.p() + "    r:" + pos.r());
                    // System.out.println("isgp = " + groupPos);
                    Com4jObject com4jobj_sys_pos = groupPos.formats(FRETypeCodeConstants.frXyzWpr);
                    IXyzWpr sys_pos = com4jobj_sys_pos.queryInterface(IXyzWpr.class);
                    // System.out.println("sys_pos = " + sys_pos);
                    // System.out.println("x: " + sys_pos.x() + "   y:" + sys_pos.y() + "    z:" + sys_pos.z());
                    // System.out.println("w: " + sys_pos.w() + "   y:" + sys_pos.p() + "    r:" + sys_pos.r());
                    // System.out.println("Refreshing groupPos");
                    long t0 = System.currentTimeMillis();
//        groupPos.refresh();
                    long t1 = System.currentTimeMillis();
                    // System.out.println("refresh groupPose took = " + (t1 - t0) + "millis");
                    sys_pos.setAll(cart.x * lengthScale, cart.y * lengthScale, cart.z * lengthScale,
                            Math.toDegrees(rpy.r), Math.toDegrees(rpy.p), Math.toDegrees(rpy.y));
                    // System.out.println("updating groupPos");
                    long t2 = System.currentTimeMillis();
                    groupPos.update();
                    long t3 = System.currentTimeMillis();
                    // System.out.println("updating groupPos took " + (t3 - t2) + " ms");
                    // System.out.println("sys_pos = " + sys_pos);
                    // System.out.println("x: " + sys_pos.x() + "   y:" + sys_pos.y() + "    z:" + sys_pos.z());
                    // System.out.println("w: " + sys_pos.w() + "   y:" + sys_pos.p() + "    r:" + sys_pos.r());

                    // System.out.println("cart = " + cart);
                    // System.out.println("rpy = " + rpy);
                    // System.out.println("Starting move " + moveCount);
                    // System.out.println("currentWaypointNumber = " + currentWaypointNumber);
                    long t4 = System.currentTimeMillis();
                    moveToGroupPos();
                    if (Thread.currentThread().isInterrupted()) {
                        return;
                    }
                    Thread.sleep(40);
                    double dist = distTransFrom(pose);
                    double distRot = distRotFrom(pose);
//                    // System.out.println("dist = " + dist);
                    while (dist > distanceTolerance
                            || distRot > distanceRotTolerance
                            || !groupPos.isAtCurPosition()) {
                        if (Thread.currentThread().isInterrupted()) {
                            return;
                        }
                        Thread.sleep(20);
                        dist = distTransFrom(pose);
//                        // System.out.println("dist = " + dist);
                    }
                    long t5 = System.currentTimeMillis();
                    moveTime = t5;
                    // System.out.println("Move  took = " + (t5 - t4) + " ms");
                    Thread.sleep(20);
                }
            } catch (InterruptedException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ComException | PmException e) {
                e.printStackTrace();
                System.err.println("Time since moveTime = " + (System.currentTimeMillis() - moveTime));
                System.err.println("Time since moveDoneTime = " + (System.currentTimeMillis() - moveDoneTime));
//                Set<String> curPrognames = this.getProgramNames();
//                curPrognames.removeAll(origProgNames);
                // System.out.println("curPrognames = " + curPrognames);
                showError(e.toString());
            }
        }, "moveThread" + moveCount);
        moveThread.start();
    }
    public double distanceTolerance = 1.0; // millimeter
    public double distanceRotTolerance = 0.5; // degrees

    private void handleSetLengthUnits(SetLengthUnitsType slu) {
        this.setLengthUnit(slu.getUnitName());
        setCommandState(CommandStateEnumType.CRCL_DONE);
    }

    private void handleSetEndPoseTolerance(SetEndPoseToleranceType sepCmd) {
        PoseToleranceType poseTol = sepCmd.getTolerance();
        distanceTolerance = Math.min(poseTol.getXPointTolerance().doubleValue(),
                Math.min(poseTol.getXPointTolerance().doubleValue(),
                        poseTol.getZPointTolerance().doubleValue()));
        setCommandState(CommandStateEnumType.CRCL_DONE);
    }

    long dwellEndTime = 0;

    private void handleDwell(DwellType dwellCmd) {
        dwellEndTime = System.currentTimeMillis() + ((long) (dwellCmd.getDwellTime().doubleValue() * 1000.0 + 1.0));
        System.out.println("dwellEndTime = " + dwellEndTime);
        setCommandState(CommandStateEnumType.CRCL_WORKING);
    }

    private void setCommandState(CommandStateEnumType newState) {
        status.getCommandStatus().setCommandState(newState);
    }
    private ConfigureJointReportsType cjrs;
    private Map<Integer, ConfigureJointReportType> cjrMap = null;

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
//                    e.printStackTrace();
                    continue;
                }
                try {
                    if (tsk != null && tskProgName != null && tskProgName.equals(prog.name())) {
                        FRETaskStatusConstants tskStatus = tsk.status();
                        System.out.println("tskStatus = " + tskStatus);
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
//            robot.tasks().abortAll(true);
            prog.run(FREStepTypeConstants.frStepNone, 1, FREExecuteConstants.frExecuteFwd);
        }
    }

    private void handleActuateJoints(ActuateJointsType ajCmd) throws PmException, InterruptedException {
        // System.out.println("groupPos.isAtCurPosition() = " + groupPos.isAtCurPosition());
//        groupPos.refresh();
        posReg98Updated = false;
        setCommandState(CommandStateEnumType.CRCL_WORKING);
        updatePosReg97();
        posReg97.refresh();
        final IJoint posReg97Joint = posReg97.formats(FRETypeCodeConstants.frJoint).queryInterface(IJoint.class);
        System.out.println("posReg97Joint = " + posReg97Joint);
        for (ActuateJointType aj : ajCmd.getActuateJoint()) {
            double val = aj.getJointPosition().doubleValue();
            System.out.println("fval = " + val);
            short number = aj.getJointNumber().shortValue();
            System.out.println("number = " + number);
            if (val > this.upperJointLimits[number - 1]) {
                System.out.println("over upper limit");
                System.out.println("number = " + number);
                System.out.println("fval = " + val);
                val = this.upperJointLimits[number - 1];
                System.out.println("fval = " + val);
            }
            if (val < this.lowerJointLimits[number - 1]) {
                System.out.println("uner lower limit");
                System.out.println("number = " + number);
                System.out.println("fval = " + val);
                val = this.lowerJointLimits[number - 1];
                System.out.println("fval = " + val);
            }
            float curVal = (float) posReg97Joint.item(number);
            System.out.println("curVal = " + curVal);
            float absDiff = (float) Math.abs(val - curVal);
            float speed = DEFAULT_JOINT_SPEED;

            JointDetailsType jd = aj.getJointDetails();
            if (jd instanceof JointSpeedAccelType) {
                JointSpeedAccelType jsa = (JointSpeedAccelType) jd;
                if (jsa.getJointSpeed() != null) {
                    speed = (float) jsa.getJointSpeed().floatValue();
                    if (speed < Float.MIN_NORMAL) {
                        speed = Float.MIN_NORMAL;
                    }
                }
            }
            if (speed < 0.5f) {
                speed = 0.5f;
            }
            int time = (int) (1000 * absDiff / speed);
            if (time < 5000) {
                time = 5000;
            }
            regNumeric97.regFloat(speed);
//            reg97Var.update();
            posReg97Joint.item(number, val);
        }
//        limitAndUpdatePos(posReg97);
        posReg97.update();
        System.out.println("move_joint_prog = " + move_joint_prog);
        this.runTPProgram(move_joint_prog);

        if (moveThread != null) {
            moveThread.interrupt();
            try {
                moveThread.join(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            moveThread = null;
        }
//        ICurPosition icp = robot.curPosition();
////        // System.out.println("icp = " + icp);
//        ICurGroupPosition icgp = icp.group((short) 1, FRECurPositionConstants.frJointDisplayType);
////        // System.out.println("icgp = " + icgp);
//        Com4jObject com4jobj_pos = icgp.formats(FRETypeCodeConstants.frJoint);
//        IJoint cur_joint_pos = com4jobj_pos.queryInterface(IJoint.class);
//        // System.out.println("pos = " + cur_joint_pos);
//        Com4jObject com4jobj_sys_pos = groupPos.formats(FRETypeCodeConstants.frJoint);
//        
//        IJoint sys_pos = com4jobj_sys_pos.queryInterface(IJoint.class);
//        for (short i = 1; i <= cur_joint_pos.count(); i++) {
//            double d = cur_joint_pos.item(i);
//            sys_pos.item(i, d);
//        }
//        // System.out.println("sys_pos = " + sys_pos);
//        for (ActuateJointType aj : ajCmd.getActuateJoint()) {
//            sys_pos.item(aj.getJointNumber().shortValue(), aj.getJointPosition().doubleValue());
//        }
////        sys_pos.
////        // System.out.println("x: " + sys_pos.x() + "   y:" + sys_pos.y() + "    z:" + sys_pos.z());
////        // System.out.println("w: " + sys_pos.w() + "   y:" + sys_pos.p() + "    r:" + sys_pos.r());
//
////        Com4jObject com4jobj_joint_sys_pos = isgp.formats(FRETypeCodeConstants.frJoint);
////        IJoint sys_joint_pos = com4jobj_joint_sys_pos.queryInterface(IJoint.class);
////        // System.out.println("sys_joint_pos = " + sys_joint_pos);
////        for (short i = 1; i <= sys_joint_pos.count(); i++) {
////            try {
////                // System.out.println("sys_joint_pos.item(" + i + ") = " + sys_joint_pos.item(i));
////            } catch (Exception e) {
////                System.err.println("i= " + i);
////                e.printStackTrace();
////            }
////        }
//        groupPos.update();
////        groupPos.update();
////        moveToGroupPos();
//        moveCount++;
//        moveThread = new Thread(() -> {
//            try {
//                // System.out.println("Starting move " + moveCount);
//                long t4 = System.currentTimeMillis();
//                moveToGroupPos();
//                long t5 = System.currentTimeMillis();
//                moveTime = t5;
//                // System.out.println("Move  took = " + (t4 - t5) + " ms");
////                Set<String> curPrognames = this.getProgramNames();
////                curPrognames.removeAll(origProgNames);
//                // System.out.println("curPrognames = " + curPrognames);
//            } catch (InterruptedException ie) {
//                // ignore
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.err.println("Time since moveTime = " + (System.currentTimeMillis() - moveTime));
//                System.err.println("Time since moveDoneTime = " + (System.currentTimeMillis() - moveDoneTime));
////                Set<String> curPrognames = this.getProgramNames();
////                curPrognames.removeAll(origProgNames);
//                // System.out.println("curPrognames = " + curPrognames);
//            }
//        }, "moveThread" + moveCount);
//        moveThread.start();
    }
    public static final float DEFAULT_JOINT_SPEED = 10.0f;

    private Set<String> lastAlarms;

    private Set<String> getAlarms() {
        IAlarms alarms = robot.alarms();
        Map<java.util.Date, String> alarmMap = new java.util.TreeMap<>();
        java.util.Date maxResetDate = null;
        if (null != alarms) {
            for (Com4jObject alarm_obj : alarms) {
//                // System.out.println("alarm_obj = " + alarm_obj);
                IAlarm alarm = alarm_obj.queryInterface(IAlarm.class);
//                // System.out.println("alarm = " + alarm);
                if (null != alarm) {
                    if (alarm.errorClass() == 1) {
                        if (maxResetDate == null || alarm.timeStamp().after(maxResetDate)) {
                            maxResetDate = alarm.timeStamp();
                            // System.out.println("maxResetDate = " + maxResetDate);
                            // System.out.println("alarm.index() = " + alarm.index());
                            // System.out.println("alarm.errorNumber() = " + alarm.errorNumber());
                            // System.out.println("alarm.errorSeverity() = " + alarm.errorSeverity());
                            // System.out.println("alarm.errorClass() = " + alarm.errorClass());
                            // System.out.println("alarm.severityMessage() = " + alarm.severityMessage());
                            // System.out.println("alarm.errorMotion() = " + alarm.errorMotion());
                            // System.out.println("alarm.errorMnemonic() = " + alarm.errorMnemonic());
                            // System.out.println("alarm.errorMessage() = " + alarm.errorMessage());
                            // System.out.println("alarm.causeMnemonic() = " + alarm.causeMnemonic());
                            // System.out.println("alarm.causeMessage() = " + alarm.causeMessage());
                        }
                    }
//                    // System.out.println("alarm.index() = " + alarm.index());
//                    // System.out.println("alarm.errorNumber() = " + alarm.errorNumber());
//                    // System.out.println("alarm.errorSeverity() = " + alarm.errorSeverity());
//                    // System.out.println("alarm.errorClass() = " + alarm.errorClass());
//                    // System.out.println("alarm.severityMessage() = " + alarm.severityMessage());
//                    // System.out.println("alarm.errorMotion() = " + alarm.errorMotion());
//                    // System.out.println("alarm.errorMnemonic() = " + alarm.errorMnemonic());
//                    // System.out.println("alarm.errorMessage() = " + alarm.errorMessage());
//                    // System.out.println("alarm.causeMnemonic() = " + alarm.causeMnemonic());
//                    // System.out.println("alarm.causeMessage() = " + alarm.causeMessage());
                    alarmMap.put(alarm.timeStamp(), alarm.errorMessage());
                }
            }
        }
        // System.out.println("alarmMap = " + alarmMap);
        // System.out.println("maxResetDate = " + maxResetDate);
        Set<String> alarmSet = new TreeSet<>();
        for (Com4jObject alc4jo : alarms) {
            IAlarm alarm = alc4jo.queryInterface(IAlarm.class);
            if (null != alarm) {
//                if(alarm.errorClass() == FREAlarmSeverityConstants.frErrorReset.comEnumValue()) {
//                    alarmSet.clear();
//                }
                if (alarm.errorMotion() != 0 && alarm.timeStamp().after(maxResetDate)) {
                    alarmSet.add(alarm.errorMessage());
                }
            }
        }
        // System.out.println("alarmSet = " + alarmSet);
        return alarmSet;
    }

    private CRCLCommandType prevCmd = null;

    CRCLSocket utilCrclSocket = new CRCLSocket();

    private void startCrclServer() throws IOException {
        es = Executors.newWorkStealingPool();
        ss = new ServerSocket(localPort);
        es.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    CRCLSocket cs = new CRCLSocket(ss.accept());
                    clients.add(cs);
                    es.submit(() -> {
                        try {
                            while (!Thread.currentThread().isInterrupted()) {
                                try {
                                    CRCLCommandInstanceType cmdInstance = cs.readCommand(validate);
                                    CRCLCommandType cmd = cmdInstance.getCRCLCommand();
                                    if (cmd instanceof GetStatusType) {
                                        CRCLStatusType status = readStatusFromRobot();
                                        cs.writeStatus(status, validate);
                                    } else {
                                        try {
                                            if (null == status.getCommandStatus()) {
                                                status.setCommandStatus(new CommandStatusType());
                                                status.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_ERROR);
                                            }

                                            if (status.getCommandStatus().getCommandState() == CommandStateEnumType.CRCL_DONE) {
                                                setCommandState(CommandStateEnumType.CRCL_WORKING);
                                            }
                                            status.getCommandStatus().setCommandID(cmd.getCommandID());
                                            status.getCommandStatus().setStatusID(BigInteger.ONE);
//                                    this.checkAlarms();
                                            if (null == robot || !robot.isConnected() || null == groupPos) {
                                                showError(utilCrclSocket.commandToSimpleString(cmd, 18, 70) + " recieved when robot not connected or not initialized.");
                                                continue;
                                            }
                                            if (null != this.jframe && this.jframe.getjCheckBoxLogAllCommands().isSelected()) {
                                                showError(utilCrclSocket.commandToSimpleString(cmd, 18, 70) + " recieved.");
                                                showError(cs.getLastCommandString());
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
                                            } else {
                                                showError("Unimplemented  command :" + cmd.getClass().getSimpleName());
                                            }
                                            prevCmd = cmd;
//                                    this.checkAlarms();
                                        } catch (ComException comEx) {
                                            showError(comEx.getMessage() + " : cmd=" + utilCrclSocket.commandToSimpleString(cmd, 18, 70));
                                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "cmd=" + utilCrclSocket.commandToPrettyString(cmd), comEx);
                                            disconnectRemoteRobot();
                                            connectRemoteRobot();
                                        }
                                    }
                                } catch (PmException ex) {
                                    showError(ex.getMessage());
                                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (ComException comEx) {
                                    showComException(comEx);
                                    disconnectRemoteRobot();
                                    connectRemoteRobot();
                                }
                            }
                        } catch (SocketException se) {
                            // probably just closing the connection.
                        } catch (JAXBException | EXIException | SAXException | IOException | InterruptedException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            showError(ex.getMessage());
                        } catch (Exception ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            showError(ex.getMessage());
                        } finally {
                            try {
                                System.out.println("Closing connection with " + cs.getInetAddress() + ":" + cs.getPort());
                                clients.remove(cs);
                                cs.close();
                            } catch (IOException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                }
            }
        });

    }
    private IRobot2 robot;
    private IIndGroupPosition groupPos;
    private ITPProgram close_gripper_prog;
    private ITPProgram open_gripper_prog;
    private ITPProgram move_linear_prog;
    private ITPProgram move_w_time_prog;
    private ITPProgram move_joint_prog;
    private IVar overrideVar = null;
    private IVar reg96Var=null;
    private IVar reg97Var=null;
    private IVar reg98Var=null;
    
    private IRegNumeric regNumeric96 = null;
    private IRegNumeric regNumeric97 = null;
    private IRegNumeric regNumeric98 = null;
    private ISysGroupPosition posReg98 = null;
    private ISysGroupPosition posReg97 = null;
    private FanucCRCLServerJFrame jframe = null;
//    private EventCookie robot_ec = null;

    private IRobotNeighborhood neighborhood = null;
    private String neighborhoodname = "AgilityLabLRMate200iD";
    private Thread robotMonitorThread = null;

    public String getNeighborhoodname() {
        return neighborhoodname;
    }

    public void setNeighborhoodname(String neighborhoodname) {
        if (this.neighborhoodname != neighborhoodname) {
            this.neighborhoodname = neighborhoodname;
            if (null != jframe) {
                jframe.getjTextFieldRobotNeighborhoodPath().setText(remoteRobotHost);
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
            if (null != jframe) {
                jframe.setPreferRobotNeighborhood(preferRobotNeighborhood);
            }
            this.disconnectRemoteRobot();
            this.connectRemoteRobot();
        }
    }

    float lowerJointLimits[] = new float[6];
    float upperJointLimits[] = new float[6];

    private void connectRemoteRobot() {
        try {

            if (preferRobotNeighborhood) {
                if (null == neighborhood) {
                    System.out.println("Calling createFRCRobotNeighborhood ...");
                    neighborhood = createFRCRobotNeighborhood();
                }
                System.out.println("Getting list of robots from neighborhood ...");
                IRNRobots robots = neighborhood.robots();
                for (Com4jObject c4jo : robots) {
                    IRNRobot irnrobot = c4jo.queryInterface(IRNRobot.class);
//            // System.out.println("irnrobot = " + irnrobot);
                    if (null != irnrobot) {
                        System.out.println("irnrobot.name() = " + irnrobot.name());
                        if (irnrobot.name().equals(neighborhoodname)) {
                            Com4jObject robot_object = irnrobot.robotServer();
                            robot = robot_object.queryInterface(IRobot2.class);
                            if (null != robot) {
                                try {
                                    System.out.println("irnrobot.connectionStatus() = " + irnrobot.connectionStatus());
                                    System.out.println("Found matching robot with name " + irnrobot.name());
                                } catch (ComException comEx) {
                                    showComException(comEx);
                                }
                            }
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
            // System.out.println("ir = " + robot);
//        robotMonitorThread = new Thread(() -> {
//            EventCookie robot_ec = robot.advise(IRobotEvents.class, new IRobotEvents() {
//                @Override
//                public void error(IRobotErrorInfo errInfo) {
//                    showError("error=" + errInfo);
//                    if (null != errInfo) {
//                        showError("errInfo = " + errInfo);
//                        showError("errInfo.description() = " + errInfo.description());
//                        showError("errInfo.source() = " + errInfo.source());
//                        showError("errInfo..helpFile() = " + errInfo.helpFile());
//                    }
//                }
//
//                @Override
//                public void disconnect() {
//                    showError("Robot disconnected.");
//                }
//
//                @Override
//                public void connect() {
//                    showInfo("Connected to " + remoteRobotHost + " at " + new Date());
//                }
//
//            });
//            try {
//                while (!Thread.currentThread().isInterrupted()) {
//                    Thread.sleep(100);
//                }
//            } catch (InterruptedException interruptedException) {
//            } finally {
//                robot_ec.close();
//            }
//        }, "robotMonitor");
//        robotMonitorThread.start();
//        // System.out.println("robot_ed = " + robot_ec);
            if (!robot.isConnected()) {
                System.out.println("Connecting to " + remoteRobotHost + " ...");
                robot.connectEx(remoteRobotHost, false, 100, 100);
            }
            // System.out.println("ir.isConnected() = " + robot.isConnected());
//        IRobotErrorInfo errInfo = robot.getErrorInfo();
//        if (null != errInfo) {
//            // System.out.println("errInfo = " + errInfo);
//            // System.out.println("errInfo.description() = " + errInfo.description());
//            // System.out.println("errInfo.source() = " + errInfo.source());
//            // System.out.println("errInfo..helpFile() = " + errInfo.helpFile());
//        }

//        IAlarms alarms = robot.alarms();
            // System.out.println("alarms = " + alarms);
//        alarmCookie = alarms.advise(IAlarmNotify.class, new IAlarmNotify() {
//            @Override
//            public void alarmNotify(Com4jObject alarm) {
//                try {
//                    // System.out.println("alarm = " + alarm);
//                    IAlarm ialarm = alarm.queryInterface(IAlarm.class);
//                    // System.out.println("ialarm = " + ialarm);
//                    Main.this.alarmEventOccured = true;
//                    JOptionPane.showMessageDialog(null,
//                            ialarm.errorMessage());
//                    Main.this.getStatus().getCommandStatus().setCommandState(CommandStateEnumType.CRCL_ERROR);
//                } catch (PmException ex) {
//                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//        // System.out.println("alarmCookie = " + alarmCookie);
//        Map<java.util.Date, IAlarm> alarmMap = new java.util.TreeMap<>();
//        java.util.Date maxResetDate = null;
//        if (null != alarms) {
//            for (Com4jObject alarm_obj : alarms) {
//                // System.out.println("alarm_obj = " + alarm_obj);
//                IAlarm alarm = alarm_obj.queryInterface(IAlarm.class);
//                // System.out.println("alarm = " + alarm);
//                if (null != alarm) {
//                    if (alarm.errorClass() == 1) {
//                        if (maxResetDate == null || alarm.timeStamp().after(maxResetDate)) {
//                            maxResetDate = alarm.timeStamp();
//                        }
//                    }
//                    // System.out.println("alarm.timeStamp() = " + alarm.timeStamp());
//
//                    // System.out.println("alarm.errorNumber() = " + alarm.errorNumber());
//                    // System.out.println("alarm.errorSeverity() = " + alarm.errorSeverity());
//                    // System.out.println("alarm.errorClass() = " + alarm.errorClass());
//                    // System.out.println("alarm.severityMessage() = " + alarm.severityMessage());
//                    // System.out.println("alarm.errorMotion() = " + alarm.errorMotion());
//                    // System.out.println("alarm.errorMnemonic() = " + alarm.errorMnemonic());
//                    // System.out.println("alarm.errorMessage() = " + alarm.errorMessage());
//                    // System.out.println("alarm.causeMnemonic() = " + alarm.causeMnemonic());
//                    // System.out.println("alarm.causeMessage() = " + alarm.causeMessage());
//                    alarmMap.put(alarm.timeStamp(), alarm);
//                }
//            }
//        }
//        // System.out.println("maxResetDate = " + maxResetDate);
//        // System.out.println("alarmMap = " + alarmMap);
//        ICurPosition icp = robot.curPosition();
//        posEventCookie = icp.advise(IPositionEvents.class, new IPositionEvents() {
//            @Override
//            public void change() {
//                // System.out.println("position changed");
//            }
//        });
            // System.out.println("icp = " + icp);
//        ICurGroupPosition icgp = icp.group((short) 1, FRECurPositionConstants.frWorldDisplayType);
            // System.out.println("icgp = " + icgp);
//        Com4jObject com4jobj_pos = icgp.formats(FRETypeCodeConstants.frXyzWpr);
//        IXyzWpr pos = com4jobj_pos.queryInterface(IXyzWpr.class);
            // System.out.println("pos = " + pos);
            // System.out.println("x: " + pos.x() + "   y:" + pos.y() + "    z:" + pos.z());
            // System.out.println("w: " + pos.w() + "   y:" + pos.p() + "    r:" + pos.r());
//        Com4jObject com4jobj_joint_pos = icgp.formats(FRETypeCodeConstants.frJoint);
//        IJoint joint_pos = com4jobj_joint_pos.queryInterface(IJoint.class);
//        // System.out.println("joint_pos = " + joint_pos);
//        for (short i = 1; i <= joint_pos.count(); i++) {
//            // System.out.println("joint_pos.item(" + i + ") = " + joint_pos.item(i));
//        }
            System.out.println("Calling robot.createIndependentPosition ...");
            IIndPosition iip = robot.createIndependentPosition(FREGroupBitMaskConstants.frGroup1BitMask);
            iip.record();
            groupPos = iip.group((short) 1);
//          ISysPositions isps = robot.regPositions();
//        Com4jObject com4jo = isps.iterator().next();
//        // System.out.println("com4jo = " + com4jo);
//        ISysPosition isp = com4jo.queryInterface(ISysPosition.class);
//        // System.out.println("isp = " + isp);
//        // System.out.println("isp.isInitialized() = " + isp.isInitialized());
//        if (!isp.isInitialized()) {
//            return;
//        }
//        groupPos = isp.group((short) 1);
            System.out.println("Getting list of programs ...");
            IPrograms programs = robot.programs();
            if (null != programs) {
                synchronized (tpPrograms) {
                    if (null != jframe) {
                        jframe.setPrograms(null);
                    }
                    tpPrograms.clear();
                    for (Com4jObject com4jo_program : programs) {
                        ITPProgram program = com4jo_program.queryInterface(ITPProgram.class);
//            // System.out.println("program = " + program);
//            if (null != program) {
//                // System.out.println("program.name() = " + program.name());
//                try {
//                    ITPLines lines = program.lines();
//                    if (null != lines) {
//                        for (Com4jObject c4jo : lines) {
////                            // System.out.println("c4jo = " + c4jo);
//                            ITPSimpleLine sl = c4jo.queryInterface(ITPSimpleLine.class);
//                            if(null != sl) {
//                                // System.out.println(sl.number() + ": " + sl.text());
//                            }
//                        }
//                    }
//
//                } catch (Exception e) {
//                }
//            }
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
                        if (null != program && program.name().equalsIgnoreCase("MOVE_JOINTTEST")) {
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
            regNumeric97.regFloat(DEFAULT_JOINT_SPEED);
            reg98Var = robot.regNumerics().item(98, null).queryInterface(IVar.class);
            regNumeric98 = ((Com4jObject) reg98Var.value()).queryInterface(IRegNumeric.class);
            regNumeric98.regFloat(DEFAULT_CART_SPEED);

            posReg98 = robot.regPositions().item(98, null).queryInterface(ISysPosition.class).group((short) 1);
            posReg98.record();
            System.out.println("posReg98 = " + posReg98);
            posReg97 = robot.regPositions().item(97, null).queryInterface(ISysPosition.class).group((short) 1);
            posReg97.record();
            System.out.println("posReg97 = " + posReg97);
            System.out.println("Calling robot.sysVariables() ...");
            IVars sysvars = robot.sysVariables();
            System.out.println("Getting system variable for $MCR.$GENOVERRIDE ...");
            overrideVar = sysvars.item("$MCR.$GENOVERRIDE", null).queryInterface(IVar.class);
            IVar xLimitVar1 = sysvars.item("$DCSS_CPC[1].$X[1]", null).queryInterface(IVar.class);
            System.out.println("xLimitVar1 = " + xLimitVar1);
            if (null != xLimitVar1) {
                System.out.println("xLimitVar1.value() = " + xLimitVar1.value());
                xMax = xMin = (Float) xLimitVar1.value();
            }
            IVar xLimitVar2 = sysvars.item("$DCSS_CPC[1].$X[2]", null).queryInterface(IVar.class);
            System.out.println("xLimitVar2 = " + xLimitVar2);
            if (null != xLimitVar2) {
                System.out.println("xLimitVar2.value() = " + xLimitVar2.value());
                float v = (Float) xLimitVar2.value();
                if (xMax < v) {
                    xMax = v;
                }
                if (xMin > v) {
                    xMin = v;
                }
            }
            IVar xLimitVar3 = sysvars.item("$DCSS_CPC[1].$X[3]", null).queryInterface(IVar.class);
            System.out.println("xLimitVar3 = " + xLimitVar3);
            if (null != xLimitVar3) {
                System.out.println("xLimitVar3.value() = " + xLimitVar3.value());
                float v = (Float) xLimitVar3.value();
                if (xMax < v) {
                    xMax = v;
                }
                if (xMin > v) {
                    xMin = v;
                }
            }
            IVar xLimitVar4 = sysvars.item("$DCSS_CPC[1].$X[4]", null).queryInterface(IVar.class);
            System.out.println("xLimitVar4 = " + xLimitVar4);
            if (null != xLimitVar4) {
                System.out.println("xLimitVar4.value() = " + xLimitVar4.value());
                float v = (Float) xLimitVar4.value();
                if (xMax < v) {
                    xMax = v;
                }
                if (xMin > v) {
                    xMin = v;
                }
            }

            System.out.println("xMin = " + xMin);
            System.out.println("xMax = " + xMax);

            IVar yLimitVar1 = sysvars.item("$DCSS_CPC[1].$Y[1]", null).queryInterface(IVar.class);
            System.out.println("yLimitVar1 = " + yLimitVar1);
            if (null != yLimitVar1) {
                System.out.println("yLimitVar1.value() = " + yLimitVar1.value());
                yMax = yMin = (Float) yLimitVar1.value();
            }
            IVar yLimitVar2 = sysvars.item("$DCSS_CPC[1].$Y[2]", null).queryInterface(IVar.class);
            System.out.println("yLimitVar2 = " + yLimitVar2);
            if (null != yLimitVar2) {
                System.out.println("yLimitVar2.value() = " + yLimitVar2.value());
                float v = (Float) yLimitVar2.value();
                if (yMax < v) {
                    yMax = v;
                }
                if (yMin > v) {
                    yMin = v;
                }
            }
            IVar yLimitVar3 = sysvars.item("$DCSS_CPC[1].$Y[3]", null).queryInterface(IVar.class);
            System.out.println("yLimitVar3 = " + yLimitVar3);
            if (null != yLimitVar3) {
                System.out.println("yLimitVar3.value() = " + yLimitVar3.value());
                float v = (Float) yLimitVar3.value();
                if (yMax < v) {
                    yMax = v;
                }
                if (yMin > v) {
                    yMin = v;
                }
            }
            IVar yLimitVar4 = sysvars.item("$DCSS_CPC[1].$Y[4]", null).queryInterface(IVar.class);
            System.out.println("yLimitVar4 = " + yLimitVar4);
            if (null != yLimitVar4) {
                System.out.println("yLimitVar4.value() = " + yLimitVar4.value());
                float v = (Float) yLimitVar4.value();
                if (yMax < v) {
                    yMax = v;
                }
                if (yMin > v) {
                    yMin = v;
                }
            }

            System.out.println("yMin = " + yMin);
            System.out.println("yMax = " + yMax);

            IVar zLimitVar1 = sysvars.item("$DCSS_CPC[1].$Z1", null).queryInterface(IVar.class);
            System.out.println("zLimitVar1 = " + zLimitVar1);
            if (null != zLimitVar1) {
                System.out.println("zLimitVar1.value() = " + zLimitVar1.value());
                zMax = zMin = (Float) zLimitVar1.value();
            }
            IVar zLimitVar2 = sysvars.item("$DCSS_CPC[1].$Z2", null).queryInterface(IVar.class);
            System.out.println("zLimitVar2 = " + zLimitVar2);
            if (null != zLimitVar2) {
                System.out.println("zLimitVar2.value() = " + zLimitVar2.value());
                float v = (Float) zLimitVar2.value();
                if (zMax < v) {
                    zMax = v;
                }
                if (zMin > v) {
                    zMin = v;
                }
            }

            System.out.println("zMin = " + zMin);
            System.out.println("zMax = " + zMax);
            for (int i = 0; i < 6; i++) {
                IVar jointLowerLimVar = sysvars.item("$MRR_GRP[1].$LOWERLIMSDF[" + (i + 1) + "]", null).queryInterface(IVar.class);
                System.out.println("joint1LowerLimVar = " + jointLowerLimVar);
                System.out.println("joint1LowerLimVar.value() = " + jointLowerLimVar.value());
                this.lowerJointLimits[i] = (Float) jointLowerLimVar.value();
            }
            for (int i = 0; i < 6; i++) {
                IVar jointUpperLimVar = sysvars.item("$MRR_GRP[1].$UPPERLIMSDF[" + (i + 1) + "]", null).queryInterface(IVar.class);
                System.out.println("joint1UpperLimVar = " + jointUpperLimVar);
                System.out.println("joint1UpperLimVar.value() = " + jointUpperLimVar.value());
                this.upperJointLimits[i] = (Float) jointUpperLimVar.value();
            }
            System.out.println("lowerJointLimits = " + Arrays.toString(lowerJointLimits));
            System.out.println("upperJointLimits = " + Arrays.toString(upperJointLimits));
            updateJFrame();
        } catch (ComException comEx) {
            showComException(comEx);
        } catch (Exception e) {
            e.printStackTrace();
            showError(e.toString());
        }
//        robot.
    }
    public static final float DEFAULT_CART_SPEED = 100.0f;

    private String lastComExString = null;
    private long last_com_ex_time = 0;

    public void showComException(ComException comEx) {
        String newMsg = comEx.getMessage();
        if (!newMsg.equals(lastComExString) || (System.currentTimeMillis() - last_com_ex_time) > 5000) {
            showError(newMsg);
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, comEx);
            lastComExString = newMsg;
            last_com_ex_time = System.currentTimeMillis();
        }
    }

    public void updateJFrame() {
        //        // System.out.println("mcrVar = " + mcrVar);
//        // System.out.println("mcrVar.value() = " + mcrVar.value());
//        // System.out.println("mcrVar.value().getClass() = " + mcrVar.value().getClass());
//            int mcr_genoverride_value = Integer.valueOf(overrideVar.value().toString());
//            final int startOverride = Math.max(9, Math.min(mcr_genoverride_value, ((int)maxRelativeSpeed)));
//            overrideVar.value(startOverride);
        if (null != jframe) {
            jframe.setRobot(robot);
            jframe.setPrograms(tpPrograms);
            jframe.getjTableCartesianLimits().setModel(new DefaultTableModel(
                    new Object[][]{
                        new Object[]{"X", xMin, 0, xMax},
                        new Object[]{"Y", yMin, 0, yMax},
                        new Object[]{"Z", zMin, 0, zMax},},
                    new String[]{"Axis", "Minimum", "Current", "Maximum"}));
            jframe.getjTableJointLimits().setModel(new DefaultTableModel(
                    new Object[][]{
                        new Object[]{1, lowerJointLimits[0], 0, upperJointLimits[0]},
                        new Object[]{2, lowerJointLimits[1], 0, upperJointLimits[1]},
                        new Object[]{3, lowerJointLimits[2], 0, upperJointLimits[2]},
                        new Object[]{4, lowerJointLimits[3], 0, upperJointLimits[3]},
                        new Object[]{5, lowerJointLimits[4], 0, upperJointLimits[4]},
                        new Object[]{6, lowerJointLimits[5], 0, upperJointLimits[5]}
                    },
                    new String[]{"Joint", "Minimum", "Current", "Maximum"}));
            jframe.setOverrideVar(getOverideVar());
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        String neighborhoodname = args.length > 0 ? args[0] : "AgilityLabLRMate200iD";
        String host = args.length > 1 ? args[1] : "129.6.78.111";
        int port = args.length > 2 ? Integer.valueOf(args[2]) : CRCLSocket.DEFAULT_PORT;
        boolean prefRNN = (args.length > 3) ? Boolean.valueOf(args[3]) : false;
        main.start(prefRNN, neighborhoodname, host, port);
        System.out.println("Press enter \"stop\" to quit");
        Scanner in = new Scanner(System.in);
        while (!in.nextLine().equals("stop")) {
            System.out.println("Enter \"stop\" to quit");
        }
        main.stop();
//        // System.out.println("isps = " + isps);
//        for (Com4jObject com4jo : isps) {
//            // System.out.println("com4jo = " + com4jo);
//            ISysPosition isp = com4jo.queryInterface(ISysPosition.class);
//            // System.out.println("isp = " + isp);
//            // System.out.println("isp.isInitialized() = " + isp.isInitialized());
//            if (!isp.isInitialized()) {
//                continue;
//            }
//            // System.out.println("isp.isAtCurPosition() = " + isp.isAtCurPosition());
//
//            ISysGroupPosition isgp = isp.group((short) 1);
//            // System.out.println("isgp = " + isgp);
//            Com4jObject com4jobj_sys_pos = isgp.formats(FRETypeCodeConstants.frXyzWpr);
//            IXyzWpr sys_pos = com4jobj_sys_pos.queryInterface(IXyzWpr.class);
//            // System.out.println("sys_pos = " + sys_pos);
//            // System.out.println("x: " + sys_pos.x() + "   y:" + sys_pos.y() + "    z:" + sys_pos.z());
//            // System.out.println("w: " + sys_pos.w() + "   y:" + sys_pos.p() + "    r:" + sys_pos.r());
//
//            Com4jObject com4jobj_joint_sys_pos = isgp.formats(FRETypeCodeConstants.frJoint);
//            IJoint sys_joint_pos = com4jobj_joint_sys_pos.queryInterface(IJoint.class);
//            // System.out.println("sys_joint_pos = " + sys_joint_pos);
//            for (short i = 1; i <= sys_joint_pos.count(); i++) {
//                try {
//                    // System.out.println("sys_joint_pos.item(" + i + ") = " + sys_joint_pos.item(i));
//                } catch (Exception e) {
//                    System.err.println("i= " + i);
//                    e.printStackTrace();
//                }
//            }

//            sys_pos.setAll(pos.x()+10, pos.y(), pos.z(), pos.w(), pos.p(), pos.r());
//            isp.refresh();
//            // System.out.println("isp.isAtCurPosition() = " + isp.isAtCurPosition());
//
//            isgp = isp.group((short) 1);
//            // System.out.println("isgp = " + isgp);
//            com4jobj_sys_pos = isgp.formats(FRETypeCodeConstants.frXyzWpr);
//            sys_pos = com4jobj_sys_pos.queryInterface(IXyzWpr.class);
//            // System.out.println("sys_pos = " + sys_pos);
//            // System.out.println("x: " + sys_pos.x() + "   y:" + sys_pos.y() + "    z:" + sys_pos.z());
//            // System.out.println("w: " + sys_pos.w() + "   y:" + sys_pos.p() + "    r:" + sys_pos.r());
//            com4jobj_joint_sys_pos = isgp.formats(FRETypeCodeConstants.frJoint);
//            sys_joint_pos = com4jobj_joint_sys_pos.queryInterface(IJoint.class);
//            // System.out.println("sys_joint_pos = " + sys_joint_pos);
//            for (short i = 1; i <= sys_joint_pos.count(); i++) {
//                try {
//                    // System.out.println("sys_joint_pos.item(" + i + ") = " + sys_joint_pos.item(i));
//                } catch (Exception e) {
//                    System.err.println("i= "+i);
//                    e.printStackTrace();
//                }
//            }
////            isgp.copy(com4jobj_sys_pos);
//            isgp.moveto();
//            break;
//        }
//        new Scanner(System.in).nextLine();
//        icgp.moveto();
//        IRobotNeighborhood irn = ClassFactory.createFRCRobotNeighborhood();
//        IRNRobots irnRobots = irn.robots();
//        for (Com4jObject com4jo : irnRobots) {
//            // System.out.println("com4jo = " + com4jo);
//            IRobot2 ir2 = com4jo.queryInterface(IRobot2.class);
//            // System.out.println("ir2 = " + ir2);
////            IRNRobot irnRobot = com4jo.queryInterface(IRNRobot.class);
////            // System.out.println("irnRobot = " + irnRobot);
////            if(null != irnRobot) {
////                // System.out.println("irnRobot.pathName() = " + irnRobot.pathName());
////                if(irnRobot.pathName().equals("\\AgilityLabLRMate200iD")) {
////                    IRNServices services = irnRobot.services();
////                    // System.out.println("services = " + services);
////                    for(Com4jObject service_com4jo : services) {
////                        // System.out.println("service_com4jo = " + service_com4jo);
////                        IRNService service = service_com4jo.queryInterface(IRNService.class);
////                        // System.out.println("service = " + service);
//////                        service.
////                    }
////                }
//        }
    }
}
