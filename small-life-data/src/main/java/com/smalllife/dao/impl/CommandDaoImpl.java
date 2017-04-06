package com.smalllife.dao.impl;

import com.smalllife.dao.CommandDao;
import com.smalllife.dao.model.CommandEntity;
import com.smalllife.dao.model.CommandType;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Aaron on 02/04/2017.
 */
@Service
public class CommandDaoImpl extends AbstractDao<CommandEntity> implements CommandDao {
    @Override
    public CommandEntity find(ObjectId sessionId) {
        Query<CommandEntity> query = createQuery();
        query.field(CommandEntity.Fields.sessionId).equal(sessionId);
        return query.get();
    }

    @Override
    public void delete(ObjectId sessionId) {
        Query<CommandEntity> query = createQuery();
        query.field(CommandEntity.Fields.sessionId).equal(sessionId);
        datastore.delete(query);
    }

    @Override
    public CommandEntity save(ObjectId sessionId, CommandType type) {
        Query<CommandEntity> query = createQuery();
        query.field(CommandEntity.Fields.sessionId).equal(sessionId);
        UpdateOperations operations = createUpdateOperations();
        operations.set(CommandEntity.Fields.command, type);
        operations.set(CommandEntity.Fields.date, new Date());
        return datastore.findAndModify(query, operations, false, true);
    }

    @Override
    public CommandEntity save(ObjectId sessionId, CommandType type,String content) {
        Query<CommandEntity> query = createQuery();
        query.field(CommandEntity.Fields.sessionId).equal(sessionId);
        UpdateOperations operations = createUpdateOperations();
        operations.set(CommandEntity.Fields.command, type);
        operations.set(CommandEntity.Fields.content, content);
        operations.set(CommandEntity.Fields.date, new Date());
        return datastore.findAndModify(query, operations, false, true);
    }

}
