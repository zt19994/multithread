package com.multithread.chapter28;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * 文件改变事件
 *
 * @author zt1994 2020/7/6 21:08
 */
public class FileChangeEvent {

    private final Path path;

    private final WatchEvent.Kind<?> kind;

    public FileChangeEvent(Path path, WatchEvent.Kind<?> kind) {
        this.path = path;
        this.kind = kind;
    }

    public Path getPath() {
        return path;
    }

    public WatchEvent.Kind<?> getKind() {
        return kind;
    }
}
