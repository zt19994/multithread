package com.multithread.book2.chapter1;

import java.util.concurrent.TimeUnit;

/**
 * wait测试释放当前线程的锁，不释放其他线程的锁
 *
 * 线程A只释放了resourceA的锁，没有释放resourceB的锁，
 * 所以下面代码会产生死锁，线程A持有resourceB锁，线程B尝试获取resourceB的锁
 *
 * @author zt1994 2020/8/2 11:47
 */
public class WaitTest1 {

    // 创建资源
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 获取resourceA共享资源的monitor锁
                    synchronized (resourceA) {
                        System.out.println("threadA get resourceA lock");
                        // 获取resourceB共享资源的monitor锁
                        synchronized (resourceB) {
                            System.out.println("threadA get resourceB lock");

                            // 阻塞A，并释放获取到的resourceA的锁
                            System.out.println("threadA release resourceA lock");
                            resourceA.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 休眠1秒，保证threadA先获取到resourceA和resourceB的monitor锁
                    TimeUnit.SECONDS.sleep(1);

                    // 获取resourceA共享资源的monitor锁
                    synchronized (resourceA) {
                        System.out.println("threadB get resourceA lock");

                        System.out.println("threadB try get resourceB lock");
                        // 获取resourceB共享资源的monitor锁
                        synchronized (resourceB) {
                            System.out.println("threadA get resourceB lock");

                            // 阻塞B，并释放获取到的resourceA的锁
                            System.out.println("threadA release resourceA lock");
                            resourceB.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println("main over");

    }

}
