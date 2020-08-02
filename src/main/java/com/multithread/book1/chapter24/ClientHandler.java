package com.multithread.book1.chapter24;

import java.io.*;
import java.net.Socket;

/**
 * 客户端处理
 *
 * @author zt1994 2020/6/12 21:14
 */
public class ClientHandler implements Runnable {

    /**
     * 客户端socket连接
     */
    private final Socket socket;

    /**
     * 客户端的identify
     */
    private final String clientIndentify;

    public ClientHandler(final Socket socket) {
        this.socket = socket;
        this.clientIndentify = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
    }


    @Override
    public void run() {
        try {
            this.chat();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.release();
        }
    }


    private void release() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (Throwable e) {

        }
    }

    private void chat() throws IOException {
        BufferedReader bufferedReader = wrap2Reader(this.socket.getInputStream());
        PrintStream printStream = warp2Print(this.socket.getOutputStream());
        String received;
        while ((received = bufferedReader.readLine()) != null) {
            // 将客户端发送的消息输出到控制台
           System.out.printf("client:%s-message:%s\n", clientIndentify, received);
           if (received.equals("quit")) {
               // quit 断开连接
               write2Client(printStream, "client will close");
               socket.close();
               break;
           }
        }
    }


    /**
     * 将输入字节流封装成bufferedReader缓冲字符流
     *
     * @param inputStream
     * @return
     */
    private BufferedReader wrap2Reader(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }


    /**
     * 将输出字节流封装成printStream
     *
     * @param outputStream
     * @return
     */
    private PrintStream warp2Print(OutputStream outputStream) {
        return new PrintStream(outputStream);
    }


    /**
     * 该方法主要用于想客户端发送消息
     *
     * @param print
     * @param message
     */
    private void write2Client(PrintStream print, String message) {
        print.println(message);
        print.flush();
    }
}
