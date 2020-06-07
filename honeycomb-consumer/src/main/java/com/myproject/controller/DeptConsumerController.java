package com.myproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by 书一 on 2020/6/7.
 */
@RestController
public class DeptConsumerController {
    @Autowired
    private RestTemplate restTemplate;



}
