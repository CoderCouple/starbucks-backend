package com.starbucks.persistance;

import javax.jdo.PersistenceManager;

public interface DBConn {
    PersistenceManager getPmp();
}
