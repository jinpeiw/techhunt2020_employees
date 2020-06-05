drop table if exists govtech_employees;

create table govtech_employees (id nvarchar(255) not null primary key, login nvarchar(255) not null unique, name nvarchar(255) not null, salary double not null);

insert into govtech_employees (id, login, name, salary) values ('t0001','test','test','1000.55');