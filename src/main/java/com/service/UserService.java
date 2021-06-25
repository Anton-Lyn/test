package com.service;

import com.entity.User;

import java.util.List;

public interface UserService {
    User checkLogin (String email, String password);
    void registerNewUser(User user);
    String hashingPassword(String password);
    boolean emailValidityCheck(String email);
    User checkUserExistence (String email);
    void userUpdate(User user);
    boolean isAdmin(User user, String password);
    boolean idUser(User user, String password);
    List<User> getAllUsers ();
}
