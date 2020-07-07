package com.multithread.chapter29;

/**
 * 事件实现Message
 *
 * @author zt1994 2020/7/7 21:21
 */
public class Event implements Message {

    /**
     * 获取事件类型并返回
     *
     * @return
     */
    @Override
    public Class<? extends Message> getType() {
        return getClass();
    }
}
