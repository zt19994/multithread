package com.multithread.chapter29.chat;

import com.multithread.chapter29.Event;

/**
 * 用户上线事件
 *
 * @author zt1994 2020/7/8 21:20
 */
public class UserOnlineEvent extends Event {

    private final User user;

    public UserOnlineEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
