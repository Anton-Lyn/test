package com;

import java.sql.*;

public class Query {

    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL_NEW_TABLE = "jdbc:mysql://localhost:3306/first_base";
    public static final String DB_USER_NAME = "root";
    public static final String DB_USER_PASSWORD = "password";


    private Connection connection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL_NEW_TABLE, DB_USER_NAME, DB_USER_PASSWORD);
            System.out.println("Connected!");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            System.out.println("Error connect");
        }
        return connection;
    }

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
        Query q = new Query();
        final Connection connection = q.connection();
        q.create(connection);
        q.showName(connection);
    }
}
