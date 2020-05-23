package com.multithread.chapter17;

/**
 * @author zt1994 2020/5/22 22:05
 */
public class ReadWriteLockImpl implements ReadWriteLock {

    /**
     * 定义对象锁
     */
    private final Object MUTEX = new Object();

    /**
     * 当前有多少线程正在写入
     */
    private int writingWriters = 0;

    /**
     * 当前有多少线程正在等待写入
     */
    private int waitingWriters = 0;

    /**
     * 当前有多少线程在读取
     */
    private int readingReaders = 0;

    /**
     * 设置读写偏好
     */
    private boolean preferWriter;

    public ReadWriteLockImpl() {
        this(true);
    }

    public ReadWriteLockImpl(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    @Override
    public Lock readLock() {
        return new ReadLock(this);
    }

    @Override
    public Lock writeLock() {
        return new WriteLock(this);
    }

    /**
     * 增加写线程
     */
    void incrementWritingWriters() {
        this.writingWriters++;
    }

    /**
     * 增加等待的锁
     */
    void incrementWaitingWriters() {
        this.waitingWriters++;
    }

    /**
     * 增加读取的锁
     */
    void incrementReadingReaders() {
        this.readingReaders++;
    }

    /**
     * 减少写线程
     */
    void decrementWritingWriters() {
        this.writingWriters--;
    }

    /**
     * 减少等待的锁
     */
    void decrementWaitingWriters() {
        this.waitingWriters--;
    }

    /**
     * 减少读取的锁
     */
    void decrementReadingReaders() {
        this.readingReaders--;
    }

    @Override
    public int getWritingWriters() {
        return this.writingWriters;
    }

    @Override
    public int getWaitingWriters() {
        return this.waitingWriters;
    }

    @Override
    public int getReadingReaders() {
        return this.readingReaders;
    }

    Object getMUTEX() {
        return this.MUTEX;
    }

    boolean getPreferWriter() {
        return this.preferWriter;
    }

    /**
     * 修改偏好
     *
     * @param preferWriter
     */
    void changePrefer(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }
}
