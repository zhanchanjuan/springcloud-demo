package com.myproject.aspect;

import org.aspectj.bridge.SourceLocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/**
 * 类级，方法级切面  execution
 * @author shuyi
 * @date 2020/7/29
 */
@Aspect
@Component
public class WebAspect {

    @Pointcut("execution(* com.myproject.aspect..*.*(..))")
    public void executePackage(){}

    /**
     * 前置通知，目标方法调用前被调用
     * @param point
     */
    @Before("executePackage()")
    public void beforeAdvice(JoinPoint point){
        System.out.println("测试前置通知-----");
        Object o=point.getThis();
        Object t=point.getTarget();
        Object[] args=point.getArgs();
        JoinPoint.StaticPart p=point.getStaticPart();
        String k=point.getKind();
        Signature s= point.getSignature();
//        SourceLocation l= (SourceLocation) point.getSourceLocation();
        System.out.println("目标方法信息测试打印,1："+o+";2:"+t+";3:"+args+";4:"+p+";5:"+k+";6:"+s+";7:");
    }

    /**
     * 后置最终通知，目标方法执行完执行
     */
    @After("executePackage()")
    public void afterAdvice(){
        System.out.println("测试后置通知------");
    }

    /**
     *   /**
     *      * 后置返回通知
     *      * 如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     *      * 如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     *      * returning 只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行
     *      * @param joinPoint
     *      * @param keys
     *      */
    @AfterReturning(value = "executePackage()",returning = "keys")
    public void afterReturningAdvice(JoinPoint point, String keys){
        System.out.println("测试后置返回通知- - - - -");
        System.out.println("后置返回通知 返回值："+keys);
    }

    /**
     * /**
     *      * 后置异常通知
     *      *  定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     *      *  throwing 只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     *      * @param joinPoint
     *      * @param exception
     */
    @AfterThrowing(value = "executePackage()",throwing = "exception")
    public void afterThrowingAdvice(JoinPoint point,NullPointerException exception){
        System.out.println("测试后置异常通知- - - - -");
    }


    @Around("execution(* com.myproject.aspect.AopController1.testAround(..))")
    public Object aroundAdvice(ProceedingJoinPoint proceed,Around around){
        System.out.println("测试环绕通知-------");
        System.out.println("环绕通知的目标方法名----"+proceed.getSignature().getName());
        try{
            //执行目标方法
            Object obj=proceed.proceed();
            return obj;
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }finally {
            System.out.println("- - - - - 环绕通知 end - - - -");
        }
        return null;
    }




}
