package com.tom.core.cache;

import java.io.Serializable;
import java.util.Set;

public interface ICache {

    Object get(final String key);

    void set(final String key, final Serializable value, long seconds);

    void set(final String key,  Object value, long seconds);

    void set(final String key, final Serializable value);

    Boolean setIfAbsent(String key, Serializable value);

    Boolean exists(final String key);

    void del(final String key);

    void delList(Set key);

    Boolean expire(final String key, final long seconds);

    Boolean expireAt(final String key, final long unixTime);

    Set getList(final String key);
}
