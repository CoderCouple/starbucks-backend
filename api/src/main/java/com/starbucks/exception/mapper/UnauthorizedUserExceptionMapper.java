package com.starbucks.exception.mapper;

import com.starbucks.exception.UnauthorizedUserException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnauthorizedUserExceptionMapper implements ExceptionMapper<UnauthorizedUserException> {

    private static final long serialVersionUID = 1L;

    @Override
    public Response toResponse(final UnauthorizedUserException ex) {
        return Response.status(401).entity(ex.getMessage()).type(MediaType.APPLICATION_JSON).build();
    }
}
