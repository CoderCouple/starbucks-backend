package com.starbucks.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResourceApi {

    @GET
    @Path("/order")
    public Response getAllOrder() {
        return Response.ok().entity("Pong!").build();
    }

    @GET
    @Path("/order/{id}")
    public Response getOrder() {
        return Response.ok().entity("Pong!").build();
    }

    @POST
    @Path("/order/{id}")
    public Response createOrder() {
        return Response.ok().entity("Pong!").build();
    }

    @PUT
    @Path("/order/{id}")
    public Response updateOrder() {
        return Response.ok().entity("Pong!").build();
    }

    @DELETE
    @Path("/order/{id}")
    public Response deleteOrder() {
        return Response.ok().entity("Pong!").build();
    }

}
