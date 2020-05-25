package com.multithread.chapter19;

/**
 * task接口 提供给调用者实现计算逻辑
 *
 * @author zt1994 2020/5/25 21:24
 */
public interface Task<IN, OUT> {

    /**
     * 给定一个参数，经过计算返回结果
     *
     * @param input
     * @return
     */
    OUT get(IN input);
}
