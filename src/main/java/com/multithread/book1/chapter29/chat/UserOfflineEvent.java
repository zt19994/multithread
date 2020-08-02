package com.multithread.book1.chapter29.chat;

/**
 * 用户下线事件
 *
 * @author zt1994 2020/7/8 21:21
 */
public class UserOfflineEvent extends UserOnlineEvent {

    public UserOfflineEvent(User user) {
        super(user);
    }
}
