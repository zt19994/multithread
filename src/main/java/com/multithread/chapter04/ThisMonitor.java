package com.multithread.chapter04;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

/**
 * this monitor
 * <p>
 * synchronized 关键字修饰的一个实例对象的两个不同的实例，争抢的是同一个monitor的lock，与之关联的引用则是thisMonitor的实例引用
 *
 * @author zt1994 2020/3/17 21:31
 */
public class ThisMonitor {

    public synchronized void method1() {
        System.out.println(currentThread().getName() + " 进入方法1");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 改为
     * public void method2() {
     *     synchronized (this) {
     *         // 一样的效果
     *     }
     * }
     */
    public synchronized void method2() {
        System.out.println(currentThread().getName() + " 进入方法2");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThisMonitor thisMonitor = new ThisMonitor();
        new Thread(thisMonitor::method1, "T1").start();
        new Thread(thisMonitor::method2, "T2").start();
    }
}
