package com.starbucks.api;

import io.swagger.annotations.Api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "User")
@Path("v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResourceApi {

    @GET
    @Path("/order/{id}")
    public Response getOrderById() {
        return Response.ok().entity("Pong!").build();
    }

    @POST
    @Path("/order")
    public Response createOrder() {
        return Response.ok().entity("Pong!").build();
    }

    @PUT
    @Path("/order")
    public Response updateOrder() {
        return Response.ok().entity("Pong!").build();
    }

    @DELETE
    @Path("/order")
    public Response deleteOrder() {
        return Response.ok().entity("Pong!").build();
    }

}
