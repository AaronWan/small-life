package com.smalllife.impl;

import com.smalllife.TagService;
import com.smalllife.dao.TagDao;
import com.smalllife.dao.model.ContentType;
import com.smalllife.dao.model.SessionEntity;
import com.smalllife.dao.model.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Aaron on 17/4/5.
 */
@Service
public class TagServiceImpl  implements TagService  {
    @Autowired
    private TagDao tagDao;
    @Override
    public List<TagEntity> list(SessionEntity sessionEntity) {
        return tagDao.findTags(sessionEntity.getId());
    }

    @Override
    public TagEntity get(SessionEntity sessionEntity, long tagId) {
        return tagDao.finTag(sessionEntity.getId(),tagId);
    }

    @Override
    public TagEntity save(SessionEntity sessionEntity, String tagName, ContentType contentType) {
        return tagDao.saveTag(sessionEntity.getId(),tagName,contentType);
    }
}
