package com.starbucks.exception.mapper;

import com.starbucks.exception.MissingRequiredFieldException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MissingRequiredFieldExceptionMapper implements ExceptionMapper<MissingRequiredFieldException> {

    private static final long serialVersionUID = 1L;

    @Override
    public Response toResponse(final MissingRequiredFieldException ex) {
        return Response.status(400).entity(ex.getMessage()).type(MediaType.APPLICATION_JSON).build();
    }
}
