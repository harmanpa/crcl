/*
 * This is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * 
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United Statesm
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
package crcl.ui;

import com.github.wshackle.crcl4java.exi.CrclExiSocket;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStateEnumType;
import crcl.base.GetStatusType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.LengthUnitEnumType;
import crcl.base.PoseType;
import static crcl.ui.PendantClient.getRobotImage;
import crcl.utils.CRCLSocket;
import crcl.utils.CRCLException;
import crcl.utils.SimRobotEnum;
import crcl.utils.SimServerOuter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author Will Shackleford{@literal <william.shackleford@nist.gov> }
 */
public class SimServer extends javax.swing.JFrame implements SimServerOuter {

    String tempDir = "/tmp";

    private final static SimRobotEnum DEFAULT_ROBOTTYPE = SimRobotEnum.valueOf(System.getProperty("crcl4java.simserver.robottype", SimRobotEnum.SIMPLE.toString()));
    /**
     * Creates new form SimServer
     *
     * @throws javax.xml.parsers.ParserConfigurationException when
     * javax.xml.parsers.DocumentBuilderFactory fails in XpathUtils
     */
    public SimServer() throws ParserConfigurationException {
        initComponents();
        SimRobotEnum defaultRobotType
                = DEFAULT_ROBOTTYPE;
        this.inner = new SimServerInner(this);
        java.awt.EventQueue.invokeLater(() -> this.updatePanelsPrivate());
        this.lengthUnitComboBox.setSelectedItem(LengthUnitEnumType.MILLIMETER);
        for (SimRobotEnum srType : SimRobotEnum.values()) {
            this.setRobotType(SimRobotEnum.PLAUSIBLE);
            inner.setLengthUnit(LengthUnitEnumType.MILLIMETER);
        }
        this.jComboBoxRobotType.setModel(new DefaultComboBoxModel<>(SimRobotEnum.values()));
        this.jComboBoxRobotType.setSelectedItem(defaultRobotType);
        this.setRobotType(defaultRobotType);
        this.setStatSchema(CRCLSocket.readStatSchemaFiles(SimServer.statSchemasFile));
        this.setCmdSchema(CRCLSocket.readCmdSchemaFiles(SimServer.cmdSchemasFile));
        String portPropertyString = System.getProperty("crcl4java.port");
        if (null != portPropertyString) {
            inner.setPort(Integer.valueOf(portPropertyString));
            this.jTextFieldPort.setText(portPropertyString);
        } else {
            this.jTextFieldPort.setText(Integer.toString(inner.getPort()));
        }
        inner.restartServer();
        try {
            String imageLogDirProp = System.getProperty("crcl4java.simserver.imagelogdir");
            File imageLogDir = null;
            if (imageLogDirProp != null && imageLogDirProp.length() > 0) {
                imageLogDir = new File(imageLogDirProp);
                if (!imageLogDir.exists()
                        || !imageLogDir.isDirectory()
                        || !imageLogDir.canWrite()) {
                    Logger.getLogger(SimServer.class.getName()).log(Level.SEVERE, "Can''t use tempDir from property temp.dir {0}", imageLogDir);
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
            Logger.getLogger(SimServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean logImages = LOG_IMAGES_DEFAULT;
        this.jCheckBoxMenuItemLogImages.setSelected(logImages);
        this.overHeadJPanel1.setLogImages(logImages);
        this.sideViewJPanel1.setLogImages(logImages);
        this.jCheckBoxTeleportToGoals.setSelected(inner.isTeleportToGoals());
        this.setIconImage(SERVER_IMAGE);
    }

    private static Image createImage(Dimension d, Color bgColor, Color textColor, Image baseImage) {
        BufferedImage bi = new BufferedImage(d.width, d.height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = bi.createGraphics();
        g2d.setBackground(bgColor);
        g2d.setColor(textColor);
        g2d.clearRect(0, 0, d.width, d.height);
        g2d.setFont(new Font(g2d.getFont().getName(), g2d.getFont().getStyle(), 24));
        g2d.drawImage(baseImage,0,0,null);
        bi.flush();
//        try {
//            File f = File.createTempFile("icon", ".png");
//            System.out.println("f = " + f);
//            ImageIO.write(bi, "PNG", f);
//        } catch (IOException ex) {
//            Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return bi;
    }

    private static final Dimension ICON_SIZE = new Dimension(32, 32);
    private static final Image BASE_IMAGE = getRobotImage();
    private static final Image SERVER_IMAGE = createImage(ICON_SIZE, Color.MAGENTA, Color.BLACK, BASE_IMAGE);
    private static final Image DONE_IMAGE = createImage(ICON_SIZE, Color.white, Color.BLACK, BASE_IMAGE);
    private static final Image ERROR_IMAGE = createImage(ICON_SIZE, Color.red, Color.BLACK, BASE_IMAGE);
    private static final Image WORKING_IMAGE = createImage(ICON_SIZE, Color.green, Color.BLACK, BASE_IMAGE);
    private static final Image DISCONNECTED_IMAGE = createImage(ICON_SIZE, Color.GRAY, Color.BLACK, BASE_IMAGE);

    private static final boolean LOG_IMAGES_DEFAULT = Boolean.getBoolean("crcl4java.simserver.logimages");
    
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        overHeadJPanel1 = new crcl.ui.OverHeadJPanel();
        jPanel2 = new javax.swing.JPanel();
        sideViewJPanel1 = new crcl.ui.SideViewJPanel();
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
        lengthUnitComboBox = new crcl.ui.LengthUnitComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemEditStatus = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemSetSchema = new javax.swing.JMenuItem();
        jCheckBoxMenuItemValidateXML = new javax.swing.JCheckBoxMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jCheckBoxMenuItemRandomPacket = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemAppendZero = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemIncludeGripperStatus = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemReplaceState = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDebugMoveDone = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDebugReadCommand = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDebugSendStatus = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemReplaceXmlHeader = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemEXI = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemLogImages = new javax.swing.JCheckBoxMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItemViewCommandLogBrief = new javax.swing.JMenuItem();
        jMenuItemViewCommandLogFull = new javax.swing.JMenuItem();

        FormListener formListener = new FormListener();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CRCL SImulation Server");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Overhead View"));
        jPanel1.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout overHeadJPanel1Layout = new javax.swing.GroupLayout(overHeadJPanel1);
        overHeadJPanel1.setLayout(overHeadJPanel1Layout);
        overHeadJPanel1Layout.setHorizontalGroup(
            overHeadJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
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
            .addGap(0, 232, Short.MAX_VALUE)
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
        jTextFieldPort.addActionListener(formListener);

        jLabel3.setText("Cycle Time (ms): ");

        jTextFieldCycleTime.setText("100");
        jTextFieldCycleTime.addActionListener(formListener);

        jButtonReset.setText("Reset");
        jButtonReset.addActionListener(formListener);

        jCheckBoxInitialized.setText("Initialized");
        jCheckBoxInitialized.addActionListener(formListener);

        jCheckBoxSendStatusWithoutRequest.setText("Send Status Without Requests");

        jButtonRestartServer.setText("Restart Server");
        jButtonRestartServer.addActionListener(formListener);

        jComboBoxRobotType.addActionListener(formListener);

        jCheckBoxTeleportToGoals.setText("Teleport to Goals");
        jCheckBoxTeleportToGoals.addActionListener(formListener);

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

        lengthUnitComboBox.addActionListener(formListener);

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

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextFieldConnectedClients, jTextFieldCurWaypoint, jTextFieldCurrentCommandType, jTextFieldCycleCount, jTextFieldEndEffector, jTextFieldNumWaypoints});

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

        jMenu1.setText("File");

        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(formListener);
        jMenu1.add(jMenuItemExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItemEditStatus.setText("Status in Table ...");
        jMenuItemEditStatus.addActionListener(formListener);
        jMenu2.add(jMenuItemEditStatus);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("XML Schemas");

        jMenuItemSetSchema.setText("Set Schema File(s)  ... ");
        jMenuItemSetSchema.addActionListener(formListener);
        jMenu3.add(jMenuItemSetSchema);

        jCheckBoxMenuItemValidateXML.setSelected(true);
        jCheckBoxMenuItemValidateXML.setText("Validate XML with Schema(s)");
        jMenu3.add(jCheckBoxMenuItemValidateXML);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Options");

        jCheckBoxMenuItemRandomPacket.setText("Randomize Packeting");
        jMenu4.add(jCheckBoxMenuItemRandomPacket);

        jCheckBoxMenuItemAppendZero.setText("Append 0 for string termination");
        jMenu4.add(jCheckBoxMenuItemAppendZero);

        jCheckBoxMenuItemIncludeGripperStatus.setText("Connect to Gripper Server ...");
        jCheckBoxMenuItemIncludeGripperStatus.addActionListener(formListener);
        jMenu4.add(jCheckBoxMenuItemIncludeGripperStatus);

        jCheckBoxMenuItemReplaceState.setText("Replace CRCL_Working,CRCL_Done with Working,Done ...");
        jMenu4.add(jCheckBoxMenuItemReplaceState);

        jCheckBoxMenuItemDebugMoveDone.setText("Debug MOVE Done");
        jMenu4.add(jCheckBoxMenuItemDebugMoveDone);

        jCheckBoxMenuItemDebugReadCommand.setText("Debug Read Command");
        jMenu4.add(jCheckBoxMenuItemDebugReadCommand);

        jCheckBoxMenuItemDebugSendStatus.setText("Debug Send Status");
        jMenu4.add(jCheckBoxMenuItemDebugSendStatus);

        jCheckBoxMenuItemReplaceXmlHeader.setSelected(true);
        jCheckBoxMenuItemReplaceXmlHeader.setText("Replace XML Headers");
        jMenu4.add(jCheckBoxMenuItemReplaceXmlHeader);

        jCheckBoxMenuItemEXI.setText("Use EXI (Efficient XML Interchange)");
        jCheckBoxMenuItemEXI.addActionListener(formListener);
        jMenu4.add(jCheckBoxMenuItemEXI);

        jCheckBoxMenuItemLogImages.setText("Log Images");
        jCheckBoxMenuItemLogImages.addActionListener(formListener);
        jMenu4.add(jCheckBoxMenuItemLogImages);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Tools");

        jMenuItemViewCommandLogBrief.setText("View Command Log Brief");
        jMenuItemViewCommandLogBrief.addActionListener(formListener);
        jMenu5.add(jMenuItemViewCommandLogBrief);

        jMenuItemViewCommandLogFull.setText("View Command Log Full");
        jMenuItemViewCommandLogFull.addActionListener(formListener);
        jMenu5.add(jMenuItemViewCommandLogFull);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
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
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jTextFieldPort) {
                SimServer.this.jTextFieldPortActionPerformed(evt);
            }
            else if (evt.getSource() == jTextFieldCycleTime) {
                SimServer.this.jTextFieldCycleTimeActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonReset) {
                SimServer.this.jButtonResetActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxInitialized) {
                SimServer.this.jCheckBoxInitializedActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonRestartServer) {
                SimServer.this.jButtonRestartServerActionPerformed(evt);
            }
            else if (evt.getSource() == jComboBoxRobotType) {
                SimServer.this.jComboBoxRobotTypeActionPerformed(evt);
            }
            else if (evt.getSource() == lengthUnitComboBox) {
                SimServer.this.lengthUnitComboBoxActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemExit) {
                SimServer.this.jMenuItemExitActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemEditStatus) {
                SimServer.this.jMenuItemEditStatusActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemSetSchema) {
                SimServer.this.jMenuItemSetSchemaActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemIncludeGripperStatus) {
                SimServer.this.jCheckBoxMenuItemIncludeGripperStatusActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemEXI) {
                SimServer.this.jCheckBoxMenuItemEXIActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemLogImages) {
                SimServer.this.jCheckBoxMenuItemLogImagesActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemViewCommandLogBrief) {
                SimServer.this.jMenuItemViewCommandLogBriefActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemViewCommandLogFull) {
                SimServer.this.jMenuItemViewCommandLogFullActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxTeleportToGoals) {
                SimServer.this.jCheckBoxTeleportToGoalsActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private boolean editing_status = false;

//    public String getDocumentation(String name) throws SAXException, IOException, XPathExpressionException, ParserConfigurationException {
//        return xpu.queryXml(statSchemaFiles, "/schema/complexType[@name=\""+name+"\"]/annotation/documentation/text()");
//    }
    final SimServerInner inner;

    @Override
    public boolean isValidateXMLSelected() {
        return jCheckBoxMenuItemValidateXML.isSelected();
    }

    private CRCLStatusType getStatus() {
        return inner.getStatus();
    }

    private void jMenuItemEditStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditStatusActionPerformed
        this.editing_status = true;
        try {
            CRCLStatusType newstat = ObjTableJPanel.editObject(this.getStatus(),
                    this, "Edit Status", true,
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
    }//GEN-LAST:event_jMenuItemEditStatusActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenuItemSetSchemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSetSchemaActionPerformed
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
            CRCLSocket.saveCmdSchemaFiles(SimServer.cmdSchemasFile, fa);
            setStatSchema(fa);
            CRCLSocket.saveStatSchemaFiles(SimServer.statSchemasFile, fa);
        }
    }//GEN-LAST:event_jMenuItemSetSchemaActionPerformed

    private void sideViewJPanel1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_sideViewJPanel1ComponentResized
        this.updatePanels(true);
    }//GEN-LAST:event_sideViewJPanel1ComponentResized

    private CrclExiSocket gripperSocket = null;
    private Thread gripperReadThread = null;
    private int gripperPort = 4005;
    private String gripperHost = "localhost";
    private boolean sendGripperStatusRequests = true;

    private void closeGripperSocket() {
        if (null != gripperSocket) {
            try {
                gripperSocket.close();
                gripperSocket = null;
                this.inner.setGripperSocket(gripperSocket);
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        if (null != gripperReadThread) {
            try {
                gripperReadThread.interrupt();
                gripperReadThread.join(100);
                gripperReadThread = null;
            } catch (InterruptedException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
    }
    public static final Logger LOGGER = Logger.getLogger(SimServer.class.getName());

    private void jCheckBoxMenuItemIncludeGripperStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemIncludeGripperStatusActionPerformed
        if (this.jCheckBoxMenuItemIncludeGripperStatus.isSelected()) {
            try {
                this.closeGripperSocket();
                String gripperPortString = JOptionPane.showInputDialog(this, "Gripper Server Port?", this.gripperPort);
                gripperPort = Integer.valueOf(gripperPortString);
                String gripperHostString = JOptionPane.showInputDialog(this, "Gripper Server Host?", this.gripperHost);
                gripperPort = Integer.valueOf(gripperPortString);
                sendGripperStatusRequests = (JOptionPane.showConfirmDialog(this, "Send status requests?") == JOptionPane.YES_OPTION);
                this.gripperSocket = new CrclExiSocket(gripperHost, gripperPort);
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
                                CRCLStatusType gripperStatus
                                        = gripperSocket.readStatus(jCheckBoxMenuItemValidateXML.isSelected());
                                SimServer.this.getStatus().setGripperStatus(gripperStatus.getGripperStatus());
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
    }//GEN-LAST:event_jCheckBoxMenuItemIncludeGripperStatusActionPerformed

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

    private void jCheckBoxMenuItemEXIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemEXIActionPerformed
        inner.restartServer();
    }//GEN-LAST:event_jCheckBoxMenuItemEXIActionPerformed

    public <T, R> R apply(TryFunction<T, R> f, T b) {
        try {
            return f.apply(b);
        } catch (Exception ex) {
            Logger.getLogger(SimServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static interface TryFunction<T, R> {

        public R apply(T o) throws Exception;
    };


    private void jMenuItemViewCommandLogBriefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemViewCommandLogBriefActionPerformed
        List<CRCLCommandType> l = inner.getCmdLog();
        final CRCLSocket s = this.inner.getCheckerCRCLSocket();
        String string = l.stream()
                .map(c -> apply(s::commandToSimpleString, c))
                .filter(Objects::nonNull)
                .collect(Collectors.joining("\n"));
        this.showMessage(string);
    }//GEN-LAST:event_jMenuItemViewCommandLogBriefActionPerformed

    private void jMenuItemViewCommandLogFullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemViewCommandLogFullActionPerformed
        List<CRCLCommandType> l = inner.getCmdLog();
        final CRCLSocket s = this.inner.getCheckerCRCLSocket();
        String string = l.stream()
                .map((CRCLCommandType c) -> s.commandToPrettyString(c,""))
                .filter(Objects::nonNull)
                .collect(Collectors.joining("\n"));
        this.showMessage(string);
    }//GEN-LAST:event_jMenuItemViewCommandLogFullActionPerformed

    private void jCheckBoxMenuItemLogImagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemLogImagesActionPerformed
        this.overHeadJPanel1.setLogImages(this.jCheckBoxMenuItemLogImages.isSelected());
        this.sideViewJPanel1.setLogImages(this.jCheckBoxMenuItemLogImages.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItemLogImagesActionPerformed

    private void jComboBoxRobotTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRobotTypeActionPerformed
        SimRobotEnum robotType = SimRobotEnum.valueOf(this.jComboBoxRobotType.getSelectedItem().toString());
        this.setRobotType(robotType);
    }//GEN-LAST:event_jComboBoxRobotTypeActionPerformed

    private void jButtonRestartServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRestartServerActionPerformed
        int new_port = Integer.valueOf(this.jTextFieldPort.getText());
        new Thread(() -> {
            inner.setPort(new_port);
            inner.restartServer();
        }).start();
    }//GEN-LAST:event_jButtonRestartServerActionPerformed

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        inner.reset();
        this.updatePanels(true);
    }//GEN-LAST:event_jButtonResetActionPerformed

    private void jTextFieldCycleTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCycleTimeActionPerformed
        long newDelayMillis = Long.valueOf(this.jTextFieldCycleTime.getText());
        inner.setDelayMillis(newDelayMillis);
    }//GEN-LAST:event_jTextFieldCycleTimeActionPerformed

    private void jTextFieldPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPortActionPerformed
        int new_port = Integer.valueOf(this.jTextFieldPort.getText());
        inner.setPort(new_port);
        inner.restartServer();
    }//GEN-LAST:event_jTextFieldPortActionPerformed

    private void lengthUnitComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lengthUnitComboBoxActionPerformed
        if (this.inner.getLengthUnit() != lengthUnitComboBox.getSelectedItem()) {
            this.inner.setLengthUnit(lengthUnitComboBox.getSelectedItem());
        }
    }//GEN-LAST:event_lengthUnitComboBoxActionPerformed

    private void jCheckBoxInitializedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxInitializedActionPerformed
       if(this.jCheckBoxInitialized.isSelected()) {
           this.inner.initialize();
       }
    }//GEN-LAST:event_jCheckBoxInitializedActionPerformed

    private void jCheckBoxTeleportToGoalsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTeleportToGoalsActionPerformed
        this.inner.setTeleportToGoals(this.jCheckBoxTeleportToGoals.isSelected());
    }//GEN-LAST:event_jCheckBoxTeleportToGoalsActionPerformed

    @Override
    public boolean isEXISelected() {
        return this.jCheckBoxMenuItemEXI.isSelected();
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
//            Logger.getLogger(SimServer.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean isAppendZeroSelected() {
        return this.jCheckBoxMenuItemAppendZero.isSelected();
    }

    @Override
    public boolean isReplaceXmlHeaderSelected() {
        return this.jCheckBoxMenuItemReplaceXmlHeader.isSelected();
    }

    @Override
    public boolean isRandomPacketSelected() {
        return this.jCheckBoxMenuItemRandomPacket.isSelected();
    }

    @Override
    public boolean isReplaceStateSelected() {
        return this.jCheckBoxMenuItemReplaceState.isSelected();
    }

    @Override
    public boolean isEditingStatus() {
        return this.editing_status;
    }

    @Override
    public void updateCycleCount(int _newCycleCount) {
        this.jTextFieldCycleCount.setText(Integer.toString(_newCycleCount));
    }

    private final Predicate<CRCLStatusType> checkStatusValidPredicate = new Predicate<CRCLStatusType>() {
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

    @Override
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
                    MultiLineStringJPanel.showText(s, SimServer.this, "SimServer Message", true);
                }
                last_message_show_time = System.currentTimeMillis();
                showing_message = false;
            }
        });
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

    @Override
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
        } catch (Exception ex) {
            LOGGER.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new SimServer().setVisible(true);
                } catch (ParserConfigurationException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonRestartServer;
    private javax.swing.JCheckBox jCheckBoxInitialized;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemAppendZero;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDebugMoveDone;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDebugReadCommand;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDebugSendStatus;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemEXI;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemIncludeGripperStatus;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemLogImages;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemRandomPacket;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemReplaceState;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemReplaceXmlHeader;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemValidateXML;
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemEditStatus;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemSetSchema;
    private javax.swing.JMenuItem jMenuItemViewCommandLogBrief;
    private javax.swing.JMenuItem jMenuItemViewCommandLogFull;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextArea jTextAreaErrors;
    private javax.swing.JTextField jTextFieldConnectedClients;
    private javax.swing.JTextField jTextFieldCurWaypoint;
    private javax.swing.JTextField jTextFieldCurrentCommandType;
    private javax.swing.JTextField jTextFieldCycleCount;
    private javax.swing.JTextField jTextFieldCycleTime;
    private javax.swing.JTextField jTextFieldEndEffector;
    private javax.swing.JTextField jTextFieldNumWaypoints;
    private javax.swing.JTextField jTextFieldPort;
    private crcl.ui.LengthUnitComboBox lengthUnitComboBox;
    private crcl.ui.OverHeadJPanel overHeadJPanel1;
    private crcl.ui.SideViewJPanel sideViewJPanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean isDebugMoveDoneSelected() {
        return this.jCheckBoxMenuItemDebugMoveDone.isSelected();
    }

    @Override
    public boolean isDebugReadCommandSelected() {
        return this.jCheckBoxMenuItemDebugReadCommand.isSelected();
    }

    @Override
    public boolean isDebugSendStatusSelected() {
        return this.jCheckBoxMenuItemDebugSendStatus.isSelected();
    }

}
