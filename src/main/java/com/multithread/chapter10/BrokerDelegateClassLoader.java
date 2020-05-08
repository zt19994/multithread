package com.multithread.chapter10;

/**
 * @author zt1994 2020/5/6 21:21
 */
public class BrokerDelegateClassLoader extends ClassLoader {

    // ... 省略代码


    Boolean resolve = true;


    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            Class<?> klass = findLoadedClass(name);
            if (klass == null) {
                if (name.startsWith("java.") || name.startsWith("javax")) {
                    try {
                        klass = getSystemClassLoader().loadClass(name);
                    } catch (Exception e) {
                        //ignore
                    }
                }
            } else {
                try {
                    klass = this.findClass(name);
                } catch (ClassNotFoundException e) {
                    // ignore
                }
                if (klass == null) {
                    if (getParent() != null) {
                        klass = getParent().loadClass(name);
                    } else {
                        klass = getSystemClassLoader().loadClass(name);
                    }
                }
            }

            if (null == klass) {
                throw  new ClassNotFoundException("The class " + name + " not found.");
            }

            if (resolve) {
                resolveClass(klass);
            }
            return klass;
        }


    }
}
