package com.multithread.chapter20;

import java.util.LinkedList;

/**
 * 确保挂起队列
 *
 * @author zt1994 2020/5/26 21:10
 */
public class GuardedSuspensionQueue {

    /**
     * queue
     */
    private final LinkedList<Integer> queue = new LinkedList<>();

    /**
     * 定义queue的最大容量为100
     */
    private final int LIMIT = 100;

    public void offer(Integer data) throws InterruptedException {
        synchronized (this) {
            while (queue.size() > LIMIT) {
                // 挂起
                this.wait();
            }
            // 唤醒
            queue.addLast(data);
            this.notifyAll();
        }
    }

    public Integer take() throws InterruptedException {
        synchronized (this) {
            while (queue.isEmpty()) {
                // 挂起
                this.wait();
            }
            // 唤醒
            this.notifyAll();
            return queue.removeFirst();
        }
    }
}
