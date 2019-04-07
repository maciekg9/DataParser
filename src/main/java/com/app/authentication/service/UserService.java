package com.app.authentication.service;

import com.app.authentication.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}