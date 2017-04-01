package com.smalllife.mongosupport;

import com.smalllife.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Aaron on 01/04/2017.
 */
@Slf4j
public class FileConfig implements IConfig {
    private String content;
    private Properties configMap = new Properties();
    private List<String> configLine;
    private String configName;

    public FileConfig(String configName) {
        this.configName = configName;
    }

    @PostConstruct
    public IConfig load() throws IOException {
        this.content = IOUtils.toString(ConfigFactory.class.getResourceAsStream("/conf" + File.separator + configName), "utf-8");
        this.configLine = null;
        this.configMap.clear();
        return this;
    }

    @Override
    public List<String> getLine() {
        if (configLine == null) {
            this.configLine = JsonUtil.fromJson(this.content, ArrayList.class);

        }
        return this.configLine;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public Object get(String key, Object def) {
        if (this.configMap.keySet().size()==0) {
            try {
                this.configMap.load(IOUtils.toInputStream(this.content));
            } catch (IOException e) {
                log.error("get", e);
            }
        }
        return this.configMap.getOrDefault(key, def);
    }

    @Override
    public String get(String key) {
        return get(key,"")+"";
    }

    @Override
    public String getString(String key, String def) {
        return get(key, def) + "";
    }

    @Override
    public Integer getInteger(String key, String def) {
        return Integer.valueOf(get(key, def) + "");
    }


    @Override
    public Long getLong(String key, String def) {
        return Long.valueOf(get(key, def) + "");
    }

    @Override
    public boolean getBool(String key) {
        return Boolean.valueOf(get(key,false)+"");
    }

    @Override
    public String getName() {
        return this.configName;
    }
}
