INSERT INTO `USER_ACCOUNT` ("ID" VARCHAR(255) NOT NULL,
                            "EMAIL" VARCHAR(255) NOT NULL,
                            "FIRSTNAME" VARCHAR(255),
                            "LASTNAME" VARCHAR(255),
                            "PASSWORD" VARCHAR(255),
                            "STATUS" INTEGER,
                            "ACCOUNT_ID" VARCHAR(255)
                           )
VALUES (
           ('7a5a1f83-6bf2-4895-a536-1689f8cf4eea', 'c.miossec@mail.com', 'Christophe', 'Miossec', '$2y$10$Ei4ASFiEKe6PoOUnVlfB5eLPa0IgpWbeS8OlRS5RMx.RwUxLH3Ycm', 0, '22d1995d-1bbb-480a-a59d-4aeb34d29788'),
           ('51686c1c-3f5e-4edb-8d8b-b809b4f2f1b9', 'a.sylvestre@mail.com', 'Anne', 'Sylvestre', '$2y$10$QZZ32o0e/IDHF/6PDN0oCOi72ApuRBoMwjYsH3iUF7sSP6ein667S', 0, 'b1f35fdd-df41-4cd7-bb04-50ca4cc83895'),
           ('7b874bca-a8eb-4ae9-b7c9-c888891ab38b', 'g.brassens@mail.com', 'Georges', 'Brassens', '$2y$10$QZZ32o0e/IDHF/6PDN0oCOi72ApuRBoMwjYsH3iUF7sSP6ein667S', 0, '0642173c-93e4-4fa0-80d6-5c69fd9c7da4')
)