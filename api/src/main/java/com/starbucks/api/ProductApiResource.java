package com.starbucks.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.starbucks.config.SharedConfig;
import com.starbucks.payload.ProductPayload;
import com.starbucks.service.ProductService;
import com.starbucks.view.ProductView;
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

@Api(value = "Admin")
@Path("v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductApiResource {

    private SharedConfig config;
    private ProductService productService;

    @Inject
    public ProductApiResource(final SharedConfig config, final ProductService productService) {
        this.config = config;
        this.productService = productService;
    }

    @GET
    @Path("/product/{productId}")
    @ApiOperation(value = "Get Product by Id API",
    notes = "This API allows an admin to retrieve a particular product by its Id",
    response = ProductView.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED ACCESS"),
            @ApiResponse(code = 404, message = "PRODUCT NOT FOUND")

    })
    public Response getProductById(@PathParam("productId") final int productId) {
        ProductView productView = productService.getProductById(productId);
        return Response.ok().entity(productView).build();
    }

    @POST
    @Path("/product")
    @ApiOperation(value = "Create Product API",
            notes = "This API allows an admin to create a particular product",
            response = ProductView.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED ACCESS"),
            @ApiResponse(code = 404, message = "PRODUCT NOT FOUND")
    })
    public Response createProduct(@Valid final ProductPayload payload) {
        Map<String, String> productPayloadMap = new ObjectMapper().convertValue(payload, new TypeReference<Map<String, String>>() { });
        ProductView productView =  productService.createProduct(productPayloadMap);
        return Response.ok().entity(productView).build();
    }

    @PUT
    @Path("/product/{type}")
    @ApiOperation(value = "Update Product By Id API",
            notes = "This API allows an admin to update a particular product by its Id",
            response = Boolean.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED ACCESS"),
            @ApiResponse(code = 404, message = "PRODUCT NOT FOUND")
    })
    public Response updateProduct(@ApiParam(value = "type", required = true, allowableValues = "CAPPUCCINO, ESPRESSO, MOCHAS, MACCHIATOS, LATTES") @PathParam("type") final String type, @Valid final ProductPayload payload) {
        Map<String, String> productPayloadMap = new ObjectMapper().convertValue(payload, new TypeReference<Map<String, String>>() { });
        boolean isUpdated =  productService.updateProduct(type, productPayloadMap);
        JsonObject object = new JsonObject();
        if (isUpdated) {
            object.addProperty("data", "Product updated for type : " + type);
            return Response.ok().entity(object.toString()).build();
        } else {
            object.addProperty("error", "Product can not be updated for type : " + type);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(object.toString()).build();
        }
    }

    @DELETE
    @Path("/product/{productId}")
    @ApiOperation(value = "Delete Product by Id API",
            notes = "This API allows an admin to delete a particular product by its Id",
            response = Boolean.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED ACCESS"),
            @ApiResponse(code = 404, message = "PRODUCT NOT FOUND")
    })
    public Response deleteProduct(@PathParam("productId") final int productId) {
        boolean isDeleted = productService.deleteProduct(productId);
        JsonObject object = new JsonObject();
        if (isDeleted) {
            object.addProperty("data", "Product deleted for productId : " + productId);
            return Response.ok().entity(object.toString()).build();
        } else {
            object.addProperty("error", "Product can not be deleted for productId : " + productId);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(object.toString()).build();
        }
    }

}
