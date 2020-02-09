package com.zf.dao.mysql;

import com.zf.dao.mysql.sqlprovider.TestSqlProvider;
import com.zf.model.TestDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDAO {

    /**
     * @param id
     * @return
     */
    @SelectProvider(type = TestSqlProvider.class, method = "queryTestByID")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "status", column = "status")
    })
    List<TestDO> getTestById(@Param(value = "id") Integer id);
}
