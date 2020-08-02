package com.multithread.book1.chapter07;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * 防止重复启动
 * <p>
 * 1、指定文件夹下会生成一个.lock文件，当线程停止后会删除该文件
 * 2、强制杀死进程，不会收到任何中断信号，所有hook无效
 * 3、hook中可以执行一些资源释放的工作，比如关闭句柄、socket链接、数据库connection
 * 4、hook中不要执行一些特别耗时的操作，会让程序退出缓慢
 *
 * @author zt1994 2020/4/7 21:44
 */
public class PreventDuplicated {

    private final static String LOCK_PATH = "E:\\test\\locks";

    private final static String LOCK_FILE = ".lock";

    private final static String PERMISSIONS = "rw-------";

    public static void main(String[] args) throws IOException {
        checkRunning();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("The program received kill SIGNAL.");
            getLockFile().toFile().delete();
        }));

        for (; ; ) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
                System.out.println("program is running.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void checkRunning() throws IOException {
        Path path = getLockFile();
        if (path.toFile().exists()) {
            throw new RuntimeException("The program already running.");
        }

        File file = path.toFile();
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);
        try {//异常处理
            //如果file文件夹下没有test.txt就会创建该文件
            BufferedWriter bw = new BufferedWriter(new FileWriter(absolutePath));
            bw.write(PERMISSIONS);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Path getLockFile() {
        return Paths.get(LOCK_PATH, LOCK_FILE);
    }
}
