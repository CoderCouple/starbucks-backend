package com.starbucks.cors;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import java.util.Optional;

/**
 * Adds response headers required for cross-origin requests (CORS).
 * <p>
 * CORS allows your web service to be called from the browser from different origins. For example, this can allow the
 * browser to call {@code api.sports.yahoo.com} directly from a page loaded from {@code sports.yahoo.com} without
 * proxying the call through through {@code sports.yahoo.com}.
 *
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS">Cross-Origin Resource Sharing (CORS)</a>
 */
@Priority(Priorities.HEADER_DECORATOR) // this is not @Inherited
public abstract class AbstractCorsFilter implements ContainerResponseFilter {

    /**
     * Add response headers for CORS if the Origin request header is accepted.
     *
     * @param requestContext  The JAX-RS request context.
     * @param responseContext The JAX-RS response context.
     */
    @Override
    public final void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext) {
        MultivaluedMap<String, String> requestHeaders = requestContext.getHeaders();
        MultivaluedMap<String, Object> responseHeaders = responseContext.getHeaders();

        // CORS permission depends on the origin making the request.
        responseHeaders.putSingle(HttpHeaders.VARY, CorsHeaders.ORIGIN);

        final String origin = requestHeaders.getFirst(CorsHeaders.ORIGIN);
        if (acceptOrigin(origin)) {
            // Allow this origin to make cross origin requests.
            responseHeaders.putSingle(CorsHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
            // The only legal header value is "true". If not allowed, omit the header.
            if (allowCredentials(origin)) {
                responseHeaders.putSingle(CorsHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
            }
            getMaxAge(origin)
                    .ifPresent(maxAge -> responseHeaders.putSingle(CorsHeaders.ACCESS_CONTROL_MAX_AGE, maxAge));
            getAllowedMethods(origin)
                    .ifPresent(methods -> responseHeaders.putSingle(CorsHeaders.ACCESS_CONTROL_ALLOW_METHODS, methods));
            getAllowedHeaders(origin)
                    .ifPresent(headers -> responseHeaders.putSingle(CorsHeaders.ACCESS_CONTROL_ALLOW_HEADERS, headers));
        }
    }

    /**
     * Allows an Origin to access this service.
     *
     * @param origin The Origin header value for the request.
     * @return {@code true} to send CORS headers, false to omit
     */
    protected abstract boolean acceptOrigin(String origin);

    /**
     * Allows credentials (cookies) to be sent with cross-origin requests.
     *
     * @param origin The Origin header value for the request.
     * @return {@code true} to allow credentials to be sent.
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Access-Control-Allow-Credentials">Access-Control-Allow-Credentials</a>
     */
    protected abstract boolean allowCredentials(String origin);

    /**
     * How long the CORS preflight response can be cached, in seconds.
     *
     * @param origin The Origin header value for the request.
     * @return The CORS policy TTL, or empty to omit the header.
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Access-Control-Max-Age">Access-Control-Max-Age</a>
     */
    protected abstract Optional<Integer> getMaxAge(String origin);

    /**
     * The comma separated list of methods that can be called with cross-origin requests.
     * <p>
     * The following methods are safelisted and do not need to be explicitly allowed unless non-safelisted headers are
     * used:
     * <ul>
     * <li>GET</li>
     * <li>HEAD</li>
     * <li>POST</li>
     * </ul>
     *
     * @param origin The Origin header value for the request.
     * @return The rendered header value, or empty to omit the header.
     * @see #getAllowedHeaders
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Access-Control-Allow-Methods">Access-Control-Allow-Methods</a>
     * @see <a href="https://fetch.spec.whatwg.org/#cors-safelisted-method">CORS-safelisted methods</a>
     */
    protected abstract Optional<String> getAllowedMethods(String origin);

    /**
     * The comma separated list of headers that can be sent with cross-origin requests.
     * <p>
     * The following headers are safelisted and do not need to be explicitly allowed:
     * <ul>
     * <li>Accept</li>
     * <li>Accept-Language</li>
     * <li>Content-Language</li>
     * </ul>
     * <p>
     * Additionally, Content-Type is safelisted if the header value is one of the following:
     * <ul>
     * <li>application/x-www-form-urlencoded</li>
     * <li>multipart/form-data</li>
     * <li>text/plain</li>
     * </ul>
     *
     * @param origin The Origin header value for the request.
     * @return The rendered header value, or empty to omit the header.
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Access-Control-Allow-Headers">Access-Control-Allow-Headers</a>
     * @see <a href="https://fetch.spec.whatwg.org/#cors-safelisted-request-header">CORS-safelisted request-headers</a>
     */
    protected abstract Optional<String> getAllowedHeaders(String origin);
}
