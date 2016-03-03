/* 
 * This is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * 
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain. This software is experimental.
 * NIST assumes no responsibility whatsoever for its use by other
 * parties, and makes no guarantees, expressed or implied, about its
 * quality, reliability, or any other characteristic. We would appreciate
 * acknowledgment if the software is used. This software can be
 * redistributed and/or modified freely provided that any derivative works
 * bear some notice that they are derived from it, and any modified
 * versions bear some notice that they have been modified.
 * 
 */
package com.github.wshackle.fanuccrclservermain;

import com.github.wshackle.fanuc.robotserver.FRECurPositionConstants;
import com.github.wshackle.fanuc.robotserver.FREExecuteConstants;
import com.github.wshackle.fanuc.robotserver.FREProgramTypeConstants;
import com.github.wshackle.fanuc.robotserver.FREStepTypeConstants;
import com.github.wshackle.fanuc.robotserver.FRETaskStatusConstants;
import com.github.wshackle.fanuc.robotserver.FRETypeCodeConstants;
import com.github.wshackle.fanuc.robotserver.IConfig;
import com.github.wshackle.fanuc.robotserver.ICurGroupPosition;
import com.github.wshackle.fanuc.robotserver.IJoint;
import com.github.wshackle.fanuc.robotserver.IProgram;
import com.github.wshackle.fanuc.robotserver.IRobot2;
import com.github.wshackle.fanuc.robotserver.ISysPosition;
import com.github.wshackle.fanuc.robotserver.ITPProgram;
import com.github.wshackle.fanuc.robotserver.ITPSimpleLine;
import com.github.wshackle.fanuc.robotserver.ITask;
import com.github.wshackle.fanuc.robotserver.ITasks;
import com.github.wshackle.fanuc.robotserver.IVar;
import com.github.wshackle.fanuc.robotserver.IVars;
import com.github.wshackle.fanuc.robotserver.IXyzWpr;
import com4j.Com4jObject;
import com4j.ComException;
import crcl.base.CRCLProgramType;
import crcl.base.DwellType;
import crcl.base.EndCanonType;
import crcl.base.InitCanonType;
import crcl.base.LengthUnitEnumType;
import crcl.base.MoveToType;
import crcl.base.PoseToleranceType;
import crcl.base.PoseType;
import crcl.base.SetEndEffectorType;
import crcl.base.SetEndPoseToleranceType;
import crcl.base.SetLengthUnitsType;
import crcl.base.SetTransSpeedType;
import crcl.base.TransSpeedAbsoluteType;
import crcl.utils.CRCLException;
import crcl.utils.CRCLPosemath;
import crcl.utils.CRCLSocket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import rcs.posemath.PmCartesian;
import rcs.posemath.PmException;
import rcs.posemath.PmRpy;
import rcs.posemath.Posemath;

/**
 *
 * @author shackle
 */
public class FanucCRCLServerJFrame extends javax.swing.JFrame {

    public JCheckBox getjCheckBoxLogAllCommands() {
        return jCheckBoxLogAllCommands;
    }

    private javax.swing.Timer timer = null;

    /**
     * Creates new form FanucCRCLServerJFrame
     */
    public FanucCRCLServerJFrame() {
        initComponents();
        timer = new Timer(200, e -> updateDisplay());
        timer.start();
    }

    IVar varToWatch = null;

    public JTable getjTableCartesianLimits() {
        return jTableCartesianLimits;
    }

    public JTable getjTableJointLimits() {
        return jTableJointLimits;
    }

    public JTextField getjTextFieldLimitSafetyBumper() {
        return jTextFieldLimitSafetyBumper;
    }

    private IVar overrideVar;

    /**
     * Get the value of overrideVar
     *
     * @return the value of overrideVar
     */
    public IVar getOverrideVar() {
        return overrideVar;
    }

    /**
     * Set the value of overrideVar
     *
     * @param overrideVar new value of overrideVar
     */
    public void setOverrideVar(IVar overrideVar) {
        this.overrideVar = overrideVar;
    }

