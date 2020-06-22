package com.multithread.chapter26;

/**
 * 产品说明书
 *
 * @author zt1994 2020/6/22 21:38
 */
public abstract class InstructionBook {

    public final void create() {
        this.firstProcess();
        this.secondProcess();
    }

    /**
     * 产品处理步骤1
     */
    protected abstract void firstProcess();

    /**
     * 产品处理步骤2
     */
    protected abstract void secondProcess();
}
