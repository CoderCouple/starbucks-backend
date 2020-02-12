package com.starbucks.view;

import com.starbucks.model.Order;

import java.util.ArrayList;
import java.util.List;

public class UserOrderHistoryView {
    private int id;
    private List<Order> orders = new ArrayList<>();

    public UserOrderHistoryView(final int userId, final List<Order> orderList) {
      this.id = userId;
      this.orders.addAll(orderList);
    }

    public int getId() {
        return id;
    }

    public UserOrderHistoryView setId(final int id) {
        this.id = id;
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public UserOrderHistoryView setOrders(final List<Order> orders) {
        this.orders = orders;
        return this;
    }
}
