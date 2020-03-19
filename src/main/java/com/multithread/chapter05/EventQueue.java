package com.multithread.chapter05;

import java.util.LinkedList;

import static java.lang.Thread.currentThread;

/**
 * 事件队列
 * <p>
 * 同步阻塞消息处理：系统处理时间长，系统并发量受限，频繁创建开启和销毁线程，增加系统额外开销，当业务达到峰值时，大量业务处理
 * 线程阻塞会导致频繁的CPU上下文切换，从而降低系统性能。
 * <p>
 * 异步非阻塞消息处理：系统并发和吞吐量大，服务端线程可控制在一定范围，减少创建线程带来的资源浪费，缺点是需要再次查询结果值。
 *
 * wait()使线程阻塞
 * public final void wait() throws InterruptedException
 * public final void wait(long timeout) throws InterruptedException
 * public final void wait(long timeout) throws InterruptedException
 * 1、wait方法的三个重载方法都调用wait(long timeout),wait()等价于wait(0),0代表永不超时
 * 2、Object的wait(long timeout)方法会导致当前线程进入阻塞，直到有其他线程调用了Object的notify或者notifyAll方法，或阻塞时间到达
 * timeout才能唤醒
 * 3、wait方法必须拥有该对象的monitor，也就是wait方法必须在同步方法中使用
 * 4、当前线程执行了该对象的wait方法之后，将会放弃对该monitor的所有权并且进入与该对象关联的wait set中，即一旦线程执行了某个
 * Object的wait方法之后，它就会释放对该对象monitor的所有权，其他线程也会有机会继续争抢该monitor的所有权
 *
 *
 * notify()唤醒阻塞线程
 * 1、唤醒单个正在执行该对象wait方法的线程
 * 2、如果有某个线程由于执行该对象的wait方法而进入阻塞则会被唤醒，如果没有则会忽略
 * 3、被唤醒的线程需要重新获取对该对象所关联monitor的lock才能继续执行
 *
 * 注意：
 * 1、wait方法是可以打断的，即可以interrupt方法打断
 * 2、线程执行了wait方法后，会加入与之对应的wait set中，每个对象的monitor都有一个与之关联的wait set
 * 3、当线程进入wait set之后，notify方法可以将其唤醒，也就是从wait set中弹出，同时中断wait中的线程也会将其唤醒
 * 4、必须在同步方法中使用wait和notify方法，因为执行wait和notify的前提条件是必须持有同步方法的monitor的所有权
 * 5、同步代码的monitor必须与执行wait notify方法的对象一致，简单地说就是用哪个对象的monitor进行同步，就只能用哪个对象进行
 * wait和notify操作
 *
 * wait和sleep
 * 1、wait和sleep方法都可以是线程进入阻塞状态
 * 2、wait和sleep方法均是可中断方法，被中断后都将收到中断异常
 * 3、wait是Object的方法，而sleep是Thread特有的方法
 * 4、wait方法的执行必须是同步方法中进行，而sleep则不需要
 * 5、线程在同步方法中执行sleep方法时，并不会释放monitor的锁，而wait方法则会释放monitor的锁
 * 6、sleep方法短暂休眠之后会主动退出阻塞，而wait方法（没有指定wait时间）则需要被其他线程中断后才能退出阻塞
 * @author zt1994 2020/3/19 20:33
 */
public class EventQueue {

    private final int max;

    static class Event {

    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();

    private static final int DEFAULT_MAX_EVENT = 10;

    public EventQueue() {
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue(int max) {
        this.max = max;
    }


    /**
     * 增加event
     *
     * @param event
     */
    public void offer(Event event) {
        synchronized (eventQueue) {
            if (eventQueue.size() >= max) {
                try {
                    console("queue已满");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            console("提交新的一个event");
            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }


    /**
     * 提取event
     *
     * @return
     */
    public Event take() {
        synchronized (eventQueue) {
            if (eventQueue.isEmpty()) {
                try {
                    console("queue已空");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Event event = eventQueue.removeFirst();
            this.eventQueue.notify();
            console("event " + event + "已处理");
            return event;
        }
    }


    /**
     * 输出信息
     *
     * @param message
     */
    private void console(String message) {
        System.out.printf("%s:%s\n", currentThread().getName(), message);
    }
}
