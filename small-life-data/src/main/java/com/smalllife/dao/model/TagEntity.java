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
@Entity(value = "Tag", noClassnameStored = true)
public class TagEntity {
    @Id
    private ObjectId id;
    //tag name
    @Property(Fields.name)
    private String name;
    //    user id
    @Property(Fields.openId)
    private String openId;
    //    type 某一类信息
    @Property(Fields.type)
    private ContentType type;
    //记录次数
    @Property(Fields.recordCount)
    private String recordCount;
    //    修改时间
    @Property(Fields.modifyTime)
    private Long modifyTime;
    //    创建时间
    @Property(Fields.createTime)
    private Long createTime;

    public interface Fields {
        String id = "_id", name = "N", openId = "OI", type = "T", recordCount = "RC", createTime = "CT", modifyTime = "MT";
    }

}
