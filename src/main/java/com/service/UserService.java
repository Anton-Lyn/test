package com.service;

import com.entity.User;

public interface UserService {
    public boolean checkLogin (String email, String password);
    void registerNewUser(User user);
}
