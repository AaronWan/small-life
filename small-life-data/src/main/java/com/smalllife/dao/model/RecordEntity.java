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
@Entity(value = "Record", noClassnameStored = true)
public class RecordEntity {
    @Id
    private Long id;

    @Property(Fields.tagId)
    private Long tagId;

    @Property(Fields.sessionId)
    private ObjectId sessionId;

    @Property(Fields.content)
    private Object content;

    @Property(Fields.createTime)
    private Long createTime;

    public interface Fields {
        String id = "_id", tagId = "TId", sessionId = "SId",  content = "C", createTime = "CT";
    }
}
