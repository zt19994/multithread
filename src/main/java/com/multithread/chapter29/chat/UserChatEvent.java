package com.multithread.chapter29.chat;

/**
 * 用户聊天事件
 *
 * @author zt1994 2020/7/8 21:22
 */
public class UserChatEvent extends UserOnlineEvent {

    /**
     * ChatEvent 需要有聊天的信息
     */
    private final String message;

    public UserChatEvent(User user, String message) {
        super(user);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
