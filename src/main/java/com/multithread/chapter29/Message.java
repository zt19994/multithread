package com.multithread.chapter29;

/**
 * 消息
 *
 * @author zt1994 2020/7/7 21:05
 */
public interface Message {

    /**
     * 返回Message的类型
     *
     * @return
     */
    Class<? extends Message> getType();
}
