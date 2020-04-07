package com.multithread.chapter07;

import java.util.concurrent.TimeUnit;

/**
 * 空异常处理
 *
 * @author zt1994 2020/4/7 21:28
 */
public class EmptyExceptionHandler {

    public static void main(String[] args) {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println(mainGroup.getName());
        System.out.println(mainGroup.getParent());
        System.out.println(mainGroup.getParent().getParent());

        final Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {

            }

            System.out.println(1 / 0);
        }, "Test-thread");

        thread.start();
    }
}
