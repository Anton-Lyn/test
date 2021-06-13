package com.service;

import com.entity.Test;

import java.util.List;

public interface GetTestsService {
    List<Test> getTestsList (Integer idSubject);
}
