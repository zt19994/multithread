package com.multithread.book1.chapter10;

/**
 * 系统类加载器，用于加载第三方jar，也是自定义类加载器的默认父加载器
 *
 * @author zt1994 2020/4/26 21:23
 */
public class ApplicationClassLoader {

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(ApplicationClassLoader.class.getClassLoader());
    }
}
