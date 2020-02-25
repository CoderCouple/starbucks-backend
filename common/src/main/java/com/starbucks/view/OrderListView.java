package com.starbucks.view;

import com.starbucks.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderListView {

    private List<OrderView> orders = new ArrayList<>();

    public OrderListView(final List<Order> orders) {
        for (final  Order order : orders) {
         this.orders.add(new OrderView(order));
        }
    }

    public List<OrderView> getOrders() {
        return orders;
    }
}
