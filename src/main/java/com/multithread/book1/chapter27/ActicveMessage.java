package com.multithread.book1.chapter27;

import com.multithread.book1.chapter19.Future;

import java.lang.reflect.Method;

/**
 * activeMessage只在框架内使用，不会对外暴露
 *
 * @author zt1994 2020/6/28 21:12
 */
class ActicveMessage {

    /**
     * 接口方法的参数
     */
    private final Object[] objects;

    /**
     * 接口方法
     */
    private final Method method;

    /**
     * 有返回值的方法
     */
    private final ActiveFuture<Object> future;

    /**
     * 具体的service接口
     */
    private final Object service;

    public ActicveMessage(Builder builder) {
        this.objects = builder.objects;
        this.method = builder.method;
        this.future = builder.future;
        this.service = builder.service;
    }


    /**
     * ActiveMessage 的方法通过反射的方式调用执行的具体实现
     */
    public void execute() {
        try {
            // 执行接口的方法
            Object result = method.invoke(service, objects);
            if (future != null) {
                Future<?> realFuture = (Future<?>) result;
                Object realResult = realFuture.get();
                future.finish(realResult);
            }
        } catch (Exception e) {
            if (future != null) {
                future.finish(null);
            }
        }
    }

    static class Builder {
        private Object[] objects;

        private Method method;

        private ActiveFuture<Object> future;

        private Object service;

        public Builder useMethod(Method method) {
            this.method = method;
            return this;
        }

        public Builder returnFuture(ActiveFuture<Object> future) {
            this.future = future;
            return this;
        }

        public Builder withObjects(Object[] objects) {
            this.objects = objects;
            return this;
        }

        public Builder forService(Object service) {
            this.service = service;
            return this;
        }

        public ActicveMessage build() {
            return new ActicveMessage(this);
        }
    }
}
