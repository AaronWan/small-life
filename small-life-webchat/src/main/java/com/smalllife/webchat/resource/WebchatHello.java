package com.smalllife.webchat.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created by Aaron on 17/3/29.
 */
@Service
@Path("/")
@Slf4j
public class WebchatHello {
    @Path("/")
    @GET
    public String configure() {
        return "hello";
    }
}
