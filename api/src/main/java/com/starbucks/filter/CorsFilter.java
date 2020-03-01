package com.starbucks.filter;

import com.starbucks.config.SharedConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.util.List;

import static com.starbucks.constant.Constants.HTTP_HEADER_ACCESS_CONTROL_ALLOW_CREDENTIALS;
import static com.starbucks.constant.Constants.HTTP_HEADER_ACCESS_CONTROL_ALLOW_HEADERS;
import static com.starbucks.constant.Constants.HTTP_HEADER_ACCESS_CONTROL_ALLOW_METHODS;
import static com.starbucks.constant.Constants.HTTP_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN;
import static com.starbucks.constant.Constants.HTTP_HEADER_ACCESS_CONTROL_MAX_AGE;
import static com.starbucks.constant.Constants.HTTP_HEADER_AUTHORIZATION;
import static com.starbucks.constant.Constants.HTTP_HEADER_CONTENT_TYPE;
import static com.starbucks.constant.Constants.HTTP_HEADER_ORIGIN;
import static com.starbucks.constant.Constants.HTTP_METHOD_GET;
import static com.starbucks.constant.Constants.HTTP_METHOD_OPTIONS;
import static com.starbucks.constant.Constants.WHITELISTED_DOMAIN;


@Singleton
@Provider
@PreMatching
//This filter is currently not used. Kept here just for reference sake.
public class CorsFilter implements ContainerResponseFilter {

    public static final String MAX_AGE = "3600";

    private final SharedConfig config;
    private static final Logger LOGGER = LoggerFactory.getLogger(CorsFilter.class);

    @Inject
    public CorsFilter(final SharedConfig config) {
        this.config = config;
    }

    @Override
    public void filter(final ContainerRequestContext containerRequestContext,
                       final ContainerResponseContext containerResponseContext) {
        String origin = containerRequestContext.getHeaderString(HTTP_HEADER_ORIGIN);

        // TODO: Unable to inherit whitelistedDomain settings from master to stage, and so, temporarily we copy the
        // settings
        List<String> whitelistedDomains = config.getList(WHITELISTED_DOMAIN);
        MultivaluedMap<String, Object> responseHeaders = containerResponseContext.getHeaders();
        if (origin != null && whitelistedDomains.stream().anyMatch(domain -> origin.endsWith(domain))) {
            responseHeaders.putSingle(HTTP_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN, origin);
            responseHeaders.putSingle(HTTP_HEADER_ACCESS_CONTROL_MAX_AGE, MAX_AGE);
            responseHeaders.putSingle(HTTP_HEADER_ACCESS_CONTROL_ALLOW_HEADERS, String.join(",",
                    HTTP_HEADER_CONTENT_TYPE, HTTP_HEADER_AUTHORIZATION));
            responseHeaders.putSingle(HTTP_HEADER_ACCESS_CONTROL_ALLOW_CREDENTIALS, true);
            responseHeaders.putSingle(HTTP_HEADER_ACCESS_CONTROL_ALLOW_METHODS, String.join(",", HTTP_METHOD_GET,
                    HTTP_METHOD_OPTIONS));
            LOGGER.info(HTTP_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN, origin, " generated request");
        } else {
            // When origin is not in whitelist, remove any "Access-Control-Allow-Origin" header
            if (responseHeaders.getFirst(HTTP_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN) != null) {
                responseHeaders.remove(HTTP_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN);
            }
        }
    }
}
