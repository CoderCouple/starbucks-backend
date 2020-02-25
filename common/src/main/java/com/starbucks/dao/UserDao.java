package com.starbucks.dao;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import com.starbucks.model.Order;
import com.starbucks.model.User;
import com.starbucks.persistance.PersistenceManagerProvider;
import com.starbucks.persistance.PersistentDao;
import com.starbucks.view.UserView;

import javax.jdo.Query;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static javax.jdo.Query.SQL;

public class UserDao extends PersistentDao<User> {

    @AssistedInject
    public UserDao(final @Assisted PersistenceManagerProvider pmp) {
        super(pmp, User.class);
    }

    public static final String USER_BASE_QUERY = "SELECT u.id as id, " +
            "u.guid as guid, " +
            "u.firstName as firstName, " +
            "u.lastName as lastName, " +
            "u.email as email, " +
            "u.password as password, " +
            "u.dateOfBirth as dateOfBirth, " +
            "u.isActive as isActive, " +
            "u.created as created, " +
            "u.updated as updated " +
            "FROM `User` u ";

    public static final String ORDER_BASE_QUERY = "SELECT o.id as id, " +
            "o.transactionId as transactionId, " +
            "o.userId as userId, " +
            "o.status as status, " +
            "o.total as total, " +
            "o.purchaseDate as purchaseDate, " +
            "o.created as created, " +
            "o.updated as updated " +
            "FROM `Order` o ";

    public static final String GET_USER_BY_EMAIL = USER_BASE_QUERY + "WHERE u.email = :email";

    public static final String GET_USER_BY_USER_ID = USER_BASE_QUERY + "WHERE u.id = :userId";

    public static final String GET_USER_ORDERS_BY_USER_ID = ORDER_BASE_QUERY + "WHERE o.userId = :userId";

    public static final String GET_ALL_USERS_QUERY = USER_BASE_QUERY;

    public UserView createUserIfDoesNotExist(final User user) throws SQLIntegrityConstraintViolationException {
        User userRecord = persistAndFetch(user);
        return new UserView(userRecord);
    }

    public Optional<User> fetchUserByEmail(final String email) {
        Objects.requireNonNull(email);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("email", email);

        Query query = getPmp().get().newQuery(SQL, GET_USER_BY_EMAIL);
        query.setResultClass(User.class);
        query.setUnique(true);
        User userRecord = (User) query.executeWithMap(parameters);

        return Optional.ofNullable(userRecord);
    }

    public Optional<User> fetchUserById(final int userId) {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", String.valueOf(userId));

        Query query = getPmp().get().newQuery(SQL, GET_USER_BY_USER_ID);
        query.setResultClass(User.class);
        query.setUnique(true);
        User userRecord = (User) query.executeWithMap(parameters);

        return Optional.ofNullable(userRecord);
    }

    public List<Order> fetchUserHistoryById(final int userId) {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", String.valueOf(userId));

        Query query = getPmp().get().newQuery(SQL, GET_USER_ORDERS_BY_USER_ID);
        query.setResultClass(Order.class);

        List<Order> orderList = (List<Order>) query.executeWithMap(parameters);
        return orderList;
    }

    public Optional<List<User>> fetchAllUsers() {

        Query query = getPmp().get().newQuery(SQL, GET_ALL_USERS_QUERY);
        query.setResultClass(User.class);
        List<User> userRecords = (List<User>) query.execute();

        return Optional.ofNullable(userRecords);
    }
}
