package com.starbucks.api;

import com.starbucks.payload.LoginPayload;
import com.starbucks.payload.RegistrationPayload;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResourceApi {

    @POST
    @Path("user/register")
    public Response register(final RegistrationPayload payload) {
        return Response.ok().entity(payload).build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("user/login")
    public Response login(final LoginPayload payload) {
        return Response.ok().entity("Pong").build();
    }

    @GET
    @Path("user/logout/{userId}")
    public Response logout(@PathParam("userId") final int userId) {
        return Response.ok().entity("Pong").build();
    }


    @GET
    @Path("user/history/{userId}")
    public Response history(@PathParam("userId") final int userId) {
        return Response.ok().entity("Pong").build();
    }


    @GET
    @Path("/user/{userId}")
    public Response getUser(@PathParam("userId") final int userId) {
        return Response.ok().entity("Pong").build();
    }


}
