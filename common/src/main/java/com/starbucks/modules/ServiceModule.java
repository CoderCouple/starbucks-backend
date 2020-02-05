package com.starbucks.modules;

import com.google.inject.AbstractModule;
import com.starbucks.service.PingService;
import com.starbucks.service.PingServiceImpl;
import com.starbucks.service.UserService;
import com.starbucks.service.UserServiceImpl;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PingService.class).to(PingServiceImpl.class);
        bind(UserService.class).to(UserServiceImpl.class);

    }
}
