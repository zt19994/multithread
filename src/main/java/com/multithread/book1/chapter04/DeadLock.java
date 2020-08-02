package com.multithread.book1.chapter04;

import static java.lang.Thread.currentThread;

/**
 * 死锁
 *
 * 程序死锁：
 * 1、交叉锁可导致程序出现死锁
 *
 * 2、内存不足
 *
 * 3、一问一答式的数据交换
 *
 * 4、数据库锁
 *
 * 5、文件锁
 *
 * 6、死循环引起的死锁
 *
 * HashMap 不具备线程安全的能力，线程安全要使用ConcurrentHashMap数据结构
 * @author zt1994 2020/3/18 21:02
 */
public class DeadLock {

    private final Object MUTEX_READ = new Object();

    private final Object MUTEX_WEITE = new Object();

    public void read() {
        synchronized (MUTEX_READ) {
            System.out.println(currentThread().getName() + " 进入read方法lock");
            synchronized (MUTEX_WEITE) {
                System.out.println(currentThread().getName() + " 进入write方法lock");
            }
            System.out.println(currentThread().getName() + " 退出write方法lock");
        }
        System.out.println(currentThread().getName() + " 退出read方法lock");
    }

    public void write() {
        synchronized (MUTEX_WEITE) {
            System.out.println(currentThread().getName() + " 进入write方法lock");
            synchronized (MUTEX_READ) {
                System.out.println(currentThread().getName() + " 进入read方法lock");
            }
            System.out.println(currentThread().getName() + " 退出read方法lock");
        }
        System.out.println(currentThread().getName() + " 退出write方法lock");
    }

    public static void main(String[] args) {
        final DeadLock deadLock = new DeadLock();
        new Thread(() -> {
           while (true) {
               deadLock.read();
           }
        }, "READ-THREAD").start();

        new Thread(() -> {
            while (true) {
                deadLock.write();
            }
        }, "WRITE-THREAD").start();
    }
}
