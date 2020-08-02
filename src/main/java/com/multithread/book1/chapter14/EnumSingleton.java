package com.multithread.book1.chapter14;

/**
 * 枚举单例设计
 *
 * @author zt1994 2020/5/13 21:45
 */
public enum EnumSingleton {

    INSTANCE;
    /**
     * 实例变量
     */
    private byte[] data = new byte[1024];

    EnumSingleton() {
        System.out.println("INSTANCE will be initialized immediately");
    }

    public static void method() {
        // 调用该方法则会主动适应singleton，INSTANCE将会被初始化
    }

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
