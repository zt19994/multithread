package com.multithread.book2.chapter6;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;

/**
 * 生产者消费者
 *
 * @author zt1994 2020/8/5 11:16
 */
public class ProducerAndConsumer {

    final static NonReentrantLock lock = new NonReentrantLock();
    final static Condition notFull = lock.newCondition();
    final static Condition notEmpty = lock.newCondition();

    final static Queue<String> queue = new LinkedBlockingDeque<>();
    final static int queueSize = 10;

    public static void main(String[] args) {
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    // 如果队列满了，则等待
                    while (queue.size() == queueSize) {
                        notEmpty.await();
                    }

                    // 添加元素到队列
                    queue.add("ele");

                    // 唤醒消费线程
                    notFull.signalAll();

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    // 队列空，则等待
                    while (0 == queue.size()) {
                        notFull.await();
                    }

                    // 消费一个元素
                    String ele = queue.poll();
                    // 唤醒生产线程
                    notEmpty.signalAll();

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
