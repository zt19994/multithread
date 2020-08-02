package com.multithread.book1.chapter29;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步处理通道
 *
 * @author zt1994 2020/7/8 20:52
 */
public abstract class AsyncChannel implements Channel<Event> {

    /**
     * 使用多线程方式提交message
     */
    private final ExecutorService executorService;

    public AsyncChannel() {
        this(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2));
    }

    public AsyncChannel(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void dispatch(Event message) {
        executorService.submit(() -> this.handle(message));
    }

    /**
     * 提供抽象方法，供子类实现具体的Message处理
     *
     * @param message
     */
    protected abstract void handle(Event message);

    /**
     * 关闭ExecutorService方法
     */
    void stop() {
        if (null != executorService && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}
