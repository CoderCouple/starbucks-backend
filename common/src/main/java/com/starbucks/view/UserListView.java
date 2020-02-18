package com.starbucks.view;

import com.starbucks.model.User;

import java.util.List;

public class UserListView {

    private List<User> users;

    public UserListView(final List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
