package com.starbucks.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("v1")
public class FibonacciResourceApi {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/fibonacci")
    public String ping() {
        return "Pong!";
    }
}
