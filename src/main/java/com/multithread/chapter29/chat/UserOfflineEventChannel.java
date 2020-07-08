package com.multithread.chapter29.chat;

import com.multithread.chapter29.AsyncChannel;
import com.multithread.chapter29.Event;

/**
 * 用户离线事件处理通道
 *
 * @author zt1994 2020/7/8 21:25
 */
public class UserOfflineEventChannel extends AsyncChannel {

    @Override
    protected void handle(Event message) {
        UserOfflineEvent event = (UserOfflineEvent) message;
        System.out.println("用户：" + event.getUser().getName() + " 下线");
    }
}
