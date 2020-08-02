package com.multithread.book1.chapter28;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 文件改变测试
 *
 * @author zt1994 2020/7/6 21:14
 */
public class FileChangeTest {

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.
                newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

        final EventBus eventBus = new AsyncEventBus(executor);
        // 注册
        eventBus.register(new FileChangeListener());
        DirectoryTargetMonitor monitor = new DirectoryTargetMonitor(eventBus, "E:\\monitor");
        monitor.startMonitor();
    }
}
