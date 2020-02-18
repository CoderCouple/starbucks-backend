package com.starbucks.service.impl;

import com.starbucks.dao.AdminDao;
import com.starbucks.exception.NotFoundException;
import com.starbucks.model.Order;
import com.starbucks.model.User;
import com.starbucks.service.AdminService;
import com.starbucks.view.OrderListView;
import com.starbucks.view.UserListView;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class AdminServiceImpl implements AdminService {


    private AdminDao adminDao;

    @Inject
    public AdminServiceImpl(final AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public UserListView getAllUsers() {
        Optional<List<User>>  userList = adminDao.fetchAllUsers();
        if (!userList.isPresent()) {
            throw new NotFoundException("User not found in DB");
        }
        return new UserListView(userList.get());
    }

    @Override
    public OrderListView getAllOrders() {
        Optional<List<Order>>  orderList = adminDao.fetchAllOrders();
        if (!orderList.isPresent()) {
            throw new NotFoundException("Order not found in DB");
        }
        return new OrderListView(orderList.get());
    }

    @Override
    public OrderListView getAllOrdersByStatus(final String status) {
        Optional<List<Order>>  orderList = adminDao.fetchAllOrdersByStatus(status);
        if (!orderList.isPresent()) {
            throw new NotFoundException("Order not found in DB");
        }
        return new OrderListView(orderList.get());
    }
}
