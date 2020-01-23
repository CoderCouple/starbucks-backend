package com.starbucks.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.ServletModule;
import com.starbucks.guice.ApiMockModule;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.inject.Inject;

public class TestConfig extends ResourceConfig {

    public static Injector injector;

    @Inject
    public TestConfig(final ServiceLocator serviceLocator) {
        packages(true, "com.starbucks");
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, "true");
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);

        injector = Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                install(new ApiMockModule());
            }
        });

        guiceBridge.bridgeGuiceInjector(injector);
    }
}
