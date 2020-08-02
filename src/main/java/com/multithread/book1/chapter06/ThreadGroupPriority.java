package com.multithread.book1.chapter06;

import java.util.concurrent.TimeUnit;

/**
 * ThreadGroup的优先级
 *
 * @author zt1994 2020/3/26 20:41
 */
public class ThreadGroupPriority {


    /**
     * 虽然出现了已经加入该group的线程的优先级大于group最大优先级的情况，但是后面加入该group的线程再不会大于新设置的值：3
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group1 = new ThreadGroup("group1");
        Thread thread = new Thread(group1, () -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread");
        thread.setDaemon(true);
        thread.start();

        System.out.println("group.getMaxPriority()=" + group1.getMaxPriority());

        System.out.println("group.getMaxPriority()=" + thread.getPriority());
        group1.setMaxPriority(3);

        System.out.println("group.getMaxPriority()=" + group1.getMaxPriority());
        System.out.println("group.getMaxPriority()=" + thread.getPriority());
    }
}
