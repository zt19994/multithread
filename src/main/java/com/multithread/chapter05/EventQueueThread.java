package com.multithread.chapter05;

import java.util.LinkedList;

import static java.lang.Thread.currentThread;

/**
 * 多线程事务队列
 *
 * notifyAll 唤醒所有等待中的线程
 *
 * @author zt1994 2020/3/19 20:33
 */
public class EventQueueThread {

    private final int max;

    static class Event {

    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();

    private static final int DEFAULT_MAX_EVENT = 10;

    public EventQueueThread() {
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueueThread(int max) {
        this.max = max;
    }


    /**
     * 增加event
     *
     * @param event
     */
    public void offer(Event event) {
        synchronized (eventQueue) {
            while (eventQueue.size() >= max) {
                try {
                    console("queue已满");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            console("提交新的一个event");
            eventQueue.addLast(event);
            eventQueue.notifyAll();
        }
    }


    /**
     * 提取event
     *
     * @return
     */
    public Event take() {
        synchronized (eventQueue) {
            while (eventQueue.isEmpty()) {
                try {
                    console("queue已空");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Event event = eventQueue.removeFirst();
            this.eventQueue.notifyAll();
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
