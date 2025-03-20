--liquibase formatted sql

--changeset ramanshmk:1
CREATE TABLE IF NOT EXISTS types
(
    id   UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(16) UNIQUE NOT NULL
    );

--changeset ramanshmk:2
CREATE TABLE IF NOT EXISTS units
(
    id   UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(16) UNIQUE NOT NULL
    );

--changeset ramanshmk:3
CREATE TABLE IF NOT EXISTS sensors
(
    id          UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name        VARCHAR(30) UNIQUE       NOT NULL,
    model       VARCHAR(15)              NOT NULL,
    range_from  INT                      NOT NULL,
    range_to    INT                      NOT NULL,
    type_id     UUID REFERENCES types (id) NOT NULL,
    unit_id     UUID REFERENCES units (id),
    location    VARCHAR(40),
    description VARCHAR(200),
    local_date  DATE                     NOT NULL
    );

--changeset ramanshmk:4
CREATE TABLE IF NOT EXISTS users
(
    id        UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    username  VARCHAR(32) UNIQUE NOT NULL,
    password  VARCHAR(128)       NOT NULL,
    firstname VARCHAR(32)        NOT NULL,
    lastname  VARCHAR(32)        NOT NULL,
    role      VARCHAR(16)        NOT NULL
    );