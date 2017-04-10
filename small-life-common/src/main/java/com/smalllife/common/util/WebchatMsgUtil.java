package com.smalllife.common.util;

import com.google.common.base.Strings;
import lombok.experimental.UtilityClass;

/**
 * Created by Aaron on 17/4/7.
 */
@UtilityClass
public class WebchatMsgUtil {
    public String getTextMsg(String from,String to, String content) {
        StringBuffer msg = new StringBuffer("<xml>" +
                "<ToUserName><![CDATA[" + to + "]]></ToUserName>\n" +
                "<FromUserName><![CDATA[" + from + "]]></FromUserName>\n" +
                "<CreateTime>" + System.currentTimeMillis() + "</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n");
        if (!Strings.isNullOrEmpty(content))
            msg.append("<Content><![CDATA[" + content + "]]></Content>\n");
        msg.append("</xml>");
        return msg.toString();
    }
}
