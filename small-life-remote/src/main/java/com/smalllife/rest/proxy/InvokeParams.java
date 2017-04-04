package com.smalllife.rest.proxy;

import com.google.common.base.Strings;
import com.smalllife.rest.proxy.annotation.*;
import com.smalllife.rest.proxy.exception.RestProxyConfigException;
import lombok.Data;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
class InvokeParams {
    /**
     * 请求的Url
     */
    private String serviceUrl;
    /**
     * 请求服务器地址
     */
    private String serviceIP;
    /**
     * 方法路径
     */
    private String methodPath;
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 方法协议类型
     */
    private String methodType;
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> queryParams = new HashMap<>();
    private Map<String, String> pathParams = new HashMap<>();
    private Object body;
    private Class resultClazz;

    public static InvokeParams getInstance(RestServiceProxyFactory.RestServiceConfig config, Method method, Object[] args) {
        RestResource restResource = method.getDeclaringClass().getAnnotation(RestResource.class);
        InvokeParams invokeParams = new InvokeParams();
        invokeParams.setServiceName(config.getServiceName());
        Object methodType;
        String methodContentType;
        String uri ;
        if((methodType=method.getAnnotation(POST.class))!=null){
            POST post=(POST)methodType;
            invokeParams.methodType="POST";
            uri=post.value();
            methodContentType=post.contentType();
        }else if ((methodType=method.getAnnotation(GET.class))!=null){
            GET get=(GET)methodType;
            invokeParams.methodType="GET";
            uri=get.value();
            methodContentType=get.contentType();
        }else if ((methodType=method.getAnnotation(PUT.class))!=null){
            PUT put=(PUT)methodType;
            invokeParams.methodType="PUT";
            uri=put.value();
            methodContentType=put.contentType();
        }else if ((methodType=method.getAnnotation(DELETE.class))!=null){
            DELETE delete=(DELETE)methodType;
            invokeParams.methodType="DELETE";
            uri=delete.value();
            methodContentType=delete.contentType();
        }else {
            throw new RestProxyConfigException(method.getName()+" not have method type");
        }

        String serviceUrl = getResourceAddress(config, uri);
        invokeParams.resultClazz = method.getReturnType();
        invokeParams.setServiceUrl(serviceUrl);
        invokeParams.setMethodPath(uri);
        invokeParams.setServiceIP(config.getAddress());
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter p = parameters[i];
            Annotation[] annotations = p.getAnnotations();
            Annotation annotation;
            if (annotations.length == 0) {
                continue;
            } else {
                annotation = annotations[0];
            }

            if (annotation instanceof Body) {
                invokeParams.body = args[i];
            } else if (annotation instanceof PathParams) {
                invokeParams.pathParams = args[i] != null ? (Map<String, String>) args[i] : null;
            } else if (annotation instanceof QueryParamsMap) {
                invokeParams.queryParams = args[i] != null ? (Map<String, String>) args[i] : null;
            } else if (annotation instanceof HeaderMap) {
                invokeParams.headers = args[i] != null ? (Map<String, String>) args[i] : new LinkedHashMap<>();
            }
        }
        invokeParams.serviceUrl = getServiceURL(serviceUrl, invokeParams.pathParams,invokeParams.queryParams);
        String globContentType=restResource.contentType();
        if(!Strings.isNullOrEmpty(globContentType)){
            invokeParams.headers.put("Content-Type", globContentType);
        }
        if (!Strings.isNullOrEmpty(methodContentType)) {
            invokeParams.headers.put("Content-Type",methodContentType );
        }
        return invokeParams;
    }

    static Pattern p = Pattern.compile("(\\{[^{}]+\\})+");

    static String getServiceURL(String url, Map<String, String> pathParams,Map<String,String> queryParams) {
        String serviceUrl = url.replaceAll(" ", "");
        if(pathParams!=null) {
            Matcher m = p.matcher(url);
            while (m.find()) {
                String pathPlaceHold = m.group();
                String pathPlaceHoldWithOutChar = pathPlaceHold.substring(1, pathPlaceHold.length() - 1);
                String value = pathParams.get(pathPlaceHoldWithOutChar);
                if (Strings.isNullOrEmpty(value)) {
                    throw new RestProxyConfigException(url + "参数" + pathPlaceHoldWithOutChar + "在参数列表中不存在");
                }
                serviceUrl = serviceUrl.replace(pathPlaceHold, value);
            }
        }
        if(queryParams!=null){
            serviceUrl+=map2QueryParams(queryParams);
        }
        return serviceUrl;
    }
    static String map2QueryParams(Map<String, String> map) {
        StringBuffer sb = new StringBuffer("?");
        map.forEach((k, v) -> {
            sb.append(k).append("=").append(v).append("&");
        });
        if (sb.length() > 1) {
            return sb.toString().substring(0, sb.length() - 1);
        } else {
            return "";
        }
    }

    private static String getResourceAddress(RestServiceProxyFactory.RestServiceConfig config, String restUri) {
        String address = config.getAddress();
        if (address == null) {
            address = "";
        }
        String template = "%s";
        if (restUri.startsWith("/")) {
            template += "%s";
        } else {
            template += "/%s";
        }
        return String.format(template, address, restUri);
    }

}