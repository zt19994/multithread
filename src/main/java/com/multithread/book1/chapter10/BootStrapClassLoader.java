package com.multithread.book1.chapter10;

/**
 * 根类加载器测试
 * <p>
 * jvm内置三大类加载器
 * 1、根类加载器
 * 2、扩展类加载器
 * 3、系统类加载器
 *
 * @author zt1994 2020/4/26 21:06
 */
public class BootStrapClassLoader {

    public static void main(String[] args) {
        //String.class的类加载器时根加载器，根加载器是获取不到引用的，因此输出为null
        System.out.println("BootStrap:" + String.class.getClassLoader());
        // 根加载器所在路径
        System.out.println(System.getProperty("sun.boot.class.path"));
    }
}
