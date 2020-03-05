package com.multithread.chapter03;

import java.util.stream.IntStream;

/**
 * yield 的作用
 * <p>
 * yield 方法属于一种启发式的方法，会提醒调度器我愿意放弃当前的CPU资源，如果CPU资源不紧张，则会忽略这种提醒。
 * 会使当前线程从running状态切换到runnable状态
 * <p>
 * yield和sleep
 * sleep会导致当前线程暂停指定的时间，没有CPU时间片的消耗
 * yield只是对CPU调度器的一个提示，如果CPU调度器没有忽略这个提示，它会导致线程上下文的切换
 * sleep会使线程短暂block，会在给定的时间内释放CPU资源。
 * yield会使running状态的thread进入runnable状态（如果CPU调度器没有忽略这个提示的话）
 * sleep几乎百分之百的完成了给定时间的休眠，而yield的提示并不能一定担保
 * 一个线程sleep另一个线程调用interrupt会捕获到中断信号，而yield不会。
 *
 * @author zt1994 2020/3/5 20:43
 */
public class YieldThread {

    public static void main(String[] args) {
        IntStream.range(0, 2).mapToObj(YieldThread::create).forEach(Thread::start);
    }

    private static Thread create(int index) {
        return new Thread(() -> {
            if (index == 0) {
                Thread.yield();
            }
            System.out.println(index);
        });
    }
}
