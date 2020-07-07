package com.multithread.chapter29.base;

import com.multithread.chapter29.base.Event;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Event loop 处理
 *
 * @author zt1994 2020/7/7 20:56
 */
public class FooEventDrivenExample {

    public static void handleEventA(Event e) {
        System.out.println(e.getData().toLowerCase());
    }

    public static void handleEventB(Event e) {
        System.out.println(e.getData().toUpperCase());
    }

    public static void main(String[] args) {
        Queue<Event> events = new LinkedList<>();
        events.add(new Event("A", "Hello"));
        events.add(new Event("A", "I am Event A"));
        events.add(new Event("B", "I am Event B"));
        events.add(new Event("B", "World"));
        Event e;
        while (!events.isEmpty()) {
            e = events.remove();
            switch (e.getType()) {
                case "A":
                    handleEventA(e);
                    break;
                case "B":
                    handleEventB(e);
                    break;
            }
        }
    }
}
