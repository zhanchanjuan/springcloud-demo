package com.myproject.common.config;



import com.myproject.common.exception.ProjectException;
import com.myproject.common.result.Result;
import com.myproject.common.result.ResultUtil;
import com.myproject.common.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

/**
 *  全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    /**
     * 全局异常处理
     *
     * @param throwable
     * @return
     */
    @ExceptionHandler({Throwable.class})
    public Result<String> Exception(Throwable throwable, HttpServletResponse response) {
        log.error("系统异常:{}", throwable.getMessage());
        // 有时抛出异常会被springSecurity包一层，需往里解析一层
        if (throwable.getCause() instanceof ProjectException) {
            ProjectException ce = (ProjectException) throwable.getCause();
            // 此处httpStatus状态待商榷，规范化应根据自定义异常code进行判断处理
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResultUtil.error(ce.getCode(), ce.getMsg(), ExceptionUtil.getExceptionDetail(ce));
        } else if (throwable.getCause() instanceof ConstraintViolationException) {
            return ResultUtil.error("sql执行异常：" + throwable.getMessage(),
                    ExceptionUtil.getExceptionDetail(throwable.getCause()));
        }
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResultUtil.error(throwable.getMessage(), ExceptionUtil.getExceptionDetail(throwable));
    }

    /**
     * 自定义异常CommonException
     *
     * @param throwable
     * @return
     */
    @ExceptionHandler({ProjectException.class})
    public Result<String> CommonException(ProjectException throwable, HttpServletResponse response) {
        log.error(throwable.getMsg());
        response.setStatus(throwable.getCode());
        return ResultUtil.error(throwable.getCode(), throwable.getMsg(), ExceptionUtil.getExceptionDetail(throwable));
    }

}
