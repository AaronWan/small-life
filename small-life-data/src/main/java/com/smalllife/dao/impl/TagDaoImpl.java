package com.smalllife.dao.impl;

import com.smalllife.dao.MetaDataDao;
import com.smalllife.dao.TagDao;
import com.smalllife.dao.model.ContentType;
import com.smalllife.dao.model.TagEntity;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Aaron on 02/04/2017.
 */
@Service
public class TagDaoImpl extends AbstractDao<TagEntity> implements TagDao {
    @Autowired
    private MetaDataDao metaDataDao;
    @Override
    public TagEntity saveTag(ObjectId sessionId, String tagName, ContentType type) {
        Query<TagEntity> query=createQuery();
        query.field(TagEntity.Fields.sessionId).equal(sessionId);
        query.field(TagEntity.Fields.name).equal(tagName);

        UpdateOperations operations = createUpdateOperations();
        operations.setOnInsert(TagEntity.Fields.sessionId,sessionId);
        operations.setOnInsert(TagEntity.Fields.name,tagName);
        operations.setOnInsert(TagEntity.Fields.tagId,metaDataDao.getTagId(sessionId));
        operations.setOnInsert(TagEntity.Fields.createTime,System.currentTimeMillis());
        operations.setOnInsert(TagEntity.Fields.type,type);
        return datastore.findAndModify(query,operations,false,true);
    }

    @Override
    public List<TagEntity> findTags(ObjectId sessionId) {
        Query<TagEntity> query=createQuery();
        query.field(TagEntity.Fields.sessionId).equal(sessionId);
        return query.asList();
    }

    @Override
    public TagEntity increCount(ObjectId sessionId, Long tagId) {
        Query<TagEntity> query=createQuery();
        query.field(TagEntity.Fields.sessionId).equal(sessionId);
        query.field(TagEntity.Fields.tagId).equal(tagId);

        UpdateOperations operations = createUpdateOperations();
        operations.inc(TagEntity.Fields.recordCount);
        return datastore.findAndModify(query,operations);
    }

    @Override
    public void delete(ObjectId sessionId, Long tagId) {
        Query<TagEntity> query=createQuery();
        query.field(TagEntity.Fields.sessionId).equal(sessionId);
        query.field(TagEntity.Fields.tagId).equal(tagId);
        datastore.delete(query);
    }
}
