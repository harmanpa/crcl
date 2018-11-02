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

import crcl.utils.outer.interfaces.ProgramRunData;
import crcl.base.ActuateJointType;
import crcl.base.ActuateJointsType;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLProgramType;
import crcl.base.CRCLStatusType;
import crcl.base.CloseToolChangerType;
import crcl.base.CommandStateEnumType;
import static crcl.base.CommandStateEnumType.CRCL_ERROR;
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
import crcl.base.OpenToolChangerType;
import crcl.base.PointType;
import crcl.base.PoseType;
import crcl.base.RotSpeedAbsoluteType;
import crcl.base.SetEndEffectorType;
import crcl.base.SetLengthUnitsType;
import crcl.base.SetRotSpeedType;
import crcl.base.SetTransSpeedType;
import crcl.base.StopConditionEnumType;
import crcl.base.StopMotionType;
import crcl.base.TransSpeedAbsoluteType;
import crcl.base.VectorType;
import crcl.ui.ConcurrentBlockProgramsException;
import crcl.ui.DefaultSchemaFiles;
import static crcl.ui.IconImages.BASE_IMAGE;
import crcl.ui.misc.ListChooserJPanel;
import crcl.ui.misc.MultiLineStringJPanel;
import crcl.ui.misc.ObjTableJPanel;
import crcl.ui.misc.XpathQueryJFrame;
import static crcl.ui.IconImages.DISCONNECTED_IMAGE;
import static crcl.ui.IconImages.DONE_IMAGE;
import static crcl.ui.IconImages.ERROR_IMAGE;
import static crcl.ui.IconImages.WORKING_IMAGE;
import crcl.ui.XFuture;
import static crcl.ui.misc.ObjTableJPanel.getAssignableClasses;
import crcl.utils.CRCLException;
import crcl.utils.CRCLPosemath;
import crcl.utils.CRCLSocket;
import crcl.utils.outer.interfaces.PendantClientMenuOuter;
import crcl.utils.outer.interfaces.PendantClientOuter;
import crcl.utils.ProgramPlotter;
import crcl.utils.outer.interfaces.CommandStatusLogElement;
import diagapplet.plotter.PlotData;
import diagapplet.plotter.plotterJFrame;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dialog;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import static java.awt.event.ActionEvent.ACTION_FIRST;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.Objects.requireNonNull;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.xml.sax.SAXException;
import rcs.posemath.PmCartesian;
import rcs.posemath.PmEulerZyx;
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

    private final PendantClientInner internal;
    private static final double RPY_JOG_INCREMENT_DEFAULT = 3.0;
    private double rpyJogIncrement = RPY_JOG_INCREMENT_DEFAULT;
    private long pauseTime = -1;
    private long unpauseTime = -1;

    private boolean jogWorldTransSpeedsSet = false;
    private boolean jogWorldRotSpeedsSet = false;
    private static final Logger LOGGER = Logger.getLogger(PendantClientJPanel.class.getName());
    @Nullable
    private XpathQueryJFrame xqJFrame = null;
    private diagapplet.plotter.@Nullable plotterJFrame xyzPlotter = null;
    private diagapplet.plotter.@Nullable plotterJFrame jointsPlotter = null;

    //    javax.swing.Timer pollTimer = null;
    @Nullable
    private volatile Thread pollingThread = null;
    private volatile boolean statusRequested = false;
    private long max_diff_readStatusEndTime_requestStatusStartTime = 0;
    private long maxPollStatusCycleTime = 0;
    private long cycles = 0;
    private LengthUnitEnumType lengthUnit = LengthUnitEnumType.MILLIMETER;
    @Nullable
    private CRCLProgramType recordPointsProgram = null;
    private static final String SETTINGSREF = "clientsettingsref";
    private static final String CRCLJAVA_USER_DIR = ".crcljava";
    private static final String recent_files_dir = ".crcl_pendant_client_recent_files";

    private PendantClientMenuOuter menuOuter;

    private boolean debugShowProgram = false;

    /**
     * Get the value of debugShowProgram
     *
     * @return the value of debugShowProgram
     */
    public boolean isDebugShowProgram() {
        return debugShowProgram;
    }

    /**
     * Set the value of debugShowProgram
     *
     * @param debugShowProgram new value of debugShowProgram
     */
    public void setDebugShowProgram(boolean debugShowProgram) {
        this.debugShowProgram = debugShowProgram;
    }

    @Override
    public boolean checkUserText(String text) throws InterruptedException, ExecutionException {
        return MultiLineStringJPanel.showText(text).get();
    }

    public void pauseCrclProgram() {
        pauseTime = System.currentTimeMillis();
        internal.pause();
        this.jButtonResume.setEnabled(internal.isPaused());
        this.jButtonProgramPause.setEnabled(internal.isRunningProgram());
        jogWorldTransSpeedsSet = false;
        jogWorldRotSpeedsSet = false;
    }

    public String pauseInfoString() {
        return internal.pauseInfoString();
    }

    public void unpauseCrclProgram() {
        String startPauseInfo = pauseInfoString();
        pauseTime = System.currentTimeMillis();
        internal.unpause();
        this.jButtonResume.setEnabled(internal.isPaused());
        this.jButtonProgramPause.setEnabled(internal.isRunningProgram());
        jogWorldTransSpeedsSet = false;
        jogWorldRotSpeedsSet = false;
        if (isPaused()) {
            System.err.println("startPauseInfo = " + startPauseInfo);
            String currentPauseInfo = pauseInfoString();
            System.err.println("currentPauseInfo=" + currentPauseInfo);
            throw new IllegalStateException("still paused after unpauseCrclProgram()");
        }
    }

    public void showJointsPlot() {
        jointsPlotter = new plotterJFrame();
        jointsPlotter.setTitle("JOINTS");
        jointsPlotter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jointsPlotter.setVisible(true);
    }

    /**
     * Get the value of minLimit
     *
     * @return the value of minLimit
     */
    public PmCartesian getMinLimit() {
        return internal.getMinLimit();
    }

    /**
     * Set the value of minLimit
     *
     * @param minLimit new value of minLimit
     */
    public void setMinLimit(PmCartesian minLimit) {
        internal.setMinLimit(minLimit);
    }

    /**
     * Get the value of maxLimit
     *
     * @return the value of maxLimit
     */
    public PmCartesian getMaxLimit() {
        return internal.getMaxLimit();
    }

    /**
     * Set the value of maxLimit
     *
     * @param maxLimit new value of maxLimit
     */
    public void setMaxLimit(PmCartesian maxLimit) {
        internal.setMaxLimit(maxLimit);
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
            DefaultSchemaFiles defaultSchemaFiles = DefaultSchemaFiles.instance();
            CRCLSocket.saveCmdSchemaFiles(defaultSchemaFiles.getCmdSchemasFile(), fa);
            internal.setStatSchema(fa);
            CRCLSocket.saveStatSchemaFiles(defaultSchemaFiles.getStatSchemasFile(), fa);
            internal.setProgramSchema(fa);
            CRCLSocket.saveProgramSchemaFiles(defaultSchemaFiles.getProgramSchemasFile(), fa);
        }
    }

    @Override
    public void showLastProgramLineExecTimeMillisDists(int row, ProgramRunData prd) {
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            showLastProgramLineExecTimeMillisDistsPrivate(row, prd);
        } else {
            javax.swing.SwingUtilities.invokeLater(() -> {
                showLastProgramLineExecTimeMillisDistsPrivate(row, prd);
            });
        }
    }

    private void showLastProgramLineExecTimeMillisDistsPrivate(int row, ProgramRunData prd) {
        long millis = prd.getTime();
        double dist = prd.getDist();
        boolean result = prd.isResult();
        if (row >= 0 && row < jTableProgram.getRowCount()) {
            jTableProgram.setValueAt(millis, row, 3);
            jTableProgram.setValueAt(dist, row, 4);
            jTableProgram.setValueAt(result, row, 5);
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

    private volatile int errorsLength = 0;

    private void showErrorsPopup(MouseEvent evt) {
        JPopupMenu errorsPop = new JPopupMenu();
        JMenuItem clearMi = new JMenuItem("Clear");
        clearMi.addActionListener(e -> {
            errorsLength = 0;
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
        if (internal.isRunningProgram()) {
            return internal.getCurrentProgramLine();
        }
        return jTableProgram.getSelectionModel().getMinSelectionIndex();
    }

    int programLineShowing = -1;

    public static interface ProgramLineListener {

        public void accept(PendantClientJPanel panel, int line, CRCLProgramType program, CRCLStatusType status);
    }

    public static interface CurrentPoseListener {

        public void handlePoseUpdate(PendantClientJPanel panel, CRCLStatusType stat, @Nullable CRCLCommandType cmd, boolean isHoldingObjectExpected, long statRecieveTime);
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

    @Nullable
    public CRCLStatusType getStatus() {
        return internal.getStatus();
    }

    private void finishShowCurrentProgramLine(final int line, final CRCLProgramType program, @Nullable CRCLStatusType status, List<ProgramRunData> progRunDataList, StackTraceElement ste[]) {

        if (programShowing == null) {
            showProgram(program, progRunDataList, line);
        } else if (program != programShowing) {
            if (programShowing.getMiddleCommand().size() != program.getMiddleCommand().size()
                    || programShowing.getInitCanon().getCommandID() != program.getInitCanon().getCommandID()
                    || programShowing.getEndCanon().getCommandID() != program.getEndCanon().getCommandID()) {
                showProgram(program, progRunDataList, line);
            }
        }
        try {
            logShowCurrentProgramLineInfo(line, program, status, progRunDataList, ste);
        } catch (IOException | JAXBException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (line >= this.jTableProgram.getRowCount() || line < 0) {
            return;
        }
        if (line != programLineShowing) {
            if (null != program) {
                if (getProgramRow() != line) {
                    jTableProgram.getSelectionModel().setSelectionInterval(line, line);
                }
                final int row = getProgramRow();
                if (row > 0 && row < jTableProgram.getRowCount() - 1) {
                    Object idObject = jTableProgram.getValueAt(row, 1);
                    if (idObject instanceof Long) {
                        long id = (Long) idObject;
                        if (program.getMiddleCommand().size() <= line - 1
                                || id != program.getMiddleCommand().get(line - 1).getCommandID()) {
                            showProgram(program, progRunDataList, line);
                        }
                    }
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
                showSelectedProgramLine(line, program);

            } else {
                showSelectedProgramCommand("No Program loaded.");
            }
        }
        if (null != status) {
            for (int i = 0; i < programLineListeners.size(); i++) {
                ProgramLineListener l = programLineListeners.get(i);
                l.accept(this, line, program, status);
            }
        }
        programLineShowing = line;
    }

    private volatile int lastShowSelectedProgramLineLine = -99;
    @Nullable
    private volatile CRCLProgramType lastshowSelectedProgramLineProgram = null;

    private void showSelectedProgramLine(final int line, final CRCLProgramType program) {
        if (null != lastshowSelectedProgramLineProgram
                && lastShowSelectedProgramLineLine == line
                && lastshowSelectedProgramLineProgram == program) {
            return;
        }
        lastShowSelectedProgramLineLine = line;
        lastshowSelectedProgramLineProgram = program;
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
        programPlotterJPanelOverhead.setProgram(program);
        programPlotterJPanelSide.setProgram(program);
        programPlotterJPanelOverhead.setIndex(line);
        programPlotterJPanelSide.setIndex(line);
        programPlotterJPanelOverhead.repaint();
        programPlotterJPanelSide.repaint();
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

    private void logShowCurrentProgramLineInfo(final int line, CRCLProgramType program, @Nullable CRCLStatusType status, @Nullable List<ProgramRunData> progRunDataList, StackTraceElement trace[]) throws IOException, JAXBException {
        if (!debugShowProgram) {
            return;
        }
        PrintStream ps = getShowProgramLogPrintStream();
        ps.println(" \ttrace=" + Arrays.toString(trace));
        int count = logShowProgramCount.incrementAndGet();

        String programString = CRCLSocket.getUtilSocket().programToPrettyDocString(program, false);
        File programFile = File.createTempFile("logShowCurrentProgramLineInfo_program_" + getPort() + "_" + count, ".xml");
        try (PrintStream psProgramFile = new PrintStream(new FileOutputStream(programFile))) {
            psProgramFile.println(programString);
        }

        File progRunDataListFile = File.createTempFile("showProgramLog_progRunDataList_" + getPort() + "_" + count, ".csv");
        if (null != progRunDataList) {
            saveProgramRunDataListToCsv(progRunDataListFile, progRunDataList);
        }
        long time = System.currentTimeMillis();
        ps.println("count=" + count + ",time=" + time + ",(time-showProgramLogStartTime)=" + (time - showProgramLogStartTime));
        String runDataListInfoString = "";
        if (null != progRunDataList) {
            runDataListInfoString = "\", programRunDataList=\"" + progRunDataListFile + "\" (size=" + progRunDataList.size() + "))";
        }
        if (null != status) {
            String statusString = CRCLSocket.getUtilSocket().statusToPrettyString(status, false);
            File statusFile = File.createTempFile("logShowCurrentProgramLineInfo_status_" + getPort() + "_" + count, ".xml");
            try (PrintStream psStatusFile = new PrintStream(new FileOutputStream(statusFile))) {
                psStatusFile.println(statusString);
            }
            ps.println("logShowCurrentProgramLineInfo(line=" + line + ",program=\"" + programFile + ",status=\"" + statusFile + runDataListInfoString);
        } else {
            ps.println("logShowCurrentProgramLineInfo(line=" + line + ",program=\"" + programFile + runDataListInfoString);
        }
        ps.flush();
    }

    @Override
    @SuppressWarnings("nullness")
    public void showCurrentProgramLine(final int line,
            CRCLProgramType program,
            @Nullable CRCLStatusType status,
            List<ProgramRunData> progRunDataList) {
        StackTraceElement ste[] = Thread.currentThread().getStackTrace();
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            finishShowCurrentProgramLine(line, CRCLPosemath.copy(program), status, progRunDataList, ste);
        } else {
            final CRCLStatusType curInternalStatus = (null == status) ? null : CRCLPosemath.copy(status);
            List<ProgramRunData> progRunDataListCopy = (null != progRunDataList) ? new ArrayList<>(progRunDataList) : Collections.emptyList();
            java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    finishShowCurrentProgramLine(line, CRCLPosemath.copy(program), curInternalStatus, progRunDataListCopy, ste);
                }
            });
        }
    }

    /**
     * Creates new form PendantClientJPanel
     *
     */
    @SuppressWarnings("initialization")
    public PendantClientJPanel(@Nullable Container outerContainer, @Nullable JFrame outerJFrame) {
        try {
            this.outerContainer = outerContainer;
            this.outerJFrame = outerJFrame;
            initComponents();
            this.internal = new PendantClientInner(this, DefaultSchemaFiles.instance());
            init();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    /**
     * Creates new form PendantClientJPanel
     *
     */
    @SuppressWarnings("initialization")
    public PendantClientJPanel() {
        this(null, null);
    }

    private void init() {
        String portPropertyString = System.getProperty("crcl4java.port");
        if (null != portPropertyString && portPropertyString.length() > 0) {
            int port = Integer.parseInt(portPropertyString);
            this.jTextFieldPort.setText("" + port);
        }
        String hostPropertyString = System.getProperty("crcl4java.host");
        if (null != hostPropertyString && hostPropertyString.length() > 0) {
            this.jTextFieldHost.setText(hostPropertyString);
        }
//        DefaultSchemaFiles defaultSchemaFiles = DefaultSchemaFiles.instance();
//        internal.setStatSchema(CRCLSocket.readStatSchemaFiles(defaultSchemaFiles.getStatSchemasFile()));
//        internal.setCmdSchema(CRCLSocket.readCmdSchemaFiles(defaultSchemaFiles.getCmdSchemasFile()));
//        internal.setProgramSchema(CRCLSocket.readProgramSchemaFiles(defaultSchemaFiles.getProgramSchemasFile()));
//        readRecentCommandFiles();
//        readRecentPrograms();
        final String programPropertyString = System.getProperty("crcl4java.program");
        if (null != programPropertyString) {
            final String nonNullProgramPropertyString = programPropertyString;
            java.awt.EventQueue.invokeLater(() -> {
                openXmlProgramFile(new File(nonNullProgramPropertyString));
            });
        }
        if (!(outerContainer instanceof PendantClientJInternalFrame)) {
            checkSettingsRef();
        }
        this.updateUIFromInternal();
//        this.jTableProgram.getSelectionModel().addListSelectionListener(e -> finishShowCurrentProgramLine(getProgramRow(), internal.getProgram(), internal.getStatus(), null));
        this.internal.addPropertyChangeListener(new PendantClientJPanel.MyPropertyChangeListener());
        this.jTableProgram.getSelectionModel().addListSelectionListener(programTableListSelectionListener);
        this.transformJPanel1.setPendantClient(this);
        this.jTextFieldStatus.setBackground(Color.GRAY);
        this.programPlotterJPanelOverhead.setPlotter(new ProgramPlotter(ProgramPlotter.View.OVERHEAD));
        this.programPlotterJPanelSide.setPlotter(new ProgramPlotter(ProgramPlotter.View.SIDE));
    }

    private ListSelectionListener programTableListSelectionListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
//            System.out.println("e = " + e);
            boolean adjusting = e.getValueIsAdjusting();
            int row = jTableProgram.getSelectedRow();
            if (!adjusting && null != programShowing && row >= 0) {
                showSelectedProgramLine(row, programShowing);
            }
            //finishShowCurrentProgramLine(getProgramRow(), internal.getProgram(), internal.getStatus(), internal.getProgRunDataList(),Thread.currentThread().getStackTrace());
        }
    };

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
        int chckPort = Integer.parseInt(this.jTextFieldPort.getText());
        CRCLSocket socket = internal.getCRCLSocket();
        if (null != socket) {
            int port1 = socket.getPort();
            if (port1 != chckPort) {
                System.err.println("PendantClientJPanel.getPort() : " + port1 + " != " + chckPort);
                this.jTextFieldPort.setText("" + port1);
            }
            return port1;
        }
        return chckPort;
    }

    public void setPort(int port) {
        jTextFieldPort.setText(Integer.toString(port));
    }

    private Optional<Object> safeInvokeMethod(Method m, Object o) {
        try {
            return Optional.ofNullable(m.invoke(o));

        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Throwable cause = ex.getCause();
            if (null != cause) {
                Logger.getLogger(PendantClientJPanel.class
                        .getName()).log(Level.SEVERE, "m=" + m + ",o=" + o, cause);
            }
            Logger.getLogger(PendantClientJPanel.class
                    .getName()).log(Level.SEVERE, "m=" + m + ",o=" + o, ex);
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

    public ExecutorService getRunProgramService() {
        return internal.getRunProgramService();
    }

    public void setRunProgramService(ExecutorService runProgramService) {
        internal.setRunProgramService(runProgramService);
    }

    public void resetRunProgramServiceToDefault() {
        internal.resetRunProgramServiceToDefault();
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
                boolean visibleAndValid = isVisible() && isValid();
                Stream.of(ma)
                        .filter(m -> Modifier.isPublic(m.getModifiers()))
                        .filter(m -> m.getName().startsWith("get"))
                        .filter(m -> !m.getName().startsWith("getTempLogDir"))
                        .filter(m -> !m.getName().contains("Mouse"))
                        .filter(m -> !m.getName().startsWith("getPropertiesFile"))
                        .filter(m -> !m.getName().startsWith("getLocationOnScreen") || visibleAndValid)
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
                        .filter(m -> !m.getName().startsWith("getTempLogDir"))
                        .filter(m -> !m.getName().startsWith("getPropertiesFile"))
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

    @SuppressWarnings({"unchecked", "nullness"})
    @Nullable
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

        } else if (clss.isAssignableFrom(boolean.class
        )) {
            return (T) Boolean.valueOf(s);

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
        } else if (clss.isAssignableFrom(Boolean.class
        )) {
            return (T) Boolean.valueOf(s);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void setParam(String... args) {
        try {
            if (args.length < 3) {
                return;
            }

            Class<?> clss = null;

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
                String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
                try {
                    m = this.internal.getClass()
                            .getMethod(methodName,
                                    clss);
                } catch (NoSuchMethodException ex) {
                    // ignore and just return
                }
                if (null == m) {
                    return;
                }
                m.invoke(this.internal, o);
            } else {
                String methodName = "set" + args[1].substring(0, 1).toUpperCase() + args[1].substring(1);
                try {
                    m = this.getClass().getMethod(methodName,
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
    public static PoseType tableToPose(JTable table, PoseDisplayMode displayMode) throws PmException {

        TableModel tm = table.getModel();
        PmCartesian tran = new PmCartesian((Double) tm.getValueAt(0, 1), (Double) tm.getValueAt(1, 1), (Double) tm.getValueAt(2, 1));

        switch (displayMode) {
            case XYZ_XAXIS_ZAXIS:
                PoseType p = new PoseType();
                PointType pt = CRCLPosemath.toPointType(tran);
                VectorType xv = new VectorType();
                xv.setI((Double) tm.getValueAt(3, 1));
                xv.setJ((Double) tm.getValueAt(4, 1));
                xv.setK((Double) tm.getValueAt(5, 1));
                VectorType zv = new VectorType();
                zv.setI((Double) tm.getValueAt(6, 1));
                zv.setJ((Double) tm.getValueAt(7, 1));
                zv.setK((Double) tm.getValueAt(8, 1));
                p.setPoint(pt);
                p.setXAxis(xv);
                p.setZAxis(zv);
                return p;

            case XYZ_RPY:
                PmRpy rpy = new PmRpy(
                        Math.toRadians((Double) tm.getValueAt(3, 1)),
                        Math.toRadians((Double) tm.getValueAt(4, 1)),
                        Math.toRadians((Double) tm.getValueAt(5, 1)));
                return CRCLPosemath.toPoseType(tran, rpy);

            case XYZ_RX_RY_RZ:
                PmEulerZyx zyx = new PmEulerZyx(
                        Math.toRadians((Double) tm.getValueAt(3, 1)),
                        Math.toRadians((Double) tm.getValueAt(4, 1)),
                        Math.toRadians((Double) tm.getValueAt(5, 1)));
                return CRCLPosemath.toPoseType(tran, Posemath.toMat(zyx));

            default:
                throw new IllegalArgumentException("displayMode =" + displayMode);
        }
    }

    public int getProgramRow() {
        final int selectedRows[] = this.jTableProgram.getSelectedRows();
        return (null == selectedRows || selectedRows.length < 1) ? 0 : selectedRows[0];
    }

    public String getVersion() {
        try (
                InputStream versionIs
                = requireNonNull(ClassLoader.getSystemResourceAsStream("version"),
                        "ClassLoader.getSystemResourceAsStream(\"version\")");
                BufferedReader br = new BufferedReader(new InputStreamReader(versionIs))) {
            StringBuilder sb = new StringBuilder();
            String line = null;
            while (null != (line = br.readLine())) {
                sb.append(line);
            }
            sb.append("\nSchema versions = ").append(CRCLSocket.getSchemaVersions().toString());
            return sb.toString();
        } catch (Exception ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            String exString = ex.toString();
            if (exString.length() > 30) {
                exString = exString.substring(0, 30);
            }
            return exString;
        }
    }

    private static String createAssertErrorString(CRCLCommandType cmd, long id) {
        return "command id being reduced id=" + id + ", cmd=" + CRCLSocket.cmdToString(cmd);
    }

    private void setCommandId(CRCLCommandType cmd, long id) {
        assert cmd.getCommandID() <= id :
                createAssertErrorString(cmd, id);
        cmd.setCommandID(id);
    }

    public void recordPoint(PoseType pose) {
        try {
            CRCLProgramType program = this.recordPointsProgram;
            if (program == null) {
                program = new CRCLProgramType();
                this.recordPointsProgram = program;
                InitCanonType initCmd = new InitCanonType();
                setCommandId(initCmd, 1);
                program.setInitCanon(initCmd);
                EndCanonType endCmd = new EndCanonType();
                setCommandId(endCmd, 3);
                program.setEndCanon(endCmd);
            }
            if (null == program) {
                throw new IllegalStateException("recordPointsProgram==null");
            }
            MoveToType moveToCmd = new MoveToType();
            PoseType endPose = new PoseType();
            PointType posePoint = pose.getPoint();
            if (null != posePoint) {
                endPose.setPoint(posePoint);
            }
            VectorType poseXAxis = pose.getXAxis();
            if (null != poseXAxis) {
                endPose.setXAxis(poseXAxis);
            }
            VectorType poseZAxis = pose.getZAxis();
            if (null != poseZAxis) {
                endPose.setZAxis(poseZAxis);
            }
            moveToCmd.setEndPosition(endPose);
            moveToCmd.setMoveStraight(this.jCheckBoxStraight.isSelected());
            setCommandId(moveToCmd, program.getMiddleCommand().size() + 1);
            program.getMiddleCommand().add(moveToCmd);
            setCommandId(program.getEndCanon(), moveToCmd.getCommandID() + 1);
            internal.setProgram(program);
            showProgram(program, Collections.emptyList(), 0);
        } catch (Exception ex) {
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
        CRCLProgramType program = this.recordPointsProgram;
        if (null != program) {
            try {
                program.getMiddleCommand().clear();
                this.internal.setProgram(program);
                internal.getProgRunDataList().clear();
                this.showProgram(program, Collections.emptyList(), -1);
            } catch (Exception ex) {
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

    @Override
    public void clearProgramTimesDistances() {
        StackTraceElement ste[] = Thread.currentThread().getStackTrace();
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            clearProgramTimesDistancesInternal(ste);
        } else {
            javax.swing.SwingUtilities.invokeLater(
                    () -> clearProgramTimesDistancesInternal(ste));
        }
        programLineShowing = -1;
        jogWorldTransSpeedsSet = false;
        jogWorldRotSpeedsSet = false;
    }

    private void logClearProgramTimesDistancesInternalInfo(StackTraceElement trace[]) throws IOException, JAXBException {
        if (!debugShowProgram) {
            return;
        }
        PrintStream ps = getShowProgramLogPrintStream();
        int count = logShowProgramCount.incrementAndGet();
        long time = System.currentTimeMillis();
        ps.println(" \ttrace = " + Arrays.toString(trace));
        ps.println("count=" + count + ",time=" + time + ",(time-showProgramLogStartTime)=" + (time - showProgramLogStartTime));
        ps.println("clearProgramTimesDistancesInternal()");
        ps.flush();
    }

    private void clearProgramTimesDistancesInternal(StackTraceElement ste[]) {
        try {
            logClearProgramTimesDistancesInternalInfo(ste);
        } catch (IOException | JAXBException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < jTableProgram.getRowCount(); i++) {
            jTableProgram.setValueAt(-1L, i, 3);
            jTableProgram.setValueAt(0.0, i, 4);
            jTableProgram.setValueAt(false, i, 5);
        }
    }

    @Override
    public PoseType currentStatusPose() {
        final CRCLStatusType status = requireNonNull(internal.getStatus(), "internal.getStatus()");
        return requireNonNull(CRCLPosemath.getPose(status), "CRCLPosemath.getPose(status)");
    }

    public Optional<CRCLStatusType> currentStatus() {
        return Optional.ofNullable(internal)
                .map(x -> x.getStatus());
    }

    public Optional<CommandStateEnumType> currentState() {
        return currentStatus()
                .map(x -> x.getCommandStatus())
                .map(x -> x.getCommandState());
    }

    @SuppressWarnings("nullness")
    @Override
    public MiddleCommandType currentProgramCommand() {
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

    private final AtomicInteger pollStopCount = new AtomicInteger();

    public boolean checkPose(PoseType goalPose, boolean ignoreCartTran) {
        return internal.checkPose(goalPose, ignoreCartTran);
    }

    private void pollStatus(int startPollStopCount) {
//        final int startPollStopCount = pollStopCount.get();
        try {
            while (!Thread.currentThread().isInterrupted()
                    && this.jCheckBoxPoll.isSelected()
                    && internal.isConnected()
                    && startPollStopCount == pollStopCount.get()) {
                cycles++;
                long requestStatusStartTime = System.currentTimeMillis();
                synchronized (internal) {
                    internal.requestStatus();
                    statusRequested = true;
                    internal.readStatus();
                    statusRequested = false;
                }
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
        } catch (Exception ex) {
            if (!Thread.currentThread().isInterrupted()
                    && this.jCheckBoxPoll.isSelected()
                    && null != internal.getCRCLSocket()
                    && startPollStopCount == pollStopCount.get()) {
                Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        int startPollStopCount = pollStopCount.incrementAndGet();
        pollingThread = new Thread(() -> pollStatus(startPollStopCount), "PendantClient.pollStatus.socket=" + internal.getCRCLSocket());
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
        pollStopCount.incrementAndGet();
        Thread pt = pollingThread;
        if (null != pt) {
            try {
                pt.join(100 + internal.getPoll_ms());
            } catch (InterruptedException ex) {
            }
            if (pt.isAlive()) {
                if (this.internal.isDebugInterrupts()) {
                    Thread.dumpStack();
                    System.err.println("Interruptint pollingThread = " + pt);
                    System.out.println("Interruptint pollingThread = " + pt);
                    System.out.println("pollingThread.getStackTrace() = " + Arrays.toString(pt.getStackTrace()));
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
                pt.interrupt();
                try {
                    pt.join(1000);
                } catch (InterruptedException ex) {
                    if (internal.isConnected()) {
                        Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            pollingThread = null;
            if (statusRequested && !disconnecting) {
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
        if (isDisableTextPopups()) {
            crcl.ui.misc.MultiLineStringJPanel.disableShowText = true;
        }
    }

    public void openXmlProgramFile(File f) {
        try {
            this.clearProgramTimesDistances();
            this.clearRecordedPoints();
            internal.openXmlProgramFile(f, true);
        } catch (Exception ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            showMessage(ex);
        }
    }

    public void saveXmlProgramFile(File f) throws JAXBException, CRCLException {
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

    /**
     * Get the value of sideProgramPlotter
     *
     * @return the value of sideProgramPlotter
     */
    @Nullable
    public ProgramPlotter getSideProgramPlotter() {
        return programPlotterJPanelSide.getPlotter();
    }

    /**
     * Get the value of overheadProgramPlotter
     *
     * @return the value of overheadProgramPlotter
     */
    @Nullable
    public ProgramPlotter getOverheadProgramPlotter() {
        return programPlotterJPanelOverhead.getPlotter();
    }

    @Nullable
    public CRCLProgramType getProgram() {
        return internal.getProgram();
    }

    @Nullable
    private volatile CRCLProgramType prevSetProgramProgram = null;
    private volatile int prevSetProgramLength = -1;
    @Nullable
    private volatile CRCLProgramType showProgramCopy = null;

    public void setProgram(@Nullable CRCLProgramType program) {
//        CRCLProgramType newProgram = copyProgram(program);
        this.internal.setProgram(program);
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            prevSetProgramProgram = null;
            showProgramCopy = null;
            if (null != program) {
                this.showProgram(program, Collections.emptyList(), 0);
            }
            return;
        }

        if (null == program) {
            prevSetProgramProgram = null;
            showProgramCopy = null;
        } else if (showProgramCopy == null || prevSetProgramProgram != program || program.getMiddleCommand().size() != prevSetProgramLength) {
            showProgramCopy = CRCLPosemath.copy(program);
            prevSetProgramProgram = program;
            prevSetProgramLength = program.getMiddleCommand().size();
        }
        if (null != showProgramCopy) {
            CRCLProgramType programToShow = showProgramCopy;
            try {
                javax.swing.SwingUtilities.invokeLater(() -> this.showProgram(programToShow, Collections.emptyList(), 0));
            } catch (Exception ex) {
                Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void finishOpenXmlProgramFile(File f,
            CRCLProgramType program, boolean saveRecent) {
        try {
            this.recordPointsProgram = null;
            showProgram(program, Collections.emptyList(), 0);
            internal.setProgram(program);
            this.saveRecentProgram(f);
            this.jTabbedPaneLeftUpper.setSelectedComponent(this.jPanelProgram);
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
            return requireNonNull(br.readLine(), "br.readLine() : f=" + f);
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

    private boolean showing_message = false;
    private volatile long last_message_show_time = 0;

    @MonotonicNonNull
    private final Container outerContainer;
    @MonotonicNonNull
    private final JFrame outerJFrame;
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

    @Nullable
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

    @Nullable
    private Window searchForOuterWindow() {
        if (outerContainer instanceof Window) {
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
    @Nullable
    public Container getOuterContainer() {
        return outerContainer;
    }

    /**
     * Get the value of outerFrame
     *
     * @return the value of outerFrame
     */
    @Nullable
    public Window getOuterWindow() {
        if (outerContainer instanceof Window) {
            return (Window) outerContainer;
        }
        if (null != outerJFrame) {
            return outerJFrame;
        }
        if (null != outerContainer && outerContainer instanceof Window) {
            return (Window) outerContainer;
        }
        return searchForOuterWindow();
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
                    Window window = PendantClientJPanel.this.getOuterWindow();
                    if (null != window) {
                        MultiLineStringJPanel.showText(s,
                                window,
                                "Message from Client",
                                Dialog.ModalityType.APPLICATION_MODAL);
                    }
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
                int elen = PendantClientJPanel.this.errorsLength;
                if (elen > 5000 && (elen + sWithThread.length()) > 20000) {
                    String s = jTextAreaErrors.getText();
                    if (s.length() > 5000) {
                        int startIndex = s.indexOf('\n', s.length() - 5000);
                        if (startIndex < 1) {
                            startIndex = s.length() - 5000;
                        }
                        s = s.substring(startIndex);
                        elen = s.length();
                        jTextAreaErrors.setText(s);
                    }
                }
                jTextAreaErrors.append("\n" + sWithThread);
                elen += 1 + sWithThread.length();
                PendantClientJPanel.this.errorsLength = elen;
            }
        });
        return false;
    }

    @Override
    public void showMessage(Throwable t) {
        this.showMessage(t.toString());
    }

    double last_t_pos_logged = 0;

    final Map<Integer, Double> last_joints = new HashMap<>();

    private boolean jointsChanged(List<JointStatusType> jsl) {
        if (jsl.size() != last_joints.values().size()) {
            return true;
        }
        for (JointStatusType jst : jsl) {
            Double D = last_joints.get(jst.getJointNumber());
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
        XpathQueryJFrame xqJFrameLocal = this.xqJFrame;
        if (null != xqJFrameLocal && xqJFrameLocal.isUpdateAutomaticallySelected()) {
            String q = xqJFrameLocal.getQuery();
            if (q != null && q.length() > 0) {
                String lastStatusString = crclSocket.getLastStatusString();
                if (null != lastStatusString) {
                    xqJFrameLocal.runQuery(q, lastStatusString);
                }
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

    @Nullable
    private volatile CRCLProgramType lastFinishSetStatusProgramCopy = null;
    @Nullable
    private volatile CRCLProgramType lastFinishSetStatusInternalProgram = null;
    private volatile int lastFinishSetStatusInternalProgramLength = -1;

    @Nullable
    private CRCLProgramType getFinishSetStatusProgramCopy(CRCLProgramType internalProgram) {
        if (null == internalProgram) {
            lastFinishSetStatusProgramCopy = null;
            lastFinishSetStatusInternalProgram = null;
            lastFinishSetStatusInternalProgramLength = -1;
            return null;
        }
        if (lastFinishSetStatusInternalProgram == internalProgram && null != lastFinishSetStatusProgramCopy
                && lastFinishSetStatusInternalProgramLength == internalProgram.getMiddleCommand().size()) {
            return lastFinishSetStatusProgramCopy;
        } else {
            lastFinishSetStatusProgramCopy = CRCLPosemath.copy(internalProgram);
            lastFinishSetStatusInternalProgramLength = internalProgram.getMiddleCommand().size();
            return lastFinishSetStatusProgramCopy;
        }
    }

    @Override
    public void finishSetStatus() {
        long statRecieveTime = System.currentTimeMillis();
        final CRCLStatusType curInternalStatus
                = requireNonNull(internal.getStatus(), "internal.getStatus()");
        final boolean isHoldingObjectExpected = internal.isHoldingObjectExpected();
        final CRCLCommandType lastCmd = internal.getLastCommandSent();
        CRCLProgramType internalProgram = internal.getProgram();
        StackTraceElement ste[] = Thread.currentThread().getStackTrace();
        updateCurrentPoseListeners(curInternalStatus, lastCmd, isHoldingObjectExpected, statRecieveTime);

        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            lastFinishSetStatusProgramCopy = null;
            lastFinishSetStatusInternalProgram = null;
            finishSetStatusPriv(internalProgram, curInternalStatus, lastCmd, isHoldingObjectExpected, ste, statRecieveTime);
        } else {
            final CRCLStatusType curInternalStatusCopy
                    = requireNonNull(CRCLPosemath.copy(curInternalStatus), "CRCLPosemath.copy(curInternalStatus)");
            if (null == internalProgram) {
                lastFinishSetStatusProgramCopy = null;
                lastFinishSetStatusInternalProgram = null;
            }
            CRCLProgramType program = (null != internalProgram && isProgramCopyNeeded(curInternalStatusCopy))
                    ? getFinishSetStatusProgramCopy(internalProgram)
                    : null;
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    PendantClientJPanel.this.finishSetStatusPriv(program, curInternalStatusCopy, lastCmd, isHoldingObjectExpected, ste, statRecieveTime);
                }
            });
        }
    }

    private String lastStateDescription = "";
    @Nullable
    private String lastProgramFile = null;

    @Nullable
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
            if (null != f && f.exists() && f.getName().equals(filename)) {
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

    @Nullable
    public List<ProgramRunData> getLastProgRunDataList() {
        return internal.getLastProgRunDataList();
    }

    public void saveProgramRunDataListToCsv(File f, List<ProgramRunData> list) throws IOException {
        internal.saveProgramRunDataListToCsv(f, list);
    }

    public void saveLastProgramRunDataListToCsv(File f) throws IOException {
        internal.saveLastProgramRunDataListToCsv(f);
    }

    private boolean isProgramCopyNeeded(final CRCLStatusType curInternalStatus) {
        try {
            if (null == internal.getProgram()) {
                return false;
            }
            if (null != curInternalStatus && null != curInternalStatus.getCommandStatus()) {
                CommandStatusType ccst = curInternalStatus.getCommandStatus();
                if (null != ccst) {
                    if (!internal.isRunningProgram()) {
                        Integer ccstProgramIndex = ccst.getProgramIndex();
                        if (null != ccstProgramIndex) {
                            int index = ccstProgramIndex.intValue();
                            if (index != lastProgramIndex) {
                                return true;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Nullable
    private volatile CommandStateEnumType lastIconState = null;

    private void finishSetStatusPriv(
            @Nullable CRCLProgramType program,
            @Nullable CRCLStatusType curInternalStatus,
            @Nullable CRCLCommandType lastCommandSent,
            boolean isHoldingObject,
            StackTraceElement[] ste,
            long statReceiveTime) {
        if (needInitPoint && null != curInternalStatus) {
            PointType pt = CRCLPosemath.getPoint(curInternalStatus);
            if (null != pt) {
                pt = CRCLPosemath.copy(pt);
                setPlottersInitPoint(pt);
                needInitPoint = false;
            } else {
                setPlottersInitPoint(null);
//                programPlotterJPanelOverhead.getPlotter().setInitPoint(null);
//                programPlotterJPanelSide.getPlotter().setInitPoint(null);
                needInitPoint = true;
            }
        }
        if (null != curInternalStatus && null != curInternalStatus.getCommandStatus()) {
            CommandStatusType ccst = curInternalStatus.getCommandStatus();
            if (null != ccst) {
                this.jTextFieldStatCmdID.setText("" + ccst.getCommandID());
                String ccstStateDescription = ccst.getStateDescription();
                String stateDescription = "";
                if (null != ccstStateDescription) {
                    stateDescription = ccstStateDescription;
                }
                Container container = this.getOuterContainer();
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
                                if (null != outerJFrame) {
                                    outerJFrame.setIconImage(ERROR_IMAGE);
                                }
                                break;

                            case CRCL_WORKING:
                                this.jTextFieldStatus.setBackground(Color.GREEN);
                                if (null != outerJFrame) {
                                    outerJFrame.setIconImage(WORKING_IMAGE);
                                }
                                break;

                            case CRCL_READY:
                            case CRCL_DONE:
                            default:
                                this.jTextFieldStatus.setBackground(Color.WHITE);
                                int line = internal.getCurrentProgramLine();
                                if (null != outerJFrame) {
                                    if (lastIconState == CRCL_ERROR
                                            || lastIconState == null
                                            || !isRunningProgram()
                                            || program == null
                                            || line < 1
                                            || line >= program.getMiddleCommand().size()) {
                                        outerJFrame.setIconImage(DONE_IMAGE);
                                    }
                                }
                                break;

                        }
                        lastIconState = state;
                    }
                    if (null != container && null != outerJFrame) {
                        if (null != stateDescription) {
                            updateTitle(ccst, stateString, stateDescription);
                        }
                    }
                    if (!internal.isRunningProgram()) {
                        String ccstProgramFile = ccst.getProgramFile();
                        if (null != ccstProgramFile
                                && !ccstProgramFile.equals(internal.getOutgoingProgramFile())
                                && !ccstProgramFile.equals(lastProgramFile)) {
                            File f = findProgram(ccstProgramFile);
                            if (null != f) {
                                openXmlProgramFile(f);
                            }
                            lastProgramFile = ccstProgramFile;
                        }
                        Integer ccstProgramIndex = ccst.getProgramIndex();
                        if (null != ccstProgramIndex) {
                            int index = ccstProgramIndex.intValue();
                            if (index != lastProgramIndex && null != program) {
                                finishShowCurrentProgramLine(index, program, curInternalStatus, internal.getProgRunDataList(), ste);
                                lastProgramIndex = index;
                            }
                        }
                    }
                }
                this.jTextFieldStatusID.setText("" + ccst.getStatusID());
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
                        int jn = js.getJointNumber();
                        if (null != js.getJointVelocity()) {
                            if (tm.getColumnCount() < 3) {
                                tm.setColumnCount(3);
                            }
                            tm.setValueAt(js.getJointVelocity(), jn - 1, 2);
                            hasVel = true;
                        }
                        if (null != js.getJointTorqueOrForce()) {
                            if (tm.getColumnCount() < 4) {
                                tm.setColumnCount(4);
                            }
                            tm.setValueAt(js.getJointTorqueOrForce(), jn - 1, 3);
                            hasForce = true;
                        }
                        if (null == js.getJointPosition()) {
//                            tm.setValueAt(Double.NaN, jn-1,1);
                            continue;
                        }

                        double pos = js.getJointPosition();
                        tm.setValueAt(jn, jn - 1, 0);
                        tm.setValueAt(pos, jn - 1, 1);
                        if (this.getMenuOuter().isPlotJointsSelected()) {
                            plotterJFrame plotter = this.jointsPlotter;
                            if (null == this.jointsPlotter) {
                                plotter = new plotterJFrame();
                                plotter.setTitle("JOINTS");
                                plotter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                plotter.setVisible(true);
                                this.jointsPlotter = plotter;
                            }
                            if (null == plotter) {
                                throw new IllegalStateException("null == plotter");
                            }
                            String pname = "joint[" + jn + "]";
                            PlotData pd = plotter.getPlotByName(pname);
                            if (null == pd) {
                                pd = new PlotData();
                                pd.name = pname;
                                plotter.AddPlot(pd, pname);
                            }
                            plotter.AddPointToPlot(pd, t, pos, true);
                            if (pd.get_num_points() < 100) {
                                plotter.FitToGraph();
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
                    = Optional.ofNullable(curInternalStatus)
                            .map(CRCLPosemath::getPose)
                            .orElse(null);
            if (null != p) {
                updatePoseTable(p, this.jTablePose, getCurrentPoseDisplayMode());
                PointType pt = p.getPoint();
                if (null != pt) {
                    ProgramPlotter overheadPlotter = programPlotterJPanelOverhead.getPlotter();
                    if (null != overheadPlotter) {
                        overheadPlotter.setCurrentPoint(pt);
                    }
                    ProgramPlotter sidePlotter = programPlotterJPanelSide.getPlotter();
                    if (null != sidePlotter) {
                        sidePlotter.setCurrentPoint(pt);
                    }
//                    programPlotterJPanelOverhead.getPlotter().setCurrentPoint(pt);
//                    programPlotterJPanelSide.getPlotter().setCurrentPoint(pt);
                }
                checkMenuOuter();
                if (this.menuOuter.isPlotXyzSelected() && null != pt) {
                    plotterJFrame plotter = this.xyzPlotter;
                    if (null == plotter) {
                        plotter = new plotterJFrame();
                        plotter.setTitle("XYZ");
                        plotter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        plotter.setVisible(true);
                        this.xyzPlotter = plotter;
                    }
                    if (null == plotter) {
                        throw new IllegalStateException("null == plotter");
                    }
                    double t = System.currentTimeMillis();
//                    XMLGregorianCalendar xgc = p.getTimestamp();
//                    if (null != xgc) {
//                        double old_t = t;
//                        t = (double) xgc.toGregorianCalendar().getTime().getTime();
//                    }
                    if (t > this.last_t_pos_logged) {

                        PlotData xpd = plotter.getPlotByName("x");
                        if (null == xpd) {
                            xpd = new PlotData();
                            xpd.name = "x";
                            plotter.AddPlot(xpd, "x");
                        }
                        double x = pt.getX();
                        plotter.AddPointToPlot(xpd, t, x, true);
                        PlotData ypd = plotter.getPlotByName("y");
                        if (null == ypd) {
                            ypd = new PlotData();
                            ypd.name = "y";
                            plotter.AddPlot(xpd, "y");
                        }
                        double y = pt.getY();
                        plotter.AddPointToPlot(ypd, t, y, true);
                        PlotData zpd = plotter.getPlotByName("z");
                        if (null == zpd) {
                            zpd = new PlotData();
                            zpd.name = "x";
                            plotter.AddPlot(zpd, "z");
                        }
                        double z = pt.getZ();
                        plotter.AddPointToPlot(zpd, t, z, true);
                        if (xpd.get_num_points() < 100) {
                            plotter.FitToGraph();
                        }
                        plotter.ScrollRight();
                        plotter.repaint();
                        this.last_t_pos_logged = t;
                    }
                }
            }
        }
    }

    private void updateCurrentPoseListeners(CRCLStatusType curInternalStatus, CRCLCommandType lastCommandSent, boolean isHoldingObject, long statReceiveTime) {
        for (CurrentPoseListener l : currentPoseListeners) {
            l.handlePoseUpdate(this, curInternalStatus, lastCommandSent, isHoldingObject, statReceiveTime);
        }
    }

    private void setPlottersInitPoint(@Nullable PointType pt) {
        ProgramPlotter overheadPlotter = programPlotterJPanelOverhead.getPlotter();
        if (null != overheadPlotter) {
            overheadPlotter.setInitPoint(pt);
        }
        ProgramPlotter sidePlotter = programPlotterJPanelSide.getPlotter();
        if (null != sidePlotter) {
            sidePlotter.setInitPoint(pt);
        }
    }

    final List<UpdateTitleListener> updateTitleListeners = new ArrayList<>();

    public void addUpdateTitleListener(UpdateTitleListener utl) {
        updateTitleListeners.add(utl);
    }

    public void removeUpdateTitleListener(UpdateTitleListener utl) {
        updateTitleListeners.remove(utl);
    }

    public String getLastMessage() {
        return internal.getLastMessage();
    }

    public void updateTitle(CommandStatusType ccst, String stateString, String stateDescription) {

        String ccstProgramFile = ccst.getProgramFile();
        Integer ccstProgramIndex = ccst.getProgramIndex();
        String program = (null != ccstProgramFile && null != ccstProgramIndex)
                ? " " + ccstProgramFile + ":" + ccstProgramIndex.toString() : "";
        final Integer ccstProgramLength = ccst.getProgramLength();

        if (!program.isEmpty() && null != ccstProgramLength) {
            program += "/" + ccstProgramLength.toString();
        }
        if (program.length() > 1) {
            program = " " + program.trim() + " ";
        }
        JInternalFrame internalFrame = null;
        if (outerContainer instanceof JInternalFrame) {
            internalFrame = (JInternalFrame) outerContainer;
        }
        String stateDescriptionString = stateDescription;
        if (stateDescriptionString == null) {
            stateDescriptionString = "";
        } else if (stateDescriptionString.length() > 10) {
            stateDescriptionString = stateDescriptionString.substring(0, 9);
        }
        if (stateDescriptionString.length() > 1) {
            stateDescriptionString = " " + stateDescriptionString.trim() + " ";
        }

        String hostPort = "";
        CRCLSocket socket = internal.getCRCLSocket();
        if (null != socket) {
            InetAddress addr = socket.getInetAddress();
            if (null != addr) {
                hostPort = " " + addr.getHostAddress() + ":" + socket.getPort() + " ";
            }
        }
        String newTitle = "CRCL Client: "
                + hostPort
                + stateString
                + stateDescriptionString
                + program;
        if (newTitle.length() > 72) {
            newTitle = newTitle.substring(0, 72);
        }
        if (null != internalFrame) {
            internalFrame.setTitle(newTitle);
        } else if (null != outerJFrame) {
            outerJFrame.setTitle(newTitle);
        }
        if (null != updateTitleListeners) {
            for (UpdateTitleListener utl : updateTitleListeners) {
                utl.titleChanged(ccst, outerContainer, stateString, stateDescription);
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
            if (null == p) {
                p = CRCLPosemath.identityPose();
            }
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
        PmRotationMatrix mat = CRCLPosemath.toPmRotationMatrix(p);
        PmEulerZyx zyx = new PmEulerZyx();
        Posemath.pmMatZyxConvert(mat, zyx);

        if (tm.getRowCount() > 2 + index) {
            tm.setValueAt(Math.toDegrees(zyx.x), 0 + index, 1);
            tm.setValueAt(Math.toDegrees(zyx.y), 1 + index, 1);
            tm.setValueAt(Math.toDegrees(zyx.z), 2 + index, 1);
        }
    }

    private static void updateXaxisZaxisTable(PoseType p, DefaultTableModel tm, int index) {
        VectorType xv = p.getXAxis();
        VectorType zv = p.getZAxis();
        if (null != xv && tm.getRowCount() > 2 + index) {
            tm.setValueAt(xv.getI(), 0 + index, 1);
            tm.setValueAt(xv.getJ(), 1 + index, 1);
            tm.setValueAt(xv.getK(), 2 + index, 1);
        }
        if (null != zv && tm.getRowCount() > 5 + index) {
            tm.setValueAt(zv.getI(), 3 + index, 1);
            tm.setValueAt(zv.getJ(), 4 + index, 1);
            tm.setValueAt(zv.getK(), 5 + index, 1);
        }
    }

    public static void updatePointTable(PoseType p, DefaultTableModel tm, int index) {
        PointType pt = p.getPoint();
        if (null != pt && tm.getRowCount() > 2 + index) {
            tm.setValueAt(pt.getX(), 0 + index, 1);
            tm.setValueAt(pt.getY(), 1 + index, 1);
            tm.setValueAt(pt.getZ(), 2 + index, 1);
        }
    }

    private volatile boolean disconnecting = false;

    public void disconnect() {
        closeShowProgramLogPrintStream();
        disconnecting = true;
        if (isRunningProgram()) {
            internal.showErrorMessage("diconnect while isRunningProgram");
            throw new IllegalStateException("disconnect while isRunningProgram");
        }
        this.jTextFieldStatus.setBackground(Color.GRAY);
        Window window = this.getOuterWindow();
        if (window instanceof PendantClientJFrame) {
            PendantClientJFrame frm = (PendantClientJFrame) window;
            if (null != frm) {
                frm.setIconImage(DISCONNECTED_IMAGE);
                frm.setTitle("CRCL Client: Disconnected");
            }
        }
        if (outerContainer instanceof JInternalFrame) {
            JInternalFrame jInternalFrame = (JInternalFrame) outerContainer;
            jInternalFrame.setTitle("CRCL Client: Disconnected");
        }

        stopPollTimer();
        internal.disconnect();
        jogWorldTransSpeedsSet = false;
        jogWorldRotSpeedsSet = false;
    }

    private void closeShowProgramLogPrintStream() {
        if (null != showProgramLogPrintStream) {
            PrintStream ps = showProgramLogPrintStream;
            showProgramLogPrintStream = null;
            ps.close();
        }
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
        this.jTextFieldHost.setText(_host);
        setPort(_port);
        internal.connect(_host, _port);
        jogWorldTransSpeedsSet = false;
        jogWorldRotSpeedsSet = false;

        Window window = this.getOuterWindow();
        if (window instanceof PendantClientJFrame) {
            PendantClientJFrame frm = (PendantClientJFrame) window;
            if (null != frm) {
                frm.setIconImage(BASE_IMAGE);
                frm.setTitle("CRCL Client: " + _host + ":" + _port);
            }
        }
        if (outerContainer instanceof JInternalFrame) {
            JInternalFrame jInternalFrame = (JInternalFrame) outerContainer;
            jInternalFrame.setTitle("CRCL Client: " + _host + ":" + _port);
        }
    }

    private javax.swing.@Nullable Timer jog_timer = null;

    private double lastJogJointPos = Double.NEGATIVE_INFINITY;

    private void jogJointStart(final double increment) {
        if (null == internal.getCRCLSocket()
                || null == internal.getStatus()) {
            showMessage("Can not send command when not connected.");
            return;
        }
        internal.setJogIncrement(Double.parseDouble(this.jTextFieldJointJogIncrement.getText()));
//        this.setJointControlModes(JointControlModeEnumType.POSITION);
        final int index = this.jComboBoxJointAxis.getSelectedIndex() + 1;
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
                    final CRCLStatusType status
                            = requireNonNull(internal.getStatus(), "internal.getStatus()");
                    final JointStatusType js = CRCLSocket.getJointStatus(status, index);
                    if (null == js) {
                        showMessage("Can't jog without joint position internal.getStatus() for joint " + index);
                        return;
                    }
                    final CommandStatusType commandStatus
                            = requireNonNull(status.getCommandStatus(), "status.getCommandStatus()");
                    if (commandStatus.getCommandState() == CommandStateEnumType.CRCL_ERROR) {
                        showMessage("Can't when status commandState = " + CommandStateEnumType.CRCL_ERROR);
                        jogStop();
                    }
                    double pos = js.getJointPosition();
                    if (apCount > 1) {
                        if (commandStatus.getCommandState() != CommandStateEnumType.CRCL_DONE) {
                            if (PendantClientJPanel.this.menuOuter.isDebugWaitForDoneSelected()
                                    || PendantClientJPanel.this.menuOuter.isDebugSendCommandSelected()) {
                                System.err.println("Jog Timer ActionListener waiting for DONE");
                            }
                            return;
                        }
                        if (commandStatus.getCommandID() < internal.getCmdId()) {
                            if (PendantClientJPanel.this.menuOuter.isDebugWaitForDoneSelected()
                                    || PendantClientJPanel.this.menuOuter.isDebugSendCommandSelected()) {
                                System.err.println("Jog Timer ActionListener waiting for ID greater than " + internal.getCmdId());
                            }
                            return;
                        }
                        if (Math.abs(pos - lastJogJointPos) <= Math.abs((increment) * 0.001)) {
                            System.err.println("Position unchanged from last jog attempt. pos=" + pos);
                        }
                    }
                    ActuateJointsType ajst = new ActuateJointsType();
                    List<ActuateJointType> ajl = ajst.getActuateJoint();
                    ActuateJointType aj = new ActuateJointType();
                    aj.setJointNumber(js.getJointNumber());

//                    
                    lastJogJointPos = pos;
                    aj.setJointPosition(js.getJointPosition() + increment);
                    JointSpeedAccelType jsa = new JointSpeedAccelType();
                    jsa.setJointSpeed(Double.parseDouble(PendantClientJPanel.this.jTextFieldJointJogSpeed.getText()));
                    aj.setJointDetails(jsa);
                    ajl.add(aj);
                    incAndSendCommandFromAwt(ajst);
//                    apCount = 0;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    jogStop();
                }
            }
        };
        jogActionListener.actionPerformed(new ActionEvent(this, JOG_JOINT_START_ACTION_ID, "jogJointStart"));
        jog_timer = new javax.swing.Timer(internal.getJogInterval(), jogActionListener);
        jog_timer.start();
    }
    private static final int JOG_JOINT_START_ACTION_ID = ACTION_FIRST + 1;
    private static final int JOG_WORLD_START_ACTION_ID = ACTION_FIRST + 2;

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
            final double axisIncrement = tmpinc;
            final double inc = tmpinc;
            if (null != jog_timer) {
                jog_timer.stop();
                jog_timer = null;
            }
            jogStopFlag = false;
            ActionListener jogActionListener = new ActionListener() {

                private int actionCount = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        actionCount++;
                        if (jogStopFlag) {
                            return;
                        }
                        if (actionCount < 2 && !internal.isInitSent()) {
                            InitCanonType initCmd = new InitCanonType();
                            incAndSendCommandFromAwt(initCmd);
                            return;
                        }
                        if (!jogWorldTransSpeedsSet) {
                            SetTransSpeedType stst = new SetTransSpeedType();
                            TransSpeedAbsoluteType tas = new TransSpeedAbsoluteType();
                            tas.setSetting(Double.parseDouble(jTextFieldTransSpeed.getText()));
                            stst.setTransSpeed(tas);
                            incAndSendCommandFromAwt(stst);
                            jogWorldTransSpeedsSet = true;
                            return;
                        }
                        if (!jogWorldRotSpeedsSet) {
                            SetRotSpeedType srst = new SetRotSpeedType();
                            RotSpeedAbsoluteType ras = new RotSpeedAbsoluteType();
                            ras.setSetting(Double.parseDouble(jTextFieldRotationSpeed.getText()));
                            srst.setRotSpeed(ras);
                            incAndSendCommandFromAwt(srst);
                            jogWorldRotSpeedsSet = true;
                        }
                        final CRCLStatusType status = requireNonNull(internal.getStatus(), "internal.getStatus()");
                        final CommandStatusType commandStatus = requireNonNull(status.getCommandStatus(), "status.getCommandStatus()");
                        if (commandStatus.getCommandState() == CommandStateEnumType.CRCL_ERROR) {
                            jogStop();
                            final String statusString = status.getCommandStatus().getStateDescription();
                            javax.swing.SwingUtilities.invokeLater(() -> showMessage("Can not jog when status is " + CommandStateEnumType.CRCL_ERROR + " : "
                                    + statusString));
                        }
                        if (commandStatus.getCommandState() != CommandStateEnumType.CRCL_DONE) {
                            if (PendantClientJPanel.this.menuOuter.isDebugWaitForDoneSelected()
                                    || PendantClientJPanel.this.menuOuter.isDebugSendCommandSelected()) {
                                System.err.println("Jog Timer ActionListener waiting for DONE");
                            }
                            return;
                        }
                        if (commandStatus.getCommandID() < internal.getCmdId()) {
                            if (jogStopFlag
                                    || actionCount > 10
                                    || !(internal.getLastCommandSent() instanceof StopMotionType)) {
                                if (PendantClientJPanel.this.menuOuter.isDebugWaitForDoneSelected()
                                        || PendantClientJPanel.this.menuOuter.isDebugSendCommandSelected()) {
                                    System.err.println("Jog Timer ActionListener internal.getStatus().getCommandStatus().getCommandID() < internal.getCmdId() ");
                                    System.err.println("internal.getCmdId() = " + internal.getCmdId());
                                    System.err.println("commandStatus.getCommandID() = " + commandStatus.getCommandID());
                                    System.err.println("internal.getLastCommandSent() = " + internal.getLastCommandSent());
                                    System.err.println("internal.getLastCommandSentStackTrace() = " + Arrays.toString(internal.getLastCommandSentStackTrace()));
                                    System.err.println("internal.getPrevLastCommandSent() = " + internal.getPrevLastCommandSent());
                                }
                                return;
                            }
                        }
                        MoveToType moveToCmd = new MoveToType();
                        PoseType endPos = new PoseType();
                        PointType endPosPoint = new PointType();
                        endPos.setPoint(endPosPoint);
                        VectorType endPosXAxis = new VectorType();
                        endPos.setXAxis(endPosXAxis);
                        VectorType endPosZAxis = new VectorType();
                        endPos.setZAxis(endPosZAxis);
                        moveToCmd.setEndPosition(endPos);
                        PoseType pose = Optional.ofNullable(internal)
                                .map(PendantClientInner::getStatus)
                                .map(CRCLPosemath::getPose)
                                .orElse(null);
                        if (null != pose) {
                            final PointType posePoint = requireNonNull(pose.getPoint(), "pose.getPoint()");
                            endPosPoint.setX(posePoint.getX());
                            endPosPoint.setY(posePoint.getY());
                            endPosPoint.setZ(posePoint.getZ());
                            final VectorType poseXAxis = requireNonNull(pose.getXAxis(), "pose.getXAxis()");
                            endPosXAxis.setI(poseXAxis.getI());
                            endPosXAxis.setJ(poseXAxis.getJ());
                            endPosXAxis.setK(poseXAxis.getK());
                            final VectorType poseZAxis = requireNonNull(pose.getZAxis(), "pose.getZAxis()");
                            endPosZAxis.setI(poseZAxis.getI());
                            endPosZAxis.setJ(poseZAxis.getJ());
                            endPosZAxis.setK(poseZAxis.getK());
                            switch (axis) {
                                case "X":
                                    endPosPoint.setX(posePoint.getX() + axisIncrement);
                                    break;

                                case "Y":
                                    endPosPoint.setY(posePoint.getY() + axisIncrement);
                                    break;

                                case "Z":
                                    endPosPoint.setZ(posePoint.getZ() + axisIncrement);
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
                            incAndSendCommandFromAwt(moveToCmd);
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
                    final PoseType pose = requireNonNull(internal.currentStatusPose(), "internal.getPose()");
                    PmRotationMatrix pm = CRCLPosemath.toPmRotationMatrix(pose);
                    PmRpy rpy = Posemath.toRpy(pm);
                    rpy.y += inc;
                    PmRotationVector pm2 = Posemath.toRot(rpy);
                    PoseType nextPose = CRCLPosemath.toPoseType(
                            CRCLPosemath.toPmCartesian(internal.currentStatusPoint()),
                            pm2);
                    moveToType.setEndPosition(nextPose);
                }

                private void incrementPitch(MoveToType moveToType, final double inc) throws PmException {
                    final PoseType pose = requireNonNull(internal.currentStatusPose(), "internal.getPose()");
                    PmRotationMatrix pm = CRCLPosemath.toPmRotationMatrix(pose);
                    PmRpy rpy = Posemath.toRpy(pm);
                    rpy.p += inc;
                    PmRotationVector pm2 = Posemath.toRot(rpy);
                    PoseType nextPose = CRCLPosemath.toPoseType(
                            CRCLPosemath.toPmCartesian(internal.currentStatusPoint()),
                            pm2);
                    moveToType.setEndPosition(nextPose);
                }

                private void incrementRoll(MoveToType moveToType, final double inc) throws PmException {
                    final PoseType pose = requireNonNull(internal.currentStatusPose(), "internal.getPose()");
                    PmRotationMatrix pm = CRCLPosemath.toPmRotationMatrix(pose);
                    PmRpy rpy = Posemath.toRpy(pm);
                    rpy.r += inc;
                    PmRotationVector pm2 = Posemath.toRot(rpy);
                    PoseType nextPose = CRCLPosemath.toPoseType(
                            CRCLPosemath.toPmCartesian(internal.currentStatusPoint()),
                            pm2);
                    moveToType.setEndPosition(nextPose);
                }
            };
            jogActionListener.actionPerformed(new ActionEvent(this, JOG_WORLD_START_ACTION_ID, "jogWorldStart"));
            jog_timer = new javax.swing.Timer(internal.getJogInterval(), jogActionListener);
            jog_timer.start();
        } catch (Exception ex) {
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

    @Nullable
    public String getCrclClientErrorMessage() {
        return internal.getCrclClientErrorMessage();
    }

    public void clearCrclClientErrorMessage() {
        internal.clearCrclClientErrorMessage();
    }

    private void saveRecentCommandInstance(CRCLCommandInstanceType cmdInstance) throws JAXBException, IOException {
        CRCLSocket tmpcs = internal.getTempCRCLSocket();
        String s = tmpcs.commandInstanceToPrettyDocString(cmdInstance, true);
        File fDir = new File(System.getProperty("user.home"), recent_files_dir);
        boolean made_dir = fDir.mkdirs();
        Logger.getLogger(PendantClientJPanel.class.getName()).finest(() -> "mkdir " + fDir + " returned " + made_dir);
        final CRCLCommandType crclCommand = cmdInstance.getCRCLCommand();
        if (null == crclCommand) {
            return;
        }
        String name = crclCommand.getClass().getSimpleName();
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
        XpathQueryJFrame frame = this.xqJFrame;
        if (null == frame) {
            try {
                frame = new XpathQueryJFrame();
                this.xqJFrame = frame;
            } catch (ParserConfigurationException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        if (null != frame) {
            frame.setVisible(true);
        }
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

    public int getMaxRecordCommandsCount() {
        return internal.getMaxRecordCommandsCount();
    }

    public void setMaxRecordCommandsCount(int maxRecordCommandsCount) {
        internal.setMaxRecordCommandsCount(maxRecordCommandsCount);
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
        } catch (Exception ex) {
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

    @Nullable
    public File getTempLogDir() {
        return internal.getTempLogDir();
    }

    public void setTempLogDir(File tempLogDir) {
        this.internal.setTempLogDir(tempLogDir);
    }

    public void showStatusLog() {
        try {
            File tmpFile
                    = (internal.getTempLogDir() != null)
                    ? File.createTempFile("poseList", ".csv", internal.getTempLogDir())
                    : File.createTempFile("poseList", ".csv");
            this.internal.savePoseListToCsvFile(tmpFile.getAbsolutePath());
            Desktop.getDesktop().open(tmpFile);
        } catch (IOException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PmException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void saveJTable(File f, boolean append, JTable jtable) throws IOException {
        boolean fNotEmpty = f.exists() && f.length() > 0;
        try (CSVPrinter printer = new CSVPrinter(new PrintStream(new FileOutputStream(f, append)), CSVFormat.DEFAULT)) {
            TableModel tm = jtable.getModel();
            if (!fNotEmpty || !append) {
                List<String> colNameList = new ArrayList<>();
                for (int i = 0; i < tm.getColumnCount(); i++) {
                    colNameList.add(tm.getColumnName(i));
                }
                printer.printRecord(colNameList);
            }
            for (int i = 0; i < tm.getRowCount(); i++) {
                List<Object> l = new ArrayList<>();
                for (int j = 0; j < tm.getColumnCount(); j++) {
                    Object o = tm.getValueAt(i, j);
//                    if (o instanceof File) {
//                        Path rel = f.getParentFile().toPath().toRealPath().relativize(Paths.get(((File) o).getCanonicalPath())).normalize();
//                        if (rel.toString().length() < ((File) o).getCanonicalPath().length()) {
//                            l.add(rel);
//                        } else {
//                            l.add(o);
//                        }
//                    } else {
                    l.add(o);
//                    }
                }
                printer.printRecord(l);
            }
        }
    }

    @Nullable
    private volatile File commandStatusLogFile = null;

    private File getCommandStatusLogFile() throws IOException {
        if (null == commandStatusLogFile) {
            commandStatusLogFile
                    = (internal.getTempLogDir() != null)
                    ? File.createTempFile("commandStatus_", ".csv", internal.getTempLogDir())
                    : File.createTempFile("commandStatus_", ".csv");
        }
        return commandStatusLogFile;
    }

    public void showCommandStatusLog() {
        try {
            File f = writeCommandStatusLogFile();
            Desktop.getDesktop().open(f);
        } catch (IOException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private File writeCommandStatusLogFile() throws IOException {
        File f = getCommandStatusLogFile();
        saveJTable(f, true, jTableCommandStatusLog);
        this.internal.printCommandStatusLogNoHeader(f, true, true);
        return f;
    }

    public void browseOpenProgramXml() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "XML Program Files", "xml");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        this.clearRecordedPoints();
        final CRCLProgramType program = internal.getProgram();
        if (null != program) {
            program.getMiddleCommand().clear();
            try {
                this.showProgram(program, Collections.emptyList(), 0);
            } catch (Exception ex) {
                Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            openXmlProgramFile(f);
        }
    }

    public boolean isValidateXmlSchema() {
        return internal.isValidateXmlSchema();
    }

    public void setValidateXmlSchema(boolean validateXmlSchema) {
        internal.setValidateXmlSchema(validateXmlSchema);
    }

    @Override
    public CRCLProgramType editProgram(CRCLProgramType program) {
//        internal.getXpu().setSchemaFiles(internal.getProgramSchemaFiles());
        program = ObjTableJPanel.editObject(program,
                internal.getXpu(),
                internal.getProgSchemaFiles(),
                internal.getCheckProgramValidPredicate());
        return program;
    }

    public void openXmlInstanceFile(File f) throws CRCLException, SAXException, JAXBException, IOException, ParserConfigurationException, XPathExpressionException {
        String s = internal.getXpu().queryXml(f, "/");
        CRCLCommandInstanceType cmdInstance
                = internal.getTempCRCLSocket().stringToCommand(s, internal.isValidateXmlSchema());
        CRCLCommandType cmd = cmdInstance.getCRCLCommand();
        if (null == cmd) {
            return;
        }
        cmd = ObjTableJPanel.editObject(
                cmd,
                internal.getXpu(),
                internal.getCmdSchemaFiles(),
                internal.getCheckCommandValidPredicate());
        incAndSendCommandFromAwt(cmd);
        this.saveRecentCommand(cmd);
    }

    private String tableCommandString(CRCLCommandType cmd) throws ParserConfigurationException, SAXException, IOException, JAXBException {
        return CRCLSocket.commandToSimpleString(cmd);
    }

    @Nullable
    private volatile PrintStream showProgramLogPrintStream = null;
    private AtomicInteger logShowProgramCount = new AtomicInteger();
    private long showProgramLogStartTime = -1;

    private PrintStream getShowProgramLogPrintStream() throws IOException {
        if (null != showProgramLogPrintStream) {
            return showProgramLogPrintStream;
        }
        File programLog = File.createTempFile("showProgramLog_" + getPort(), ".txt");
        showDebugMessage("Logging showProgram debug info to " + programLog);
        showProgramLogStartTime = System.currentTimeMillis();
        showProgramLogPrintStream = new PrintStream(new FileOutputStream(programLog));
        return showProgramLogPrintStream;
    }

    private void logShowProgramInfo(CRCLProgramType program, @Nullable List<ProgramRunData> progRunDataList, int line) throws IOException, JAXBException {
        if (!debugShowProgram) {
            return;
        }
        PrintStream ps = getShowProgramLogPrintStream();
        int count = logShowProgramCount.incrementAndGet();

        String programString = CRCLSocket.getUtilSocket().programToPrettyDocString(program, false);
        File programFile = File.createTempFile("showProgramLog_" + getPort() + "_" + count, ".xml");
        try (PrintStream psProgramFile = new PrintStream(new FileOutputStream(programFile))) {
            psProgramFile.println(programString);
        }
        File progRunDataListFile = File.createTempFile("showProgramLog_progRunDataList_" + getPort() + "_" + count, ".csv");
        int programRunDataListSize = -1;
        if (null != progRunDataList) {
            saveProgramRunDataListToCsv(progRunDataListFile, progRunDataList);
            programRunDataListSize = progRunDataList.size();
        } else {
            progRunDataListFile = null;
        }
        long time = System.currentTimeMillis();
        ps.println("count=" + count + ",time=" + time + ",(time-showProgramLogStartTime)=" + (time - showProgramLogStartTime));
        ps.println("logShowProgramInfo(program=\"" + programFile + "\", programRunDataList=\"" + progRunDataListFile + "\" (size=" + programRunDataListSize + "),line=" + line + ")");
        ps.flush();
    }

    @MonotonicNonNull
    private volatile CRCLProgramType programShowing = null;

    public void showProgram(CRCLProgramType program, List<ProgramRunData> progRunDataList, int line) {
        try {
            programShowing = program;
            logShowProgramInfo(program, progRunDataList, line);
            DefaultTableModel dtm = (DefaultTableModel) this.jTableProgram.getModel();
            if (null == program) {
                dtm.setRowCount(0);
                return;
            }
//            long maxCmdId = 1;
            InitCanonType init = program.getInitCanon();
            List<MiddleCommandType> middleCommands
                    = new ArrayList<>(program.getMiddleCommand());
            EndCanonType endCommand = program.getEndCanon();
            dtm.setRowCount(2 + middleCommands.size());
            long initCmdId = init.getCommandID();
//            maxCmdId = Math.max(maxCmdId, initCmdId);
            dtm.setValueAt(-1, 0, 0);
            dtm.setValueAt(initCmdId, 0, 1);
            try {
                dtm.setValueAt(tableCommandString(init), 0, 2);
            } catch (JAXBException ex) {
                Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            ProgramRunData prd = null;
            if (null != progRunDataList && !progRunDataList.isEmpty()) {
                prd = progRunDataList.get(0);
            }
            if (null != prd && prd != ProgramRunData.PROGRAM_RUN_DATA_PLACEHOLDER) {
                dtm.setValueAt(prd.getTime(), 0, 3);
                dtm.setValueAt(prd.getDist(), 0, 4);
                dtm.setValueAt(prd.isResult(), 0, 5);
            } else {
                dtm.setValueAt(-1L, 0, 3);
                dtm.setValueAt(0.0, 0, 4);
                dtm.setValueAt(false, 0, 5);
            }
            for (int i = 0; i < middleCommands.size(); i++) {
                MiddleCommandType middleCommand = middleCommands.get(i);
                if (null == middleCommand) {
                    showDebugMessage("middleCommands contains null at " + i);
                    continue;
                }
//                maxCmdId = maxCmdId + 1;
                long midCmdId = middleCommand.getCommandID();
//                if (maxCmdId > midCmdId) {
//                    showDebugMessage("middleCommands contains command with too small command id  at " + i + "\n : maxCmdId=" + maxCmdId + ", midCmdId=" + midCmdId+", initCmdId="+initCmdId+",\n program.getInitCanon().getCommandID()="+program.getInitCanon().getCommandID());
//                    throw new IllegalStateException("middleCommands contains command with too small command id  at " + i + "\n : maxCmdId=" + maxCmdId + ", midCmdId=" + midCmdId+", initCmdId="+initCmdId+",\n program.getInitCanon().getCommandID()="+program.getInitCanon().getCommandID());
//                }
//                maxCmdId = Math.max(maxCmdId, middleCommand.getCommandID());
                dtm.setValueAt(i, i + 1, 0);
                dtm.setValueAt(midCmdId, i + 1, 1);
                dtm.setValueAt(tableCommandString(middleCommand), i + 1, 2);
                if (null != progRunDataList && progRunDataList.size() > i + 1 && i < line) {
                    prd = progRunDataList.get(i + 1);
                } else {
                    prd = null;
                }
                if (i > line + 1 && null != progRunDataList && progRunDataList.size() > i + 1
                        && progRunDataList.get(i + 1) != null && progRunDataList.get(i + 1) != ProgramRunData.PROGRAM_RUN_DATA_PLACEHOLDER) {
                    throw new IllegalStateException(" Program Data after line");
                }
                if (null != prd && ProgramRunData.PROGRAM_RUN_DATA_PLACEHOLDER != prd) {
                    dtm.setValueAt(prd.getTime(), i + 1, 3);
                    dtm.setValueAt(prd.getDist(), i + 1, 4);
                    dtm.setValueAt(prd.isResult(), i + 1, 5);
                } else {
                    dtm.setValueAt(-1L, i + 1, 3);
                    dtm.setValueAt(0.0, i + 1, 4);
                    dtm.setValueAt(false, i + 1, 5);
                }
            }
//            maxCmdId = maxCmdId + 1;
            long endCmdId = endCommand.getCommandID();
            if (null != progRunDataList && progRunDataList.size() > 1 + middleCommands.size()) {
                prd = progRunDataList.get(1 + middleCommands.size());
            }
            dtm.setValueAt(-1, 1 + middleCommands.size(), 0);
            dtm.setValueAt(endCmdId, 1 + middleCommands.size(), 1);
            dtm.setValueAt(tableCommandString(endCommand), 1 + middleCommands.size(), 2);
            if (null != prd && prd != ProgramRunData.PROGRAM_RUN_DATA_PLACEHOLDER) {
                dtm.setValueAt(prd.getTime(), 1 + middleCommands.size(), 3);
                dtm.setValueAt(prd.getDist(), 1 + middleCommands.size(), 4);
                dtm.setValueAt(prd.isResult(), 1 + middleCommands.size(), 5);
            } else {
                dtm.setValueAt(-1L, 1 + middleCommands.size(), 3);
                dtm.setValueAt(0.0, 1 + middleCommands.size(), 4);
                if (null == dtm.getValueAt(1 + middleCommands.size(), 5)) {
                    dtm.setValueAt(false, 1 + middleCommands.size(), 5);
                }
            }
            for (int i = 0; i < dtm.getRowCount(); i++) {
                for (int j = 0; j < dtm.getColumnCount(); j++) {
                    Object o = dtm.getValueAt(i, j);
                    Class<?> clss = dtm.getColumnClass(j);
                    if (!clss.isInstance(o)) {
                        System.err.println("Bad object : " + o + " at " + i + "," + j + ": columnClass =" + clss);
                        dtm.setValueAt(null, i, j);
                    }
                }
            }

            if (line >= 0 && line < dtm.getRowCount()) {
                jTableProgram.getSelectionModel().setSelectionInterval(line, line);
            }
        } catch (ParserConfigurationException | SAXException | IOException | JAXBException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        programLineShowing = -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "nullness", "deprecation", "rawtypes"})
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
        jCheckBoxStepping = new javax.swing.JCheckBox();
        jPanelJogging = new javax.swing.JPanel();
        jComboBoxJointAxis = new javax.swing.JComboBox<>();
        jPanelJogMinus = new javax.swing.JPanel();
        jLabelJogMinus = new javax.swing.JLabel();
        jPanelJogPlus = new javax.swing.JPanel();
        jLabelJogPlus = new javax.swing.JLabel();
        jComboBoxXYZRPY = new javax.swing.JComboBox<>();
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanelMoveTo = new javax.swing.JPanel();
        jButtonMoveTo = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableMoveToPose = new javax.swing.JTable();
        jCheckBoxStraight = new javax.swing.JCheckBox();
        jButtonMoveToCurrent = new javax.swing.JButton();
        jComboBoxMoveToPoseDisplayMode = new javax.swing.JComboBox<>();
        transformJPanel1 = new crcl.ui.misc.TransformJPanel();
        jPanelCommandStatusLogOuter = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableCommandStatusLog = new javax.swing.JTable();
        jCheckBoxPauseCommandStatusLog = new javax.swing.JCheckBox();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldLogMaxLength = new javax.swing.JTextField();
        jCheckBoxLogCommandStatusToFile = new javax.swing.JCheckBox();
        jButtonShowCommandStatusLogFile = new javax.swing.JButton();
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
        jScrollPaneErrors = new javax.swing.JScrollPane();
        jTextAreaErrors = new javax.swing.JTextArea();
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

        jTabbedPaneLeftUpper.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPaneLeftUpper.setName(""); // NOI18N
        jTabbedPaneLeftUpper.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneLeftUpperStateChanged(evt);
            }
        });

        jTableProgram.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(-1),  new Long(1), "InitCanonType",  new Long(-1),  new Double(0.0), null},
                { new Integer(-1),  new Long(2), "EndCanonType",  new Long(-1),  new Double(0.0), null}
            },
            new String [] {
                "Index", "ID", "Type", "Time To Execute(ms)", "Distance Moved", "Result"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Long.class, java.lang.String.class, java.lang.Long.class, java.lang.Double.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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

        jButtonStepBack.setText("Step Back");
        jButtonStepBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStepBackActionPerformed(evt);
            }
        });

        jButtonStepFwd.setText("Step Fwd");
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

        jCheckBoxStepping.setText("Stepping");
        jCheckBoxStepping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSteppingActionPerformed(evt);
            }
        });

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
                                .addComponent(jLabelExpectHoldingObject))
                            .addGroup(jPanelProgramLayout.createSequentialGroup()
                                .addComponent(jButtonProgramPause)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonResume)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonProgramAbort)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonProgramRun)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonRunProgFromCurrentLine)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBoxStepping)))
                        .addContainerGap(91, Short.MAX_VALUE))))
        );
        jPanelProgramLayout.setVerticalGroup(
            jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProgramLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneProgram, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
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
                    .addComponent(jButtonRunProgFromCurrentLine)
                    .addComponent(jCheckBoxStepping))
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

        jComboBoxJointAxis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Joint 1 (S)", "Joint 2 (L)", "Joint 3 (U)", "Joint 4 (R)", "Joint 5 (B)", "Joint 6 (T)", "Joint 7 (E)", "Joint 8 " }));

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

        jComboBoxXYZRPY.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "X", "Y", "Z", "Roll", "Pitch", "Yaw", " " }));

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

        jButton1.setText("Open Tool Changer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Close Tool Changer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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
                                .addComponent(jComboBoxJointAxis, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addGroup(jPanelJoggingLayout.createSequentialGroup()
                                .addComponent(lengthUnitComboBoxLengthUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanelJoggingLayout.setVerticalGroup(
            jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJoggingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanelJogMinus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBoxJointAxis)
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
                    .addComponent(lengthUnitComboBoxLengthUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
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
                .addContainerGap(274, Short.MAX_VALUE))
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

        jComboBoxMoveToPoseDisplayMode.setModel(new javax.swing.DefaultComboBoxModel(PoseDisplayMode.values()));
        jComboBoxMoveToPoseDisplayMode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxMoveToPoseDisplayModeItemStateChanged(evt);
            }
        });
        jComboBoxMoveToPoseDisplayMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMoveToPoseDisplayModeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMoveToLayout = new javax.swing.GroupLayout(jPanelMoveTo);
        jPanelMoveTo.setLayout(jPanelMoveToLayout);
        jPanelMoveToLayout.setHorizontalGroup(
            jPanelMoveToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMoveToLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMoveToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jComboBoxMoveToPoseDisplayMode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelMoveToLayout.createSequentialGroup()
                        .addComponent(jButtonMoveTo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxStraight)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonMoveToCurrent))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
                .addContainerGap(408, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxMoveToPoseDisplayMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(329, Short.MAX_VALUE))
        );

        jTabbedPaneLeftUpper.addTab("MoveTo", jPanelMoveTo);
        jTabbedPaneLeftUpper.addTab("Transform Setup", transformJPanel1);

        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTableCommandStatusLog.setModel(getCommandStatusLogModel());
        jScrollPane7.setViewportView(jTableCommandStatusLog);

        jCheckBoxPauseCommandStatusLog.setSelected(true);
        jCheckBoxPauseCommandStatusLog.setText("Pause Log");
        jCheckBoxPauseCommandStatusLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPauseCommandStatusLogActionPerformed(evt);
            }
        });

        jLabel19.setText(" Max: ");

        jTextFieldLogMaxLength.setText("200   ");
        jTextFieldLogMaxLength.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLogMaxLengthActionPerformed(evt);
            }
        });

        jCheckBoxLogCommandStatusToFile.setText("Log to File");
        jCheckBoxLogCommandStatusToFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxLogCommandStatusToFileActionPerformed(evt);
            }
        });

        jButtonShowCommandStatusLogFile.setText("Show");
        jButtonShowCommandStatusLogFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowCommandStatusLogFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCommandStatusLogOuterLayout = new javax.swing.GroupLayout(jPanelCommandStatusLogOuter);
        jPanelCommandStatusLogOuter.setLayout(jPanelCommandStatusLogOuterLayout);
        jPanelCommandStatusLogOuterLayout.setHorizontalGroup(
            jPanelCommandStatusLogOuterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCommandStatusLogOuterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCommandStatusLogOuterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                    .addGroup(jPanelCommandStatusLogOuterLayout.createSequentialGroup()
                        .addComponent(jCheckBoxPauseCommandStatusLog)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLogMaxLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxLogCommandStatusToFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonShowCommandStatusLogFile)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelCommandStatusLogOuterLayout.setVerticalGroup(
            jPanelCommandStatusLogOuterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCommandStatusLogOuterLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelCommandStatusLogOuterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxPauseCommandStatusLog)
                    .addComponent(jLabel19)
                    .addComponent(jTextFieldLogMaxLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxLogCommandStatusToFile)
                    .addComponent(jButtonShowCommandStatusLogFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPaneLeftUpper.addTab("Command Status Log", jPanelCommandStatusLogOuter);

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
        jScrollPaneErrors.setViewportView(jTextAreaErrors);

        jTabbedPaneLeftUpper.addTab("Errors", jScrollPaneErrors);

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
                                .addComponent(jTextFieldStatCmdID, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
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
            .addGap(0, 108, Short.MAX_VALUE)
        );
        programPlotterJPanelOverheadLayout.setVerticalGroup(
            programPlotterJPanelOverheadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 237, Short.MAX_VALUE)
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
            .addGap(0, 237, Short.MAX_VALUE)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneLeftUpper, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPaneRightUpper)
                .addGap(11, 11, 11))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPaneRightUpper)
                    .addComponent(jTabbedPaneLeftUpper))
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
                final CRCLProgramType program = internal.getProgram();
                if (null == program) {
                    return;
                }
                MiddleCommandType cmdOrig = program.getMiddleCommand().get(index - 1);
                MiddleCommandType cmdEdited
                        = (MiddleCommandType) ObjTableJPanel.editObject(
                                cmdOrig,
                                internal.getXpu(),
                                internal.getCmdSchemaFiles(),
                                PendantClientJPanel.this.internal.getCheckCommandValidPredicate());
                if (null == cmdEdited) {
                    showDebugMessage("Edit Program Item cancelled. cmdEdited == null");
                    return;
                }
                program.getMiddleCommand().set(index - 1, cmdEdited);
                this.showProgram(program, internal.getProgRunDataList(), -1);
                this.showCurrentProgramLine(index, program, internal.getStatus(), internal.getProgRunDataList());
            } catch (Exception ex) {
                Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonEditProgramItemActionPerformed

    @SuppressWarnings("rawtypes")
    private static DefaultTableModel getCommandStatusLogModel() {
        return new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                PendantClientInner.COMMAND_STATUS_LOG_HEADINGS
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Boolean.class, java.lang.Double.class, java.lang.Long.class, java.lang.Double.class, java.lang.Object.class, java.lang.Long.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
    }

    private void jButtonDeletProgramItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeletProgramItemActionPerformed
        int index = getProgramRow();
        if (index > 0 && index < this.jTableProgram.getRowCount() - 1) {
            try {
                final CRCLProgramType program = internal.getProgram();
                if (null == program) {
                    return;
                }
                program.getMiddleCommand().remove(index - 1);
                internal.getProgRunDataList().clear();
                this.showProgram(program, Collections.emptyList(), -1);
                this.showCurrentProgramLine(index, program, internal.getStatus(), internal.getProgRunDataList());
            } catch (Exception ex) {
                Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonDeletProgramItemActionPerformed

    private void jButtonAddProgramItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddProgramItemActionPerformed
        try {
            addProgramItem();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            showMessage(ex);
        }
    }//GEN-LAST:event_jButtonAddProgramItemActionPerformed

    @SuppressWarnings("rawtypes")
    private void addProgramItem() throws SecurityException, InvocationTargetException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        int index = getProgramRow();
        if (index > 0 && index < this.jTableProgram.getRowCount() - 1) {
            Class<?> clss = MiddleCommandType.class;
            List<Class<?>> availClasses = getAssignableClasses(clss,
                    ObjTableJPanel.getClasses());
            Class ca[] = availClasses.toArray(new Class[availClasses.size()]);
            final Window outerWindow = this.getOuterWindow();
            if (null == outerWindow) {
                return;
            }
            Class<?> selectedClss = ListChooserJPanel.choose(outerWindow,
                    "Type of new Item",
                    ca, null);
            if (selectedClss == null) {
                showDebugMessage("Add Program Item cancelled. selectedClss == null");
                return;
            }
            MiddleCommandType cmdOrig = (MiddleCommandType) selectedClss.getDeclaredConstructor().newInstance();
            MiddleCommandType cmdEdited
                    = (MiddleCommandType) ObjTableJPanel.editObject(
                            cmdOrig,
                            internal.getXpu(),
                            internal.getCmdSchemaFiles(),
                            PendantClientJPanel.this.internal.getCheckCommandValidPredicate());
            if (null == cmdEdited) {
                showDebugMessage("Add Program Item cancelled. cmdEdited == null");
                return;
            }
            CRCLProgramType program = internal.getProgram();
            if (program == null) {
                program = new CRCLProgramType();
                program.setInitCanon(new InitCanonType());
                program.setEndCanon(new EndCanonType());
                internal.setProgram(program);
            }
            program.getMiddleCommand().add(index - 1, cmdEdited);
            internal.getProgRunDataList().clear();
            this.showProgram(program, Collections.emptyList(), -1);
            this.showCurrentProgramLine(index, program, internal.getStatus(), internal.getProgRunDataList());
        }
    }

    private void jButtonProgramRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProgramRunActionPerformed

        runCurrentProgramAsync(jCheckBoxStepping.isSelected());
    }//GEN-LAST:event_jButtonProgramRunActionPerformed

    public StackTraceElement[] getRunProgramReturnFalseTrace() {
        return internal.getRunProgramReturnFalseTrace();
    }

    @Nullable
    private XFuture<Boolean> lastProgramFuture = null;

    public boolean runCurrentProgram(boolean stepMode) {
        prepRunCurrentProgram(stepMode);
        final CRCLProgramType program = internal.getProgram();
        if (null == program) {
            throw new IllegalStateException("null == program");
        }
        return internal.runProgram(program, 0);
    }

    public XFuture<Boolean> runCurrentProgramAsync(boolean stepMode) {
        try {
            prepRunCurrentProgram(stepMode);
            XFuture<Boolean> future = internal.startRunProgramThread(0);
            XFuture<Boolean> ret = checkFutureChange(future);
            lastProgramFuture = future;
            return ret;
        } catch (Exception ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            XFuture<Boolean> future = new XFuture<>("runCurrentProgram immediate exception");
            future.completeExceptionally(ex);
            lastProgramFuture = null;
            return future;
        }
    }

    private volatile boolean needInitPoint = false;

    private void prepRunCurrentProgram(boolean stepMode) {
        try {
            boolean startAsConnected = isConnected();
            int startPollStopCount = pollStopCount.get();
            Thread startPollThread = pollingThread;
            CRCLSocket startCrclSocket = internal.getCRCLSocket();
            int startlocalPort = -1;
            int startRemotePort = -1;
            if (null != startCrclSocket) {
                Socket startSocket = startCrclSocket.getSocket();
                if (null != startSocket) {
                    startlocalPort = startSocket.getLocalPort();
                    startRemotePort = startSocket.getPort();
                }
            }
            if (!startAsConnected) {
                connectCurrent();
            }
            CRCLSocket step2CrclSocket = internal.getCRCLSocket();
            int step2localPort = -1;
            int step2RemotePort = -1;
            if (null != step2CrclSocket) {
                Socket step2Socket = step2CrclSocket.getSocket();
                if (null != step2Socket) {
                    step2localPort = step2Socket.getLocalPort();
                    step2RemotePort = step2Socket.getPort();
                }
            }
            stopPollTimer();

            this.clearProgramTimesDistances();
            int new_poll_ms = Integer.parseInt(this.jTextFieldPollTime.getText());
            internal.setQuitOnTestCommandFailure(true);
            internal.setPoll_ms(new_poll_ms);
            internal.setWaitForDoneDelay(new_poll_ms);
            setStepMode(stepMode);
            this.jButtonResume.setEnabled(internal.isPaused());
            this.jButtonProgramPause.setEnabled(internal.isRunningProgram());
            jogWorldTransSpeedsSet = false;
            jogWorldRotSpeedsSet = false;
            boolean requestStatusResult = internal.requestStatus();
            if (!requestStatusResult) {
                throw new RuntimeException("requestStatus() returned false");
            }
            boolean readStatusResult = internal.readStatus();
            if (!readStatusResult) {
                throw new RuntimeException("readStatus() returned false");
            }
            System.out.println("prepRunCurrentProgram: readStatusResult = " + readStatusResult);
            PointType pt = internal.currentStatusPoint();
            if (null != pt) {
                pt = CRCLPosemath.copy(pt);
                setPlottersInitPoint(pt);
                needInitPoint = false;
            } else {
                setPlottersInitPoint(null);
                needInitPoint = true;
            }
        } catch (Exception exception) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, exception);
            if (exception instanceof RuntimeException) {
                throw (RuntimeException) exception;
            } else {
                throw new RuntimeException(exception);
            }
        }
    }

    public void setStepMode(boolean newStepMode) {
        internal.setStepMode(newStepMode);
        jCheckBoxStepping.setSelected(newStepMode);
    }

    public boolean isStepMode() {
        boolean stepMode = internal.isStepMode();
        if (jCheckBoxStepping.isSelected() != stepMode) {
            jCheckBoxStepping.setSelected(stepMode);
        }
        return stepMode;
    }

    private void jButtonResumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResumeActionPerformed
        if (pauseTime > this.internal.getRunStartMillis()) {
            this.internal.runStartMillis += (System.currentTimeMillis() - pauseTime);
            pauseTime = -1;
        }
        internal.unpause();
        this.jButtonResume.setEnabled(internal.isPaused());
        this.jButtonProgramPause.setEnabled(internal.isRunningProgram());
        jogWorldTransSpeedsSet = false;
        jogWorldRotSpeedsSet = false;
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
        continueCurrentProgram(jCheckBoxStepping.isSelected());
    }//GEN-LAST:event_jButtonRunProgFromCurrentLineActionPerformed

    public boolean isPaused() {
        return internal.isPaused();
    }

    public boolean isRunningProgram() {
        return internal.isRunningProgram();
    }

    public boolean isBlockPrograms() {
        return internal.isBlockPrograms();
    }

    public int startBlockingPrograms() {
        try {
            setProgram(null);
        } catch (Exception ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return internal.startBlockingPrograms();
    }

    public void stopBlockingPrograms(int count) throws ConcurrentBlockProgramsException {
        internal.stopBlockingPrograms(count);
    }

    public boolean isIgnoreTimeouts() {
        return internal.isIgnoreTimeouts();
    }

    /**
     * Set the value of ignoreTimeouts
     *
     * @param ignoreTimeouts new value of ignoreTimeouts
     */
    public void setIgnoreTimeouts(boolean ignoreTimeouts) {
        internal.setIgnoreTimeouts(ignoreTimeouts);
    }

    @MonotonicNonNull
    private volatile XFuture<Boolean> programFutureInternal = null;
    @MonotonicNonNull
    private volatile XFuture<Boolean> lastContinueCurrentProgramRet = null;

    public XFuture<Boolean> continueCurrentProgram(boolean stepMode) {
        if (internal.isBlockPrograms()) {
            internal.printStartBlockingProgramInfo();
            internal.showErrorMessage("Block Programs");
            throw new IllegalStateException("Block Programs");
        }
        try {
            XFuture<Boolean> origProgramFutureInternal = programFutureInternal;
            if (null != origProgramFutureInternal) {
                System.out.println("origProgramFutureInternal.isDone() = " + origProgramFutureInternal.isDone());
                System.out.println("origProgramFutureInternal.isCancelled() = " + origProgramFutureInternal.isCancelled());
            }
            if (null != lastContinueCurrentProgramRet) {
                System.out.println("lastContinueCurrentProgramRet.isDone() = " + lastContinueCurrentProgramRet.isDone());
                System.out.println("lastContinueCurrentProgramRet.isCancelled() = " + lastContinueCurrentProgramRet.isCancelled());
            }
            if (!isConnected()) {
                connectCurrent();
            }
            final CRCLProgramType program = internal.getProgram();
            if (null == program
                    || getCurrentProgramLine() < 0
                    || getCurrentProgramLine() > (program.getMiddleCommand().size() + 1)) {
                return XFuture.completedFuture(internal.getCommandState() != CommandStateEnumType.CRCL_ERROR);
            }
            if (pauseTime > this.internal.runStartMillis) {
                this.internal.runStartMillis += (System.currentTimeMillis() - pauseTime);
            }
            pauseTime = -1;
            if (this.getCurrentProgramLine() < 1) {
                this.internal.runStartMillis = System.currentTimeMillis();
            }

//            final CountDownLatch latch = new CountDownLatch(1);
//            XFuture<Boolean> newProgramFutureInternal = 
//                    XFuture.runAsync("continueCurrentProgram.step1", () -> {
//                try {
//                    latch.await();
//                    this.internal.closeTestProgramThread();
//                    this.internal.setStepMode(false);
//                    if (internal.isPaused()) {
//                        internal.unpause();
//                    }
//                    internal.resendInit();
//                } catch (Exception ex) {
//                    Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            })
            this.internal.closeTestProgramThread();
            setStepMode(stepMode);
            if (internal.isPaused()) {
                internal.unpause();
            } else {
                System.out.println("internal.isPaused() = " + internal.isPaused());
            }
//            try {
//                internal.resendInit();
//            } catch (InterruptedException ex) {
//                Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
//            }
            XFuture<Boolean> newProgramFutureInternal
                    = internal.startRunProgramThread(-2);//this.getCurrentProgramLine());
            XFuture<Boolean> ret = checkFutureChange(newProgramFutureInternal);
            programFutureInternal = newProgramFutureInternal;
//            latch.countDown();
            if (null != origProgramFutureInternal) {
                System.out.println("origProgramFutureInternal.isDone() = " + origProgramFutureInternal.isDone());
                System.out.println("origProgramFutureInternal.isCancelled() = " + origProgramFutureInternal.isCancelled());
            }
            if (null != lastContinueCurrentProgramRet) {
                System.out.println("lastContinueCurrentProgramRet.isDone() = " + lastContinueCurrentProgramRet.isDone());
                System.out.println("lastContinueCurrentProgramRet.isCancelled() = " + lastContinueCurrentProgramRet.isCancelled());
            }
            lastContinueCurrentProgramRet = ret;
            return ret;
        } catch (Exception ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            XFuture<Boolean> ret = (new XFuture<>("continueCurrentProgram.exception"));
            ret.completeExceptionally(ex);
            return ret;
        }
    }

    private XFuture<Boolean> checkFutureChange(XFuture<Boolean> newProgramFutureInternal) {
        XFuture<Boolean> ret;
        ret = newProgramFutureInternal
                .thenCompose("PendantClient.checkFutureChange",
                        x -> (null != programFutureInternal && newProgramFutureInternal != programFutureInternal) ? checkFutureChange(programFutureInternal) : XFuture.completedFuture(x));
        return ret;
    }

    private void jButtonStepBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStepBackActionPerformed
        setStepMode(true);
        internal.abort();
        int l = this.getCurrentProgramLine();
        if (l > 0) {
            l--;
        }
        internal.startRunProgramThread(l);
    }//GEN-LAST:event_jButtonStepBackActionPerformed

    private void jButtonStepFwdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStepFwdActionPerformed
        setStepMode(true);
        boolean wasPaused = internal.isPaused();
        boolean wasRunning = internal.isRunningProgram();
        if (internal.isPaused()) {
            internal.unpause();
        }
        if (!wasRunning) {
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
        //        cmdId = cmdId.shift(1);
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
            incAndSendCommandFromAwt(setLengthUnitsCmd);
        } catch (JAXBException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lengthUnitComboBoxLengthUnitActionPerformed

    private void jTextFieldJointJogSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldJointJogSpeedActionPerformed
        internal.setJogJointSpeed(Double.parseDouble(this.jTextFieldJointJogSpeed.getText()));
    }//GEN-LAST:event_jTextFieldJointJogSpeedActionPerformed

    private void jTextFieldTransSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTransSpeedActionPerformed
        internal.setJogTransSpeed(Double.parseDouble(this.jTextFieldTransSpeed.getText()));
        jogWorldTransSpeedsSet = false;
    }//GEN-LAST:event_jTextFieldTransSpeedActionPerformed

    private void jTextFieldRotationSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRotationSpeedActionPerformed
        internal.setJogRotSpeed(Double.parseDouble(this.jTextFieldRotationSpeed.getText()));
        jogWorldRotSpeedsSet = false;
    }//GEN-LAST:event_jTextFieldRotationSpeedActionPerformed

    private void jButtonRecordPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecordPointActionPerformed
        this.recordCurrentPoint();
    }//GEN-LAST:event_jButtonRecordPointActionPerformed

    private void jButtonOpenGripperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenGripperActionPerformed
        try {
            SetEndEffectorType seeCmd = new SetEndEffectorType();
            seeCmd.setSetting(1.0);
            incAndSendCommandFromAwt(seeCmd);
            CRCLProgramType recProgram = this.recordPointsProgram;
            if (null != recProgram) {
                this.recordCurrentPoint();
                recProgram.getMiddleCommand().add(seeCmd);
                internal.setProgram(recProgram);
                showProgram(recProgram, Collections.emptyList(), -1);
            }
        } catch (JAXBException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonOpenGripperActionPerformed

    private void jButtonCloseGripperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseGripperActionPerformed
        try {
            SetEndEffectorType seeCmd = new SetEndEffectorType();
            seeCmd.setSetting(0.0);
            incAndSendCommandFromAwt(seeCmd);
            CRCLProgramType recProgram = this.recordPointsProgram;
            if (null != recProgram) {
                this.recordCurrentPoint();
                recProgram.getMiddleCommand().add(seeCmd);
                internal.setProgram(recProgram);
                showProgram(recProgram, Collections.emptyList(), -1);
            }
        } catch (JAXBException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonCloseGripperActionPerformed

    private PoseDisplayMode lastMoveToPoseDisplayMode = PoseDisplayMode.XYZ_XAXIS_ZAXIS;

    private void jButtonMoveToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMoveToActionPerformed
        try {
            MoveToType moveto = new MoveToType();
            PoseType p = tableToPose(this.jTableMoveToPose, lastMoveToPoseDisplayMode);
            moveto.setEndPosition(p);
            moveto.setMoveStraight(this.jCheckBoxStraight.isSelected());
            incAndSendCommandFromAwt(moveto);
        } catch (Exception ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonMoveToActionPerformed

    public PoseDisplayMode getCurrentPoseDisplayMode() {
        return (PoseDisplayMode) jComboBoxPoseDisplayMode.getSelectedItem();
    }

    private void jButtonMoveToCurrentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMoveToCurrentActionPerformed
        lastMoveToPoseDisplayMode = (PoseDisplayMode) jComboBoxMoveToPoseDisplayMode.getSelectedItem();
        final PoseType pose = internal.currentStatusPose();
        if (null != pose) {
            updatePoseTable(pose, this.jTableMoveToPose, lastMoveToPoseDisplayMode);
        }
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
            incAndSendCommandFromAwt(end);
        } catch (JAXBException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonEndActionPerformed

    public boolean incAndSendCommandFromAwt(CRCLCommandType cmd) throws JAXBException {
        internal.clearLastIncCommandThread();
        boolean ret = internal.incAndSendCommand(cmd);
        internal.clearLastIncCommandThread();
        return ret;
    }


    private void jButtonInitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInitActionPerformed
        try {
            InitCanonType init = new InitCanonType();
            incAndSendCommandFromAwt(init);
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
        jogWorldTransSpeedsSet = false;
        jogWorldRotSpeedsSet = false;
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

    @SuppressWarnings({"nullness", "rawtypes"})
    private void setPoseDisplayModelXAxisZAxis(JTable table, boolean editable) {
        table.setModel(new javax.swing.table.DefaultTableModel(
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
                false, editable
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

    /**
     * Set the value of enableDebugConnect
     *
     * @param enableDebugConnect should debugging connections be enabled.
     */
    public void setEnableDebugConnect(boolean enableDebugConnect) {
        internal.setDebugConnectDisconnect(enableDebugConnect);
    }

    @SuppressWarnings({"nullness", "rawtypes"})
    private void setPoseDisplayModelRpy(JTable table, boolean editable) {
        table.setModel(new javax.swing.table.DefaultTableModel(
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
                false, editable
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    @SuppressWarnings({"nullness", "rawtypes"})
    private void setPoseDisplayModelRxRyRz(JTable table, boolean editable) {
        table.setModel(new javax.swing.table.DefaultTableModel(
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
                false, editable
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
        updateDisplayMode(jTablePose, getCurrentPoseDisplayMode(), false);
        final PoseType pose = internal.currentStatusPose();
        if (null != pose) {
            updatePoseTable(pose, jTablePose, getCurrentPoseDisplayMode());
        }
    }//GEN-LAST:event_jComboBoxPoseDisplayModeActionPerformed

    private void jComboBoxPoseDisplayModeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxPoseDisplayModeItemStateChanged
        updateDisplayMode(jTablePose, getCurrentPoseDisplayMode(), false);
        final PoseType pose = internal.currentStatusPose();
        if (null != pose) {
            updatePoseTable(pose, jTablePose, getCurrentPoseDisplayMode());
        }
    }//GEN-LAST:event_jComboBoxPoseDisplayModeItemStateChanged

    private void jComboBoxMoveToPoseDisplayModeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxMoveToPoseDisplayModeItemStateChanged
        try {
            PoseDisplayMode newMoveToPoseDisplayMode = (PoseDisplayMode) jComboBoxMoveToPoseDisplayMode.getSelectedItem();
            if (newMoveToPoseDisplayMode != lastMoveToPoseDisplayMode) {
                PoseType origPose = tableToPose(jTableMoveToPose, lastMoveToPoseDisplayMode);
                updateDisplayMode(jTableMoveToPose, newMoveToPoseDisplayMode, true);
                lastMoveToPoseDisplayMode = newMoveToPoseDisplayMode;
                updatePoseTable(origPose, this.jTableMoveToPose, lastMoveToPoseDisplayMode);
            }
        } catch (Exception ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBoxMoveToPoseDisplayModeItemStateChanged

    private void jComboBoxMoveToPoseDisplayModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMoveToPoseDisplayModeActionPerformed
        try {
            PoseDisplayMode newMoveToPoseDisplayMode = (PoseDisplayMode) jComboBoxMoveToPoseDisplayMode.getSelectedItem();
            if (newMoveToPoseDisplayMode != lastMoveToPoseDisplayMode) {
                PoseType origPose = tableToPose(jTableMoveToPose, lastMoveToPoseDisplayMode);
                updateDisplayMode(jTableMoveToPose, newMoveToPoseDisplayMode, true);
                lastMoveToPoseDisplayMode = newMoveToPoseDisplayMode;
                updatePoseTable(origPose, this.jTableMoveToPose, lastMoveToPoseDisplayMode);
            }
        } catch (Exception ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBoxMoveToPoseDisplayModeActionPerformed

    private boolean pauseCommandStatusLog = true;
    private void jCheckBoxPauseCommandStatusLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPauseCommandStatusLogActionPerformed
        pauseCommandStatusLog = jCheckBoxPauseCommandStatusLog.isSelected();
    }//GEN-LAST:event_jCheckBoxPauseCommandStatusLogActionPerformed

    private void jTextFieldLogMaxLengthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLogMaxLengthActionPerformed
        internal.setMaxLogSize(Integer.parseInt(jTextFieldLogMaxLength.getText()));
    }//GEN-LAST:event_jTextFieldLogMaxLengthActionPerformed

    private void jButtonShowCommandStatusLogFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowCommandStatusLogFileActionPerformed
        showCommandStatusLog();
    }//GEN-LAST:event_jButtonShowCommandStatusLogFileActionPerformed

    private void jCheckBoxLogCommandStatusToFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxLogCommandStatusToFileActionPerformed
        commandStatusLogFile = null;
        logCommandStatusToFile = jCheckBoxLogCommandStatusToFile.isSelected();
        ((DefaultTableModel) jTableCommandStatusLog.getModel()).setRowCount(0);
        jCheckBoxPauseCommandStatusLog.setSelected(true);
        pauseCommandStatusLog = true;
    }//GEN-LAST:event_jCheckBoxLogCommandStatusToFileActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            OpenToolChangerType otcCmd = new OpenToolChangerType();
            incAndSendCommandFromAwt(otcCmd);
            CRCLProgramType recProgram = this.recordPointsProgram;
            if (null != recProgram) {
                this.recordCurrentPoint();
                recProgram.getMiddleCommand().add(otcCmd);
                internal.setProgram(recProgram);
                showProgram(recProgram, Collections.emptyList(), -1);
            }
        } catch (JAXBException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            CloseToolChangerType ctcCmd = new CloseToolChangerType();
            incAndSendCommandFromAwt(ctcCmd);
            CRCLProgramType recProgram = this.recordPointsProgram;
            if (null != recProgram) {
                this.recordCurrentPoint();
                recProgram.getMiddleCommand().add(ctcCmd);
                internal.setProgram(recProgram);
                showProgram(recProgram, Collections.emptyList(), -1);
            }
        } catch (JAXBException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBoxSteppingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSteppingActionPerformed
        internal.setStepMode(jCheckBoxStepping.isSelected());
    }//GEN-LAST:event_jCheckBoxSteppingActionPerformed

    @Nullable
    public Thread getRunProgramThread() {
        return internal.getRunProgramThread();
    }

    @Nullable
    public XFuture<Boolean> getRunProgramFuture() {
        return internal.getRunProgramFuture();
    }

    private volatile boolean logCommandStatusToFile = false;

    private void updateDisplayMode(JTable table, PoseDisplayMode displayMode, boolean editable) {
        switch (displayMode) {
            case XYZ_XAXIS_ZAXIS:
                setPoseDisplayModelXAxisZAxis(table, editable);
                break;

            case XYZ_RPY:
                setPoseDisplayModelRpy(table, editable);
                break;

            case XYZ_RX_RY_RZ:
                setPoseDisplayModelRxRyRz(table, editable);
                break;
        }
    }

    private static final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss.SSS");

    public static String getTimeString(long ms) {
        Date date = new Date(ms);
        return timeFormat.format(date);
    }

    public static void autoResizeTableColWidths(JTable table) {

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        int fullsize = 0;
        Container parent = table.getParent();
        if (null != parent) {
            fullsize = Math.max(parent.getPreferredSize().width, parent.getSize().width);
        }
        int sumWidths = 0;
        for (int i = 0; i < table.getColumnCount(); i++) {
            DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
            TableColumn col = colModel.getColumn(i);
            int width = 0;

            TableCellRenderer renderer = col.getHeaderRenderer();
            if (renderer == null) {
                renderer = table.getTableHeader().getDefaultRenderer();
            }
            Component headerComp = renderer.getTableCellRendererComponent(table, col.getHeaderValue(),
                    false, false, 0, i);
            width = Math.max(width, headerComp.getPreferredSize().width);
            for (int r = 0; r < table.getRowCount(); r++) {
                renderer = table.getCellRenderer(r, i);
                Component comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, i),
                        false, false, r, i);
                width = Math.max(width, comp.getPreferredSize().width);
            }
            if (i == table.getColumnCount() - 1) {
                if (width < fullsize - sumWidths) {
                    width = fullsize - sumWidths;
                }
            }
            col.setPreferredWidth(width + 2);
            sumWidths += width + 2;
        }
    }

    public void printCommandStatusLog() throws IOException {
        internal.printCommandStatusLog(System.out, false);
    }

    public void printCommandStatusLog(Appendable appendable, boolean clearLog) throws IOException {
        internal.printCommandStatusLog(appendable, clearLog);
    }

    public String commandStatusLogToString() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            internal.printCommandStatusLog(pw, false);
        } catch (IOException ex) {
            Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sw.toString();
    }

    public Object @Nullable [] logElementToArray(CommandStatusLogElement el) {
        return internal.logElementToArray(el);
    }

    @Override
    public void updateCommandStatusLog(Deque<CommandStatusLogElement> log) {
        if (logCommandStatusToFile) {
            try {
                writeCommandStatusLogFile();
                return;
            } catch (IOException ex) {
                Logger.getLogger(PendantClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!pauseCommandStatusLog && !log.isEmpty()) {
            javax.swing.SwingUtilities.invokeLater(() -> {
                if (!log.isEmpty() && !jCheckBoxPauseCommandStatusLog.isSelected()) {
                    DefaultTableModel tableModel = (DefaultTableModel) jTableCommandStatusLog.getModel();
                    while (!log.isEmpty()) {
                        while (tableModel.getRowCount() > internal.getMaxLogSize()) {
                            tableModel.removeRow(0);
                        }
                        CommandStatusLogElement el = log.pollFirst();
                        if (null != el) {
                            Object[] data = logElementToArray(el);
                            if (null != data) {
                                tableModel.addRow(data);
                            }
                        }
                    }
                    autoResizeTableColWidths(jTableCommandStatusLog);
                }
            });
        }
    }

    private static String comparableClassName(Class<?> clss) {
        String canonicalName = clss.getCanonicalName();
        if (null != canonicalName) {
            return canonicalName;
        }
        return clss.getName();
    }

    public void addToCommandsMenu(JMenu commandsMenuParent) {
        PendantClientJPanel pendantClientJPanel1 = this;
        List<Class<?>> allClasses = ObjTableJPanel.getClasses();
        List<Class<?>> cmdClasses = ObjTableJPanel.getAssignableClasses(CRCLCommandType.class,
                allClasses);
        Collections.sort(cmdClasses, Comparator.comparing(PendantClientJPanel::comparableClassName));
        for (final Class<?> c : cmdClasses) {
            JMenuItem jmi = new JMenuItem(comparableClassName(c));
            jmi.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        PendantClientInner internal = pendantClientJPanel1.getInternal();
                        CRCLCommandType cmd = (CRCLCommandType) c.getDeclaredConstructor().newInstance();
                        cmd
                                = ObjTableJPanel.editObject(
                                        cmd,
                                        internal.getXpu(),
                                        internal.getCmdSchemaFiles(),
                                        internal.getCheckCommandValidPredicate());
                        if (null != cmd) {
                            internal.incAndSendCommand(cmd);
                            pendantClientJPanel1.saveRecentCommand(cmd);
                        }
                    } catch (Exception ex) {
                        LOGGER.log(Level.SEVERE, null, ex);
                    }
                }
            });
            commandsMenuParent.add(jmi);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JButton jButtonShowCommandStatusLogFile;
    private javax.swing.JButton jButtonStepBack;
    private javax.swing.JButton jButtonStepFwd;
    private javax.swing.JCheckBox jCheckBoxLogCommandStatusToFile;
    private javax.swing.JCheckBox jCheckBoxMonitorHoldingOutput;
    private javax.swing.JCheckBox jCheckBoxPauseCommandStatusLog;
    private javax.swing.JCheckBox jCheckBoxPoll;
    private javax.swing.JCheckBox jCheckBoxStepping;
    private javax.swing.JCheckBox jCheckBoxStraight;
    private javax.swing.JComboBox<String> jComboBoxJointAxis;
    private javax.swing.JComboBox<PoseDisplayMode> jComboBoxMoveToPoseDisplayMode;
    private javax.swing.JComboBox<PoseDisplayMode> jComboBoxPoseDisplayMode;
    private javax.swing.JComboBox<String> jComboBoxXYZRPY;
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
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JPanel jPanelCommandStatusLogOuter;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPaneErrors;
    private javax.swing.JScrollPane jScrollPaneProgram;
    private javax.swing.JTabbedPane jTabbedPaneLeftUpper;
    private javax.swing.JTabbedPane jTabbedPaneRightUpper;
    private javax.swing.JTable jTableCommandStatusLog;
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
    private javax.swing.JTextField jTextFieldLogMaxLength;
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
