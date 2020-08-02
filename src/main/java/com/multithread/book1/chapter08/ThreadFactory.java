package com.multithread.book1.chapter08;

/**
 * 线程工厂 用于创建线程
 *
 * @author zt1994 2020/4/8 21:34
 */
@FunctionalInterface
public interface ThreadFactory {

    /**
     * 创建线程
     *
     * @param runnable
     * @return
     */
    Thread createThread(Runnable runnable);
}
