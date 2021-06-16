package com.DAO.DAOImpl;

import com.DAO.QuestionDAO;
import com.connection.ConnectionPool;
import com.entity.Test;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class QuestionDAOImpl implements QuestionDAO {

    public void changeQuestion(Test test) {

        String sqlQuery = "UPDATE Test SET question = ?, answer_1 = ?, answer_2 = ?, answer_3 = ?, answer_4 = ? WHERE id_Test= ? ";

        log.info("Connecting to the database to change the question");
        try (Connection connection = ConnectionPool.getConnection()) {
            log.info("Successful connection");
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setString(1, test.getQuestion());
                preparedStatement.setString(2, test.getAnswer1());
                preparedStatement.setString(3, test.getAnswer2());
                preparedStatement.setString(4, test.getAnswer3());
                preparedStatement.setString(5, test.getAnswer4());
                preparedStatement.setInt(6, test.getIdTest());
                preparedStatement.executeUpdate();
            } catch (SQLException exception) {
                log.error(exception.getLocalizedMessage());
            }
        } catch (SQLException exception) {
            log.error(exception.getLocalizedMessage());
        }
    }

    @Override
    public Integer getIdTestByQuestion(String question) {

        String sqlQuery = "SELECT id_Test FROM Test WHERE question = ?";
        int idTest = 0;

        log.info("Connecting to the database to get id test by question");
        try (Connection connection = ConnectionPool.getConnection()) {
            log.info("Successful connection");
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setString(1, question);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    idTest = resultSet.getInt("id_Test");
                }
            } catch (SQLException exception) {
                log.error(exception.getLocalizedMessage());
            }
        } catch (SQLException exception) {
            log.error(exception.getLocalizedMessage());
        }
        return idTest;
    }

    @Override
    public void deleteQuestion(Integer idTest) {

        String sqlQuery = "DELETE FROM Test WHERE id_Test = ?";

        log.info("Connecting to the database to delete question");
        try (Connection connection = ConnectionPool.getConnection()) {
            log.info("Successful connection");
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, idTest);
                preparedStatement.executeUpdate();
            } catch (SQLException exception) {
                log.error(exception.getLocalizedMessage());
            }
        } catch (SQLException exception) {
            log.error(exception.getLocalizedMessage());
        }
    }
}
