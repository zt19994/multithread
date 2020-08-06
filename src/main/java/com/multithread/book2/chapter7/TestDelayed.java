package com.multithread.book2.chapter7;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 测试过期延迟队列
 *
 * @author zt1994 2020/8/6 16:55
 */
public class TestDelayed {

    static class DelayedEle implements Delayed {

        // 延迟时间
        private final long delayTime;

        // 到期时间
        private final long expire;

        // 任务名称
        private String taskName;

        public DelayedEle(long delay, String taskName) {
            delayTime = delay;
            this.taskName = taskName;
            expire = System.currentTimeMillis() + delay;
        }


        /**
         * 剩余时间=到期时间-当前时间
         *
         * @param unit
         * @return
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }


        /**
         * 优先级队列里面的优先级规则
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("DelayedEle{");
            sb.append("delay=").append(delayTime);
            sb.append(", expire=").append(expire);
            sb.append(", taskName='").append(taskName).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        DelayQueue<DelayedEle> delayedQueue = new DelayQueue<>();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            DelayedEle element = new DelayedEle(random.nextInt(500), "task" + i);
            delayedQueue.offer(element);
        }

        DelayedEle ele = null;
        try {
            for (; ; ) {
                while ((ele = delayedQueue.take()) != null) {
                    // 出队的顺序和delay时间有关，而与创建任务的顺序无关
                    System.out.println(ele.toString());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
