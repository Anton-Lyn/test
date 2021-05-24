package com.DAO.DAOImpl;

import com.DAO.UserDAO;
import com.connection.ConnectionPool;
import com.entity.User;

import java.sql.*;
import java.util.Optional;


public class UserDAOImpl implements UserDAO {

    private static UserDAOImpl userDAOImpl;

    private UserDAOImpl () {}

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
            try (PreparedStatement statement = connection.prepareStatement(s)) {
                statement.setString(1, loginUser);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    user.setPassword(resultSet.getString("password"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.of(user);
    }
}
