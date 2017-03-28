package com.smalllife.webchat.resource;

import com.smalllife.qq.AesException;
import com.smalllife.qq.XMLParse;
import com.smalllife.qq.model.BaseMsg;
import com.smalllife.qq.model.TextMsg;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.util.*;

import static sun.jvm.hotspot.debugger.win32.coff.DebugVC50X86RegisterEnums.TAG;

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
