package com.smalllife.webchat.resource;

import com.smalllife.CommandService;
import com.smalllife.common.util.XMLUtil;
import com.smalllife.dao.model.CommandType;
import com.smalllife.model.WebChatMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private CommandService commandService;
    @Path("/")
    @POST
    public Response receiveMessage(@QueryParam("signature") String signature,
                                   @QueryParam("openid") String openId,
                                   @QueryParam("timestamp") String timestamp,
                                   @QueryParam("nonce") String nonce, @Context HttpServletRequest request) throws IOException {
        String temp = IOUtils.toString(request.getInputStream(), "utf-8");
        try {
            WebChatMsg arg = XMLUtil.xmlToBean(temp, WebChatMsg.class);
            String msg = commandService.processCommand(arg);
            log.info(msg);
            return Response.ok(msg).type(MediaType.APPLICATION_XML_TYPE).encoding("utf-8").build();
        } catch (Throwable e) {
            log.error("receiveMessage:msg:{}", temp);
            throw e;
        }
    }

}
