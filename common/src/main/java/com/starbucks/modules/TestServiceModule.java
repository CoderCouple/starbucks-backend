package com.starbucks.modules;

import com.google.inject.AbstractModule;
import com.starbucks.service.PingService;
import com.starbucks.service.PingServiceMockImpl;

public class TestServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PingService.class).to(PingServiceMockImpl.class);
    }
}
