package com.multithread.chapter18;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * int类的叠加器
 *
 * @author zt1994 2020/5/24 18:15
 */
public class IntegerAccumulator {

    private int init;

    public IntegerAccumulator(int init) {
        this.init = init;
    }

    /**
     * 对初始值加一
     *
     * @param i
     * @return
     */
    public int add(int i) {
        this.init += i;
        return this.init;
    }


    /**
     * 获取初始值
     *
     * @return
     */
    public int getValue() {
        return this.init;
    }

    public static void main(String[] args) {
        IntegerAccumulator accumulator = new IntegerAccumulator(0);
        IntStream.range(0, 3).forEach(i -> new Thread(() -> {
            int inc = 0;
            while (true) {
                int oldValue;
                int result;
                synchronized (IntegerAccumulator.class) {
                    oldValue = accumulator.getValue();
                    result = accumulator.add(inc);
                }
                System.out.println(oldValue + "+" + inc + "=" + result);
                if (inc + oldValue != result) {
                    System.err.println("ERROR:" + oldValue + "+" + inc + "=" + result);
                }
                inc++;
                slowly();
            }
        }).start());
    }


    private static void slowly() {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
