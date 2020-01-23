package com.starbucks.api;

import com.starbucks.config.SharedConfig;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PingResourceApi {

    private SharedConfig config;

    @Inject
    public PingResourceApi(final SharedConfig config) {
        this.config = config;
    }

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("{ \"data\":\"Pong! from " + config.getString("appName") + "\" }").build();
    }
}
