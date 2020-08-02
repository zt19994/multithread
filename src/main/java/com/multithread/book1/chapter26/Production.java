package com.multithread.book1.chapter26;

/**
 * 产品
 *
 * @author zt1994 2020/6/22 21:43
 */
public class Production extends InstructionBook {

    /**
     * 产品id
     */
    private final int productId;

    public Production(int productId) {
        this.productId = productId;
    }

    @Override
    protected void firstProcess() {
        System.out.println("进行编号" + productId + "的产品第一步处理");
    }

    @Override
    protected void secondProcess() {
        System.out.println("进行编号" + productId + "的产品第二步处理");
    }
}
