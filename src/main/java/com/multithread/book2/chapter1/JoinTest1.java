package com.multithread.book2.chapter1;

import java.util.concurrent.TimeUnit;

/**
 * join 测试
 *
 * @author zt1994 2020/8/2 15:23
 */
public class JoinTest1 {

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("child threadA over.");
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("child threadB over.");
            }
        });

        threadA.start();
        threadB.start();

        System.out.println("wait all child thread over.");

        threadA.join();
        threadB.join();

        System.out.println("all child thread over.");
    }
}
