package com.starbucks.modules;

import com.google.inject.AbstractModule;

public class CommonBaseModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new ConfigReaderModule());
        install(new ServiceModule());
    }
}
