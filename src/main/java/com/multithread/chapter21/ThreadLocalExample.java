package com.multithread.chapter21;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * ThreadLocal 实例
 *
 * @author zt1994 2020/5/28 20:57
 */
public class ThreadLocalExample {

    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

        IntStream.range(0, 10)
                .forEach(i -> new Thread(() -> {
                    try {
                        threadLocal.set(i);
                        System.out.println(Thread.currentThread() + " set i " + threadLocal.get());
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(Thread.currentThread() + " get i " + threadLocal.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start());
    }
}
