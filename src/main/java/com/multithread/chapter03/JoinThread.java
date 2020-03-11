package com.multithread.chapter03;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * join 线程中的使用
 * <p>
 * B join A 会使，当前线程B进入等待，直到线程A结束生命周期，或者到达给定的时间，此期间线程B处于blocked状态。
 * join()方法会使当前线程永远地等待下去，直到期间被另外的线程中断，或者join的线程执行结束。
 * 使用join(long millis)和join(long millis, int nanos)，当到达时间也会退出阻塞
 *
 * @author zt1994 2020/3/11 20:27
 */
public class JoinThread {


    /**
     * 交替输出线程1和2，最后输出main线程
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // 定义两个线程保存到Threads
        List<Thread> threads = IntStream.range(1, 3).mapToObj(JoinThread::create).collect(Collectors.toList());
        threads.forEach(Thread::start);

        for (Thread thread : threads) {
            thread.join();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "#" + i);
            shortSleep();
        }
    }


    private static Thread create(int seq) {
        return new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "#" + i);
                shortSleep();
            }
        });
    }

    private static void shortSleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
