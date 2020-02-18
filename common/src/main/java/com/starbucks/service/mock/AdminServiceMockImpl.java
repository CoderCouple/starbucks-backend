package com.starbucks.service.mock;

import com.starbucks.model.Order;
import com.starbucks.model.User;
import com.starbucks.service.AdminService;
import com.starbucks.view.OrderListView;
import com.starbucks.view.UserListView;

import java.util.ArrayList;
import java.util.List;

public class AdminServiceMockImpl implements AdminService {
    @Override
    public UserListView getAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(User.sample());
        return new UserListView(users);
    }

    @Override
    public OrderListView getAllOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(Order.sample());
        return new OrderListView(orders);
    }

    @Override
    public OrderListView getAllOrdersByStatus(final String status) {
        List<Order> orders = new ArrayList<>();
        orders.add(Order.sample().setStatus(status));
        return new OrderListView(orders);
    }
}
