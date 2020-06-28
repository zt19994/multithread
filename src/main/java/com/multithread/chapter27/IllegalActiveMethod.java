package com.multithread.chapter27;

/**
 * 如果方法不符合则抛出异常
 *
 * @author zt1994 2020/6/28 21:10
 */
public class IllegalActiveMethod extends Exception {

    public IllegalActiveMethod(String message) {
        super(message);
    }
}
