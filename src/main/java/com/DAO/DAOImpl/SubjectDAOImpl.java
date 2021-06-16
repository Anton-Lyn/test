package com.DAO.DAOImpl;

import com.DAO.SubjectDAO;
import com.connection.ConnectionPool;
import com.entity.Subject;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class SubjectDAOImpl implements SubjectDAO {

    public String findQuery (Integer idSort) {
        String sqlQuery = null;
        switch (idSort) {
            case 1 : sqlQuery = "SELECT * FROM Subject ORDER BY name_subject";
            break;
            case 2 : sqlQuery = "SELECT * FROM Subject ORDER BY complexity";
            break;
            case 3 : sqlQuery = "SELECT * FROM Subject ORDER BY complexity DESC";
            break;
            case 4 : sqlQuery = "SELECT * FROM Subject ORDER BY frequency";
            break;
            case 5 : sqlQuery = "SELECT * FROM Subject ORDER BY frequency DESC";
            break;
        }
        return sqlQuery;
    }

    @Override
    public List<Subject> getAllSubjects(Integer idSortBy) {

        List<Subject> allSubjects = new ArrayList<>();
        String sqlQuery = findQuery(idSortBy);

        log.info("Connecting to the database to get all subjects");
        try (Connection connection = ConnectionPool.getConnection()) {
            log.info("Successful connection");
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
                    subject.setFrequency(resultSet.getInt("frequency"));
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

        log.info("Connecting to the database to get the test time");
        try (Connection connection = ConnectionPool.getConnection()) {
            log.info("Successful connection");
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

    public void addingANewSubject(String nameSub, String complexityNewSub, Time timeNewSub) {

        String sqlQuery = "INSERT INTO Subject VALUES (DEFAULT,?,?,?,?,?,?,?)";
        String valueDate = getData();

        log.info("Connecting to the database to adding a new subject");
        try (Connection connection = ConnectionPool.getConnection()) {
            log.info("Successful connection");
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, 1);
                preparedStatement.setString(2, nameSub);
                preparedStatement.setString(3, complexityNewSub);
                preparedStatement.setString(4, valueDate);
                preparedStatement.setString(5, valueDate);
                preparedStatement.setTime(6, timeNewSub);
                preparedStatement.setInt(7, 0);
                preparedStatement.executeUpdate();
            } catch (SQLException exception) {
                log.error(exception.getLocalizedMessage());
            }
        } catch (SQLException exception) {
            log.error(exception.getLocalizedMessage());
        }
    }

    public String getData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = new Date();
        return simpleDateFormat.format(date);
    }
}
