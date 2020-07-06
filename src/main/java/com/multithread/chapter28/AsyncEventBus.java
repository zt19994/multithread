package com.multithread.chapter28;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步bus
 *
 * @author zt1994 2020/7/6 20:40
 */
public class AsyncEventBus extends EventBus {

    public AsyncEventBus(String busName, EventExceptionHandler exceptionHandler, ThreadPoolExecutor executor) {
        super(busName, exceptionHandler, executor);
    }

    public AsyncEventBus(String busName, ThreadPoolExecutor executor) {
        this(busName, null, executor);
    }

    public AsyncEventBus(ThreadPoolExecutor executor) {
        this("default-async", null, executor);
    }

    public AsyncEventBus(EventExceptionHandler exceptionHandler, ThreadPoolExecutor executor) {
        this("default-async", exceptionHandler, executor);
    }
}
