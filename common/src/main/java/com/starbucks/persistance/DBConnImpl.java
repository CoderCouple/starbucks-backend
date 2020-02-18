package com.starbucks.persistance;

import com.starbucks.config.SharedConfig;

import javax.inject.Inject;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import java.util.Properties;

public class DBConnImpl implements DBConn {
    private SharedConfig config;
    private PersistenceManagerFactory pmf;
    private  PersistenceManager pm;

    @Inject
    public DBConnImpl(final SharedConfig config) {
        this.config = config;
        pmf = JDOHelper.getPersistenceManagerFactory(getDBProperties(config));
        pm = pmf.getPersistenceManager();
    }

    @Override
    public PersistenceManager getPmp() {
        return pm;
    }

    private Properties getDBProperties(final SharedConfig config) {
        Properties properties = new Properties();
        properties.setProperty("javax.jdo.option.ConnectionDriverName", config.getString("datanucleus.ConnectionDriverName"));
        properties.setProperty("javax.jdo.option.ConnectionURL", config.getString("datanucleus.ConnectionURL"));
        properties.setProperty("javax.jdo.option.ConnectionUserName", config.getString("datanucleus.ConnectionUserName"));
        properties.setProperty("javax.jdo.option.ConnectionPassword", config.getString("datanucleus.ConnectionPassword"));
        properties.setProperty("datanucleus.query.sql.allowAll", config.getString("datanucleus.query.sql.allowAll"));
        return properties;
    }
}
