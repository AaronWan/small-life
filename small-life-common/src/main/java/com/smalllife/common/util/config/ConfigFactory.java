package com.smalllife.common.util.config;


import lombok.Data;

/**
 * Created by Aaron on 01/04/2017.
 */
@Data
public class ConfigFactory {
    public static void getConfig(String configName,ConfigHandler handler){
        IConfig config=new FileConfig(configName);
        handler.load(config);
    }
}
