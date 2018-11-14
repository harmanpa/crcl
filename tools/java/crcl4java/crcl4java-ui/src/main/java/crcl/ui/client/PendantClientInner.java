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

import crcl.utils.outer.interfaces.CommandStatusLogElement;
import crcl.base.ActuateJointType;
import crcl.base.ActuateJointsType;
import crcl.base.AngleUnitEnumType;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLProgramType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStateEnumType;
import static crcl.base.CommandStateEnumType.CRCL_ERROR;
import static crcl.base.CommandStateEnumType.CRCL_WORKING;
import crcl.base.CommandStatusType;
import crcl.base.ConfigureJointReportType;
import crcl.base.ConfigureJointReportsType;
import crcl.base.DwellType;
import crcl.base.EndCanonType;
import crcl.base.GetStatusType;
import crcl.base.GripperStatusType;
import crcl.base.InitCanonType;
import crcl.base.JointDetailsType;
import crcl.base.JointSpeedAccelType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.LengthUnitEnumType;
import crcl.base.MessageType;
import crcl.base.MiddleCommandType;
import crcl.base.MoveScrewType;
import crcl.base.MoveThroughToType;
import crcl.base.MoveToType;
import crcl.base.ParallelGripperStatusType;
import crcl.base.PointType;
import crcl.base.PoseAndSetType;
import crcl.base.PoseType;
import crcl.base.PoseStatusType;
import crcl.base.PoseToleranceType;
import crcl.base.RotAccelAbsoluteType;
import crcl.base.RotAccelRelativeType;
import crcl.base.RotAccelType;
import crcl.base.RotSpeedAbsoluteType;
import crcl.base.RotSpeedRelativeType;
import crcl.base.RotSpeedType;
import crcl.base.SetAngleUnitsType;
import crcl.base.SetEndEffectorType;
import crcl.base.SetEndPoseToleranceType;
import crcl.base.SetIntermediatePoseToleranceType;
import crcl.base.SetLengthUnitsType;
import crcl.base.SetTransSpeedType;
import crcl.base.StopConditionEnumType;
import crcl.base.StopMotionType;
import crcl.base.ThreeFingerGripperStatusType;
import crcl.base.TransAccelAbsoluteType;
import crcl.base.TransAccelRelativeType;
import crcl.base.TransAccelType;
import crcl.base.TransSpeedAbsoluteType;
import crcl.base.TransSpeedRelativeType;
import crcl.base.TransSpeedType;
import crcl.base.VacuumGripperStatusType;
import crcl.base.VectorType;
import crcl.ui.ConcurrentBlockProgramsException;
import crcl.ui.DefaultSchemaFiles;
import crcl.utils.CrclCommandWrapper;
import crcl.ui.XFuture;
import static crcl.ui.client.PendantClientJPanel.getTimeString;

