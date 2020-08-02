package com.multithread.book1.chapter19;

/**
 * @author zt1994 2020/5/25 21:29
 */
public class FutureTask<T> implements Future<T> {

    /**
     * 计算结果
     */
    private T result;

    /**
     * 任务是否完成
     */
    private boolean isDone = false;

    /**
     * 定义对象锁
     */
    private final Object LOCK = new Object();

    @Override
    public T get() throws InterruptedException {
        synchronized (LOCK) {
            // 当任务未完成时，调用get方法会被阻塞
            while (!isDone) {
                LOCK.wait();
            }
            return result;
        }
    }

    @Override
    public boolean done() {
        return isDone;
    }


    /**
     * 用于为FutureTask设置计算结果
     *
     * @param result
     */
    protected void finish(T result) {
        synchronized (LOCK) {
            if (isDone) {
                return;
            }
            this.result = result;
            this.isDone = true;
            // 唤醒阻塞线程
            LOCK.notifyAll();
        }
    }
}
