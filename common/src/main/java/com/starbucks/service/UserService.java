package com.starbucks.service;

import com.starbucks.view.UserOrderHistoryView;
import com.starbucks.view.UserProfileView;
import com.starbucks.view.UserView;

import java.util.Map;

public interface UserService {

    UserView registerUser(final Map<String, String> payload);

    UserProfileView loginUser(final Map<String, String> payload);

    boolean logoutUser(final int userId);

    UserView getUserById(final int userId);

    UserOrderHistoryView getUserHistory(final int userId);
}
