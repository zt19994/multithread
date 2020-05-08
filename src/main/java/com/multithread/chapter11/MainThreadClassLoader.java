package com.multithread.chapter11;

/**
 * main的上下文类加载器
 *
 * @author zt1994 2020/5/8 21:32
 */
public class MainThreadClassLoader {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader());
    }
}
