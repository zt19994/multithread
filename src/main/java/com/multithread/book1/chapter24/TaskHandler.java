package com.multithread.book1.chapter24;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 任务处理
 *
 * @author zt1994 2020/6/12 20:55
 */
public class TaskHandler implements Runnable {

    /**
     * 需要处理的请求
     */
    private final Request request;

    public TaskHandler(Request request) {
        this.request = request;
    }

    @Override
    public void run() {
        System.out.println("Begin handle " + request);
        slowly();
        System.out.println("End handle " + request);
    }


    /**
     * 模拟处理请求
     */
    private void slowly() {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
