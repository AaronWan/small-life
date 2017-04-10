package com.smalllife.dao.model;


import com.google.common.base.Strings;
import com.google.common.util.concurrent.Runnables;

/**
 * Created by Aaron on 02/04/2017.
 */
public enum CommandType implements ICommandHandler {
    AddRecord(1, "添加记录", "tagId;content") {
        @Override
        public void process(SessionEntity entity) {

        }

        @Override
        public void preProcess(SessionEntity entity) {

        }
    }, AddTag(2, "添加标签", "tagName,tagType") {
        @Override
        public void process(SessionEntity entity) {

        }

        @Override
        public void preProcess(SessionEntity entity) {

        }
    }, AllTag(3, "查看现有标签", "") {
        @Override
        public void process(SessionEntity entity) {

        }

        @Override
        public void preProcess(SessionEntity entity) {

        }
    }, TagContent(4, "查看标签内容", "tagId") {
        @Override
        public void process(SessionEntity entity) {

        }

        @Override
        public void preProcess(SessionEntity entity) {

        }
    }, Reset(0, "重置", "") {
        @Override
        public void process(SessionEntity entity) {

        }

        @Override
        public void preProcess(SessionEntity entity) {

        }
    };
    private int id;
    private String name;
    private String rule;
    private String content;

    CommandType(int id, String name, String rule) {
        this.id = id;
        this.name = name;
        this.rule = rule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static String toCommandType() {
        CommandType[] types = CommandType.values();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < types.length; i++) {
            sb.append(types[i].toString());
            if (i < types.length - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        if (Strings.isNullOrEmpty(rule)) {
            return id +
                    "--->" + name;
        }
        return id +
                "--->" + name + "-->" + rule;
    }

    private static CommandType getCommand(String command) {
        try {
            int id = Integer.valueOf(command);
            for (int i = 0; i < values().length; i++) {
                CommandType temp = values()[i];
                if (temp.id == id) {
                    return temp;
                }
            }
        } catch (RuntimeException e) {

        }
        return null;
    }

    public static boolean isReset(String content) {
        return content.equals(CommandType.Reset.getId() + "")
                ||
                content.toLowerCase().equals(CommandType.Reset.name().toLowerCase());
    }

    public static CommandType getCommandFromUserContent(String content) {
        String[] commandContent = content.split(" ");
        CommandType command = getCommand(commandContent[0]);
        if (commandContent.length > 1) {
            command.content = commandContent[1];
        }
        return command;
    }

}
