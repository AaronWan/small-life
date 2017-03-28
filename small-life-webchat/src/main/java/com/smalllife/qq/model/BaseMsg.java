package com.smalllife.qq.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Aaron on 28/03/2017.
 */
@Data
public class BaseMsg {
    @JsonProperty("ToUserName")
    private String toUserName;
    @JsonProperty("FromUserName")
    private String fromUserName;
    @JsonProperty("CreateTime")
    private long createTime;
    @JsonProperty("MsgType")
    private WebchatContentType type;
    @JsonProperty("MsgId")
    private String msgId;
}
