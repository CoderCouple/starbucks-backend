package com.starbucks.service.impl;

import com.starbucks.dao.OrderDao;
import com.starbucks.exception.DuplicateUserException;
import com.starbucks.exception.NotFoundException;
import com.starbucks.exception.StarbucksApiException;
import com.starbucks.model.LineItem;
import com.starbucks.model.Order;
import com.starbucks.service.OrderService;
import com.starbucks.util.Utils;
import com.starbucks.view.OrderView;

import javax.inject.Inject;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    @Inject
    public OrderServiceImpl(final OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public OrderView getOrderById(final int orderId) {
        Optional<Order> order = orderDao.fetchOrderById(orderId);
        if (!order.isPresent()) {
            throw new NotFoundException("Order not found in DB");
        }
        return new OrderView(order.get());
    }

    @Override
    public OrderView createOrder(final Map<String, Object> payload) {
        OrderView orderView = null;
        int userId = Integer.valueOf((String) payload.get("userId"));
        String transactionId = userId + "-" + Instant.now().toEpochMilli();
        List<Map<String, String>> lineItems = (List<Map<String, String>>) payload.get("lineItems");
        List<LineItem> lineItemList = new ArrayList<>();
        for (final Map<String, String> item : lineItems) {
            LineItem lineItem = new LineItem()
                    .setOrderId(Integer.valueOf((String) payload.get("")))
                    .setProductId(Integer.valueOf(item.get("")))
                    .setQuantity(Integer.valueOf(item.get("")));

            lineItemList.add(lineItem);
        }
        Order order = new Order()
                .setTransactionId(transactionId)
                .setUserId(userId)
                .setInventoryId(1)
                .setStatus(Order.Status.PLACED)
                .setTotal(100)
                .setPurchaseDate(Utils.getUTCTimestamp("2020-01-01 01:01:01"))
                .setCreated(Utils.getUTCTimestamp("2020-01-01 01:01:01"))
                .setUpdated(Utils.getUTCTimestamp("2020-01-01 01:01:01"));;

        try {
            orderView = orderDao.createOrder(order, lineItemList);
        } catch (final Exception ex) {
            throw new DuplicateUserException("Duplicate User Found");
        }

        return orderView;
    }

    @Override
    public boolean updateOrder(final int orderId, final Map<String, Object> payload) {
        String status = payload.get("status").toString();
        boolean isSuccessful = orderDao.updateOrder(orderId, status);
        if (!isSuccessful) {
            throw new StarbucksApiException("Failed to delete product in DB");
        }
        return isSuccessful;
    }

    @Override
    public boolean deleteOrder(final int orderId) {
        boolean isSuccessful = orderDao.deleteOrderById(orderId);
        if (!isSuccessful) {
            throw new StarbucksApiException("Failed to delete product in DB");
        }
        return isSuccessful;
    }
}
