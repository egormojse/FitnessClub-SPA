
CREATE TABLE person (
                        id int  PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
                        username VARCHAR(30) NOT NULL,
                        first_name VARCHAR(20),
                        last_name VARCHAR(20),
                        bd_date DATE,
                        email VARCHAR(100) UNIQUE NOT NULL,
                        role VARCHAR(100),
                        password VARCHAR(100) NOT NULL
);

create table trainers(
                         id int  PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY ,
                         username VARCHAR(255) NOT NULL,
                         name VARCHAR(255) NOT NULL,
                         password VARCHAR(100) NOT NULL,
                         email VARCHAR(100) UNIQUE NOT NULL,
                         specialization VARCHAR(255),
                         experience INT,
                         bio TEXT,
                         role VARCHAR(100)

);

CREATE TABLE spa_procedures (
                                id int  PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
                                name VARCHAR(255) NOT NULL,
                                description TEXT,
                                duration INT
);

CREATE TABLE spa_employees (
                               id int  PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
                               username VARCHAR(255) NOT NULL,
                               name VARCHAR(255) NOT NULL,
                               password VARCHAR(100) NOT NULL,
                               email VARCHAR(100) UNIQUE NOT NULL,
                               specialization VARCHAR(255),
                               experience INT,
                               bio TEXT,
                               role VARCHAR(100)
);

CREATE TABLE spa_booking (
                             id int  PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
                             user_id int REFERENCES person(id),
                             procedure_id int references spa_procedures(id),
                             date TIMESTAMP,
                             status varchar(50) default 'Зарегистрирован(а)'
);

CREATE TABLE workout_booking (
                                 id int  PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
                                 user_id int REFERENCES person(id),
                                 trainer_id int references trainers(id),
                                 date TIMESTAMP,
                                 status varchar(50) default 'Зарегистрирован(а)'
);

create table product(
                        id int primary key GENERATED BY DEFAULT AS IDENTITY ,
                        name varchar(50),
                        description TEXT,
                        price decimal(10,2),
                        category varchar(50),
                        stock int
);

create table "order"(
                        id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY ,
                        user_id int references person(id),
                        order_date TIMESTAMP,
                        total_price decimal(10,2),
                        status varchar(50) default 'Обрабатывается'
);

create table orderItem(
                          id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY ,
                          order_id int references "order"(id),
                          product_id int references product(id),
                          quantity int ,
                          price decimal(10,2)
);


create table membershipType(
                               id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY ,
                               type varchar(50),
                               gymVisits int,
                               spaVisits int,
                               duration int,
                               order_date TIMESTAMP
);

ALTER TABLE person
    ADD COLUMN gym_visits INT DEFAULT 0,
    ADD COLUMN spa_visits INT DEFAULT 0,
    ADD COLUMN membership_id INT REFERENCES membership_type(id);
