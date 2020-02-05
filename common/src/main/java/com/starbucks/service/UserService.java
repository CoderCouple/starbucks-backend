package com.starbucks.service;


import com.starbucks.model.User;

import java.util.Map;

public interface UserService {

    User registerUser(Map<String, String> payload);
}
