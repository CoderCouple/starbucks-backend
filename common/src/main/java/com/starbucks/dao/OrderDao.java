package com.starbucks.dao;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import com.starbucks.model.Order;
import com.starbucks.persistance.PersistenceManagerProvider;
import com.starbucks.persistance.PersistentDao;
import com.starbucks.view.OrderView;

import javax.jdo.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static javax.jdo.Query.SQL;

public class OrderDao extends PersistentDao<Order> {

    @AssistedInject
    public OrderDao(final @Assisted PersistenceManagerProvider pmp) {
        super(pmp, Order.class);
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

    public static final String GET_ALL_ORDERS_QUERY = ORDER_BASE_QUERY;

    public static final String GET_ALL_ORDER_BY_STATUS_QUERY = GET_ALL_ORDERS_QUERY + "WHERE o.status = :status";

    public static final String GET_ORDER_BY_ID = ORDER_BASE_QUERY + "WHERE o.id = :orderId";

    public static final String DELETE_ORDER_BY_ID = "DELETE FROM `Order` WHERE id = :orderId";

    public static final String UPDATE_ORDER_BY_ID = "UPDATE `Order` o SET o.status = :status WHERE o.id = :orderId";

    public Optional<Order> fetchOrderById(final int orderId) {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("orderId", String.valueOf(orderId));

        Query query = getPmp().get().newQuery(SQL, GET_ORDER_BY_ID);
        query.setResultClass(Order.class);
        query.setUnique(true);
        Order orderRecord  = (Order) query.executeWithMap(parameters);

        return Optional.ofNullable(orderRecord);
    }

    public OrderView createOrder(final Order order) {
        Order orderRecord = persistAndFetch(order);
        return new OrderView(orderRecord);
    }

    public boolean updateOrder(final int orderId, final String status) {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("orderId", String.valueOf(orderId));
        parameters.put("status", status);
        Query query = getPmp().get().newQuery(SQL, UPDATE_ORDER_BY_ID);
        query.setResultClass(Long.class);
        Long recordCount = (Long) query.executeWithMap(parameters);

        return recordCount == 1;
    }

    public boolean deleteOrderById(final int orderId) {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("orderId", String.valueOf(orderId));
        Query query = getPmp().get().newQuery(SQL, DELETE_ORDER_BY_ID);
        query.setResultClass(Long.class);
        Long recordCount = (Long) query.executeWithMap(parameters);

        return recordCount == 1;
    }

    public Optional<List<Order>> fetchAllOrders() {

        Query query = getPmp().get().newQuery(SQL, GET_ALL_ORDERS_QUERY);
        query.setResultClass(Order.class);
        List<Order> orderRecords = (List<Order>) query.execute();

        return Optional.ofNullable(orderRecords);
    }

    public Optional<List<Order>> fetchAllOrdersByStatus(final String status) {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("status", status);

        Query query = getPmp().get().newQuery(SQL, GET_ALL_ORDER_BY_STATUS_QUERY);
        query.setResultClass(Order.class);
        List<Order> orderRecords = (List<Order>) query.executeWithMap(parameters);

        return Optional.ofNullable(orderRecords);
    }

}
