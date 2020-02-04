package com.starbucks.guice;

import com.google.inject.AbstractModule;
import com.starbucks.modules.CommonBaseModule;

public class ApiBaseModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new ApiEndpointModule());
        install(new CommonBaseModule());
    }
}
