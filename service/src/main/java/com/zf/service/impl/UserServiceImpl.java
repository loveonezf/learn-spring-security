package com.zf.service.impl;

import com.zf.dao.mysql.UserDAO;
import com.zf.model.UserDO;
import com.zf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public UserDO getUserByLoginName(String loginName) {
        return userDAO.getUserByLoginName(loginName);
    }
}
