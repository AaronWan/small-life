package com.smalllife.dao;

import com.smalllife.dao.model.SessionEntity;

/**
 * Created by Aaron on 01/04/2017.
 */
public interface SessionDao {
     SessionEntity findAndModify(String appOpenId, String openId);
}
