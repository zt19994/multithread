package com.multithread.book1.chapter06;

import java.util.concurrent.TimeUnit;

/**
 * 复制ThreadGroup
 *
 * public int enumerate(ThreadGroup[] list)
 * public int enumerate(ThreadGroup[] list, boolean recurse)
 *
 * @author zt1994 2020/3/25 21:36
 */
public class ThreadGroupEnumerateThreadGroups {

    /**
     * myGroup1的父group为mainGroup，而myGroup2的父group为myGroup1，所有递归复制的结果为2，不递归的情况为1
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup myGroup1 = new ThreadGroup("MyGroup1");
        ThreadGroup myGroup2 = new ThreadGroup(myGroup1, "MyGroup2");

        TimeUnit.MILLISECONDS.sleep(2);
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        ThreadGroup[] list = new ThreadGroup[mainGroup.activeGroupCount()];

        int recurseSize = mainGroup.enumerate(list);
        System.out.println(recurseSize);

        recurseSize = mainGroup.enumerate(list, false);
        System.out.println(recurseSize);
    }
}
