package com.smalllife.dao.model;

/**
 * Created by Aaron on 02/04/2017.
 */
public enum ContentType {
    text,date,number;

    public static ContentType getValueOf(String type){
        try{
            return valueOf(type);
        }catch (RuntimeException e){
            return text;
        }
    }
}
