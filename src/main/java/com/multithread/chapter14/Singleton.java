package com.multithread.chapter14;

/**
 * 饿汉单例设计  无法懒加载
 * 懒汉模式 在多线程中无法保证单例的唯一性
 * 懒汉模式加数据同步 用synchronized修饰，synchronized关键字的排他性，getInstance性能低下
 *
 * @author zt1994 2020/5/13 21:24
 */
public final class Singleton {

    /**
     * 实例变量
     */
    private byte[] data = new byte[1024];

    /**
     * 在定义实例对象的时候直接初始化
     */
    private static Singleton instance = new Singleton();

    // private static Singleton instance = null;  懒汉模式

    /**
     * 私有构造函数，不允许外呼new
     */
    private Singleton() {
    }

    public static Singleton getInstance() {
        // 懒汉模式
        /*if (instance == null) {
            instance = new Singleton();
        }*/
        return instance;
    }

    // 懒汉模式加数据同步
    /*public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }*/
}
