package com.starbucks.view;

import com.starbucks.model.Order;

import java.util.List;

public class OrderListView {

    private List<Order> orders;

    public OrderListView(final List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
