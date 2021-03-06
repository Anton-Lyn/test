package com.DAO.DAOImpl;

import com.DAO.UserDAO;
import com.connection.ConnectionPool;
import com.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
                log.info("Successful connection");
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
        log.info("Connecting to the database to adding user");
        try (Connection connection = ConnectionPool.getConnection()) {
            log.info("Successful connection");
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
            }
        } catch (SQLException exception) {
            log.error(exception.getLocalizedMessage());
        }
    }

    @Override
    public Optional<User> findUserExistence(String loginUser) {
        User user = null;
        String sqlQuery = "SELECT * FROM User WHERE login = ?";
        log.info("Connecting to the database to check user in DB");
        try (Connection connection = ConnectionPool.getConnection()) {
            log.info("Successful connection");
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
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
                    user.setDateUpdateUser(resultSet.getString("created_At"));
                    user.setDateCreatedUser(resultSet.getString("update_At"));
                }
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
    public void editUser(User user) {

        String valueData = getData();
        String sqlQuery = "UPDATE User SET name = ?, login = ?, password = ?, role = ?, preferred_Lang = ?, blocked = ?, update_At = ? WHERE id_User = ?";

        log.info("Connecting to the database to update user");
        try (Connection connection = ConnectionPool.getConnection()) {
            log.info("Successful connection");
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

    public List<User> getAllUsersDB() {

        String sqlQuery = "SELECT name, id_User FROM User";
        List<User> userList = new ArrayList<>();

        log.info("Connecting to the database to get all subjects");
        try (Connection connection = ConnectionPool.getConnection()) {
            log.info("Successful connection");
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sqlQuery);
                while (resultSet.next()) {
                    User user = new User();
                    user.setUserName(resultSet.getString("name"));
                    user.setUserId(resultSet.getInt("id_User"));
                    userList.add(user);
                }
            } catch (SQLException exception) {
                log.error(exception.getLocalizedMessage());
            }
        } catch (SQLException exception) {
            log.error(exception.getLocalizedMessage());
        }
        return userList;
    }

    public void deleteUser(Integer idUser) {

        String sqlQuery = "DELETE FROM User WHERE id_User = ?";

        log.info("Connecting to the database to delete question");
        try (Connection connection = ConnectionPool.getConnection()) {
            log.info("Successful connection");
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, idUser);
                preparedStatement.executeUpdate();
            } catch (SQLException exception) {
                log.error(exception.getLocalizedMessage());
            }
        } catch (SQLException exception) {
            log.error(exception.getLocalizedMessage());
        }
    }
}
