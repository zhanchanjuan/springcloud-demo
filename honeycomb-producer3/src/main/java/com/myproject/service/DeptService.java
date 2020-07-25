package com.myproject.service;

import com.myproject.common.pojo.Dept;

import java.util.List;
import java.util.Map;

/**
 * Created by 书一 on 2020/6/6.
 */
public interface DeptService {
    List<Dept> selListDept();

    int addDept(Dept dept);

    Map<String,Object> selDeptById(Integer deptNo);
    Map<String,Object> selDeptById2(Integer deptNo);
    Map<String,Object> selDeptById3(Integer deptNo);
    Map<String,Object> selDeptById4(Integer deptNo);
}
