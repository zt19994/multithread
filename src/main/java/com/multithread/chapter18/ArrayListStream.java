package com.multithread.chapter18;

import java.util.Arrays;
import java.util.List;

/**
 * 数组流
 *
 * @author zt1994 2020/5/24 18:10
 */
public class ArrayListStream {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java", "Thread", "Concurrency", "Scala", "Clojure");

        list.parallelStream().map(String::toUpperCase).forEach(System.out::println);
        list.forEach(System.out::println);
    }
}
