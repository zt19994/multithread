package com.multithread.chapter19;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * FutureService实现类  异步submit
 *
 * @author zt1994 2020/5/25 21:27
 */
public class FutureServiceImpl<IN, OUT> implements FutureService<IN, OUT> {

    /**
     * 为线程起名 前缀
     */
    private final static String FUTURE_THREAD_PREFIX = "FUTURE-";

    private final AtomicInteger nextCounter = new AtomicInteger(0);

    private String getNextName() {
        return FUTURE_THREAD_PREFIX + nextCounter.getAndIncrement();
    }

    @Override
    public Future<?> submit(Runnable runnable) {
        final FutureTask<Void> future = new FutureTask<>();
        new Thread(() -> {
            runnable.run();
            // 任务执行结束之后将null作为结果传给future
            future.finish(null);
        }, getNextName()).start();
        return null;
    }

    @Override
    public Future submit(Task<IN, OUT> task, IN input) {
        final FutureTask<OUT> future = new FutureTask<>();
        new Thread(() -> {
            OUT result = task.get(input);
            // 任务执行结束之后，将真实的结果通过finish方法传递给future
            future.finish(result);
        }, getNextName()).start();
        return future;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input, CallBack<OUT> callBack) {
        final FutureTask<OUT> future = new FutureTask<>();
        new Thread(() -> {
            OUT result = task.get(input);
            // 任务执行结束之后，将真实的结果通过finish方法传递给future
            future.finish(result);
            callBack.call(result);
        }, getNextName()).start();
        return future;
    }
}
