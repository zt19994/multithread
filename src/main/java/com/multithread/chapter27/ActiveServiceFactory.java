package com.multithread.chapter27;

import com.multithread.chapter19.Future;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 核心主动方法工厂类
 *
 * @author zt1994 2020/6/28 21:30
 */
public class ActiveServiceFactory {

    /**
     * 存放ActiveMessage的队列
     */
    private final static ActiveMessageQueueNew queue = new ActiveMessageQueueNew();


    public static <T> T active(T instance) {
        Object proxy = Proxy.newProxyInstance(instance.getClass().getClassLoader(), instance.getClass().getInterfaces(),
                new ActiveInvocationHandler<>(instance));
        return (T) proxy;
    }

    private static class ActiveInvocationHandler<T> implements InvocationHandler {

        private final T instance;

        public ActiveInvocationHandler(T instance) {
            this.instance = instance;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(ActiveMethod.class)) {
                this.checkMethod(method);
                ActicveMessage.Builder builder = new ActicveMessage.Builder();
                builder.useMethod(method).withObjects(args).forService(instance);
                Object result = null;
                if (this.isReturnFutureType(method)) {
                    result = new ActiveFuture<>();
                    builder.returnFuture((ActiveFuture) result);
                }
                queue.offer(builder.build());
                return result;
            } else {
                return method.invoke(instance, args);
            }
        }

        /**
         * 检查方法
         *
         * @param method
         * @throws IllegalActiveMethod
         */
        private void checkMethod(Method method) throws IllegalActiveMethod {
            if (!isReturnFutureType(method) && !isReturnVoidType(method)) {
                throw new IllegalActiveMethod("the method [" + method.getName() + " return type must be void/Future");
            }
        }


        /**
         * 判断方法是否为Future返回类型
         *
         * @param method
         * @return
         */
        private boolean isReturnFutureType(Method method) {
            return method.getReturnType().isAssignableFrom(Future.class);
        }


        /**
         * 判断方法是否无返回类型
         *
         * @param method
         * @return
         */
        private boolean isReturnVoidType(Method method) {
            return method.getReturnType().equals(Void.TYPE);
        }
    }
}
