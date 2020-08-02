package com.multithread.book1.chapter29;

import java.util.HashMap;
import java.util.Map;

/**
 * 事件分配
 *
 * @author zt1994 2020/7/7 21:26
 */
public class EventDispatcher implements DynamicRouter<Message> {

    /**
     * 用于保存Channel和Message之间的关系
     */
    private final Map<Class<? extends Message>, Channel> routerTable;

    public EventDispatcher() {
        this.routerTable = new HashMap<>();
    }

    @Override
    public void registerChannel(Class<? extends Message> messageType, Channel<? extends Message> channel) {
        this.routerTable.put(messageType, channel);
    }

    @Override
    public void dispatch(Message message) {
        if (routerTable.containsKey(message.getType())) {
            routerTable.get(message.getType()).dispatch(message);
        } else {
            throw new MessageMatcherException("不能匹配" + message.getType() + "的消息类型");
        }
    }
}
