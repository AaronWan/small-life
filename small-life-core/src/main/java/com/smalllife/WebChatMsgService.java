package com.smalllife;


import com.smalllife.dao.model.SessionEntity;

/**
 * Created by Aaron on 01/04/2017.
 */
public interface WebChatMsgService {
    void sendTextMsg(SessionEntity session, String text);

    void sendImageMsg(SessionEntity session, byte[] datas);

    void sendVideoMsg(SessionEntity session, byte[] datas);

}
