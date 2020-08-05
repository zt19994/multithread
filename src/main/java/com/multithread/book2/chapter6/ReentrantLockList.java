package com.multithread.book2.chapter6;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock实现线程安全的list
 *
 * 加锁保证同一时刻只有一个线程可以对array进行修改和访问
 *
 * @author zt1994 2020/8/5 12:20
 */
public class ReentrantLockList {

    private ArrayList<String> array = new ArrayList<>();

    private volatile ReentrantLock lock = new ReentrantLock();

    /**
     * 添加元素
     *
     * @param str
     */
    public void add(String str) {
        lock.lock();
        try {
            array.add(str);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 删除元素
     *
     * @param str
     */
    public void remove(String str) {
        lock.lock();
        try {
            array.remove(str);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取指定数据
     *
     * @param index
     * @return
     */
    public String get(int index) {
        lock.lock();
        try {
            return array.get(index);
        } finally {
            lock.unlock();
        }
    }

}
