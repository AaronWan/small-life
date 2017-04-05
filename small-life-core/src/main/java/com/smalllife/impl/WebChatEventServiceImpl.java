package com.smalllife.impl;

import com.smalllife.WebChatEventService;
import com.smalllife.model.WebChatMsg;
import org.springframework.stereotype.Service;

/**
 * Created by Aaron on 01/04/2017.
 */
@Service
public class WebChatEventServiceImpl implements WebChatEventService {

    @Override
    public void eventHandler(String openId, WebChatMsg msg) {

    }
}
