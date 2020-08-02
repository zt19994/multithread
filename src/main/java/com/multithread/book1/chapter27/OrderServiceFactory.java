package com.multithread.book1.chapter27;

/**
 * 订单服务工厂
 *
 * @author zt1994 2020/6/27 21:46
 */
public final class OrderServiceFactory {

    private final static ActiveMessageQueue activeMessageQueue = new ActiveMessageQueue();

    private OrderServiceFactory() {

    }

    /**
     * 返回orderServiceProxy
     *
     * @param orderService
     * @return
     */
    public static OrderService toActiveObject(OrderService orderService) {
        return new OrderServiceProxy(orderService, activeMessageQueue);
    }
}
