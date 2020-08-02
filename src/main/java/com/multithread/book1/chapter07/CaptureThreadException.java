package com.multithread.book1.chapter07;

import java.util.concurrent.TimeUnit;

/**
 * 捕获线程异常
 *
 * 1、setUncaughtExceptionHandler(UncaughtExceptionHandler eh) 为某个特定线程指定UncaughtExceptionHandler
 * 2、setDefaultUncaughtExceptionHandler(UncaughtExceptionHandler eh) 设置全局的UncaughtExceptionHandler
 * 3、getUncaughtExceptionHandler() 获取特定线程的UncaughtException
 * 4、getDefaultUncaughtExceptionHandler() 获取全局的UncaughtExceptionHandler
 *
 * @author zt1994 2020/4/7 21:22
 */
public class CaptureThreadException {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + " occur exception.");
            e.printStackTrace();
        });
        final Thread thread = new Thread(() -> {
           try {
               TimeUnit.SECONDS.sleep(2);
           } catch (InterruptedException e) {

           }
            // 异常
            System.out.println(1 / 0);
        }, "Test-thread");

       thread.start();
    }
}
