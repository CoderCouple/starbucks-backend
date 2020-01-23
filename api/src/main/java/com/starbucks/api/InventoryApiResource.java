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
public class InventoryApiResource {

    @GET
    @Path("/inventory/{id}")
    public Response getInventoryById() {
        return Response.ok().entity("Pong!").build();
    }

    @POST
    @Path("/inventory")
    public Response createInventory() {
        return Response.ok().entity("Pong!").build();
    }

    @PUT
    @Path("/inventory")
    public Response updateInventory() {
        return Response.ok().entity("Pong!").build();
    }

    @DELETE
    @Path("/inventory")
    public Response deleteInventory() {
        return Response.ok().entity("Pong!").build();
    }

}
