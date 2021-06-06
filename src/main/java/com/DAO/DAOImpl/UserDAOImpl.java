package com.DAO.DAOImpl;

import com.DAO.UserDAO;
import com.connection.ConnectionPool;
import com.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String s = "SELECT * FROM User WHERE login = ?";
        try (Connection connection = ConnectionPool.getConnection()) {
            log.info("Connecting to a database to find a user");
            try (PreparedStatement statement = connection.prepareStatement(s)) {
                log.info("Successful connection to the database. Sending a query to the database");
                statement.setString(1, loginUser);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    user.setUserId(resultSet.getInt("id_User"));
                    user.setUsername(resultSet.getString("name"));
                    user.setLoginUser(resultSet.getString("login"));
                    user.setUserPassword(resultSet.getString("password"));
                    user.setUserRole(resultSet.getString("role"));
                    user.setUserPreferredLang(resultSet.getInt("preferred_Lang"));
                    user.setUserStatus(resultSet.getBoolean("blocked"));
                    user.setDateCreatedUser(resultSet.getString("created_At"));
                    user.setDateUpdateUser(resultSet.getString("update_At"));
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
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getLoginUser());
                statement.setString(3, user.getUserPassword());
                statement.setString(4, "user");
                statement.setInt(5, user.getUserPreferredLang());
                statement.setBoolean(6, false);
                statement.setString(7, valueData);
                statement.setString(8, valueData);
                statement.executeUpdate();
                log.info("Successfully send request to DB");
            } catch (SQLException exception) {
                log.error(exception.getLocalizedMessage());
                connection.rollback();
            }
        } catch (SQLException exception) {
            log.error(exception.getLocalizedMessage());
        }
    }

    @Override
    public String getData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }
}
