package com.myproject.redislock;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
public class CommonRedisHelper {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 加分布式锁
     *
     * @param lockPrefix
     * @param key
     * @param timeout
     * @return
     * commonRedisHelper.setNx(lockPrefix, key, expire
     */
    public boolean setNx(String lockPrefix, String key, long timeout) {
        ValueOperations valueOperations = redisTemplate.opsForValue();

        Boolean flag = valueOperations.setIfAbsent(lockPrefix + key, System.currentTimeMillis());
        // 如果成功--设置超时时间, 防止超时
        if (flag) {
            valueOperations.set(lockPrefix + key, getLockValue(lockPrefix, key), timeout, TimeUnit.SECONDS);
        }
        return flag;
    }

    /**
     * 删除锁
     *
     * @param track
     * @param sector
     */
    public void delete(String track, String sector) {
        redisTemplate.delete(track + sector);
    }

    /**
     * 查询锁
     * @return 写锁时间
     */
    public long getLockValue(String track, String sector) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        long createTime = (long) valueOperations.get(track + sector);
        return createTime;
    }
}
