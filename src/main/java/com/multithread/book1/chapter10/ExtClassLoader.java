package com.multithread.book1.chapter10;

/**
 * 扩展类加载器
 *
 * 扩展类加载器的父类是根加载器，主要用于加载JAVA_HOME下的jre\lb\ext子目录里面的类库。
 * @author zt1994 2020/4/26 21:11
 */
public class ExtClassLoader {

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.ext.dirs"));
    }
}
