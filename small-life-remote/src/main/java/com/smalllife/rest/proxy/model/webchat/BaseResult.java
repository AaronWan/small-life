package com.smalllife.rest.proxy.model.webchat;


import com.smalllife.common.util.JsonUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Aaron on 26/12/2016.
 */
@Getter
@Setter
public class BaseResult {
    private int errcode;
    private String errmsg;
    public boolean isSuccess() {
        return errcode == 0;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
