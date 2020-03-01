package com.starbucks.cors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 * A simple configurable implementation of {@code AbstractCorsFilter}. This class supports a single CORS policy for
 * all accepted origins. This class is not suitable for applications that need different CORS policies for different
 * origins, e.g. allowing GET from all origins but only allowing POST with cookie from certain origins.
 * <p>
 * This class provides a builder with a fluent API to create instances. These instances can be registered directly in a
 * JAX-RS container:
 * <pre><code>
 * register(SimpleCorsFilter.newBuilder()
 * .addOriginDomain("yahoo.com")
 * .allowCredentials()
 * .build());
 * </code></pre>
 * If you prefer to load your filters via reflection or service discovery, you can subclass this class and have the
 * default constructor pass a builder to the superclass constructor. Make sure to copy the {@literal @}Priority
 * annotation to your subclass as it is not {@literal @}Inherited.
 * <pre><code>
 * {@literal @}Priority(Priorities.HEADER_DECORATOR)
 * {@literal @}Provider
 * public class MyCorsFilter extends SimpleCorsFilter {
 * public MyCorsFilter() {
 * super(SimpleCorsFilter.newBuilder()
 * .addOriginDomain("yahoo.com")
 * .allowCredentials()
 * .build());
 * }
 * }
 * </code></pre>
 */
@Priority(Priorities.HEADER_DECORATOR) // this is not @Inherited
public class SimpleCorsFilter extends AbstractCorsFilter {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleCorsFilter.class);

    /**
     * Creates a new builder for configuring a new filter.
     *
     * @return A new builder.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Constructs new filter instances with a fluent API.
     */
    public static class Builder {
        private Collection<Pattern> originPatterns = new ArrayList<>();
        private boolean allowCredentials = false;
        private Integer maxAge;
        private Collection<String> allowedMethods = new TreeSet<>();
        private Collection<String> allowedHeaders = new TreeSet<>();

        /**
         * Adds a regular expression for accepting the host component of the Origin header. The scheme and port
         * components are stripped from the Origin before pattern matching.
         *
         * @param pattern The regular expression.
         * @return This builder for method chaining.
         */
        public Builder allowOriginPattern(final Pattern pattern) {
            originPatterns.add(pattern);
            return this;
        }

        /**
         * A convenience method for accepting any hosts within a domain (and its subdomains).
         *
         * @param domain The domain name, e.g. {@code "yahoo.com"}.
         * @return This builder for method chaining.
         */
        public Builder allowOriginDomain(final String domain) {
            return allowOriginPattern(Pattern.compile("(^|\\.)" + Pattern.quote(domain) + "$"));
        }

        /**
         * Allows credentials (cookies) to be passed in cross-origin requests.
         *
         * @return This builder for method chaining.
         */
        public Builder allowCredentials() {
            allowCredentials = true;
            return this;
        }

        /**
         * Allows caching of CORS preflight responses for the specified duration.
         *
         * @param maxAge Caching duration in seconds.
         * @return This builder for method chaining.
         */
        public Builder withMaxAge(final int maxAge) {
            this.maxAge = maxAge;
            return this;
        }

        /**
         * Allows an HTTP method to be called in cross-origin request.
         *
         * @param method The method.
         * @return This builder for method chaining.
         * @see javax.ws.rs.HttpMethod
         * @see AbstractCorsFilter#getAllowedMethods
         */
        public Builder allowMethod(final String method) {
            this.allowedMethods.add(method);
            return this;
        }

        /**
         * Allows an HTTP header to be passed in a cross-origin request.
         *
         * @param header The header name.
         * @return This builder for method chaining.
         * @see AbstractCorsFilter#getAllowedHeaders
         */
        public Builder allowHeader(final String header) {
            this.allowedHeaders.add(header);
            return this;
        }

        /**
         * Constructs a new filter with the current configuration.
         *
         * @return The new filter instance.
         */
        public SimpleCorsFilter build() {
            return new SimpleCorsFilter(this);
        }
    }

    private final Collection<Pattern> originPatterns;
    private final boolean allowCredentials;
    private final Integer maxAge;
    private final String allowedMethods;
    private final String allowedHeaders;

    /**
     * Constructs a new instance using a builder's configuration. This constructor is left visible so that
     * subclasses can be defined with default constructors which allows them to be created with reflection or service
     * discovery. If you are explicitly registering filter instances, you can use the builder directly.
     *
     * @param b The builder to use for configuration.
     */
    protected SimpleCorsFilter(final Builder b) {
        originPatterns = b.originPatterns;
        if (originPatterns.isEmpty()) {
            LOG.warn("No Origin patterns configured, no CORS headers will be emitted");
        }
        allowCredentials = b.allowCredentials;
        maxAge = b.maxAge;
        allowedMethods = b.allowedMethods.isEmpty() ? null : String.join(",", b.allowedMethods);
        allowedHeaders = b.allowedHeaders.isEmpty() ? null : String.join(",", b.allowedHeaders);
    }

    @Override // AbstractCorsFilter
    protected final boolean acceptOrigin(final String origin) {
        try {
            String originHost = new URL(origin).getHost();
            return originPatterns.stream().anyMatch(pattern -> pattern.matcher(originHost).find());
        } catch (final MalformedURLException e) {
            LOG.warn("Origin is not a valid URL, will not allow: {}", origin, e);
            return false;
        }
    }

    @Override // AbstractCorsFilter
    protected final boolean allowCredentials(final String origin) {
        return allowCredentials;
    }

    @Override // AbstractCorsFilter
    protected final Optional<Integer> getMaxAge(final String origin) {
        return Optional.ofNullable(maxAge);
    }

    @Override // AbstractCorsFilter
    protected final Optional<String> getAllowedMethods(final String origin) {
        return Optional.ofNullable(allowedMethods);
    }

    @Override // AbstractCorsFilter
    protected final Optional<String> getAllowedHeaders(final String origin) {
        return Optional.ofNullable(allowedHeaders);
    }
}
