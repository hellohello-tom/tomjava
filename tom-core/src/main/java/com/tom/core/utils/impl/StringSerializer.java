package com.tom.core.utils.impl;

import com.tom.core.utils.IRedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.UnsupportedEncodingException;

public class StringSerializer implements IRedisSerializer<String> {

    private static final String DEFAULT_CHARSET = "UTF-8";

    private String charset = DEFAULT_CHARSET;

    @Override
    public byte[] serialize(String s) throws SerializationException {
        try {
            return (s == null ? null : s.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            throw new SerializationException("serialize error, string=" + s, e);
        }
    }

    @Override
    public String deserialize(byte[] bytes) throws SerializationException {
        try {
            return (bytes == null ? null : new String(bytes, charset));
        } catch (UnsupportedEncodingException e) {
            throw new SerializationException("deserialize error", e);
        }
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
