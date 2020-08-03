package com.multithread.book2.chapter1;

/**
 * 中断测试
 *
 * @author zt1994 2020/8/3 11:01
 */
public class InterruptedTest1 {

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {

                }
            }
        });

        threadA.start();
        threadA.interrupt();

        System.out.println("isInterrupted:" + threadA.isInterrupted());
        // 虽然用的是threadA，但是interrupted()方法是获取的当前线程的中断标记，即主线程的中断标志
        System.out.println("isInterrupted:" + threadA.interrupted());
        System.out.println("isInterrupted:" + Thread.interrupted());
        System.out.println("isInterrupted:" + threadA.isInterrupted());

        threadA.join();
        System.out.println("main thread is over");
    }
}
