package com.service;

import com.entity.Subject;
import java.util.List;

public interface SubjectService {
    List<Subject> getSubjectList(Integer idSort);
}
