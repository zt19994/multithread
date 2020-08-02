package com.multithread.book1.chapter03;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 线程中断 interrupt
 * <p>
 * Object wait()
 * Object wait(long)
 * Object wait(long,int)
 * Thread sleep(long)
 * Thread sleep(long,int)
 * Thread join
 * Thread join(long)
 * Thread join(long,int)
 * InterruptibleChannel 的 io 操作
 * Selector 的 wakeup 方法
 * <p>
 * 上面方法可以使线程进入阻塞状态，调用当前线程的interrupt方法，可以打断阻塞
 *
 * @author zt1994 2020/3/10 21:10
 */
public class InterruptThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Oh, I am be interrupted.");
            }
        });

        thread.start();

        TimeUnit.MICROSECONDS.sleep(2);
        // 调用interrupt方法打断了阻塞
        thread.interrupt();
    }


    /**
     * 测试isInterrupted
     */
    @Test
    public void isInterruptedTest() throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    // do nothing, just empty loop.
                }
            }
        };

        thread.start();
        TimeUnit.MICROSECONDS.sleep(2);
        System.out.println("线程是isInterrupted吗？ " + thread.isInterrupted());
        thread.interrupt();
        System.out.println("线程是isInterrupted吗？ " + thread.isInterrupted());
    }


    /**
     * 测试捕获中断信号
     */
    @Test
    public void catchInterruptedTest() throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                        // 捕获interrupt后会将interrupt标识复原
                        System.out.println("I am be interrupted?" + isInterrupted());
                    }
                }
            }
        };

        thread.setDaemon(true);
        thread.start();
        TimeUnit.MICROSECONDS.sleep(2);
        System.out.println("线程是isInterrupted吗？ " + thread.isInterrupted());
        thread.interrupt();
        TimeUnit.MICROSECONDS.sleep(2);
        System.out.println("线程是isInterrupted吗？ " + thread.isInterrupted());
    }


    /**
     * interrupted 方法  interrupted 判断到了其被中断，立即擦除了中断标识，并且只有一次返回true，后面都将返回false
     */
    @Test
    public void interruptedTest() throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.interrupted());
                }
            }
        };
        thread.setDaemon(true);
        thread.start();

        TimeUnit.MICROSECONDS.sleep(2);
        thread.interrupt();
    }


    /**
     * 测试可中断方法
     */
    @Test
    public void test() {
        System.out.println("Main 线程is interrupted?" + Thread.interrupted());

        // 中断
        Thread.currentThread().interrupt();
        // isInterrupted 不擦除中断标识
        System.out.println("Main 线程is interrupted?" + Thread.currentThread().isInterrupted());

        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            // 由于isInterrupted 不擦除中断标识，所有会被中断捕获
            System.out.println("I will be interrupted still.");
        }
    }
}
