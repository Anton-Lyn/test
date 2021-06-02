package com.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    Integer id;
    String name;
    String login;
    String password;
    String role;
    Integer preferredLang;
    Boolean blocked;
    String createdAt;
    String updateAT;

}
