package com.multithread.chapter08;

/**
 * 任务队列 用于缓存提交到线程池中的任务
 *
 * @author zt1994 2020/4/8 21:31
 */
public interface RunnableQueue {

    /**
     * 当有新任务进来时首先会offer到队列中
     *
     * @param runnable
     */
    void offer(Runnable runnable);

    /**
     * 工作线程通过take方法获取runnable
     *
     * @return
     */
    Runnable take() throws InterruptedException;

    /**
     * 获取任务队列中任务的数量
     *
     * @return
     */
    int size();
}
