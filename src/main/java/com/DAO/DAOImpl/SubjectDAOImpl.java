package com.DAO.DAOImpl;

import com.DAO.SubjectDAO;
import com.connection.ConnectionPool;
import com.entity.Subject;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SubjectDAOImpl implements SubjectDAO {

    private static SubjectDAOImpl subjectDAO;

    private SubjectDAOImpl() {

    }

    public static SubjectDAOImpl getInstance() {
        if (subjectDAO == null) {
            subjectDAO = new SubjectDAOImpl();
        }
        return subjectDAO;
    }


    @Override
    public List<Subject> getAllSubjects() {

        List<Subject> allSubjects = new ArrayList<>();
        String sql = "SELECT * FROM Subject";

        try (Connection connection = ConnectionPool.getConnection()) {
            log.info("Try to connection to DB to find Subject");
            try (Statement statement = connection.createStatement()) {

                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Subject subject = new Subject();
                    subject.setId(resultSet.getInt("id_subject"));
                    subject.setIdLang(resultSet.getInt("lang_Id"));
                    subject.setName(resultSet.getString("name_subject"));
                    subject.setComplexity(resultSet.getInt("complexity"));
                    subject.setCreatedData(resultSet.getString("created_At"));
                    subject.setUpdateData(resultSet.getString("update_At"));
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
}
