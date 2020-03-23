package com.multithread.chapter05.booleanlock;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * lock 接口
 *
 * @author zt1994 2020/3/23 20:57
 */
public interface Lock {

    /**
     * 锁住线程
     *
     * @throws InterruptedException
     */
    void lock() throws InterruptedException;

    /**
     * 带时间的锁
     *
     * @param mills
     * @throws InterruptedException
     * @throws TimeoutException
     */
    void lock(long mills) throws InterruptedException, TimeoutException;

    /**
     * 解锁
     */
    void unlock();

    /**
     * 获取阻塞线程列表
     *
     * @return
     */
    List<Thread> getBlockedThreads();
}
