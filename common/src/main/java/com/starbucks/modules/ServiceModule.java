package com.starbucks.modules;

import com.google.inject.AbstractModule;
import com.starbucks.service.AdminService;
import com.starbucks.service.ProductService;
import com.starbucks.service.OrderService;
import com.starbucks.service.PingService;
import com.starbucks.service.impl.AdminServiceImpl;
import com.starbucks.service.impl.ProductServiceImpl;
import com.starbucks.service.impl.OrderServiceImpl;
import com.starbucks.service.impl.PingServiceImpl;
import com.starbucks.service.UserService;
import com.starbucks.service.impl.UserServiceImpl;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PingService.class).to(PingServiceImpl.class);
        bind(UserService.class).to(UserServiceImpl.class);
        bind(OrderService.class).to(OrderServiceImpl.class);
        bind(AdminService.class).to(AdminServiceImpl.class);
        bind(ProductService.class).to(ProductServiceImpl.class);

    }
}
