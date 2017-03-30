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
import java.util.HashMap;

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
        String msg=TextMsg.getReply(arg.getFromUserName(),arg.getToUserName(),arg.getContent());

        return Response.ok(msg).type(MediaType.APPLICATION_XML_TYPE).encoding("utf-8").build();
    }

}
