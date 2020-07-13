package com.zf.dao.mysql.sys;

import com.zf.model.sys.UserDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

    @Select("SELECT u.id,u.loginName,u.passWord,u.status FROM sys_user u WHERE u.loginName=#{loginName}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "loginName", column = "loginName"),
            @Result(property = "passWord", column = "passWord"),
            @Result(property = "status", column = "status")
    })
    UserDO getUserByLoginName(@Param(value = "loginName") String loginName);
}
