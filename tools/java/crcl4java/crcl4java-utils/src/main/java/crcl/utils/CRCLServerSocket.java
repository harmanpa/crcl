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
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

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
public class CRCLServerSocket implements AutoCloseable, Runnable {

    private final List<CRCLServerClientInfo> clients = new ArrayList<>();

    public static class CRCLServerClientInfo implements AutoCloseable {

        private final CRCLSocket socket;

        /*@Nullable*/
        private final Future<?> future;

        public CRCLSocket getSocket() {
            return socket;
        }

        /*@Nullable*/
        public Future<?> getFuture() {
            return future;
        }

        public CRCLServerClientInfo(CRCLSocket socket) {
            this(socket, ((Future<?>) null));
        }

        public CRCLServerClientInfo(CRCLSocket socket, /*@Nullable*/ Future<?> future) {
            this.socket = socket;
            this.future = future;
        }

        @Override
        public void close() {
            try {
                if (null != socket) {
                    this.socket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(CRCLServerSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (null != future) {
                this.future.cancel(true);
            }
        }
    }

    /**
     * Get the value of clients
     *
     * @return the value of clients
     */
    public List<CRCLServerClientInfo> getClients() {
        return Collections.unmodifiableList(clients);
    }

    private int port = CRCLSocket.DEFAULT_PORT;

    /**
     * Get the value of port
     *
     * @return the value of port
     */
    public int getPort() {
        return port;
    }

    /**
     * Set the value of port
     *
     * @param port new value of port
     */
    public void setPort(int port) throws IOException {
        if (isRunning()) {
            throw new IllegalStateException("Can not set field when server is running.");
        }
        this.port = port;
        if (serverSocket != null) {
            serverSocket.close();
            serverSocket = null;
        }
        if (serverSocketChannel != null) {
            serverSocketChannel.close();
            serverSocketChannel = null;
        }
    }

    /*@Nullable*/
    private ExecutorService callbackService = null;

    /**
     * Get the value of callbackService
     *
     * @return the value of callbackService
     */
    /*@Nullable*/
    public ExecutorService getCallbackService() {
        return callbackService;
    }

    /**
     * Set the value of callbackService
     *
     * @param callbackService new value of callbackService
     */
    public void setCallbackService(ExecutorService callbackService) {
        if (isRunning()) {
            throw new IllegalStateException("Can not set field when server is running.");
        }
        this.callbackService = callbackService;
    }

    /*@Nullable*/
    private ExecutorService executorService;

    /**
     * Get the value of executorService
     *
     * @return the value of executorService
     */
    /*@Nullable*/
    public ExecutorService getExecutorService() {
        return executorService;
    }

    /**
     * Set the value of executorService
     *
     * @param executorService new value of executorService
     */
    public void setExecutorService(ExecutorService executorService) {
        if (isRunning()) {
            throw new IllegalStateException("Can not set field when server is running.");
        }
        this.executorService = executorService;
    }

    /*@Nullable*/ private volatile ServerSocketChannel serverSocketChannel;

    private boolean closing = false;

    @Override
    public void close() throws Exception {
        started = false;
        closing = true;
        if (queueEvents) {
            queue.offer(new CRCLServerSocketEvent(null, null, null), 1, TimeUnit.SECONDS);
        }
        if (null != serverSocketChannel) {
            try {
                serverSocketChannel.close();
            } catch (IOException iOException) {
            }
            serverSocketChannel = null;
        }
        if (null != serverSocket) {
            try {
                serverSocket.close();
            } catch (IOException iOException) {
            }
            serverSocket = null;
        }
        if (null != selector) {
            try {
                selector.close();
            } catch (IOException iOException) {
            }
            selector = null;
        }
        for (int i = 0; i < clients.size(); i++) {
            try {
                CRCLServerClientInfo client = clients.get(i);
                client.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        clients.clear();
        if (null != executorService) {
            executorService.shutdownNow();
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        }
        if (null != callbackService) {
            callbackService.shutdownNow();
            callbackService.awaitTermination(1, TimeUnit.SECONDS);
        }
        listeners.clear();
        queue.clear();
    }

    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    private boolean validate;

    /**
     * Get the value of validate
     *
     * @return the value of validate
     */
    public boolean isValidate() {
        return validate;
    }

    /**
     * Set the value of validate
     *
     * @param validate new value of validate
     */
    public void setValidate(boolean validate) {
        if (isRunning()) {
            throw new IllegalStateException("Can not set field when server is running.");
        }
        this.validate = validate;
    }

    /*@Nullable*/ private ServerSocket serverSocket;

    final List<CRCLServerSocketEventListener> listeners = new ArrayList<>();

    public synchronized void addListener(CRCLServerSocketEventListener l) {
        listeners.add(l);
    }

    public synchronized void removeListener(CRCLServerSocketEventListener l) {
        listeners.remove(l);
    }

    private boolean queueEvents = false;

    /**
     * Get the value of queueEvents
     *
     * @return the value of queueEvents
     */
    public boolean isQueueEvents() {
        return queueEvents;
    }

    /**
     * Set the value of queueEvents
     *
     * @param queueEvents new value of queueEvents
     */
    public void setQueueEvents(boolean queueEvents) {
        if (isRunning()) {
            throw new IllegalStateException("Can not set field when server is running.");
        }
        this.queueEvents = queueEvents;
    }

    private BlockingQueue<CRCLServerSocketEvent> queue = new LinkedBlockingQueue<>();

    private void handleEvent(final CRCLServerSocketEvent event) throws InterruptedException {
        if (closing) {
            return;
        }
        for (int i = 0; i < listeners.size(); i++) {
            if (closing) {
                return;
            }
            final CRCLServerSocketEventListener l = listeners.get(i);
            if (null == callbackService) {
                l.accept(event);
            } else {
                callbackService.submit(new Runnable() {
                    @Override
                    public void run() {
                        if (closing) {
                            return;
                        }
                        l.accept(event);
                    }
                });
            }
        }

        if (queueEvents && !closing) {
            queue.put(event);
        }
        if (event.getException() instanceof SocketException
                || event.getException() instanceof EOFException) {
            try {
                CRCLSocket source = event.getSource();
                if (null != source) {
                    source.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(CRCLServerSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < clients.size(); i++) {
                CRCLServerClientInfo c = clients.get(i);
                if (Objects.equals(c.getSocket(), event.getSource())) {
                    clients.remove(c);
                }
            }
            throw new InterruptedException("Closing socket due to " + event.getException());
        }
    }

    public CRCLServerSocketEvent waitForEvent() throws InterruptedException {
        if (!started && !isRunning()) {
            throw new IllegalStateException("CRCLServerSocket must be running/started before call to waitForEvent.");
        }
        if (!queueEvents) {
            throw new IllegalStateException("queueEvents should be set before call to waitForEvent.");

        }
        return queue.take();
    }

    public List<CRCLServerSocketEvent> checkForEvents() {
        List<CRCLServerSocketEvent> l = new ArrayList<>();
        queue.drainTo(l);
        return l;
    }

    /*@Nullable*/
    private Selector selector;

    /**
     * Get the value of selector
     *
     * @return the value of selector
     */
    /*@Nullable*/
    public Selector getSelector() {
        return selector;
    }

    /**
     * Set the value of selector
     *
     * @param selector new value of selector
     */
    public void setSelector(Selector selector) {
        if (isRunning()) {
            throw new IllegalStateException("Can not set field when server is running.");
        }
        this.selector = selector;
    }

    /*@Nullable*/ 
    private SocketAddress localAddress;
    
    /*@Nullable*/
    private InetAddress bindAddress;

    /**
     * Get the value of bindAddress
     *
     * @return the value of bindAddress
     */
    /*@Nullable*/
    public InetAddress getBindAddress() {
        return bindAddress;
    }

    /**
     * Set the value of bindAddress
     *
     * @param bindAddress new value of bindAddress
     */
    public void setBindAddress(InetAddress bindAddress) {
        if (isRunning()) {
            throw new IllegalStateException("Can not set field when server is running.");
        }
        this.bindAddress = bindAddress;
    }

    private static int runcount = 0;

    private boolean multithreaded = Boolean.getBoolean("CRCLServerSocket.multithreaded");

    /**
     * Get the value of multithreaded
     *
     * @return the value of multithreaded
     */
    public boolean isMultithreaded() {
        return multithreaded;
    }

    /**
     * Set the value of multithreaded
     *
     * @param multithreaded new value of multithreaded
     */
    public void setMultithreaded(boolean multithreaded) throws IOException, InterruptedException {
        if (isRunning()) {
            throw new IllegalStateException("Can not set field when server is running.");
        }
        this.multithreaded = multithreaded;
        if (multithreaded) {
            if (null != serverSocketChannel) {
                serverSocketChannel.close();
                serverSocketChannel = null;
            }
            if (null != selector) {
                selector.close();
                selector = null;
            }
        } else {
            if (null != serverSocket) {
                serverSocket.close();
                serverSocket = null;
            }
            if (null != executorService) {
                executorService.shutdownNow();
                executorService.awaitTermination(1, TimeUnit.SECONDS);
                executorService = null;
            }
        }
    }

    private volatile boolean running = false;

    public boolean isRunning() {
        return running;
    }

    @Override
    public void run() {

        if (isRunning()) {
            throw new IllegalStateException("Can not start again when server is already running.");
        }
        running = true;
        try {
            if (this.closing) {
                return;
            }
            runcount++;
            for (CRCLServerClientInfo crclSocket : clients) {
                crclSocket.close();
            }
            clients.clear();
            if (null == localAddress) {
                localAddress = new InetSocketAddress(port);
            }
            if (multithreaded) {
                runMultiThreaded();
            } else {
                runSingleThreaded();
            }
        } catch (Throwable ex) {
            running = false;
            if (!closing) {
                Logger.getLogger(CRCLServerSocket.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        } finally {
            running = false;
        }
    }

    private void runSingleThreaded() throws InterruptedException, IOException, ClosedChannelException {
        ServerSocketChannel channelForRun = this.serverSocketChannel;
        if (null == channelForRun) {
            SocketAddress localAddressToBind = this.localAddress;
            if(null == localAddressToBind) {
                throw new IllegalStateException("null == localAddressToBind");
            }
            try {
                channelForRun = (ServerSocketChannel) ServerSocketChannel.open()
                        .bind(localAddressToBind)
                        .setOption(StandardSocketOptions.SO_REUSEADDR, true)
                        .configureBlocking(false);
                this.serverSocketChannel = channelForRun;
            } catch (BindException bindException) {
                if(localAddressToBind instanceof InetSocketAddress) {
                    InetSocketAddress loclaInetSocketAddress =  (InetSocketAddress) localAddressToBind;
                    System.err.println("localAdrress = " + loclaInetSocketAddress.getHostString() + ":" + loclaInetSocketAddress.getPort());
                }
                throw new IOException(bindException);
            }
        }
        Selector selectorForRun = this.selector;
        if (null == selectorForRun) {
            selectorForRun = Selector.open();
            this.selector = selectorForRun;
        }
        if (null == channelForRun) {
            throw new IllegalStateException("serverSocketChannel==null");
        }

        if (null == selectorForRun) {
            throw new IllegalStateException("selectorForRun==null");
        }
        channelForRun.register(selectorForRun, SelectionKey.OP_ACCEPT);
        while (!closing && !Thread.currentThread().isInterrupted()) {
            selectorForRun.select();
            SelectionKey keys[] = getAndClearKeys(selectorForRun);
            for (int i = 0; i < keys.length; i++) {
                SelectionKey key = keys[i];

                if (key.channel() == channelForRun) {
                    SocketChannel clientSocketChannel
                            = channelForRun.accept();
                    if (null == clientSocketChannel) {
                        System.out.println("key = " + key);
                    } else {
                        clientSocketChannel
                                = (SocketChannel) clientSocketChannel.configureBlocking(false);
                        CRCLSocket crclSocket = new CRCLSocket(clientSocketChannel);
                        clients.add(new CRCLServerClientInfo(crclSocket, null));
                        SelectionKey newKey
                                = clientSocketChannel.register(selectorForRun, SelectionKey.OP_READ, crclSocket);
                    }
                } else {
                    CRCLSocket crclSocket = (CRCLSocket) key.attachment();
                    List<CRCLCommandInstanceType> cmdInstances;
                    try {
                        SocketChannel s = ((SocketChannel) key.channel());
                        try {
                            ByteBuffer bb = ByteBuffer.allocate(4096);
                            int readbytes = s.read(bb);
                            if (readbytes > 0) {
                                String string = new String(bb.array(), 0, readbytes);
                                cmdInstances = crclSocket.parseMultiCommandString(string, validate);
                                for (CRCLCommandInstanceType instance : cmdInstances) {
                                    handleEvent(new CRCLServerSocketEvent(crclSocket, instance, null));
                                }
                            }
                        } catch (IOException iOException) {
                            try {
                                s.close();
                                selectorForRun.selectedKeys().remove(key);
                            } catch (Exception exception) {
                            }
                            handleEvent(new CRCLServerSocketEvent(crclSocket, null, iOException));
                            try {
                                crclSocket.close();
                            } catch (Exception exception) {
                            }
                        }
                    } catch (CRCLException ex) {
                        handleEvent(new CRCLServerSocketEvent(crclSocket, null, ex));
                    }
                }
            }
        }
    }

    @SuppressWarnings("nullness")
    private SelectionKey[] getAndClearKeys(Selector selectorForRun) {
        SelectionKey keys[] = new SelectionKey[0];
        synchronized (selectorForRun) {
            Set<SelectionKey> keySet = selectorForRun.selectedKeys();
            synchronized (keySet) {
                keys = keySet.toArray(keys);
                keySet.removeAll(Arrays.asList(keys));
            }
        }
        return keys;
    }

    private int backlog = 0;

    /**
     * Get the value of backlog
     *
     * @return the value of backlog
     */
    public int getBacklog() {
        return backlog;
    }

    /**
     * Set the value of backlog
     *
     * @param backlog new value of backlog
     */
    public void setBacklog(int backlog) throws IOException {
        if (isRunning()) {
            throw new IllegalStateException("Can not start again when server is already running.");
        }
        this.backlog = backlog;
        if (serverSocket != null) {
            serverSocket.close();
            serverSocket = null;
        }
        if (serverSocketChannel != null) {
            serverSocketChannel.close();
            serverSocketChannel = null;
        }
    }

    private Socket acceptFromServerSocket() throws SocketException, IOException {
        ServerSocket acceptServerSocket = this.serverSocket;
        if (null == acceptServerSocket) {
            throw new IllegalStateException("serverSocket == null");
        }
        // This is an anoying hack around the problem that you can't rely on
        // serverSocket.accept() being interrupted.
        // This only generally needs to happen when shuting down the server
        int orig_timeout = acceptServerSocket.getSoTimeout();
        acceptServerSocket.setSoTimeout(500);
        Socket ret = null;
        boolean interrupted = false;
        try {
            interrupted = interrupted || Thread.currentThread().isInterrupted();
            while (ret == null && !closing && !interrupted) {
                try {
                    ret = acceptServerSocket.accept();
                } catch (SocketTimeoutException ignored) {
                }
            }
        } finally {
            acceptServerSocket.setSoTimeout(orig_timeout);
        }
        if (null == ret) {
            throw new IllegalStateException("acceptFromServerSocket returned null,closing=" + closing + ",interrupted=" + interrupted);
        }
        return ret;
    }

    private void runMultiThreaded() throws InterruptedException, IOException {

        if (null != serverSocketChannel) {
            serverSocketChannel.close();
            serverSocketChannel = null;
        }
        if (null != selector) {
            selector.close();
            selector = null;
        }
        
        ExecutorService runMultiExecutorService = initExecutorService();
        if (null == runMultiExecutorService) {
            throw new IllegalStateException("null == runMultiExcututorService");
        }
        if (null == serverSocket) {
            if (null != bindAddress) {
                serverSocket = new ServerSocket(port, backlog, bindAddress);
            } else {
                serverSocket = new ServerSocket(port);
            }
        }
        serverSocket.setReuseAddress(true);
        while (!closing && !Thread.currentThread().isInterrupted()) {

            Socket socket = acceptFromServerSocket();
            if (null == socket) {
                return;
            }
            final CRCLSocket crclSocket = new CRCLSocket(socket);
            Future<?> future = runMultiExecutorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (!closing
                                && crclSocket.isConnected()
                                && !Thread.currentThread().isInterrupted()) {
                            try {
                                CRCLCommandInstanceType instance = crclSocket.readCommand(validate);
                                handleEvent(new CRCLServerSocketEvent(crclSocket, instance, null));
                            } catch (CRCLException | IOException ex) {
                                handleEvent(new CRCLServerSocketEvent(crclSocket, null, ex));
                            }
                        }
                    } catch (InterruptedException ex1) {
                        Logger.getLogger(CRCLServerSocket.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            });
            clients.add(new CRCLServerClientInfo(crclSocket, future));
        }
    }

    private ExecutorService initExecutorService() {
        ExecutorService es = this.executorService;
        if (null != es) {
            return es;
        }
        ExecutorService newExecutorService = Executors.newCachedThreadPool(new ThreadFactory() {

            int num = 0;

            @Override
            public Thread newThread(Runnable r) {
                num++;
                Thread t = new Thread(r, "CRCLServerSocket" + runcount + "_" + num);
//                        t.setDaemon(true);
                return t;
            }
        });
        this.executorService = newExecutorService;
        return newExecutorService;
    }

    public CRCLServerSocket() {
        this.port = CRCLSocket.DEFAULT_PORT;
    }

    public CRCLServerSocket(int port) {
        this.port = port;
        this.localAddress = new InetSocketAddress(port);
    }

    public CRCLServerSocket(int port, int backlog) {
        this.port = port;
        this.backlog = backlog;
        this.localAddress = new InetSocketAddress(port);
    }

    public CRCLServerSocket(int port, int backlog, InetAddress addr) throws IOException {
        this.port = port;
        this.backlog = backlog;
        this.bindAddress = addr;
        this.localAddress = new InetSocketAddress(bindAddress, port);
    }

    {
        if (multithreaded) {
            callbackService = Executors.newSingleThreadExecutor();
        }
    }

    private boolean started = false;

    public Future<?> start() {
        if (isRunning()) {
            throw new IllegalStateException("Can not start again when server is already running.");
        }
        ExecutorService serviceFoStart = initExecutorService();
        started = true;
        return serviceFoStart.submit(this);
    }
}
