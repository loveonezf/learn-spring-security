package com.zf.service;

import com.zf.model.TestDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestService {

    /**
     * @param id
     * @return
     */
    List<TestDO> getTestById(@Param(value = "id") Integer id);
}
