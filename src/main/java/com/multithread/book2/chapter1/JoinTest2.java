package com.multithread.book2.chapter1;

import java.util.concurrent.TimeUnit;

/**
 * join 测试
 *
 * @author zt1994 2020/8/2 15:23
 */
public class JoinTest2 {

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadA begin run.");
                for (; ; ) {

                }
            }
        });

        // 获取主线程
        final Thread mainThread = Thread.currentThread();

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mainThread.interrupt();
            }
        });

        threadA.start();
        threadB.start();

       try {
           threadA.join();
       } catch (InterruptedException e) {
           System.out.println("main thread:" + e);
       }
    }
}
