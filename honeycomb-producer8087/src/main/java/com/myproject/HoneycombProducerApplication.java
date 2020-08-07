package com.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * Created by 书一 on 2020/6/6.
 */
@SpringBootApplication
@ServletComponentScan
@EnableEurekaServer
@EnableDiscoveryClient
public class HoneycombProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HoneycombProducerApplication.class,args);
    }
}
