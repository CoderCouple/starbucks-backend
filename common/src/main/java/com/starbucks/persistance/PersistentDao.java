package com.starbucks.persistance;

import com.google.common.collect.Iterables;
import com.google.inject.assistedinject.Assisted;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static javax.jdo.Query.SQL;

public class PersistentDao<T extends PersistentObject> {
    private final PersistenceManagerProvider pmp;
    protected final Class<T> clazz;

    public PersistentDao(@Assisted final PersistenceManagerProvider pmp, final Class<T> clazz) {
        this.pmp = pmp;
        this.clazz = clazz;
    }

    protected PersistenceManagerProvider getPmp() {
        return pmp;
    }

    public List<T> getByField(final Object value, final String field) {
        return getByField(Collections.singletonList(value), field);
    }

    public List<T> getByField(final Collection<?> values, final String field) {
        return getByField(values, field, null);
    }

    private List<T> getByField(final Collection<?> values, final String field, final String orderBy) {
        if (values == null || values.isEmpty()) {
            return Collections.emptyList();
        }
        final Query q = getPmp().get().newQuery(clazz, ":values.contains(" + field + ")").orderBy(orderBy);
        @SuppressWarnings("unchecked") List<T> result = (List<T>) q.execute(values);
        return result;
    }

    protected Optional<T> getFirst(final List<T> list) {
        return Optional.ofNullable(Iterables.getFirst(list, null));
    }

    public Optional<T> getFirstByField(final Object value, final String field) {
        return getFirst(getByField(Collections.singletonList(value), field));
    }

    public Optional<T> getFirstByField(final Collection<?> values, final String field) {
        return getFirst(getByField(values, field, null));
    }

    /**
     * Write to DB
     * @param value
     * @return
     */
    public T persist(final T value) {
        PersistenceManager pm = getPmp().get();
        pm.makePersistent(value);
        return value;
    }

    public Collection<T> persistAll(final Collection<T> values) {
        PersistenceManager pm = getPmp().get();
        pm.makePersistentAll(values);
        return values;
    }

    public List<T> persistList(final List<T> list) {
        PersistenceManager pm = getPmp().get();
        List<T> persistedList = new ArrayList<>();
        for (final T value: list) {
            pm.makePersistent(value);
            persistedList.add(value);
        }
        return persistedList;
    }

    /**
     * Read after write to DB
     * @param value
     * @return persisted value
     */
    public T persistAndFetch(final T value) {
        PersistenceManager pm = getPmp().get();
        persist(value);
        // TODO: Revisit refresh()/retrieve instead of fetching by Id
        Object id = pm.getObjectId(value);
        T persistedValue = (T) pm.getObjectById(id);
        return persistedValue;
    }

    protected boolean updateTable(final String sqlQuery, final int countOfRecordsToBeUpdated) {
        final Query query = getPmp().get().newQuery(SQL, sqlQuery);
        query.setResultClass(Long.class);
        query.setUnique(true);
        Long results = (Long) query.execute();
        return (results.intValue() == countOfRecordsToBeUpdated);
    }

    protected boolean updateTable(final String sqlQuery, final Map<String, Object> param, final int recordsToBeUpdated) {
        final Query query = getPmp().get().newQuery(SQL, sqlQuery);
        query.setResultClass(Long.class);
        query.setUnique(true);
        Long results = (Long) query.executeWithMap(param);
        return (results.intValue() != recordsToBeUpdated);
    }
}
