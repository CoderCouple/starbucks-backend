package com.starbucks.service;

import com.starbucks.dao.UserDao;
import com.starbucks.exception.DuplicateUserException;
import com.starbucks.exception.UnauthorizedUserException;
import com.starbucks.exception.UserNotFoundException;
import com.starbucks.model.Order;
import com.starbucks.model.User;
import com.starbucks.security.CoDecService;
import com.starbucks.view.UserOrderHistoryView;
import com.starbucks.view.UserProfileView;
import com.starbucks.view.UserView;

import javax.inject.Inject;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Inject
    public UserServiceImpl(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserView registerUser(final Map<String, String> payload) throws DuplicateUserException {
        UserView userView = null;
        User user = new User()
                .setFirstName(payload.get("firstName"))
                .setGuid(UUID.randomUUID().toString())
                .setLastName(payload.get("lastName"))
                .setEmail(payload.get("email"))
                .setPassword(CoDecService.encryptPassword(payload.get("password")))
                .setDateOfBirth(Date.valueOf(payload.get("dob")))
                .setIsActive(true);

        try {
             userView = userDao.createUserIfDoesNotExist(user);
        } catch (final Exception ex) {
            throw new DuplicateUserException("Duplicate User Found");
        }

        return userView;
    }

    @Override
    public UserProfileView loginUser(final Map<String, String> payload) {
        String email = payload.get("email");
        String pwd = payload.get("password");
        Optional<User> user =  userDao.fetchUserByEmail(email);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found in DB");
        }
        User userInfo = user.get();
        if (!CoDecService.checkPassword(pwd, userInfo.getPassword())) {
            throw new UnauthorizedUserException("Unauthorized User. Password miss match!!");
        }
        List<Order> orderList = userDao.fetchUserHistoryById(userInfo.getId());
        return new UserProfileView(userInfo, orderList);
    }

    @Override
    public boolean logoutUser(final int userId) {
        Optional<User> user = userDao.fetchUserById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found in DB");
        }
        return true;
    }

    @Override
    public UserView getUserById(final int userId) {
        Optional<User> user = userDao.fetchUserById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found in DB");
        }
        return new UserView(user.get());
    }

    @Override
    public UserOrderHistoryView getUserHistory(final int userId) {
        Optional<User> user = userDao.fetchUserById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found in DB");
        }
        List<Order> orders = userDao.fetchUserHistoryById(userId);
        return new UserOrderHistoryView(userId, orders);
    }
}
