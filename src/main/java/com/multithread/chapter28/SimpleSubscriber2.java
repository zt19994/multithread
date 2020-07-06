package com.multithread.chapter28;

/**
 * 简单订阅者2
 *
 * @author zt1994 2020/7/6 20:45
 */
public class SimpleSubscriber2 {

    @Subscribe
    public void method1(String message) {
        System.out.println("==SimpleSubscriber2==method1==" + message);
    }

    @Subscribe(topic = "test")
    public void method2(String message) {
        System.out.println("==SimpleSubscriber2==method2==" + message);
    }

}
