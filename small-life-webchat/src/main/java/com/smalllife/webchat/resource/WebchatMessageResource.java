package com.smalllife.webchat.resource;

import com.smalllife.qq.AesException;
import com.smalllife.qq.XMLParse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

/**
 * Created by Aaron on 27/03/2017.
 */
@Service
@Path("/webchat")
@Slf4j
public class WebchatMessageResource {
    @Path("/")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String receiveMessage(@QueryParam("signature") String signature,
                                 @QueryParam("openid") String openId,
                                 @QueryParam("timestamp") String timestamp,
                                 @QueryParam("nonce") String nonce,@RequestBody byte[] body) throws AesException {
        log.info("params:{}",new String(body));
        Object[] content=XMLParse.extract(new String(body));
        return null;
    }
}
