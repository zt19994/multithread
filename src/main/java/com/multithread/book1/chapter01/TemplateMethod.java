package com.multithread.book1.chapter01;

/**
 * 模板设计模式
 * <p>
 * print方法类似于Thread的start方法，wrapPrint类似于run方法。
 * 好处在于，程序结构有父类控制，并且是final修饰的，不允许被重写，子类只需要实现想要的逻辑任务即可。
 *
 * @author zt1994 2020/2/25 20:57
 */
public class TemplateMethod {

    /**
     * 打印信息
     *
     * @param message
     */
    public final void print(String message) {
        System.out.println("------");
        wrapPrint(message);
        System.out.println("------");
    }

    /**
     * 包装
     *
     * @param message
     */
    protected void wrapPrint(String message) {

    }


    public static void main(String[] args) {
        TemplateMethod t1 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("-" + message + "-");
            }
        };
        t1.print("Hello world!");

        TemplateMethod t2 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("+" + message + "+");
            }
        };
        t2.print("Hello Thread!");
    }

}
