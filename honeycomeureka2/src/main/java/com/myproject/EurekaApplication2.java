package com.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * Created by 书一 on 2020/6/7.
 */
@SpringBootApplication
  //服务网端的启动类，可以接受别的服务注册进来
@EnableEurekaServer
public class EurekaApplication2 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication2.class,args);
    }
}
