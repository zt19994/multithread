package com.multithread.chapter23;

import java.util.concurrent.TimeUnit;

/**
 * 无线等待countDownLatch实现
 *
 * @author zt1994 2020/6/10 21:01
 */
public class CountDownLatch extends Latch {

    private Runnable runnable;

    public CountDownLatch(int limit) {
        super(limit);
    }

    public CountDownLatch(int limit, Runnable runnable) {
        super(limit);
        this.runnable = runnable;
    }

    @Override
    public void await() throws InterruptedException {
        synchronized (this) {
            // 当limit>0 当前线程进入阻塞状态
            while (limit > 0) {
                this.wait();
            }
        }
        if (null != runnable) {
            runnable.run();
        }
    }

    @Override
    public void countDown() {
        synchronized (this) {
            if (limit <= 0) {
                throw new IllegalStateException("all of task already arrived.");
            }
            // limit 减一 通知阻塞线程
            limit--;
            this.notifyAll();
        }
    }

    @Override
    public int getUnarrived() {
        // 返回还未完成的任务数
        return limit;
    }

    @Override
    public void await(TimeUnit unit, long time) throws InterruptedException, WaitTimeoutExeption {
        if (time <= 0) {
            throw new IllegalStateException("The time is invalid.");
        }
        long remainingNanos = unit.toNanos(time);
        final long endNanos = System.nanoTime() + remainingNanos;
        synchronized (this) {
            while (limit > 0) {
                // 如果超时则抛出异常
                if (TimeUnit.NANOSECONDS.toMillis(remainingNanos) < 0) {
                    throw new WaitTimeoutExeption("The wait time over specify time.");
                }
                this.wait(TimeUnit.NANOSECONDS.toMillis(remainingNanos));
                remainingNanos = endNanos - System.nanoTime();
            }
        }
        if (null != runnable) {
            runnable.run();
        }
    }
}
