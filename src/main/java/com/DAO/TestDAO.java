package com.DAO;

import com.entity.Results;
import com.entity.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface TestDAO {
    void addFrequency (Integer idSubject);
    List<Test> giveAllTests(Integer idSubject);
    int returnResultTest(Map<String, String> answersUser);
    ArrayList<String> getAllQuestions ();
    void setResultUserInDB (Integer idUser, Integer idSubject, int score);
    String getData();
    void deleteSubject (Integer idSubject);
    void deleteResults (Integer idSubject);
    void deleteTests(Integer idSubject);
    void addTest (Test test);
    List<Results> getResults(Integer idUser);
    void deleteResultsByUser(Integer idUser);
}
