package com.DAO.DAOImpl;

import com.DAO.UserDAO;
import com.connection.ConnectionPool;
import com.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Slf4j
public class UserDAOImpl implements UserDAO {

    private static UserDAOImpl userDAOImpl;

    private UserDAOImpl() {
    }

    public static UserDAOImpl getInstance() {
        if (userDAOImpl == null) {
            userDAOImpl = new UserDAOImpl();
        }
        return userDAOImpl;
    }

    @Override
    public Optional<User> findUserByLogin(String loginUser) {
        User user = new User();
        String s = "SELECT password FROM User WHERE login = ?";
        try (Connection connection = ConnectionPool.getConnection()) {
            log.info("Try to connection to DB");
            try (PreparedStatement statement = connection.prepareStatement(s)) {
                log.info("Successfully connection to DB. Try to send request to DB to find user");
                statement.setString(1, loginUser);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    user.setPassword(resultSet.getString("password"));
                }
                log.info("Successfully send request to DB");
            } catch (SQLException exception) {
                log.error(exception.getLocalizedMessage());
                connection.rollback();
            }
        } catch (SQLException exception) {
            log.error(exception.getLocalizedMessage());
        }
        return Optional.of(user);
    }

    @Override
    public void addUser(User user) {

        String valueData = getData();

        String sql = "INSERT INTO User VALUES (DEFAULT,?,?,?,?,?,?,?,?)";
        log.info("Try to connection to DB");
        try (Connection connection = ConnectionPool.getConnection()) {
            log.info("Successfully connection to DB. Try to send request to DB to add user");
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getLogin());
                statement.setString(3, user.getPassword());
                statement.setString(4, "user");
                statement.setInt(5, user.getPreferredLang());
                statement.setBoolean(6, false);
                statement.setString(7, valueData);
                statement.setString(8, valueData);
                statement.executeUpdate();
                log.info("Successfully send request to DB");
            } catch (SQLException exception) {
                log.error(exception.getLocalizedMessage());
            }
        } catch (SQLException exception) {
            log.error(exception.getLocalizedMessage());
        }
    }

    @Override
    public String getData() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String valueData = formater.format(date);
        return valueData;
    }
}
