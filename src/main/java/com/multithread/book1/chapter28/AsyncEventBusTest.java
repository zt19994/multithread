package com.multithread.book1.chapter28;


import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 测试
 *
 * @author zt1994 2020/7/6 20:49
 */
public class AsyncEventBusTest {

    public static void main(String[] args) {
        Bus bus = new AsyncEventBus("TestBus", (ThreadPoolExecutor) Executors.newFixedThreadPool(10));
        bus.register(new SimpleSubscriber1());
        bus.register(new SimpleSubscriber2());
        bus.post("Hello");
        System.out.println("---------");
        bus.post("Hello", "test");
    }
}
