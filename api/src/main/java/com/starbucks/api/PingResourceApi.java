package com.starbucks.api;

import com.starbucks.config.SharedConfig;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("v1")
public class PingResourceApi {

    private SharedConfig config;

    @Inject
    public PingResourceApi(SharedConfig config) {
        this.config=config;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/ping")
    public String ping() {
        return "Pong!" +" from "+config.getString("appName");
    }
}
