package com.multithread.chapter08;

import java.util.LinkedList;

/**
 * 运行线程队列
 *
 * @author zt1994 2020/4/9 20:57
 */
public class LinkedRunnableQueue implements RunnableQueue {

    /**
     * 任务队列的最大容量，在构造时传入
     */
    private final int limit;

    /**
     * 拒绝策略
     */
    private final DenyPolicy denyPolicy;

    /**
     * 存放任务的队列
     */
    private final LinkedList<Runnable> runnableList = new LinkedList<>();

    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }


    /**
     * 同步方法
     *
     * @param runnable
     */
    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableList) {
            if (runnableList.size() >= limit) {
                denyPolicy.reject(runnable, threadPool);
            } else {
                runnableList.addLast(runnable);
                runnableList.notifyAll();
            }
        }
    }

    /**
     * 同步方法
     *
     * @return
     * @throws InterruptedException
     */
    @Override
    public Runnable take() throws InterruptedException {
        synchronized (runnableList) {
            while (runnableList.isEmpty()) {
                try {
                    // 如果任务队列中没有可执行任务，则当前线程将会挂起，进入runnableList关联的monitor waitSet中等待唤醒
                    runnableList.wait();
                } catch (InterruptedException e) {
                    // 被中断时需要将该异常抛出
                    throw e;
                }
            }
        }
        // 从任务队列头部移除一个任务
        return runnableList.removeFirst();
    }

    @Override
    public int size() {
        synchronized (runnableList) {
            // 返回当前任务队列中的任务数
            return runnableList.size();
        }
    }
}
