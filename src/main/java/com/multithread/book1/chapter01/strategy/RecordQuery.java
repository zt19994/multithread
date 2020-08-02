package com.multithread.book1.chapter01.strategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 查询数据
 *
 * query 只负责将数据查询出来，RowHandler 进行数据封装。
 *
 * RecordQuery 只负责数据的获取，RowHandler 负责数据的加工，责任分明，每个类均功能单一。
 *
 * @author zt1994 2020/2/26 21:18
 */
public class RecordQuery {

    private final Connection connection;

    public RecordQuery(Connection connection) {
        this.connection = connection;
    }

    public <T> T query(RowHandler<T> handler, String sql, Object... params) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 1;
            for (Object param : params) {
                statement.setObject(index++, param);
            }

            ResultSet resultSet = statement.executeQuery();
            return handler.handle(resultSet);
        }
    }
}
