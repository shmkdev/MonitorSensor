--liquibase formatted sql

--changeset ramanshmk:1
INSERT INTO types (name)
VALUES ('Pressure'),
       ('Voltage'),
       ('Temperature'),
       ('Humidity');

--changeset ramanshmk:2
INSERT INTO units (name)
VALUES ('bar'),
       ('voltage'),
       ('°С'),
       ('%');

--changeset ramanshmk:3
INSERT INTO users (username, password, firstname, lastname, role)
VALUES ('admin@yandex.ru', '{noop}12345', 'admin', 'a', 'ADMINISTRATOR'),
       ('user@yandex.ru', '{noop}12345', 'user', 'u', 'VIEWER')