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
    account_name varchar(100),
    account_lastname varchar(100),
    account_age int(3),
    account_sex varchar(100),
    account_sexual_taste varchar(100),
    account_latitude float(11),
    account_longtitude float(11),
    account_location varchar(100),
    account_descriptions varchar(1000),
    PRIMARY KEY (account_id)
);

CREATE TABLE SEARCHING
(
    search_id int(11) NOT NULL AUTO_INCREMENT,
    search_latitude float(11),
    search_longtitude float(11),
    search_age int(3),
    search_sex varchar(100),
    search_sexual_taste varchar(100),
    search_min_age int(3),
    search_max_age int(3),
    search_distance float(11),
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
    PRIMARY KEY (matching_id),
    FOREIGN KEY (matching_account_do) REFERENCES ACCOUNT(account_id),
    FOREIGN KEY (matching_account_done) REFERENCES ACCOUNT(account_id)
);
