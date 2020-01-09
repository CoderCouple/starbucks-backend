package com.starbucks.app;

import com.starbucks.request.ObjectMapperContextResolver;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("api")
public class App extends ResourceConfig {
    public App() {

        packages(true ,"com.starbucks.api");

        // Jackson
        register(ObjectMapperContextResolver.class);
        register(JacksonFeature.class);
    }
}
