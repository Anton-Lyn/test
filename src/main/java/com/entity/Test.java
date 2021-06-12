package com.entity;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Test {
    Integer idTest;
    Integer idLanguage;
    Integer idSubject;
    String question;
    String answer1;
    String answer2;
    String answer3;
}
