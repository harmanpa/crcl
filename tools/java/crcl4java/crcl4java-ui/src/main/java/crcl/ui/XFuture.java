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
import java.io.PrintStream;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class XFuture<T> extends CompletableFuture<T> {

    @MonotonicNonNull private volatile Future<T> futureFromExecSubmit = null;
    @MonotonicNonNull private volatile Thread threadToInterrupt = null;
    @MonotonicNonNull private volatile Runnable onCancelAllRunnable = null;

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

    @Nullable protected Future<T> getFutureFromExecSubmit() {
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
        this.name = name;
        this.startTime = System.currentTimeMillis();
        this.num = numCounter.incrementAndGet();
        this.createThread = Thread.currentThread();
        this.createTrace = this.createThread.getStackTrace();
    }

    public static XFutureVoid allOfWithName(String name, Collection<? extends CompletableFuture<?>> cfsCollection) {
        return allOfWithName(name, cfsCollection.toArray(new CompletableFuture[0]));
    }

    public static XFutureVoid allOf(String name, Collection<? extends CompletableFuture<?>> cfsCollection) {
        return allOf(cfsCollection.toArray(new CompletableFuture[0]));
    }

    private static String createTraceToString(StackTraceElement stea[]) {
        return Arrays.stream(stea)
                .filter(ste -> !ste.getClassName().contains("Future") && !ste.getClassName().startsWith("java.lang"))
                .map(StackTraceElement::toString)
                .collect(Collectors.joining(","));
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
        ps.println("Status for " + this.toString());
        internalPrintStatus(ps);

        if (isCompletedExceptionally()) {
            this.exceptionally(t -> {
                if (!(t instanceof CancellationException) || printCancellationExceptions) {
                    System.err.println(XFuture.this.toString() + " Completed Exceptionally with: ");
                    t.printStackTrace(ps);
                }
                return null;
            });
            return;
        }
        ps.println();
    }

    @SuppressWarnings("nullness")
    private Object printEx(Throwable t, PrintStream ps) {
        if (!(t instanceof CancellationException) || printCancellationExceptions) {
            System.err.println(XFuture.this.toString() + " Completed Exceptionally with: ");
            t.printStackTrace(ps);
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
    @Nullable private volatile String getProfileStringThreadName = null;

    public String getProfileString() {
        setCompleteTime();
        if (completeTime < 0 && isDone()) {
            System.err.println("wtf");
        }
        if (null != getProfileStringTrace || null != getProfileStringThreadName) {
            System.out.println("getProfileStringThreadName = " + getProfileStringThreadName);
            System.out.println("getProfileStringTrace = " + Arrays.toString(getProfileStringTrace));
        }
        getProfileStringTrace = Thread.currentThread().getStackTrace();
        getProfileStringThreadName = Thread.currentThread().getName();
        return joinAny(",", num, startTime, completeTime, (completeTime - startTime), (completeTime - maxDepCompleteTime), "\"" + name + "\"", isCompletedExceptionally(), isCancelled(), isDone(), xFutureAlsoCancelCount.get(), cfFutureAlsoCancelCount.get(), "\"" + createTraceToString(createTrace) + "\"");
    }

    @Nullable private volatile List<String> prevProfileStrings = null;

    @SuppressWarnings("unchecked")
    private void getAllProfileString(Iterable<CompletableFuture> localAlsoCancels, List<String> listIn) {

        List<String> localPrevProfileStrings = this.prevProfileStrings;
        List<CompletableFuture> localAlsoCancelsCopy = new ArrayList<CompletableFuture>();
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
//                        int xfAlsoCancelSize = xf.alsoCancel.size();
                        List<CompletableFuture> localAlsoCancel = new ArrayList<>(xf.alsoCancel);
//                        List<CompletableFuture> prevAlsoCancelCopy = new ArrayList<>();
//                        if (null != xf.prevAlsoCancel) {
//                            prevAlsoCancelCopy.addAll(xf.prevAlsoCancel);
//                            xf.checkPrevAlsoCancelProfiled();
//                        }
//                        if (localAlsoCancel.size() != xfAlsoCancelSize) {
//                            System.err.println("size mismatch");
//                        }
                        xf.clearAlsoCancel();
//                        if (localAlsoCancel.size() != xf.prevAlsoCancel.size()) {
//                            System.err.println("size mismatch");
//                        }
                        xf.getAllProfileString(localAlsoCancel, listIn);
                    } else {
                        System.err.println("also cancel not done");
                        xf.getAllProfileString(this.alsoCancel, listIn);
                    }
//                    xf.checkPrevAlsoCancelProfiled();
                }
            }
            listIn.add(getProfileString());
        }
        StackTraceElement[] localGetProfileStringTrace = this.getProfileStringTrace;
        if (localGetProfileStringTrace == null) {
            System.err.println("getAllProfileString called without setting getProfileStringTrace");
        }
