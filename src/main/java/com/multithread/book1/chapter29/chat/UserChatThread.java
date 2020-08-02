package com.multithread.book1.chapter29.chat;

import com.multithread.book1.chapter29.AsyncEventDispatcher;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 聊天用户线程
 *
 * @author zt1994 2020/7/8 21:28
 */
public class UserChatThread extends Thread {

    private final User user;
    private final AsyncEventDispatcher dispatcher;

    public UserChatThread(User user, AsyncEventDispatcher dispatcher) {
        super(user.getName());
        this.user = user;
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        try {
            // 用户上线，发送Online Event
            dispatcher.dispatch(new UserOnlineEvent(user));
            for (int i = 0; i < 5; i++) {
                // 用户发送聊天消息
                dispatcher.dispatch(new UserChatEvent(user, getName() + "-Hello-" + i));
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 用户下线
            dispatcher.dispatch(new UserOfflineEvent(user));
        }
    }
}
