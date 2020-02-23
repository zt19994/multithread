package com.multithread.chapter01;

import java.util.concurrent.TimeUnit;

/**
 * 快速开始一个多线程 程序只输出浏览网页新闻，未并发执行
 *
 * @author zt1994 2020/2/23 20:36
 */
public class TryConcurrency {

    public static void main(String[] args) {
        browseNews();
        enjoyMusic();
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
