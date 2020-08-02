package com.multithread.book1.chapter29.base;

/**
 * 事件event包含类型和数据
 *
 * @author zt1994 2020/7/7 20:50
 */
public class Event {

    private final String type;

    private final String data;

    public Event(String type, String data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }
}
