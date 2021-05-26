package com.service.serviceImpl;

import com.DAO.DAOImpl.UserDAOImpl;
import com.entity.User;
import com.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        UserServiceImpl userService = new UserServiceImpl();
        String password = user.getPassword();
        String hashPassword = userService.haschedPassword(password);
        user.setPassword(hashPassword);
        userDAO.addUser(user);
    }

    @Override
    public String haschedPassword(String password) {
        log.info("Start hashed password");
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException exception) {
            log.error(exception.getLocalizedMessage());
        }
        assert messageDigest != null;
        byte[] bytes = messageDigest.digest(password.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(String.format("%02X", b));
        }
        log.info("Password hashed");
        return String.valueOf(stringBuilder);
    }

    @Override
    public boolean validEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