import crcl.ui.misc.MultiLineStringJPanel;
import crcl.ui.server.SimServerInner;
import crcl.utils.AnnotatedPose;
import crcl.utils.CRCLPosemath;
import crcl.utils.CRCLSocket;
import crcl.utils.CRCLException;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.pose;
import static crcl.utils.CRCLPosemath.vectorToPmCartesian;
import crcl.utils.outer.interfaces.PendantClientOuter;
import crcl.utils.PoseToleranceChecker;
import crcl.utils.Utils;
import crcl.utils.XpathUtils;
import crcl.utils.outer.interfaces.PendantClientMenuOuter;
import crcl.utils.outer.interfaces.ProgramRunData;
import static crcl.utils.outer.interfaces.ProgramRunData.PROGRAM_RUN_DATA_PLACEHOLDER;
import java.awt.Desktop;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.net.SocketException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static java.util.Objects.requireNonNull;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.xpath.XPathExpressionException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.xml.sax.SAXException;
import rcs.posemath.PmCartesian;
import rcs.posemath.PmException;
import rcs.posemath.PmPose;
import rcs.posemath.PmRpy;
import rcs.posemath.Posemath;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class PendantClientInner {

    //    public static final BigDecimal BIG_DECIMAL_POINT_01 = new BigDecimal("0.01");
    public static final double FIVE_DEGREES_IN_RADIANS = Math.toRadians(5.0);
    public static final Logger LOGGER = Logger.getLogger(PendantClientInner.class.getName());
    public static final String PROP_LENGTHUNIT = "lengthUnit";

    private static long getLongProperty(String propName, long defaultLong) {
        return Long.parseLong(System.getProperty(propName, Long.toString(defaultLong)));
    }

    public static <T> Optional<T> tryGet(TrySupplier<T> ts) {
        try {
            return Optional.ofNullable(ts.tryGet());
        } catch (Throwable ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return Optional.empty();
    }

    public static Optional<JointStatusType> getJointStatus(CRCLStatusType status, int id) {
        return Optional.ofNullable(status)
                .map((x) -> x.getJointStatuses())
                .filter((x) -> x != null)
                .map((x) -> x.getJointStatus())
                .flatMap((x) -> x.stream().filter((x2) -> x2.getJointNumber() == id).findAny());
    }

    public static Stream<JointStatusType> getJointValues(CRCLStatusType status,
            Collection<Integer> ids) {
        return ids.stream()
                .map((x) -> getJointStatus(status, x))
                .flatMap((x) -> x.map(Stream::of).orElseGet(Stream::empty));
    }

    public static String getJointString(CRCLStatusType status,
            Function<JointStatusType, String> mapper,
            Collection<Integer> ids) {
        return getJointValues(status, ids)
                .map(mapper)
                .collect(Collectors.joining(","));
    }

    @MonotonicNonNull
    private CRCLStatusType status = null;

    @Nullable
    private volatile CRCLSocket crclSocket = null;

    private final PendantClientOuter outer;
    private static final double JOG_INCREMENT_DEFAULT = 3.0;
    private double jogIncrement = JOG_INCREMENT_DEFAULT;
    @Nullable
    private volatile CRCLProgramType program = null;

    private PoseToleranceType expectedEndPoseTolerance = new PoseToleranceType();
    private PoseToleranceType expectedIntermediatePoseTolerance;
    private volatile AtomicInteger close_test_count = new AtomicInteger(0);
    int request_status_count = 0;
    private final GetStatusType getStatusMsg = new GetStatusType();
    private static final int JOG_INTERVAL_DEFAULT = 50;
    private int jogInterval = JOG_INTERVAL_DEFAULT;
    private static final double XYZ_JOG_INCREMENT_DEFAULT = 3.0;
    private double xyzJogIncrement = XYZ_JOG_INCREMENT_DEFAULT;
    private int poll_ms = jogInterval;

    private final XpathUtils xpu;
    @MonotonicNonNull
    private CRCLSocket checkerCRCLSocket = null;
    @MonotonicNonNull
    private CRCLCommandInstanceType checkerCommandInstance = null;

    @SuppressWarnings("initialization")
    private final Function<CRCLProgramType, XFuture<Boolean>> checkProgramValidPredicate
            = this::checkProgramValid;

    @SuppressWarnings("initialization")
    private final Function<CRCLCommandType, XFuture<Boolean>> checkCommandValidPredicate
            = this::checkCommandValid;

//    private File[] cmdSchemaFiles = null;
//    private File[] programSchemaFiles = null;
    @Nullable
    private CRCLCommandType lastCommandSent = null;

    @Nullable
    public CRCLCommandType getLastCommandSent() {
        return this.lastCommandSent;
    }

    @Nullable
    private CRCLCommandType prevLastCommandSent = null;
    private boolean recordCommands = false;
    private int maxRecordCommandsCount = 1000;

    public int getMaxRecordCommandsCount() {
        return maxRecordCommandsCount;
    }

    public void setMaxRecordCommandsCount(int maxRecordCommandsCount) {
        this.maxRecordCommandsCount = maxRecordCommandsCount;
    }

    final private Queue<CRCLCommandType> recordedCommandsQueue
            = new ConcurrentLinkedQueue<>();
    private final List<CRCLCommandType> recordedCommandsList = new ArrayList<>();
    private long waitForDoneDelay = getLongProperty("PendantClient.waitForDoneDelay", 100);
    @Nullable
    private Thread readerThread = null;
    final private List<AnnotatedPose> poseList = new ArrayList<>();
    final private Queue<AnnotatedPose> poseQueue = new ConcurrentLinkedQueue<>();
    private boolean disconnecting = false;
    private boolean stopStatusReaderFlag = false;
    private double jointTol = jogIncrement * 5.0;
    private AngleUnitEnumType angleType = AngleUnitEnumType.RADIAN;
    private LengthUnitEnumType lengthUnit = LengthUnitEnumType.MILLIMETER;

    @SuppressWarnings("initialization")
    private final PropertyChangeSupport propertyChangeSupport
            = new PropertyChangeSupport(this);

    final private Map<Integer, ConfigureJointReportType> cjrMap = new HashMap<>();
    private volatile BlockingQueue<Object> pauseQueue = new ArrayBlockingQueue<>(1);
    private volatile boolean paused = false;
    private volatile AtomicInteger waiting_for_pause_queue = new AtomicInteger(0);
    private volatile AtomicInteger pause_count = new AtomicInteger(0);
    long programCommandStartTime;
    private volatile boolean stepMode = false;
    private boolean quitOnTestCommandFailure = !Boolean.getBoolean("crcl4java.client.continueOnTestCommandFailure");
    long runStartMillis = 0;
    long runEndMillis = 0;
    private double jointMoveAccel;
    private double jointMoveSpeed;
    @Nullable
    private volatile CRCLStatusType testCommandStartStatus = null;
    private boolean lengthUnitSent = false;
    private LengthUnitEnumType testCommandStartLengthUnit = LengthUnitEnumType.MILLIMETER;
    boolean testCommandStartLengthUnitSent = false;

    private double jogRotSpeed = 3.0;

    private PendantClientMenuOuter menuOuter() {
        if (null == outer) {
            throw new IllegalStateException("PendantClientOuter not set.");
        }
        if (null == outer.getMenuOuter()) {
            throw new IllegalStateException("PendantClientOuter has null PendantClientMenuOuter.");
        }
        return outer.getMenuOuter();
    }

    /**
     * Get the value of jogRotSpeed
     *
     * @return the value of jogRotSpeed
     */
    public double getJogRotSpeed() {
        return jogRotSpeed;
    }

    /**
     * Set the value of jogRotSpeed
     *
     * @param jogRotSpeed new value of jogRotSpeed
     */
    public void setJogRotSpeed(double jogRotSpeed) {
        this.jogRotSpeed = jogRotSpeed;
    }

    private static final double JOG_TRANS_SPEED_DEFAULT = 100.0;

    private double jogTransSpeed = JOG_TRANS_SPEED_DEFAULT;

    /**
     * Get the value of jogTransSpeed
     *
     * @return the value of jogTransSpeed
     */
    public double getJogTransSpeed() {
        return jogTransSpeed;
    }

    /**
     * Set the value of jogTransSpeed
     *
     * @param jogTransSpeed new value of jogTransSpeed
     */
    public void setJogTransSpeed(double jogTransSpeed) {
        this.jogTransSpeed = jogTransSpeed;
    }

    private File statSchemaFiles[];
    private File cmdSchemaFiles[];
    private File progSchemaFiles[];

    public File[] getStatSchemaFiles() {
        return statSchemaFiles;
    }

    public File[] getCmdSchemaFiles() {
        return cmdSchemaFiles;
    }

    public File[] getProgSchemaFiles() {
        return progSchemaFiles;
    }

    private final StackTraceElement createStackTrace[];
    private final DefaultSchemaFiles defaultsInstance;

    PendantClientInner(PendantClientOuter outer, DefaultSchemaFiles defaultsInstance) throws ParserConfigurationException {
        this.outer = outer;
        this.xpu = new XpathUtils();
        this.defaultsInstance = defaultsInstance;
        createStackTrace = Thread.currentThread().getStackTrace();
        this.expectedEndPoseTolerance = new PoseToleranceType();
        this.expectedEndPoseTolerance.setXAxisTolerance(5.0 /* degrees */);
        this.expectedEndPoseTolerance.setZAxisTolerance(5.0 /* degrees */);
        this.expectedEndPoseTolerance.setXPointTolerance(3.0);
        this.expectedEndPoseTolerance.setYPointTolerance(3.0);
        this.expectedEndPoseTolerance.setZPointTolerance(3.0);

        this.expectedIntermediatePoseTolerance = new PoseToleranceType();
        this.expectedIntermediatePoseTolerance.setXAxisTolerance(5.0 /* degrees */);
        this.expectedIntermediatePoseTolerance.setZAxisTolerance(5.0 /* degrees */);
        this.expectedIntermediatePoseTolerance.setXPointTolerance(3.0);
        this.expectedIntermediatePoseTolerance.setYPointTolerance(3.0);
        this.expectedIntermediatePoseTolerance.setZPointTolerance(3.0);
        this.statSchemaFiles = CRCLSocket.readStatSchemaFiles(defaultsInstance.getStatSchemasFile());
        this.setStatSchema(this.statSchemaFiles);
        this.cmdSchemaFiles = CRCLSocket.readCmdSchemaFiles(defaultsInstance.getCmdSchemasFile());
        this.setCmdSchema(this.cmdSchemaFiles);
        this.progSchemaFiles = CRCLSocket.readProgramSchemaFiles(defaultsInstance.getProgramSchemasFile());
        this.setProgramSchema(this.progSchemaFiles);
    }

    /**
     * Get the value of expectedEndPoseTolerance
     *
     * @return the value of expectedEndPoseTolerance
     */
    public PoseToleranceType getExpectedEndPoseTolerance() {
        return expectedEndPoseTolerance;
    }

    /**
     * Set the value of expectedEndPoseTolerance
     *
     * @param expectedEndPoseTolerance new value of expectedEndPoseTolerance
     */
    public void setExpectedEndPoseTolerance(PoseToleranceType expectedEndPoseTolerance) {
        this.expectedEndPoseTolerance = expectedEndPoseTolerance;
    }

    /**
     * Get the value of expectedIntermediatePoseTolerance
     *
     * @return the value of expectedIntermediatePoseTolerance
     */
    public PoseToleranceType getExpectedIntermediatePoseTolerance() {
        return expectedIntermediatePoseTolerance;
    }

    /**
     * Set the value of expectedIntermediatePoseTolerance
     *
     * @param expectedIntermediatePoseToleranceType new value of
     * expectedIntermediatePoseTolerance
     */
    public void setExpectedIntermediatePoseTolerance(PoseToleranceType expectedIntermediatePoseToleranceType) {
        this.expectedIntermediatePoseTolerance = expectedIntermediatePoseToleranceType;
    }

    /**
     * Get the value of program
     *
     * @return the value of program
     */
    @Nullable
    public CRCLProgramType getProgram() {
        return program;
    }

    private final List<ProgramRunData> progRunDataList = new ArrayList<>();

    /**
     * Get the value of progRunDataList
     *
     * @return the value of progRunDataList
     */
    public List<ProgramRunData> getProgRunDataList() {
        return progRunDataList;
    }

    /**
     * Set the value of program
     *
     * @param program new value of program
     */
    public void setProgram(@Nullable CRCLProgramType program) {
        this.program = program;
        progRunDataList.clear();
        outer.clearProgramTimesDistances();
    }

    @Nullable
    public CRCLSocket getCRCLSocket() {
        return this.crclSocket;
    }

    private boolean debugInterrupts;

    /**
     * Get the value of debugInterrupts
     *
     * @return the value of debugInterrupts
     */
    public boolean isDebugInterrupts() {
        return debugInterrupts;
    }

    /**
     * Set the value of debugInterrupts
     *
     * @param debugInterrupts new value of debugInterrupts
     */
    public void setDebugInterrupts(boolean debugInterrupts) {
        this.debugInterrupts = debugInterrupts;
    }

    private final List<StackTraceElement[]> interruptStacks = Collections.synchronizedList(new ArrayList<>());

    private volatile boolean blockPrograms = false;
    private final AtomicInteger blockProgramsSetCount = new AtomicInteger();

    @Nullable
    private volatile Thread startBlockProgramsThread = null;
    private volatile StackTraceElement startBlockProgramsTrace @Nullable []  = null;
    private volatile long startBlockProgramsTime = -1;

    public void printStartBlockingProgramInfo() {
        System.out.println("startBlockProgramsThread = " + startBlockProgramsThread);
        System.out.println("startBlockProgramsTrace = " + Arrays.toString(startBlockProgramsTrace));
        System.out.println("startBlockProgramsTime = " + (System.currentTimeMillis() - startBlockProgramsTime));
    }

    public boolean isBlockPrograms() {
        return blockPrograms;
    }

    public int startBlockingPrograms() {
        startBlockProgramsThread = Thread.currentThread();
        startBlockProgramsTrace = startBlockProgramsThread.getStackTrace();
        startBlockProgramsTime = System.currentTimeMillis();
        this.blockPrograms = true;
        return blockProgramsSetCount.incrementAndGet();
    }

    public void stopBlockingPrograms(int count) throws ConcurrentBlockProgramsException {
        int c = blockProgramsSetCount.get();
        if (c != count) {
            throw new ConcurrentBlockProgramsException("wrong count " + count + "!= " + c);
        }
        this.blockPrograms = false;
        blockProgramsSetCount.incrementAndGet();
    }

    private volatile StackTraceElement @Nullable [] closeTestProgramRunProgramThreadTrace = null;
    private volatile StackTraceElement @Nullable [] closeTestProgramThreadTrace = null;
    @Nullable
    private volatile Thread closeTestProgramThreadThead = null;

    public void closeTestProgramThread() {
        if (!isRunningProgram()) {
            return;
        }
        closeTestProgramThreadThead = Thread.currentThread();
        closeTestProgramThreadTrace = Thread.currentThread().getStackTrace();
        Thread rpt = this.runProgramThread;
        if (null == rpt) {
            closeTestProgramRunProgramThreadTrace = null;
        } else {
            closeTestProgramRunProgramThreadTrace = rpt.getStackTrace();
        }
        if (null != runProgramFuture) {
            runProgramFuture.cancelAll(true);
        }
        if (isRunningProgram()) {
            if (null != runProgramThread) {
                System.err.println("closeTestProgramRunProgramThreadTrace = " + Arrays.toString(closeTestProgramRunProgramThreadTrace));
            }
            showErrorMessage("still running after cancel: runProgramFuture=" + runProgramFuture + ", runProgramThread=" + runProgramThread);
        }
    }

    public boolean isDone(long minCmdId) {
        CRCLStatusType stat = this.status;
        if (null != stat) {
            final CommandStatusType commandStatus = stat.getCommandStatus();
            if (null != commandStatus) {
                return commandStatus.getCommandID() == minCmdId
                        && commandStatus.getCommandState() == CommandStateEnumType.CRCL_DONE;
            }
        }
        return false;
    }

    public boolean isError(long minCmdId) {
        CRCLStatusType stat = this.status;
        if (null != stat) {
            final CommandStatusType commandStatus = stat.getCommandStatus();
            if (null != commandStatus) {
                return commandStatus.getCommandID() == minCmdId
                        && commandStatus.getCommandState() == CommandStateEnumType.CRCL_ERROR;
            }
        }
        return false;
    }

    private static String createAssertErrorString(CRCLCommandType cmd, long oldId, long newId) {
        return "command id being reduced from " + oldId + " to " + newId + ", cmd=" + CRCLSocket.cmdToString(cmd);
    }

    private void setCommandId(CRCLCommandType cmd, long id) {
        long commandID = cmd.getCommandID();
        assert commandID <= id :
                createAssertErrorString(cmd, commandID, id);
//        if (!(cmd instanceof GetStatusType)) {
//            if (Math.abs(id - cmd.getCommandID()) > 3 && id > 3 && cmd.getCommandID() > 3) {
//                showErrorMessage("Math.abs(id - cmd.getCommandID()) > 3 && id > 3, id=" + id + ",cmd.getCommandID()=" + cmd.getCommandID());
//            }
//        }
        if (debugConnectDisconnect || debugInterrupts) {
            if (!(cmd instanceof GetStatusType) && !(cmd instanceof StopMotionType) && !(cmd instanceof InitCanonType)) {
                if (id != commandID) {
                    System.out.println("setCommandId : id = " + id + ", cmd.getCommandID() = " + commandID);
                }
            }
        }
        cmd.setCommandID(id);
    }

    private void setCommandId(CRCLCommandType cmd) {
        setCommandId(cmd, commandId.incrementAndGet());
    }

    public boolean requestStatus() throws JAXBException {
        request_status_count++;
        LOGGER.log(Level.FINEST, () -> "PendantClientInner.requestStatus() : request_status_count=" + request_status_count);
        boolean result = false;
        setCommandId(getStatusMsg, commandId.get());
        result = this.sendCommand(getStatusMsg);
        LOGGER.log(Level.FINEST, () -> "PendantClientInner.requestStatus() : returning from RequestStatus() " + request_status_count);
        return result;
    }

    @Nullable
    private PrintStream logStream = null;

    @Nullable
    private File tempLogDir = null;

    /**
     * Get the value of tempLogDir
     *
     * @return the value of tempLogDir
     */
    @Nullable
    public File getTempLogDir() {
        return tempLogDir;
    }

    /**
     * Set the value of tempLogDir
     *
     * @param tempLogDir new value of tempLogDir
     */
    public void setTempLogDir(File tempLogDir) {
        this.tempLogDir = tempLogDir;
    }

    public void openLogStream() {
        try {
            if (null != tempLogDir) {
                logFile = File.createTempFile("crcl.client.", ".log.txt", tempLogDir);
            } else {
                logFile = File.createTempFile("crcl.client.", ".log.txt");
            }
            logStream = new PrintStream(new FileOutputStream(logFile));
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    @Nullable
    private volatile String crclClientErrorMessage = null;

    @Nullable
    public String getCrclClientErrorMessage() {
        return crclClientErrorMessage;
    }

    public void clearCrclClientErrorMessage() {
        crclClientErrorMessage = null;
    }

    private volatile boolean skipWrappedMessageCommands = true;

    /**
     * Get the value of skipWrappedMessageCommands
     *
     * @return the value of skipWrappedMessageCommands
     */
    public boolean isSkipWrappedMessageCommands() {
        return skipWrappedMessageCommands;
    }

    /**
     * Set the value of skipWrappedMessageCommands
     *
     * @param skipWrappedMessageCommands new value of skipWrappedMessageCommands
     */
    public void setSkipWrappedMessageCommands(boolean skipWrappedMessageCommands) {
        this.skipWrappedMessageCommands = skipWrappedMessageCommands;
    }

    public void showErrorMessage(String s) {
        System.err.println(s);
        if (!disconnecting && !aborting) {
            Thread.dumpStack();
        }
        crclClientErrorMessage = s;
        outer.showMessage(s);
        if (null == logStream) {
            openLogStream();
        }
        if (null != logStream) {
            logStream.println(s);
        }
        pause();
    }

    private void showMessage(String s) {
        outer.showMessage(s);
        if (null == logStream) {
            openLogStream();
        }
        if (null != logStream) {
            logStream.println(s);
        }
    }

    private void showDebugMessage(String s) {
        outer.showDebugMessage(s);
        if (null == logStream) {
            openLogStream();
        }
        if (null != logStream) {
            logStream.println("DEBUG :" + s);
        }
    }

    private void printDisconnectInfo() {
        long t = System.currentTimeMillis();
        System.out.println("connectThread = " + connectThread);
        System.out.println("connectTrace = " + Arrays.toString(connectTrace));
        System.out.println("connnectTime = " + (t - connnectTime));

        System.out.println("disconnectThread = " + disconnectThread);
        System.out.println("disconnectTrace = " + Arrays.toString(disconnectTrace));
        System.out.println("disconnnectTime = " + (t - disconnnectTime));
    }

    private void showMessage(Throwable t) {
        if (t instanceof CRCLException) {
            Throwable tc = t.getCause();
            if (tc instanceof SocketException) {
                printDisconnectInfo();
            }
        } else if (t instanceof SocketException) {
            printDisconnectInfo();
        }
        showErrorMessage(t.toString());
        outer.showMessage(t);
        if (null == logStream) {
            openLogStream();
        }
        if (null != logStream) {
            logStream.println(t);
        }
    }

    public XpathUtils getXpu() {
        return this.xpu;
    }

    public Function<CRCLProgramType, XFuture<Boolean>> getCheckProgramValidPredicate() {
        return checkProgramValidPredicate;
    }

    public Function<CRCLCommandType, XFuture<Boolean>> getCheckCommandValidPredicate() {
        return checkCommandValidPredicate;
    }

    public XFuture<Boolean> checkProgramValid(CRCLProgramType prog) {
        try {
            if (null == checkerCommandInstance) {
                checkerCommandInstance = new CRCLCommandInstanceType();
            }
            String s = getTempCRCLSocket().programToPrettyString(prog, true);
            return MultiLineStringJPanel.showText(s);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            showMessage(ex);
        }
        return XFuture.completedFuture(false);
    }

    public CRCLSocket getTempCRCLSocket() {
        if (null != checkerCRCLSocket) {
            return checkerCRCLSocket;
        }
        if (cmdSchema == null) {
            throw new IllegalStateException("cmdSchema==null");
        }
        if (statSchema == null) {
            throw new IllegalStateException("statSchema==null");
        }
        if (progSchema == null) {
            throw new IllegalStateException("progSchema==null");
        }
        return (checkerCRCLSocket = new CRCLSocket(cmdSchema, statSchema, progSchema));
    }

    public XFuture<Boolean> checkCommandValid(CRCLCommandType cmdObj) {
        try {
            if (null == checkerCommandInstance) {
                checkerCommandInstance = new CRCLCommandInstanceType();
            }
            checkerCommandInstance.setCRCLCommand(cmdObj);
            String s = getTempCRCLSocket().commandInstanceToPrettyString(checkerCommandInstance, true);
            if (cmdObj instanceof MoveThroughToType) {
                MoveThroughToType mtt = (MoveThroughToType) cmdObj;
                int num_positions = mtt.getNumPositions();
                if (num_positions < 2) {
                    throw new RuntimeException("MoveThroughToType : NumPositions must be at-least 2 but was " + num_positions);
                }
                int wpts_length = mtt.getWaypoint().size();
                if (wpts_length != num_positions) {
                    throw new RuntimeException("MoveThroughToType : NumPositions must equal number of waypoints but NumPostions=" + num_positions
                            + " but number of waypoints = " + wpts_length);
                }
            }
            return MultiLineStringJPanel.showText(s);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            showMessage(ex);
        }
        return XFuture.completedFuture(false);
    }

    @MonotonicNonNull
    private volatile Schema statSchema = null;

    final synchronized void setStatSchema(File[] fa) {
        try {
            statSchema = CRCLSocket.filesToStatSchema(fa);
            this.statSchemaFiles = fa;
            if (null != this.crclSocket) {
                this.crclSocket.setStatSchema(statSchema);
            }
        } catch (CRCLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    @MonotonicNonNull
    private volatile Schema cmdSchema = null;

    final void setCmdSchema(File[] fa) {
        try {
            cmdSchema = CRCLSocket.filesToCmdSchema(fa);
            this.cmdSchemaFiles = fa;
            if (null != this.crclSocket) {
                this.crclSocket.setCmdSchema(cmdSchema);
            }
        } catch (CRCLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    @MonotonicNonNull
    private volatile Schema progSchema = null;

    final synchronized void setProgramSchema(File[] fa) {
        try {
            if (fa.length < 1) {

            }
            progSchema = CRCLSocket.filesToProgramSchema(fa);
            this.progSchemaFiles = fa;
            if (null != this.crclSocket) {
                this.crclSocket.setProgramSchema(progSchema);
            }
        } catch (CRCLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    @MonotonicNonNull
    private File logFile = null;

    public void openLogFile() {
        if (null != logStream) {
            try {
                logStream.flush();
                logStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logStream = null;
        }
        try {
            if (null != logFile) {
                Desktop.getDesktop().open(logFile);
            }
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    private boolean validateXmlSchema = true;

    public boolean isValidateXmlSchema() {
        return validateXmlSchema;
    }

    public void setValidateXmlSchema(boolean validateXmlSchema) {
        this.validateXmlSchema = validateXmlSchema;
    }

    public void openXmlProgramFile(File f, boolean addRecent) {

        try {
            if (null != logStream) {
                try {
                    logStream.flush();
                    logStream.close();
                } catch (Exception e) {
                    LOGGER.log(Level.FINEST, "", e);
                }
                logStream = null;
            }
            String s = this.xpu.queryXml(f, "/");
            CRCLSocket cs = getTempCRCLSocket();
            CRCLProgramType programFromFile
                    = cs.stringToProgram(s, isValidateXmlSchema());
            final String programFromFileName = programFromFile.getName();
            if (null == programFromFileName || programFromFileName.length() < 1) {
                String fname = f.getName();
                if (fname.endsWith(".xml")) {
                    fname = fname.substring(0, fname.length() - 4);
                }
                programFromFile.setName(fname);
            }
            this.setProgram(programFromFile);
            if (null != tempLogDir) {
                logFile = File.createTempFile("crcl.client." + f.getName() + ".", ".log.txt", tempLogDir);
            } else {
                logFile = File.createTempFile("crcl.client." + f.getName() + ".", ".log.txt");
            }
            logStream = new PrintStream(new FileOutputStream(logFile));
            outer.showDebugMessage("Logging to " + logFile.getCanonicalPath());
            outer.finishOpenXmlProgramFile(f, programFromFile, addRecent);
            setOutgoingProgramFile(f.getName());
        } catch (SAXException | IOException | XPathExpressionException | ParserConfigurationException | CRCLException exception) {
            throw new RuntimeException("Failed to openProgramFile(" + f + "," + addRecent + ") : " + exception.getMessage(), exception);
        }

    }

    public void saveXmlProgramFile(File f) throws CRCLException {
        CRCLSocket cs = getTempCRCLSocket();
        CRCLProgramType programToSave = this.program;
        if (null == programToSave) {
            throw new IllegalStateException("null == program");
        }
        final String origProgramName = programToSave.getName();
        if (null == origProgramName || origProgramName.length() < 1) {
            String fname = f.getName();
            if (fname.endsWith(".xml")) {
                fname = fname.substring(0, fname.length() - 4);
            }
            programToSave.setName(fname);
        }
        String str
                = cs.programToPrettyString(programToSave, validateXmlSchema);
        try (PrintWriter pw = new PrintWriter(new FileWriter(f))) {
            pw.println(str);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            showMessage(ex);
        }
    }

    /**
     * Set the value of recordCommands
     *
     * @param recordCommands new value of recordCommands
     */
    public void setRecordCommands(boolean recordCommands) {
        this.recordCommands = recordCommands;
    }

    public List<CRCLCommandType> getRecordedCommandsList() {
        synchronized (recordedCommandsList) {
            CRCLCommandType cmd = null;
            while (null != (cmd = recordedCommandsQueue.poll())) {
                recordedCommandsList.add(cmd);
            }
        }
        return Collections.unmodifiableList(recordedCommandsList);
    }

    private long lastCommandIdSent = -999;

    private volatile StackTraceElement lastCommandSentStackTrace @Nullable []  = null;
    private volatile StackTraceElement prevLastCommandSentStackTrace @Nullable []  = null;

    @Nullable
    public CRCLCommandType getPrevLastCommandSent() {
        return prevLastCommandSent;
    }

    public long getLastCommandIdSent() {
        return lastCommandIdSent;
    }

    public StackTraceElement @Nullable [] getLastCommandSentStackTrace() {
        return lastCommandSentStackTrace;
    }

    public StackTraceElement @Nullable [] getPrevLastCommandSentStackTrace() {
        return prevLastCommandSentStackTrace;
    }

    private volatile boolean lastCmdTriedWasStop = false;

    private volatile int initCount = 0;
//    private final AtomicReference<StackTraceElement @Nullable[]> initTrace0 = new AtomicReference<>();
//    private final AtomicReference<StackTraceElement @Nullable[]> initTrace1 = new AtomicReference<>();
//    private final AtomicReference<StackTraceElement @Nullable[]> initTrace2 = new AtomicReference<>();
//    private final AtomicReference<StackTraceElement @Nullable[]> initTrace3 = new AtomicReference<>();

    private boolean sendCommandPrivate(CRCLCommandType cmd) {
        try {
            CRCLSocket crclSocketForSend = this.crclSocket;
            if (null == crclSocketForSend) {
                showMessage("Can not send command when not connected.");
                return false;
            }
            if (cmd instanceof InitCanonType) {
                initCount++;
            }
            if (cmd instanceof MoveToType) {
                initCount = 0;
            }
            if (cmd instanceof CrclCommandWrapper) {
                if (((CrclCommandWrapper) cmd).getWrappedCommand().getCommandID() != cmd.getCommandID()) {
                    throw new IllegalArgumentException("((CrclCommandWrapper) cmd).getWrappedCommand().getCommandID() " + ((CrclCommandWrapper) cmd).getWrappedCommand().getCommandID() + " != cmd.getCommandID() " + cmd.getCommandID());
                }
            }
            lastCmdTriedWasStop = (cmd instanceof StopMotionType);
            CRCLCommandInstanceType cmdInstance
                    = new CRCLCommandInstanceType();
            cmdInstance.setCRCLCommand(cmd);
            if (!(cmdInstance.getCRCLCommand() instanceof GetStatusType)) {
                prevLastCommandSent = lastCommandSent;
                prevLastCommandSentStackTrace = lastCommandSentStackTrace;
                lastCommandSent = cmdInstance.getCRCLCommand();
                lastCommandSentStackTrace = Thread.currentThread().getStackTrace();
                int currentRecordedCommandsQueueSize = recordedCommandsQueue.size();
                for (int i = maxRecordCommandsCount; i < currentRecordedCommandsQueueSize; i++) {
                    if (null == recordedCommandsQueue.poll()) {
                        break;
                    }
                }
                if (recordCommands) {
                    recordedCommandsQueue.add(cmd);
                }
                if (null != outgoingProgramFile) {
                    cmdInstance.setProgramFile(outgoingProgramFile);
                }
                if (null != outgoingProgramIndex) {
                    cmdInstance.setProgramIndex(outgoingProgramIndex);
                }
                if (null != outgoingProgramLength) {
                    cmdInstance.setProgramLength(outgoingProgramLength);
                }
            }
            final long id = cmd.getCommandID();
            if (!(cmd instanceof GetStatusType)) {
                if (cmd instanceof MoveToType) {
                    final MoveToType moveToCmd = (MoveToType) cmd;
                    final PoseType endPosition = requireNonNull(moveToCmd.getEndPosition(), "moveToCmd.getEndPosition()");
                    final PointType endPositionPoint = requireNonNull(endPosition.getPoint(), "endPosition.getPoint()");
                    lastMoveToCmdPoint = CRCLPosemath.copy(endPositionPoint);
                }
                CommandLogElement cmdLogEl = new CommandLogElement(cmd, System.currentTimeMillis(), programName, programIndex, crclSocketForSend.toString());
                lastCommandStatusLogElement = cmdLogEl;
                commandStatusLog.add(cmdLogEl);
            }
            while (commandStatusLog.size() > maxLogSize) {
                commandStatusLog.pollFirst();
            }
            crclSocketForSend.writeCommand(cmdInstance, validateXmlSchema);
            if (!(cmd instanceof StopMotionType) && !(cmd instanceof InitCanonType)) {
                if (id != cmd.getCommandID()) {
                    printIncCommandInfo(System.err);
                    throw new IllegalStateException("id(" + id + ") != cmd.getCommandID() " + cmd.getCommandID());
                }
                final CRCLCommandType crclInstanceCommand = requireNonNull(cmdInstance.getCRCLCommand(), "cmdInstance.getCRCLCommand()");
                if (id != crclInstanceCommand.getCommandID()) {
                    printIncCommandInfo(System.err);
                    throw new IllegalStateException("id(" + id + ") != cmdInstance.getCRCLCommand().getCommandID() " + crclInstanceCommand.getCommandID());
                }
            }
            lastCommandIdSent = id;
            return true;
        } catch (Exception ex) {
            if (!disconnecting) {
                LOGGER.log(Level.SEVERE, null, ex);
                if (!(cmd instanceof GetStatusType)) {
                    showMessage(ex);
                    showErrorMessage(ex.toString());
                }
                if (null != ex.getCause() && ex.getCause() instanceof SocketException) {
                    disconnect();
                }
                if (ex instanceof RuntimeException) {
                    throw (RuntimeException) ex;
                } else {
                    throw new RuntimeException(ex);
                }
            }
        }
        return false;
    }

    private void printIncCommandInfo(PrintStream ps) {
        ps.println("secondLastIncCommandThread = " + secondLastIncCommandThread);
        ps.println("secondLastIncCommandThreadStackTrace = " + Arrays.toString(secondLastIncCommandThreadStackTrace));
        ps.println("lastIncCommandThreadStackId = " + lastIncCommandThreadStackId);
        ps.println("lastIncCommandThreadStackTime = " + lastIncCommandThreadStackTime);
        ps.println("secondLastIncCommandThreadStackTrace = " + Arrays.toString(secondLastIncCommandThreadStackTrace));
        ps.println("lastIncCommandThread = " + lastIncCommandThread);
        ps.println("lastIncCommandThreadStackTrace = " + Arrays.toString(lastIncCommandThreadStackTrace));
    }

    private final AtomicLong commandId = new AtomicLong(3);

    @Nullable
    private volatile Thread lastIncCommandThread = null;
    private volatile StackTraceElement lastIncCommandThreadStackTrace @Nullable []  = null;
    private volatile long lastIncCommandThreadStackId;
    private volatile long lastIncCommandThreadStackTime;
    @Nullable
    private volatile Thread secondLastIncCommandThread = null;
    private volatile StackTraceElement secondLastIncCommandThreadStackTrace @Nullable []  = null;
    private volatile long secondLastIncCommandThreadStackId;
    private volatile long secondLastIncCommandThreadStackTime;

    public void clearLastIncCommandThread() {
        lastIncCommandThread = null;
    }

    private void incCommandID(CRCLCommandType cmd) {
        long id;
        Thread incCommandThread = Thread.currentThread();
        StackTraceElement ste[] = incCommandThread.getStackTrace();
        long incCommandTime = System.currentTimeMillis();
        if (null != lastIncCommandThread && incCommandThread != lastIncCommandThread && lastIncCommandThread.isAlive()) {
            System.err.println("Commands being incremented by two threads : incCommandTread = " + incCommandThread + ", lastIncCommandThred=" + lastIncCommandThread);
        }
        secondLastIncCommandThread = lastIncCommandThread;
        secondLastIncCommandThreadStackTrace = lastIncCommandThreadStackTrace;
        lastIncCommandThread = incCommandThread;
        lastIncCommandThreadStackTrace = ste;
        synchronized (cmd) {
            id = commandId.incrementAndGet();
            setCommandId(cmd, id);
            if (cmd instanceof CrclCommandWrapper) {
                setCommandId(((CrclCommandWrapper) cmd).getWrappedCommand(), id);
            }
        }
        long sid = statusCommandId();
        if (sid <= 1 && id <= 1 && null != status) {
            final CommandStatusType commandStatus = status.getCommandStatus();
            if (null != commandStatus) {
                if (commandStatus.getCommandState() == CommandStateEnumType.CRCL_DONE) {
                    commandStatus.setCommandState(CommandStateEnumType.CRCL_READY);
                }
                commandStatus.setCommandID(System.currentTimeMillis());
            }
        }
        secondLastIncCommandThreadStackId = lastIncCommandThreadStackId;
        secondLastIncCommandThreadStackTime = lastIncCommandThreadStackTime;
        lastIncCommandThreadStackId = id;
        lastIncCommandThreadStackTime = incCommandTime;
    }

    public long statusCommandId() {
        CRCLStatusType s = getStatus();
        if (null != s) {
            CommandStatusType cs = s.getCommandStatus();
            if (null != cs) {
                return cs.getCommandID();
            } else {
                throw new IllegalStateException("getStatus().getCommandStatus() returned null");
            }
        } else {
            throw new IllegalStateException("getStatus() returned null");
        }
    }

    public String commandToSimpleString(@Nullable CRCLCommandType cmd) {
        if (null != cmd) {
            try {
                return CRCLSocket.commandToSimpleString(cmd);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, null, ex);
                return ex.toString();
            }
        } else {
            return "null";
        }
    }

    public boolean incAndSendCommand(CRCLCommandType cmd) throws JAXBException {
        this.incCommandID(cmd);
        long sid = statusCommandId();
        if (cmd.getCommandID() == sid) {
            String cmdString = commandToSimpleString(cmd);
            String lastCommandString = commandToSimpleString(lastCommandSent);
            throw new IllegalStateException("unsent command with id " + cmd.getCommandID()
                    + " already matches  status command id  " + sid + " , cmd="
                    + cmdString + ", lastCommandSent=" + lastCommandString);
        }
        if (cmd instanceof CrclCommandWrapper) {
            if (((CrclCommandWrapper) cmd).getWrappedCommand().getCommandID() == sid) {
                String cmdString = commandToSimpleString(cmd);
                String lastCommandString = commandToSimpleString(lastCommandSent);
                throw new IllegalStateException("unsent wrapped command with id " + ((CrclCommandWrapper) cmd).getWrappedCommand().getCommandID()
                        + " already matches  status command id  " + sid + " , cmd="
                        + cmdString + ", lastCommandSent=" + lastCommandString);
            }
        }
        return sendCommand(cmd);
    }

    public boolean sendCommand(CRCLCommandType cmd) throws JAXBException {
        if (null == cmd) {
            throw new IllegalArgumentException("cmd can not be null");
        }
        if (null == this.crclSocket) {
            throw new IllegalStateException("crclSocket must not be null.");
        }
        if (cmd instanceof CrclCommandWrapper) {
            CrclCommandWrapper wrapped = (CrclCommandWrapper) cmd;
            wrapped.notifyOnStartListeners();
            cmd = wrapped.getWrappedCommand();
        }
        if (!(cmd instanceof GetStatusType) && menuOuter().isDebugSendCommandSelected()) {
            showDebugMessage("Debugging Send Command : cmd = " + cmdString(cmd));
        }
        if (cmd instanceof InitCanonType) {
            initSent = true;
        } else if (cmd instanceof SetAngleUnitsType) {
            SetAngleUnitsType setAngle = (SetAngleUnitsType) cmd;
            this.setAngleType(setAngle.getUnitName());
        } else if (cmd instanceof SetLengthUnitsType) {
            SetLengthUnitsType setLengthUnit = (SetLengthUnitsType) cmd;
            this.setLengthUnit(setLengthUnit.getUnitName());
            this.lengthUnitSent = true;
        } else if (cmd instanceof SetEndPoseToleranceType) {
            SetEndPoseToleranceType endPoseTol = (SetEndPoseToleranceType) cmd;
            this.setExpectedEndPoseTolerance(endPoseTol.getTolerance());
        } else if (cmd instanceof SetIntermediatePoseToleranceType) {
            SetIntermediatePoseToleranceType intermediatePoseTol = (SetIntermediatePoseToleranceType) cmd;
            this.setExpectedIntermediatePoseTolerance(intermediatePoseTol.getTolerance());
        } else if (cmd instanceof ConfigureJointReportsType) {
            ConfigureJointReportsType cjrs = (ConfigureJointReportsType) cmd;
            for (ConfigureJointReportType cjr : cjrs.getConfigureJointReport()) {
                cjrMap.put(cjr.getJointNumber(), cjr);
            }
        } else if (cmd instanceof SetEndEffectorType) {
            SetEndEffectorType seeCmd = (SetEndEffectorType) cmd;
            this.setHoldingObjectExpected(seeCmd.getSetting() < 0.5);
            holdingErrorOccured = false;
        } else if (cmd instanceof MoveToType) {
            MoveToType moveToCmd = (MoveToType) cmd;
            PointType pt = moveToCmd.getEndPosition().getPoint();
            if (pt.getX() > maxLimit.x) {
                setCommandState(CRCL_ERROR);
                throw new IllegalArgumentException("MoveToCmd : " + CRCLSocket.cmdToString(cmd) + " exceeds maxLimit.x=" + maxLimit.x);
            }
            if (pt.getY() > maxLimit.y) {
                setCommandState(CRCL_ERROR);
                throw new IllegalArgumentException("MoveToCmd : " + CRCLSocket.cmdToString(cmd) + " exceeds maxLimit.y=" + maxLimit.y);
            }
            if (pt.getZ() > maxLimit.z) {
                setCommandState(CRCL_ERROR);
                throw new IllegalArgumentException("MoveToCmd : " + CRCLSocket.cmdToString(cmd) + " exceeds maxLimit.z=" + maxLimit.z);
            }
            if (pt.getX() < minLimit.x) {
                setCommandState(CRCL_ERROR);
                throw new IllegalArgumentException("MoveToCmd : " + CRCLSocket.cmdToString(cmd) + " exceeds minLimit.x=" + minLimit.x);
            }
            if (pt.getY() < minLimit.y) {
                setCommandState(CRCL_ERROR);
                throw new IllegalArgumentException("MoveToCmd : " + CRCLSocket.cmdToString(cmd) + " exceeds minLimit.y=" + minLimit.y);
            }
            if (pt.getZ() < minLimit.z) {
                setCommandState(CRCL_ERROR);
                throw new IllegalArgumentException("MoveToCmd : " + CRCLSocket.cmdToString(cmd) + " exceeds minLimit.z=" + minLimit.z);
            }
        }
        boolean ret = this.sendCommandPrivate(cmd);

        if (!(cmd instanceof GetStatusType)) {
            if (menuOuter().isDebugSendCommandSelected()) {
                showDebugMessage("PendantClientInner.sendCommand() : ret = " + ret);
            }
        } else if (getCommandState() == CommandStateEnumType.CRCL_DONE) {
            setCommandState(CommandStateEnumType.CRCL_WORKING);
        }
        return ret;
    }

    public void setCommandState(CommandStateEnumType state) {
        if (null != status) {
            CommandStatusType commandStatus = status.getCommandStatus();
            if (null != commandStatus) {
                commandStatus.setCommandState(state);
            } else {
                commandStatus = new CommandStatusType();
                commandStatus.setCommandID(1);
                commandStatus.setCommandState(state);
            }
        }
    }

    private volatile boolean aborting = false;

    public void abort() {
        try {
            aborting = true;
            this.programName = null;
            this.programIndex = -1;
            runProgramAbortCount.incrementAndGet();
            boolean wasPaused = isPaused();
            boolean wasRunning = isRunningProgram();
            if (wasPaused && wasRunning) {
                System.out.println("wasPaused = " + wasPaused);
                System.err.println("Unpausing to complete abort.");
                unpause();
                runProgramAbortCount.incrementAndGet();
                Thread.sleep(50);
                runProgramAbortCount.incrementAndGet();
                pause();
                runProgramAbortCount.incrementAndGet();
            }
            this.closeTestProgramThread();
            stopMotion(StopConditionEnumType.FAST);
        } catch (JAXBException | InterruptedException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        aborting = false;
    }

    private volatile boolean initSent = false;

    public boolean isInitSent() {
        return initSent;
    }

    /**
     * Send a new command to stop motion.
     *
     * @param stopType the value of stop condition
     * @throws javax.xml.bind.JAXBException when xml can not be generated.
     */
    public void stopMotion(StopConditionEnumType stopType) throws JAXBException {

        StopMotionType stop = new StopMotionType();
        stop.setStopCondition(stopType);
        stop.setCommandID(Math.max(1, commandId.get() - 1));
        programName = null;
        programIndex = -1;
        this.sendCommand(stop);
    }

    public long resendInit() throws JAXBException, InterruptedException {
        InitCanonType init = new InitCanonType();
        init.setCommandID(Math.max(1, commandId.get() - 3));
        this.sendCommand(init);
        waitForDone(init.getCommandID(), 2000, this.pause_count.get());
        return init.getCommandID();
    }

    public boolean waitForStatus(long timeoutMilliSeconds, long delay, int starting_pause_count, int startRunProgramAbortCount) throws InterruptedException, JAXBException {
        long start = System.currentTimeMillis();
        while (null == this.getStatus() && !Thread.currentThread().isInterrupted()) {
            if (startRunProgramAbortCount >= 0 && runProgramAbortCount.get() != startRunProgramAbortCount) {
                return false;
            }
            if (timeoutMilliSeconds >= 0
                    && System.currentTimeMillis() - start > timeoutMilliSeconds) {
                return false;
            }
            if (delay > 0) {
                Thread.sleep(delay);
            }
            synchronized (this) {
                if (!requestStatus()) {
                    return false;
                }
                if (null == readerThread) {
                    readStatus();
                }
            }
            if (this.pause_count.get() != starting_pause_count || this.paused) {
                return false;
            }
        }
        return true;
    }

    private long lastWaitForDoneTimeDiff = -1;
    private long lastWaitForDoneFullTimeout = -1;
    private long lastWaitForDoneMinCmdId = -1;
    @Nullable
    private Exception lastWaitForDoneException = null;

    private static final long WAIT_FOR_DONE_TIMEOUT_EXTENSION
            = Long.parseLong(System.getProperty("crcl.client.wait_for_done_timeout_extension", "5000"));

    /**
     * Poll the status until the current command is done or ends with an error.
     *
     * @param minCmdId the value of minCmdId
     * @param timeoutMilliSeconds the value of timeoutMilliSeconds
     * @param pause_count_start initial value of pause_count used for comparison
     * to detect pauses
     * @return the boolean
     * @throws InterruptedException when Thread interrupted
     * @throws javax.xml.bind.JAXBException when there is a failure creating the
     * XML
     */
    public WaitForDoneResult waitForDone(final long minCmdId, final long timeoutMilliSeconds, final int pause_count_start)
            throws InterruptedException, JAXBException {

        try {
            if (menuOuter().isDebugWaitForDoneSelected()) {
                showDebugStatus();
            }
            long start = System.currentTimeMillis();
            long timeDiff = System.currentTimeMillis() - start;
            lastWaitForDoneTimeDiff = timeDiff;
            final long fullTimeout = timeoutMilliSeconds
                    + ((waitForDoneDelay > 0) ? 2 * waitForDoneDelay : 0)
                    + WAIT_FOR_DONE_TIMEOUT_EXTENSION;
            lastWaitForDoneFullTimeout = fullTimeout;
            lastWaitForDoneMinCmdId = minCmdId;
            while (true) {
                if (isDone(minCmdId)) {
                    return WaitForDoneResult.WFD_DONE;
                }
                if (isError(minCmdId)) {
                    return WaitForDoneResult.WFD_ERROR;
                }
                if (holdingErrorOccured) {
                    return WaitForDoneResult.WFD_HOLDING_ERROR;
                }
                if (Thread.currentThread().isInterrupted()) {
                    if (debugInterrupts) {
                        System.out.println("Current Thread is interrupted : " + Thread.currentThread());
                        Thread.dumpStack();
                        System.out.println("interruptStacks = " + interruptStacks);
                    }
                    return WaitForDoneResult.WFD_INTERRUPTED;
                }
                if (menuOuter().isDebugWaitForDoneSelected()) {
                    showDebugStatus();
                    showDebugMessage("PendantClient waitForDone(" + minCmdId + ") timeDiff = " + timeDiff + " / " + timeoutMilliSeconds + " = " + ((double) timeDiff) / timeoutMilliSeconds);
                }
                if (waitForDoneDelay > 0) {
                    Thread.sleep(waitForDoneDelay);
                }
                if (!isConnected()) {
                    return WaitForDoneResult.WFD_SOCKET_DISCONNECTED;
                }
                synchronized (this) {
                    if (!requestStatus()) {
                        return WaitForDoneResult.WFD_REQUEST_STATUS_FAILED;
                    }
                    if (null == readerThread) {
                        readStatus();
                    }
                }
                if (this.pause_count.get() != pause_count_start || this.paused) {
                    return WaitForDoneResult.WFD_PAUSED;
                }
                timeDiff = System.currentTimeMillis() - start;
                lastWaitForDoneTimeDiff = timeDiff;
                if (timeDiff > fullTimeout && !ignoreTimeouts) {
                    if (isDone(minCmdId)) {
                        return WaitForDoneResult.WFD_DONE;
                    }
                    if (isError(minCmdId)) {
                        return WaitForDoneResult.WFD_ERROR;
                    }
                    return WaitForDoneResult.WFD_TIMEOUT;
                }
            }
        } catch (InterruptedException interruptedException) {
            System.out.println("Current Thread is interrupted : " + Thread.currentThread());
            interruptedException.printStackTrace();
            System.out.println("interruptStacks = " + interruptStacks);
            return WaitForDoneResult.WFD_INTERRUPTED;
        } catch (Exception ex) {
            // Ugly hack hoping to catch strange debugging problem.
            LOGGER.log(Level.SEVERE, null, ex);
            lastWaitForDoneException = ex;
            return WaitForDoneResult.WFD_EXCEPTION;
        }
    }

    public long getWaitForDoneDelay() {
        return waitForDoneDelay;
    }

    public void setWaitForDoneDelay(long waitForDoneDelay) {
        this.waitForDoneDelay = waitForDoneDelay;
    }

    private void showDebugStatus() {
        if (null == this.status) {
            showDebugMessage("PendantClient this.status == null");
        } else if (null == this.status.getCommandStatus()) {
            showDebugMessage("PendantClient this.status.getCommandStatus() == null");
        } else {
            showDebugMessage("PendantClient this.status.getCommandStatus().getCommandID() ="
                    + this.status.getCommandStatus().getCommandID());
            showDebugMessage("PendantClient this.status.getCommandStatus().getCommandState() ="
                    + this.status.getCommandStatus().getCommandState());
        }
    }

    /**
     * Set the value of status
     *
     * @param status new value of status
     */
    public void setStatus(CRCLStatusType status) {
        this.status = status;
//        if (null != status.getCommandStatus()) {
//            String desc = status.getCommandStatus().getStateDescription();
//            if (null != desc && desc.length() > 0) {
//                if (!desc.equals(lastDescription)) {
//                    showMessage(desc);
//                }
//                lastDescription = desc;
//            }
//        }
        outer.finishSetStatus();
    }

    @Nullable
    public CRCLStatusType getStatus() {
        return this.status;
    }

    @Nullable
    public CommandStatusType getCommandStatus() {
        CRCLStatusType s = this.getStatus();
        if (null != s) {
            return s.getCommandStatus();
        }
        return null;
    }

    public CommandStateEnumType getCommandState() {
        CommandStatusType cs = getCommandStatus();
        if (null != cs) {
            return cs.getCommandState();
        }
        return CommandStateEnumType.CRCL_ERROR;
    }

    public PoseType currentStatusPose() {
        if (status == null) {
            throw new IllegalStateException("status==null");
        }
        return requireNonNull(CRCLPosemath.getPose(status), "CRCLPosemath.getPose(status)");
    }

    public PointType currentStatusPoint() {
        if (status == null) {
            throw new IllegalStateException("status==null");
        }
        return requireNonNull(CRCLPosemath.getPoint(status), "CRCLPosemath.getPoint(status)");
    }

    private int maxPoseListLength = 1000;

    /**
     * Get the value of maxPoseListLength
     *
     * @return the value of maxPoseListLength
     */
    public int getMaxPoseListLength() {
        return maxPoseListLength;
    }

    /**
     * Set the value of maxPoseListLength
     *
     * @param maxPoseListLength new value of maxPoseListLength
     */
    public void setMaxPoseListLength(int maxPoseListLength) {
        this.maxPoseListLength = maxPoseListLength;
    }

    public List<AnnotatedPose> getPoseList() {
        if (null != poseQueue) {
            synchronized (poseList) {
                AnnotatedPose ap = null;
                while (null != (ap = poseQueue.poll())) {
                    if (poseList.size() > maxPoseListLength && maxPoseListLength > 0) {
                        poseList.remove(0);
                    }
                    poseList.add(ap);
                }
            }
        }
        return Collections.unmodifiableList(poseList);
    }

    @SuppressWarnings({"unchecked", "nullness"})
    public void savePoseListToCsvFile(String poseFileName) throws IOException, PmException {
        final List<AnnotatedPose> poselist = this.getPoseList();
        if (null == poseFileName
                || poselist == null
                || poselist.isEmpty()) {
            return;
        }
        List<JointStatusesType> jss
                = poselist
                        .stream()
                        .map((x) -> x.getStatus())
                        .filter((x) -> x != null)
                        .map((x) -> x.getJointStatuses())
                        .filter((x) -> x != null)
                        .collect(Collectors.toList());
        final Set<Integer> jointIds = new TreeSet<>();
        jss.stream()
                .flatMap((x) -> x.getJointStatus().stream())
                .forEach((x) -> jointIds.add(x.getJointNumber()));
        Optional<JointStatusesType> exampleJss = jss.stream().findAny();
        Optional<JointStatusType> exampleJs
                = exampleJss
                        .map((x) -> x.getJointStatus())
                        .map((x) -> x.stream().findAny())
                        .orElse(Optional.empty());
        final boolean havePos
                = exampleJs
                        .map((x) -> x.getJointPosition() != null)
                        .orElse(false);
        final boolean haveVel
                = exampleJs
                        .map((x) -> x.getJointVelocity() != null)
                        .orElse(false);
        final boolean haveForce
                = exampleJs
                        .map((x) -> x.getJointTorqueOrForce() != null)
                        .orElse(false);

        final PmRpy rpyZero = new PmRpy();
        try (PrintWriter pw = new PrintWriter(new FileWriter(poseFileName))) {
            String headers = "time,relTime,cmdIdFromStatus,lastSentCmdId,State,cmdName,x,y,z,roll,pitch,yaw,"
                    + (havePos ? jointIds.stream().map((x) -> "Joint" + x + "Pos").collect(Collectors.joining(",")) : "")
                    + (haveVel ? "," + jointIds.stream().map((x) -> "Joint" + x + "Vel").collect(Collectors.joining(",")) : "")
                    + (haveForce ? "," + jointIds.stream().map((x) -> "Joint" + x + "Force").collect(Collectors.joining(",")) : "");
            pw.println(headers);
            final long firstTime = poseList.get(0).getTime();

            poselist
                    .stream()
                    .map((pose) -> {
                        PmRpy rpy = tryGet(() -> Posemath.toRpy(pose.rot)).orElse(rpyZero);
                        Stream<?> stream = Stream.builder()
                                .add(pose.getTime())
                                .add(pose.getTime() - firstTime)
                                .add(pose.getStatus().getCommandStatus().getCommandID())
                                .add(pose.getLastCommandIdSent())
                                .add(pose.getStatus().getCommandStatus().getCommandState())
                                .add(pose.getCommandName())
                                .add(pose.tran.x)
                                .add(pose.tran.y)
                                .add(pose.tran.z)
                                .add(Math.toDegrees(rpy.r))
                                .add(Math.toDegrees(rpy.p))
                                .add(Math.toDegrees(rpy.y))
                                .build();
                        if (havePos) {
                            stream = Stream.concat(stream, getJointValues(pose.getStatus(), jointIds)
                                    .map((x) -> x.getJointPosition())
                            );
                        }
                        if (haveVel) {
                            stream = Stream.concat(stream, getJointValues(pose.getStatus(), jointIds)
                                    .map((x) -> x.getJointVelocity())
                            );
                        }
                        if (haveForce) {
                            stream = Stream.concat(stream, getJointValues(pose.getStatus(), jointIds)
                                    .map((x) -> x.getJointTorqueOrForce())
                            );
                        }
                        return stream
                                .map((x) -> x.toString())
                                .collect(Collectors.joining(","));
                    })
                    .forEach((s) -> pw.println(s));
        }
    }

    private boolean holdingErrorOccured;

    /**
     * Get the value of holdingErrorOccured
     *
     * @return the value of holdingErrorOccured
     */
    public boolean isHoldingErrorOccured() {
        return holdingErrorOccured;
    }

    /**
     * Set the value of holdingErrorOccured
     *
     * @param holdingErrorOccured new value of holdingErrorOccured
     */
    public void setHoldingErrorOccured(boolean holdingErrorOccured) {
        this.holdingErrorOccured = holdingErrorOccured;
    }

    private int holdingErrorRepCount = 0;

    private volatile long lastLogCmdTime = System.currentTimeMillis();
    @MonotonicNonNull
    private volatile PointType lastLogMoveToCmdPoint = null;
    @MonotonicNonNull
    private volatile PointType lastLogStatusPoint = null;

    private double distFromLastLogMoveToCmdPoint() {
        if (null == lastLogMoveToCmdPoint) {
            return Double.NaN;
        }
        if (null == lastLogStatusPoint) {
            return Double.NaN;
        }
        return CRCLPosemath.diffPoints(lastLogMoveToCmdPoint, lastLogStatusPoint);
    }

    @Nullable
    private volatile PointType lastMoveToCmdPoint = null;

    @Nullable
    private Double distFromLastMoveToCmdPoint(CRCLStatusType status) {
        if (null == lastLogMoveToCmdPoint) {
            return null;
        }
        PointType pt = CRCLPosemath.getPoint(status);
        if (null == pt) {
            return null;
        }
        return CRCLPosemath.diffPoints(lastLogMoveToCmdPoint, pt);
    }

    private volatile double lastLogElementToArrayX = Double.NaN;
    private volatile double lastLogElementToArrayY = Double.NaN;
    private volatile double lastLogElementToArrayZ = Double.NaN;

    private static String fmtDouble(double d) {
        return String.format("%.3f", d);
    }

    @SuppressWarnings("nullness")
    public Object @Nullable [] logElementToArray(CommandStatusLogElement el) {
        if (null == el) {
            return null;
        }
        if (el instanceof CommandLogElement) {
            CommandLogElement cel = (CommandLogElement) el;
            CRCLCommandType cmd = cel.getCmd();
            if (cmd instanceof GetStatusType) {
                return null;
            }
            lastLogCmdTime = cel.getTime();
            if (cmd instanceof MoveToType) {
                MoveToType moveTo = (MoveToType) cmd;
                final PoseType endPosition = requireNonNull(moveTo.getEndPosition(), "moveTo.getEndPosition()");
                PointType point = requireNonNull(endPosition.getPoint(), "endPosition.getPoint()");
                lastLogMoveToCmdPoint = point;
                return new Object[]{
                    getTimeString(cel.getTime()),
                    true,
                    fmtDouble(cel.getTime() - lastLogCmdTime),
                    cel.getId(),
                    fmtDouble(distFromLastLogMoveToCmdPoint()),
                    null,
                    cel.getTime(),
                    cel.getProgName(),
                    cel.getProgIndex(),
                    cel.getSvrSocket(),
                    cmd.getName(),
                    fmtDouble(lastLogStatusPoint.getX()),
                    fmtDouble(lastLogStatusPoint.getY()),
                    fmtDouble(lastLogStatusPoint.getZ()),
                    CRCLSocket.cmdToString(cmd)
                };
            } else {
                return new Object[]{
                    getTimeString(cel.getTime()),
                    true,
                    fmtDouble(cel.getTime() - lastLogCmdTime),
                    cel.getId(),
                    fmtDouble(distFromLastLogMoveToCmdPoint()),
                    null,
                    cel.getTime(),
                    cel.getProgName(),
                    cel.getProgIndex(),
                    cel.getSvrSocket(),
                    cmd.getName(),
                    fmtDouble(lastLogStatusPoint.getX()),
                    fmtDouble(lastLogStatusPoint.getY()),
                    fmtDouble(lastLogStatusPoint.getZ()),
                    CRCLSocket.cmdToString(cmd)
                };
            }
        } else if (el instanceof StatusLogElement) {
            StatusLogElement sel = (StatusLogElement) el;
            CRCLStatusType status = sel.getStatus();
            PointType point = CRCLPosemath.getPoint(status);
            lastLogStatusPoint = point;
            return new Object[]{
                getTimeString(sel.getTime()),
                false,
                fmtDouble(sel.getTime() - lastLogCmdTime),
                sel.getId(),
                distFromLastLogMoveToCmdPoint(),
                status.getCommandStatus().getCommandState(),
                sel.getTime(),
                sel.getProgName(),
                sel.getProgIndex(),
                sel.getSvrSocket(),
                status.getName(),
                point.getX(),
                point.getY(),
                point.getZ(),
                status.getCommandStatus().getStateDescription()
            };
        } else {
            throw new IllegalStateException("log contains " + el);
        }
    }

    public void printCommandStatusLog() throws IOException {
        printCommandStatusLog(System.out, false);
    }

    @Nullable
    private volatile Appendable lastPrintCommandStatusAppendable = null;

    public void printCommandStatusLog(Appendable appendable, boolean clearLog) throws IOException {
        CSVPrinter printer = new CSVPrinter(appendable, CSVFormat.DEFAULT);
        if (!Objects.equals(lastPrintCommandStatusAppendable, appendable)) {
            printer.printRecord((Object[]) COMMAND_STATUS_LOG_HEADINGS);
            lastPrintCommandStatusAppendable = appendable;
        }
        printCommandStatusLogNoHeader(appendable, clearLog);
    }

    static final String[] COMMAND_STATUS_LOG_HEADINGS = new String[]{
        "Time", "Cmd?", "TimeDiff", "Command ID", "Distance", "State", "time_ms", "ProgramIndex", "X", "Y", "Z", "ProgramName", "Server", "Name", "Text"
    };

    @SuppressWarnings("rawtypes")
    static final Class[] COMMAND_STATUS_LOG_TYPES = new Class[]{
        String.class, Boolean.class, String.class, Long.class, String.class, Object.class, Long.class, Integer.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class,};

    public void printCommandStatusLogNoHeader(Appendable appendable, boolean clearLog) throws IOException {
        CSVPrinter printer = new CSVPrinter(appendable, CSVFormat.DEFAULT);
        if (clearLog) {
            CommandStatusLogElement el = commandStatusLog.pollFirst();
            while (el != null) {
                printLogElement(el, printer);
                el = commandStatusLog.pollFirst();
            }
        } else {
            for (CommandStatusLogElement el : commandStatusLog) {
                printLogElement(el, printer);
            }
        }

    }

    public void printCommandStatusLogNoHeader(File f, boolean append, boolean clearLog) throws IOException {
        try (CSVPrinter printer = new CSVPrinter(new PrintStream(new FileOutputStream(f, append)), CSVFormat.DEFAULT)) {
            if (clearLog) {
                CommandStatusLogElement el = commandStatusLog.pollFirst();
                while (el != null) {
                    printLogElement(el, printer);
                    el = commandStatusLog.pollFirst();
                }
            } else {
                for (CommandStatusLogElement el : commandStatusLog) {
                    printLogElement(el, printer);
                }
            }
        }
    }

    private void printLogElement(CommandStatusLogElement el, final CSVPrinter printer) throws IOException {
        Object array[] = logElementToArray(el);
        if (null != array) {
            printer.printRecord(array);
        }
    }

    public String commandStatusLogToString() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            printCommandStatusLog(pw, false);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return sw.toString();
    }

    private final ConcurrentLinkedDeque<CommandStatusLogElement> commandStatusLog
            = new ConcurrentLinkedDeque<>();

    private volatile int maxLogSize = 500;

    public int getMaxLogSize() {
        return maxLogSize;
    }

    public void setMaxLogSize(int maxLogSize) {
        this.maxLogSize = maxLogSize;
    }

    private volatile long lastReadStatusTime = -1;

    public boolean isUnpausing() {
        return unpausing;
    }

    boolean readStatus() {
        CRCLSocket readSocket = this.crclSocket;
        try {
            if (null == readSocket || null == menuOuter()) {
                return false;
            }
            if (menuOuter().replaceStateSelected()) {
                readSocket.setStatusStringInputFilter(CRCLSocket.addCRCLToState);
            } else {
                readSocket.setStatusStringInputFilter(x -> x);
            }
            final CRCLStatusType curStatus
                    = readSocket.readStatus(validateXmlSchema);
            if (curStatus == null) {
                return false;
            }
            lastReadStatusTime = System.currentTimeMillis();
            addStatusToCommandStatusLog(curStatus);
            outer.updateCommandStatusLog(commandStatusLog);
            if (menuOuter().isDebugReadStatusSelected()) {
                //outer.showDebugMessage("curStatus = "+curStatus);
                String statString = readSocket.getLastStatusString();
                outer.showDebugMessage("crclSocket.getLastStatusString() = " + statString);
                if (null == statString || statString.length() < 1) {
                    outer.showDebugMessage("crclSocket.statusToString(curStatus,false)="
                            + readSocket.statusToString(curStatus, false));
                }
            }
            if (outer.isMonitoringHoldingObject() && holdingObjectExpected) {
                GripperStatusType gripperStatus = curStatus.getGripperStatus();
                if (null == gripperStatus || null == gripperStatus.isHoldingObject() || !gripperStatus.isHoldingObject()) {
                    holdingErrorRepCount++;
                    if (holdingErrorRepCount > 25 && !holdingErrorOccured) {
                        outer.showMessage("Object dropped or missing?");
                        holdingErrorOccured = true;
                    }
                } else {
                    holdingErrorRepCount = 0;
                }
            }

            outer.checkXmlQuery(readSocket);
            if (menuOuter().isRecordPoseSelected()
                    && null != CRCLPosemath.getPose(curStatus)) {

                PmPose pmPose = CRCLPosemath.toPmPose(curStatus);
                final CommandStatusType commandStatus = curStatus.getCommandStatus();
                if (null != commandStatus) {
                    if (poseQueue.size() < 2 * maxPoseListLength + 100) {
                        CRCLStatusType curStatusCopy = CRCLPosemath.copy(curStatus);
                        if (null != curStatusCopy) {
                            AnnotatedPose annotatedPose
                                    = new AnnotatedPose(System.currentTimeMillis(),
                                            lastCommandIdSent,
                                            commandStatus.getCommandID() <= lastCommandIdSent ? cmdNameString(lastCommandSent) : cmdNameString(prevLastCommandSent),
                                            pmPose.tran, pmPose.rot, curStatusCopy);
                            poseQueue.add(annotatedPose);
                        }
                    }
                }
            }
            this.setStatus(curStatus);
        } catch (CRCLException crclException) {
            Throwable ex = crclException.getCause();
            if (ex instanceof IOException || ex instanceof IllegalStateException) {
                if (disconnecting || ex instanceof SocketException) {
                    return false;
                }
                LOGGER.log(Level.SEVERE, null, ex);
                this.showMessage(ex);
                java.awt.EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            PendantClientInner.this.disconnect();
                        } catch (Exception e) {
                            LOGGER.log(Level.FINEST, "", e);
                        }
                    }
                });

            } else if (ex instanceof JAXBException) {
                if (disconnecting) {
                    return false;
                }
                LOGGER.log(Level.SEVERE, null, ex);
                this.showMessage(ex.toString() + NEW_LINE + readSocket.getLastStatusString() + NEW_LINE);
                java.awt.EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            disconnect();
                        } catch (Exception e) {
                            LOGGER.log(Level.FINEST, "", e);
                        }
                    }
                });
            } else {
                if (disconnecting) {
                    return false;
                }
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    @Nullable
    private volatile CommandStatusLogElement lastCommandStatusLogElement = null;

    @Nullable
    private CommandStatusLogElement getLastCommandStatusLogElement() {
        CommandStatusLogElement lastEl = commandStatusLog.peekLast();
        if (lastEl == null) {
            return lastCommandStatusLogElement;
        }
        lastCommandStatusLogElement = lastEl;
        return lastEl;
    }

    public void addStatusToCommandStatusLog(final CRCLStatusType curStatus) {
        long curTime = System.currentTimeMillis();
        CommandStatusLogElement lastEl = getLastCommandStatusLogElement();
        CommandStatusType curCmdStatus = curStatus.getCommandStatus();
        CommandStateEnumType curState = curCmdStatus.getCommandState();
        double requiredTimeDiff = (curState == CRCL_WORKING) ? 500 : 20000;
        if (lastEl != null
                && lastEl.getId() == curStatus.getCommandStatus().getCommandID()
                && (lastEl instanceof StatusLogElement)
                && curTime - lastEl.getTime() < requiredTimeDiff) {
            StatusLogElement statLastEl = (StatusLogElement) lastEl;
            CommandStatusType lastCmdStatus = statLastEl.getStatus().getCommandStatus();
            CommandStateEnumType lastState = lastCmdStatus.getCommandState();

            if (curState == lastState
                    && Objects.equals(curCmdStatus.getStateDescription(),
                            lastCmdStatus.getStateDescription())) {
                Double curDist = distFromLastMoveToCmdPoint(curStatus);
                Double lastDist = distFromLastMoveToCmdPoint(statLastEl.getStatus());
                if (curDist == null && lastDist == null) {
                    // skipping add to status log of almost identical status
                    return;
                }

                if (curDist != null && lastDist != null && Math.abs(curDist - lastDist) < 0.01) {
                    // skipping add to status log of almost identical status
                    return;
                }
            }
        }
        StatusLogElement statEl = new StatusLogElement(
                curStatus,
                System.currentTimeMillis(),
                programName,
                programIndex,
                (null != crclSocket) ? crclSocket.toString() : "");
        lastCommandStatusLogElement = statEl;
        commandStatusLog.add(statEl);
        while (commandStatusLog.size() > maxLogSize) {
            commandStatusLog.pollFirst();
        }
    }

    public boolean isConnected() {
        return null != this.crclSocket && this.crclSocket.isConnected();
    }

    private final AtomicInteger connectCount = new AtomicInteger();

    private boolean debugConnectDisconnect;

    /**
     * Get the value of debugConnectDisconnect
     *
     * @return the value of debugConnectDisconnect
     */
    public boolean isDebugConnectDisconnect() {
        return debugConnectDisconnect;
    }

    /**
     * Set the value of debugConnectDisconnect
     *
     * @param debugConnectDisconnect new value of debugConnectDisconnect
     */
    public void setDebugConnectDisconnect(boolean debugConnectDisconnect) {
        this.debugConnectDisconnect = debugConnectDisconnect;
    }

    @Nullable
    private volatile Thread connectThread = null;
    private volatile StackTraceElement connectTrace @Nullable []  = null;
    private volatile long connnectTime = -1;
    private volatile int lastSocketLocalPort = -1;
    private volatile int lastSocketRemotePort = -1;
    private volatile int socketLocalPort = -1;
    private volatile int socketRemotePort = -1;

    public synchronized void connect(String host, int port) {
        try {
            if (isConnected()) {
                throw new IllegalStateException("Already connected :  " + this.crclSocket + ", timeSinceConnect=" + (System.currentTimeMillis() - connnectTime) + ", connectThread=" + connectThread + ",connectTrace=" + Arrays.toString(connectTrace));
            }
            if (null == cmdSchema) {
                throw new IllegalStateException("null==cmdSchema");
            }
            if (null == statSchema) {
                throw new IllegalStateException("null==statSchema");
            }
            if (null == progSchema) {
                System.err.println("defaultsInstance.getProgramSchemasFile() = " + defaultsInstance.getProgramSchemasFile());
                System.err.println("progSchemaFiles = " + Arrays.toString(progSchemaFiles));
                System.err.println("PendantClientInner : createStackTrace = " + XFuture.traceToString(createStackTrace));
                throw new IllegalStateException("null==progSchema:  Utils.getCrclUserHomeDir()= " + Utils.getCrclUserHomeDir());
            }
            disconnecting = false;
            connectThread = Thread.currentThread();
            connectTrace = connectThread.getStackTrace();
            connnectTime = System.currentTimeMillis();
            if (debugConnectDisconnect) {
                Thread.dumpStack();
                System.err.println("port = " + port);
            }
            connectCount.incrementAndGet();

            CRCLSocket origCrclSocket = this.crclSocket;
            CRCLSocket newCrclSocket = new CRCLSocket(host, port, cmdSchema, statSchema, progSchema);
            this.crclSocket = newCrclSocket;
            lastSocketLocalPort = socketLocalPort;
            lastSocketRemotePort = socketRemotePort;
            final Socket socket = requireNonNull(newCrclSocket.getSocket(), "newCrclSocket.getSocket()");
            socketLocalPort = socket.getLocalPort();
            socketRemotePort = socket.getPort();

            if (null != cmdSchema) {
                newCrclSocket.setCmdSchema(cmdSchema);
            }
            LOGGER.log(Level.FINE, "PendantClientInner.connect : crclSocket = " + crclSocket);
            startStatusReaderThread();

            outer.finishConnect();
        } catch (CRCLException | IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            showMessage("Can't connect to " + host + ":" + port + " -- " + ex.getMessage());
        }
    }

    public void startStatusReaderThread() {
        this.stopStatusReaderThread();
        if (menuOuter().isUseReadStatusThreadSelected()) {
            readerThread = new Thread(new Runnable() {

                @Override
                public void run() {
                    final Thread t = Thread.currentThread();
                    while (!t.isInterrupted()
                            && !stopStatusReaderFlag
                            && null != crclSocket
                            && crclSocket.isConnected()) {
                        PendantClientInner.this.readStatus();
                    }
                }
            }, "PendantClientInner.statusReaderThread");
            readerThread.start();
        }
    }

    private final AtomicInteger disconnectCount = new AtomicInteger();

    @Nullable
    private volatile Thread disconnectThread = null;
    private volatile StackTraceElement disconnectTrace @Nullable []  = null;
    private volatile long disconnnectTime = -1;

    public synchronized void disconnect() {

        if (isRunningProgram()) {
            showErrorMessage("diconnect while isRunningProgram");
            throw new IllegalStateException("diconnect while isRunningProgram");
        }
        if (debugConnectDisconnect) {
            System.err.println("crclSocket = " + crclSocket);
            Thread.dumpStack();
        }
        disconnectThread = Thread.currentThread();
        disconnectTrace = disconnectThread.getStackTrace();
        disconnnectTime = System.currentTimeMillis();

        disconnectCount.incrementAndGet();
        if (debugConnectDisconnect) {
            System.out.println("disconnectCount = " + disconnectCount.get());
        }
        disconnecting = true;
        initSent = false;
        stopStatusReaderThread();
        closeTestProgramThread();
        outer.stopPollTimer();
        if (null != crclSocket) {
            LOGGER.log(Level.FINE, "PendantClientInner.disconnect : crclSocket = " + crclSocket);
//            System.err.println("crclSocket = " + crclSocket);
//            System.err.println("crclSocket.getLocalPort() = " + crclSocket.getLocalPort());
//            System.err.println("crclSocket.getPort() = " + crclSocket.getPort());
            try {
                crclSocket.close();
                Thread.sleep(100);
            } catch (Exception ex) {
                if (!disconnecting) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            }
            crclSocket = null;
        }
        stopStatusReaderThread();
        closeTestProgramThread();
        outer.finishDisconnect();
    }

    public void stopStatusReaderThread() {
        Thread origReaderThread = this.readerThread;
        if (null != origReaderThread) {
            try {
                stopStatusReaderFlag = true;
                if (origReaderThread.isAlive()) {
                    if (debugInterrupts) {
                        Thread.dumpStack();
                        System.err.println("Interrupting readerThread = " + origReaderThread);
                        System.out.println("readerThread.getStackTrace() = " + Arrays.toString(origReaderThread.getStackTrace()));
                    }
                    origReaderThread.interrupt();
                    origReaderThread.join(1500);
                }
            } catch (InterruptedException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            } finally {
                this.readerThread = null;
                stopStatusReaderFlag = false;
            }
        }
    }

    public double getJointTol() {
        return jointTol;
    }

    public void setJointTol(double jointTol) {
        this.jointTol = jointTol;
    }

    private boolean holdingObjectExpected;

    private volatile PmCartesian minLimit = new PmCartesian(-10000, -10000, -10000);

    /**
     * Get the value of minLimit
     *
     * @return the value of minLimit
     */
    public PmCartesian getMinLimit() {
        return new PmCartesian(minLimit.x, minLimit.y, minLimit.z);
    }

    /**
     * Set the value of minLimit
     *
     * @param minLimit new value of minLimit
     */
    public void setMinLimit(PmCartesian minLimit) {
        this.minLimit = minLimit;
    }

    private volatile PmCartesian maxLimit = new PmCartesian(10000, 10000, 10000);

    /**
     * Get the value of maxLimit
     *
     * @return the value of maxLimit
     */
    public PmCartesian getMaxLimit() {
        return new PmCartesian(maxLimit.x, maxLimit.y, maxLimit.z);
    }

    /**
     * Set the value of maxLimit
     *
     * @param maxLimit new value of maxLimit
     */
    public void setMaxLimit(PmCartesian maxLimit) {
        this.maxLimit = maxLimit;
    }

    /**
     * Get the value of holdingObjectExpected
     *
     * @return the value of holdingObjectExpected
     */
    public boolean isHoldingObjectExpected() {
        return holdingObjectExpected;
    }

    /**
     * Set the value of holdingObjectExpected
     *
     * @param holdingObjectExpected new value of holdingObjectExpected
     */
    public void setHoldingObjectExpected(boolean holdingObjectExpected) {
        this.holdingObjectExpected = holdingObjectExpected;
        outer.setExpectedHoldingObject(holdingObjectExpected);
    }

    private String lastMessage = "";

    /**
     * Get the value of lastMessage
     *
     * @return the value of lastMessage
     */
    public String getLastMessage() {
        return lastMessage;
    }

    /**
     * Set the value of lastMessage
     *
     * @param lastMessage new value of lastMessage
     */
    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    private boolean testCommandEffect(CRCLCommandType cmd, long cmdStartTime) {
        if (cmd instanceof MessageType) {
            setLastMessage(((MessageType) cmd).getMessage());
            return true;
        }
        if (cmd instanceof ActuateJointsType) {
            return testActuateJointsEffect((ActuateJointsType) cmd);
        }
        if (cmd instanceof MoveThroughToType) {
            return testMoveThroughToEffect((MoveThroughToType) cmd);
        }
        if (cmd instanceof MoveToType) {
            return testMoveToEffect((MoveToType) cmd);
        }
        if (cmd instanceof ConfigureJointReportsType) {
            return testConfigureJointReportsEffect((ConfigureJointReportsType) cmd);
        }
        if (cmd instanceof SetLengthUnitsType) {
            return testSetLengthUnitsEffect((SetLengthUnitsType) cmd);
        }
        if (cmd instanceof DwellType) {
            return testDwellEffect((DwellType) cmd, cmdStartTime);
        }
//        if (cmd instanceof SetEndEffectorType) {
//            SetEndEffectorType seeCmd = (SetEndEffectorType) cmd;
//            this.setHoldingObjectExpected(seeCmd.getSetting() < 0.5);
//        }
        return true;
    }

//    private double maxDwell = getDoubleProperty("crcl4java.maxdwell", 6000.0);
    private double maxDwellEffectDifference = getDoubleProperty("crcl4java.ui.maxDwellEffectDifference", 20000.0);

    private static double getDoubleProperty(String propName, double defaultVal) {
        return Double.parseDouble(System.getProperty(propName, Double.toString(defaultVal)));
    }

    @MonotonicNonNull
    private String effectFailedMessage = null;

    private boolean testActuateJointsEffect(ActuateJointsType ajst) {
        if (null == status) {
            throw new IllegalStateException("null==status");
        }
        List<ActuateJointType> ajl = ajst.getActuateJoint();
        final JointStatusesType jointStatuses = status.getJointStatuses();
        if (null == jointStatuses) {
            showMessage("ActuateJoints failed : (null == status.getJointStatuses() ");
            return false;
        }
        for (ActuateJointType aj : ajl) {
            List<JointStatusType> jointListTest = jointStatuses.getJointStatus();
            JointStatusType jointStatusTest = null;
            for (int j = 0; j < jointListTest.size(); j++) {
                JointStatusType jsj = jointListTest.get(j);
                if (jsj.getJointNumber() == aj.getJointNumber()) {
                    jointStatusTest = jointListTest.get(j);
                    break;
                }
            }
            if (null == jointStatusTest) {
                showMessage("ActuateJoints failed : no jointStatus for " + aj.getJointNumber());
                return false;
            }
            double jointDiff = Math.abs(jointStatusTest.getJointPosition() - aj.getJointPosition());
            if (jointDiff > jointTol) {
                effectFailedMessage = "ActuateJoints failed measured position differs from commanded position." + NEW_LINE
                        + "JointNumber: " + aj.getJointNumber() + NEW_LINE
                        + "Commanded :" + aj.getJointPosition() + NEW_LINE
                        + "Status (Measured): " + jointStatusTest.getJointPosition() + NEW_LINE
                        + "Tolerance: " + jointTol + NEW_LINE
                        + "Diff: " + jointDiff;
                showMessage(effectFailedMessage);
                return false;
            }
        }
        return true;
    }

    /**
     * Get the value of angleType
     *
     * @return the value of angleType
     */
    public AngleUnitEnumType getAngleType() {
        return angleType;
    }

    public void setAngleType(AngleUnitEnumType newAngleType) {
        this.angleType = newAngleType;
    }

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
        LengthUnitEnumType oldLengthUnit = this.lengthUnit;
        this.lengthUnit = lengthUnit;
        propertyChangeSupport.firePropertyChange(PROP_LENGTHUNIT, oldLengthUnit, lengthUnit);
    }

    /**
     * Add PropertyChangeListener.
     *
     * @param listener listener to be notified of property changes.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener previously added listener to be removed.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    private boolean testMoveThroughToEffect(MoveThroughToType moveThroughTo) {
        PoseType curPose = this.currentStatusPose();
        PoseType cmdPose = moveThroughTo.getWaypoint().get(moveThroughTo.getNumPositions() - 1);
        return PoseToleranceChecker.isInTolerance(curPose, cmdPose, expectedEndPoseTolerance, angleType);
    }

    private boolean testDwellEffect(DwellType dwell, long startTime) {
        long elapsed = System.currentTimeMillis() - startTime;
        double dwellTime = dwell.getDwellTime() * 1000.0;
//        if (dwellTime > maxDwell) {
//            effectFailedMessage = "dwellTime of " + dwellTime + " exceeded max of " + maxDwell;
//            LOGGER.warning(effectFailedMessage);
//            return true;
//        }
        long expected = (long) (dwellTime);
        if (Math.abs(elapsed - expected) > maxDwellEffectDifference && !ignoreTimeouts) {
            effectFailedMessage = "Dwell expected to take " + expected + " ms but took " + elapsed + " ms.";
            outer.showMessage(effectFailedMessage);
            return false;
        }
        return true;
    }

    private double unitToScale(LengthUnitEnumType unit) {
        switch (unit) {
            case METER:
                return 1_000.0;

            case MILLIMETER:
                return 1.0;

            case INCH:
                return 25.4;
        }
        return Double.NaN; // This should never happen.
    }

    private boolean testSetLengthUnitsEffect(SetLengthUnitsType slu) {
        if (!testCommandStartLengthUnitSent) {
            return true;
        }
        CRCLStatusType origStatus = requireNonNull(this.testCommandStartStatus, "testCommandStartStatus");
        PointType origPoint = requireNonNull(CRCLPosemath.getPoint(origStatus), "CRCLPosemath.getPoint(testCommandStartStatus)");
        PointType curPoint = this.currentStatusPoint();
        double newScale = unitToScale(slu.getUnitName());
        double oldScale = unitToScale(testCommandStartLengthUnit);
        double convertScale = newScale / oldScale;
        double origX = origPoint.getX();
        double expectedX = origX / convertScale;
        double curX = curPoint.getX();
        if (Math.abs(expectedX - curX) > 0.0001) {
            showMessage("X value after SelLengthUnits to " + slu.getUnitName() + " with original value " + origX + " " + testCommandStartLengthUnit + " was expected to become " + expectedX + " " + slu.getUnitName() + " but instead was " + curX);
            return false;
        }
        double origY = origPoint.getY();
        double expectedY = origY / convertScale;
        double curY = curPoint.getY();
        if (Math.abs(expectedY - curY) > 0.0001) {
            showMessage("Y value after SelLengthUnits to " + slu.getUnitName() + " with original value " + origY + " " + testCommandStartLengthUnit + " was expected to become " + expectedY + " " + slu.getUnitName() + " but instead was " + curY);
            return false;
        }
        double origZ = origPoint.getZ();
        double expectedZ = origZ / convertScale;
        double curZ = curPoint.getZ();
        if (Math.abs(expectedZ - curZ) > 0.0001) {
            showMessage("Z value after SelLengthUnits to " + slu.getUnitName() + " with original value " + origZ + " " + testCommandStartLengthUnit + " was expected to become " + expectedZ + " " + slu.getUnitName() + " but instead was " + curZ);
            return false;
        }
        return true;
    }

    private boolean testConfigureJointReportsEffect(ConfigureJointReportsType dwell) {
        if (null == cjrMap || cjrMap.size() < 1) {
            return true;
        }
        final CRCLStatusType status1 = this.getStatus();
        if (null == status1) {
            return checkCJRMap();
        }
        final JointStatusesType jointStatuses = status1.getJointStatuses();
        if (null == jointStatuses) {
            return checkCJRMap();
        }
        List<JointStatusType> jsl = jointStatuses.getJointStatus();
        for (JointStatusType js : jsl) {
            int number = js.getJointNumber();
            ConfigureJointReportType cjr = cjrMap.get(number);
            if (null != cjr) {
                if (cjr.isReportPosition() && js.getJointPosition() == null) {
                    outer.showMessage("ConfigureJointReports set reportPosition to true but position is null for joint " + number);
                    return false;
                }
                if (!cjr.isReportPosition() && js.getJointPosition() != null) {
                    outer.showMessage("ConfigureJointReports set reportPosition to false but position is " + js.getJointPosition() + " for joint " + number);
                    return false;
                }
                if (cjr.isReportVelocity() && js.getJointVelocity() == null) {
                    outer.showMessage("ConfigureJointReports set reportVelocity to true but velocity is null for joint " + number);
                    return false;
                }
                if (!cjr.isReportVelocity() && js.getJointVelocity() != null) {
                    outer.showMessage("ConfigureJointReports set reportVelocity to false but velocity is " + js.getJointVelocity() + " for joint " + number);
                    return false;
                }
                if (cjr.isReportTorqueOrForce() && js.getJointTorqueOrForce() == null) {
                    outer.showMessage("ConfigureJointReports set reportTorqueOrForce to true but torqueOrForce is null for joint " + number);
                    return false;
                }
                if (!cjr.isReportTorqueOrForce() && js.getJointTorqueOrForce() != null) {
                    outer.showMessage("ConfigureJointReports set reportPosition to false but torqueOrForce is " + js.getJointTorqueOrForce() + " for joint " + number);
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkCJRMap() {
        if (cjrMap.values().stream()
                .noneMatch(cjr -> cjr.isReportPosition() || cjr.isReportTorqueOrForce() || cjr.isReportVelocity())) {
            return true;
        }
        outer.showMessage("ConfigureJointReports sent but JointStatuses is null");
        return false;
    }

    private final String NEW_LINE = System.lineSeparator();

    private boolean testMoveToEffect(MoveToType moveTo) {
        PoseType curPose = this.currentStatusPose();
        if (curPose == null || PoseToleranceChecker.containsNull(curPose)) {
            outer.showMessage("MoveTo Failed current pose contains null.");
            return false;
        }
        PoseType cmdPose = moveTo.getEndPosition();
        if (cmdPose == null || PoseToleranceChecker.containsNull(cmdPose)) {
            outer.showMessage("MoveTo Failed cmdPose contains null.");
            return false;
        }
        if (!PoseToleranceChecker.isInTolerance(curPose, cmdPose, expectedEndPoseTolerance, angleType)) {
            String message = "MoveTo Failed : diference between curPose and cmdPose exceeds tolerance." + NEW_LINE
                    + "curPose =" + CRCLPosemath.toString(curPose) + NEW_LINE
                    + "cmdPose=" + CRCLPosemath.toString(cmdPose) + NEW_LINE
                    + "expectedEndPoseTolerance=" + expectedEndPoseTolerance + ", angleType=" + angleType + NEW_LINE
                    + PoseToleranceChecker.checkToleranceString(curPose, cmdPose, expectedEndPoseTolerance, angleType) + NEW_LINE
                    + "moveTo.getCommandID()=" + moveTo.getCommandID() + NEW_LINE;
            if (null != status) {
                final CommandStatusType commandStatus = status.getCommandStatus();
                if (null != commandStatus) {
                    message
                            += "stat.getCommandStatus().getCommandId()=" + commandStatus.getCommandID() + NEW_LINE
                            + "stat.getCommandStatus().getCommandState()=" + commandStatus.getCommandState() + NEW_LINE;
                }
            }
            this.effectFailedMessage = message;
            outer.showMessage(message);
            return false;
        }
        return true;
    }

    public void stepFwd() {

    }

    @Nullable
    private volatile ProgramState pauseProgramState = null;
    @Nullable
    private volatile Thread pauseRunningProgramThread = null;

    private volatile boolean ignoreTimeouts;

    /**
     * Get the value of ignoreTimeouts
     *
     * @return the value of ignoreTimeouts
     */
    public boolean isIgnoreTimeouts() {
        return ignoreTimeouts;
    }

    /**
     * Set the value of ignoreTimeouts
     *
     * @param ignoreTimeouts new value of ignoreTimeouts
     */
    public void setIgnoreTimeouts(boolean ignoreTimeouts) {
        this.ignoreTimeouts = ignoreTimeouts;
    }

    @Nullable
    private volatile Thread pauseThread = null;
    private volatile long pauseStartTime = -1;
    private volatile StackTraceElement pauseTrace @Nullable []  = null;

    public long timeSincePause() {
        return System.currentTimeMillis() - pauseStartTime;
    }

    private synchronized void internalSetPausedTrue() {
        if (!paused) {
            pauseThread = Thread.currentThread();
            pauseTrace = pauseThread.getStackTrace();
            pauseStartTime = System.currentTimeMillis();
            pauseProgramState = programState;
            paused = true;
        }
    }

    public void pause() {
        try {
            internalSetPausedTrue();
            pause_count.incrementAndGet();
            pauseQueue.clear();
            if (isConnected() && !lastCmdTriedWasStop) {
                stopMotion(StopConditionEnumType.NORMAL);
            }
        } catch (JAXBException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public String pauseInfoString() {
        StringBuilder builder = new StringBuilder();
        builder.append("pauseThread=").append(pauseThread).append("\n");
        builder.append("pauseTrace=").append(pauseTrace).append("\n");
        builder.append("pauseStartTime=").append(pauseStartTime).append(" or ").append((System.currentTimeMillis() - pauseStartTime)).append("ago.\n");
        builder.append("pauseProgramState=").append(pauseProgramState).append("\n");
        builder.append("pause_count=").append(pause_count.get()).append("\n");
        builder.append("unpauseThread=").append(unpauseThread).append("\n");
        builder.append("unpauseProgramState=").append(unpauseProgramState).append("\n");
        builder.append("unpauseTime=").append(unpauseTime).append("\n");
        builder.append("unpausePauseCount=").append(unpausePauseCount).append("\n");
        return builder.toString();
    }

    public void waitForPause(int startRunProgramAbortCount) throws InterruptedException {
        while (paused) {
            if (startRunProgramAbortCount != runProgramAbortCount.get()) {
                return;
            }
            waiting_for_pause_queue.incrementAndGet();
            pauseQueue.take();
            waiting_for_pause_queue.decrementAndGet();
        }
    }

    @Nullable
    private volatile Thread unpauseThread = null;
    @Nullable
    private volatile ProgramState unpauseProgramState = null;
    private volatile long unpauseTime = 0;
    private volatile long unpausePauseCount = 0;

    private synchronized void internalSetPausedFalse() {
        if (paused) {
            paused = false;
            unpauseThread = Thread.currentThread();
            unpauseProgramState = programState;
            unpauseTime = System.currentTimeMillis();
            unpausePauseCount = pause_count.get();
        }
    }

    private volatile boolean unpausing = false;

    public void unpause() {
        unpausing = true;
        try {
            if (paused) {
                System.out.println("PendantClientInner.unpause() called.\n");
            }
            internalSetPausedFalse();

            for (int i = 0; i < waiting_for_pause_queue.get() + 1; i++) {
                try {
                    if (pauseQueue.isEmpty()) {
                        internalSetPausedFalse();
                        pauseQueue.put(Thread.currentThread().getStackTrace());
                        internalSetPausedFalse();
                    } else {
                        break;
                    }
                } catch (InterruptedException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            }
            internalSetPausedFalse();
        } finally {
            unpausing = false;
        }
    }

    public boolean isStepMode() {
        return stepMode;
    }

    public void setStepMode(boolean stepMode) {
        this.stepMode = stepMode;
    }

    /**
     * Get the value of quitOnTestCommandFailure
     *
     * @return the value of quitOnTestCommandFailure
     */
    public boolean isQuitOnTestCommandFailure() {
        return quitOnTestCommandFailure;
    }

    /**
     * Set the value of quitOnTestCommandFailure
     *
     * @param quitOnTestCommandFailure new value of quitOnTestCommandFailure
     */
    public void setQuitOnTestCommandFailure(boolean quitOnTestCommandFailure) {
        this.quitOnTestCommandFailure = quitOnTestCommandFailure;
    }

    @Nullable
    private String outgoingProgramFile = null;

    /**
     * Get the value of outgoingProgramFile
     *
     * @return the value of outgoingProgramFile
     */
    @Nullable
    public String getOutgoingProgramFile() {
        return outgoingProgramFile;
    }

    /**
     * Set the value of outgoingProgramFile
     *
     * @param outgoingProgramFile new value of outgoingProgramFile
     */
    public void setOutgoingProgramFile(String outgoingProgramFile) {
        this.outgoingProgramFile = outgoingProgramFile;
    }

    @Nullable
    private Integer outgoingProgramIndex;

    /**
     * Get the value of outgoingProgramIndex
     *
     * @return the value of outgoingProgramIndex
     */
    @Nullable
    public Integer getOutgoingProgramIndex() {
        return outgoingProgramIndex;
    }

    /**
     * Set the value of outgoingProgramIndex
     *
     * @param outgoingProgramIndex new value of outgoingProgramIndex
     */
    public void setOutgoingProgramIndex(Integer outgoingProgramIndex) {
        this.outgoingProgramIndex = outgoingProgramIndex;
    }

    @Nullable
    private Integer outgoingProgramLength = null;

    /**
     * Get the value of outgoingProgramLength
     *
     * @return the value of outgoingProgramLength
     */
    @Nullable
    public Integer getOutgoingProgramLength() {
        return outgoingProgramLength;
    }

    /**
     * Set the value of outgoingProgramLength
     *
     * @param outgoingProgramLength new value of outgoingProgramLength
     */
    public void setOutgoingProgramLength(Integer outgoingProgramLength) {
        this.outgoingProgramLength = outgoingProgramLength;
    }

    private final ThreadLocal<StackTraceElement @Nullable []> callingRunProgramStackTrace = new ThreadLocal<>();

    public ThreadLocal<StackTraceElement @Nullable []> getCallingRunProgramStackTrace() {
        return callingRunProgramStackTrace;
    }

    public boolean runProgram(CRCLProgramType prog, int startLine) {
        return runProgram(prog, startLine, null, null);
    }

    public static class ProgramState {

        private final CRCLProgramType program;
        private final int line;
        private final CRCLCommandType cmd;

        public ProgramState(CRCLProgramType program, int line, CRCLCommandType cmd) {
            this.program = program;
            this.line = line;
            this.cmd = cmd;
        }

        public CRCLProgramType getProgram() {
            return program;
        }

        public int getLine() {
            return line;
        }

        public CRCLCommandType getCmd() {
            return cmd;
        }
    }

    @Nullable
    private volatile ProgramState programState = null;

    /**
     * Get the value of programState
     *
     * @return the value of programState
     */
    @Nullable
    public ProgramState getProgramState() {
        return programState;
    }

    /**
     * Set the value of programState
     *
     * @param programState new value of programState
     */
    public void setProgramState(ProgramState programState) {
        this.programState = programState;
    }

    private boolean commandIdCompareAndSet(long expect, long update) {
        LOGGER.log(Level.FINE, "commandIdCompareAndSet: update = {0}, expect = {1}", new Object[]{update, expect});
        return commandId.compareAndSet(expect, update);
    }

    private volatile int lastRunProgramStartLine = -1;

//    private boolean checkProgIds(CRCLProgramType prog) {
//        long initId = prog.getInitCanon().getCommandID();
//        long endId = prog.getEndCanon().getCommandID();
//        int sz = prog.getMiddleCommand().size();
//        if (endId != (initId + sz + 1)) {
//            System.err.println("endId != (initId + prog.getMiddleCommand().size()) : initId=" + initId + ", endId=" + endId + ", prog.getMiddleCommand().size() = " + sz);
//            return false;
//        }
//        for (int i = 0; i < sz; i++) {
//            MiddleCommandType cmd = prog.getMiddleCommand().get(i);
//            if (cmd.getCommandID() != (initId + i + 1)) {
//                System.err.println("cmd.getCommandID() != (initId+i) : initId=" + initId + ", cmd.getCommandID()=" + cmd.getCommandID() + ", i=" + i);
//                return false;
//            }
//        }
//        return true;
//    }
    @Nullable
    private volatile String programName = null;
    private volatile int programIndex = -1;
    private volatile int lastProgramIndex = -1;
    @MonotonicNonNull
    private volatile String lastProgramName = null;
    private volatile int lastShowCurrentProgramLine = 0;

    public void showCurrentProgramLine(final int line, CRCLProgramType program, @Nullable CRCLStatusType status) {
        lastShowCurrentProgramLine = line;
        if (null != status) {
            outer.showCurrentProgramLine(line, program, status, progRunDataList);
        }
    }

    @Nullable
    private volatile Thread runProgramThread = null;

    @Nullable
    public Thread getRunProgramThread() {
        return runProgramThread;
    }

    @Nullable
    public XFuture<Boolean> getRunProgramFuture() {
        return runProgramFuture;
    }

    public int getCurrentProgramLine() {
        return lastShowCurrentProgramLine;
    }

    @MonotonicNonNull
    private volatile List<ProgramRunData> lastProgRunDataList = null;

    @Nullable
    public List<ProgramRunData> getLastProgRunDataList() {
        return lastProgRunDataList;
    }

    public void saveProgramRunDataListToCsv(File f, List<ProgramRunData> list) throws IOException {
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(f), CSVFormat.DEFAULT)) {
            printer.printRecord("time", "dist", "result", "id", "cmdString");
            for (ProgramRunData prd : list) {
                if (null != prd) {
                    printer.printRecord(prd.getTime(), prd.getDist(), prd.isResult(), prd.getId(), prd.getCmdString());
                }
            }
        }
    }

    public void saveLastProgramRunDataListToCsv(File f) throws IOException {
        if (null == lastProgRunDataList) {
            return;
        }
        saveProgramRunDataListToCsv(f, lastProgRunDataList);
    }

    private volatile StackTraceElement[] runProgramReturnFalseTrace = null;

    public StackTraceElement[] getRunProgramReturnFalseTrace() {
        return runProgramReturnFalseTrace;
    }

    private void setRunProgramReturnFalseTrace() {
        runProgramReturnFalseTrace = Thread.currentThread().getStackTrace();
    }

    private volatile MiddleCommandType lastMidddleCmd = null;

    private boolean runProgram(CRCLProgramType prog, int startLine,
            final StackTraceElement @Nullable [] threadCreateCallStack,
            @Nullable XFuture<Boolean> future) {
        int index = startLine;
        String progName = prog.getName();
        CRCLCommandType lineCmd = null;
        try {
            final int origStartLine = startLine;
            final int startRunProgramAbortCount = runProgramAbortCount.get();
            if (this.isBlockPrograms()) {
                System.out.println("origStartLine = " + origStartLine);
                System.out.println("threadCreateCallStack = " + Arrays.toString(threadCreateCallStack));
                printStartBlockingProgramInfo();
                showErrorMessage("Block Programs");
                throw new IllegalStateException("Block Programs");
            }
            int pause_count_start = this.pause_count.get();
            boolean waitForStatusResult = waitForStatus(100, 50, pause_count_start, startRunProgramAbortCount);
            if (!waitForStatusResult) {
                showMessage("runProgram() failed waiting for initial status");
                System.out.println("request_status_count = " + request_status_count);
                System.out.println("readerThread = " + readerThread);
                System.out.println("pause_count.get() = " + pause_count.get());
                System.out.println("pause_count_start = " + pause_count_start);
                setRunProgramReturnFalseTrace();
                throw new RuntimeException("runProgram() failed waiting for initial status");
            }
            runProgramThread = Thread.currentThread();
            if (startLine == -2) {
                startLine = lastShowCurrentProgramLine;
            }

            if (lastProgramIndex > startLine + 2 && startLine > 2) {
                System.out.println("origStartLine = " + origStartLine);
                System.out.println("threadCreateCallStack = " + Arrays.toString(threadCreateCallStack));
                showErrorMessage("programIndex moving backwards: " + lastProgramIndex + ">" + startLine);
                throw new IllegalStateException("programIndex moving backwards: " + lastProgramIndex + ">" + startLine);
            }

            if (lastProgramIndex > startLine + 2 && startLine >= 0 && null != lastProgramName && lastProgramName.equals(prog.getName())) {
                System.out.println("origStartLine = " + origStartLine);
                System.out.println("threadCreateCallStack = " + Arrays.toString(threadCreateCallStack));
                showErrorMessage("programIndex moving backwards: " + lastProgramName + ">" + startLine + ": lastProgramName= " + lastProgramName);
                throw new IllegalStateException("programIndex moving backwards: " + lastProgramName + ">" + startLine);
            }
            if (prog != program) {
                setProgram(prog);
            }
            CRCLProgramType origProg = CRCLPosemath.copy(prog);
            final int start_close_test_count = this.close_test_count.get();
            lastRunProgramStartLine = startLine;
            holdingErrorOccured = false;
            crclClientErrorMessage = null;
            holdingErrorRepCount = 0;
            callingRunProgramStackTrace.set(threadCreateCallStack);
            progRunDataList.clear();
            outer.clearProgramTimesDistances();

            int i = 0;
            if (null == prog) {
                System.err.println("startLine=" + startLine);
                System.err.println("threadCreateCallStack=" + Arrays.toString(threadCreateCallStack));
                throw new IllegalArgumentException("prog == null");
            }

            this.waitForPause(startRunProgramAbortCount);
            int runProgamAbortCount1 = runProgramAbortCount.get();
            if (runProgamAbortCount1 != startRunProgramAbortCount) {
                setRunProgramReturnFalseTrace();
                throw new RuntimeException("probram aborted : runProgamAbortCount1=" + runProgamAbortCount1 + ", startRunProgramAbortCount=" + startRunProgramAbortCount);
            }
            long id = commandId.get();
            InitCanonType initCmd = prog.getInitCanon();
            long progId = initCmd.getCommandID();
            if (null != prog && null != prog.getMiddleCommand()) {
                setOutgoingProgramLength(prog.getMiddleCommand().size());
            } else {
                setOutgoingProgramLength(0);
            }

            if (null == this.crclSocket) {
                String outerHost = outer.getHost();
                int outerPort = outer.getPort();
                this.connect(outerHost, outerPort);
                if (!this.isConnected()) {
                    showErrorMessage("runProgram() failed because not connected to server");
                    setRunProgramReturnFalseTrace();
                    throw new RuntimeException("runProgram() failed because not connected to server : host=" + outerHost + ",port=" + outerPort);
                }
            }
            outer.stopPollTimer();

            programCommandStartTime = System.currentTimeMillis();
            PmCartesian p0 = getPoseCart();
            if (this.runStartMillis < 1 || startLine == 0) {
                this.runStartMillis = System.currentTimeMillis();
                this.runEndMillis = -1;
            }
            setOutgoingProgramIndex(startLine);
            CRCLStatusType curStatus = getStatus();
            if (null != curStatus) {
                showCurrentProgramLine(startLine, prog, curStatus);
            }
            if (startLine == 0) {
                logRunProgramDebugInfo(startLine, id, progId);
                while ((progId - 1) > id
                        && !commandIdCompareAndSet(id, (progId - 1))) {
                    id = commandId.get();
                    progId = initCmd.getCommandID();
                }

                programState = new ProgramState(prog, 0, initCmd);
                programName = prog.getName();
                programIndex = 0;
                index = 0;
                lineCmd = initCmd;
                testCommand(initCmd, startRunProgramAbortCount);
                int runProgramAbortCountCheck3 = runProgramAbortCount.get();
                if (runProgramAbortCountCheck3 != startRunProgramAbortCount) {
                    setRunProgramReturnFalseTrace();
                    throw new RuntimeException("probram aborted : runProgramAbortCountCheck3=" + runProgramAbortCountCheck3 + ", startRunProgramAbortCount=" + startRunProgramAbortCount);
                }
                if (stepMode) {
                    pause();
                }
                startLine = 1;
            }
            long time_to_exec = System.currentTimeMillis() - programCommandStartTime;
            PmCartesian p1 = getPoseCart();

            ProgramRunData prd = new ProgramRunData(time_to_exec, p1.distFrom(p0), true, initCmd.getCommandID(), CRCLSocket.commandToSimpleString(initCmd));
            while (progRunDataList.size() <= 1) {
                progRunDataList.add(PROGRAM_RUN_DATA_PLACEHOLDER);
            }
            progRunDataList.set(0, prd);
            outer.showLastProgramLineExecTimeMillisDists(0, prd);
            p0 = p1;
            showCurrentProgramLine(startLine, prog, getStatus());
            List<MiddleCommandType> middleCommands = prog.getMiddleCommand();
            MiddleCommandType cmd = null;
            for (i = (startLine > 1 ? startLine : 1); i < middleCommands.size() + 1; i++) {
                if (null != future && future.isCancelled()) {
                    try {
                        stopMotion(StopConditionEnumType.FAST);
                    } catch (JAXBException ex) {
                        LOGGER.log(Level.SEVERE, null, ex);
                    }
                    System.err.println("runProgram() stopped when future.isCancelled() returned true");
                    setRunProgramReturnFalseTrace();
                    throw new RuntimeException("runProgram() stopped when future.isCancelled() returned true");
                }
                programCommandStartTime = System.currentTimeMillis();
                runProgramThread = Thread.currentThread();
                setOutgoingProgramIndex(i);
                cmd = middleCommands.get(i - 1);
                do {
                    id = commandId.get();
                    progId = cmd.getCommandID();
                    if (i == startLine) {
                        logRunProgramDebugInfo(startLine, id, progId);
                    }
                } while ((progId - 1) > id
                        && !commandIdCompareAndSet(id, (progId - 1)));
                if (cmd instanceof CrclCommandWrapper) {
                    CrclCommandWrapper wrapper = (CrclCommandWrapper) cmd;
                    wrapper.setCurProgram(prog);
                    wrapper.setCurProgramIndex(i - 1);
                }
                programState = new ProgramState(prog, i, cmd);
                programName = prog.getName();
                index = i + 1;
                programIndex = index;
                if (lastProgramIndex > index && index > 2) {
                    showErrorMessage("programIndex moving backwards: " + lastProgramIndex + ">" + index);
                    throw new IllegalStateException("programIndex moving backwards: " + lastProgramIndex + ">" + index);
                }
                lastProgramIndex = programIndex;
                progName = prog.getName();
                if (null != progName) {
                    lastProgramName = progName;
                }
                if (this.isBlockPrograms()) {
                    printStartBlockingProgramInfo();
                    showErrorMessage("Block Programs");
                    throw new IllegalStateException("Block Programs");
                }
                lineCmd = cmd;
                testCommand(cmd, startRunProgramAbortCount);
//                if (!result) {
//                    if (this.isQuitOnTestCommandFailure()) {
//                        if (isConnected()) {
//                            stopMotion(StopConditionEnumType.FAST);
//                        }
//                        if (null != future) {
//                            future.cancelAll(false);
//                        }
//                        setRunProgramReturnFalseTrace();
//                        throw new RuntimeException("runProgram() failed with index=" + index + " because cmd=" + cmdString(cmd) + " failed.");
//                    }
//                }
                curStatus
                        = requireNonNull(
                                this.getStatus(),
                                "this.getStatus()");
                final CommandStatusType commandStatus
                        = requireNonNull(
                                curStatus.getCommandStatus(),
                                "curStatus.getCommandStatus()");
                CommandStateEnumType commandState = commandStatus.getCommandState();
                if (commandState != CommandStateEnumType.CRCL_DONE) {
                    throw new RuntimeException("commandState=" + commandState + " when expected to be done. index=" + index + ", cmd=" + cmdString(cmd));
                }
                time_to_exec = System.currentTimeMillis() - programCommandStartTime;
                p1 = getPoseCart();
                if (i <= middleCommands.size()) {
                    showCurrentProgramLine(i, prog, getStatus());
                    prd = new ProgramRunData(time_to_exec, p1.distFrom(p0), true, cmd.getCommandID(), CRCLSocket.commandToSimpleString(cmd));
                    while (progRunDataList.size() <= i) {
                        progRunDataList.add(PROGRAM_RUN_DATA_PLACEHOLDER);
                    }
                    progRunDataList.set(i, prd);
                    outer.showLastProgramLineExecTimeMillisDists(i, prd);
                }
                p0 = p1;
                if (stepMode) {
                    pause();
                }
            }
            lastMidddleCmd = cmd;
            if (!middleCommands.isEmpty()) {
                if (outer.getCurrentProgramLine() != middleCommands.size()) {
                    System.out.println("outer.getCurrentProgramLine() = " + outer.getCurrentProgramLine());
                    System.out.println("middleCommands.size() = " + middleCommands.size());
                }
                if (cmd != middleCommands.get(middleCommands.size() - 1)) {
                    System.out.println("cmd = " + cmd);
                    System.out.println("middleCommands.get(middleCommands.size()-1) = " + middleCommands.get(middleCommands.size() - 1));
                }
            }

            programCommandStartTime = System.currentTimeMillis();
            EndCanonType endCmd = prog.getEndCanon();
            programName = prog.getName();
            index = -1;
            programIndex = -1;
            lineCmd = endCmd;
            testCommand(endCmd, startRunProgramAbortCount);
            int runProgramAbortCountCheck2 = runProgramAbortCount.get();
            if (runProgramAbortCountCheck2 != startRunProgramAbortCount) {
                setRunProgramReturnFalseTrace();
                throw new RuntimeException("probram aborted : runProgramAbortCountCheck2=" + runProgramAbortCountCheck2 + ", startRunProgramAbortCount=" + startRunProgramAbortCount);
            }
            time_to_exec = System.currentTimeMillis() - programCommandStartTime;
            prd = new ProgramRunData(time_to_exec, p1.distFrom(p0), true, endCmd.getCommandID(), CRCLSocket.commandToSimpleString(endCmd));
            while (progRunDataList.size() <= middleCommands.size() + 1) {
                progRunDataList.add(PROGRAM_RUN_DATA_PLACEHOLDER);
            }
            progRunDataList.set(middleCommands.size() + 1, prd);
            outer.showLastProgramLineExecTimeMillisDists(middleCommands.size() + 1, prd);
            showCurrentProgramLine(middleCommands.size() + 2, prog, getStatus());
            curStatus
                    = requireNonNull(
                            this.getStatus(),
                            "this.getStatus()");
            final CommandStatusType commandStatus
                    = requireNonNull(
                            curStatus.getCommandStatus(),
                            "curStatus.getCommandStatus()");
            return commandStatus.getCommandState() != CommandStateEnumType.CRCL_ERROR;

        } catch (Exception ex) {
            Logger.getLogger(PendantClientInner.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("startLine = " + startLine);
            System.err.println("index = " + index);
            System.err.println("progName = " + progName);
            if (null != lineCmd) {
                System.err.println("lineCmd = " + CRCLSocket.cmdToString(lineCmd));
            }
            throw new RuntimeException("Failed to run program " + progName + " : " + ex.getMessage(), ex);
        } finally {
            try {
                if (null != crclSocket) {
                    stopMotion(StopConditionEnumType.FAST);
                }
            } catch (JAXBException jAXBException) {
                Logger.getLogger(PendantClientInner.class.getName()).log(Level.SEVERE, null, jAXBException);
            }

            setOutgoingProgramIndex(-1);
            this.runEndMillis = System.currentTimeMillis();
            outer.checkPollSelected();
            callingRunProgramStackTrace.set(null);
            lastProgRunDataList = new ArrayList<>(progRunDataList);
            runProgramThread = null;
        }
    }

    private void logRunProgramDebugInfo(int startLine, long id, long progId) {
        CRCLProgramType prog = this.program;
        if (null == prog) {
            if (debugConnectDisconnect || debugInterrupts) {
                System.out.println("runProgram(startLine = " + startLine + ") :id = " + id + ", program=null , progId = " + progId);
            } else {
                LOGGER.log(Level.FINE, "runProgram(startLine = " + startLine + ") :id = " + id + ", program=null, progId = " + progId);
            }
            return;
        }
        String programName = prog.getName();
        if (debugConnectDisconnect || debugInterrupts) {
            System.out.println("runProgram(startLine = " + startLine + ") :id = " + id + ", program.getName() = " + programName + ", progId = " + progId);
        } else {
            LOGGER.log(Level.FINE, "runProgram(startLine = " + startLine + ") :id = " + id + ", program.getName() = " + programName + ", progId = " + progId);
        }
    }

    private PmCartesian getPoseCart() {
        PmCartesian p0
                = Optional.ofNullable(status)
                        .map(CRCLPosemath::getPoint)
                        .filter(x -> x != null)
                        .map(CRCLPosemath::toPmCartesian)
                        .orElse(new PmCartesian());
        return p0;
    }

    public boolean isPaused() {
        return this.paused;
    }

    private volatile boolean lastRunProgramFutureNotCompleted = false;
    private volatile boolean lastRunProgramThreadAlive = false;

    public boolean isRunningProgram() {
        boolean runProgramFutureNotCompleted = isRunProgramFutureNotCompleted();
        boolean runProgramThreadAlive = isRunProgramThreadAlive();
        lastRunProgramFutureNotCompleted = runProgramFutureNotCompleted;
        lastRunProgramThreadAlive = runProgramThreadAlive;
        return runProgramFutureNotCompleted
                || (null == runProgramFuture && runProgramThreadAlive);
    }

    private boolean isRunProgramThreadAlive() {
        return null != runProgramThread && runProgramThread.isAlive();
    }

    private boolean isRunProgramFutureNotCompleted() {
        return null != runProgramFuture && !runProgramFuture.isDone() && !runProgramFuture.isCancelled()
                && !runProgramFuture.isCompletedExceptionally();
    }

    public long getRunStartMillis() {
        return runStartMillis;
    }

    public long getRunEndMillis() {
        return runEndMillis;
    }

    public Map<String, String> getDefaultTestPropertiesMap() {
        Map<String, String> map = new HashMap<>();
        map.put("jointTol", Double.toString(jointTol));
        map.put("jointPosIncrement", Double.toString(jogIncrement));
        map.put("jointMoveSpeed", "");
        map.put("jointMoveAccel", "");
        map.put("xyzAxisIncrement", Double.toString(this.getXyzJogIncrement()));
        map.put("maxJoint", "10");
        return map;
    }

    /**
     * Get the value of jointMoveAccel
     *
     * @return the value of jointMoveAccel
     */
    public double getJointMoveAccel() {
        return jointMoveAccel;
    }

    /**
     * Set the value of jointMoveAccel
     *
     * @param jointMoveAccel new value of jointMoveAccel
     */
    public void setJointMoveAccel(double jointMoveAccel) {
        this.jointMoveAccel = jointMoveAccel;
    }

    /**
     * Get the value of jointMoveSpeed
     *
     * @return the value of jointMoveSpeed
     */
    public double getJointMoveSpeed() {
        return jointMoveSpeed;
    }

    private static final double JOG_JOINT_SPEED_DEFAULT = 20;
    private double jogJointSpeed = JOG_JOINT_SPEED_DEFAULT;

    /**
     * Get the value of jogJointSpeed
     *
     * @return the value of jogJointSpeed
     */
    public double getJogJointSpeed() {
        return jogJointSpeed;
    }

    /**
     * Set the value of jogJointSpeed
     *
     * @param jogJointSpeed new value of jogJointSpeed
     */
    public void setJogJointSpeed(double jogJointSpeed) {
        this.jogJointSpeed = jogJointSpeed;
    }

    /**
     * Set the value of jointMoveSpeed
     *
     * @param jointMoveSpeed new value of jointMoveSpeed
     */
    public void setJointMoveSpeed(double jointMoveSpeed) {
        this.jointMoveSpeed = jointMoveSpeed;
    }

    /**
     * Tests if an automatically generated set of commands appears to be
     * correctly implemented by the server.
     *
     * @param testProperies map of option names to values to modify the tests
     *
     * @return false for failure or true for success
     */
    public boolean runTest(Map<String, String> testProperies) {
        try {
            if (null == this.crclSocket) {
                this.connect(outer.getHost(), outer.getPort());
                if (!this.isConnected()) {
                    showMessage("runTest() failed because not connected to server");
                    return false;
                }
            }
            int startRunProgramAbortCount = runProgramAbortCount.get();
            outer.stopPollTimer();
            int pause_count_start = this.pause_count.get();
            final int orig_pause_count_start = pause_count_start;
            boolean waitForStatusResult = waitForStatus(100, 50, pause_count_start, startRunProgramAbortCount);
            if (!waitForStatusResult) {
                showMessage("runTest() failed because waiting for initial status");
                System.out.println("request_status_count = " + request_status_count);
                System.out.println("readerThread = " + readerThread);
                System.out.println("pause_count.get() = " + pause_count.get());
                System.out.println("orig_pause_count_start = " + orig_pause_count_start);
                synchronized (this) {
                    boolean requestStatusResult = requestStatus();
                    System.out.println("requestStatusResult = " + requestStatusResult);
                    boolean readStatusResult = readStatus();
                    System.out.println("readStatusResult = " + readStatusResult);
                }
                return false;
            }
            InitCanonType initCmd = new InitCanonType();
            testCommand(initCmd, startRunProgramAbortCount);
            SetLengthUnitsType setUnitType = new SetLengthUnitsType();
            setUnitType.setUnitName(this.lengthUnit);
            testCommand(setUnitType, startRunProgramAbortCount);
            CRCLStatusType curStatus = requireNonNull(this.status, "this.status");
            final JointStatusesType jointStatuses = requireNonNull(curStatus.getJointStatuses(), "curStatus.getJointStatuses()");
            if (null != jointStatuses) {
                ConfigureJointReportsType cjrs = new ConfigureJointReportsType();
                List<JointStatusType> jointList = jointStatuses.getJointStatus();
                cjrs.getConfigureJointReport().clear();
                for (int i = 0; i < jointList.size(); i++) {
                    ConfigureJointReportType cjr = new ConfigureJointReportType();
                    cjr.setReportPosition(true);
                    cjr.setReportVelocity(true);
                    cjr.setJointNumber(jointList.get(i).getJointNumber());
                    cjrs.getConfigureJointReport().add(cjr);
                }
                testCommand(cjrs, startRunProgramAbortCount);
            }

            CRCLProgramType testProgram = new CRCLProgramType();
            testProgram.setInitCanon(initCmd);
            Optional.ofNullable(testProperies)
                    .map(m -> m.get("jointTol"))
                    .map(Double::valueOf)
                    .ifPresent(this::setJointTol);
            double jointPosIncrement
                    = Optional.ofNullable(testProperies)
                            .map(m -> m.get("jointPosIncrement"))
                            .map(Double::parseDouble)
                            .orElse(jogIncrement);
            Double testJointMoveSpeed
                    = Optional.ofNullable(testProperies)
                            .map(m -> m.get("jointMoveSpeed"))
                            .filter(s -> s.length() > 0)
                            .map(Double::valueOf)
                            .orElse(null);
            Double testJointMoveAccel
                    = Optional.ofNullable(testProperies)
                            .map(m -> m.get("jointMoveAccel"))
                            .filter(s -> s.length() > 0)
                            .map(Double::valueOf)
                            .orElse(null);
            final Double xyzAxisIncrement
                    = Optional.ofNullable(testProperies)
                            .map(m -> m.get("xyzAxisIncrement"))
                            .map(Double::valueOf)
                            .orElse(this.getXyzJogIncrement());
            SetTransSpeedType setTransSpeed = new SetTransSpeedType();
            TransSpeedRelativeType transRel = new TransSpeedRelativeType();
            transRel.setFraction(1.0);
            setTransSpeed.setTransSpeed(transRel);
            testProgram.getMiddleCommand().add(setTransSpeed);
            if (null != jointStatuses) {
                List<JointStatusType> jointList = jointStatuses.getJointStatus();
                ConfigureJointReportsType cjrs = new ConfigureJointReportsType();
                cjrs.getConfigureJointReport().clear();
                for (int i = 0; i < jointList.size(); i++) {
                    ConfigureJointReportType cjr = new ConfigureJointReportType();
                    cjr.setReportPosition(true);
                    cjr.setReportVelocity(true);
                    cjr.setJointNumber(jointList.get(i).getJointNumber());
                    cjrs.getConfigureJointReport().add(cjr);
                }
                testProgram.getMiddleCommand().add(cjrs);
                int maxJoint = Integer.parseInt(testProperies.getOrDefault("maxJoint", "10"));
                for (int i = 0; i < jointList.size() && i < maxJoint; i++) {
                    JointStatusType js = jointList.get(i);
                    ActuateJointsType ajst = new ActuateJointsType();
                    List<ActuateJointType> ajl = ajst.getActuateJoint();
                    ActuateJointType aj = new ActuateJointType();
                    aj.setJointNumber(js.getJointNumber());
                    double origPosition = js.getJointPosition();
                    aj.setJointPosition(js.getJointPosition() + jointPosIncrement);
                    JointSpeedAccelType jsa = new JointSpeedAccelType();
                    if (null != testJointMoveSpeed) {
                        jsa.setJointSpeed(testJointMoveSpeed);
                    }
                    if (null != testJointMoveAccel) {
                        jsa.setJointAccel(testJointMoveAccel);
                    }
                    aj.setJointDetails(jsa);
                    ajl.add(aj);
                    testProgram.getMiddleCommand().add(ajst);
                    ajst = new ActuateJointsType();
                    ajl = ajst.getActuateJoint();
                    aj = new ActuateJointType();
                    aj.setJointNumber(js.getJointNumber());
                    aj.setJointPosition(origPosition);
                    jsa = new JointSpeedAccelType();
                    jsa.setJointSpeed(jointTol);
                    aj.setJointDetails(jsa);
                    ajl.add(aj);
                    testProgram.getMiddleCommand().add(ajst);
                    DwellType dwell = new DwellType();
                    dwell.setDwellTime(0.25);
                    testProgram.getMiddleCommand().add(dwell);
                }
            }
            setUnitType = new SetLengthUnitsType();
            setUnitType.setUnitName(LengthUnitEnumType.INCH);
            testProgram.getMiddleCommand().add(setUnitType);
            setUnitType = new SetLengthUnitsType();
            setUnitType.setUnitName(LengthUnitEnumType.METER);
            testProgram.getMiddleCommand().add(setUnitType);
            setUnitType = new SetLengthUnitsType();
            setUnitType.setUnitName(LengthUnitEnumType.MILLIMETER);
            testProgram.getMiddleCommand().add(setUnitType);
            PoseType pose = Optional.ofNullable(this)
                    .map(PendantClientInner::getStatus)
                    .map(CRCLPosemath::getPose)
                    .map(CRCLPosemath::copy)
                    .orElse(null);
            if (null != pose) {
                MoveToType moveToOrig = new MoveToType();
                PoseType origEndPos = new PoseType();
                PointType origEndPosPoint = new PointType();
                VectorType origEndXAxis = new VectorType();
                VectorType origEndZAxis = new VectorType();
                PointType posePoint = requireNonNull(pose.getPoint(), "pose.getPoint()");
                origEndPosPoint.setX(posePoint.getX());
                origEndPosPoint.setY(posePoint.getY());
                origEndPosPoint.setZ(posePoint.getZ());
                VectorType poseXAxis = requireNonNull(pose.getXAxis(), "pose.getXAxis()");
                origEndXAxis.setI(poseXAxis.getI());
                origEndXAxis.setJ(poseXAxis.getJ());
                origEndXAxis.setK(poseXAxis.getK());
                VectorType poseZAxis = requireNonNull(pose.getZAxis(), "pose.getZAxis()");
                origEndZAxis.setI(poseZAxis.getI());
                origEndZAxis.setJ(poseZAxis.getJ());
                origEndZAxis.setK(poseZAxis.getK());
                origEndPos.setPoint(origEndPosPoint);
                origEndPos.setXAxis(origEndXAxis);
                origEndPos.setZAxis(origEndZAxis);
                moveToOrig.setEndPosition(origEndPos);
                testProgram.getMiddleCommand().add(moveToOrig);
                MoveToType moveToXPlus = new MoveToType();
                PoseType xPlusPos
                        = pose(
                                point(posePoint.getX() + xyzAxisIncrement, posePoint.getY(), posePoint.getZ()),
                                poseXAxis,
                                poseZAxis);
                moveToXPlus.setEndPosition(xPlusPos);
                testProgram.getMiddleCommand().add(moveToXPlus);
                DwellType dwell = new DwellType();
                dwell.setDwellTime(0.25);
                testProgram.getMiddleCommand().add(dwell);
                testProgram.getMiddleCommand().add(moveToOrig);
                MoveToType moveToYPlus = new MoveToType();
                PoseType yPlusPos
                        = pose(
                                point(posePoint.getX(), posePoint.getY() + xyzAxisIncrement, posePoint.getZ()),
                                poseXAxis,
                                poseZAxis);
                moveToYPlus.setEndPosition(yPlusPos);
                testProgram.getMiddleCommand().add(moveToYPlus);
                dwell = new DwellType();
                dwell.setDwellTime(0.25);
                testProgram.getMiddleCommand().add(dwell);
                testProgram.getMiddleCommand().add(moveToOrig);
                MoveToType moveToZPlus = new MoveToType();
                PoseType zPlusPos
                        = pose(
                                point(posePoint.getX(), posePoint.getY(), posePoint.getZ() + xyzAxisIncrement),
                                poseXAxis,
                                poseZAxis);
                moveToZPlus.setEndPosition(zPlusPos);
                testProgram.getMiddleCommand().add(moveToZPlus);
                dwell = new DwellType();
                dwell.setDwellTime(0.25);
                testProgram.getMiddleCommand().add(dwell);
                testProgram.getMiddleCommand().add(moveToOrig);
            }
            EndCanonType endCmd = new EndCanonType();
            testProgram.setEndCanon(endCmd);
            String progString
                    = getTempCRCLSocket().programToPrettyDocString(testProgram, true);
            File testProgramFile = (null != tempLogDir)
                    ? File.createTempFile("crclTest", ".xml", tempLogDir)
                    : File.createTempFile("crclTest", ".xml");
            Files.write(testProgramFile.toPath(), progString.getBytes());
            this.openXmlProgramFile(testProgramFile, false);
            outer.showDebugMessage("Test program saved to " + testProgramFile.getCanonicalPath());
            return runProgram(testProgram, 0);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            outer.showMessage(ex);
        } finally {
            outer.checkPollSelected();
        }
        return false;
    }

    private String cmdNameString(@Nullable CRCLCommandType cmd) {
        if (null == cmd) {
            return "";
        }
        if (cmd instanceof CrclCommandWrapper) {
            cmd = ((CrclCommandWrapper) cmd).getWrappedCommand();
        }
        String cmdName = cmd.getClass().getSimpleName();
        final String prefix = "crcl.base.";
        if (cmdName.startsWith(prefix)) {
            return cmdName.substring(prefix.length());
        }
        return cmdName;
    }

    private String cmdString(CRCLCommandType cmd) throws JAXBException {
        if (cmd instanceof CrclCommandWrapper) {
            cmd = ((CrclCommandWrapper) cmd).getWrappedCommand();
        }
        String cmdName = cmdNameString(cmd);
        return cmdName + " with ID = " + cmd.getCommandID() + "\n" + this.getTempCRCLSocket().commandToString(cmd, false) + "\n";
    }

    public double getJointPosition(int jointNumber) {
        CRCLStatusType stat = requireNonNull(this.status, "this.status");
        JointStatusesType jointStatuses = requireNonNull(stat.getJointStatuses(), "stat.getJointStatuses()");
        List<JointStatusType> jsl = jointStatuses.getJointStatus();
        if (null == jsl) {
            throw new IllegalStateException("status.getJointStatuses().getJointStatus() == null");
        }
        for (JointStatusType js : jsl) {
            if (js.getJointNumber() == jointNumber) {
                return js.getJointPosition();
            }
        }
        throw new IllegalStateException("No match for jointNumber " + jointNumber + " on JointStatus list " + jsl);
    }

    private long getTimeoutForAcuateJoints(ActuateJointsType ajst) {
        List<ActuateJointType> ajl = ajst.getActuateJoint();
        double maxDiff = 0;
        for (ActuateJointType aj : ajl) {
            double jp = getJointPosition(aj.getJointNumber());
//            double thisDiff = jp.subtract(aj.getJointPosition()).abs();
            double thisDiff = Math.abs(jp - aj.getJointPosition());
            JointDetailsType jd = aj.getJointDetails();
            if (jd instanceof JointSpeedAccelType) {
                JointSpeedAccelType jas = (JointSpeedAccelType) jd;
                if (jas.getJointSpeed() != null) {
                    double vel = requireNonNull(jas.getJointSpeed(), "jas.getJointSpeed()");
                    if (vel <= 0) {
                        throw new RuntimeException("Invalid value of " + jas.getJointSpeed());
                    }
                    if (!Double.isFinite(vel)) {
                        throw new RuntimeException("Invalid value of " + jas.getJointSpeed());
                    }
                    try {
                        thisDiff /= vel;
                    } catch (Throwable e) {
                        System.err.println("thisDiff=" + thisDiff);
                        System.err.println("vel=" + vel);
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                    Double accelObject = jas.getJointAccel();
                    if (null != accelObject) {
                        double accel = accelObject;
                        thisDiff += Math.abs(2 * vel / accel);
                    }
                }
            }
            maxDiff = Math.max(maxDiff, thisDiff);
        }
        return 2000 + (long) (maxDiff * 15000.0);
    }

    private long getTimeoutForMoveThroughTo(MoveThroughToType cmd) {
        return 90000;
    }

    private long getTimeoutForMoveTo(MoveToType cmd) {
        return 90000;
    }

    private long getTimeoutForMoveScrew(MoveScrewType cmd) {
        return 90000;
    }

    private long getTimeoutForDwell(DwellType cmd) {
        return (long) (1500 + cmd.getDwellTime() * 1000);
    }

    private long getTimeout(CRCLCommandType cmd) {
        if (cmd instanceof ActuateJointsType) {
            return getTimeoutForAcuateJoints((ActuateJointsType) cmd);
        }
        if (cmd instanceof MoveThroughToType) {
            return getTimeoutForMoveThroughTo((MoveThroughToType) cmd);
        }
        if (cmd instanceof MoveToType) {
            return getTimeoutForMoveTo((MoveToType) cmd);
        }
        if (cmd instanceof MoveScrewType) {
            return getTimeoutForMoveScrew((MoveScrewType) cmd);
        }
        if (cmd instanceof DwellType) {
            return getTimeoutForDwell((DwellType) cmd);
        }
        if (cmd instanceof InitCanonType) {
            return 30000;
        }
        return 3000;
    }

    private CommandStatusType copyCommandStatus(CommandStatusType oldCmdStat) {
        if (null == oldCmdStat) {
            throw new IllegalArgumentException("null == oldCmdStat");
        }
        CommandStatusType newCmdStat = new CommandStatusType();
        newCmdStat.setCommandID(oldCmdStat.getCommandID());
        newCmdStat.setCommandState(oldCmdStat.getCommandState());
        newCmdStat.setStatusID(oldCmdStat.getStatusID());
        String oldCmdStatName = oldCmdStat.getName();
        if (null != oldCmdStatName) {
            newCmdStat.setName(oldCmdStatName);
        }
        return newCmdStat;
    }

    private GripperStatusType copyGripperStatusType(GripperStatusType oldGripperStat) {
        if (null == oldGripperStat) {
            throw new IllegalArgumentException("null == oldGripperStat");
        }
        GripperStatusType newGripperStat = null;
        if (oldGripperStat instanceof VacuumGripperStatusType) {
            VacuumGripperStatusType oldVacGripperStat = (VacuumGripperStatusType) oldGripperStat;
            VacuumGripperStatusType newVacGripperStat = new VacuumGripperStatusType();
            newVacGripperStat.setIsPowered(oldVacGripperStat.isIsPowered());
            newGripperStat = newVacGripperStat;
        } else if (oldGripperStat instanceof ThreeFingerGripperStatusType) {
            ThreeFingerGripperStatusType oldThreeFingerGripperStat = (ThreeFingerGripperStatusType) oldGripperStat;
            ThreeFingerGripperStatusType newThreeFingerGripperStat = new ThreeFingerGripperStatusType();
            newThreeFingerGripperStat.setFinger1Force(oldThreeFingerGripperStat.getFinger1Force());
            newThreeFingerGripperStat.setFinger2Force(oldThreeFingerGripperStat.getFinger2Force());
            newThreeFingerGripperStat.setFinger3Force(oldThreeFingerGripperStat.getFinger3Force());
            newThreeFingerGripperStat.setFinger1Position(oldThreeFingerGripperStat.getFinger1Position());
            newThreeFingerGripperStat.setFinger2Position(oldThreeFingerGripperStat.getFinger2Position());
            newThreeFingerGripperStat.setFinger3Position(oldThreeFingerGripperStat.getFinger3Position());
            newGripperStat = newThreeFingerGripperStat;
        } else if (oldGripperStat instanceof ParallelGripperStatusType) {
            ParallelGripperStatusType oldParallelGripperStat = (ParallelGripperStatusType) oldGripperStat;
            ParallelGripperStatusType newParallelGripperStat = new ParallelGripperStatusType();
            newParallelGripperStat.setSeparation(oldParallelGripperStat.getSeparation());
            newGripperStat = newParallelGripperStat;
        } else {
            throw new IllegalArgumentException("Unexpected Class for GripperStatus:" + oldGripperStat.getClass());
        }
        newGripperStat.setGripperName(oldGripperStat.getGripperName());
        String oldGripperStatName = oldGripperStat.getName();
        if (null != oldGripperStatName) {
            newGripperStat.setName(oldGripperStatName);
        }
        return newGripperStat;
    }

    private JointStatusType copyJointStatus(JointStatusType oldJointStat) {
        if (oldJointStat == null) {
            throw new IllegalArgumentException("null == oldJointStat");
        }
        JointStatusType newJointStat = new JointStatusType();
        newJointStat.setJointNumber(oldJointStat.getJointNumber());
        newJointStat.setJointPosition(oldJointStat.getJointPosition());
        newJointStat.setJointVelocity(oldJointStat.getJointVelocity());
        newJointStat.setJointTorqueOrForce(oldJointStat.getJointTorqueOrForce());
        String oldJointStatName = oldJointStat.getName();
        if (null != oldJointStatName) {
            newJointStat.setName(oldJointStatName);
        }
        return newJointStat;
    }

    private JointStatusesType copyJointStatuses(JointStatusesType oldJointStats) {
        if (null == oldJointStats) {
            throw new IllegalArgumentException("null == oldJointStats");
        }
        JointStatusesType newJointStats = new JointStatusesType();
        for (JointStatusType js : oldJointStats.getJointStatus()) {
            newJointStats.getJointStatus().add(copyJointStatus(js));
        }
        String oldJointStatsName = oldJointStats.getName();
        if (null != oldJointStatsName) {
            newJointStats.setName(oldJointStatsName);
        }
        return newJointStats;
    }

    private RotAccelType copyRotAccel(RotAccelType oldRotAccel) {
        if (null == oldRotAccel) {
            throw new IllegalArgumentException("null == oldRotAccel");
        }
        RotAccelType newRotAccel = null;
        if (oldRotAccel instanceof RotAccelAbsoluteType) {
            RotAccelAbsoluteType oldRotAccelAbsolute = (RotAccelAbsoluteType) oldRotAccel;
            RotAccelAbsoluteType newRotAccelAbsolute = new RotAccelAbsoluteType();
            newRotAccelAbsolute.setSetting(oldRotAccelAbsolute.getSetting());
            newRotAccel = newRotAccelAbsolute;
        } else if (oldRotAccel instanceof RotAccelRelativeType) {
            RotAccelRelativeType oldRotAccelRel = (RotAccelRelativeType) oldRotAccel;
            RotAccelRelativeType newRotAccelRel = new RotAccelRelativeType();
            newRotAccelRel.setFraction(oldRotAccelRel.getFraction());
            newRotAccel = newRotAccelRel;
        } else {
            throw new IllegalArgumentException("Unrecognized class :" + oldRotAccel.getClass());
        }
        String oldRotAccelName = oldRotAccel.getName();
        if (null != oldRotAccelName) {
            newRotAccel.setName(oldRotAccelName);
        }
        return newRotAccel;
    }

    private RotSpeedType copyRotSpeed(RotSpeedType oldRotSpeed) {
        if (null == oldRotSpeed) {
            throw new IllegalArgumentException("null == oldRotSpeed");
        }
        RotSpeedType newRotSpeed = null;
        if (oldRotSpeed instanceof RotSpeedAbsoluteType) {
            RotSpeedAbsoluteType oldRotSpeedAbsolute = (RotSpeedAbsoluteType) oldRotSpeed;
            RotSpeedAbsoluteType newRotSpeedAbsolute = new RotSpeedAbsoluteType();
            newRotSpeedAbsolute.setSetting(oldRotSpeedAbsolute.getSetting());
            newRotSpeed = newRotSpeedAbsolute;
        } else if (oldRotSpeed instanceof RotSpeedRelativeType) {
            RotSpeedRelativeType oldRotSpeedRel = (RotSpeedRelativeType) oldRotSpeed;
            RotSpeedRelativeType newRotSpeedRel = new RotSpeedRelativeType();
            newRotSpeedRel.setFraction(oldRotSpeedRel.getFraction());
            newRotSpeed = newRotSpeedRel;
        } else {
            throw new IllegalArgumentException("unrecognized class :" + oldRotSpeed.getClass());
        }
        String oldRotSpeedName = oldRotSpeed.getName();
        if (null != oldRotSpeedName) {
            newRotSpeed.setName(oldRotSpeedName);
        }
        return newRotSpeed;
    }

    private TransAccelType copyTransAccel(TransAccelType oldTransAccel) {
        if (null == oldTransAccel) {
            throw new IllegalArgumentException("null == oldTransAccel");
        }
        TransAccelType newTransAccel = null;
        if (oldTransAccel instanceof TransAccelAbsoluteType) {
            TransAccelAbsoluteType oldTransAccelAbsolute = (TransAccelAbsoluteType) oldTransAccel;
            TransAccelAbsoluteType newTransAccelAbsolute = new TransAccelAbsoluteType();
            newTransAccelAbsolute.setSetting(oldTransAccelAbsolute.getSetting());
            newTransAccel = newTransAccelAbsolute;
        } else if (oldTransAccel instanceof TransAccelRelativeType) {
            TransAccelRelativeType oldTransAccelRel = (TransAccelRelativeType) oldTransAccel;
            TransAccelRelativeType newTransAccelRel = new TransAccelRelativeType();
            newTransAccelRel.setFraction(oldTransAccelRel.getFraction());
            newTransAccel = newTransAccelRel;
        } else {
            throw new IllegalArgumentException("unrecognized class :" + oldTransAccel.getClass());
        }
        String oldTransAccelName = oldTransAccel.getName();
        if (null != oldTransAccelName) {
            newTransAccel.setName(oldTransAccelName);
        }
        return newTransAccel;
    }

    private TransSpeedType copyTransSpeed(TransSpeedType oldTransSpeed) {
        if (null == oldTransSpeed) {
            throw new IllegalArgumentException("null == oldTransSpeed");
        }
        TransSpeedType newTransSpeed = null;
        if (oldTransSpeed instanceof TransSpeedAbsoluteType) {
            TransSpeedAbsoluteType oldTransSpeedAbsolute = (TransSpeedAbsoluteType) oldTransSpeed;
            TransSpeedAbsoluteType newTransSpeedAbsolute = new TransSpeedAbsoluteType();
            newTransSpeedAbsolute.setSetting(oldTransSpeedAbsolute.getSetting());
            newTransSpeed = newTransSpeedAbsolute;
        } else if (oldTransSpeed instanceof TransSpeedRelativeType) {
            TransSpeedRelativeType oldTransSpeedRel = (TransSpeedRelativeType) oldTransSpeed;
            TransSpeedRelativeType newTransSpeedRel = new TransSpeedRelativeType();
            newTransSpeedRel.setFraction(oldTransSpeedRel.getFraction());
            newTransSpeed = newTransSpeedRel;
        } else {
            throw new IllegalArgumentException("unrecognized class :" + oldTransSpeed.getClass());
        }
        String oldTransSpeedName = oldTransSpeed.getName();
        if (null != oldTransSpeedName) {
            newTransSpeed.setName(oldTransSpeedName);
        }
        return newTransSpeed;
    }

    private PoseToleranceType copyPoseTolerance(PoseToleranceType oldPoseTol) {
        if (null == oldPoseTol) {
            throw new IllegalArgumentException("null == oldPoseTol");
        }
        PoseToleranceType newPoseTol = new PoseToleranceType();
        newPoseTol.setXAxisTolerance(oldPoseTol.getXAxisTolerance());
        newPoseTol.setZAxisTolerance(oldPoseTol.getZAxisTolerance());
        newPoseTol.setXPointTolerance(oldPoseTol.getXPointTolerance());
        newPoseTol.setYPointTolerance(oldPoseTol.getYPointTolerance());
        newPoseTol.setZPointTolerance(oldPoseTol.getZPointTolerance());
        return newPoseTol;
    }

    private PointType copyPoint(PointType oldPoint) {
        if (null == oldPoint) {
            throw new IllegalArgumentException("null == oldPoint");
        }
        PointType newPoint = new PointType();
        newPoint.setX(oldPoint.getX());
        newPoint.setY(oldPoint.getY());
        newPoint.setZ(oldPoint.getZ());
        String oldPointName = oldPoint.getName();
        if (null != oldPointName) {
            newPoint.setName(oldPointName);
        }
        return newPoint;
    }

    private VectorType copyVector(VectorType oldVector) {
        if (null == oldVector) {
            throw new IllegalArgumentException("null == oldVector");
        }
        VectorType newVector = new VectorType();
        newVector.setI(oldVector.getI());
        newVector.setJ(oldVector.getJ());
        newVector.setK(oldVector.getK());
        String oldVectorName = oldVector.getName();
        if (null != oldVectorName) {
            newVector.setName(oldVectorName);
        }
        return newVector;
    }

    private PoseType copyPose(PoseType oldPose) {
        if (null == oldPose) {
            throw new IllegalArgumentException("null == oldPose");
        }
        PoseType newPose = null;
        if (oldPose instanceof PoseAndSetType) {
            PoseAndSetType oldPoseAndSet = (PoseAndSetType) oldPose;
            PoseAndSetType newPoseAndSet = new PoseAndSetType();
            newPoseAndSet.setCoordinated(oldPoseAndSet.isCoordinated());
            newPoseAndSet.setRotAccel(copyRotAccel(oldPoseAndSet.getRotAccel()));
            newPoseAndSet.setRotSpeed(copyRotSpeed(oldPoseAndSet.getRotSpeed()));
            newPoseAndSet.setTransAccel(copyTransAccel(oldPoseAndSet.getTransAccel()));
            newPoseAndSet.setTransSpeed(copyTransSpeed(oldPoseAndSet.getTransSpeed()));
            newPoseAndSet.setTolerance(copyPoseTolerance(oldPoseAndSet.getTolerance()));
            newPose = newPoseAndSet;
        } else {
            newPose = new PoseType();
        }
        PointType oldPosePoint = oldPose.getPoint();
        if (null != oldPosePoint) {
            newPose.setPoint(copyPoint(oldPosePoint));
        }
        VectorType oldPoseXAxis = oldPose.getXAxis();
        if (null != oldPoseXAxis) {
            newPose.setXAxis(copyVector(oldPoseXAxis));
        }
        VectorType oldPoseZAxis = oldPose.getZAxis();
        if (null != oldPoseZAxis) {
            newPose.setZAxis(copyVector(oldPoseZAxis));
        }
        String oldPoseName = oldPose.getName();
        if (null != oldPoseName) {
            newPose.setName(oldPoseName);
        }
        return newPose;
    }

    private PoseStatusType copyPoseStatus(PoseStatusType oldPoseStatus) {
        if (null == oldPoseStatus) {
            throw new IllegalArgumentException("null == oldPoseStatus");
        }
        PoseStatusType newPoseStatus = new PoseStatusType();
        PoseType oldPoseStatusPose = requireNonNull(oldPoseStatus.getPose(), "oldPoseStatus.getPose()");
        newPoseStatus.setPose(copyPose(oldPoseStatusPose));
        String oldPoseStatusName = oldPoseStatus.getName();
        if (null != oldPoseStatusName) {
            newPoseStatus.setName(oldPoseStatusName);
        }
        return newPoseStatus;
    }

    private CRCLStatusType copyStatus(CRCLStatusType oldStatus) {
        if (null == oldStatus) {
            throw new IllegalArgumentException("null == oldStatus");
        }
        CRCLStatusType newStat = new CRCLStatusType();
        CommandStatusType commandStatus = requireNonNull(oldStatus.getCommandStatus(), "oldStatus.getCommandStatus()");
        newStat.setCommandStatus(copyCommandStatus(commandStatus));
        GripperStatusType gripperStatus = oldStatus.getGripperStatus();
        if (null != gripperStatus) {
            newStat.setGripperStatus(copyGripperStatusType(gripperStatus));
        }
        JointStatusesType jointStatuses = oldStatus.getJointStatuses();
        if (null != jointStatuses) {
            newStat.setJointStatuses(copyJointStatuses(jointStatuses));
        }
        PoseStatusType poseStatus = oldStatus.getPoseStatus();
        if (null != poseStatus) {
            newStat.setPoseStatus(copyPoseStatus(poseStatus));
        }
        String oldStatusName = oldStatus.getName();
        if (null != oldStatusName) {
            newStat.setName(oldStatusName);
        }
        return newStat;
    }

    private String createInterrupStackString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.interruptStacks.size(); i++) {
            StackTraceElement[] stack = interruptStacks.get(i);
            sb.append(NEW_LINE);
            sb.append("stack ");
            sb.append(i);
            sb.append(":");
            sb.append(NEW_LINE);
            sb.append(NEW_LINE);
            sb.append("java.lang.Exception: Stack trace");
            sb.append(NEW_LINE);
            for (StackTraceElement el : stack) {
                sb.append("\tat " + el.toString());
                sb.append(NEW_LINE);
            }
        }
        interruptStacks.clear();
        java.util.Collections.unmodifiableCollection(poseList);
        return sb.toString();
    }

    private boolean printDetailedCommandFailureInfo = false;

    /**
     * Get the value of printDetailedCommandFailureInfo
     *
     * @return the value of printDetailedCommandFailureInfo
     */
    public boolean isPrintDetailedCommandFailureInfo() {
        return printDetailedCommandFailureInfo;
    }

    /**
     * Set the value of printDetailedCommandFailureInfo
     *
     * @param printDetailedCommandFailureInfo new value of
     * printDetailedCommandFailureInfo
     */
    public void setPrintDetailedCommandFailureInfo(boolean printDetailedCommandFailureInfo) {
        this.printDetailedCommandFailureInfo = printDetailedCommandFailureInfo;
    }

    private final AtomicInteger runProgramAbortCount = new AtomicInteger();

    /**
     * Test a command by sending it and waiting for the status to indicate it
     * succeeded of failed. Additional effect properties are also checked for
     * some commands.
     *
     * @param cmd the command to send and test
     * @param startingRunProgramAbortCount
     * @return false for failure or true for success
     *
     * @throws javax.xml.bind.JAXBException failed to parse or generate xml
     * @throws InterruptedException this thread was interrupted
     * @throws java.io.IOException socket closed/failed.
     * @throws rcs.posemath.PmException math failure
     * @throws crcl.utils.CRCLException CRCL utility failed.
     */
    private void testCommand(CRCLCommandType cmd, final int startingRunProgramAbortCount) throws JAXBException, InterruptedException, IOException, PmException, CRCLException {
        int rpac = runProgramAbortCount.get();

        if (rpac != startingRunProgramAbortCount) {
            setRunProgramReturnFalseTrace();
            throw new RuntimeException("aborting : runProgramAbortCount=" + rpac + ", startingRunProgramAbortCount=" + startingRunProgramAbortCount + ", cmd=" + cmdString(cmd));
        }
        if (cmd instanceof CrclCommandWrapper) {
            this.waitForPause(startingRunProgramAbortCount);
            rpac = runProgramAbortCount.get();
            if (rpac != startingRunProgramAbortCount) {
                setRunProgramReturnFalseTrace();
                throw new RuntimeException("aborting : runProgramAbortCount=" + rpac + ", startingRunProgramAbortCount=" + startingRunProgramAbortCount + ", cmd=" + cmdString(cmd));
            }
            CrclCommandWrapper wrapped = (CrclCommandWrapper) cmd;
            CRCLCommandType wcmd = wrapped.getWrappedCommand();
            if (wcmd instanceof MessageType && skipWrappedMessageCommands) {
                incCommandID(cmd);
                wrapped.notifyOnStartListeners();
                rpac = runProgramAbortCount.get();
                if (rpac != startingRunProgramAbortCount) {
                    throw new RuntimeException("aborting : runProgramAbortCount=" + rpac + ", startingRunProgramAbortCount=" + startingRunProgramAbortCount + ", cmd=" + cmdString(cmd));
                }
                if (wrapped.getWrappedCommand() instanceof MessageType) {
                    wrapped.notifyOnDoneListeners();
                    rpac = runProgramAbortCount.get();
                    if (rpac != startingRunProgramAbortCount) {
                        throw new RuntimeException("aborting : runProgramAbortCount=" + rpac + ", startingRunProgramAbortCount=" + startingRunProgramAbortCount + ", cmd=" + cmdString(cmd));
                    }
                    return;
                }
            }
        } else if (cmd instanceof MoveToType) {
            MoveToType moveTo = (MoveToType) cmd;
            PoseType endPose = moveTo.getEndPosition();
            if (endPose == null) {
                throw new RuntimeException("MoveTo Command has invalid endPosition : null");
            }
            if (!checkPose(endPose)) {
                throw new RuntimeException("MoveTo Command has invalid endPosition :" + CRCLPosemath.poseToString(endPose));
            }
        }
        CRCLStatusType origStatuPrep = this.getStatus();
        if (null == origStatuPrep) {
            showMessage("testCommand can not get starting status");
            throw new IllegalStateException("testCommand can not get starting status");
        }
        CRCLStatusType origStatus = copyStatus(origStatuPrep);
        CRCLStatusType lastCmdStartStatus = origStatus;
        CRCLStatusType curStatus = origStatus;
        final long timeout = getTimeout(cmd);

        final long testCommandStartTime = System.currentTimeMillis();
        final String cmdString = cmdString(cmd);
        long sendCommandTime = testCommandStartTime;
        long curTime = testCommandStartTime;
        String poseListSaveFileName = null;
        this.lastWaitForDoneException = null;
        final int startingConnectCount = connectCount.get();
        final int startingDisconnectCount = disconnectCount.get();
        int pause_count_start = this.pause_count.get();
        final int orig_pause_count_start = pause_count_start;
        do {
            int pause_check = this.pause_count.get();
            if (rpac != startingRunProgramAbortCount) {
                throw new RuntimeException("aborting : runProgramAbortCount=" + rpac + ", startingRunProgramAbortCount=" + startingRunProgramAbortCount + ", cmd=" + cmdString(cmd));
            }
            if (pause_count_start != pause_check) {
                do {
                    System.out.println("pause_count_start(" + pause_count_start + ") != pause_check(" + pause_check + ")");
                    long id = resendInit();
                    pause_count_start = this.pause_count.get();
                    synchronized (this) {
                        if (!requestStatus()) {
                            throw new RuntimeException("attempt to requestStatus() failed.");
                        }
                        if (null == readerThread) {
                            readStatus();
                        }
                    }
                    boolean waitForStatusResult = waitForStatus(100, 50, pause_count_start, startingRunProgramAbortCount);
                    WaitForDoneResult wfdResult = waitForDone(id, timeout, pause_count_start);
                    if (wfdResult != WaitForDoneResult.WFD_ERROR) {
                        break;
                    }
                    Thread.sleep(100);
                    this.waitForPause(startingRunProgramAbortCount);
                    rpac = runProgramAbortCount.get();
                    if (rpac != startingRunProgramAbortCount) {
                        throw new RuntimeException("aborting : runProgramAbortCount=" + rpac + ", startingRunProgramAbortCount=" + startingRunProgramAbortCount + ", cmd=" + cmdString(cmd));
                    }
                } while (true);
            }
            pause_count_start = this.pause_count.get();
            if (null == crclSocket) {
                throw new IllegalStateException("crclSocket must not be null");
            }
            if (cmd instanceof GetStatusType) {
                showDebugMessage("Ignoring command GetStatusType inside a program.");
                return;
            }
            int currentConnectCount = connectCount.get();
            if (startingConnectCount != currentConnectCount) {
                System.err.println("Connected while testing command");
                throw new RuntimeException("currentConnectCount=" + currentConnectCount + ", startingConnectCount=" + startingConnectCount);
            }
            int currentDisconnectCount = disconnectCount.get();
            if (startingDisconnectCount != currentDisconnectCount) {
                System.err.println("Disconnected while testing command");
                throw new RuntimeException("currentDisconnectCount=" + currentDisconnectCount + ", startingDisconnectCount=" + startingDisconnectCount);
            }
            this.waitForPause(startingRunProgramAbortCount);
            rpac = runProgramAbortCount.get();
            if (rpac != startingRunProgramAbortCount) {
                throw new RuntimeException("aborting : runProgramAbortCount=" + rpac + ", startingRunProgramAbortCount=" + startingRunProgramAbortCount + ", cmd=" + cmdString(cmd));
            }
            if (null == this.getStatus()) {
                boolean waitForStatusResult = waitForStatus(2000, 200, pause_count_start, startingRunProgramAbortCount);
            }
            rpac = runProgramAbortCount.get();
            if (rpac != startingRunProgramAbortCount) {
                throw new RuntimeException("aborting : runProgramAbortCount=" + rpac + ", startingRunProgramAbortCount=" + startingRunProgramAbortCount + ", cmd=" + cmdString(cmd));
            }
            testCommandStartLengthUnitSent = lengthUnitSent;
            testCommandStartLengthUnit = this.getLengthUnit();
            CRCLStatusType startStatusPrep = this.getStatus();
            if (null == startStatusPrep) {
                System.out.println("origStatuPrep = " + origStatuPrep);
                showMessage("testCommand can not get starting status");
                throw new RuntimeException("failed to get starting status");
            }
            CRCLStatusType startStatus = copyStatus(startStatusPrep);
            lastCmdStartStatus = startStatus;
            this.testCommandStartStatus = startStatus;
            sendCommandTime = System.currentTimeMillis();
            if (!incAndSendCommand(cmd)) {
                if (pause_count_start != this.pause_count.get()) {
                    continue;
                }
                showMessage("Can not send " + cmdString + ".");
                throw new RuntimeException("incAndSendCommand failed");
            }
            curStatus = this.getStatus();
            if (cmd.getCommandID() == startStatus.getCommandStatus().getCommandID() && cmd.getCommandID() > 1) {
                String commandLogString = commandStatusLogToString();
                System.err.println("commandLogString = " + commandLogString);
                String lastCmdString = commandToSimpleString(lastCommandSent);
                String intString = this.createInterrupStackString();
                String curStatusString
                        = (curStatus != null)
                                ? getTempCRCLSocket().statusToString(curStatus, false)
                                : "null";
                String messageString
                        = "Id of command to send already matches status id. cmd=" + CRCLSocket.cmdToString(cmd) + NEW_LINE
                        + "lastWaitForDoneException=" + lastWaitForDoneException + NEW_LINE
                        + "cmd=" + cmdString + "." + NEW_LINE
                        + "lastCommandSent=" + lastCmdString + "." + NEW_LINE
                        + "testCommandStartStatus=" + getTempCRCLSocket().statusToString(startStatus, false) + "." + NEW_LINE
                        + "current status=" + curStatusString + "." + NEW_LINE
                        + "sendCommandTime=" + sendCommandTime + NEW_LINE
                        + "curTime = " + curTime + NEW_LINE
                        + "(curTime-sendCommandTime)=" + (curTime - sendCommandTime) + NEW_LINE
                        + "lastReadStatusTime = " + lastReadStatusTime + NEW_LINE
                        + "(curTime-lastReadStatusTime)=" + (curTime - lastReadStatusTime) + NEW_LINE
                        + "(lastReadStatusTime-sendCommandTime)=" + (lastReadStatusTime - sendCommandTime) + NEW_LINE
                        + "timeout=" + timeout + NEW_LINE
                        + "poseListSaveFileName=" + poseListSaveFileName + NEW_LINE
                        + "cmd.getCommandID() = " + cmd.getCommandID() + NEW_LINE
                        + ((status == null || status.getCommandStatus() == null)
                        ? "status.getCommandStatus()=null\n"
                        : ("status.getCommandStatus().getCommandID()=" + status.getCommandStatus().getCommandID() + NEW_LINE
                        + "status.getCommandStatus().getCommandState()=" + status.getCommandStatus().getCommandState() + NEW_LINE))
                        + "intString=" + intString + NEW_LINE
                        + "commandLogString = " + commandLogString + NEW_LINE;
                System.out.println(messageString);
                showErrorMessage(messageString);
            }
            sendCommandTime = System.currentTimeMillis();
            WaitForDoneResult wfdResult = waitForDone(cmd.getCommandID(), timeout, pause_count_start);
            if (cmd instanceof CrclCommandWrapper) {
                CrclCommandWrapper wrapper = (CrclCommandWrapper) cmd;
                if (wfdResult == WaitForDoneResult.WFD_DONE) {
                    wrapper.notifyOnDoneListeners();
                } else if (wfdResult == WaitForDoneResult.WFD_ERROR) {
                    wrapper.notifyOnErrorListeners();
                }
                cmd = wrapper.getWrappedCommand();
            }
            currentConnectCount = connectCount.get();
            if (startingConnectCount != currentConnectCount) {
                System.err.println("Connected while testing command");
                throw new RuntimeException("currentConnectCount=" + currentConnectCount + ", startingConnectCount=" + startingConnectCount);
            }
            currentDisconnectCount = disconnectCount.get();
            if (startingDisconnectCount != currentDisconnectCount) {
                System.err.println("Disconnected while testing command");
                throw new RuntimeException("currentDisconnectCount=" + currentDisconnectCount + ", startingDisconnectCount=" + startingDisconnectCount);
            }
            if (cmd instanceof EndCanonType) {
                return;
//                return wfdResult != WaitForDoneResult.WFD_ERROR
//                        && wfdResult != WaitForDoneResult.WFD_EXCEPTION
//                        && wfdResult != WaitForDoneResult.WFD_HOLDING_ERROR
//                        && null != curStatus
//                        && null != curStatus.getCommandStatus()
//                        && curStatus.getCommandStatus().getCommandState() != CommandStateEnumType.CRCL_ERROR;
            }
            if (wfdResult != WaitForDoneResult.WFD_DONE) {
                if (pause_count_start != this.pause_count.get()) {
                    continue;
                }
                curTime = System.currentTimeMillis();
                if (null != this.getPoseList()) {
                    File tmpFile
                            = (null != tempLogDir)
                                    ? File.createTempFile("poseList", ".csv", tempLogDir)
                                    : File.createTempFile("poseList", ".csv");
                    poseListSaveFileName = tmpFile.getCanonicalPath();
                    this.savePoseListToCsvFile(tmpFile.getCanonicalPath());
                }
                printCommandStatusLog();
                String intString = this.createInterrupStackString();
                String lastCmdString = commandToSimpleString(lastCommandSent);
                String curStatusString
                        = (curStatus != null)
                                ? getTempCRCLSocket().statusToString(curStatus, false)
                                : "null";
                JointStatusesType jointStatuses
                        = startStatus.getJointStatuses();
                List<JointStatusType> jointStatus
                        = (jointStatuses == null) ? null
                                : jointStatuses.getJointStatus();
                Map<Integer, Double> startStatusJointsMap
                        = (null == jointStatus) ? null
                                : jointStatus.stream().collect(Collectors.toMap(JointStatusType::getJointNumber, JointStatusType::getJointPosition));
                String startStatusJointsMapString
                        = (null == startStatusJointsMap) ? null
                                : startStatusJointsMap.toString();
                String messageString = cmd.getClass().getName() + ((wfdResult != WaitForDoneResult.WFD_TIMEOUT) ? " failed. " : " timed out. ") + NEW_LINE
                        + "wfdResult=" + wfdResult + NEW_LINE
                        + "lastWaitForDoneException=" + lastWaitForDoneException + NEW_LINE
                        + "cmd=" + cmdString + "." + NEW_LINE
                        + "lastCommandSent=" + lastCmdString + "." + NEW_LINE
                        + "testCommandStartStatus=" + getTempCRCLSocket().statusToString(startStatus, false) + "." + NEW_LINE
                        + "testCommandStartStatus.getPose=" + CRCLPosemath.toString(startStatus.getPoseStatus().getPose()) + "." + NEW_LINE
                        + "testCommandStartStatus.getJoints()=" + startStatusJointsMapString + "." + NEW_LINE
                        + "current status=" + curStatusString + "." + NEW_LINE
                        + "current status.getPose()=" + ((curStatus != null) ? CRCLPosemath.toString(curStatus.getPoseStatus().getPose()) : "null") + "." + NEW_LINE
                        + "current status.getJoints()=" + ((curStatus != null) ? curStatus.getJointStatuses().getJointStatus().stream().collect(Collectors.toMap(JointStatusType::getJointNumber, JointStatusType::getJointPosition)) : "null") + "." + NEW_LINE
                        + "sendCommandTime=" + sendCommandTime + NEW_LINE
                        + "curTime = " + curTime + NEW_LINE
                        + "(curTime-sendCommandTime)=" + (curTime - sendCommandTime) + NEW_LINE
                        + "lastReadStatusTime = " + lastReadStatusTime + NEW_LINE
                        + "(curTime-lastReadStatusTime)=" + (curTime - lastReadStatusTime) + NEW_LINE
                        + "(lastReadStatusTime-sendCommandTime)=" + (lastReadStatusTime - sendCommandTime) + NEW_LINE
                        + "timeout=" + timeout + NEW_LINE
                        + "poseListSaveFileName=" + poseListSaveFileName + NEW_LINE
                        + "cmd.getCommandID() = " + cmd.getCommandID() + NEW_LINE
                        + ((startStatus == null || startStatus.getCommandStatus() == null)
                        ? "startStatus.getCommandStatus()=null\n"
                        : ("startStatus.getCommandStatus().getCommandID()=" + startStatus.getCommandStatus().getCommandID() + NEW_LINE
                        + "startStatus.getCommandStatus().getCommandState()=" + startStatus.getCommandStatus().getCommandState() + NEW_LINE))
                        + ((curStatus == null || curStatus.getCommandStatus() == null)
                        ? "status.getCommandStatus()=null\n"
                        : ("status.getCommandStatus().getCommandID()=" + curStatus.getCommandStatus().getCommandID() + NEW_LINE
                        + "status.getCommandStatus().getCommandState()=" + curStatus.getCommandStatus().getCommandState() + NEW_LINE))
                        + "intString=" + intString + NEW_LINE;
                System.out.println(messageString);
                showErrorMessage(messageString);
                if (debugInterrupts || printDetailedCommandFailureInfo) {
                    SimServerInner.printAllClientStates(System.err);
                    Thread.getAllStackTraces().entrySet().forEach((x) -> {
                        System.err.println("Thread:" + x.getKey().getName());
                        Arrays.stream(x.getValue()).forEach((xx) -> {
                            System.err.println("\t" + xx);
                        });
                        System.err.println("");
                    });
                    System.err.println("intString=" + intString);
                }
                throw new RuntimeException("wfdResult="+wfdResult+", messageString="+messageString);
            }
        } while (pause_count_start != this.pause_count.get());

        boolean effectOk = testCommandEffect(cmd, sendCommandTime);
        if (!effectOk) {
            String intString = this.createInterrupStackString();
            String lastCmdString = commandToSimpleString(lastCommandSent);
            String commandLogString = commandStatusLogToString();
            System.err.println("commandLogString = " + commandLogString);
            String curStatusString
                    = (curStatus != null)
                            ? getTempCRCLSocket().statusToString(curStatus, false)
                            : "null";
            String messageString = cmd.getClass().getName() + " testCommandEffect failed " + NEW_LINE
                    + "cmd=" + cmdString + "." + NEW_LINE
                    + "lastCommandSent=" + lastCmdString + "." + NEW_LINE
                    + "effectFailedMessage=" + effectFailedMessage + "." + NEW_LINE
                    + "origStatus=" + getTempCRCLSocket().statusToString(origStatus, false) + "." + NEW_LINE
                    + "lastCmdStartStatus=" + getTempCRCLSocket().statusToString(lastCmdStartStatus, false) + "." + NEW_LINE
                    + "current status=" + curStatusString + "." + NEW_LINE
                    + "sendCommandTime=" + sendCommandTime + NEW_LINE
                    + "curTime = " + curTime + NEW_LINE
                    + "(curTime-sendCommandTime)=" + (curTime - sendCommandTime) + NEW_LINE
                    + "timeout=" + timeout + NEW_LINE
                    + "poseListSaveFileName=" + poseListSaveFileName + NEW_LINE
                    + "cmd.getCommandID() = " + cmd.getCommandID() + NEW_LINE
                    + ((status == null || status.getCommandStatus() == null)
                    ? "status.getCommandStatus()=null\n"
                    : ("status.getCommandStatus().getCommandID()=" + status.getCommandStatus().getCommandID() + NEW_LINE
                    + "status.getCommandStatus().getCommandState()=" + status.getCommandStatus().getCommandState() + NEW_LINE))
                    + "intString=" + intString + NEW_LINE
                    + "commandLogString = " + commandLogString + NEW_LINE;
            System.out.println(messageString);
            showErrorMessage(messageString);
            throw new RuntimeException("testCommandEffect returned false : messageString="+messageString);
        }
        return;
    }

    final static private AtomicInteger runProgramThreadCount = new AtomicInteger();

    private final ExecutorService defaultRunProgramService
            = Executors.newSingleThreadExecutor(new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r, "PendantClientInner.runProgram" + runProgramThreadCount.incrementAndGet());
                    thread.setDaemon(true);
                    return thread;
                }
            });

    private ExecutorService runProgramService = defaultRunProgramService;

    public ExecutorService getRunProgramService() {
        return runProgramService;
    }

    public void setRunProgramService(ExecutorService runProgramService) {
        this.runProgramService = runProgramService;
    }

    public void resetRunProgramServiceToDefault() {
        runProgramService = defaultRunProgramService;
    }

    private volatile long lastStartRunProgramThreadCommandId = -1;
    private volatile long lastStartRunProgramThreadProgId = -1;
    private volatile int lastStartRunProgramThreadStartLine = -1;

    @Nullable
    private volatile XFuture<Boolean> runProgramFuture = null;

    public XFuture<Boolean> startRunProgramThread(final int startLine) {

        if (null == program) {
            throw new IllegalStateException("program is null");
        }
        if (this.isBlockPrograms()) {
            printStartBlockingProgramInfo();
            showErrorMessage("Block Programs");
            throw new IllegalStateException("Block Programs");
        }
        if (startLine < 0 && startLine != -2) {
            throw new IllegalArgumentException("startLine=" + startLine + " (must be atleast 0)");
        }
        final boolean startingPaused = paused;
        final int startingLastProgramIndex = lastProgramIndex;
        final String startLastProgramName = lastProgramName;
        if (startingPaused) {
            if (crclClientErrorMessage == null || crclClientErrorMessage.length() < 1) {
                showErrorMessage("startRunProgramThread called when paused.");
            }
            throw new IllegalStateException("startRunProgramThread called when paused.");
        }

        if (startingLastProgramIndex > startLine + 2 && startLine > 2) {
            showErrorMessage("programIndex moving backwards: " + startingLastProgramIndex + ">" + startLine);
            throw new IllegalStateException("programIndex moving backwards: " + startingLastProgramIndex + ">" + startLine + ": lastProgramName= " + startLastProgramName);
        }
        CRCLProgramType prog = this.program;
        if (null == prog) {
            showErrorMessage("startRunProgramThread: program is null :  startLine = " + startLine);
            throw new IllegalStateException("startRunProgramThread: program is null :  startLine = " + startLine);
        }
        if (startingLastProgramIndex > startLine + 2 && startLine >= 0 && null != startLastProgramName && startLastProgramName.equals(prog.getName())) {
            showErrorMessage("programIndex moving backwards: " + startingLastProgramIndex + ">" + startLine + ": lastProgramName= " + startLastProgramName);
            throw new IllegalStateException("programIndex moving backwards: " + startingLastProgramIndex + ">" + startLine);
        }

//        checkProgIds(program);
        lastStartRunProgramThreadStartLine = startLine;
        long id = commandId.get();
        lastStartRunProgramThreadCommandId = id;
        InitCanonType initCmd = prog.getInitCanon();
        if (startLine == 0) {
            long progId = initCmd.getCommandID();
            System.out.println("startRunProgramThread(startLine = " + startLine + ") :id = " + id + ", program.getName() = " + prog.getName() + ", progId = " + progId);
            lastStartRunProgramThreadProgId = progId;
            if (Math.abs(id - progId) > 3 && id > 3) {
                System.err.println("Math.abs(id-progId)>3: progId=" + progId + ", id=" + id);
            }
        } else if (startLine < prog.getMiddleCommand().size() && startLine > 0) {
            long progId = prog.getMiddleCommand().get(startLine - 1).getCommandID();
            System.out.println("startRunProgramThread(startLine = " + startLine + ") :id = " + id + ", program.getName() = " + prog.getName() + ", progId = " + progId);
            lastStartRunProgramThreadProgId = progId;
            if (Math.abs(id - progId) > 3 && id > 3) {
                System.err.println("Math.abs(id-progId)>3: progId=" + progId + ", id=" + id + ", startLine=" + startLine);
            }
        }
        this.closeTestProgramThread();
        final StackTraceElement[] callingStackTrace = Thread.currentThread().getStackTrace();

        final boolean nextCheckedPaused = paused;
        final int nextCheckedLastProgramIndex = lastProgramIndex;
        final String nextCheckedLastProgramName = lastProgramName;
        if (nextCheckedPaused) {
            if (crclClientErrorMessage == null || crclClientErrorMessage.length() < 1) {
                showErrorMessage("startRunProgramThread called when paused.");
            }
            throw new IllegalStateException("startRunProgramThread called when paused.");
        }

        if (nextCheckedLastProgramIndex > startLine + 2 && startLine > 2) {
            showErrorMessage("programIndex moving backwards: " + nextCheckedLastProgramIndex + ">" + startLine);
            throw new IllegalStateException("programIndex moving backwards: " + nextCheckedLastProgramIndex + ">" + startLine);
        }

        if (startingLastProgramIndex > startLine + 2 && startLine >= 0 && null != nextCheckedLastProgramName && nextCheckedLastProgramName.equals(prog.getName())) {
            showErrorMessage("programIndex moving backwards: " + startingLastProgramIndex + ">" + startLine + ": lastProgramName= " + nextCheckedLastProgramName);
            throw new IllegalStateException("programIndex moving backwards: " + startingLastProgramIndex + ">" + startLine);
        }

        if (lastProgramIndex > lastShowCurrentProgramLine + 2 && startLine == -2 && null != lastProgramName && lastProgramName.equals(prog.getName())) {
            showErrorMessage("programIndex moving backwards: " + lastProgramName + ">" + startLine + ": lastProgramName= " + lastProgramName);
            throw new IllegalStateException("programIndex moving backwards: " + lastProgramName + ">" + startLine);
        }

        if (this.isBlockPrograms()) {
            printStartBlockingProgramInfo();
            throw new IllegalStateException("Block Programs");
        }
        final CRCLProgramType progFinal = prog;
        runProgramFuture
                = XFuture.supplyAsync("startRunProgramThread(" + startLine + ":" + progFinal.getName() + ").socket=" + getCRCLSocket(), () -> {
//                    this.closeTestProgramThread();
                    return runProgram(progFinal, startLine, callingStackTrace, null);
                },
                        runProgramService);
//        Thread rtpt = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                future.complete(runProgram(program, startLine, callingStackTrace, future));
//
//            }
//
//        }, "PendantClientInner.runProgram");
//        rtpt.start();
//        runTestProgramThread.set(rtpt);
        return runProgramFuture;
    }

    public void startRunTestThread(final Map<String, String> testProperties) {
//        this.closeTestProgramThread();
//        Thread rtpt = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                runTest(testProperties);
//            }
//
//        }, "PendantClientInner.runTest");
//        rtpt.start();
//        this.runTestProgramThread.set(rtpt);
        if (null != runProgramFuture) {
            runProgramFuture.cancelAll(true);
        }
        runProgramFuture = XFuture.supplyAsync("startRunTestThread", () -> runTest(testProperties), runProgramService);
    }

    public int getPoll_ms() {
        return poll_ms;
    }

    public void setPoll_ms(int poll_ms) {
        this.poll_ms = poll_ms;
    }

    public long getCmdId() {
        return commandId.get();
    }

    public double getJogIncrement() {
        return jogIncrement;
    }

    public void setJogIncrement(double jogIncrement) {
        this.jogIncrement = jogIncrement;
    }

    public void resetPrefs() {
        jogIncrement = JOG_INCREMENT_DEFAULT;
        xyzJogIncrement = XYZ_JOG_INCREMENT_DEFAULT;
        jogJointSpeed = JOG_JOINT_SPEED_DEFAULT;
        jogTransSpeed = JOG_TRANS_SPEED_DEFAULT;
        jogInterval = JOG_INTERVAL_DEFAULT;
    }

    public double getXyzJogIncrement() {
        return xyzJogIncrement;
    }

    public void setXyzJogIncrement(double xyzJogIncrement) {
        this.xyzJogIncrement = xyzJogIncrement;
    }

    public int getJogInterval() {
        return jogInterval;
    }

    public void setJogInterval(int jogInterval) {
        this.jogInterval = jogInterval;
    }

    public static interface TrySupplier<T> {

        public T tryGet() throws Throwable;

    }

    public boolean checkPose(PoseType goalPose) {
        return checkPose(goalPose, false);
    }

    public boolean checkPose(PoseType goalPose, boolean ignoreCartTran) {
        PointType point = goalPose.getPoint();

        if (!ignoreCartTran) {
            if (point.getX() > maxLimit.x) {
                showMessage("Bad postion : point.getX()=" + point.getX() + "  exceeds maxLimit.x=" + maxLimit.x);
                setCommandState(CommandStateEnumType.CRCL_ERROR);
                return false;
            }
            if (point.getY() > maxLimit.y) {
                showMessage("Bad postion : point.getY()=" + point.getY() + "  exceeds maxLimit.y=" + maxLimit.y);
                setCommandState(CommandStateEnumType.CRCL_ERROR);
                return false;
            }

            if (point.getZ() > maxLimit.z) {
                showMessage("Bad postion : point.getZ()=" + point.getZ() + "  exceeds maxLimit.z=" + maxLimit.z);
                setCommandState(CommandStateEnumType.CRCL_ERROR);
                return false;
            }

            if (point.getX() < minLimit.x) {
                showMessage("Bad postion : point.getX()=" + point.getX() + "  exceeds minLimit.x=" + minLimit.x);
                setCommandState(CommandStateEnumType.CRCL_ERROR);
                return false;
            }
            if (point.getY() < minLimit.y) {
                showMessage("Bad postion : point.getY()=" + point.getY() + "  exceeds minLimit.y=" + minLimit.y);
                setCommandState(CommandStateEnumType.CRCL_ERROR);
                return false;
            }
            if (point.getZ() < minLimit.z) {
                showMessage("Bad postion : point.getZ()=" + point.getZ() + "  exceeds minLimit.z=" + minLimit.z);
                setCommandState(CommandStateEnumType.CRCL_ERROR);
                return false;
            }
        }
        VectorType goalXAxis = requireNonNull(goalPose.getXAxis(), "goalPose.getXAxis()");
        PmCartesian xvec = vectorToPmCartesian(goalXAxis);
        if (Math.abs(xvec.mag() - 1.0) > 1e-3) {
            showMessage("Bad postion : xvec " + xvec + " has magnitude not equal to one.");
            setCommandState(CommandStateEnumType.CRCL_ERROR);
            return false;
        }
        VectorType goalZAxis = requireNonNull(goalPose.getZAxis(), "goalPose.getZAxis()");
        PmCartesian zvec = vectorToPmCartesian(goalZAxis);
        if (Math.abs(zvec.mag() - 1.0) > 1e-3) {
            showMessage("Bad postion : zvec " + zvec + " has magnitude not equal to one.");
            setCommandState(CommandStateEnumType.CRCL_ERROR);
            return false;
        }
        if (Math.abs(Posemath.pmCartCartDot(xvec, zvec)) > 1e-3) {
            showMessage("Bad postion : xvec " + xvec + " and zvec " + zvec + " are not orthogonal.");
            setCommandState(CommandStateEnumType.CRCL_ERROR);
            return false;
        }
        return true;
    }

}
