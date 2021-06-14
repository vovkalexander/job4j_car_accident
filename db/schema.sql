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