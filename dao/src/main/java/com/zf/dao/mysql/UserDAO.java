package com.zf.dao.mysql;

import com.zf.model.UserDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

    @Select("select u.loginname,u.pwd,u.logintime from `user` u WHERE u.loginname=#{loginName}")
    @Results({
            @Result(property = "loginName", column = "loginname"),
            @Result(property = "pwd", column = "pwd"),
            @Result(property = "logintime", column = "logintime"),
    })
    UserDO getUserByLoginName(@Param(value = "loginName") String loginName);
}
