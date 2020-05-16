package com.multithread.chapter15;

/**
 * 任务接口
 *
 * @author zt1994 2020/5/16 20:57
 */
public interface Task<T> {

    /**
     * 任务执行接口，该接口允许有返回值
     *
     * @return
     */
    T call();
}
