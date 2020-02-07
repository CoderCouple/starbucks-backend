package com.starbucks.service;


import com.starbucks.view.UserView;

import java.util.Map;

public interface UserService {

    UserView registerUser(Map<String, String> payload);
}
