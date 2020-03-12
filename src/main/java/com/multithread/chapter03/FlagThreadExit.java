package com.multithread.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * volatile flag 退出线程
 *
 * @author zt1994 2020/3/12 20:46
 */
public class FlagThreadExit {

    static class MyTask extends Thread {
        private volatile boolean closed = false;

        @Override
        public void run() {
            System.out.println("I will start work");
            while (!closed && !isInterrupted()) {
                // working
            }
            System.out.println("I will be exiting");
        }

        public void close() {
            this.closed = true;
            this.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask myTask = new MyTask();
        myTask.start();
        TimeUnit.MINUTES.sleep(1);
        System.out.println("System will be shutdown");
        myTask.close();
    }
}
