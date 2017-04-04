package com.smalllife.rest.proxy.resource;


import com.google.common.collect.Maps;
import com.mongodb.util.JSON;
import com.smalllife.common.util.JsonUtil;
import com.smalllife.rest.proxy.model.webchat.GetAccessToken;
import com.smalllife.rest.proxy.model.webchat.SendTextMessage;
import com.smalllife.rest.proxy.model.webchat.msg.TextMsg;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.util.Map;

/**
 * Created by Aaron on 04/04/2017.
 */
public class WebchatResourceTest extends BaseTest {


    @Autowired
    private WebchatResource webchatResource;
    private String secret = "f895fd77bc45aa563c6e3e77b5be1bd6";
    private String appid = "wx35677cfff653cce6";

    @Test
    public void getAccessToken() throws Exception {
        GetAccessToken.Result result = accessToken();
        System.out.println(result);
    }

    private GetAccessToken.Result accessToken() {
        Map<String, String> params = Maps.newHashMap();
        params.put("secret", secret);
        params.put("appid", appid);
        params.put("grant_type", "client_credential");
        return webchatResource.getAccessToken(params);
    }

    //<xml><ToUserName><![CDATA[oHcnvt3Qc0jzw4Tqr1YCaFzkJInc]]></ToUserName>
//<FromUserName><![CDATA[gh_21b14df87c5a]]></FromUserName>
//<CreateTime>1491194824350</CreateTime>
//<MsgType><![CDATA[text]]></MsgType>
//<Content><![CDATA[1--->添加记录
//2--->添加标签
//3--->查看现有标签
//4--->查看标签内容]]></Content>
//</xml>
    @Test
    public void sendTextMessage() throws Exception {
//        Map<String, String> params = Maps.newHashMap();
//        params.put("access_token", accessToken().getAccessToken());
//        TextMsg msg=new TextMsg();
//        msg.setTouser("oHcnvt3Qc0jzw4Tqr1YCaFzkJInc");
//        msg.setText("text ........");
//        SendTextMessage.Result result = webchatResource.sendTextMessage(params,msg);
//        System.out.println(JsonUtil.toPrettyJson(result));
    }
}