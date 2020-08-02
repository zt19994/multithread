package com.multithread.book1.chapter05;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 的缺点
 *
 * 1、无法控制阻塞时长
 * 2、synchronized 同步的线程不可被中断
 *
 * @author zt1994 2020/3/22 21:35
 */
public class SynchronizedDefect {


    public synchronized void syncMethod() {
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedDefect defect = new SynchronizedDefect();
        Thread t1 = new Thread(defect::syncMethod, "T1");
        t1.start();
        TimeUnit.MILLISECONDS.sleep(2);

        Thread t2 = new Thread(defect::syncMethod, "T2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(2);
        t2.interrupt();
        System.out.println(t2.isInterrupted());
        System.out.println(t2.getState());
    }
}
