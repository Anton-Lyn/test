package com.DAO.DAOImpl;

import com.DAO.TestDAO;
import com.connection.ConnectionPool;
import com.entity.Results;
import com.entity.Test;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
public class TestDAOImpl implements TestDAO {

    @Override
    public List<Test> giveAllTests(Integer idSubject) {

        String sqlQuery = "SELECT question, answer_1, answer_2, answer_3, answer_4 FROM Test WHERE id_Subject = ?";
        List<Test> allTest = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, idSubject);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Test test = new Test();
                    test.setQuestion(resultSet.getString("question"));
                    test.setAnswer1(resultSet.getString("answer_1"));
                    test.setAnswer2(resultSet.getString("answer_2"));
                    test.setAnswer3(resultSet.getString("answer_3"));
                    test.setAnswer4(resultSet.getString("answer_4"));
                    allTest.add(test);
                }
            }
        } catch (SQLException exception) {
            log.error(exception.getLocalizedMessage());
        }
        return allTest;
    }

    @Override
    public int returnResultTest(Map<String, String> answersUser) {

        String sqlQuery = "SELECT answer_4 FROM Test WHERE question= ?";
        int result = 0;
        String key;
        String value;
        String timeValue;


        try (Connection connection = ConnectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                connection.setAutoCommit(false);
                for (Map.Entry<String, String> entry : answersUser.entrySet()) {
                    key = entry.getKey();
                    value = entry.getValue();

                    preparedStatement.setString(1, key);

                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        timeValue = resultSet.getString("answer_4");
                        if (value.equals(timeValue)) {
                            result++;
                        }
                    }
                }
                connection.commit();
            } catch (SQLException exception) {
                log.error(exception.getLocalizedMessage());
                connection.rollback();
            }
        } catch (SQLException exception) {
            log.error(exception.getLocalizedMessage());
        }
        return result;
    }

    public ArrayList<String> getAllQuestions() {

        ArrayList<String> allQuestions = new ArrayList<>();
        String sqlQuery = "SELECT question FROM Test;";


        try (Connection connection = ConnectionPool.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sqlQuery);
                while (resultSet.next()) {
                    allQuestions.add(resultSet.getString("question"));
                }
            } catch (SQLException exception) {
                log.error(exception.getLocalizedMessage());
                connection.rollback();
            }
        } catch (SQLException exception) {
            log.error(exception.getLocalizedMessage());
        }
        return allQuestions;
    }

    public void setResultUserInDB(Integer idUser, Integer idSubject, int score) {

        String sqlQuery = "INSERT INTO Results (id_Result, id_user, id_subject, score, date_Test) VALUES (DEFAULT, ?, ?, ?, ?);";
        String date = getData();

        try (Connection connection = ConnectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setString(1, String.valueOf(idUser));
                preparedStatement.setString(2, String.valueOf(idSubject));
                preparedStatement.setInt(3, score);
                preparedStatement.setString(4, date);
                preparedStatement.executeUpdate();
            } catch (SQLException exception) {
                log.error(exception.getLocalizedMessage());
            }
        } catch (SQLException exception) {
            log.error(exception.getLocalizedMessage());
        }
    }

    public List<Results> getResults (Integer idUser) {

        List<Results> results = new ArrayList<>();
        String sqlQuery = "SELECT name_subject,score,date_Test  FROM Subject, Results WHERE Subject.id_subject=Results.id_subject AND Results.id_user=?";

        try (Connection connection = ConnectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, idUser);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Results result = new Results();
                    result.setNameSubject(resultSet.getString("name_subject"));
                    result.setScore(resultSet.getInt("score"));
                    result.setDateTest(resultSet.getString("date_Test"));
                    results.add(result);
                }
            } catch (SQLException exception) {
                log.error(exception.getLocalizedMessage());
            }
        } catch (SQLException exception) {
            log.error(exception.getLocalizedMessage());
        }
        return results;
    }

    public String getData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = new Date();
        return simpleDateFormat.format(date);
    }
}