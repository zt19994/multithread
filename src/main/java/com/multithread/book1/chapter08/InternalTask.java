package com.multithread.book1.chapter08;

/**
 * 内部任务类
 *
 * @author zt1994 2020/4/8 21:44
 */
public class InternalTask implements Runnable {

    private final RunnableQueue runnableQueue;

    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        // 当任务running并且没有中断，则不断将queue中获取runnable，然后执行run方法
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                Runnable task = runnableQueue.take();
                task.run();
            } catch (InterruptedException e) {
                running = false;
                break;
            }
        }
    }


    /**
     * 停止当前任务，主要会在线程池的shutdown方法中使用
     */
    public void stop() {
        this.running = false;
    }
}
