package com.starbucks.api;

import com.starbucks.config.SharedConfig;
import com.starbucks.service.PingService;
import io.swagger.annotations.Api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "Test")
@Path("v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PingResourceApi {

    private SharedConfig config;
    private PingService pingService;

    @Inject
    public PingResourceApi(final SharedConfig config, final PingService pingService) {
        this.config = config;
        this.pingService = pingService;
    }

    @GET
    @Path("/ping")
    public Response ping() {
        String res = pingService.getPingResponse();
        return Response.ok().entity("{\"data\":\"" + res + " from " + config.getString("appName") + "\"}").build();
    }
}
