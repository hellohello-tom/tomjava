package com.tom.core.redis;

import com.tom.core.cache.ICache;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisCacher implements ICache {
    private long EXPIRE = 18000;

    private RedisTemplate<String, Object> redisTemplate;

    public RedisCacher() {
    }

    public RedisCacher(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public final Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public final void set(final String key, final Serializable value, long seconds) {
        redisTemplate.opsForValue().set(key, value);
        expire(key, seconds);
    }

    @Override
    public void set(String key, Object value, long seconds) {
        redisTemplate.opsForValue().set(key, value);
        expire(key, seconds);
    }

    @Override
    public final void set(final String key, final Serializable value) {
        redisTemplate.opsForValue().set(key, value, EXPIRE, TimeUnit.SECONDS);
        expire(key, EXPIRE);
    }

    @Override
    public Boolean setIfAbsent(String key, Serializable value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    @Override
    public final Boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public final void del(final String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void delList(Set key) {
        redisTemplate.delete(key);
    }

    /**
     * 在某段时间后失效
     *
     * @return
     */
    @Override
    public final Boolean expire(final String key, final long seconds) {
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * 在某个时间点失效
     *
     * @param key
     * @param unixTime
     * @return
     */
    @Override
    public final Boolean expireAt(final String key, final long unixTime) {
        return redisTemplate.expireAt(key, new Date(unixTime));
    }

    @Override
    public Set getList(String key) {
        Set collection = redisTemplate.keys("*");
        return collection;
    }
}
