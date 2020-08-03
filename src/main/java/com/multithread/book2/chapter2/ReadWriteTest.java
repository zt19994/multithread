package com.multithread.book2.chapter2;

import java.util.concurrent.TimeUnit;

/**
 * 重排序测试
 *
 * @author zt1994 2020/8/3 16:47
 */
public class ReadWriteTest {

    public static class ReadThread extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                if (ready) {
                    System.out.println(num + num);
                }
                System.out.println("read thread.");
            }
        }
    }

    public static class WriteThread extends Thread {
        @Override
        public void run() {
            num = 2;
            ready = true;
            System.out.println("write thread set over.");
        }
    }

    private static int num = 0;

    private static volatile boolean ready = false;

    public static void main(String[] args) throws InterruptedException {
        ReadThread readThread = new ReadThread();
        readThread.start();

        WriteThread writeThread = new WriteThread();
        writeThread.start();

        TimeUnit.SECONDS.sleep(5);
        readThread.interrupt();
        System.out.println("main exit.");
    }

}
