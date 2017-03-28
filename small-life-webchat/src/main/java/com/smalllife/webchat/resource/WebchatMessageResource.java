package com.smalllife.webchat.resource;

import com.smalllife.qq.AesException;
import com.smalllife.qq.model.BaseMsg;
import com.smalllife.qq.model.TextMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Aaron on 27/03/2017.
 */
@Service
@Path("/webchat")
@Slf4j
public class WebchatMessageResource {
    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public BaseMsg receiveMessage(@QueryParam("signature") String signature,
                                 @QueryParam("openid") String openId,
                                 @QueryParam("timestamp") String timestamp,
                                 @QueryParam("nonce") String nonce, @BeanParam TextMsg msg) throws AesException {
        return msg;
    }

}
