package com.tom.core.utils;


import com.tom.core.exception.RedisSerializationException;

public interface IRedisSerializer<T> {

    byte[] serialize(T t) throws RedisSerializationException;

    T deserialize(byte[] bytes) throws RedisSerializationException;
}
