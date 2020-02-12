package com.starbucks.dao;

import com.starbucks.model.Order;
import com.starbucks.model.User;
import com.starbucks.persistance.DBConn;
import com.starbucks.view.UserView;

import javax.inject.Inject;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static javax.jdo.Query.SQL;

public class UserDao extends BaseDao {

    private DBConn conn;

    @Inject
    public UserDao(final DBConn conn) {
        this.conn = conn;
    }

    public static final String USER_BASE_QUERY =    "SELECT * FROM `User` u";

    public static final String GET_USER_BY_EMAIL = USER_BASE_QUERY + " WHERE u.email = :email";

    public static final String GET_USER_BY_USER_ID = USER_BASE_QUERY + " WHERE u.id = :userId";

    public static final String ORDER_BASE_QUERY = "Select * from `Order` o , User u";

    public static final String GET_USER_ORDERS_BY_USER_ID = ORDER_BASE_QUERY + " WHERE u.id = o.userId AND 0.userId = :userId";

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

    public Optional<User> fetchUserByEmail(final String email) {
        Objects.requireNonNull(email);

        PersistenceManager pm = conn.getPmp();

        Map<String, String> parameters = new HashMap<>();
        parameters.put("email", email);

        User userRecord = null;
        Query query = pm.newQuery(SQL, GET_USER_BY_EMAIL);
        query.setResultClass(User.class);
        query.setUnique(true);
        userRecord  = (User) query.executeWithMap(parameters);

        return Optional.ofNullable(userRecord);
    }

    public Optional<User> fetchUserById(final int userId) {

        PersistenceManager pm = conn.getPmp();

        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", String.valueOf(userId));

        User userRecord = null;

        Query query = pm.newQuery(SQL, GET_USER_BY_USER_ID);
        query.setResultClass(User.class);
        query.setUnique(true);
        userRecord  = (User) query.executeWithMap(parameters);

        return Optional.ofNullable(userRecord);
    }

    public List<Order> fetchUserHistoryById(final int userId) {

        PersistenceManager pm = conn.getPmp();

        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", String.valueOf(userId));

        Query query = pm.newQuery(SQL, GET_USER_ORDERS_BY_USER_ID);
        query.setResultClass(Order.class);

        List<Order> orderList = (List<Order>) query.executeWithMap(parameters);
        return orderList;
    }
}
