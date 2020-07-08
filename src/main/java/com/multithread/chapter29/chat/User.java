package com.multithread.chapter29.chat;

/**
 * 用户
 *
 * @author zt1994 2020/7/8 21:19
 */
public class User {

    private final String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
