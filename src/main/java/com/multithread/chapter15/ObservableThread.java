package com.multithread.chapter15;

import java.time.temporal.ChronoUnit;

/**
 * 观察任务线程
 *
 * @author zt1994 2020/5/16 20:59
 */
public class ObservableThread<T> extends Thread implements Observable {

    private final TaskLifecycle<T> lifecycle;

    private final Task<T> task;

    private Cycle cycle;

    /**
     *指定task的实现
     */
    public ObservableThread(Task<T> task) {
        this(new TaskLifecycle.EmptyLifecycleL<>(), task);
    }


    public ObservableThread(TaskLifecycle<T> lifecycle, Task<T> task) {
        super();
        // task 不能为 null
        if (task == null) {
            throw new IllegalArgumentException("The task is required.");
        }
        this.lifecycle = lifecycle;
        this.task = task;
    }

    @Override
    public final void run() {
        // 在执行线程逻辑单元时，分别触发相应的事件
        this.update(Cycle.STARTED, null,null);
        try {
            this.update(Cycle.RUNNING, null, null);

            T result = this.task.call();
            this.update(Cycle.DONG, result, null);
        } catch (Exception e) {
            this.update(Cycle.ERROR, null, e);
        }
    }


    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if (lifecycle == null) {
            return;
        }
        try {
            switch (cycle) {
                case STARTED:
                    this.lifecycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.lifecycle.onRunning(currentThread());
                    break;
                case DONG:
                    this.lifecycle.onFinish(currentThread(), result);
                    break;
                case ERROR:
                    this.lifecycle.onError(currentThread(), e);
                    break;
            }
        } catch (Exception ex) {
            if (cycle == Cycle.ERROR) {
                throw ex;
            }
        }
    }


    @Override
    public Cycle getCycle() {
        return null;
    }
}
