    -- liquibase formatted sql

    -- changeset Ezechiel:1
    CREATE TABLE EMPLOYEE (
        ID INT,
        FIRST_NAME VARCHAR (50),
        LAST_NAME VARCHAR (50),
        USER_NAME VARCHAR (100),
        PRIMARY KEY (ID)
    );

    CREATE TABLE TASK (
        ID INT,
        TITLE VARCHAR(200),
        DESCRIPTION TEXT,
        INITIAL_TIME numeric,
        REMAINING_TIMI numeric,
        PRIMARY KEY (ID)
    );

    CREATE TABLE EMPLOYEE_TASK (
        EMPLOYEE_ID INT,
        TASK_ID INT,
        PRIMARY KEY (EMPLOYEE_ID, TASK_ID),
        FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEE(ID),
        FOREIGN KEY (TASK_ID) REFERENCES TASK(ID)
    );