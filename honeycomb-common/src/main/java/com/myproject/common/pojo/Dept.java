package com.myproject.common.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shuyi
 * @date 2020/6/2
 */
@Data
public class Dept implements Serializable {
    private static final long serialVersionUID = -7731865410220747519L;
    private Integer deptNo;
    private String deptName;
    private String dbSource;
}
