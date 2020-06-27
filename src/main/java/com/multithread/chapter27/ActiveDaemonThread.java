package com.multithread.chapter27;

/**
 * 保护线程
 *
 * @author zt1994 2020/6/27 21:38
 */
class ActiveDaemonThread extends Thread {

    private final ActiveMessageQueue queue;

    public ActiveDaemonThread(ActiveMessageQueue queue) {
        super("ActiveDaemonThread");
        this.queue = queue;
        setDaemon(true);
    }

    @Override
    public void run() {
        for (;;) {
            // 从messages队列中获取一个methodMessage，然后执行execute方法
            MethodMessage methodMessage = this.queue.take();
            methodMessage.execute();
        }
    }
}
