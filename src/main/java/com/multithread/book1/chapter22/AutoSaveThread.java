package com.multithread.book1.chapter22;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 自动保存线程
 *
 * @author zt1994 2020/6/2 21:34
 */
public class AutoSaveThread extends Thread {

    private final Document document;

    public AutoSaveThread(Document document) {
        super("DocumentAutoSaveThread");
        this.document = document;
    }

    @Override
    public void run() {
        while (true) {
            try {
                document.save();
                TimeUnit.SECONDS.sleep(1);
            } catch (IOException | InterruptedException e){
                break;
            }
        }
    }
}
