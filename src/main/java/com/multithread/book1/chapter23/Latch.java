package com.multithread.book1.chapter23;

import java.util.concurrent.TimeUnit;

/**
 * 门阀类
 *
 * @author zt1994 2020/6/3 21:49
 */
public abstract class Latch {

    /**
     * 用于控制多少个线程完成任务时才能打开门阀
     */
    protected int limit;

    public Latch(int limit) {
        this.limit = limit;
    }


    /**
     * 使当前线程一直等待，直到所有的线程都完成工作，被阻塞的线程是允许被中断的
     *
     * @throws InterruptedException
     */
    public abstract void await() throws InterruptedException;

    /**
     * 当任务线程完成工作后，调用该方法使计数器减一
     */
    public abstract void countDown();

    /**
     * 获取当前还有多少个线程没有完成任务
     *
     * @return
     */
    public abstract int getUnarrived();

    /**
     * 超时等待
     *
     * @param unit
     * @param time
     * @throws InterruptedException
     */
    public abstract void await(TimeUnit unit, long time) throws InterruptedException, WaitTimeoutExeption;
}
