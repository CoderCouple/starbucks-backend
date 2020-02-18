package com.starbucks.modules;

import com.google.inject.AbstractModule;
import com.starbucks.service.AdminService;
import com.starbucks.service.OrderService;
import com.starbucks.service.PingService;
import com.starbucks.service.ProductService;
import com.starbucks.service.UserService;
import com.starbucks.service.mock.AdminServiceMockImpl;
import com.starbucks.service.mock.OrderServiceMockImpl;
import com.starbucks.service.mock.PingServiceMockImpl;
import com.starbucks.service.mock.ProductServiceMockImpl;
import com.starbucks.service.mock.UserServiceMockImpl;

public class TestServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PingService.class).to(PingServiceMockImpl.class);
        bind(UserService.class).to(UserServiceMockImpl.class);
        bind(OrderService.class).to(OrderServiceMockImpl.class);
        bind(AdminService.class).to(AdminServiceMockImpl.class);
        bind(ProductService.class).to(ProductServiceMockImpl.class);
    }
}
