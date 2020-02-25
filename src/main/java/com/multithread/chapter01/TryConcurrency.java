package com.multithread.chapter01;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 快速开始一个多线程 程序只输出浏览网页新闻，未并发执行
 *
 * 线程的生命周期：
 * 五个过程：new、runnable、running、blocked、terminated
 *
 * 1、new 即创建一个线程，还没听start开始执行线程
 * 2、runnable 调用start可以让线程进入可执行状态，runnable只会意外终止或进入running状态
 * 3、running 线程真正执行的状态，running也是runnable，单runnable不是running状态；
 *   a、running进入terminated状态，调用stop
 *   b、running进入blocked状态，调用sleep或wait加入waitSet
 *   c、running由于某个阻塞的IO操作，因为网络数据的读写进入blocked状态
 *   d、running由于获取某个锁资源，加入该锁的阻塞队列进入blocked状态
 *   e、running由于CPU的调度器轮询使该线程放弃执行，进入runnable状态
 *   f、running由于线程主动调用yield方法，放弃CPU执行权，进入runnable状态
 * 4、blocked 状态可切换为以下状态；
 *   a、blocked直接进入terminated状态，调用stop方法，或意外死亡（JVM Crash）
 *   b、blocked由于线程阻塞操作结束，如读取了想要的数据字节进入runnable状态
 *   c、blocked由于完成了指定时间的休眠，进入runnable状态
 *   d、blocked由于wait中的线程被其他线程notify/notifyall唤醒，进入runnable状态
 *   e、blocked由于线程获取到了某个锁资源，进入runnable状态
 *   f、blocked由于线程在阻塞过程中被打断，如其他线程调用了interrupt方法，进入runnable状态
 * 5、terminated 状态，是线程最终的状态，该状态将不会切换到其他状态，进入terminated状态意味着该线程的整个生命周期的结束。
 *   a、线程正常结束，结束生命周期
 *   b、线程运行出错，意外结束
 *   c、JVM Crash，导致所有的线程都结束
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
