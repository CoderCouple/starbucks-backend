package com.starbucks.service;

import com.starbucks.model.User;
import com.starbucks.view.UserView;

import java.util.Map;

public class UserServiceMockImpl implements UserService {
    @Override
    public UserView registerUser(final Map<String, String> payload) {
        return new UserView(User.sample());
    }
}
