package com.multithread.chapter06;

/**
 * 线程组的销毁
 * <p>
 * destroy用于销毁ThreadGroup，该方法只针对一个没有任何active线程的group，如果group中有active线程存在，将出现异常
 *
 * @author zt1994 2020/4/2 21:36
 */
public class ThreadGroupDestroy {

    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("TestGroup");

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println("group.isDestroy=" + group.isDestroyed());
        mainGroup.list();

        group.destroy();
        System.out.println("group.isDestroy=" + group.isDestroyed());
        mainGroup.list();
    }
}
