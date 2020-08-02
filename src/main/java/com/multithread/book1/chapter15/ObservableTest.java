package com.multithread.book1.chapter15;

import java.util.concurrent.TimeUnit;

/**
 * 线程观察测试
 *
 * @author zt1994 2020/5/16 21:12
 */
public class ObservableTest {

    public static void main(String[] args) {
        ObservableThread observableThread = new ObservableThread<>(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" finished done");
            return null;
        });

        observableThread.start();
    }
}
