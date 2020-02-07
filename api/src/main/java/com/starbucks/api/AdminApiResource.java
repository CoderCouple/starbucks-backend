package com.starbucks.api;

import io.swagger.annotations.Api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "Admin")
@Path("v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminApiResource {

    @GET
    @Path("/admin/orders")
    public Response getAllOrder() {
        return Response.ok().entity("Pong!").build();
    }

    @GET
    @Path("admin/users")
    public Response getAllUsers() {
        return Response.ok().entity("Pong!").build();
    }

    @GET
    @Path("admin/orders/filter")
    public Response getOrderFilter() {
        return Response.ok().entity("Pong!").build();
    }

}
