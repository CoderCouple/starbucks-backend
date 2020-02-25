package com.starbucks.persistance;

import com.google.inject.Provider;
import com.starbucks.exception.StarbucksDBAccessException;

public class DaoProvider {

    private final StarbucksPersistenceDirector persistenceDirector;
    private final DaoFactory daoFactory;
    private final Provider<Boolean> accessMasterDb;

    public DaoProvider(final StarbucksPersistenceDirector persistenceDirector,
                       final DaoFactory daoFactory,
                       final Provider<Boolean> accessMasterDb) {
        this.persistenceDirector = persistenceDirector;
        this.daoFactory = daoFactory;
        this.accessMasterDb = accessMasterDb;
    }

    public DaoFactory getDaoFactory() {
        return daoFactory;
    }

    /**
     * Get Read Persistent Manager Provider
     *
     * @return Read Persistent Manager Provider
     */
    public PersistenceManagerProvider getReadPmp() {
        return persistenceDirector.getReadPmp();
    }

    /**
     * Get Critical Read Persistent Manager Provider
     *
     * @return Critical Read Persistent Manager Provider
     */
    public PersistenceManagerProvider getCriticalReadPmp() {
        if (!accessMasterDb.get()) {
            return persistenceDirector.getReadPmp();
        } else {
            return persistenceDirector.getCriticalReadPmp();
        }

    }

    /**
     * Get Write Persistent Manager Provider
     *
     * @throws StarbucksDBAccessException
     * @return Write Persistent Manager Provider
     */
    public PersistenceManagerProvider getWritePmp() {
        if (!accessMasterDb.get()) {
            throw new StarbucksDBAccessException("Cannot access Write Pmp from Read Only rotation");
        }
        return persistenceDirector.getWritePmp();
    }
}
