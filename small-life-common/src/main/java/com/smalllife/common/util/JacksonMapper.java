package com.smalllife.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class JacksonMapper {
    private static final ObjectMapper object = new ObjectMapper();

    private static final XmlMapper xml = new XmlMapper();

    private JacksonMapper() {
    }

    public static ObjectMapper getObjectMapper() {
        return object;
    }

    public static XmlMapper getXmlMapper() {
        return xml;
    }
}