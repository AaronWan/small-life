package com.smalllife.qq.model;

import com.smalllife.common.util.JsonUtil;
import org.junit.Test;

import java.io.IOException;

import static com.smalllife.common.util.XMLUtil.beanToXml;
import static com.smalllife.common.util.XMLUtil.xmlToBean;

/**
 * Created by Aaron on 28/03/2017.
 */
public class MsgTest {
    @Test
    public void parseTest() throws IOException {
        String xml = "<xml>" +
                "<ToUserName>123</ToUserName>" +
                "<FromUserName>245</FromUserName>" +
                "<CreateTime>1348831860</CreateTime>" +
                "<MsgType>text</MsgType>" +
                "<Content>this is a test</Content>" +
                "<MsgId>1234567890123456</MsgId>" +
                "</xml>";
        TextMsg msg=xmlToBean(xml,TextMsg.class);
        System.out.println(JsonUtil.toPrettyJson(msg));

        System.out.println(beanToXml(msg));
    }



}
