package com.smalllife.rest.proxy;

import com.google.common.reflect.Reflection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.smalllife.common.util.config.ConfigFactory;
import com.smalllife.common.util.config.IConfig;
import com.smalllife.rest.proxy.annotation.RestResource;
import com.smalllife.rest.proxy.codec.AbstractRestCodeC;
import com.smalllife.rest.proxy.exception.RestProxyConfigException;
import com.smalllife.rest.proxy.exception.RestProxyInvokeException;
import com.smalllife.rest.proxy.exception.RestProxyRuntimeException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Aaron on 26/12/2016.
 */
@Slf4j
@Data
public class RestServiceProxyFactory {
    private String configName;
    private Map<String, RestServiceConfig> serviceConfigMaps = new HashMap<>();
    private Map<String, AbstractRestCodeC> serviceCodeCMaps = new HashMap<>();
    private final static RestClient restClient = new RestClient();

    public RestServiceProxyFactory() {
    }

    public void init() {
        ConfigFactory.getConfig(configName, (IConfig config) -> {
            String content = new String(config.getContent());
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            serviceConfigMaps = gson.fromJson(content, new TypeToken<Map<String, RestServiceConfig>>() {
            }.getType());

            serviceConfigMaps.forEach((key, value) -> {
                restClient.createHttpClientForService(key, value);
            });

            log.info("log RestServiceProxyFactory {} ", configName);
            log.info(gson.toJson(serviceConfigMaps));

        });
    }


    public <T> T newRestServiceProxy(Class<T> clazz) {
        return Reflection.newProxy(clazz, (Object proxy, Method method, Object[] args) -> {
            RestResource restResource = method.getDeclaringClass().getAnnotation(RestResource.class);
            AbstractRestCodeC codec = getCodeC(restResource);
            String serviceName = restResource.value();
            InvokeParams invokeParams = InvokeParams.getInstance(getConfig(serviceName), method, args);

            long start = System.currentTimeMillis();
            Object ret = null;

            try {
                ret = restClient.invoke(serviceName, invokeParams, codec);
            } catch (RestProxyRuntimeException e){
                log.error("access [{}({})] ,url:{},header:{},arg:{},errorCode:{},errorMsg:{}",
                        invokeParams.getServiceName(),invokeParams.getMethodPath(),invokeParams.getServiceUrl(),
                        invokeParams.getHeaders(),
                        invokeParams.getBody(),e.getCode(),e.getMessage()
                        );
                throw e;
            }finally {
                log.debug("method:{},cost:{} ms,url:{},arg:{},pathParams:{},queryParams:{},result:{}",invokeParams.getMethodType(),
                        System.currentTimeMillis()-start,
                        invokeParams.getServiceUrl(),
                        invokeParams.getBody(), invokeParams.getPathParams(), invokeParams.getQueryParams(),ret);
            }
            return ret;
        });
    }

    private AbstractRestCodeC getCodeC(RestResource restResource) {
        AbstractRestCodeC codeC = serviceCodeCMaps.get(restResource.value());
        if (serviceCodeCMaps.get(restResource.value()) == null) {
            if (restResource.codec() == null) {
                throw new RestProxyInvokeException("codec init error ,please check " + restResource.value() + " config ");
            }
            try {
                codeC = (AbstractRestCodeC) Class.forName(restResource.codec()).newInstance();
            } catch (Exception e) {
                throw new RestProxyConfigException("codec init error:" + restResource.codec());
            }
            serviceCodeCMaps.put(restResource.value(), codeC);
        }
        return codeC;
    }

    public RestServiceConfig getConfig(String serviceName){
        RestServiceConfig config=this.getServiceConfigMaps().get(serviceName);
        if(config==null){
            throw new RestProxyConfigException("config:"+configName+"'s "+serviceName+" 不存在");
        }
        return config;
    }
    public static class RestServiceConfig {

        private String address;

        private int socketTimeOut;

        private int connectionTimeOut;

        private String serviceName;

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getSocketTimeOut() {
            return socketTimeOut;
        }

        public void setSocketTimeOut(int socketTimeOut) {
            this.socketTimeOut = socketTimeOut;
        }

        public int getConnectionTimeOut() {
            return connectionTimeOut;
        }

        public void setConnectionTimeOut(int connectionTimeOut) {
            this.connectionTimeOut = connectionTimeOut;
        }

    }
}
