package com.smalllife.model;

import com.smalllife.dao.model.RecordEntity;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Aaron on 06/04/2017.
 */
@Data
public class Record {
    String createTime;
    String content;
    private static SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd hh-mm-ss");

    public static Record getRecord(RecordEntity entity){
        Record record=new Record();
        if(entity.getContent() instanceof Date)
        record.setContent(sdf.format(entity.getContent()));

        record.setCreateTime(sdf.format(entity.getCreateTime()));
        return record;
    }
}
