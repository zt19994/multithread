package com.multithread.chapter25;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * soft LRU 缓存
 * @author zt1994 2020/6/18 21:37
 */
public class SoftLRUCache<K, V> {

    private final LinkedList<K> keyList = new LinkedList<K>();

    /**
     * value 采用 softReference 进行修饰
     */
    private final Map<K, SoftReference<V>> cahce = new HashMap<>();

    private final int capacity;

    private final CacheLoader<K, V> cacheLoader;

    public SoftLRUCache(int capacity, CacheLoader<K, V> cacheLoader) {
        this.capacity = capacity;
        this.cacheLoader = cacheLoader;
    }

    public void put(K key, V value) {
        if (keyList.size() >= capacity) {
            // eldest data
            K eldestKey = keyList.removeFirst();
            cahce.remove(eldestKey);
        }
        if (keyList.contains(key)) {
            keyList.remove(key);
        }

        keyList.addLast(key);
        // 保存softReference
        cahce.put(key, new SoftReference<>(value));
    }


    public V get(K key) {
        V value;
        boolean success = keyList.remove(key);
        if (!success) {
            value = cacheLoader.load(key);
            this.put(key, value);
        } else {
            value = cahce.get(key).get();
            keyList.addLast(key);
        }
        return value;
    }

    @Override
    public String toString() {
        return keyList.toString();
    }
}
