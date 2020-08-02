package com.multithread.book1.chapter28;

/**
 * 定义event bus的所以使用方法
 *
 * @author zt1994 2020/6/30 21:33
 */
public interface Bus {

    /**
     * 将某个对象注册到bus上，从此之后该类成为subscriber
     *
     * @param subscriber
     */
    void register(Object subscriber);

    /**
     * 取消注册
     *
     * @param subscriber
     */
    void unregister(Object subscriber);

    /**
     * 提交event到默认的topic
     *
     * @param event
     */
    void post(Object event);

    /**
     * 提交event到指定topic
     *
     * @param event
     * @param topic
     */
    void post(Object event, String topic);

    /**
     * 关闭该bus
     */
    void close();

    /**
     * 获取Bus的名称标识
     *
     * @return
     */
    String getBusName();

}
