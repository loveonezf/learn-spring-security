package com.zf.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
//@EqualsAndHashCode(callSuper = true)
public class TestDO {

    @ApiModelProperty(name = "id",value = "主键ID",dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "name",value = "名称",dataType = "String")
    private String name;

    @ApiModelProperty(name = "status",value = "状态",dataType = "Integer")
    private Integer status;
}
