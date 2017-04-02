package com.smalllife.common.mongo.mongosupport;

import org.mongodb.morphia.AdvancedDatastore;
/**
 * 扩展datastore功能,提供切换db功能
 * Created by lirui on 2016-03-14 22:13.
 */
public interface DatastoreExt extends AdvancedDatastore {
  /**
   * 切换使用的db
   *
   * @param dbName db名字
   * @return datastore对象
   */
  DatastoreExt use(String dbName);
}