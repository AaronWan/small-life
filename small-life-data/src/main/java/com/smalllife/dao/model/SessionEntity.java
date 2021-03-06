package com.smalllife.dao.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * Created by Aaron on 02/04/2017.
 */
@Setter
@Getter
@Entity(value = "Session", noClassnameStored = true)
public class SessionEntity {
    @Id
    private ObjectId id;
    @Property(Fields.appOpenId)
    private String appOpenId;
    @Property(Fields.openId)
    private String openId;
    @Property(Fields.createTime)
    private Long createTime;
    @Property(Fields.updateTime)
    private Long updateTime;

    public interface Fields {
        String id = "_id", appId = "AI", appOpenId = "AOI",openId="OI", createTime = "CT",updateTime="UT";
    }
}
