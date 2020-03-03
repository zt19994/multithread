package com.multithread.chapter02;

/**
 * 守护线程
 * <p>
 * JVM 正常情况下，若没有一个非守护线程，则进程会退出
 *
 * @author zt1994 2020/3/3 20:50
 */
public class DaemonThread {


    /**
     * 当注释掉设置thread为守护线程时，JVM进程不会推出，因为有一个非法的守护线程在运行
     *
     * 1、setDaemon 必须在线程启动start之前
     * 2、isDaemon 判断是否为守护线程
     * 3、父类为守护线程，则子类也是守护线程
     * 4、守护线程具有自动结束生命周期的特性
     * 5、守护线程经常用作执行一些后台任务，有称为后台线程
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 将thread设置为守护线程
        thread.setDaemon(true);

        thread.start();
        Thread.sleep(2_000L);
        System.out.println("Main 线程完成生命周期");
    }
}
