package com.multithread.book2.chapter6;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport unpark()方法测试
 *
 * @author zt1994 2020/8/4 20:49
 */
public class UnparkTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("child thread begin park");
                // 调用park，挂起
                while (!Thread.currentThread().isInterrupted()) {
                    LockSupport.park();
                }

                System.out.println("child thread unpark");
            }
        });

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("main thread begin unpark");

        // 调用unpark方法让thread线程持有许可证，然后park方法返回
        //LockSupport.unpark(thread);
        // 中断
        thread.interrupt();
    }
}
