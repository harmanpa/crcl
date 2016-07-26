/*
 * This software is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain. NIST Real-Time Control System software is an experimental
 * system. NIST assumes no responsibility whatsoever for its use by other
 * parties, and makes no guarantees, expressed or implied, about its
 * quality, reliability, or any other characteristic. We would appreciate
 * acknowledgement if the software is used. This software can be
 * redistributed and/or modified freely provided that any derivative works
 * bear some notice that they are derived from it, and any modified
 * versions bear some notice that they have been modified.
 * 
 */
package crcl.ui.client;

import crcl.base.ActuateJointType;
import crcl.base.ActuateJointsType;
import crcl.base.AngleUnitEnumType;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLProgramType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStateEnumType;
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
import crcl.exi.CrclExiSocket;
import crcl.ui.misc.MultiLineStringJPanel;
import crcl.ui.server.SimServerInner;
import crcl.utils.AnnotatedPose;
import crcl.utils.CRCLPosemath;
import crcl.utils.CRCLSocket;
import crcl.utils.CRCLException;
import crcl.utils.outer.interfaces.PendantClientOuter;
import crcl.utils.PoseToleranceChecker;
import crcl.utils.XpathUtils;
import crcl.utils.outer.interfaces.PendantClientMenuOuter;
import java.awt.Desktop;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.SocketException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
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
    public static final BigDecimal BIG_DECIMAL_FIVE_DEGREES_IN_RADIANS = BigDecimal.valueOf(Math.PI / 36);
    public static final Logger LOGGER = Logger.getLogger(PendantClientInner.class.getName());
    public static final String PROP_LENGTHUNIT = "lengthUnit";

    private static long getLongProperty(String propName, long defaultLong) {
        return Long.parseLong(System.getProperty(propName, Long.toString(defaultLong)));
    }

    public static <T> Optional<T> tryGet(TrySupplier<T> ts) {
        try {
            return Optional.of(ts.tryGet());
        } catch (Throwable ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return Optional.empty();
    }

    public static Optional<JointStatusType> getJointStatus(CRCLStatusType status, BigInteger id) {
        return Optional.ofNullable(status)
                .map((x) -> x.getJointStatuses())
                .filter((x) -> x != null)
                .map((x) -> x.getJointStatus())
                .flatMap((x) -> x.stream().filter((x2) -> x2.getJointNumber().compareTo(id) == 0).findAny());
    }

    public static Stream<JointStatusType> getJointValues(CRCLStatusType status,
            Collection<BigInteger> ids) {
        return ids.stream()
                .map((x) -> getJointStatus(status, x))
                .flatMap((x) -> x.map(Stream::of).orElseGet(Stream::empty));
    }

    public static String getJointString(CRCLStatusType status,
            Function<JointStatusType, String> mapper,
            Collection<BigInteger> ids) {
        return getJointValues(status, ids)
                .map(mapper)
                .collect(Collectors.joining(","));
    }

    private Thread runTestProgramThread = null;

    private CRCLStatusType status;
    private CrclExiSocket crclSocket = null;

    private final PendantClientOuter outer;
    private static final double JOG_INCREMENT_DEFAULT = 3.0;
    private double jogIncrement = JOG_INCREMENT_DEFAULT;
    private CRCLProgramType program;

    private PoseToleranceType expectedEndPoseTolerance = new PoseToleranceType();
    private PoseToleranceType expectedIntermediatePoseTolerance;
    private volatile AtomicInteger close_test_count = new AtomicInteger(0);
    int request_status_count = 0;
    private final GetStatusType getStatusMsg = new GetStatusType();
    private static final int JOG_INTERVAL_DEFAULT=50;
    private int jogInterval = JOG_INTERVAL_DEFAULT;
    private static final double XYZ_JOG_INCREMENT_DEFAULT=3.0;
    private double xyzJogIncrement = XYZ_JOG_INCREMENT_DEFAULT;
    private int poll_ms = jogInterval;
    private BigInteger cmdId = BigInteger.ONE;
    private final XpathUtils xpu;
    private CRCLSocket checkerCRCLSocket = null;
    private CRCLCommandInstanceType checkerCommandInstance = null;
    private final Predicate<CRCLProgramType> checkProgramValidPredicate
            = this::checkProgramValid;
    private final Predicate<CRCLCommandType> checkCommandValidPredicate
            = this::checkCommandValid;
//    private File[] cmdSchemaFiles = null;
//    private File[] programSchemaFiles = null;
    private CRCLCommandType lastCommandSent = null;
    private CRCLCommandType prevLastCommandSent = null;
    private boolean recordCommands = true;
    final private Queue<CRCLCommandType> recordedCommandsQueue
            = new ConcurrentLinkedQueue<>();
    private final List<CRCLCommandType> recordedCommandsList = new ArrayList<>();
    ;
    private long waitForDoneDelay = getLongProperty("PendantClient.waitForDoneDelay", 100);
    Thread readerThread = null;
    private String lastDescription;
    final private List<AnnotatedPose> poseList = new ArrayList<>();
    private Queue<AnnotatedPose> poseQueue = null;
    private boolean disconnecting = false;
    private boolean stopStatusReaderFlag = false;
    private BigDecimal jointTol = BigDecimal.valueOf(jogIncrement / 5.0);
    private AngleUnitEnumType angleType = AngleUnitEnumType.RADIAN;
    private LengthUnitEnumType lengthUnit = LengthUnitEnumType.MILLIMETER;
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private Map<Integer, ConfigureJointReportType> cjrMap = null;
    private volatile BlockingQueue<Object> pauseQueue = new ArrayBlockingQueue<>(1);
    private volatile boolean paused = false;
    private volatile AtomicInteger waiting_for_pause_queue = new AtomicInteger(0);
    private volatile AtomicInteger pause_count = new AtomicInteger(0);
    long programCommandStartTime;
    private volatile boolean stepMode = false;
    private boolean quitOnTestCommandFailure = !Boolean.getBoolean("crcl4java.client.continueOnTestCommandFailure");
    long runStartMillis = 0;
    long runEndMillis = 0;
    private BigDecimal jointMoveAccel = null;
    private BigDecimal jointMoveSpeed = null;
    private CRCLStatusType testCommandStartStatus = null;
    private boolean lengthUnitSent = false;
    private LengthUnitEnumType testCommandStartLengthUnit = null;
    boolean testCommandStartLengthUnitSent = false;

    private double jogRotSpeed = 3.0;
    
    private PendantClientMenuOuter menuOuter() {
        if(null == outer) {
            throw new IllegalStateException("PendantClientOuter not set.");
        }
        if(null == outer.getMenuOuter()) {
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

    private static final double JOG_TRANS_SPEED_DEFAULT=100.0;
    
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

    public PendantClientInner(PendantClientOuter outer) throws ParserConfigurationException {
        this.outer = outer;
        this.xpu = new XpathUtils();
        this.expectedEndPoseTolerance = new PoseToleranceType();
        this.expectedEndPoseTolerance.setXAxisTolerance(BIG_DECIMAL_FIVE_DEGREES_IN_RADIANS);
        this.expectedEndPoseTolerance.setZAxisTolerance(BIG_DECIMAL_FIVE_DEGREES_IN_RADIANS);
        this.expectedEndPoseTolerance.setXPointTolerance(BigDecimal.ONE);
        this.expectedEndPoseTolerance.setYPointTolerance(BigDecimal.ONE);
        this.expectedEndPoseTolerance.setZPointTolerance(BigDecimal.ONE);

        this.expectedIntermediatePoseTolerance = new PoseToleranceType();
        this.expectedIntermediatePoseTolerance.setXAxisTolerance(BIG_DECIMAL_FIVE_DEGREES_IN_RADIANS);
        this.expectedIntermediatePoseTolerance.setZAxisTolerance(BIG_DECIMAL_FIVE_DEGREES_IN_RADIANS);
        this.expectedIntermediatePoseTolerance.setXPointTolerance(BigDecimal.ONE);
        this.expectedIntermediatePoseTolerance.setYPointTolerance(BigDecimal.ONE);
        this.expectedIntermediatePoseTolerance.setZPointTolerance(BigDecimal.ONE);
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
    public CRCLProgramType getProgram() {
        return program;
    }

    /**
     * Set the value of program
     *
     * @param program new value of program
     */
    public void setProgram(CRCLProgramType program) {
        this.program = program;
    }

    public CRCLSocket getCRCLSocket() {
        return this.crclSocket;
    }

    public void closeTestProgramThread() {
        close_test_count.incrementAndGet();
        if (null != runTestProgramThread) {
            if (runTestProgramThread.equals(Thread.currentThread())) {
                return;
            }
            this.runTestProgramThread.interrupt();
            try {
                this.runTestProgramThread.join(100);
            } catch (InterruptedException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
            this.runTestProgramThread = null;
        }
    }

    public boolean isDone(BigInteger minCmdId) {
        return this.status != null
                && this.status.getCommandStatus() != null
                && this.status.getCommandStatus().getCommandID() != null
                && this.status.getCommandStatus().getCommandID().compareTo(minCmdId) == 0
                && this.status.getCommandStatus().getCommandState() != CommandStateEnumType.CRCL_WORKING;
    }

    public boolean requestStatus() throws JAXBException {
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(PendantClientInner.class.getName()).log(Level.SEVERE, null, ex);
//        }
        request_status_count++;
        LOGGER.log(Level.FINEST, () -> "PendantClientInner.requestStatus() : request_status_count=" + request_status_count);
        boolean result = false;
        synchronized (this) {
            if (null == cmdId || cmdId.compareTo(BigInteger.ONE) < 0) {
                cmdId = BigInteger.ONE;
            }
            getStatusMsg.setCommandID(cmdId);
            result = this.sendCommand(getStatusMsg);
        }
        LOGGER.log(Level.FINEST, () -> "PendantClientInner.requestStatus() : returning from RequestStatus() " + request_status_count);
        return result;
    }

    private PrintStream logStream = null;

    public void openLogStream() {
        try {
            logFile = File.createTempFile("crcl.client.", ".log.txt");
            logStream = new PrintStream(new FileOutputStream(logFile));
        } catch (IOException ex) {
            Logger.getLogger(PendantClientInner.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void showMessage(Throwable t) {
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

    public Predicate<CRCLProgramType> getCheckProgramValidPredicate() {
        return checkProgramValidPredicate;
    }

    public Predicate<CRCLCommandType> getCheckCommandValidPredicate() {
        return checkCommandValidPredicate;
    }

    public boolean checkProgramValid(CRCLProgramType prog) {
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
        return false;
    }

    public CRCLSocket getTempCRCLSocket() {
        if (null != checkerCRCLSocket) {
            return checkerCRCLSocket;
        }
        return (checkerCRCLSocket = new CRCLSocket());
    }

    public boolean checkCommandValid(CRCLCommandType cmdObj) {
        try {
            if (null == checkerCommandInstance) {
                checkerCommandInstance = new CRCLCommandInstanceType();
            }
            checkerCommandInstance.setCRCLCommand(cmdObj);
            String s = getTempCRCLSocket().commandInstanceToPrettyString(checkerCommandInstance, true);
            if (cmdObj instanceof MoveThroughToType) {
                MoveThroughToType mtt = (MoveThroughToType) cmdObj;
                int num_positions = mtt.getNumPositions().intValue();
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
        return false;
    }

    public synchronized void setStatSchema(File[] fa) {
        try {
            CRCLSocket.filesToDefaultStatSchema(fa);
            if (null != this.crclSocket) {
                this.crclSocket.setStatSchema(CRCLSocket.getDefaultStatSchema());
            }
        } catch (CRCLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

//    public String getDocumentation(String name) throws SAXException, IOException, XPathExpressionException, ParserConfigurationException {
//        return xpu.queryXml(cmdSchemaFiles, "/schema/complexType[@name=\""+name+"\"]/annotation/documentation/text()");
//    }
    public void setCmdSchema(File[] fa) {
        try {
            CRCLSocket.filesToDefaultCmdSchema(fa);
            if (null != this.crclSocket) {
                this.crclSocket.setStatSchema(CRCLSocket.getDefaultCmdSchema());
            }
//            cmdSchemaFiles = fa;
        } catch (CRCLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void setProgramSchema(File[] fa) {
        try {
            CRCLSocket.filesToDefaultProgramSchema(fa);
            if (null != this.crclSocket) {
                this.crclSocket.setProgramSchema(CRCLSocket.getDefaultProgramSchema());
            }
        } catch (CRCLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public File[] getCmdSchemaFiles() {
        return CRCLSocket.getDefaultCmdSchemaFiles();
    }

    public File[] getProgramSchemaFiles() {
        return CRCLSocket.getDefaultProgramSchemaFiles();
    }

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

    public void openXmlProgramFile(File f, boolean addRecent) throws SAXException, IOException, CRCLException, XPathExpressionException, ParserConfigurationException {

        if (null != logStream) {
            try {
                logStream.flush();
                logStream.close();
            } catch (Exception e) {
                Logger.getLogger(PendantClientInner.class.getName()).log(Level.FINEST, "", e);
            }
            logStream = null;
        }
        String s = this.xpu.queryXml(f, "/");
        CRCLSocket cs = getTempCRCLSocket();
        CRCLProgramType program
                = cs.stringToProgram(s, menuOuter().validateXmlSelected());
        if (null == program.getName() || program.getName().length() < 1) {
            String fname = f.getName();
            if (fname.endsWith(".xml")) {
                fname = fname.substring(0, fname.length() - 4);
            }
            program.setName(fname);
        }
        this.setProgram(program);
        logFile = File.createTempFile("crcl.client." + f.getName() + ".", ".log.txt");
        logStream = new PrintStream(new FileOutputStream(logFile));
        outer.showDebugMessage("Logging to " + logFile.getCanonicalPath());
        outer.finishOpenXmlProgramFile(f, program, addRecent);
        setOutgoingProgramFile(f.getName());
//        cmdId = cmdId.add(BigInteger.ONE);f
//        cmd.setCommandID(cmdId);
//        this.sendCommand(cmd);
//        this.saveRecentCommand(cmd);
    }

    public void saveXmlProgramFile(File f) throws CRCLException {
        CRCLSocket cs = getTempCRCLSocket();
        if (null == program.getName() || program.getName().length() < 1) {
            String fname = f.getName();
            if (fname.endsWith(".xml")) {
                fname = fname.substring(0, fname.length() - 4);
            }
            program.setName(fname);
        }
        String str
                = cs.programToPrettyString(program, menuOuter().validateXmlSelected());
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

    private BigInteger lastCommandIdSent = null;

    private boolean sendCommandPrivate(CRCLCommandType cmd) {
        try {
            if (null == crclSocket) {
                showMessage("Can not send command when not connected.");
                return false;
            }
            CRCLCommandInstanceType cmdInstance
                    = new CRCLCommandInstanceType();
            cmdInstance.setCRCLCommand(cmd);
            if (!(cmdInstance.getCRCLCommand() instanceof GetStatusType)) {
                prevLastCommandSent = lastCommandSent;
                lastCommandSent = cmdInstance.getCRCLCommand();
                if (recordCommands) {
                    recordedCommandsQueue.add(cmd);
                }
                if (null != outgoingProgramIndex) {
                    cmdInstance.setProgramFile(outgoingProgramFile);
                }
                if (null != outgoingProgramFile) {
                    cmdInstance.setProgramIndex(outgoingProgramIndex);
                    cmdInstance.setProgramLength(outgoingProgramLength);
                }
            }
            BigInteger id = cmd.getCommandID();
            crclSocket.writeCommand(cmdInstance, menuOuter().validateXmlSelected());
            lastCommandIdSent = id;
            return true;
        } catch (CRCLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            showMessage(ex);
        }
        return false;
    }

    private void incCommandID(CRCLCommandType cmd) {
        if (null == cmdId) {
            cmdId = BigInteger.ONE;
        }
        if (null != cmd.getCommandID()) {
            cmdId = cmd.getCommandID();
        }
        List<Long> usedIds = new ArrayList<>();
        if (null != status
                && null != status.getCommandStatus()
                && null != status.getCommandStatus().getCommandID()) {
            usedIds.add(status.getCommandStatus().getCommandID().longValue());
        }
        if (null != lastCommandIdSent) {
            usedIds.add(lastCommandIdSent.longValue());
        }
        final long cmdIdInt = cmdId.longValue();
        if (null == cmd.getCommandID() || usedIds.stream().anyMatch(i -> i == cmdIdInt)) {
            long newCmdIdInt = usedIds.stream().mapToLong(i -> i).max().orElse(cmdIdInt) + 1;
            cmd.setCommandID(BigInteger.valueOf(newCmdIdInt));
        }
    }

    public boolean incAndSendCommand(CRCLCommandType cmd) throws JAXBException {
        this.incCommandID(cmd);
        return sendCommand(cmd);
    }

    public boolean sendCommand(CRCLCommandType cmd) throws JAXBException {
        if (null == cmd) {
            throw new IllegalArgumentException("cmd can not be null");
        }
        if (null == this.crclSocket) {
            throw new IllegalStateException("crclSocket must not be null.");
        }
        if (!(cmd instanceof GetStatusType) && menuOuter().isDebugSendCommandSelected()) {
            showDebugMessage("PendantClientInner.sendCommand() : cmd = " + cmdString(cmd));
        }
        if (cmd instanceof SetAngleUnitsType) {
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
            if (null == cjrMap) {
                cjrMap = new HashMap<>();
            }
            for (ConfigureJointReportType cjr : cjrs.getConfigureJointReport()) {
                cjrMap.put(cjr.getJointNumber().intValue(), cjr);
            }
        } else if (cmd instanceof SetEndEffectorType) {
            this.setHoldingObjectExpected(false);
            holdingErrorOccured = false;
        }
        boolean ret = this.sendCommandPrivate(cmd);

        if (!(cmd instanceof GetStatusType)) {
            if (menuOuter().isDebugSendCommandSelected()) {
                showDebugMessage("PendantClientInner.sendCommand() : ret = " + ret);
            }
        } else if (getCommandState().filter(st -> st == CommandStateEnumType.CRCL_DONE).isPresent()) {
            setCommandState(CommandStateEnumType.CRCL_WORKING);
        }
        return ret;
    }

    public Optional<CommandStateEnumType> getCommandState() {
        if (null != status) {
            CommandStatusType commandStatus = status.getCommandStatus();
            if (null != commandStatus) {
                return Optional.of(commandStatus.getCommandState());
            }
        }
        return Optional.empty();
    }

    public void setCommandState(CommandStateEnumType state) {
        if (null != status) {
            CommandStatusType commandStatus = status.getCommandStatus();
            if (null != commandStatus) {
                commandStatus.setCommandState(state);
            } else {
                commandStatus = new CommandStatusType();
                commandStatus.setCommandID(BigInteger.ONE);
                commandStatus.setCommandState(state);
            }
        }
    }

    public void abort() {
        try {
            this.closeTestProgramThread();
            unpause();
            stopMotion(StopConditionEnumType.FAST);
        } catch (JAXBException ex) {
            Logger.getLogger(PendantClientInner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param stopType the value of stopType
     */
    public void stopMotion(StopConditionEnumType stopType) throws JAXBException {
        StopMotionType stop = new StopMotionType();
        stop.setStopCondition(stopType);
        this.incAndSendCommand(stop);
    }

    public boolean waitForStatus(long timeoutMilliSeconds, long delay) throws InterruptedException, JAXBException {
        long start = System.currentTimeMillis();
        int old_pause_count = this.pause_count.get();
        while (null == this.getStatus() && !Thread.currentThread().isInterrupted()) {
            if (timeoutMilliSeconds >= 0
                    && System.currentTimeMillis() - start > timeoutMilliSeconds) {
                return false;
            }
            if (delay > 0) {
                Thread.sleep(delay);
            }
            if (!requestStatus()) {
                return false;
            }
            if (null == readerThread) {
                readStatus();
            }
            if (this.pause_count.get() != old_pause_count || this.paused) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param minCmdId the value of minCmdId
     * @param timeoutMilliSeconds the value of timeoutMilliSeconds
     * @return the boolean
     * @throws InterruptedException when Thread interrupted
     */
    public boolean waitForDone(BigInteger minCmdId, long timeoutMilliSeconds)
            throws InterruptedException, JAXBException {

        boolean returnDone = false;
        try {
            if (menuOuter().isDebugWaitForDoneSelected()) {
                showDebugStatus();
            }
            long start = System.currentTimeMillis();
            long timeDiff = System.currentTimeMillis() - start;
            int old_pause_count = this.pause_count.get();
            final long fullTimeout = timeoutMilliSeconds
                    + ((waitForDoneDelay > 0) ? 2 * waitForDoneDelay : 0);
            while (!Thread.currentThread().isInterrupted()
                    && !isDone(minCmdId)
                    && timeDiff < fullTimeout) {
                if (holdingErrorOccured) {
                    return false;
                }
                if (menuOuter().isDebugWaitForDoneSelected()) {
                    showDebugStatus();
                    showDebugMessage("PendantClient waitForDone(" + minCmdId + ") timeDiff = " + timeDiff + " / " + timeoutMilliSeconds + " = " + ((double) timeDiff) / timeoutMilliSeconds);
                }
                if (waitForDoneDelay > 0) {
                    Thread.sleep(waitForDoneDelay);
                }
                if (!requestStatus()) {
                    return false;
                }
                if (null == readerThread) {
                    readStatus();
                }
                if (this.pause_count.get() != old_pause_count || this.paused) {
                    return false;
                }
                timeDiff = System.currentTimeMillis() - start;
            }
            if (menuOuter().isDebugWaitForDoneSelected()) {
                showDebugMessage("PendantClient waitForDone(" + minCmdId + ") returning");
                showDebugStatus();
                showDebugMessage("PendantClient timeDiff = " + timeDiff + " / " + timeoutMilliSeconds + " = " + ((double) timeDiff) / timeoutMilliSeconds);
                if (timeDiff >= timeoutMilliSeconds) {
                    showMessage("Timed out waiting for DONE.");
                }
            }
            returnDone = !Thread.currentThread().isInterrupted()
                    && isDone(minCmdId);
        } catch (NullPointerException ex) {
            // Ugly hack hoping to catch strange debugging problem.
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return returnDone;
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

    public CRCLStatusType getStatus() {
        return this.status;
    }

    public PoseType getPose() {
        return CRCLPosemath.getPose(status);
    }

    public PointType getPoint() {
        return CRCLPosemath.getPoint(status);
    }

    public List<AnnotatedPose> getPoseList() {
        if (null != poseQueue) {
            synchronized (poseList) {
                AnnotatedPose ap = null;
                while (null != (ap = poseQueue.poll())) {
                    poseList.add(ap);
                }
            }
        }
        return Collections.unmodifiableList(poseList);
    }

    @SuppressWarnings("unchecked")
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
        final Set<BigInteger> jointIds = new TreeSet<>();
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
                        Stream stream = Stream.builder()
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

    public void readStatus() {
        try {
            if (menuOuter().replaceStateSelected()) {
                crclSocket.setStatusStringInputFilter(CRCLSocket.addCRCLToState);
            } else {
                crclSocket.setStatusStringInputFilter(null);
            }
            final CRCLStatusType curStatus
                    = crclSocket.readStatus(menuOuter().validateXmlSelected());
            if (menuOuter().isDebugReadStatusSelected()) {
                //outer.showDebugMessage("curStatus = "+curStatus);
                String statString = crclSocket.getLastStatusString();
                outer.showDebugMessage("crclSocket.getLastStatusString() = " + statString);
                if (null != curStatus
                        && (null == statString || statString.length() < 1)) {
                    outer.showDebugMessage("crclSocket.statusToString(curStatus,false)="
                            + crclSocket.statusToString(curStatus, false));
                }
            }
            if (outer.isMonitoringHoldingObject() && holdingObjectExpected) {
                GripperStatusType gripperStatus = status.getGripperStatus();
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
            if (curStatus == null) {
                return;
            }
            outer.checkXmlQuery(crclSocket);
            if (menuOuter().isRecordPoseSelected()
                    && null != CRCLPosemath.getPose(status)) {
                if (null == poseQueue) {
                    poseQueue = new ConcurrentLinkedQueue<>();
                }
                PmPose pmPose = CRCLPosemath.toPmPose(curStatus);
                if (null != curStatus.getCommandStatus()) {
                    AnnotatedPose annotatedPose
                            = new AnnotatedPose(System.currentTimeMillis(),
                                    lastCommandIdSent,
                                    curStatus.getCommandStatus().getCommandID().compareTo(lastCommandIdSent) <= 0 ? cmdNameString(lastCommandSent) : cmdNameString(prevLastCommandSent),
                                    pmPose.tran, pmPose.rot,
                                    CRCLPosemath.copy(curStatus)
                            );
                    poseQueue.add(annotatedPose);
                }
            }
            this.setStatus(curStatus);
        } catch (CRCLException crclException) {
            Throwable ex = crclException.getCause();
            if (ex instanceof IOException || ex instanceof IllegalStateException) {
                if (disconnecting || ex instanceof SocketException) {
                    return;
                }
                LOGGER.log(Level.SEVERE, null, ex);
                this.showMessage(ex);
                java.awt.EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            PendantClientInner.this.disconnect();
                        } catch (Exception e) {
                            Logger.getLogger(PendantClientInner.class.getName()).log(Level.FINEST, "", e);
                        }
                    }
                });

            } else if (ex instanceof JAXBException) {
                if (disconnecting) {
                    return;
                }
                LOGGER.log(Level.SEVERE, null, ex);
                this.showMessage(ex.toString() + NEW_LINE + crclSocket.getLastStatusString() + NEW_LINE);
                java.awt.EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            disconnect();
                        } catch (Exception e) {
                            Logger.getLogger(PendantClientInner.class.getName()).log(Level.FINEST, "", e);
                        }
                    }
                });
            } else {
                if (disconnecting) {
                    return;
                }
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isConnected() {
        return null != this.crclSocket && this.crclSocket.isConnected();
    }

    public synchronized void connect(String host, int port) {
        try {
            disconnect();
            disconnecting = false;
            crclSocket = new CrclExiSocket(host, port);
            crclSocket.setEXIEnabled(menuOuter().isEXISelected());
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

    public synchronized void disconnect() {
        disconnecting = true;
        if (null != crclSocket) {
            try {
                crclSocket.close();
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
            crclSocket = null;
        }
        stopStatusReaderThread();
        if (null != runTestProgramThread && !runTestProgramThread.equals(Thread.currentThread())) {
            this.closeTestProgramThread();
        }
        outer.finishDisconnect();
    }

    public void stopStatusReaderThread() {
        if (null != readerThread) {
            try {
                stopStatusReaderFlag = true;
                readerThread.interrupt();
                readerThread.join(1500);
            } catch (InterruptedException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            } finally {
                readerThread = null;
                stopStatusReaderFlag = false;
            }

        }
    }

    public BigDecimal getJointTol() {
        return jointTol;
    }

    public void setJointTol(BigDecimal jointTol) {
        this.jointTol = jointTol;
    }

    private boolean holdingObjectExpected;

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

        private String lastMessage;

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
            setLastMessage(((MessageType)cmd).getMessage());
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
        if (cmd instanceof SetEndEffectorType) {
            SetEndEffectorType seeCmd = (SetEndEffectorType) cmd;
            this.setHoldingObjectExpected(seeCmd.getSetting().doubleValue() < 0.5);
        }
        return true;
    }

    private double maxDwell = getDoubleProperty("crcl4java.maxdwell", 6000.0);

    private static double getDoubleProperty(String propName, double defaultVal) {
        return Double.valueOf(System.getProperty(propName, Double.toString(defaultVal)));
    }

    private boolean testActuateJointsEffect(ActuateJointsType ajst) {
        List<ActuateJointType> ajl = ajst.getActuateJoint();
        if (null == status.getJointStatuses()) {
            showMessage("Test program failed : (null == status.getJointStatuses() ");
            return false;
        }
        for (ActuateJointType aj : ajl) {
            List<JointStatusType> jointListTest = status.getJointStatuses().getJointStatus();
            JointStatusType jointStatusTest = null;
            for (int j = 0; j < jointListTest.size(); j++) {
                JointStatusType jsj = jointListTest.get(j);
                if (jsj.getJointNumber().compareTo(aj.getJointNumber()) == 0) {
                    jointStatusTest = jointListTest.get(j);
                    break;
                }
            }
            if (null == jointStatusTest) {
                showMessage("Test program failed : no jointStatus for " + aj.getJointNumber());
                return false;
            }
            BigDecimal jointDiff = jointStatusTest.getJointPosition().subtract(aj.getJointPosition()).abs();
            if (jointDiff.compareTo(jointTol) > 0) {
                showMessage("Test program failed measured position differs from commanded position." + NEW_LINE
                        + "JointNumber: " + aj.getJointNumber() + NEW_LINE
                        + "Commanded :" + aj.getJointPosition() + NEW_LINE
                        + "Status (Measured): " + jointStatusTest.getJointPosition() + NEW_LINE
                        + "Diff: " + jointDiff);
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
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    private boolean testMoveThroughToEffect(MoveThroughToType moveThroughTo) {
        PoseType curPose = this.getPose();
        PoseType cmdPose = moveThroughTo.getWaypoint().get(moveThroughTo.getNumPositions().intValue() - 1);
        return PoseToleranceChecker.isInTolerance(curPose, cmdPose, expectedEndPoseTolerance, angleType);
    }

    private boolean testDwellEffect(DwellType dwell, long startTime) {
        long elapsed = System.currentTimeMillis() - startTime;
        double dwellTime = dwell.getDwellTime().doubleValue() * 1000.0;
        if (dwellTime > maxDwell) {
            LOGGER.warning("dwellTime of " + dwellTime + " exceeded max of " + maxDwell);
            return true;
        }
        long expected = (long) (dwellTime);
        if (Math.abs(elapsed - expected) > 3500) {
            outer.showMessage("Dwell expected to take " + expected + " ms but took " + elapsed + " ms.");
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
        PointType origPoint = this.testCommandStartStatus.getPoseStatus().getPose().getPoint();
        PointType curPoint = this.getStatus().getPoseStatus().getPose().getPoint();
        double newScale = unitToScale(slu.getUnitName());
        double oldScale = unitToScale(testCommandStartLengthUnit);
        double convertScale = newScale / oldScale;
        double origX = origPoint.getX().doubleValue();
        double expectedX = origX / convertScale;
        double curX = curPoint.getX().doubleValue();
        if (Math.abs(expectedX - curX) > 0.0001) {
            showMessage("X value after SelLengthUnits to " + slu.getUnitName() + " with original value " + origX + " " + testCommandStartLengthUnit + " was expected to become " + expectedX + " " + slu.getUnitName() + " but instead was " + curX);
            return false;
        }
        double origY = origPoint.getY().doubleValue();
        double expectedY = origY / convertScale;
        double curY = curPoint.getY().doubleValue();
        if (Math.abs(expectedY - curY) > 0.0001) {
            showMessage("Y value after SelLengthUnits to " + slu.getUnitName() + " with original value " + origY + " " + testCommandStartLengthUnit + " was expected to become " + expectedY + " " + slu.getUnitName() + " but instead was " + curY);
            return false;
        }
        double origZ = origPoint.getZ().doubleValue();
        double expectedZ = origZ / convertScale;
        double curZ = curPoint.getZ().doubleValue();
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
        if (null == this.getStatus() || null == this.getStatus().getJointStatuses()) {
            if (cjrMap.values().stream()
                    .noneMatch(cjr -> cjr.isReportPosition() || cjr.isReportTorqueOrForce() || cjr.isReportVelocity())) {
                return true;
            }
            outer.showMessage("ConfigureJointReports sent but JointStatuses is null");
            return false;
        }
        List<JointStatusType> jsl = this.getStatus().getJointStatuses().getJointStatus();
        for (JointStatusType js : jsl) {
            int number = js.getJointNumber().intValue();
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

    private final String NEW_LINE = System.lineSeparator();

    private boolean testMoveToEffect(MoveToType moveTo) {
        PoseType curPose = this.getPose();
        if (PoseToleranceChecker.containsNull(curPose)) {
            outer.showMessage("MoveTo Failed current pose contains null.");
            return false;
        }
        PoseType cmdPose = moveTo.getEndPosition();
        if (PoseToleranceChecker.containsNull(curPose)) {
            outer.showMessage("MoveTo Failed cmdPose contains null.");
            return false;
        }
        if (!PoseToleranceChecker.isInTolerance(curPose, cmdPose, expectedEndPoseTolerance, angleType)) {
            outer.showMessage("MoveTo Failed : diference between curPose and cmdPose exceeds tolerance." + NEW_LINE
                    + "curPose =" + CRCLPosemath.toString(curPose) + NEW_LINE
                    + "cmdPose=" + CRCLPosemath.toString(cmdPose) + NEW_LINE
                    + "expectedEndPoseTolerance=" + expectedEndPoseTolerance + ", angleType=" + angleType + NEW_LINE
                    + PoseToleranceChecker.checkToleranceString(curPose, cmdPose, expectedEndPoseTolerance, angleType) + NEW_LINE
                    + "moveTo.getCommandID()=" + moveTo.getCommandID() + NEW_LINE
                    + "stat.getCommandStatus().getCommandId()=" + status.getCommandStatus().getCommandID() + NEW_LINE
                    + "stat.getCommandStatus().getCommandState()=" + status.getCommandStatus().getCommandState() + NEW_LINE
            );
            return false;
        }
        return true;
    }

    public void stepFwd() {

    }

    public void pause() {
        try {
            pause_count.incrementAndGet();
            pauseQueue.clear();
            paused = true;
            stopMotion(StopConditionEnumType.NORMAL);
        } catch (JAXBException ex) {
            Logger.getLogger(PendantClientInner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void waitForPause() throws InterruptedException {
        while (paused) {
            waiting_for_pause_queue.incrementAndGet();
            pauseQueue.take();
            waiting_for_pause_queue.decrementAndGet();
        }
    }

    public void unpause() {
        paused = false;
        for (int i = 0; i < waiting_for_pause_queue.get() + 1; i++) {
            try {
                if (pauseQueue.isEmpty()) {
                    pauseQueue.put(this);
                } else {
                    break;
                }
            } catch (InterruptedException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
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

    private String outgoingProgramFile;

    /**
     * Get the value of outgoingProgramFile
     *
     * @return the value of outgoingProgramFile
     */
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

    private BigInteger outgoingProgramIndex;

    /**
     * Get the value of outgoingProgramIndex
     *
     * @return the value of outgoingProgramIndex
     */
    public BigInteger getOutgoingProgramIndex() {
        return outgoingProgramIndex;
    }

    /**
     * Set the value of outgoingProgramIndex
     *
     * @param outgoingProgramIndex new value of outgoingProgramIndex
     */
    public void setOutgoingProgramIndex(BigInteger outgoingProgramIndex) {
        this.outgoingProgramIndex = outgoingProgramIndex;
    }

    private BigInteger outgoingProgramLength;

    /**
     * Get the value of outgoingProgramLength
     *
     * @return the value of outgoingProgramLength
     */
    public BigInteger getOutgoingProgramLength() {
        return outgoingProgramLength;
    }

    /**
     * Set the value of outgoingProgramLength
     *
     * @param outgoingProgramLength new value of outgoingProgramLength
     */
    public void setOutgoingProgramLength(BigInteger outgoingProgramLength) {
        this.outgoingProgramLength = outgoingProgramLength;
    }

    public boolean runProgram(CRCLProgramType prog, int startLine) {
        final int start_close_test_count = this.close_test_count.get();
        holdingErrorOccured = false;
        holdingErrorRepCount = 0;
        try {
            setOutgoingProgramLength(BigInteger.valueOf(prog.getMiddleCommand().size()));
            paused = false;
            if (null == this.crclSocket) {
                this.connect(outer.getHost(), outer.getPort());
                if (!this.isConnected()) {
                    showMessage("runProgram() failed because not connected to server");
                    return false;
                }
            }
            outer.stopPollTimer();
//            this.stopStatusReaderThread();
            cmdId = BigInteger.ZERO;
            programCommandStartTime = System.currentTimeMillis();
            PmCartesian p0 = getPoseCart();
            if (this.runStartMillis < 1 || startLine == 0) {
                this.runStartMillis = System.currentTimeMillis();
                this.runEndMillis = -1;
            }
            if (startLine == 0) {
                setOutgoingProgramIndex(BigInteger.ZERO);
                outer.showCurrentProgramLine(startLine);
                InitCanonType initCmd = prog.getInitCanon();
                if (initCmd.getCommandID() == null) {
                    initCmd.setCommandID(BigInteger.ONE);
                }
                if ((null != lastCommandIdSent && initCmd.getCommandID().compareTo(lastCommandIdSent) == 0)
                        || (null != status && null != status.getCommandStatus()
                        && null != status.getCommandStatus().getCommandID()
                        && status.getCommandStatus().getCommandID().compareTo(initCmd.getCommandID()) == 0)) {
                    BigInteger max = CRCLPosemath.getMaxId(prog);
                    InitCanonType newInitCmd = new InitCanonType();
                    newInitCmd.setCommandID(max.add(BigInteger.ONE));
                    if (!testCommand(newInitCmd)) {
                        return false;
                    }
                } else if (!testCommand(initCmd)) {
                    return false;
                }
                if (stepMode) {
                    pause();
                }
                startLine = 1;
            }
            long time_to_exec = System.currentTimeMillis() - programCommandStartTime;
            PmCartesian p1 = getPoseCart();
            outer.showLastProgramLineExecTimeMillisDists(time_to_exec, p1.distFrom(p0), true);
            p0 = p1;
            outer.showCurrentProgramLine(startLine);
            List<MiddleCommandType> middleCommands = prog.getMiddleCommand();
            for (int i = startLine; i < middleCommands.size(); i++) {
                programCommandStartTime = System.currentTimeMillis();
                setOutgoingProgramIndex(BigInteger.valueOf(i));
                MiddleCommandType cmd = middleCommands.get(i - 1);
                boolean result = testCommand(cmd);
                if (!result) {
                    if (this.isQuitOnTestCommandFailure()) {
                        return false;
                    }
                }
                if (this.getStatus().getCommandStatus().getCommandState() != CommandStateEnumType.CRCL_DONE) {
                    result = false;
                }
                time_to_exec = System.currentTimeMillis() - programCommandStartTime;
                p1 = getPoseCart();
                outer.showLastProgramLineExecTimeMillisDists(time_to_exec, p1.distFrom(p0), result);
                p0 = p1;
                if (stepMode) {
                    pause();
                }
                outer.showCurrentProgramLine(i + 1);
            }
            programCommandStartTime = System.currentTimeMillis();
            EndCanonType endCmd = prog.getEndCanon();
            if (!testCommand(endCmd)) {
                return false;
            }
            time_to_exec = System.currentTimeMillis() - programCommandStartTime;
            outer.showLastProgramLineExecTimeMillisDists(time_to_exec, 0, true);
            outer.showCurrentProgramLine(middleCommands.size() + 2);
            outer.showDebugMessage("testProgram() succeeded");
            return true;
        } catch (InterruptedException ex) {
            if (close_test_count.get() <= start_close_test_count) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        } catch (CRCLException | IOException | PmException | JAXBException ex) {
            Logger.getLogger(PendantClientInner.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            setOutgoingProgramIndex(null);
            this.runEndMillis = System.currentTimeMillis();
            outer.checkPollSelected();
        }
        return false;
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

    public boolean isRunningProgram() {
        return !paused
                && null != this.runTestProgramThread
                && this.runTestProgramThread.isAlive();
    }

    public long getRunStartMillis() {
        return runStartMillis;
    }

    public long getRunEndMillis() {
        return runEndMillis;
    }

    public Map<String, String> getDefaultTestPropertiesMap() {
        Map<String, String> map = new HashMap<>();
        map.put("jointTol", jointTol.toString());
        map.put("jointPosIncrement", BigDecimal.valueOf(jogIncrement).toString());
        map.put("jointMoveSpeed", jointMoveSpeed != null ? jointMoveSpeed.toString() : "");
        map.put("jointMoveAccel", jointMoveAccel != null ? jointMoveAccel.toString() : "");
        map.put("xyzAxisIncrement", BigDecimal.valueOf(this.getXyzJogIncrement()).toString());
        return map;
    }

    /**
     * Get the value of jointMoveAccel
     *
     * @return the value of jointMoveAccel
     */
    public BigDecimal getJointMoveAccel() {
        return jointMoveAccel;
    }

    /**
     * Set the value of jointMoveAccel
     *
     * @param jointMoveAccel new value of jointMoveAccel
     */
    public void setJointMoveAccel(BigDecimal jointMoveAccel) {
        this.jointMoveAccel = jointMoveAccel;
    }

    /**
     * Get the value of jointMoveSpeed
     *
     * @return the value of jointMoveSpeed
     */
    public BigDecimal getJointMoveSpeed() {
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
    public void setJointMoveSpeed(BigDecimal jointMoveSpeed) {
        this.jointMoveSpeed = jointMoveSpeed;
    }

    /**
     * Tests if an automatically generated set of commands appears to be
     * correctly implemented by the server.
     *
     * @param testProperies
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
            outer.stopPollTimer();
            InitCanonType initCmd = new InitCanonType();
            cmdId = cmdId.add(BigInteger.ONE);
            initCmd.setCommandID(cmdId);
            testCommand(initCmd);
            SetLengthUnitsType setUnitType = new SetLengthUnitsType();
            setUnitType.setUnitName(this.lengthUnit);
            cmdId = cmdId.add(BigInteger.ONE);
            setUnitType.setCommandID(cmdId);
            testCommand(setUnitType);
            if (null != status.getJointStatuses()) {
                ConfigureJointReportsType cjrs = new ConfigureJointReportsType();
                cmdId = cmdId.add(BigInteger.ONE);
                cjrs.setCommandID(cmdId);
                List<JointStatusType> jointList = status.getJointStatuses().getJointStatus();
                cjrs.getConfigureJointReport().clear();
                for (int i = 0; i < jointList.size(); i++) {
                    ConfigureJointReportType cjr = new ConfigureJointReportType();
                    cjr.setReportPosition(true);
                    cjr.setReportVelocity(true);
                    cjr.setJointNumber(jointList.get(i).getJointNumber());
                    cjrs.getConfigureJointReport().add(cjr);
                }
                testCommand(cjrs);
            }

            CRCLProgramType testProgram = new CRCLProgramType();
            testProgram.setInitCanon(initCmd);
            Optional.ofNullable(testProperies)
                    .map(m -> m.get("jointTol"))
                    .map(Double::valueOf)
                    .map(BigDecimal::valueOf)
                    .ifPresent(this::setJointTol);
            BigDecimal jointPosIncrement
                    = Optional.ofNullable(testProperies)
                    .map(m -> m.get("jointPosIncrement"))
                    .map(Double::valueOf)
                    .map(BigDecimal::valueOf)
                    .orElse(BigDecimal.valueOf(jogIncrement));
            BigDecimal testJointMoveSpeed
                    = Optional.ofNullable(testProperies)
                    .map(m -> m.get("jointMoveSpeed"))
                    .filter(s -> s.length() > 0)
                    .map(Double::valueOf)
                    .map(BigDecimal::valueOf)
                    .orElse(null);
            BigDecimal testJointMoveAccel
                    = Optional.ofNullable(testProperies)
                    .map(m -> m.get("jointMoveAccel"))
                    .filter(s -> s.length() > 0)
                    .map(Double::valueOf)
                    .map(BigDecimal::valueOf)
                    .orElse(null);
            final BigDecimal xyzAxisIncrement
                    = Optional.ofNullable(testProperies)
                    .map(m -> m.get("xyzAxisIncrement"))
                    .map(Double::valueOf)
                    .map(BigDecimal::valueOf)
                    .orElse(BigDecimal.valueOf(this.getXyzJogIncrement()));
            SetTransSpeedType setTransSpeed = new SetTransSpeedType();
            cmdId = cmdId.add(BigInteger.ONE);
            setTransSpeed.setCommandID(cmdId);
            TransSpeedRelativeType transRel = new TransSpeedRelativeType();
            transRel.setFraction(BigDecimal.ONE);
            setTransSpeed.setTransSpeed(transRel);
            testProgram.getMiddleCommand().add(setTransSpeed);
            if (null != status.getJointStatuses()) {

                List<JointStatusType> jointList = status.getJointStatuses().getJointStatus();
                ConfigureJointReportsType cjrs = new ConfigureJointReportsType();
                cmdId = cmdId.add(BigInteger.ONE);
                cjrs.setCommandID(cmdId);
                cjrs.getConfigureJointReport().clear();
                for (int i = 0; i < jointList.size(); i++) {
                    ConfigureJointReportType cjr = new ConfigureJointReportType();
                    cjr.setReportPosition(true);
                    cjr.setReportVelocity(true);
                    cjr.setJointNumber(jointList.get(i).getJointNumber());
                    cjrs.getConfigureJointReport().add(cjr);
                }
                testProgram.getMiddleCommand().add(cjrs);
                for (int i = 0; i < jointList.size(); i++) {
                    JointStatusType js = jointList.get(i);
                    ActuateJointsType ajst = new ActuateJointsType();
                    List<ActuateJointType> ajl = ajst.getActuateJoint();
                    ActuateJointType aj = new ActuateJointType();
                    aj.setJointNumber(js.getJointNumber());
                    BigDecimal origPosition = BigDecimal.valueOf(js.getJointPosition().doubleValue());
                    aj.setJointPosition(js.getJointPosition().add(jointPosIncrement));
                    JointSpeedAccelType jsa = new JointSpeedAccelType();
                    if (null != testJointMoveSpeed) {
                        jsa.setJointSpeed(testJointMoveSpeed);
                    }
                    if (null != testJointMoveAccel) {
                        jsa.setJointAccel(testJointMoveAccel);
                    }
                    aj.setJointDetails(jsa);
                    ajl.add(aj);
                    cmdId = cmdId.add(BigInteger.ONE);
                    ajst.setCommandID(cmdId);
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
                    cmdId = cmdId.add(BigInteger.ONE);
                    ajst.setCommandID(cmdId);
                    testProgram.getMiddleCommand().add(ajst);
                    DwellType dwell = new DwellType();
                    dwell.setDwellTime(BigDecimal.valueOf(0.25));
                    cmdId = cmdId.add(BigInteger.ONE);
                    dwell.setCommandID(cmdId);
                    testProgram.getMiddleCommand().add(dwell);
                }
            }
            setUnitType = new SetLengthUnitsType();
            setUnitType.setUnitName(LengthUnitEnumType.INCH);
            cmdId = cmdId.add(BigInteger.ONE);
            setUnitType.setCommandID(cmdId);
            testProgram.getMiddleCommand().add(setUnitType);
            setUnitType = new SetLengthUnitsType();
            setUnitType.setUnitName(LengthUnitEnumType.METER);
            cmdId = cmdId.add(BigInteger.ONE);
            setUnitType.setCommandID(cmdId);
            testProgram.getMiddleCommand().add(setUnitType);
            setUnitType = new SetLengthUnitsType();
            setUnitType.setUnitName(LengthUnitEnumType.MILLIMETER);
            cmdId = cmdId.add(BigInteger.ONE);
            setUnitType.setCommandID(cmdId);
            testProgram.getMiddleCommand().add(setUnitType);
            PoseType pose = Optional.ofNullable(this)
                    .map(PendantClientInner::getStatus)
                    .map(CRCLPosemath::getPose)
                    .orElse(null);
            if (null != pose) {
                MoveToType moveToOrig = new MoveToType();
                PoseType origEndPos = new PoseType();
                origEndPos.setPoint(new PointType());
                origEndPos.setXAxis(new VectorType());
                origEndPos.setZAxis(new VectorType());
                origEndPos.getPoint().setX(pose.getPoint().getX());
                origEndPos.getPoint().setY(pose.getPoint().getY());
                origEndPos.getPoint().setZ(pose.getPoint().getZ());
                origEndPos.getXAxis().setI(pose.getXAxis().getI());
                origEndPos.getXAxis().setJ(pose.getXAxis().getJ());
                origEndPos.getXAxis().setK(pose.getXAxis().getK());
                origEndPos.getZAxis().setI(pose.getZAxis().getI());
                origEndPos.getZAxis().setJ(pose.getZAxis().getJ());
                origEndPos.getZAxis().setK(pose.getZAxis().getK());
                moveToOrig.setEndPosition(origEndPos);
                cmdId = cmdId.add(BigInteger.ONE);
                moveToOrig.setCommandID(cmdId);
                testProgram.getMiddleCommand().add(moveToOrig);
                MoveToType moveToXPlus = new MoveToType();
                PoseType xPlusPos = new PoseType();
                xPlusPos.setPoint(new PointType());
                xPlusPos.setXAxis(new VectorType());
                xPlusPos.setZAxis(new VectorType());
                xPlusPos.getPoint().setX(pose.getPoint().getX().add(xyzAxisIncrement));
                xPlusPos.getPoint().setY(pose.getPoint().getY());
                xPlusPos.getPoint().setZ(pose.getPoint().getZ());
                xPlusPos.getXAxis().setI(pose.getXAxis().getI());
                xPlusPos.getXAxis().setJ(pose.getXAxis().getJ());
                xPlusPos.getXAxis().setK(pose.getXAxis().getK());
                xPlusPos.getZAxis().setI(pose.getZAxis().getI());
                xPlusPos.getZAxis().setJ(pose.getZAxis().getJ());
                xPlusPos.getZAxis().setK(pose.getZAxis().getK());
                moveToXPlus.setEndPosition(xPlusPos);
                cmdId = cmdId.add(BigInteger.ONE);
                moveToXPlus.setCommandID(cmdId);
                testProgram.getMiddleCommand().add(moveToXPlus);
                DwellType dwell = new DwellType();
                dwell.setDwellTime(BigDecimal.valueOf(0.25));
                cmdId = cmdId.add(BigInteger.ONE);
                dwell.setCommandID(cmdId);
                testProgram.getMiddleCommand().add(dwell);
                cmdId = cmdId.add(BigInteger.ONE);
                moveToOrig.setCommandID(cmdId);
                testProgram.getMiddleCommand().add(moveToOrig);
                MoveToType moveToYPlus = new MoveToType();
                PoseType yPlusPos = new PoseType();
                yPlusPos.setPoint(new PointType());
                yPlusPos.setXAxis(new VectorType());
                yPlusPos.setZAxis(new VectorType());
                yPlusPos.getPoint().setX(pose.getPoint().getX());
                yPlusPos.getPoint().setY(pose.getPoint().getY().add(xyzAxisIncrement));
                yPlusPos.getPoint().setZ(pose.getPoint().getZ());
                yPlusPos.getXAxis().setI(pose.getXAxis().getI());
                yPlusPos.getXAxis().setJ(pose.getXAxis().getJ());
                yPlusPos.getXAxis().setK(pose.getXAxis().getK());
                yPlusPos.getZAxis().setI(pose.getZAxis().getI());
                yPlusPos.getZAxis().setJ(pose.getZAxis().getJ());
                yPlusPos.getZAxis().setK(pose.getZAxis().getK());
                moveToYPlus.setEndPosition(yPlusPos);
                cmdId = cmdId.add(BigInteger.ONE);
                moveToYPlus.setCommandID(cmdId);
                testProgram.getMiddleCommand().add(moveToYPlus);
                dwell = new DwellType();
                dwell.setDwellTime(BigDecimal.valueOf(0.25));
                cmdId = cmdId.add(BigInteger.ONE);
                dwell.setCommandID(cmdId);
                testProgram.getMiddleCommand().add(dwell);
                cmdId = cmdId.add(BigInteger.ONE);
                moveToOrig.setCommandID(cmdId);
                testProgram.getMiddleCommand().add(moveToOrig);
                MoveToType moveToZPlus = new MoveToType();
                PoseType zPlusPos = new PoseType();
                zPlusPos.setPoint(new PointType());
                zPlusPos.setXAxis(new VectorType());
                zPlusPos.setZAxis(new VectorType());
                zPlusPos.getPoint().setX(pose.getPoint().getX());
                zPlusPos.getPoint().setY(pose.getPoint().getY());
                zPlusPos.getPoint().setZ(pose.getPoint().getZ().add(xyzAxisIncrement));
                zPlusPos.getXAxis().setI(pose.getXAxis().getI());
                zPlusPos.getXAxis().setJ(pose.getXAxis().getJ());
                zPlusPos.getXAxis().setK(pose.getXAxis().getK());
                zPlusPos.getZAxis().setI(pose.getZAxis().getI());
                zPlusPos.getZAxis().setJ(pose.getZAxis().getJ());
                zPlusPos.getZAxis().setK(pose.getZAxis().getK());
                moveToZPlus.setEndPosition(zPlusPos);
                cmdId = cmdId.add(BigInteger.ONE);
                moveToZPlus.setCommandID(cmdId);
                testProgram.getMiddleCommand().add(moveToZPlus);
                dwell = new DwellType();
                dwell.setDwellTime(BigDecimal.valueOf(0.25));
                cmdId = cmdId.add(BigInteger.ONE);
                dwell.setCommandID(cmdId);
                testProgram.getMiddleCommand().add(dwell);
                cmdId = cmdId.add(BigInteger.ONE);
                moveToOrig.setCommandID(cmdId);
                testProgram.getMiddleCommand().add(moveToOrig);
            }
            EndCanonType endCmd = new EndCanonType();
            cmdId = cmdId.add(BigInteger.ONE);
            endCmd.setCommandID(cmdId);
            testProgram.setEndCanon(endCmd);
            String progString
                    = getTempCRCLSocket().programToPrettyDocString(testProgram, true);
            File testProgramFile = File.createTempFile("crclTest", ".xml");
            Files.write(testProgramFile.toPath(), progString.getBytes());
            this.openXmlProgramFile(testProgramFile, false);
            outer.showDebugMessage("Test program saved to " + testProgramFile.getCanonicalPath());
            return runProgram(testProgram, 0);
        } catch (CRCLException | InterruptedException | IOException | JAXBException | ParserConfigurationException | XPathExpressionException | SAXException | PmException ex) {
            Logger.getLogger(PendantClientInner.class.getName()).log(Level.SEVERE, null, ex);
            outer.showMessage(ex);
        } finally {
            outer.checkPollSelected();
        }
        return false;
    }

    private String cmdNameString(CRCLCommandType cmd) {
        if (null == cmd) {
            return "";
        }
        String cmdName = cmd.getClass().getSimpleName();
        final String prefix = "crcl.base.";
        if (cmdName.startsWith(prefix)) {
            return cmdName.substring(prefix.length());
        }
        return cmdName;
    }

    private String cmdString(CRCLCommandType cmd) throws JAXBException {
        String cmdName = cmdNameString(cmd);
        return cmdName + " with ID = " + cmd.getCommandID() + ", \txml: " + this.getTempCRCLSocket().commandToString(cmd, false);
    }

    public BigDecimal getJointPosition(BigInteger jointNumber) {
        if (null == status || null == status.getJointStatuses()) {
            return null;
        }
        List<JointStatusType> jsl = status.getJointStatuses().getJointStatus();
        if (null == jsl) {
            return null;
        }
        for (JointStatusType js : jsl) {
            if (js.getJointNumber().compareTo(jointNumber) == 0) {
                return js.getJointPosition();
            }
        }
        return null;
    }

    private long getTimeoutForAcuateJoints(ActuateJointsType ajst) {
        List<ActuateJointType> ajl = ajst.getActuateJoint();
        double maxDiff = 0;
        for (ActuateJointType aj : ajl) {
            BigDecimal jp = getJointPosition(aj.getJointNumber());
            if (null == jp) {
                continue;
            }
            double thisDiff = jp.subtract(aj.getJointPosition()).abs().doubleValue();
            JointDetailsType jd = aj.getJointDetails();
            if (jd instanceof JointSpeedAccelType) {
                JointSpeedAccelType jas = (JointSpeedAccelType) jd;
                if (jas.getJointSpeed() != null) {
                    double vel = jas.getJointSpeed().doubleValue();
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
                    if (null != jas.getJointAccel()) {
                        double accel = jas.getJointAccel().doubleValue();
                        thisDiff += Math.abs(2 * vel / accel);
                    }
                }
            }
            maxDiff = Math.max(maxDiff, thisDiff);
        }
        return 2000 + (long) (maxDiff * 1500.0);
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
        return (long) (1500 + cmd.getDwellTime().doubleValue() * 1000);
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
        return 3000;
    }

    private CommandStatusType copyCommandStatus(CommandStatusType oldCmdStat) {
        if (null == oldCmdStat) {
            return null;
        }
        CommandStatusType newCmdStat = new CommandStatusType();
        newCmdStat.setCommandID(oldCmdStat.getCommandID());
        newCmdStat.setCommandState(oldCmdStat.getCommandState());
        newCmdStat.setStatusID(oldCmdStat.getStatusID());
        newCmdStat.setName(oldCmdStat.getName());
        return newCmdStat;
    }

    private GripperStatusType copyGripperStatusType(GripperStatusType oldGripperStat) {
        if (null == oldGripperStat) {
            return null;
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
        }
        if (null == newGripperStat) {
            throw new RuntimeException("Unexpected Class for GripperStatus:" + oldGripperStat.getClass());
        }
        newGripperStat.setGripperName(oldGripperStat.getGripperName());
        newGripperStat.setName(oldGripperStat.getName());
        return newGripperStat;
    }

    private JointStatusType copyJointStatus(JointStatusType oldJointStat) {
        if (oldJointStat == null) {
            return null;
        }
        JointStatusType newJointStat = new JointStatusType();
        newJointStat.setJointNumber(oldJointStat.getJointNumber());
        newJointStat.setJointPosition(oldJointStat.getJointPosition());
        newJointStat.setJointVelocity(oldJointStat.getJointVelocity());
        newJointStat.setJointTorqueOrForce(oldJointStat.getJointTorqueOrForce());
        newJointStat.setName(oldJointStat.getName());
        return newJointStat;
    }

    private JointStatusesType copyJointStatuses(JointStatusesType oldJointStats) {
        if (null == oldJointStats) {
            return null;
        }
        JointStatusesType newJointStats = new JointStatusesType();
        for (JointStatusType js : oldJointStats.getJointStatus()) {
            newJointStats.getJointStatus().add(copyJointStatus(js));
        }
        newJointStats.setName(oldJointStats.getName());
        return newJointStats;
    }

    private RotAccelType copyRotAccel(RotAccelType oldRotAccel) {
        if (null == oldRotAccel) {
            return null;
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
        }
        if (null != newRotAccel) {
            newRotAccel.setName(oldRotAccel.getName());
        }
        return newRotAccel;
    }

    private RotSpeedType copyRotSpeed(RotSpeedType oldRotSpeed) {
        if (null == oldRotSpeed) {
            return null;
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
        }
        if (null != newRotSpeed) {
            newRotSpeed.setName(oldRotSpeed.getName());
        }
        return newRotSpeed;
    }

    private TransAccelType copyTransAccel(TransAccelType oldTransAccel) {
        if (null == oldTransAccel) {
            return null;
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
        }
        if (null != newTransAccel) {
            newTransAccel.setName(oldTransAccel.getName());
        }
        return newTransAccel;
    }

    private TransSpeedType copyTransSpeed(TransSpeedType oldTransSpeed) {
        if (null == oldTransSpeed) {
            return null;
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
        }
        if (null != newTransSpeed) {
            newTransSpeed.setName(oldTransSpeed.getName());
        }
        return newTransSpeed;
    }

    private PoseToleranceType copyPoseTolerance(PoseToleranceType oldPoseTol) {
        if (null == oldPoseTol) {
            return null;
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
            return null;
        }
        PointType newPoint = new PointType();
        newPoint.setX(oldPoint.getX());
        newPoint.setY(oldPoint.getY());
        newPoint.setZ(oldPoint.getZ());
        newPoint.setName(oldPoint.getName());
        return newPoint;
    }

    private VectorType copyVector(VectorType oldVector) {
        if (null == oldVector) {
            return null;
        }
        VectorType newVector = new VectorType();
        newVector.setI(oldVector.getI());
        newVector.setJ(oldVector.getJ());
        newVector.setK(oldVector.getK());
        newVector.setName(oldVector.getName());
        return newVector;
    }

    private PoseType copyPose(PoseType oldPose) {
        if (null == oldPose) {
            return null;
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
        newPose.setPoint(copyPoint(oldPose.getPoint()));
        newPose.setXAxis(copyVector(oldPose.getXAxis()));
        newPose.setZAxis(copyVector(oldPose.getZAxis()));
        newPose.setName(oldPose.getName());
        return newPose;
    }

    private PoseStatusType copyPoseStatus(PoseStatusType oldPoseStatus) {
        if (null == oldPoseStatus) {
            return null;
        }
        PoseStatusType newPoseStatus = new PoseStatusType();
        newPoseStatus.setPose(copyPose(oldPoseStatus.getPose()));
        newPoseStatus.setName(oldPoseStatus.getName());
        return newPoseStatus;
    }

    private CRCLStatusType copyStatus(CRCLStatusType oldStatus) {
        if (null == oldStatus) {
            return null;
        }
        CRCLStatusType newStat = new CRCLStatusType();
        newStat.setCommandStatus(copyCommandStatus(oldStatus.getCommandStatus()));
        newStat.setGripperStatus(copyGripperStatusType(oldStatus.getGripperStatus()));
        newStat.setJointStatuses(copyJointStatuses(oldStatus.getJointStatuses()));
        newStat.setPoseStatus(copyPoseStatus(oldStatus.getPoseStatus()));
        newStat.setName(oldStatus.getName());
        return newStat;
    }

    /**
     *
     * @param cmd the value of command to test
     * @param timeout the value of timeout
     * @return false for failure or true for success
     * @throws InterruptedException
     */
    private boolean testCommand(CRCLCommandType cmd) throws JAXBException, InterruptedException, IOException, PmException, CRCLException {
        final long timeout = getTimeout(cmd);
        int pause_count_start = this.pause_count.get();
        long testCommandStartTime = System.currentTimeMillis();
        final String cmdString = cmdString(cmd);
        long sendCommandTime = testCommandStartTime;
        long curTime = testCommandStartTime;
        String poseListSaveFileName = null;
        do {
            if (null == crclSocket) {
                throw new IllegalStateException("crclSocket must not be null");
            }
            if (cmd instanceof GetStatusType) {
                showDebugMessage("Ignoring command GetStatusType inside a program.");
                return true;
            }
            this.waitForPause();
            pause_count_start = this.pause_count.get();
            if (null == this.getStatus()) {
                waitForStatus(2000, 200);
            }
            testCommandStartLengthUnitSent = lengthUnitSent;
            testCommandStartLengthUnit = this.getLengthUnit();
            testCommandStartStatus = copyStatus(this.getStatus());
            if (null == testCommandStartStatus) {
                showMessage("testCommand can not get starting status");
                return false;
            }
//            if (cmd.getCommandID() == null) {
//                showMessage("Command from program has null commandId");
//                return false;
//            }
//
//            if (cmd.getCommandID().compareTo(status.getCommandStatus().getCommandID()) == 0
//                    || cmd.getCommandID().compareTo(lastCommandIdSent) == 0) {
//                if (cmd instanceof InitCanonType) {
//                    InitCanonType newInitCanon = new InitCanonType();
//                    newInitCanon.setCommandID(BigInteger.TEN);
//                    incAndSendCommand(newInitCanon);
//                    sendCommandTime = System.currentTimeMillis();
//                    if (!waitForDone(newInitCanon.getCommandID(), timeout)) {
//                        showMessage("Forced InitCanonType timedout.");
//                        return false;
//                    }
//                    if (cmd.getCommandID().compareTo(status.getCommandStatus().getCommandID()) == 0
//                            || cmd.getCommandID().compareTo(lastCommandIdSent) == 0) {
//                        showMessage("Command has same commandID as current status. cmd=" + CRCLSocket.getUtilSocket().commandToString(cmd, false)+ ", lastCommandIdSent="+lastCommandIdSent);
//                        return false;
//                    }
//                } else {
//                    showMessage("Command has same commandID as current status. cmd=" + CRCLSocket.getUtilSocket().commandToString(cmd, false)+ ", lastCommandIdSent="+lastCommandIdSent);
//                    return false;
//                }
//            }
//            if (!sendCommand(cmd)) {
//                if (pause_count_start != this.pause_count) {
//                    continue;
//                }
//                showMessage("Can not send " + cmdString + ".");
//                return false;
//            }
            if (!incAndSendCommand(cmd)) {
                if (pause_count_start != this.pause_count.get()) {
                    continue;
                }
                showMessage("Can not send " + cmdString + ".");
                return false;
            }

            sendCommandTime = System.currentTimeMillis();
            if (!waitForDone(cmd.getCommandID(), timeout)) {
                if (pause_count_start != this.pause_count.get()) {
                    continue;
                }
                curTime = System.currentTimeMillis();
                if (null != this.getPoseList()) {
                    File tmpFile = File.createTempFile("poseList", ".csv");
                    poseListSaveFileName = tmpFile.getCanonicalPath();
                    this.savePoseListToCsvFile(tmpFile.getCanonicalPath());
                }
                showMessage("Test Progam timed out waiting for DONE from " + NEW_LINE
                        + "cmd=" + cmdString + "." + NEW_LINE
                        + "testCommandStartStatus=" + getTempCRCLSocket().statusToString(testCommandStartStatus, false) + "." + NEW_LINE
                        + "current status=" + getTempCRCLSocket().statusToString(status, false) + "." + NEW_LINE
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
                );
                SimServerInner.printAllClientStates(System.err);
                Thread.getAllStackTraces().entrySet().forEach((x) -> {
                    System.err.println("Thread:" + x.getKey().getName());
                    Arrays.stream(x.getValue()).forEach((xx) -> {
                        System.err.println("\t" + xx);
                    });
                    System.err.println("");
                });
                return false;
            }
        } while (pause_count_start != this.pause_count.get());

        boolean effectOk = testCommandEffect(cmd, testCommandStartTime);
        if (!effectOk) {
            showMessage("Test Progam testCommandEffect failed for " + NEW_LINE
                    + "cmd=" + cmdString + "." + NEW_LINE
                    + "testCommandStartStatus=" + getTempCRCLSocket().statusToString(testCommandStartStatus, false) + "." + NEW_LINE
                    + "current status=" + getTempCRCLSocket().statusToString(status, false) + "." + NEW_LINE
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
            );
        }
        return effectOk;
    }

    public void startRunProgramThread(final int startLine) {
        this.closeTestProgramThread();
        this.runTestProgramThread = new Thread(new Runnable() {

            @Override
            public void run() {
                runProgram(program, startLine);
            }

        }, "PendantClientInner.runProgram");
        this.runTestProgramThread.start();
    }

    public void startRunTestThread(final Map<String, String> testProperties) {
        this.closeTestProgramThread();
        this.runTestProgramThread = new Thread(new Runnable() {

            @Override
            public void run() {
                runTest(testProperties);
            }

        }, "PendantClientInner.runTest");
        this.runTestProgramThread.start();
    }

    public int getPoll_ms() {
        return poll_ms;
    }

    public void setPoll_ms(int poll_ms) {
        this.poll_ms = poll_ms;
    }

    public BigInteger getCmdId() {
        return cmdId;
    }

    public void setCmdId(BigInteger cmdId) {
        this.cmdId = cmdId;
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

}
