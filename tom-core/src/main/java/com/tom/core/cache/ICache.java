package com.tom.core.cache;

import java.io.Serializable;

public interface ICache {

    Object get(final String key);

    void set(final String key, final Serializable value, int seconds);

    void set(final String key, final Serializable value);

    Boolean setIfAbsent(String key, Serializable value);

    Boolean exists(final String key);

    void del(final String key);

    Boolean expire(final String key, final int seconds);

    Boolean expireAt(final String key, final long unixTime);
}
