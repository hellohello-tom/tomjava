package com.tom.web.core.shiro.cache;

import com.tom.core.cache.ICache;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class ShiroCache<K, V> implements Cache<K, V> {


    private static final String REDIS_SHIRO_CACHE = "tom-shiro-cache:";
    private String cacheKey;

    private ICache cache;
    private int globExpire = 30;

    public ShiroCache(String name, ICache cache,int globalExpire) {
        this.cacheKey = REDIS_SHIRO_CACHE + name + ":";
        this.cache = cache;
        globExpire = globalExpire;
    }


    @Override
    public V get(K k) throws CacheException {
        K key = getCacheKey(k);
        return (V) cache.get((String) key);
    }

    @Override
    public V put(K k, V value) throws CacheException {
        K key = getCacheKey(k);
        cache.set((String) key, value, globExpire);
        return get(k);
    }

    @Override
    public V remove(K k) throws CacheException {
        K key = getCacheKey(k);
        cache.del((String) key);
        return get(k);
    }

    @Override
    public void clear() throws CacheException {
        cache.delList(keys());
    }

    @Override
    public int size() {
        Collection collection = cache.getList("*");
        return collection.size();
    }

    @Override
    public Set<K> keys() {
        return cache.getList("*");
    }

    @Override
    public Collection<V> values() {
        return Collections.emptyList();
    }

    private K getCacheKey(Object k) {
        return (K) (this.cacheKey + k);
    }
}
