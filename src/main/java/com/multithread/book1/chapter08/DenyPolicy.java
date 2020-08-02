package com.multithread.book1.chapter08;

/**
 * 保护策略 用于当queue的runnable达到了limit上限时，采用何种策略通知提交者
 *
 * @author zt1994 2020/4/8 21:35
 */
@FunctionalInterface
public interface DenyPolicy {

    /**
     * 拒绝方法
     *
     * @param runnable
     * @param threadPool
     */
    void reject(Runnable runnable, ThreadPool threadPool);

    /**
     * 拒绝策略 丢弃任务
     */
    class DiscardDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {

        }
    }


    /**
     * 向提交者抛出异常
     */
    class AbortDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {

        }
    }


    /**
     * 使任务在提交者所在的线程中执行`
     */
    class RunnerDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {

        }
    }
}
