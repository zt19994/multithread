package com.multithread.chapter19;

/**
 * 回调接口
 *
 * @author zt1994 2020/5/25 21:53
 */
@FunctionalInterface
public interface CallBack<T> {

    /**
     * 任务完成后会调用该方法，其中T为任务执行后的结果
     *
     * @param t
     */
    void call(T t);
}
