package com.starbucks.exception.mapper;

import com.google.gson.JsonObject;
import com.starbucks.exception.UnauthorizedUserException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static com.starbucks.constant.Constants.ERROR;

@Provider
public class UnauthorizedUserExceptionMapper implements ExceptionMapper<UnauthorizedUserException> {

    private static final long serialVersionUID = 1L;

    @Override
    public Response toResponse(final UnauthorizedUserException ex) {
        JsonObject object = new JsonObject();
        object.addProperty(ERROR, ex.getMessage());
        return Response.status(Response.Status.UNAUTHORIZED).entity(object.toString()).type(MediaType.APPLICATION_JSON).build();
    }
}
