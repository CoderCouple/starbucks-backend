package com.starbucks.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.starbucks.util.ParsedResponse;
import cucumber.api.guice.CucumberModules;
import cucumber.runtime.java.guice.InjectorSource;

public class CucumberInjectorSource implements InjectorSource {
    @Override
    public Injector getInjector() {
        return Guice.createInjector(Stage.DEVELOPMENT, CucumberModules.SCENARIO, new AbstractModule() {
            @Override
            protected void configure() {
                bind(ParsedResponse.class).toInstance(new ParsedResponse());
            }
        });
    }
}
