package com.myproject.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author shuyi
 * @date 2020/7/29
 */
@Aspect
@Component
public class WebDescAspect {
    @Pointcut("@annotation(com.myproject.aspect.WebDesc)")
    public void executionAnnotation(){}

    @Before("executionAnnotation()")
    public void beforeAnnotation(){
        System.out.println("注解前置通知测试------");
    }

    @After("executionAnnotation()")
    public void afterAnnotation(){
        System.out.println("注解后置通知测试------");
    }

    @Around("@annotation(webDesc)")
    public Object aroundAnnotation(ProceedingJoinPoint point,WebDesc webDesc){
        System.out.println("注解环绕通知测试------");
        System.out.println("注解的参数值是："+webDesc.describe()+":"+webDesc.prefix());
        try{
            System.out.println("ANNOTATION 调用类：" + point.getSignature().getDeclaringTypeName());
            System.out.println("ANNOTATION 调用类名" + point.getSignature().getDeclaringType().getSimpleName());
            Object obj=point.proceed();
            System.out.println("环绕通知测试 ----end----");
            return obj;
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
        return null;
    }
}
