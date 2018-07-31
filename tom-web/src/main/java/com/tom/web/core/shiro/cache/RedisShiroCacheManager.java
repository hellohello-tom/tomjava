package com.tom.web.core.shiro.cache;

import com.tom.core.redis.RedisCacher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import javax.annotation.Resource;

public class RedisShiroCacheManager implements CacheManager {

    @Resource
    private RedisCacher redisCacher;

    private int globalExpire;

    public RedisShiroCacheManager(int globalExpire) {
        this.globalExpire = globalExpire;
    }


    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new ShiroCache<K, V>(name, redisCacher, globalExpire);
    }
}
