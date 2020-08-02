package com.multithread.book1.chapter06;

import java.util.concurrent.TimeUnit;

/**
 * 线程组interrupt
 * <p>
 * 线程组interrupt会导致group中所有的active线程被interrupt
 *
 * @author zt1994 2020/4/2 21:28
 */
public class ThreadGroupInterrupt {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("TestGroup");

        new Thread(group, () -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(2);
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("t1 will exit");
        }, "t1").start();

        new Thread(group, () -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("t2 will exit");
        }, "t2").start();

        TimeUnit.MILLISECONDS.sleep(2);

        group.interrupt();

    }

}
