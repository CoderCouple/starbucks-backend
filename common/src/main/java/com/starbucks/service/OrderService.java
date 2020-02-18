package com.starbucks.service;

import com.starbucks.view.OrderView;

import java.util.Map;

public interface OrderService {

    OrderView getOrderById(final int orderId);

    OrderView createOrder(final Map<String, Object> payload);

    boolean updateOrder(final int orderId, final Map<String, Object> payload);

    boolean deleteOrder(final int orderId);

}
