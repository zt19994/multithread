package com.multithread.book1.chapter16;

/**
 * 餐具
 *
 * @author zt1994 2020/5/21 20:59
 */
public class TableWare {

    /**
     * 餐具名称
     */
    private final String toolName;

    public TableWare(String toolName) {
        this.toolName = toolName;
    }

    @Override
    public String toString() {
        return "TableWare{" +
                "toolName='" + toolName + '\'' +
                '}';
    }
}
