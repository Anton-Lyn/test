package com.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Time;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subject {
    Integer idSubject;
    Integer idLangSubject;
    String nameSubject;
    Integer complexitySubject;
    String dateCreatedSubject;
    String dateUpdateSubject;
    Time timeTest;
}
