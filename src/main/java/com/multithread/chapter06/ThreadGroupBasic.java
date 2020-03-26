package com.multithread.chapter06;

import java.util.concurrent.TimeUnit;

/**
 * ThreadGroup的基本操作
 *
 * 1、activeCount() 获取group中活跃的线程，估计值，并不能百分之百保证数字一定正确，该方法会递归获取其他子group中的活跃线程。
 * 2、activeGroupCount()用于获取group中活跃的子group，也是近似估值，该方法也会递归获取所有的子group
 * 3、getMaxPriority()用于获取group的优先级，Group优先级最大为10，所有线程的优先级不能大于group的优先级
 * 4、getName()用于获取group的名字
 * 5、getParent()用于获取group的父group，如果父group不存在，则返回null
 * 6、list()该方法没有返回值，执行该方法会将group中所有的活跃线程信息全部输出到控制台
 * 7、parentOf(ThreadGroup g)会判断group的最大优先级，最大优先级不能超过父group的最大优先级，执行该方法不仅会改变当前group
 * 的最大优先级，还会改变所有子group的优先级
 *
 * @author zt1994 2020/3/26 20:41
 */
public class ThreadGroupBasic {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group1 = new ThreadGroup("group1");
        Thread thread = new Thread(group1, () -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread");
        thread.setDaemon(true);
        thread.start();

        TimeUnit.MILLISECONDS.sleep(1);

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        System.out.println("activeCount=" + mainGroup.activeCount());
        System.out.println("activeGroupCount=" + mainGroup.activeGroupCount());
        System.out.println("getMaxPriority=" + mainGroup.getMaxPriority());
        System.out.println("getName=" + mainGroup.getName());
        System.out.println("getParent=" + mainGroup.getParent());
        mainGroup.list();
        System.out.println("----------------");
        System.out.println("parentOf=" + mainGroup.parentOf(group1));
        System.out.println("parentOf=" + mainGroup.parentOf(mainGroup));
    }
}
