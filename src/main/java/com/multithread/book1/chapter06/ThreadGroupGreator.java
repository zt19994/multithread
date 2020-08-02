package com.multithread.book1.chapter06;

/**
 * threadGroup 创建
 *
 * 1、public ThreadGroup(String name)
 * 2、public ThreadGroup(ThreadGroup parent, String name)
 *
 * @author zt1994 2020/3/24 21:37
 */
public class ThreadGroupGreator {

    public static void main(String[] args) {
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup group1 = new ThreadGroup("Group1");
        System.out.println(group1.getParent() == currentGroup);

        ThreadGroup group2 = new ThreadGroup(group1, "Group2");
        System.out.println(group2.getParent() == group1);
    }
}
