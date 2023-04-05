drop database assessment;

create database if not exists assessment;

use assessment;

create table if not exists user(
    user_id varchar(8) not null,
    username varchar(50) not null,
    _name varchar(50),
    constraint user_pk primary key(user_id)
);

create table if not exists task(
    task_id int not null auto_increment,
    user_id varchar(8) not null,
    description varchar(255) not null,
    priority int not null,
    due_date date not null,
    constraint task_pk primary key(task_id),
    constraint task_fk foreign key(user_id) references user(user_id)
);

