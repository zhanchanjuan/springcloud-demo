package com.myproject.redislock;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * redis锁注解
 * @author shuyi
 * @date 2020/7/30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RedisLockAnnotation {
    String lockPrefix() default "";
    String lockKey() default "";
    long timeOut() default 5;
    TimeUnit timeUnit() default TimeUnit.SECONDS;

}
