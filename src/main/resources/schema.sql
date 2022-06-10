CREATE TABLE person

(

    id         UUID NOT NULL DEFAULT random_uuid() PRIMARY KEY,
    email      VARCHAR(255) NOT NULL,
    firstName  VARCHAR(100) NOT NULL,
    lastName   VARCHAR(100) NOT NULL,
    password   VARCHAR(255) NOT NULL

);