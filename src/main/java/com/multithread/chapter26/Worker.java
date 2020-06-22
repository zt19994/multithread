package com.multithread.chapter26;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 工人
 *
 * @author zt1994 2020/6/22 21:58
 */
public class Worker extends Thread {

    private final ProductionChannel channel;

    /**
     * 产品处理随机时间
     */
    private final static Random random = new Random(System.currentTimeMillis());

    public Worker(String workerName, ProductionChannel channel) {
        super(workerName);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Production production = channel.takeProduction();
                System.out.println(getName() + " process the " + production);
                production.create();
                TimeUnit.SECONDS.sleep(random.nextInt());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
