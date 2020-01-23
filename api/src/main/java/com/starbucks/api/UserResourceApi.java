package com.starbucks.api;

import com.starbucks.payload.RegistrationPayload;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResourceApi {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/login")
    public Response login() {
        return Response.ok().entity("Pong").build();
    }

    @POST
    @Path("/register")
    public Response register(final RegistrationPayload payload) {
        return Response.ok().entity(payload).build();
    }

    @GET
    @Path("/logout")
    public String logout() {
        return "Pong!";
    }


    @GET
    @Path("/history")
    public String history() {
        return "Pong!";
    }


    @GET
    @Path("/user")
    public String getAllUsers() {
        return "Pong!";
    }


    @GET
    @Path("/user/{id}")
    public String getUser() {
        return "Pong!";
    }


}
