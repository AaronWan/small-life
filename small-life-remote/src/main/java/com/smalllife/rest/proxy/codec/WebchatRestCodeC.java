package com.smalllife.rest.proxy.codec;

import com.smalllife.common.util.JsonUtil;
import com.smalllife.rest.proxy.exception.RestProxyBusinessException;
import com.smalllife.rest.proxy.model.webchat.BaseResult;
import lombok.extern.log4j.Log4j;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by Aaron on 04/01/2017.
 */
@Log4j
public class WebchatRestCodeC extends AbstractRestCodeC{
    public <T> byte[] toProto(T obj) {
        return JsonUtil.toJson(obj).getBytes();
    }

    public <T> T responseHandler(Map<String,List<String>> headers, byte[] bytes, Class<T> clazz) {
        T ret = null;
        try {
            ret = JsonUtil.fromJson(new String(bytes, "UTF-8"), clazz);
        } catch (UnsupportedEncodingException e) {
            log.error("responseHandler:",e);
        }
        validateResult(ret);
        return ret;
    }


    @Override
    public void validateResult(Object ret) {
        if (BaseResult.class.isInstance(ret)) {
            BaseResult result = (BaseResult) ret;
            if (!result.isSuccess()) {
                throw new RestProxyBusinessException(result.getErrcode(), result.getErrmsg());
            }
        }
    }

}
