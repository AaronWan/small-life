package com.smalllife.mongosupport;

import java.util.List;

/**
 * Created by Aaron on 01/04/2017.
 */
public interface IConfig {
    String getName();
    List<String> getLine();

    String getContent();

    Object get(String key,Object def);

    String get(String key);

    String getString(String key, String def);

    Integer getInteger(String key, String def);

    Long getLong(String key, String def);

    boolean getBool(String s);
}
