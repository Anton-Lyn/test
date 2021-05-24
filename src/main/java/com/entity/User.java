package com.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.time.OffsetDateTime;

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
    OffsetDateTime createdAt;
    OffsetDateTime updateAT;

}
