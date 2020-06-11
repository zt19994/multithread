package com.multithread.chapter23;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 程序员旅游类
 *
 * @author zt1994 2020/6/10 21:07
 */
public class ProgrammerTravel extends Thread {

    /**
     * 门阀
     */
    private final Latch latch;

    /**
     * 程序员
     */
    private final String programmer;

    /**
     * 交通工具
     */
    private final String transportation;

    public ProgrammerTravel(Latch latch, String programmer, String transportation) {
        this.latch = latch;
        this.programmer = programmer;
        this.transportation = transportation;
    }

    @Override
    public void run() {
        System.out.println(programmer + " start take the transportation [" + transportation + "]");
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(programmer + " arrived by " + transportation);
        // 完成任务使计数器减一
        latch.countDown();
    }
}
