package com.myproject.dao;

import com.myproject.common.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 书一 on 2020/6/6.
 */
@Mapper
@Repository
public interface DeptDao {

    List<Dept> selListDept();

    int addDept(Dept dept);

    Dept selDeptById(@Param("deptNo")Integer deptNo);


}

