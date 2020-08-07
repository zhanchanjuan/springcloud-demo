package com.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@SpringBootApplication
//向服务中心注册
@EnableDiscoveryClient
//开启路由网关
@EnableZuulProxy
public class ZuulCenter8081Application {

	public static void main(String[] args) {
		SpringApplication.run(ZuulCenter8081Application.class, args);
	}

}
