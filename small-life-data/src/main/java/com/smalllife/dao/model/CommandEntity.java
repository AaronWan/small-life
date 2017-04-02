package com.smalllife.dao.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.Date;

/**
 * Created by Aaron on 02/04/2017.
 * 记录命令
 */
@Setter
@Getter
@Entity(value = "Command", noClassnameStored = true)
@Indexes({
        @Index(name = "date_expire",
                fields = {
                        @Field(value = CommandEntity.Fields.date),
                },expireAfterSeconds = 60*15)
})
public class CommandEntity {
    @Id
    private ObjectId id;
    @Property(Fields.sessionId)
    private ObjectId sessionId;
    @Property(Fields.command)
    private CommandType command;
    @Property(Fields.date)
    private Date date;

    public interface Fields{
        String id="_id",sessionId="SId",command="C",date="D";
    }

}
