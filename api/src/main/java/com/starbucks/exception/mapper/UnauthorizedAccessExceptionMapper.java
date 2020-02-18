package com.starbucks.exception.mapper;

import com.google.gson.JsonObject;
import com.starbucks.exception.UnauthorizedAccessException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static com.starbucks.constant.Constants.ERROR;

@Provider
public class UnauthorizedAccessExceptionMapper implements ExceptionMapper<UnauthorizedAccessException> {

    private static final long serialVersionUID = 1L;

    @Override
    public Response toResponse(final UnauthorizedAccessException ex) {
        JsonObject object = new JsonObject();
        object.addProperty(ERROR, ex.getMessage());
        return Response.status(Response.Status.UNAUTHORIZED).entity(object.toString()).type(MediaType.APPLICATION_JSON).build();
    }
}
