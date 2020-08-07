package com.myproject.aspect;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shuyi
 * @date 2020/7/29
 */
@RestController
@RequestMapping("/aop")
public class AopController1 {

    @GetMapping("test1")
    public String testAop(String key){
        System.out.println("打印结果key="+key);
        return "key="+key;
    }

    @GetMapping("/test2")
    public String testAfterThrowing(String key){
        throw new NullPointerException();
    }

    @GetMapping("/test3")
    public String testAround(String key){
        System.out.println("打印结果key="+key);
        return "key="+key;
    }
}
