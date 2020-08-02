package com.multithread.book1.chapter29;

/**
 * 动态路由,帮助Event找到合适的Channel并且传送给它
 *
 * @author zt1994 2020/7/7 21:10
 */
public interface DynamicRouter<E extends Message> {

    /**
     * 针对每一种Message类型注册相关的Channel,只有找到合适的Channel该Message才会被处理
     *
     * @param messageType
     * @param channel
     */
    void registerChannel(Class<? extends E> messageType, Channel<? extends E> channel);

    /**
     * 为相应的Channel分配Message
     *
     * @param message
     */
    void dispatch(E message);

}
