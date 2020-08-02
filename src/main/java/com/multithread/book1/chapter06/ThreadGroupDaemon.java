package com.multithread.book1.chapter06;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程组
 * <p>
 * 如果一个ThreadGroup的daemon被设置为true，那么group中没有任何active线程的时候该group将自动destroy
 * ThreadGroup设置为daemon，不影响线程的daemon属性
 *
 * @author zt1994 2020/4/2 21:41
 */
public class ThreadGroupDaemon {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group1 = new ThreadGroup("Group1");
        new Thread(group1, () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "group1-thread1").start();

        ThreadGroup group2 = new ThreadGroup("Group2");
        new Thread(group2, () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "group2-thread1").start();

        group2.setDaemon(true);

        TimeUnit.SECONDS.sleep(3);
        System.out.println(group1.isDestroyed());
        System.out.println(group2.isDestroyed());
    }
}
