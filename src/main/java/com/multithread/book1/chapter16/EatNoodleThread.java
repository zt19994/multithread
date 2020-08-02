package com.multithread.book1.chapter16;

/**
 * 吃面线程
 *
 * @author zt1994 2020/5/21 21:00
 */
public class EatNoodleThread extends Thread {

    /**
     * 名称
     */
    private final String name;

    private final TablewarePair tablewarePair;


    public EatNoodleThread(String name, TablewarePair tablewarePair) {
        this.name = name;
        this.tablewarePair = tablewarePair;
    }

    @Override
    public void run() {
        while (true) {
            this.eat();
        }
    }


    /**
     * 吃面的过程
     */
    private void eat() {
        synchronized (tablewarePair) {
            System.out.println(name + "拿起" + tablewarePair.getLeftTool() + "(left)");
            System.out.println(name + "拿起" + tablewarePair.getRightTool() + "(right)");
            System.out.println(name + "正在吃");
            System.out.println(name + "放下" + tablewarePair.getRightTool() + "(right)");
            System.out.println(name + "放下" + tablewarePair.getLeftTool() + "(left)");
        }
    }


    public static void main(String[] args) {
        TableWare fork = new TableWare("fork");
        TableWare knife = new TableWare("knife");
        TablewarePair tablewarePair = new TablewarePair(fork, knife);
        new EatNoodleThread("A", tablewarePair).start();
        new EatNoodleThread("B", tablewarePair).start();
    }
}
