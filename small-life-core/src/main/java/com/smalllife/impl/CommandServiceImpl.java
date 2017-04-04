package com.smalllife.impl;

import com.smalllife.CommandService;
import com.smalllife.WebChatEventService;
import com.smalllife.WebChatMsgService;
import com.smalllife.dao.CommandDao;
import com.smalllife.dao.SessionService;
import com.smalllife.dao.model.CommandEntity;
import com.smalllife.dao.model.CommandType;
import com.smalllife.dao.model.SessionEntity;
import com.smalllife.model.WebChatMsg;
import com.smalllife.model.WebchatContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Aaron on 02/04/2017.
 */
@Service
@Slf4j
public class CommandServiceImpl implements CommandService {
    @Autowired
    private WebChatEventService eventService;
    @Autowired
    private WebChatMsgService msgService;
    @Autowired
    private CommandDao commandDao;
    @Autowired
    private SessionService sessionService;

    @Override
    public void processCommand(WebChatMsg msg) {
        log.debug("processCommand:{}", msg);
        SessionEntity sessionEntity = sessionService.getSession(msg.getToUserName(), msg.getFromUserName());
        CommandEntity commandEntity = commandDao.find(sessionEntity.getId());
        WebchatContentType type = msg.getType();
        if (commandEntity == null) {
            if (!type.equals(WebchatContentType.text)) {
                msgService.sendTextMsg(sessionEntity, CommandType.toCommandType());
            } else {
                CommandType commandType = null;
                try {
                    commandType = CommandType.getCommand(msg.getContent());
                    if (commandType == null) {
                        msgService.sendTextMsg(sessionEntity, CommandType.toCommandType());
                    } else {
                        commandDao.save(sessionEntity.getId(), commandType);
                        msgService.sendTextMsg(sessionEntity,"您选择了"+commandType.name()+"，请输入内容～+～");
                    }
                } catch (Throwable e) {
                    log.error("processCommand", e);
                    msgService.sendTextMsg(sessionEntity, CommandType.toCommandType());
                }
            }
        }else{

        }
    }


}
