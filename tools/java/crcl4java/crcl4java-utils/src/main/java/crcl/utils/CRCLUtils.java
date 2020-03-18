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
package crcl.utils;

import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLProgramType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStatusType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.MiddleCommandType;
import crcl.base.PointType;
import crcl.base.PoseType;
import crcl.base.VectorType;
import static crcl.utils.CRCLSocket.getUtilSocket;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import static java.util.Objects.requireNonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class CRCLUtils {

    private CRCLUtils() {
    }

    public static String traceToString(StackTraceElement trace @Nullable []) {
        if (null == trace) {
            return "";
        }
        try (StringWriter stringWriter = new StringWriter()) {
            try (PrintWriter printWriter = new PrintWriter(stringWriter)) {
                boolean first = true;
                for (StackTraceElement traceElement : trace) {
                    String traceString = traceElement.toString();
                    if (first && traceString.contains("Thread.getStackTrace(")) {
                        first = false;
                        continue;
                    }
                    first = false;
                    printWriter.println("\tat " + traceElement);
                }
            }
            stringWriter.flush();
            return stringWriter.toString();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String getCrclUserHomeDir() {
        boolean isWindows = System.getProperty("os.name").startsWith("Windows");

        String dir;
        if (isWindows) {
            dir = System.getProperty("windows.crcl.user.home", System.getProperty("crcl.user.home", System.getProperty("user.home")));
        } else {
            dir = System.getProperty("linux.crcl.user.home", System.getProperty("crcl.user.home", System.getProperty("user.home")));
        }
//        if(!dir.endsWith("netbeans_run_user_home")) {
//            System.out.println("System.getProperty(\"user.home\") = " + System.getProperty("user.home"));
//            System.out.println("System.getProperty(\"crcl.user.home\") = " + System.getProperty("crcl.user.home"));
//            System.out.println("dir = " + dir);
//            Properties props = System.getProperties();
//            props.list(System.out);
//        }
        return dir;
    }

    /**
     * Read a CRCL Status from a File with the given file.
     *
     * @param f File to read
     * @return CRCLStatus read from file.
     * @throws CRCLException file is not valid CRCLStatus
     * @throws IOException unable to read from file
     */
    public static CRCLStatusType readStatusFile(File f) throws CRCLException, IOException {
        return readStatusFile(f.toPath());
    }

    /**
     * Read a CRCL Status from a File with the given path.
     *
     * @param p Path to file to read
     * @return CRCLStatus read from file.
     * @throws CRCLException file is not valid CRCLStatus
     * @throws IOException unable to read from file
     */
    public static CRCLStatusType readStatusFile(Path p) throws CRCLException, IOException {
        CRCLSocket cs = getUtilSocket();
        String str = new String(Files.readAllBytes(p),StandardCharsets.UTF_8);
        synchronized (cs) {
            return cs.stringToStatus(str, true);
        }
    }

    /**
     * Read a CRCL Program from a File with the given file.
     *
     * @param f File to read
     * @return CRCLProgram read from file.
     * @throws CRCLException file is not valid CRCLProgram
     * @throws IOException unable to read from file
     */
    public static CRCLProgramType readProgramFile(File f) throws CRCLException, IOException {
        return readProgramFile(f.toPath());
    }

    /**
     * Read a CRCL Program from a File with the given path.
     *
     * @param p Path to file to read
     * @return CRCLProgram read from file.
     * @throws CRCLException file is not valid CRCLProgram
     * @throws IOException unable to read from file
     */
    public static CRCLProgramType readProgramFile(Path p) throws CRCLException, IOException {
        CRCLSocket cs = getUtilSocket();
        String str = new String(Files.readAllBytes(p),StandardCharsets.UTF_8);
        synchronized (cs) {
            return cs.stringToProgram(str, true);
        }
    }

    /**
     * Read a CRCL Program from a File with the given filename.
     *
     * @param filename name of file to read
     * @return CRCLProgram read from file.
     * @throws CRCLException file is not valid CRCLProgram
     * @throws IOException unable to read from file
     */
    public static CRCLProgramType readProgramFile(String filename) throws CRCLException, IOException {
        return readProgramFile(Paths.get(filename));
    }

    public static long getLongProperty(String name, long defaultValue) {
        String propVal = System.getProperty(name);
        if (propVal == null) {
            return defaultValue;
        }
        return Long.parseLong(propVal);
    }

    public static @Nullable
    JointStatusType getJointStatus(CRCLStatusType _status, BigInteger bi) {
        return getJointStatus(_status, bi.intValue());
    }

    public static @Nullable
    JointStatusType getJointStatus(CRCLStatusType _status, int bi) {
        if (null == _status) {
            return null;
        }
        JointStatusesType jsst = _status.getJointStatuses();
        if (null == jsst) {
            return null;
        }
        Iterable<JointStatusType> jsl = getNonNullJointStatusIterable(jsst);
        if (null != jsl) {
            for (JointStatusType js : jsl) {
                if (js.getJointNumber() == bi) {
                    return js;
                }
            }
        }
        return null;
    }

    private static <T> Iterator<T> createEmptyIterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                throw new NoSuchElementException("Created from null iterator");
            }
        };
    }

    @SuppressWarnings({"nullness", "initialization"})
    static private <T> Iterator<@NonNull T> createNoNullIterator(@Nullable Iterator<@Nullable T> iteratorIn) {
        return new Iterator<T>() {

            @Nullable
            T cachedNext = getNextNonNull(iteratorIn);

            private @Nullable
            T getNextNonNull(@Nullable Iterator<@Nullable T> _iteratorIn) {
                if (null == _iteratorIn) {
                    return null;
                }
                while (_iteratorIn.hasNext()) {
                    T n = _iteratorIn.next();
                    if (null != n) {
                        return n;
                    }
                }
                return null;
            }

            @Override
            public boolean hasNext() {
                return cachedNext != null;
            }

            @Override
            public T next() {
                T ret = this.cachedNext;
                if (null == ret) {
                    throw new NoSuchElementException();
                }
                cachedNext = getNextNonNull(iteratorIn);
                return ret;
            }
        };
    }

    @SuppressWarnings({"nullness", "initialization"})
    public static <T> Iterable<@NonNull T> getNonNullIterable(@Nullable Iterable<T> itIn) {
        if (null == itIn) {
            return createEmptyIterable();
        }
        Iterable<T> ret = createNoNullIterableCopy(itIn);
        return ret;
    }

