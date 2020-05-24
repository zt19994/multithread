package com.multithread.chapter18;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 不可变的int累加器
 *
 * @author zt1994 2020/5/24 18:28
 */
public class IntegerAccumulatorFinal {

    private final int init;

    public IntegerAccumulatorFinal(int init) {
        this.init = init;
    }

    /**
     * 构造新的累加器，需要用到另外一个accumulator和初始值
     * @param accumulatorFinal
     * @param init
     */
    public IntegerAccumulatorFinal(IntegerAccumulatorFinal accumulatorFinal, int init) {
        this.init = accumulatorFinal.getValue() + init;
    }


    /**
     * 每次相加都会产生一个新的accumulator
     * @param i
     * @return
     */
    public IntegerAccumulatorFinal add(int i) {
        return new IntegerAccumulatorFinal(this, i);
    }

    public int getValue() {
        return this.init;
    }


    public static void main(String[] args) {
        IntegerAccumulatorFinal accumulator = new IntegerAccumulatorFinal(0);
        IntStream.range(0, 3).forEach(i -> new Thread(() -> {
            int inc = 0;
            while (true) {
                int oldValue = accumulator.getValue();
                int result = accumulator.add(inc).getValue();
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
