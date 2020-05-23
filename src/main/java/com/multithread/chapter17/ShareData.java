package com.multithread.chapter17;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 测试读写锁
 *
 * @author zt1994 2020/5/23 17:47
 */
public class ShareData {

    /**
     * 定义共享数据
     */
    private final List<Character> container = new ArrayList<>();

    /**
     * 构造readWriteLock
     */
    private final ReadWriteLock readWriteLock = ReadWriteLock.readWriteLock();

    /**
     * 创建读取锁
     */
    private final Lock readLock = readWriteLock.readLock();

    /**
     * 创建写入锁
     */
    private final Lock writeLock = readWriteLock.writeLock();

    private final int length;

    public ShareData(int length) {
        this.length = length;
        for (int i = 0; i < length; i++) {
            container.add(i, 'c');
        }
    }

    public char[] read() throws InterruptedException {
        try {
            // 使用读锁进行lock
            readLock.lock();
            char[] newBuffer = new char[length];
            for (int i = 0; i < length; i++) {
                newBuffer[i] = container.get(i);
            }
            slowly();
            return newBuffer;
        } finally {
            // 操作结束释放锁
            readLock.lock();
        }
    }

    public void write(char c) throws InterruptedException {
        try {
            // 使用写锁进行lock
            writeLock.lock();
            for (int i = 0; i < length; i++) {
                this.container.add(i, 'c');
            }
            slowly();
        } finally {
            // 操作完后，对写锁进行释放
            writeLock.unlock();
        }
    }

    /**
     * 简单模拟操作的耗时
     */
    private void slowly() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
