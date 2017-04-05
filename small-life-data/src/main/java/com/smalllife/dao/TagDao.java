package com.smalllife.dao;

import com.smalllife.dao.model.ContentType;
import com.smalllife.dao.model.TagEntity;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by Aaron on 02/04/2017.
 */
public interface TagDao {

    TagEntity saveTag(ObjectId sessionId, String tagName, ContentType type);

    List<TagEntity> findTags(ObjectId sessionId);

    TagEntity increCount(ObjectId sessionId,Long tagId);

    void delete(ObjectId sessionId,Long tagId);

    TagEntity finTag(ObjectId sessionId, long tagId);
}
