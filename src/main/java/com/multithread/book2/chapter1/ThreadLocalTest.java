package com.multithread.book2.chapter1;

/**
 * ThreadLocal 测试
 *
 * @author zt1994 2020/8/3 14:31
 */
public class ThreadLocalTest {

    /**
     * print 函数
     *
     * @param str
     */
    static void print(String str) {
        // 打印当前线程本地内存中的localVariable变量
        System.out.println(str + ":" + localVariable.get());
        // 清除当前线程本地内存中的localVariable变量
        localVariable.remove();
    }


    static ThreadLocal<String> localVariable = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                // 设置线程中本地变量localVariable的值
                localVariable.set("threadA local variable");
                print("threadA");
                System.out.println("threadA remove after:" + localVariable.get());
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                // 设置线程中本地变量localVariable的值
                localVariable.set("threadB local variable");
                print("threadB");
                System.out.println("threadB remove after:" + localVariable.get());
            }
        });

        threadA.start();
        threadB.start();
    }
}
