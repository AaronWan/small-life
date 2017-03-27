package com.smalllife.webchat.resource;

import com.google.common.collect.Lists;
import com.smalllife.qq.AesException;
import com.smalllife.qq.SHA1;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

/**
 * Created by Aaron on 26/03/2017.
 */
@Path("webchat")
@Produces(MediaType.APPLICATION_JSON)
@Service
public class WebchatConfigureResource {
    @Path("configure")
    @GET
    public boolean configure(@QueryParam("signature") String signature,
                     @QueryParam("echostr") String echostr,
                     @QueryParam("timestamp") String timestamp,
                     @QueryParam("nonce") String nonce) throws AesException {
        String token="kuailedashan";
        String aesKey="gxrdYpX1oyDgZdEpqWKdScmT3VPNKDBk4iAssxM66Fe";
        List<String> tokens= Lists.newArrayList(token,timestamp,nonce);
        Collections.sort(tokens);
        return SHA1.getSHA1(token,timestamp,nonce,aesKey).equals(signature);
    }


    public static void main(String[] args) {

    }
}
