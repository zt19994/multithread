package com.multithread.book1.chapter04;

/**
 * 实现runnable的模拟呼叫机
 *
 * 注意：当MAX数量太大时，会出现数据不一致问题
 * 1、某个号码略过了，没有出现
 * 2、某个号码被多次显示
 * 3、号码超过了最大值500
 *
 * 三个问题结果都是多个线程对index变量（共享资源）同时操作引起的。
 *
 * 原因分析：
 * 1、号码被略过
 * 线程的执行是由CPU时间片轮询调度的
 * 例如：当thread1和thread2都执行到index=65，thread2将index修改为66后未输出之前，CPU调度器将
 * 执行权力交给了thread1，thread1直接将index增加到了67，那么66就被忽略了。
 *
 * 2、号码重复出现
 * thread1执行index+1，然后CPU执行权到thread2，由于thread1并没有给index赋予计算后的值88，因此thread2执行index+1任然是88。
 *
 * 3、号码超过了最大值
 * 当index=499时，thread1和thread2都满足条件，thread2短暂停顿，thread1将index增加到500，thread2恢复运行又将500加1，得到index
 * 为501，超过了最大值
 *
 * @author zt1994 2020/2/27 21:12
 */
public class TicketWindowRunnable implements Runnable {

    private int index = 1;

    private static final int MAX = 500;

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
