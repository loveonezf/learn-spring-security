package com.zf.service.impl;

import com.zf.dao.mysql.TestDAO;
import com.zf.model.TestDO;
import com.zf.service.TestService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDAO testDAO;

    /**
     * @param id
     * @return
     */
    public List<TestDO> getTestById(Integer id) {
        var testList = testDAO.getTestById(id);
        return testList;
    }
}
