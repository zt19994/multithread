package com.multithread.chapter14;

/**
 * Holder 单例设计 利用类加载的特点
 *
 * @author zt1994 2020/5/13 21:42
 */
public class HolderSingleton {

    /**
     * 实例变量
     */
    private byte[] data = new byte[1024];

    private HolderSingleton() {

    }

    private static class Holder {
        private static HolderSingleton instance = new HolderSingleton();
    }

    public static HolderSingleton getInstance() {
        return Holder.instance;
    }
}
