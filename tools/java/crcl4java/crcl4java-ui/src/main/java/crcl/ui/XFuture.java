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

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
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

public class XFuture<T> extends CompletableFuture<T> {

    private volatile Future<T> futureFromExecSubmit = null;
    private volatile Thread threadToInterrupt = null;
    private volatile Runnable onCancelAllRunnable = null;

    private final String name;
    private final long startTime;
    private final int num;
    private volatile long completeTime = -1;
    private final Thread createThread;
    private final StackTraceElement createTrace[];

    static private final AtomicInteger numCounter = new AtomicInteger();
    
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
        ps.println("num,start_time,end_time,time_diff,name,exception,cancel,done,trace");
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

    public String getProfileString() {
        setCompleteTime();
        if(completeTime < 0 && isDone() ) {
            System.err.println("wtf");
        }
        return joinAny(",", num,startTime, completeTime, (completeTime - startTime),"\""+ name+"\"", isCompletedExceptionally(), isCancelled(), isDone(),"\""+ createTraceToString(createTrace)+"\"");
    }

    private volatile List<String> prevProfileStrings = null;

    @SuppressWarnings("unchecked")
    private void getAllProfileString(List<String> listIn) {
        for (CompletableFuture f : alsoCancel) {
            if (f instanceof XFuture) {
                XFuture xf = (XFuture) f;
                xf.getAllProfileString(listIn);
            }
        }
        List<String> localPrevProfileStrings = this.prevProfileStrings;
        if(null != localPrevProfileStrings) {
            listIn.addAll(localPrevProfileStrings);
        } else {
            listIn.add(getProfileString());
        }
    }

