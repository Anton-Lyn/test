package com.DAO;

import com.entity.Subject;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface SubjectDAO {
    List<Subject> getAllSubjects();
    Time getTimeTest(Integer idSubject);
}
