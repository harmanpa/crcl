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
package crcl.ui;

import static crcl.ui.ObjTableJPanel.getAssignableClasses;
import crcl.base.ActuateJointType;
import crcl.base.ActuateJointsType;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLProgramType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStateEnumType;
import crcl.base.CommandStatusType;
import crcl.base.EndCanonType;
import crcl.base.InitCanonType;
import crcl.base.JointSpeedAccelType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.LengthUnitEnumType;
import crcl.base.MiddleCommandType;
import crcl.base.MoveToType;
import crcl.base.PointType;
import crcl.base.PoseType;
import crcl.base.RotSpeedAbsoluteType;
import crcl.base.SetEndEffectorType;
import crcl.base.SetLengthUnitsType;
import crcl.base.SetRotSpeedType;
import crcl.base.SetTransSpeedType;
import crcl.base.StopConditionEnumType;
import crcl.base.TransSpeedAbsoluteType;
import crcl.base.VectorType;
import crcl.utils.AnnotatedPose;
import crcl.utils.CRCLPosemath;
import crcl.utils.CRCLSocket;
import crcl.utils.CRCLSocketException;
import crcl.utils.PendantClientOuter;
import diagapplet.plotter.PlotData;
import diagapplet.plotter.plotterJFrame;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;
import rcs.posemath.PmException;
import rcs.posemath.PmRotationMatrix;
import rcs.posemath.PmRotationVector;
import rcs.posemath.PmRpy;
import rcs.posemath.Posemath;

/**
 *
 * @author Will Shackleford{@literal <william.shackleford@nist.gov> }
 */
public class PendantClient extends javax.swing.JFrame implements PendantClientOuter {

    public PendantClient(GraphicsConfiguration gc) throws ParserConfigurationException {
        super(gc);
        this.internal = new PendantClientInner(this);
        init();
    }

    public PendantClient(String title) throws ParserConfigurationException {
        super(title);
        this.internal = new PendantClientInner(this);
        init();
        setTitle(title);
    }

    /**
     * Creates new form PendantClient
     *
     * @throws javax.xml.parsers.ParserConfigurationException when
     * javax.xml.parsers.DocumentBuilderFactory fails in XpathUtils
     */
    public PendantClient() throws ParserConfigurationException {
        super();
        this.internal = new PendantClientInner(this);
        init();
    }

