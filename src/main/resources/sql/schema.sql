drop database if exists hardware_managment_system;
create database hardware_managment_system;

use hardware_managment_system;

CREATE TABLE customer(
                         id VARCHAR(6) PRIMARY KEY,
                         name VARCHAR(30),
                         address VARCHAR(30),
                         email varchar(30),
                        contactnumber INT
);

CREATE TABLE orders(
                       id VARCHAR(6) PRIMARY KEY,
                       date DATE,
                       customerId VARCHAR(6) NOT NULL,
                       CONSTRAINT FOREIGN KEY(customerId) REFERENCES customer(id) on Delete Cascade on Update Cascade
);

CREATE TABLE item(
                     code VARCHAR(6) PRIMARY KEY,
                     description VARCHAR(50),
                     unitPrice DECIMAL(8,2),
                     qtyOnHand INT(5)

);

CREATE TABLE order_detail(
                            orderId VARCHAR(6),
                            itemCode VARCHAR(6),
                            qty INT(11),
                            unitPrice DECIMAL(8,2),
                            CONSTRAINT FOREIGN KEY (orderId) REFERENCES orders(id) on Delete Cascade on Update Cascade,
                            CONSTRAINT FOREIGN KEY (itemCode) REFERENCES item(code) on Delete Cascade on Update Cascade
);

 create table Supplier(
SID VARCHAR (10) primary key,
SName varchar (20),
contactnumber int (15),
imeladdress varchar (35)
)


create table Supplierdetails(
SID VARCHAR (10),
 code VARCHAR(6),
catagory varchar (20),
description varchar (29),
 foreign key (SID) references Supplier (SID),
foreign key (code) references item (code)
);


create table payment(
p_code varchar (20)primary key,
amount varchar (29),
pdate DATE
);


CREATE TABLE Transport (
T_id varchar (15) primary key ,
T_QTY varchar (20)
);

create table Transportdetails(
T_area varchar (17),
t_time varchar (10),
T_id varchar (15),
id VARCHAR(6),
 foreign key (T_id) references Transport(T_id),
foreign key (id) references orders(id)
);

create table Vehical (
VID VARCHAR (19) PRIMARY KEY ,
Vmodel varchar (25)

);


create table Employee (
eid varchar (20) primary key ,
contactnumber int (20),
address varchar (28),
name varchar (25)
);


create table Mechine (
code varchar (14) primary key ,
Model varchar (38)

);
create table admin(
username varchar (7) primary key,
email varchar(50),
password varchar (12)
);

insert into admin values ('Dinu', 'dinu@gmail.com', '1234');
