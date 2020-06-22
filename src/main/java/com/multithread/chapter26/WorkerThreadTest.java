package com.multithread.chapter26;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 流水线模式测试
 *
 * @author zt1994 2020/6/22 22:12
 */
public class WorkerThreadTest {

    public static void main(String[] args) {
        final ProductionChannel channel = new ProductionChannel(5);
        AtomicInteger productionNo = new AtomicInteger();
        IntStream.range(1, 8).forEach(i ->
                new Thread(() -> {
                    while (true) {
                        channel.offerProduction(new Production(productionNo.getAndIncrement()));
                        try {
                            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start()
        );
    }
}
