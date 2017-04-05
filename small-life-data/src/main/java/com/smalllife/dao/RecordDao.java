package com.smalllife.dao;

import com.smalllife.dao.model.RecordEntity;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by Aaron on 01/04/2017.
 */
public interface RecordDao {
    RecordEntity save(ObjectId sessionId,Long tagId,Object content);
    List<RecordEntity> list(ObjectId sessionId, Long tagId);
}
