package com.multithread.chapter03.fight;

import java.util.List;

/**
 * 模拟航班查询
 *
 * @author zt1994 2020/3/11 21:05
 */
public interface FightQuery {

    /**
     * 获取数据
     *
     * @return
     */
    List<String> get();
}
