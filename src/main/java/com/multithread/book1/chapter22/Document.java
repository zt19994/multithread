package com.multithread.book1.chapter22;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * 文档类
 *
 * @author zt1994 2020/6/2 21:18
 */
public class Document {

    /**
     * 如果文档发生改变，changed为true
     */
    private boolean changed = false;

    /**
     * 一次需要保存的内容，可以将其理解为内容缓存
     */
    private List<String> context = new ArrayList<>();

    private final FileWriter writer;

    /**
     * 自动保存文档的线程
     */
    private static AutoSaveThread autoSaveThread;

    private Document(String documentPath, String documentName) throws IOException {
        this.writer = new FileWriter(new File(documentPath, documentName));
    }

    public static Document create(String documentPath, String documentName) throws IOException {
        Document document = new Document(documentPath, documentName);
        autoSaveThread = new AutoSaveThread(document);
        autoSaveThread.start();
        return document;
    }


    /**
     * 文档编辑 即 往content队列中提交字符串
     *
     * @param context
     */
    public void edit(String context) {
        synchronized (this) {
            this.context.add(context);
            this.changed = true;
        }
    }


    /**
     * 文档关闭的时候首先中断自动保存线程，然后关闭writer释放资源
     *
     * @throws IOException
     */
    public void close() throws IOException {
        autoSaveThread.interrupt();
        writer.close();
    }


    /**
     * 用于外部显示的保存
     *
     * @throws IOException
     */
    public void save() throws IOException {
        synchronized (this) {
            // 如果文档已经被保存了，则直接返回
            if (!changed) {
                return;
            }

            System.out.println(Thread.currentThread() + " execute the save action");
            // 将内容写入文档
            ListIterator<String> stringListIterator = context.listIterator();
            while (stringListIterator.hasNext()) {
                String cacheLine = stringListIterator.next();
                this.writer.write(cacheLine);
                this.writer.write("\r\n");
                this.writer.flush();
                // 修改changed为false，表示没有新的编辑内容
                this.changed = false;
                stringListIterator.remove();
            }
        }
    }

}