    private void init() {
        initComponents();
        addCommandsMenu();

        String portPropertyString = System.getProperty("crcljava.port");
        if (null != portPropertyString) {
            this.jTextFieldPort.setText(portPropertyString);
        }
        String hostPropertyString = System.getProperty("crcljava.host");
        if (null != hostPropertyString) {
            this.jTextFieldHost.setText(hostPropertyString);
        }
        internal.setStatSchema(CRCLSocket.readStatSchemaFiles(PendantClient.statSchemasFile));
        internal.setCmdSchema(CRCLSocket.readCmdSchemaFiles(PendantClient.cmdSchemasFile));
        internal.setProgramSchema(CRCLSocket.readProgramSchemaFiles(PendantClient.programSchemasFile));
        readRecentCommandFiles();
        readRecentPrograms();
        final String programPropertyString = System.getProperty("crcljava.program");
        if (null != programPropertyString) {
            java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    openXmlProgramFile(new File(programPropertyString));
                }
            });
        }
        checkSettingsRef();
        this.updateUIFromInternal();
        this.jTableProgram.getSelectionModel().addListSelectionListener(e -> finishShowCurrentProgramLine(this.jTableProgram.getSelectedRow()));
        this.internal.addPropertyChangeListener(new MyPropertyChangeListener());
    }

    private void updateUIFromInternal() {
        this.jTextFieldJointJogIncrement.setText(Double.toString(internal.getJogIncrement()));
        this.jTextFieldXYZJogIncrement.setText(Double.toString(internal.getXyzJogIncrement()));
        this.jTextFieldJointJogSpeed.setText(Double.toString(internal.getJogJointSpeed()));
        this.jTextFieldTransSpeed.setText(Double.toString(internal.getJogTransSpeed()));
        this.jTextFieldRPYJogIncrement.setText(Double.toString(this.rpyJogIncrement));
        this.jTextFieldJogInterval.setText(Double.toString(internal.getJogInterval()));
        this.jCheckBoxMenuItemQuitProgramOnTestCommandFail.setSelected(internal.isQuitOnTestCommandFailure());

    }

    private void readRecentCommandFiles() {
        File fMainDir = new File(System.getProperty("user.home"),
                recent_files_dir);
        if (!fMainDir.exists()) {
            return;
        }
        File fa[] = fMainDir.listFiles(new java.io.FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() && pathname.canRead();
            }
        });
        if (null == fa || fa.length < 1) {
            return;
        }
        Arrays.sort(fa, new Comparator<File>() {

            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (File fSubDir : fa) {
            JMenu jm = new JMenu(fSubDir.getName());
            this.jMenuCommandRecent.add(jm);
            File sub_fa[] = fSubDir.listFiles(new java.io.FilenameFilter() {

                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".xml");
                }
            });
            Arrays.sort(sub_fa, new Comparator<File>() {

                @Override
                public int compare(File o1, File o2) {
                    return Long.compare(o1.lastModified(), o2.lastModified());
                }
            });
            for (int i = 0; i < sub_fa.length && i < 3; i++) {
                File xmlFile = sub_fa[i];
                JMenuItem jmi = new JMenuItem(xmlFile.getName());
                jmi.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            openXmlInstanceFile(xmlFile);
                        } catch (ParserConfigurationException | CRCLSocketException | JAXBException | XPathExpressionException | IOException | SAXException ex) {
                            LOGGER.log(Level.SEVERE, null, ex);
                            showMessage(ex);
                        }
                    }
                });
                jm.add(jmi);
            }
        }

    }
    public static final Logger LOGGER = Logger.getLogger(PendantClient.class.getName());

    public void openXmlProgramFile(File f) {
        try {
            this.clearProgramTimesDistances();
            this.clearRecordedPoints();
            internal.openXmlProgramFile(f, true);
        } catch (SAXException | IOException | CRCLSocketException | XPathExpressionException | ParserConfigurationException ex) {
            Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
            showMessage(ex);
        }
    }

    public void saveXmlProgramFile(File f) throws JAXBException, CRCLSocketException {
        if (null != recordPointsProgram) {
            recordPointsProgram.getInitCanon().setCommandID(BigInteger.ONE);
            for (int i = 0; i < recordPointsProgram.getMiddleCommand().size(); i++) {
                recordPointsProgram.getMiddleCommand().get(i).setCommandID(BigInteger.valueOf(i + 1));
            }
            recordPointsProgram.getEndCanon().setCommandID(BigInteger.valueOf(recordPointsProgram.getMiddleCommand().size()));
            internal.setProgram(recordPointsProgram);
            this.showProgram(recordPointsProgram);
        }
        internal.saveXmlProgramFile(f);
    }

    @Override
    public boolean isRecordPoseSelected() {
        return this.jCheckBoxMenuItemRecordPoseList.isSelected();
    }

    @Override
    public void finishOpenXmlProgramFile(File f,
            CRCLProgramType program, boolean saveRecent) {
        try {
            this.recordPointsProgram = null;
            showProgram(program);
            internal.setProgram(program);
            this.saveRecentProgram(f);
            this.jTabbedPane1.setSelectedComponent(this.jPanelProgram);
        } catch (JAXBException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            showMessage(ex);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    private String getFirstLine(File f) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            return br.readLine();
        }
    }

    private Set<String> getRecentPrograms() {
        File fMainDir = new File(System.getProperty("user.home"), recent_programs_dir);
        Set<String> pathSet = new TreeSet<>();
        if (!fMainDir.exists()) {
            return pathSet;
        }
        File fa[] = fMainDir.listFiles(new java.io.FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return !pathname.isDirectory() && pathname.canRead();
            }
        });
        if (null == fa || fa.length < 1) {
            return pathSet;
        }
        Arrays.sort(fa, new Comparator<File>() {

            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (File f : fa) {
            try {
                String path = getFirstLine(f).trim();
                File fprog = new File(path);
                if (!fprog.exists() || !fprog.canRead() || fprog.isDirectory()) {
                    f.delete();
                    continue;
                }
                final String fprogCanon = fprog.getCanonicalPath();
                if (pathSet.contains(fprogCanon)) {
                    continue;
                }
                pathSet.add(fprogCanon);
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        return pathSet;
    }

    private void readRecentPrograms() {
        Set<String> pathSet = this.getRecentPrograms();
        this.jMenuRecentProgram.removeAll();
        for (final String fprogCanon : pathSet) {
            try {
                JMenuItem jmi = new JMenuItem(fprogCanon);
                jmi.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        openXmlProgramFile(new File(fprogCanon));
                    }
                });
                this.jMenuRecentProgram.add(jmi);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
    }

    private void addCommandsMenu() {
        try {
            List<Class> allClasses = ObjTableJPanel.getClasses();
            List<Class> cmdClasses = ObjTableJPanel.getAssignableClasses(CRCLCommandType.class,
                    allClasses);
            Collections.sort(cmdClasses, new Comparator<Class>() {

                @Override
                public int compare(Class o1, Class o2) {
                    return o1.getCanonicalName().compareTo(o2.getCanonicalName());
                }
            });
            for (final Class c : cmdClasses) {
                JMenuItem jmi = new JMenuItem(c.getCanonicalName());
                jmi.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            CRCLCommandType cmd = (CRCLCommandType) c.newInstance();
                            cmd
                                    = ObjTableJPanel.editObject(cmd,
                                            internal.getXpu(),
                                            null,
                                            PendantClient.this.internal.getCheckCommandValidPredicate());
                            if (null != cmd) {
                                internal.incAndSendCommand(cmd);
                                saveRecentCommand(cmd);
                            }
                        } catch (InstantiationException ex) {
                            LOGGER.log(Level.SEVERE, null, ex);
                        } catch (IllegalAccessException ex) {
                            LOGGER.log(Level.SEVERE, null, ex);
                        } catch (JAXBException ex) {
                            LOGGER.log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            LOGGER.log(Level.SEVERE, null, ex);
                        }
                    }
                });
                this.jMenuCmds.add(jmi);
            }
        } catch (Throwable e) {
            LOGGER.log(Level.SEVERE, null, e);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldStatCmdID = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableJoints = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldStatus = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablePose = new javax.swing.JTable();
        jTextFieldStatusID = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldStatusStateDescription = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaErrors = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jTextFieldPort = new javax.swing.JTextField();
        jButtonConnect = new javax.swing.JButton();
        jButtonDisconnect = new javax.swing.JButton();
        jButtonEnd = new javax.swing.JButton();
        jButtonInit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldHost = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jCheckBoxPoll = new javax.swing.JCheckBox();
        jTextFieldPollTime = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButtonOpenGripper = new javax.swing.JButton();
        jButtonCloseGripper = new javax.swing.JButton();
        jButtonRecordPoint = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelMoveTo = new javax.swing.JPanel();
        jButtonMoveTo = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableMoveToPose = new javax.swing.JTable();
        jCheckBoxStraight = new javax.swing.JCheckBox();
        jButtonMoveToCurrent = new javax.swing.JButton();
        jPanelProgram = new javax.swing.JPanel();
        jScrollPaneProgram = new javax.swing.JScrollPane();
        jTableProgram = new javax.swing.JTable();
        jButtonProgramPause = new javax.swing.JButton();
        jButtonProgramAbort = new javax.swing.JButton();
        jButtonEditProgramItem = new javax.swing.JButton();
        jButtonDeletProgramItem = new javax.swing.JButton();
        jButtonAddProgramItem = new javax.swing.JButton();
        jButtonProgramRun = new javax.swing.JButton();
        jButtonResume = new javax.swing.JButton();
        jButtonPlotProgramItem = new javax.swing.JButton();
        jButtonRunProgFromCurrentLine = new javax.swing.JButton();
        jButtonStepBack = new javax.swing.JButton();
        jButtonStepFwd = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldRunTime = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaSelectedProgramCommand = new javax.swing.JTextArea();
        jPanelJogging = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jPanelJogMinus = new javax.swing.JPanel();
        jLabelJogMinus = new javax.swing.JLabel();
        jPanelJogPlus = new javax.swing.JPanel();
        jLabelJogPlus = new javax.swing.JLabel();
        jComboBoxXYZRPY = new javax.swing.JComboBox();
        jPanelJogPlus1 = new javax.swing.JPanel();
        jLabelJogPlus1 = new javax.swing.JLabel();
        jPanelJogMinus1 = new javax.swing.JPanel();
        jLabelJogMinus1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldJointJogIncrement = new javax.swing.JTextField();
        jTextFieldXYZJogIncrement = new javax.swing.JTextField();
        jTextFieldRPYJogIncrement = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldJogInterval = new javax.swing.JTextField();
        lengthUnitComboBoxLengthUnit = new crcl.ui.LengthUnitComboBox();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldJointJogSpeed = new javax.swing.JTextField();
        jTextFieldTransSpeed = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldRotationSpeed = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jMenuBarReplaceCommandState = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemOpenXmlCommandInstance = new javax.swing.JMenuItem();
        jMenuCommandRecent = new javax.swing.JMenu();
        jMenuItemOpenXmlProgram = new javax.swing.JMenuItem();
        jMenuRecentProgram = new javax.swing.JMenu();
        jMenuItemSaveProgramAs = new javax.swing.JMenuItem();
        jMenuItemClearRecordedPoints = new javax.swing.JMenuItem();
        jMenuItemSavePoseList = new javax.swing.JMenuItem();
        jMenuItemLoadPrefs = new javax.swing.JMenuItem();
        jMenuItemSavePrefs = new javax.swing.JMenuItem();
        jMenuItemViewLogFile = new javax.swing.JMenuItem();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuTools = new javax.swing.JMenu();
        jMenuItemXPathQuery = new javax.swing.JMenuItem();
        jCheckBoxMenuItemPlotXYZ = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemJoints = new javax.swing.JCheckBoxMenuItem();
        jMenuItemRunTest = new javax.swing.JMenuItem();
        jCheckBoxMenuItemRecordPoseList = new javax.swing.JCheckBoxMenuItem();
        jMenuItemPoseList3DPlot = new javax.swing.JMenuItem();
        jMenuItemOpenStatusLog = new javax.swing.JMenuItem();
        jMenuItemShowCommandLog = new javax.swing.JMenuItem();
        jMenuCmds = new javax.swing.JMenu();
        jMenuXmlSchemas = new javax.swing.JMenu();
        jMenuItemSetSchemaFiles = new javax.swing.JMenuItem();
        jCheckBoxMenuItemValidateXml = new javax.swing.JCheckBoxMenuItem();
        jMenuOptions = new javax.swing.JMenu();
        jCheckBoxMenuItemReplaceState = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDebugWaitForDone = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDebugSendCommand = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDebugReadStatus = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemUseEXI = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemUseReadStatusThread = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemRecordCommands = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemQuitProgramOnTestCommandFail = new javax.swing.JCheckBoxMenuItem();

        FormListener formListener = new FormListener();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CRCL Pendant Client");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Status"));

        jLabel3.setText("Command ID:");

        jTextFieldStatCmdID.setEditable(false);
        jTextFieldStatCmdID.setText("0");

        jTableJoints.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), null, null, null},
                { new Integer(2), null, null, null},
                { new Integer(3), null, null, null},
                { new Integer(4), null, null, null},
                { new Integer(5), null, null, null},
                { new Integer(6), null, null, null}
            },
            new String [] {
                "Joint", "Position", "Velocity", "TorqueOrForce"
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
        jScrollPane2.setViewportView(jTableJoints);

        jLabel6.setText("Status : ");

        jTablePose.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"X", null},
                {"Y", null},
                {"Z", null},
                {"XI", null},
                {"XJ", null},
                {"XK", null},
                {"ZI", null},
                {"ZJ", null},
                {"Zk", null},
                {"Roll", null},
                {"Pitch", null},
                {"Yaw", null}
            },
            new String [] {
                "Pose Axis", "Position"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTablePose);

        jTextFieldStatusID.setText("0");

        jLabel10.setText("Status ID:");

        jLabel18.setText("State Description:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldStatCmdID, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(jTextFieldStatus)
                            .addComponent(jTextFieldStatusID)
                            .addComponent(jTextFieldStatusStateDescription))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldStatCmdID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldStatusID, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldStatusStateDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Errors"));

        jTextAreaErrors.setColumns(20);
        jTextAreaErrors.setLineWrap(true);
        jTextAreaErrors.setRows(5);
        jScrollPane1.setViewportView(jTextAreaErrors);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Connection")));

        jTextFieldPort.setText("64444");

        jButtonConnect.setText("Connect");
        jButtonConnect.addActionListener(formListener);

        jButtonDisconnect.setText("Disconnect ");
        jButtonDisconnect.setEnabled(false);
        jButtonDisconnect.addActionListener(formListener);

        jButtonEnd.setText("End");
        jButtonEnd.setEnabled(false);
        jButtonEnd.addActionListener(formListener);

        jButtonInit.setText("Init");
        jButtonInit.setEnabled(false);
        jButtonInit.addActionListener(formListener);

        jLabel1.setText("Host: ");

        jTextFieldHost.setText("localhost");

        jLabel2.setText("Port:");

        jCheckBoxPoll.setSelected(true);
        jCheckBoxPoll.setText("Poll");
        jCheckBoxPoll.addActionListener(formListener);

        jTextFieldPollTime.setText("50");
        jTextFieldPollTime.addActionListener(formListener);

        jLabel4.setText("Poll interval(ms):");

        jButtonOpenGripper.setText("Open Gripper");
        jButtonOpenGripper.setEnabled(false);
        jButtonOpenGripper.addActionListener(formListener);

        jButtonCloseGripper.setText("Close Gripper");
        jButtonCloseGripper.setEnabled(false);
        jButtonCloseGripper.addActionListener(formListener);

        jButtonRecordPoint.setText("Record Point");
        jButtonRecordPoint.addActionListener(formListener);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldHost, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButtonConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDisconnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxPoll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPollTime, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButtonInit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEnd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRecordPoint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonOpenGripper)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCloseGripper)))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonConnect, jButtonDisconnect});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConnect)
                    .addComponent(jButtonDisconnect)
                    .addComponent(jCheckBoxPoll)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldPollTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonInit)
                    .addComponent(jButtonEnd)
                    .addComponent(jButtonRecordPoint)
                    .addComponent(jButtonOpenGripper)
                    .addComponent(jButtonCloseGripper))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTabbedPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane1.setName(""); // NOI18N
        jTabbedPane1.addChangeListener(formListener);

        jPanelMoveTo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanelMoveTo.setName("MoveTo"); // NOI18N

        jButtonMoveTo.setText("MoveTo");
        jButtonMoveTo.setEnabled(false);
        jButtonMoveTo.addActionListener(formListener);

        jTableMoveToPose.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"X", null},
                {"Y", null},
                {"Z", null},
                {"XI",  new Double(1.0)},
                {"XJ",  new Double(0.0)},
                {"XK",  new Double(0.0)},
                {"ZI",  new Double(0.0)},
                {"ZJ",  new Double(0.0)},
                {"ZK",  new Double(1.0)}
            },
            new String [] {
                "Pose Axis", "Position"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTableMoveToPose);

        jCheckBoxStraight.setSelected(true);
        jCheckBoxStraight.setText("Straight");

        jButtonMoveToCurrent.setText("Current");
        jButtonMoveToCurrent.addActionListener(formListener);

        javax.swing.GroupLayout jPanelMoveToLayout = new javax.swing.GroupLayout(jPanelMoveTo);
        jPanelMoveTo.setLayout(jPanelMoveToLayout);
        jPanelMoveToLayout.setHorizontalGroup(
            jPanelMoveToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMoveToLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMoveToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMoveToLayout.createSequentialGroup()
                        .addComponent(jButtonMoveTo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxStraight)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonMoveToCurrent))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMoveToLayout.setVerticalGroup(
            jPanelMoveToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMoveToLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMoveToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonMoveTo)
                    .addComponent(jCheckBoxStraight)
                    .addComponent(jButtonMoveToCurrent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("MoveTo", jPanelMoveTo);

        jTableProgram.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Long(1), "InitCanonType",  new Long(-1),  new Double(0.0), null},
                { new Long(2), "EndCanonType",  new Long(-1), null, null}
            },
            new String [] {
                "ID", "Type", "Time To Execute(ms)", "Distance Moved", "Result"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.Long.class, java.lang.Double.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableProgram.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPaneProgram.setViewportView(jTableProgram);

        jButtonProgramPause.setText("Pause");
        jButtonProgramPause.setEnabled(false);
        jButtonProgramPause.addActionListener(formListener);

        jButtonProgramAbort.setText("Abort");
        jButtonProgramAbort.addActionListener(formListener);

        jButtonEditProgramItem.setText("Edit Item");
        jButtonEditProgramItem.addActionListener(formListener);

        jButtonDeletProgramItem.setText("Delete Item");
        jButtonDeletProgramItem.addActionListener(formListener);

        jButtonAddProgramItem.setText("Add Item");
        jButtonAddProgramItem.addActionListener(formListener);

        jButtonProgramRun.setText("Run From Start");
        jButtonProgramRun.addActionListener(formListener);

        jButtonResume.setText("Resume");
        jButtonResume.setEnabled(false);
        jButtonResume.addActionListener(formListener);

        jButtonPlotProgramItem.setText("Plot");
        jButtonPlotProgramItem.addActionListener(formListener);

        jButtonRunProgFromCurrentLine.setText("Run from Current Line");
        jButtonRunProgFromCurrentLine.addActionListener(formListener);

        jButtonStepBack.setText("Step Back");
        jButtonStepBack.addActionListener(formListener);

        jButtonStepFwd.setText("Step Fwd");
        jButtonStepFwd.addActionListener(formListener);

        jLabel12.setText("Run Time:");

        jTextFieldRunTime.setEditable(false);
        jTextFieldRunTime.setText("-1.0");

        jLabel13.setText("Selected Command:");

        jTextAreaSelectedProgramCommand.setEditable(false);
        jTextAreaSelectedProgramCommand.setColumns(20);
        jTextAreaSelectedProgramCommand.setRows(5);
        jScrollPane5.setViewportView(jTextAreaSelectedProgramCommand);

        javax.swing.GroupLayout jPanelProgramLayout = new javax.swing.GroupLayout(jPanelProgram);
        jPanelProgram.setLayout(jPanelProgramLayout);
        jPanelProgramLayout.setHorizontalGroup(
            jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProgramLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPaneProgram, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelProgramLayout.createSequentialGroup()
                        .addComponent(jButtonEditProgramItem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDeletProgramItem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAddProgramItem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPlotProgramItem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonStepBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonStepFwd))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelProgramLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldRunTime))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelProgramLayout.createSequentialGroup()
                        .addGroup(jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelProgramLayout.createSequentialGroup()
                                .addComponent(jButtonProgramPause)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonResume)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonProgramAbort)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonProgramRun)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonRunProgFromCurrentLine))
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelProgramLayout.setVerticalGroup(
            jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProgramLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneProgram, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addGap(7, 7, 7)
                .addGroup(jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextFieldRunTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonProgramPause)
                    .addComponent(jButtonProgramAbort)
                    .addComponent(jButtonProgramRun)
                    .addComponent(jButtonResume)
                    .addComponent(jButtonRunProgFromCurrentLine))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEditProgramItem)
                    .addComponent(jButtonDeletProgramItem)
                    .addComponent(jButtonAddProgramItem)
                    .addComponent(jButtonPlotProgramItem)
                    .addComponent(jButtonStepBack)
                    .addComponent(jButtonStepFwd))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Program", jPanelProgram);

        jPanelJogging.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanelJogging.setName("Jogging"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Joint 1", "Joint 2", "Joint 3", "Joint 4", "Joint 5", "Joint 6" }));

        jPanelJogMinus.setBackground(new java.awt.Color(255, 255, 255));
        jPanelJogMinus.setBorder(new javax.swing.border.MatteBorder(null));
        jPanelJogMinus.addMouseListener(formListener);

        jLabelJogMinus.setBackground(new java.awt.Color(255, 255, 255));
        jLabelJogMinus.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabelJogMinus.setText("Jog -");
        jLabelJogMinus.addMouseListener(formListener);

        javax.swing.GroupLayout jPanelJogMinusLayout = new javax.swing.GroupLayout(jPanelJogMinus);
        jPanelJogMinus.setLayout(jPanelJogMinusLayout);
        jPanelJogMinusLayout.setHorizontalGroup(
            jPanelJogMinusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJogMinusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelJogMinus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelJogMinusLayout.setVerticalGroup(
            jPanelJogMinusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJogMinusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelJogMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelJogPlus.setBackground(new java.awt.Color(255, 255, 255));
        jPanelJogPlus.setBorder(new javax.swing.border.MatteBorder(null));

        jLabelJogPlus.setBackground(new java.awt.Color(255, 255, 255));
        jLabelJogPlus.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabelJogPlus.setText("Jog +");
        jLabelJogPlus.addMouseListener(formListener);

        javax.swing.GroupLayout jPanelJogPlusLayout = new javax.swing.GroupLayout(jPanelJogPlus);
        jPanelJogPlus.setLayout(jPanelJogPlusLayout);
        jPanelJogPlusLayout.setHorizontalGroup(
            jPanelJogPlusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJogPlusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelJogPlus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelJogPlusLayout.setVerticalGroup(
            jPanelJogPlusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJogPlusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelJogPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jComboBoxXYZRPY.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "X", "Y", "Z", "Roll", "Pitch", "Yaw", " " }));

        jPanelJogPlus1.setBackground(new java.awt.Color(255, 255, 255));
        jPanelJogPlus1.setBorder(new javax.swing.border.MatteBorder(null));

        jLabelJogPlus1.setBackground(new java.awt.Color(255, 255, 255));
        jLabelJogPlus1.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabelJogPlus1.setText("Jog +");
        jLabelJogPlus1.addMouseListener(formListener);

        javax.swing.GroupLayout jPanelJogPlus1Layout = new javax.swing.GroupLayout(jPanelJogPlus1);
        jPanelJogPlus1.setLayout(jPanelJogPlus1Layout);
        jPanelJogPlus1Layout.setHorizontalGroup(
            jPanelJogPlus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJogPlus1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelJogPlus1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelJogPlus1Layout.setVerticalGroup(
            jPanelJogPlus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJogPlus1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelJogPlus1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelJogMinus1.setBackground(new java.awt.Color(255, 255, 255));
        jPanelJogMinus1.setBorder(new javax.swing.border.MatteBorder(null));
        jPanelJogMinus1.addMouseListener(formListener);

        jLabelJogMinus1.setBackground(new java.awt.Color(255, 255, 255));
        jLabelJogMinus1.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabelJogMinus1.setText("Jog -");
        jLabelJogMinus1.addMouseListener(formListener);

        javax.swing.GroupLayout jPanelJogMinus1Layout = new javax.swing.GroupLayout(jPanelJogMinus1);
        jPanelJogMinus1.setLayout(jPanelJogMinus1Layout);
        jPanelJogMinus1Layout.setHorizontalGroup(
            jPanelJogMinus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJogMinus1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelJogMinus1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelJogMinus1Layout.setVerticalGroup(
            jPanelJogMinus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJogMinus1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelJogMinus1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setText("Joint Jog Increment: ");

        jLabel7.setText("XYZ Jog Increment:");

        jLabel8.setText("RPY Jog Increment:");

        jTextFieldJointJogIncrement.setText("3.0");
        jTextFieldJointJogIncrement.addActionListener(formListener);

        jTextFieldXYZJogIncrement.setText("3.0");
        jTextFieldXYZJogIncrement.addActionListener(formListener);

        jTextFieldRPYJogIncrement.setText("3.0");
        jTextFieldRPYJogIncrement.addActionListener(formListener);

        jLabel9.setText("Jog Time Period (ms) :");

        jTextFieldJogInterval.setText("100");
        jTextFieldJogInterval.addActionListener(formListener);

        lengthUnitComboBoxLengthUnit.addActionListener(formListener);

        jLabel11.setText("Joint Jog Speed:");

        jTextFieldJointJogSpeed.setText("100");
        jTextFieldJointJogSpeed.addActionListener(formListener);

        jTextFieldTransSpeed.setText("3.0");
        jTextFieldTransSpeed.addActionListener(formListener);

        jLabel14.setText("Trans Speed:");

        jLabel15.setText("Rotation Speed:");

        jTextFieldRotationSpeed.setText("3.0");
        jTextFieldRotationSpeed.addActionListener(formListener);

        jLabel16.setText("degrees");

        jLabel17.setText("degrees/second");

        javax.swing.GroupLayout jPanelJoggingLayout = new javax.swing.GroupLayout(jPanelJogging);
        jPanelJogging.setLayout(jPanelJoggingLayout);
        jPanelJoggingLayout.setHorizontalGroup(
            jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJoggingLayout.createSequentialGroup()
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelJoggingLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelJoggingLayout.createSequentialGroup()
                                .addComponent(jComboBoxXYZRPY, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelJogMinus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelJogPlus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelJoggingLayout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelJogMinus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelJogPlus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelJoggingLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel14)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldJogInterval, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldJointJogSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldJointJogIncrement, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldRPYJogIncrement, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTransSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldXYZJogIncrement, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldRotationSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lengthUnitComboBoxLengthUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))))
                .addContainerGap(198, Short.MAX_VALUE))
        );

        jPanelJoggingLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextFieldJogInterval, jTextFieldJointJogIncrement, jTextFieldJointJogSpeed, jTextFieldRPYJogIncrement, jTextFieldTransSpeed, jTextFieldXYZJogIncrement});

        jPanelJoggingLayout.setVerticalGroup(
            jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJoggingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelJogMinus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1)
                    .addComponent(jPanelJogPlus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelJogMinus1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxXYZRPY)
                    .addComponent(jPanelJogPlus1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldXYZJogIncrement, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lengthUnitComboBoxLengthUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTransSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRPYJogIncrement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRotationSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldJointJogIncrement, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jTextFieldJointJogSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldJogInterval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(128, Short.MAX_VALUE))
        );

        jPanelJoggingLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextFieldJogInterval, jTextFieldJointJogIncrement, jTextFieldJointJogSpeed, jTextFieldRPYJogIncrement, jTextFieldTransSpeed, jTextFieldXYZJogIncrement});

        jTabbedPane1.addTab("Jog", jPanelJogging);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jTabbedPane1))
        );

        jMenu1.setText("File");
        jMenu1.addActionListener(formListener);

        jMenuItemOpenXmlCommandInstance.setText("Open CRCL XML Command Instance File ... ");
        jMenuItemOpenXmlCommandInstance.addActionListener(formListener);
        jMenu1.add(jMenuItemOpenXmlCommandInstance);

        jMenuCommandRecent.setText("Recent CRCL XML Command Instance Files");
        jMenu1.add(jMenuCommandRecent);

        jMenuItemOpenXmlProgram.setText("Open CRCL XML Program File ...");
        jMenuItemOpenXmlProgram.addActionListener(formListener);
        jMenu1.add(jMenuItemOpenXmlProgram);

        jMenuRecentProgram.setText("Recent CRCL XML Program File ...");
        jMenu1.add(jMenuRecentProgram);

        jMenuItemSaveProgramAs.setText("Save Recorded Points Program As ...");
        jMenuItemSaveProgramAs.addActionListener(formListener);
        jMenu1.add(jMenuItemSaveProgramAs);

        jMenuItemClearRecordedPoints.setText("Clear Recorded Points");
        jMenuItemClearRecordedPoints.addActionListener(formListener);
        jMenu1.add(jMenuItemClearRecordedPoints);

        jMenuItemSavePoseList.setText("Save Pose List ...");
        jMenuItemSavePoseList.addActionListener(formListener);
        jMenu1.add(jMenuItemSavePoseList);

        jMenuItemLoadPrefs.setText("Load Preferences File ...");
        jMenuItemLoadPrefs.addActionListener(formListener);
        jMenu1.add(jMenuItemLoadPrefs);

        jMenuItemSavePrefs.setText("Save Preferences File ...");
        jMenuItemSavePrefs.addActionListener(formListener);
        jMenu1.add(jMenuItemSavePrefs);

        jMenuItemViewLogFile.setText("View Log File ");
        jMenuItemViewLogFile.addActionListener(formListener);
        jMenu1.add(jMenuItemViewLogFile);

        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(formListener);
        jMenu1.add(jMenuItemExit);

        jMenuBarReplaceCommandState.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBarReplaceCommandState.add(jMenu2);

        jMenuTools.setText(" Tools ");

        jMenuItemXPathQuery.setText("XPath Status  Query ... ");
        jMenuItemXPathQuery.addActionListener(formListener);
        jMenuTools.add(jMenuItemXPathQuery);

        jCheckBoxMenuItemPlotXYZ.setText("2D Plot XYZ vs Time ...");
        jCheckBoxMenuItemPlotXYZ.addActionListener(formListener);
        jMenuTools.add(jCheckBoxMenuItemPlotXYZ);

        jCheckBoxMenuItemJoints.setText("Plot Joints");
        jCheckBoxMenuItemJoints.addActionListener(formListener);
        jMenuTools.add(jCheckBoxMenuItemJoints);

        jMenuItemRunTest.setText("Run Test");
        jMenuItemRunTest.addActionListener(formListener);
        jMenuTools.add(jMenuItemRunTest);

        jCheckBoxMenuItemRecordPoseList.setSelected(true);
        jCheckBoxMenuItemRecordPoseList.setText("Record Pose List");
        jMenuTools.add(jCheckBoxMenuItemRecordPoseList);

        jMenuItemPoseList3DPlot.setText("3D Pose List Plot ...");
        jMenuItemPoseList3DPlot.addActionListener(formListener);
        jMenuTools.add(jMenuItemPoseList3DPlot);

        jMenuItemOpenStatusLog.setText("Open Status Log ...");
        jMenuItemOpenStatusLog.addActionListener(formListener);
        jMenuTools.add(jMenuItemOpenStatusLog);

        jMenuItemShowCommandLog.setText("Show Command Log ...");
        jMenuItemShowCommandLog.addActionListener(formListener);
        jMenuTools.add(jMenuItemShowCommandLog);

        jMenuBarReplaceCommandState.add(jMenuTools);

        jMenuCmds.setText("Commands");
        jMenuBarReplaceCommandState.add(jMenuCmds);

        jMenuXmlSchemas.setText("XML Schemas");

        jMenuItemSetSchemaFiles.setText("Set XML Schema Files ... ");
        jMenuItemSetSchemaFiles.addActionListener(formListener);
        jMenuXmlSchemas.add(jMenuItemSetSchemaFiles);

        jCheckBoxMenuItemValidateXml.setSelected(true);
        jCheckBoxMenuItemValidateXml.setText("Validate using Schemas");
        jMenuXmlSchemas.add(jCheckBoxMenuItemValidateXml);

        jMenuBarReplaceCommandState.add(jMenuXmlSchemas);

        jMenuOptions.setText("Options");

        jCheckBoxMenuItemReplaceState.setText("Replace Ready,Done,.. with CRCL_Ready,CRCL_DONE ...");
        jMenuOptions.add(jCheckBoxMenuItemReplaceState);

        jCheckBoxMenuItemDebugWaitForDone.setText("Debug waitForDone()");
        jMenuOptions.add(jCheckBoxMenuItemDebugWaitForDone);

        jCheckBoxMenuItemDebugSendCommand.setText("Debug sendCommand()");
        jMenuOptions.add(jCheckBoxMenuItemDebugSendCommand);

        jCheckBoxMenuItemDebugReadStatus.setText("Debug  readStatus() ");
        jMenuOptions.add(jCheckBoxMenuItemDebugReadStatus);

        jCheckBoxMenuItemUseEXI.setText("USE EXI (Efficient XML Interchange)");
        jCheckBoxMenuItemUseEXI.addActionListener(formListener);
        jMenuOptions.add(jCheckBoxMenuItemUseEXI);

        jCheckBoxMenuItemUseReadStatusThread.setText("Use seperate read status thread.");
        jCheckBoxMenuItemUseReadStatusThread.addActionListener(formListener);
        jMenuOptions.add(jCheckBoxMenuItemUseReadStatusThread);

        jCheckBoxMenuItemRecordCommands.setSelected(true);
        jCheckBoxMenuItemRecordCommands.setText("Record Commands");
        jCheckBoxMenuItemRecordCommands.addActionListener(formListener);
        jMenuOptions.add(jCheckBoxMenuItemRecordCommands);

        jCheckBoxMenuItemQuitProgramOnTestCommandFail.setSelected(true);
        jCheckBoxMenuItemQuitProgramOnTestCommandFail.setText("Quit Program on Test Command Fail");
        jCheckBoxMenuItemQuitProgramOnTestCommandFail.addActionListener(formListener);
        jMenuOptions.add(jCheckBoxMenuItemQuitProgramOnTestCommandFail);

        jMenuBarReplaceCommandState.add(jMenuOptions);

        setJMenuBar(jMenuBarReplaceCommandState);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.MouseListener, javax.swing.event.ChangeListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jButtonConnect) {
                PendantClient.this.jButtonConnectActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonDisconnect) {
                PendantClient.this.jButtonDisconnectActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonEnd) {
                PendantClient.this.jButtonEndActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonInit) {
                PendantClient.this.jButtonInitActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxPoll) {
                PendantClient.this.jCheckBoxPollActionPerformed(evt);
            }
            else if (evt.getSource() == jTextFieldPollTime) {
                PendantClient.this.jTextFieldPollTimeActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonOpenGripper) {
                PendantClient.this.jButtonOpenGripperActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonCloseGripper) {
                PendantClient.this.jButtonCloseGripperActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonRecordPoint) {
                PendantClient.this.jButtonRecordPointActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonMoveTo) {
                PendantClient.this.jButtonMoveToActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonMoveToCurrent) {
                PendantClient.this.jButtonMoveToCurrentActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonProgramPause) {
                PendantClient.this.jButtonProgramPauseActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonProgramAbort) {
                PendantClient.this.jButtonProgramAbortActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonEditProgramItem) {
                PendantClient.this.jButtonEditProgramItemActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonDeletProgramItem) {
                PendantClient.this.jButtonDeletProgramItemActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonAddProgramItem) {
                PendantClient.this.jButtonAddProgramItemActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonProgramRun) {
                PendantClient.this.jButtonProgramRunActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonResume) {
                PendantClient.this.jButtonResumeActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonPlotProgramItem) {
                PendantClient.this.jButtonPlotProgramItemActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonRunProgFromCurrentLine) {
                PendantClient.this.jButtonRunProgFromCurrentLineActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonStepBack) {
                PendantClient.this.jButtonStepBackActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonStepFwd) {
                PendantClient.this.jButtonStepFwdActionPerformed(evt);
            }
            else if (evt.getSource() == jTextFieldJointJogIncrement) {
                PendantClient.this.jTextFieldJointJogIncrementActionPerformed(evt);
            }
            else if (evt.getSource() == jTextFieldXYZJogIncrement) {
                PendantClient.this.jTextFieldXYZJogIncrementActionPerformed(evt);
            }
            else if (evt.getSource() == jTextFieldRPYJogIncrement) {
                PendantClient.this.jTextFieldRPYJogIncrementActionPerformed(evt);
            }
            else if (evt.getSource() == jTextFieldJogInterval) {
                PendantClient.this.jTextFieldJogIntervalActionPerformed(evt);
            }
            else if (evt.getSource() == lengthUnitComboBoxLengthUnit) {
                PendantClient.this.lengthUnitComboBoxLengthUnitActionPerformed(evt);
            }
            else if (evt.getSource() == jTextFieldJointJogSpeed) {
                PendantClient.this.jTextFieldJointJogSpeedActionPerformed(evt);
            }
            else if (evt.getSource() == jTextFieldTransSpeed) {
                PendantClient.this.jTextFieldTransSpeedActionPerformed(evt);
            }
            else if (evt.getSource() == jTextFieldRotationSpeed) {
                PendantClient.this.jTextFieldRotationSpeedActionPerformed(evt);
            }
            else if (evt.getSource() == jMenu1) {
                PendantClient.this.jMenu1ActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemOpenXmlCommandInstance) {
                PendantClient.this.jMenuItemOpenXmlCommandInstanceActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemOpenXmlProgram) {
                PendantClient.this.jMenuItemOpenXmlProgramActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemSaveProgramAs) {
                PendantClient.this.jMenuItemSaveProgramAsActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemClearRecordedPoints) {
                PendantClient.this.jMenuItemClearRecordedPointsActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemSavePoseList) {
                PendantClient.this.jMenuItemSavePoseListActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemLoadPrefs) {
                PendantClient.this.jMenuItemLoadPrefsActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemSavePrefs) {
                PendantClient.this.jMenuItemSavePrefsActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemViewLogFile) {
                PendantClient.this.jMenuItemViewLogFileActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemExit) {
                PendantClient.this.jMenuItemExitActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemXPathQuery) {
                PendantClient.this.jMenuItemXPathQueryActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemPlotXYZ) {
                PendantClient.this.jCheckBoxMenuItemPlotXYZActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemJoints) {
                PendantClient.this.jCheckBoxMenuItemJointsActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemRunTest) {
                PendantClient.this.jMenuItemRunTestActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemPoseList3DPlot) {
                PendantClient.this.jMenuItemPoseList3DPlotActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemOpenStatusLog) {
                PendantClient.this.jMenuItemOpenStatusLogActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemShowCommandLog) {
                PendantClient.this.jMenuItemShowCommandLogActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemSetSchemaFiles) {
                PendantClient.this.jMenuItemSetSchemaFilesActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemUseEXI) {
                PendantClient.this.jCheckBoxMenuItemUseEXIActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemUseReadStatusThread) {
                PendantClient.this.jCheckBoxMenuItemUseReadStatusThreadActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemRecordCommands) {
                PendantClient.this.jCheckBoxMenuItemRecordCommandsActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemQuitProgramOnTestCommandFail) {
                PendantClient.this.jCheckBoxMenuItemQuitProgramOnTestCommandFailActionPerformed(evt);
            }
        }

        public void mouseClicked(java.awt.event.MouseEvent evt) {
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == jLabelJogMinus) {
                PendantClient.this.jLabelJogMinusMouseExited(evt);
            }
            else if (evt.getSource() == jLabelJogPlus) {
                PendantClient.this.jLabelJogPlusMouseExited(evt);
            }
            else if (evt.getSource() == jLabelJogPlus1) {
                PendantClient.this.jLabelJogPlus1MouseExited(evt);
            }
            else if (evt.getSource() == jLabelJogMinus1) {
                PendantClient.this.jLabelJogMinus1MouseExited(evt);
            }
        }

        public void mousePressed(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == jPanelJogMinus) {
                PendantClient.this.jPanelJogMinusMousePressed(evt);
            }
            else if (evt.getSource() == jLabelJogMinus) {
                PendantClient.this.jLabelJogMinusMousePressed(evt);
            }
            else if (evt.getSource() == jLabelJogPlus) {
                PendantClient.this.jLabelJogPlusMousePressed(evt);
            }
            else if (evt.getSource() == jLabelJogPlus1) {
                PendantClient.this.jLabelJogPlus1MousePressed(evt);
            }
            else if (evt.getSource() == jPanelJogMinus1) {
                PendantClient.this.jPanelJogMinus1MousePressed(evt);
            }
            else if (evt.getSource() == jLabelJogMinus1) {
                PendantClient.this.jLabelJogMinus1MousePressed(evt);
            }
        }

        public void mouseReleased(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == jLabelJogMinus) {
                PendantClient.this.jLabelJogMinusMouseReleased(evt);
            }
            else if (evt.getSource() == jLabelJogPlus) {
                PendantClient.this.jLabelJogPlusMouseReleased(evt);
            }
            else if (evt.getSource() == jLabelJogPlus1) {
                PendantClient.this.jLabelJogPlus1MouseReleased(evt);
            }
            else if (evt.getSource() == jLabelJogMinus1) {
                PendantClient.this.jLabelJogMinus1MouseReleased(evt);
            }
        }

        public void stateChanged(javax.swing.event.ChangeEvent evt) {
            if (evt.getSource() == jTabbedPane1) {
                PendantClient.this.jTabbedPane1StateChanged(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private final static File statSchemasFile = new File(System.getProperty("user.home"),
            ".crcljava_pendantclient_stat_schemas.txt");

    private final static File cmdSchemasFile = new File(System.getProperty("user.home"),
            ".crcljava_pendantclient_cmd_schemas.txt");

    private final static File programSchemasFile = new File(System.getProperty("user.home"),
            ".crcljava_pendantclient_cmd_schemas.txt");

    private boolean showing_message = false;
    private volatile long last_message_show_time = 0;

    @Override
    public void showMessage(final String s) {
        if (showDebugMessage(s)) {
            return;
        }

        if (showing_message) {
            return;
        }
        showing_message = true;
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                long t = System.currentTimeMillis();
                if (t - last_message_show_time > 5000) {
                    last_message_show_time = System.currentTimeMillis();
                    MultiLineStringJPanel.showText(s, PendantClient.this, "Message from Client", true);
                }
                last_message_show_time = System.currentTimeMillis();
                showing_message = false;
            }
        });
    }

    @Override
    public boolean showDebugMessage(final String s) {
        final String sWithThread = "Thread:" + Thread.currentThread().getName() + " " + s;
        LOGGER.log(Level.FINE, sWithThread);
        if (!this.isVisible()) {
            return true;
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                jTextAreaErrors.append("\n" + sWithThread);
            }
        });
        return false;
    }

    @Override
    public void showMessage(Throwable t) {
        this.showMessage(t.toString());
    }

    double last_t_pos_logged = 0;

    final Map<BigInteger, BigDecimal> last_joints = new HashMap<>();

    private boolean jointsChanged(List<JointStatusType> jsl) {
        if (jsl.size() != last_joints.values().size()) {
            return true;
        }
        for (JointStatusType jst : jsl) {
            BigDecimal D = last_joints.get(jst.getJointNumber());
            if (null == D) {
                return true;
            }
            if (!D.equals(jst.getJointPosition())) {
                return true;
            }
        }
        return false;
    }

    private void copyJointPositions(List<JointStatusType> jsl) {
        this.last_joints.clear();
        for (JointStatusType jst : jsl) {
            this.last_joints.put(jst.getJointNumber(), jst.getJointPosition());
        }
    }

    public void setStatus(CRCLStatusType _status) {
        internal.setStatus(_status);
    }

    @Override
    public void checkXmlQuery(CRCLSocket crclSocket) {
        if (null != this.xqJFrame && this.xqJFrame.isUpdateAutomaticallySelected()) {
            String q = this.xqJFrame.getQuery();
            if (q != null && q.length() > 0) {
                xqJFrame.runQuery(q, crclSocket.getLastStatusString());
            }
        }
    }

    @Override
    public void finishConnect() {
        this.jButtonConnect.setEnabled(false);
        this.jButtonDisconnect.setEnabled(true);
        this.jButtonEnd.setEnabled(true);
        this.jButtonInit.setEnabled(true);
        this.jButtonMoveTo.setEnabled(true);
        this.jButtonCloseGripper.setEnabled(true);
        this.jButtonOpenGripper.setEnabled(true);
        if (this.jCheckBoxPoll.isSelected()) {
            this.startPollTimer();
        }
    }

    @Override
    public void finishSetStatus() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                PendantClient.this.finishSetStatusPriv();
            }
        });
    }

    private void finishSetStatusPriv() {
        if (null != internal.getStatus() && null != internal.getStatus().getCommandStatus()) {
            CommandStatusType ccst = internal.getStatus().getCommandStatus();
            if (null != ccst) {
                if (null != ccst.getCommandID()) {
                    this.jTextFieldStatCmdID.setText(ccst.getCommandID().toString());
                }
                if (null != ccst.getCommandState()) {
                    this.jTextFieldStatus.setText(ccst.getCommandState().toString());
                }
                this.jTextFieldStatusID.setText(ccst.getStatusID().toString());
                String stateDescription = ccst.getStateDescription();
                if (null != stateDescription) {
                    this.jTextFieldStatusStateDescription.setText(ccst.getStateDescription());
                } else {
                    this.jTextFieldStatusStateDescription.setText("");
                }
            }
            JointStatusesType jsst = internal.getStatus().getJointStatuses();
            if (jsst != null) {
                List<JointStatusType> jsl = jsst.getJointStatus();
                boolean joints_changed = this.jointsChanged(jsl);
                if (joints_changed) {
                    this.copyJointPositions(jsl);
                    DefaultTableModel tm = (DefaultTableModel) this.jTableJoints.getModel();
                    double t = System.currentTimeMillis();
                    tm.setRowCount(jsl.size());
                    for (JointStatusType js : jsl) {
                        int jn = js.getJointNumber().intValue();
                        if (null != js.getJointVelocity()) {
                            tm.setValueAt(js.getJointVelocity().doubleValue(), jn - 1, 2);
                        }
                        if (null != js.getJointTorqueOrForce()) {
                            tm.setValueAt(js.getJointTorqueOrForce().doubleValue(), jn - 1, 3);
                        }
                        if (null == js.getJointPosition()) {
//                            tm.setValueAt(Double.NaN, jn-1,1);
                            continue;
                        }

                        double pos = js.getJointPosition().doubleValue();
                        tm.setValueAt(jn, jn - 1, 0);
                        tm.setValueAt(pos, jn - 1, 1);
                        if (this.jCheckBoxMenuItemJoints.isSelected()) {
                            if (null == this.jointsPlotter) {
                                jointsPlotter = new plotterJFrame();
                                jointsPlotter.setTitle("JOINTS");
                                jointsPlotter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                jointsPlotter.setVisible(true);
                            }
                            String pname = "joint[" + jn + "]";
                            PlotData pd = this.jointsPlotter.getPlotByName(pname);
                            if (null == pd) {
                                pd = new PlotData();
                                pd.name = pname;
                                this.jointsPlotter.AddPlot(pd, pname);
                            }
                            this.jointsPlotter.AddPointToPlot(pd, t, pos, rootPaneCheckingEnabled);
                            if (pd.get_num_points() < 100) {
                                this.jointsPlotter.FitToGraph();
                            }
                        }
                    }
                    if (null != this.jointsPlotter) {
                        this.jointsPlotter.ScrollRight();
                    }
                }
            }
            PoseType p
                    = Optional.ofNullable(internal)
                    .map(PendantClientInner::getStatus)
                    .map(CRCLPosemath::getPose)
                    .orElse(null);
            if (null != p) {
                updatePoseTable(p, this.jTablePose);
                PointType pt = p.getPoint();
                if (this.jCheckBoxMenuItemPlotXYZ.isSelected()) {
                    if (null == xyzPlotter) {
                        xyzPlotter = new plotterJFrame();
                        xyzPlotter.setTitle("XYZ");
                        xyzPlotter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        xyzPlotter.setVisible(true);
                    }
                    double t = System.currentTimeMillis();
//                    XMLGregorianCalendar xgc = p.getTimestamp();
//                    if (null != xgc) {
//                        double old_t = t;
//                        t = (double) xgc.toGregorianCalendar().getTime().getTime();
//                    }
                    if (t > this.last_t_pos_logged) {
                        PlotData xpd = xyzPlotter.getPlotByName("x");
                        if (null == xpd) {
                            xpd = new PlotData();
                            xpd.name = "x";
                            xyzPlotter.AddPlot(xpd, "x");
                        }
                        double x = pt.getX().doubleValue();
                        xyzPlotter.AddPointToPlot(xpd, t, x, true);
                        PlotData ypd = xyzPlotter.getPlotByName("y");
                        if (null == ypd) {
                            ypd = new PlotData();
                            ypd.name = "y";
                            xyzPlotter.AddPlot(xpd, "y");
                        }
                        double y = pt.getY().doubleValue();
                        xyzPlotter.AddPointToPlot(ypd, t, y, true);
                        PlotData zpd = xyzPlotter.getPlotByName("z");
                        if (null == zpd) {
                            zpd = new PlotData();
                            zpd.name = "x";
                            xyzPlotter.AddPlot(zpd, "z");
                        }
                        double z = pt.getZ().doubleValue();
                        xyzPlotter.AddPointToPlot(zpd, t, z, true);
                        if (xpd.get_num_points() < 100) {
                            xyzPlotter.FitToGraph();
                        }
                        xyzPlotter.ScrollRight();
                        xyzPlotter.repaint();
                        this.last_t_pos_logged = t;
                    }
                }

            }
        }
    }

    private void updatePoseTable(PoseType p, JTable jTable) {
        try {
            DefaultTableModel tm = (DefaultTableModel) jTable.getModel();
            PointType pt = p.getPoint();
            if (null != pt) {
                tm.setValueAt(pt.getX().doubleValue(), 0, 1);
                tm.setValueAt(pt.getY().doubleValue(), 1, 1);
                tm.setValueAt(pt.getZ().doubleValue(), 2, 1);
            }
            VectorType xv = p.getXAxis();
            if (null != xv) {
                tm.setValueAt(xv.getI().doubleValue(), 3, 1);
                tm.setValueAt(xv.getJ().doubleValue(), 4, 1);
                tm.setValueAt(xv.getK().doubleValue(), 5, 1);
            }
            VectorType zv = p.getZAxis();
            if (null != zv) {
                tm.setValueAt(zv.getI().doubleValue(), 6, 1);
                tm.setValueAt(zv.getJ().doubleValue(), 7, 1);
                tm.setValueAt(zv.getK().doubleValue(), 8, 1);
            }
            if (tm.getRowCount() >= 12) {
                PmRpy rpy = CRCLPosemath.toPmRpy(p);
                tm.setValueAt(Math.toDegrees(rpy.r), 9, 1);
                tm.setValueAt(Math.toDegrees(rpy.p), 10, 1);
                tm.setValueAt(Math.toDegrees(rpy.y), 11, 1);
            }
        } catch (PmException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    private void disconnect() {
        internal.disconnect();
        jogWorldSpeedsSet = false;
    }

    @Override
    public void finishDisconnect() {
        this.jButtonConnect.setEnabled(true);
        this.jButtonDisconnect.setEnabled(false);
        this.jButtonEnd.setEnabled(false);
        this.jButtonInit.setEnabled(false);
        this.jButtonMoveTo.setEnabled(false);
        this.jButtonCloseGripper.setEnabled(false);
        this.jButtonOpenGripper.setEnabled(false);
        this.stopPollTimer();
    }

    private final PendantClientInner internal;

    public boolean isConnected() {
        return internal.isConnected();
    }

    public void connect(String _host, int _port) {
        internal.connect(_host, _port);
        jogWorldSpeedsSet = false;
    }


    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemExitActionPerformed

//    JointControlModeEnumType jcme = null;
//
//    private void setJointControlModes(JointControlModeEnumType _jcme) {
//        if (this.jcme != _jcme) {
//            SetJointControlModesType setjcm = new SetJointControlModesType();
//            List<JointControlModeType> ljcm = setjcm.getJointControlMode();
//            JointStatusesType jsst = internal.getStatus().getJointStatuses();
//            if (jsst != null) {
//                List<JointStatusType> jsl = jsst.getJointStatus();
//                for (JointStatusType js : jsl) {
//                    JointControlModeType jcm = new JointControlModeType();
//                    jcm.setMode(_jcme);
//                    jcm.setJointNumber(js.getJointNumber());
//                    ljcm.shift(jcm);
//                }
//            }
//            cmdId = cmdId.shift(BigInteger.ONE);
//            setjcm.setCommandID(cmdId);
//            this.cmd_instance.setCRCLCommand(setjcm);
//            this.sendCommand();
//            this.jcme = _jcme;
//        }
//    }
    private javax.swing.Timer jog_timer = null;

    private double lastJogJointPos = Double.NEGATIVE_INFINITY;

    private void jogJointStart(final double increment) {
        if (null == internal.getCRCLSocket()
                || null == internal.getStatus()) {
            showMessage("Can not send command when not connected.");
            return;
        }
        internal.setJogIncrement(Double.valueOf(this.jTextFieldJointJogIncrement.getText()));
//        this.setJointControlModes(JointControlModeEnumType.POSITION);
        final int index = this.jComboBox1.getSelectedIndex() + 1;
        if (null != jog_timer) {
            jog_timer.stop();
            jog_timer = null;
        }
        lastJogJointPos = Double.NEGATIVE_INFINITY;
        jogStopFlag = false;
        ActionListener jogActionListener = new ActionListener() {

            private int apCount = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    apCount++;
                    if (jogStopFlag) {
                        return;
                    }
                    final JointStatusType js = CRCLSocket.getJointStatus(internal.getStatus(), index);
                    if (null == js) {
                        showMessage("Can't jog without joint position internal.getStatus() for joint " + index);
                        return;
                    }

                    if (internal.getStatus().getCommandStatus().getCommandState() == CommandStateEnumType.CRCL_ERROR) {
                        showMessage("Can't when status commandState = " + CommandStateEnumType.CRCL_ERROR);
                        jogStop();
                    }
                    ActuateJointsType ajst = new ActuateJointsType();
                    List<ActuateJointType> ajl = ajst.getActuateJoint();
                    ActuateJointType aj = new ActuateJointType();
                    aj.setJointNumber(js.getJointNumber());
                    double pos = js.getJointPosition().doubleValue();
                    if (Math.abs(pos - lastJogJointPos) <= Math.abs((increment) * 0.001)) {
                        return;
                    }
                    lastJogJointPos = pos;
                    aj.setJointPosition(js.getJointPosition().add(BigDecimal.valueOf(increment)));
                    JointSpeedAccelType jsa = new JointSpeedAccelType();
                    jsa.setJointSpeed(BigDecimal.valueOf(Double.valueOf(PendantClient.this.jTextFieldJointJogSpeed.getText())));
                    aj.setJointDetails(jsa);
                    ajl.add(aj);
//                    SimServerInner.debugCmdSendTime = System.currentTimeMillis();
                    internal.incAndSendCommand(ajst);
//                    System.out.println("apCount = " + apCount);
                    apCount = 0;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    jogStop();
                }
            }
        };
        jogActionListener.actionPerformed(null);
        jog_timer = new javax.swing.Timer(internal.getJogInterval(), jogActionListener);
        jog_timer.start();
    }

    private double rpyJogIncrement = 3.0;

    private boolean jogStopFlag = true;

    /**
     * Get the value of rpyJogIncrement
     *
     * @return the value of rpyJogIncrement
     */
    public double getRpyJogIncrement() {
        return rpyJogIncrement;
    }

    /**
     * Set the value of rpyJogIncrement
     *
     * @param rpyJogIncrement new value of rpyJogIncrement
     */
    public void setRpyJogIncrement(double rpyJogIncrement) {
        this.rpyJogIncrement = rpyJogIncrement;
    }

    private boolean jogWorldSpeedsSet = false;

    private void jogWorldStart(double sign) {
        try {
            if (null == internal.getStatus()
                    || null == internal.getStatus()) {
                showMessage("Can not send command when not connected.");
                return;
            }
            if (!jogWorldSpeedsSet) {
                SetTransSpeedType stst = new SetTransSpeedType();
                TransSpeedAbsoluteType tas = new TransSpeedAbsoluteType();
                tas.setSetting(BigDecimal.valueOf(Double.valueOf(this.jTextFieldTransSpeed.getText())));
                stst.setTransSpeed(tas);
                internal.incAndSendCommand(stst);
                internal.waitForDone(stst.getCommandID(), 200);
                SetRotSpeedType srst = new SetRotSpeedType();
                RotSpeedAbsoluteType ras = new RotSpeedAbsoluteType();
                ras.setSetting(BigDecimal.valueOf(Double.valueOf(this.jTextFieldRotationSpeed.getText())));
                srst.setRotSpeed(ras);
                internal.incAndSendCommand(srst);
                internal.waitForDone(srst.getCommandID(), 200);
                jogWorldSpeedsSet = true;
            }
//        this.setJointControlModes(JointControlModeEnumType.POSITION);
            final String axis = (String) this.jComboBoxXYZRPY.getSelectedItem();
            double tmpinc = 1.0;
            sign = Math.signum(sign);
            switch (axis) {
                case "X":
                case "Y":
                case "Z":
                    tmpinc = internal.getXyzJogIncrement() * sign;
                    break;

                case "Roll":
                case "Pitch":
                case "Yaw":
                    tmpinc = Math.toRadians(this.rpyJogIncrement) * sign;
                    break;
            }
            final BigDecimal axisIncrement = BigDecimal.valueOf(tmpinc);
            final double inc = tmpinc;
            if (null != jog_timer) {
                jog_timer.stop();
                jog_timer = null;
            }
            jogStopFlag = false;
            ActionListener jogActionListener = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (jogStopFlag) {
                            return;
                        }
                        if (internal.getStatus().getCommandStatus().getCommandState() == CommandStateEnumType.CRCL_ERROR) {
                            showMessage("Can not jog when status is " + CommandStateEnumType.CRCL_ERROR);
                            jogStop();
                        }
                        if (internal.getStatus().getCommandStatus().getCommandState() != CommandStateEnumType.CRCL_DONE) {
                            if (PendantClient.this.jCheckBoxMenuItemDebugWaitForDone.isSelected()
                                    || PendantClient.this.jCheckBoxMenuItemDebugSendCommand.isSelected()) {
                                System.err.println("Jog Timer ActionListener waiting for DONE");
                            }
                            return;
                        }
                        if (internal.getStatus().getCommandStatus().getCommandID().compareTo(internal.getCmdId()) < 0) {
                            if (PendantClient.this.jCheckBoxMenuItemDebugWaitForDone.isSelected()
                                    || PendantClient.this.jCheckBoxMenuItemDebugSendCommand.isSelected()) {
                                System.err.println("Jog Timer ActionListener waiting for ID greater than " + internal.getCmdId());
                            }
                            return;
                        }
//                    System.out.println("jtstart is done =" + internal.getStatus().getCommandStatus().getCommandID().compareTo(internal.getCmdId()));
                        MoveToType moveToCmd = new MoveToType();
                        PoseType endPos = new PoseType();
                        endPos.setPoint(new PointType());
                        endPos.setXAxis(new VectorType());
                        endPos.setZAxis(new VectorType());
                        moveToCmd.setEndPosition(endPos);
                        PoseType pose = Optional.ofNullable(internal)
                                .map(PendantClientInner::getStatus)
                                .map(CRCLPosemath::getPose)
                                .orElse(null);
                        if (null != pose) {
                            moveToCmd.getEndPosition().getPoint().setX(pose.getPoint().getX());
                            moveToCmd.getEndPosition().getPoint().setY(pose.getPoint().getY());
                            moveToCmd.getEndPosition().getPoint().setZ(pose.getPoint().getZ());
                            moveToCmd.getEndPosition().getXAxis().setI(pose.getXAxis().getI());
                            moveToCmd.getEndPosition().getXAxis().setJ(pose.getXAxis().getJ());
                            moveToCmd.getEndPosition().getXAxis().setK(pose.getXAxis().getK());
                            moveToCmd.getEndPosition().getZAxis().setI(pose.getZAxis().getI());
                            moveToCmd.getEndPosition().getZAxis().setJ(pose.getZAxis().getJ());
                            moveToCmd.getEndPosition().getZAxis().setK(pose.getZAxis().getK());
                            switch (axis) {
                                case "X":
                                    moveToCmd.getEndPosition().getPoint().setX(pose.getPoint().getX().add(axisIncrement));
                                    break;

                                case "Y":
                                    moveToCmd.getEndPosition().getPoint().setY(pose.getPoint().getY().add(axisIncrement));
                                    break;

                                case "Z":
                                    moveToCmd.getEndPosition().getPoint().setZ(pose.getPoint().getZ().add(axisIncrement));
                                    break;

                                case "Roll":
                                    incrementRoll(moveToCmd, inc);
                                    break;

                                case "Pitch":
                                    incrementPitch(moveToCmd, inc);
                                    break;

                                case "Yaw":
                                    incrementYaw(moveToCmd, inc);
                                    break;

                            }
                            internal.incAndSendCommand(moveToCmd);
//                        System.out.println("jt end is done =" + internal.getStatus().getCommandStatus().getCommandID().compareTo(internal.getCmdId()));
                        } else {
                            showMessage("Can't jog when pose == null");
                            jogStop();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        jogStop();
                    }
                }

                private void incrementYaw(MoveToType moveToType, final double inc) throws PmException {
                    PmRotationMatrix pm = CRCLPosemath.toPmRotationMatrix(internal.getPose());
                    PmRpy rpy = Posemath.toRpy(pm);
                    rpy.y += inc;
                    PmRotationVector pm2 = Posemath.toRot(rpy);
                    PoseType nextPose = CRCLPosemath.toPoseType(
                            CRCLPosemath.pointToPmCartesian(internal.getPoint()),
                            pm2);
                    moveToType.setEndPosition(nextPose);
                }

                private void incrementPitch(MoveToType moveToType, final double inc) throws PmException {
                    PmRotationMatrix pm = CRCLPosemath.toPmRotationMatrix(internal.getPose());
                    PmRpy rpy = Posemath.toRpy(pm);
                    rpy.p += inc;
                    PmRotationVector pm2 = Posemath.toRot(rpy);
                    PoseType nextPose = CRCLPosemath.toPoseType(
                            CRCLPosemath.pointToPmCartesian(internal.getPoint()),
                            pm2);
                    moveToType.setEndPosition(nextPose);
                }

                private void incrementRoll(MoveToType moveToType, final double inc) throws PmException {
                    PmRotationMatrix pm = CRCLPosemath.toPmRotationMatrix(internal.getPose());
                    PmRpy rpy = Posemath.toRpy(pm);
                    rpy.r += inc;
                    PmRotationVector pm2 = Posemath.toRot(rpy);
                    PoseType nextPose = CRCLPosemath.toPoseType(
                            CRCLPosemath.pointToPmCartesian(internal.getPoint()),
                            pm2);
                    moveToType.setEndPosition(nextPose);
                }
            };
            jogActionListener.actionPerformed(null);
            jog_timer = new javax.swing.Timer(internal.getJogInterval(), jogActionListener);
            jog_timer.start();
        } catch (JAXBException | InterruptedException ex) {
            Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, "Can not start world jog.", ex);
        }
    }

    private void jogStop() {
        jogStopFlag = true;
        if (null != jog_timer) {
            try {
                jog_timer.stop();
                jog_timer = null;
                internal.stopMotion(StopConditionEnumType.FAST);
            } catch (JAXBException ex) {
                Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void checkPollSelected() {
        if (this.jCheckBoxPoll.isSelected()) {
            this.startPollTimer();
        }
    }

    private void commonJogStop() {
        this.jogStop();
        this.jLabelJogMinus.setBackground(Color.WHITE);
        this.jLabelJogMinus.setForeground(Color.BLACK);
        this.jPanelJogMinus.setBackground(Color.WHITE);
        this.jLabelJogMinus.repaint();
        this.jPanelJogMinus.repaint();
        this.jLabelJogMinus1.setBackground(Color.WHITE);
        this.jLabelJogMinus1.setForeground(Color.BLACK);
        this.jPanelJogMinus1.setBackground(Color.WHITE);
        this.jLabelJogMinus1.repaint();
        this.jPanelJogMinus1.repaint();
        this.jLabelJogPlus.setBackground(Color.WHITE);
        this.jLabelJogPlus.setForeground(Color.BLACK);
        this.jPanelJogPlus.setBackground(Color.WHITE);
        this.jLabelJogPlus.repaint();
        this.jPanelJogPlus.repaint();
        this.jLabelJogPlus1.setBackground(Color.WHITE);
        this.jLabelJogPlus1.setForeground(Color.BLACK);
        this.jPanelJogPlus1.setBackground(Color.WHITE);
        this.jLabelJogPlus1.repaint();
        this.jPanelJogPlus1.repaint();
    }

    private XpathQueryJFrame xqJFrame = null;
    private void jMenuItemXPathQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemXPathQueryActionPerformed
        if (null == xqJFrame) {
            try {
                xqJFrame = new XpathQueryJFrame();
            } catch (ParserConfigurationException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        xqJFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItemXPathQueryActionPerformed

    private void jButtonMoveToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMoveToActionPerformed
        try {
            MoveToType moveto = new MoveToType();
            PoseType p = new PoseType();
            DefaultTableModel tm = (DefaultTableModel) this.jTableMoveToPose.getModel();
            PointType pt = new PointType();
            pt.setX(BigDecimal.valueOf((Double) tm.getValueAt(0, 1)));
            pt.setY(BigDecimal.valueOf((Double) tm.getValueAt(1, 1)));
            pt.setZ(BigDecimal.valueOf((Double) tm.getValueAt(2, 1)));
            VectorType xv = new VectorType();
            xv.setI(BigDecimal.valueOf((Double) tm.getValueAt(3, 1)));
            xv.setJ(BigDecimal.valueOf((Double) tm.getValueAt(4, 1)));
            xv.setK(BigDecimal.valueOf((Double) tm.getValueAt(5, 1)));
            VectorType zv = new VectorType();
            zv.setI(BigDecimal.valueOf((Double) tm.getValueAt(6, 1)));
            zv.setJ(BigDecimal.valueOf((Double) tm.getValueAt(7, 1)));
            zv.setK(BigDecimal.valueOf((Double) tm.getValueAt(8, 1)));
            p.setPoint(pt);
            p.setXAxis(xv);
            p.setZAxis(zv);
            moveto.setEndPosition(p);
            moveto.setMoveStraight(this.jCheckBoxStraight.isSelected());
            internal.incAndSendCommand(moveto);
        } catch (JAXBException ex) {
            Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonMoveToActionPerformed

    diagapplet.plotter.plotterJFrame xyzPlotter = null;

    diagapplet.plotter.plotterJFrame jointsPlotter = null;

    private void jMenuItemSetSchemaFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSetSchemaFilesActionPerformed
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
            internal.setCmdSchema(fa);
            CRCLSocket.saveCmdSchemaFiles(PendantClient.cmdSchemasFile, fa);
            internal.setStatSchema(fa);
            CRCLSocket.saveStatSchemaFiles(PendantClient.statSchemasFile, fa);
            internal.setProgramSchema(fa);
            CRCLSocket.saveProgramSchemaFiles(PendantClient.programSchemasFile, fa);
        }
    }//GEN-LAST:event_jMenuItemSetSchemaFilesActionPerformed

    private void jCheckBoxMenuItemJointsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemJointsActionPerformed
        if (this.jCheckBoxMenuItemJoints.isSelected()) {
            jointsPlotter = new plotterJFrame();
            jointsPlotter.setTitle("JOINTS");
            jointsPlotter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jointsPlotter.setVisible(true);
        }
    }//GEN-LAST:event_jCheckBoxMenuItemJointsActionPerformed

    private void jCheckBoxMenuItemPlotXYZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemPlotXYZActionPerformed
        if (this.jCheckBoxMenuItemPlotXYZ.isSelected()) {
            xyzPlotter = new plotterJFrame();
            xyzPlotter.setTitle("XYZ");
            xyzPlotter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            xyzPlotter.setVisible(true);
        }
    }//GEN-LAST:event_jCheckBoxMenuItemPlotXYZActionPerformed

    private void jButtonMoveToCurrentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMoveToCurrentActionPerformed
        this.updatePoseTable(internal.getPose(), this.jTableMoveToPose);
    }//GEN-LAST:event_jButtonMoveToCurrentActionPerformed

    private static final String recent_files_dir = ".crcl_pendant_client_recent_files";

    private void saveRecentCommandInstance(CRCLCommandInstanceType cmd) throws JAXBException, IOException {
        CRCLSocket tmpcs = internal.getTempCRCLSocket();
        String s = tmpcs.commandInstanceToPrettyDocString(cmd, true);
        File fDir = new File(System.getProperty("user.home"), recent_files_dir);
        fDir.mkdirs();
        String name = cmd.getCRCLCommand().getClass().getSimpleName();
        int pindex = name.lastIndexOf('.');
        if (pindex > 0 && pindex < name.length()) {
            name = name.substring(pindex + 1);
        }
        File fDir2 = new File(fDir, name);
        fDir2.mkdirs();
        Date dNow = new Date();
        SimpleDateFormat ft
                = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss_a_zzz_");
        String date_string = ft.format(dNow);
        File f = File.createTempFile(date_string, ".xml", fDir2);
        try (FileWriter fw = new FileWriter(f)) {
            fw.write(s);
        }
    }

    private static final String recent_programs_dir = ".crcl_pendant_client_recent_programs";

    private void saveRecentProgram(File fprog) throws JAXBException, IOException {
        Set<String> pathSet = this.getRecentPrograms();
        if (pathSet.contains(fprog.getCanonicalPath())) {
            return;
        }
        File fDir = new File(System.getProperty("user.home"), recent_programs_dir);
        fDir.mkdirs();
        String name = fprog.getName();
        if (null == name) {
            name = "untitled";
        }
        Date dNow = new Date();
        SimpleDateFormat ft
                = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss_a_zzz_");
        String date_string = ft.format(dNow);
        File flink = File.createTempFile(name + "_" + date_string, ".txt", fDir);
        try (FileWriter fw = new FileWriter(flink)) {
            fw.write(fprog.getCanonicalPath());
        }
    }

    private void saveRecentCommand(CRCLCommandType cmd) throws JAXBException, IOException {
        CRCLCommandInstanceType instanceForSave = new CRCLCommandInstanceType();
        instanceForSave.setCRCLCommand(cmd);
        this.saveRecentCommandInstance(instanceForSave);
    }

    private void browseOpenCommandXml() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "XML Instance Files", "xml");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File f = chooser.getSelectedFile();
                openXmlInstanceFile(f);
            } catch (SAXException | JAXBException | CRCLSocketException | IOException | XPathExpressionException | ParserConfigurationException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
                this.showMessage(ex);
            }
        }
    }

    private void browseOpenProgramXml() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "XML Program Files", "xml");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        this.clearRecordedPoints();
        if (null != internal.getProgram()) {
            internal.getProgram().getMiddleCommand().clear();
            try {
                this.showProgram(internal.getProgram());
            } catch (JAXBException ex) {
                Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            openXmlProgramFile(f);
        }
    }

    @Override
    public CRCLProgramType editProgram(CRCLProgramType program) {
//        internal.getXpu().setSchemaFiles(internal.getProgramSchemaFiles());
        program = ObjTableJPanel.editObject(program,
                internal.getXpu(),
                internal.getProgramSchemaFiles(),
                internal.getCheckProgramValidPredicate());
        return program;
    }

    private void openXmlInstanceFile(File f) throws CRCLSocketException, SAXException, JAXBException, IOException, ParserConfigurationException, XPathExpressionException {
        String s = internal.getXpu().queryXml(f, "/");
        CRCLCommandInstanceType cmdInstance
                = internal.getCRCLSocket().stringToCommand(s, this.jCheckBoxMenuItemValidateXml.isSelected());
        CRCLCommandType cmd = cmdInstance.getCRCLCommand();
        cmd = ObjTableJPanel.editObject(cmd, internal.getXpu(), internal.getCmdSchemaFiles(),
                internal.getCheckCommandValidPredicate());
        internal.incAndSendCommand(cmd);
        this.saveRecentCommand(cmd);
    }

    private String tableCommandString(CRCLCommandType cmd) throws ParserConfigurationException, SAXException, IOException, JAXBException {
        return this.internal.getTempCRCLSocket().commandToSimpleString(cmd);
    }

    public void showProgram(CRCLProgramType program) throws JAXBException {
        try {
            DefaultTableModel dtm = (DefaultTableModel) this.jTableProgram.getModel();
            BigInteger maxCmdId = BigInteger.ONE;
            InitCanonType init = program.getInitCanon();
            List<MiddleCommandType> middleCommands = program.getMiddleCommand();
            dtm.setRowCount(2 + middleCommands.size());
            if (init.getCommandID() == null) {
                init.setCommandID(maxCmdId);
            } else {
                maxCmdId = maxCmdId.max(init.getCommandID());
            }
            dtm.setValueAt(init.getCommandID().longValue(), 0, 0);
            try {
                dtm.setValueAt(tableCommandString(init), 0, 1);
            } catch (JAXBException ex) {
                Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            dtm.setValueAt(-1, 0, 2);
            dtm.setValueAt(0.0, 0, 2);
            for (int i = 0; i < middleCommands.size(); i++) {
                MiddleCommandType middleCommand = middleCommands.get(i);
                if (null == middleCommand) {
                    showDebugMessage("middleCommands contains null at " + i);
                    continue;
                }
                maxCmdId = maxCmdId.add(BigInteger.ONE);
                middleCommand.setCommandID(maxCmdId);
                maxCmdId = maxCmdId.max(middleCommand.getCommandID());
                dtm.setValueAt(middleCommand.getCommandID().longValue(), i + 1, 0);
                dtm.setValueAt(tableCommandString(middleCommand), i + 1, 1);
                dtm.setValueAt(-1, i + 1, 2);
                dtm.setValueAt(0.0, i + 1, 3);
                dtm.setValueAt(false, i + 1, 4);
            }
            EndCanonType endCommand = program.getEndCanon();
            maxCmdId = maxCmdId.add(BigInteger.ONE);
            if (endCommand.getCommandID() == null || endCommand.getCommandID().compareTo(maxCmdId) < 0) {
                endCommand.setCommandID(maxCmdId);
            }
            dtm.setValueAt(endCommand.getCommandID().longValue(), 1 + middleCommands.size(), 0);
            dtm.setValueAt(tableCommandString(endCommand), 1 + middleCommands.size(), 1);
            dtm.setValueAt(-1, 1 + middleCommands.size(), 2);
            dtm.setValueAt(0.0, 1 + middleCommands.size(), 3);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        programLineShowing = -1;
    }
    private void jMenuItemOpenXmlCommandInstanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOpenXmlCommandInstanceActionPerformed
        this.browseOpenCommandXml();
    }//GEN-LAST:event_jMenuItemOpenXmlCommandInstanceActionPerformed

    private void jMenuItemOpenXmlProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOpenXmlProgramActionPerformed
        this.browseOpenProgramXml();
    }//GEN-LAST:event_jMenuItemOpenXmlProgramActionPerformed

