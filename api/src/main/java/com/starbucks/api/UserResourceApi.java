package com.starbucks.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("v1")
public class UserResourceApi {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/login")
    public String login() {
        return "Pong!";
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/register")
    public String register() {
        return "Pong!";
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/logout")
    public String logout() {
        return "Pong!";
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/history")
    public String history() {
        return "Pong!";
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/user")
    public String getAllUsers() {
        return "Pong!";
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/user/{id}")
    public String getUser() {
        return "Pong!";
    }


}
