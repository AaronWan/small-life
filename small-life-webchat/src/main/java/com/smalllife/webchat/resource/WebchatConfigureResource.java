package com.smalllife.webchat.resource;

import com.smalllife.qq.AesException;
import com.smalllife.qq.SHA1;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by Aaron on 26/03/2017.
 */
@Path("webchat")
@Produces(MediaType.APPLICATION_JSON)
@Service
public class WebchatConfigureResource {
    @Path("/")
    @GET
    public String configure(@QueryParam("signature") String signature,
                            @QueryParam("echostr") String echostr,
                            @QueryParam("timestamp") String timestamp,
                            @QueryParam("nonce") String nonce) throws AesException {
        String token = "kuailedashan";
        if (SHA1.getSHA1(token, timestamp, nonce).equals(signature)) {
            return echostr;
        } else {
            return null;
        }
    }

}
