package com.smalllife;

import com.smalllife.dao.model.ContentType;
import com.smalllife.dao.model.SessionEntity;
import com.smalllife.dao.model.TagEntity;

import java.util.List;

/**
 * Created by Aaron on 17/4/5.
 */
public interface TagService {
    List<TagEntity> list(SessionEntity sessionEntity);

    TagEntity get(SessionEntity sessionEntity, long tagId);

    TagEntity save(SessionEntity sessionEntity, String tagName, ContentType contentType);
}
