package com.myproject.service;

import com.myproject.common.pojo.Dept;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 书一 on 2020/6/6.
 */
public interface DeptService {
    List<Dept> selListDept();

    int addDept(Dept dept);

    Dept selDeptById(@Param("deptNo")Integer deptNo);
}
