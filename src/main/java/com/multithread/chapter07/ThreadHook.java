package com.multithread.chapter07;

import java.util.concurrent.TimeUnit;

/**
 * 线程hook
 *
 * Hook 线程会在jvm进程退出时执行，通过runtime可以为jvm注入多个Hook线程
 *
 * @author zt1994 2020/4/7 21:37
 */
public class ThreadHook {

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("The hook thread 1 is running.");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The hook thread 1 will exit.");
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("The hook thread 2 is running.");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The hook thread 2 will exit.");
            }
        });
        System.out.println("The program will is stopping.");
    }
}
