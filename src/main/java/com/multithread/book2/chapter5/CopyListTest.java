package com.multithread.book2.chapter5;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList 测试
 * <p>
 * 迭代器的弱一致性，修改和迭代器的数组是两个新旧数组，修改的是新数组，迭代器的是旧数组
 *
 * @author zt1994 2020/8/4 19:20
 */
public class CopyListTest {

    private static volatile CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        arrayList.add("hello");
        arrayList.add("world");
        arrayList.add("你好");
        arrayList.add("世界");
        arrayList.add("!");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                arrayList.set(1, "修改");
                arrayList.remove(2);
                arrayList.remove(3);
            }
        });

        // 保证在修改线程启动前获取迭代器
        Iterator<String> iterator = arrayList.iterator();

        thread.start();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("arrayList:" + arrayList);
    }

}
