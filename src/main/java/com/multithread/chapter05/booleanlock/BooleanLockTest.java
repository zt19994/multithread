package com.multithread.chapter05.booleanlock;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

/**
 * booleanLock 测试
 *
 * @author zt1994 2020/3/23 21:33
 */
public class BooleanLockTest {

    private final Lock lock = new BooleanLock();

    /**
     * 同步方法
     */
    public void syncMethod() {
        try {
            lock.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            int randomInt = ThreadLocalRandom.current().nextInt(10);
            System.out.println(Thread.currentThread() + "获取锁");
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    /**
     * 阻塞的线程可超时方法
     */
    public void syncMethodTimeoutAble() {
        try {
            lock.lock(1000);
            System.out.println(Thread.currentThread() + "获取锁");
            int randomInt = ThreadLocalRandom.current().nextInt(10);
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BooleanLockTest lockTest = new BooleanLockTest();
        IntStream.range(0, 10)
                .mapToObj(i -> new Thread(lockTest::syncMethod))
                .forEach(Thread::start);
    }


    /**
     * 可中断测试
     */
    @Test
    public void syncMethodTest() throws InterruptedException {
        BooleanLockTest lockTest = new BooleanLockTest();
        new Thread(lockTest::syncMethod, "T1").start();
        TimeUnit.MILLISECONDS.sleep(2);
        Thread t2 = new Thread(lockTest::syncMethod, "T2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(10);
        t2.interrupt();
    }


    /**
    * 超时测试
    */
    @Test
    public void syncMethodTimeoutAbleTest() throws InterruptedException {
        BooleanLockTest lockTest = new BooleanLockTest();
        new Thread(lockTest::syncMethod, "T1").start();
        TimeUnit.MILLISECONDS.sleep(2);
        Thread t2 = new Thread(lockTest::syncMethodTimeoutAble, "T2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(10);
    }
}
