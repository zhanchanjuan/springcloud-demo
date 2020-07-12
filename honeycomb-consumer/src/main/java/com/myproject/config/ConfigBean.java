package com.myproject.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by 书一 on 2020/7/12.
 */
@Configuration
public class ConfigBean {
    //配置负载均衡实现restTemplate
    @Bean
    @LoadBalanced  //Ribbon 负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
