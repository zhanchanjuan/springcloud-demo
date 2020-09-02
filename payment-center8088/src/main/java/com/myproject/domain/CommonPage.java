package com.myproject.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页类
 *
 */
@Data
@ApiModel(value = "分页")
public class CommonPage<T> {
    @ApiModelProperty(value = "页码", notes = "默认0(第一页)")
    private Integer pageNo = 0;

    @ApiModelProperty(value = "每页展示数据", notes = "默认10条")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "排序字段")
    private String sortName;

    @ApiModelProperty(value = "排序类型", notes = "asc:升序;desc:降序 (默认升序)")
    private String sortType = "asc";

    @ApiModelProperty(value = "条件查询")
    private T data;

}
