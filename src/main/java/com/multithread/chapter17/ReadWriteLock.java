package com.multithread.chapter17;

/**
 * 读写锁
 *
 * @author zt1994 2020/5/22 21:54
 */
public interface ReadWriteLock {

    /**
     * 创建reader锁
     *
     * @return
     */
    Lock readLock();

    /**
     * 创建write锁
     *
     * @return
     */
    Lock writeLock();

    /**
     * 获取当前有多少写线程
     *
     * @return
     */
    int getWritingWriters();

    /**
     * 获取当前有多少线程正在等待获取写入锁
     *
     * @return
     */
    int getWaitingWriters();

    /**
     * 获取当前有多少reader线程
     *
     * @return
     */
    int getReadingReaders();

    static ReadWriteLock readWriteLock() {
        return new ReadWriteLockImpl();
    }

    static ReadWriteLock readWriteLock(boolean preferWriter) {
        return new ReadWriteLockImpl(preferWriter);
    }
}
