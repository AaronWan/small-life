package com.smalllife.dao.impl;

import com.smalllife.dao.MetaDataDao;
import com.smalllife.dao.RecordDao;
import com.smalllife.dao.model.RecordEntity;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Aaron on 17/4/6.
 */
@Service
public class RecordDaoImpl extends AbstractDao<RecordEntity> implements RecordDao {
    @Autowired
    private MetaDataDao metaDataDao;
    @Override
    public RecordEntity save(ObjectId sessionId, Long tagId, Object content) {
        RecordEntity recordEntity=new RecordEntity();
        recordEntity.setId(metaDataDao.getRecordId(sessionId));
        recordEntity.setSessionId(sessionId);
        recordEntity.setContent(content);
        recordEntity.setCreateTime(System.currentTimeMillis());
        recordEntity.setTagId(tagId);
        datastore.save(recordEntity);
        return recordEntity;
    }

    @Override
    public List<RecordEntity> list(ObjectId sessionId, Long tagId) {
        Query<RecordEntity> query = createQuery();
        query.field(RecordEntity.Fields.sessionId).equal(sessionId);
        if(tagId!=null)
            query.field(RecordEntity.Fields.tagId).equal(tagId);
        return query.asList();
    }
}
