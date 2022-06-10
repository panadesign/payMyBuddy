CREATE TABLE person

(

    id         IDENTITY PRIMARY KEY NOT NULL,
    email      VARCHAR(255) NOT NULL,
    firstName  VARCHAR(100) NOT NULL,
    lastName   VARCHAR(100) NOT NULL,
    password   VARCHAR(255) NOT NULL

);