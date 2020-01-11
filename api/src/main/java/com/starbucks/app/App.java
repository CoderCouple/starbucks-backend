package com.starbucks.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.google.inject.servlet.ServletModule;
import com.starbucks.guice.BaseModule;
import com.starbucks.request.ObjectMapperContextResolver;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("api")
public class App extends ResourceConfig {
    @Inject
    public App(ServiceLocator serviceLocator) {

        // Packages to Scan for Jersey
        packages(true ,"com.starbucks.api");
        packages(true,"com.starbucks.exception.mapper");

        // Jackson
        register(ObjectMapperContextResolver.class);
        register(JacksonFeature.class);

        // HK2-Guice Bridge
        Injector injector = createGuiceInjector();
        initGuiceIntoHK2Bridge(serviceLocator, injector);

        // Register Filters

        // CORS

        // OpenAPI Specification (a.k.a Swagger)
    }

    private void initGuiceIntoHK2Bridge(ServiceLocator serviceLocator, Injector injector) {
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(injector);
    }

    private boolean isMac(){
        return System.getProperty("os.name").equalsIgnoreCase("mac os x");
    }

    private Module[] getBaseModule(){
        return new Module [] {
                new ServletModule(),
                new BaseModule()
        };
    }

    private Injector createGuiceInjector(){
        if(isMac())
            return Guice.createInjector(getBaseModule());
        else
            return Guice.createInjector(Stage.PRODUCTION,getBaseModule());
    }
}