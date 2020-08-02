package com.multithread.book1.chapter25;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 最少引用缓存
 *
 * @author zt1994 2020/6/15 21:43
 */
public class LRUCache<K, V> {

    /**
     * 用于记录key值的顺序
     */
    private final LinkedList<K> keyList = new LinkedList<>();

    /**
     * 用于存放数据
     */
    private final Map<K, V> cache = new HashMap<>();

    /**
     * cache 的最大容量
     */
    private final int capacity;

    /**
     * 定义加载数据的方法
     */
    private final CacheLoader<K, V> cacheLoader;

    public LRUCache(int capacity, CacheLoader<K, V> cacheLoader) {
        this.capacity = capacity;
        this.cacheLoader = cacheLoader;
    }

    public void put(K key, V value) {
        // 当元素超过容量时，将最老的数据清除
        if (keyList.size() >= capacity) {
            K eldestKey = keyList.removeFirst();
            cache.remove(eldestKey);
        }
        // 如果数据已存在，则将key从cache中删除
        if (keyList.contains(key)) {
            keyList.remove(key);
        }
        // 将key存放入队尾
        cache.put(key, value);
    }

    public V get(K key) {
        V value;
        // 先将key从key list移除
        boolean success = keyList.remove(key);
        // 如果失败则表明该数据不存在
        if (!success) {
            // 通过cache loader对数据进行加载
            value = cacheLoader.load(key);
            // 调用put方法cache数据
            this.put(key, value);
        } else {
            // 如果删除成功，则从cache中返回数据，并且将key再次放入队尾
            value = cache.get(key);
            keyList.addLast(key);
        }
        return value;
    }

    @Override
    public String toString() {
        return this.keyList.toString();
    }
}
