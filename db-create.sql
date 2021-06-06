CREATE
    DATABASE `first_base`;

USE
    `first_base`;

CREATE TABLE `Language`
(
    `id_Language`      int         NOT NULL,
    `id_Name_Language` varchar(45) NOT NULL,
    PRIMARY KEY (`id_Language`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `Subject`
(
    `id_subject`   int         NOT NULL AUTO_INCREMENT,
    `lang_Id`      int         NOT NULL,
    `name_subject` varchar(45) NOT NULL,
    `complexity`   int         NOT NULL,
    `created_At`   datetime DEFAULT NULL,
    `update_At`    datetime DEFAULT NULL,
    PRIMARY KEY (`id_subject`),
    KEY `FK_Subject_idx` (`lang_Id`),
    CONSTRAINT `FK_Subject` FOREIGN KEY (`lang_Id`) REFERENCES `Language` (`id_Language`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `Test`
(
    `id_Test`    int         NOT NULL AUTO_INCREMENT,
    `lang_Id`    int         NOT NULL,
    `id_Subject` int         NOT NULL,
    `question`   varchar(45) NOT NULL,
    `answer_1`   varchar(45) NOT NULL,
    `answer_2`   varchar(45) NOT NULL,
    `answer_3`   varchar(45) NOT NULL,
    PRIMARY KEY (`id_Test`),
    KEY `id_Subject_idx` (`id_Subject`),
    KEY `FK_Test_idx` (`lang_Id`),
    CONSTRAINT `FK_Test` FOREIGN KEY (`lang_Id`) REFERENCES `Language` (`id_Language`),
    CONSTRAINT `id_Subject` FOREIGN KEY (`id_Subject`) REFERENCES `Subject` (`id_subject`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `Answer`
(
    `id_Answer` int NOT NULL,
    `id_Test`   int NOT NULL,
    `answer`    varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id_Answer`),
    KEY `FK_answer_idx` (`id_Test`),
    CONSTRAINT `FK_answer` FOREIGN KEY (`id_Test`) REFERENCES `Test` (`id_Test`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `User`
(
    `id_User`        int         NOT NULL AUTO_INCREMENT,
    `name`           varchar(45) NOT NULL,
    `login`          varchar(45) NOT NULL,
    `password`       varchar(45) NOT NULL,
    `role`           varchar(45) NOT NULL,
    `preffered_Lang` int      DEFAULT NULL,
    `blocked`        tinyint  DEFAULT NULL,
    `created_At`     datetime DEFAULT NULL,
    `update_At`      datetime DEFAULT NULL,
    PRIMARY KEY (`id_User`),
    UNIQUE KEY `login_UNIQUE` (`login`),
    KEY `FK_User_idx` (`preffered_Lang`),
    CONSTRAINT `FK_User` FOREIGN KEY (`preffered_Lang`) REFERENCES `Language` (`id_Language`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `Results`
(
    `id_Result`  varchar(45) NOT NULL,
    `id_user`    int DEFAULT NULL,
    `id_subject` int DEFAULT NULL,
    `score`      int DEFAULT NULL,
    PRIMARY KEY (`id_Result`),
    KEY `subject` (`id_subject`),
    KEY `user` (`id_user`),
    CONSTRAINT `subject` FOREIGN KEY (`id_subject`) REFERENCES `Subject` (`id_subject`),
    CONSTRAINT `user` FOREIGN KEY (`id_user`) REFERENCES `User` (`id_User`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;