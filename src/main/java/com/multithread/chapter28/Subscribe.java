package com.multithread.chapter28;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解event bus指定接收消息时的回调方法
 *
 * @author zt1994 2020/6/30 21:44
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Subscribe {

    String topic() default "default-topic";
}
