package com.multithread.chapter19;

/**
 * future 接口
 *
 * @author zt1994 2020/5/25 21:12
 */
public interface Future<T> {

    /**
     * 返回计算后的结果，该方法会陷入阻塞状态
     *
     * @return
     * @throws InterruptedException
     */
    T get() throws InterruptedException;

    /**
     * 判断任务是否已经被执行完成
     *
     * @return
     */
    boolean done();
}
