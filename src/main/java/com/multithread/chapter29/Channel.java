package com.multithread.chapter29;

/**
 * 通道channel负责处理一种类型
 *
 * @author zt1994 2020/7/7 21:08
 */
public interface Channel<E extends Message> {

    /**
     * dispatch 方法用于负责Message的调度
     *
     * @param message
     */
    void dispatch(E message);
}
