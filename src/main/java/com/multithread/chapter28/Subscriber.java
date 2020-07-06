package com.multithread.chapter28;

import java.lang.reflect.Method;

/**
 * 订阅者
 *
 * @author zt1994 2020/7/1 21:44
 */
public class Subscriber {

    private final Object subscribeObject;

    private final Method subscribeMethod;

    private boolean disable = false;

    public Subscriber(Object subscribeObject, Method subscribeMethod) {
        this.subscribeObject = subscribeObject;
        this.subscribeMethod = subscribeMethod;
    }

    public Object getSubscribeObject() {
        return subscribeObject;
    }

    public Method getSubscribeMethod() {
        return subscribeMethod;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}
