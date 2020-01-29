package com.starbucks.modules;

import com.google.inject.AbstractModule;

public class CommonMockModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new TestServiceModule());
    }
}
