package com.smalllife.rest.proxy.codec;


import java.util.List;
import java.util.Map;

/**
 * Created by Aaron on 04/01/2017.
 */
public abstract class AbstractRestCodeC {
    public abstract <T> byte[] toProto(T obj);

    public abstract <T> T responseHandler(Map<String,List<String>> headers, byte[] bytes, Class<T> clazz);

    public abstract void validateResult(Object ret);
}
