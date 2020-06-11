package com.multithread.chapter23;

import java.util.concurrent.TimeUnit;

/**
 * 测试门阀设计模式
 *
 * @author zt1994 2020/6/10 21:13
 */
public class LatchTest {

    public static void main(String[] args) throws InterruptedException {
        Latch latch = new CountDownLatch(4);
        new ProgrammerTravel(latch, "Alex", "Bus").start();
        new ProgrammerTravel(latch, "Peter", "Walking").start();
        new ProgrammerTravel(latch, "Mike", "Subway").start();
        new ProgrammerTravel(latch, "Jack", "Bicycle").start();
        try {
            latch.await(TimeUnit.SECONDS, 5);
            System.out.println();
        } catch (WaitTimeoutExeption e) {
            e.printStackTrace();
        }
        System.out.println("== all of programmer arrived ==");
    }
}
