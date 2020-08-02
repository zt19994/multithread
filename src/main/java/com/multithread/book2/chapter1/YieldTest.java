package com.multithread.book2.chapter1;

/**
 * yield测试
 *
 * @author zt1994 2020/8/2 16:32
 */
public class YieldTest implements Runnable {

    public YieldTest() {
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            // 当i=0时让出CPU执行权
            if ((i % 5) == 0) {
                System.out.println(Thread.currentThread() + "yield cpu.");

                //Thread.yield();
            }
            System.out.println(Thread.currentThread() + " is over.");
        }
    }

    public static void main(String[] args) {
        new YieldTest();
        new YieldTest();
        new YieldTest();
    }
}
