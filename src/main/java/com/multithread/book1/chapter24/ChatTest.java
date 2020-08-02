package com.multithread.book1.chapter24;

import java.io.IOException;

/**
 * 聊天测试
 *
 * @author zt1994 2020/6/12 21:25
 */
public class ChatTest {

    public static void main(String[] args) throws IOException {
        new ChatServer().startServer();
    }
}
