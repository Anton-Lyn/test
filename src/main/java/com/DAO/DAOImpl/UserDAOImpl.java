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
    public String getData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    @Override
    public Optional<User> findUserByLogin(String loginUser) {
        User user = null;
        String sqlQuery = "SELECT * FROM User WHERE login = ?";
        try (Connection connection = ConnectionPool.getConnection()) {
            log.info("Connecting to a database to find a user");
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                log.info("Successful connection to the database. Sending a query to the database");
                statement.setString(1, loginUser);
                ResultSet resultSet = statement.executeQuery();
                user = new User();
                while (resultSet.next()) {
                    user.setUserId(resultSet.getInt("id_User"));
                    user.setUserName(resultSet.getString("name"));
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
        return Optional.ofNullable(user);
    }

    @Override
    public void addUser(User user) {

        String valueData = getData();

        String sqlQuery = "INSERT INTO User VALUES (DEFAULT,?,?,?,?,?,?,?,?)";
        log.info("Try to connection to DB");
        try (Connection connection = ConnectionPool.getConnection()) {
            log.info("Successfully connection to DB. Try to send request to DB to add user");
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getLoginUser());
                preparedStatement.setString(3, user.getUserPassword());
                preparedStatement.setString(4, "user");
                preparedStatement.setInt(5, user.getUserPreferredLang());
                preparedStatement.setBoolean(6, false);
                preparedStatement.setString(7, valueData);
                preparedStatement.setString(8, valueData);
                preparedStatement.executeUpdate();
                log.info("Successfully send request to DB");
            } catch (SQLException exception) {
                log.error(exception.getLocalizedMessage());
                connection.rollback();
            }
        } catch (SQLException exception) {
            // todo do handling
            log.error(exception.getLocalizedMessage());
        }
    }

    @Override
    public int findUserExistence(String loginUser) {
        // todo get rid of zero, add optional or Integer
        int idUser = 0;
        String sqlQuery = "SELECT id_User FROM User WHERE login = ?";
        try (Connection connection = ConnectionPool.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, loginUser);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    idUser = resultSet.getInt("id_User");
                }
            } catch (SQLException exception) {
                log.error(exception.getLocalizedMessage());
                connection.rollback();
            }
        } catch (SQLException exception) {
            log.error(exception.getLocalizedMessage());
        }
        return idUser;
    }

    @Override
    public void editUser(User user) {

        String valueData = getData();
        String sqlQuery = "UPDATE User SET name = ?, login = ?, password = ?, role = ?, preferred_Lang = ?, blocked = ?, update_At = ? WHERE id_User = ?";

        try (Connection connection = ConnectionPool.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, user.getUserName());
                statement.setString(2, user.getLoginUser());
                statement.setString(3, user.getUserPassword());
                statement.setString(4, user.getUserRole());
                statement.setInt(5, user.getUserPreferredLang());
                statement.setBoolean(6, user.getUserStatus());
                statement.setString(7, valueData);
                statement.setInt(8, user.getUserId());
                statement.executeUpdate();
            } catch (SQLException exception) {
                log.error(exception.getLocalizedMessage());
                connection.rollback();
            }
        } catch (SQLException exception) {
            log.error(exception.getLocalizedMessage());
        }
    }
}
