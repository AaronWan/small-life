package com.smalllife.qq.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import lombok.Data;


/**
 * Created by Aaron on 28/03/2017.
 */
@Data
public class TextMsg extends BaseMsg {
    /**
     * 查看api文档关于收发消息推送的消息格式基本一致。 如以下格式：
     * <xml>
     * <ToUserName>123</ToUserName>
     * <FromUserName>245</FromUserName>
     * <CreateTime>1348831860</CreateTime>
     * <MsgType>text</MsgType>
     * <Content>this is a test</Content>
     * <MsgId>1234567890123456</MsgId>
     * </xml> 那么，我们就可以进行统一处理。
     */

    @JsonProperty("Content")
    @JacksonXmlCData
    private String content;


}
