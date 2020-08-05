package com.multithread.book2.chapter6;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantLockReadWrite实现线程安全的list
 *
 * 加锁保证同一时刻只有一个线程可以对array进行修改和访问
 *
 * @author zt1994 2020/8/5 12:20
 */
public class ReentrantLockReadWriteList {

    private ArrayList<String> array = new ArrayList<>();

    private volatile ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    /**
     * 添加元素
     *
     * @param str
     */
    public void add(String str) {
        writeLock.lock();
        try {
            array.add(str);
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * 删除元素
     *
     * @param str
     */
    public void remove(String str) {
        writeLock.lock();
        try {
            array.remove(str);
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * 获取指定数据
     *
     * @param index
     * @return
     */
    public String get(int index) {
        readLock.lock();
        try {
            return array.get(index);
        } finally {
            readLock.unlock();
        }
    }

}
