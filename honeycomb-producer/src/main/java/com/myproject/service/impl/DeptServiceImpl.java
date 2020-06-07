package com.myproject.service.impl;

import com.myproject.common.pojo.Dept;
import com.myproject.dao.DeptDao;
import com.myproject.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 书一 on 2020/6/6.
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;
    @Override
    public List<Dept> selListDept() {
        return deptDao.selListDept();
    }

    @Override
    public int addDept(Dept dept) {
        return deptDao.addDept(dept);
    }

    @Override
    public Dept selDeptById(Integer deptNo) {
        return deptDao.selDeptById(deptNo);
    }
}
