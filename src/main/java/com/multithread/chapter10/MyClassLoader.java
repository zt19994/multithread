package com.multithread.chapter10;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 自定义类加载器 基于磁盘的ClassLoader
 *
 * @author zt1994 2020/4/28 21:23
 */
public class MyClassLoader extends ClassLoader {

    /**
     * 定义默认的class存放路径
     */
    private final static Path DEFAULT_CLASS_DIR = Paths.get("G:", "classloader1");

    private final Path classDir;

    public MyClassLoader() {
        super();
        this.classDir = DEFAULT_CLASS_DIR;
    }

    public MyClassLoader(String classDir) {
        super();
        this.classDir = Paths.get(classDir);
    }

    public MyClassLoader(String classDir, ClassLoader parent) {
        super(parent);
        this.classDir = Paths.get(classDir);
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = this.readClassBytes(name);
        // 如果数据为null或没有读取到任何消息，在抛出异常
        if (null == classBytes || classBytes.length == 0) {
            throw new ClassNotFoundException("Can not load the class " + name);
        }

        // 调用defineClass方法定义class
        return this.defineClass(name, classBytes, 0, classBytes.length);
    }


    /**
     * 将class文件读入内存
     *
     * @param name
     * @return
     */
    private byte[] readClassBytes(String name) throws ClassNotFoundException {
        // 将包名分隔符转换为文件路径分隔符
        String classPath = name.replace(".", "/");
        Path classFullPath = classDir.resolve(Paths.get(classPath + ".class"));
        if (!classFullPath.toFile().exists()) {
            throw new ClassNotFoundException("The class " + name + " not found.");
        }

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Files.copy(classFullPath, baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new ClassNotFoundException("load the class " + name + " occur error.", e);
        }
    }


    @Override
    public String toString() {
        return "My ClassLoader";
    }
}
