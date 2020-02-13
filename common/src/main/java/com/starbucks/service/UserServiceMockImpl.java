package com.starbucks.service;

import com.starbucks.model.Order;
import com.starbucks.model.User;
import com.starbucks.view.UserOrderHistoryView;
import com.starbucks.view.UserProfileView;
import com.starbucks.view.UserView;

import java.util.Arrays;
import java.util.Map;

public class UserServiceMockImpl implements UserService {
    @Override
    public UserView registerUser(final Map<String, String> payload) {
        return new UserView(User.sample());
    }

    @Override
    public UserProfileView loginUser(final Map<String, String> payload) {
        return new UserProfileView(User.sample(), Arrays.asList(Order.sample()));
    }

    @Override
    public boolean logoutUser(final int userId) {
        return true;
    }

    @Override
    public UserView getUserById(final int userId) {
        return new UserView(User.sample());
    }

    @Override
    public UserOrderHistoryView getUserHistory(final int userId) {
        return new UserOrderHistoryView(userId, Arrays.asList(Order.sample()));
    }
}
