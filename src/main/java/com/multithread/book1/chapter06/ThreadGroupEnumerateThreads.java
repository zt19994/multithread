package com.multithread.book1.chapter06;

import java.util.concurrent.TimeUnit;

/**
 * 复制数组
 *
 * public int enumerate(Thread[] list)
 * public int enumerate(Thread[] list, boolean recurse)
 *
 * @author zt1994 2020/3/25 21:26
 */
public class ThreadGroupEnumerateThreads {

    /**
     * 第二个recurseSize比第一个少1，因为 recurse 为false，myGroup中的线程将不会包含在内
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup myGroup = new ThreadGroup("MyGroup");
        Thread thread = new Thread(myGroup, () -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "MyThread");
        thread.start();

        TimeUnit.MILLISECONDS.sleep(2);
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        Thread[] list = new Thread[mainGroup.activeCount()];
        int recurseSize = mainGroup.enumerate(list);
        System.out.println(recurseSize);

        recurseSize = mainGroup.enumerate(list, false);
        System.out.println(recurseSize);
    }
}
