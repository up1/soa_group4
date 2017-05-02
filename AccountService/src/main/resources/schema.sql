DROP TABLE IF EXISTS SEARCHING;
DROP TABLE IF EXISTS MATCHING;
DROP TABLE IF EXISTS ACCOUNT;


CREATE TABLE ACCOUNT
(
    account_id int(11) NOT NULL AUTO_INCREMENT,
    account_uid varchar(100) NOT NULL,
    account_email varchar(100) NOT NULL,
    account_username varchar(100) NOT NULL,
    account_password varchar(100) NOT NULL,
    account_name varchar(100) DEFAULT ' ',
    account_lastname varchar(100) DEFAULT ' ',
    account_birthday Long,
    account_sex varchar(100) DEFAULT 'Male',
    account_sexual_taste varchar(100) DEFAULT 'Normal',
    account_latitude float(11) DEFAULT 13.784945,
    account_longtitude float(11) DEFAULT 100.785200,
    account_location varchar(100) DEFAULT 'Please Enter Location',
    account_descriptions varchar(1000) DEFAULT ' ',
    account_new_user int(1) DEFAULT 0,
    enable TINYINT NOT NULL DEFAULT 1,
    role VARCHAR(20) NOT NULL DEFAULT 'user',
    PRIMARY KEY (account_id)

);

CREATE TABLE SEARCHING
(
    search_id int(11) NOT NULL AUTO_INCREMENT,
    search_latitude float(11) DEFAULT 13.784945,
    search_longtitude float(11) DEFAULT 100.785200,
    search_birthday Long,
    search_sex varchar(100) DEFAULT 'Female',
    search_sexual_taste varchar(100) DEFAULT 'Normal',
    search_min_age int(3) DEFAULT 18,
    search_max_age int(3) DEFAULT 20,
    search_distance float(11) DEFAULT 100.0,
    account_id int(11),
    PRIMARY KEY (search_id),
    FOREIGN KEY (account_id) REFERENCES ACCOUNT(account_id)
);

CREATE TABLE MATCHING
(
    matching_id int(11) NOT NULL AUTO_INCREMENT,
    matching_account_do int(11),
    matching_account_done int(11),
    matching_status int(1),
    PRIMARY KEY (matching_id)
);