package com.service;

import com.entity.User;

public interface UserService {
    boolean checkLogin (String email, String password);
    void registerNewUser(User user);
    String haschedPassword (String password);
    boolean validEmail (String email);
}
