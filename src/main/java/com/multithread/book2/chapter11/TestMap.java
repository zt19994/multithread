package com.multithread.book2.chapter11;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * map 测试模拟多用户同时进入直播间时map信息的维护
 *
 * putIfAbsent判断key是否存在，否则则返回key对应的value，否则放入值返回null
 *
 * @author zt1994 2020/8/9 19:31
 */
public class TestMap {

    static ConcurrentHashMap<String, List<String>> map= new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list1 = new CopyOnWriteArrayList<>();
                list1.add("device1");
                list1.add("device2");

                List<String> oldList = map.putIfAbsent("topic1", list1);
                if (oldList != null) {
                    oldList.addAll(list1);
                }
                System.out.println(map);
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list1 = new CopyOnWriteArrayList<>();
                list1.add("device11");
                list1.add("device22");

                List<String> oldList = map.putIfAbsent("topic1", list1);
                if (oldList != null) {
                    oldList.addAll(list1);
                }
                System.out.println(map);
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list1 = new CopyOnWriteArrayList<>();
                list1.add("device111");
                list1.add("device222");

                List<String> oldList = map.putIfAbsent("topic2", list1);
                if (oldList != null) {
                    oldList.addAll(list1);
                }
                System.out.println(map);
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }

}
