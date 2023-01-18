create schema if not exists company;

use company;

create table if not exists companies (
    id varchar(100) not null,
    name varchar(100) not null, 
    short_name varchar(20) not null, 
    alias varchar(20) not null,
    created_at timestamp(6) not null, 
    updated_at timestamp(6) default null,
    primary key (id) 
);

create user if not exists company_user_service identified by '123';

grant all on company.companies to company_user_service;