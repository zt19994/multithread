package com.multithread.book2.chapter11;

import java.text.SimpleDateFormat;

/**
 * 测试SimpleDateFormat
 *
 * @author zt1994 2020/8/9 19:56
 */
public class TestSimpleDateFormat {

    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        synchronized (simpleDateFormat) {
                            System.out.println(simpleDateFormat.parse("2020-8-9 19:59:59"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }

}
