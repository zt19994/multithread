package com.multithread.chapter24;

import com.multithread.chapter08.BasicThreadPool;
import com.multithread.chapter08.ThreadPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 聊天服务
 *
 * @author zt1994 2020/6/12 21:06
 */
public class ChatServer {

    /**
     * 服务端端口
     */
    private final int port;

    /**
     * 定义线程池
     */
    private ThreadPool threadPool;

    /**
     * 服务端socket
     */
    private ServerSocket serverSocket;

    public ChatServer(int port) {
        this.port = port;
    }


    /**
     * 默认端口为13312
     */
    public ChatServer() {
        this(13312);
    }

    public void startServer() throws IOException {
        // 创建线程池，初始化一个线程
        this.threadPool = new BasicThreadPool(1, 4, 2, 1000);
        this.serverSocket = new ServerSocket(port);
        this.serverSocket.setReuseAddress(true);
        System.out.println("Chat server is started and listen at port:" + port);
        this.listen();
    }

    private void listen() throws IOException {
        for (; ; ) {
            Socket client = serverSocket.accept();
            this.threadPool.execute(new ClientHandler(client));
        }
    }
}
