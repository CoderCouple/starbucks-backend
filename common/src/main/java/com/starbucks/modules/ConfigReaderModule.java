package com.starbucks.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.starbucks.config.ConfigReader;
import com.starbucks.config.SharedConfig;
import com.starbucks.config.SharedConfigImpl;
import com.starbucks.persistance.DBConn;
import com.starbucks.persistance.DBConnImpl;

public class ConfigReaderModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ConfigReader.class);
    }

    @Provides
    @Singleton
    public SharedConfig getSharedConfig(final ConfigReader configReader) {
        return new SharedConfigImpl(configReader);
    }

    @Provides
    @Singleton
    public DBConn getDBConnection(final SharedConfig config) {
        return new DBConnImpl(config);
    }
}
