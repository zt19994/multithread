package com.multithread.chapter26;

/**
 * 产品流水线处理
 *
 * @author zt1994 2020/6/22 21:47
 */
public class ProductionChannel {

    /**
     * 最多可以加工产品数
     */
    private final static int MAX_PROD = 100;

    /**
     * 产品处理队列
     */
    private final Production[] productionQueue;

    /**
     * 队列尾部
     */
    private int tail;

    /**
     * 队列头部
     */
    private int head;

    /**
     * 当前流水线待加工产品数
     */
    private int total;

    /**
     * 产品线上工人
     */
    private final Worker[] workers;

    public ProductionChannel(int workerSize) {
        this.workers = new Worker[workerSize];
        this.productionQueue = new Production[MAX_PROD];
        // 实例化工人并启动
        for (int i = 0; i < workerSize; i++) {
            workers[i] = new Worker("Worker-" + i, this);
            workers[i].start();
        }
    }

    /**
     * 接受上游产品
     *
     * @param production
     */
    public void offerProduction(Production production) {
        synchronized (this) {
            while (total >= productionQueue.length) {
                // 产品过多，阻塞线程
                try {
                    this.wait();
                } catch (InterruptedException e) {

                }
            }
            // 队列中添加新的产品
            productionQueue[tail] = production;
            tail = (tail + 1) % productionQueue.length;
            total++;
            this.notifyAll();
        }
    }


    /**
     * 获取产品，进行加工
     *
     * @return
     */
    public Production takeProduction() {
        synchronized (this) {
            while (total <= 0) {
                // 当没有产品时，等待
                try {
                    this.wait();
                } catch (InterruptedException e) {

                }
            }
            // 获取产品
            Production production = productionQueue[head];
            head = (head + 1) % productionQueue.length;
            total--;
            this.notifyAll();
            return production;
        }
    }
}
