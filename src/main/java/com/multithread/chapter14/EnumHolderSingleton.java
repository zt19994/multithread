package com.multithread.chapter14;

/**
 * 枚举Holder单例设计
 *
 * @author zt1994 2020/5/13 21:45
 */
public class EnumHolderSingleton {

    /**
     * 实例变量
     */
    private byte[] data = new byte[1024];

    private EnumHolderSingleton() {
    }

   private enum EnumHolder {
       INSTANCE;
       private EnumHolderSingleton instance;

       EnumHolder() {
           this.instance = new EnumHolderSingleton();
       }

       private EnumHolderSingleton getSingleton() {
           return instance;
       }
   }

    public static EnumHolderSingleton getInstance() {
        return EnumHolder.INSTANCE.getSingleton();
    }
}
