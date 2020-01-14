package com.starbucks.guice;

import com.google.inject.AbstractModule;
import org.reflections.Reflections;

import javax.ws.rs.Path;

public class ApiEndpointModule extends AbstractModule {
    @Override
    protected void configure() {
        Reflections reflections = new Reflections("com.starbucks.ping");
        reflections.getTypesAnnotatedWith(Path.class).forEach(this::bind);
    }
}
