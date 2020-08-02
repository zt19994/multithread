package com.multithread.book1.chapter04;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 关键字提供了一种互斥机制，即同一时刻，只能有一个线程访问同步资源，（某线程获取了与mutex关联的monitor锁）。
 *
 * synchronized注意事项：
 * 1、与monitor关联的对象不能为空
 *
 * 2、synchronized作用域太大
 *
 * 3、不同的monitor企图锁相同的方法
 *
 * 4、多个锁的交叉导致四锁
 *
 * @author zt1994 2020/3/17 20:49
 */
public class Mutex {

    private final static Object MUTEX = new Object();

    public void accessResource() {
        synchronized (MUTEX) {
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final Mutex mutex = new Mutex();
        for (int i = 0; i < 5; i++) {
            new Thread(mutex::accessResource).start();
        }
    }
}
