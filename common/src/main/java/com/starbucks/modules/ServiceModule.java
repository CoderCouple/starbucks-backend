package com.starbucks.modules;

import com.google.inject.AbstractModule;
import com.starbucks.service.PingService;
import com.starbucks.service.PingServiceImpl;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PingService.class).to(PingServiceImpl.class);

    }
}
