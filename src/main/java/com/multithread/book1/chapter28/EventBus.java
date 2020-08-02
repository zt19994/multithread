package com.multithread.book1.chapter28;

import java.util.concurrent.Executor;

/**
 * 同步事件
 *
 * @author zt1994 2020/6/30 21:48
 */
public class EventBus implements Bus {

    /**
     * 用于维护Subscriber的注册表
     */
    private final Registry registry = new Registry();

    private String busName;

    /**
     * 默认名称
     */
    private final static String DEFAULT_BUS_NAME = "default";

    /**
     * 默认主题
     */
    private final static String DEFAULT_TOPIC = "default-topic";

    /**
     * 用于分发广播消息到各个Subscriber的类
     */
    private final Dispatcher dispatcher;

    public EventBus() {
        this(DEFAULT_BUS_NAME, null, Dispatcher.SEQ_EXECUTOR_SERVICE);
    }

    public EventBus(String busName) {
        this(busName, null, Dispatcher.SEQ_EXECUTOR_SERVICE);
    }

    EventBus(String busName, EventExceptionHandler exceptionHandler, Executor executor) {
        this.busName = busName;
        this.dispatcher = Dispatcher.newDispatcher(exceptionHandler, executor);
    }

    public EventBus(EventExceptionHandler exceptionHandler) {
        this(DEFAULT_BUS_NAME, exceptionHandler, Dispatcher.SEQ_EXECUTOR_SERVICE);
    }

    /**
     * 订阅注册委托给Registry
     *
     * @param subscriber
     */
    @Override
    public void register(Object subscriber) {
        this.registry.bind(subscriber);
    }


    /**
     * 解除注册同样委托给Registry
     *
     * @param subscriber
     */
    @Override
    public void unregister(Object subscriber) {
        this.registry.unbind(subscriber);
    }


    /**
     * 提交默认event到默认topic
     *
     * @param event
     */
    @Override
    public void post(Object event) {
        this.post(event, DEFAULT_TOPIC);
    }


    /**
     * 提交event到指定topic，具体的动作是由Dispatcher来完成的
     *
     * @param event
     * @param topic
     */
    @Override
    public void post(Object event, String topic) {
        this.dispatcher.dispatch(this, registry, event, topic);
    }


    /**
     * 关闭销毁bus
     */
    @Override
    public void close() {
        this.dispatcher.close();
    }


    /**
     * 返回bus的名称
     *
     * @return
     */
    @Override
    public String getBusName() {
        return this.busName;
    }
}
