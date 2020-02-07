package com.starbucks.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starbucks.config.SharedConfig;
import com.starbucks.payload.LoginPayload;
import com.starbucks.payload.RegistrationPayload;
import com.starbucks.service.UserService;
import com.starbucks.view.UserView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Api(value = "User")
@Path("v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResourceApi {

    private SharedConfig config;
    private UserService userService;

    @Inject
    public UserResourceApi(final SharedConfig config, final UserService userService) {
        this.config = config;
        this.userService = userService;
    }

    @POST
    @Path("user/register")
    @ApiOperation(value = "Register the user ")
    public Response register(final RegistrationPayload payload) {
        Map<String, String> userPayload = new ObjectMapper().convertValue(payload, new TypeReference<Map<String, String>>() { });
        UserView user =  userService.registerUser(userPayload);
        return Response.ok().entity(user).build();
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
