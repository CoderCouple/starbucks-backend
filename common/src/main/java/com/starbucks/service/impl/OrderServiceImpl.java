package com.starbucks.service.impl;

import com.starbucks.exception.NotFoundException;
import com.starbucks.exception.StarbucksApiException;
import com.starbucks.model.LineItem;
import com.starbucks.model.Order;
import com.starbucks.persistance.DaoProvider;
import com.starbucks.persistance.PersistenceManagerProvider;
import com.starbucks.service.OrderService;
import com.starbucks.view.OrderView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private DaoProvider daoProvider;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Inject
    public OrderServiceImpl(final DaoProvider daoProvider) {
        this.daoProvider = daoProvider;
    }

    @Override
    public OrderView getOrderById(final int orderId) {
        try (final PersistenceManagerProvider pmp = daoProvider.getReadPmp()) {
            Optional<Order> order =  daoProvider.getDaoFactory().getOrderDao(pmp).fetchOrderById(orderId);
            if (!order.isPresent()) {
                throw new NotFoundException("Order not found in DB");
            }
            return new OrderView(order.get());
        }
    }

    @Override
    public OrderView createOrder(final Map<String, Object> payload) {
        OrderView orderView = null;
        int userId = Integer.valueOf(payload.get("userId").toString());
        String transactionId = "TRNX-" + userId + "-" + Instant.now().toEpochMilli();
        double total =  Double.valueOf(payload.get("total").toString());
        String status = payload.get("status").toString();
        String purchaseDate = payload.get("purchaseDate").toString();

        Order order = new Order()
                .setTransactionId(transactionId)
                .setUserId(userId)
                .setStatus(Order.Status.PLACED)
                .setTotal(total)
                //ToDo: add purchase date here
                .setPurchaseDate(Timestamp.from(Instant.now()));

        List<Map<String, Object>> lineItems = (List<Map<String, Object>>) payload.get("lineItems");

        try (final PersistenceManagerProvider pmp = daoProvider.getWritePmp()) {
            pmp.startTransaction();

            orderView = daoProvider.getDaoFactory().getOrderDao(pmp).createOrder(order);

            for (final Map<String, Object> item : lineItems) {

                int productId  = Integer.parseInt(item.get("productId").toString());
                int quantity  = Integer.parseInt(item.get("quantity").toString());
                String type  = item.get("type").toString();
                int orderId = orderView.getId();


                LineItem lineItem = new LineItem()
                        .setOrderId(orderId)
                        .setProductId(productId)
                        .setQuantity(quantity);
                daoProvider.getDaoFactory().getProductDao(pmp).updateProduct(type, quantity);
                daoProvider.getDaoFactory().getLineItemDao(pmp).createLineItem(lineItem);
            }

            pmp.commitTransaction();
        } catch (final Exception ex) {
            throw new StarbucksApiException("Failed to create order in DB");
        }

        return orderView;
    }

    @Override
    public boolean updateOrder(final int orderId, final Map<String, Object> payload) {
        try (final PersistenceManagerProvider pmp = daoProvider.getWritePmp()) {
            String status = payload.get("status").toString();
            boolean isSuccessful = daoProvider.getDaoFactory().getOrderDao(pmp).updateOrder(orderId, status);
            if (!isSuccessful) {
                throw new StarbucksApiException("Failed to delete product in DB");
            }
            return isSuccessful;
        }
    }

    @Override
    public boolean deleteOrder(final int orderId) {
        try (final PersistenceManagerProvider pmp = daoProvider.getWritePmp()) {
            boolean isSuccessful = daoProvider.getDaoFactory().getOrderDao(pmp).deleteOrderById(orderId);
            if (!isSuccessful) {
                throw new StarbucksApiException("Failed to delete product in DB");
            }
            return isSuccessful;
        }
    }
}
