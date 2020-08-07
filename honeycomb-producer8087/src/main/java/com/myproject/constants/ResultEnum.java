package com.myproject.constants;

import lombok.Getter;

/**
 * 统一返回结果枚举
 */
@Getter
public enum ResultEnum {

    /**
     * 错误码
     */
    SYSTEM_ERROR(500, "程序出错"),
    /**
     * 成功码
     */
    SUCCESS(200, "请求成功"),
    ;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示
     */
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
