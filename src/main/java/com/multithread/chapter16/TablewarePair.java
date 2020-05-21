package com.multithread.chapter16;

/**
 * 一队餐具
 *
 * @author zt1994 2020/5/21 21:09
 */
public class TablewarePair {

    private final TableWare leftTool;

    private final TableWare rightTool;

    public TablewarePair(TableWare leftTool, TableWare rightTool) {
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    public TableWare getLeftTool() {
        return leftTool;
    }

    public TableWare getRightTool() {
        return rightTool;
    }
}
