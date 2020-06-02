package com.multithread.chapter22;

import java.io.IOException;
import java.util.Scanner;

/**
 * 文档编辑线程
 *
 * @author zt1994 2020/6/2 21:49
 */
public class DocumentEditThread extends Thread {

    private final String documentPath;

    private final String documentName;

    private final Scanner scanner = new Scanner(System.in);

    public DocumentEditThread(String documentPath, String documentName) {
        super("DocumentEditThread");
        this.documentPath = documentPath;
        this.documentName = documentName;
    }

    @Override
    public void run() {
        int times = 0;

        try {
            Document document = Document.create(documentPath, documentName);
            while (true) {
                // 获取用户的键盘输入
                String text = scanner.next();
                if ("quit".equals(text)) {
                    document.close();
                    break;
                }
                // 将内容编辑到document中
                document.edit(text);
                if (times == 5) {
                    document.save();
                    times = 0;
                }
                times++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
