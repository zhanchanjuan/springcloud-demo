package com.myproject;

import com.myRule.MyRibbonRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * Created by 书一 on 2020/6/7.
 */
//Ribbon和eureka整合以后.客户端可以直接调用，不用关系ip地址
@SpringBootApplication
@EnableEurekaServer   //SpringCloud,eureka核心注解
@RibbonClient(name = "HONEYCOMB-PRODUCER",configuration =MyRibbonRule.class )   //配置-->在微服务启动的时候就可以加载自定义的ribbonRule规则类
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class,args);
    }
}
