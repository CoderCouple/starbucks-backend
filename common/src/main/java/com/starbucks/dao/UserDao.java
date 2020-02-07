package com.starbucks.dao;

import com.starbucks.model.User;
import com.starbucks.persistance.DBConn;
import com.starbucks.view.UserView;

import javax.inject.Inject;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

public class UserDao extends BaseDao {

    private DBConn conn;

    @Inject
    public UserDao(final DBConn conn) {
        this.conn = conn;
    }

    public static final String USER_BASE_QUERY = "SELECT * FROM User;";

    public UserView createUserIfDoesNotExist(final User user) {
        PersistenceManager pm = conn.getPmp();
        Transaction tx = pm.currentTransaction();
        User userRecord = null;
        try {
            tx.begin();
            userRecord = (User) persistAndFetch(pm, user);
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }

        }

        return new UserView(userRecord);
    }
}
