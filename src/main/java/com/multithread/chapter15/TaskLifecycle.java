package com.multithread.chapter15;


/**
 * 任务生命周期
 *
 * @author zt1994 2020/5/16 20:40
 */
public interface TaskLifecycle<T> {

    /**
     * 任务启动时会触发onStart方法
     *
     * @param thread
     */
    void onStart(Thread thread);

    /**
     * 任务正在运行时会触发onRunning方法
     *
     * @param thread
     */
    void onRunning(Thread thread);

    /**
     * 任务运行结束时触发onFinish, result 是任务执行结束后的结果
     *
     * @param thread
     * @param result
     */
    void onFinish(Thread thread, T result);

    /**
     * 任务执行报错时会触发onError
     *
     * @param thread
     * @param e
     */
    void onError(Thread thread, Exception e);

    /**
     * 生命周期接口的空实现
     *
     * @param <T>
     */
    class EmptyLifecycleL<T> implements TaskLifecycle<T> {

        @Override
        public void onStart(Thread thread) {

        }

        @Override
        public void onRunning(Thread thread) {

        }

        @Override
        public void onFinish(Thread thread, T result) {

        }

        @Override
        public void onError(Thread thread, Exception e) {

        }
    }
}
