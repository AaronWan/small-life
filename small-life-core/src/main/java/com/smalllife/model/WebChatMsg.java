package com.smalllife.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.google.common.base.Strings;
import com.smalllife.dao.model.SessionEntity;
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
    @JsonProperty("Format")
    @JacksonXmlCData
    private String format;
    @JsonProperty("Recognition")
    @JacksonXmlCData
    private String recognition;
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

    //    event
    @JsonProperty("Event")
    @JacksonXmlCData
    private EventType event;

    public static String getReply(WebChatMsg arg) {
        StringBuffer msg = new StringBuffer("<xml>" +
                "<ToUserName><![CDATA[" + arg.fromUserName + "]]></ToUserName>\n" +
                "<FromUserName><![CDATA[" + arg.toUserName + "]]></FromUserName>\n" +
                "<CreateTime>" + System.currentTimeMillis() + "</CreateTime>\n" +
                "<MsgType><![CDATA[" + arg.getType() + "]]></MsgType>\n");
        if (!Strings.isNullOrEmpty(arg.content))
            msg.append("<Content><![CDATA[" + arg.content + "]]></Content>\n");
        if (!Strings.isNullOrEmpty(arg.url))
            msg.append("<Url><![CDATA[" + arg.url + "]]></Url>\n");
        if (!Strings.isNullOrEmpty(arg.title))
            msg.append("<Title><![CDATA[" + arg.title + "]]></Title>\n");
        if (!Strings.isNullOrEmpty(arg.description))
            msg.append("<Description><![CDATA[" + arg.description + "]]></Description>\n");
        if (!Strings.isNullOrEmpty(arg.thumbMediaId))
            msg.append("<ThumbMediaId><![CDATA[" + arg.thumbMediaId + "]]></ThumbMediaId>\n");
        if (!Strings.isNullOrEmpty(arg.mediaId))
            msg.append("<MediaId><![CDATA[" + arg.mediaId + "]]></MediaId>\n");
        if (!Strings.isNullOrEmpty(arg.location_X))
            msg.append("<Location_X><![CDATA[" + arg.location_X + "]]></Location_X>\n");
        if (!Strings.isNullOrEmpty(arg.location_Y))
            msg.append("<Location_Y><![CDATA[" + arg.location_Y + "]]></Location_Y>\n");
        if (!Strings.isNullOrEmpty(arg.scale))
            msg.append("<Scale><![CDATA[" + arg.scale + "]]></Scale>\n");
        if (!Strings.isNullOrEmpty(arg.label))
            msg.append("<Label><![CDATA[" + arg.label + "]]></Label>\n");
        if (!Strings.isNullOrEmpty(arg.picUrl))
            msg.append("<PicUrl><![CDATA[" + arg.picUrl + "]]></PicUrl>\n");
        msg.append("</xml>");
        return msg.toString();
    }

    public static String getTextMsg(SessionEntity sessionEntity, String content) {
        StringBuffer msg = new StringBuffer("<xml>" +
                "<ToUserName><![CDATA[" + sessionEntity.getOpenId() + "]]></ToUserName>\n" +
                "<FromUserName><![CDATA[" + sessionEntity.getAppOpenId() + "]]></FromUserName>\n" +
                "<CreateTime>" + System.currentTimeMillis() + "</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n");
        if (!Strings.isNullOrEmpty(content))
            msg.append("<Content><![CDATA[" + content + "]]></Content>\n");
        msg.append("</xml>");
        return msg.toString();
    }


        @Override
    public String toString() {
        return "WebChatMsg{" +
                "toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime=" + createTime +
                ", type=" + type +
                ", msgId=" + msgId +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", thumbMediaId='" + thumbMediaId + '\'' +
                ", mediaId='" + mediaId + '\'' +
                ", format='" + format + '\'' +
                ", recognition='" + recognition + '\'' +
                ", location_X='" + location_X + '\'' +
                ", location_Y='" + location_Y + '\'' +
                ", scale='" + scale + '\'' +
                ", label='" + label + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", event=" + event +
                '}';
    }
}
