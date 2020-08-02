package com.multithread.book1.chapter07;

import java.util.concurrent.TimeUnit;

/**
 * 空异常处理
 * <p>
 * 1、先判断父类是否有uncaughtException
 * 2、再判断是否有全局 uncaughtException
 * 3、都没有直接将异常的堆栈信息定向到System.err中
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
