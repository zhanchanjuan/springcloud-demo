package com.myproject.controller;


import com.myproject.common.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by 书一 on 2020/6/7.
 */
@RestController
public class DeptConsumerController {
    /**
     * 这里是消费者模块的cotroller,消费者这里不应该有service层，直接用RestTemplate调用就可以，注册到spring中
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 消费模块，添加Ribbon做负载均衡以后，下面的客户端提供地址就不应该是固定的，而是动态的，可以用服务名来访问（该服务可能注册到集群注册中心，一个服务注册到了多个注册中心的情况）
     */
    private static final String REST_URL_PREFIX="http://HONEYCOMB-PRODUCER";

    @RequestMapping("/addDept")
    public int addDept(@RequestBody Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX+"/addDept",dept,int.class);
    }

    @RequestMapping("/deptInfo")
    public Map<String,Object> selDeptById(Integer deptNo){
        return restTemplate.getForObject(REST_URL_PREFIX+"/deptInfo"+deptNo,Map.class);
    }

    @RequestMapping("/listDept")
    public List<Dept> selListDept(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/listDept",List.class);
    }




}
