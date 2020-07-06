package com.myproject.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 统一返回结果类 泛型
 */
@Data
@ApiModel(value = "统一返回值")
public class Result<T> {

    @ApiModelProperty(value = "请求码")
    private Integer code;

    @ApiModelProperty(value = "提示信息")
    private String msg;

    @ApiModelProperty(value = "详情")
    private T data;

}