package com.starbucks.service;

import com.starbucks.dao.UserDao;
import com.starbucks.model.User;
import com.starbucks.util.Utils;

import javax.inject.Inject;
import java.sql.Date;
import java.util.Map;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Inject
    public UserServiceImpl(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User registerUser(final Map<String, String> payload) {
        User user = new User()
                .setFirstName(payload.get("firstName"))
                .setGuid(UUID.randomUUID().toString())
                .setLastName(payload.get("lastName"))
                .setEmail(payload.get("email"))
                .setPassword(payload.get("password"))
                .setDateOfBirth(new Date(Utils.getUTCTimestamp(payload.get("dob")).getTime()))
                .setIsActive(true);

        return userDao.createUserIfDoesNotExist(user);
    }
}
