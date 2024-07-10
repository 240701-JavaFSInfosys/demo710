DROP TABLE IF EXISTS PERSON;
CREATE TABLE PERSON (id integer primary key, name varchar(255), active boolean, cell long);
INSERT INTO PERSON (id, name, active, cell) values (1, 'john doe', true, 1111111111);
INSERT INTO PERSON (id, name, active, cell) values (2, 'jane doe', true, 2222222222);