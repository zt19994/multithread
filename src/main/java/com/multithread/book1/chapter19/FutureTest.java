package com.multithread.book1.chapter19;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * future 测试
 *
 * @author zt1994 2020/5/25 21:45
 */
public class FutureTest {

    /**
     * 测试无返回值
     */
    @Test
    public void noReturnTest() throws InterruptedException {
        FutureService<Void, Void> service = FutureService.newService();
        Future<?> future = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I am finish done");
        });
        future.get();
    }


    /**
     * 测试有返回值
     */
    @Test
    public void returnTest() throws InterruptedException {
        FutureService<String, Integer> service = FutureService.newService();
        Future<Integer> future = service.submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return input.length();
        }, "Hello");
        System.out.println(future.get());
    }


    /**
     * 回调模式测试
     */
    @Test
    public void callBackTest() throws InterruptedException {
        FutureService<String, Integer> service = FutureService.newService();
        Future<Integer> future = service.submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return input.length();
        }, "Hello World", System.out::println);
        System.out.println(future.get());
    }
}
