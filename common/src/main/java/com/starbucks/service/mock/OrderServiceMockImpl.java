package com.starbucks.service.mock;

import com.starbucks.model.Order;
import com.starbucks.service.OrderService;
import com.starbucks.view.OrderView;

import java.util.Map;

public class OrderServiceMockImpl implements OrderService {
    @Override
    public OrderView getOrderById(final int orderId) {
        return new OrderView(Order.sample().setId(orderId));
    }

    @Override
    public OrderView createOrder(final Map<String, Object> payload) {
        return new OrderView(Order.sample());
    }

    @Override
    public boolean updateOrder(final int productId, final Map<String, Object> payload) {
        return true;
    }

    @Override
    public boolean deleteOrder(final int orderId) {
        return true;
    }
}
