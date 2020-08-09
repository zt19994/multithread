package com.multithread.book2.chapter10;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch测试，主线程等待所有子线程执行完毕后在进行汇总
 *
 * @author zt1994 2020/8/7 18:17
 */
public class JoinCountDownLatch {

    private static volatile CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("child threadA over.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("child threadB over.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }
        });

        threadA.start();
        threadB.start();

        System.out.println("wait all child thread over");

        // 等待子线程执行完毕，返回
        countDownLatch.await();

        System.out.println("all child thread over");
    }

}
