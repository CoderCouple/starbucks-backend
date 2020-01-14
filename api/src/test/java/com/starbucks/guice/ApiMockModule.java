package com.starbucks.guice;

import com.google.inject.AbstractModule;

public class ApiMockModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new BaseModule());
        install(new ApiEndpointModule());
    }
}
