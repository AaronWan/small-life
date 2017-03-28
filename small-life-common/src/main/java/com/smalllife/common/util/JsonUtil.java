package com.smalllife.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Type;

/**
 * Created by Aaron on 28/03/2017.
 */
public class JsonUtil {
    private static Gson gson =new GsonBuilder().create();
    private static Gson withNullGson = (new GsonBuilder()).serializeNulls().create();
    private static Gson prettyGson = (new GsonBuilder()).setPrettyPrinting().create();

    public JsonUtil() {
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static String toPrettyJson(Object obj) {
        return prettyGson.toJson(obj);
    }

    public static String toJsonWithNull(Object obj) {
        return withNullGson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public static <T> T fromJson(String json, Type clazz) {
        return gson.fromJson(json, clazz);
    }
}
