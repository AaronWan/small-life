package com.smalllife.impl;

import com.smalllife.RecordService;
import com.smalllife.dao.RecordDao;
import com.smalllife.dao.model.RecordEntity;
import com.smalllife.dao.model.SessionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Aaron on 17/4/6.
 */
@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordDao recordDao;
    @Override
    public RecordEntity create(SessionEntity session, Long tagId, Object content) {
        return recordDao.save(session.getId(),tagId,content);
    }

    @Override
    public List<RecordEntity> list(SessionEntity session, Long tagId) {
        return recordDao.list(session.getId(),tagId);
    }
}
