package com.multithread.book1.chapter28;

/**
 * 文件改变监听者
 *
 * @author zt1994 2020/7/6 21:13
 */
public class FileChangeListener {

    @Subscribe
    public void onChange(FileChangeEvent event) {
        System.out.printf("%s-%s\n", event.getPath(), event.getKind());
    }
}
