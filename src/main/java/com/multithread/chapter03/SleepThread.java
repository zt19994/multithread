package com.multithread.chapter03;

/**
 * sleep thread
 *
 * @author zt1994 2020/3/4 21:01
 */
public class SleepThread {


    /**
     * 每个线程休眠互不影响 Thread.sleep 只导致当前线程进入指定时间的休眠
     *
     * @param args
     */
    public static void main(String[] args) {
        new Thread(() -> {
            long startTime = System.currentTimeMillis();
            sleep(2_00L);
            long endTime = System.currentTimeMillis();
            System.out.println(String.format("Total spend %d ms", (endTime - startTime)));
        }).start();

        long startTime = System.currentTimeMillis();
        sleep(3_00L);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("Main thread total spend %d ms", (endTime - startTime)));
    }


    /**
     * 线程 sleep
     *
     * @param ms
     */
    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
