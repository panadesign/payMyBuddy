INSERT INTO ACCOUNT(id, balance)
VALUES ('a6a5889e-3908-486e-813a-252185c88480', 25.0),
       ('c6692cdc-c59e-436a-949d-6869805e3c7b', 0.0),
       ('0642173c-93e4-4fa0-80d6-5c69fd9c7da4', 53.5);

INSERT INTO USER_ACCOUNT (id, email, firstname, lastname, password, status, account_id)
VALUES ('7a5a1f83-6bf2-4895-a536-1689f8cf4eea', 'c.miossec@mail.com', 'Christophe', 'Miossec',
        '$2y$10$Ei4ASFiEKe6PoOUnVlfB5eLPa0IgpWbeS8OlRS5RMx.RwUxLH3Ycm', 0,
        'a6a5889e-3908-486e-813a-252185c88480'),
       ('51686c1c-3f5e-4edb-8d8b-b809b4f2f1b9', 'a.sylvestre@mail.com', 'Anne', 'Sylvestre',
        '$2y$10$QZZ32o0e/IDHF/6PDN0oCOi72ApuRBoMwjYsH3iUF7sSP6ein667S', 0,
        'c6692cdc-c59e-436a-949d-6869805e3c7b'),
       ('7b874bca-a8eb-4ae9-b7c9-c888891ab38b', 'g.brassens@mail.com', 'Georges', 'Brassens',
        '$2y$10$QZZ32o0e/IDHF/6PDN0oCOi72ApuRBoMwjYsH3iUF7sSP6ein667S', 0,
        '0642173c-93e4-4fa0-80d6-5c69fd9c7da4');

INSERT INTO user_account_contact_list (user_account_id, contact_list_id)
VALUES ('7a5a1f83-6bf2-4895-a536-1689f8cf4eea', '51686c1c-3f5e-4edb-8d8b-b809b4f2f1b9');
