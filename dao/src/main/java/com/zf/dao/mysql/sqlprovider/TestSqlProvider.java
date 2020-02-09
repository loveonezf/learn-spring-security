package com.zf.dao.mysql.sqlprovider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class TestSqlProvider {

    private String TABLE_NAME = "learn.test";

    public String queryTestByID(@Param(value = "id") Integer id) {

        String COLUMNS_NAME = "id,name,status";
        return new SQL() {{
            SELECT(COLUMNS_NAME);
            FROM(TABLE_NAME);
            WHERE("id=#{id}");
        }}.toString();
    }
}
