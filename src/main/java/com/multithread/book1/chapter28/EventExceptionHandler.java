package com.multithread.book1.chapter28;

/**
 * 事件异常处理
 *
 * @author zt1994 2020/7/1 21:52
 */
public interface EventExceptionHandler {

    void handle(Throwable cause, EventContext context);
}
