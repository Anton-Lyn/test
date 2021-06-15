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

    UserDAOImpl userDAO;
    UserServiceImpl userService;

    @Override
    public User checkLogin(String email, String password) {
        log.info("Create user for check login and password in database");
        Optional<User> user = UserDAOImpl.getInstance().findUserByLogin(email);
        log.info("Successfully create user. Start check password user");
        User user1 = null;
        if (user.isPresent()) {
            user1 = user.get();
        }
        return user1;
    }

    public int checkUserExistence (String email){
        userDAO = UserDAOImpl.getInstance();
      return userDAO.findUserExistence(email);
    }

    @Override
    public void registerNewUser(User user) {
        log.info("Create user for add user in DB.");
        userDAO = UserDAOImpl.getInstance();
        log.info("Successfully create user. Start add user in DB");
        userService = new UserServiceImpl();
        String password = user.getUserPassword();
        String hashPassword = userService.hashingPassword(password);
        user.setUserPassword(hashPassword);
        userDAO.addUser(user);
    }

    @Override
    public String hashingPassword(String password) {
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

    public void editUserService (User user){
        userDAO = UserDAOImpl.getInstance();
        userService = new UserServiceImpl();
        String password = user.getUserPassword();
        String hashPassword = userService.hashingPassword(password);
        user.setUserPassword(hashPassword);
        userDAO.editUser(user);
    }

    @Override
    public boolean validEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
