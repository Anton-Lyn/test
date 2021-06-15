package com.DAO;

import com.entity.Subject;

import java.sql.Time;
import java.util.List;

public interface SubjectDAO {
    List<Subject> getAllSubjects(Integer idSortBy);
    Time getTimeTest(Integer idSubject);
    void addNewSubject (String nameSub, String complexityNewSub, Time timeNewSub);
}
