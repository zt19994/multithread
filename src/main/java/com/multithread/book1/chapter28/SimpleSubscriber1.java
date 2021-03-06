package com.multithread.book1.chapter28;

/**
 * 简单订阅者1
 *
 * @author zt1994 2020/7/6 20:45
 */
public class SimpleSubscriber1 {

    @Subscribe
    public void method1(String message) {
        System.out.println("==SimpleSubscriber1==method1==" + message);
    }

    @Subscribe(topic = "test")
    public void method2(String message) {
        System.out.println("==SimpleSubscriber1==method2==" + message);
    }

}
