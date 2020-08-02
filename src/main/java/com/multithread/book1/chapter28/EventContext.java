package com.multithread.book1.chapter28;

import java.lang.reflect.Method;

/**
 * 事件上下文
 *
 * @author zt1994 2020/7/1 21:53
 */
public interface EventContext {

    /**
     * 获取源
     *
     * @return
     */
    String getSource();

    /**
     * 获取订阅者
     *
     * @return
     */
    Object getSubscriber();

    /**
     * 获取订阅
     *
     * @return
     */
    Method getSubscribe();

    /**
     * 获取事件
     *
     * @return
     */
    Object getEvent();
}
