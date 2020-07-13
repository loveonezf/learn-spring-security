package com.zf.service.sys.impl;

import com.zf.dao.mysql.sys.UserDAO;
import com.zf.model.sys.UserDO;
import com.zf.service.dmodel.ValidateCode;
import com.zf.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final String validateCodeCacheKey = "validate_code-";

    public UserDO getUserByLoginName(String loginName) {
        return userDAO.getUserByLoginName(loginName);
    }

    /**
     * 验证码放入redis
     *
     * @param code
     * @return
     */
    public String addValidateCode(String code) {
        String uuid = UUID.randomUUID().toString();
        // 添加图形验证码到redis, 缓存5分钟
        redisTemplate.opsForValue().set(validateCodeCacheKey + uuid, code, 5, TimeUnit.MINUTES);
        return uuid;
    }

    /**
     * 登录验证码验证
     *
     * @param uuid
     * @return
     */
    public String getValidateCode(String uuid) {
        String validateCode = redisTemplate.opsForValue().get(validateCodeCacheKey + uuid);
        return validateCode;
    }

}
