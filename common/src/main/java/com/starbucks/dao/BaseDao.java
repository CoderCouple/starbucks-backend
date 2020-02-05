package com.starbucks.dao;

import javax.jdo.PersistenceManager;

public class BaseDao<T extends PersistentObject> {

    public T persist(final PersistenceManager pm, final T value) {
        pm.makePersistent(value);
        return value;
    }

    public T persistAndFetch(final PersistenceManager pm, final T value) {
        persist(pm, value);
        Object id = pm.getObjectId(value);
        T persistedValue = (T) pm.getObjectById(id);
        return persistedValue;
    }
}
