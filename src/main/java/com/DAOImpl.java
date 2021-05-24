package com;

import com.connection.ConnectionPool;

import java.sql.*;

public class DAOImpl {

    public void create (Connection connection) {
        String s = "INSERT INTO User (name, login, password, role) VALUES (?,?,?,?);";
        try (PreparedStatement statement = connection.prepareStatement(s)) {
            statement.setString(1, "Lexa");
            statement.setString(2, "lexa@gmail.com");
            statement.setString(3, "passwordlexa");
            statement.setString(4, "user");
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void showName (Connection connection) {
        String s = "SELECT login FROM User;";
        try (Statement statement = connection.createStatement()){
            ResultSet r = statement.executeQuery(s);
            while (r.next()){
                String n = r.getString("login");
                System.out.println(n);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DAOImpl q = new DAOImpl();
        try(Connection connection = ConnectionPool.getConnection();){
//            q.create(connection);
            q.showName(connection);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
