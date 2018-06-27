package com.tom.core.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

/**
 * @Auther: tom
 * @Date: 2018年6月26日
 * @Description: redis key值的组成规则
 */
public class CacheKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return generateKey(params);
    }

    private String generateKey(Object... params) {
        if (params.length == 0) {
            return "arg[0]";
        } else {
            if (params.length == 1) {
                Object param = params[0];
                if (param != null && !param.getClass().isArray()) {
                    return param.toString();
                }
            }

            StringBuilder sb = new StringBuilder("arg[");
            for (int i = 0, len = params.length; i < len; i++) {
                sb.append(params[i].hashCode());
                if (i < len - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }
}
