package com.smalllife.dao;

import org.bson.types.ObjectId;

/**
 * Created by Aaron on 02/04/2017.
 */
public interface MetaDataDao {
    Long getTagId(ObjectId sessionId);
    Long getRecordId(ObjectId sessionId);
}
