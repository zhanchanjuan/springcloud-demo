package com.myproject.aspect;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shuyi
 * @date 2020/7/29
 */
@RestController
@RequestMapping("/aop2")
public class AopController2 {

    @GetMapping("/annotationTest")
    @WebDesc(describe = "This is a testAnnotation controller",prefix = "注解切面测试")
    public String testAnnotation(String key){
        System.out.println("打印接口方法执行结果："+key);
        return "key="+key;
    }

}
