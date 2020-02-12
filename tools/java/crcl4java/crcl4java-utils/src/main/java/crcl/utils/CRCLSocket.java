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
package crcl.utils;

import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLProgramType;
import crcl.base.CRCLStatusType;
import crcl.base.GetStatusType;
import crcl.base.ObjectFactory;
import static crcl.utils.CRCLUtils.getNonNullCmd;
import static crcl.utils.CRCLUtils.statToDebugString;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/*
 * 
 * NOTE: Comments beginning with {@literal @} or {@literal >>>} are used by Checker Framework Comments
 * beginning with {@literal @} must have no spaces in the comment or Checker will ignore
 * it.
 *
 * See http://types.cs.washington.edu/checker-framework for null pointer
 * checks. This file can be compiled without the Checker Framework, but using
 * the framework allows potential NullPointerExceptions to be found.
 */
 /*>>>
import org.checkerframework.checker.nullness.qual.*;
 */
/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov> }
 */
public class CRCLSocket implements AutoCloseable {

    public static final int DEFAULT_PORT = 64444;

    static final public UnaryOperator<String> addCRCLToState = new UnaryOperator<String>() {
        @Override
        public String apply(String t) {
            return addCRCLToStatePriv(t);
        }
    };

    private static class UtilSocketHider {

        static final CRCLSocket UTIL_SOCKET = new CRCLSocket();
    }

    public static CRCLSocket getUtilSocket() {
        return UtilSocketHider.UTIL_SOCKET;
    }

    final public @Nullable
    Socket getSocket() {
        if (null != socketChannel) {
            return socketChannel.socket();
        }
        return socket;
    }

    @Override
    public String toString() {
        Socket socket = getSocket();
        return "CRCLSocket(" + ((socket == null) ? "null" : socket.getRemoteSocketAddress() + ")");
    }

    static final public UnaryOperator<String> removeCRCLFromState = new UnaryOperator<String>() {

        @Override
        public String apply(String t) {
            return removeCRCLFromStatePriv(t);
        }
    };
    final public static String statusHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "<CRCLStatus\n"
            + "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
            + "  xsi:noNamespaceSchemaLocation=\"../xmlSchemas/CRCLStatus.xsd\">";
    final public static String cmdHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "<CRCLCommandInstance\n"
            + "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
            + "  xsi:noNamespaceSchemaLocation=\"../xmlSchemas/CRCLCommandInstance.xsd\">";
    final public static String progHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "<CRCLProgram\n"
            + "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
            + "  xsi:noNamespaceSchemaLocation=\"../xmlSchemas/CRCLProgramInstance.xsd\">";
    final public static boolean DEFAULT_JAXB_FRAGMENT = true;

    static @Nullable
    File commandXsdFile = null;

    public static @Nullable
    File getCommandXsdFile() {
        return commandXsdFile;
    }

    private static String addCRCLToStatePriv(String in) {
        return in.replaceAll("<CommandState>Working</CommandState>", "<CommandState>WORKING</CommandState>")
                .replaceAll("<CommandState>Done</CommandState>", "<CommandState>DONE</CommandState>")
                .replaceAll("<CommandState>Error</CommandState>", "<CommandState>ERROR</CommandState>")
                .replaceAll("<CommandState>Ready</CommandState>", "<CommandState>CRCL_Ready</CommandState>");
    }

    private static String removeCRCLFromStatePriv(String in) {
        return in.replaceAll("<CommandState>WORKING</CommandState>", "<CommandState>Working</CommandState>")
                .replaceAll("<CommandState>DONE</CommandState>", "<CommandState>Done</CommandState>")
                .replaceAll("<CommandState>ERROR</CommandState>", "<CommandState>Error</CommandState>")
                .replaceAll("<CommandState>CRCL_Ready</CommandState>", "<CommandState>Ready</CommandState>");
    }

    private @MonotonicNonNull
    SocketChannel socketChannel;

    /**
     * Get the value of socketChannel
     *
     * @return the value of socketChannel
     */
    public @Nullable
    SocketChannel getSocketChannel() {
        return socketChannel;
    }

    /**
     * Set the value of socketChannel
     *
     * @param socketChannel new value of socketChannel
     */
    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    private UnaryOperator<String> statusStringInputFilter = STRING_IDENTITY_OPERATOR;
    private UnaryOperator<String> statusStringOutputFilter = STRING_IDENTITY_OPERATOR;
    private boolean jaxbFragment = DEFAULT_JAXB_FRAGMENT;
    private final @Nullable
    Socket socket;

    private @Nullable
    String lastStatusString = null;
    private @Nullable
    String lastCommandString = null;
    private @Nullable
    String lastProgramString = null;

    private @Nullable
    Schema cmdSchema = null;
    private @Nullable
    Schema programSchema = null;
    private @Nullable
    Schema statSchema = null;
    /*@NonNull*/ protected final Marshaller m_cmd;
    /*@NonNull*/ protected final Unmarshaller u_cmd;
    /*@NonNull*/ protected final Marshaller m_prog;
    /*@NonNull*/ protected final Unmarshaller u_prog;
    /*@NonNull*/ protected final Marshaller m_stat;
    /*@NonNull*/ protected final Unmarshaller u_stat;
    private String readInProgressString = "";

    private @Nullable
    BufferedInputStream bufferedInputStream = null;
    private boolean useBufferedInputStream = true;
    private @Nullable
    SAXSource exiCommandInSaxSource = null;
    private @Nullable
    SAXSource exiStatusInSaxSource = null;
    private final ObjectFactory objectFactory
            = new ObjectFactory();
    final static public boolean DEFAULT_APPEND_TRAILING_ZERO = false;
    final static public boolean DEFAULT_RANDOM_PACKETING = false;
    public boolean appendTrailingZero = DEFAULT_APPEND_TRAILING_ZERO;
    public boolean randomPacketing = DEFAULT_RANDOM_PACKETING;
    private @Nullable
    Random random = null;
    public int rand_seed = 12345;
    private @Nullable
    String last_xml_version_header = null;
    private @Nullable
    String last_orig_first_tag = null;
    private boolean replaceHeader;

    private static volatile boolean protectionDomainChecked = false;

    public CRCLSocket() {
        this.socket = null;
    }

    private static final Logger LOGGER = Logger.getLogger(CRCLSocket.class.getName());
    private static final boolean DEBUG_JAXB_SELECTION
            = Boolean.getBoolean("crcl.DEBUG_JAXB_SELECTION");

