package com.DAO.DAOImpl;

import com.connection.ConnectionPool;
import com.entity.Test;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class TestDAOImpl {

    public List<Test> giveAllTests(Integer idSubject) {

        String sqlQuery = "SELECT question, answer_1, answer_2, answer_3 FROM Test WHERE id_Subject = ?";
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
                    allTest.add(test);
                }
            }
        } catch (SQLException exception) {
            log.error(exception.getLocalizedMessage());
        }
        return allTest;
    }

    public int returnResultTest(Map<String, String> answersUser) {

        String sqlQuery = "SELECT answer_3 FROM Test WHERE question= ?";
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
                        timeValue = resultSet.getString("answer_3");
                        if (value.equals(timeValue)) {
                            result++;
                        }
                    }
                }
                connection.commit();
            } catch (SQLException exception) {
                connection.rollback();
                log.error(exception.getLocalizedMessage());
            }
        } catch (SQLException exception) {
            log.error(exception.getLocalizedMessage());
        }
        return result;
    }
}
