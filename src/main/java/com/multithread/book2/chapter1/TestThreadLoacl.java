package com.multithread.book2.chapter1;

/**
 * 测试ThreadLocal不具有继承性
 * <p>
 * 同一个ThreadLocal变量在父线程中被设置值后，在子线程中是获取不到的
 * <p>
 * InheritableThreadLocal具有继承特性
 *
 * @author zt1994 2020/8/3 15:01
 */
public class TestThreadLoacl {

    //public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("hello world.");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread:" + threadLocal.get());
            }
        });

        thread.start();

        System.out.println("main:" + threadLocal.get());
    }

}
