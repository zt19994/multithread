package com.multithread.chapter19;

/**
 * future 实现接口
 *
 * @author zt1994 2020/5/25 21:21
 */
public interface FutureService<IN, OUT> {

    /**
     * 提交不需要返回值的任务，Future.get 方法会返回null
     *
     * @param runnable
     * @return
     */
    Future<?> submit(Runnable runnable);

    /**
     * 提交需要返回值的任务 task接口代替了runnable接口
     *
     * @param task
     * @param input
     * @return
     */
    Future<OUT> submit(Task<IN, OUT> task, IN input);

    /**
     * 提交需要返回值的任务 task接口代替了runnable接口, callBack回调接口
     *
     * @param task
     * @param input
     * @param callBack
     * @return
     */
    Future<OUT> submit(Task<IN, OUT> task, IN input, CallBack<OUT> callBack);

    /**
     * 使用静态方法创建一个FutureService的实现
     *
     * @param <T>
     * @param <R>
     * @return
     */
    static <T, R> FutureService<T, R> newService() {
        return new FutureServiceImpl<>();
    }
}
