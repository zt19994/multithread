package com.multithread.book1.chapter04;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

/**
 * this monitor
 * <p>
 * synchronized 关键字同步某个类的不同静态方法，争抢的也是同一个monitor的lock，与之关联的引用则是classMonitor的实例引用
 *
 * @author zt1994 2020/3/17 21:31
 */
public class ClassMonitor {

    public static synchronized void method1() {
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
     *     synchronized (ClassMonitor.class) {
     *         // 一样的效果
     *     }
     * }
     */
    public static synchronized void method2() {
        System.out.println(currentThread().getName() + " 进入方法2");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(ClassMonitor::method1, "T1").start();
        new Thread(ClassMonitor::method2, "T2").start();
    }
}
