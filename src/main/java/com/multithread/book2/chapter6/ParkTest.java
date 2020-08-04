package com.multithread.book2.chapter6;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport park方法测试
 *
 * @author zt1994 2020/8/4 20:41
 */
public class ParkTest {

    public static void main(String[] args) {
        System.out.println("begin park");
        // 使线程持有许可证
        LockSupport.unpark(Thread.currentThread());

        LockSupport.park();
        // 默认情况下调用lockSupport类的方法是不持有许可证的，所有线程被阻塞挂起，end park 不会输出
        System.out.println("end park");
    }
}
