package com.multithread.book1.chapter27;


import com.multithread.book1.chapter19.Future;

/**
 * order service
 *
 * @author zt1994 2020/6/27 19:54
 */
public interface OrderService {

    /**
     * 根据订单编号查询订单明细
     *
     * @param orderId
     * @return
     */
    Future<String> findOrderDetails(long orderId);

    /**
     * 提交订单，没有返回值
     *
     * @param account
     * @param orderId
     */
    void order(String account, long orderId);
}