    public void updateDisplay() {
        try {
            if (null == robot) {
                return;
            }
            if (null != varToWatch) {
                varToWatch.refresh();
                jTextFieldSysVarValue.setText(varToWatch.value().toString());
            }
            DefaultTableModel dtm = (DefaultTableModel) this.jTableTasks.getModel();
            dtm.setRowCount(0);
            ITasks tasks = robot.tasks();
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
                        jTextAreaErrors.append(e.toString() + "\n");
                        continue;
                    }
                    if (!jCheckBoxShowAborted.isSelected() && tskStatus == FRETaskStatusConstants.frStatusAborted) {
                        continue;
                    }
                    if (null == tskProgName && null == pType && null == tskStatus) {
                        continue;
                    }
                    dtm.addRow(new Object[]{
                        tskProgName == null ? "" : tskProgName,
                        pType == null ? "" : pType.toString(),
                        tskStatus == null ? "" : tskStatus.toString()});
                }
            }
            ICurGroupPosition curPos = robot.curPosition().group((short) 1, FRECurPositionConstants.frWorldDisplayType);
            IXyzWpr curXyzWpr = curPos.formats(FRETypeCodeConstants.frXyzWpr).queryInterface(IXyzWpr.class);
            IConfig conf = curXyzWpr.config();
            if (!this.jCheckBoxEditCartesianLimits.isSelected()) {
                this.jTextFieldCartesianConfig.setText(conf.text());
                DefaultTableModel dtmCartPos = (DefaultTableModel) this.jTableCartesianLimits.getModel();
                dtmCartPos.setValueAt(curXyzWpr.x(), 0, 2);
                dtmCartPos.setValueAt(curXyzWpr.y(), 1, 2);
                dtmCartPos.setValueAt(curXyzWpr.z(), 2, 2);
            }
            if (!this.jCheckBoxEditJointLimits.isSelected()) {
                curPos = robot.curPosition().group((short) 1, FRECurPositionConstants.frJointDisplayType);
                IJoint curJoints = curPos.formats(FRETypeCodeConstants.frJoint).queryInterface(IJoint.class);
                DefaultTableModel dtmJointPos = (DefaultTableModel) this.jTableJointLimits.getModel();
                for (short i = 0; i < curJoints.count(); i++) {
                    dtmJointPos.setValueAt(curJoints.item((short) (i + 1)), i, 2);
                }
            }
            if (null != overrideVar) {
                overrideVar.refresh();
                jSliderOverride.setValue((Integer) overrideVar.value());
            }
        } catch (ComException e) {
            if (e.getMessage().contains("8004000e compobj.dll is too old for the ole2.dll initialized : Object is no longer valid.")) {
                this.updateWatchVar(robot);
            }
        }
    }

    public JSlider getjSliderOverride() {
        return jSliderOverride;
    }

    public JSlider getjSliderMaxOverride() {
        return jSliderMaxOverride;
    }

    public JTextArea getjTextAreaErrors() {
        return jTextAreaErrors;
    }

    public JMenuItem getjMenuItemReconnectRobot() {
        return jMenuItemReconnectRobot;
    }

    public JMenuItem getjMenuItemResetAlarms() {
        return jMenuItemResetAlarms;
    }

    private void includeProgram(CRCLProgramType crclProg, ITPProgram prog, Map<Integer, PmXyzWpr> posMap) {
        BigInteger cmdId = BigInteger.ONE;
        System.out.println("includeProgram called for " + prog.name());
        if (crclProg.getMiddleCommand().size() > 0) {
            cmdId = crclProg.getMiddleCommand().get(crclProg.getMiddleCommand().size() - 1).getCommandID();
        }
        for (Com4jObject lineObj : prog.lines()) {
            try {
                if (ConvertProgramLine(lineObj, cmdId, crclProg, prog, posMap)) {
                    continue;
                }
                if (crclProg.getMiddleCommand().size() > 0) {
                    cmdId = crclProg.getMiddleCommand().get(crclProg.getMiddleCommand().size() - 1).getCommandID();
                }
                cmdId = cmdId.add(BigInteger.ONE);
            } catch (PmException ex) {
                Logger.getLogger(FanucCRCLServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("includeProgram returning for " + prog.name());
    }

    public static class PmXyzWpr {

        public final PmCartesian cart;
        public final PmRpy rpy;

        public PmXyzWpr() {
            cart = new PmCartesian();
            rpy = new PmRpy();
        }

        public PmXyzWpr(double x, double y, double z, double w, double p, double r) {
            rpy = new PmRpy(r, p, w);
            cart = new PmCartesian(x, y, z);
        }

        public PmXyzWpr(IXyzWpr xyzwpr) {
            rpy = new PmRpy(Math.toRadians(xyzwpr.w()), Math.toRadians(xyzwpr.p()), Math.toRadians(xyzwpr.r()));
            cart = new PmCartesian(xyzwpr.x(), xyzwpr.y(), xyzwpr.z());
        }

        public void setAll(PmXyzWpr other) {
            cart.x = other.cart.x;
            cart.y = other.cart.y;
            cart.z = other.cart.z;
            rpy.r = other.rpy.r;
            rpy.p = other.rpy.p;
            rpy.y = other.rpy.y;
        }

        public void incOne(int index, double value) {
            switch (index) {
                case 1:
                    cart.x += value;
                    break;
                case 2:
                    cart.y += value;
                    break;
                case 3:
                    cart.z += value;
                    break;
                case 4:
                    rpy.y += value;
                    break;
                case 5:
                    rpy.p += value;
                    break;
                case 6:
                    rpy.r += value;
                    break;

                default:
                    throw new IllegalArgumentException("index must be between 1 and 6, index=" + index);
            }
        }

        public void setOne(int index, double value) {
            switch (index) {
                case 1:
                    cart.x = value;
                    break;
                case 2:
                    cart.y = value;
                    break;
                case 3:
                    cart.z = value;
                    break;
                case 4:
                    rpy.y = value;
                    break;
                case 5:
                    rpy.p = value;
                    break;
                case 6:
                    rpy.r = value;
                    break;

                default:
                    throw new IllegalArgumentException("index must be between 1 and 6, index=" + index);
            }
        }

        @Override
        public String toString() {
            return "PmXyzWpr{" + "cart=" + cart + ", rpy=" + rpy + '}';
        }

    }

    public void ConvertProgram(ITPProgram prog) {
        try {
            CRCLProgramType crclProg = new CRCLProgramType();
            InitCanonType initCmd = new InitCanonType();
            BigInteger cmdId = BigInteger.ONE;
            initCmd.setCommandID(cmdId);
            crclProg.setInitCanon(initCmd);
            Map<Integer, PmXyzWpr> posMap = new TreeMap<>();
            for (Com4jObject posObj : robot.regPositions()) {
                ISysPosition pos = posObj.queryInterface(ISysPosition.class);
                Optional.ofNullable(pos)
                        .filter(ISysPosition::isInitialized)
                        .map(p -> p.group((short) 1))
                        .map(p -> p.formats(FRETypeCodeConstants.frXyzWpr))
                        .map(p -> p.queryInterface(IXyzWpr.class))
                        .map(PmXyzWpr::new)
                        .ifPresent(p -> posMap.put(pos.id(), p));
            }
            System.out.println("posMap = " + posMap);
            SetLengthUnitsType sluCmd = new SetLengthUnitsType();
            sluCmd.setCommandID(cmdId);
            sluCmd.setUnitName(LengthUnitEnumType.MILLIMETER);
            cmdId = cmdId.add(BigInteger.ONE);
            crclProg.getMiddleCommand().add(sluCmd);
            SetEndPoseToleranceType sepCmd = new SetEndPoseToleranceType();

            sepCmd.setCommandID(cmdId);
            PoseToleranceType poseTol = new PoseToleranceType();
            final BigDecimal ZERO_POINT_ONE = BigDecimal.valueOf(0.1);
            poseTol.setXPointTolerance(ZERO_POINT_ONE);
            poseTol.setYPointTolerance(ZERO_POINT_ONE);
            poseTol.setZPointTolerance(ZERO_POINT_ONE);
            final BigDecimal ZERO_POINT_ONE_DEG = BigDecimal.valueOf(0.1 * Math.PI / 180.0);
            poseTol.setXAxisTolerance(ZERO_POINT_ONE_DEG);
            poseTol.setZAxisTolerance(ZERO_POINT_ONE_DEG);
            sepCmd.setTolerance(poseTol);
            cmdId = cmdId.add(BigInteger.ONE);
            crclProg.getMiddleCommand().add(sepCmd);
            for (Com4jObject lineObj : prog.lines()) {
                try {
                    if (ConvertProgramLine(lineObj, cmdId, crclProg, prog, posMap)) {
                        continue;
                    }
                    if (crclProg.getMiddleCommand().size() > 0) {
                        cmdId = crclProg.getMiddleCommand().get(crclProg.getMiddleCommand().size() - 1).getCommandID();
                    }
                    cmdId = cmdId.add(BigInteger.ONE);
                } catch (PmException ex) {
                    Logger.getLogger(FanucCRCLServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            EndCanonType end = new EndCanonType();
            if (crclProg.getMiddleCommand().size() > 0) {
                cmdId = crclProg.getMiddleCommand().get(crclProg.getMiddleCommand().size() - 1).getCommandID();
            }
            cmdId = cmdId.add(BigInteger.ONE);
            end.setCommandID(cmdId);
            crclProg.setEndCanon(end);
            String crclProgText = new CRCLSocket().programToPrettyString(crclProg, true);
            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(new File(System.getProperty("user.home"), prog.name() + ".xml"));
            if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(this)) {
                File f = chooser.getSelectedFile();
                Files.write(f.toPath(), crclProgText.getBytes());
            }
        } catch (CRCLException | IOException ex) {
            this.jTextAreaErrors.append(ex.toString());
            JOptionPane.showMessageDialog(this, ex);
            Logger.getLogger(FanucCRCLServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    private IRobot2 robot = null;

    /**
     * Get the value of robot
     *
     * @return the value of robot
     */
    public IRobot2 getRobot() {
        return robot;
    }

    /**
     * Set the value of robot
     *
     * @param robot new value of robot
     */
    public void setRobot(IRobot2 robot) {
        this.robot = robot;
        updateWatchVar(robot);
    }

    public void updateWatchVar(IRobot2 robot1) {
        try {
            if (null != robot1) {
                String varName = this.jTextFieldSysVarName.getText();
                System.out.println("varName = " + varName);
                IVars sysVars = robot1.sysVariables();
                System.out.println("sysVars = " + sysVars);
                varToWatch = Optional.ofNullable(sysVars).map(ivars -> ivars.item(varName, null)).map(o -> o.queryInterface(IVar.class)).orElse(null);
                System.out.println("varToWatch = " + varToWatch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
            if (this.preferRobotNeighborhood && !this.jRadioButtonUseRobotNeighborhood.isSelected()) {
                this.jRadioButtonUseDirectIP.setSelected(false);
                this.jRadioButtonUseRobotNeighborhood.setSelected(true);
            }
            if (!this.preferRobotNeighborhood && this.jRadioButtonUseRobotNeighborhood.isSelected()) {
                this.jRadioButtonUseDirectIP.setSelected(true);
                this.jRadioButtonUseRobotNeighborhood.setSelected(false);
            }
        }
    }

    public JRadioButton getjRadioButtonUseDirectIP() {
        return jRadioButtonUseDirectIP;
    }

    public JRadioButton getjRadioButtonUseRobotNeighborhood() {
        return jRadioButtonUseRobotNeighborhood;
    }

    public JTextField getjTextFieldHostName() {
        return jTextFieldHostName;
    }

    public JTextField getjTextFieldRobotNeighborhoodPath() {
        return jTextFieldRobotNeighborhoodPath;
    }

    public boolean ConvertProgramLine(Com4jObject lineObj, BigInteger cmdId, CRCLProgramType crclProg, ITPProgram prog, Map<Integer, PmXyzWpr> posMap) throws PmException {
        ITPSimpleLine l = lineObj.queryInterface(ITPSimpleLine.class);
        if (null == l) {
            return true;
        }
        String lineTxt = l.text();
        if (lineTxt == null) {
            return true;
        }
        System.out.println("lineTxt = " + lineTxt);
        lineTxt = lineTxt.trim();
        if (lineTxt.endsWith(";")) {
            lineTxt = lineTxt.substring(0, lineTxt.length() - 1).trim();
        }
        if (lineTxt.trim().startsWith("!")) {
            return true;
        }
        String cmdName = prog.name() + " " + l.number() + " " + cmdId + " " + l.text();
        cmdName = cmdName.trim().replace('(', '_').replace(')', '_').replaceAll("[ \\[\\],.:;/\\\\]+", "_");
        System.out.println("cmdName = " + cmdName);
        int eqIndex = lineTxt.indexOf('=');
        if (eqIndex > 0 && eqIndex < lineTxt.length() - 1) {
            String lhs = lineTxt.substring(0, eqIndex).trim();
            System.out.println("lhs = " + lhs);
            String rhs = lineTxt.substring(eqIndex + 1).trim();
            if (rhs.endsWith(";")) {
                rhs = rhs.substring(0, rhs.length() - 1).trim();
            }
            System.out.println("rhs = " + rhs);
            if (lhs.startsWith("PR[") && lhs.endsWith("]")) {
                int cindex = lhs.indexOf(',');
                Integer lhs_id = 0;
                if (cindex > 0) {
                    lhs_id = Integer.valueOf(lhs.substring(3, cindex));
                    System.out.println("lhs_id = " + lhs_id);
                    System.out.println("old posMap.get(lhs_id) = " + posMap.get(lhs_id));
                    String el_index_string = lhs.substring(cindex + 1, lhs.length() - 1);
                    System.out.println("el_index_string = " + el_index_string);
                    int el_index = Integer.valueOf(el_index_string);
                    if (rhs.startsWith("(") && rhs.endsWith(")")) {
                        double value = Double.valueOf(rhs.substring(1, rhs.length() - 1));
                        posMap.get(lhs_id).setOne(el_index, value);
                        System.out.println("el_index = " + el_index);
                        System.out.println("value = " + value);
                        System.out.println("new posMap.get(lhs_id) = " + posMap.get(lhs_id));
                    }
                } else {
                    lhs_id = Integer.valueOf(lhs.substring(3, lhs.length() - 1));
                    System.out.println("lhs_id = " + lhs_id);
                    System.out.println("posMap.get(lhs_id) = " + posMap.get(lhs_id));
                    if (rhs.startsWith("PR[") && rhs.endsWith("]")) {
                        Integer rhs_id = Integer.valueOf(rhs.substring(3, rhs.length() - 1));
                        System.out.println("rhs_id = " + rhs_id);
                        System.out.println("posMap.get(rhs_id) = " + posMap.get(rhs_id));
                        posMap.get(lhs_id).setAll(posMap.get(rhs_id));
                        System.out.println("new posMap.get(lhs_id) = " + posMap.get(lhs_id));
                    }
                }
            }
            return true;
        } else {
            String parts[] = lineTxt.split("[ \t\r\n]+");
            System.out.println("parts = " + Arrays.toString(parts));
            if (parts.length > 1 && parts[0].equalsIgnoreCase("call")) {
                String progToCallName = parts[1];
                System.out.println("progToCallName = " + progToCallName);
                if (progToCallName.equalsIgnoreCase("GRIPPER_OPEN") || progToCallName.equalsIgnoreCase("OPEN_GRIPPER")) {
                    SetEndEffectorType seet = new SetEndEffectorType();
                    seet.setSetting(BigDecimal.ONE);
                    seet.setCommandID(cmdId);
                    seet.setName(cmdName);
                    crclProg.getMiddleCommand().add(seet);
                } else if (progToCallName.equalsIgnoreCase("GRIPPER_CLOSE") || progToCallName.equalsIgnoreCase("CLOSE_GRIPPER")) {
                    SetEndEffectorType seet = new SetEndEffectorType();
                    seet.setSetting(BigDecimal.ZERO);
                    seet.setCommandID(cmdId);
                    seet.setName(cmdName);
                    crclProg.getMiddleCommand().add(seet);
                } else {
                    programs.stream().filter(p -> p.name().equals(progToCallName)).findAny()
                            .ifPresent(pToCall -> includeProgram(crclProg, pToCall, posMap));

                }
            } else if (parts.length > 1 && parts[0].equalsIgnoreCase("WAIT")) {
                DwellType dwellCmd = new DwellType();
                dwellCmd.setCommandID(cmdId);
                String timeString = parts[1];
                double timeScale = 1.0;
                if (timeString.endsWith("(sec)")) {
                    timeString = timeString.substring(0, timeString.indexOf('(')).trim();
                }
                dwellCmd.setDwellTime(BigDecimal.valueOf(Double.valueOf(timeString) * timeScale));
                dwellCmd.setName(cmdName);
                crclProg.getMiddleCommand().add(dwellCmd);
            } else if (parts.length > 1 && (parts[0].equalsIgnoreCase("J") || parts[0].equalsIgnoreCase("L"))) {
                if (parts[1].startsWith("PR[") && parts[1].endsWith("]")) {
                    Integer id = Integer.valueOf(parts[1].substring(3, parts[1].length() - 1));
                    PmXyzWpr posxyzwpr = posMap.get(id);
                    if (null != posxyzwpr) {
//                        PmCartesian cart = new PmCartesian(posxyzwpr.x(), posxyzwpr.y(), posxyzwpr.z());
//                        PmRpy rpy = new PmRpy(Math.toRadians(posxyzwpr.w()), Math.toRadians(posxyzwpr.p()), Math.toRadians(posxyzwpr.r()));
                        PoseType pose = CRCLPosemath.toPoseType(posxyzwpr.cart, Posemath.toRot(posxyzwpr.rpy));
                        if (parts[2].endsWith("mm/sec")) {
                            SetTransSpeedType setSpeedCmd = new SetTransSpeedType();
                            TransSpeedAbsoluteType transSpeed = new TransSpeedAbsoluteType();
                            String spdString = parts[2].substring(0, parts[2].length() - "mm/sec".length()).trim();
                            transSpeed.setSetting(BigDecimal.valueOf(Double.valueOf(spdString)));
                            setSpeedCmd.setTransSpeed(transSpeed);
                            setSpeedCmd.setCommandID(cmdId);
                            crclProg.getMiddleCommand().add(setSpeedCmd);
                            cmdId = cmdId.add(BigInteger.ONE);
                        }
                        System.out.println("parts = " + Arrays.toString(parts));
                        MoveToType mtt = new MoveToType();
                        mtt.setEndPosition(pose);
                        mtt.setCommandID(cmdId);
                        mtt.setName(cmdName);
                        crclProg.getMiddleCommand().add(mtt);
                        cmdId = cmdId.add(BigInteger.ONE);
//                        DwellType dwellCmd = new DwellType();
//                        dwellCmd.setCommandID(cmdId);
//                        dwellCmd.setDwellTime(BigDecimal.valueOf(0.25));
//                        crclProg.getMiddleCommand().add(dwellCmd);
                    }
                }
            }
        }
        return false;
    }

    private List<ITPProgram> programs;

    public void runReconnectAction(ActionEvent e) {
        for (ActionListener al : jMenuItemReconnectRobot.getActionListeners()) {
            al.actionPerformed(e);
        }
    }

    public void setPrograms(List<ITPProgram> _programs) {
        this.programs = _programs;
        this.jMenuRunProgram.removeAll();
        this.jMenuConvertProgram.removeAll();
        if (null != programs) {
            synchronized (programs) {
                Collections.sort(programs, Comparator.comparing(ITPProgram::name));
                for (ITPProgram p : programs) {
                    JMenuItem jmi = new JMenuItem(p.name());
                    jmi.addActionListener(e -> {
                        runReconnectAction(e);
                        if (JOptionPane.showConfirmDialog(FanucCRCLServerJFrame.this, "Run " + p.name()) == JOptionPane.YES_OPTION) {
                            p.run(FREStepTypeConstants.frStepNone, 1, FREExecuteConstants.frExecuteFwd);
                            runReconnectAction(e);
                        }
                    });
                    this.jMenuRunProgram.add(jmi);
                    jmi = new JMenuItem(p.name());
                    jmi.addActionListener(e -> {
                        ConvertProgram(p);
                    });
                    this.jMenuConvertProgram.add(jmi);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupConnectType = new javax.swing.ButtonGroup();
        jSliderOverride = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaErrors = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSliderMaxOverride = new javax.swing.JSlider();
        jTextFieldHostName = new javax.swing.JTextField();
        jRadioButtonUseDirectIP = new javax.swing.JRadioButton();
        jRadioButtonUseRobotNeighborhood = new javax.swing.JRadioButton();
        jTextFieldRobotNeighborhoodPath = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldSysVarName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldSysVarValue = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldLimitSafetyBumper = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCartesianLimits = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableJointLimits = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableTasks = new javax.swing.JTable();
        jButtonAbortAllTasks = new javax.swing.JButton();
        jCheckBoxShowAborted = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldCartesianConfig = new javax.swing.JTextField();
        jCheckBoxLogAllCommands = new javax.swing.JCheckBox();
        jCheckBoxEditJointLimits = new javax.swing.JCheckBox();
        jCheckBoxEditCartesianLimits = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemResetAlarms = new javax.swing.JMenuItem();
        jMenuItemReconnectRobot = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuRunProgram = new javax.swing.JMenu();
        jMenuConvertProgram = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fanuc CRCL Server");

        jSliderOverride.setMajorTickSpacing(10);
        jSliderOverride.setMinorTickSpacing(10);
        jSliderOverride.setPaintLabels(true);
        jSliderOverride.setPaintTicks(true);
        jSliderOverride.setPaintTrack(false);
        jSliderOverride.setValue(100);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Max Override:");

        jTextAreaErrors.setColumns(20);
        jTextAreaErrors.setRows(5);
        jScrollPane1.setViewportView(jTextAreaErrors);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Errors:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Override:");

        jSliderMaxOverride.setMajorTickSpacing(10);
        jSliderMaxOverride.setMinorTickSpacing(10);
        jSliderMaxOverride.setPaintLabels(true);
        jSliderMaxOverride.setPaintTicks(true);
        jSliderMaxOverride.setPaintTrack(false);
        jSliderMaxOverride.setValue(100);

        jTextFieldHostName.setText("129.6.78.111");

        buttonGroupConnectType.add(jRadioButtonUseDirectIP);
        jRadioButtonUseDirectIP.setText("Use Direct Hostname/IP Address:");

        buttonGroupConnectType.add(jRadioButtonUseRobotNeighborhood);
        jRadioButtonUseRobotNeighborhood.setText("Use Robot Neighborhood Path:");

        jTextFieldRobotNeighborhoodPath.setText("\\AgilityLabLRMate200iD");

        jLabel4.setText("System Var Name: ");

        jTextFieldSysVarName.setText("$MCR.$GENOVERRIDE");
        jTextFieldSysVarName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSysVarNameActionPerformed(evt);
            }
        });

        jLabel5.setText("System Var Value :");

        jLabel6.setText("Cart.  Limit Safety Buffer");

        jTextFieldLimitSafetyBumper.setText("120.0");

        jLabel7.setText("Cartesian Limits:");

        jTableCartesianLimits.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"X",  new Double(-1000.0),  new Double(0.0),  new Double(1000.0)},
                {"Y",  new Double(-1000.0),  new Double(0.0),  new Double(1000.0)},
                {"Z",  new Double(-1000.0), null,  new Double(1000.0)}
            },
            new String [] {
                "Axis", "Minimum", "Current", "Maximum"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableCartesianLimits);

        jTableJointLimits.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1),  new Double(-1000.0),  new Double(0.0),  new Double(1000.0)},
                { new Integer(2),  new Double(-1000.0),  new Double(0.0),  new Double(1000.0)},
                { new Integer(3),  new Double(-1000.0),  new Double(0.0),  new Double(1000.0)},
                { new Integer(4),  new Double(-1000.0),  new Double(0.0),  new Double(1000.0)},
                { new Integer(5),  new Double(-1000.0),  new Double(0.0),  new Double(1000.0)},
                { new Integer(6),  new Double(-1000.0),  new Double(0.0),  new Double(1000.0)}
            },
            new String [] {
                "Joint", "Minimum", "Current", "Maximum"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableJointLimits);

        jLabel8.setText("Joint Limits");

        jLabel9.setText("Tasks");

        jTableTasks.setAutoCreateRowSorter(true);
        jTableTasks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Task", "Type", "State"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTableTasks);

        jButtonAbortAllTasks.setText("Abort All Tasks");
        jButtonAbortAllTasks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbortAllTasksActionPerformed(evt);
            }
        });

        jCheckBoxShowAborted.setText("Show Aborted");

        jLabel10.setText("Joint Configuration:");

        jTextFieldCartesianConfig.setEditable(false);

        jCheckBoxLogAllCommands.setText("Log All Commands");

        jCheckBoxEditJointLimits.setText("Edit Joint Limits");

        jCheckBoxEditCartesianLimits.setText("Edit Cartesian Limits");
        jCheckBoxEditCartesianLimits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxEditCartesianLimitsActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItemResetAlarms.setText("Reset Alarms");
        jMenu1.add(jMenuItemResetAlarms);

        jMenuItemReconnectRobot.setText("Reconnect Robot");
        jMenu1.add(jMenuItemReconnectRobot);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenuRunProgram.setText("Run Program");
        jMenuBar1.add(jMenuRunProgram);

        jMenuConvertProgram.setText("Convert Program");
        jMenuBar1.add(jMenuConvertProgram);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldLimitSafetyBumper))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldSysVarName, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldSysVarValue, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBoxShowAborted)
                                    .addComponent(jButtonAbortAllTasks)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBoxLogAllCommands))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jCheckBoxEditCartesianLimits)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSliderMaxOverride, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jSliderOverride, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButtonUseDirectIP)
                            .addComponent(jRadioButtonUseRobotNeighborhood))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldRobotNeighborhoodPath, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                            .addComponent(jTextFieldHostName)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jCheckBoxEditJointLimits)
                                .addComponent(jTextFieldCartesianConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jSliderMaxOverride, jSliderOverride});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldHostName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonUseDirectIP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRobotNeighborhoodPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonUseRobotNeighborhood))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldSysVarName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldSysVarValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldLimitSafetyBumper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAbortAllTasks)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldCartesianConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jCheckBoxShowAborted)
                    .addComponent(jCheckBoxEditJointLimits)
                    .addComponent(jCheckBoxEditCartesianLimits))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSliderOverride, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSliderMaxOverride, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jCheckBoxLogAllCommands))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldSysVarNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSysVarNameActionPerformed
        this.updateWatchVar(robot);
    }//GEN-LAST:event_jTextFieldSysVarNameActionPerformed

    private void jButtonAbortAllTasksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbortAllTasksActionPerformed
        robot.tasks().abortAll(true);
    }//GEN-LAST:event_jButtonAbortAllTasksActionPerformed

    private void jCheckBoxEditCartesianLimitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxEditCartesianLimitsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxEditCartesianLimitsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FanucCRCLServerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FanucCRCLServerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FanucCRCLServerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FanucCRCLServerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FanucCRCLServerJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupConnectType;
    private javax.swing.JButton jButtonAbortAllTasks;
    private javax.swing.JCheckBox jCheckBoxEditCartesianLimits;
    private javax.swing.JCheckBox jCheckBoxEditJointLimits;
    private javax.swing.JCheckBox jCheckBoxLogAllCommands;
    private javax.swing.JCheckBox jCheckBoxShowAborted;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuConvertProgram;
    private javax.swing.JMenuItem jMenuItemReconnectRobot;
    private javax.swing.JMenuItem jMenuItemResetAlarms;
    private javax.swing.JMenu jMenuRunProgram;
    private javax.swing.JRadioButton jRadioButtonUseDirectIP;
    private javax.swing.JRadioButton jRadioButtonUseRobotNeighborhood;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSlider jSliderMaxOverride;
    private javax.swing.JSlider jSliderOverride;
    private javax.swing.JTable jTableCartesianLimits;
    private javax.swing.JTable jTableJointLimits;
    private javax.swing.JTable jTableTasks;
    private javax.swing.JTextArea jTextAreaErrors;
    private javax.swing.JTextField jTextFieldCartesianConfig;
    private javax.swing.JTextField jTextFieldHostName;
    private javax.swing.JTextField jTextFieldLimitSafetyBumper;
    private javax.swing.JTextField jTextFieldRobotNeighborhoodPath;
    private javax.swing.JTextField jTextFieldSysVarName;
    private javax.swing.JTextField jTextFieldSysVarValue;
    // End of variables declaration//GEN-END:variables
}
