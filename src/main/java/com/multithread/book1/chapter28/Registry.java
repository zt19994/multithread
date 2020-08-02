package com.multithread.book1.chapter28;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 注册表类
 *
 * @author zt1994 2020/7/1 21:42
 */
class Registry {

    /**
     * 存储Subscriber集合和topic之间的关系的map
     */
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<Subscriber>> subscriberContainer = new ConcurrentHashMap<>();

    public void bind(Object subscriber) {
        List<Method> subscribeMethods = getSubscribeMethods(subscriber);
        subscribeMethods.forEach(m -> tierSubscriber(subscriber, m));
    }


    /**
     * 解除绑定
     *
     * @param subscriber
     */
    public void unbind(Object subscriber) {
        // unbind为了提高速度，只对subscriber进行失效操作
        subscriberContainer.forEach((key, queue) -> queue.forEach(s -> {
            if (s.getSubscribeObject() == subscriber) {
                s.setDisable(true);
            }
        }));
    }


    /**
     * 扫描订阅者
     *
     * @param topic
     * @return
     */
    public ConcurrentLinkedQueue<Subscriber> scanSubscriber(final String topic) {
        return subscriberContainer.get(topic);
    }


    private void tierSubscriber(Object subscriber, Method method) {
        final  Subscribe subscribe = method.getDeclaredAnnotation(Subscribe.class);
        String topic = subscribe.topic();
        // 当某topic没有Subscriber Queue的时候创建一个
        subscriberContainer.computeIfAbsent(topic, key -> new ConcurrentLinkedQueue<>());

        // 创建一个subscriber并且加入subscriber列表中
        subscriberContainer.get(topic).add(new Subscriber(subscriber, method));
    }


    /**
     * 获取订阅方法
     *
     * @param subscriber
     * @return
     */
    private List<Method> getSubscribeMethods(Object subscriber) {
        final List<Method> methods = new ArrayList<>();
        Class<?> temp = subscriber.getClass();
        // 不断获取当前类和父类的所以@Subscribe方法
        while (temp != null) {
            // 获取所有方法
            Method[] declaredMethods = temp.getDeclaredMethods();
            // 只有public方法并且有一个入参并且是被@Subscribe标记的方法才符合回调方法
            Arrays.stream(declaredMethods)
                    .filter(m -> m.isAnnotationPresent(Subscribe.class)
                            && m.getParameterCount() == 1
                            && m.getModifiers() == Modifier.PUBLIC)
                    .forEach(methods::add);
            temp = temp.getSuperclass();
        }
        return methods;
    }

}
