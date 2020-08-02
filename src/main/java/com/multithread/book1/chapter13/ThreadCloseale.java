package com.multithread.book1.chapter13;

/**
 * volatile 特性 无法保证原子性，可以保证可见性和有序性
 *
 * @author zt1994 2020/5/13 21:19
 */
public class ThreadCloseale extends Thread {

    private volatile boolean started = true;

    @Override
    public void run() {
        while (started) {
            // do work
        }
    }

    public void shutdown () {
        this.started = false;
    }
}
