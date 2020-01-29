package com.starbucks.guice;

import com.google.inject.AbstractModule;
import com.starbucks.modules.ServiceModule;

public class BaseModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new ConfigReaderModule());
        install(new ApiEndpointModule());
        install(new ServiceModule());
    }
}
