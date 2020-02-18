package com.starbucks.api;

import com.starbucks.config.SharedConfig;
import com.starbucks.service.AdminService;
import com.starbucks.view.OrderListView;
import com.starbucks.view.UserListView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "Admin")
@Path("v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminApiResource {

    private SharedConfig config;
    private AdminService adminService;
    @Inject
    public AdminApiResource(final SharedConfig config, final AdminService adminService) {
        this.config = config;
        this.adminService = adminService;
    }

    @GET
    @Path("/admin/orders")
    @ApiOperation(value = "Get All Orders API",
    notes = "This API allows admins to get the list of all the orders placed in the system",
    response = OrderListView.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED ACCESS")
    })
    public Response getAllOrder() {
        OrderListView orderList = adminService.getAllOrders();
        return Response.ok().entity(orderList).build();
    }

    @GET
    @Path("admin/users")
    @ApiOperation(value = "Get All Users API",
    notes = "This API allows admins to get the list of all the user in the system",
    response = UserListView.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED ACCESS")
    })
    public Response getAllUsers() {
        UserListView userList = adminService.getAllUsers();
        return Response.ok().entity(userList).build();
    }

    @GET
    @Path("admin/orders/{status}")
    @ApiOperation(value = "Get All Orders By Status API",
    notes = "This API allows admins to get the list of all the orders placed in the system by their status",
    response = OrderListView.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED ACCESS")
    })
    public Response getAllOrdersByStatus(@ApiParam(name = "status", value = "status of the order", required = true, allowableValues = "PLACED, CANCELED, BLOCKED, COMPLETED") @PathParam("status") final String status) {
        OrderListView orderList = adminService.getAllOrdersByStatus(status);
        return Response.ok().entity(orderList).build();
    }

}
