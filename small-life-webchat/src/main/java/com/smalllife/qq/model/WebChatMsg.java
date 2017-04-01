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
public class WebChatMsg {
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

    //text
    @JsonProperty("Content")
    @JacksonXmlCData
    private String content;

    //  link
    @JsonProperty("Url")
    @JacksonXmlCData
    private String url;
    @JsonProperty("Title")
    @JacksonXmlCData
    private String title;
    @JsonProperty("Description")
    @JacksonXmlCData
    private String description;
//video
    @JsonProperty("ThumbMediaId")
    @JacksonXmlCData
    private String thumbMediaId;


    @JsonProperty("MediaId")
    @JacksonXmlCData
    private String mediaId;


//    location
    @JsonProperty("Location_X")
    @JacksonXmlCData
    private String location_X;
    @JsonProperty("Location_Y")
    @JacksonXmlCData
    private String location_Y;
    @JsonProperty("Scale")
    @JacksonXmlCData
    private String scale;
    @JsonProperty("Label")
    @JacksonXmlCData
    private String label;

//image
    @JsonProperty("PicUrl")
    @JacksonXmlCData
    private String picUrl;

    public static String getReply(WebChatMsg arg) {
        String msg="<xml>"+
                "<ToUserName><![CDATA["+arg.fromUserName+"]]></ToUserName>\n" +
                "<FromUserName><![CDATA["+arg.toUserName+"]]></FromUserName>\n" +
                "<CreateTime>"+System.currentTimeMillis()+"</CreateTime>\n" +
                "<MsgType><![CDATA["+arg.getType()+"]]></MsgType>\n" +
                "<Content><![CDATA["+arg.content+"]]></Content>\n" +
                "<Url><![CDATA["+arg.url+"]]></Content>\n" +
                "<Title><![CDATA["+arg.title+"]]></Content>\n" +
                "<Description><![CDATA["+arg.description+"]]></Content>\n" +
                "<ThumbMediaId><![CDATA["+arg.thumbMediaId+"]]></Content>\n" +
                "<MediaId><![CDATA["+arg.mediaId+"]]></Content>\n" +
                "<Location_X><![CDATA["+arg.location_X+"]]></Content>\n" +
                "<Location_Y><![CDATA["+arg.location_Y+"]]></Content>\n" +
                "<Scale><![CDATA["+arg.scale+"]]></Content>\n" +
                "<Label><![CDATA["+arg.label+"]]></Content>\n" +
                "<PicUrl><![CDATA["+arg.picUrl+"]]></Content>\n" +
                "</xml>";
        return msg;
    }
}
