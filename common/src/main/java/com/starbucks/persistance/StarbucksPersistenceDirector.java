package com.starbucks.persistance;

import com.codahale.metrics.MetricRegistry;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class StarbucksPersistenceDirector {

    private final PersistenceDirector writeDirector;
    private final PersistenceDirector readDirector;

    @Inject
    public StarbucksPersistenceDirector(final @Named("write") BaseJDOConfig writeConfig,
                                            final @Named("read") BaseJDOConfig readConfig,
                                            final MetricRegistry metricRegistry,
                                            final boolean isUnitTest) {

        this.writeDirector = new PersistenceDirector(writeConfig, metricRegistry, isUnitTest);
        this.readDirector = new PersistenceDirector(readConfig, metricRegistry, isUnitTest);
    }

    public PersistenceManagerProvider getWritePmp() {
        return writeDirector.getPmp();
    }

    public PersistenceManagerProvider getCriticalReadPmp() {
        return writeDirector.getReadOnlyPmp();
    }

    public PersistenceManagerProvider getReadPmp() {
        return readDirector.getReadOnlyPmp();
    }

}
