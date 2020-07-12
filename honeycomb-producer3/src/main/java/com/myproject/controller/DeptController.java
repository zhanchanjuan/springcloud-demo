package com.myproject.controller;

import com.myproject.common.pojo.Dept;
import com.myproject.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by 书一 on 2020/6/6.
 */
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @PostMapping("/addDept")
    public int addDept(@RequestBody Dept dept){
        return deptService.addDept(dept);
    }

    @GetMapping("/deptInfo")
    public Map<String,Object> selDeptById(Integer deptNo){
        return deptService.selDeptById(deptNo);
    }

    @GetMapping("/listDept")
    public List<Dept> selListDept(){
        return deptService.selListDept();
    }


}
