package com.smalllife.dao;

import com.smalllife.dao.model.SessionEntity;

/**
 * Created by Aaron on 02/04/2017.
 */
public interface SessionService {
    SessionEntity getSession(String appOpenId,String openId);
}
