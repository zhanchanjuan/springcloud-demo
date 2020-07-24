package com.myproject.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户基础信息
 */
@Data
public class BdCustomer implements Serializable {
    private static final long serialVersionUID = 261356392017414752L;
    //用户编号
    private String custNo;
    private String custAcct;
    private String telephone;
    private String password;
    private String regTime;
    private String isBind;
    private String isLocked;
    private String sources;
    private String custName;
    private String idType;
}
