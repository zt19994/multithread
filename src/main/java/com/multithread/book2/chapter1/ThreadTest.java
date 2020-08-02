package com.multithread.book2.chapter1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Thread继承
 *
 * @author zt1994 2020/8/2 11:14
 */
public class ThreadTest {

    /**
     * 继承Thread类并重写run方法
     */
    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("I am a child thread.");
        }
    }


    /**
     * 实现Runnable接口
     */
    public static class RunnableTask implements Runnable {
        @Override
        public void run() {
            System.out.println("I am a child thread.");
        }
    }


    /**
     * FutureTask方式
     */
    public static class CallerTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "hello";
        }
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();

        RunnableTask task = new RunnableTask();
        new Thread(task).start();
        new Thread(task).start();

        // 创建异步任务
        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
        new Thread(futureTask).start();
        try {
            // 等待任务执行完毕，并返回结果
            String result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
