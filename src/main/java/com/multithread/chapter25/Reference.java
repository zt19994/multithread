package com.multithread.chapter25;

/**
 * 引用
 *
 * @author zt1994 2020/6/15 21:40
 */
public class Reference {

    /**
     * 1M
     */
    private final byte[] data = new byte[2 << 19];

    @Override
    protected void finalize() throws Throwable {
        System.out.println("the reference will be GC.");
    }
}
