package com.aidex.common.core.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author: Chen Jiaying
 */
@Service("redisLock")
public class RedisLock {

    /**
     * 3s过期时间
     */
    private static final Long EXPIRE_TIME = 10000L;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public boolean lock(String key) {
        String lock = key + EXPIRE_TIME;
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            long expireAt = System.currentTimeMillis() + EXPIRE_TIME + 1;
            Boolean acquire = connection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());
            if (acquire) {
                return true;
            } else {
                byte[] value = connection.get(lock.getBytes());
                if (Objects.nonNull(value) && value.length > 0) {
                    long expireTime = Long.parseLong(new String(value));
                    // 如果锁已经过期
                    if (expireTime < System.currentTimeMillis()) {
                        // 重新加锁，防止死锁
                        byte[] oldValue = connection.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + EXPIRE_TIME + 1).getBytes());
                        return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                    }
                }
            }
            return false;
        });
    }
    public void unlock(String key) {
        redisTemplate.delete(key + EXPIRE_TIME);
    }



}
