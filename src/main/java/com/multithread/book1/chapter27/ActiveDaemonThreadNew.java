package com.multithread.book1.chapter27;

/**
 * 保护线程
 *
 * @author zt1994 2020/6/27 21:38
 */
class ActiveDaemonThreadNew extends Thread {

    private final ActiveMessageQueueNew queue;

    public ActiveDaemonThreadNew(ActiveMessageQueueNew queue) {
        super("ActiveDaemonThread");
        this.queue = queue;
        setDaemon(true);
    }

    @Override
    public void run() {
        for (;;) {
            // 从messages队列中获取一个methodMessage，然后执行execute方法
            ActicveMessage acticveMessage = this.queue.take();
            acticveMessage.execute();
        }
    }
}
