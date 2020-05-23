package com.multithread.chapter17;

/**
 * 写入锁
 *
 * @author zt1994 2020/5/22 22:16
 */
public class WriteLock implements Lock {

    private final ReadWriteLockImpl readWriteLock;

    public WriteLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMUTEX()) {
            try {
                // 等待获取写入锁加一
                readWriteLock.incrementWaitingWriters();
                // 如果此时有其他线程正在被读写，则当前线程被挂起
                while (readWriteLock.getReadingReaders() > 0 || readWriteLock.getWritingWriters() > 0) {
                    readWriteLock.getMUTEX().wait();
                }
            } finally {
                // 成功获取写入锁，是等待获取写入锁的计数器减一
                this.readWriteLock.decrementWaitingWriters();
            }
            // 正在写入的线程数量加一
            readWriteLock.incrementWritingWriters();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMUTEX()) {
            // 减少正在写入的线程计数器
            readWriteLock.decrementWritingWriters();
            // 将读写偏好设为false，可以是读锁被最快获取到
            readWriteLock.changePrefer(false);
            // 通知唤醒其他Mutex monitor wait set 中的线程
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
