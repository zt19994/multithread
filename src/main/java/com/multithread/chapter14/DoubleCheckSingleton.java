package com.multithread.chapter14;

/**
 * Double Check 设计单例模式
 * 当线程instance==null时，加锁，不成立时getInstance不要加锁
 *
 * @author zt1994 2020/5/13 21:35
 */
public class DoubleCheckSingleton {

    /**
     * 实例变量
     */
    private byte[] data = new byte[1024];

    private static volatile DoubleCheckSingleton instance = null;

    private DoubleCheckSingleton() {

    }

    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
