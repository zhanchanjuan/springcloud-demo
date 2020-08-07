package com.myproject.redislock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableScheduling
public class TimeTaskService {

    /**
     * 执行定时任务
     * 切面执行的目标方法
     **/
    @Scheduled(cron = "*/5 * * * * ?")
    @RedisLockAnnotation(lockPrefix = "TimeTaskService",timeOut=30,lockKey="job")
    public void executeTask() {
        System.out.println("hello world!");
    }

}
