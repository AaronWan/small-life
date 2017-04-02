package com.smalllife.model;

import com.smalllife.common.util.JsonUtil;
import com.smalllife.common.util.XMLUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static com.smalllife.common.util.XMLUtil.beanToXml;
import static com.smalllife.common.util.XMLUtil.xmlToBean;

/**
 * Created by Aaron on 28/03/2017.
 */
public class MsgTest {
    @Test
    public void parseTest() throws IOException {
        String xml = "<xml>\n" +
                "<ToUserName><![CDATA[公众号]]></ToUserName>\n" +
                "<FromUserName><![CDATA[粉丝号]]></FromUserName>\n" +
                "<CreateTime>1460537339</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[欢迎开启公众号开发者模式]]></Content>\n" +
                "<MsgId>6272960105994287618</MsgId>\n" +
                "</xml>";
        HashMap<String,String> arg = XMLUtil.xmlToBean(xml, HashMap.class);
        TextMsg msg=xmlToBean(xml,TextMsg.class);
        System.out.println(JsonUtil.toPrettyJson(msg));
        System.out.println(beanToXml(msg).replaceAll(" xmlns=\"\"",""));
    }



}
