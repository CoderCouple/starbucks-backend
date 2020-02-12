package com.starbucks.exception.mapper;

import com.starbucks.exception.UserNotFoundException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserNotFoundExceptionMapper implements ExceptionMapper<UserNotFoundException> {

    private static final long serialVersionUID = 1L;

    @Override
    public Response toResponse(final UserNotFoundException ex) {
        return Response.status(404).entity(ex.getMessage()).type(MediaType.APPLICATION_JSON).build();
    }
}
