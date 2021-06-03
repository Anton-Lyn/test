package com.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subject {
    Integer id;
    Integer idLang;
    String name;
    Integer complexity;
    String created_At;
    String update_At;
}
