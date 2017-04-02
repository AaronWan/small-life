package com.smalllife.dao.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;

/**
 * Created by Aaron on 02/04/2017.
 */
@Setter
@Getter
@Entity(value = "Session", noClassnameStored = true)
public class SessionEntity {
    private ObjectId i;
    private String appId;
    private String openId;
    private Long createTime;

}
