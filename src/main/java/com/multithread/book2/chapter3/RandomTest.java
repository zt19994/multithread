package com.multithread.book2.chapter3;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机生成数测试
 *
 * @author zt1994 2020/8/4 10:55
 */
public class RandomTest {

    public static void main(String[] args) {
        //Random random = new Random();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 10; i++) {
            // 生成随机数 0-5 包含0 不包含5
            System.out.println(random.nextInt(5));
        }
    }
}
