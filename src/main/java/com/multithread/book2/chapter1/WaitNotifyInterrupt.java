package com.multithread.book2.chapter1;

import edu.princeton.cs.algs4.In;

import java.util.concurrent.TimeUnit;

/**
 * wait 通知 中断
 *
 * @author zt1994 2020/8/2 14:31
 */
public class WaitNotifyInterrupt {

    static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("----begin----");
                    synchronized (obj) {
                        obj.wait();
                    }
                    System.out.println("----end----");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        TimeUnit.SECONDS.sleep(1);

        System.out.println("----begin interrupt threadA----");
        threadA.interrupt();
        System.out.println("----end interrupt threadA----");
    }
}
