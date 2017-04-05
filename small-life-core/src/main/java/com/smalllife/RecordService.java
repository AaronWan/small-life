package com.smalllife;

import com.smalllife.dao.model.RecordEntity;
import com.smalllife.dao.model.SessionEntity;

import java.util.List;

/**
 * Created by Aaron on 17/4/6.
 */
public interface RecordService {
    RecordEntity create(SessionEntity session,Long tagId,Object content);
    List<RecordEntity> list(SessionEntity session, Long tagId);
}
