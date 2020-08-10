package com.multithread.book2.chapter11;

import java.util.concurrent.*;

/**
 * Future测试
 *
 * @author zt1994 2020/8/10 16:06
 */
public class FutureTest {

    private final static ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 1, 1l, TimeUnit.MINUTES
            , new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future futureA = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("start runnable A");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Future futureB = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("start runnable B");
            }
        });

        Future futureC = null;
        try {
            futureC = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("start runnable C");
                }
            });
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        System.out.println("task A " + futureA.get());
        System.out.println("task B " + futureB.get());
        System.out.println("task C " + (futureC == null ? null : futureC.get()));

        executorService.shutdown();
    }

}
