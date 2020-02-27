package com.multithread.chapter01;

/**
 * 实现runnable的模拟呼叫机
 *
 * @author zt1994 2020/2/27 21:12
 */
public class TicketWindowRunnable implements Runnable {

    private int index = 1;

    private static final int MAX = 50;

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println(Thread.currentThread() + " 的号码是：" + (index++));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TicketWindowRunnable ticketTask = new TicketWindowRunnable();

        Thread t1 = new Thread(ticketTask, "一号机");
        t1.start();

        Thread t2 = new Thread(ticketTask, "一号机");
        t2.start();

        Thread t3 = new Thread(ticketTask, "一号机");
        t3.start();

        Thread t4 = new Thread(ticketTask, "一号机");
        t4.start();
    }
}
