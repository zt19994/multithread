package com.multithread.book2.chapter11;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 测试SimpleDateFormat ThreadLocal实现线程安全
 *
 * @author zt1994 2020/8/9 19:56
 */
public class TestSimpleDateFormat2 {

    static ThreadLocal<DateFormat> safeSdf = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(safeSdf.get().parse("2020-8-9 19:59:59"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        // 使用完毕后清除，避免内存泄漏
                        safeSdf.remove();
                    }
                }
            });
            thread.start();
        }
    }

}
