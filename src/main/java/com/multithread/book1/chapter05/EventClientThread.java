package com.multithread.book1.chapter05;

import java.util.concurrent.TimeUnit;

/**
 * 模拟时间客户端
 *
 * @author zt1994 2020/3/19 21:09
 */
public class EventClientThread {

    public static void main(String[] args) {

        final EventQueueThread eventQueue = new EventQueueThread();

        new Thread(() -> {
            for (; ; ) {
                eventQueue.offer(new EventQueueThread.Event());
            }
        }, "Producer").start();


        new Thread(() -> {
            for (; ; ) {
                eventQueue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer").start();
    }

}
