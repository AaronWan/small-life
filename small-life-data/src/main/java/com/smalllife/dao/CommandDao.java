package com.smalllife.dao;

import com.smalllife.dao.model.CommandEntity;
import com.smalllife.dao.model.CommandType;
import org.bson.types.ObjectId;

/**
 * Created by Aaron on 02/04/2017.
 */
public interface CommandDao {
    CommandEntity find(ObjectId sessionId);

    void delete(ObjectId sessionId);

    CommandEntity save(ObjectId sessionId, CommandType type);
}
