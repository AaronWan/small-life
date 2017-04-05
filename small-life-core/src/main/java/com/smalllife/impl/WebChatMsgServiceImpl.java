package com.smalllife.impl;

import com.smalllife.WebChatMsgService;
import com.smalllife.dao.model.SessionEntity;
import org.springframework.stereotype.Service;

/**
 * Created by Aaron on 03/04/2017.
 */
@Service
public class WebChatMsgServiceImpl implements WebChatMsgService {
    @Override
    public void sendTextMsg(SessionEntity session, String text) {

    }

    @Override
    public void sendImageMsg(SessionEntity session, byte[] datas) {

    }

    @Override
    public void sendVideoMsg(SessionEntity session, byte[] datas) {

    }
}
