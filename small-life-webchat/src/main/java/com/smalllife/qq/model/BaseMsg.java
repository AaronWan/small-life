package com.smalllife.qq.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import lombok.Data;


/**
 * Created by Aaron on 28/03/2017.
 */
@Data
@JsonRootName(value = "xml")
public class BaseMsg {
    @JsonProperty("ToUserName")
    @JacksonXmlCData
    private String toUserName;
    @JsonProperty("FromUserName")
    @JacksonXmlCData
    private String fromUserName;
    @JsonProperty("CreateTime")
    private long createTime;
    @JsonProperty("MsgType")
    @JacksonXmlCData
    private WebchatContentType type;
    @JsonProperty("MsgId")
    private long msgId;
}
