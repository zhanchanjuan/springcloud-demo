package com.myproject.common.result;

/**
 * @author shuyi
 * @date 2020/8/14
 */

import com.myproject.common.constants.CommonConstants;
import com.myproject.common.constants.ResultEnum;

/**
 * 返回结果工具类
 */
public class ResultUtil {
    /**
     * 成功
     * @param code 返回码
     * @param msg  返回消息
     * @param obj  返回自定义数据
     * @return
     */
    public static <T> Result<T> success(Integer code, String msg, T obj) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(obj);
        return result;
    }

    public static <T> Result<T> success(String msg, T obj) {
        return success(ResultEnum.SUCCESS.getCode(), msg, obj);
    }

    public static <T> Result<T> success(T obj) {
        return success(ResultEnum.SUCCESS.getMsg(), obj);
    }

    public static <T> Result<T> success(String str) {
        return success(str, null);
    }

    public static <T> Result<T> success() {
        return success(ResultEnum.SUCCESS.getMsg(), null);
    }

    /**
     * 失败
     *
     * @param code 返回码
     * @param msg  返回消息
     * @param obj  返回自定义数据（异常详情）
     * @return
     */
    public static <T> Result<T> error(Integer code, String msg, T obj) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(CommonConstants.SHOW_HTTP_ERROR_DETAIL ? obj : null);
        return result;
    }

    public static <T> Result<T> error(String msg, T obj) {
        // 判断是否记录详细异常日志
        return error(ResultEnum.SYSTEM_ERROR.getCode(), msg, CommonConstants.SHOW_HTTP_ERROR_DETAIL ? obj : null);
    }

    public static Result error(String msg) {
        return error(msg, null);
    }

    public static Result error(Object obj) {
        return error(ResultEnum.SYSTEM_ERROR.getMsg(), obj);
    }

    public static Result error() {
        return error(ResultEnum.SYSTEM_ERROR.getMsg());
    }

}
