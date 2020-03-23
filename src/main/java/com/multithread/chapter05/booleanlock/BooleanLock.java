package com.multithread.chapter05.booleanlock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static java.lang.System.currentTimeMillis;

/**
 * boolean lock
 *
 * @author zt1994 2020/3/23 21:00
 */
public class BooleanLock implements Lock {

    /**
     * 当前拥有锁的线程
     */
    private Thread currentThread;

    /**
     * boolean开关
     * false表示当前线程没有被任何线程获得或者已经释放
     * true表示该锁已经被某个线程获得
     */
    private boolean locked = false;

    /**
     * 存储阻塞线程
     */
    private final List<Thread> blockedList = new ArrayList<>();


    @Override
    public void lock() {
        synchronized (this) {
            while (locked) {
                // 暂存当前线程
                final Thread tempThread = Thread.currentThread();
                try {
                    if (!blockedList.contains(tempThread)) {
                        blockedList.add(tempThread);
                    }
                    this.wait();
                } catch (InterruptedException e) {
                    // 如果当前线程在wait时被中断，则从blockedList中将其删除，避免内存泄漏
                    blockedList.remove(tempThread);
                    e.printStackTrace();
                }

            }
            blockedList.remove(Thread.currentThread());
            this.locked = true;
            this.currentThread = Thread.currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if (mills <= 0) {
                this.lock();
            } else {
                long remainingMills = mills;
                long endMills = currentTimeMillis() + remainingMills;
                while (locked) {
                    if (remainingMills <= 0) {
                        throw new TimeoutException("超时 未获取lock:" + mills);
                    }
                    if (!blockedList.contains(Thread.currentThread())) {
                        blockedList.add(Thread.currentThread());
                    }
                    this.wait(remainingMills);
                    remainingMills = endMills - currentTimeMillis();
                }
                blockedList.remove(Thread.currentThread());
                this.locked = true;
                this.currentThread = Thread.currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this) {
            if (currentThread == Thread.currentThread()) {
                this.locked = false;
                Optional.of(Thread.currentThread().getName() + " 释放锁.").ifPresent(System.out::println);
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        // 返回不可修改的列表
        return Collections.unmodifiableList(blockedList);
    }
}
