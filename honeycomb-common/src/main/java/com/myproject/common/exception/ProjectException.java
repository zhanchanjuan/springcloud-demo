package com.myproject.common.exception;



import com.myproject.common.constants.ResultEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 支付异常
 */
@Getter
@Setter
public class ProjectException extends RuntimeException {

    private int code;

    private String msg;

    public ProjectException() {
        super(ResultEnum.SYSTEM_ERROR.getMsg());
        this.code = ResultEnum.SYSTEM_ERROR.getCode();
        this.msg = ResultEnum.SYSTEM_ERROR.getMsg();
    }

    public ProjectException(String msg) {
        super(msg);
        this.code = ResultEnum.SYSTEM_ERROR.getCode();
        this.msg = msg;
    }

    public ProjectException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

}
