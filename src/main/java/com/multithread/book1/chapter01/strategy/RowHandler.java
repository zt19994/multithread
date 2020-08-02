package com.multithread.book1.chapter01.strategy;

import java.sql.ResultSet;

/**
 * 把数据的封装部分抽取成一个策略接口
 *
 * @author zt1994 2020/2/26 21:17
 */
public interface RowHandler<T> {

    /**
     * 处理接口
     *
     * @param rs
     * @return
     */
    T handle(ResultSet rs);
}
