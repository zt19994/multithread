package com.multithread.book1.chapter23;

/**
 * 超时等待异常
 *
 * @author zt1994 2020/6/11 21:12
 */
public class WaitTimeoutExeption extends Exception {

    public WaitTimeoutExeption(String message) {
        super(message);
    }
}
