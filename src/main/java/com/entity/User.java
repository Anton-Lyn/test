package com.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    Integer userId;
    String userName;
    String loginUser;
    String userPassword;
    String userRole;
    Integer userPreferredLang;
    Boolean userStatus;
    String dateCreatedUser;
    String dateUpdateUser;
}
