package com.multithread.book2.chapter6;

import java.util.concurrent.locks.StampedLock;

/**
 * 管理二维点
 *
 * @author zt1994 2020/8/5 15:00
 */
public class Point {

    // x,y表示二维点坐标
    private double x, y;

    // 锁实例
    private final StampedLock s1 = new StampedLock();

    /**
     * 移动操作
     *
     * @param deltaX
     * @param deltaY
     */
    void move(double deltaX, double deltaY) {
        long stamp = s1.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            s1.unlockWrite(stamp);
        }
    }

    /**
     * 距离原点长度
     *
     * @return
     */
    double distanceFromOrigin() {
        long stamp = s1.tryOptimisticRead();
        double currentX = x;
        double currentY = y;
        // 检查锁有没有被其他写线程抢占
        if (!s1.validate(stamp)) {
            // 如果被抢占则获取一个共享读锁
            stamp = s1.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                s1.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }


    /**
     * 如果在原点则移动
     *
     * @param newX
     * @param newY
     */
    void moveIfAtOrigin(double newX, double newY) {
        long stamp = s1.readLock();
        try {
            while (x == 0.0 && y == 0.0) {
                // 尝试升级读锁为写锁
                long ws = s1.tryConvertToWriteLock(stamp);
                if (ws != 0L) {
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                } else {
                    // 读锁升级失败则释放读锁，显示获取独占写锁，然后循环重试
                    s1.unlockRead(stamp);
                    stamp = s1.writeLock();
                }
            }
        } finally {
            s1.unlock(stamp);
        }
    }
}
