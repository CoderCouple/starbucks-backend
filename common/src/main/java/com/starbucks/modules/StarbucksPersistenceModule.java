package com.starbucks.modules;

import com.codahale.metrics.MetricRegistry;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.starbucks.config.SharedConfig;
import com.starbucks.persistance.BaseJDOConfig;
import com.starbucks.persistance.ReadJDOConfig;
import com.starbucks.persistance.WriteJDOConfig;

public class StarbucksPersistenceModule extends AbstractModule {

    private static volatile BaseJDOConfig writeBaseJDOConfig;
    private static volatile BaseJDOConfig readBaseJDOConfig;

    @Override
    protected void configure() {
    }

    @Provides
    @Named("write")
    private static BaseJDOConfig getWriteBaseJDOConfig(final SharedConfig sharedConfig,
                                                       final MetricRegistry metricRegistry) {
        if (writeBaseJDOConfig == null) {
            synchronized (StarbucksPersistenceModule.class) {
                if (writeBaseJDOConfig == null) {
                    writeBaseJDOConfig = new WriteJDOConfig(sharedConfig, metricRegistry);
                }
            }
        }
        return writeBaseJDOConfig;
    }

    @Provides
    @Named("read")
    private static BaseJDOConfig getReadBaseJDOConfig(final SharedConfig sharedConfig,
                                                      final MetricRegistry metricRegistry) {
        if (readBaseJDOConfig == null) {
            synchronized (StarbucksPersistenceModule.class) {
                if (readBaseJDOConfig == null) {
                    readBaseJDOConfig = new ReadJDOConfig(sharedConfig, metricRegistry);
                }
            }
        }
        return readBaseJDOConfig;
    }
}
