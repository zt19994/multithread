package com.multithread.book2.chapter1;

import java.util.concurrent.TimeUnit;

/**
 * 死锁测试
 *
 * @author zt1994 2020/8/3 14:08
 */
public class DeadLockTest {

    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + " get resourceA");

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread() + " waiting get resourceB");
                    synchronized (resourceB) {
                        System.out.println(Thread.currentThread() + " get resourceB");
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread() + " get resourceB");

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread() + " waiting get resourceA");
                    synchronized (resourceA) {
                        System.out.println(Thread.currentThread() + " get resourceA");
                    }
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
