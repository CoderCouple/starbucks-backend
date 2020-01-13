package com.starbucks.guice;

import com.google.inject.AbstractModule;
import com.starbucks.config.ConfigReader;
import com.starbucks.config.SharedConfig;
import com.starbucks.config.SharedConfigImpl;

public class ConfigReaderModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ConfigReader.class);
        bind(SharedConfig.class).to(SharedConfigImpl.class);
    }
}
