package com.starbucks.api;

import com.starbucks.config.SharedConfig;
import com.starbucks.service.PingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "Health Check")
@Path("v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PingApiResource {

    private SharedConfig config;
    private PingService pingService;

    @Inject
    public PingApiResource(final SharedConfig config, final PingService pingService) {
        this.config = config;
        this.pingService = pingService;
    }

    @GET
    @Path("/ping")
    @ApiOperation(value = "Ping API",
    notes = "Simple Ping API for health check",
    response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "PING NOT FOUND"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    public Response ping() {
        String res = pingService.getPingResponse();
        return Response.ok().entity("{\"data\":\"" + res + " from " + config.getString("appName") + "\"}").build();
    }
}
