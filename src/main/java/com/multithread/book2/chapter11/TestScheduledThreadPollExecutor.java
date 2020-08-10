package com.multithread.book2.chapter11;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试ScheduledThreadPoolExecutor
 *
 * @author zt1994 2020/8/10 11:29
 */
public class TestScheduledThreadPollExecutor {

    static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

    public static void main(String[] args) {
        scheduledThreadPoolExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("---one Task---");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                throw new RuntimeException("error");
            }
        }, 500, TimeUnit.MICROSECONDS);

        scheduledThreadPoolExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    System.out.println("---two Task---");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 1000, TimeUnit.MICROSECONDS);

        scheduledThreadPoolExecutor.shutdown();
    }
}
