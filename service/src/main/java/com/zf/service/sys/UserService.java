package com.zf.service.sys;

import com.zf.model.sys.UserDO;
import org.apache.ibatis.annotations.Param;

public interface UserService {

    UserDO getUserByLoginName(@Param(value = "loginName") String loginName);

    /**
     * 验证码放入redis
     * @param code
     * @return
     */
    String addValidateCode(String code);

    /**
     * 登录验证码验证
     *
     * @param uuid
     * @return
     */
    String getValidateCode(String uuid);
}
