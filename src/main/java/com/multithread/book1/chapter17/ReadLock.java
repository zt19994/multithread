package com.multithread.book1.chapter17;

/**
 * 读写锁
 *
 * @author zt1994 2020/5/22 22:11
 */
public class ReadLock implements Lock {

    private final ReadWriteLockImpl readWriteLock;

    public ReadLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMUTEX()) {
            // 线程进入写操作时，或者写操作在等待并且偏向写锁的标识为true时，会无法获取读锁，只能被挂起
            while (readWriteLock.getWritingWriters() > 0 ||
                    (readWriteLock.getPreferWriter() && readWriteLock.getWaitingWriters() > 0)) {
                readWriteLock.getMUTEX().wait();
            }
            // 成功获取读入锁，并使readingReaders数量增加
            readWriteLock.incrementReadingReaders();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMUTEX()) {
            // 释放锁的过程就是使当前reading的数量减一
            readWriteLock.decrementReadingReaders();
            // 将偏向设为true，write的机会更多
            readWriteLock.changePrefer(true);
            // 通知唤醒与Mutex关联monitor wait set中的线程
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
