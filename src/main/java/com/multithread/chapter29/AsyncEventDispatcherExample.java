package com.multithread.chapter29;

import java.util.concurrent.TimeUnit;

/**
 * 异步事件分配测试实例
 *
 * @author zt1994 2020/7/7 21:31
 */
public class AsyncEventDispatcherExample {

    /**
     * InputEventHandler需要向Router发生Event
     */
    static class AsyncInputEventHandler extends AsyncChannel {

        private final AsyncEventDispatcher dispatcher;

        public AsyncInputEventHandler(AsyncEventDispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        @Override
        protected void handle(Event message) {
            EventDispatcherExample.InputEvent inputEvent = (EventDispatcherExample.InputEvent) message;
            System.out.printf("X:%d,Y:%d\n", inputEvent.getX(), inputEvent.getY());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int result = inputEvent.getX() + inputEvent.getY();
            dispatcher.dispatch(new EventDispatcherExample.ResultEvent(result));
        }
    }

    /**
     * 处理ResultEvent的Handler(Channel),只是简单的将计算结果输出到控制台
     */
    static class AsyncResultEventHandler extends AsyncChannel {

        @Override
        protected void handle(Event message) {
            EventDispatcherExample.ResultEvent resultEvent = (EventDispatcherExample.ResultEvent) message;
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("结果是：" + resultEvent.getResult());
        }
    }



    public static void main(String[] args) {
        // 构造Router
        AsyncEventDispatcher dispatcher = new AsyncEventDispatcher();
        // 将Event和Handler(Channel)的绑定关系注册到Dispatcher
        dispatcher.registerChannel(EventDispatcherExample.InputEvent.class, new AsyncInputEventHandler(dispatcher));
        dispatcher.registerChannel(EventDispatcherExample.ResultEvent.class, new AsyncResultEventHandler());
        dispatcher.dispatch(new EventDispatcherExample.InputEvent(1, 2));
    }
}
