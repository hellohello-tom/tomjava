package com.tom.core.exception;

public class RedisSerializationException  extends Exception {

    public RedisSerializationException(String msg) {
        super(msg);
    }
    public RedisSerializationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
