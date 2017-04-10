package com.smalllife.dao.model;

/**
 * Created by Aaron on 17/4/7.
 */
public interface ICommandHandler {
    void process(SessionEntity entity);
    void preProcess(SessionEntity entity);
}
