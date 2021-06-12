package com;

import com.DAO.DAOImpl.TestDAOImpl;
import com.connection.ConnectionPool;
import com.entity.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassForTestMethod {

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

    public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
//        digest.update(input.getBytes());
        byte[] hash = digest.digest(input.getBytes());
        int[] hash2 = new int[hash.length];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hash2.length; i++) {
            String hex = Integer.toHexString(0xFF & hash[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString().toUpperCase();
    }

    public static String MD5(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] bytes = messageDigest.digest(password.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(String.format("%02X", b));
        }
        return String.valueOf(stringBuilder);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
//        DAOImpl q = new DAOImpl();
//        try(Connection connection = ConnectionPool.getConnection();){
//            q.create(connection);
////            q.showName(connection);
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//        }


        TestDAOImpl testDAO = new TestDAOImpl();

        List<Test> testList = testDAO.giveAllTests(1);

        System.out.println(testList);

        Map<String, String> correctAnswer = new HashMap<>();
        correctAnswer.put("Посчитайте 2+2", "3");
        correctAnswer.put("Посчитайте 3+3", "1");
        correctAnswer.put("Посчитайте 5-2", "4");
        correctAnswer.put("История", "Падре");

//        for (Map.Entry<String, String> entry : correctAnswer.entrySet()) {
//            String key = entry.getKey();
//            String tab = entry.getValue();
//            System.out.println(key + " " + tab);
//        }

        int df = testDAO.returnResultTest(correctAnswer);
        int q = correctAnswer.size();
        System.out.println("ОЦенка" + df + "/" + correctAnswer.size());
        System.out.println(((df / q) * 100) + "%");

//        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        java.util.Date date = new Date();
//        String dataCheck = formater.format(date);
//        System.out.println(dataCheck);

//        Integer language = Integer.valueOf("1");
//        System.out.println(language);

//
//        String s = "admin";
//        if (s.equals("admin")) {
//            System.out.println("adminPage");
//        } else System.out.println("erre page");
//
//        System.out.println(hash("anton123", "MD5"));
//        System.out.println("----------------------------------");
//        System.out.println(MD5("anton123"));
//        System.out.println("----------------------------------");
//        System.out.println(hash("anton123", "SHA-256"));


//912EC803B2CE49E4A541068D495AB570
//                ----------------------------------
//912EC803B2CE49E4A541068D495AB570
//                ----------------------------------
//F0E4C2F76C58916EC258F246851BEA091D14D4247A2FC3E18694461B1816E13B


    }

}
