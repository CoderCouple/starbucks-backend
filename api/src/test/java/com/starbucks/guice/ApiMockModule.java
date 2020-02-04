package com.starbucks.guice;

import com.google.inject.AbstractModule;
import com.starbucks.modules.ConfigReaderModule;
import com.starbucks.modules.TestServiceModule;

public class ApiMockModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new ConfigReaderModule());
        install(new ApiEndpointModule());
        install(new TestServiceModule());
    }
}
