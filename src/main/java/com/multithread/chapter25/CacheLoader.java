package com.multithread.chapter25;

/**
 * @author zt1994 2020/6/15 21:46
 */
@FunctionalInterface
public interface CacheLoader<K, V> {

    /**
     * 定义加载数据的方式
     *
     * @param k
     * @return
     */
    V load(K k);
}
