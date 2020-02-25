package com.starbucks.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Named;
import com.starbucks.persistance.DaoFactory;
import com.starbucks.persistance.DaoProvider;
import com.starbucks.persistance.StarbucksPersistenceDirector;

import static com.starbucks.constant.Constants.ACCESS_MASTER_DB;

public class DaoProviderModule extends AbstractModule {

    private static volatile DaoProvider daoProvider;

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().build(DaoFactory.class));
    }

    @Provides
    protected final DaoProvider getDaoProvider(final StarbucksPersistenceDirector entertainmentPersistenceDirector,
                                               final DaoFactory daoFactory,
                                               @Named(ACCESS_MASTER_DB) final Provider<Boolean> accessMasterDb) {
        if (daoProvider == null) {
            synchronized (DaoProviderModule.class) {
                if (daoProvider == null) {
                    daoProvider =
                            new DaoProvider(entertainmentPersistenceDirector, daoFactory,
                                    accessMasterDb);
                }
            }

        }
        return daoProvider;
    }
}
