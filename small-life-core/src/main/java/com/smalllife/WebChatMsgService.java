package com.smalllife;

import com.smalllife.model.WebChatMsg;

/**
 * Created by Aaron on 01/04/2017.
 */
public interface WebChatMsgService {
    void sendTextMsg(String openId, String text);

    void sendImageMsg(String openId, byte[] datas);

    void sendVideoMsg(String openId, byte[] datas);




}
