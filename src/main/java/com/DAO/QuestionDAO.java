package com.DAO;

import com.entity.Test;

public interface QuestionDAO {
    void changeQuestion (Test test);
    Integer getIdTestByQuestion (String question);
    void deleteQuestion (Integer isTest);
}
