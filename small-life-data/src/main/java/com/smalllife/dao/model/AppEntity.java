package com.smalllife.dao.model;

import lombok.Getter;
import lombok.Setter;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * Created by Aaron on 02/04/2017.
 */
@Setter
@Getter
@Entity(value = "App", noClassnameStored = true)
public class AppEntity {
    @Id
    private Object id;
    @Property(Fields.openId)
    private String openId;
    @Property(Fields.name)
    private String name;
    @Property(Fields.createTime)
    private Long createTime;
    @Property(Fields.updateTime)
    private Long updateTime;

    public interface Fields {
        String id = "_id", openId = "OI", name = "N", createTime = "CT", updateTime = "UT";
    }
}
