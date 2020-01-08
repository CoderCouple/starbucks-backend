package com.starbucks.api;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("v1")
public class OrderResourceApi {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/order")
    public String getAllOrder() {
        return "Pong!";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/order/{id}")
    public String getOrder() {
        return "Pong!";
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/order/{id}")
    public String createOrder() {
        return "Pong!";
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/order/{id}")
    public String updateOrder() {
        return "Pong!";
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/order/{id}")
    public String deleteOrder() {
        return "Pong!";
    }

}
