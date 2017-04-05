package com.smalllife.dao.model;


import com.google.common.base.Strings;
import com.google.common.util.concurrent.Runnables;

/**
 * Created by Aaron on 02/04/2017.
 */
public enum CommandType {
    AddRecord(1, "添加记录","tagId;content"), AddTag(2, "添加标签","tagName;tagType"), AllTag(3, "查看现有标签",""), TagContent(4, "查看标签内容","tagId"), Reset(0,"重置" ,"" );
    private int id;
    private String name;
    private String rule;

    CommandType(int id, String name,String rule) {
        this.id = id;
        this.name = name;
        this.rule=rule;
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

    public static String toCommandType(){
        CommandType[] types=CommandType.values();
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i <types.length ; i++) {
            sb.append(types[i].toString());
            if(i<types.length-1){
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        if(Strings.isNullOrEmpty(rule)){
        return id +
                "--->" + name;
        }
        return id +
                "--->" + name+"-->"+rule;
    }
    public static CommandType getCommand(String command){
        try{
        int id=Integer.valueOf(command);
        for (int i = 0; i < values().length; i++) {
            CommandType temp = values()[i];
            if(temp.id==id){
                return temp;
            }
        }}catch (RuntimeException e){

        }
        return null;
    }
    public static void main(String[] args) {
        System.out.println(toCommandType());
    }
}
