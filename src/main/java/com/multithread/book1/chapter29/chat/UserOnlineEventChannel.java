package com.multithread.book1.chapter29.chat;

import com.multithread.book1.chapter29.AsyncChannel;
import com.multithread.book1.chapter29.Event;

/**
 * 用户上线事件处理通道
 *
 * @author zt1994 2020/7/8 21:23
 */
public class UserOnlineEventChannel extends AsyncChannel {

    @Override
    protected void handle(Event message) {
        UserOnlineEvent event = (UserOnlineEvent) message;
        System.out.println("用户：" + event.getUser().getName() + " 上线");
    }
}
