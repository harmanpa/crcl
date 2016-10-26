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
package crcl.ui.server;

import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStateEnumType;
import crcl.base.GetStatusType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.LengthUnitEnumType;
import crcl.base.PoseType;

import crcl.ui.misc.MultiLineStringJPanel;
import crcl.ui.misc.ObjTableJPanel;
import static crcl.ui.server.SimServerJFrame.LOGGER;
import crcl.utils.CRCLException;
import crcl.utils.CRCLSocket;
import crcl.utils.SimRobotEnum;
import crcl.utils.outer.interfaces.SimServerMenuOuter;
import crcl.utils.outer.interfaces.SimServerOuter;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class SimServerJPanel extends javax.swing.JPanel implements SimServerOuter {

    /**
     * Creates new form SimServerJPanel
     */
    public SimServerJPanel() throws ParserConfigurationException {
        initComponents();
        this.inner = new SimServerInner(this);
        SimRobotEnum defaultRobotType
                = DEFAULT_ROBOTTYPE;
//        this.inner = new SimServerInner(this);
        java.awt.EventQueue.invokeLater(() -> this.updatePanelsPrivate());
        this.lengthUnitComboBox.setSelectedItem(LengthUnitEnumType.MILLIMETER);
        for (SimRobotEnum srType : SimRobotEnum.values()) {
            this.setRobotType(srType);
            inner.setLengthUnit(LengthUnitEnumType.MILLIMETER);
        }
        this.jComboBoxRobotType.setModel(new DefaultComboBoxModel<>(SimRobotEnum.values()));
        this.jComboBoxRobotType.setSelectedItem(defaultRobotType);
        this.setRobotType(defaultRobotType);
        this.setStatSchema(CRCLSocket.readStatSchemaFiles(SimServerJPanel.statSchemasFile));
        this.setCmdSchema(CRCLSocket.readCmdSchemaFiles(SimServerJPanel.cmdSchemasFile));
        String portPropertyString = System.getProperty("crcl4java.port");
        if (null != portPropertyString) {
            inner.setPort(Integer.parseInt(portPropertyString));
            this.jTextFieldPort.setText(portPropertyString);
        } else {
            this.jTextFieldPort.setText(Integer.toString(inner.getPort()));
        }
//        inner.restartServer();
        try {
            String imageLogDirProp = System.getProperty("crcl4java.simserver.imagelogdir");
            File imageLogDir = null;
            if (imageLogDirProp != null && imageLogDirProp.length() > 0) {
                imageLogDir = new File(imageLogDirProp);
                if (!imageLogDir.exists()
                        || !imageLogDir.isDirectory()
                        || !imageLogDir.canWrite()) {
                    Logger.getLogger(SimServerJPanel.class.getName()).log(Level.SEVERE, "Can''t use tempDir from property temp.dir {0}", imageLogDir);
                    imageLogDir = null;
                }
            }
            if (null == imageLogDir) {
                File testTempFile = File.createTempFile("test", "suffix");
                imageLogDir = testTempFile.getParentFile();
                Files.delete(testTempFile.toPath());
            }
            String imageLogDirPath = imageLogDir.getCanonicalPath();
            this.overHeadJPanel1.setImageLogDir(imageLogDirPath);
            this.sideViewJPanel1.setImageLogDir(imageLogDirPath);
        } catch (IOException ex) {
            Logger.getLogger(SimServerJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean logImages = LOG_IMAGES_DEFAULT;
        this.overHeadJPanel1.setLogImages(logImages);
        this.sideViewJPanel1.setLogImages(logImages);
        this.jCheckBoxTeleportToGoals.setSelected(inner.isTeleportToGoals());
    }

    public void restartServer() {
        inner.restartServer();
    }

    static public boolean LOG_IMAGES_DEFAULT = false;

    String tempDir = "/tmp";

    private final static SimRobotEnum DEFAULT_ROBOTTYPE = SimRobotEnum.valueOf(System.getProperty("crcl4java.simserver.robottype", SimRobotEnum.SIMPLE.toString()));

    transient private CRCLSocket gripperSocket = null;
    transient private Thread gripperReadThread = null;
    private int gripperPort = 4005;
    private String gripperHost = "localhost";
    private boolean sendGripperStatusRequests = true;

    private void setRobotType(SimRobotEnum robotType) {
        this.inner.setRobotType(robotType);
        this.overHeadJPanel1.setJointvals(inner.getJointPositions());
        this.sideViewJPanel1.setJointvals(inner.getJointPositions());
        this.overHeadJPanel1.setSeglengths(inner.getSeglengths());
        this.sideViewJPanel1.setSeglengths(inner.getSeglengths());
        this.overHeadJPanel1.setRobotType(robotType);
        this.sideViewJPanel1.setRobotType(robotType);
        this.updatePanels(true);
    }

    public static final Logger LOGGER = Logger.getLogger(SimServerJPanel.class.getName());

    private void closeGripperSocket() {
        if (null != gripperSocket) {
            try {
                gripperSocket.close();
                gripperSocket = null;
                this.inner.setGripperSocket(gripperSocket);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        if (null != gripperReadThread) {
            try {
                if (gripperReadThread.isAlive()) {
                    Thread.dumpStack();
                    System.err.println("Interrupting gripperReadThread = " + gripperReadThread);
                    System.out.println("Interrupting gripperReadThread = " + gripperReadThread);
                    System.out.println("gripperReadThread.getStackTrace() = " + Arrays.toString(gripperReadThread.getStackTrace()));
                    gripperReadThread.interrupt();
                    gripperReadThread.join(100);
                }
                gripperReadThread = null;
            } catch (InterruptedException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
    }

    private CRCLStatusType getStatus() {
        return inner.getStatus();
    }

    private SimServerMenuOuter menuOuter;

    /**
     * Get the value of menuOuter
     *
     * @return the value of menuOuter
     */
    public SimServerMenuOuter getMenuOuter() {
        return menuOuter;
    }

    /**
     * Set the value of menuOuter
     *
     * @param menuOuter new value of menuOuter
     */
    public void setMenuOuter(SimServerMenuOuter menuOuter) {
        this.menuOuter = menuOuter;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        overHeadJPanel1 = new crcl.ui.server.OverHeadJPanel();
        jPanel2 = new javax.swing.JPanel();
        sideViewJPanel1 = new crcl.ui.server.SideViewJPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaErrors = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldPort = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldCycleTime = new javax.swing.JTextField();
        jButtonReset = new javax.swing.JButton();
        jCheckBoxInitialized = new javax.swing.JCheckBox();
        jCheckBoxSendStatusWithoutRequest = new javax.swing.JCheckBox();
        jButtonRestartServer = new javax.swing.JButton();
        jComboBoxRobotType = new javax.swing.JComboBox<>();
        jCheckBoxTeleportToGoals = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldConnectedClients = new javax.swing.JTextField();
        jTextFieldCycleCount = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldNumWaypoints = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldCurWaypoint = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldEndEffector = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldCurrentCommandType = new javax.swing.JTextField();
        lengthUnitComboBox = new crcl.ui.misc.LengthUnitComboBox();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Overhead View"));
        jPanel1.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout overHeadJPanel1Layout = new javax.swing.GroupLayout(overHeadJPanel1);
        overHeadJPanel1.setLayout(overHeadJPanel1Layout);
        overHeadJPanel1Layout.setHorizontalGroup(
            overHeadJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        overHeadJPanel1Layout.setVerticalGroup(
            overHeadJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        jPanel1.add(overHeadJPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Side View"));
        jPanel2.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout sideViewJPanel1Layout = new javax.swing.GroupLayout(sideViewJPanel1);
        sideViewJPanel1.setLayout(sideViewJPanel1Layout);
        sideViewJPanel1Layout.setHorizontalGroup(
            sideViewJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        sideViewJPanel1Layout.setVerticalGroup(
            sideViewJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );

        jPanel2.add(sideViewJPanel1, java.awt.BorderLayout.CENTER);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Errors and Messages"));

        jTextAreaErrors.setColumns(20);
        jTextAreaErrors.setLineWrap(true);
        jTextAreaErrors.setRows(5);
        jTextAreaErrors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAreaErrorsMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextAreaErrorsMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextAreaErrorsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTextAreaErrors);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Setup"));

        jLabel1.setText("Port: ");

        jTextFieldPort.setText("64444");
        jTextFieldPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPortActionPerformed(evt);
            }
        });

        jLabel3.setText("Cycle Time (ms): ");

        jTextFieldCycleTime.setText("100");
        jTextFieldCycleTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCycleTimeActionPerformed(evt);
            }
        });

        jButtonReset.setText("Reset");
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        jCheckBoxInitialized.setText("Initialized");
        jCheckBoxInitialized.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxInitializedActionPerformed(evt);
            }
        });

        jCheckBoxSendStatusWithoutRequest.setText("Send Status Without Requests");

        jButtonRestartServer.setText("Restart Server");
        jButtonRestartServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRestartServerActionPerformed(evt);
            }
        });

        jComboBoxRobotType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxRobotTypeActionPerformed(evt);
            }
        });

        jCheckBoxTeleportToGoals.setText("Teleport to Goals");
        jCheckBoxTeleportToGoals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTeleportToGoalsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jCheckBoxSendStatusWithoutRequest))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButtonReset)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonRestartServer)))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jCheckBoxInitialized)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(92, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBoxRobotType, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldCycleTime)
                            .addComponent(jTextFieldPort, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jCheckBoxTeleportToGoals)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldCycleTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonReset)
                    .addComponent(jButtonRestartServer)
                    .addComponent(jComboBoxRobotType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxInitialized)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxSendStatusWithoutRequest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBoxTeleportToGoals))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Status"));

        jLabel2.setText("Connected Clients: ");

        jLabel4.setText("Cycle Count:");

        jTextFieldConnectedClients.setEditable(false);
        jTextFieldConnectedClients.setText("0");

        jTextFieldCycleCount.setEditable(false);
        jTextFieldCycleCount.setText("0");

        jLabel5.setText("Number of Waypoints: ");

        jTextFieldNumWaypoints.setEditable(false);
        jTextFieldNumWaypoints.setText("0");

        jLabel6.setText("Current Waypoint:");

        jTextFieldCurWaypoint.setEditable(false);
        jTextFieldCurWaypoint.setText("0");

        jLabel7.setText("Length Units: ");

        jLabel8.setText("End Effector: ");

        jLabel9.setText("Current Commant Type:");

        lengthUnitComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lengthUnitComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldEndEffector, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCurrentCommandType, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCurWaypoint, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNumWaypoints, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCycleCount, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldConnectedClients, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lengthUnitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextFieldConnectedClients, jTextFieldCurWaypoint, jTextFieldCurrentCommandType, jTextFieldCycleCount, jTextFieldEndEffector, jTextFieldNumWaypoints, lengthUnitComboBox});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldConnectedClients, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldCycleCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldNumWaypoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCurWaypoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldCurrentCommandType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldEndEffector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lengthUnitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private boolean editing_status = false;

