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
@Entity(value = "MetaData", noClassnameStored = true)
public class MetaEntity {
    @Id
    private ObjectId id;
    @Property(Fields.name)
    private String name;
    @Property(Fields.number)
    private Long number;

    public interface Fields {
        String id = "_id", name = "N", number = "Number";
    }
}
