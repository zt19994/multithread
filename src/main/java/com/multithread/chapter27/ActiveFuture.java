package com.multithread.chapter27;

import com.multithread.chapter19.FutureTask;

/**
 * 返回类 重写finish
 *
 * @author zt1994 2020/6/27 21:11
 */
public class ActiveFuture<T> extends FutureTask<T> {


    @Override
    protected void finish(T result) {
        super.finish(result);
    }
}
