package com.multithread.book1.chapter04;

/**
 * 实现runnable的模拟呼叫机
 * <p>
 * synchronized 关键字
 * synchronized 可以用于对代码块或方法进行修饰，而不能够用于对class以及变量进行修饰。
 *
 * 1、同步方法
 * [default|public|private|protected] synchronized [static] type method()
 * 例如：
 * public synchronized void sync() {
 *     ...
 * }
 *
 * public synchronized static void staticSync() {
 *     ...
 * }
 *
 * 2、同步代码块
 * private final Object MUTEX = new Object();
 * public void sync() {
 *     synchronized (MUTEX) {
 *         ...
 *     }
 * }
 *
 * @author zt1994 2020/2/27 21:12
 */
public class SyncTicketWindowRunnable implements Runnable {

    private int index = 1;

    private static final int MAX = 500;

    /**
     * mutex 互斥
     */
    private final static Object MUTEX = new Object();

    @Override
    public void run() {
        synchronized (MUTEX) {
            while (index <= MAX) {
                System.out.println(Thread.currentThread() + " 的号码是：" + (index++));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SyncTicketWindowRunnable ticketTask = new SyncTicketWindowRunnable();

        Thread t1 = new Thread(ticketTask, "一号机");
        t1.start();

        Thread t2 = new Thread(ticketTask, "一号机");
        t2.start();

        Thread t3 = new Thread(ticketTask, "一号机");
        t3.start();

        Thread t4 = new Thread(ticketTask, "一号机");
        t4.start();
    }
}
