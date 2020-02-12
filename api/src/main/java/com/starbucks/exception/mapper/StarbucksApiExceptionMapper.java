package com.starbucks.exception.mapper;

import com.starbucks.exception.StarbucksApiException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class StarbucksApiExceptionMapper implements ExceptionMapper<StarbucksApiException> {

    private static final long serialVersionUID = 1L;

    @Override
    public Response toResponse(final StarbucksApiException ex) {
        return Response.status(500).entity(ex.getMessage()).type(MediaType.APPLICATION_JSON).build();
    }
}
