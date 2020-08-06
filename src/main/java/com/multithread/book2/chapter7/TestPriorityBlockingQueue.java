package com.multithread.book2.chapter7;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 优先级阻塞队列测试
 *
 * @author zt1994 2020/8/6 16:27
 */
public class TestPriorityBlockingQueue {

    static class Task implements Comparable<Task> {

        private int priority = 0;

        private String taskName;

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public int compareTo(Task o) {
            if (this.priority >= o.getPriority()) {
                return 1;
            } else {
                return -1;
            }
        }

        public void doSomeThing() {
            System.out.println(taskName + ":" + priority);
        }
    }


    public static void main(String[] args) {
        PriorityBlockingQueue<Task> priorityQueue = new PriorityBlockingQueue<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setPriority(random.nextInt(10));
            task.setTaskName("taskName" + i);
            priorityQueue.offer(task);
        }

        while (!priorityQueue.isEmpty()) {
            Task task = priorityQueue.poll();
            if (null != task) {
                // 执行任务的先后顺序和它们被放入队列的先后顺序没有关系，而是和它们的优先级有关系
                task.doSomeThing();
            }
        }

    }
}