    @SuppressWarnings("unchecked")
    private void internalPrintProfile(PrintStream ps) {
        for (CompletableFuture f : alsoCancel) {
            if (f instanceof XFuture) {
                XFuture xf = (XFuture) f;
                xf.internalPrintProfile(ps);
            }
        }
        //ps.println("start_time,end_time,time_diff,name,exception,cancel,done");
        List<String> localPrevPStrings = this.prevProfileStrings;
        if (null != localPrevPStrings) {
            for (String profileString : localPrevPStrings) {
                ps.println(profileString);
            }
        }
        ps.println(getProfileString());
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

    public Runnable getOnCancelAllRunnable() {
        return onCancelAllRunnable;
    }

    public void setOnCancelAllRunnable(Runnable onCancelAllRunnable) {
        this.onCancelAllRunnable = onCancelAllRunnable;
    }

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
                return newThraed;
            }
        };
        public static final ExecutorService DEFAULT_EXECUTOR_SERVICE = Executors.newCachedThreadPool(THREAD_FACTORY);
    }

    public static ExecutorService getDefaultThreadPool() {
        return Hider.DEFAULT_EXECUTOR_SERVICE;
    }

    public static XFuture<Void> allOfWithName(String name, CompletableFuture<?>... cfs) {
        CompletableFuture<Void> orig = CompletableFuture.allOf(cfs);
        XFuture<Void> ret = staticwrap(name, orig);
        ret.alsoCancel.addAll(Arrays.asList(cfs));
        return ret;
    }

    public static XFuture<Object> anyOfWithName(String name, CompletableFuture<?>... cfs) {
        CompletableFuture<Object> orig = CompletableFuture.anyOf(cfs);
        XFuture<Object> ret = staticwrap(name, orig);
        ret.alsoCancel.addAll(Arrays.asList(cfs));
        return ret;
    }

    public static XFuture<Void> allOf(CompletableFuture<?>... cfs) {
        CompletableFuture<Void> orig = CompletableFuture.allOf(cfs);
        XFuture<Void> ret = staticwrap("allOfWithName", orig);
        ret.alsoCancel.addAll(Arrays.asList(cfs));
        return ret;
    }

    public static XFuture<Object> anyOf(CompletableFuture<?>... cfs) {
        CompletableFuture<Object> orig = CompletableFuture.anyOf(cfs);
        XFuture<Object> ret = staticwrap("anyOfWithName", orig);
        ret.alsoCancel.addAll(Arrays.asList(cfs));
        return ret;
    }

    private final ConcurrentLinkedDeque<CompletableFuture<?>> alsoCancel = new ConcurrentLinkedDeque<>();

    public static <T> XFuture<T> supplyAsync(String name, Callable<T> c, ExecutorService es) {
        XFuture<T> myf = new XFuture<>(name);
        Future<T> f = es.submit(() -> {
            try {
                String tname = Thread.currentThread().getName();
                int cindex = tname.indexOf(':');
                String tname_sub = tname;
                if (cindex > 0) {
                    tname_sub = tname.substring(0, cindex);
                }
                Thread.currentThread().setName(tname_sub + ":" + name);
                T result = c.call();
                myf.complete(result);
//                myf.alsoCancel.clear();
                Thread.currentThread().setName(tname);
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

    @SuppressWarnings("unchecked")
    public static XFuture<Void> runAsync(String name, Runnable r, ExecutorService es) {
        XFuture<Void> myf = new XFuture<>(name);
        Future<?> f = es.submit(() -> {
            try {
                String tname = Thread.currentThread().getName();
                int cindex = tname.indexOf(':');
                String tname_sub = tname;
                if (cindex > 0) {
                    tname_sub = tname.substring(0, cindex);
                }
                Thread.currentThread().setName(tname_sub + ":" + name);
                r.run();
                Thread.currentThread().setName(tname);
            } catch (Throwable throwable) {
                myf.completeExceptionally(throwable);
                Logger.getLogger(XFuture.class.getName()).log(Level.SEVERE, null, throwable);
                throw new RuntimeException(throwable);
            }
            myf.complete(null);
//            myf.alsoCancel.clear();
        });
        myf.futureFromExecSubmit = ((Future<Void>) f);
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

    public static XFuture<Void> runAsync(String name, Runnable r) {
        return runAsync(name, r, getDefaultThreadPool());
    }

    public static <T> XFuture<T> supplyAsync(String name, Callable<T> c) {
        return supplyAsync(name, c, getDefaultThreadPool());
    }

    private static void setTName(String name) {
        String tname = Thread.currentThread().getName();
        int cindex = tname.indexOf(':');
        String tname_sub = tname;
        if (cindex > 0) {
            tname_sub = tname.substring(0, cindex);
        }
        Thread.currentThread().setName(tname_sub + ":" + name);
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

    private static volatile boolean keepOldProfileStrings;

    public static boolean getKeepOldProfileStrings() {
        return keepOldProfileStrings;
    }

    public static void setKeepOldProfileStrings(boolean newKeepOldProfileStrings) {
        keepOldProfileStrings = newKeepOldProfileStrings;
    }

    @Override
    public boolean complete(T value) {
        boolean ret = super.complete(value);
        if (keepOldProfileStrings) {
            List<String> l = new ArrayList<>();
            getAllProfileString(l);
            this.prevProfileStrings = l;
        }
        this.alsoCancel.clear();
        setCompleteTime();
        return ret;
    }

    private void setCompleteTime() {
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
        this.alsoCancel.clear();
        setCompleteTime();
        return ret;
    }

    @Override
    public boolean completeExceptionally(Throwable ex) {
        boolean ret = super.completeExceptionally(ex);
        this.alsoCancel.clear();
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
        CompletableFuture<U> f = super.thenApply(fname(fn, name))
                .thenCompose((CompletionStage<U> stage) -> {
                    if (stage instanceof CompletableFuture) {
                        myF.alsoCancel.add((CompletableFuture) stage);
                    } else {
                        myF.alsoCancel.add(stage.toCompletableFuture());
                    }
                    return stage;
                });
        myF.alsoCancel.add(f);
        myF.alsoCancel.add(f.thenAccept(x -> myF.complete(x)));
        myF.alsoCancel.add(this);
        return myF;
    }

    @Override
    public <U> XFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn, Executor executor) {
        return this.thenComposeAsync(name + ".thenComposeAsync", fn, executor);
    }

    public <U> XFuture<U> thenComposeAsync(String name, Function<? super T, ? extends CompletionStage<U>> fn, Executor executor) {
        XFuture<U> myF = new XFuture<>(name);
        CompletableFuture<U> f = super.thenApplyAsync(fname(fn, name), executor)
                .thenCompose((CompletionStage<U> stage) -> {
                    if (stage instanceof CompletableFuture) {
                        myF.alsoCancel.add((CompletableFuture) stage);
                    } else {
                        myF.alsoCancel.add(stage.toCompletableFuture());
                    }
                    return stage;
                });
        myF.alsoCancel.add(f);
        myF.alsoCancel.add(f.thenAccept(x -> myF.complete(x)));
        myF.alsoCancel.add(this);
        return myF;
    }

    @Override
    public <U> XFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn) {
        return this.thenComposeAsync(name + ".thenComposeAsync", fn);
    }

    public <U> XFuture<U> thenComposeAsync(String name, Function<? super T, ? extends CompletionStage<U>> fn) {
        XFuture<U> myF = new XFuture<>(name);
        CompletableFuture<U> f = super.thenApplyAsync(fname(fn, name), getDefaultThreadPool())
                .thenCompose((CompletionStage<U> stage) -> {
                    if (stage instanceof CompletableFuture) {
                        myF.alsoCancel.add((CompletableFuture) stage);
                    } else {
                        myF.alsoCancel.add(stage.toCompletableFuture());
                    }
                    return stage;
                });
        myF.alsoCancel.add(f);
        myF.alsoCancel.add(f.thenAccept(x -> myF.complete(x)));
        myF.alsoCancel.add(this);
        return myF;
    }

    private volatile Thread cancelThread = null;
    private volatile long cancelTime = -1;
    private volatile StackTraceElement cancelStack[] = null;

    public StackTraceElement[] getCancelStack() {
        return cancelStack;
    }

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
        this.alsoCancel.clear();
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
//                    XFuture.this.alsoCancel.clear();
//                    newFuture.alsoCancel.clear();
                });
        newFuture.alsoCancel.add(future);
        return newFuture;
    }

    public <T> XFuture<T> wrap(String name, CompletableFuture<T> future) {
        if (future instanceof XFuture) {
            if (this != future) {
                ((XFuture<T>) future).alsoCancel.add(this);
            }
            return (XFuture<T>) future;
        }
        XFuture<T> newFuture = new XFuture<>(name);
        future.handle(newFuture::logException)
                .thenAccept(x -> {
                    newFuture.complete(x);
//                    XFuture.this.alsoCancel.clear();
//                    newFuture.alsoCancel.clear();
                });
        newFuture.alsoCancel.add(this);
        newFuture.alsoCancel.add(future);
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

    private volatile Throwable throwable = null;

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
    public XFuture<T> exceptionally(Function<Throwable, ? extends T> fn) {
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
    public XFuture<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action, Executor executor) {
        return wrap(this.name + ".runAfterEitherAsync", super.runAfterEitherAsync(other, action, executor));
    }

    @Override
    public XFuture<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action) {
        return wrap(this.name + ".runAfterEitherAsync", super.runAfterEitherAsync(other, action, getDefaultThreadPool()));
    }

    @Override
    public XFuture<Void> runAfterEither(CompletionStage<?> other, Runnable action) {
        return wrap(this.name + ".runAfterEither", super.runAfterEither(other, action));
    }

    @Override
    public XFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action, Executor executor) {
        return wrap(this.name + ".acceptEitherAsync", super.acceptEitherAsync(other, action, executor));
    }

    @Override
    public XFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action) {
        return wrap(this.name + ".acceptEitherAsync", super.acceptEitherAsync(other, action, getDefaultThreadPool()));
    }

    @Override
    public XFuture<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action) {
        return wrap(this.name + ".acceptEither", super.acceptEither(other, action));
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
    public XFuture<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action, Executor executor) {
        return wrap(this.name + ".runAfterBothAsync", super.runAfterBothAsync(other, action, executor));
    }

    @Override
    public XFuture<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action) {
        return wrap(this.name + ".runAfterBothAsync", super.runAfterBothAsync(other, action, getDefaultThreadPool()));
    }

    @Override
    public XFuture<Void> runAfterBoth(CompletionStage<?> other, Runnable action) {
        return wrap(this.name + ".runAfterBoth", super.runAfterBoth(other, action));
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

    @Override
    public <U, V> XFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn, Executor executor) {
        XFuture<V> ret = wrap(this.name + ".thenCombineAsync", super.thenCombineAsync(other, fn, executor));
        if (other instanceof CompletableFuture) {
            ret.alsoCancel.add((CompletableFuture) other);
        }
        return ret;
    }

    @Override
    public <U, V> XFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn) {
        XFuture<V> ret = wrap(this.name + ".thenCombineAsync", super.thenCombineAsync(other, fn, getDefaultThreadPool()));
        if (other instanceof CompletableFuture) {
            ret.alsoCancel.add((CompletableFuture) other);
        }
        return ret;
    }

    @Override
    public <U, V> XFuture<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn) {
        XFuture<V> ret = wrap(this.name + ".thenCombine", super.thenCombine(other, fn));
        if (other instanceof CompletableFuture) {
            ret.alsoCancel.add((CompletableFuture) other);
        }
        return ret;
    }

    public <U, V> XFuture<V> thenCombine(String name, CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn) {
        XFuture<V> ret = wrap(name, super.thenCombine(other, fn));
        if (other instanceof CompletableFuture) {
            ret.alsoCancel.add((CompletableFuture) other);
        }
        return ret;
    }

    public XFuture<Void> thenRunAsync(String name, Runnable action, Executor executor) {
        return wrap(name, super.thenRunAsync(runWrap(action), executor));
    }

    @Override
    public XFuture<Void> thenRunAsync(Runnable action, Executor executor) {
        return wrap(this.name + ".thenRunAsync", super.thenRunAsync(runWrap(action), executor));
    }

    @Override
    public XFuture<Void> thenRunAsync(Runnable action) {
        return wrap(this.name + ".thenRunAsync", super.thenRunAsync(runWrap(action), getDefaultThreadPool()));
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
    public XFuture<Void> thenRun(Runnable action) {
        return wrap(this.name + ".thenRun", super.thenRun(runWrap(action)));
    }

    public XFuture<Void> thenRun(String name, Runnable action) {
        return wrap(name, super.thenRun(runWrap(action)));
    }

    @Override
    public XFuture<Void> thenAcceptAsync(Consumer<? super T> action, Executor executor) {
        return wrap(this.name + ".thenAcceptAsync", super.thenAcceptAsync(action, executor));
    }

    @Override
    public XFuture<Void> thenAcceptAsync(Consumer<? super T> action) {
        return wrap(this.name + ".thenAcceptAsync", super.thenAcceptAsync(action, getDefaultThreadPool()));
    }

    @Override
    public XFuture<Void> thenAccept(Consumer<? super T> action) {
        return wrap(this.name + ".thenAccept", super.thenAccept(action));
    }

    @Override
    public <U> XFuture<U> thenApplyAsync(Function<? super T, ? extends U> fn, Executor executor) {
        return wrap(this.name + ".thenApplyAsync", super.thenApplyAsync(fn, executor));
    }

    public <U> XFuture<U> thenApplyAsync(String name, Function<? super T, ? extends U> fn, Executor executor) {
        return wrap(name, super.thenApplyAsync(fn, executor));
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
