package com.multithread.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * 退出线程
 * <p>
 * deprecated的stop方法，不推荐使用，因为可能会在关闭线程时可能不释放掉monitor的锁。
 * <p>
 * 1、线程结束生命周期正常结束
 * 2、捕获中断信号关闭线程
 * 3、使用volatile开关控制
 *
 * @author zt1994 2020/3/12 20:33
 */
public class InterruptThreadExit {


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("I will start work");
                while (!isInterrupted()) {
                    //working
                    /*try {
                        TimeUnit.MICROSECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        break;
                    }*/
                }
                System.out.println("I will be exiting");
            }
        };

        thread.start();
        TimeUnit.MILLISECONDS.sleep(1);
        System.out.println("System will be shutdown");
        thread.interrupt();
    }
}
