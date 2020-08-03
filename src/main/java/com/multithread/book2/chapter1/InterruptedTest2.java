package com.multithread.book2.chapter1;

/**
 * 中断测试
 *
 * @author zt1994 2020/8/3 11:01
 */
public class InterruptedTest2 {

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                // 中断标志为true时退出循环，清除中断标志
                while (!Thread.currentThread().interrupted()) {

                }
                System.out.println("threadA isInterrupted:" + Thread.currentThread().isInterrupted());
            }
        });

        threadA.start();
        threadA.interrupt();
        threadA.join();
        System.out.println("main thread is over");
    }
}
