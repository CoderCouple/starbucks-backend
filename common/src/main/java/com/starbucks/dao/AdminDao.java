package com.starbucks.dao;

import com.starbucks.model.Order;
import com.starbucks.model.User;
import com.starbucks.persistance.DBConn;

import javax.inject.Inject;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static javax.jdo.Query.SQL;

public class AdminDao extends BaseDao {
    private DBConn conn;

    @Inject
    public AdminDao(final DBConn conn) {
        this.conn = conn;
    }

    public static final String GET_ALL_USERS_QUERY =    "SELECT u.id as id, " +
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

    public static final String GET_ALL_ORDERS_QUERY = "SELECT o.id as id, " +
            "o.transactionId as transactionId, " +
            "o.userId as userId, " +
            "o.status as status, " +
            "o.total as total, " +
            "o.purchaseDate as purchaseDate, " +
            "o.created as created, " +
            "o.updated as updated " +
            "FROM `Order` o ";

    public static final String GET_ALL_ORDER_BY_STATUS_QUERY = GET_ALL_ORDERS_QUERY + "WHERE o.status = :status";

    public Optional<List<Order>> fetchAllOrders() {
        PersistenceManager pm = conn.getPmp();

        List<Order> orderRecords = null;

        Query query = pm.newQuery(SQL, GET_ALL_ORDERS_QUERY);
        query.setResultClass(Order.class);
        orderRecords = (List<Order>) query.execute();

        return Optional.ofNullable(orderRecords);
    }

    public Optional<List<Order>> fetchAllOrdersByStatus(final String status) {
        PersistenceManager pm = conn.getPmp();

        Map<String, String> parameters = new HashMap<>();
        parameters.put("status", status);

        List<Order> orderRecords = null;

        Query query = pm.newQuery(SQL, GET_ALL_ORDER_BY_STATUS_QUERY);
        query.setResultClass(Order.class);
        orderRecords = (List<Order>) query.executeWithMap(parameters);

        return Optional.ofNullable(orderRecords);
    }

    public Optional<List<User>> fetchAllUsers() {
        PersistenceManager pm = conn.getPmp();

        List<User> userRecords = null;

        Query query = pm.newQuery(SQL, GET_ALL_USERS_QUERY);
        query.setResultClass(User.class);
        userRecords = (List<User>) query.execute();

        return Optional.ofNullable(userRecords);
    }


}
