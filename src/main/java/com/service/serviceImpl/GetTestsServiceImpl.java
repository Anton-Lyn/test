package com.service.serviceImpl;

import com.DAO.DAOImpl.TestDAOImpl;
import com.entity.Test;
import com.service.GetTestsService;

import java.util.List;

public class GetTestsServiceImpl implements GetTestsService {

    TestDAOImpl testDAO = new TestDAOImpl();
    public List<Test> getTestsList (Integer idSubject) {
        return testDAO.giveAllTests(idSubject);
    }
}
