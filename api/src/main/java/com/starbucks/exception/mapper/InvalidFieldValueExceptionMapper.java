package com.starbucks.exception.mapper;

import com.starbucks.exception.InvalidFieldValueException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidFieldValueExceptionMapper implements ExceptionMapper<InvalidFieldValueException> {

    private static final long serialVersionUID = 1L;

    @Override
    public Response toResponse(final InvalidFieldValueException ex) {
        return Response.status(400).entity(ex.getMessage()).type(MediaType.APPLICATION_JSON).build();
    }
}
