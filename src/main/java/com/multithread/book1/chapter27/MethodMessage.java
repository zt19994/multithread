package com.multithread.book1.chapter27;

import java.util.Map;

/**
 * 抽象消息方法类
 *
 * @author zt1994 2020/6/27 21:17
 */
public abstract class MethodMessage {

    /**
     * 用于收集方法参数，如果有返回Future类型则一起收集
     */
    protected final Map<String, Object> params;

    protected final OrderService orderService;

    public MethodMessage(Map<String, Object> params, OrderService orderService) {
        this.params = params;
        this.orderService = orderService;
    }


    /**
     * 抽象方法，扮演work thread的说明书
     */
    public abstract void execute();
}
