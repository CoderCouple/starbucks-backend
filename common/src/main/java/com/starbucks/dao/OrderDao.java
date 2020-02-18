package com.starbucks.dao;

import com.starbucks.model.LineItem;
import com.starbucks.model.Order;
import com.starbucks.persistance.DBConn;
import com.starbucks.view.OrderView;

import javax.inject.Inject;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static javax.jdo.Query.SQL;

public class OrderDao extends BaseDao {

    private DBConn conn;

    @Inject
    public OrderDao(final DBConn conn) {
        this.conn = conn;
    }

    public static final String ORDER_BASE_QUERY = "SELECT o.id as id, " +
            "o.transactionId as transactionId, " +
            "o.userId as userId, " +
            "o.status as status, " +
            "o.total as total, " +
            "o.purchaseDate as purchaseDate, " +
            "o.created as created, " +
            "o.updated as updated " +
            "FROM `Order` o ";

    public static final String GET_ORDER_BY_ID = ORDER_BASE_QUERY + "WHERE o.id = :orderId";

    public static final String DELETE_ORDER_BY_ID = "DELETE FROM `Order` WHERE id = :orderId";

    public static final String UPDATE_ORDER_BY_ID = "UPDATE `Order` o SET o.status = :status WHERE o.id = :orderId";

    public Optional<Order> fetchOrderById(final int orderId) {

        PersistenceManager pm = conn.getPmp();

        Map<String, String> parameters = new HashMap<>();
        parameters.put("orderId", String.valueOf(orderId));

        Order orderRecord = null;

        Query query = pm.newQuery(SQL, GET_ORDER_BY_ID);
        query.setResultClass(Order.class);
        query.setUnique(true);
        orderRecord  = (Order) query.executeWithMap(parameters);

        return Optional.ofNullable(orderRecord);
    }

    public OrderView createOrder(final Order order, final List<LineItem> lineItems) {
        return new OrderView(order);
    }

    public boolean updateOrder(final int orderId, final String status) {
        PersistenceManager pm = conn.getPmp();

        Map<String, String> parameters = new HashMap<>();
        parameters.put("orderId", String.valueOf(orderId));
        parameters.put("status", status);
        Transaction tx = pm.currentTransaction();
        Long recordCount = 0L;
        try {
            tx.begin();
            Query query = pm.newQuery(SQL, UPDATE_ORDER_BY_ID);
            query.setResultClass(Long.class);
            recordCount = (Long) query.executeWithMap(parameters);
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }

        }
        return recordCount == 1;
    }

    public boolean deleteOrderById(final int orderId) {

        PersistenceManager pm = conn.getPmp();

        Map<String, String> parameters = new HashMap<>();
        parameters.put("orderId", String.valueOf(orderId));
        Transaction tx = pm.currentTransaction();
        Long recordCount = 0L;
        try {
            tx.begin();
            Query query = pm.newQuery(SQL, DELETE_ORDER_BY_ID);
            query.setResultClass(Long.class);
            recordCount = (Long) query.executeWithMap(parameters);
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }

        }
        return recordCount == 1;
    }

}
