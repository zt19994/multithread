package com.multithread.chapter08;

/**
 * 运行时策略异常
 *
 * @author zt1994 2020/4/8 21:40
 */
public class RunnableDenyException extends RuntimeException {

    public RunnableDenyException(String message) {
        super(message);
    }
}
