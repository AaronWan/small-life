package com.smalllife.webchat.resource;

import com.smalllife.common.util.XMLUtil;
import com.smalllife.qq.model.TextMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by Aaron on 27/03/2017.
 */
@Service
@Path("/webchat")
@Slf4j
public class WebchatMessageResource {
    @Path("/")
    @POST
    public Response receiveMessage(@QueryParam("signature") String signature,
                                   @QueryParam("openid") String openId,
                                   @QueryParam("timestamp") String timestamp,
                                   @QueryParam("nonce") String nonce, @Context HttpServletRequest request) throws IOException {
        TextMsg arg = XMLUtil.xmlToBean(IOUtils.toString(request.getInputStream(), "utf-8"), TextMsg.class);
        TextMsg result=new TextMsg();
        result.setContent("I love you");
        result.setCreateTime(System.currentTimeMillis());
        result.setFromUserName(arg.getToUserName());
        result.setToUserName(arg.getFromUserName());
        return Response.ok(XMLUtil.beanToXml(result)).build();
    }

}
