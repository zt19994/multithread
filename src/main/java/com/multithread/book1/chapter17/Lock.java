package com.multithread.book1.chapter17;

/**
 * 锁接口
 *
 * @author zt1994 2020/5/22 21:51
 */
public interface Lock {

    /**
     * 获取显式锁，没有获取锁的线程将被阻塞
     * @throws InterruptedException
     */
    void lock() throws InterruptedException;

    /**
     * 释放获取的锁
     */
    void unlock();
}
