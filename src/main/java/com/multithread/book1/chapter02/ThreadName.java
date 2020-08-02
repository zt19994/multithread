package com.multithread.book1.chapter02;

import org.junit.Test;

import java.util.stream.IntStream;

/**
 * 线程命名
 *
 * 线程的父子关系：
 * 1、一个线程的创建肯定是由另一个线程完成的
 * 2、被创建线程的父线程是创建它的线程
 *
 * @author zt1994 2020/3/1 21:27
 */
public class ThreadName {

    /**
     * 线程前缀
     */
    private static final String PREFIX = "ALEX-";

    /**
     * 测试线程默认命名 默认是 Thread-0 Thread + 数字
     */
    @Test
    public void defaultThreadNameTest() {
        IntStream.range(0, 5).boxed().map(i -> new Thread(
                () -> System.out.println(Thread.currentThread().getName())))
                .forEach(Thread::start);
    }


    /**
     * 创建线程名称
     */
    @Test
    public void createThreadNameTest() {
        IntStream.range(0, 5).mapToObj(ThreadName::createThread)
                .forEach(Thread::start);
    }

    private static Thread createThread(final int intName) {
        return new Thread(() -> System.out.println(Thread.currentThread().getName()), PREFIX + intName);
    }


    /**
    * 测试ThreadGroup关系
    */
    @Test
    public void threadGroupTest(){
        Thread t1 = new Thread("t1");

        ThreadGroup group = new ThreadGroup("TestGroup");

        Thread t2 = new Thread(group,"t2");
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        System.out.println("当前线程所属线程组：" + currentGroup.getName());
        System.out.println("t1 和当前线程是否所属一个线程组：" + (currentGroup == t1.getThreadGroup()));
        System.out.println("t2 和当前线程不是一个线程组：" + (currentGroup == t2.getThreadGroup()));
        System.out.println("t2 所属线程组是TestGroup：" + (group == t2.getThreadGroup()));
    }
}
