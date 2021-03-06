package com.smalllife.dao.impl;

import com.smalllife.common.mongo.mongosupport.DatastoreExt;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Aaron on 30/12/2016.
 */
@Setter
@Getter
@Slf4j
public class AbstractDao<T> {
    @Autowired
    protected DatastoreExt datastore;
    private Class<?> clazz;
    @PostConstruct
    public void init() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        try {
            clazz = Class.forName(parameterizedType.getActualTypeArguments()[0].getTypeName());
            datastore.ensureIndexes(clazz, true);
            log.info("ensureIndex {}",clazz.getName());
        } catch (ClassNotFoundException e) {
            log.error("ensureIndex",e);
        }
    }
    public Query createQuery(){
        return datastore.createQuery(clazz);
    }
    public UpdateOperations createUpdateOperations(){
        return datastore.createUpdateOperations(clazz);
    }
}
