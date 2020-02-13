package com.starbucks.exception.mapper;

import com.google.gson.JsonObject;
import com.starbucks.exception.StarbucksApiException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static com.starbucks.constant.Constants.ERROR;

@Provider
public class StarbucksApiExceptionMapper implements ExceptionMapper<StarbucksApiException> {

    private static final long serialVersionUID = 1L;

    @Override
    public Response toResponse(final StarbucksApiException ex) {
        JsonObject object = new JsonObject();
        object.addProperty(ERROR, ex.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(object.toString()).type(MediaType.APPLICATION_JSON).build();
    }
}
