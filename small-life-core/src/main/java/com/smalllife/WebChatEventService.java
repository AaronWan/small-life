package com.smalllife;

import com.smalllife.model.WebChatMsg;

/**
 * Created by Aaron on 01/04/2017.
 */
public interface WebChatEventService {
    void eventHandler(String openId, WebChatMsg msg);
}
