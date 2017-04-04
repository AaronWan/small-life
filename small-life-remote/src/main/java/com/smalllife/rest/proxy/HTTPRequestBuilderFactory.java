package com.smalllife.rest.proxy;

import com.smalllife.rest.proxy.codec.AbstractRestCodeC;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.BasicHttpEntity;

import java.io.ByteArrayInputStream;
import java.util.Map;

/**
 * Created by Aaron on 23/03/2017.
 */
public class HTTPRequestBuilderFactory {
    public static RequestBuilder create(InvokeParams invokeParams, AbstractRestCodeC codeC) {
        return RestProxyRequestBuilder.create(invokeParams.getMethodType()).build(invokeParams, codeC);
    }

    enum RestProxyRequestBuilder implements HTTPRequestBuilder {
        GET {
            @Override
            public RequestBuilder build(InvokeParams invokeParams, AbstractRestCodeC codeC) {
                RequestBuilder requestBuilder = RequestBuilder.get();
                setHttpHeaderAndUri(invokeParams, requestBuilder);
                return requestBuilder;
            }
        }, DELETE {
            @Override
            public RequestBuilder build(InvokeParams invokeParams, AbstractRestCodeC codeC) {
                RequestBuilder requestBuilder = RequestBuilder.delete();
                setHttpHeaderAndUri(invokeParams, requestBuilder);
                return requestBuilder;
            }
        }, POST {
            @Override
            public RequestBuilder build(InvokeParams invokeParams, AbstractRestCodeC codeC) {
                RequestBuilder requestBuilder = RequestBuilder.post();
                requestBuilder.setUri(invokeParams.getServiceUrl());
                setEntity(requestBuilder, invokeParams, codeC);
                setHttpHeaderAndUri(invokeParams, requestBuilder);
                return requestBuilder;
            }
        }, PUT {
            @Override
            public RequestBuilder build(InvokeParams invokeParams, AbstractRestCodeC codeC) {
                RequestBuilder requestBuilder = RequestBuilder.put();
                requestBuilder.setUri(invokeParams.getServiceUrl());
                setEntity(requestBuilder, invokeParams, codeC);
                setHttpHeaderAndUri(invokeParams, requestBuilder);
                return requestBuilder;
            }
        };

        void setHttpHeaderAndUri(InvokeParams invokeParams, RequestBuilder requestBuilder) {
            for (Map.Entry<String, String> e : invokeParams.getHeaders().entrySet()) {
                requestBuilder.addHeader(e.getKey(), e.getValue());
            }
            requestBuilder.setUri(invokeParams.getServiceUrl());
        }

        public void setEntity(RequestBuilder requestBuilder, InvokeParams invokeParams, AbstractRestCodeC codeC) {
            BasicHttpEntity entity = new BasicHttpEntity();
            entity.setContentEncoding("UTF-8");
            if (invokeParams.getBody() != null) {
                byte[] bytes = codeC.toProto(invokeParams.getBody());
                entity.setContentLength(bytes.length);
                entity.setContent(new ByteArrayInputStream(bytes));
                requestBuilder.setEntity(entity);
            }
        }

        public static HTTPRequestBuilder create(String methodType) {
            return valueOf(methodType);
        }



    }

    interface HTTPRequestBuilder {
        RequestBuilder build(InvokeParams invokeParams, AbstractRestCodeC codeC);
    }
}
