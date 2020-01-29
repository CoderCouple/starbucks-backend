package com.starbucks.persistance;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import java.io.File;

public class DBConnImpl implements DBConn {

    private final static File FILE = new File("/Users/sunil28/Desktop/Projects/starbucks-backend/common/src/main/resources/liquibase/db.properties");
    private static PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory(FILE);
    private static PersistenceManager pm = pmf.getPersistenceManager();


    @Override
    public PersistenceManager getPmp() {
        return pm;
    }
}
