package com.multithread.chapter09;

/**
 * 测试类的初始化
 *
 * 类的加载就是将class文件中的二进制数据读取到内存中，然后将该字节流所代表的静态存储结构转换为方法区中运行时的数据结构，
 * 并且在堆内存中生成一个该类的java.lang.Class对象，作为访问方法区数据结构的入口。
 *
 * 类加载的最终产物就是堆内存中的class对象。
 *
 * 类的连接阶段
 * 1、验证
 * 2、准备
 * 3、解析
 * @author zt1994 2020/4/22 21:12
 */
public class Singleton {

    private static int x = 0;

    private static int y;

    private static Singleton instance = new Singleton();

    private Singleton() {
        x ++;
        y ++;
    }

    public static Singleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println(instance.x);
        System.out.println(instance.y);
    }
}
