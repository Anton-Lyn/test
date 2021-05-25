package com.service.serviceImpl;

import com.DAO.DAOImpl.UserDAOImpl;
import com.entity.User;
import com.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public boolean checkLogin(String email, String password) {
        log.info("Create user for check login and password in database");
        Optional<User> user = UserDAOImpl.getInstance().findUserByLogin(email);
        log.info("Successfully create user. Start check password user");
        if (user.isPresent()) {
            User user1 = user.get();
            String s = user1.getPassword();
            if (s == null) {
                return false;
            }
            log.info("Successfully check user in database");
            return s.equals(password);
        }
        return false;
    }

    @Override
    public void registerNewUser(User user) {
        log.info("Create user for add user in DB.");
        UserDAOImpl userDAO = UserDAOImpl.getInstance();
        log.info("Successfully create user. Start add user in DB");
        userDAO.addUser(user);
    }
}
