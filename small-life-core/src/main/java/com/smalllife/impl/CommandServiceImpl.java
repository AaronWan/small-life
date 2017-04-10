package com.smalllife.impl;

import com.smalllife.*;
import com.smalllife.common.util.JsonUtil;
import com.smalllife.dao.CommandDao;
import com.smalllife.dao.SessionService;
import com.smalllife.dao.model.*;
import com.smalllife.model.Record;
import com.smalllife.model.WebChatMsg;
import com.smalllife.model.WebchatContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
    private RecordService recordService;
    @Autowired
    private CommandDao commandDao;
    @Autowired
    private SessionService sessionService;

    @Autowired
    private TagService tagService;

    @Override
    public String processCommand(WebChatMsg msg) {
        log.debug("processCommand:{}", msg);
        String content=msg.getContent();
        SessionEntity sessionEntity = sessionService.getSession(msg.getToUserName(), msg.getFromUserName());
        CommandEntity commandEntity = commandDao.find(sessionEntity.getId());
        log.debug("command:{}",JsonUtil.toJson(commandEntity));
        WebchatContentType type = msg.getType();
        if(CommandType.isReset(content)){
            commandDao.delete(sessionEntity.getId());
            return WebChatMsg.getTextMsg(sessionEntity, commandEntity.getCommand().getName()+"命令已清除");
        }
        if (commandEntity == null) {
            if (!type.equals(WebchatContentType.text)) {
                return WebChatMsg.getTextMsg(sessionEntity, CommandType.toCommandType());
            } else {
                CommandType commandType;
                try {
                    commandType = CommandType.getCommandFromUserContent(content);
                    if (commandType == null) {
                        return WebChatMsg.getTextMsg(sessionEntity,  CommandType.toCommandType());
                    } else {
                        if(commandType.equals(CommandType.AllTag)){
                            commandDao.save(sessionEntity.getId(), CommandType.TagContent,commandType.getContent());
                            return WebChatMsg.getTextMsg(sessionEntity,"输入标签id查看记录内容"+ JsonUtil.toPrettyJson(tagService.list(sessionEntity).stream().map(item->{item.setId(null);item.setCreateTime(null);item.setModifyTime(null);item.setSessionId(null);return item;}).collect(Collectors.toList())));
                        }else if(commandType.equals(CommandType.TagContent)){
                            commandDao.save(sessionEntity.getId(), CommandType.TagContent,CommandType.TagContent.getContent());
                            return WebChatMsg.getTextMsg(sessionEntity,"输入标签id查看记录内容"+ JsonUtil.toPrettyJson(tagService.list(sessionEntity).stream().map(item->{item.setId(null);item.setCreateTime(null);item.setModifyTime(null);item.setSessionId(null);return item;}).collect(Collectors.toList())));
                        }
                        commandDao.save(sessionEntity.getId(), commandType);
                        return WebChatMsg.getTextMsg(sessionEntity,"您选择了"+commandType.getName()+"，请输入内容～+～");
                    }
                } catch (Throwable e) {
                    log.error("processCommand", e);
                    return WebChatMsg.getTextMsg(sessionEntity, CommandType.toCommandType());
                }
            }
        }else{
            if(commandEntity.getCommand().equals(CommandType.AddTag)){
                String[] tag=content.split(",");
                String tagName=tag[0];
                TagEntity tagEntity;
                if (tag.length == 2) {
                    tagEntity=tagService.save(sessionEntity, tagName, ContentType.valueOf(tag[1]));
                }else{
                    tagEntity=tagService.save(sessionEntity, tagName, ContentType.text);
                }
                commandDao.save(sessionEntity.getId(),CommandType.AddRecord,tagEntity.getTagId()+"");
                return WebChatMsg.getTextMsg(sessionEntity, tagName+"标签添加成功,请添加记录或0退出添加");
            }else if(commandEntity.getCommand().equals(CommandType.AddRecord)){
                Long tagId=Long.valueOf(commandEntity.getContent());
                TagEntity tagEntity=tagService.get(sessionEntity,tagId);
                RecordEntity entity;
                if(tagEntity.getType().equals(ContentType.date)){
                    entity = recordService.create(sessionEntity, tagId, new Date());
                }else{
                    entity=recordService.create(sessionEntity,tagId,content);
                }
                return WebChatMsg.getTextMsg(sessionEntity, "第"+entity.getId()+"条记录保存成功，您可以继续添加或0退出");
            }else if(commandEntity.getCommand().equals(CommandType.TagContent)){
                Long tagId=Long.valueOf(content);
                List<RecordEntity> tagEntity=recordService.list(sessionEntity,tagId);
                return WebChatMsg.getTextMsg(sessionEntity, JsonUtil.toPrettyJson(tagEntity.stream().map(item-> Record.getRecord(item)).collect(Collectors.toList())));
            }
            return WebChatMsg.getTextMsg(sessionEntity, "未开发，谢谢关注");
        }
    }


}
