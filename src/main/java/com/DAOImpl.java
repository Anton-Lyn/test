package com;

import com.connection.ConnectionPool;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DAOImpl {

    public void create(Connection connection) {

//        try {
//            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date date = new Date();
//            String s = formater.format(date);
//            java.sql.Timestamp dateq = new java.sql.Timestamp(new java.util.Date().getTime());
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }

        String s = "INSERT INTO User (name, login, password, role, created_At) VALUES (?,?,?,?,?);";
        try (PreparedStatement statement = connection.prepareStatement(s)) {

            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String s1 = formater.format(date);
//            java.sql.Date d = new java.sql.Date(formater.parse(String.valueOf(date)).getTime());

            statement.setString(1, "test");
            statement.setString(2, "test");
            statement.setString(3, "test");
            statement.setString(4, "test");
            statement.setString(5, s1);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void showName(Connection connection) {
        String s = "SELECT login FROM User;";
        try (Statement statement = connection.createStatement()) {
            ResultSet r = statement.executeQuery(s);
            while (r.next()) {
                String n = r.getString("login");
                System.out.println(n);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        DAOImpl q = new DAOImpl();
//        try(Connection connection = ConnectionPool.getConnection();){
//            q.create(connection);
////            q.showName(connection);
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//        }


//        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        java.util.Date date = new Date();
//        String dataCheck = formater.format(date);
//        System.out.println(dataCheck);

        Integer language = Integer.valueOf("1");
        System.out.println(language);

    }

}
