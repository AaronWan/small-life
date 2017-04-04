package com.smalllife.impl;

import com.smalllife.dao.SessionDao;
import com.smalllife.dao.SessionService;
import com.smalllife.dao.model.SessionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Aaron on 02/04/2017.
 */
@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private SessionDao sessionDao;
    @Override
    public SessionEntity getSession(String appOpenId, String openId) {
        return sessionDao.findAndModify(appOpenId,openId);
    }
}
