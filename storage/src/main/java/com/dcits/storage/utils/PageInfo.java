package com.dcits.storage.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 分页参数类
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@ApiModel(value = "com.dcits.storage.utils.PageInfo<T>", description = "分页参数类")
@Setter
@Getter
public class PageInfo<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "当前页序号")
    private Integer pageNumber = 0;

    @ApiModelProperty(value = "每页数据条数")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "查询条件实体")
    private T selectOptions;

    @ApiModelProperty(value = "固定参数Map，{参数名称:参数值}；参数名用属性名+Param，避免和查询条件属性名重复；若参数要使用IN语句，在参数名前加@，参数值用逗号隔开")
    private Map params = new HashMap();
}
