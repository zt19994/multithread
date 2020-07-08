package com.multithread.chapter29.chat;

import com.multithread.chapter29.AsyncEventDispatcher;

/**
 * 用户聊天应用测试
 *
 * @author zt1994 2020/7/8 21:34
 */
public class UserChatApplication {

    public static void main(String[] args) {
        // 定义异步Router
        final AsyncEventDispatcher dispatcher = new AsyncEventDispatcher();
        // 为Router注册Channel和Event之间的关系
        dispatcher.registerChannel(UserOnlineEvent.class, new UserOnlineEventChannel());
        dispatcher.registerChannel(UserOfflineEvent.class, new UserOfflineEventChannel());
        dispatcher.registerChannel(UserChatEvent.class, new UserChatEventChannel());

        new UserChatThread(new User("Leo"), dispatcher).start();
        new UserChatThread(new User("Alex"), dispatcher).start();
        new UserChatThread(new User("Lina"), dispatcher).start();
    }
}
