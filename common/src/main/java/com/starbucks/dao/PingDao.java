package com.starbucks.dao;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import com.starbucks.model.Ping;
import com.starbucks.persistance.PersistenceManagerProvider;
import com.starbucks.persistance.PersistentDao;
import com.starbucks.view.PingView;

import javax.jdo.Query;

import static javax.jdo.Query.SQL;

public class PingDao extends PersistentDao<Ping> {

    @AssistedInject
    public PingDao(final @Assisted PersistenceManagerProvider pmp) {
        super(pmp, Ping.class);
    }

    public static final String PING_BASE_QUERY = "SELECT id, data FROM Ping";

    public PingView getPing() {

        Query q = getPmp().get().newQuery(SQL, PING_BASE_QUERY);
        q.setResultClass(Ping.class);
        q.setUnique(true);
        Ping ping = (Ping) q.execute();

        return new PingView(ping);
    }
}
