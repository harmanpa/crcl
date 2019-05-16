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
package crcl.ui;

import static crcl.ui.XFutureVoid.allOf;
import static crcl.ui.XFutureVoid.allOfWithName;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class XFuture<T> extends CompletableFuture<T> {

    @MonotonicNonNull
    private volatile Future<T> futureFromExecSubmit = null;
    @MonotonicNonNull
    private volatile Thread threadToInterrupt = null;
    @MonotonicNonNull
    private volatile Runnable onCancelAllRunnable = null;

    private final String name;
    private final long startTime;
    private volatile long maxDepCompleteTime;
    private final int num;
    private volatile long completeTime = -1;
    private final Thread createThread;
    private final StackTraceElement createTrace[];

    static private final AtomicInteger numCounter = new AtomicInteger();
    private final AtomicInteger xFutureAlsoCancelCount = new AtomicInteger();
    private final AtomicInteger cfFutureAlsoCancelCount = new AtomicInteger();

    @Nullable
    protected Future<T> getFutureFromExecSubmit() {
        return futureFromExecSubmit;
    }

    protected void setFutureFromExecSubmit(Future<T> f) {
        this.futureFromExecSubmit = f;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getCompleteTime() {
        return completeTime;
    }

    public XFuture(String name) {
        this.name = defaultName(name);
        this.startTime = System.currentTimeMillis();
        this.num = numCounter.incrementAndGet();
        this.createThread = Thread.currentThread();
        this.createTrace = this.createThread.getStackTrace();
    }

    public Thread getCreateThread() {
        return createThread;
    }

    public StackTraceElement[] getCreateTrace() {
        return createTrace;
    }

    @SuppressWarnings("rawtypes")
    public static XFutureVoid allOfWithName(String name, Collection<? extends CompletableFuture<?>> cfsCollection) {
        return allOfWithName(name, cfsCollection.toArray(new CompletableFuture[0]));
    }

    @SuppressWarnings("rawtypes")
    public static XFutureVoid allOf(Collection<? extends CompletableFuture<?>> cfsCollection) {
        return allOf(cfsCollection.toArray(new CompletableFuture[0]));
    }

    private static String shortTraceToString(StackTraceElement trace @Nullable []) {
        if (null != trace) {
            for (int i = 0; i < trace.length; i++) {
                StackTraceElement stackTraceElement = trace[i];
                if (stackTraceElement.getClassName().contains("Future")) {
                    continue;
                }
                if (stackTraceElement.getClassName().startsWith("java.")) {
                    continue;
                }
                return stackTraceElement.toString();
            }
        }
        return "";
    }

    public static String traceToString(StackTraceElement trace @Nullable []) {
        if (null == trace) {
            return "";
        }
        try (StringWriter stringWriter = new StringWriter()) {
            try (PrintWriter printWriter = new PrintWriter(stringWriter)) {
                boolean first = true;
                int firstExternal = 0;
                int lastExternal = trace.length - 1;
                for (int i = 0; i < trace.length - 1; i++) {
                    StackTraceElement stackTraceElement = trace[i];
                    final String className = stackTraceElement.getClassName();
                    if (!className.startsWith("java.") && !className.contains("Future")) {
                        firstExternal = i;
                        break;
                    }
                }
                for (int i = trace.length - 1; i > firstExternal; i--) {
                    StackTraceElement stackTraceElement = trace[i];
                    final String className = stackTraceElement.getClassName();
                    if (!className.startsWith("java.") && !className.contains("Future")) {
                        lastExternal = i;
                        break;
                    }
                }
                if (firstExternal > 0) {
                    printWriter.println("\t \t// skipping (firstExternal=" + firstExternal + ") " + Arrays.toString(Arrays.copyOfRange(trace, 0, firstExternal)));
                }
                for (int i = firstExternal; i <= lastExternal; i++) {
                    StackTraceElement stackTraceElement = trace[i];
                    printWriter.println("\tat " + stackTraceElement);
                }
                if (lastExternal > 0 && lastExternal < trace.length - 1) {
                    printWriter.println("\t \t// skipping (" + (lastExternal + 1) + "," + trace.length + ") " + Arrays.toString(Arrays.copyOfRange(trace, lastExternal + 1, trace.length)));
                }
            }
            stringWriter.flush();
            return stringWriter.toString();
        } catch (IOException ex) {
            String msg = "trace=" + trace;
            logStaticException(msg, ex);
            throw new RuntimeException(ex);
        }
    }

    private static void logStaticException(String msg, Throwable ex) {
        if (!closingMode) {
            Logger.getLogger(XFuture.class.getName()).log(Level.SEVERE, msg, ex);
        }
    }

    public void printProfile() {
        printProfile(System.out);
    }

    public void printProfile(PrintStream ps) {
        ps.println("num,start_time,end_time,time_diff,runTime,name,exception,cancel,done,xdeps,nonxdeps,trace");
        internalPrintProfile(ps);
    }

    public void printStatus() {
        printStatus(System.out);
    }

    public void printStatus(PrintStream ps) {
        ps.println();
        ps.println("Status for " + XFuture.this.toString());
        internalPrintStatus(ps);

        if (isCompletedExceptionally()) {
            this.exceptionally(t -> {
                if (!isPrintedOrCancellationException(t) || printCancellationExceptions) {
                    ps.println(XFuture.this.getName() + " Completed Exceptionally with: ");
                    t.printStackTrace(ps);
                    String forExceptionString = XFuture.this.forExceptionString();
                    ps.println("forExceptionString = " + forExceptionString);
                }
                return null;
            });
            return;
        }
        ps.println("\t END Status for " + XFuture.this.toString());
        ps.println();
    }

    @SuppressWarnings("nullness")
    private Object printEx(Throwable t, PrintStream ps) {
        if (!isPrintedOrCancellationException(t) || printCancellationExceptions) {
            ps.println(XFuture.this.getName() + " Completed Exceptionally with: ");
            t.printStackTrace(ps);
            String forExceptionString = XFuture.this.forExceptionString();
            ps.println("forExceptionString = " + forExceptionString);
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public ConcurrentLinkedDeque<CompletableFuture<?>> getAlsoCancel() {
        return alsoCancel;
    }

    private volatile StackTraceElement @Nullable [] getProfileStringTrace = null;
    @Nullable
    private volatile String getProfileStringThreadName = null;

    public String getProfileString() {
        setCompleteTime();
        if (null != getProfileStringTrace || null != getProfileStringThreadName && !closingMode) {
            System.out.println("getProfileStringThreadName = " + getProfileStringThreadName);
            System.out.println("getProfileStringTrace = " + Arrays.toString(getProfileStringTrace));
        }
        getProfileStringTrace = Thread.currentThread().getStackTrace();
        getProfileStringThreadName = Thread.currentThread().getName();
        return joinAny(",", num, startTime, completeTime, (completeTime - startTime), (completeTime - maxDepCompleteTime), "\"" + name + "\"", isCompletedExceptionally(), isCancelled(), isDone(), xFutureAlsoCancelCount.get(), cfFutureAlsoCancelCount.get(), "\"" + shortTraceToString(createTrace) + "\"");
    }

    @Nullable
    private volatile List<String> prevProfileStrings = null;

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void getAllProfileString(Iterable<CompletableFuture<?>> localAlsoCancels, List<String> listIn) {

        List<String> localPrevProfileStrings = this.prevProfileStrings;
        List<CompletableFuture<?>> localAlsoCancelsCopy = new ArrayList<>();
        if (null != localPrevProfileStrings) {
            listIn.addAll(localPrevProfileStrings);
        } else {
            for (CompletableFuture f : localAlsoCancels) {
                localAlsoCancelsCopy.add(f);
                if (f instanceof XFuture) {
                    XFuture xf = (XFuture) f;
                    if (xf.isDone() || xf.isCancelled() || xf.isCompletedExceptionally()) {
                        StackTraceElement xfGetProfileTrace[] = xf.getProfileStringTrace;
                        List<String> xfPrevProfileString = xf.prevProfileStrings;
                        List<CompletableFuture> localAlsoCancel = new ArrayList<>(xf.alsoCancel);
                        xf.clearAlsoCancel();
                        xf.getAllProfileString(localAlsoCancel, listIn);
                    } else {
                        if (!closingMode) {
                            System.err.println("also cancel not done");
                        }
                        xf.getAllProfileString(this.alsoCancel, listIn);
                    }
                }
            }
            listIn.add(getProfileString());
        }
        StackTraceElement[] localGetProfileStringTrace = this.getProfileStringTrace;
        if (localGetProfileStringTrace == null && !closingMode) {
            System.err.println("getAllProfileString called without setting getProfileStringTrace");
        }
    }

    @SuppressWarnings("unchecked")
    private void internalPrintProfile(PrintStream ps) {
        //ps.println("start_time,end_time,time_diff,name,exception,cancel,done");
        List<String> localPrevPStrings = this.prevProfileStrings;
        if (null != localPrevPStrings) {
            for (String profileString : localPrevPStrings) {
                ps.println(profileString);
            }
        } else {
            List<String> l = new ArrayList<>();
            List<CompletableFuture<?>> localAlsoCancel = new ArrayList<>(this.alsoCancel);
            if (!localAlsoCancel.isEmpty()) {
                clearAlsoCancel();
                StackTraceElement preGetProfileTrace[] = this.getProfileStringTrace;
                List<String> prePrevProfileString = this.prevProfileStrings;
                getAllProfileString(localAlsoCancel, l);
                this.prevProfileStrings = l;
                localPrevPStrings = l;
                for (String profileString : localPrevPStrings) {
                    ps.println(profileString);
                }
            }
        }
    }

    private static String joinAny(String delim, Object... objects) {
        if (null == objects || objects.length < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < objects.length - 1; i++) {
            sb.append(objects[i].toString());
            sb.append(delim);
        }
        sb.append(objects[objects.length - 1].toString());
        return sb.toString();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void internalPrintStatus(PrintStream ps) {

        if (isCompletedExceptionally()) {
            ps.println(XFuture.this.toString() + " is CompletedExceptionally.");
            this.exceptionally(t -> {
                if (!isPrintedOrCancellationException(t) || printCancellationExceptions) {
                    ps.println(XFuture.this.forExceptionString() + " Completed Exceptionally with: ");
                    t.printStackTrace(ps);
                    String forExceptionString = XFuture.this.forExceptionString();
                    ps.println("forExceptionString = " + forExceptionString);
                }
                return null;
            });
            return;
        }
        if (isCancelled()) {
            ps.println(XFuture.this.toString() + " is cancelled.");
            return;
        } else if (isDone()) {
            ps.println(XFuture.this.toString() + " is done.");
            return;
        }
        for (CompletableFuture<?> f : alsoCancel) {
            if (f instanceof XFuture) {
                XFuture xf = (XFuture) f;
                xf.internalPrintStatus(ps);
            }
        }
        Function<Throwable, Object> exPrinter = (Throwable t) -> {
            return printEx(t, ps);
        };
        boolean allAlsoCancelDone = true;
        for (CompletableFuture<?> f : alsoCancel) {
            if (!(f instanceof XFuture)) {
                if (!f.isDone()) {
                    allAlsoCancelDone = false;
                }
                ps.println("done=" + isDone() + "\tcancelled=" + isCancelled() + "\t" + f.toString());
                if (f.isCompletedExceptionally()) {
                    CompletableFuture<Object> fob = (CompletableFuture<Object>) f;
                    fob.exceptionally(exPrinter);
                }
            }
        }
        ps.println("done=" + isDone() + "\tcancelled=" + isCancelled() + "\t" + XFuture.this.toString());
        if (!isDone() && allAlsoCancelDone) {
            ps.println("!isDone() && allAlsoCancelDone");
            ps.println("origMaxRecurse=" + origMaxRecurse);
            ps.println(name + ".createTrace=");
            ps.println(traceToString(createTrace));
        }
    }

    @Override
    public String toString() {
        return super.toString() + "{" + name + "(" + getRunTime() + "ms ago) }";
    }

    @SuppressWarnings("nullness")
    public String forExceptionString() {
        StackTraceElement[] trace = this.createTrace;
        int startTraceInfoIndex = 0;
        for (int i = 0; i < trace.length; i++) {
            StackTraceElement stackTraceElement = trace[i];
            String traceString = stackTraceElement.toString();
            if (i == 0 && traceString.contains("Thread.getStackTrace")) {
                startTraceInfoIndex++;
            }
            if (traceString.contains("XFuture.<init>") || traceString.contains("XFutureVoid.<init>") || traceString.contains(".staticwrap")) {
                startTraceInfoIndex++;
                continue;
            }
            break;
        }
        StackTraceElement[] shortenedCreateTrace = Arrays.copyOfRange(trace, startTraceInfoIndex, trace.length);
        return "\nsuper.toString()=" + super.toString() + "\n"
                + "name=" + name + "\n"
                + " runTime=" + getRunTime() + "ms ago\n"
                + alsoCancelStringNames()
                + longCancelString()
                + manuallyCompletedString()
                + manuallyCompletedExceptionallyString()
                + " createThread=" + createThread + ",\n"
                + " createTrace= \n" + traceToString(shortenedCreateTrace) + "\n";
    }

    private String alsoCancelStringNames() {
        StringBuilder sb = new StringBuilder();
        for (CompletableFuture<?> cf : alsoCancel) {
            sb.append(cf).append("\n");
            if (cf instanceof XFuture) {
                XFuture<?> xf = (XFuture) cf;
                if (xf.isCancelled() || xf.isCompletedExceptionally()) {
                    sb.append(xf.name).append(" : forExceptionString=").append(xf.forExceptionString()).append("\n");
                    sb.append(xf.name).append(" :  end forExceptionString").append("\n");
                }
            }
        }
        return sb.toString();
    }

    private String manuallyCompletedString() {
        if (null != manuallyCompletedValue
                && null != manuallyCompletedThread
                && null != manuallyCompletedTrace) {
            return "completedThrowable=" + manuallyCompletedValue + "\n"
                    + "completedThread=" + manuallyCompletedThread + "\n"
                    + "completedTrace= \n" + traceToString(manuallyCompletedTrace) + "\n";
        }
        return "";
    }

    private String manuallyCompletedExceptionallyString() {
        if (null != completedExceptionallyThrowable
                && null != completedExceptionallyThread
                && null != completedExceptionallyTrace) {
            return "completedExceptionallyThrowable=" + completedExceptionallyThrowable + "\n"
                    + "completedExceptionallyThread=" + completedExceptionallyThread + "\n"
                    + "completedExceptionallyTrace= \n" + traceToString(completedExceptionallyTrace) + "\n";
        }
        return "";
    }

    @Nullable
    public Runnable getOnCancelAllRunnable() {
        return onCancelAllRunnable;
    }

    public void setOnCancelAllRunnable(Runnable onCancelAllRunnable) {
        this.onCancelAllRunnable = onCancelAllRunnable;
    }

    @Nullable
    public Thread getThreadToInterrupt() {
        return threadToInterrupt;
    }

    public void setThreadToInterrupt(Thread threadToInterrupt) {
        this.threadToInterrupt = threadToInterrupt;
    }

    private static class Hider {

        private static final ThreadFactory THREAD_FACTORY = new ThreadFactory() {

            private final AtomicInteger count = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                Thread newThraed = new Thread(r, "XFutureThread_" + count.incrementAndGet());
                newThraed.setDaemon(true);
                return newThraed;
            }
        };
        public static final ExecutorService DEFAULT_EXECUTOR_SERVICE = Executors.newCachedThreadPool(THREAD_FACTORY);
    }

    public static ExecutorService getDefaultThreadPool() {
        return Hider.DEFAULT_EXECUTOR_SERVICE;
    }

    private void alsoCancelAddAll(Iterable<? extends CompletableFuture<?>> cfs) {
        for (CompletableFuture<?> cf : cfs) {
            alsoCancelAdd(cf);
        }
    }

    public static XFutureVoid allOfWithName(String name, CompletableFuture<?>... cfs) {
        return XFutureVoid.allOfWithName(name, cfs);
    }

    public static XFuture<Object> anyOfWithName(String name, CompletableFuture<?>... cfs) {
        CompletableFuture<Object> orig = CompletableFuture.anyOf(cfs);
        XFuture<Object> ret = staticwrap(name, orig);
        ret.alsoCancelAddAll(Arrays.asList(cfs));
        return ret;
    }

    public static XFutureVoid allOf(CompletableFuture<?>... cfs) {
        return XFutureVoid.allOf(cfs);
    }

    public static XFuture<Object> anyOf(CompletableFuture<?>... cfs) {
        CompletableFuture<Object> orig = CompletableFuture.anyOf(cfs);
        XFuture<Object> ret = staticwrap("anyOfWithName", orig);
        ret.alsoCancelAddAll(Arrays.asList(cfs));
        return ret;
    }

    public static void main(String[] args) throws InterruptedException {
        XFuture.supplyAsync(() -> 2)
                .thenApplyAsync(x -> {
                    System.out.println("here");
                    throw new RuntimeException();
                })
                .thenAccept(x -> {
                    System.out.println("thenAccept : x = " + x);
                })
                .exceptionally((Throwable u) -> {
                    System.out.println("exceptionally u = " + u);
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                })
                .handle((Object t, Throwable u) -> {
                    System.out.println("handle1 t = " + t);
                    System.out.println("handle1 u = " + u);
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                })
                .handle((Object t, Throwable u) -> {
                    System.out.println("handle2 t = " + t);
                    System.out.println("handle2 u = " + u);
                    return 3;

//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                })
                .thenAccept(x -> {
                    System.out.println("thenAccept2 : x = " + x);
                });

//                        (int x,Throwable t) -> {
//                    System.out.println("t = " + t);
//                    return x;
//                });
        Thread.sleep(2000);
    }

    private final ConcurrentLinkedDeque<CompletableFuture<?>> alsoCancel = new ConcurrentLinkedDeque<>();

//    public static final  Function<Throwable, ?> RETHROWER = XFuture::rethrow;
    public static <T> T rethrow(Throwable t) {
        if (t == null) {
            return (T) null;
        } else if (t instanceof RuntimeException) {
            throw (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new RuntimeException(t);
        }
    }

    public static <T> XFuture<T> supplyAsync(String name, Callable<T> callable, Function<Throwable, T> handler, ExecutorService es) {
        final XFuture<T> myf = new XFuture<>(name);
        if (null == es) {
            throw new IllegalArgumentException("ExecutorService es==null");
        }
        if (es.isShutdown()) {
            throw new IllegalArgumentException("ExecutorService es.isShutdown()");
        }
        Future<T> f = es.submit(() -> {
            String tname = Thread.currentThread().getName();
            try {
                if (!javax.swing.SwingUtilities.isEventDispatchThread()) {
                    int cindex = tname.indexOf(':');
                    String tname_sub = tname;
                    if (cindex > 0) {
                        tname_sub = tname.substring(0, cindex);
                    }
                    Thread.currentThread().setName(tname_sub + ":" + name);
                }
                T result = callable.call();
                myf.complete(result);
//                myf.alsoCancel.clear();
                if (!javax.swing.SwingUtilities.isEventDispatchThread()) {
                    Thread.currentThread().setName(tname);
                }
                return result;
            } catch (Throwable throwable) {
                String msg = "Exception in XFuture " + myf.forExceptionString();
                checkAndLogExceptioni(throwable, msg);
                try {
                    T result = handler.apply(throwable);
                    myf.complete(result);
//                myf.alsoCancel.clear();
                    if (!javax.swing.SwingUtilities.isEventDispatchThread()) {
                        Thread.currentThread().setName(tname);
                    }
                    return result;
                } catch (Throwable throwable2) {
                    logStaticException("Exception in handler " + throwable2.getMessage(), throwable2);
                }
                myf.completeExceptionally(throwable);
                throw new RuntimeException(throwable);
            }
        });
        myf.futureFromExecSubmit = f;
        return myf;
    }

    private static void checkAndLogExceptioni(Throwable throwable1, String msg) {
        if (!closingMode) {
            if (!isPrintedOrCancellationException(throwable1) || printCancellationExceptions) {
                Logger.getLogger(XFuture.class.getName()).log(Level.SEVERE, msg, throwable1);
            }
        }
    }

    public static <T> XFuture<T> supplyAsync(String name, Supplier<T> supplier, ExecutorService es) {
        final XFuture<T> myf = new XFuture<>(name);
        if (null == es) {
            throw new IllegalArgumentException("ExecutorService es==null");
        }
        if (es.isShutdown()) {
            throw new IllegalArgumentException("ExecutorService es.isShutdown()");
        }
        Future<T> f = es.submit(() -> {
            try {
                String tname = Thread.currentThread().getName();
                if (!javax.swing.SwingUtilities.isEventDispatchThread()) {
                    int cindex = tname.indexOf(':');
                    String tname_sub = tname;
                    if (cindex > 0) {
                        tname_sub = tname.substring(0, cindex);
                    }
                    Thread.currentThread().setName(tname_sub + ":" + name);
                }
                T result = supplier.get();
                myf.complete(result);
//                myf.alsoCancel.clear();
                if (!javax.swing.SwingUtilities.isEventDispatchThread()) {
                    Thread.currentThread().setName(tname);
                }
                return result;
            } catch (Throwable throwable) {
                myf.completeExceptionally(throwable);
                checkAndLogExceptioni(throwable, "Exception in XFuture " + myf.forExceptionString());
                throw new RuntimeException(throwable);
            }
        });
        myf.futureFromExecSubmit = f;
        return myf;
    }

    public static <T> XFuture<T> completedFutureWithName(String name, T object) {
        XFuture<T> ret = new XFuture<>(name);
        ret.complete(object);
        return ret;
    }

    public static <T> XFuture<T> completedFuture(T object) {
        XFuture<T> ret = new XFuture<>("completedFuture(" + object + ")");
        ret.complete(object);
        return ret;
    }

    public static XFutureVoid runAsync(String name, Runnable r) {
        return XFutureVoid.runAsync(name, r);
    }

    public static XFutureVoid runAsync(Runnable r) {
        return XFutureVoid.runAsync("unnamedRunAsync", r);
    }

    public static XFutureVoid runAsync(String name, Runnable r, ExecutorService es) {
        return XFutureVoid.runAsync(name, r, es);
    }

    public static <T> XFuture<T> supplyAsync(String name, Supplier<T> c) {
        return supplyAsync(name, c, getDefaultThreadPool());
    }

    public static <T> XFuture<T> supplyAsync(Supplier<T> c) {
        return supplyAsync("unnamedSupplyAsync", c, getDefaultThreadPool());
    }

    public static <T> XFuture<T> supplyAsync(String name, Callable<T> callable, Function<Throwable, T> handler) {
        return supplyAsync(name, callable, handler, getDefaultThreadPool());
    }

    public static <T> XFuture<T> supplyAsync(Callable<T> callable, Function<Throwable, T> handler) {
        return supplyAsync("unnamedSupplyAsync", callable, handler, getDefaultThreadPool());
    }

    private static void setTName(String name) {
        if (!javax.swing.SwingUtilities.isEventDispatchThread()) {
            String tname = Thread.currentThread().getName();
            int cindex = tname.indexOf(':');
            String tname_sub = tname;
            if (cindex > 0) {
                tname_sub = tname.substring(0, cindex);
            }
            Thread.currentThread().setName(tname_sub + ":" + name);
        }
    }

    private <FR, FT> Function<FR, FT> fname(Function<FR, FT> fn, String name) {
        return x -> {
            try {
                setCompleteTime();
                setTName(name);
                return fn.apply(x);
            } catch (Throwable t) {
                checkAndLogExceptioni(t, "Exception in XFuture " + XFuture.this.forExceptionString());
                if (t instanceof RuntimeException) {
                    throw ((RuntimeException) t);
                } else {
                    throw new RuntimeException(t);
                }
            }
        };
    }

    @Override
    public <U> XFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn) {
        return this.thenCompose(defaultName(), fn);
    }

    public XFutureVoid thenComposeToVoid(Function<? super T, ? extends XFuture<Void>> fn) {
        return this.thenComposeAsyncToVoid(defaultName(), fn);
    }

    public XFutureVoid thenComposeAsyncToVoid(Function<? super T, ? extends XFuture<Void>> fn) {
        return this.thenComposeAsyncToVoid(defaultName(), fn);
    }

    public XFutureVoid thenComposeAsyncToVoid(Function<? super T, ? extends XFuture<Void>> fn, ExecutorService es) {
        return this.thenComposeAsyncToVoid(defaultName(), fn, es);
    }

    public XFutureVoid thenComposeToVoid(String name, Function<? super T, ? extends XFuture<Void>> fn) {
        XFuture<Void> future = this.thenCompose(name, fn);
        XFutureVoid ret = new XFutureVoid(name);
        future.thenRun(() -> ret.complete());
        ret.alsoCancelAdd(future);
        return ret;
    }

    public XFutureVoid thenComposeAsyncToVoid(String name, Function<? super T, ? extends XFuture<Void>> fn) {
        XFuture<Void> future = this.thenComposeAsync(name, fn);
        XFutureVoid ret = new XFutureVoid(name);
        future.thenRun(() -> ret.complete());
        ret.alsoCancelAdd(future);
        return ret;
    }

    public XFutureVoid thenComposeAsyncToVoid(String name, Function<? super T, ? extends XFuture<Void>> fn, ExecutorService es) {
        XFuture<Void> future = this.thenComposeAsync(name, fn, es);
        XFutureVoid ret = new XFutureVoid(name);
        future.thenRun(() -> ret.complete());
        ret.alsoCancelAdd(future);
        return ret;
    }

    private volatile boolean keepOldProfileStrings;

    public boolean getKeepOldProfileStrings() {
        return keepOldProfileStrings;
    }

    public void setKeepOldProfileStrings(boolean newKeepOldProfileStrings) {
        keepOldProfileStrings = newKeepOldProfileStrings;
        if (newKeepOldProfileStrings) {
            for (CompletableFuture<?> cf : this.alsoCancel) {
                if (cf instanceof XFuture) {
                    XFuture<?> xf = (XFuture) cf;
                    xf.setKeepOldProfileStrings(true);
                }
            }
        }
    }

    @Nullable
    private volatile T manuallyCompletedValue = null;
    @Nullable
    private volatile Thread manuallyCompletedThread = null;
    private volatile StackTraceElement manuallyCompletedTrace @Nullable []  = null;

    static volatile boolean closingMode = false;

    public static boolean isClosingMode() {
        return closingMode;
    }

    public static void setClosingMode(boolean closingMode) {
        XFuture.closingMode = closingMode;
    }

    private volatile static boolean debugCompleteWithExceptions = false;

    public static boolean isDebugCompleteWithExceptions() {
        return debugCompleteWithExceptions;
    }

    public static void setDebugCompleteWithExceptions(boolean debugCompleteWithExceptions) {
        XFuture.debugCompleteWithExceptions = debugCompleteWithExceptions;
    }

    @Override
    public boolean complete(T value) {

        if (isCancelled()) {
            if (closingMode || !debugCompleteWithExceptions) {
                return false;
            }
            String forExString = this.forExceptionString();
            if (!closingMode) {
                Logger.getLogger(XFuture.class.getName()).log(Level.SEVERE,
                        "Attempt to complete a future that was already cancelled : forExString= " + forExString);
                throw new IllegalStateException("Attempt to complete a future that was already completedExceptionally");
            }
        }
        if (isCompletedExceptionally()) {
            if (closingMode || !debugCompleteWithExceptions) {
                return false;
            }
            exceptionally(this::logAndRethrow);
            if (!closingMode) {
                String forExString = this.forExceptionString();
                Logger.getLogger(XFuture.class.getName()).log(Level.SEVERE,
                        "Attempt to complete a future that was already completedExceptionally : forExString= " + forExString);
            }
            throw new IllegalStateException("Attempt to complete a future that was already completedExceptionally");
        }
        if (isDone()) {
            return false;
        }
        manuallyCompletedValue = value;

        boolean ret = super.complete(value);
        saveProfileStrings();
        clearAlsoCancel();
        setCompleteTime();
        return ret;
    }

    private void saveProfileStrings() {
        if (keepOldProfileStrings) {
            List<String> l = new ArrayList<>();
            List<CompletableFuture<?>> localAlsoCancel = new ArrayList<>(this.alsoCancel);
            if (!localAlsoCancel.isEmpty()) {
                clearAlsoCancel();
                StackTraceElement preGetProfileTrace[] = this.getProfileStringTrace;
                List<String> prePrevProfileString = this.prevProfileStrings;
                getAllProfileString(localAlsoCancel, l);
                this.prevProfileStrings = l;
//                checkPrevAlsoCancelProfiled();
            }
        }
    }

    private void setCompleteTime() {
        long localMaxDepCompleteTime = this.maxDepCompleteTime;
        if (this.startTime > localMaxDepCompleteTime) {
            localMaxDepCompleteTime = this.startTime;
        }
        for (CompletableFuture<?> f : this.alsoCancel) {
            if (f instanceof XFuture) {
                XFuture<?> xf = (XFuture) f;
                long xfCompleteTime = xf.completeTime;
                if (xfCompleteTime > localMaxDepCompleteTime) {
                    localMaxDepCompleteTime = xfCompleteTime;
                }
            }
            if (!f.isDone() && !f.isCancelled() && !f.isCompletedExceptionally()) {
                localMaxDepCompleteTime = System.currentTimeMillis();
            }
        }
        if (localMaxDepCompleteTime > this.maxDepCompleteTime) {
            this.maxDepCompleteTime = localMaxDepCompleteTime;
        }
        if (this.completeTime < 0) {
            if (isDone() || isCompletedExceptionally() || isCancelled()) {
                this.completeTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        boolean ret = super.cancel(mayInterruptIfRunning);
        if (null != cancelThread) {
            cancelThread = Thread.currentThread();
            cancelStack = cancelThread.getStackTrace();
            cancelTime = System.currentTimeMillis();
        }
        saveProfileStrings();
        clearAlsoCancel();
        setCompleteTime();
        return ret;
    }

    @Nullable
    private volatile Throwable completedExceptionallyThrowable = null;
    @Nullable
    private volatile Thread completedExceptionallyThread = null;
    private volatile StackTraceElement completedExceptionallyTrace @Nullable []  = null;

    @Override
    public boolean completeExceptionally(Throwable ex) {
        completedExceptionallyThrowable = ex;
        completedExceptionallyThread = Thread.currentThread();
        completedExceptionallyTrace = Thread.currentThread().getStackTrace();

        boolean ret = super.completeExceptionally(ex);
        saveProfileStrings();
        clearAlsoCancel();
        setCompleteTime();
        return ret;
    }

    public long getRunTime() {
        long sTime = this.getStartTime();
        long cTime = this.getCompleteTime();
        long endTime = (cTime > 0) ? cTime : System.currentTimeMillis();
        return endTime - sTime;
    }

    protected String defaultName() {
        return defaultName(getName());
    }

    private static String defaultName(String name) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        final int ssIndexOf = name.indexOf("//");
        String nameSubstring;
        if (ssIndexOf > 0) {
            nameSubstring = name.substring(0, ssIndexOf);
        } else {
            nameSubstring = name;
        }
        for (int i = 0; i < trace.length; i++) {
            StackTraceElement stackTraceElement = trace[i];
            String className = stackTraceElement.getClassName();
            if (!className.startsWith("java.") && !className.contains("XFuture")) {
                return nameSubstring + "//" + stackTraceElement.toString();
            }
        }
        return name;
    }

    public <U> XFuture<U> thenCompose(String name, Function<? super T, ? extends CompletionStage<U>> fn) {

        XFuture<U> myF = new XFuture<>(name);
        myF.setKeepOldProfileStrings(this.keepOldProfileStrings);
        CompletableFuture<U> f = super.thenApply(fname(fn, name))
                .thenCompose((CompletionStage<U> stage) -> {
                    if (stage instanceof CompletableFuture) {
                        myF.alsoCancelAdd((CompletableFuture) stage);
                    } else {
                        myF.alsoCancelAdd(stage.toCompletableFuture());
                    }
                    return stage;
                });
        myF.alsoCancelAdd(f);
        myF.alsoCancelAdd(f.thenAccept(x -> myF.complete(x)));
        myF.alsoCancelAdd(this);
        return myF;
    }

    @Override
    public <U> XFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn, Executor executor) {
        return this.thenComposeAsync(defaultName(), fn, executor);
    }

    public <U> XFuture<U> thenComposeAsync(String name, Function<? super T, ? extends CompletionStage<U>> fn, Executor executor) {
        XFuture<U> myF = new XFuture<>(name);
        myF.setKeepOldProfileStrings(this.keepOldProfileStrings);
        CompletableFuture<U> f = super.thenApplyAsync(fname(fn, name), executor)
                .thenCompose((CompletionStage<U> stage) -> {
                    if (stage instanceof CompletableFuture) {
                        myF.alsoCancelAdd((CompletableFuture) stage);
                    } else {
                        myF.alsoCancelAdd(stage.toCompletableFuture());
                    }
                    return stage;
                });
        myF.alsoCancelAdd(f);
        myF.alsoCancelAdd(f.thenAccept(x -> myF.complete(x)));
        myF.alsoCancelAdd(this);
        return myF;
    }

    @Override
    public <U> XFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn) {
        return this.thenComposeAsync(defaultName(), fn);
    }

    public <U> XFuture<U> thenComposeAsync(String name, Function<? super T, ? extends CompletionStage<U>> fn) {
        XFuture<U> myF = new XFuture<>(name);
        myF.setKeepOldProfileStrings(this.keepOldProfileStrings);
        CompletableFuture<U> f = super.thenApplyAsync(fname(fn, name), getDefaultThreadPool())
                .thenCompose((CompletionStage<U> stage) -> {
                    if (stage instanceof CompletableFuture) {
                        myF.alsoCancelAdd((CompletableFuture) stage);
                    } else {
                        myF.alsoCancelAdd(stage.toCompletableFuture());
                    }
                    return stage;
                });
        myF.alsoCancelAdd(f);
        myF.alsoCancelAdd(f.thenAccept(x -> myF.complete(x)));
        myF.alsoCancelAdd(this);
        return myF;
    }

    @Nullable
    private volatile Thread cancelThread = null;
    private volatile long cancelTime = -1;
    private volatile StackTraceElement cancelStack  @Nullable []  = null;

    public StackTraceElement @Nullable [] getCancelStack() {
        return cancelStack;
    }

    @Nullable
    public XFuture<?> getCanceledDependant() {
        for (CompletableFuture<?> f : alsoCancel) {
            if (f instanceof XFuture) {
                XFuture<?> xf = (XFuture<?>) f;
                if (xf.cancelStack != null && xf.cancelThread != null) {
                    return xf;
                }
            }
        }
        return null;
    }

    public String shortCancelString() {
        if (null == cancelThread || null == cancelStack) {
            return "";
        }
        return " Canceled by " + cancelThread + " at " + shortTraceToString(cancelStack) + " at " + (new Date(cancelTime));
    }

    public String longCancelString() {
        if (null == cancelThread || null == cancelStack) {
            return "";
        }
        return " Canceled by thread=" + cancelThread + "\n"
                + " cancelStackTrace = \n" + traceToString(cancelStack) + "\n"
                + " cancelTime = " + (new Date(cancelTime)) + " or " + (System.currentTimeMillis() - cancelTime) + " ms ago.\n";
    }

    public String cancelDependantString() {
        XFuture<?> xf = getCanceledDependant();
        if (null != xf) {
            return xf.shortCancelString();
        }
        return "";
    }

    private static volatile boolean globalAllowInterupts = false;

    public static boolean getGlobalAllowInterrupts() {
        return globalAllowInterupts;
    }

    public static void setGlobalAllowInterrupts(boolean b) {
        globalAllowInterupts = b;
    }

    public void cancelAll(boolean mayInterrupt) {
        try {
            if (!isDone()) {
                if (alsoCancel.isEmpty()
                        && null == onCancelAllRunnable
                        && null == futureFromExecSubmit) {
                    if (null == cancelThread) {
                        cancelThread = Thread.currentThread();
                        cancelStack = cancelThread.getStackTrace();
                        cancelTime = System.currentTimeMillis();
                    }
                    super.cancel(mayInterrupt);
                } else {
                    cancelAllRecurse(mayInterrupt, this, 0, false);
                }
            }
        } catch (Exception e) {
            if (!closingMode) {
                Thread.dumpStack();
                Logger.getLogger(XFuture.class.getName()).log(Level.SEVERE, "", e);
                System.err.println("Cancel all ignoring " + e.toString());
            }
        }
    }

    private static final int MAX_RECURSE_COUNT
            = Integer.parseInt(System.getProperty("crcl.XFuture.MaxRecurseCount", "250"));

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void cancelAllRecurse(boolean mayInterrupt, XFuture<?> topFuture, int recurseCount, boolean stackDumped) {
        if (recurseCount > MAX_RECURSE_COUNT) {
            throw new IllegalStateException("recurseCount=" + recurseCount + ", topFuture=" + topFuture);
        }
        List<CompletableFuture<?>> alsoCancelCopy = new ArrayList<>(this.alsoCancel);

        if (null == cancelThread) {
            cancelThread = Thread.currentThread();
            cancelStack = cancelThread.getStackTrace();
            cancelTime = System.currentTimeMillis();
        }
        try {
            if (!this.isCancelled() && !this.isDone() && !this.isCompletedExceptionally()) {
                if (!closingMode && !stackDumped) {
                    System.err.println();
                    System.err.println("Cancelling XFuture " + getName());
                    Thread.dumpStack();
                    System.err.println("    createTrace=" + traceToString(createTrace));
                    System.err.println("End Cancelling XFuture " + getName());
                    System.err.println();
                    stackDumped = true;
                }
                this.cancel(false);
            }
        } catch (Exception e) {
            if (!closingMode) {
                System.err.println("Cancel all ignoring " + e.toString());
            }
        }
        if (null != onCancelAllRunnable) {
            onCancelAllRunnable.run();
        }
        if (null != futureFromExecSubmit) {
            futureFromExecSubmit.cancel(mayInterrupt);
        }
        if (mayInterrupt && null != threadToInterrupt && Thread.currentThread() != threadToInterrupt && globalAllowInterupts) {
            if (!closingMode) {
                Thread.dumpStack();
                System.err.println(toString() + "interrupting thread " + threadToInterrupt);
            }
            threadToInterrupt.interrupt();
        }

        for (CompletableFuture<?> f : alsoCancelCopy) {
            if (null != f && f != this && !f.isCancelled() && !f.isDone() && !f.isCompletedExceptionally()) {
                if (f instanceof XFuture) {
                    ((XFuture) f).cancelAllRecurse(mayInterrupt, topFuture, recurseCount + 1, stackDumped);
                } else {
                    f.cancel(mayInterrupt && globalAllowInterupts);
                }
            }
        }
        saveProfileStrings();
        clearAlsoCancel();
    }
    private volatile boolean cleared = false;

    private void clearAlsoCancel() {

        if (keepOldProfileStrings) {
            List<CompletableFuture<?>> alsoCancelCopy = new ArrayList<>(this.alsoCancel);
            this.alsoCancel.clear();
        } else {
            this.alsoCancel.clear();
        }
        cleared = true;
    }

    public static class PrintedXFutureRuntimeException extends RuntimeException {

        public PrintedXFutureRuntimeException(Throwable cause) {
            super(cause);
        }
    }

    static volatile PrintStream logExceptionPrintStream = System.err;

    public static void setLogExceptionPrintStream(PrintStream _logExceptionPrintStream) {
        logExceptionPrintStream = _logExceptionPrintStream;
    }

    public static PrintStream getLogExceptionPrintStream() {
        return logExceptionPrintStream;
    }

    static volatile boolean printCancellationExceptions = Boolean.getBoolean("XFuture.printCancellationExceptions");

    public static void setPrintCancellationExceptions(boolean _printCancellationExceptions) {
        printCancellationExceptions = _printCancellationExceptions;
    }

    public static boolean getPrintCancellationExceptions() {
        return printCancellationExceptions;
    }

    static private <T> T hiddenHandler(T ret, Throwable thrown, CompletableFuture<T> future, XFuture<T> newFuture) {
        if (null != thrown) {
            if (thrown instanceof PrintedXFutureRuntimeException) {
                newFuture.completeExceptionally(thrown);
                throw ((PrintedXFutureRuntimeException) thrown);
            } else if (thrown instanceof Error) {
                newFuture.completeExceptionally(thrown);
                throw ((Error) thrown);
            } else {
                PrintStream ps = logExceptionPrintStream;
                if (null != ps && !closingMode) {
                    Throwable cause = thrown.getCause();
                    boolean isCancellationException
                            = isPrintedOrCancellationException(thrown);
                    if (!isCancellationException || printCancellationExceptions) {
                        ps.println("future=" + future);
                        ps.println("Exception " + thrown + " passed through XFuture : " + newFuture.getName());
                        thrown.printStackTrace(ps);

                        if (null != cause) {
                            ps.println("cause=");
                            cause.printStackTrace(ps);
                        }
                        ps.println("XFuture.forExceptionString() = " + newFuture.forExceptionString());
                    }
                }
                newFuture.completeExceptionally(thrown);
                throw new PrintedXFutureRuntimeException(thrown);
            }
        }
        newFuture.complete(ret);
        return ret;
    }

    private static <T> XFuture<T> staticwrap(String name, CompletableFuture<T> future) {
        if (future instanceof XFuture) {
            return (XFuture<T>) future;
        }
        XFuture<T> newFuture = new XFuture<>(name);
        future.handle((x, t) -> hiddenHandler(x, t, future, newFuture));
        newFuture.alsoCancelAdd(future);
        return newFuture;
    }

    public <T> XFuture<T> wrap(String name, CompletableFuture<T> future) {
        if (future instanceof XFuture) {
            if (this != future) {
                ((XFuture<T>) future).alsoCancelAdd(this);
            }
            return (XFuture<T>) future;
        }
        XFuture<T> newFuture = new XFuture<>(name);
        newFuture.setKeepOldProfileStrings(this.keepOldProfileStrings);
        future.handle((x, t) -> hiddenHandler(x, t, future, newFuture));
        newFuture.alsoCancelAdd(this);
        newFuture.alsoCancelAdd(future);
        return newFuture;
    }

    public XFutureVoid wrapvoid(String name, CompletableFuture<Void> future) {
        if (future instanceof XFutureVoid) {
            if (this != future) {
                ((XFuture<Void>) future).alsoCancelAdd(this);
            }
            return (XFutureVoid) future;
        }
        XFutureVoid newFuture = new XFutureVoid(name);
        newFuture.setKeepOldProfileStrings(this.keepOldProfileStrings);
        future.handle((x, t) -> hiddenHandler(x, t, future, newFuture));
        newFuture.alsoCancelAdd(this);
        newFuture.alsoCancelAdd(future);
        return newFuture;
    }

    private <A, R> Function<A, R> fWrap(Function<A, R> f) {
        return x -> {
            try {
                return f.apply(x);
            } catch (Throwable t) {
                checkAndLogExceptioni(t, "Exception in XFuture " + XFuture.this.forExceptionString());
                if (t instanceof RuntimeException) {
                    throw ((RuntimeException) t);
                } else {
                    throw new RuntimeException(t);
                }
            }
        };
    }

    private <A, B, R> BiFunction<A, B, R> biWrap(BiFunction<A, B, R> f) {
        return (A a, B b) -> {
            try {
                return f.apply(a, b);
            } catch (Throwable t) {
                checkAndLogExceptioni(t, "Exception in XFuture " + XFuture.this.forExceptionString());
                if (t instanceof RuntimeException) {
                    throw ((RuntimeException) t);
                } else {
                    throw new RuntimeException(t);
                }
            }
        };
    }

    static boolean isPrintedOrCancellationException(Throwable t) {
        Throwable cause = t.getCause();
        return (t instanceof CancellationException) || (t instanceof PrintedXFutureRuntimeException)
                || (null != cause && t != cause
                && (cause instanceof CancellationException) || (cause instanceof PrintedXFutureRuntimeException));
    }

    @Nullable
    private volatile Throwable throwable = null;

    @SuppressWarnings("nullness")
    @Nullable
    public Throwable getThrowable() {
        if (!isCompletedExceptionally()) {
            return null;
        }
        if (null != throwable) {
            return throwable;
        }
        super.exceptionally(t -> {
            throwable = t;
            if (null != t) {
                if (t instanceof RuntimeException) {
                    throw (RuntimeException) t;
                } else {
                    throw new RuntimeException(t);
                }
            }
            return null;
        });
        return throwable;
    }

    @Override
    public XFuture<T> exceptionally(Function<Throwable, ? extends @Nullable T> fn) {
        return wrap(this.name + ".exceptionally", super.exceptionally(fn));
    }

    public XFuture<T> peekException(Consumer<Throwable> consumer) {
        Function<Throwable, T> func = (Throwable throwable1) -> {
            if (null != throwable1) {
                consumer.accept(throwable1);
                if (throwable1 instanceof RuntimeException) {
                    throw (RuntimeException) throwable1;
                } else {
                    throw new RuntimeException(throwable1);
                }
            }
            throw new NullPointerException("throwable1");
        };
        return wrap(this.name + ".exceptionally", super.exceptionally(func));
    }

    public XFuture<T> exceptionally(String name, Function<Throwable, ? extends T> fn) {
        return wrap(name, super.exceptionally(fn));
    }

    public XFuture<T> always(Runnable r) {
        return this.always(defaultName(), r);
    }

    public XFuture<T> always(String name, Runnable r) {
        return wrap(name, super.handle((x, t) -> {
            try {
                r.run();
            } catch (Throwable t2) {
                logAndRethrow(t2);
            }
            rethrow(t);
            return x;
        }));
    }

    public XFuture<T> alwaysAsync(String name, Runnable r, ExecutorService service) {
        return wrap(name, super.handleAsync((x, t) -> {
            try {
                r.run();
            } catch (Throwable t2) {
                logAndRethrow(t2);
            }
            rethrow(t);
            return x;
        }, service));
    }

    public XFuture<T> alwaysAsync(Runnable r, ExecutorService service) {
        return this.alwaysAsync(defaultName(), r, service);
    }

    @Override
    public <U> XFuture<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn, Executor executor) {
        return this.handleAsync(defaultName(), fn, executor);
    }

    public <U> XFuture<U> handleAsync(String name, BiFunction<? super T, Throwable, ? extends U> fn, Executor executor) {
        return wrap(name, super.handleAsync(biWrap(fn), executor));
    }

    @Override
    public <U> XFuture<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn) {
        return this.handle(defaultName(), fn);
    }

    public <U> XFuture<U> handleAsync(String name, BiFunction<? super T, Throwable, ? extends U> fn) {
        return wrap(name, super.handleAsync(biWrap(fn)));
    }

    @Override
    public <U> XFuture<U> handle(BiFunction<? super T, Throwable, ? extends U> fn) {
        return this.handle(defaultName(), fn);
    }

    public <U> XFuture<U> handle(String name, BiFunction<? super T, Throwable, ? extends U> fn) {
        return wrap(name, super.handle(biWrap(fn)));
    }

    @Override
    public XFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action, Executor executor) {
        return wrap(this.name + ".whenCompleteAsync", super.whenCompleteAsync(action, executor));
    }

    @Override
    public XFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action) {
        return wrap(this.name + ".whenCompleteAsync", super.whenCompleteAsync(action));
    }

    @Override
    public XFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action) {
        return wrap(this.name + ".whenComplete", super.whenComplete(action));
    }

    @Override
    public XFutureVoid runAfterEitherAsync(CompletionStage<?> other, Runnable action, Executor executor) {
        return wrapvoid(this.name + ".runAfterEitherAsync", super.runAfterEitherAsync(other, action, executor));
    }

    @Override
    public XFutureVoid runAfterEitherAsync(CompletionStage<?> other, Runnable action) {
        return wrapvoid(this.name + ".runAfterEitherAsync", super.runAfterEitherAsync(other, action, getDefaultThreadPool()));
    }

    @Override
    public XFutureVoid runAfterEither(CompletionStage<?> other, Runnable action) {
        return wrapvoid(this.name + ".runAfterEither", super.runAfterEither(other, action));
    }

    @Override
    public XFutureVoid acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action, Executor executor) {
        return wrapvoid(this.name + ".acceptEitherAsync", super.acceptEitherAsync(other, action, executor));
    }

    @Override
    public XFutureVoid acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action) {
        return wrapvoid(this.name + ".acceptEitherAsync", super.acceptEitherAsync(other, action, getDefaultThreadPool()));
    }

    @Override
    public XFutureVoid acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action) {
        return wrapvoid(this.name + ".acceptEither", super.acceptEither(other, action));
    }

    @Override
    public <U> XFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn, Executor executor) {
        return wrap(this.name + ".applyToEitherAsync", super.applyToEitherAsync(other, fn, executor));
    }

    @Override
    public <U> XFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn) {
        return wrap(this.name + ".applyToEitherAsync", super.applyToEitherAsync(other, fn, getDefaultThreadPool()));
    }

    @Override
    public <U> XFuture<U> applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn) {
        return wrap(this.name + ".applyToEither", super.applyToEither(other, fn));
    }

    @Override
    public XFutureVoid runAfterBothAsync(CompletionStage<?> other, Runnable action, Executor executor) {
        return wrapvoid(this.name + ".runAfterBothAsync", super.runAfterBothAsync(other, action, executor));
    }

    @Override
    public XFutureVoid runAfterBothAsync(CompletionStage<?> other, Runnable action) {
        return wrapvoid(this.name + ".runAfterBothAsync", super.runAfterBothAsync(other, action, getDefaultThreadPool()));
    }

    @Override
    public XFutureVoid runAfterBoth(CompletionStage<?> other, Runnable action) {
        return wrapvoid(this.name + ".runAfterBoth", super.runAfterBoth(other, action));
    }

    @Override
    public <U> XFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action, Executor executor) {
        return wrap(this.name + ".thenAcceptBothAsync", super.thenAcceptBothAsync(other, action, executor));
    }

    @Override
    public <U> XFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action) {
        return wrap(this.name + ".thenAcceptBothAsync", super.thenAcceptBothAsync(other, action, getDefaultThreadPool()));
    }

    @Override
    public <U> XFuture<Void> thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action) {
        return wrap(this.name + ".thenAcceptBoth", super.thenAcceptBoth(other, action));
    }

    private int computeMaxRecurseCount() {
        int maxRecurse = 0;
        if (this.isCancelled() || this.isCompletedExceptionally() || this.isDone()) {
            clearAlsoCancel();
            return 0;
        }
        List<CompletableFuture<?>> alsoCancelCopy = new ArrayList<>(this.alsoCancel);
        for (int i = 0; i < alsoCancelCopy.size(); i++) {
            CompletableFuture<?> cf = alsoCancelCopy.get(i);
            if (cf != null && !cf.isCancelled() && !cf.isCompletedExceptionally() && !cf.isDone()) {
                if (cf instanceof XFuture) {
                    XFuture xf = (XFuture) cf;
                    int xfMaxRecurse = xf.computeMaxRecurseCount();
                    if (xfMaxRecurse > maxRecurse) {
                        maxRecurse = xfMaxRecurse;
                    }
                }
            }
        }
        return maxRecurse + 1;
    }

    private volatile int origMaxRecurse = 0;

    protected void alsoCancelAdd(CompletableFuture<?> cf) {
        if (cf instanceof XFuture) {
            XFuture<?> xf = (XFuture) cf;
            int xfMaxRecurseCount = xf.computeMaxRecurseCount();
            if (xfMaxRecurseCount > origMaxRecurse - 1) {
                origMaxRecurse = xfMaxRecurseCount + 1;
            }
            if (xfMaxRecurseCount > MAX_RECURSE_COUNT - 1) {
                throw new IllegalStateException("xf.computeMaxRecurseCount() =" + xfMaxRecurseCount + ",MAX_RECURSE_COUNT=" + MAX_RECURSE_COUNT + ",xf=" + xf);
            }
            if (xf.keepOldProfileStrings) {
                this.setKeepOldProfileStrings(true);
            }
            if (this.keepOldProfileStrings) {
                xf.setKeepOldProfileStrings(true);
            }
            xFutureAlsoCancelCount.incrementAndGet();
        } else {
            cfFutureAlsoCancelCount.incrementAndGet();
        }
        if (cf == this) {
            throw new IllegalStateException("attempt to addAlsoCancel of self :this=" + this);
        }
        this.alsoCancel.add(cf);
    }

    @Override
    public <U, V> XFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn, Executor executor) {
        XFuture<V> ret = wrap(this.name + ".thenCombineAsync", super.thenCombineAsync(other, fn, executor));
        if (other instanceof CompletableFuture) {
            ret.alsoCancelAdd((CompletableFuture) other);
        }
        return ret;
    }

    @Override
    public <U, V> XFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn) {
        XFuture<V> ret = wrap(this.name + ".thenCombineAsync", super.thenCombineAsync(other, fn, getDefaultThreadPool()));
        if (other instanceof CompletableFuture) {
            ret.alsoCancelAdd((CompletableFuture) other);
        }
        return ret;
    }

    @Override
    public <U, V> XFuture<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn) {
        XFuture<V> ret = wrap(this.name + ".thenCombine", super.thenCombine(other, fn));
        if (other instanceof CompletableFuture) {
            ret.alsoCancelAdd((CompletableFuture) other);
        }
        return ret;
    }

    public <U, V> XFuture<V> thenCombine(String name, CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn) {
        XFuture<V> ret = wrap(name, super.thenCombine(other, fn));
        if (other instanceof CompletableFuture) {
            ret.alsoCancelAdd((CompletableFuture) other);
        }
        return ret;
    }

    public XFutureVoid thenRunAsync(String name, Runnable action, Executor executor) {
        return wrapvoid(name, super.thenRunAsync(runWrap(action), executor));
    }

    @Override
    public XFutureVoid thenRunAsync(Runnable action, Executor executor) {
        return this.thenRunAsync(defaultName(), action, executor);
    }

    @Override
    public XFutureVoid thenRunAsync(Runnable action) {
        return this.thenRunAsync(defaultName(), action);
    }

    public XFutureVoid thenRunAsync(String name, Runnable action) {
        return wrapvoid(name, super.thenRunAsync(runWrap(action), getDefaultThreadPool()));
    }

    public XFutureVoid thenRunAsync(String name, Runnable action, ExecutorService es) {
        return wrapvoid(name, super.thenRunAsync(runWrap(action), es));
    }

    private Runnable runWrap(Runnable r) {
        return () -> {
            try {
                r.run();
            } catch (Throwable t) {
                logAndRethrow(t);
            }
        };
    }

    private <T> T logAndRethrow(Throwable t) {
        checkAndLogExceptioni(t, "Exception in XFuture " + XFuture.this.forExceptionString());
        return rethrow(t);
    }

    @Override
    public XFutureVoid thenRun(Runnable action) {
        return this.thenRun(defaultName(), action);
    }

    public XFutureVoid thenRun(String name, Runnable action) {
        return wrapvoid(name, super.thenRun(runWrap(action)));
    }

    @Override
    public XFutureVoid thenAcceptAsync(Consumer<? super T> action, Executor executor) {
        return wrapvoid(this.name + ".thenAcceptAsync", super.thenAcceptAsync(action, executor));
    }

    @Override
    public XFutureVoid thenAcceptAsync(Consumer<? super T> action) {
        return wrapvoid(this.name + ".thenAcceptAsync", super.thenAcceptAsync(action, getDefaultThreadPool()));
    }

    @Override
    public XFutureVoid thenAccept(Consumer<? super T> action) {
        return wrapvoid(this.name + ".thenAccept", super.thenAccept(action));
    }

    @Override
    public <U> XFuture<U> thenApplyAsync(Function<? super T, ? extends U> fn, Executor executor) {
        return wrap(this.name + ".thenApplyAsync", super.thenApplyAsync(fn, executor));
    }

    public <U> XFuture<U> thenApplyAsync(String name, Function<? super T, ? extends U> fn, Executor executor) {
        return wrap(name, super.thenApplyAsync(fn, executor));
    }

    public <U> XFuture<U> thenApplyAsync(String name, Function<? super T, ? extends U> fn) {
        return wrap(name, super.thenApplyAsync(fn, getDefaultThreadPool()));
    }

    @Override
    public <U> XFuture<U> thenApplyAsync(Function<? super T, ? extends U> fn) {
        return wrap(this.name + ".thenApplyAsync", super.thenApplyAsync(fn, getDefaultThreadPool()));
    }

    @Override
    public <U> XFuture<U> thenApply(Function<? super T, ? extends U> fn) {
        return wrap(this.name + ".thenApply", super.thenApply(fn));
    }

    public <U> XFuture<U> thenApply(String name, Function<? super T, ? extends U> fn) {
        return wrap(name, super.thenApply(fn));
    }

    public XFuture<T> alwaysCompose(Supplier<XFutureVoid> supplier) {
        return this.alwaysCompose(defaultName(), supplier);
    }

    public XFuture<T> alwaysCompose(String name, Supplier<XFutureVoid> supplier) {
        XFuture<T> ret = new XFuture<T>(name);
        XFuture<T> orig
                = wrap(
                        name,
                        super.handle((T x, Throwable t) -> {
                            try {
                                supplier.get()
                                        .thenRun(() -> {
                                            if (null != t) {
                                                ret.completeExceptionally(t);
                                                if (t instanceof RuntimeException) {
                                                    throw ((RuntimeException) t);
                                                } else {
                                                    throw new RuntimeException(t);
                                                }
                                            }
                                            ret.complete((T) x);
                                        });
                            } catch (Throwable t2) {
                                logStaticException("Exception in XFutureVoid  " + XFuture.this.toString(), t2);
                                if (null == t) {
                                    if (t2 instanceof RuntimeException) {
                                        throw ((RuntimeException) t2);
                                    } else {
                                        throw new RuntimeException(t2);
                                    }
                                }
                            }
                            if (null != t) {
                                if (t instanceof RuntimeException) {
                                    throw ((RuntimeException) t);
                                } else {
                                    throw new RuntimeException(t);
                                }
                            }
                            return x;
                        }));
        ret.alsoCancelAdd(orig);
        return ret;
    }

    public XFuture<T> alwaysComposeAsync(Supplier<XFutureVoid> supplier, ExecutorService service) {
        return this.alwaysComposeAsync(defaultName(), supplier, service);
    }

    public XFuture<T> alwaysComposeAsync(String name, Supplier<XFutureVoid> supplier, ExecutorService service) {
        XFuture<T> ret = new XFuture<T>(name);
        XFuture<T> orig
                = wrap(
                        name,
                        super.handleAsync((T x, Throwable t) -> {
                            try {
                                supplier.get()
                                        .thenRun(() -> {
                                            if (null != t) {
                                                ret.completeExceptionally(t);
                                                if (t instanceof RuntimeException) {
                                                    throw ((RuntimeException) t);
                                                } else {
                                                    throw new RuntimeException(t);
                                                }
                                            }
                                            ret.complete((T) x);
                                        });
                            } catch (Throwable t2) {
                                logStaticException("Exception in XFutureVoid  " + XFuture.this.toString(), t2);
                                if (null == t) {
                                    if (t2 instanceof RuntimeException) {
                                        throw ((RuntimeException) t2);
                                    } else {
                                        throw new RuntimeException(t2);
                                    }
                                }
                            }
                            if (null != t) {
                                if (t instanceof RuntimeException) {
                                    throw ((RuntimeException) t);
                                } else {
                                    throw new RuntimeException(t);
                                }
                            }
                            return x;
                        },
                                service));
        ret.alsoCancelAdd(orig);
        return ret;
    }
}
