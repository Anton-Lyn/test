package com.DAO;

import com.entity.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface TestDAO {
    List<Test> giveAllTests(Integer idSubject);
    int returnResultTest(Map<String, String> answersUser);
    ArrayList<String> getAllQuestions ();
    void setResultUserInDB (Integer idUser, Integer idSubject, int score);
    String getData();
}
