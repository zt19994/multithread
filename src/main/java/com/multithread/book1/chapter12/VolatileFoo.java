package com.multithread.book1.chapter12;

import java.util.concurrent.TimeUnit;

/**
 * volatile 关键字解析
 *
 * volatile 关键字只能修饰类变量和实例变量，对于参数，局部变量以及实例常量，类常量都不能进行修饰，比如下面的MAX
 *
 * @author zt1994 2020/5/11 21:02
 */
public class VolatileFoo {

    final static int MAX = 5;

    static volatile int init_value = 0;

    public static void main(String[] args) {
        //启动一个reader线程，当发现local_value和init_value不同时，则输出init_value
        new Thread(() -> {
           int localValue = init_value;
           while (localValue < MAX) {
               if (init_value != localValue) {
                   System.out.printf("The init_value is updated to [%d]\n", init_value);
                   localValue = init_value;
               }
           }
        }, "Reader").start();

        new Thread(() -> {
            int localValue = init_value;
            while (localValue < MAX) {
                // 修改init_value值
                localValue++;
                System.out.printf("The init_value will be changed to [%d]\n", localValue);
                init_value = localValue;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Updater").start();
    }
}
