package com.multithread.chapter01;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 快速开始一个多线程 程序只输出浏览网页新闻，未并发执行
 *
 * @author zt1994 2020/2/23 20:36
 */
public class TryConcurrency {

    /**
     * 尝试并发
     */
    @Test
    public void firstTest() {
        browseNews();
        enjoyMusic();
    }

    /**
     * 测试并发运行交替输出
     */
    @Test
    public void concurrencyTest() {
        // 通过匿名内部类的方式创建线程，从写run方法
        new Thread() {
            @Override
            public void run() {
                enjoyMusic();
            }
        }.start();
        browseNews();
    }

    /**
     * lambda 表达式改造
     */
    @Test
    public void lambdaTest() {
        new Thread(TryConcurrency::enjoyMusic).start();
        browseNews();
    }


    /**
     * 浏览网页新闻
     */
    private static void browseNews() {
        for (; ; ) {
            System.out.println("浏览网页新闻");
            sleep(1);
        }
    }

    /**
     * 听音乐
     */
    private static void enjoyMusic() {
        for (; ; ) {
            System.out.println("听音乐");
            sleep(1);
        }
    }

    /**
     * 多线程等待
     *
     * @param seconds
     */
    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
