package com.multithread.chapter08;

/**
 * 线程池
 *
 * @author zt1994 2020/4/8 21:21
 */
public interface ThreadPool {

    /**
     * 提交任务到线程池
     *
     * @param runnable
     */
    void execute(Runnable runnable);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 获取线程池的初始化大小
     *
     * @return
     */
    int getInitSize();

    /**
     * 获取线程池最大的线程数
     *
     * @return
     */
    int getMaxSize();

    /**
     * 获取线程池的核心线程数量
     *
     * @return
     */
    int getCoreSize();

    /**
     * 获取线程池中用于缓存任务队列的大小
     *
     * @return
     */
    int getQueueSize();

    /**
     * 获取线程池中活跃线程的数量
     *
     * @return
     */
    int getActiveCount();

    /**
     * 查看线程池是否已经被shutdown
     *
     * @return
     */
    boolean isShutdown();
}
