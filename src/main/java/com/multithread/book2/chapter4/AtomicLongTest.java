package com.multithread.book2.chapter4;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 原子操作测试
 *
 * @author zt1994 2020/8/4 13:10
 */
public class AtomicLongTest {

    // 创建Long型原子计数器
    private static AtomicLong atomicLong = new AtomicLong();

    private static Integer[] arrayOne = new Integer[]{0, 1, 2, 3, 1, 0, 11, 0, 4, 0};
    private static Integer[] arrayTwo = new Integer[]{0, 4, 0, 8, 92, 0, 2, 3, 0};

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                int size = arrayOne.length;
                for (int i = 0; i < size; i++) {
                    if (arrayOne[i].intValue() == 0) {
                        atomicLong.incrementAndGet();
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                int size = arrayTwo.length;
                for (int i = 0; i < size; i++) {
                    if (arrayTwo[i] == 0) {
                        atomicLong.incrementAndGet();
                    }
                }
            }
        });

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println("count 0:" + atomicLong.get());
    }

}
