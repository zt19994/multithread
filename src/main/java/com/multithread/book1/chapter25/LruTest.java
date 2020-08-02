package com.multithread.book1.chapter25;

/**
 * 测试
 *
 * @author zt1994 2020/6/16 20:58
 */
public class LruTest {

    public static void main(String[] args) {
        LRUCache<String, Reference> cache = new LRUCache<>(5, key -> new Reference());
        cache.get("Alex");
        cache.get("Jack");
        cache.get("Peter");
        cache.get("Mark");
        cache.get("Leo");
        // 默认顺序 leo  Mark Peter Jack Alex
        cache.get("Jenny");
        // Alex 将被移除
        System.out.println(cache.toString());
    }
}
