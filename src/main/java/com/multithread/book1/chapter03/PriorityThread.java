package com.multithread.book1.chapter03;

import com.multithread.T;
import org.junit.Test;

/**
 * 线程的优先级
 *
 * 线程优先级高的获取cpu调度的机会高
 * 设置线程优先级同样是hint操作
 *
 * cpu比较忙，设置优先级可能会获得更多的cpu时间片，但是闲时优先级的高低几乎不会有任何作用
 *
 * 线程优先级不能小于1也不能大于10，如果指定的线程优先级大于线程所在group的优先级，指定的优先级将失效，会默认设为group的优先级
 *
 * @author zt1994 2020/3/9 20:59
 */
public class PriorityThread {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
           while (true) {
               System.out.println("t1");
           }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                System.out.println("t2");
            }
        });
        t2.setPriority(10);
        t1.start();
        t2.start();
    }


    /**
    * 测试优先级
     *
     * 输出结果为7，即最大线程优先级不能超过group的优先级
    */
    @Test
    public void priorityTest(){
        ThreadGroup test = new ThreadGroup("test");
        test.setMaxPriority(7);

        Thread thread = new Thread(test, "test-thread");
        thread.setPriority(10);
        System.out.println(thread.getPriority());
    }


    /**
    * 测试默认优先级
    */
    @Test
    public void defaultPriorityTest(){
        Thread t1 = new Thread();
        System.out.println("t1 优先级：" + t1.getPriority());

        Thread t2 = new Thread(() -> {
            Thread t3 = new Thread();
            System.out.println("t3 优先级：" + t3.getPriority());
        });

        t2.setPriority(6);
        t2.start();
        System.out.println("t2 优先级：" + t2.getPriority());
    }
}
