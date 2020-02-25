package com.starbucks.modules;

import com.codahale.metrics.MetricRegistry;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.starbucks.persistance.BaseJDOConfig;
import com.starbucks.persistance.StarbucksPersistenceDirector;

public class PersistenceModule extends AbstractModule {

    private static volatile StarbucksPersistenceDirector persistenceDirector;

    @Override
    protected void configure() {
    }

    @Provides
    public static StarbucksPersistenceDirector getPersistenceDirector(
            final @Named("write") BaseJDOConfig writeConfig,
            final @Named("read") BaseJDOConfig readConfig,
            final MetricRegistry metricRegistry) {
        if (persistenceDirector == null) {
            synchronized (PersistenceModule.class) {
                if (persistenceDirector == null) {
                    persistenceDirector =
                            new StarbucksPersistenceDirector(writeConfig, readConfig, metricRegistry, false);
                }
            }
        }
        return persistenceDirector;
    }
}
