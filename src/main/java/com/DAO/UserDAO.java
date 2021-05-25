package com.DAO;

import com.entity.User;
import java.util.Optional;

public interface UserDAO {
    Optional<User> findUserByLogin(String loginUser);
    void addUser(User user);
    String getData();
}
