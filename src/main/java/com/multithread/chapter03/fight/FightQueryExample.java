package com.multithread.chapter03.fight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 航班查询实例
 *
 * @author zt1994 2020/3/11 21:15
 */
public class FightQueryExample {

    private static List<String> fightCompany = Arrays.asList("CSA", "CEA", "HNA");

    public static void main(String[] args) {
        List<String> results = search("SH", "BJ");
        System.out.println("======result=====");
        results.forEach(System.out::println);
    }

    private static List<String> search(String origin, String destination) {
        final List<String> result = new ArrayList<>();
        // 创建航班搜索线程
        List<FightQueryTask> tasks = fightCompany.stream()
                .map(f -> createSearchTask(f, origin, destination))
                .collect(Collectors.toList());
        // 线程启动
        tasks.forEach(Thread::start);

        // 分别调用一个join方法，阻塞当前线程
        tasks.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        tasks.stream().map(FightQuery::get).forEach(result::addAll);
        return result;
    }


    /**
     * 创建航班查询线程
     *
     * @param fight
     * @param origin
     * @param destination
     * @return
     */
    private static FightQueryTask createSearchTask(String fight, String origin, String destination) {
        return new FightQueryTask(fight, origin, destination);
    }
}
