package com.myproject.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 方法 切面
 * @author shuyi
 * @date 2020/7/28
 */
@Aspect
public class AnnotationPointcut {
    @Before("execution(* com.myproject.service.impl.DeptServiceImpl.*(..))")
    public void before(){
        System.out.println("---------方法执行前---------");
    }

    /**
     * execution(* com.myproject.service.impl.DeptServiceImpl.*(..))
     * execution()是最常用的切点函数，其语法如下所示：
     * 整个表达式可以分为五个部分：
     * 1、execution(): 表达式主体。
     * 2、第一个*号：表示返回类型， *号表示所有的类型。
     * 3、包名：表示需要拦截的包名
     * 5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数
     */
    @After("execution(* com.myproject.service.impl.DeptServiceImpl.*(..))")
    public void after(){
        System.out.println("---------方法执行后---------");
    }


    //在环绕增强中，可以给定一个参数，代表要获取处理切入的点。
    @Around("execution(* com.myproject.service.impl.DeptServiceImpl.*(..))")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("环绕前");
        System.out.println("签名:"+jp.getSignature());
        //执行目标方法proceed
        Object proceed = jp.proceed();
        System.out.println("环绕后");
        System.out.println(proceed);
    }
}
