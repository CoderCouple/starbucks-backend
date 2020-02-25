package com.starbucks.service.impl;

import com.starbucks.exception.DuplicateUserException;
import com.starbucks.exception.NotFoundException;
import com.starbucks.exception.UnauthorizedAccessException;
import com.starbucks.model.Order;
import com.starbucks.model.User;
import com.starbucks.persistance.DaoProvider;
import com.starbucks.persistance.PersistenceManagerProvider;
import com.starbucks.security.CoDecService;
import com.starbucks.service.UserService;
import com.starbucks.view.UserOrderHistoryView;
import com.starbucks.view.UserProfileView;
import com.starbucks.view.UserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    private DaoProvider daoProvider;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Inject
    public UserServiceImpl(final DaoProvider daoProvider) {
        this.daoProvider = daoProvider;
    }

    @Override
    public UserView registerUser(final Map<String, String> payload) throws DuplicateUserException {
        User user = new User()
                .setFirstName(payload.get("firstName"))
                .setGuid(UUID.randomUUID().toString())
                .setLastName(payload.get("lastName"))
                .setEmail(payload.get("email"))
                .setPassword(CoDecService.encryptPassword(payload.get("password")))
                .setDateOfBirth(Date.valueOf(payload.get("dob")))
                .setIsActive(true);

        try (final PersistenceManagerProvider pmp = daoProvider.getWritePmp()) {
            return daoProvider.getDaoFactory().getUserDao(pmp).createUserIfDoesNotExist(user);
        } catch (final Exception ex) {
            throw new DuplicateUserException("Duplicate User Found");
        }
    }

    @Override
    public UserProfileView loginUser(final Map<String, String> payload) {
        String email = payload.get("email");
        String pwd = payload.get("password");
        try (final PersistenceManagerProvider pmp = daoProvider.getReadPmp()) {
            Optional<User> user = daoProvider.getDaoFactory().getUserDao(pmp).fetchUserByEmail(email);
            if (!user.isPresent()) {
                throw new NotFoundException("User not found in DB");
            }
            User userInfo = user.get();
            if (!CoDecService.checkPassword(pwd, userInfo.getPassword())) {
                throw new UnauthorizedAccessException("Unauthorized User. Password miss match!!");
            }
            List<Order> orderList = daoProvider.getDaoFactory().getUserDao(pmp).fetchUserHistoryById(userInfo.getId());
            return new UserProfileView(userInfo, orderList);
        }
    }

    @Override
    public boolean logoutUser(final int userId) {
        try (final PersistenceManagerProvider pmp = daoProvider.getReadPmp()) {
            Optional<User> user = daoProvider.getDaoFactory().getUserDao(pmp).fetchUserById(userId);
            if (!user.isPresent()) {
                throw new NotFoundException("User not found in DB");
            }
            return true;
        }
    }

    @Override
    public UserView getUserById(final int userId) {
        try (final PersistenceManagerProvider pmp = daoProvider.getReadPmp()) {
            Optional<User> user = daoProvider.getDaoFactory().getUserDao(pmp).fetchUserById(userId);
            if (!user.isPresent()) {
                throw new NotFoundException("User not found in DB");
            }
            return new UserView(user.get());
        }
    }

    @Override
    public UserOrderHistoryView getUserHistory(final int userId) {
        try (final PersistenceManagerProvider pmp = daoProvider.getReadPmp()) {
            Optional<User> user = daoProvider.getDaoFactory().getUserDao(pmp).fetchUserById(userId);
            if (!user.isPresent()) {
                throw new NotFoundException("User not found in DB");
            }
            List<Order> orders = daoProvider.getDaoFactory().getUserDao(pmp).fetchUserHistoryById(userId);
            return new UserOrderHistoryView(userId, orders);
        }
    }
}
