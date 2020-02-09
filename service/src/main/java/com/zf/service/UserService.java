package com.zf.service;

import com.zf.model.UserDO;
import org.apache.ibatis.annotations.Param;

public interface UserService {

    UserDO getUserByLoginName(@Param(value = "loginName") String loginName);
}
