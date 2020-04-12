package com.multithread.chapter08;

import java.util.concurrent.TimeUnit;

/**
 * 线程池测试
 *
 * @author zt1994 2020/4/12 20:41
 */
public class ThreadPoolTest {


    public static void main(String[] args) throws InterruptedException {
        BasicThreadPool threadPool = new BasicThreadPool(2, 6, 4, 1000);

        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " is running and done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            for (; ; ) {
                System.out.println("getActiveCount:" + threadPool.getActiveCount());
                System.out.println("getQueueSize:" + threadPool.getQueueSize());
                System.out.println("getCoreSize:" + threadPool.getCoreSize());
                System.out.println("getMaxSize:" + threadPool.getMaxSize());
                System.out.println("==========");
                TimeUnit.SECONDS.sleep(5);
            }
        }
    }
}
