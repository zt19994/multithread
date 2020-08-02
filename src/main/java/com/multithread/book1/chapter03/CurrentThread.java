package com.multithread.book1.chapter03;

/**
 * 当前线程
 * <p>
 * currentThread 用于返回当前执行线程的引用
 *
 * @author zt1994 2020/3/9 21:13
 */
public class CurrentThread {

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() == this);
            }
        };
        thread.start();

        String name = Thread.currentThread().getName();
        System.out.println("main".equals(name));
    }
}
