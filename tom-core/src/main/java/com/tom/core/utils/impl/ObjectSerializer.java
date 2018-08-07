package com.tom.core.utils.impl;

import com.tom.core.exception.RedisSerializationException;
import com.tom.core.utils.IRedisSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ObjectSerializer implements IRedisSerializer<Object> {
    private static Logger logger = LoggerFactory.getLogger(ObjectSerializer.class);

    public static final int BYTE_ARRAY_OUTPUT_STREAM_SIZE = 128;

    @Override
    public byte[] serialize(Object object) throws RedisSerializationException {
        byte[] result = new byte[0];

        if (object == null) {
            return result;
        }
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(BYTE_ARRAY_OUTPUT_STREAM_SIZE);
        if (!(object instanceof Serializable)) {
            throw new RedisSerializationException("requires a Serializable payload "
                    + "but received an object of type [" + object.getClass().getName() + "]");
        }
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            result =  byteStream.toByteArray();
        } catch (IOException e) {
            throw new RedisSerializationException("serialize error, object=" + object, e);
        }

        return result;
    }

    @Override
    public Object deserialize(byte[] bytes) throws RedisSerializationException {
        Object result = null;

        if (bytes == null || bytes.length == 0) {
            return result;
        }

        try {
            ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteStream);
            result = objectInputStream.readObject();
        } catch (IOException e) {
            throw new RedisSerializationException("deserialize error", e);
        } catch (ClassNotFoundException e) {
            throw new RedisSerializationException("deserialize error", e);
        }

        return result;
    }
}
