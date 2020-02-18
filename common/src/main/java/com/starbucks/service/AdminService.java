package com.starbucks.service;

import com.starbucks.view.OrderListView;
import com.starbucks.view.UserListView;

public interface AdminService {

    UserListView getAllUsers();

    OrderListView getAllOrders();

    OrderListView getAllOrdersByStatus(final String status);
}
