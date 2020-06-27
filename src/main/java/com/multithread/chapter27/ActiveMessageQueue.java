package com.multithread.chapter27;

import java.util.LinkedList;

/**
 * 主动对象队列
 *
 * @author zt1994 2020/6/27 21:09
 */
public class ActiveMessageQueue {

    /**
     * 用于存放提交的MethodMessage消息
     */
    private final LinkedList<MethodMessage> messages = new LinkedList<>();

    public ActiveMessageQueue() {
        new ActiveDaemonThread(this).start();
    }

    public void offer(MethodMessage methodMessage) {
        synchronized (this) {
            messages.addLast(methodMessage);
            // 因为只有一个线程负责take数据，因此没有必要使用notifyAll()方法
            this.notify();
        }
    }

    protected MethodMessage take() {
        synchronized (this) {
            // 当messages没有message时，线程进入阻塞
            while (messages.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 获取其中一个MethodMessage并且从队列中移除
            return messages.removeFirst();
        }
    }
}