//        this.checkPrevAlsoCancelProfiled();
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
            List<CompletableFuture> localAlsoCancel = new ArrayList<>(this.alsoCancel);
            if (!localAlsoCancel.isEmpty()) {
                clearAlsoCancel();
                StackTraceElement preGetProfileTrace[] = this.getProfileStringTrace;
                List<String> prePrevProfileString = this.prevProfileStrings;
                getAllProfileString(localAlsoCancel, l);
//                checkPrevAlsoCancelProfiled();
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

    @SuppressWarnings("unchecked")
    private void internalPrintStatus(PrintStream ps) {

        if (isCompletedExceptionally()) {
            ps.println(this.toString() + " is CompletedExceptionally.");
            this.exceptionally(t -> {
                if (!(t instanceof CancellationException) || printCancellationExceptions) {
                    System.err.println(XFuture.this.toString() + " Completed Exceptionally with: ");
                    t.printStackTrace(ps);
                }
                return null;
            });
            return;
        }
        if (isCancelled()) {
            ps.println(this.toString() + " is cancelled.");
            return;
        } else if (isDone()) {
            ps.println(this.toString() + " is done.");
            return;
        }
        for (CompletableFuture f : alsoCancel) {
            if (f instanceof XFuture) {
                XFuture xf = (XFuture) f;
                xf.internalPrintStatus(ps);
            }
        }
        Function<Throwable, Object> exPrinter = (Throwable t) -> {
            return printEx(t, ps);
        };
        for (CompletableFuture<?> f : alsoCancel) {
            if (!(f instanceof XFuture)) {
                ps.println("done=" + isDone() + "\tcancelled=" + isCancelled() + "\t" + f.toString());
                if (f.isCompletedExceptionally()) {
                    CompletableFuture<Object> fob = (CompletableFuture<Object>) f;
                    fob.exceptionally(exPrinter);
                }
            }
        }
        ps.println("done=" + isDone() + "\tcancelled=" + isCancelled() + "\t" + this.toString());

    }

    @Override
    public String toString() {
        return super.toString() + "{" + name + "(" + getRunTime() + "ms ago) " + cancelString() + "createThread=" + createThread + ", createTrace=" + createTraceToString(createTrace) + '}';
    }

    @Nullable public Runnable getOnCancelAllRunnable() {
        return onCancelAllRunnable;
    }

    public void setOnCancelAllRunnable(Runnable onCancelAllRunnable) {
        this.onCancelAllRunnable = onCancelAllRunnable;
    }

    @Nullable public Thread getThreadToInterrupt() {
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
        for (CompletableFuture cf : cfs) {
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

    private final ConcurrentLinkedDeque<CompletableFuture<?>> alsoCancel = new ConcurrentLinkedDeque<>();

    public static <T> XFuture<T> supplyAsync(String name, Callable<T> c, ExecutorService es) {
        XFuture<T> myf = new XFuture<>(name);
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
                T result = c.call();
                myf.complete(result);
//                myf.alsoCancel.clear();
                if (!javax.swing.SwingUtilities.isEventDispatchThread()) {
                    Thread.currentThread().setName(tname);
                }
                return result;
            } catch (Throwable throwable) {
                myf.completeExceptionally(throwable);
                Logger.getLogger(XFuture.class.getName()).log(Level.SEVERE, null, throwable);
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

    public static XFutureVoid runAsync(String name, Runnable r, ExecutorService es) {
        return XFutureVoid.runAsync(name, r, es);
    }

    public static <T> XFuture<T> supplyAsync(String name, Callable<T> c) {
        return supplyAsync(name, c, getDefaultThreadPool());
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
                Logger.getLogger(XFuture.class.getName()).log(Level.SEVERE, null, t);
                FT chk = fn.apply(x);
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
        return this.thenCompose(name + ".thenCompose", fn);
    }

    public XFutureVoid thenComposeToVoid(Function<? super T, ? extends XFuture<Void>> fn) {
        XFuture<Void> future = this.thenCompose(name + ".thenComposeToVoid", fn);
        XFutureVoid ret = new XFutureVoid(name + ".thenComposeToVoid");
        future.thenRun(() -> ret.complete());
        ret.alsoCancelAdd(future);
        return ret;
    }

    public XFutureVoid thenComposeAsyncToVoid(Function<? super T, ? extends XFuture<Void>> fn) {
        XFuture<Void> future = this.thenComposeAsync(name + ".thenComposeAsyncToVoid", fn);
        XFutureVoid ret = new XFutureVoid(name + ".thenComposeAsyncToVoid");
        future.thenRun(() -> ret.complete());
        ret.alsoCancelAdd(future);
        return ret;
    }

    public XFutureVoid thenComposeAsyncToVoid(Function<? super T, ? extends XFuture<Void>> fn, ExecutorService es) {
        XFuture<Void> future = this.thenComposeAsync(name + ".thenComposeAsyncToVoid", fn, es);
        XFutureVoid ret = new XFutureVoid(name + ".thenComposeAsyncToVoid");
        future.thenRun(() -> ret.complete());
        ret.alsoCancelAdd(future);
        return ret;
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
            for (CompletableFuture cf : this.alsoCancel) {
                if (cf instanceof XFuture) {
                    XFuture xf = (XFuture) cf;
                    xf.setKeepOldProfileStrings(true);
                }
            }
        }
    }

    @Override
    public boolean complete(T value) {
        boolean ret = super.complete(value);
        saveProfileStrings();
        clearAlsoCancel();
        setCompleteTime();
        return ret;
    }

    private void saveProfileStrings() {
        if (keepOldProfileStrings) {
            List<String> l = new ArrayList<>();
            List<CompletableFuture> localAlsoCancel = new ArrayList<>(this.alsoCancel);
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
        for (CompletableFuture f : this.alsoCancel) {
            if (f instanceof XFuture) {
                XFuture xf = (XFuture) f;
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

    @Override
    public boolean completeExceptionally(Throwable ex) {
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
        return this.thenComposeAsync(name + ".thenComposeAsync", fn, executor);
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
        return this.thenComposeAsync(name + ".thenComposeAsync", fn);
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

    @Nullable private volatile Thread cancelThread = null;
    private volatile long cancelTime = -1;
    private volatile StackTraceElement cancelStack  @Nullable []  = null;

    public StackTraceElement @Nullable [] getCancelStack() {
        return cancelStack;
    }

    @Nullable public XFuture<?> getCanceledDependant() {
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

    public String cancelString() {
        if (null == cancelThread || null == cancelStack) {
            return "";
        }
        return " Canceled by " + cancelThread + " at " + createTraceToString(cancelStack) + " at " + (new Date(cancelTime));
    }

    public String cancelDependantString() {
        XFuture<?> xf = getCanceledDependant();
        if (null != xf) {
            return xf.cancelString();
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

//    private volatile List<CompletableFuture<?>> prevAlsoCancel = null;
    public void cancelAll(boolean mayInterrupt) {
        try {
            List<CompletableFuture<?>> alsoCancelCopy = new ArrayList<>(this.alsoCancel);
            if (null != cancelThread) {
                cancelThread = Thread.currentThread();
                cancelStack = cancelThread.getStackTrace();
                cancelTime = System.currentTimeMillis();
            }
            try {
                if (!this.isCancelled() && !this.isDone() && !this.isCompletedExceptionally()) {
                    this.cancel(false);
                }
            } catch (Exception e) {
                System.err.println("Cancel all ignoring " + e.toString());
            }
            if (null != onCancelAllRunnable) {
                onCancelAllRunnable.run();
            }
            if (null != futureFromExecSubmit) {
                futureFromExecSubmit.cancel(mayInterrupt);
            }
            if (mayInterrupt && null != threadToInterrupt && Thread.currentThread() != threadToInterrupt && globalAllowInterupts) {
                Thread.dumpStack();
                System.err.println(toString() + "interrupting thread " + threadToInterrupt);
                threadToInterrupt.interrupt();
            }

            for (CompletableFuture f : alsoCancel) {
                if (null != f && f != this && !f.isCancelled() && !f.isDone() && !f.isCompletedExceptionally()) {
                    try {
                        if (f instanceof XFuture) {
                            ((XFuture) f).cancelAll(mayInterrupt);
                        } else {
                            f.cancel(mayInterrupt && globalAllowInterupts);
                        }
                    } catch (Exception e) {
                        System.err.println("Cancel all ignoring " + e.toString());
                    }
                }
            }
            for (CompletableFuture f : alsoCancelCopy) {
                if (null != f && f != this && !f.isCancelled() && !f.isDone() && !f.isCompletedExceptionally()) {
                    try {
                        if (f instanceof XFuture) {
                            ((XFuture) f).cancelAll(mayInterrupt);
                        } else {
                            f.cancel(mayInterrupt && globalAllowInterupts);
                        }
                    } catch (Exception e) {
                        System.err.println("Cancel all ignoring " + e.toString());
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Cancel all ignoring " + e.toString());
        }
        saveProfileStrings();
        clearAlsoCancel();
    }
    private volatile boolean cleared = false;

    private void clearAlsoCancel() {
//        if (cleared) {
//            System.err.println("cleared twice");
//        }
        if (keepOldProfileStrings) {
            List<CompletableFuture<?>> alsoCancelCopy = new ArrayList<>(this.alsoCancel);
            this.alsoCancel.clear();
//            if (null == prevAlsoCancel) {
//                prevAlsoCancel = new ArrayList<>();
//            }
//            prevAlsoCancel.addAll(alsoCancelCopy);
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

    private static volatile PrintStream logExceptionPrintStream = System.err;

    public static void setLogExceptionPrintStream(PrintStream _logExceptionPrintStream) {
        logExceptionPrintStream = _logExceptionPrintStream;
    }

    public static PrintStream getLogExceptionPrintStream() {
        return logExceptionPrintStream;
    }

    private static volatile boolean printCancellationExceptions = Boolean.getBoolean("XFuture.printCancellationExceptions");

    public static void setPrintCancellationExceptions(boolean _printCancellationExceptions) {
        printCancellationExceptions = _printCancellationExceptions;
    }

    public static boolean getPrintCancellationExceptions() {
        return printCancellationExceptions;
    }

    public <T> T logException(T ret, Throwable thrown) {
        if (null != thrown) {
            this.completeExceptionally(thrown);
            if (thrown instanceof PrintedXFutureRuntimeException) {
                throw ((PrintedXFutureRuntimeException) thrown);
            } else {
                PrintStream ps = logExceptionPrintStream;
                if (null != ps) {
                    Throwable cause = thrown.getCause();
                    boolean isCancellationException
                            = (thrown instanceof CancellationException)
                            || (null != cause && cause instanceof CancellationException);
                    if (!isCancellationException || printCancellationExceptions) {
                        thrown.printStackTrace(ps);
                        if (null != cause) {
                            cause.printStackTrace(ps);
                        }
                    } else {
                        if (thrown instanceof RuntimeException) {
                            RuntimeException re = (RuntimeException) thrown;
                            throw re;
                        }
                    }
                }
                throw new PrintedXFutureRuntimeException(thrown);
            }
        }
        return ret;
    }

    private static <T> XFuture<T> staticwrap(String name, CompletableFuture<T> future) {
        if (future instanceof XFuture) {
            return (XFuture<T>) future;
        }
        XFuture<T> newFuture = new XFuture<>(name);
        future.handle(newFuture::logException)
                .thenAccept(x -> {
                    newFuture.complete(x);
                });
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
        future.handle(newFuture::logException)
                .thenAccept(x -> {
                    newFuture.complete(x);
                });
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
        future.handle(newFuture::logException)
                .thenRun(() -> {
                    newFuture.complete();
                });
        newFuture.alsoCancelAdd(this);
        newFuture.alsoCancelAdd(future);
        return newFuture;
    }

    private <A, R> Function<A, R> fWrap(Function<A, R> f) {
        return x -> {
            try {
                return f.apply(x);
            } catch (Throwable t) {
                Logger.getLogger(XFuture.class.getName()).log(Level.SEVERE, null, t);
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
                return (R) f.apply(a, b);
            } catch (Throwable t) {
                Logger.getLogger(XFuture.class.getName()).log(Level.SEVERE, null, t);
                if (t instanceof RuntimeException) {
                    throw ((RuntimeException) t);
                } else {
                    throw new RuntimeException(t);
                }
            }
        };
    }

    @Nullable private volatile Throwable throwable = null;

    @SuppressWarnings("nullness")
    @Nullable public Throwable getThrowable() {
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

    public XFuture<T> exceptionally(String name, Function<Throwable, ? extends T> fn) {
        return wrap(name, super.exceptionally(fn));
    }

    public XFuture<T> always(Runnable r) {
        return wrap(name + ".always", super.handle((x, t) -> {
            try {
                r.run();
            } catch (Throwable t2) {
                Logger.getLogger(XFuture.class.getName()).log(Level.SEVERE, null, t2);
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
    }

    public XFuture<T> always(String name, Runnable r) {
        return wrap(name, super.handle((x, t) -> {
            try {
                r.run();
            } catch (Throwable t2) {
                Logger.getLogger(XFuture.class.getName()).log(Level.SEVERE, null, t2);
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
    }

    public XFuture<T> alwaysAsync(String name, Runnable r, ExecutorService service) {
        return wrap(name, super.handleAsync((x, t) -> {
            try {
                r.run();
            } catch (Throwable t2) {
                Logger.getLogger(XFuture.class.getName()).log(Level.SEVERE, null, t2);
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
        }, service));
    }

    public XFuture<T> alwaysAsync(Runnable r, ExecutorService service) {
        return wrap(this.name + ".alwaysAsync", super.handleAsync((x, t) -> {
            try {
                r.run();
            } catch (Throwable t2) {
                Logger.getLogger(XFuture.class.getName()).log(Level.SEVERE, null, t2);
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
        }, service));
    }

    @Override
    public <U> XFuture<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn, Executor executor) {
        return wrap(this.name + ".handleAsync", super.handleAsync(biWrap(fn), executor));
    }

    public <U> XFuture<U> handleAsync(String name, BiFunction<? super T, Throwable, ? extends U> fn, Executor executor) {
        return wrap(name, super.handleAsync(biWrap(fn), executor));
    }

    @Override
    public <U> XFuture<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn) {
        return wrap(this.name + ".handleAsync", super.handleAsync(biWrap(fn)));
    }

    public <U> XFuture<U> handleAsync(String name, BiFunction<? super T, Throwable, ? extends U> fn) {
        return wrap(name, super.handleAsync(biWrap(fn)));
    }

    @Override
    public <U> XFuture<U> handle(BiFunction<? super T, Throwable, ? extends U> fn) {
        return wrap(this.name + ".handle", super.handle(biWrap(fn)));
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

    protected void alsoCancelAdd(CompletableFuture<?> cf) {
        if (cf instanceof XFuture) {
            XFuture xf = (XFuture) cf;
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
        return wrapvoid(this.name + ".thenRunAsync", super.thenRunAsync(runWrap(action), executor));
    }

    @Override
    public XFutureVoid thenRunAsync(Runnable action) {
        return wrapvoid(this.name + ".thenRunAsync", super.thenRunAsync(runWrap(action), getDefaultThreadPool()));
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
                Logger.getLogger(XFuture.class.getName()).log(Level.SEVERE, null, t);
                if (t instanceof RuntimeException) {
                    throw ((RuntimeException) t);
                } else {
                    throw new RuntimeException(t);
                }
            }
        };
    }

    @Override
    public XFutureVoid thenRun(Runnable action) {
        return wrapvoid(this.name + ".thenRun", super.thenRun(runWrap(action)));
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

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        TestClass.main(args);
    }
}

/**
 *
 * @author will
 */
class TestClass {

    static XFuture<Void> startT1() {
        XFuture<Void> f
                = XFuture.runAsync("startT1", () -> {
                    try {
                        int count = 0;
                        System.out.println("T1 started");
                        Thread.sleep(2000);
                        System.out.println("T1 finished");
                        System.out.println(new Date());
                    } catch (InterruptedException interruptedException) {
                    }
                });
        return f;
    }

    static XFuture<Void> startT2() {
        XFuture<Void> f = new XFuture<>("startT2");
        new Thread(() -> {
            try {
                int count = 0;
                System.out.println("T2 started");

                while (!f.isCancelled() && ++count < 50) {
                    Thread.sleep(100);
                }
                System.out.println("T2 count=" + count);
                System.out.println(new Date());
            } catch (InterruptedException interruptedException) {
            } finally {
                f.complete(null);
            }
        }).start();
        return f;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        AtomicReference<Thread> at = new AtomicReference<>();
        ExecutorService es = Executors.newCachedThreadPool();
        XFuture<Void> f = startT1().thenCompose(x -> startT2()).thenCompose(x -> startT1());
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(XFuture.class.getName()).log(Level.SEVERE, null, ex);
        }
        f.cancelAll(true);
        System.out.println("f.isCancelled() = " + f.isCancelled());
        System.out.println(new Date());

    }

}
