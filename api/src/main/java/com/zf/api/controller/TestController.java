package com.zf.api.controller;

import com.google.common.base.Joiner;
import com.zf.model.TestDO;
import com.zf.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Test")
@Api(value = "测试接口", tags = "TestController", description = "测试接口相关")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/getTestById", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取测试数据")
    public List<TestDO> getTestById(Integer id) {

        var wareCodeFilterList = new ArrayList<String>();
        wareCodeFilterList.add("1");
        wareCodeFilterList.add("232");
        wareCodeFilterList.add("454");

        String wareCodeStr = "'" + Joiner.on("','").join(wareCodeFilterList) + "'";


        var result = testService.getTestById(id);
        return result;
    }
}
