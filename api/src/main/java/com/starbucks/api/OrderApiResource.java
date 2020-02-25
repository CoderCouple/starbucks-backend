package com.starbucks.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.starbucks.config.SharedConfig;
import com.starbucks.payload.OrderPayload;
import com.starbucks.service.OrderService;
import com.starbucks.view.OrderView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Api(value = "User")
@Path("v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderApiResource {

    private SharedConfig config;
    private OrderService orderService;

    @Inject
    public OrderApiResource(final SharedConfig config, final OrderService orderService) {
        this.config = config;
        this.orderService = orderService;
    }

    @GET
    @Path("/order/{orderId}")
    @ApiOperation(value = "Get Order By Id API",
    notes = "This API allows a user to retrieve the a particular order by its Id",
    response = OrderView.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "ORDER NOT FOUND")
    })
    public Response getOrderById(@ApiParam(value = "Order Id", required = true) @PathParam("orderId") final int orderId) {
        OrderView orderView = orderService.getOrderById(orderId);
        return Response.ok().entity(orderView).build();
    }

    @POST
    @Path("/order")
    @ApiOperation(value = "Created Order API",
    notes = "This API allows a user to create an Order",
    response = OrderView.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201 , message = "CREATED"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    public Response createOrder(@Valid final OrderPayload payload) {
        Map<String, Object> orderPayloadMap = new ObjectMapper().convertValue(payload, new TypeReference<Map<String, Object>>() { });
        OrderView orderView =  orderService.createOrder(orderPayloadMap);
        return Response.ok().entity(orderView).build();
    }

    @PUT
    @Path("/order/{orderId}")
    @ApiOperation(value = "Update Order By Id API",
    notes = "This API lets a use to update a particular order",
    response = Boolean.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    public Response updateOrder(@PathParam("orderId") final int orderId, @Valid final OrderPayload payload) {
        Map<String, Object> orderPayloadMap = new ObjectMapper().convertValue(payload, new TypeReference<Map<String, Object>>() { });
        boolean isUpdated =  orderService.updateOrder(orderId, orderPayloadMap);
        JsonObject object = new JsonObject();
        if (isUpdated) {
            object.addProperty("data", "Order updated for orderId : " + orderId);
            return Response.ok().entity(object.toString()).build();
        } else {
            object.addProperty("error", "Order can not be updated for orderId : " + orderId);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(object.toString()).build();
        }
    }

    @DELETE
    @Path("/order/{orderId}")
    @ApiOperation(value = "Delete Order By Id API",
    notes = "This API allows a user to delete a particular Order using the orderId",
    response = Boolean.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "ORDER NOT FOUND")
    })
    public Response deleteOrder(@ApiParam(value = "Order Id", required = true) @PathParam("orderId") final int orderId) {
        boolean isDeleted = orderService.deleteOrder(orderId);
        JsonObject object = new JsonObject();
        if (isDeleted) {
            object.addProperty("data", "Order deleted for orderId : " + orderId);
            return Response.ok().entity(object.toString()).build();
        } else {
            object.addProperty("error", "Order can not be deleted for orderId : " + orderId);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(object.toString()).build();
        }
    }

}
