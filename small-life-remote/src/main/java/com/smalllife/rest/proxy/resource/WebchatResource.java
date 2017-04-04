package com.smalllife.rest.proxy.resource;


import com.smalllife.rest.proxy.annotation.*;
import com.smalllife.rest.proxy.model.webchat.GetAccessToken;
import com.smalllife.rest.proxy.model.webchat.SendTextMessage;
import com.smalllife.rest.proxy.model.webchat.msg.TextMsg;

import java.util.Map;


/**
 * Created by Aaron on 26/12/2016.
 */
@RestResource(value = "Webchat", desc = "工作流接口调用", codec = "com.smalllife.rest.proxy.codec.WebchatRestCodeC", contentType = "application/json")
public interface WebchatResource {
    @GET(value = "/token", contentType = "application/json")
    GetAccessToken.Result getAccessToken(@QueryParamsMap Map querymap);

    @POST(value = "/message/custom/send", contentType = "application/json")
    SendTextMessage.Result sendTextMessage(@QueryParamsMap Map querymap,@Body TextMsg arg);
}
