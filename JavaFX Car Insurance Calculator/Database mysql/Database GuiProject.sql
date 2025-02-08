CREATE DATABASE `sql_workbench` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
use sql_workbench;

create table USER(
user_name varchar(100),
DOB date,
salary double,
gender varchar(20),
user_status varchar(30),
username varchar(20),
user_password varchar(20),
constraint user_pk PRIMARY KEY (username)
);

create table carInfo(
username varchar(20),
carType varchar(120),
price double,
coverage varchar(100),
Location varchar(100),
engineCapacity varchar(100),
discount varchar(100),
constraint user_fk FOREIGN KEY (username) references user (username)
);

create table admin(
admin_id varchar(15),
admin_pass varchar(30),
constraint adminID primary key (admin_id)
);

insert into admin values('Havitra', 'Havitra_5');
