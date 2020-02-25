package com.starbucks.view;

import com.starbucks.model.User;

import java.util.List;

public class UserListView {

    private List<UserView> users;

    public UserListView(final List<User> users) {
        for (final User user : users) {
            this.users.add(new UserView(user));
        }
    }

    public List<UserView> getUsers() {
        return users;
    }
}
