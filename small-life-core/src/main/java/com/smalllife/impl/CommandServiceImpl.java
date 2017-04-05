package com.smalllife.impl;

import com.smalllife.CommandService;
import com.smalllife.TagService;
import com.smalllife.WebChatEventService;
import com.smalllife.WebChatMsgService;
import com.smalllife.common.util.JsonUtil;
import com.smalllife.dao.CommandDao;
import com.smalllife.dao.SessionService;
import com.smalllife.dao.model.CommandEntity;
import com.smalllife.dao.model.CommandType;
import com.smalllife.dao.model.ContentType;
import com.smalllife.dao.model.SessionEntity;
import com.smalllife.model.WebChatMsg;
import com.smalllife.model.WebchatContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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

    @Autowired
    private TagService tagService;

    @Override
    public String processCommand(WebChatMsg msg) {
        log.debug("processCommand:{}", msg);
        SessionEntity sessionEntity = sessionService.getSession(msg.getToUserName(), msg.getFromUserName());
        CommandEntity commandEntity = commandDao.find(sessionEntity.getId());
        log.debug("command:{}",JsonUtil.toJson(commandEntity));
        WebchatContentType type = msg.getType();
        if (commandEntity == null) {
            if (!type.equals(WebchatContentType.text)) {
                return WebChatMsg.getTextMsg(sessionEntity, CommandType.toCommandType());
            } else {
                CommandType commandType;
                try {
                    commandType = CommandType.getCommand(msg.getContent());
                    if (commandType == null) {
                        return WebChatMsg.getTextMsg(sessionEntity,  CommandType.toCommandType());
                    } else {
                        commandDao.save(sessionEntity.getId(), commandType);
                        return WebChatMsg.getTextMsg(sessionEntity,"您选择了"+commandType.getName()+"，请输入内容～+～");
                    }
                } catch (Throwable e) {
                    log.error("processCommand", e);
                    return WebChatMsg.getTextMsg(sessionEntity, CommandType.toCommandType());
                }
            }
        }else{
            if(commandEntity.getCommand()==CommandType.AddTag){
                String[] tag=msg.getContent().split("|");
                tagService.save(sessionEntity,tag[0], ContentType.valueOf(tag[1]));
                return WebChatMsg.getTextMsg(sessionEntity, "Tag保存成功");
            }else if(commandEntity.getCommand()==CommandType.AllTag){
                return WebChatMsg.getTextMsg(sessionEntity, JsonUtil.toPrettyJson(tagService.list(sessionEntity).stream().map(item->{item.setId(null);item.setCreateTime(null);item.setModifyTime(null);item.setSessionId(null);return item;}).collect(Collectors.toList())));
            }
            return WebChatMsg.getTextMsg(sessionEntity, "未开发，谢谢关注");
        }
    }


}
