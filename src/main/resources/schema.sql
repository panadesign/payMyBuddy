CREATE TABLE person
(
    id         UUID         NOT NULL DEFAULT random_uuid() PRIMARY KEY,
    id_account UUID         NOT NULL DEFAULT random_uuid() UNIQUE,
    email      VARCHAR(255) NOT NULL UNIQUE,
    firstName  VARCHAR(100) NOT NULL,
    lastName   VARCHAR(100) NOT NULL,
    password   VARCHAR(255) NOT NULL
);

CREATE TABLE account
(
    id      UUID NOT NULL DEFAULT random_uuid() PRIMARY KEY,
    balance FLOAT
);