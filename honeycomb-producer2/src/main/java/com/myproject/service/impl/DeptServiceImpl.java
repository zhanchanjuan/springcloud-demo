package com.myproject.service.impl;

import com.myproject.common.pojo.Dept;
import com.myproject.dao.DeptDao;
import com.myproject.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 书一 on 2020/6/6.
 */
@Service
@Slf4j
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
    public Map<String,Object> selDeptById(Integer deptNo) {
        Map<String,Object> dept=deptDao.selDeptById(deptNo);
        log.info("查询到对应部分的信息"+dept);
        return dept;
    }
}
