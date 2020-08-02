package com.multithread.book1.chapter22;

/**
 * balking 设计模式测试
 *
 * @author zt1994 2020/6/2 21:55
 */
public class BalkingTest {

    public static void main(String[] args) {
        new DocumentEditThread("E:\\", "balking.txt").start();
    }
}
