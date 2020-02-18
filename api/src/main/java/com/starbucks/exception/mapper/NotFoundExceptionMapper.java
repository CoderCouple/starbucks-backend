package com.starbucks.exception.mapper;

import com.google.gson.JsonObject;
import com.starbucks.exception.NotFoundException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static com.starbucks.constant.Constants.ERROR;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    private static final long serialVersionUID = 1L;

    @Override
    public Response toResponse(final NotFoundException ex) {
        JsonObject object = new JsonObject();
        object.addProperty(ERROR, ex.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(object.toString()).type(MediaType.APPLICATION_JSON).build();
    }
}
