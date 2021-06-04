package com.service.serviceImpl;

import com.DAO.DAOImpl.SubjectDAOImpl;
import com.entity.Subject;
import com.service.SubjectService;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {

    public List<Subject> getSubjectList() {
        return SubjectDAOImpl.getInstance().getAllSubjects();
    }
}
