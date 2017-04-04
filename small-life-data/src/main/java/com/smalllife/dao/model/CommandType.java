package com.smalllife.dao.model;


/**
 * Created by Aaron on 02/04/2017.
 */
public enum CommandType {
    AddRecord(1, "添加记录"), AddTag(2, "添加标签"), AllTag(3, "查看现有标签"), TagContent(4, "查看标签内容");
    private int id;
    private String name;

    CommandType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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
        return id +
                "--->" + name;
    }
    public static CommandType getCommand(String command){
        int id=Integer.valueOf(command);
        for (int i = 0; i < values().length; i++) {
            CommandType temp = values()[i];
            if(temp.id==id){
                return temp;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        System.out.println(toCommandType());
    }
}
