package com.starbucks.modules;

import com.google.inject.AbstractModule;
import com.starbucks.service.PingService;
import com.starbucks.service.PingServiceMockImpl;
import com.starbucks.service.UserService;
import com.starbucks.service.UserServiceMockImpl;

public class TestServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PingService.class).to(PingServiceMockImpl.class);
        bind(UserService.class).to(UserServiceMockImpl.class);
    }
}
