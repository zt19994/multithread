package com.multithread.book1.chapter01;

/**
 * 模拟叫号机程序
 * <p>
 * <p>
 * runnable 接口说明 - 将线程的控制本身和业务逻辑的运行分离开来，达到职责分明、功能单一的原则
 * <p>
 * 创建线程的方式 ：
 * 1、构造一个Thread
 * 2、实现Runnable接口
 * 这种说法不严谨
 * <p>
 * 创建线程只有构造Thread类，但是实现线程的执行单元则有两种方式：
 * 1、重写Thread的run方法
 * 2、实现Runnable接口的run方法，并且将Runnable实例用作构造Thread的参数
 *
 * @author zt1994 2020/2/26 21:00
 */
public class TicketWindow extends Thread {

    /**
     * 柜台名称
     */
    private final String name;

    /**
     * 最大处理50笔业务
     */
    private static final int MAX = 50;

    /**
     * 叫号索引
     */
    private static int index = 1;


    public TicketWindow(String name) {
        this.name = name;
    }


    @Override
    public void run() {
        while (index < MAX) {
            System.out.println("柜台：" + name + " 当前号码时：" + (index++));
        }
    }


    public static void main(String[] args) {
        TicketWindow ticketWindow1 = new TicketWindow("一号机");
        ticketWindow1.start();

        TicketWindow ticketWindow2 = new TicketWindow("二号机");
        ticketWindow2.start();

        TicketWindow ticketWindow3 = new TicketWindow("三号机");
        ticketWindow3.start();

        TicketWindow ticketWindow4 = new TicketWindow("四号机");
        ticketWindow4.start();
    }

}
