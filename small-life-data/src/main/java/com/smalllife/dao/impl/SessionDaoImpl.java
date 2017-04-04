package com.smalllife.dao.impl;

import com.smalllife.dao.SessionDao;
import com.smalllife.dao.model.SessionEntity;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.stereotype.Service;

/**
 * Created by Aaron on 02/04/2017.
 */
@Service
public class SessionDaoImpl extends AbstractDao<SessionEntity> implements SessionDao{
    @Override
    public SessionEntity findAndModify(String appOpenId, String openId) {
        Query<SessionEntity> query = createQuery();
        query.field(SessionEntity.Fields.appOpenId).equal(appOpenId);
        query.field(SessionEntity.Fields.openId).equal(openId);
        UpdateOperations operations = createUpdateOperations();
        operations.setOnInsert(SessionEntity.Fields.appOpenId,appOpenId);
        operations.setOnInsert(SessionEntity.Fields.openId,openId);
        operations.setOnInsert(SessionEntity.Fields.createTime,System.currentTimeMillis());
        operations.set(SessionEntity.Fields.updateTime,System.currentTimeMillis());
        return datastore.findAndModify(query,operations,false,true);
    }
}
