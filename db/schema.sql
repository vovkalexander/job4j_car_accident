create table accident (
id serial primary key,
name varchar(2000),
text varchar(2000),
address varchar(2000),
type_id int not null references accident_type(id)
);

create table accident_type (
id serial primary key,
name varchar(2000)
);

create table rule (
id serial primary key,
name varchar(2000)
);

create table accident_rule (
accident_id int references accident(id),
rule_id int references rule(id),
primary key (accident_id, rule_id)
);

INSERT INTO accident_type
(name)
values
('Две машины'),
('Машина и человек'),
('Машина и велосипед');

INSERT INTO  rule
(name)
values
('Статья. 1'),
('Статья. 2'),
('Статья. 3');


CREATE TABLE users (
id serial primary key,
username VARCHAR(50) NOT NULL unique,
password VARCHAR(100) NOT NULL,
enabled boolean default true,
authority_id int not null references authorities(id)
);


CREATE TABLE authorities (
id serial primary key,
authority VARCHAR(50) NOT NULL unique
);

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$v26rDzKNSc9R2e/S.CUmoeQpaX5ihVHbUqCPg3mIJrplO1QsbLHXu',
(select id from authorities where authority = 'ROLE_ADMIN'));