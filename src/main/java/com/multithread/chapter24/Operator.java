package com.multithread.chapter24;

import com.multithread.chapter08.BasicThreadPool;
import com.multithread.chapter08.ThreadPool;

/**
 * 处理者
 *
 * @author zt1994 2020/6/12 20:58
 */
public class Operator {

    private final ThreadPool threadPool = new BasicThreadPool(2, 6, 4, 1000);

    public void call(String business) {
        // 每一个请求创建一个线程去处理
        TaskHandler taskHandler = new TaskHandler(new Request(business));
        threadPool.execute(taskHandler);
    }
}