//    public String getDocumentation(String name) throws SAXException, IOException, XPathExpressionException, ParserConfigurationException {
//        return xpu.queryXml(statSchemaFiles, "/schema/complexType[@name=\""+name+"\"]/annotation/documentation/text()");
//    }
    private transient final SimServerInner inner;

    private void showErrorsPopup(MouseEvent evt) {
        JPopupMenu errorsPop = new JPopupMenu();
        JMenuItem clearMi = new JMenuItem("Clear");
        clearMi.addActionListener(e -> {
            jTextAreaErrors.setText("");
            errorsPop.setVisible(false);
        });
        errorsPop.add(clearMi);
        errorsPop.show(evt.getComponent(), evt.getX(), evt.getY());
    }

    public <T, R> R apply(TryFunction<T, R> f, T b) {
        try {
            return f.apply(b);
        } catch (Exception ex) {
            Logger.getLogger(SimServerJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static interface TryFunction<T, R> {

        public R apply(T o) throws Exception;
    };

    public void setSchemaAction() {
        JFileChooser jFileChooser = new JFileChooser();
        javax.swing.filechooser.FileFilter[] ffa = jFileChooser.getChoosableFileFilters();
        for (javax.swing.filechooser.FileFilter ff : ffa) {
            jFileChooser.removeChoosableFileFilter(ff);
        }
        jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML Schema file", "xsd"));
        jFileChooser.setMultiSelectionEnabled(true);
        int result = jFileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File fa[] = jFileChooser.getSelectedFiles();
            setCmdSchema(fa);
            CRCLSocket.saveCmdSchemaFiles(cmdSchemasFile, fa);
            setStatSchema(fa);
            CRCLSocket.saveStatSchemaFiles(statSchemasFile, fa);
        }
    }

    public int getPort() {
        return inner.getPort();
    }

    public void viewCommandLogBriefAction() {
        List<CRCLCommandType> l = inner.getCmdLog();
        final CRCLSocket s = this.inner.getCheckerCRCLSocket();
        String string = l.stream()
                .map(c -> apply(s::commandToSimpleString, c))
                .filter(Objects::nonNull)
                .collect(Collectors.joining("\n"));
        this.showMessage(string);
    }

    public void setLogImages(boolean logImages) {
        this.overHeadJPanel1.setLogImages(logImages);
        this.sideViewJPanel1.setLogImages(logImages);
    }

    public void aboutAction() {
        try {
            JOptionPane.showMessageDialog(this, getVersion());
        } catch (IOException ex) {
            Logger.getLogger(SimServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void viewCommandLogFullAction() {
        List<CRCLCommandType> l = inner.getCmdLog();
        final CRCLSocket s = this.inner.getCheckerCRCLSocket();
        String string = l.stream()
                .map((CRCLCommandType c) -> s.commandToPrettyString(c, ""))
                .filter(Objects::nonNull)
                .collect(Collectors.joining("\n"));
        this.showMessage(string);
    }

    public void setIncludeGripperAction() {
        try {
            this.closeGripperSocket();
            String gripperPortString = JOptionPane.showInputDialog(this, "Gripper Server Port?", this.gripperPort);
            gripperPort = Integer.parseInt(gripperPortString);
            gripperHost = JOptionPane.showInputDialog(this, "Gripper Server Host?", this.gripperHost);
            gripperPort = Integer.parseInt(gripperPortString);
            sendGripperStatusRequests = (JOptionPane.showConfirmDialog(this, "Send status requests?") == JOptionPane.YES_OPTION);
            this.gripperSocket = new CRCLSocket(gripperHost, gripperPort);
            this.gripperReadThread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        GetStatusType getStatus = new GetStatusType();
                        getStatus.setCommandID(BigInteger.ONE);
                        CRCLCommandInstanceType cmdInstance = new CRCLCommandInstanceType();
                        cmdInstance.setCRCLCommand(getStatus);
                        while (!Thread.currentThread().isInterrupted()) {
                            CRCLCommandInstanceType gripperCmd = inner.getGripperCmdQueue().poll();
                            if (null != gripperCmd) {
                                gripperSocket.writeCommand(gripperCmd);
                            }
                            if (sendGripperStatusRequests) {
                                Thread.sleep(inner.getDelayMillis());
                                getStatus.setCommandID(getStatus.getCommandID().add(BigInteger.ONE));
                                gripperSocket.writeCommand(cmdInstance, false);
                            }
                            checkMenuOuter();
                            CRCLStatusType gripperStatus
                                    = gripperSocket.readStatus(menuOuter.isValidateXMLSelected());
                            SimServerJPanel.this.getStatus().setGripperStatus(gripperStatus.getGripperStatus());
                        }
                    } catch (CRCLException ex) {
                        LOGGER.log(Level.SEVERE, null, ex);
                        showMessage(ex);
                    } catch (InterruptedException ex) {
                        LOGGER.log(Level.SEVERE, null, ex);
                    }
                }
            }, "simServerReadGripperThread");
            gripperReadThread.start();
            this.inner.setGripperSocket(gripperSocket);

        } catch (IOException | CRCLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void editStatusAction() {
        this.editing_status = true;
        try {
            CRCLStatusType newstat
                    = ObjTableJPanel.editObject(this.getStatus(),
                            this.getOuterFrame(),
                            "Edit Status", true,
                            inner.getXpu(),
                            null,
                            this.checkStatusValidPredicate);
            if (null != newstat) {
                inner.setStatus(newstat);
                JointStatusesType jsst = this.getStatus().getJointStatuses();
                if (null == jsst) {
                    return;
                }
                List<JointStatusType> jsl = jsst.getJointStatus();
                for (JointStatusType jst : jsl) {
                    int jvindex = jst.getJointNumber().intValue() - 1;
                    double pos = jst.getJointPosition().doubleValue();
                    inner.setJointPosition(pos, jvindex);
                    inner.setCommandedJointPosition(pos, jvindex);
                }
                this.updatePanels(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.editing_status = false;
        }
    }

    @Override
    public boolean isEditingStatus() {
        return editing_status;
    }

    public String getVersion() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("version")))) {
            StringBuilder sb = new StringBuilder();
            String line = null;
            while (null != (line = br.readLine())) {
                sb.append(line);
            }
            sb.append("\nSchema versions = ").append(CRCLSocket.getSchemaVersions().toString());
            return sb.toString();
        }
    }

    private void checkMenuOuter() {
        if (null == menuOuter) {
            throw new IllegalStateException("SimServerMenuOuter reference is null.");
        }
    }

    private final static File cmdSchemasFile = new File(System.getProperty("user.home"),
            ".crcljava_simserver_cmd_schemas.txt");

    private final static File statSchemasFile = new File(System.getProperty("user.home"),
            ".crcljava_simserver_stat_schemas.txt");

    private void setCmdSchema(File[] fa) {
        inner.setCmdSchema(fa);
    }

    private void setStatSchema(File[] fa) {
        inner.setStatSchema(fa);
    }

//    public String getStatusXmlString() {
//        try {
//            StringWriter sw = new StringWriter();
//            JAXBContext jc = JAXBContext.newInstance("crcl");
//            Marshaller m = jc.createMarshaller();
//            m.setProperty(Marshaller.JAXB_FRAGMENT, true);
//            m.marshal(jaxb_status, sw);
//            return sw.toString();
//        } catch (JAXBException ex) {
//            Logger.getLogger(SimServerJPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//    JointControlModeEnumType controlmodes[] = new JointControlModeEnumType[]{
//        JointControlModeEnumType.POSITION,
//        JointControlModeEnumType.POSITION,
//        JointControlModeEnumType.POSITION,
//        JointControlModeEnumType.POSITION,
//        JointControlModeEnumType.POSITION,
//        JointControlModeEnumType.POSITION,};
    public void setCommandState(CommandStateEnumType state) {
        inner.setCommandState(state);
    }

    public CommandStateEnumType getCommandState() {
        return inner.getCommandState();
    }

    public boolean checkPose(PoseType goalPose) {
        return inner.checkPose(goalPose);
    }

    @Override
    public void updateCycleCount(int _newCycleCount) {
        this.jTextFieldCycleCount.setText(Integer.toString(_newCycleCount));
    }

    transient private final Predicate<CRCLStatusType> checkStatusValidPredicate = new Predicate<CRCLStatusType>() {
        @Override
        public boolean test(CRCLStatusType t) {
            return checkStatusValid(t);
        }
    };

//            = this::checkStatusValid;
    public boolean checkStatusValid(CRCLStatusType statusObj) {
        try {
            String s = inner.getCheckerCRCLSocket().statusToPrettyString(statusObj, true);
            return MultiLineStringJPanel.showText(s);
        } catch (JAXBException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            showMessage(ex);
        }
        return false;
    }

    public boolean isSendStatusWithoutRequestSelected() {
        return jCheckBoxSendStatusWithoutRequest.isSelected();
    }

    public void showMessage(Throwable t) {
        showMessage(t.toString());
    }

    private volatile long last_message_show_time = 0;

    @Override
    public void showDebugMessage(final String s) {
        final String sWithThread = "Thread:" + Thread.currentThread().getName() + " \n" + s;
        LOGGER.log(Level.FINE, sWithThread);
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                jTextAreaErrors.setText(jTextAreaErrors.getText() + "\n" + sWithThread);
            }
        });
    }

    @Override
    public void updateNumWaypoints(int numWaypoints) {
        this.jTextFieldNumWaypoints.setText(Integer.toString(numWaypoints));
    }

    private JFrame outerFrame;
    private boolean searchedForOuterFrame = false;

    private JFrame searchForOuterFrame() {
        if (searchedForOuterFrame) {
            return outerFrame;
        }
        searchedForOuterFrame = true;
        Container container = this;
        while (null != (container = container.getParent())) {
            if (container instanceof JFrame) {
                return (JFrame) container;
            }
        }
        return null;
    }

    /**
     * Get the value of outerFrame
     *
     * @return the value of outerFrame
     */
    public JFrame getOuterFrame() {
        if (null == outerFrame) {
            outerFrame = searchForOuterFrame();
        }
        return outerFrame;
    }

    /**
     * Set the value of outerFrame
     *
     * @param outerFrame new value of outerFrame
     */
    public void setOuterFrame(JFrame outerFrame) {
        this.outerFrame = outerFrame;
    }

    @Override
    public void showMessage(final String s) {
        showDebugMessage(s);
        inner.setStateDescription(s);
        if (showing_message) {
            return;
        }
        showing_message = true;
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                long t = System.currentTimeMillis();
                if (t - last_message_show_time > 500) {
                    last_message_show_time = System.currentTimeMillis();
                    MultiLineStringJPanel.showText(s, getOuterFrame(), "SimServer Message", true);
                }
                last_message_show_time = System.currentTimeMillis();
                showing_message = false;
            }
        });
    }

    private boolean toolChangerOpen;

    /**
     * Get the value of toolChangerOpen
     *
     * @return the value of toolChangerOpen
     */
    public boolean isToolChangerOpen() {
        return toolChangerOpen;
    }

    /**
     * Set the value of toolChangerOpen
     *
     * @param toolChangerOpen new value of toolChangerOpen
     */
    public void setToolChangerOpen(boolean toolChangerOpen) {
        this.toolChangerOpen = toolChangerOpen;
    }

    private void updatePanelsPrivate() {
        this.overHeadJPanel1.setJointvals(inner.getJointPositions());
        this.overHeadJPanel1.setSeglengths(inner.getSeglengths());
        this.overHeadJPanel1.repaint();
        this.sideViewJPanel1.setJointvals(inner.getJointPositions());
        this.sideViewJPanel1.repaint();
    }

    @Override
    public void updatePanels(boolean jointschanged) {
        if (jointschanged) {
            updatePanelsPrivate();
        }
    }

    @Override
    public void finishSetCurrentWaypoint(int currentWaypoint) {
        this.jTextFieldCurWaypoint.setText(Integer.toString(currentWaypoint));
    }

    @Override
    public void updateIsInitialized(boolean initialized) {
        this.jCheckBoxInitialized.setSelected(initialized);
    }

    @Override
    public void updateCurrentCommandType(String cmdName) {
        this.jTextFieldCurrentCommandType.setText(cmdName);
    }
    private boolean showing_message = false;

    public boolean isInitializedSelected() {
        return this.jCheckBoxInitialized.isSelected();
    }

    @Override
    public void updateEndEffector(String text) {
        this.jTextFieldEndEffector.setText(text);
    }

    @Override
    public void updateToolChangerIsOpen(boolean isOpen) {
        this.setToolChangerOpen(isOpen);
    }

    @Override
    public void updateLengthUnit(LengthUnitEnumType lu) {
        this.lengthUnitComboBox.setSelectedItem(lu);
    }

    @Override
    public void updateConnectedClients(int numClients) {
        this.jTextFieldConnectedClients.setText(Integer.toString(numClients));
    }

    private void jTextAreaErrorsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaErrorsMousePressed
        if (evt.isPopupTrigger()) {
            showErrorsPopup(evt);
        }
    }//GEN-LAST:event_jTextAreaErrorsMousePressed

    private void jTextAreaErrorsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaErrorsMouseReleased
        if (evt.isPopupTrigger()) {
            showErrorsPopup(evt);
        }
    }//GEN-LAST:event_jTextAreaErrorsMouseReleased

    private void jTextAreaErrorsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaErrorsMouseClicked
        if (evt.isPopupTrigger()) {
            showErrorsPopup(evt);
        }
    }//GEN-LAST:event_jTextAreaErrorsMouseClicked

    private void jTextFieldPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPortActionPerformed
        int new_port = Integer.parseInt(this.jTextFieldPort.getText());
        inner.setPort(new_port);
        inner.restartServer();
    }//GEN-LAST:event_jTextFieldPortActionPerformed

    private void jTextFieldCycleTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCycleTimeActionPerformed
        long newDelayMillis = Long.parseLong(this.jTextFieldCycleTime.getText());
        inner.setDelayMillis(newDelayMillis);
    }//GEN-LAST:event_jTextFieldCycleTimeActionPerformed

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        inner.reset();
        this.updatePanels(true);
    }//GEN-LAST:event_jButtonResetActionPerformed

    private void jCheckBoxInitializedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxInitializedActionPerformed
        if (this.jCheckBoxInitialized.isSelected()) {
            this.inner.initialize();
        }
    }//GEN-LAST:event_jCheckBoxInitializedActionPerformed

    private void jButtonRestartServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRestartServerActionPerformed
        int new_port = Integer.parseInt(this.jTextFieldPort.getText());
        new Thread(() -> {
            inner.setPort(new_port);
            inner.restartServer();
        }).start();
    }//GEN-LAST:event_jButtonRestartServerActionPerformed

    private void jComboBoxRobotTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRobotTypeActionPerformed
        SimRobotEnum robotType = SimRobotEnum.valueOf(this.jComboBoxRobotType.getSelectedItem().toString());
        this.setRobotType(robotType);
    }//GEN-LAST:event_jComboBoxRobotTypeActionPerformed

    private void jCheckBoxTeleportToGoalsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTeleportToGoalsActionPerformed
        this.inner.setTeleportToGoals(this.jCheckBoxTeleportToGoals.isSelected());
    }//GEN-LAST:event_jCheckBoxTeleportToGoalsActionPerformed

    private void lengthUnitComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lengthUnitComboBoxActionPerformed
        if (this.inner.getLengthUnit() != lengthUnitComboBox.getSelectedItem()) {
            this.inner.setLengthUnit(lengthUnitComboBox.getSelectedItem());
        }
    }//GEN-LAST:event_lengthUnitComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonRestartServer;
    private javax.swing.JCheckBox jCheckBoxInitialized;
    private javax.swing.JCheckBox jCheckBoxSendStatusWithoutRequest;
    private javax.swing.JCheckBox jCheckBoxTeleportToGoals;
    private javax.swing.JComboBox<SimRobotEnum> jComboBoxRobotType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaErrors;
    private javax.swing.JTextField jTextFieldConnectedClients;
    private javax.swing.JTextField jTextFieldCurWaypoint;
    private javax.swing.JTextField jTextFieldCurrentCommandType;
    private javax.swing.JTextField jTextFieldCycleCount;
    private javax.swing.JTextField jTextFieldCycleTime;
    private javax.swing.JTextField jTextFieldEndEffector;
    private javax.swing.JTextField jTextFieldNumWaypoints;
    private javax.swing.JTextField jTextFieldPort;
    private crcl.ui.misc.LengthUnitComboBox lengthUnitComboBox;
    private crcl.ui.server.OverHeadJPanel overHeadJPanel1;
    private crcl.ui.server.SideViewJPanel sideViewJPanel1;
    // End of variables declaration//GEN-END:variables
}
