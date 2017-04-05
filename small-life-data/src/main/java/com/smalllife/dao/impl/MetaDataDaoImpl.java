package com.smalllife.dao.impl;

import com.smalllife.dao.MetaDataDao;
import com.smalllife.dao.model.MetaEntity;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.stereotype.Service;

/**
 * Created by Aaron on 02/04/2017.
 */
@Service
public class MetaDataDaoImpl extends AbstractDao<MetaEntity> implements MetaDataDao {
    @Override
    public Long getTagId(ObjectId sessionId) {
        Query<MetaEntity> query = createQuery();
        query.field(MetaEntity.Fields.name).equal("Tag_" + sessionId);
        UpdateOperations operation = createUpdateOperations();
        operation.inc(MetaEntity.Fields.number);
        return datastore.findAndModify(query, operation,false,true).getNumber();
    }

    @Override
    public Long getRecordId(ObjectId sessionId) {
        Query<MetaEntity> query = createQuery();
        query.field(MetaEntity.Fields.name).equal("Record_" + sessionId);
        UpdateOperations operation = createUpdateOperations();
        operation.inc(MetaEntity.Fields.number);
        return datastore.findAndModify(query, operation,false,true).getNumber();
    }
}
