package com.myproject.myRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 书一 on 2020/7/12.
 */
@Configuration
public class MyRibbonRule {
    /**
     * 将负载均衡规则定义为随机的
     */
    @Bean
    public IRule myRule(){
        return new RandomRule();
    }
}
