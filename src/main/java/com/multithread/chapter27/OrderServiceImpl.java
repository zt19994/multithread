package com.multithread.chapter27;


import com.multithread.chapter19.Future;
import com.multithread.chapter19.FutureService;

import java.util.concurrent.TimeUnit;

/**
 * order service 实现
 *
 * @author zt1994 2020/6/27 19:59
 */
public class OrderServiceImpl implements OrderService {


    /**
     * 根据订单编号查询订单明细
     *
     * @param orderId
     * @return
     */
    @Override
    public Future<String> findOrderDetails(long orderId) {
        return FutureService.<Long, String>newService().submit(input -> {
            try {
                // 模拟执行任务
                TimeUnit.SECONDS.sleep(10);
                System.out.println("process the orderId -> " + orderId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "The order Details Information";
        }, orderId, null);
    }


    /**
     * 提交订单，没有返回值
     *
     * @param account
     * @param orderId
     */
    @Override
    public void order(String account, long orderId) {
        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println("process the order for account " + account + " , orderId " + orderId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
