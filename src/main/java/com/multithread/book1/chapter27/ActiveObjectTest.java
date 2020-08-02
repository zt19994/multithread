package com.multithread.book1.chapter27;

/**
 * 测试主动对象
 *
 * @author zt1994 2020/6/27 21:49
 */
public class ActiveObjectTest {

    public static void main(String[] args) throws InterruptedException {
        OrderService orderService = OrderServiceFactory.toActiveObject(new OrderServiceImpl());
        orderService.order("hello", 123456);
        System.out.println("Return immediately");
        Thread.currentThread().join();
    }
}
