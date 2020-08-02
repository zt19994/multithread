package com.multithread.book1.chapter27;

import java.util.Map;

/**
 * 处理订单
 *
 * @author zt1994 2020/6/27 21:25
 */
public class OrderMessage extends MethodMessage {

    public OrderMessage(Map<String, Object> params, OrderService orderService) {
        super(params, orderService);
    }

    @Override
    public void execute() {
        // 获取参数
        String account = (String) params.get("account");
        long orderId = (long) params.get("orderId");

        // 执行真正的order方法
        orderService.order(account, orderId);
    }
}
