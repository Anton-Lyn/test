package com.service.serviceImpl;

import com.DAO.DAOImpl.SubjectDAOImpl;
import com.entity.Subject;
import com.service.SubjectService;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    SubjectDAOImpl subjectDAO = new SubjectDAOImpl();

    public List<Subject> getSubjectList() {
        return subjectDAO.getAllSubjects();
    }
}
