package com.starbucks.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starbucks.config.SharedConfig;
import com.starbucks.payload.LoginPayload;
import com.starbucks.payload.RegistrationPayload;
import com.starbucks.service.UserService;
import com.starbucks.view.UserOrderHistoryView;
import com.starbucks.view.UserProfileView;
import com.starbucks.view.UserView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.inject.Inject;
import javax.validation.Valid;
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
public class UserApiResource {

    private SharedConfig config;
    private UserService userService;

    @Inject
    public UserApiResource(final SharedConfig config, final UserService userService) {
        this.config = config;
        this.userService = userService;
    }

    @POST
    @Path("user/register")
    @ApiOperation(value = "User Registration API",
    notes = "This API allows a user to register himself in the system",
    response = UserView.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "DUPLICATE USER FOUND")
    })
    public Response registerUser(@Valid final RegistrationPayload payload) {
        Map<String, String> registrationPayloadMap = new ObjectMapper().convertValue(payload, new TypeReference<Map<String, String>>() { });
        UserView user =  userService.registerUser(registrationPayloadMap);
        return Response.ok().entity(user).build();
    }

    @POST
    @Path("user/login")
    @ApiOperation(value = "User Login API",
    notes = "This API let's user login into the system using the Email and Password as credentials.",
    response = UserProfileView.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "USER NOT FOUND"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED ACCESS")
    })
    public Response loginUser(@Valid final LoginPayload payload) {
        Map<String, String> loginPayloadMap = new ObjectMapper().convertValue(payload, new TypeReference<Map<String, String>>() { });
        UserProfileView userProfileView =  userService.loginUser(loginPayloadMap);
        return Response.ok().entity(userProfileView).build();
    }

    @GET
    @Path("user/logout/{userId}")
    @ApiOperation(value = "User Logout API",
    notes = "This API lets user log out of the system",
    response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "USER NOT FOUND")
    })
    public Response logoutUser(@ApiParam(value = "User Id", required = true) @PathParam("userId") final int userId) {
        boolean isSuccessful =  userService.logoutUser(userId);
        if (isSuccessful) {
            return Response.ok().entity("{ \"data\" : " + "\"User successfully logged out !!!\"" + " }").build();
        } else {
            return Response.ok().entity("{ \"data\" : " + "\"User logged out failed !!!\"" + " }").build();
        }
    }


    @GET
    @Path("/user/{userId}")
    @ApiOperation(value = "Get User By Id API",
            notes = "This API fetches the user details by sing userId as input",
            response = UserView.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200 , message = "OK"),
            @ApiResponse(code = 404, message = "USER NOT FOUND")
    })
    public Response getUser(@ApiParam(value = "User Id", required = true) @PathParam("userId") final int userId) {
        UserView userView =  userService.getUserById(userId);
        return Response.ok().entity(userView).build();
    }


    @GET
    @Path("user/history/{userId}")
    @ApiOperation(value = "User Order History API",
    notes = "This API is used to fetch user order history",
    response = UserOrderHistoryView.class,
    responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "USER NOT FOUND")
    })
    public Response getHistory(@ApiParam(value = "User Id", required = true) @PathParam("userId") final int userId) {
        UserOrderHistoryView userOrderHistoryView =  userService.getUserHistory(userId);
        return Response.ok().entity(userOrderHistoryView).build();
    }
}
