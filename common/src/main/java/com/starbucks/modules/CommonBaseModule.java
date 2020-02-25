package com.starbucks.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;

import static com.starbucks.constant.Constants.ACCESS_MASTER_DB;

public class CommonBaseModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new ConfigReaderModule());
        install(new ServiceModule());
        install(new DaoProviderModule());
        install(new PersistenceModule());
        install(new StarbucksPersistenceModule());
    }

    @Provides
    @Named(ACCESS_MASTER_DB)
    public Boolean shouldAccessMasterDB() {
        return true;
    }
}
