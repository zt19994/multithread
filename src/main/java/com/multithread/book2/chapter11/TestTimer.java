package com.multithread.book2.chapter11;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * 测试timer
 *
 * @author zt1994 2020/8/10 11:13
 */
public class TestTimer {

    static Timer timer = new Timer();

    public static void main(String[] args) {
        // 任务1
        timer.schedule(new TimerTask() {
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
        }, 500);

        // 任务2
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (; ; ) {
                    System.out.println("---two Task---");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 1000);
    }
}
