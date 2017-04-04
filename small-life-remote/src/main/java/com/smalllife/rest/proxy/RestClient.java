package com.smalllife.rest.proxy;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.smalllife.rest.proxy.codec.AbstractRestCodeC;
import com.smalllife.rest.proxy.exception.RestProxyInvokeException;
import com.smalllife.rest.proxy.exception.RestProxyRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Simple Wrapper of HttpClient for FSI Protocol Created by liyiguang on 16/1/14.
 */
@Slf4j
public class RestClient {


    protected static final int DEFAULT_MAX_CONNECTION = 512;
    protected static final int DEFAULT_MAX_PER_ROUTE_CONNECTION = 50;

    protected static final int DEFAULT_SOCKET_TIMEOUT = 5000;
    protected static final int DEFAULT_CONNECTION_TIMEOUT = 2000;

    private CloseableHttpClient defaultHttpClient;

    private Map<String, CloseableHttpClient> httpClientMap = Maps.newConcurrentMap();

    public RestClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(DEFAULT_MAX_CONNECTION);
        connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE_CONNECTION);

        SocketConfig.Builder sb = SocketConfig.custom();
        sb.setSoKeepAlive(true);
        sb.setTcpNoDelay(true);
        connectionManager.setDefaultSocketConfig(sb.build());

        HttpClientBuilder hb = HttpClientBuilder.create();
        hb.setConnectionManager(connectionManager);

        RequestConfig.Builder rb = RequestConfig.custom();
        rb.setSocketTimeout(DEFAULT_SOCKET_TIMEOUT);
        rb.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT);

        hb.setDefaultRequestConfig(rb.build());

        defaultHttpClient = hb.build();
    }

    public void createHttpClientForService(String serviceKey, RestServiceProxyFactory.RestServiceConfig serviceConfig) {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(50);
        connectionManager.setDefaultMaxPerRoute(50);

        SocketConfig.Builder sb = SocketConfig.custom();
        sb.setSoKeepAlive(true);
        sb.setTcpNoDelay(true);
        connectionManager.setDefaultSocketConfig(sb.build());

        HttpClientBuilder hb = HttpClientBuilder.create();
        hb.setConnectionManager(connectionManager);

        RequestConfig.Builder rb = RequestConfig.custom();
        rb.setSocketTimeout(serviceConfig.getSocketTimeOut() == 0 ? DEFAULT_SOCKET_TIMEOUT
                : serviceConfig.getSocketTimeOut());
        rb.setConnectTimeout(serviceConfig.getConnectionTimeOut() == 0 ? DEFAULT_CONNECTION_TIMEOUT
                : serviceConfig.getConnectionTimeOut());

        hb.setDefaultRequestConfig(rb.build());

        try {
            CloseableHttpClient oldClient = httpClientMap.get(serviceKey);
            if (oldClient != null) {
                oldClient.close();
            }
        } catch (Exception e) {
            log.error("Close httpclient error,serviceKey:{}", serviceKey, e);
        }

        httpClientMap.put(serviceKey, hb.build());
    }

    public <R> R invoke(String serviceName, InvokeParams invokeParams, AbstractRestCodeC codeC) {
        DefaultRestResponseHandler<R> handler = new DefaultRestResponseHandler<>(invokeParams.getServiceUrl(), invokeParams.getResultClazz(), codeC);
        try {

            CloseableHttpClient serviceClient = httpClientMap.get(serviceName);
            if (serviceClient == null) {
                serviceClient = defaultHttpClient;
            }
            RequestBuilder requestBuilder = HTTPRequestBuilderFactory.create(invokeParams, codeC);
            try (CloseableHttpResponse response = serviceClient.execute(requestBuilder.build())) {
                R rst = handler.handleResponse(response);
                if (rst == null) {
                    throw new RestProxyInvokeException("返回结果为空");
                }
                return rst;
            }
        } catch (IOException e) {
            throw new RestProxyInvokeException("[" + serviceName + "]" + e.getLocalizedMessage(), e);
        }
    }


    public static final class DefaultRestResponseHandler<T> implements ResponseHandler<T> {

        private Class<T> clazz;
        private AbstractRestCodeC codeC;
        private String uri;

        public DefaultRestResponseHandler(String uri, Class<T> clazz, AbstractRestCodeC codeC) {
            this.uri = uri;
            this.clazz = clazz;
            this.codeC = codeC;
        }

        @Override
        public T handleResponse(HttpResponse response) throws IOException {

            int statusCode = response.getStatusLine().getStatusCode();
            Map<String, List<String>> headers = Maps.newHashMap();
            Header[] tempHeaders = response.getAllHeaders();
            for (int i = 0; i < tempHeaders.length; i++) {
                Header header = tempHeaders[i];
                if (CollectionUtils.isEmpty(headers.get(header.getName()))) {
                    headers.put(header.getName().toLowerCase(), Lists.newArrayList(header.getValue()));
                } else {
                    headers.get(header.getName().toLowerCase()).add(header.getValue());
                }
            }
            HttpEntity in = response.getEntity();
            switch (statusCode) {
                case HttpStatus.SC_OK:
                    return codeC.responseHandler(headers, EntityUtils.toByteArray(in), clazz);
                default:
                    if (in != null) {
                        return codeC.responseHandler(headers, EntityUtils.toByteArray(response.getEntity()), clazz);
                    }
                    throw new RestProxyRuntimeException(statusCode, "StatusCode:" + statusCode + ",url:" + this.uri);
            }
        }
    }

}
