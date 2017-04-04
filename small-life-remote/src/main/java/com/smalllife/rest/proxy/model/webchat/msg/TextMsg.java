package com.smalllife.rest.proxy.model.webchat.msg;

import com.google.common.collect.Maps;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Map;

/**
 * Created by Aaron on 04/04/2017.
 */
@Data
public class TextMsg {
    @SerializedName("touser")
    private String touser;
    @SerializedName("msgtype")
    private String type="text";
    @SerializedName("text")
    private Map<String,String> text= Maps.newHashMap();

    public void setText(String text){
        this.text.put("content",text);
    }
}
