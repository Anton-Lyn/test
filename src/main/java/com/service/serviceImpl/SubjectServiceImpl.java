package com.service.serviceImpl;

import com.DAO.DAOImpl.SubjectDAOImpl;
import com.service.SubjectService;

public class SubjectServiceImpl implements SubjectService {
    public Object getSubjectList() {
        SubjectDAOImpl subjectDAO = new SubjectDAOImpl();
        return subjectDAO.getAllSubjects();
    }
}
