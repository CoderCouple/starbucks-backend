package com.starbucks.modules;

import com.codahale.metrics.MetricRegistry;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.starbucks.persistance.BaseJDOConfig;
import com.starbucks.persistance.StarbucksPersistenceDirector;

import static com.starbucks.constant.Constants.ACCESS_MASTER_DB;

public class TestPersistenceModule extends AbstractModule {

    private static volatile StarbucksPersistenceDirector persistenceDirector;

    @Override
    protected void configure() {
    }

    @Provides
    public static StarbucksPersistenceDirector getPersistenceDirector(
            final @Named("write") BaseJDOConfig writeConfig,
            final MetricRegistry metricRegistry) {
        if (persistenceDirector == null) {
            synchronized (TestPersistenceModule.class) {
                if (persistenceDirector == null) {
                    persistenceDirector =
                            new StarbucksPersistenceDirector(writeConfig, writeConfig, metricRegistry, true);
                }
            }
        }
        return persistenceDirector;
    }

    @Provides
    @Named(ACCESS_MASTER_DB)
    public Boolean shouldAccessMasterDB() {
        return true;
    }

}
