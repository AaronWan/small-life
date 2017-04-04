package com.smalllife;

import com.smalllife.model.WebChatMsg;

/**
 * Created by Aaron on 02/04/2017.
 */
public interface CommandService {
    void processCommand(WebChatMsg msg);
}
