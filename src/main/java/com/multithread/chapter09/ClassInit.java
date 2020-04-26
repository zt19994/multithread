package com.multithread.chapter09;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 类的初始化
 * <p>
 * 同一时间，只能有一个线程执行到静态代码块中的内容，并且静态代码块仅仅只会被执行一次。
 *
 * @author zt1994 2020/4/22 21:33
 */
public class ClassInit {

    static {
        try {
            System.out.println("The ClassInit static code black will be invoke.");
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IntStream.range(0, 5).forEach(i -> new Thread(ClassInit::new));
    }
}
