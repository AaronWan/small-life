package com.smalllife.dao.impl;


import com.smalllife.BaseTest;
import com.smalllife.dao.TagDao;
import com.smalllife.dao.model.TagEntity;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Aaron on 05/04/2017.
 */
public class TagDaoImplTest extends BaseTest {
    @Autowired
    private TagDao tagDao;
    @Test
    public void findTags() throws Exception {
        List<TagEntity> tags = tagDao.findTags(new ObjectId());
        System.out.println(tags);
    }

}