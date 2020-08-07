package com.myproject.redislock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * 锁拦截器的实现
 */
@Aspect
@Component
public class RedisLockAspect {

    private static final Integer MAX_RETRY_COUNT = 3;
    private static final String LOCK_PRE_FIX = "lockPrefix";
    private static final String LOCK_KEY = "lockKey";
    private static final String TIME_OUT = "timeOut";
    private static final int PROTECT_TIME = 2 << 11;//4096



    private static final Logger log = LoggerFactory.getLogger(RedisLockAspect.class);

    @Autowired
    private CommonRedisHelper commonRedisHelper;


    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.myproject.redislock.RedisLockAnnotation)")
    public void redisLockAspect() {
    }

    /**
     * 切面实现 环绕通知
     * @param proceeding 目标方法
     * @throws Exception 异常抛出
     */
    @Around("redisLockAspect()")
    public void lockAroundAction(ProceedingJoinPoint proceeding){
        //获取redis锁
        Boolean flag =getLock(proceeding, 0, System.currentTimeMillis());
        if (flag) {
            try {
                proceeding.proceed();
                Thread.sleep(PROTECT_TIME);
            } catch (Throwable throwable) {
                throw new RuntimeException("分布式锁执行发生异常" + throwable.getMessage(), throwable);
            } finally {
                // 删除锁
                this.delLock(proceeding);
            }
        } else {
            log.info("其他系统正在执行此项任务");
        }
    }


    /**
     * 获取锁
     *
     * @param proceeding
     * @return
     *  Boolean flag = this.getLock(proceeding, 0, System.currentTimeMillis());
     */
    private boolean getLock(ProceedingJoinPoint proceeding, int count, long currentTime) {
        //获取注解中的参数
        Map<String, Object> annotationArgs = this.getAnnotationArgs(proceeding);
        String lockPrefix = (String) annotationArgs.get(LOCK_PRE_FIX);
        String key = (String) annotationArgs.get(LOCK_KEY);
        long expire = (long) annotationArgs.get(TIME_OUT);
        //String key = this.getFirstArg(proceeding);
        if (StringUtils.isEmpty(lockPrefix) || StringUtils.isEmpty(key)) {
            // 此条执行不到
            throw new RuntimeException("RedisLock,锁前缀,锁名未设置");
        }
        //SETNX  加分布式锁  redis 如果不存在key 则做set 动作
        if (commonRedisHelper.setNx(lockPrefix, key, expire)) {
            return true;
        } else {
            //如果存在
            // 如果当前时间与锁的时间差, 大于保护时间（保护时间要包括睡眠的时间）,则强制删除锁(防止锁死)
            long createTime = commonRedisHelper.getLockValue(lockPrefix, key);
            if ((currentTime - createTime) > (expire * 1000 + PROTECT_TIME)) {
                count ++;
                //重试3次，大于3次，退出程序
                if (count > MAX_RETRY_COUNT){
                    return false;
                }
                commonRedisHelper.delete(lockPrefix, key);
                getLock(proceeding,count,currentTime);
            }
            return false;
        }
    }

    /**
     * 删除锁
     *
     * @param proceeding
     */
    private void delLock(ProceedingJoinPoint proceeding) {
        Map<String, Object> annotationArgs = this.getAnnotationArgs(proceeding);
        String lockPrefix = (String) annotationArgs.get(LOCK_PRE_FIX);
        String key = (String) annotationArgs.get(LOCK_KEY);
        commonRedisHelper.delete(lockPrefix, key);
    }

    /**
     * 获取锁参数
     *
     * @param proceeding
     * @return
     */
    private Map<String, Object> getAnnotationArgs(ProceedingJoinPoint proceeding) {
        Class target = proceeding.getTarget().getClass();
        Method[] methods = target.getMethods();
        String methodName = proceeding.getSignature().getName();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Map<String, Object> result = new HashMap<String, Object>();
                RedisLockAnnotation redisLockAnnotation = method.getAnnotation(RedisLockAnnotation.class);
                result.put(LOCK_PRE_FIX, redisLockAnnotation.lockPrefix());
                result.put(LOCK_KEY, redisLockAnnotation.lockKey());
                result.put(TIME_OUT, redisLockAnnotation.timeUnit().toSeconds(redisLockAnnotation.timeOut()));
                return result;
            }
        }
        return null;
    }

    /**
     * 获取第一个String类型的参数为锁的业务参数
     *此方法暂未用上
     * @param proceeding
     * @return
     */
    @Deprecated
    public String getFirstArg(ProceedingJoinPoint proceeding) {
        Object[] args = proceeding.getArgs();
        if (args != null && args.length > 0) {
            for (Object object : args) {
                String type = object.getClass().getName();
                if ("java.lang.String".equals(type)) {
                    return (String) object;
                }
            }
        }
        return null;
    }


}
