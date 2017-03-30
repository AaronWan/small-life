package com.smalllife.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Aaron on 28/03/2017.
 */
public class XMLUtil {
    /**
     * XML To Object
     *
     * @param content
     * @param cls
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T xmlToBean(String content, Class<T> cls) throws IOException {
        XmlMapper xml = JacksonMapper.getXmlMapper();
        T obj = xml.readValue(content, cls);
        return obj;
    }


    /**
     * @param xmlFile
     * @param cls
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T xmlToBean(File xmlFile, Class<T> cls) throws IOException {
        XmlMapper xml = JacksonMapper.getXmlMapper();
        T obj = xml.readValue(xmlFile, cls);
        return obj;
    }

    /**
     * @param xmlInputStream
     * @param cls
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T xmlToBean(InputStream xmlInputStream, Class<T> cls) throws IOException {
        XmlMapper xml = JacksonMapper.getXmlMapper();
        T obj = xml.readValue(xmlInputStream, cls);
        return obj;
    }

    public static <T> String beanToXml(T bean) throws JsonProcessingException {
        XmlMapper xml = JacksonMapper.getXmlMapper();

        String string = xml.writeValueAsString(bean);
        return string;
    }

}