//    @SuppressWarnings({"nullness", "initialization"})
    public static <T> void clearAndSetList(@Nullable List<@Nullable T> list, @Nullable Collection<? extends T> collectionToAdd) {
        if (null != list) {
            list.clear();
            if (null != collectionToAdd) {
                list.addAll(collectionToAdd);
            }
        }
    }

    @SuppressWarnings({"nullness", "initialization"})
    public static <T> List<@NonNull T> getNonNullFilteredList(@Nullable Iterable<T> itIn) {
        if (null == itIn) {
            return Collections.emptyList();
        }
        Iterable<T> iterable = createNoNullIterableCopy(itIn);
        List<T> newList = new ArrayList<>();
        for (T el : iterable) {
            newList.add(el);
        }
        return Collections.unmodifiableList(newList);
    }

    static public CRCLCommandType getNonNullCmd(CRCLCommandInstanceType cmdInstance) {
        return requireNonNull(
                requireNonNull(cmdInstance, "cmdInstance")
                        .getCRCLCommand(),
                "getCRCLCommand()"
        );
    }

    public static CommandStatusType getNonNullCommandStatus(CRCLStatusType stat) {
        return requireNonNull(
                requireNonNull(stat, "stat")
                        .getCommandStatus(),
                "getCommandStatus()"
        );
    }

    public static VectorType getNonNullXAxis(PoseType pose) {
        return requireNonNull(
                requireNonNull(pose, "pose").
                        getXAxis(),
                "getXAxis()"
        );
    }

    public static PointType getNonNullPoint(PoseType pose) {
        return requireNonNull(requireNonNull(pose, "pose").getPoint(), "getPoint()");
    }

    @SuppressWarnings({"nullness", "initialization"})
    private static <T> Iterable<@NonNull T> createNoNullIterableCopy(Iterable<@Nullable T> itIn) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                Iterator<@Nullable T> iteratorIn = itIn.iterator();
                Iterator<T> iteratorOut = createNoNullIterator(iteratorIn);
                return iteratorOut;
            }
        };
    }

    public static <T> Iterable<T> createEmptyIterable() {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return createEmptyIterator();
            }
        };
    }
    
    @SuppressWarnings("nullness")
    public static List<MiddleCommandType> middleCommands(CRCLProgramType prog) {
        return requireNonNull(
                requireNonNull(prog, "prog")
                        .getMiddleCommand(),
                "getMiddleCommand()"
        );
    }

    public static Iterable<JointStatusType> getNonNullJointStatusIterable(JointStatusesType jsst) {
        return getNonNullIterable(
                requireNonNull(
                        requireNonNull(jsst, "jsst")
                                .getJointStatus(),
                        "getJointStatus()"
                )
        );
    }

    private static String vectorToDebugString(final @Nullable VectorType v) {
        return v == null ? "null" : v.toString() + " { "
                + "I=" + v.getI() + ","
                + "J=" + v.getJ() + ","
                + "K=" + v.getK() + " } ";
    }

    private static String pointToDebugString(final @Nullable PointType p) {
        return p == null ? "null" : p.toString() + " { "
                + "X=" + p.getX() + ","
                + "Y=" + p.getY() + ","
                + "Z=" + p.getZ() + " } ";
    }

    private static String poseToDebugString(final @Nullable PoseType p) {
        return p == null ? "null" : p.toString() + " { "
                + "Point=" + pointToDebugString(p.getPoint()) + ","
                + "XAxis=" + vectorToDebugString(p.getXAxis()) + ","
                + "ZAxis=" + vectorToDebugString(p.getZAxis()) + " } ";
    }

    private static String commandStatToDebugString(final @Nullable CommandStatusType c) {
        return c == null ? "null" : c.toString() + " { "
                + "CommandId=" + c.getCommandID() + ","
                + "CommandState=" + c.getCommandState() + ","
                + "StatusId=" + c.getStatusID() + " } ";
    }

    private static String jointStatusToDebugString(final @Nullable JointStatusType j) {
        return j == null ? "null" : j.toString() + " { "
                + "JointNumber=" + j.getJointNumber() + ","
                + "Position=" + j.getJointPosition() + ","
                + "Velocity=" + j.getJointVelocity() + ","
                + "TorqueOrForce=" + j.getJointTorqueOrForce()
                + " } ";
    }

    private static String jointStatusListToDebugString(final Iterable<JointStatusType> iterable) {
        StringBuilder sb = new StringBuilder();
        Iterator<JointStatusType> it = iterable.iterator();
        while (it.hasNext()) {
            JointStatusType jst = it.next();
            sb.append(jointStatusToDebugString(jst));
            if (it.hasNext()) {
                sb.append(',');
            }
        }
        return sb.toString();
    }

    private static String jointStatusesToDebugString(@Nullable JointStatusesType j) {
        return j == null ? "null" : j.toString() + " { "
                + "JointStatus=" + jointStatusListToDebugString(getNonNullJointStatusIterable(j)) + " } ";
    }

    public static String statToDebugString(@Nullable CRCLStatusType stat) {
        return stat == null ? "null" : stat.toString() + " { "
                + "CommandStatus=" + commandStatToDebugString(stat.getCommandStatus()) + ","
                + "Pose=" + poseToDebugString(CRCLPosemath.getNullablePose(stat)) + ","
                + "JointStatuses=" + jointStatusesToDebugString(stat.getJointStatuses()) + " } ";
    }
}
