package com.multithread.book1.chapter29;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 异步事件分发
 *
 * @author zt1994 2020/7/8 20:59
 */
public class AsyncEventDispatcher implements DynamicRouter<Event> {

    /**
     * 线程安全的ConcurrentHashMap替换HashMap
     */
    private final Map<Class<? extends Event>, AsyncChannel> routerTable;

    public AsyncEventDispatcher() {
        this.routerTable = new ConcurrentHashMap<>();
    }

    @Override
    public void registerChannel(Class<? extends Event> messageType, Channel<? extends Event> channel) {
        if (!(channel instanceof AsyncChannel)) {
            throw new IllegalArgumentException("Channel必须是异步的");
        }
        this.routerTable.put(messageType, (AsyncChannel) channel);
    }

    @Override
    public void dispatch(Event message) {
        if (routerTable.containsKey(message.getType())) {
            routerTable.get(message.getType()).dispatch(message);
        } else {
            throw new MessageMatcherException("不能匹配数据类型：" + message.getType());
        }
    }

    public void shutdown() {
        // 关闭所以的Channel以释放资源
        routerTable.values().forEach(AsyncChannel::stop);
    }
}
