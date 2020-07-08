package com.multithread.chapter29.chat;

import com.multithread.chapter29.AsyncChannel;
import com.multithread.chapter29.Event;

/**
 * 用户聊天事件处理
 *
 * @author zt1994 2020/7/8 21:27
 */
public class UserChatEventChannel extends AsyncChannel {

    @Override
    protected void handle(Event message) {
        UserChatEvent event = (UserChatEvent) message;
        System.out.println("用户：" + event.getUser().getName() + " 说：" + event.getMessage());
    }
}
