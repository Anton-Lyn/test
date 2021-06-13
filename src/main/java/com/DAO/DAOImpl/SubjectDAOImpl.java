package com.DAO.DAOImpl;

import com.DAO.SubjectDAO;
import com.connection.ConnectionPool;
import com.entity.Subject;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class SubjectDAOImpl implements SubjectDAO {

    @Override
    public List<Subject> getAllSubjects() {

        List<Subject> allSubjects = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Subject";

        try (Connection connection = ConnectionPool.getConnection()) {
            log.info("Try to connection to DB to find Subject");
            try (Statement statement = connection.createStatement()) {

                ResultSet resultSet = statement.executeQuery(sqlQuery);
                while (resultSet.next()) {
                    Subject subject = new Subject();
                    subject.setIdSubject(resultSet.getInt("id_subject"));
                    subject.setIdLangSubject(resultSet.getInt("lang_Id"));
                    subject.setNameSubject(resultSet.getString("name_subject"));
                    subject.setComplexitySubject(resultSet.getInt("complexity"));
                    subject.setDateCreatedSubject(resultSet.getString("created_At"));
                    subject.setDateUpdateSubject(resultSet.getString("update_At"));
                    subject.setTimeTest(resultSet.getTime("time_To_Test"));
                    allSubjects.add(subject);
                }
            } catch (SQLException exception) {
                log.error(exception.getLocalizedMessage());
            }
        } catch (SQLException exception) {
            log.error(exception.getLocalizedMessage());
        }
        return allSubjects;
    }

    public Time getTimeTest(Integer idSubject) {

        String sqlQuery = "SELECT time_To_Test FROM Subject WHERE id_subject=?";
        Time timeTest = null;

        try (Connection connection = ConnectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, idSubject);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    timeTest = resultSet.getTime("time_To_Test");
                }
            } catch (SQLException exception) {
                log.error(exception.getLocalizedMessage());
            }
        } catch (SQLException exception) {
            log.error(exception.getLocalizedMessage());
        }
        return timeTest;
    }
}