    // Instance initializer called by all constructors , but not seperately callable.
    {
        try {
            ClassLoader cl = crcl.base.ObjectFactory.class.getClassLoader();
            if (null == cl) {
                throw new RuntimeException("crcl.base.ObjectFactory.class.getClassLoader() returned null");
            }
            final /*@NonNull*/ ClassLoader nnCl = (/*@NonNull*/ClassLoader) cl;

            if (!protectionDomainChecked) {
                String javaClassVersion = System.getProperty("java.class.version");
                if (DEBUG_JAXB_SELECTION) {
                    System.out.println("javaClassVersion = " + javaClassVersion);
                    String javaSpecVmVersion = System.getProperty("java.vm.specification.version");
                    System.out.println("javaSpecVmVersion = " + javaSpecVmVersion);
                    String javaVmVersion = System.getProperty("java.vm.version");
                    System.out.println("javaVmVersion = " + javaVmVersion);
                    String jaxbFactory = System.getProperty("javax.xml.bind.JAXBContextFactory");
                    System.out.println("jaxbFactory = " + jaxbFactory);
                    ProtectionDomain jaxbProDeom = javax.xml.bind.JAXBContext.class.getProtectionDomain();
                    System.out.println("jaxbProDeom = " + jaxbProDeom);
                }
                protectionDomainChecked = true;
                if (javaClassVersion.compareTo("52.0") > 0) {
                    String useEclipseJaxbPropertyString = System.getProperty("crcl.useEclipseJaxb");
                    boolean useEclipseJaxb = false;
                    if (null != useEclipseJaxbPropertyString) {
                        useEclipseJaxb = Boolean.valueOf(useEclipseJaxbPropertyString);
                    }
                    if (useEclipseJaxb) {
                        Class<?> eclipselinkClass;
                        try {
                            eclipselinkClass = Class.forName("org.eclipse.persistence.jaxb.JAXBContextFactory");
                            if (DEBUG_JAXB_SELECTION) {
                                System.out.println("eclipselinkClass = " + eclipselinkClass);
                            }
                            ProtectionDomain proDeom = javax.xml.bind.JAXBContext.class.getProtectionDomain();
                            LOGGER.log(Level.FINE, "JAXBContext.class.getProtectionDomain() = {0}", proDeom);
                            System.setProperty("javax.xml.bind.JAXBContextFactory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(CRCLSocket.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            JAXBContext context = JAXBContext.newInstance("crcl.base", nnCl);
            assert null != context : "@AssumeAssertion(nullness)";
            u_cmd = context.createUnmarshaller();
            m_cmd = context.createMarshaller();
            u_stat = context.createUnmarshaller();
            m_stat = context.createMarshaller();
            u_prog = context.createUnmarshaller();
            m_prog = context.createMarshaller();

            bufferedInputStream = null;
        } catch (JAXBException ex) {
            LOGGER.log(Level.SEVERE, "", ex);
            System.exit(0);
            throw new RuntimeException(ex);
        }
    }

    public CRCLSocket(Schema cmdSchema, Schema statSchema, Schema programSchema) {
        this(null, cmdSchema, statSchema, programSchema);
    }

    private volatile StackTraceElement cmdSchemSetTrace @Nullable []  = null;
    private volatile File @Nullable [] cmdSchemaFiles = null;

    public File @Nullable [] getCmdSchemaFiles() {
        return cmdSchemaFiles;
    }

    public void setCmdSchemaFiles(File[] cmdSchemaFiles) {
        this.cmdSchemaFiles = cmdSchemaFiles;
    }

    public StackTraceElement @Nullable [] getCmdSchemSetTrace() {
        return cmdSchemSetTrace;
    }

    public CRCLSocket(@Nullable Socket socket, @Nullable Schema cmdSchema, @Nullable Schema statSchema, @Nullable Schema programSchema) {
        this.socket = socket;
        this.cmdSchema = cmdSchema;
        this.cmdSchemSetTrace = Thread.currentThread().getStackTrace();
        this.statSchema = statSchema;
        this.programSchema = programSchema;
    }

    public CRCLSocket(@Nullable Socket socket) {
        this.socket = socket;
    }

    public CRCLSocket(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
        this.socket = socketChannel.socket();
    }

    public CRCLSocket(String hostname, int port) throws CRCLException, IOException {

        try {
            this.socket = new Socket(hostname, port);
        } catch (IOException iOException) {
            LOGGER.log(Level.SEVERE, "CRCLSocket failed to connect to host={0}, port={1}", new Object[]{hostname, port});
            throw iOException;
        }
    }

    public CRCLSocket(String hostname, int port, Schema cmdSchema, Schema statSchema, Schema programSchema) throws CRCLException, IOException {

        try {
            this.socket = new Socket(hostname, port);
            this.cmdSchemSetTrace = Thread.currentThread().getStackTrace();
            this.cmdSchema = cmdSchema;
            this.statSchema = statSchema;
            this.programSchema = programSchema;
        } catch (IOException iOException) {
            LOGGER.log(Level.SEVERE, "CRCLSocket failed to connect to host={0}, port={1}", new Object[]{hostname, port});
            throw iOException;
        }
    }

    public boolean isConnected() {
        Socket socket = getSocket();
        if (null == socket) {
            return false;
        }
        return socket.isConnected();
    }

    public void reconnect() throws IOException {
        if (null != socketChannel) {
            throw new RuntimeException("socketChannel != null");
        }
        Socket socketToReconnect = this.socket;
        if (null == socketToReconnect) {
            throw new RuntimeException("null == socket");
        }
        final SocketAddress remoteSocketAddress = socketToReconnect.getRemoteSocketAddress();
        if (null == remoteSocketAddress) {
            throw new RuntimeException("null == remoteSocketAddress");
        }
        socketToReconnect.connect(remoteSocketAddress);
    }

    public boolean isClosed() {
        Socket socket = getSocket();
        if (null == socket) {
            return false;
        }
        return socket.isClosed();
    }

    public int getLocalPort() {
        if (null != socketChannel) {
            return socketChannel.socket().getLocalPort();
        }
        if (null == this.socket) {
            return -1;
        }
        return this.socket.getLocalPort();
    }

    public int getPort() {
        if (null != socketChannel) {
            return socketChannel.socket().getLocalPort();
        }
        if (null == this.socket) {
            return -1;
        }
        return this.socket.getPort();
    }

    public @Nullable
    InetAddress getInetAddress() {
        if (null != socketChannel) {
            return socketChannel.socket().getInetAddress();
        }
        if (null == this.socket) {
            return null;
        }
        return this.socket.getInetAddress();
    }

    /**
     * Get the value of statusStringInputFilter
     *
     * @return the value of statusStringInputFilter
     */
    public UnaryOperator<String> getStatusStringInputFilter() {
        return statusStringInputFilter;
    }

    /**
     * Set the value of statusStringInputFilter
     *
     * @param statusStringInputFilter new value of statusStringInputFilter
     */
    public void setStatusStringInputFilter(UnaryOperator<String> statusStringInputFilter) {
        this.statusStringInputFilter = statusStringInputFilter;
    }

    /**
     * Get the value of statusStringOutputFilter
     *
     * @return the value of statusStringOutputFilter
     */
    public UnaryOperator<String> getStatusStringOutputFilter() {
        return statusStringOutputFilter;
    }

    /**
     * Set the value of statusStringOutputFilter
     *
     * @param statusStringOutputFilter new value of statusStringOutputFilter
     */
    public void setStatusStringOutputFilter(UnaryOperator<String> statusStringOutputFilter) {
        this.statusStringOutputFilter = statusStringOutputFilter;
    }

//    public static String DEFAULT_STATUS_NO_NAMESPACE_JAXB_SCHEMA_LOCATION = "../xmlSchemas/CRCLStatus.xsd";
//    public static String DEFAULT_COMMAND_NO_NAMESPACE_JAXB_SCHEMA_LOCATION = ".../xmlSchemas/CRCLCommandInstance.xsd";
//    public static String DEFAULT_PROGRAM_NO_NAMESPACE_JAXB_SCHEMA_LOCATION = "../xmlSchemas/CRCLProgramInstance.xsd";
//    public static String DEFAULT_JAXB_SCHEMA_LOCATION = "http://www.w3.org/2001/XMLSchema-instance";
    /**
     * Get the value of jaxbFragment
     *
     * @return the value of jaxbFragment
     */
    public boolean isJaxbFragment() {
        return jaxbFragment;
    }

    /**
     * Set the value of jaxbFragment
     *
     * @param jaxbFragment new value of jaxbFragment
     */
    public void setJaxbFragment(boolean jaxbFragment) {
        this.jaxbFragment = jaxbFragment;
    }

    /**
     * Get the value of lastStatusString
     *
     * @return the value of lastStatusString or null if no status has been
     * read/converted.
     */
    public @Nullable
    String getLastStatusString() {
        return lastStatusString;
    }

    /**
     * Get the value of lastCommandString
     *
     * @return the value of lastCommandString or null if no command has been
     * read/converted.
     */
    public @Nullable
    String getLastCommandString() {
        return lastCommandString;
    }

    /**
     * Get the value of cmdSchema
     *
     * @return the value of cmdSchema
     */
    public @Nullable
    Schema getProgramSchema() {
        return programSchema;
    }

    /**
     * Set the value of cmdSchema
     *
     * @param programSchema new value of cmdSchema
     */
    public void setProgramSchema(@Nullable Schema programSchema) {
        this.programSchema = programSchema;
        if (null != programSchema) {
            if (null != this.m_prog) {
                setUnmarshallerSchema(u_prog, programSchema);
            }
            if (null != this.u_prog) {
                this.u_prog.setSchema(programSchema);
            }
        }
    }

    /**
     * Get the value of cmdSchema
     *
     * @return the value of cmdSchema
     */
    public @Nullable
    Schema getCmdSchema() {
        return cmdSchema;
    }

    /**
     * Set the value of cmdSchema
     *
     * @param cmdSchema new value of cmdSchema
     */
    public void setCmdSchema(Schema cmdSchema) {
        this.cmdSchema = cmdSchema;
        this.cmdSchemSetTrace = Thread.currentThread().getStackTrace();
        if (null != cmdSchema) {
            if (null != m_cmd) {
                m_cmd.setSchema(cmdSchema);
            }
            if (null != u_cmd) {
                u_cmd.setSchema(cmdSchema);
            }
        }
    }

    /**
     * Get the value of statSchema
     *
     * @return the value of statSchema
     */
    public @Nullable
    Schema getStatSchema() {
        return statSchema;
    }

    /**
     * Set the value of statSchema
     *
     * @param statSchema new value of statSchema
     */
    public void setStatSchema(Schema statSchema) {
        this.statSchema = statSchema;
        if (null != statSchema) {
            if (null != m_stat) {
                m_stat.setSchema(statSchema);
            }
            if (null != u_stat) {
                u_stat.setSchema(statSchema);
            }
        }
    }

    @Override
    public void close() throws IOException {
//        creatorInfo.closed = true;
        exiCommandInSaxSource = null;
        if (null != bufferedInputStream) {
            bufferedInputStream.close();
            bufferedInputStream = null;
        }
        IOException exception = null;
        if (null != socketChannel) {
            try {
                socketChannel.close();
            } catch (IOException iOException) {
                exception = iOException;
            }
        }

        try {
            if (null != socket) {
                socket.close();
            }
        } catch (IOException iOException) {
            exception = iOException;
        }
        cmdSchema = null;
        statSchema = null;
        programSchema = null;
        if (null != exception) {
            throw exception;
        }
    }

//    @Override
//    protected void finalize() throws Throwable {
//        super.finalize();
//        this.close();
//    }
    public String getReadInProgressString() {
        return this.readInProgressString;
    }

//    public String readUntilEndTagOld(final String tag, final InputStream is) throws IOException {
//        byte ba1[] = new byte[1];
//        String rips = "";
//        final String endTagStartString = "</" + tag;
//        final String startTag = "<" + tag;
//        boolean insideStartTag = false;
//        boolean startTag_found = false;
//        boolean endTag_started = false;
//        String str = "";
//        String skipped_str = "";
//        StringBuilder skipped_str_sb = new StringBuilder();
//        synchronized (is) {
//            while (ba1[0] != '>' || !endTag_started && !Thread.currentThread().isInterrupted()) {
//                int bytes_read = is.read(ba1);
//                if (bytes_read != 1) {
//                    Level lvl = rips.length() > 0 ? Level.SEVERE : Level.FINE;
//                    final int brF = bytes_read;
//                    final String ripsF = rips;
//                    LOGGER.log(lvl, "CRCLSocket.readUntilEndTag({0}): read returned {1} before end of tag was found. str = {2}", new Object[]{tag, brF, ripsF});
//                    throw new SocketException("socket closed after read returned:" + bytes_read);
//                }
//                if (ba1[0] == 0) {
//                    continue;
//                }
//                rips += new String(ba1);
//                if (ba1[0] == '>' && !endTag_started && insideStartTag) {
//                    if (rips.endsWith("/>")) {
//                        break;
//                    }
//                    insideStartTag = false;
//                }
//                this.readInProgressString = rips;
//                if (!startTag_found) {
//                    while (rips.length() > 0
//                            && !rips.startsWith(startTag.substring(0, Math.min(rips.length(), startTag.length())))) {
////                        skipped_str += rips.substring(0, 1);
//                        if(LOGGER.isLoggable(Level.FINER)) {
//                            skipped_str_sb.append(rips.substring(0, 1));
//                        }
//                        rips = rips.substring(1);
//                        this.readInProgressString = rips;
//                    }
//                    if (rips.startsWith(startTag)) {
//                        startTag_found = true;
//                        insideStartTag = true;
//                    }
//                } else if (!endTag_started) {
//                    endTag_started = rips.endsWith(endTagStartString);
//                }
//            }
//            str = rips;
//            rips = "";
//            this.readInProgressString = rips;
//        }
//        final String threadName = Thread.currentThread().getName();
//        skipped_str = skipped_str_sb.toString();
//        final String skipped_str_f = skipped_str;
//        LOGGER.log(Level.FINER, "readUntilEndTag({0}) called with skipped_str=\"{1}\"  from Thread: {2}", new Object[]{tag, skipped_str_f, threadName});
//        return str;
//    }
    public String readUntilEndTag(final String tag, final InputStream is) throws IOException {
        String rips = "";
        final String endTagStartString = "</" + tag;
        final String startTag = "<" + tag;
        StringBuilder sb = new StringBuilder();
        StringBuilder skipped = new StringBuilder();
        synchronized (is) {
            byte lastbyte = 0;
            lastbyte = readUntilMatch(startTag, is, skipped);
            sb.append(startTag);
            lastbyte = readUntilMatch(">", is, sb);
            sb.append(">");
            if (lastbyte != '/') {
                readUntilMatch(endTagStartString, is, sb);
                sb.append(endTagStartString);
                readUntilMatch(">", is, sb);
                sb.append(">");
            }
        }

        final String str = sb.toString();
        return str;
    }

    private byte readUntilMatch(String toMatch, final InputStream is, StringBuilder sb) throws IOException {
        byte[] tagBytes;
        byte[] tagBytesPrep;
        tagBytes = toMatch.getBytes(UTF_8);
        tagBytesPrep = new byte[tagBytes.length];
        int tagBytesPrepped = 0;
        byte lastbyte = 0;
        while (tagBytesPrepped < tagBytesPrep.length) {
            int readret = is.read(tagBytesPrep, tagBytesPrepped, (tagBytesPrep.length - tagBytesPrepped));
            if (readret > 0) {
                tagBytesPrepped += readret;
                for (int i = 0; i < tagBytesPrepped; i++) {
                    if (tagBytesPrep[i] != tagBytes[i]) {
                        while (i < tagBytesPrepped - 1 && tagBytesPrep[i + 1] != tagBytes[0]) {
                            i++;
                        }
                        sb.append(new String(tagBytesPrep, 0, (i + 1),UTF_8));
                        lastbyte = tagBytesPrep[i];
                        if (i < tagBytesPrepped - 1) {
                            System.arraycopy(tagBytesPrep, i + 1, tagBytesPrep, 0, tagBytesPrepped - i - 1);
                            tagBytesPrepped -= (i + 1);
                        } else {
                            tagBytesPrepped = 0;
                        }
                        i = 0;
                        break;
                    }
                }
            } else if (readret < 0) {
                throw new EOFException("is(" + is + ").read(tagBytesPrep(" + Arrays.toString(tagBytesPrep) + "), tagBytesPrepped(" + tagBytesPrepped + "), (" + (tagBytesPrep.length - tagBytesPrepped) + "))) returned " + readret + ": sb=" + sb);
            }
        }
        return lastbyte;
    }

    @SuppressWarnings("nullness")
    private static void setUnmarshallerSchema(@Nullable Unmarshaller u, @Nullable Schema schema) {
        // we need to be able to set the schema to null but since 
        // the setSchema method is not annotated there is no way 
        // for a nullness checker to know this is safe.
        if (null != u) {
            u.setSchema(schema);
        }
    }

    private String unparsedCommandString = "";

    /**
     * Parse a string that may contain multiple or partial XML
     * CRCLCommandInstances, and return a list of those commands. Partial
     * commands are saved and used as a prefix to the string passed in the next
     * call to this function.
     *
     * @param s String that may contain multiple XML CRCLCommandInstances
     * @return list of CRCLCommandInstances from strings so far.
     * @throws CRCLException if the string is invalid
     */
    public List<CRCLCommandInstanceType> parseMultiCommandString(String s) throws CRCLException {
        return parseMultiCommandString(s, false);
    }

    /**
     * Parse a string that may contain multiple or partial XML
     * CRCLCommandInstances, and return a list of those commands. Partial
     * commands are saved and used as a prefix to the string passed in the next
     * call to this function.
     *
     * @param s String that may contain multiple XML CRCLCommandInstances
     * @param validate validate each instance against the schema.
     * @return list of CRCLCommandInstances from strings so far.
     * @throws CRCLException if the string is invalid
     */
    public List<CRCLCommandInstanceType> parseMultiCommandString(String s, boolean validate) throws CRCLException {
        unparsedCommandString += s;
        int index = -1;
        final String endtag = "</CRCLCommandInstance>";
        List<CRCLCommandInstanceType> list = new ArrayList<>();
        while (0 < (index = unparsedCommandString.indexOf(endtag))) {
            int endindex = index + endtag.length();
            String s0 = unparsedCommandString.substring(0, endindex);
            CRCLCommandInstanceType instance = stringToCommand(s0, validate);
            unparsedCommandString = unparsedCommandString.substring(endindex);
            list.add(instance);
        }
        return list;
    }

    private String unparsedStatusString = "";

    /**
     * Parse a string that may contain multiple or partial XML CRCLStatusTypes,
     * and return a list of those status messages. Partial commands are saved
     * and used as a prefix to the string passed in the next call to this
     * function.
     *
     * @param s String that may contain multiple XML CRCLStatusTypes
     * @return list of CRCLStatusTypes from strings so far.
     * @throws CRCLException if the string is invalid
     */
    public List<CRCLStatusType> parseMultiStatusString(String s) throws CRCLException {
        return parseMultiStatusString(s, false);
    }

    /**
     * Parse a string that may contain multiple or partial XML CRCLStatusTypes,
     * and return a list of those status messages. Partial commands are saved
     * and used as a prefix to the string passed in the next call to this
     * function.
     *
     * @param s String that may contain multiple XML CRCLStatusTypes
     * @param validate validate each instance against the schema.
     * @return list of CRCLStatusTypes from strings so far.
     * @throws CRCLException if the string is invalid
     */
    public List<CRCLStatusType> parseMultiStatusString(String s, boolean validate) throws CRCLException {
        unparsedStatusString += s;
        int index = -1;
        final String endtag = "</CRCLStatus>";
        List<CRCLStatusType> list = new ArrayList<>();
        while (0 < (index = unparsedStatusString.indexOf(endtag))) {
            int endindex = index + endtag.length();
            String s0 = unparsedStatusString.substring(0, endindex);
            CRCLStatusType instance = stringToStatus(s0, validate);
            unparsedStatusString = unparsedStatusString.substring(endindex);
            list.add(instance);
        }
        return list;
    }

    public CRCLCommandInstanceType stringToCommand(String str, boolean validate) throws CRCLException {
        this.checkCommandSchema(validate);

        synchronized (u_cmd) {
            try {
                this.lastCommandString = str;
                setUnmarshallerSchema(u_cmd, validate ? cmdSchema : null);
                JAXBElement<?> el
                        = (JAXBElement) u_cmd.unmarshal(new StringReader(str));
                CRCLCommandInstanceType instance
                        = (CRCLCommandInstanceType) el.getValue();
                if (null == instance) {
                    throw new RuntimeException("el.getValue() == null : el=" + el + ", str=" + str);
                }
                return instance;
            } catch (JAXBException ex) {
                throw new CRCLException("str=" + str + " \n" + ex, ex);
            }
        }

    }

    public CRCLCommandInstanceType readCommandFromStream(final InputStream is, boolean validate) throws JAXBException {

        synchronized (u_cmd) {
            setUnmarshallerSchema(u_cmd, validate ? cmdSchema : null);
            JAXBElement<?> el = (JAXBElement) u_cmd.unmarshal(is);
            CRCLCommandInstanceType instance
                    = (CRCLCommandInstanceType) el.getValue();
            if (null == instance) {
                throw new RuntimeException("el.getValue() == null : el=" + el);
            }
            return instance;
        }
    }

    public CRCLProgramType stringToProgram(String str, boolean validate) throws CRCLException {
        this.checkProgramSchema(validate);
        try {
            synchronized (u_prog) {
                this.lastProgramString = str;
                setUnmarshallerSchema(u_prog, validate ? programSchema : null);
                JAXBElement<?> el = (JAXBElement) u_prog.unmarshal(new StringReader(str));
                CRCLProgramType prog
                        = (CRCLProgramType) el.getValue();
                if (null == prog) {
                    throw new RuntimeException("el.getValue() == null : el=" + el);
                }
                return prog;
            }
        } catch (Exception ex) {
            throw new CRCLException(ex);
        }
    }

    public CRCLCommandInstanceType readCommand() throws CRCLException {
        try {
            return readCommand(false);
        } catch (Exception ex) {
            throw new CRCLException(ex);
        }
    }

    private int defaultSoTimeout = 0;

    public int getDefaultSoTimeout() {
        return defaultSoTimeout;
    }

    public void setDefaultSoTimeout(int defaultSoTimeout) {
        this.defaultSoTimeout = defaultSoTimeout;
    }

    public CRCLCommandInstanceType readCommand(boolean validate) throws CRCLException, IOException {
        return readCommand(validate, defaultSoTimeout);
    }

    public CRCLCommandInstanceType readCommand(boolean validate, int soTimeout) throws CRCLException, IOException {
        final String threadName = Thread.currentThread().getName();
        final String str = this.readUntilEndTag("CRCLCommandInstance", getBufferedInputStream(soTimeout));
        if (null == str) {
            throw new EOFException("readUntilEndTag returned null");
        }
        CRCLCommandInstanceType cmd = stringToCommand(str, validate);
        final CRCLCommandType cc = cmd.getCRCLCommand();
        final Level loglevel = (cc instanceof GetStatusType) ? Level.FINER : Level.FINE;
        final long ccID = (cc != null) ? cc.getCommandID() : -1;
        LOGGER.log(loglevel, "readCommand() returning {0} ID={1} str={2}  called from Thread: {3}", new Object[]{cc, ccID, str, threadName});
        this.lastCommandString = str;
        return cmd;
    }

    public List<CRCLCommandInstanceType> checkForCommands(boolean validate) throws CRCLException {
        try {
            SocketChannel channel = this.socketChannel;
            if (null != channel) {
                ByteBuffer bb = ByteBuffer.allocate(4096);
                int bytesread = channel.read(bb);
                final byte[] array = bb.array();
                String string = arrayToString(array, bytesread);
                return parseMultiCommandString(string, validate);
            }
            Socket sock = this.socket;
            if (null != sock) {
                int bytesavail = sock.getInputStream().available();
                if (bytesavail > 0) {
                    byte buf[] = new byte[bytesavail];
                    int bytes_read = sock.getInputStream().read(buf);
                    if (bytes_read > 0) {
                        String s = new String(buf, 0, bytes_read,UTF_8);
                        return parseMultiCommandString(s, validate);
                    }
                }
            } else {
                throw new IllegalStateException("socket and socketChannel are both null");
            }
        } catch (Exception ex) {
            Logger.getLogger(CRCLSocket.class.getName()).log(Level.SEVERE, null, ex);
            throw new CRCLException(ex);
        }
        return Collections.emptyList();
    }

    private String arrayToString(final byte[] array, int bytesread) {
        String string = new String(array, 0, bytesread,UTF_8);
        return string;
    }

    public List<CRCLStatusType> checkForStatusMessages(boolean validate) throws CRCLException {
        try {
            SocketChannel channel = this.socketChannel;
            if (null != channel) {
                ByteBuffer bb = ByteBuffer.allocate(4096);
                int bytesread = channel.read(bb);
                String string = arrayToString(bb.array(), bytesread);
                return parseMultiStatusString(string, validate);
            }
            Socket sock = this.socket;
            if (null != sock) {
                int bytesavail = sock.getInputStream().available();
                if (bytesavail > 0) {
                    byte buf[] = new byte[bytesavail];
                    int bytes_read = sock.getInputStream().read(buf);
                    if (bytes_read > 0) {
                        String s = arrayToString(buf, bytes_read);
                        return parseMultiStatusString(s, validate);
                    }
                }
            } else {
                throw new IllegalStateException("socket and socketChannel are both null");
            }
        } catch (Exception ex) {
            Logger.getLogger(CRCLSocket.class.getName()).log(Level.SEVERE, null, ex);
            throw new CRCLException(ex);
        }
        return Collections.emptyList();
    }

    public CRCLStatusType stringToStatus(String str, boolean validate) throws CRCLException {
        this.checkStatusSchema(validate);
        synchronized (u_stat) {
            try {
                if (this.statusStringInputFilter != null) {
                    str = this.statusStringInputFilter.apply(str);
                }
                lastStatusString = str;
                setUnmarshallerSchema(u_stat, validate ? statSchema : null);
                JAXBElement<?> el = (JAXBElement) u_stat.unmarshal(new StringReader(str));
                CRCLStatusType instance
                        = (CRCLStatusType) el.getValue();
                if (null == instance) {
                    throw new RuntimeException("el.getValue() == null : el=" + el);
                }
                return instance;
            } catch (JAXBException ex) {
                System.err.println("defaultStatSchemaFiles = " + Arrays.toString(CRCLSchemaUtils.getDefaultStatSchemaFiles()));
                throw new CRCLException(str, ex);
            }
        }
    }

    public CRCLStatusType readStatusFromStream(final InputStream is, boolean validate) throws JAXBException {
        synchronized (u_stat) {
            setUnmarshallerSchema(u_stat, validate ? statSchema : null);
            JAXBElement<?> el = (JAXBElement) u_stat.unmarshal(is);
            CRCLStatusType instance
                    = (CRCLStatusType) el.getValue();
            if (null == instance) {
                throw new RuntimeException("el.getValue() == null : el=" + el);
            }
            return instance;
        }
    }

    public CRCLStatusType
            readStatusFromSaxSource(SAXSource saxSource) throws JAXBException {
        synchronized (u_stat) {
            JAXBElement<CRCLStatusType> el
                    = u_stat.unmarshal(saxSource, CRCLStatusType.class
                    );
            return el.getValue();
        }
    }

    protected InputStream getBufferedInputStream(int timeout) throws IOException {

        if (null != socketChannel) {
            if (!socketChannel.isBlocking() && socketChannel.isRegistered()) {
                throw new IllegalStateException("Can not use SocketChannel inputStream when set non-blocking and registered with selector. It must be deregistered with the SelectionKey's cancel method.");
            }
            socketChannel = (SocketChannel) socketChannel.configureBlocking(true);
        }
        if (null != bufferedInputStream) {
            return bufferedInputStream;
        }
        Socket socket = getSocket();
        if (null == socket) {
            throw new IllegalStateException("socket is null");
        }
        socket.setSoTimeout(timeout);
        if (!useBufferedInputStream) {
            return socket.getInputStream();
        }
        BufferedInputStream newBufferedInputStream = new BufferedInputStream(socket.getInputStream());
        bufferedInputStream = newBufferedInputStream;
        return newBufferedInputStream;
    }

    public CRCLStatusType readStatus() throws CRCLException {
        return readStatus(false);
    }

    public CRCLStatusType readStatus(boolean validate) throws CRCLException {
        return readStatus(validate, defaultSoTimeout);
    }

    public CRCLStatusType readStatus(boolean validate, int soTimeout)
            throws CRCLException {
        long t0 = System.currentTimeMillis();
        try {
            this.lastStatusString = this.readUntilEndTag("CRCLStatus", getBufferedInputStream(soTimeout));
            if (null == this.lastStatusString) {
                throw new EOFException("readUntilEndTag returned null");
            }
            return stringToStatus(this.lastStatusString, validate);
        } catch (Exception ex) {
            long timeDiff = System.currentTimeMillis() - t0;
            Logger.getLogger(CRCLSocket.class.getName()).log(Level.SEVERE,
                    "timeDiff=" + timeDiff + ",port=" + getPort() + ", validate=" + validate + ", soTimeout=" + soTimeout, ex);
            final Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
            for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
                System.out.println("Thread = " + entry.getKey());
                System.out.println("Trace = " + CRCLUtils.traceToString(entry.getValue()));
                System.out.println("");
            }
            throw new CRCLException(ex);
        }
    }

    public String commandToString(CRCLCommandType cmd, boolean validate) {
        try {
            if (null == cmd) {
                throw new IllegalArgumentException("cmd == null");
            }
            if (cmd instanceof CRCLCommandWrapper) {
                CRCLCommandWrapper wrapper = (CRCLCommandWrapper) cmd;
                cmd = wrapper.getWrappedCommand();
            }
            CRCLCommandInstanceType instance = new CRCLCommandInstanceType();
            instance.setCRCLCommand(cmd);
            String str = removeHeader(this.commandToString(instance, validate));
            str = str.trim();
            if (str.endsWith("</CRCLCommandInstance>")) {
                str = str.substring(0, str.length() - "</CRCLCommandInstance>".length());
            }
            if (str.startsWith("<Name>")) {
                int endnameIndex = str.indexOf("</Name>");
                if (endnameIndex > 0) {
                    str = str.substring(endnameIndex + 7);
                }
            }
            return str;
        } catch (Exception ex) {
            Logger.getLogger(CRCLSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    private int checkCommandSchemaCount = 0;

    private void checkCommandSchema(boolean validate) throws CRCLException {
        checkCommandSchemaCount++;
        if (checkCommandSchemaCount > 100) {
            checkCommandSchemaCount = 0;
            cmdSchema = null;
        }
        if (validate) {
            if (null == cmdSchema) {
                Schema defaultCmdSchema = CRCLSchemaUtils.getDefaultCmdSchema();
                if (null == defaultCmdSchema) {
                    File fa[] = CRCLSchemaUtils.readCmdSchemaFiles();
                    if (null != fa) {
                        Schema cmdSchemaFromFiles = CRCLSchemaUtils.filesToCmdSchema(fa);
                        if (null != cmdSchemaFromFiles) {
                            setCmdSchema(cmdSchemaFromFiles);
                            setCmdSchemaFiles(fa);
                        }
                    }
                } else {
                    setCmdSchema(defaultCmdSchema);
                    if (null == cmdSchemaFiles) {
                        cmdSchemaFiles = CRCLSchemaUtils.getDefaultCmdSchemaFiles();;
                    }
                }
            }
        }
    }

    private int checkStatusSchemaCount = 0;

    private void checkStatusSchema(boolean validate) throws CRCLException {
        checkStatusSchemaCount++;
        if (checkStatusSchemaCount > 100) {
            checkStatusSchemaCount = 0;
            statSchema = null;
        }
        if (validate) {
            if (null == statSchema) {
                Schema defaultStatSchema = CRCLSchemaUtils.getDefaultStatSchema();
                if (null == defaultStatSchema) {
                    File fa[] = CRCLSchemaUtils.readStatSchemaFiles();
                    if (null != fa) {
                        Schema schemaFromFiles = CRCLSchemaUtils.filesToStatSchema(fa);
                        if (null != schemaFromFiles) {
                            setStatSchema(schemaFromFiles);
                        }
                    }
                } else {
                    setStatSchema(defaultStatSchema);
                }
            }
        }
    }

    private int checkProgramSchemaCount = 0;

    private void checkProgramSchema(boolean validate) throws CRCLException {
        checkProgramSchemaCount++;
        if (checkProgramSchemaCount > 100) {
            checkProgramSchemaCount = 0;
            programSchema = null;
        }
        if (validate) {
            if (null == programSchema) {
                Schema defaultProgramSchema = CRCLSchemaUtils.getDefaultProgramSchema();
                if (null == defaultProgramSchema) {
                    File fa[] = CRCLSchemaUtils.readProgramSchemaFiles();
                    if (null != fa) {
                        setProgramSchema(CRCLSchemaUtils.filesToProgramSchema(fa));
                    }
                } else {
                    setProgramSchema(defaultProgramSchema);
                }
            }
        }
    }

    @SuppressWarnings("nullness")
    private void setMarshallerSchema(Marshaller marshaller, @Nullable Schema schema) {
        if (null != marshaller) {
            marshaller.setSchema(schema);
        }
    }

    @SuppressWarnings({"nullness", "initialization"})
    private static <T> @NonNull JAXBElement<@NonNull T> filterJaxbElement(@Nullable JAXBElement<T> in) {
        if (null == in) {
            throw new NullPointerException("JAXBElement< T> in");
        }
        if (null == in.getValue()) {
            throw new NullPointerException("in.getValue()");
        }
        return (JAXBElement<T>) in;
    }

    public String commandToString(CRCLCommandInstanceType cmd, boolean validate) throws CRCLException {
        if (null == cmd.getCRCLCommand()) {
            throw new IllegalArgumentException("cmd.getCRCLCommand() must not be null. Use setCRCLCommand(...).");
        }
        JAXBElement<CRCLCommandInstanceType> jaxb_cmd
                = filterJaxbElement(
                        objectFactory.createCRCLCommandInstance(cmd)
                );
        StringWriter sw = new StringWriter();
        String ret = null;
        this.checkCommandSchema(validate);
        synchronized (m_cmd) {
            try {
                setMarshallerSchema(m_cmd, validate ? cmdSchema : null);
                m_cmd.marshal(jaxb_cmd, sw);
                String str = sw.toString();
                if (replaceHeader) {
                    str = removeHeader(str);
                    ret = cmdHeader + str;
                } else {
                    ret = str;
                }
            } catch (JAXBException ex) {
                throw new CRCLException(ex);
            }
        }
        this.lastCommandString = ret;
        return ret;
    }

    public String programToString(CRCLProgramType prog, boolean validate) throws CRCLException {
        JAXBElement<CRCLProgramType> jaxb_prog
                = filterJaxbElement(
                        objectFactory.createCRCLProgram(prog)
                );
        StringWriter sw = new StringWriter();
        this.checkProgramSchema(validate);
        synchronized (m_prog) {
            try {
                setMarshallerSchema(m_prog, validate ? programSchema : null);
                m_prog.marshal(jaxb_prog, sw);
                String str = sw.toString();
                if (replaceHeader) {
                    str = removeHeader(str);
                    this.lastProgramString = progHeader + str;
                } else {
                    this.lastProgramString = str;
                }
            } catch (JAXBException ex) {
                throw new CRCLException(ex);
            }
        }
        return this.lastProgramString;
    }

    public String commandToPrettyString(CRCLCommandType cmd) throws JAXBException, CRCLException {
        CRCLCommandInstanceType instance = new CRCLCommandInstanceType();
        if (cmd instanceof CRCLCommandWrapper) {
            CRCLCommandWrapper wrapper = (CRCLCommandWrapper) cmd;
            cmd = wrapper.getWrappedCommand();
        }
        instance.setCRCLCommand(cmd);
        return commandInstanceToPrettyString(instance, false);
    }

    public String commandToPrettyString(CRCLCommandType cmd, String errorText) {
        if (cmd instanceof CRCLCommandWrapper) {
            CRCLCommandWrapper wrapper = (CRCLCommandWrapper) cmd;
            cmd = wrapper.getWrappedCommand();
        }
        try {
            return commandToPrettyString(cmd);
        } catch (JAXBException | CRCLException ex) {
            Logger.getLogger(CRCLSocket.class.getName()).log(Level.SEVERE, "could not convert cmd=" + cmd, ex);
        }
        return errorText;
    }

    public String commandInstanceToPrettyString(CRCLCommandInstanceType cmd, boolean validate) throws JAXBException, CRCLException {
        if (null == cmd.getCRCLCommand()) {
            throw new IllegalArgumentException("cmd.getCRCLCommand() must not be null. Use setCRCLCommand(...).");
        }
//        if (null == cmd.getCRCLCommand().getCommandID()) {
//            throw new IllegalArgumentException("cmd.getCRCLCommand().getCommandID() must not be null. Use setCommandID(BigInteger.valueOf(...)).");
//        }
        JAXBElement<CRCLCommandInstanceType> jaxb_cmd
                = filterJaxbElement(
                        objectFactory.createCRCLCommandInstance(cmd)
                );
        StringWriter sw = new StringWriter();
        String ret = null;
        try {
            this.checkCommandSchema(validate);
            synchronized (m_cmd) {
                setMarshallerSchema(m_cmd, validate ? cmdSchema : null);
                m_cmd.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                m_cmd.marshal(jaxb_cmd, sw);
                m_cmd.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
                String str = sw.toString();
                if (replaceHeader) {
                    str = removeHeader(str);
                    ret = cmdHeader + str;
                } else {
                    ret = str;
                }
            }
        } catch (Exception exception) {
            System.out.println("");
            System.out.flush();
            System.err.println("cmdSchemSetTrace = " + CRCLUtils.traceToString(this.cmdSchemSetTrace));
            final File[] cmdSchemaFilesLocal = cmdSchemaFiles;
            System.err.println("cmdSchemaFiles = " + Arrays.toString(cmdSchemaFilesLocal));
            if (null != cmdSchemaFilesLocal) {
                for (int i = 0; i < cmdSchemaFilesLocal.length; i++) {
                    try {
                        System.err.println("cmdSchemaFiles[" + i + "]=" + cmdSchemaFilesLocal[i].getCanonicalPath());
                    } catch (Exception ex) {
                        Logger.getLogger(CRCLSocket.class.getName()).log(Level.SEVERE, "i=" + i + ", cmdSchemaFiles[i]=" + cmdSchemaFilesLocal[i], ex);
                    }
                }
            }
            LOGGER.log(Level.SEVERE, "sw=\n" + sw.toString() + "\n, cmd=" + cmd + ",, validate=" + validate, exception);
            if (exception instanceof RuntimeException) {
                throw (RuntimeException) exception;
            } else {
                throw new RuntimeException(exception);
            }
        }
        this.lastCommandString = ret;
        return ret;
    }

    public String commandInstanceToPrettyDocString(CRCLCommandInstanceType cmd, boolean validate) throws CRCLException {
        try {
            if (null == cmd.getCRCLCommand()) {
                throw new IllegalArgumentException("cmd.getCRCLCommand() must not be null. Use setCRCLCommand(...).");
            }
            JAXBElement<CRCLCommandInstanceType> jaxb_cmd
                    = filterJaxbElement(
                            objectFactory.createCRCLCommandInstance(cmd)
                    );
            StringWriter sw = new StringWriter();
            String ret = null;
            checkCommandSchema(validate);

            synchronized (m_cmd) {
                setMarshallerSchema(m_cmd, validate ? cmdSchema : null);
                m_cmd.setProperty(Marshaller.JAXB_FRAGMENT, false);
                m_cmd.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                m_cmd.marshal(jaxb_cmd, sw);
                m_cmd.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
                m_cmd.setProperty(Marshaller.JAXB_FRAGMENT, jaxbFragment);
                String str = sw.toString();
                if (replaceHeader) {
                    str = removeHeader(str);
                    ret = cmdHeader + str;
                } else {
                    ret = str;
                }
            }
            this.lastCommandString = ret;
            return ret;
        } catch (Exception ex) {
            throw new CRCLException(ex);
        }
    }

    public String programToPrettyString(CRCLProgramType prog, boolean validate) throws CRCLException {
        JAXBElement<CRCLProgramType> jaxb_prog
                = filterJaxbElement(
                        objectFactory.createCRCLProgram(prog)
                );
        StringWriter sw = new StringWriter();
        this.checkProgramSchema(validate);
        synchronized (m_prog) {
            try {
                setMarshallerSchema(m_prog, validate ? programSchema : null);
                m_prog.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                m_prog.marshal(jaxb_prog, sw);
                m_prog.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
                String str = sw.toString();
                if (replaceHeader) {
                    str = removeHeader(str);
                    this.lastProgramString = progHeader + str;
                } else {
                    this.lastProgramString = str;
                }
            } catch (JAXBException ex) {
                throw new CRCLException(ex);
            }
        }
        return this.lastProgramString;
    }

    public String programToPrettyDocString(CRCLProgramType prog, boolean validate) throws JAXBException {
        JAXBElement<CRCLProgramType> jaxb_proj
                = filterJaxbElement(
                        objectFactory.createCRCLProgram(prog)
                );
        StringWriter sw = new StringWriter();
        synchronized (m_prog) {
            setMarshallerSchema(m_prog, validate ? programSchema : null);
            m_prog.setProperty(Marshaller.JAXB_FRAGMENT, false);
            m_prog.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m_prog.marshal(jaxb_proj, sw);
            m_prog.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
            m_prog.setProperty(Marshaller.JAXB_FRAGMENT, jaxbFragment);
            String str = sw.toString();
            if (replaceHeader) {
                str = removeHeader(str);
                this.lastProgramString = progHeader + str;
            } else {
                this.lastProgramString = str;
            }
        }
        return this.lastProgramString;
    }

    public void writeCommand(CRCLCommandInstanceType cmd) throws CRCLException {
        writeCommand(cmd, false);
    }

    public synchronized void writeCommand(CRCLCommandInstanceType cmd, boolean validate) throws CRCLException {
        try {
            final CRCLCommandType cc = cmd.getCRCLCommand();
            if (null == cc) {
                throw new IllegalArgumentException("cmd.getCRCLCommand() must not be null. Use setCRCLCommand(...).");
            }
//            if (null == cc.getCommandID()) {
//                throw new IllegalArgumentException("cmd.getCRCLCommand().getCommandID() must not be null. Use setCommandID(BigInteger.valueOf(...)).");
//            }
            final String threadName = Thread.currentThread().getName();
            final Level loglevel = (cc instanceof GetStatusType) ? Level.FINER : Level.FINE;
            LOGGER.log(loglevel, "writeCommand({0} ID={1}) called from Thread: {2}", new Object[]{cc, cc.getCommandID(), threadName});
            final Socket socket = getSocket();
            if (null == socket) {
                throw new IllegalStateException("Internal socket is null.");
            }
            assert null != socket : "@AssumeAssertion(nullable)";
            final String str = commandToString(cmd, validate);
            LOGGER.log(loglevel, "writeCommand({0} ID={1}) with str = {2} called from Thread: {3}", new Object[]{cc, cc.getCommandID(), str, threadName});
            writeWithFill(str);
            this.lastCommandString = str;
        } catch (SocketException socketException) {
            try {
                close();
            } catch (IOException ex) {
                Logger.getLogger(CRCLSocket.class.getName()).log(Level.SEVERE, null, ex);
                throw new CRCLException(ex);
            }
            throw new CRCLException(socketException);
        } catch (IOException | InterruptedException ex) {
            throw new CRCLException(ex);
        }
    }

    public void writeCommand(CRCLCommandType cc) throws CRCLException {
        writeCommand(cc, false);
    }

    public synchronized void writeCommand(CRCLCommandType cc, boolean validate) throws CRCLException {
        CRCLCommandInstanceType commandInstance = new CRCLCommandInstanceType();
        commandInstance.setCRCLCommand(cc);
        writeCommand(commandInstance, validate);
    }

    protected void writePackets(byte ba[]) throws IOException, InterruptedException {
        SocketChannel channel = this.socketChannel;
        if (null != channel) {
            writePackets(channel, ba);
        } else {
            Socket sock = getSocket();
            if (null == sock) {
                throw new IllegalStateException("getSocket() returned null");
            }
            writePackets(sock.getOutputStream(), ba);
        }
    }

    private void writePackets(SocketChannel channel, byte ba[]) throws IOException, InterruptedException {
        if (!this.randomPacketing) {
            int writesTried = 0;
            final ByteBuffer baWrapBB = ByteBuffer.wrap(ba);
            int byteswritten = channel.write(baWrapBB);
            try {
                while (byteswritten < ba.length) {
                    writesTried++;
                    final ByteBuffer remainingBB = ByteBuffer.wrap(ba, byteswritten, ba.length - byteswritten);
                    byteswritten += channel.write(remainingBB);
                    Thread.sleep(20);
                }
            } catch (Exception ex1) {
                System.err.println("writesTried = " + writesTried);
                System.err.println("byteswritten = " + byteswritten);
                System.err.println("ba.length = " + ba.length);
                System.err.println("ba = " + Arrays.toString(ba));
                System.err.println("channel = " + channel);
                System.out.println("channel.getLocalAddress() = " + channel.getLocalAddress());
                System.out.println("channel.getRemoteAddress() = " + channel.getRemoteAddress());
                LOGGER.log(Level.SEVERE,
                        "",
                        ex1);
                if (ex1 instanceof RuntimeException) {
                    throw (RuntimeException) ex1;
                } else {
                    throw new RuntimeException(ex1);
                }
            }
        } else {
            if (null == random) {
                random = new Random(rand_seed);
            }
            assert null != random : "@AssumeAssertion(nullable)";
            writeRandomSizedPackets(ba, channel, random);
        }
    }

    private void writePackets(OutputStream os, byte ba[]) throws IOException, InterruptedException {
        if (!this.randomPacketing) {
            os.write(ba);
        } else {
            if (null == random) {
                random = new Random(rand_seed);
            }
            assert null != random : "@AssumeAssertion(nullable)";
            writeRandomSizedPackets(ba, os, random);
        }
    }

    private void writeRandomSizedPackets(byte[] ba, SocketChannel channel, Random rand) throws InterruptedException, IOException {
        int bytes_written = 0;
        while (bytes_written < ba.length) {
            int bytes_to_write = rand.nextInt(ba.length - 1) + 1;
            if (bytes_to_write >= ba.length - bytes_written) {
                bytes_to_write = ba.length - bytes_written;
            }
            int write_ret = channel.write(ByteBuffer.wrap(ba, bytes_written, bytes_to_write));
            bytes_written += write_ret;
            if (bytes_written < ba.length) {
                Thread.sleep(rand.nextInt(150));
            }
        }
    }

    private void writeRandomSizedPackets(byte[] ba, OutputStream os, Random rand) throws InterruptedException, IOException {
        int bytes_written = 0;
        while (bytes_written < ba.length) {
            int bytes_to_write = rand.nextInt(ba.length - 1) + 1;
            if (bytes_to_write >= ba.length - bytes_written) {
                bytes_to_write = ba.length - bytes_written;
            }
            os.write(ba, bytes_written, bytes_to_write);
            bytes_written += bytes_to_write;
            if (bytes_written < ba.length) {
                os.flush();
                Thread.sleep(rand.nextInt(150));
            }
        }
    }

    public void writeWithFill(String str) throws IOException, InterruptedException {
        try {
            if (null != socketChannel) {
                if (!appendTrailingZero) {
                    this.writePackets(socketChannel, str.getBytes(UTF_8));
                } else {
                    int len = str.length();
                    byte bytesPlusOne[] = new byte[len + 1];
                    System.arraycopy(str.getBytes(UTF_8), 0, bytesPlusOne, 0, len);
                    this.writePackets(socketChannel, bytesPlusOne);
                }
                return;
            }

            final Socket socket = getSocket();
            if (null == socket) {
                throw new IllegalStateException("Internal socket is null.");
            }
            assert null != socket : "@AssumeAssertion(nullable)";
            OutputStream os = socket.getOutputStream();
            synchronized (os) {
                if (!appendTrailingZero) {
                    this.writePackets(os, str.getBytes(UTF_8));
                } else {
                    int len = str.length();
                    byte bytesPlusOne[] = new byte[len + 1];
                    System.arraycopy(str.getBytes(UTF_8), 0, bytesPlusOne, 0, len);
                    this.writePackets(os, bytesPlusOne);
                }
                os.flush();
            }
        } catch (IOException | InterruptedException | IllegalStateException iOException) {
            LOGGER.log(Level.SEVERE,
                    "str=" + str,
                    iOException);
            throw iOException;
        }
    }

    public void writeProgram(CRCLProgramType prog, boolean validate) throws CRCLException {
        try {
            this.lastProgramString = programToString(prog, validate);
            this.writeWithFill(this.lastProgramString);
        } catch (IOException | InterruptedException ex) {
            throw new CRCLException(ex);
        }
    }

    /**
     * Get the value of replaceHeader
     *
     * @return the value of replaceHeader
     */
    public boolean isReplaceHeader() {
        return replaceHeader;
    }

    /**
     * Set the value of replaceHeader
     *
     * @param replaceHeader new value of replaceHeader
     */
    public void setReplaceHeader(boolean replaceHeader) {
        this.replaceHeader = replaceHeader;
    }

    public String statusToString(CRCLStatusType status, boolean validate) throws CRCLException {

        if (null == status) {
            throw new NullPointerException("status");
        }
        if (status.getCommandStatus() == null) {
            throw new IllegalArgumentException("status.getCommandStatus()  must not be null. Use setCommandStatus(...)");
        }
//        if (status.getCommandStatus().getCommandID() == null) {
//            throw new IllegalArgumentException("status.getCommandStatus().getCommandID()  must not be null. Use getCommandStatus().setCommandID(BigInteger.valueOf(...))");
//        }
//        if (status.getCommandStatus().getStatusID() == null) {
//            throw new IllegalArgumentException("status.getCommandStatus().getStatusID()  must not be null. Use getCommandStatus().setStatusID(BigInteger.valueOf(...))");
//        }
        JAXBElement<CRCLStatusType> jaxb_status
                = filterJaxbElement(
                        objectFactory.createCRCLStatus(status)
                );
        StringWriter sw = new StringWriter();
        this.checkStatusSchema(validate);
        synchronized (m_stat) {
            setMarshallerSchema(m_stat, validate ? statSchema : null);
            try {
                m_stat.marshal(jaxb_status, sw);
            } catch (Exception ex1) {
                LOGGER.log(Level.SEVERE,
                        "First Exception validate=" + validate + " sw.toString() = " + sw.toString() + ",status=" + statToDebugString(status),
                        ex1);
                sw = new StringWriter();
                try {
                    setMarshallerSchema(m_stat, null);
                    m_stat.marshal(jaxb_status, sw);
                } catch (Exception ex2) {
                    LOGGER.log(Level.SEVERE, "Second Exception", ex2);
                    throw new RuntimeException(ex2);
                }
            }
            String str = sw.toString();
            if (replaceHeader) {
                str = removeHeader(str);
                this.lastStatusString = statusHeader + str;
            } else {
                this.lastStatusString = str;
            }
            if (null != this.statusStringOutputFilter) {
                this.lastStatusString = this.statusStringOutputFilter.apply(this.lastStatusString);
            }
            return this.lastStatusString;
        }
    }

    private String removeHeader(String str) {
        int qgtindex = str.indexOf("?>");
        if (qgtindex > 0) {
            last_xml_version_header = str.substring(0, qgtindex + 2);
            str = str.substring(qgtindex + 2);
        }
        int gtindex = str.indexOf('>');
        if (gtindex > 0) {
            last_orig_first_tag = str.substring(0, gtindex + 1);
            str = str.substring(gtindex + 1);
        }
        return str;
    }

    public static String statusToPrettyString(CRCLStatusType status) {
        try {
            return getUtilSocket().statusToPrettyString(status, false);
        } catch (Exception e) {
            Logger.getLogger(CRCLSocket.class.getName()).log(Level.SEVERE, null, e);
            String msg = e.getMessage();
            if (null == msg) {
                return "EXCEPTION";
            }
            return msg;
        }
    }

    public String statusToPrettyString(CRCLStatusType status, boolean validate) throws JAXBException {
        JAXBElement<CRCLStatusType> jaxb_status
                = filterJaxbElement(
                        objectFactory.createCRCLStatus(status)
                );
        StringWriter sw = new StringWriter();
        synchronized (m_stat) {
            setMarshallerSchema(m_stat, validate ? statSchema : null);
            m_stat.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m_stat.marshal(jaxb_status, sw);
            m_stat.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
            String str = sw.toString();
            if (replaceHeader) {
                str = removeHeader(str);
                this.lastStatusString = statusHeader + str;
            } else {
                this.lastStatusString = str;
            }
            if (null != this.statusStringOutputFilter) {
                this.lastStatusString = this.statusStringOutputFilter.apply(this.lastStatusString);
            }
            return this.lastStatusString;
        }
    }

    public String commandToSimpleString(CRCLCommandInstanceType cmdInstance) throws ParserConfigurationException, SAXException, IOException {
        return commandToSimpleString(getNonNullCmd(cmdInstance));
    }

    public static String cmdToString(CRCLCommandType cmd) {
        try {
            return getUtilSocket().commandToSimpleString(cmd, 12, 60);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(CRCLSocket.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }
    }

    public static String cmdToString(CRCLCommandType cmd, int max_fields, int max_length) {
        try {
            if (null == cmd) {
                throw new IllegalArgumentException("cmd == null");
            }
            return getUtilSocket().commandToSimpleString(cmd, max_fields, max_length);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(CRCLSocket.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }
    }

    public static class XmlToCsvHandler extends DefaultHandler {

        @SuppressWarnings({"deprecation","JdkObsolete"})
        private final StringBuffer buffer = new StringBuffer();
        
        private int fields = 0;
        private int last_end_buffer_length = 0;
        private int maxFields;

        public int getMaxFields() {
            return maxFields;
        }

        public void setMaxFields(int maxFields) {
            this.maxFields = maxFields;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (fields <= maxFields) {
                buffer.append(ch, start, length);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            final int bl = buffer.length();
            if (bl > last_end_buffer_length) {
                fields++;
                if (fields < maxFields) {
                    buffer.append(",");
                } else if (fields == maxFields) {
                    buffer.append(" ...");
                }
                last_end_buffer_length = buffer.length();
            }
        }

        @Override
        public String toString() {
            return buffer.toString();
        }

    }

    static public String commandToSimpleString(@Nullable CRCLCommandType cmd) {
        try {
            if (null == cmd) {
                return "null";
            }
            return getUtilSocket().commandToSimpleString(cmd, 15, 80);
        } catch (Exception ex) {
            Logger.getLogger(CRCLSocket.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }
    }

    public String commandToSimpleString(@Nullable CRCLCommandType cmd, final int max_fields, final int max_length) throws ParserConfigurationException, SAXException, IOException {
        if (null == cmd) {
            return "null";
        }
        if (cmd instanceof CRCLCommandWrapper) {
            CRCLCommandWrapper wrapper = (CRCLCommandWrapper) cmd;
            cmd = wrapper.getWrappedCommand();
        }
        String xmlString = this.commandToString(cmd, false);
//        throw new RuntimeException(xmlString);
        try {
            XmlToCsvHandler handler = new XmlToCsvHandler();
            handler.setMaxFields(max_fields);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlreader = parser.getXMLReader();
            xmlreader.setContentHandler(handler);
            xmlreader.parse(new InputSource(new StringReader(xmlString)));
            String cmdName = cmd.getClass().getName();
            int lpindex = cmdName.lastIndexOf('.');
            if (lpindex > 0 && lpindex < cmdName.length() - 1) {
                cmdName = cmdName.substring(lpindex + 1);
            }
            if (cmdName.endsWith("Type")) {
                cmdName = cmdName.substring(0, cmdName.length() - 4);
            }
            String content = handler.toString();
            content = content.trim();
            if (content.length() > max_length) {
                content = content.substring(0, (max_length - 4)) + " ...";
            }
            return cmdName + " " + content;
        } catch (SAXException sAXException) {
            throw new SAXException("xmlString=\"" + xmlString + "\"", sAXException);
        } catch (IOException iOException) {
            throw new IOException("xmlString=" + xmlString, iOException);
        }
    }

    static public String statusToSimpleString(@Nullable CRCLStatusType stat) {
        try {
            if (null == stat) {
                return "null";
            }
            return " CRCLStatusType{ " + getUtilSocket().statusToSimpleString(stat, 30, 160) + " } ";
        } catch (Exception ex) {
            Logger.getLogger(CRCLSocket.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }
    }

    public String statusToSimpleString(@Nullable CRCLStatusType stat, final int max_fields, final int max_length) {
        if (null == stat) {
            return "null";
        }
        try {
            String xmlString = this.statusToString(stat, false);
            try {
                XmlToCsvHandler handler = new XmlToCsvHandler();
                handler.setMaxFields(max_fields);
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser parser = factory.newSAXParser();
                XMLReader xmlreader = parser.getXMLReader();
                xmlreader.setContentHandler(handler);
                xmlreader.parse(new InputSource(new StringReader(xmlString)));
                String cmdName = stat.getClass().getName();
                int lpindex = cmdName.lastIndexOf('.');
                if (lpindex > 0 && lpindex < cmdName.length() - 1) {
                    cmdName = cmdName.substring(lpindex + 1);
                }
                if (cmdName.endsWith("Type")) {
                    cmdName = cmdName.substring(0, cmdName.length() - 4);
                }
                String content = handler.toString();
                content = content.trim();
                if (content.length() > max_length) {
                    content = content.substring(0, (max_length - 4)) + " ...";
                }
                return cmdName + " " + content;
            } catch (SAXException sAXException) {
                throw new SAXException("xmlString=" + xmlString, sAXException);
            } catch (IOException iOException) {
                throw new IOException("xmlString=" + xmlString, iOException);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "stat=" + stat, ex);
            throw new RuntimeException(ex);
        }
    }

    public void writeStatus(CRCLStatusType status) throws CRCLException {
        writeStatus(status, false);
    }

    public synchronized void writeStatus(CRCLStatusType status, boolean validate)
            throws CRCLException {
        try {
            if (status.getCommandStatus() == null) {
                throw new IllegalArgumentException("status.getCommandStatus()  must not be null. Use setCommandStatus(...)");
            }
//            if (status.getCommandStatus().getCommandID() == null) {
//                throw new IllegalArgumentException("status.getCommandStatus().getCommandID()  must not be null. Use getCommandStatus().setCommandID(BigInteger.valueOf(...))");
//            }
//            if (status.getCommandStatus().getStatusID() == null) {
//                throw new IllegalArgumentException("status.getCommandStatus().getStatusID()  must not be null. Use getCommandStatus().setStatusID(BigInteger.valueOf(...))");
//            }
            final Socket socket = getSocket();
            if (null == socket) {
                throw new IllegalStateException("Internal socket is null.");
            }
            assert null != socket : "@AssumeAssertion(nullable)";
            this.lastStatusString = statusToString(status, validate);
            this.writeWithFill(this.lastStatusString);
        } catch (IOException | InterruptedException ex) {
            System.err.println("lastStatusString=" + lastStatusString);
            System.err.println("socketChannel=" + socketChannel);
            if (null != socketChannel) {
                try {
                    System.err.println("socketChannel.getLocalAddress()=" + socketChannel.getLocalAddress());
                    System.err.println("socketChannel.getRemodeAddress()=" + socketChannel.getRemoteAddress());
                } catch (IOException iOException) {
                    LOGGER.log(Level.SEVERE, "", iOException);
                }
            }
            System.err.println("socket=" + socket);
            throw new CRCLException(ex);
        }
    }

    static public interface UnaryOperator<T> {

        public T apply(T t);
    }

    private static final class IdentityUnaryOperator<T> implements UnaryOperator<T> {

        @Override
        public T apply(T t) {
            return t;
        }
    }

    private static final IdentityUnaryOperator<String> STRING_IDENTITY_OPERATOR
            = new IdentityUnaryOperator<>();
}
