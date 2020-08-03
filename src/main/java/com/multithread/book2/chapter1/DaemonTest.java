package com.multithread.book2.chapter1;

/**
 * 守护线程测试
 *
 * @author zt1994 2020/8/3 14:20
 */
public class DaemonTest {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {

                }
            }
        });

        //设置守护线程
        thread.setDaemon(true);
        thread.start();

        System.out.println("main thread is over");
    }
}
