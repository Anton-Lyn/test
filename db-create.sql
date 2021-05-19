CREATE first_base;

USE first_base;

CREATE TABLE 'User' (
id_user INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(20) NOT NULL,
login, VARCHAR NOT NULL,
password, VARCHAR NOT NULL,
role, VARCHAR(5) NOT NULL);

CREATE TABLE 'Subject' (
id_subject INT AUTO_INCREMENT PRIMARY KEY,
name_subject VARCHAR NOT NULL,
complexity VARCHAR NOT NULL,
time_for_test TIME NOT NULL);

CREATE TABLE 'Test'(
id_test INT AUTO_INCREMENT PRIMARY KEY,
id_subject INT NOT NULL,
question VARCHAR NOT NULL,
answer_1 VARCHAR NOT NULL,
answer_2 VARCHAR NOT NULL,
answer_3 VARCHAR NOT NULL,
true_answer VARCHAR NOT NULL,
FOREIGN KEY (id_subject) REFERENCES Subject (id_subject));

CREATE TABLE 'Resutls' (
id_user INT,
id_subject INT,
score INT,
FOREIGN KEY (id_user) REFERENCES User(id_user),
FOREIGN KEY (id_subject) REFERENCES Subject (id_subject));
