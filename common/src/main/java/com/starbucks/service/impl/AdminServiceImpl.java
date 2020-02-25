package com.starbucks.service.impl;

import com.starbucks.exception.NotFoundException;
import com.starbucks.model.Order;
import com.starbucks.model.User;
import com.starbucks.persistance.DaoProvider;
import com.starbucks.persistance.PersistenceManagerProvider;
import com.starbucks.service.AdminService;
import com.starbucks.view.OrderListView;
import com.starbucks.view.UserListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class AdminServiceImpl implements AdminService {

    private DaoProvider daoProvider;
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Inject
    public AdminServiceImpl(final DaoProvider daoProvider) {
        this.daoProvider = daoProvider;
    }

    @Override
    public UserListView getAllUsers() {
        try (final PersistenceManagerProvider pmp = daoProvider.getReadPmp()) {
            Optional<List<User>> userList = daoProvider.getDaoFactory().getUserDao(pmp).fetchAllUsers();
            if (!userList.isPresent()) {
                throw new NotFoundException("User not found in DB");
            }
            return new UserListView(userList.get());
        }
    }

    @Override
    public OrderListView getAllOrders() {
        try (final PersistenceManagerProvider pmp = daoProvider.getReadPmp()) {
            Optional<List<Order>> orderList = daoProvider.getDaoFactory().getOrderDao(pmp).fetchAllOrders();
            if (!orderList.isPresent()) {
                throw new NotFoundException("Order not found in DB");
            }
            return new OrderListView(orderList.get());
        }
    }

    @Override
    public OrderListView getAllOrdersByStatus(final String status) {
        try (final PersistenceManagerProvider pmp = daoProvider.getReadPmp()) {
            Optional<List<Order>> orderList = daoProvider.getDaoFactory().getOrderDao(pmp).fetchAllOrdersByStatus(status);
            if (!orderList.isPresent()) {
                throw new NotFoundException("Order not found in DB");
            }
            return new OrderListView(orderList.get());
        }
    }
}