//    javax.swing.Timer pollTimer = null;
    private Thread pollingThread = null;
    private volatile boolean statusRequested = false;

    private long max_diff_readStatusEndTime_requestStatusStartTime = 0;
    private long maxPollStatusCycleTime = 0;
    private long cycles = 0;

    private void pollStatus() {
        try {
            while (!Thread.currentThread().isInterrupted()
                    && this.jCheckBoxPoll.isSelected() && null != internal.getCRCLSocket()) {
                cycles++;
                long requestStatusStartTime = System.currentTimeMillis();
                internal.requestStatus();
                statusRequested = true;
                internal.readStatus();
                statusRequested = false;
                long readStatusEndTime = System.currentTimeMillis();
                long diff_readStatusEndTime_requestStatusStartTime = (readStatusEndTime - requestStatusStartTime);
                if (max_diff_readStatusEndTime_requestStatusStartTime < diff_readStatusEndTime_requestStatusStartTime) {
                    max_diff_readStatusEndTime_requestStatusStartTime = diff_readStatusEndTime_requestStatusStartTime;
                }

                Thread.sleep(internal.getPoll_ms());
                long endCycleTime = System.currentTimeMillis();
                long pollStatusCycleTime = endCycleTime - requestStatusStartTime;
                if (pollStatusCycleTime > maxPollStatusCycleTime) {
                    maxPollStatusCycleTime = pollStatusCycleTime;
                }

            }
        } catch (InterruptedException interruptedException) {
        } catch (JAXBException ex) {
            Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void startPollTimer() {
//        pollTimer = new javax.swing.Timer(internal.getPoll_ms(), new ActionListener() {
//
//            private boolean toggler = false;
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                if (!toggler) {
//                    internal.requestStatus();
//                    if (!isUseReadStatusThreadSelected()) {
//                        toggler = true;
//                    }
//                } else {
//                    if (!isUseReadStatusThreadSelected()) {
//                        internal.readStatus();
//                    }
//                    toggler = false;
//                }
//            }
//        });
//        pollTimer.start();
        this.stopPollTimer();
        pollingThread = new Thread(this::pollStatus, "PendantClient.pollStatus");
        pollingThread.start();
    }

    @Override
    public void stopPollTimer() {
        if (null != pollingThread) {
            pollingThread.interrupt();
            try {
                pollingThread.join(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            pollingThread = null;
            if (statusRequested) {
                internal.readStatus();
            }
        }
    }

    private void jMenuItemRunTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRunTestActionPerformed
        Map<String, String> testPropsMap
                = PropertiesJPanel.confirmPropertiesMap(this, "Test Run Properties", true, internal.getDefaultTestPropertiesMap());
        internal.startRunTestThread(testPropsMap);
    }//GEN-LAST:event_jMenuItemRunTestActionPerformed

    private void clearProgramTimesDistances() {
        DefaultTableModel dtm = (DefaultTableModel) this.jTableProgram.getModel();
        for (int i = 0; i < dtm.getRowCount(); i++) {
            dtm.setValueAt(-1, i, 2);
            dtm.setValueAt(0.0, i, 3);
        }
        programLineShowing = -1;
        jogWorldSpeedsSet = false;
    }

    private void jButtonProgramRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProgramRunActionPerformed

        this.clearProgramTimesDistances();
        int new_poll_ms = Integer.valueOf(this.jTextFieldPollTime.getText());
        internal.setPoll_ms(new_poll_ms);
        internal.setWaitForDoneDelay(new_poll_ms);
        internal.startRunProgramThread(0);
        this.jButtonResume.setEnabled(internal.isPaused());
        this.jButtonProgramPause.setEnabled(internal.isRunningProgram());
        jogWorldSpeedsSet = false;
    }//GEN-LAST:event_jButtonProgramRunActionPerformed

    private void jButtonResumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResumeActionPerformed
        if (pauseTime > this.internal.getRunStartMillis()) {
            this.internal.runStartMillis += (System.currentTimeMillis() - pauseTime);
            pauseTime = -1;
        }
        internal.unpause();
        this.jButtonResume.setEnabled(internal.isPaused());
        this.jButtonProgramPause.setEnabled(internal.isRunningProgram());
        jogWorldSpeedsSet = false;
    }//GEN-LAST:event_jButtonResumeActionPerformed

    long pauseTime = -1;

    private void jButtonProgramPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProgramPauseActionPerformed
        pauseTime = System.currentTimeMillis();
        internal.pause();
        this.jButtonResume.setEnabled(internal.isPaused());
        this.jButtonProgramPause.setEnabled(internal.isRunningProgram());
        jogWorldSpeedsSet = false;
    }//GEN-LAST:event_jButtonProgramPauseActionPerformed

    private void jButtonProgramAbortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProgramAbortActionPerformed
        pauseTime = System.currentTimeMillis();
        internal.abort();
    }//GEN-LAST:event_jButtonProgramAbortActionPerformed

    private void jButtonDeletProgramItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeletProgramItemActionPerformed
        int index = this.jTableProgram.getSelectedRow();
        if (index > 0 && index < this.jTableProgram.getRowCount() - 1) {
            try {
                internal.getProgram().getMiddleCommand().remove(index - 1);
                this.showProgram(internal.getProgram());
                this.showCurrentProgramLine(index);
            } catch (JAXBException ex) {
                Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonDeletProgramItemActionPerformed

    private void jButtonEditProgramItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditProgramItemActionPerformed
        int index = this.jTableProgram.getSelectedRow();
        if (index > 0 && index < this.jTableProgram.getRowCount() - 1) {
            try {
                MiddleCommandType cmdOrig = internal.getProgram().getMiddleCommand().get(index - 1);
                MiddleCommandType cmdEdited
                        = (MiddleCommandType) ObjTableJPanel.editObject(cmdOrig,
                                internal.getXpu(),
                                internal.getCmdSchemaFiles(),
                                PendantClient.this.internal.getCheckCommandValidPredicate());
                if (null == cmdEdited) {
                    showDebugMessage("Edit Program Item cancelled. cmdEdited == null");
                    return;
                }
                internal.getProgram().getMiddleCommand().set(index - 1, cmdEdited);
                this.showProgram(internal.getProgram());
                this.showCurrentProgramLine(index);
            } catch (JAXBException ex) {
                Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonEditProgramItemActionPerformed

    private void jButtonAddProgramItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddProgramItemActionPerformed
        int index = this.jTableProgram.getSelectedRow();
        if (index > 0 && index < this.jTableProgram.getRowCount() - 1) {
            try {
                Class clss = MiddleCommandType.class;
                List<Class> availClasses = getAssignableClasses(clss,
                        ObjTableJPanel.getClasses());
                Class ca[] = availClasses.toArray(new Class[availClasses.size()]);
                Class selectedClss = ListChooserJPanel.Choose(this, "Type of new Item", ca, null);
                if (selectedClss == null) {
                    showDebugMessage("Add Program Item cancelled. selectedClss == null");
                    return;
                }
                MiddleCommandType cmdOrig = (MiddleCommandType) selectedClss.newInstance();
                MiddleCommandType cmdEdited
                        = (MiddleCommandType) ObjTableJPanel.editObject(cmdOrig,
                                internal.getXpu(),
                                internal.getCmdSchemaFiles(),
                                PendantClient.this.internal.getCheckCommandValidPredicate());
                if (null == cmdEdited) {
                    showDebugMessage("Add Program Item cancelled. cmdEdited == null");
                    return;
                }
                internal.getProgram().getMiddleCommand().add(index - 1, cmdEdited);
                this.showProgram(internal.getProgram());
                this.showCurrentProgramLine(index);
            } catch (InstantiationException | IllegalAccessException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
                showMessage(ex);
            } catch (JAXBException ex) {
                Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonAddProgramItemActionPerformed

    private void jMenuItemSaveProgramAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSaveProgramAsActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "XML Program Files", "xml");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File f = chooser.getSelectedFile();
                saveXmlProgramFile(f);
            } catch (JAXBException | CRCLSocketException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
                this.showMessage(ex);
            }
        }
    }//GEN-LAST:event_jMenuItemSaveProgramAsActionPerformed

    private void jMenuItemSavePoseListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSavePoseListActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Comma-Separated-Values", "csv");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                this.internal.savePoseListToCsvFile(chooser.getSelectedFile().getAbsolutePath());
            } catch (IOException ex) {
                Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PmException ex) {
                Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jMenuItemSavePoseListActionPerformed

    private void jMenuItemPoseList3DPlotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPoseList3DPlotActionPerformed
        try {
//            File tmpFile = File.createTempFile("poseList", ".csv");
//            this.internal.savePoseListToCsvFile(tmpFile.getAbsolutePath());
            com.github.wshackle.poselist3dplot.MainJFrame.showPoseList(this.internal.getPoseList());
        } catch (Exception ex) {
            Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemPoseList3DPlotActionPerformed

    private void jMenuItemOpenStatusLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOpenStatusLogActionPerformed
        try {
            File tmpFile = File.createTempFile("poseList", ".csv");
            this.internal.savePoseListToCsvFile(tmpFile.getAbsolutePath());
            Desktop.getDesktop().open(tmpFile);
        } catch (IOException ex) {
            Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PmException ex) {
            Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemOpenStatusLogActionPerformed

    private void jButtonPlotProgramItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlotProgramItemActionPerformed
        final int index = this.jTableProgram.getSelectedRow();
        if (index > 0 && index < this.jTableProgram.getRowCount() - 1) {
            MiddleCommandType cmdOrig = internal.getProgram().getMiddleCommand().get(index - 1);
            BigInteger id = cmdOrig.getCommandID();
            final List<AnnotatedPose> l
                    = this.internal
                    .getPoseList()
                    .stream()
                    .filter(x -> x.getCmdId().compareTo(id) == 0)
                    .collect(Collectors.toList());
            com.github.wshackle.poselist3dplot.MainJFrame
                    .showPoseList(l);
        }
    }//GEN-LAST:event_jButtonPlotProgramItemActionPerformed

    private void jCheckBoxMenuItemUseEXIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemUseEXIActionPerformed
        if (this.isConnected()) {
            this.disconnect();
            this.connect(this.jTextFieldHost.getText(), Integer.valueOf(this.jTextFieldPort.getText()));
        }
    }//GEN-LAST:event_jCheckBoxMenuItemUseEXIActionPerformed

    private void jCheckBoxMenuItemUseReadStatusThreadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemUseReadStatusThreadActionPerformed
        if (this.jCheckBoxMenuItemDebugReadStatus.isSelected()) {
            internal.startStatusReaderThread();
        } else {
            internal.stopStatusReaderThread();
        }
    }//GEN-LAST:event_jCheckBoxMenuItemUseReadStatusThreadActionPerformed

    private void jCheckBoxMenuItemRecordCommandsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemRecordCommandsActionPerformed
        internal.setRecordCommands(this.jCheckBoxMenuItemRecordCommands.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItemRecordCommandsActionPerformed

    private void jMenuItemShowCommandLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemShowCommandLogActionPerformed
        MultiLineStringJPanel.showText(internal.getRecordedCommandsList().stream().map(cmd -> internal.getTempCRCLSocket().commandToPrettyString(cmd, "")).collect(Collectors.joining("\n\n")));
    }//GEN-LAST:event_jMenuItemShowCommandLogActionPerformed

    private CRCLProgramType recordPointsProgram = null;

    public void recordPoint(PoseType pose) {
        try {
            if (recordPointsProgram == null) {
                recordPointsProgram = new CRCLProgramType();
                InitCanonType initCmd = new InitCanonType();
                initCmd.setCommandID(BigInteger.ONE);
                recordPointsProgram.setInitCanon(initCmd);
                EndCanonType endCmd = new EndCanonType();
                initCmd.setCommandID(BigInteger.valueOf(3));
                recordPointsProgram.setEndCanon(endCmd);
            }
            MoveToType moveToCmd = new MoveToType();
            PoseType endPose = new PoseType();
            endPose.setPoint(pose.getPoint());
            endPose.setXAxis(pose.getXAxis());
            endPose.setZAxis(pose.getZAxis());
            moveToCmd.setEndPosition(endPose);
            moveToCmd.setMoveStraight(this.jCheckBoxStraight.isSelected());
            moveToCmd.setCommandID(BigInteger.valueOf(recordPointsProgram.getMiddleCommand().size() + 1));
            recordPointsProgram.getMiddleCommand().add(moveToCmd);
            recordPointsProgram.getEndCanon().setCommandID(moveToCmd.getCommandID().add(BigInteger.ONE));
            internal.setProgram(recordPointsProgram);
            showProgram(recordPointsProgram);
        } catch (JAXBException ex) {
            Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recordCurrentPoint() {
        Optional.ofNullable(internal)
                .map(PendantClientInner::getStatus)
                .map(CRCLPosemath::getPose)
                .ifPresent(this::recordPoint);
    }

    public void clearRecordedPoints() {
        if (null != recordPointsProgram) {
            try {
                recordPointsProgram.getMiddleCommand().clear();
                this.internal.setProgram(recordPointsProgram);
                this.showProgram(recordPointsProgram);
            } catch (JAXBException ex) {
                Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void jMenuItemClearRecordedPointsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClearRecordedPointsActionPerformed
        this.clearRecordedPoints();
    }//GEN-LAST:event_jMenuItemClearRecordedPointsActionPerformed


    private void jButtonRecordPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecordPointActionPerformed
        this.recordCurrentPoint();
    }//GEN-LAST:event_jButtonRecordPointActionPerformed

    private void jButtonCloseGripperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseGripperActionPerformed
        try {
            SetEndEffectorType seeCmd = new SetEndEffectorType();
            seeCmd.setSetting(BigDecimal.ZERO);
            internal.incAndSendCommand(seeCmd);
            if (null != recordPointsProgram) {
                this.recordCurrentPoint();
                recordPointsProgram.getMiddleCommand().add(seeCmd);
                internal.setProgram(recordPointsProgram);
                showProgram(recordPointsProgram);
            }
        } catch (JAXBException ex) {
            Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonCloseGripperActionPerformed

    private void jButtonOpenGripperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenGripperActionPerformed
        try {
            SetEndEffectorType seeCmd = new SetEndEffectorType();
            seeCmd.setSetting(BigDecimal.ONE);
            internal.incAndSendCommand(seeCmd);
            if (null != recordPointsProgram) {
                this.recordCurrentPoint();
                recordPointsProgram.getMiddleCommand().add(seeCmd);
                internal.setProgram(recordPointsProgram);
                showProgram(recordPointsProgram);
            }
        } catch (JAXBException ex) {
            Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonOpenGripperActionPerformed

    private void jTextFieldPollTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPollTimeActionPerformed
        int new_poll_ms = Integer.valueOf(this.jTextFieldPollTime.getText());
        internal.setPoll_ms(new_poll_ms);
        this.stopPollTimer();
        if (this.jCheckBoxPoll.isSelected()) {
            this.startPollTimer();
        }
    }//GEN-LAST:event_jTextFieldPollTimeActionPerformed

    private void jCheckBoxPollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPollActionPerformed
        this.stopPollTimer();
        if (this.jCheckBoxPoll.isSelected() && null != internal.getCRCLSocket()) {
            this.startPollTimer();
        }
    }//GEN-LAST:event_jCheckBoxPollActionPerformed

    private void jButtonInitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInitActionPerformed
        try {
            InitCanonType init = new InitCanonType();
            internal.incAndSendCommand(init);
        } catch (JAXBException ex) {
            Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonInitActionPerformed

    private void jButtonEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEndActionPerformed
        try {
            EndCanonType end = new EndCanonType();
            internal.incAndSendCommand(end);
        } catch (JAXBException ex) {
            Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonEndActionPerformed

    private void jButtonDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDisconnectActionPerformed
        this.disconnect();
    }//GEN-LAST:event_jButtonDisconnectActionPerformed

    private void jButtonConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConnectActionPerformed
        this.connect(this.jTextFieldHost.getText(), Integer.valueOf(this.jTextFieldPort.getText()));
    }//GEN-LAST:event_jButtonConnectActionPerformed

    private void jButtonStepFwdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStepFwdActionPerformed
        internal.setStepMode(true);
        if (internal.isPaused() && internal.isRunningProgram()) {
            internal.unpause();
        } else {
            internal.startRunProgramThread(this.getCurrentProgramLine());
        }
    }//GEN-LAST:event_jButtonStepFwdActionPerformed

    private void jButtonRunProgFromCurrentLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRunProgFromCurrentLineActionPerformed
        if (pauseTime > this.internal.runStartMillis) {
            this.internal.runStartMillis += (System.currentTimeMillis() - pauseTime);
        }
        pauseTime = -1;
        if (this.getCurrentProgramLine() < 1) {
            this.internal.runStartMillis = System.currentTimeMillis();
        }
        this.internal.setStepMode(false);
        if (internal.isPaused() && internal.isRunningProgram()) {
            internal.unpause();
        } else {
            internal.startRunProgramThread(this.getCurrentProgramLine());
        }
    }//GEN-LAST:event_jButtonRunProgFromCurrentLineActionPerformed

    private void jButtonStepBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStepBackActionPerformed
        internal.setStepMode(true);
        internal.abort();
        int l = this.getCurrentProgramLine();
        if (l > 0) {
            l--;
        }
        internal.startRunProgramThread(l);
    }//GEN-LAST:event_jButtonStepBackActionPerformed

    private void jMenuItemLoadPrefsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLoadPrefsActionPerformed
        JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.home")));
        if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(this)) {
            File f = chooser.getSelectedFile();
            loadPrefsFile(f);
        }
    }//GEN-LAST:event_jMenuItemLoadPrefsActionPerformed

    private void jMenuItemSavePrefsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSavePrefsActionPerformed
        JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.home")));
        if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(this)) {
            File f = chooser.getSelectedFile();
            savePrefsFile(f);
        }
    }//GEN-LAST:event_jMenuItemSavePrefsActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private LengthUnitEnumType lengthUnit = LengthUnitEnumType.MILLIMETER;

    private void updateLengthUnit(LengthUnitEnumType newUnit) {
        LengthUnitEnumType oldUnit
                = lengthUnit;
        if (newUnit != oldUnit) {
            double oldScale = 1.0;
            double newScale = 1.0;
            switch (oldUnit) {
                case METER:
                    oldScale = 1.0;
                    break;

                case MILLIMETER:
                    oldScale = 0.001;
                    break;

                case INCH:
                    oldScale = 0.0254;
                    break;
            }
            switch (newUnit) {
                case METER:
                    newScale = 1.0;
                    break;

                case MILLIMETER:
                    newScale = 0.001;
                    break;

                case INCH:
                    newScale = 0.0254;
                    break;
            }
            lengthUnitComboBoxLengthUnit.setSelectedItem(newUnit);
            double oldInc = internal.getXyzJogIncrement();
            double newInc = oldInc * oldScale / newScale;
            internal.setXyzJogIncrement(newInc);
            this.jTextFieldXYZJogIncrement.setText(Double.toString(newInc));
            lengthUnit = newUnit;
        }
    }

    private class MyPropertyChangeListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            switch (evt.getPropertyName()) {
                case PendantClientInner.PROP_LENGTHUNIT:
                    updateLengthUnit(internal.getLengthUnit());
                    break;
            }
        }
    }
    private void jCheckBoxMenuItemQuitProgramOnTestCommandFailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemQuitProgramOnTestCommandFailActionPerformed
        this.internal.setQuitOnTestCommandFailure(this.jCheckBoxMenuItemQuitProgramOnTestCommandFail.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItemQuitProgramOnTestCommandFailActionPerformed

    private void jMenuItemViewLogFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemViewLogFileActionPerformed
        internal.openLogFile();
    }//GEN-LAST:event_jMenuItemViewLogFileActionPerformed

    private void jTextFieldTransSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTransSpeedActionPerformed
        internal.setJogTransSpeed(Double.valueOf(this.jTextFieldTransSpeed.getText()));
        jogWorldSpeedsSet = false;
    }//GEN-LAST:event_jTextFieldTransSpeedActionPerformed

    private void jTextFieldJointJogSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldJointJogSpeedActionPerformed
        internal.setJogJointSpeed(Double.valueOf(this.jTextFieldJointJogSpeed.getText()));
    }//GEN-LAST:event_jTextFieldJointJogSpeedActionPerformed

    private void lengthUnitComboBoxLengthUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lengthUnitComboBoxLengthUnitActionPerformed
        try {
            SetLengthUnitsType setLengthUnitsCmd = new SetLengthUnitsType();
            setLengthUnitsCmd.setUnitName(this.lengthUnitComboBoxLengthUnit.getSelectedItem());
            this.updateLengthUnit(setLengthUnitsCmd.getUnitName());
            internal.incAndSendCommand(setLengthUnitsCmd);
        } catch (JAXBException ex) {
            Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lengthUnitComboBoxLengthUnitActionPerformed

    private void jTextFieldJogIntervalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldJogIntervalActionPerformed
        internal.setJogInterval(Integer.valueOf(this.jTextFieldJogInterval.getText()));
    }//GEN-LAST:event_jTextFieldJogIntervalActionPerformed

    private void jTextFieldRPYJogIncrementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRPYJogIncrementActionPerformed
        this.setRpyJogIncrement(Double.valueOf(this.jTextFieldRPYJogIncrement.getText()));
    }//GEN-LAST:event_jTextFieldRPYJogIncrementActionPerformed

    private void jTextFieldXYZJogIncrementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldXYZJogIncrementActionPerformed
        internal.setXyzJogIncrement(Double.valueOf(this.jTextFieldXYZJogIncrement.getText()));
    }//GEN-LAST:event_jTextFieldXYZJogIncrementActionPerformed

    private void jTextFieldJointJogIncrementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldJointJogIncrementActionPerformed
        internal.setJogIncrement(Double.valueOf(this.jTextFieldJointJogIncrement.getText()));
    }//GEN-LAST:event_jTextFieldJointJogIncrementActionPerformed

    private void jPanelJogMinus1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelJogMinus1MousePressed
        this.jogWorldStart(-1.0);
        this.jLabelJogMinus1.setBackground(Color.BLACK);
        this.jLabelJogMinus1.setForeground(Color.WHITE);
        this.jPanelJogMinus1.setBackground(Color.BLACK);
        this.jLabelJogMinus1.repaint();
        this.jPanelJogMinus1.repaint();
    }//GEN-LAST:event_jPanelJogMinus1MousePressed

    private void jLabelJogMinus1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogMinus1MouseExited
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJogMinus1MouseExited

    private void jLabelJogMinus1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogMinus1MouseReleased
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJogMinus1MouseReleased

    private void jLabelJogMinus1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogMinus1MousePressed
        this.jogWorldStart(-1.0);
        this.jLabelJogMinus1.setBackground(Color.BLACK);
        this.jLabelJogMinus1.setForeground(Color.WHITE);
        this.jPanelJogMinus1.setBackground(Color.BLACK);
        this.jLabelJogMinus1.repaint();
        this.jPanelJogMinus1.repaint();
    }//GEN-LAST:event_jLabelJogMinus1MousePressed

    private void jLabelJogPlus1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogPlus1MouseReleased
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJogPlus1MouseReleased

    private void jLabelJogPlus1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogPlus1MousePressed
        this.jogWorldStart(+1.0);
        this.jLabelJogPlus1.setBackground(Color.BLACK);
        this.jLabelJogPlus1.setForeground(Color.WHITE);
        this.jPanelJogPlus1.setBackground(Color.BLACK);
        this.jLabelJogPlus1.repaint();
        this.jPanelJogPlus1.repaint();
    }//GEN-LAST:event_jLabelJogPlus1MousePressed

    private void jLabelJogPlus1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogPlus1MouseExited
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJogPlus1MouseExited

    private void jLabelJogPlusMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogPlusMouseReleased
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJogPlusMouseReleased

    private void jLabelJogPlusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogPlusMousePressed
        this.jogJointStart(+1.0 * internal.getJogIncrement());
        this.jLabelJogPlus.setBackground(Color.BLACK);
        this.jLabelJogPlus.setForeground(Color.WHITE);
        this.jPanelJogPlus.setBackground(Color.BLACK);
        this.jLabelJogPlus.repaint();
        this.jPanelJogPlus.repaint();
    }//GEN-LAST:event_jLabelJogPlusMousePressed

    private void jLabelJogPlusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogPlusMouseExited
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJogPlusMouseExited

    private void jPanelJogMinusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelJogMinusMousePressed
        //        ActuateJointsType jog = new ActuateJointsType();
        //        cmdId = cmdId.shift(BigInteger.ONE);
        //        jog.setCommandID(cmdId);
        //        ActuateJointType joint = new ActuateJointType();
        //        List<ActuateJointType> l = jog.getActuateJoint();
        //        if(l == null) {
        //            l = new ArrayList<>();
        //        }
        //        l.shift(joint);
        //        joint.setActuate(true);
        //
        //        this.cmd_instance.setCRCLCommand(jog);
        //        this.sendCommand();
    }//GEN-LAST:event_jPanelJogMinusMousePressed

    private void jLabelJogMinusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogMinusMouseExited
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJogMinusMouseExited

    private void jLabelJogMinusMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogMinusMouseReleased
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJogMinusMouseReleased

    private void jLabelJogMinusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogMinusMousePressed
        this.jogJointStart(-1.0 * internal.getJogIncrement());
        this.jLabelJogMinus.setBackground(Color.BLACK);
        this.jLabelJogMinus.setForeground(Color.WHITE);
        this.jPanelJogMinus.setBackground(Color.BLACK);
        this.jLabelJogMinus.repaint();
        this.jPanelJogMinus.repaint();
    }//GEN-LAST:event_jLabelJogMinusMousePressed

    private void jTextFieldRotationSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRotationSpeedActionPerformed
        internal.setJogRotSpeed(Double.valueOf(this.jTextFieldRotationSpeed.getText()));
        jogWorldSpeedsSet = false;
    }//GEN-LAST:event_jTextFieldRotationSpeedActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        jogWorldSpeedsSet = false;
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private static void scrollToVisible(JTable table, int rowIndex, int vColIndex) {
        if (!(table.getParent() instanceof JViewport)) {
            return;
        }
        JViewport viewport = (JViewport) table.getParent();
        Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);
        Point pt = viewport.getViewPosition();
        rect.setLocation(rect.x - pt.x, rect.y - pt.y);
        viewport.scrollRectToVisible(rect);
    }

    public int getCurrentProgramLine() {
        return jTableProgram.getSelectionModel().getMinSelectionIndex();
    }

    int programLineShowing = -1;

    private void finishShowCurrentProgramLine(final int line) {
        if (line != programLineShowing) {
            if (jTableProgram.getSelectedRow() != line) {
                jTableProgram.getSelectionModel().setSelectionInterval(line, line);
            }
            scrollToVisible(jTableProgram, line, 0);
            jTableProgram.repaint();
            jPanelProgram.revalidate();
            jPanelProgram.repaint();
            long endMillis
                    = (internal.getRunEndMillis() > 0 && internal.getRunEndMillis() > internal.getRunStartMillis())
                            ? internal.getRunEndMillis() : System.currentTimeMillis();
            double runTime = (endMillis - this.internal.getRunStartMillis()) / 1000.0;
            this.jTextFieldRunTime.setText(String.format("%.1f", runTime));
            if (line == 0) {
                try {
                    InitCanonType cmd = this.internal.getProgram().getInitCanon();
                    String cmdString = this.internal.getTempCRCLSocket().commandToPrettyString(cmd);
                    showSelectedProgramCommand(cmdString);
                } catch (JAXBException ex) {
                    Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (line > 0 && line <= this.internal.getProgram().getMiddleCommand().size()) {
                try {
                    MiddleCommandType cmd = this.internal.getProgram().getMiddleCommand().get(line - 1);
                    String cmdString = this.internal.getTempCRCLSocket().commandToPrettyString(cmd);
                    showSelectedProgramCommand(cmdString);
                } catch (JAXBException ex) {
                    Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (line == this.internal.getProgram().getMiddleCommand().size() + 1) {
                try {
                    EndCanonType cmd = this.internal.getProgram().getEndCanon();
                    String cmdString = this.internal.getTempCRCLSocket().commandToPrettyString(cmd);
                    showSelectedProgramCommand(cmdString);
                } catch (JAXBException ex) {
                    Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        programLineShowing = line;
    }

    private void showSelectedProgramCommand(String cmdString) {
        int endlineindex = cmdString.indexOf('\n');
        if (endlineindex > 0 && endlineindex < cmdString.length()) {
            cmdString = cmdString.substring(endlineindex + 1);
        }
        int instancestartindex = cmdString.indexOf("<CRCLCommandInstance>");
        if (instancestartindex >= 0) {
            cmdString = cmdString.substring(instancestartindex + "<CRCLCommandInstance>".length());
            endlineindex = cmdString.indexOf('\n');
            if (endlineindex > 0 && endlineindex < cmdString.length()) {
                cmdString = cmdString.substring(endlineindex + 1);
            }
        }
        int instanceendindex = cmdString.indexOf("</CRCLCommandInstance>");
        if (instanceendindex > 0) {
            cmdString = cmdString.substring(0, instanceendindex);
        }
        cmdString = cmdString.trim();
        this.jTextAreaSelectedProgramCommand.setText(cmdString);
        this.jTextAreaSelectedProgramCommand.setCaretPosition(0);
    }

    @Override
    public void showCurrentProgramLine(final int line) {
        if (line >= this.jTableProgram.getRowCount() || line < 0) {
            return;
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                finishShowCurrentProgramLine(line);
            }
        });
    }

    @Override
    public boolean isEXISelected() {
        return this.jCheckBoxMenuItemUseEXI.isSelected();
    }

    @Override
    public boolean isUseReadStatusThreadSelected() {
        return this.jCheckBoxMenuItemUseReadStatusThread.isSelected();
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
        } catch (ClassNotFoundException ex) {
            LOGGER.log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            LOGGER.log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            LOGGER.log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            LOGGER.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new PendantClient().setVisible(true);
                } catch (ParserConfigurationException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @Override
    public boolean validateXmlSelected() {
        return this.jCheckBoxMenuItemValidateXml.isSelected();

    }

    @Override
    public boolean replaceStateSelected() {
        return this.jCheckBoxMenuItemReplaceState.isSelected();
    }

    @Override
    public String getHost() {
        return this.jTextFieldHost.getText();
    }

    @Override
    public int getPort() {
        return Integer.valueOf(this.jTextFieldPort.getText());
    }

    @Override
    public boolean isDebugWaitForDoneSelected() {
        return this.jCheckBoxMenuItemDebugWaitForDone.isSelected();
    }

    private Optional<Object> safeInvokeMethod(Method m, Object o) {
        try {
            return Optional.ofNullable(m.invoke(o));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.empty();
    }

    private void safeInvokeMethod2(Method m, Object... args) {
        try {
            m.invoke(this, args);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void savePrefsFile(File f) {
        try {
            File crcljavaDir = new File(System.getProperty("user.home"), CRCLJAVA_USER_DIR);
            crcljavaDir.mkdirs();
            File settingsRef = new File(crcljavaDir, SETTINGSREF);
            try (PrintStream psRef = new PrintStream(new FileOutputStream(settingsRef))) {
                psRef.println(f.getCanonicalPath());
            }
            try (PrintStream ps = new PrintStream(new FileOutputStream(f))) {
                Method ma[] = this.getClass().getMethods();
                Stream.of(ma)
                        .filter(m -> Modifier.isPublic(m.getModifiers()))
                        .filter(m -> m.getName().startsWith("is"))
                        .filter(m -> m.getParameterTypes().length == 0)
                        .filter(m -> m.getReturnType().isAssignableFrom(boolean.class))
                        .map(m -> safeInvokeMethod(m, PendantClient.this)
                                .map(result -> m.getReturnType().getCanonicalName() + " " + m.getName().substring(2, 3).toLowerCase() + m.getName().substring(3) + "=" + result.toString())
                                .orElse("# could not invoke" + m.getName()))
                        .forEachOrdered(ps::println);
                Stream.of(ma)
                        .filter(m -> Modifier.isPublic(m.getModifiers()))
                        .filter(m -> m.getName().startsWith("get"))
                        .filter(m -> m.getParameterTypes().length == 0)
                        .map(m -> safeInvokeMethod(m, PendantClient.this)
                                .map(result -> m.getReturnType().getCanonicalName() + " " + m.getName().substring(3, 4).toLowerCase() + m.getName().substring(4) + "=" + result.toString())
                                .orElse("# could not invoke" + m.getName()))
                        .forEachOrdered(ps::println);
                ma = this.internal.getClass().getMethods();
                Stream.of(ma)
                        .filter(m -> Modifier.isPublic(m.getModifiers()))
                        .filter(m -> m.getName().startsWith("is"))
                        .filter(m -> m.getParameterTypes().length == 0)
                        .filter(m -> m.getReturnType().isAssignableFrom(boolean.class))
                        .map(m -> safeInvokeMethod(m, PendantClient.this.internal)
                                .map(result -> m.getReturnType().getCanonicalName() + " internal." + m.getName().substring(2, 3).toLowerCase() + m.getName().substring(3) + "=" + result.toString())
                                .orElse("# could not invoke" + m.getName()))
                        .forEachOrdered(ps::println);
                Stream.of(ma)
                        .filter(m -> Modifier.isPublic(m.getModifiers()))
                        .filter(m -> m.getName().startsWith("get"))
                        .filter(m -> m.getParameterTypes().length == 0)
                        .map(m -> safeInvokeMethod(m, PendantClient.this.internal)
                                .map(result -> m.getReturnType().getCanonicalName() + " internal." + m.getName().substring(3, 4).toLowerCase() + m.getName().substring(4) + "=" + result.toString())
                                .orElse("# could not invoke" + m.getName()))
                        .forEachOrdered(ps::println);
            }
        } catch (IOException iOException) {
            showMessage(iOException);
        }
    }

    @SuppressWarnings("unchecked")
    static private <T> T valueOf(Class<T> clss, String s) {
        try {
            Method vmethod = Stream.of(clss.getMethods())
                    .filter(m -> m.getName().equals("valueOf"))
                    .filter(m -> m.getParameterTypes().length == 1)
                    .filter(m -> Modifier.isStatic(m.getModifiers()))
                    .filter(m -> m.getParameterTypes()[0].isAssignableFrom(String.class))
                    .findAny()
                    .orElse(null);
            if (null != vmethod) {
                return (T) vmethod.invoke(null, s);
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (clss.isAssignableFrom(String.class)) {
            return (T) s;
        } else if (clss.isAssignableFrom(double.class)) {
            return (T) Double.valueOf(s);
        } else if (clss.isAssignableFrom(float.class)) {
            return (T) Float.valueOf(s);
        } else if (clss.isAssignableFrom(long.class)) {
            return (T) Long.valueOf(s);
        } else if (clss.isAssignableFrom(int.class)) {
            return (T) Integer.valueOf(s);
        } else if (clss.isAssignableFrom(short.class)) {
            return (T) Short.valueOf(s);
        } else if (clss.isAssignableFrom(byte.class)) {
            return (T) Byte.valueOf(s);
        } else if (clss.isAssignableFrom(Double.class)) {
            return (T) Double.valueOf(s);
        } else if (clss.isAssignableFrom(Float.class)) {
            return (T) Float.valueOf(s);
        } else if (clss.isAssignableFrom(Long.class)) {
            return (T) Long.valueOf(s);
        } else if (clss.isAssignableFrom(Integer.class)) {
            return (T) Integer.valueOf(s);
        } else if (clss.isAssignableFrom(Short.class)) {
            return (T) Short.valueOf(s);
        } else if (clss.isAssignableFrom(Byte.class)) {
            return (T) Byte.valueOf(s);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void setParam(String... args) {
        try {
            if (args.length < 3) {
                return;
            }

            Class clss = null;
            switch (args[0]) {
                case "boolean":
                    clss = boolean.class;
                    break;

                case "int":
                    clss = int.class;
                    break;

                case "long":
                    clss = long.class;
                    break;

                case "float":
                    clss = float.class;
                    break;

                case "double":
                    clss = double.class;
                    break;
            }

            if (null == clss) {
//                clss = Class.forName(args[0]);
                return;
            }
            Object o = valueOf(clss, args[2]);
            if (null == o) {
                return;
            }
            Method m = null;
            if (args[1].startsWith("internal.")) {
                String name = args[1].substring("internal.".length());

                try {
                    m = this.internal.getClass().getMethod("set" + name.substring(0, 1).toUpperCase() + name.substring(1),
                            clss);
                } catch (NoSuchMethodException ex) {
                    // ignore and just return
                }
                if (null == m) {
                    return;
                }
                m.invoke(this.internal, o);
            } else {
                try {
                    m = this.getClass().getMethod("set" + args[1].substring(0, 1).toUpperCase() + args[1].substring(1),
                            clss);
                } catch (NoSuchMethodException ex) {
                    // ignore and just return
                }
                if (null == m) {
                    return;
                }
                m.invoke(this, o);
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, "Can not setParam with args = " + Arrays.toString(args), ex);
        }

    }

    private void checkSettingsRef() {

        try {
            File crcljavaDir = new File(System.getProperty("user.home"), CRCLJAVA_USER_DIR);
            crcljavaDir.mkdirs();
            File settingsRef = new File(crcljavaDir, SETTINGSREF);
            if (!settingsRef.exists() || !settingsRef.canRead()) {
                return;
            }
            String prefsFileName = new String(Files.readAllBytes(settingsRef.toPath())).trim();
            File prefsFile = new File(prefsFileName);
            if (prefsFile.exists() && prefsFile.canRead()) {
                if (JOptionPane.showConfirmDialog(this, "Load settings from " + prefsFileName) == JOptionPane.YES_OPTION) {
                    loadPrefsFile(prefsFile);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(PendantClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static final String SETTINGSREF = "clientsettingsref";
    private static final String CRCLJAVA_USER_DIR = ".crcljava";

    private void loadPrefsFile(File f) {
        try {
            File crcljavaDir = new File(System.getProperty("user.home"), CRCLJAVA_USER_DIR);
            crcljavaDir.mkdirs();
            File settingsRef = new File(crcljavaDir, SETTINGSREF);
            try (PrintStream psRef = new PrintStream(new FileOutputStream(settingsRef))) {
                psRef.println(f.getCanonicalPath());
            }
            try (BufferedReader br = Files.newBufferedReader(f.toPath())) {
                Method ma[] = this.getClass().getMethods();
//                Map<String,Method> map = 
//                Stream.of(ma)
//                        .filter(m -> Modifier.isPublic(m.getModifiers()))
//                        .filter(m -> m.getName().startsWith("set"))
//                        .filter(m -> m.getParameterTypes().length == 1)
//                        .distinct()
//                        .collect(Collectors.toMap(m -> m.getName().substring(3,4).toLowerCase()+m.getName().substring(4),
//                                m -> m));
                br.lines()
                        .filter(l -> !l.startsWith("#"))
                        .map(l -> l.split("[ \r\n\t=]+"))
                        .forEachOrdered(this::setParam);

//                        .map((String []sa) -> new Object[] { map.get(sa[0]), valueOf(map.get(sa[0]).getParameterTypes()[0],sa[1])})
//                        .forEachOrdered((Object[] oa) -> safeInvokeMethod2(((Method)oa[0]), oa[1]));
            }
            updateUIFromInternal();
        } catch (IOException iOException) {
            showMessage(iOException);
        }
    }

//    
//    private void jMenuItemSavePrefsActionPerformed(java.awt.event.ActionEvent evt) {                                                   
//        JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.home")));
//        if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(this)) {
//            File f = chooser.getSelectedFile();
//            savePrefsFile(f);
//        }
//    }                                                  
//    private void jMenuItemLoadPrefsActionPerformed(java.awt.event.ActionEvent evt) {                                                   
//        JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.home")));
//        if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(this)) {
//            File f = chooser.getSelectedFile();
//            loadPrefsFile(f);
//        }
//    }          

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddProgramItem;
    private javax.swing.JButton jButtonCloseGripper;
    private javax.swing.JButton jButtonConnect;
    private javax.swing.JButton jButtonDeletProgramItem;
    private javax.swing.JButton jButtonDisconnect;
    private javax.swing.JButton jButtonEditProgramItem;
    private javax.swing.JButton jButtonEnd;
    private javax.swing.JButton jButtonInit;
    private javax.swing.JButton jButtonMoveTo;
    private javax.swing.JButton jButtonMoveToCurrent;
    private javax.swing.JButton jButtonOpenGripper;
    private javax.swing.JButton jButtonPlotProgramItem;
    private javax.swing.JButton jButtonProgramAbort;
    private javax.swing.JButton jButtonProgramPause;
    private javax.swing.JButton jButtonProgramRun;
    private javax.swing.JButton jButtonRecordPoint;
    private javax.swing.JButton jButtonResume;
    private javax.swing.JButton jButtonRunProgFromCurrentLine;
    private javax.swing.JButton jButtonStepBack;
    private javax.swing.JButton jButtonStepFwd;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDebugReadStatus;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDebugSendCommand;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDebugWaitForDone;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemJoints;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemPlotXYZ;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemQuitProgramOnTestCommandFail;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemRecordCommands;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemRecordPoseList;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemReplaceState;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemUseEXI;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemUseReadStatusThread;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemValidateXml;
    private javax.swing.JCheckBox jCheckBoxPoll;
    private javax.swing.JCheckBox jCheckBoxStraight;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBoxXYZRPY;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelJogMinus;
    private javax.swing.JLabel jLabelJogMinus1;
    private javax.swing.JLabel jLabelJogPlus;
    private javax.swing.JLabel jLabelJogPlus1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBarReplaceCommandState;
    private javax.swing.JMenu jMenuCmds;
    private javax.swing.JMenu jMenuCommandRecent;
    private javax.swing.JMenuItem jMenuItemClearRecordedPoints;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemLoadPrefs;
    private javax.swing.JMenuItem jMenuItemOpenStatusLog;
    private javax.swing.JMenuItem jMenuItemOpenXmlCommandInstance;
    private javax.swing.JMenuItem jMenuItemOpenXmlProgram;
    private javax.swing.JMenuItem jMenuItemPoseList3DPlot;
    private javax.swing.JMenuItem jMenuItemRunTest;
    private javax.swing.JMenuItem jMenuItemSavePoseList;
    private javax.swing.JMenuItem jMenuItemSavePrefs;
    private javax.swing.JMenuItem jMenuItemSaveProgramAs;
    private javax.swing.JMenuItem jMenuItemSetSchemaFiles;
    private javax.swing.JMenuItem jMenuItemShowCommandLog;
    private javax.swing.JMenuItem jMenuItemViewLogFile;
    private javax.swing.JMenuItem jMenuItemXPathQuery;
    private javax.swing.JMenu jMenuOptions;
    private javax.swing.JMenu jMenuRecentProgram;
    private javax.swing.JMenu jMenuTools;
    private javax.swing.JMenu jMenuXmlSchemas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelJogMinus;
    private javax.swing.JPanel jPanelJogMinus1;
    private javax.swing.JPanel jPanelJogPlus;
    private javax.swing.JPanel jPanelJogPlus1;
    private javax.swing.JPanel jPanelJogging;
    private javax.swing.JPanel jPanelMoveTo;
    private javax.swing.JPanel jPanelProgram;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPaneProgram;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableJoints;
    private javax.swing.JTable jTableMoveToPose;
    private javax.swing.JTable jTablePose;
    private javax.swing.JTable jTableProgram;
    private javax.swing.JTextArea jTextAreaErrors;
    private javax.swing.JTextArea jTextAreaSelectedProgramCommand;
    private javax.swing.JTextField jTextFieldHost;
    private javax.swing.JTextField jTextFieldJogInterval;
    private javax.swing.JTextField jTextFieldJointJogIncrement;
    private javax.swing.JTextField jTextFieldJointJogSpeed;
    private javax.swing.JTextField jTextFieldPollTime;
    private javax.swing.JTextField jTextFieldPort;
    private javax.swing.JTextField jTextFieldRPYJogIncrement;
    private javax.swing.JTextField jTextFieldRotationSpeed;
    private javax.swing.JTextField jTextFieldRunTime;
    private javax.swing.JTextField jTextFieldStatCmdID;
    private javax.swing.JTextField jTextFieldStatus;
    private javax.swing.JTextField jTextFieldStatusID;
    private javax.swing.JTextField jTextFieldStatusStateDescription;
    private javax.swing.JTextField jTextFieldTransSpeed;
    private javax.swing.JTextField jTextFieldXYZJogIncrement;
    private crcl.ui.LengthUnitComboBox lengthUnitComboBoxLengthUnit;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean isDebugSendCommandSelected() {
        return this.jCheckBoxMenuItemDebugSendCommand.isSelected();
    }

    @Override
    public boolean isDebugReadStatusSelected() {
        return this.jCheckBoxMenuItemDebugReadStatus.isSelected();
    }

    @Override
    public void showLastProgramLineExecTimeMillisDists(long millis, double dist, boolean result) {
        DefaultTableModel dtm = (DefaultTableModel) this.jTableProgram.getModel();
        final int row = this.jTableProgram.getSelectedRow();
        if (row >= 0 && row < dtm.getRowCount()) {
            dtm.setValueAt(millis, row, 2);
            dtm.setValueAt(dist, row, 3);
            dtm.setValueAt(result, row, 4);
        }
    }

    @Override
    public boolean checkUserText(String text) {
        return MultiLineStringJPanel.showText(text);
    }

}
