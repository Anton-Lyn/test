package com.entity;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Answer {
    Integer idAnswer;
    Integer idTestInTableAnswer;
    String answer;
}
