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
package crcl.ui.client;

import crcl.base.ActuateJointType;
import crcl.base.ActuateJointsType;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLProgramType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStateEnumType;
import crcl.base.CommandStatusType;
import crcl.base.EndCanonType;
import crcl.base.GripperStatusType;
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
import crcl.ui.misc.ListChooserJPanel;
import crcl.ui.misc.MultiLineStringJPanel;
import crcl.ui.misc.ObjTableJPanel;
import crcl.ui.misc.XpathQueryJFrame;
import static crcl.ui.IconImages.DISCONNECTED_IMAGE;
import static crcl.ui.IconImages.DONE_IMAGE;
import static crcl.ui.IconImages.ERROR_IMAGE;
import static crcl.ui.IconImages.WORKING_IMAGE;
import crcl.ui.XFuture;
import static crcl.ui.client.PendantClientJPanel.PoseDisplayMode.XYZ_XAXIS_ZAXIS;
import static crcl.ui.misc.ObjTableJPanel.getAssignableClasses;
import crcl.utils.CRCLException;
import crcl.utils.CRCLPosemath;
import crcl.utils.CRCLSocket;
import crcl.utils.outer.interfaces.PendantClientMenuOuter;
import crcl.utils.outer.interfaces.PendantClientOuter;
import crcl.utils.ProgramPlotter;
import diagapplet.plotter.PlotData;
import diagapplet.plotter.plotterJFrame;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dialog;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class PendantClientJPanel extends javax.swing.JPanel implements PendantClientOuter {

    transient private final PendantClientInner internal;
    private static final double RPY_JOG_INCREMENT_DEFAULT = 3.0;
    private double rpyJogIncrement = RPY_JOG_INCREMENT_DEFAULT;
    private long pauseTime = -1;
    private boolean jogWorldSpeedsSet = false;
    private static final Logger LOGGER = Logger.getLogger(PendantClientJPanel.class.getName());
    private XpathQueryJFrame xqJFrame = null;
    private diagapplet.plotter.plotterJFrame xyzPlotter = null;
    private diagapplet.plotter.plotterJFrame jointsPlotter = null;

    //    javax.swing.Timer pollTimer = null;
    transient private Thread pollingThread = null;
    transient private volatile boolean statusRequested = false;
    private long max_diff_readStatusEndTime_requestStatusStartTime = 0;
    private long maxPollStatusCycleTime = 0;
    private long cycles = 0;
    private LengthUnitEnumType lengthUnit = LengthUnitEnumType.MILLIMETER;
    transient private CRCLProgramType recordPointsProgram = null;
    private static final String SETTINGSREF = "clientsettingsref";
    private static final String CRCLJAVA_USER_DIR = ".crcljava";
    private static final String recent_files_dir = ".crcl_pendant_client_recent_files";

    private PendantClientMenuOuter menuOuter;

    @Override
    public boolean checkUserText(String text) throws InterruptedException, ExecutionException {
        return MultiLineStringJPanel.showText(text).get();
    }

    public void pauseCrclProgram() {
        pauseTime = System.currentTimeMillis();
        internal.pause();
        this.jButtonResume.setEnabled(internal.isPaused());
        this.jButtonProgramPause.setEnabled(internal.isRunningProgram());
        jogWorldSpeedsSet = false;
    }

    public void showJointsPlot() {
//        if (this.jCheckBoxMenuItemJoints.isSelected()) {
        jointsPlotter = new plotterJFrame();
        jointsPlotter.setTitle("JOINTS");
        jointsPlotter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jointsPlotter.setVisible(true);
//        }
    }

    public void showSetSchemaFilesDialog() {
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
            CRCLSocket.saveCmdSchemaFiles(PendantClientJPanel.cmdSchemasFile, fa);
            internal.setStatSchema(fa);
            CRCLSocket.saveStatSchemaFiles(PendantClientJPanel.statSchemasFile, fa);
            internal.setProgramSchema(fa);
            CRCLSocket.saveProgramSchemaFiles(PendantClientJPanel.programSchemasFile, fa);
        }
    }

    @Override
    public void showLastProgramLineExecTimeMillisDists(long millis, double dist, boolean result) {
        DefaultTableModel dtm = (DefaultTableModel) this.jTableProgram.getModel();
        final int row = getProgramRow();
        if (row >= 0 && row < dtm.getRowCount()) {
            dtm.setValueAt(millis, row, 2);
            dtm.setValueAt(dist, row, 3);
            dtm.setValueAt(result, row, 4);
        }
    }

    /**
     * Get the value of menuOuter
     *
     * @return the value of menuOuter
     */
    public PendantClientMenuOuter getMenuOuter() {
        return menuOuter;
    }

    /**
     * Set the value of menuOuter
     *
     * @param menuOuter new value of menuOuter
     */
    public void setMenuOuter(PendantClientMenuOuter menuOuter) {
        this.menuOuter = menuOuter;
    }

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

    private static void scrollToVisible(JTable table, int rowIndex, int vColIndex) {
        Container container = table.getParent();
        if (container instanceof JViewport) {
            JViewport viewport = (JViewport) container;
            Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);
            Point pt = viewport.getViewPosition();
            rect.setLocation(rect.x - pt.x, rect.y - pt.y);
            viewport.scrollRectToVisible(rect);
        } else {
            throw new IllegalStateException("Tables parent " + container + " needs to be a JViewPort");
        }
    }

    public int getCurrentProgramLine() {
        return jTableProgram.getSelectionModel().getMinSelectionIndex();
    }

    int programLineShowing = -1;

    public static interface ProgramLineListener {

        public void accept(PendantClientJPanel panel, int line, CRCLProgramType program, CRCLStatusType status);
    }

    public static interface CurrentPoseListener {

        public void accept(PendantClientJPanel panel, PoseType pose);
    }

    private final ConcurrentLinkedDeque<CurrentPoseListener> currentPoseListeners = new ConcurrentLinkedDeque<>();

    public void addCurrentPoseListener(CurrentPoseListener l) {
        synchronized (programLineListeners) {
            if (!currentPoseListeners.contains(l)) {
                currentPoseListeners.add(l);
            }
        }
    }

    public void removeCurrentPoseListener(CurrentPoseListener l) {
        currentPoseListeners.remove(l);
    }

    private final List<ProgramLineListener> programLineListeners = new ArrayList<>();

    public void addProgramLineListener(ProgramLineListener l) {
        synchronized (programLineListeners) {
            if (!programLineListeners.contains(l)) {
                programLineListeners.add(l);
            }
        }
    }

    public void removeProgramLineListener(ProgramLineListener l) {
        programLineListeners.remove(l);
    }

    public CRCLStatusType getStatus() {
        return internal.getStatus();
    }

    private void finishShowCurrentProgramLine(final int line, final CRCLProgramType program, final CRCLStatusType status) {
        if (line != programLineShowing) {
            if (null != program) {
                if (getProgramRow() != line) {
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
                        InitCanonType cmd = program.getInitCanon();
                        String cmdString = this.internal.getTempCRCLSocket().commandToPrettyString(cmd);
                        showSelectedProgramCommand(cmdString);
                    } catch (JAXBException | CRCLException ex) {
                        Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (line > 0 && (null == program.getMiddleCommand())) {
                    try {
                        EndCanonType cmd = program.getEndCanon();
                        String cmdString = this.internal.getTempCRCLSocket().commandToPrettyString(cmd);
                        showSelectedProgramCommand(cmdString);
                    } catch (JAXBException | CRCLException ex) {
                        Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (line > 0 && line <= program.getMiddleCommand().size()) {
                    try {
                        MiddleCommandType cmd = program.getMiddleCommand().get(line - 1);
                        String cmdString = this.internal.getTempCRCLSocket().commandToPrettyString(cmd);
                        showSelectedProgramCommand(cmdString);
                    } catch (JAXBException | CRCLException ex) {
                        Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (line == program.getMiddleCommand().size() + 1) {
                    try {
                        EndCanonType cmd = program.getEndCanon();
                        String cmdString = this.internal.getTempCRCLSocket().commandToPrettyString(cmd);
                        showSelectedProgramCommand(cmdString);
                    } catch (JAXBException | CRCLException ex) {
                        Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (null == overheadProgramPlotter) {
                    setOverheadProgramPlotter(new ProgramPlotter(ProgramPlotter.View.OVERHEAD));
                }
                if (null == sideProgramPlotter) {
                    setSideProgramPlotter(new ProgramPlotter(ProgramPlotter.View.SIDE));
                }
                programPlotterJPanelOverhead.setProgram(program);
                programPlotterJPanelSide.setProgram(program);
                programPlotterJPanelOverhead.setIndex(line);
                programPlotterJPanelSide.setIndex(line);
                programPlotterJPanelOverhead.repaint();
                programPlotterJPanelSide.repaint();

            } else {
                showSelectedProgramCommand("No Program loaded.");
            }
        }
        for (int i = 0; i < programLineListeners.size(); i++) {
            ProgramLineListener l = programLineListeners.get(i);
            l.accept(this, line, program, status);
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
    public void showCurrentProgramLine(final int line, CRCLProgramType program, CRCLStatusType status) {
        if (line >= this.jTableProgram.getRowCount() || line < 0) {
            return;
        }
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            finishShowCurrentProgramLine(line, program, status);
        } else {
            final CRCLStatusType curInternalStatus = CRCLPosemath.copy(status);
            java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    finishShowCurrentProgramLine(line, program, curInternalStatus);
                }
            });
        }
    }

    /**
     * Creates new form PendantClientJPanel
     *
     * @throws javax.xml.parsers.ParserConfigurationException when xml schemas
     * are invalid.
     */
    public PendantClientJPanel() throws ParserConfigurationException {
        initComponents();
        this.internal = new PendantClientInner(this);
        init();
    }

    private void init() {
        String portPropertyString = System.getProperty("crcl4java.port");
        if (null != portPropertyString) {
            this.jTextFieldPort.setText(portPropertyString);
        }
        String hostPropertyString = System.getProperty("crcl4java.host");
        if (null != hostPropertyString) {
            this.jTextFieldHost.setText(hostPropertyString);
        }
        internal.setStatSchema(CRCLSocket.readStatSchemaFiles(PendantClientJPanel.statSchemasFile));
        internal.setCmdSchema(CRCLSocket.readCmdSchemaFiles(PendantClientJPanel.cmdSchemasFile));
        internal.setProgramSchema(CRCLSocket.readProgramSchemaFiles(PendantClientJPanel.programSchemasFile));
//        readRecentCommandFiles();
//        readRecentPrograms();
        final String programPropertyString = System.getProperty("crcl4java.program");
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
        this.jTableProgram.getSelectionModel().addListSelectionListener(e -> finishShowCurrentProgramLine(getProgramRow(), internal.getProgram(), internal.getStatus()));
        this.internal.addPropertyChangeListener(new PendantClientJPanel.MyPropertyChangeListener());
        this.transformJPanel1.setPendantClient(this);
        this.jTextFieldStatus.setBackground(Color.GRAY);
    }

    private void checkMenuOuter() throws IllegalStateException {
        if (null == menuOuter) {
            throw new IllegalStateException("Outer Menu Functions supplier not set.");
        }
    }

    @Override
    public String getHost() {
        return this.jTextFieldHost.getText();
    }

    @Override
    public int getPort() {
        return Integer.parseInt(this.jTextFieldPort.getText());
    }

    public void setPort(int port) {
        jTextFieldPort.setText(Integer.toString(port));
    }

    private Optional<Object> safeInvokeMethod(Method m, Object o) {
        try {
            return Optional.ofNullable(m.invoke(o));

        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(PendantClientJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.empty();
    }

    public void resetPrefs() {
        File crcljavaDir = new File(System.getProperty("user.home"), CRCLJAVA_USER_DIR);
        if (crcljavaDir.exists()) {
            File settingsRef = new File(crcljavaDir, SETTINGSREF);
            boolean deleted = settingsRef.delete();
            System.out.println(settingsRef + ".delete() returned " + deleted);
        }
        rpyJogIncrement = RPY_JOG_INCREMENT_DEFAULT;
        internal.resetPrefs();
        updateUIFromInternal();
    }

    private void savePrefsFile(File f) {
        try {
            File crcljavaDir = new File(System.getProperty("user.home"), CRCLJAVA_USER_DIR);
            boolean made_dir = crcljavaDir.mkdirs();
            Logger.getLogger(PendantClientJPanel.class.getName()).finest(() -> "mkdir " + crcljavaDir + " returned " + made_dir);
            File settingsRef = new File(crcljavaDir, SETTINGSREF);
            try (PrintStream psRef = new PrintStream(new FileOutputStream(settingsRef))) {
                psRef.println(f.getCanonicalPath());
            }
            try (PrintStream ps = new PrintStream(new FileOutputStream(f))) {
                Method ma[] = this.getClass().getMethods();
                Stream
                        .of(ma)
                        .filter(m -> Modifier.isPublic(m.getModifiers()))
                        .filter(m -> m.getName().startsWith("is"))
                        .filter(m -> m.getParameterTypes().length == 0)
                        .filter(m -> m.getReturnType().isAssignableFrom(boolean.class
                ))
                        .map(m -> safeInvokeMethod(m, PendantClientJPanel.this)
                        .map(result -> m.getReturnType().getCanonicalName() + " " + m.getName().substring(2, 3).toLowerCase() + m.getName().substring(3) + "=" + result.toString())
                        .orElse("# could not invoke" + m.getName()))
                        .forEachOrdered(ps::println);
                Stream.of(ma)
                        .filter(m -> Modifier.isPublic(m.getModifiers()))
                        .filter(m -> m.getName().startsWith("get"))
                        .filter(m -> m.getParameterTypes().length == 0)
                        .map(m -> safeInvokeMethod(m, PendantClientJPanel.this)
                        .map(result -> m.getReturnType().getCanonicalName() + " " + m.getName().substring(3, 4).toLowerCase() + m.getName().substring(4) + "=" + result.toString())
                        .orElse("# could not invoke" + m.getName()))
                        .forEachOrdered(ps::println);
                ma = this.internal.getClass().getMethods();
                Stream
                        .of(ma)
                        .filter(m -> Modifier.isPublic(m.getModifiers()))
                        .filter(m -> m.getName().startsWith("is"))
                        .filter(m -> m.getParameterTypes().length == 0)
                        .filter(m -> m.getReturnType().isAssignableFrom(boolean.class
                ))
                        .map(m -> safeInvokeMethod(m, PendantClientJPanel.this.internal)
                        .map(result -> m.getReturnType().getCanonicalName() + " internal." + m.getName().substring(2, 3).toLowerCase() + m.getName().substring(3) + "=" + result.toString())
                        .orElse("# could not invoke" + m.getName()))
                        .forEachOrdered(ps::println);
                Stream.of(ma)
                        .filter(m -> Modifier.isPublic(m.getModifiers()))
                        .filter(m -> m.getName().startsWith("get"))
                        .filter(m -> m.getParameterTypes().length == 0)
                        .map(m -> safeInvokeMethod(m, PendantClientJPanel.this.internal)
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
                    .filter(m -> m.getParameterTypes()[0].isAssignableFrom(String.class
            ))
                    .findAny()
                    .orElse(null);
            if (null != vmethod) {
                return (T) vmethod.invoke(null, s);

            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(PendantClientJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
        if (clss.isAssignableFrom(String.class
        )) {
            return (T) s;

        } else if (clss.isAssignableFrom(double.class
        )) {
            return (T) Double.valueOf(s);

        } else if (clss.isAssignableFrom(float.class
        )) {
            return (T) Float.valueOf(s);

        } else if (clss.isAssignableFrom(long.class
        )) {
            return (T) Long.valueOf(s);

        } else if (clss.isAssignableFrom(int.class
        )) {
            return (T) Integer.valueOf(s);

        } else if (clss.isAssignableFrom(short.class
        )) {
            return (T) Short.valueOf(s);

        } else if (clss.isAssignableFrom(byte.class
        )) {
            return (T) Byte.valueOf(s);

        } else if (clss.isAssignableFrom(Double.class
        )) {
            return (T) Double.valueOf(s);

        } else if (clss.isAssignableFrom(Float.class
        )) {
            return (T) Float.valueOf(s);

        } else if (clss.isAssignableFrom(Long.class
        )) {
            return (T) Long.valueOf(s);

        } else if (clss.isAssignableFrom(Integer.class
        )) {
            return (T) Integer.valueOf(s);

        } else if (clss.isAssignableFrom(Short.class
        )) {
            return (T) Short.valueOf(s);

        } else if (clss.isAssignableFrom(Byte.class
        )) {
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

                default:
                    return;
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
            Logger.getLogger(PendantClientJPanel.class
                    .getName()).log(Level.SEVERE, "Can not setParam with args = " + Arrays.toString(args), ex);
        }

    }

    private void checkSettingsRef() {

        try {
            File crcljavaDir = new File(System.getProperty("user.home"), CRCLJAVA_USER_DIR);
            boolean made_dir = crcljavaDir.mkdirs();
            Logger.getLogger(PendantClientJPanel.class.getName()).finest(() -> "mkdir " + crcljavaDir + " returned " + made_dir);
            File settingsRef = new File(crcljavaDir, SETTINGSREF);
            if (!settingsRef.exists() || !settingsRef.canRead()) {
                return;
            }
            String prefsFileName = new String(Files.readAllBytes(settingsRef.toPath())).trim();
            File prefsFile = new File(prefsFileName);
            if (prefsFile.exists() && prefsFile.canRead()) {
                loadPrefsFile(prefsFile);

            }
        } catch (IOException ex) {
            Logger.getLogger(PendantClientJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

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
    }

    public void loadProperties() {
        if (null != propertiesFile && propertiesFile.exists()) {
            loadPrefsFile(propertiesFile);
        }
    }

    public void saveProperties() {
        savePrefsFile(propertiesFile);
    }

    private void loadPrefsFile(File f) {
        try {
            File crcljavaDir = new File(System.getProperty("user.home"), CRCLJAVA_USER_DIR);
            boolean made_dir = crcljavaDir.mkdirs();
            Logger.getLogger(PendantClientJPanel.class.getName()).finest(() -> "mkdir " + crcljavaDir + " returned " + made_dir);
            File settingsRef = new File(crcljavaDir, SETTINGSREF);
            try (PrintStream psRef = new PrintStream(new FileOutputStream(settingsRef))) {
                psRef.println(f.getCanonicalPath());
            }
            try (BufferedReader br = Files.newBufferedReader(f.toPath())) {
//                Method ma[] = this.getClass().getMethods();
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
    public static PoseType tableToPose(JTable table) {
        PoseType p = new PoseType();
        TableModel tm = table.getModel();
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
        return p;
    }

    public int getProgramRow() {
        final int selectedRows[] = this.jTableProgram.getSelectedRows();
        return (null == selectedRows || selectedRows.length < 1) ? 0 : selectedRows[0];
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
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

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

    @Override
    public boolean isMonitoringHoldingObject() {
        return this.jCheckBoxMonitorHoldingOutput.isSelected();
    }

    @Override
    public void setExpectedHoldingObject(boolean x) {
        this.jLabelExpectHoldingObject.setText("Expect Holding Object:" + x);
        if (x) {
            jLabelExpectHoldingObject.setForeground(Color.BLACK);
            jLabelExpectHoldingObject.setBackground(Color.WHITE);
        } else {
            jLabelExpectHoldingObject.setForeground(Color.WHITE);
            jLabelExpectHoldingObject.setBackground(Color.BLACK);
        }
    }

    public boolean isHoldingObjectExpected() {
        return internal.isHoldingObjectExpected();
    }

    private class MyPropertyChangeListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            switch (evt.getPropertyName()) {
                case PendantClientInner.PROP_LENGTHUNIT:
                    updateLengthUnit(internal.getLengthUnit());
                    break;

                default:
                    // Ignore unrecognized properties.
                    break;
            }
        }
    }

    private void clearProgramTimesDistances() {
        DefaultTableModel dtm = (DefaultTableModel) this.jTableProgram.getModel();
        for (int i = 0; i < dtm.getRowCount(); i++) {
            dtm.setValueAt(-1, i, 2);
            dtm.setValueAt(0.0, i, 3);
            dtm.setValueAt(false, i, 4);
        }
        programLineShowing = -1;
        jogWorldSpeedsSet = false;
    }

    public PoseType getCurrentPose() {
        return CRCLPosemath.getPose(internal.getStatus());
    }

    public Optional<CRCLStatusType> getCurrentStatus() {
        return Optional.ofNullable(internal)
                .map(x -> x.getStatus());
    }

    public Optional<CommandStateEnumType> getCurrentState() {
        return getCurrentStatus()
                .map(x -> x.getCommandStatus())
                .map(x -> x.getCommandState());
    }

    public MiddleCommandType getCurrentProgramCommand() {
        CRCLProgramType program = internal.getProgram();
        if (null == program) {
            return null;
        }
        int curRow = getProgramRow();
        if (curRow > program.getMiddleCommand().size() || curRow < 1) {
            return null;
        }
        return program.getMiddleCommand().get(curRow - 1);
    }

    private int pollStopCount = 0;

    private void pollStatus() {
        try {
            final int startPollStopCount = pollStopCount;
            while (!Thread.currentThread().isInterrupted()
                    && this.jCheckBoxPoll.isSelected()
                    && null != internal.getCRCLSocket()
                    && startPollStopCount == pollStopCount) {
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
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
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

    public void setDebugInterrupts(boolean debugInterrupts) {
        internal.setDebugInterrupts(debugInterrupts);
    }

    public boolean isDebugInterrupts() {
        return internal.isDebugInterrupts();
    }

    @Override
    public void stopPollTimer() {
        pollStopCount++;
        if (null != pollingThread) {
            try {
                pollingThread.join(100 + internal.getPoll_ms());
            } catch (InterruptedException ex) {
            }
            if (pollingThread.isAlive()) {
                if (this.internal.isDebugInterrupts()) {
                    Thread.dumpStack();
                    System.err.println("Interruptint pollingThread = " + pollingThread);
                    System.out.println("Interruptint pollingThread = " + pollingThread);
                    System.out.println("pollingThread.getStackTrace() = " + Arrays.toString(pollingThread.getStackTrace()));
                    StackTraceElement ste[] = internal.getCallingRunProgramStackTrace().get();
                    if (null != ste) {
                        System.out.println("CallingRunProgramStackTrace: ");
                        for (StackTraceElement el : ste) {
                            System.out.println(el);
                        }
                        System.out.println("End CallingRunProgramStackTrace");
                        System.out.println("");
                    }
                }
                pollingThread.interrupt();
                try {
                    pollingThread.join(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            pollingThread = null;
            if (statusRequested) {
                internal.readStatus();
            }

        }
    }

    PendantClientInner getInternal() {
        return internal;
    }

    public void updateUIFromInternal() {
        this.jTextFieldJointJogIncrement.setText(Double.toString(internal.getJogIncrement()));
        this.jTextFieldXYZJogIncrement.setText(Double.toString(internal.getXyzJogIncrement()));
        this.jTextFieldJointJogSpeed.setText(Double.toString(internal.getJogJointSpeed()));
        this.jTextFieldTransSpeed.setText(Double.toString(internal.getJogTransSpeed()));
        this.jTextFieldRPYJogIncrement.setText(Double.toString(this.rpyJogIncrement));
        this.jTextFieldJogInterval.setText(Double.toString(internal.getJogInterval()));
        this.jTextFieldPollTime.setText(Integer.toString(internal.getPoll_ms()));
        this.stopPollTimer();
        if (this.jCheckBoxPoll.isSelected()) {
            this.startPollTimer();
        }
        if(isDisableTextPopups()) {
            crcl.ui.misc.MultiLineStringJPanel.disableShowText = true;
        }
    }

    public void openXmlProgramFile(File f) {
        try {
            this.clearProgramTimesDistances();
            this.clearRecordedPoints();
            internal.openXmlProgramFile(f, true);
        } catch (SAXException | IOException | CRCLException | XPathExpressionException | ParserConfigurationException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            showMessage(ex);
        }
    }

    public void saveXmlProgramFile(File f) throws JAXBException, CRCLException {
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

    private File lastOpenedProgramFile;

    /**
     * Get the value of lastOpenedProgramFile
     *
     * @return the value of lastOpenedProgramFile
     */
    public File getLastOpenedProgramFile() {
        return lastOpenedProgramFile;
    }

    /**
     * Set the value of lastOpenedProgramFile
     *
     * @param lastOpenedProgramFile new value of lastOpenedProgramFile
     */
    public void setLastOpenedProgramFile(File lastOpenedProgramFile) {
        this.lastOpenedProgramFile = lastOpenedProgramFile;
    }

    transient private ProgramPlotter sideProgramPlotter;

    /**
     * Get the value of sideProgramPlotter
     *
     * @return the value of sideProgramPlotter
     */
    public ProgramPlotter getSideProgramPlotter() {
        return sideProgramPlotter;
    }

    /**
     * Set the value of sideProgramPlotter
     *
     * @param sideProgramPlotter new value of sideProgramPlotter
     */
    public void setSideProgramPlotter(ProgramPlotter sideProgramPlotter) {
        this.sideProgramPlotter = sideProgramPlotter;
        this.programPlotterJPanelSide.setPlotter(sideProgramPlotter);
    }

    transient private ProgramPlotter overheadProgramPlotter;

    /**
     * Get the value of overheadProgramPlotter
     *
     * @return the value of overheadProgramPlotter
     */
    public ProgramPlotter getOverheadProgramPlotter() {
        return overheadProgramPlotter;
    }

    /**
     * Set the value of overheadProgramPlotter
     *
     * @param overheadProgramPlotter new value of overheadProgramPlotter
     */
    public void setOverheadProgramPlotter(ProgramPlotter overheadProgramPlotter) {
        this.overheadProgramPlotter = overheadProgramPlotter;
        this.programPlotterJPanelOverhead.setPlotter(overheadProgramPlotter);
    }

    public CRCLProgramType getProgram() {
        return internal.getProgram();
    }

    public void setProgram(CRCLProgramType program) throws JAXBException {
        this.internal.setProgram(program);
        this.showProgram(program);
    }

    @Override
    public void finishOpenXmlProgramFile(File f,
            CRCLProgramType program, boolean saveRecent) {
        try {
            this.recordPointsProgram = null;
            showProgram(program);
            internal.setProgram(program);
            this.saveRecentProgram(f);
            this.jTabbedPaneLeftUpper.setSelectedComponent(this.jPanelProgram);
            if (null == overheadProgramPlotter) {
                setOverheadProgramPlotter(new ProgramPlotter(ProgramPlotter.View.OVERHEAD));
            }
            if (null == sideProgramPlotter) {
                setSideProgramPlotter(new ProgramPlotter(ProgramPlotter.View.SIDE));
            }
            programPlotterJPanelOverhead.setProgram(program);
            programPlotterJPanelSide.setProgram(program);
            programPlotterJPanelOverhead.repaint();
            programPlotterJPanelSide.repaint();
            jTabbedPaneRightUpper.setSelectedComponent(jPanelProgramPlot);
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

    public Set<String> getRecentPrograms() {
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
                    boolean was_deleted = f.delete();
                    if (!was_deleted) {
                        Logger.getLogger(PendantClientJPanel.class.getName()).warning(() -> "File " + f + " delete() returned" + was_deleted);
                    }
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

    private final static File statSchemasFile = new File(System.getProperty("user.home"),
            ".crcljava_pendantclient_stat_schemas.txt");

    private final static File cmdSchemasFile = new File(System.getProperty("user.home"),
            ".crcljava_pendantclient_cmd_schemas.txt");

    private final static File programSchemasFile = new File(System.getProperty("user.home"),
            ".crcljava_pendantclient_cmd_schemas.txt");

    private boolean showing_message = false;
    private volatile long last_message_show_time = 0;

    private Container outerContainer;
    private boolean searchedForOuterFrame = false;

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

    private Container searchForOuterContainer() {
        if (searchedForOuterFrame) {
            return outerContainer;
        }
        searchedForOuterFrame = true;
        Container container = this;
        while (null != (container = container.getParent())) {
            if (container instanceof JFrame) {
                return container;
            }
            if (container instanceof JInternalFrame) {
                return container;
            }
        }
        return null;
    }

    private Window searchForOuterWindow() {
        if (null != outerContainer && outerContainer instanceof Window) {
            return (Window) outerContainer;
        }
        searchedForOuterFrame = true;
        Container container = this;
        while (null != (container = container.getParent())) {
            if (container instanceof Window) {
                return (Window) container;
            }
        }
        return null;
    }

    /**
     * Get the value of outerFrame
     *
     * @return the value of outerFrame
     */
    public Container getOuterContainer() {
        if (null == outerContainer) {
            outerContainer = searchForOuterContainer();
        }
        return outerContainer;
    }

    /**
     * Get the value of outerFrame
     *
     * @return the value of outerFrame
     */
    public Window getOuterWindow() {
        if (null != outerContainer && outerContainer instanceof Window) {
            return (Window) outerContainer;
        }
        if (null == outerContainer) {
            outerContainer = searchForOuterContainer();
        }
        if (null != outerContainer && outerContainer instanceof Window) {
            return (Window) outerContainer;
        }
        return searchForOuterWindow();
    }

    /**
     * Set the value of outerFrame
     *
     * @param outerFrame new value of outerFrame
     */
    public void setOuterFrame(JFrame outerFrame) {
        this.outerContainer = outerFrame;
    }

    @Override
    public void showMessage(final String s) {
        System.out.println(s);
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
                    MultiLineStringJPanel.showText(s,
                            PendantClientJPanel.this.getOuterWindow(),
                            "Message from Client",
                            Dialog.ModalityType.APPLICATION_MODAL);
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
        final CRCLStatusType curInternalStatus
                = CRCLPosemath.copy(internal.getStatus());
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            finishSetStatusPriv(curInternalStatus);
        } else {
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    PendantClientJPanel.this.finishSetStatusPriv(curInternalStatus);
                }
            });
        }
    }

    private String lastStateDescription = "";
    private String lastProgramFile = null;

    private File findProgram(String filename) {
        File f0 = new File(filename);
        if (f0.exists()) {
            return f0;
        }
        for (String recent : getRecentPrograms()) {
            File f = new File(recent);
            if (f.exists() && f.getName().equals(filename)) {
                return f;
            }
        }
        for (String recent : getRecentPrograms()) {
            File f = new File(recent).getParentFile();
            if (f.exists() && f.getName().equals(filename)) {
                return f;
            }
        }
        f0 = new File(System.getProperty("user.dir"), filename);
        if (f0.exists()) {
            return f0;
        }
        return null;
    }

    private int lastProgramIndex = 0;

    private void finishSetStatusPriv(final CRCLStatusType curInternalStatus) {
        if (null != curInternalStatus && null != curInternalStatus.getCommandStatus()) {
            CommandStatusType ccst = curInternalStatus.getCommandStatus();
            if (null != ccst) {
                if (null != ccst.getCommandID()) {
                    this.jTextFieldStatCmdID.setText(ccst.getCommandID().toString());
                }
                String stateDescription = ccst.getStateDescription();
                Container container = this.getOuterContainer();
                JFrame frame = null;
                if (container instanceof JFrame) {
                    frame = (JFrame) container;
                }
                if (null != ccst.getCommandState()) {
                    final CommandStateEnumType state = ccst.getCommandState();
                    String stateString = state.toString();
                    if (stateString.startsWith("CRCL_")) {
                        stateString = stateString.substring("CRCL_".length());
                    }
                    if (!stateString.equals(jTextFieldStatus.getText())) {
                        this.jTextFieldStatus.setText(stateString);

                        switch (state) {

                            case CRCL_ERROR:
                                this.jTextFieldStatus.setBackground(Color.RED);
                                if (null != frame) {
                                    frame.setIconImage(ERROR_IMAGE);
                                }
                                break;

                            case CRCL_WORKING:
                                this.jTextFieldStatus.setBackground(Color.GREEN);
                                if (null != frame) {
                                    frame.setIconImage(WORKING_IMAGE);
                                }
                                break;

                            case CRCL_READY:
                            case CRCL_DONE:
                            default:
                                this.jTextFieldStatus.setBackground(Color.WHITE);
                                if (null != frame) {
                                    frame.setIconImage(DONE_IMAGE);
                                }
//                            this.setIconImage(image);
                                break;

                        }
                    }
                    updateTitle(ccst, container, frame, stateString, stateDescription);

                    if (!internal.isRunningProgram()) {
                        if (null != ccst.getProgramFile()
                                && !ccst.getProgramFile().equals(internal.getOutgoingProgramFile())
                                && !ccst.getProgramFile().equals(lastProgramFile)) {
                            File f = findProgram(ccst.getProgramFile());
                            if (null != f) {
                                openXmlProgramFile(f);
                            }
                            lastProgramFile = ccst.getProgramFile();
                        }
                        if (null != ccst.getProgramIndex()) {
                            int index = ccst.getProgramIndex().intValue();
                            if (index != lastProgramIndex) {
                                finishShowCurrentProgramLine(index, internal.getProgram(), curInternalStatus);
                                lastProgramIndex = index;
                            }
                        }
                    }
                }
                this.jTextFieldStatusID.setText(ccst.getStatusID().toString());
                if (null != stateDescription && !stateDescription.equals(lastStateDescription)) {
                    this.jTextAreaStateDescription.setText(stateDescription);
                    lastStateDescription = stateDescription;
                }
                GripperStatusType gripperStatus = curInternalStatus.getGripperStatus();
                if (null == gripperStatus || null == gripperStatus.isHoldingObject()) {

                    jLabelHoldingObject.setText("HoldingObject: UNKNOWN");
                    jLabelHoldingObject.setOpaque(false);
                    jLabelHoldingObject.setForeground(Color.BLACK);
                    jLabelHoldingObject2.setText("HoldingObject: UNKNOWN");
                    jLabelHoldingObject2.setOpaque(false);
                    jLabelHoldingObject2.setForeground(Color.BLACK);
                } else {
                    if (gripperStatus.isHoldingObject()) {
                        jLabelHoldingObject.setText("HoldingObject: TRUE");
                        jLabelHoldingObject.setForeground(Color.BLACK);
                        jLabelHoldingObject.setBackground(Color.WHITE);
                        jLabelHoldingObject2.setText("HoldingObject: TRUE");
                        jLabelHoldingObject2.setForeground(Color.BLACK);
                        jLabelHoldingObject2.setBackground(Color.WHITE);
                    } else {
                        jLabelHoldingObject.setText("HoldingObject: FALSE");
                        jLabelHoldingObject.setForeground(Color.WHITE);
                        jLabelHoldingObject.setBackground(Color.BLACK);
                        jLabelHoldingObject2.setText("HoldingObject: FALSE");
                        jLabelHoldingObject2.setForeground(Color.WHITE);
                        jLabelHoldingObject2.setBackground(Color.BLACK);
                    }
                    jLabelHoldingObject.setOpaque(true);
                    jLabelHoldingObject2.setOpaque(true);
                }
            }
            JointStatusesType jsst = curInternalStatus.getJointStatuses();
            if (jsst != null) {
                List<JointStatusType> jsl = jsst.getJointStatus();
                boolean joints_changed = this.jointsChanged(jsl);
                if (joints_changed) {
                    this.copyJointPositions(jsl);
                    DefaultTableModel tm = (DefaultTableModel) this.jTableJoints.getModel();
                    double t = System.currentTimeMillis();
                    tm.setRowCount(jsl.size());
                    boolean hasForce = false;
                    boolean hasVel = false;
                    for (JointStatusType js : jsl) {
                        int jn = js.getJointNumber().intValue();
                        if (null != js.getJointVelocity()) {
                            if (tm.getColumnCount() < 3) {
                                tm.setColumnCount(3);
                            }
                            tm.setValueAt(js.getJointVelocity().doubleValue(), jn - 1, 2);
                            hasVel = true;
                        }
                        if (null != js.getJointTorqueOrForce()) {
                            if (tm.getColumnCount() < 4) {
                                tm.setColumnCount(4);
                            }
                            tm.setValueAt(js.getJointTorqueOrForce().doubleValue(), jn - 1, 3);
                            hasForce = true;
                        }
                        if (null == js.getJointPosition()) {
//                            tm.setValueAt(Double.NaN, jn-1,1);
                            continue;
                        }

                        double pos = js.getJointPosition().doubleValue();
                        tm.setValueAt(jn, jn - 1, 0);
                        tm.setValueAt(pos, jn - 1, 1);
                        if (this.getMenuOuter().isPlotJointsSelected()) {
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
                            this.jointsPlotter.AddPointToPlot(pd, t, pos, true);
                            if (pd.get_num_points() < 100) {
                                this.jointsPlotter.FitToGraph();
                            }
                        }
                    }
                    if (!hasForce && !hasVel) {
                        tm.setColumnIdentifiers(new String[]{"Joint", "Position"});
                        tm.setColumnCount(2);
                    } else if (!hasForce) {
                        tm.setColumnIdentifiers(new String[]{"Joint", "Position", "Velocity"});
                        tm.setColumnCount(3);
                    } else {
                        tm.setColumnIdentifiers(new String[]{"Joint", "Position", "Velocity", "TorqueOrForce"});
                        tm.setColumnCount(4);
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
                updatePoseTable(p, this.jTablePose, getCurrentPoseDisplayMode());
                PointType pt = p.getPoint();
                checkMenuOuter();
                if (this.menuOuter.isPlotXyzSelected()) {
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
                for (CurrentPoseListener l : currentPoseListeners) {
                    l.accept(this, p);
                }
            }
        }
    }

    List<UpdateTitleListener> updateTitleListeners = null;

    public void addUpdateTitleListener(UpdateTitleListener utl) {
        if (null == updateTitleListeners) {
            updateTitleListeners = new ArrayList<>();
        }
        updateTitleListeners.add(utl);
    }

    public void removeUpdateTitleListener(UpdateTitleListener utl) {
        if (null != updateTitleListeners) {
            updateTitleListeners.remove(utl);
            if (updateTitleListeners.size() < 1) {
                updateTitleListeners = null;
            }
        }
    }

    public void updateTitle(CommandStatusType ccst, Container container, JFrame frame, String stateString, String stateDescription) {
        String program = (null != ccst.getProgramFile() && null != ccst.getProgramIndex())
                ? " " + ccst.getProgramFile() + ":" + ccst.getProgramIndex().toString() : "";

        if (!program.isEmpty() && null != ccst.getProgramLength()) {
            program += "/" + ccst.getProgramLength().toString();
        }
        JInternalFrame internalFrame = null;
        if (container instanceof JInternalFrame) {
            internalFrame = (JInternalFrame) container;
        }
        if (null != frame) {
            String lastMessage = internal.getLastMessage();
            frame.setTitle("CRCL Client: " + stateString
                    + ((stateDescription != null && stateDescription.length() > 1) ? " : " + stateDescription : "")
                    + program
                    + ((lastMessage != null) ? " : " + lastMessage : ""));
        } else if (null != internalFrame) {
            String lastMessage = internal.getLastMessage();
            internalFrame.setTitle("CRCL Client: " + stateString
                    + ((stateDescription != null && stateDescription.length() > 1) ? " : " + stateDescription : "")
                    + program
                    + ((lastMessage != null) ? " : " + lastMessage : ""));
        }
        if (null != updateTitleListeners) {
            for (UpdateTitleListener utl : updateTitleListeners) {
                utl.titleChanged(ccst, container, stateString, stateDescription);
            }
        }
    }

    public static enum PoseDisplayMode {
        XYZ_XAXIS_ZAXIS,
        XYZ_RPY,
        XYZ_RX_RY_RZ
    };

    public static void updatePoseTable(PoseType p, JTable jTable, PoseDisplayMode displayMode) {
        try {
            DefaultTableModel tm = (DefaultTableModel) jTable.getModel();
            updatePointTable(p, tm, 0);
            switch (displayMode) {
                case XYZ_XAXIS_ZAXIS:
                    updateXaxisZaxisTable(p, tm, 3);
                    break;

                case XYZ_RPY:
                    updateRpyTable(p, tm, 3);
                    break;

                case XYZ_RX_RY_RZ:
                    updateRxRyRzTable(p, tm, 3);
                    break;

            }
        } catch (PmException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    private static void updateRpyTable(PoseType p, DefaultTableModel tm, int index) throws PmException {
        PmRpy rpy = CRCLPosemath.toPmRpy(p);
        if (null != rpy && tm.getRowCount() > 2 + index) {
            tm.setValueAt(Math.toDegrees(rpy.r), 0 + index, 1);
            tm.setValueAt(Math.toDegrees(rpy.p), 1 + index, 1);
            tm.setValueAt(Math.toDegrees(rpy.y), 2 + index, 1);
        }
    }

    private static void updateRxRyRzTable(PoseType p, DefaultTableModel tm, int index) throws PmException {
        PmRotationVector rv = CRCLPosemath.toPmRotationVector(p);
        if (null != rv && tm.getRowCount() > 2 + index) {
            double rotMagDeg = Math.toDegrees(rv.s);
            tm.setValueAt(rotMagDeg * rv.x, 0 + index, 1);
            tm.setValueAt(rotMagDeg * rv.y, 1 + index, 1);
            tm.setValueAt(rotMagDeg * rv.z, 2 + index, 1);
        }
    }

    private static void updateXaxisZaxisTable(PoseType p, DefaultTableModel tm, int index) {
        VectorType xv = p.getXAxis();
        VectorType zv = p.getZAxis();
        if (null != xv && tm.getRowCount() > 2 + index) {
            tm.setValueAt(xv.getI().doubleValue(), 0 + index, 1);
            tm.setValueAt(xv.getJ().doubleValue(), 1 + index, 1);
            tm.setValueAt(xv.getK().doubleValue(), 2 + index, 1);
        }
        if (null != zv && tm.getRowCount() > 5 + index) {
            tm.setValueAt(zv.getI().doubleValue(), 3 + index, 1);
            tm.setValueAt(zv.getJ().doubleValue(), 4 + index, 1);
            tm.setValueAt(zv.getK().doubleValue(), 5 + index, 1);
        }
    }

    public static void updatePointTable(PoseType p, DefaultTableModel tm, int index) {
        PointType pt = p.getPoint();
        if (null != pt && tm.getRowCount() > 2 + index) {
            tm.setValueAt(pt.getX().doubleValue(), 0 + index, 1);
            tm.setValueAt(pt.getY().doubleValue(), 1 + index, 1);
            tm.setValueAt(pt.getZ().doubleValue(), 2 + index, 1);
        }
    }

    public void disconnect() {
        this.jTextFieldStatus.setBackground(Color.GRAY);
        Window window = this.getOuterWindow();
        if (window instanceof JFrame) {
            JFrame frm = (JFrame) window;
            if (null != frm) {
                frm.setIconImage(DISCONNECTED_IMAGE);
                frm.setTitle("CRCL Client: Disconnected?");
            }
        }
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

    public boolean isConnected() {
        return internal.isConnected();
    }

    public void connect(String _host, int _port) {
        internal.connect(_host, _port);
        jogWorldSpeedsSet = false;
    }

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
        internal.setJogIncrement(Double.parseDouble(this.jTextFieldJointJogIncrement.getText()));
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
                    if (internal.getStatus().getCommandStatus().getCommandState() != CommandStateEnumType.CRCL_DONE) {
                        if (PendantClientJPanel.this.menuOuter.isDebugWaitForDoneSelected()
                                || PendantClientJPanel.this.menuOuter.isDebugSendCommandSelected()) {
                            System.err.println("Jog Timer ActionListener waiting for DONE");
                        }
                        return;
                    }
                    if (internal.getStatus().getCommandStatus().getCommandID().compareTo(internal.getCmdId()) < 0) {
                        if (PendantClientJPanel.this.menuOuter.isDebugWaitForDoneSelected()
                                || PendantClientJPanel.this.menuOuter.isDebugSendCommandSelected()) {
                            System.err.println("Jog Timer ActionListener waiting for ID greater than " + internal.getCmdId());
                        }
                        return;
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
                    jsa.setJointSpeed(BigDecimal.valueOf(Double.parseDouble(PendantClientJPanel.this.jTextFieldJointJogSpeed.getText())));
                    aj.setJointDetails(jsa);
                    ajl.add(aj);
                    internal.incAndSendCommand(ajst);
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
                tas.setSetting(BigDecimal.valueOf(Double.parseDouble(this.jTextFieldTransSpeed.getText())));
                stst.setTransSpeed(tas);
                internal.incAndSendCommand(stst);
                internal.waitForDone(stst.getCommandID(), 200);
                SetRotSpeedType srst = new SetRotSpeedType();
                RotSpeedAbsoluteType ras = new RotSpeedAbsoluteType();
                ras.setSetting(BigDecimal.valueOf(Double.parseDouble(this.jTextFieldRotationSpeed.getText())));
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

                default:
                    throw new IllegalStateException("Invalid axis selected: " + axis);
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
                        final CRCLStatusType status = internal.getStatus();
                        if (status.getCommandStatus().getCommandState() == CommandStateEnumType.CRCL_ERROR) {
                            jogStop();
                            final String statusString = status.getCommandStatus().getStateDescription();
                            javax.swing.SwingUtilities.invokeLater(() -> showMessage("Can not jog when status is " + CommandStateEnumType.CRCL_ERROR + " : "
                                    + statusString));
                        }
                        if (internal.getStatus().getCommandStatus().getCommandState() != CommandStateEnumType.CRCL_DONE) {
                            if (PendantClientJPanel.this.menuOuter.isDebugWaitForDoneSelected()
                                    || PendantClientJPanel.this.menuOuter.isDebugSendCommandSelected()) {
                                System.err.println("Jog Timer ActionListener waiting for DONE");
                            }
                            return;
                        }
                        if (internal.getStatus().getCommandStatus().getCommandID().compareTo(internal.getCmdId()) < 0) {
                            if (PendantClientJPanel.this.menuOuter.isDebugWaitForDoneSelected()
                                    || PendantClientJPanel.this.menuOuter.isDebugSendCommandSelected()) {
                                System.err.println("Jog Timer ActionListener waiting for ID greater than " + internal.getCmdId());
                            }
                            return;
                        }
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

                                default:
                                    throw new IllegalStateException("Invalid axis selected: " + axis);
                            }
                            internal.incAndSendCommand(moveToCmd);
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
                            CRCLPosemath.toPmCartesian(internal.getPoint()),
                            pm2);
                    moveToType.setEndPosition(nextPose);
                }

                private void incrementPitch(MoveToType moveToType, final double inc) throws PmException {
                    PmRotationMatrix pm = CRCLPosemath.toPmRotationMatrix(internal.getPose());
                    PmRpy rpy = Posemath.toRpy(pm);
                    rpy.p += inc;
                    PmRotationVector pm2 = Posemath.toRot(rpy);
                    PoseType nextPose = CRCLPosemath.toPoseType(
                            CRCLPosemath.toPmCartesian(internal.getPoint()),
                            pm2);
                    moveToType.setEndPosition(nextPose);
                }

                private void incrementRoll(MoveToType moveToType, final double inc) throws PmException {
                    PmRotationMatrix pm = CRCLPosemath.toPmRotationMatrix(internal.getPose());
                    PmRpy rpy = Posemath.toRpy(pm);
                    rpy.r += inc;
                    PmRotationVector pm2 = Posemath.toRot(rpy);
                    PoseType nextPose = CRCLPosemath.toPoseType(
                            CRCLPosemath.toPmCartesian(internal.getPoint()),
                            pm2);
                    moveToType.setEndPosition(nextPose);
                }
            };
            jogActionListener.actionPerformed(null);
            jog_timer = new javax.swing.Timer(internal.getJogInterval(), jogActionListener);
            jog_timer.start();
        } catch (JAXBException | InterruptedException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, "Can not start world jog.", ex);
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
                Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
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

    private void saveRecentCommandInstance(CRCLCommandInstanceType cmd) throws JAXBException, IOException {
        CRCLSocket tmpcs = internal.getTempCRCLSocket();
        String s = tmpcs.commandInstanceToPrettyDocString(cmd, true);
        File fDir = new File(System.getProperty("user.home"), recent_files_dir);
        boolean made_dir = fDir.mkdirs();
        Logger.getLogger(PendantClientJPanel.class.getName()).finest(() -> "mkdir " + fDir + " returned " + made_dir);
        String name = cmd.getCRCLCommand().getClass().getSimpleName();
        int pindex = name.lastIndexOf('.');
        if (pindex > 0 && pindex < name.length()) {
            name = name.substring(pindex + 1);
        }
        File fDir2 = new File(fDir, name);
        boolean made_dir2 = fDir2.mkdirs();
        Logger.getLogger(PendantClientJPanel.class.getName()).finest(() -> "mkdir " + fDir2 + " returned " + made_dir2);
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
        boolean made_dir = fDir.mkdirs();
        Logger.getLogger(PendantClientJPanel.class.getName()).finest(() -> "mkdir " + fDir + " returned " + made_dir);
        String name = fprog.getName();
        assert name != null : "@AssumeAssertion(nullness)";
        Date dNow = new Date();
        SimpleDateFormat ft
                = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss_a_zzz_");
        String date_string = ft.format(dNow);
        File flink = File.createTempFile(name + "_" + date_string, ".txt", fDir);
        try (FileWriter fw = new FileWriter(flink)) {
            fw.write(fprog.getCanonicalPath());
        }
    }

    public void saveRecentCommand(CRCLCommandType cmd) throws JAXBException, IOException {
        CRCLCommandInstanceType instanceForSave = new CRCLCommandInstanceType();
        instanceForSave.setCRCLCommand(cmd);
        this.saveRecentCommandInstance(instanceForSave);
    }

    public void showXpathQueryDialog() {
        if (null == xqJFrame) {
            try {
                xqJFrame = new XpathQueryJFrame();
            } catch (ParserConfigurationException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        xqJFrame.setVisible(true);
    }

    public void browseOpenCommandXml() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "XML Instance Files", "xml");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File f = chooser.getSelectedFile();
                openXmlInstanceFile(f);
            } catch (SAXException | JAXBException | CRCLException | IOException | XPathExpressionException | ParserConfigurationException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
                this.showMessage(ex);
            }
        }
    }

    public void startRunTest(Map<String, String> testPropsMap) {
        internal.startRunTestThread(testPropsMap);
    }

    public void savePoseList() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Comma-Separated-Values", "csv");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                this.internal.savePoseListToCsvFile(chooser.getSelectedFile().getAbsolutePath());
            } catch (IOException ex) {
                Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PmException ex) {
                Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void startStatusReaderThread() {
        internal.startStatusReaderThread();
    }

    public void stopStatusReaderThread() {
        internal.stopStatusReaderThread();
    }

    public void setRecordCommands(boolean recordCommands) {
        internal.setRecordCommands(recordCommands);
    }

    public void showCommandLog() {
        MultiLineStringJPanel.showText(internal.getRecordedCommandsList().stream().map(cmd -> internal.getTempCRCLSocket().commandToPrettyString(cmd, "")).collect(Collectors.joining("\n\n")));
    }

    public void loadPrefsAction() {
        JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.home")));
        if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(this)) {
            File f = chooser.getSelectedFile();
            loadPrefsFile(f);
        }
    }

    public void setQuitOnTestCommandFailure(boolean q) {
        internal.setQuitOnTestCommandFailure(q);
    }

    public void openLogFile() {
        internal.openLogFile();
    }

    public void aboutAction() {
        try {
            JOptionPane.showMessageDialog(this, getVersion());
        } catch (IOException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void savePrefsAction() {
        JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.home")));
        if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(this)) {
            File f = chooser.getSelectedFile();
            savePrefsFile(f);
        }
    }

//    public void show3DPlot() {
//        try {
////            File tmpFile = File.createTempFile("poseList", ".csv");
////            this.internal.savePoseListToCsvFile(tmpFile.getAbsolutePath());
//            com.github.wshackle.poselist3dplot.MainJFrame.showPoseList(this.internal.getPoseList());
//        } catch (Exception ex) {
//            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void useExiAction() {
        if (this.isConnected()) {
            this.disconnect();
            connectCurrent();
        }
    }

    public void showStatusLog() {
        try {
            File tmpFile = File.createTempFile("poseList", ".csv");
            this.internal.savePoseListToCsvFile(tmpFile.getAbsolutePath());
            Desktop.getDesktop().open(tmpFile);
        } catch (IOException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PmException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void browseOpenProgramXml() {
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
                Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
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

    public void openXmlInstanceFile(File f) throws CRCLException, SAXException, JAXBException, IOException, ParserConfigurationException, XPathExpressionException {
        String s = internal.getXpu().queryXml(f, "/");
        CRCLCommandInstanceType cmdInstance
                = internal.getCRCLSocket().stringToCommand(s, this.menuOuter.validateXmlSelected());
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
                Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        programLineShowing = -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPaneLeftUpper = new javax.swing.JTabbedPane();
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
        jLabelHoldingObject2 = new javax.swing.JLabel();
        jCheckBoxMonitorHoldingOutput = new javax.swing.JCheckBox();
        jLabelExpectHoldingObject = new javax.swing.JLabel();
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
        lengthUnitComboBoxLengthUnit = new crcl.ui.misc.LengthUnitComboBox();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldJointJogSpeed = new javax.swing.JTextField();
        jTextFieldTransSpeed = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldRotationSpeed = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButtonRecordPoint = new javax.swing.JButton();
        jButtonOpenGripper = new javax.swing.JButton();
        jButtonCloseGripper = new javax.swing.JButton();
        jLabelHoldingObject = new javax.swing.JLabel();
        jPanelMoveTo = new javax.swing.JPanel();
        jButtonMoveTo = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableMoveToPose = new javax.swing.JTable();
        jCheckBoxStraight = new javax.swing.JCheckBox();
        jButtonMoveToCurrent = new javax.swing.JButton();
        transformJPanel1 = new crcl.ui.misc.TransformJPanel();
        jPanelConnectionTab = new javax.swing.JPanel();
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
        jTabbedPaneRightUpper = new javax.swing.JTabbedPane();
        jPanelStatus = new javax.swing.JPanel();
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
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextAreaStateDescription = new javax.swing.JTextArea();
        jComboBoxPoseDisplayMode = new javax.swing.JComboBox<>();
        jPanelProgramPlot = new javax.swing.JPanel();
        jPanelOverheadProgramPlot = new javax.swing.JPanel();
        programPlotterJPanelOverhead = new crcl.ui.misc.ProgramPlotterJPanel();
        jPanelOverheadProgramPlot1 = new javax.swing.JPanel();
        programPlotterJPanelSide = new crcl.ui.misc.ProgramPlotterJPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaErrors = new javax.swing.JTextArea();

        jTabbedPaneLeftUpper.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPaneLeftUpper.setName(""); // NOI18N
        jTabbedPaneLeftUpper.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneLeftUpperStateChanged(evt);
            }
        });

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
        jScrollPaneProgram.setViewportView(jTableProgram);

        jButtonProgramPause.setText("Pause");
        jButtonProgramPause.setEnabled(false);
        jButtonProgramPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProgramPauseActionPerformed(evt);
            }
        });

        jButtonProgramAbort.setText("Abort");
        jButtonProgramAbort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProgramAbortActionPerformed(evt);
            }
        });

        jButtonEditProgramItem.setText("Edit Item");
        jButtonEditProgramItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditProgramItemActionPerformed(evt);
            }
        });

        jButtonDeletProgramItem.setText("Delete");
        jButtonDeletProgramItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeletProgramItemActionPerformed(evt);
            }
        });

        jButtonAddProgramItem.setText("Add");
        jButtonAddProgramItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddProgramItemActionPerformed(evt);
            }
        });

        jButtonProgramRun.setText("Run From Start");
        jButtonProgramRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProgramRunActionPerformed(evt);
            }
        });

        jButtonResume.setText("Resume");
        jButtonResume.setEnabled(false);
        jButtonResume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResumeActionPerformed(evt);
            }
        });

        jButtonPlotProgramItem.setText("Plot");
        jButtonPlotProgramItem.setEnabled(false);
        jButtonPlotProgramItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlotProgramItemActionPerformed(evt);
            }
        });

        jButtonRunProgFromCurrentLine.setText("Run from Current");
        jButtonRunProgFromCurrentLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRunProgFromCurrentLineActionPerformed(evt);
            }
        });

        jButtonStepBack.setText("Back");
        jButtonStepBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStepBackActionPerformed(evt);
            }
        });

        jButtonStepFwd.setText("Fwd");
        jButtonStepFwd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStepFwdActionPerformed(evt);
            }
        });

        jLabel12.setText("Run Time:");

        jTextFieldRunTime.setEditable(false);
        jTextFieldRunTime.setText("-1.0");

        jLabel13.setText("Selected Command:");

        jTextAreaSelectedProgramCommand.setEditable(false);
        jTextAreaSelectedProgramCommand.setColumns(20);
        jTextAreaSelectedProgramCommand.setRows(5);
        jScrollPane5.setViewportView(jTextAreaSelectedProgramCommand);

        jLabelHoldingObject2.setText("HoldingObject: UNKNOWN");
        jLabelHoldingObject2.setOpaque(true);

        jCheckBoxMonitorHoldingOutput.setText("Monitor Holding");

        jLabelExpectHoldingObject.setText("Expect Holding Object: FALSE");
        jLabelExpectHoldingObject.setOpaque(true);

        javax.swing.GroupLayout jPanelProgramLayout = new javax.swing.GroupLayout(jPanelProgram);
        jPanelProgram.setLayout(jPanelProgramLayout);
        jPanelProgramLayout.setHorizontalGroup(
            jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProgramLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProgramLayout.createSequentialGroup()
                        .addGroup(jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5)
                            .addComponent(jScrollPaneProgram))
                        .addGap(10, 10, 10))
                    .addGroup(jPanelProgramLayout.createSequentialGroup()
                        .addGroup(jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelProgramLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldRunTime, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBoxMonitorHoldingOutput)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelHoldingObject2))
                            .addComponent(jLabel13)
                            .addGroup(jPanelProgramLayout.createSequentialGroup()
                                .addComponent(jButtonProgramPause)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonResume)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonProgramAbort)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonProgramRun)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonRunProgFromCurrentLine))
                            .addGroup(jPanelProgramLayout.createSequentialGroup()
                                .addComponent(jButtonEditProgramItem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonDeletProgramItem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAddProgramItem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonPlotProgramItem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonStepBack)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonStepFwd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelExpectHoldingObject)))
                        .addGap(0, 85, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelProgramLayout.setVerticalGroup(
            jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProgramLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneProgram, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextFieldRunTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxMonitorHoldingOutput)
                    .addComponent(jLabelHoldingObject2))
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
                    .addComponent(jButtonStepFwd)
                    .addComponent(jLabelExpectHoldingObject))
                .addContainerGap())
        );

        jTabbedPaneLeftUpper.addTab("Program", jPanelProgram);

        jPanelJogging.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanelJogging.setName("Jogging"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Joint 1 (S)", "Joint 2 (L)", "Joint 3 (U)", "Joint 4 (R)", "Joint 5 (B)", "Joint 6 (T)", "Joint 7 (E)", "Joint 8 " }));

        jPanelJogMinus.setBackground(new java.awt.Color(255, 255, 255));
        jPanelJogMinus.setBorder(new javax.swing.border.MatteBorder(null));
        jPanelJogMinus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelJogMinusMousePressed(evt);
            }
        });

        jLabelJogMinus.setBackground(new java.awt.Color(255, 255, 255));
        jLabelJogMinus.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabelJogMinus.setText("Jog -");
        jLabelJogMinus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelJogMinusMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelJogMinusMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabelJogMinusMouseReleased(evt);
            }
        });

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
        jLabelJogPlus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelJogPlusMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelJogPlusMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabelJogPlusMouseReleased(evt);
            }
        });

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
        jLabelJogPlus1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelJogPlus1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelJogPlus1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabelJogPlus1MouseReleased(evt);
            }
        });

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
        jPanelJogMinus1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelJogMinus1MousePressed(evt);
            }
        });

        jLabelJogMinus1.setBackground(new java.awt.Color(255, 255, 255));
        jLabelJogMinus1.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabelJogMinus1.setText("Jog -");
        jLabelJogMinus1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelJogMinus1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabelJogMinus1MouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelJogMinus1MouseExited(evt);
            }
        });

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
        jTextFieldJointJogIncrement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldJointJogIncrementActionPerformed(evt);
            }
        });

        jTextFieldXYZJogIncrement.setText("3.0");
        jTextFieldXYZJogIncrement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldXYZJogIncrementActionPerformed(evt);
            }
        });

        jTextFieldRPYJogIncrement.setText("3.0");
        jTextFieldRPYJogIncrement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRPYJogIncrementActionPerformed(evt);
            }
        });

        jLabel9.setText("Jog Time Period (ms) :");

        jTextFieldJogInterval.setText("100");
        jTextFieldJogInterval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldJogIntervalActionPerformed(evt);
            }
        });

        lengthUnitComboBoxLengthUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lengthUnitComboBoxLengthUnitActionPerformed(evt);
            }
        });

        jLabel11.setText("Joint Jog Speed:");

        jTextFieldJointJogSpeed.setText("100");
        jTextFieldJointJogSpeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldJointJogSpeedActionPerformed(evt);
            }
        });

        jTextFieldTransSpeed.setText("3.0");
        jTextFieldTransSpeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTransSpeedActionPerformed(evt);
            }
        });

        jLabel14.setText("Trans Speed:");

        jLabel15.setText("Rotation Speed:");

        jTextFieldRotationSpeed.setText("90.0");
        jTextFieldRotationSpeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRotationSpeedActionPerformed(evt);
            }
        });

        jLabel16.setText("degrees");

        jLabel17.setText("degrees/second");

        jButtonRecordPoint.setText("Record Point");
        jButtonRecordPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecordPointActionPerformed(evt);
            }
        });

        jButtonOpenGripper.setText("Open Gripper");
        jButtonOpenGripper.setEnabled(false);
        jButtonOpenGripper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenGripperActionPerformed(evt);
            }
        });

        jButtonCloseGripper.setText("Close Gripper");
        jButtonCloseGripper.setEnabled(false);
        jButtonCloseGripper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseGripperActionPerformed(evt);
            }
        });

        jLabelHoldingObject.setText("HoldingObject: Unknown");

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
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelJogMinus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelJogPlus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelJoggingLayout.createSequentialGroup()
                                .addComponent(jComboBoxXYZRPY, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelJogMinus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelJogPlus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelJoggingLayout.createSequentialGroup()
                                .addComponent(jButtonRecordPoint)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelHoldingObject))
                            .addGroup(jPanelJoggingLayout.createSequentialGroup()
                                .addComponent(jButtonOpenGripper)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCloseGripper))))
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
                .addContainerGap(146, Short.MAX_VALUE))
        );
        jPanelJoggingLayout.setVerticalGroup(
            jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJoggingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanelJogMinus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1)
                        .addComponent(jPanelJogPlus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonRecordPoint)
                        .addComponent(jLabelHoldingObject)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanelJogMinus1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBoxXYZRPY)
                        .addComponent(jPanelJogPlus1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonOpenGripper)
                        .addComponent(jButtonCloseGripper)))
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
                .addContainerGap(261, Short.MAX_VALUE))
        );

        jTabbedPaneLeftUpper.addTab("Jog", jPanelJogging);

        jPanelMoveTo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanelMoveTo.setName("MoveTo"); // NOI18N

        jButtonMoveTo.setText("MoveTo");
        jButtonMoveTo.setEnabled(false);
        jButtonMoveTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMoveToActionPerformed(evt);
            }
        });

        jTableMoveToPose.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"X",  new Double(0.0)},
                {"Y",  new Double(0.0)},
                {"Z",  new Double(0.0)},
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
        jButtonMoveToCurrent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMoveToCurrentActionPerformed(evt);
            }
        });

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

        jTabbedPaneLeftUpper.addTab("MoveTo", jPanelMoveTo);
        jTabbedPaneLeftUpper.addTab("Transform Setup", transformJPanel1);

        jPanelConnectionTab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextFieldPort.setText("64444");

        jButtonConnect.setText("Connect");
        jButtonConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConnectActionPerformed(evt);
            }
        });

        jButtonDisconnect.setText("Disconnect ");
        jButtonDisconnect.setEnabled(false);
        jButtonDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDisconnectActionPerformed(evt);
            }
        });

        jButtonEnd.setText("End");
        jButtonEnd.setEnabled(false);
        jButtonEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEndActionPerformed(evt);
            }
        });

        jButtonInit.setText("Init");
        jButtonInit.setEnabled(false);
        jButtonInit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInitActionPerformed(evt);
            }
        });

        jLabel1.setText("Host: ");

        jTextFieldHost.setText("localhost");

        jLabel2.setText("Port:");

        jCheckBoxPoll.setSelected(true);
        jCheckBoxPoll.setText("Poll");
        jCheckBoxPoll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPollActionPerformed(evt);
            }
        });

        jTextFieldPollTime.setText("50");
        jTextFieldPollTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPollTimeActionPerformed(evt);
            }
        });

        jLabel4.setText("Poll interval(ms):");

        javax.swing.GroupLayout jPanelConnectionTabLayout = new javax.swing.GroupLayout(jPanelConnectionTab);
        jPanelConnectionTab.setLayout(jPanelConnectionTabLayout);
        jPanelConnectionTabLayout.setHorizontalGroup(
            jPanelConnectionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConnectionTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConnectionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelConnectionTabLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldHost, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelConnectionTabLayout.createSequentialGroup()
                        .addComponent(jButtonConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDisconnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonInit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEnd))
                    .addGroup(jPanelConnectionTabLayout.createSequentialGroup()
                        .addComponent(jCheckBoxPoll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(1, 1, 1)
                        .addComponent(jTextFieldPollTime, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelConnectionTabLayout.setVerticalGroup(
            jPanelConnectionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConnectionTabLayout.createSequentialGroup()
                .addGroup(jPanelConnectionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelConnectionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConnect)
                    .addComponent(jButtonDisconnect)
                    .addComponent(jButtonInit)
                    .addComponent(jButtonEnd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelConnectionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxPoll)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldPollTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPaneLeftUpper.addTab("Connection", jPanelConnectionTab);

        jPanelStatus.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanelStatus.setName("Status"); // NOI18N

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
                {"Zk", null}
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

        jTextAreaStateDescription.setColumns(20);
        jTextAreaStateDescription.setFont(new java.awt.Font("Monospaced", 0, 10)); // NOI18N
        jTextAreaStateDescription.setRows(2);
        jScrollPane6.setViewportView(jTextAreaStateDescription);

        jComboBoxPoseDisplayMode.setModel(new javax.swing.DefaultComboBoxModel(PoseDisplayMode.values()));
        jComboBoxPoseDisplayMode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxPoseDisplayModeItemStateChanged(evt);
            }
        });
        jComboBoxPoseDisplayMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPoseDisplayModeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelStatusLayout = new javax.swing.GroupLayout(jPanelStatus);
        jPanelStatus.setLayout(jPanelStatusLayout);
        jPanelStatusLayout.setHorizontalGroup(
            jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStatusLayout.createSequentialGroup()
                .addGroup(jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelStatusLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane6)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(jPanelStatusLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelStatusLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanelStatusLayout.createSequentialGroup()
                                .addGroup(jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldStatusID)
                                    .addComponent(jTextFieldStatus)))
                            .addGroup(jPanelStatusLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldStatCmdID, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))
                    .addGroup(jPanelStatusLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBoxPoseDisplayMode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelStatusLayout.setVerticalGroup(
            jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldStatCmdID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldStatusID, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxPoseDisplayMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPaneRightUpper.addTab("Status", jPanelStatus);

        jPanelProgramPlot.setName("ProgramPlot"); // NOI18N

        jPanelOverheadProgramPlot.setBorder(javax.swing.BorderFactory.createTitledBorder("Overhead"));

        javax.swing.GroupLayout programPlotterJPanelOverheadLayout = new javax.swing.GroupLayout(programPlotterJPanelOverhead);
        programPlotterJPanelOverhead.setLayout(programPlotterJPanelOverheadLayout);
        programPlotterJPanelOverheadLayout.setHorizontalGroup(
            programPlotterJPanelOverheadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 124, Short.MAX_VALUE)
        );
        programPlotterJPanelOverheadLayout.setVerticalGroup(
            programPlotterJPanelOverheadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 231, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelOverheadProgramPlotLayout = new javax.swing.GroupLayout(jPanelOverheadProgramPlot);
        jPanelOverheadProgramPlot.setLayout(jPanelOverheadProgramPlotLayout);
        jPanelOverheadProgramPlotLayout.setHorizontalGroup(
            jPanelOverheadProgramPlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOverheadProgramPlotLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(programPlotterJPanelOverhead, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelOverheadProgramPlotLayout.setVerticalGroup(
            jPanelOverheadProgramPlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOverheadProgramPlotLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(programPlotterJPanelOverhead, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelOverheadProgramPlot1.setBorder(javax.swing.BorderFactory.createTitledBorder("Side"));

        javax.swing.GroupLayout programPlotterJPanelSideLayout = new javax.swing.GroupLayout(programPlotterJPanelSide);
        programPlotterJPanelSide.setLayout(programPlotterJPanelSideLayout);
        programPlotterJPanelSideLayout.setHorizontalGroup(
            programPlotterJPanelSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        programPlotterJPanelSideLayout.setVerticalGroup(
            programPlotterJPanelSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelOverheadProgramPlot1Layout = new javax.swing.GroupLayout(jPanelOverheadProgramPlot1);
        jPanelOverheadProgramPlot1.setLayout(jPanelOverheadProgramPlot1Layout);
        jPanelOverheadProgramPlot1Layout.setHorizontalGroup(
            jPanelOverheadProgramPlot1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOverheadProgramPlot1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(programPlotterJPanelSide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelOverheadProgramPlot1Layout.setVerticalGroup(
            jPanelOverheadProgramPlot1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOverheadProgramPlot1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(programPlotterJPanelSide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelProgramPlotLayout = new javax.swing.GroupLayout(jPanelProgramPlot);
        jPanelProgramPlot.setLayout(jPanelProgramPlotLayout);
        jPanelProgramPlotLayout.setHorizontalGroup(
            jPanelProgramPlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProgramPlotLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelProgramPlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelOverheadProgramPlot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelOverheadProgramPlot1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelProgramPlotLayout.setVerticalGroup(
            jPanelProgramPlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProgramPlotLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelOverheadProgramPlot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelOverheadProgramPlot1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneRightUpper.addTab("Program Plot", jPanelProgramPlot);

        jTextAreaErrors.setColumns(20);
        jTextAreaErrors.setLineWrap(true);
        jTextAreaErrors.setRows(3);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPaneLeftUpper)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPaneRightUpper)
                        .addGap(11, 11, 11))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPaneRightUpper)
                    .addComponent(jTabbedPaneLeftUpper))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonProgramPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProgramPauseActionPerformed
        pauseCrclProgram();
    }//GEN-LAST:event_jButtonProgramPauseActionPerformed

    public void abortProgram() {
        pauseTime = System.currentTimeMillis();
        internal.abort();
    }

    private void jButtonProgramAbortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProgramAbortActionPerformed
        this.abortProgram();
    }//GEN-LAST:event_jButtonProgramAbortActionPerformed

    private void jButtonEditProgramItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditProgramItemActionPerformed
        int index = getProgramRow();
        if (index > 0 && index < this.jTableProgram.getRowCount() - 1) {
            try {
                MiddleCommandType cmdOrig = internal.getProgram().getMiddleCommand().get(index - 1);
                MiddleCommandType cmdEdited
                        = (MiddleCommandType) ObjTableJPanel.editObject(cmdOrig,
                                internal.getXpu(),
                                internal.getCmdSchemaFiles(),
                                PendantClientJPanel.this.internal.getCheckCommandValidPredicate());
                if (null == cmdEdited) {
                    showDebugMessage("Edit Program Item cancelled. cmdEdited == null");
                    return;
                }
                internal.getProgram().getMiddleCommand().set(index - 1, cmdEdited);
                this.showProgram(internal.getProgram());
                this.showCurrentProgramLine(index, internal.getProgram(), internal.getStatus());
            } catch (JAXBException ex) {
                Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonEditProgramItemActionPerformed


    private void jButtonDeletProgramItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeletProgramItemActionPerformed
        int index = getProgramRow();
        if (index > 0 && index < this.jTableProgram.getRowCount() - 1) {
            try {
                internal.getProgram().getMiddleCommand().remove(index - 1);
                this.showProgram(internal.getProgram());
                this.showCurrentProgramLine(index, internal.getProgram(), internal.getStatus());
            } catch (JAXBException ex) {
                Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonDeletProgramItemActionPerformed

    private void jButtonAddProgramItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddProgramItemActionPerformed
        int index = getProgramRow();
        if (index > 0 && index < this.jTableProgram.getRowCount() - 1) {
            try {
                Class clss = MiddleCommandType.class;
                List<Class> availClasses = getAssignableClasses(clss,
                        ObjTableJPanel.getClasses());
                Class ca[] = availClasses.toArray(new Class[availClasses.size()]);
                Class selectedClss = ListChooserJPanel.choose(this.getOuterWindow(),
                        "Type of new Item",
                        ca, null);
                if (selectedClss == null) {
                    showDebugMessage("Add Program Item cancelled. selectedClss == null");
                    return;
                }
                MiddleCommandType cmdOrig = (MiddleCommandType) selectedClss.newInstance();
                MiddleCommandType cmdEdited
                        = (MiddleCommandType) ObjTableJPanel.editObject(cmdOrig,
                                internal.getXpu(),
                                internal.getCmdSchemaFiles(),
                                PendantClientJPanel.this.internal.getCheckCommandValidPredicate());
                if (null == cmdEdited) {
                    showDebugMessage("Add Program Item cancelled. cmdEdited == null");
                    return;
                }
                internal.getProgram().getMiddleCommand().add(index - 1, cmdEdited);
                this.showProgram(internal.getProgram());
                this.showCurrentProgramLine(index, internal.getProgram(), internal.getStatus());
            } catch (InstantiationException | IllegalAccessException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
                showMessage(ex);
            } catch (JAXBException ex) {
                Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonAddProgramItemActionPerformed

    private void jButtonProgramRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProgramRunActionPerformed

        runCurrentProgram();
    }//GEN-LAST:event_jButtonProgramRunActionPerformed

    private XFuture<Boolean> lastProgramFuture = null;

    public XFuture<Boolean> runCurrentProgram() {
        try {
            if (!isConnected()) {
                connectCurrent();
            }
            this.clearProgramTimesDistances();

            int new_poll_ms = Integer.parseInt(this.jTextFieldPollTime.getText());
            internal.setQuitOnTestCommandFailure(true);
            internal.setPoll_ms(new_poll_ms);
            internal.setWaitForDoneDelay(new_poll_ms);
            internal.setStepMode(false);
            XFuture<Boolean> future = internal.startRunProgramThread(0);
            this.jButtonResume.setEnabled(internal.isPaused());
            this.jButtonProgramPause.setEnabled(internal.isRunningProgram());
            jogWorldSpeedsSet = false;
            lastProgramFuture = future;
            return future;
        } catch (Exception ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            XFuture<Boolean> future = new XFuture<>();
            future.completeExceptionally(ex);
            lastProgramFuture = null;
            return future;
        }
    }

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

    private void jButtonPlotProgramItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlotProgramItemActionPerformed
//        final int index = getProgramRow();
//        if (index > 0 && index < this.jTableProgram.getRowCount() - 1) {
//            MiddleCommandType cmdOrig = internal.getProgram().getMiddleCommand().get(index - 1);
//            BigInteger id = cmdOrig.getCommandID();
//            final List<AnnotatedPose> l
//                    = this.internal
//                    .getPoseList()
//                    .stream()
//                    .filter(x -> x.getLastCommandIdSent().compareTo(id) == 0)
//                    .collect(Collectors.toList());
//            com.github.wshackle.poselist3dplot.MainJFrame
//                    .showPoseList(l);
//        }
    }//GEN-LAST:event_jButtonPlotProgramItemActionPerformed

    private void jButtonRunProgFromCurrentLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRunProgFromCurrentLineActionPerformed
        continueCurrentProgram();
    }//GEN-LAST:event_jButtonRunProgFromCurrentLineActionPerformed

    public boolean isPaused() {
        return internal.isPaused();
    }

    public boolean isRunningProgram() {
        return internal.isRunningProgram();
    }

    public XFuture<Boolean> continueCurrentProgram() {
        if (!isConnected()) {
            connectCurrent();
        }
        if (null == internal.getProgram()
                || getCurrentProgramLine() < 0
                || getCurrentProgramLine() > (internal.getProgram().getMiddleCommand().size() + 1)) {
            return XFuture.completedFuture(internal.getStatus().getCommandStatus().getCommandState() != CommandStateEnumType.CRCL_ERROR);
        }
        if (pauseTime > this.internal.runStartMillis) {
            this.internal.runStartMillis += (System.currentTimeMillis() - pauseTime);
        }
        pauseTime = -1;
        if (this.getCurrentProgramLine() < 1) {
            this.internal.runStartMillis = System.currentTimeMillis();
        }
        this.internal.setStepMode(false);
        if (internal.isPaused()) {
            internal.unpause();
        }
        InitCanonType init = new InitCanonType();
        init.setCommandID(BigInteger.ONE);
        return XFuture.runAsync(() -> {
            try {
                internal.testCommand(init);
            } catch (JAXBException | InterruptedException | IOException | PmException | CRCLException ex) {
                Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        })
                .thenCompose(x -> internal.startRunProgramThread(this.getCurrentProgramLine()));
    }

    private void jButtonStepBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStepBackActionPerformed
        internal.setStepMode(true);
        internal.abort();
        int l = this.getCurrentProgramLine();
        if (l > 0) {
            l--;
        }
        internal.startRunProgramThread(l);
    }//GEN-LAST:event_jButtonStepBackActionPerformed

    private void jButtonStepFwdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStepFwdActionPerformed
        internal.setStepMode(true);
        if (internal.isPaused() && internal.isRunningProgram()) {
            internal.unpause();
        } else {
            internal.startRunProgramThread(this.getCurrentProgramLine());
        }
    }//GEN-LAST:event_jButtonStepFwdActionPerformed

    private void jLabelJogMinusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogMinusMouseExited
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJogMinusMouseExited

    private void jLabelJogMinusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogMinusMousePressed
        this.jogJointStart(-1.0 * internal.getJogIncrement());
        this.jLabelJogMinus.setBackground(Color.BLACK);
        this.jLabelJogMinus.setForeground(Color.WHITE);
        this.jPanelJogMinus.setBackground(Color.BLACK);
        this.jLabelJogMinus.repaint();
        this.jPanelJogMinus.repaint();
    }//GEN-LAST:event_jLabelJogMinusMousePressed

    private void jLabelJogMinusMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogMinusMouseReleased
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJogMinusMouseReleased

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

    private void jLabelJogPlusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogPlusMouseExited
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJogPlusMouseExited

    private void jLabelJogPlusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogPlusMousePressed
        this.jogJointStart(+1.0 * internal.getJogIncrement());
        this.jLabelJogPlus.setBackground(Color.BLACK);
        this.jLabelJogPlus.setForeground(Color.WHITE);
        this.jPanelJogPlus.setBackground(Color.BLACK);
        this.jLabelJogPlus.repaint();
        this.jPanelJogPlus.repaint();
    }//GEN-LAST:event_jLabelJogPlusMousePressed

    private void jLabelJogPlusMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogPlusMouseReleased
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJogPlusMouseReleased

    private void jLabelJogPlus1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogPlus1MouseExited
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJogPlus1MouseExited

    private void jLabelJogPlus1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogPlus1MousePressed
        this.jogWorldStart(+1.0);
        this.jLabelJogPlus1.setBackground(Color.BLACK);
        this.jLabelJogPlus1.setForeground(Color.WHITE);
        this.jPanelJogPlus1.setBackground(Color.BLACK);
        this.jLabelJogPlus1.repaint();
        this.jPanelJogPlus1.repaint();
    }//GEN-LAST:event_jLabelJogPlus1MousePressed

    private void jLabelJogPlus1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogPlus1MouseReleased
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJogPlus1MouseReleased

    private void jLabelJogMinus1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogMinus1MousePressed
        this.jogWorldStart(-1.0);
        this.jLabelJogMinus1.setBackground(Color.BLACK);
        this.jLabelJogMinus1.setForeground(Color.WHITE);
        this.jPanelJogMinus1.setBackground(Color.BLACK);
        this.jLabelJogMinus1.repaint();
        this.jPanelJogMinus1.repaint();
    }//GEN-LAST:event_jLabelJogMinus1MousePressed

    private void jLabelJogMinus1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogMinus1MouseReleased
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJogMinus1MouseReleased

    private void jLabelJogMinus1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogMinus1MouseExited
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJogMinus1MouseExited

    private void jPanelJogMinus1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelJogMinus1MousePressed
        this.jogWorldStart(-1.0);
        this.jLabelJogMinus1.setBackground(Color.BLACK);
        this.jLabelJogMinus1.setForeground(Color.WHITE);
        this.jPanelJogMinus1.setBackground(Color.BLACK);
        this.jLabelJogMinus1.repaint();
        this.jPanelJogMinus1.repaint();
    }//GEN-LAST:event_jPanelJogMinus1MousePressed

    private void jTextFieldJointJogIncrementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldJointJogIncrementActionPerformed
        internal.setJogIncrement(Double.parseDouble(this.jTextFieldJointJogIncrement.getText()));
    }//GEN-LAST:event_jTextFieldJointJogIncrementActionPerformed

    private void jTextFieldXYZJogIncrementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldXYZJogIncrementActionPerformed
        internal.setXyzJogIncrement(Double.parseDouble(this.jTextFieldXYZJogIncrement.getText()));
    }//GEN-LAST:event_jTextFieldXYZJogIncrementActionPerformed

    private void jTextFieldRPYJogIncrementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRPYJogIncrementActionPerformed
        this.setRpyJogIncrement(Double.parseDouble(this.jTextFieldRPYJogIncrement.getText()));
    }//GEN-LAST:event_jTextFieldRPYJogIncrementActionPerformed

    private void jTextFieldJogIntervalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldJogIntervalActionPerformed
        internal.setJogInterval(Integer.parseInt(this.jTextFieldJogInterval.getText()));
    }//GEN-LAST:event_jTextFieldJogIntervalActionPerformed

    private void lengthUnitComboBoxLengthUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lengthUnitComboBoxLengthUnitActionPerformed
        try {
            SetLengthUnitsType setLengthUnitsCmd = new SetLengthUnitsType();
            setLengthUnitsCmd.setUnitName(this.lengthUnitComboBoxLengthUnit.getSelectedItem());
            this.updateLengthUnit(setLengthUnitsCmd.getUnitName());
            internal.incAndSendCommand(setLengthUnitsCmd);
        } catch (JAXBException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lengthUnitComboBoxLengthUnitActionPerformed

    private void jTextFieldJointJogSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldJointJogSpeedActionPerformed
        internal.setJogJointSpeed(Double.parseDouble(this.jTextFieldJointJogSpeed.getText()));
    }//GEN-LAST:event_jTextFieldJointJogSpeedActionPerformed

    private void jTextFieldTransSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTransSpeedActionPerformed
        internal.setJogTransSpeed(Double.parseDouble(this.jTextFieldTransSpeed.getText()));
        jogWorldSpeedsSet = false;
    }//GEN-LAST:event_jTextFieldTransSpeedActionPerformed

    private void jTextFieldRotationSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRotationSpeedActionPerformed
        internal.setJogRotSpeed(Double.parseDouble(this.jTextFieldRotationSpeed.getText()));
        jogWorldSpeedsSet = false;
    }//GEN-LAST:event_jTextFieldRotationSpeedActionPerformed

    private void jButtonRecordPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecordPointActionPerformed
        this.recordCurrentPoint();
    }//GEN-LAST:event_jButtonRecordPointActionPerformed

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
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonOpenGripperActionPerformed

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
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonCloseGripperActionPerformed

    private void jButtonMoveToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMoveToActionPerformed
        try {
            MoveToType moveto = new MoveToType();
            PoseType p = tableToPose(this.jTableMoveToPose);
            moveto.setEndPosition(p);
            moveto.setMoveStraight(this.jCheckBoxStraight.isSelected());
            internal.incAndSendCommand(moveto);
        } catch (JAXBException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonMoveToActionPerformed

    public PoseDisplayMode getCurrentPoseDisplayMode() {
        return (PoseDisplayMode) jComboBoxPoseDisplayMode.getSelectedItem();
    }

    private void jButtonMoveToCurrentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMoveToCurrentActionPerformed
        this.updatePoseTable(internal.getPose(), this.jTableMoveToPose, getCurrentPoseDisplayMode());
    }//GEN-LAST:event_jButtonMoveToCurrentActionPerformed

    private void jButtonConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConnectActionPerformed
        connectCurrent();
    }//GEN-LAST:event_jButtonConnectActionPerformed

    public void connectCurrent() throws NumberFormatException {
        if (!isConnected()) {
            int port = Integer.parseInt(this.jTextFieldPort.getText());
            this.connect(this.jTextFieldHost.getText(),
                    port);
        }
    }

    private void jButtonDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDisconnectActionPerformed
        this.disconnect();
    }//GEN-LAST:event_jButtonDisconnectActionPerformed

    private void jButtonEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEndActionPerformed
        try {
            EndCanonType end = new EndCanonType();
            internal.incAndSendCommand(end);
        } catch (JAXBException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonEndActionPerformed

    private void jButtonInitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInitActionPerformed
        try {
            InitCanonType init = new InitCanonType();
            internal.incAndSendCommand(init);
        } catch (JAXBException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonInitActionPerformed

    private void jCheckBoxPollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPollActionPerformed
        this.stopPollTimer();
        if (this.jCheckBoxPoll.isSelected() && null != internal.getCRCLSocket()) {
            this.startPollTimer();
        }
    }//GEN-LAST:event_jCheckBoxPollActionPerformed

    private void jTextFieldPollTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPollTimeActionPerformed
        int new_poll_ms = Integer.parseInt(this.jTextFieldPollTime.getText());
        internal.setPoll_ms(new_poll_ms);
        this.stopPollTimer();
        if (this.jCheckBoxPoll.isSelected()) {
            this.startPollTimer();
        }
    }//GEN-LAST:event_jTextFieldPollTimeActionPerformed

    private void jTabbedPaneLeftUpperStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneLeftUpperStateChanged
        jogWorldSpeedsSet = false;
    }//GEN-LAST:event_jTabbedPaneLeftUpperStateChanged

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

    private void setPoseDisplayModelXAxisZAxis() {
        jTablePose.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"X", null},
                    {"Y", null},
                    {"Z", null},
                    {"XI", null},
                    {"XJ", null},
                    {"XK", null},
                    {"ZI", null},
                    {"ZJ", null},
                    {"Zk", null}
                },
                new String[]{
                    "Pose Axis", "Position"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    private boolean disableTextPopups = true;

    /**
     * Get the value of disableTextPopups
     *
     * @return the value of disableTextPopups
     */
    public boolean isDisableTextPopups() {
        return disableTextPopups;
    }

    /**
     * Set the value of disableTextPopups
     *
     * @param disableTextPopups new value of disableTextPopups
     */
    public void setDisableTextPopups(boolean disableTextPopups) {
        this.disableTextPopups = disableTextPopups;
    }

    private void setPoseDisplayModelRpy() {
        jTablePose.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"X", null},
                    {"Y", null},
                    {"Z", null},
                    {"Roll", null},
                    {"Pitch", null},
                    {"Yaw", null}
                },
                new String[]{
                    "Pose Axis", "Position"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    private void setPoseDisplayModelRxRyRz() {
        jTablePose.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"X", null},
                    {"Y", null},
                    {"Z", null},
                    {"Rx", null},
                    {"Ry", null},
                    {"Rz", null}
                },
                new String[]{
                    "Pose Axis", "Position"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    private void jComboBoxPoseDisplayModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPoseDisplayModeActionPerformed
        updateDisplayMode();
    }//GEN-LAST:event_jComboBoxPoseDisplayModeActionPerformed

    private void jComboBoxPoseDisplayModeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxPoseDisplayModeItemStateChanged
        updateDisplayMode();
    }//GEN-LAST:event_jComboBoxPoseDisplayModeItemStateChanged

    private void updateDisplayMode() {
        PoseDisplayMode displayMode = getCurrentPoseDisplayMode();
        switch (displayMode) {
            case XYZ_XAXIS_ZAXIS:
                setPoseDisplayModelXAxisZAxis();
                break;

            case XYZ_RPY:
                setPoseDisplayModelRpy();
                break;

            case XYZ_RX_RY_RZ:
                setPoseDisplayModelRxRyRz();
                break;
        }
        updatePoseTable(internal.getPose(), jTablePose, displayMode);
    }


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
    private javax.swing.JCheckBox jCheckBoxMonitorHoldingOutput;
    private javax.swing.JCheckBox jCheckBoxPoll;
    private javax.swing.JCheckBox jCheckBoxStraight;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox<PoseDisplayMode> jComboBoxPoseDisplayMode;
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
    private javax.swing.JLabel jLabelExpectHoldingObject;
    private javax.swing.JLabel jLabelHoldingObject;
    private javax.swing.JLabel jLabelHoldingObject2;
    private javax.swing.JLabel jLabelJogMinus;
    private javax.swing.JLabel jLabelJogMinus1;
    private javax.swing.JLabel jLabelJogPlus;
    private javax.swing.JLabel jLabelJogPlus1;
    private javax.swing.JPanel jPanelConnectionTab;
    private javax.swing.JPanel jPanelJogMinus;
    private javax.swing.JPanel jPanelJogMinus1;
    private javax.swing.JPanel jPanelJogPlus;
    private javax.swing.JPanel jPanelJogPlus1;
    private javax.swing.JPanel jPanelJogging;
    private javax.swing.JPanel jPanelMoveTo;
    private javax.swing.JPanel jPanelOverheadProgramPlot;
    private javax.swing.JPanel jPanelOverheadProgramPlot1;
    private javax.swing.JPanel jPanelProgram;
    private javax.swing.JPanel jPanelProgramPlot;
    private javax.swing.JPanel jPanelStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPaneProgram;
    private javax.swing.JTabbedPane jTabbedPaneLeftUpper;
    private javax.swing.JTabbedPane jTabbedPaneRightUpper;
    private javax.swing.JTable jTableJoints;
    private javax.swing.JTable jTableMoveToPose;
    private javax.swing.JTable jTablePose;
    private javax.swing.JTable jTableProgram;
    private javax.swing.JTextArea jTextAreaErrors;
    private javax.swing.JTextArea jTextAreaSelectedProgramCommand;
    private javax.swing.JTextArea jTextAreaStateDescription;
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
    private javax.swing.JTextField jTextFieldTransSpeed;
    private javax.swing.JTextField jTextFieldXYZJogIncrement;
    private crcl.ui.misc.LengthUnitComboBox lengthUnitComboBoxLengthUnit;
    private crcl.ui.misc.ProgramPlotterJPanel programPlotterJPanelOverhead;
    private crcl.ui.misc.ProgramPlotterJPanel programPlotterJPanelSide;
    private crcl.ui.misc.TransformJPanel transformJPanel1;
    // End of variables declaration//GEN-END:variables
}
