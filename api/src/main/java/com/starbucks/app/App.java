package com.starbucks.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.google.inject.servlet.ServletModule;
import com.starbucks.guice.ApiBaseModule;
import com.starbucks.request.ObjectMapperContextResolver;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("api")
public class App extends ResourceConfig {
    @Inject
    public App(final ServiceLocator serviceLocator) {

        // Packages to Scan for Jersey Resources
        packages(true , "com.starbucks.api");
        packages(true, "com.starbucks.exception.mapper");

        // Jackson
        register(ObjectMapperContextResolver.class);
        register(JacksonFeature.class);

        // HK2-Guice Bridge
        Injector injector = createGuiceInjector();
        initGuiceIntoHK2Bridge(serviceLocator, injector);

        // Register Filters

        // CORSFilter

        // Swagger
        register(ApiListingResource.class);
        register(SwaggerSerializers.class);
        setUpSwagger();

        //Tracing
        property(ServerProperties.TRACING, "ON_DEMAND");
        property(ServerProperties.TRACING_THRESHOLD, "VERBOSE");

        //Jersey BeanValidation
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

    }

    private void initGuiceIntoHK2Bridge(final ServiceLocator serviceLocator, final Injector injector) {
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(injector);
    }

    private boolean isMac() {
        return "mac os x".equalsIgnoreCase(System.getProperty("os.name"));
    }

    private Module[] getBaseModule() {
        return new Module [] {
                new ServletModule(),
                new ApiBaseModule()
        };
    }

    private Injector createGuiceInjector() {
        if (isMac()) {
            return Guice.createInjector(getBaseModule());
        } else {
            return Guice.createInjector(Stage.PRODUCTION, getBaseModule());
        }
    }

    private BeanConfig setUpSwagger() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/api");
        beanConfig.setResourcePackage("com.starbucks.api");
        beanConfig.setDescription("Provides the list of starbucks backend Apis");
        beanConfig.setTitle("Starbucks Backend API");
        beanConfig.setScan(true);
        return beanConfig;
    }
}
