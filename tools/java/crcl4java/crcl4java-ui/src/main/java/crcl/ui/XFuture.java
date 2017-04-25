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
import java.util.Arrays;
import java.util.Date;
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

public class XFuture<T> extends CompletableFuture<T> {

    private volatile Future<T> futureFromExecSubmit = null;
    private volatile Thread threadToInterrupt = null;
    private volatile Runnable onCancelAllRunnable = null;

    private final String name;

    public XFuture(String name) {
        this.name = name;
    }

    public void printStatus(PrintStream ps) {
        ps.println();
        ps.println("Status for " + this.toString());
        internalPrintStatus(ps);
        
        if (isCompletedExceptionally()) {
            this.exceptionally(t -> t.printStackTrace(ps));
            return;
        } 
        ps.println();
    }

    private void internalPrintStatus(PrintStream ps) {

        if (isCompletedExceptionally()) {
            ps.println(this.toString() + " is CompletedExceptionally.");
            this.exceptionally(t -> t.printStackTrace(ps));
            return;
        } if (isCancelled()) {
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
        for (CompletableFuture f : alsoCancel) {
            if (!(f instanceof XFuture)) {
                ps.println("done=" + isDone() + "\tcancelled=" + isCancelled() + "\t" + f.toString());
            }
        }
        ps.println("done=" + isDone() + "\tcancelled=" + isCancelled() + "\t" + this.toString());
    }

    @Override
    public String toString() {
        return super.toString() + "{name=" + name + "}";
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
                Thread newThraed = new Thread(r);
                newThraed.setName("XFutureThread_" + count.incrementAndGet());
                return newThraed;
            }
        };
        public static final ExecutorService DEFAULT_EXECUTOR_SERVICE = Executors.newCachedThreadPool(THREAD_FACTORY);
    }

    public static ExecutorService getDefaultThreadPool() {
        return Hider.DEFAULT_EXECUTOR_SERVICE;
    }

    public static XFuture<Void> allOf(String name, CompletableFuture<?>... cfs) {
        XFuture<Void> ret = new XFuture<>(name);
        CompletableFuture<Void> orig = CompletableFuture.allOf(cfs);
        ret.alsoCancel.addAll(Arrays.asList(cfs));
        return ret.wrap(name, orig);
    }

    public static XFuture<Object> anyOf(String name, CompletableFuture<?>... cfs) {
        XFuture<Object> ret = new XFuture<>(name);
        CompletableFuture<Object> orig = CompletableFuture.anyOf(cfs);
        ret.alsoCancel.addAll(Arrays.asList(cfs));
        return ret.wrap(name, orig);
    }

    private final ConcurrentLinkedDeque<CompletableFuture> alsoCancel = new ConcurrentLinkedDeque<>();

    public static <T> XFuture<T> supplyAsync(String name, Callable<T> c, ExecutorService es) {
        XFuture<T> myf = new XFuture<>(name);
        Future<T> f = es.submit(() -> {
            try {
                String tname = Thread.currentThread().getName();
                Thread.currentThread().setName(name);
                T result = c.call();
                myf.complete(result);
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
                Thread.currentThread().setName(name);
                r.run();
                Thread.currentThread().setName(tname);
            } catch (Throwable throwable) {
                myf.completeExceptionally(throwable);
                Logger.getLogger(XFuture.class.getName()).log(Level.SEVERE, null, throwable);
                throw new RuntimeException(throwable);
            }
            myf.complete(null);
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

    @Override
    public <U> XFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn) {
        return this.thenCompose(name + ".thenCompose", fn);
    }

    public <U> XFuture<U> thenCompose(String name, Function<? super T, ? extends CompletionStage<U>> fn) {

        XFuture<U> myF = new XFuture<>(name);
        CompletableFuture<U> f = super.thenApply(fn)
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
        CompletableFuture<U> f = super.thenApplyAsync(fn, executor)
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
        CompletableFuture<U> f = super.thenApplyAsync(fn, getDefaultThreadPool())
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

    public void cancelAll(boolean mayInterrupt) {
        try {

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
            if (mayInterrupt && null != threadToInterrupt && Thread.currentThread() == threadToInterrupt) {
                threadToInterrupt.interrupt();
            }

            for (CompletableFuture f : alsoCancel) {
                if (null != f && f != this && !f.isCancelled() && !f.isDone() && !f.isCompletedExceptionally()) {
                    try {
                        f.cancel(false);
                    } catch (Exception e) {
                        System.err.println("Cancel all ignoring " + e.toString());
                    }
                    if (f instanceof XFuture) {
                        ((XFuture) f).cancelAll(mayInterrupt);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Cancel all ignoring " + e.toString());
        }
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
                    }
                    if (isCancellationException && !printCancellationExceptions) {
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
                });
        newFuture.alsoCancel.add(this);
        newFuture.alsoCancel.add(future);
        return newFuture;
    }

    @Override
    public XFuture<T> exceptionally(Function<Throwable, ? extends T> fn) {
        return wrap(this.name + ".exceptionally", super.exceptionally(fn));
    }

    public XFuture<T> exceptionally(String name, Function<Throwable, ? extends T> fn) {
        return wrap(name, super.exceptionally(fn));
    }

    @Override
    public <U> XFuture<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn, Executor executor) {
        return wrap(this.name + ".handleAsync", super.handleAsync(fn, executor));
    }

    public <U> XFuture<U> handleAsync(String name, BiFunction<? super T, Throwable, ? extends U> fn, Executor executor) {
        return wrap(name, super.handleAsync(fn, executor));
    }

    @Override
    public <U> XFuture<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn) {
        return wrap(this.name + ".handleAsync", super.handleAsync(fn));
    }

    public <U> XFuture<U> handleAsync(String name, BiFunction<? super T, Throwable, ? extends U> fn) {
        return wrap(name, super.handleAsync(fn));
    }

    @Override
    public <U> XFuture<U> handle(BiFunction<? super T, Throwable, ? extends U> fn) {
        return wrap(this.name + ".handle", super.handle(fn));
    }

    public <U> XFuture<U> handle(String name, BiFunction<? super T, Throwable, ? extends U> fn) {
        return wrap(name, super.handle(fn));
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
        return wrap(this.name + ".thenCombineAsync", super.thenCombineAsync(other, fn, executor));
    }

    @Override
    public <U, V> XFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn) {
        return wrap(this.name + ".thenCombineAsync", super.thenCombineAsync(other, fn, getDefaultThreadPool()));
    }

    @Override
    public <U, V> XFuture<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn) {
        return wrap(this.name + ".thenCombine", super.thenCombine(other, fn));
    }

    @Override
    public XFuture<Void> thenRunAsync(Runnable action, Executor executor) {
        return wrap(this.name + ".thenRunAsync", super.thenRunAsync(action, executor));
    }

    @Override
    public XFuture<Void> thenRunAsync(Runnable action) {
        return wrap(this.name + ".thenRunAsync", super.thenRunAsync(action, getDefaultThreadPool()));
    }

    @Override
    public XFuture<Void> thenRun(Runnable action) {
        return wrap(this.name + ".thenRun", super.thenRun(action));
    }

    public XFuture<Void> thenRun(String name, Runnable action) {
        return wrap(name, super.thenRun(action));
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

    @Override
    public <U> XFuture<U> thenApplyAsync(Function<? super T, ? extends U> fn) {
        return wrap(this.name + ".thenApplyAsync", super.thenApplyAsync(fn, getDefaultThreadPool()));
    }

    @Override
    public <U> XFuture<U> thenApply(Function<? super T, ? extends U> fn) {
        return wrap(this.name + ".thenApply", super.thenApply(fn));
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

//        CompletableFuture cf  = new CompletableFuture();
//        System.out.println("cf = " + cf);
//        cf.complete(null);
//        System.out.println("cf = " + cf);
//        
//        XFuture xf = new XFuture("xf");
//        System.out.println("xf = " + xf);
//        xf.complete(null);
//        System.out.println("xf = " + xf);
        AtomicReference<Thread> at = new AtomicReference<>();
        ExecutorService es = Executors.newCachedThreadPool();
        XFuture<Void> f = startT1().thenCompose(x -> startT2()).thenCompose(x -> startT1());
//        CompletableFuture f = CompletableFuture.runAsync(() -> {
//            System.out.println("Step 1 started");
//            try {
//                Thread t = Thread.currentThread();
//                at.set(t);
//                System.out.println("t = " + t);
//                boolean isInterrupted = t.isInterrupted();
//                System.out.println("isInterrupted = " + isInterrupted);
//                Thread.sleep(5000);
//                System.out.println(new Date());
//                System.out.println("Step 1 ended");
//            } catch (InterruptedException ex) {
//                Logger.getLogger(JavaApplication2.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }, es);//.thenRunAsync(() -> {
//            System.out.println("Step 2 started");
//            try {
//                Thread.sleep(5000);
//                System.out.println("Step 2 ended");
//            } catch (InterruptedException ex) {
//                Logger.getLogger(JavaApplication2.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//        });
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(XFuture.class.getName()).log(Level.SEVERE, null, ex);
        }
        f.cancelAll(true);
//        f.get();
        System.out.println("f.isCancelled() = " + f.isCancelled());
        System.out.println(new Date());
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(JavaApplication2.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        if (null != at.get()) {
//            at.get().interrupt();
//            System.out.println("Interrupting = " + f.isCancelled());
//            System.out.println(new Date());
//        }

    }

}
